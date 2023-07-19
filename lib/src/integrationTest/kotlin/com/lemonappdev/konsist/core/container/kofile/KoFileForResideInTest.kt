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
            resideInPath("..snippet..", absolutePath = true) shouldBeEqualTo true
            resideInPath("..kofile/snippet..", absolutePath = true) shouldBeEqualTo true
            resideInPath("..kofile..reside-in-path.kt", absolutePath = true) shouldBeEqualTo true
            resideInPath("kofile/snippet/", absolutePath = true) shouldBeEqualTo false
        }
    }

    @Test
    fun `reside-in-project-path`() {
        // given
        val sut = getSnippetFile("reside-in-project-path")
            .files()
            .first()

        // then
        assertSoftly(sut) {
            resideInPath("..snippet..", absolutePath = false) shouldBeEqualTo true
            resideInPath("..kofile/snippet..", absolutePath = false) shouldBeEqualTo true
            resideInPath("..kofile..reside-in-project-path.kt", absolutePath = false) shouldBeEqualTo true
            resideInPath("kofile/snippet/", absolutePath = false) shouldBeEqualTo false
        }
    }

    private fun getSnippetFile(fileName: String) = getSnippetKoScope("core/container/kofile/snippet/forresidein/", fileName)
}
