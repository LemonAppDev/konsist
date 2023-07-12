package com.lemonappdev.konsist.core.declaration.kofunctiondeclaration

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoFunctionDeclarationForHasImplementationTest {
    @Test
    fun `function-inside-interface-has-implementation-in-block-body`() {
        // given
        val sut = getSnippetFile("function-inside-interface-has-implementation-in-block-body")
            .functions(includeNested = true)
            .first()

        // then
        sut.hasImplementation() shouldBeEqualTo true
    }

    @Test
    fun `function-inside-interface-has-implementation-in-expression-body`() {
        // given
        val sut = getSnippetFile("function-inside-interface-has-implementation-in-expression-body")
            .functions(includeNested = true)
            .first()

        // then
        sut.hasImplementation() shouldBeEqualTo true
    }

    @Test
    fun `function-inside-interface-has-no-implementation`() {
        // given
        val sut = getSnippetFile("function-inside-interface-has-no-implementation")
            .functions(includeNested = true)
            .first()

        // then
        sut.hasImplementation() shouldBeEqualTo false
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/kofunctiondeclaration/snippet/forhasimplementation/", fileName)
}
