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
    fun `property-inside-interface-is-not-initialized`() {
        // given
        val sut = getSnippetFile("property-inside-interface-is-not-initialized")
            .properties()
            .first()

        // then
        sut.isInitialized shouldBeEqualTo false
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/koproperty/snippet/forkoinitializerprovider/", fileName)
}
