package com.lemonappdev.konsist.core.declaration.koproperty

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoPropertyDeclarationForKoInitializerProviderTest {
    @Test
    fun `property-inside-interface-is-initialized-in-block-body`() {
        // given
        val sut = getSnippetFile("property-inside-interface-is-initialized-in-block-body")
            .properties(includeNested = true)
            .first()

        // then
        sut.isInitialized shouldBeEqualTo true
    }

    @Test
    fun `property-inside-interface-is-initialized-in-expression-body`() {
        // given
        val sut = getSnippetFile("property-inside-interface-is-initialized-in-expression-body")
            .properties(includeNested = true)
            .first()

        // then
        sut.isInitialized shouldBeEqualTo true
    }

    @Test
    fun `property-inside-interface-is-not-initialized`() {
        // given
        val sut = getSnippetFile("property-inside-interface-is-not-initialized")
            .properties(includeNested = true)
            .first()

        // then
        sut.isInitialized shouldBeEqualTo false
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/koproperty/snippet/forkoinitializerprovider/", fileName)
}
