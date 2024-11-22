package com.lemonappdev.konsist.core.declaration.kotypeargument

import com.lemonappdev.konsist.TestSnippetProvider
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoTypeArgumentDeclarationForKoIsStarProjectionProviderTest {
    @Test
    fun `star-projection-type-argument-is-star-projection`() {
        // given
        val sut =
            getSnippetFile("star-projection-type-argument-is-star-projection")
                .properties()
                .first()
                .type
                ?.typeArguments
                ?.firstOrNull()

        // then
        sut?.isStarProjection shouldBeEqualTo true
    }

    @Test
    fun `not-generic-type-argument-is-not-star-projection`() {
        // given
        val sut =
            getSnippetFile("not-generic-type-argument-is-not-star-projection")
                .properties()
                .first()
                .type
                ?.typeArguments
                ?.firstOrNull()

        // then
        sut?.isStarProjection shouldBeEqualTo false
    }

    @Test
    fun `generic-type-argument-is-not-star-projection`() {
        // given
        val sut =
            getSnippetFile("generic-type-argument-is-not-star-projection")
                .properties()
                .first()
                .type
                ?.typeArguments
                ?.firstOrNull()

        // then
        sut?.isStarProjection shouldBeEqualTo false
    }

    @Test
    fun `generic-complex-type-argument-is-not-star-projection`() {
        // given
        val sut =
            getSnippetFile("generic-complex-type-argument-is-not-star-projection")
                .properties()
                .first()
                .type
                ?.typeArguments
                ?.firstOrNull()

        // then
        sut?.isStarProjection shouldBeEqualTo false
    }

    @Test
    fun `out-projection-type-argument-is-not-star-projection`() {
        // given
        val sut =
            getSnippetFile("out-projection-type-argument-is-not-star-projection")
                .properties()
                .first()
                .type
                ?.typeArguments
                ?.firstOrNull()

        // then
        sut?.isStarProjection shouldBeEqualTo false
    }

    @Test
    fun `in-projection-type-argument-is-not-star-projection`() {
        // given
        val sut =
            getSnippetFile("in-projection-type-argument-is-not-star-projection")
                .properties()
                .first()
                .type
                ?.typeArguments
                ?.firstOrNull()

        // then
        sut?.isStarProjection shouldBeEqualTo false
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("core/declaration/kotypeargument/snippet/forkoisstarprojectionprovider/", fileName)
}
