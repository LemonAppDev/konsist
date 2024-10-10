package com.lemonappdev.konsist.core.declaration.kotypeargument.forkomodifier

import com.lemonappdev.konsist.TestSnippetProvider
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoTypeArgumentDeclarationForKoInModifierProviderTest {
    @Test
    fun `not-generic-type-argument-has-in-modifier`() {
        // given
        val sut =
            getSnippetFile("not-generic-type-argument-has-in-modifier")
                .properties()
                .first()
                .type
                ?.asGenericTypeDeclaration()
                ?.typeArguments
                ?.firstOrNull()

        // then
            sut?.hasInModifier shouldBeEqualTo false
    }

    @Test
    fun `generic-type-argument-has-in-modifier`() {
        // given
        val sut =
            getSnippetFile("generic-type-argument-has-in-modifier")
                .properties()
                .first()
                .type
                ?.asGenericTypeDeclaration()
                ?.typeArguments
                ?.firstOrNull()

        // then
            sut?.hasInModifier shouldBeEqualTo false
    }

    @Test
    fun `generic-complex-type-argument-has-in-modifier`() {
        // given
        val sut =
            getSnippetFile("generic-complex-type-argument-has-in-modifier")
                .properties()
                .first()
                .type
                ?.asGenericTypeDeclaration()
                ?.typeArguments
                ?.firstOrNull()

        // then
            sut?.hasInModifier shouldBeEqualTo false
    }

    @Test
    fun `star-projection-type-argument-has-in-modifier`() {
        // given
        val sut =
            getSnippetFile("star-projection-type-argument-has-in-modifier")
                .properties()
                .first()
                .type
                ?.asGenericTypeDeclaration()
                ?.typeArguments
                ?.firstOrNull()

        // then
            sut?.hasInModifier shouldBeEqualTo false
    }

    @Test
    fun `out-projection-type-argument-has-in-modifier`() {
        // given
        val sut =
            getSnippetFile("out-projection-type-argument-has-in-modifier")
                .properties()
                .first()
                .type
                ?.asGenericTypeDeclaration()
                ?.typeArguments
                ?.firstOrNull()

        // then
            sut?.hasInModifier shouldBeEqualTo false
    }

    @Test
    fun `in-projection-type-argument-has-in-modifier`() {
        // given
        val sut =
            getSnippetFile("in-projection-type-argument-has-in-modifier")
                .properties()
                .first()
                .type
                ?.asGenericTypeDeclaration()
                ?.typeArguments
                ?.firstOrNull()

        // then
            sut?.hasInModifier shouldBeEqualTo true
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope(
            "core/declaration/kotypeargument/forkomodifier/snippet/forkoinmodifierprovider/",
            fileName,
        )
}
