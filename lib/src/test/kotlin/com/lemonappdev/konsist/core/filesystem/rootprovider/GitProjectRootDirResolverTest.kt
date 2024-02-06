package com.lemonappdev.konsist.core.filesystem.rootprovider

import com.lemonappdev.konsist.core.filesystem.PathVerifier
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test
import java.io.File

class GitProjectRootDirResolverTest {
    @Test
    fun `git dir resolver is using the correct git folder paths`() {
        // given
        val file = mockk<File>()
        val pathVerifier = mockk<PathVerifier>()
        every { pathVerifier.verifyPathIfExists(file, ".git/config") } returns true
        every { pathVerifier.verifyPathIfExists(file, ".git/HEAD") } returns true
        every { pathVerifier.verifyPathIfExists(file, ".git/refs") } returns true
        val sut = GitProjectRootDirResolver(pathVerifier)

        // when
        val actual = sut.getProjectRootDir(file)

        // then
        actual shouldBeEqualTo file
    }
}
