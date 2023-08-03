package com.lemonappdev.konsist.core.declaration.koproperty

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoPropertyDeclarationForKoImplementationProviderTest {
    @Test
    fun `property-inside-interface-has-implementation-in-block-body`() {
        // given
        val sut = getSnippetFile("property-inside-interface-has-implementation-in-block-body")
            .properties(includeNested = true)
            .first()

        // then
        sut.hasImplementation shouldBeEqualTo true
    }

    @Test
    fun `property-inside-interface-has-implementation-in-expression-body`() {
        // given
        val sut = getSnippetFile("property-inside-interface-has-implementation-in-expression-body")
            .properties(includeNested = true)
            .first()

        // then
        sut.hasImplementation shouldBeEqualTo true
    }

    @Test
    fun `property-inside-interface-has-no-implementation`() {
        // given
        val sut = getSnippetFile("property-inside-interface-has-no-implementation")
            .properties(includeNested = true)
            .first()

        // then
        sut.hasImplementation shouldBeEqualTo false
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/kopropertydeclaration/snippet/forkoimplementationprovider/", fileName)
}
