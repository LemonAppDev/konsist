package com.lemonappdev.konsist.core.provider.util

import com.lemonappdev.konsist.core.filesystem.PathProvider
import com.lemonappdev.konsist.core.util.PathUtil
import io.mockk.every
import io.mockk.mockkObject
import io.mockk.unmockkObject
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.parallel.Isolated

/**
 * Runs in isolation, because it mocks global objects.
 */
@Isolated
class KoFileDeclarationProviderTest {
    @BeforeEach
    fun setUp() {
        // KoFileDeclarationProvider must be mocked before PathProvider, because its init block verifies project root path
        mockkObject(KoFileDeclarationProvider)
        mockkObject(PathProvider)
        every { PathProvider.rootProjectPath } returns PROJECT_ROOT_PATH
    }

    @AfterEach
    fun tearDown() {
        unmockkObject(PathProvider, KoFileDeclarationProvider)
    }

    @Test
    fun `isBuildConfigFile returns true for file in buildSrc directory`() {
        // when
        val result =
            KoFileDeclarationProvider.isBuildConfigFile(
                PathUtil.toOsSeparator("$PROJECT_ROOT_PATH/buildSrc/src/main/kotlin/SampleClass.kt"),
            )

        // then
        result shouldBeEqualTo true
    }

    @Test
    fun `isBuildConfigFile returns false for module file when project root is located in buildSrc directory`() {
        // when
        val result =
            KoFileDeclarationProvider.isBuildConfigFile(
                PathUtil.toOsSeparator("$PROJECT_ROOT_PATH/app/src/main/kotlin/SampleClass.kt"),
            )

        // then
        result shouldBeEqualTo false
    }

    companion object {
        private const val PROJECT_ROOT_PATH = "/Users/user/buildSrc/project"
    }
}
