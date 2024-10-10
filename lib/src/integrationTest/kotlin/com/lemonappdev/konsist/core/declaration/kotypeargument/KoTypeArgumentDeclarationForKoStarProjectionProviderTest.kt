package com.lemonappdev.konsist.core.declaration.kotypeargument

import com.lemonappdev.konsist.TestSnippetProvider
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoTypeArgumentDeclarationForKoStarProjectionProviderTest {
    @Test
    fun `not-generic-type-argument-type-projection`() {
        // given
        val sut =
            getSnippetFile("not-generic-type-argument-type-projection")
                .properties()
                .first()
                .type
                ?.asGenericTypeDeclaration()
                ?.typeArguments
                ?.firstOrNull()

        // then
        assertSoftly(sut) {
            it?.isStarProjection shouldBeEqualTo false
            it?.hasInModifier shouldBeEqualTo false
            it?.hasOutModifier shouldBeEqualTo false

        }
    }

    @Test
    fun `generic-type-argument-type-projection`() {
        // given
        val sut =
            getSnippetFile("generic-type-argument-type-projection")
                .properties()
                .first()
                .type
                ?.asGenericTypeDeclaration()
                ?.typeArguments
                ?.firstOrNull()

        // then
        assertSoftly(sut) {
            it?.isStarProjection shouldBeEqualTo false
            it?.hasInModifier shouldBeEqualTo false
            it?.hasOutModifier shouldBeEqualTo false
        }
    }

    @Test
    fun `generic-complex-type-argument-type-projection`() {
        // given
        val sut =
            getSnippetFile("generic-complex-type-argument-type-projection")
                .properties()
                .first()
                .type
                ?.asGenericTypeDeclaration()
                ?.typeArguments
                ?.firstOrNull()

        // then
        assertSoftly(sut) {
            it?.isStarProjection shouldBeEqualTo false
            it?.hasInModifier shouldBeEqualTo false
            it?.hasOutModifier shouldBeEqualTo false
        }
    }

    @Test
    fun `star-projection-type-argument-type-projection`() {
        // given
        val sut =
            getSnippetFile("star-projection-type-argument-type-projection")
                .properties()
                .first()
                .type
                ?.asGenericTypeDeclaration()
                ?.typeArguments
                ?.firstOrNull()

        // then
        assertSoftly(sut) {
            it?.isStarProjection shouldBeEqualTo true
            it?.hasInModifier shouldBeEqualTo false
            it?.hasOutModifier shouldBeEqualTo false
        }
    }

    @Test
    fun `out-projection-type-argument-type-projection`() {
        // given
        val sut =
            getSnippetFile("out-projection-type-argument-type-projection")
                .properties()
                .first()
                .type
                ?.asGenericTypeDeclaration()
                ?.typeArguments
                ?.firstOrNull()

        // then
        assertSoftly(sut) {
            it?.isStarProjection shouldBeEqualTo false
            it?.hasInModifier shouldBeEqualTo false
            it?.hasOutModifier shouldBeEqualTo true
        }
    }

    @Test
    fun `in-projection-type-argument-type-projection`() {
        // given
        val sut =
            getSnippetFile("in-projection-type-argument-type-projection")
                .properties()
                .first()
                .type
                ?.asGenericTypeDeclaration()
                ?.typeArguments
                ?.firstOrNull()

        // then
        assertSoftly(sut) {
            it?.isStarProjection shouldBeEqualTo false
            it?.hasInModifier shouldBeEqualTo true
            it?.hasOutModifier shouldBeEqualTo false
        }
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("core/declaration/kotypeargument/snippet/forkostarprojectionprovider/", fileName)
}
