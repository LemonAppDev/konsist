package com.lemonappdev.konsist.core.declaration.kotypeparameter.forkomodifier

import com.lemonappdev.konsist.TestSnippetProvider
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoTypeParameterDeclarationForKoOutModifierProviderTest {
    @Test
    fun `class-type-parameter-without-out-modifier`() {
        // given
        val sut =
            getSnippetFile("class-type-parameter-without-out-modifier")
                .classes()
                .first()
                .typeParameters
                .first()

        // then
        sut.hasOutModifier shouldBeEqualTo false
    }

    @Test
    fun `class-type-parameter-with-out-modifier`() {
        // given
        val sut =
            getSnippetFile("class-type-parameter-with-out-modifier")
                .classes()
                .first()
                .typeParameters
                .first()

        // then
        sut.hasOutModifier shouldBeEqualTo true
    }

    @Test
    fun `interface-type-parameter-without-out-modifier`() {
        // given
        val sut =
            getSnippetFile("interface-type-parameter-without-out-modifier")
                .interfaces()
                .first()
                .typeParameters
                .first()

        // then
        sut.hasOutModifier shouldBeEqualTo false
    }

    @Test
    fun `interface-type-parameter-with-out-modifier`() {
        // given
        val sut =
            getSnippetFile("interface-type-parameter-with-out-modifier")
                .interfaces()
                .first()
                .typeParameters
                .first()

        // then
        sut.hasOutModifier shouldBeEqualTo true
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope(
            "core/declaration/kotypeparameter/forkomodifier/snippet/forkooutmodifierprovider/",
            fileName,
        )
}
