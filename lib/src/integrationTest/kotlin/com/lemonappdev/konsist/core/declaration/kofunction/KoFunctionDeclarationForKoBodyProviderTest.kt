package com.lemonappdev.konsist.core.declaration.kofunction

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import com.lemonappdev.konsist.api.ext.list.provider.functions
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoFunctionDeclarationForKoBodyProviderTest {
    @Test
    fun `function-has-no-body`() {
        // given
        val sut =
            getSnippetFile("function-has-no-body")
                .functions()
                .first()

        // then
        assertSoftly(sut) {
            hasExpressionBody shouldBeEqualTo false
            hasBlockBody shouldBeEqualTo false
        }
    }

    @Test
    fun `function-has-expression-body`() {
        // given
        val sut =
            getSnippetFile("function-has-expression-body")
                .functions()
                .first()

        // then
        assertSoftly(sut) {
            hasExpressionBody shouldBeEqualTo true
            hasBlockBody shouldBeEqualTo false
        }
    }

    @Test
    fun `function-has-block-body`() {
        // given
        val sut =
            getSnippetFile("function-has-block-body")
                .functions()
                .first()

        // then
        assertSoftly(sut) {
            hasExpressionBody shouldBeEqualTo false
            hasBlockBody shouldBeEqualTo true
        }
    }

    private fun getSnippetFile(fileName: String) = getSnippetKoScope("core/declaration/kofunction/snippet/forkobodyprovider/", fileName)
}
