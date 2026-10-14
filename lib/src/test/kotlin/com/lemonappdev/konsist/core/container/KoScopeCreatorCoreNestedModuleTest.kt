package com.lemonappdev.konsist.core.container

import com.lemonappdev.konsist.api.container.KoScope
import com.lemonappdev.konsist.api.declaration.KoFileDeclaration
import com.lemonappdev.konsist.core.filesystem.ModuleDirectoryDetector
import com.lemonappdev.konsist.core.filesystem.PathProvider
import com.lemonappdev.konsist.core.provider.util.KoFileDeclarationProvider
import com.lemonappdev.konsist.core.util.ModuleUtil
import com.lemonappdev.konsist.core.util.PathUtil
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkObject
import io.mockk.unmockkObject
import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldContain
import org.amshove.kluent.shouldNotContain
import org.amshove.kluent.shouldThrow
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.parallel.Isolated
import java.io.File

/**
 * Project with parent module that has its own source sets ("feature") and nested child module ("feature/payment").
 * It also contains sibling nested modules with the same name ("feature/authentication/data" and "feature/main/data")
 * and Kotlin files located outside the "src" directory ("feature/FeatureScript.kt", "feature/payment/PaymentScript.kt"
 * and "feature/config/FeatureConfig.kt", where "feature/config" is not a module directory).
 * Module "feature/payment" contains Kotlin files located outside the "src" directory in directories named like a test
 * source set ("feature/payment/test/PaymentTestScript.kt" and "feature/payment/testData/PaymentTestData.kt").
 * Module "src/tools" is located in a directory named "src" and contains Kotlin files located outside its source root
 * ("src/tools/ToolsScript.kt" and "src/tools/src/test/testData/ToolsFixture.kt").
 * Module "app" contains Gradle test fixture project located in resources ("app/src/functionalTest/resources/project"),
 * which is not a module.
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
                FEATURE_SCRIPT,
                FEATURE_CONFIG,
                PAYMENT_MAIN,
                PAYMENT_SCRIPT,
                PAYMENT_TEST,
                PAYMENT_TEST_SCRIPT,
                PAYMENT_TEST_DATA,
                AUTHENTICATION_DATA_MAIN,
                AUTHENTICATION_DATA_TEST,
                MAIN_DATA_MAIN,
                APP_MAIN,
                SRC_TOOLS_MAIN,
                SRC_TOOLS_SCRIPT,
                SRC_TOOLS_FIXTURE,
                APP_FIXTURE_PROJECT_SCRIPT,
                APP_FIXTURE_PROJECT_MAIN,
            ).filter { predicate(File(it)) }
                .map { createKoFile(it) }
        }
        // Package name is read without parsing the file
        every { KoFileDeclarationProvider.getPackageName(any()) } returns PACKAGE_NAME

        mockkObject(PathProvider)
        every { PathProvider.rootProjectPath } returns PROJECT_ROOT_PATH

        // Project files do not exist, so module directories of files located outside the "src" directory are mocked
        mockkObject(ModuleDirectoryDetector)
        every { ModuleDirectoryDetector.getModuleDirectory(PROJECT_ROOT_PATH, any()) } answers { MODULE_DIRECTORIES[secondArg()] }
    }

    @AfterEach
    fun tearDown() {
        unmockkObject(ModuleDirectoryDetector, PathProvider, KoFileDeclarationProvider)
    }

    @Test
    fun `scopeFromModule for parent module does not contain files from nested module`() {
        // when
        val result = sut.scopeFromModule("feature").filePaths()

        // then
        result shouldBeEqualTo listOf(FEATURE_SCRIPT, FEATURE_CONFIG, FEATURE_MAIN, FEATURE_TEST)
    }

    @Test
    fun `scopeFromModule for nested module does not contain files from parent module`() {
        // when
        val result = sut.scopeFromModule(":feature:payment").filePaths()

        // then
        result shouldBeEqualTo listOf(PAYMENT_SCRIPT, PAYMENT_MAIN, PAYMENT_TEST, PAYMENT_TEST_SCRIPT, PAYMENT_TEST_DATA)
    }

    @Test
    fun `scopeFromModule for parent and nested module contains files from both modules`() {
        // when
        val result = sut.scopeFromModule(":feature", ":feature:payment").filePaths()

        // then
        result shouldBeEqualTo
            listOf(
                FEATURE_SCRIPT,
                FEATURE_CONFIG,
                PAYMENT_SCRIPT,
                PAYMENT_MAIN,
                PAYMENT_TEST,
                PAYMENT_TEST_SCRIPT,
                PAYMENT_TEST_DATA,
                FEATURE_MAIN,
                FEATURE_TEST,
            )
    }

    @Test
    fun `scopeFromModule for nested module does not contain files from sibling module with the same name`() {
        // when
        val result = sut.scopeFromModule(":feature:authentication:data").filePaths()

        // then
        result shouldBeEqualTo listOf(AUTHENTICATION_DATA_MAIN, AUTHENTICATION_DATA_TEST)
    }

    @Test
    fun `scopeFromModule for sibling nested modules with the same name contains files from both modules`() {
        // when
        val result = sut.scopeFromModule(":feature:authentication:data", "feature/main/data").filePaths()

        // then
        result shouldBeEqualTo listOf(AUTHENTICATION_DATA_MAIN, AUTHENTICATION_DATA_TEST, MAIN_DATA_MAIN)
    }

    @Test
    fun `scopeFromModule for nested module name without parent path returns empty scope`() {
        // when
        val result = sut.scopeFromModule("data").filePaths()

        // then
        result shouldBeEqualTo emptyList()
    }

    @Test
    fun `scopeFromProject for parent module does not contain files from nested module`() {
        // when
        val result = sut.scopeFromProject(moduleName = "feature").filePaths()

        // then
        result shouldBeEqualTo listOf(FEATURE_SCRIPT, FEATURE_CONFIG, FEATURE_MAIN, FEATURE_TEST)
    }

    @Test
    fun `scopeFromProduction for parent module does not contain files from nested module`() {
        // when
        val result = sut.scopeFromProduction(moduleName = "feature").filePaths()

        // then
        result shouldBeEqualTo listOf(FEATURE_SCRIPT, FEATURE_CONFIG, FEATURE_MAIN)
    }

    @Test
    fun `scopeFromProduction for nested module`() {
        // when
        val result = sut.scopeFromProduction(moduleName = "feature/payment").filePaths()

        // then
        result shouldBeEqualTo listOf(PAYMENT_SCRIPT, PAYMENT_MAIN)
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
        result shouldBeEqualTo listOf(PAYMENT_TEST, PAYMENT_TEST_SCRIPT, PAYMENT_TEST_DATA)
    }

    @Test
    fun `scopeFromTest for nested gradle project path and source set`() {
        // when
        val result = sut.scopeFromTest(moduleName = ":feature:payment", sourceSetName = "test").filePaths()

        // then
        result shouldBeEqualTo listOf(PAYMENT_TEST)
    }

    @Test
    fun `scopeFromProduction does not contain files located in test directories outside src directory`() {
        // when
        val result = sut.scopeFromProduction(moduleName = ":feature:payment").filePaths()

        // then
        result shouldNotContain PAYMENT_TEST_SCRIPT
        result shouldNotContain PAYMENT_TEST_DATA
    }

    @Test
    fun `scopeFromTest contains files located in test directories outside src directory`() {
        // when
        val result = sut.scopeFromTest(moduleName = ":feature:payment").filePaths()

        // then
        result shouldContain PAYMENT_TEST_SCRIPT
        result shouldContain PAYMENT_TEST_DATA
    }

    @Test
    fun `scopeFromTest does not contain files located in non-test directories outside src directory`() {
        // when
        val result = sut.scopeFromTest(moduleName = ":feature").filePaths()

        // then
        result shouldNotContain FEATURE_SCRIPT
        result shouldNotContain FEATURE_CONFIG
    }

    @Test
    fun `scopeFromPackage for parent module does not contain files from nested module`() {
        // when
        val result = sut.scopeFromPackage(PACKAGE_NAME, moduleName = "feature").filePaths()

        // then
        result shouldBeEqualTo listOf(FEATURE_SCRIPT, FEATURE_CONFIG, FEATURE_MAIN, FEATURE_TEST)
    }

    @Test
    fun `scopeFromPackage for nested gradle project path and source set`() {
        // when
        val result = sut.scopeFromPackage(PACKAGE_NAME, moduleName = ":feature:payment", sourceSetName = "main").filePaths()

        // then
        result shouldBeEqualTo listOf(PAYMENT_MAIN)
    }

    @Test
    fun `scopeFromModule contains the same files as project scope files with given module name`() {
        // given
        val projectFiles = sut.scopeFromProject().files

        listOf("root", "feature", "feature/payment", "feature/authentication/data", "app", "src/tools").forEach { moduleName ->
            // when
            val result = sut.scopeFromModule(moduleName).filePaths()

            // then
            result shouldBeEqualTo projectFiles.filter { it.moduleName == moduleName }.map { it.path }
        }
    }

    @Test
    fun `scopeFromProject for module and source set does not contain files located outside src directory`() {
        // when
        val result = sut.scopeFromProject(moduleName = "feature", sourceSetName = "main").filePaths()

        // then
        result shouldBeEqualTo listOf(FEATURE_MAIN)
    }

    @Test
    fun `scopeFromModule for parent module name with wildcard contains files from parent and nested modules`() {
        // when
        val result = sut.scopeFromModule(":feature..").filePaths()

        // then
        result shouldBeEqualTo
            listOf(
                FEATURE_SCRIPT,
                AUTHENTICATION_DATA_MAIN,
                AUTHENTICATION_DATA_TEST,
                FEATURE_CONFIG,
                MAIN_DATA_MAIN,
                PAYMENT_SCRIPT,
                PAYMENT_MAIN,
                PAYMENT_TEST,
                PAYMENT_TEST_SCRIPT,
                PAYMENT_TEST_DATA,
                FEATURE_MAIN,
                FEATURE_TEST,
            )
    }

    @Test
    fun `scopeFromModule for overlapping module names with wildcard contains each file once`() {
        // when
        val result = sut.scopeFromModule(":feature:payment..", ":feature:payment").filePaths()

        // then
        result shouldBeEqualTo listOf(PAYMENT_SCRIPT, PAYMENT_MAIN, PAYMENT_TEST, PAYMENT_TEST_SCRIPT, PAYMENT_TEST_DATA)
    }

    @Test
    fun `scopeFromModule for module name with leading wildcard contains files from sibling nested modules`() {
        // when
        val result = sut.scopeFromModule("..data").filePaths()

        // then
        result shouldBeEqualTo listOf(AUTHENTICATION_DATA_MAIN, AUTHENTICATION_DATA_TEST, MAIN_DATA_MAIN)
    }

    @Test
    fun `scopeFromModule for wildcard contains files from all modules`() {
        // when
        val result = sut.scopeFromModule("..").filePaths()

        // then
        result shouldBeEqualTo sut.scopeFromProject().filePaths()
    }

    @Test
    fun `scopeFromProduction for parent module name with wildcard and source set`() {
        // when
        val result = sut.scopeFromProduction(moduleName = ":feature..", sourceSetName = "main").filePaths()

        // then
        result shouldBeEqualTo listOf(AUTHENTICATION_DATA_MAIN, MAIN_DATA_MAIN, PAYMENT_MAIN, FEATURE_MAIN)
    }

    @Test
    fun `scopeFromTest for parent module name with wildcard`() {
        // when
        val result = sut.scopeFromTest(moduleName = ":feature..").filePaths()

        // then
        result shouldBeEqualTo listOf(AUTHENTICATION_DATA_TEST, PAYMENT_TEST, PAYMENT_TEST_SCRIPT, PAYMENT_TEST_DATA, FEATURE_TEST)
    }

    @Test
    fun `scopeFromModule for module located in src directory`() {
        // when
        val result = sut.scopeFromModule(":src:tools").filePaths()

        // then
        result shouldBeEqualTo listOf(SRC_TOOLS_SCRIPT, SRC_TOOLS_MAIN, SRC_TOOLS_FIXTURE)
    }

    @Test
    fun `scopeFromModule for root module does not contain files from module located in src directory`() {
        // when
        val result = sut.scopeFromModule("root").filePaths()

        // then
        result shouldBeEqualTo emptyList()
    }

    @Test
    fun `scopeFromProduction for module located in src directory and source set`() {
        // when
        val result = sut.scopeFromProduction(moduleName = ":src:tools", sourceSetName = "main").filePaths()

        // then
        result shouldBeEqualTo listOf(SRC_TOOLS_MAIN)
    }

    @Test
    fun `scopeFromSourceSet for module name located in src directory returns empty scope`() {
        // when
        val result = sut.scopeFromSourceSet("tools").filePaths()

        // then
        result shouldBeEqualTo emptyList()
    }

    @Test
    fun `scopeFromProduction for module located in src directory`() {
        // when
        val result = sut.scopeFromProduction(moduleName = ":src:tools").filePaths()

        // then
        result shouldBeEqualTo listOf(SRC_TOOLS_SCRIPT, SRC_TOOLS_MAIN)
    }

    @Test
    fun `scopeFromTest for module located in src directory contains file located outside source root`() {
        // when
        val result = sut.scopeFromTest(moduleName = ":src:tools").filePaths()

        // then
        result shouldBeEqualTo listOf(SRC_TOOLS_FIXTURE)
    }

    @Test
    fun `scopeFromSourceSet contains the same files as project scope files with given source set name`() {
        // given
        val projectFiles = sut.scopeFromProject().files

        listOf("main", "test", "functionalTest", "tools").forEach { sourceSetName ->
            // when
            val result = sut.scopeFromSourceSet(sourceSetName).filePaths()

            // then
            result shouldBeEqualTo projectFiles.filter { it.sourceSetName == sourceSetName }.map { it.path }
        }
    }

    @Test
    fun `scopeFromModule contains files from project located in resources`() {
        // when
        val result = sut.scopeFromModule(":app").filePaths()

        // then
        result shouldBeEqualTo listOf(APP_FIXTURE_PROJECT_SCRIPT, APP_FIXTURE_PROJECT_MAIN, APP_MAIN)
    }

    @Test
    fun `scopeFromTest contains files from project located in test resources`() {
        // when
        val result = sut.scopeFromTest(moduleName = ":app").filePaths()

        // then
        result shouldBeEqualTo listOf(APP_FIXTURE_PROJECT_SCRIPT, APP_FIXTURE_PROJECT_MAIN)
    }

    @Test
    fun `scopeFromProduction does not contain files from project located in test resources`() {
        // when
        val result = sut.scopeFromProduction(moduleName = ":app").filePaths()

        // then
        result shouldBeEqualTo listOf(APP_MAIN)
    }

    @Test
    fun `scopeFromModule for parent module contains files from its subdirectory that is not a module directory`() {
        // when
        val result = sut.scopeFromModule(":feature").filePaths()

        // then
        result shouldContain FEATURE_CONFIG
    }

    @Test
    fun `scopeFromModule for subdirectory that is not a module directory returns empty scope`() {
        // when
        val result = sut.scopeFromModule(":feature:config").filePaths()

        // then
        result shouldBeEqualTo emptyList()
    }

    @Test
    fun `scopeFromModule for module name with three dots throws exception`() {
        // given
        val func = { sut.scopeFromModule(":feature...") }

        // then
        func shouldThrow IllegalArgumentException::class
    }

    @Test
    fun `scopeFromProject for module name with three dots throws exception`() {
        // given
        val func = { sut.scopeFromProject(moduleName = "...data") }

        // then
        func shouldThrow IllegalArgumentException::class
    }

    private fun KoScope.filePaths() = files.map { it.path }

    private fun createKoFile(filePath: String): KoFileDeclaration {
        val projectPath = filePath.removePrefix(PROJECT_ROOT_PATH)

        return mockk {
            every { path } returns filePath
            every { sourceSetName } returns PathUtil.getSourceSetName(projectPath)
            every { moduleName } returns ModuleUtil.getModuleName(projectPath)
        }
    }

    companion object {
        private const val PROJECT_ROOT_PATH = "/project"
        private const val PACKAGE_NAME = "com.app"
        private const val FEATURE_MAIN = "$PROJECT_ROOT_PATH/feature/src/main/kotlin/FeatureMain.kt"
        private const val FEATURE_TEST = "$PROJECT_ROOT_PATH/feature/src/test/kotlin/FeatureTest.kt"
        private const val FEATURE_SCRIPT = "$PROJECT_ROOT_PATH/feature/FeatureScript.kt"
        private const val FEATURE_CONFIG = "$PROJECT_ROOT_PATH/feature/config/FeatureConfig.kt"
        private const val PAYMENT_SCRIPT = "$PROJECT_ROOT_PATH/feature/payment/PaymentScript.kt"
        private const val PAYMENT_MAIN = "$PROJECT_ROOT_PATH/feature/payment/src/main/kotlin/PaymentMain.kt"
        private const val PAYMENT_TEST = "$PROJECT_ROOT_PATH/feature/payment/src/test/kotlin/PaymentTest.kt"
        private const val PAYMENT_TEST_SCRIPT = "$PROJECT_ROOT_PATH/feature/payment/test/PaymentTestScript.kt"
        private const val PAYMENT_TEST_DATA = "$PROJECT_ROOT_PATH/feature/payment/testData/PaymentTestData.kt"
        private const val AUTHENTICATION_DATA_MAIN =
            "$PROJECT_ROOT_PATH/feature/authentication/data/src/main/kotlin/AuthenticationDataMain.kt"
        private const val AUTHENTICATION_DATA_TEST =
            "$PROJECT_ROOT_PATH/feature/authentication/data/src/test/kotlin/AuthenticationDataTest.kt"
        private const val MAIN_DATA_MAIN = "$PROJECT_ROOT_PATH/feature/main/data/src/main/kotlin/MainDataMain.kt"
        private const val APP_MAIN = "$PROJECT_ROOT_PATH/app/src/main/kotlin/AppMain.kt"
        private const val SRC_TOOLS_MAIN = "$PROJECT_ROOT_PATH/src/tools/src/main/kotlin/ToolsMain.kt"
        private const val SRC_TOOLS_SCRIPT = "$PROJECT_ROOT_PATH/src/tools/ToolsScript.kt"
        private const val SRC_TOOLS_FIXTURE = "$PROJECT_ROOT_PATH/src/tools/src/test/testData/ToolsFixture.kt"
        private const val APP_FIXTURE_PROJECT_SCRIPT =
            "$PROJECT_ROOT_PATH/app/src/functionalTest/resources/project/FixtureScript.kt"
        private const val APP_FIXTURE_PROJECT_MAIN =
            "$PROJECT_ROOT_PATH/app/src/functionalTest/resources/project/src/main/kotlin/FixtureMain.kt"

        // Module directories of directories containing files located outside the source root
        private val MODULE_DIRECTORIES =
            mapOf(
                "/feature" to "/feature",
                "/feature/config" to "/feature",
                "/feature/payment" to "/feature/payment",
                "/feature/payment/test" to "/feature/payment",
                "/feature/payment/testData" to "/feature/payment",
                "/src/tools" to "/src/tools",
                "/src/tools/src/test/testData" to "/src/tools",
                "/app/src/functionalTest/resources/project" to "/app/src/functionalTest/resources/project",
            )
    }
}
