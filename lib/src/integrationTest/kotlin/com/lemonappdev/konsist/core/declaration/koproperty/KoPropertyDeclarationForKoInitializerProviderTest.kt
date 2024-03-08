package com.lemonappdev.konsist.core.declaration.koproperty

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoPropertyDeclarationForKoInitializerProviderTest {
    @Test
    fun `property-is-initialized-in-default-getter`() {
        // given
        val sut = getSnippetFile("property-is-initialized-in-default-getter")
            .properties()
            .first()

        // then
        sut.isInitialized shouldBeEqualTo true
    }

    @Test
    fun `property-is-initialized-in-getter-block-body`() {
        // given
        val sut = getSnippetFile("property-is-initialized-in-getter-block-body")
            .properties()
            .first()

        // then
        sut.isInitialized shouldBeEqualTo true
    }

    @Test
    fun `property-is-initialized-in-getter-expression-body`() {
        // given
        val sut = getSnippetFile("property-is-initialized-in-getter-expression-body")
            .properties()
            .first()

        // then
        sut.isInitialized shouldBeEqualTo true
    }

    @Test
    fun `property-with-delegate-is-initialized-in-block-body`() {
        // given
        val sut = getSnippetFile("property-with-delegate-is-initialized-in-block-body")
            .properties()
            .first()

        // then
        sut.isInitialized shouldBeEqualTo true
    }

    @Test
    fun `property-with-lateinit-modifier-is-not-initialized`() {
        // given
        val sut = getSnippetFile("property-with-lateinit-modifier-is-not-initialized")
            .properties()
            .first()

        // then
        sut.isInitialized shouldBeEqualTo false
    }

    @Test
    fun `property-inside-interface-is-not-initialized`() {
        // given
        val sut = getSnippetFile("property-inside-interface-is-not-initialized")
            .properties()
            .first()

        // then
        sut.isInitialized shouldBeEqualTo false
    }

    @Test
    fun `property-has-implementation-in-getter-block-body`() {
        // given
        val sut = getSnippetFile("property-has-implementation-in-getter-block-body")
            .properties(includeNested = true)
            .first()

        // then
        sut.hasImplementation shouldBeEqualTo true
    }

    @Test
    fun `property-has-implementation-in-getter-expression-body`() {
        // given
        val sut = getSnippetFile("property-has-implementation-in-getter-expression-body")
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
        getSnippetKoScope("core/declaration/koproperty/snippet/forkoinitializerprovider/", fileName)
}
