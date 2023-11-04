package com.lemonappdev.konsist.core.declaration.koclass

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldNotBeEqualTo
import org.junit.jupiter.api.Test

class KoClassDeclarationForKoKDocProviderTest {
    @Test
    fun `class-without-kdoc`() {
        // given
        val sut =
            getSnippetFile("class-without-kdoc")
                .classes()
                .first()

        // then
        assertSoftly(sut) {
            kDoc shouldBeEqualTo null
            hasKDoc shouldBeEqualTo false
        }
    }

    @Test
    fun `class-with-kdoc`() {
        // given
        val sut =
            getSnippetFile("class-with-kdoc")
                .classes()
                .first()

        // then
        assertSoftly(sut) {
            kDoc shouldNotBeEqualTo null
            hasKDoc shouldBeEqualTo true
        }
    }

    @Test
    fun `class-with-one-line-kdoc`() {
        // given
        val sut =
            getSnippetFile("class-with-one-line-kdoc")
                .classes()
                .first()

        // then
        assertSoftly(sut) {
            kDoc shouldNotBeEqualTo null
            hasKDoc shouldBeEqualTo true
        }
    }

    private fun getSnippetFile(fileName: String) = getSnippetKoScope("core/declaration/koclass/snippet/forkokdocprovider/", fileName)
}
