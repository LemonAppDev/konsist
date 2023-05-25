package com.lemonappdev.konsist.core.container.kofile

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoFileForSourceSetTest {
    @Test
    fun `file-source-set-name`() {
        // given
        val sut = getSnippetFile("file-source-set-name")
            .files()
            .first()

        // then
        sut.containingSourceSetName shouldBeEqualTo "integrationTest"
    }

    @Test
    fun `file-has-source-set-name`() {
        // given
        val sut = getSnippetFile("file-has-source-set-name")
            .files()
            .first()

        // then
        assertSoftly(sut) {
            resideInSourceSet("integrationTest") shouldBeEqualTo true
            resideInSourceSet("test") shouldBeEqualTo false
        }
    }

    private fun getSnippetFile(fileName: String) = getSnippetKoScope("core/container/kofile/snippet/forsourceset/", fileName)
}
