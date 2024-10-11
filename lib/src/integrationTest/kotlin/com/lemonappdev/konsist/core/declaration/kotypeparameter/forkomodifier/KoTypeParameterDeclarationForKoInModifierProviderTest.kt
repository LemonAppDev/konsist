package com.lemonappdev.konsist.core.declaration.kotypeparameter.forkomodifier

import com.lemonappdev.konsist.TestSnippetProvider
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoTypeParameterDeclarationForKoInModifierProviderTest {
    @Test
    fun `class-type-parameter-without-in-modifier`() {
        // given
        val sut =
            getSnippetFile("class-type-parameter-without-in-modifier")
                .classes()
                .first()
                .typeParameters
                .first()

        // then
        sut.hasInModifier shouldBeEqualTo false
    }

    @Test
    fun `class-type-parameter-with-in-modifier`() {
        // given
        val sut =
            getSnippetFile("class-type-parameter-with-in-modifier")
                .classes()
                .first()
                .typeParameters
                .first()

        // then
        sut.hasInModifier shouldBeEqualTo true
    }

    @Test
    fun `interface-type-parameter-without-in-modifier`() {
        // given
        val sut =
            getSnippetFile("interface-type-parameter-without-in-modifier")
                .interfaces()
                .first()
                .typeParameters
                .first()

        // then
        sut.hasInModifier shouldBeEqualTo false
    }

    @Test
    fun `interface-type-parameter-with-in-modifier`() {
        // given
        val sut =
            getSnippetFile("interface-type-parameter-with-in-modifier")
                .interfaces()
                .first()
                .typeParameters
                .first()

        // then
        sut.hasInModifier shouldBeEqualTo true
    }
    
    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope(
            "core/declaration/kotypeparameter/forkomodifier/snippet/forkoinmodifierprovider/",
            fileName,
        )
}
