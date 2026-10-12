package com.lemonappdev.konsist.core.declaration.kosetter

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoSetterDeclarationForKoBodyProviderTest {
    @Test
    fun `setter-has-no-body`() {
        // given
        val sut =
            getSnippetFile("setter-has-no-body")
                .properties()
                .first()
                .setter

        // then
        assertSoftly(sut) {
            it?.hasExpressionBody shouldBeEqualTo false
            it?.hasBlockBody shouldBeEqualTo false
            it?.body shouldBeEqualTo null
        }
    }

    @Test
    fun `setter-has-expression-body`() {
        // given
        val sut =
            getSnippetFile("setter-has-expression-body")
                .properties()
                .first()
                .setter

        // then
        assertSoftly(sut) {
            it?.hasExpressionBody shouldBeEqualTo true
            it?.hasBlockBody shouldBeEqualTo false
            it?.body shouldBeEqualTo "println(value)"
        }
    }

    @Test
    fun `setter-has-block-body`() {
        // given
        val sut =
            getSnippetFile("setter-has-block-body")
                .properties()
                .first()
                .setter

        // then
        assertSoftly(sut) {
            it?.hasExpressionBody shouldBeEqualTo false
            it?.hasBlockBody shouldBeEqualTo true
            it?.body shouldBeEqualTo "if (true) field = value"
        }
    }

    private fun getSnippetFile(fileName: String) = getSnippetKoScope("core/declaration/kosetter/snippet/forkobodyprovider/", fileName)
}
