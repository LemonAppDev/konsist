package com.lemonappdev.konsist.core.declaration.kodoc

import com.lemonappdev.konsist.TestSnippetProvider
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldContain
import org.junit.jupiter.api.Test

class KoDocTest {
    @Test
    fun `kdoc-without-block-tags`() {
        // given
        val sut = getSnippetFile("kdoc-without-block-tags")
            .classes()
            .first()
            .koDoc

        // then
        sut
            ?.text
            ?.shouldContain(("Sample KDoc"))
    }

    @Test
    fun `kdoc-with-one-block-tag`() {
        // given
        val sut = getSnippetFile("kdoc-with-one-block-tag")
            .classes()
            .first()
            .koDoc

        // then
        assertSoftly(sut) {
            it?.text?.shouldContain((("Sample KDoc")))
            it?.text?.shouldContain((("@author SampleName")))
        }
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("core/declaration/kodoc/snippet/", fileName)
}
