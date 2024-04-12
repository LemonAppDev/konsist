package com.lemonappdev.konsist.core.declaration.kofunction

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoFunctionDeclarationForKoInitializerProviderTest {
    @Test
    fun `function-is-initialized-in-block-body`() {
        // given
        val sut =
            getSnippetFile("function-is-initialized-in-block-body")
                .functions(includeNested = true)
                .first()

        // then
        sut.isInitialized shouldBeEqualTo true
    }

    @Test
    fun `function-is-initialized-in-expression-body`() {
        // given
        val sut =
            getSnippetFile("function-is-initialized-in-expression-body")
                .functions(includeNested = true)
                .first()

        // then
        sut.isInitialized shouldBeEqualTo true
    }

    @Test
    fun `function-inside-interface-is-not-initialized`() {
        // given
        val sut =
            getSnippetFile("function-inside-interface-is-not-initialized")
                .functions(includeNested = true)
                .first()

        // then
        sut.isInitialized shouldBeEqualTo false
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/kofunction/snippet/forkoinitializerprovider/", fileName)
}
