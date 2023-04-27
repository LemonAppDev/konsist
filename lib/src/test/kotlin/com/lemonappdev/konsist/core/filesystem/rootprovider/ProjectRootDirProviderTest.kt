package com.lemonappdev.konsist.core.filesystem.rootprovider

import com.lemonappdev.konsist.core.filesystem.PathVerifier
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test
import java.io.File

class ProjectRootDirProviderTest {
    private class Sut(pathVerifier: PathVerifier) : ProjectRootDirProvider(pathVerifier) {
        override val paths = setOf("/path1", "/path2")
    }

    private val pathVerifier = mockk<PathVerifier>()

    private val sut = Sut(pathVerifier)

    @Test
    fun `should return project root directory when all files exists`() {
        // given
        val file = mockk<File>()
        every { pathVerifier.verifyPathIfExists(file, "/path1") } returns true
        every { pathVerifier.verifyPathIfExists(file, "/path2") } returns true

        // when
        val actual = sut.getDir(file)

        // then
        actual shouldBeEqualTo file
    }

    @Test
    fun `should return null when all files do not exist`() {
        // given
        val file = mockk<File>()
        every { pathVerifier.verifyPathIfExists(file, "/path1") } returns true
        every { pathVerifier.verifyPathIfExists(file, "/path2") } returns false

        // when
        val actual = sut.getDir(file)

        // then
        actual shouldBeEqualTo null
    }
}
