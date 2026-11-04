package com.lemonappdev.konsist.core.declaration.kofunction

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import com.lemonappdev.konsist.api.ext.list.functions
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
            body shouldBeEqualTo null
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
            body shouldBeEqualTo "0"
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
            body shouldBeEqualTo "return 0"
        }
    }

    @Test
    fun `function-has-empty-block-body`() {
        // given
        val sut =
            getSnippetFile("function-has-empty-block-body")
                .functions()
                .first()

        // then
        sut.body shouldBeEqualTo ""
    }

    @Test
    fun `function-has-single-line-block-body`() {
        // given
        val sut =
            getSnippetFile("function-has-single-line-block-body")
                .functions()
                .first()

        // then
        sut.body shouldBeEqualTo "return 0"
    }

    @Test
    fun `function-has-multiline-block-body`() {
        // given
        val sut =
            getSnippetFile("function-has-multiline-block-body")
                .functions()
                .first()

        // then
        sut.body shouldBeEqualTo
            """
            // comment
            if (value > 0) {
                return value
            }

            return 0
            """.trimIndent()
    }

    @Test
    fun `function-has-block-body-in-class`() {
        // given
        val sut =
            getSnippetFile("function-has-block-body-in-class")
                .functions(includeNested = true)
                .first()

        // then
        sut.body shouldBeEqualTo
            """
            println()
            println()
            """.trimIndent()
    }

    @Test
    fun `function-has-lambda-expression-body`() {
        // given
        val sut =
            getSnippetFile("function-has-lambda-expression-body")
                .functions()
                .first()

        // then
        sut.body shouldBeEqualTo "{ 0 }"
    }

    private fun getSnippetFile(fileName: String) = getSnippetKoScope("core/declaration/kofunction/snippet/forkobodyprovider/", fileName)
}
