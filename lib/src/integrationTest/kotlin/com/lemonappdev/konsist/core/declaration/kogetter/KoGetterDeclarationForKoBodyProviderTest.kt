package com.lemonappdev.konsist.core.declaration.kogetter

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoGetterDeclarationForKoBodyProviderTest {
    @Test
    fun `getter-has-no-body`() {
        // given
        val sut =
            getSnippetFile("getter-has-no-body")
                .properties()
                .first()
                .getter

        // then
        assertSoftly(sut) {
            it?.hasExpressionBody shouldBeEqualTo false
            it?.hasBlockBody shouldBeEqualTo false
            it?.body shouldBeEqualTo null
        }
    }

    @Test
    fun `getter-has-expression-body`() {
        // given
        val sut =
            getSnippetFile("getter-has-expression-body")
                .properties()
                .first()
                .getter

        // then
        assertSoftly(sut) {
            it?.hasExpressionBody shouldBeEqualTo true
            it?.hasBlockBody shouldBeEqualTo false
            it?.body shouldBeEqualTo "\"\""
        }
    }

    @Test
    fun `getter-has-block-body`() {
        // given
        val sut =
            getSnippetFile("getter-has-block-body")
                .properties()
                .first()
                .getter

        // then
        assertSoftly(sut) {
            it?.hasExpressionBody shouldBeEqualTo false
            it?.hasBlockBody shouldBeEqualTo true
            it?.body shouldBeEqualTo "return \"\""
        }
    }

    private fun getSnippetFile(fileName: String) = getSnippetKoScope("core/declaration/kogetter/snippet/forkobodyprovider/", fileName)
}
