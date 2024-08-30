package com.lemonappdev.konsist.core.filesystem.rootprovider

import com.lemonappdev.konsist.core.filesystem.PathVerifier
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test
import java.io.File

class ProjectRootDirResolverTest {
    private class Sut(
        pathVerifier: PathVerifier,
    ) : ProjectRootDirResolver(pathVerifier) {
        override val paths = setOf("/path-1", "/path-2")
    }

    private val pathVerifier = mockk<PathVerifier>()

    private val sut = Sut(pathVerifier)

    @Test
    fun `should return project root directory when any files exists`() {
        // given
        val file = mockk<File>()
        every { pathVerifier.verifyPathIfExists(file, "/path-1") } returns true
        every { pathVerifier.verifyPathIfExists(file, "/path-2") } returns true

        // when
        val actual = sut.getProjectRootDir(file)

        // then
        actual shouldBeEqualTo file
    }

    @Test
    fun `should return null none of the files exist`() {
        // given
        val file = mockk<File>()

        every { pathVerifier.verifyPathIfExists(file, "/path-1") } returns false
        every { pathVerifier.verifyPathIfExists(file, "/path-2") } returns false

        // when
        val actual = sut.getProjectRootDir(file)

        // then
        actual shouldBeEqualTo null
    }
}
