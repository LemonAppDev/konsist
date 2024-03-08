package com.lemonappdev.konsist.core.declaration.koobject

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldNotBeEqualTo
import org.junit.jupiter.api.Test

class KoObjectDeclarationForKoKDocProviderTest {
    @Test
    fun `object-without-kdoc`() {
        // given
        val sut =
            getSnippetFile("object-without-kdoc")
                .objects()
                .first()

        // then
        assertSoftly(sut) {
            kDoc shouldBeEqualTo null
            hasKDoc shouldBeEqualTo false
        }
    }

    @Test
    fun `object-with-kdoc`() {
        // given
        val sut =
            getSnippetFile("object-with-kdoc")
                .objects()
                .first()

        // then
        assertSoftly(sut) {
            kDoc shouldNotBeEqualTo null
            hasKDoc shouldBeEqualTo true
        }
    }

    @Test
    fun `object-with-one-line-kdoc`() {
        // given
        val sut =
            getSnippetFile("object-with-one-line-kdoc")
                .objects()
                .first()

        // then
        assertSoftly(sut) {
            kDoc shouldNotBeEqualTo null
            hasKDoc shouldBeEqualTo true
        }
    }

    private fun getSnippetFile(fileName: String) = getSnippetKoScope("core/declaration/koobject/snippet/forkokdocprovider/", fileName)
}
