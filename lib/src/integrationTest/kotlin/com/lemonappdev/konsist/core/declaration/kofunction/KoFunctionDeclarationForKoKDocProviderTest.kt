package com.lemonappdev.konsist.core.declaration.kofunction

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldNotBeEqualTo
import org.junit.jupiter.api.Test

class KoFunctionDeclarationForKoKDocProviderTest {
    @Test
    fun `function-without-kdoc`() {
        // given
        val sut =
            getSnippetFile("function-without-kdoc")
                .functions()
                .first()

        // then
        assertSoftly(sut) {
            kDoc shouldBeEqualTo null
            hasKDoc shouldBeEqualTo false
        }
    }

    @Test
    fun `function-with-kdoc`() {
        // given
        val sut =
            getSnippetFile("function-with-kdoc")
                .functions()
                .first()

        // then
        assertSoftly(sut) {
            kDoc shouldNotBeEqualTo null
            hasKDoc shouldBeEqualTo true
        }
    }

    @Test
    fun `function-with-one-line-kdoc`() {
        // given
        val sut =
            getSnippetFile("function-with-one-line-kdoc")
                .functions()
                .first()

        // then
        assertSoftly(sut) {
            kDoc shouldNotBeEqualTo null
            hasKDoc shouldBeEqualTo true
        }
    }

    private fun getSnippetFile(fileName: String) = getSnippetKoScope("core/declaration/kofunction/snippet/forkokdocprovider/", fileName)
}
