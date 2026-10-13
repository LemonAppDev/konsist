package com.lemonappdev.konsist.core.filesystem.rootprovider

import com.lemonappdev.konsist.core.filesystem.PathVerifier
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import java.io.File

class GradleProjectRootDirResolverTest {
    @ParameterizedTest
    @CsvSource(
        "true, true, true",
        "true, false, false",
        "false, true, false",
        "false, false, false",
    )
    fun `gradle dir resolver requires wrapper and settings file`(
        hasWrapper: Boolean,
        hasSettings: Boolean,
        isRoot: Boolean,
    ) {
        // given
        val file = mockk<File>()
        val pathVerifier = mockk<PathVerifier>()
        every { pathVerifier.verifyPathIfExists(file, any()) } returns false
        every { pathVerifier.verifyPathIfExists(file, "gradlew") } returns hasWrapper
        every { pathVerifier.verifyPathIfExists(file, "settings.gradle.kts") } returns hasSettings
        val sut = GradleProjectRootDirResolver(pathVerifier)

        // when
        val actual = sut.getProjectRootDir(file)

        // then
        actual shouldBeEqualTo if (isRoot) file else null
    }
}
