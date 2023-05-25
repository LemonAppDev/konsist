package com.lemonappdev.konsist.core.container.kofile

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoFileForResideInTest {
    @Test
    fun `reside-in-path`() {
        // given
        val sut = getSnippetFile("reside-in-path")
            .files()
            .first()

        // then
        assertSoftly(sut) {
            resideInPath("..snippet..") shouldBeEqualTo true
            resideInPath("..kofile/snippet..") shouldBeEqualTo true
            resideInPath("..kofile..reside-in-path.kt") shouldBeEqualTo true
            resideInPath("kofile/snippet/") shouldBeEqualTo false
        }
    }

    @Test
    fun `reside-in-root-project-path`() {
        // given
        val sut = getSnippetFile("reside-in-root-project-path")
            .files()
            .first()

        // then
        assertSoftly(sut) {
            resideInRootProjectPath("..snippet..") shouldBeEqualTo true
            resideInRootProjectPath("..kofile/snippet..") shouldBeEqualTo true
            resideInRootProjectPath("..kofile..reside-in-root-project-path.kt") shouldBeEqualTo true
            resideInRootProjectPath("kofile/snippet/") shouldBeEqualTo false
        }
    }

    private fun getSnippetFile(fileName: String) = getSnippetKoScope("core/container/kofile/snippet/forresidein/", fileName)
}
