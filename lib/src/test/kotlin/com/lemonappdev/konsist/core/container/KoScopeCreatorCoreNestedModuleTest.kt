package com.lemonappdev.konsist.core.container

import com.lemonappdev.konsist.api.container.KoScope
import com.lemonappdev.konsist.api.declaration.KoFileDeclaration
import com.lemonappdev.konsist.core.filesystem.PathProvider
import com.lemonappdev.konsist.core.provider.util.KoFileDeclarationProvider
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkObject
import io.mockk.unmockkObject
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.parallel.Isolated
import java.io.File

/**
 * Project with parent module that has its own source sets ("feature") and nested child module ("feature/payment").
 * Runs in isolation, because it mocks global objects.
 */
@Isolated
class KoScopeCreatorCoreNestedModuleTest {
    private val sut = KoScopeCreatorCore()

    @BeforeEach
    fun setUp() {
        // KoFileDeclarationProvider must be mocked before PathProvider, because its init block verifies project root path
        mockkObject(KoFileDeclarationProvider)
        // Provider filters files before parsing them
        coEvery { KoFileDeclarationProvider.getKoFileDeclarations(any()) } answers {
            val predicate = firstArg<(File) -> Boolean>()

            listOf(
                FEATURE_MAIN,
                FEATURE_TEST,
                PAYMENT_MAIN,
                PAYMENT_TEST,
                APP_MAIN,
            ).filter { predicate(File(it)) }
                .map { createKoFile(it) }
        }

        mockkObject(PathProvider)
        every { PathProvider.rootProjectPath } returns PROJECT_ROOT_PATH
    }

    @AfterEach
    fun tearDown() {
        unmockkObject(PathProvider, KoFileDeclarationProvider)
    }

    @Test
    fun `scopeFromModule for parent module does not contain files from nested module`() {
        // when
        val result = sut.scopeFromModule("feature").filePaths()

        // then
        result shouldBeEqualTo listOf(FEATURE_MAIN, FEATURE_TEST)
    }

    @Test
    fun `scopeFromModule for nested module does not contain files from parent module`() {
        // when
        val result = sut.scopeFromModule(":feature:payment").filePaths()

        // then
        result shouldBeEqualTo listOf(PAYMENT_MAIN, PAYMENT_TEST)
    }

    @Test
    fun `scopeFromModule for parent and nested module contains files from both modules`() {
        // when
        val result = sut.scopeFromModule(":feature", ":feature:payment").filePaths()

        // then
        result shouldBeEqualTo listOf(PAYMENT_MAIN, PAYMENT_TEST, FEATURE_MAIN, FEATURE_TEST)
    }

    @Test
    fun `scopeFromProject for parent module does not contain files from nested module`() {
        // when
        val result = sut.scopeFromProject(moduleName = "feature").filePaths()

        // then
        result shouldBeEqualTo listOf(FEATURE_MAIN, FEATURE_TEST)
    }

    @Test
    fun `scopeFromProduction for parent module does not contain files from nested module`() {
        // when
        val result = sut.scopeFromProduction(moduleName = "feature").filePaths()

        // then
        result shouldBeEqualTo listOf(FEATURE_MAIN)
    }

    @Test
    fun `scopeFromProduction for nested module`() {
        // when
        val result = sut.scopeFromProduction(moduleName = "feature/payment").filePaths()

        // then
        result shouldBeEqualTo listOf(PAYMENT_MAIN)
    }

    @Test
    fun `scopeFromProduction for nested gradle project path and source set`() {
        // when
        val result = sut.scopeFromProduction(moduleName = ":feature:payment", sourceSetName = "main").filePaths()

        // then
        result shouldBeEqualTo listOf(PAYMENT_MAIN)
    }

    @Test
    fun `scopeFromTest for parent module does not contain files from nested module`() {
        // when
        val result = sut.scopeFromTest(moduleName = "feature").filePaths()

        // then
        result shouldBeEqualTo listOf(FEATURE_TEST)
    }

    @Test
    fun `scopeFromTest for nested module`() {
        // when
        val result = sut.scopeFromTest(moduleName = "feature/payment").filePaths()

        // then
        result shouldBeEqualTo listOf(PAYMENT_TEST)
    }

    @Test
    fun `scopeFromTest for nested gradle project path and source set`() {
        // when
        val result = sut.scopeFromTest(moduleName = ":feature:payment", sourceSetName = "test").filePaths()

        // then
        result shouldBeEqualTo listOf(PAYMENT_TEST)
    }

    private fun KoScope.filePaths() = files.map { it.path }

    private fun createKoFile(filePath: String): KoFileDeclaration {
        val projectPath = filePath.removePrefix(PROJECT_ROOT_PATH)

        return mockk {
            every { path } returns filePath
            every { sourceSetName } returns projectPath.substringAfter("/src/").substringBefore("/")
        }
    }

    companion object {
        private const val PROJECT_ROOT_PATH = "/project"
        private const val FEATURE_MAIN = "$PROJECT_ROOT_PATH/feature/src/main/kotlin/FeatureMain.kt"
        private const val FEATURE_TEST = "$PROJECT_ROOT_PATH/feature/src/test/kotlin/FeatureTest.kt"
        private const val PAYMENT_MAIN = "$PROJECT_ROOT_PATH/feature/payment/src/main/kotlin/PaymentMain.kt"
        private const val PAYMENT_TEST = "$PROJECT_ROOT_PATH/feature/payment/src/test/kotlin/PaymentTest.kt"
        private const val APP_MAIN = "$PROJECT_ROOT_PATH/app/src/main/kotlin/AppMain.kt"
    }
}
