package com.lemonappdev.konsist.core.declaration.kokdocdeclaration

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import com.lemonappdev.konsist.api.KoKDocTag.SAMPLE
import com.lemonappdev.konsist.api.KoKDocTag.SEE
import com.lemonappdev.konsist.api.KoKDocTag.SINCE
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoKDocDeclarationForHasTagTest {
    @Test
    fun `class-has-tags`() {
        // given
        val sut = getSnippetFile("class-has-tags")
            .classes()
            .first()
            .kDoc

        // then
        assertSoftly(sut) {
            it?.hasTags(SINCE) shouldBeEqualTo true
            it?.hasTags(SINCE, SEE) shouldBeEqualTo true
            it?.hasTags(SAMPLE) shouldBeEqualTo false
            it?.hasTags(SINCE, SAMPLE) shouldBeEqualTo false
            it?.hasTags(SINCE, SEE, SAMPLE) shouldBeEqualTo false
        }
    }

    @Test
    fun `class-has-one-line-kdoc`() {
        // given
        val sut = getSnippetFile("class-has-one-line-kdoc")
            .classes()
            .first()
            .kDoc

        // then
        assertSoftly(sut) {
            it?.hasTags() shouldBeEqualTo true
            it?.hasTags(SINCE) shouldBeEqualTo true
            it?.hasTags(SINCE, SEE) shouldBeEqualTo false
        }
    }

    @Test
    fun `class-has-tags-without-description`() {
        // given
        val sut = getSnippetFile("class-has-tags-without-description")
            .classes()
            .first()
            .kDoc

        // then
        assertSoftly(sut) {
            it?.description shouldBeEqualTo ""
            it?.hasTags(SINCE) shouldBeEqualTo true
            it?.hasTags(SINCE, SEE) shouldBeEqualTo true
            it?.hasTags(SAMPLE) shouldBeEqualTo false
            it?.hasTags(SINCE, SAMPLE) shouldBeEqualTo false
            it?.hasTags(SINCE, SEE, SAMPLE) shouldBeEqualTo false
        }
    }

    private fun getSnippetFile(fileName: String) = getSnippetKoScope("core/declaration/kokdocdeclaration/snippet/forhastag/", fileName)
}
