package com.lemonappdev.konsist.core.declaration.kokdoc

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import com.lemonappdev.konsist.api.KoKDocTag
import com.lemonappdev.konsist.api.provider.KoKDocProvider
import com.lemonappdev.konsist.api.provider.KoNameProvider
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldHaveSize
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.MethodSource

class KoKDocDeclarationForKoKDocTagProviderTest {
    @ParameterizedTest
    @MethodSource("provideValues")
    fun `tags-size`(
        declarationName: String,
        size: Int,
    ) {
        // given
        val sut =
            (
                getSnippetFile("tags")
                    .declarations(includeNested = true)
                    .filterIsInstance<KoNameProvider>()
                    .first { it.name == declarationName } as KoKDocProvider
            )
                .kDoc

        // then
        sut
            ?.tags
            ?.shouldHaveSize(size)
    }

    @Test
    fun `class-with-unknown-tag`() {
        // given
        val sut =
            getSnippetFile("class-with-unknown-tag")
                .classes()
                .first()
                .kDoc

        // then
        sut?.tags shouldBeEqualTo listOf()
    }

    @Test
    fun `tags with multiline param tag`() {
        // given
        val sut =
            getSnippetFile("tags")
                .classes()
                .first()
                .kDoc

        // then
        sut
            ?.paramTags
            ?.get(0)
            ?.description
            .shouldBeEqualTo("First line description\nSecond line description")
    }

    @Test
    fun `tags with '@' into description`() {
        // given
        val sut =
            getSnippetFile("tags")
                .classes()
                .first()
                .kDoc

        // then
        sut
            ?.propertyTags
            ?.get(0)
            ?.description
            .shouldBeEqualTo("The first @property of the class.")
    }

    @Test
    fun `kdoc-without-tags`() {
        // given
        val sut =
            getSnippetFile("kdoc-without-tags")
                .classes()
                .first()
                .kDoc

        // then
        assertSoftly(sut) {
            it?.numTags shouldBeEqualTo 0
            it?.hasTags() shouldBeEqualTo false
            it?.hasTag(KoKDocTag.SINCE) shouldBeEqualTo false
            it?.hasTag(KoKDocTag.SINCE, KoKDocTag.SUPPRESS) shouldBeEqualTo false
            it?.hasAllTags(KoKDocTag.SINCE) shouldBeEqualTo false
            it?.hasAllTags(KoKDocTag.SINCE, KoKDocTag.SUPPRESS) shouldBeEqualTo false
            it?.hasTags(KoKDocTag.SINCE) shouldBeEqualTo false
        }
    }

    @Test
    fun `kdoc-has-tags`() {
        // given
        val sut =
            getSnippetFile("kdoc-has-tags")
                .classes()
                .first()
                .kDoc

        // then
        assertSoftly(sut) {
            it?.numTags shouldBeEqualTo 2
            it?.hasTags() shouldBeEqualTo true
            it?.hasTag(KoKDocTag.SINCE) shouldBeEqualTo true
            it?.hasTag(KoKDocTag.SINCE, KoKDocTag.SEE) shouldBeEqualTo true
            it?.hasTag(KoKDocTag.SINCE, KoKDocTag.SUPPRESS) shouldBeEqualTo true
            it?.hasAllTags(KoKDocTag.SINCE) shouldBeEqualTo true
            it?.hasAllTags(KoKDocTag.SINCE, KoKDocTag.SEE) shouldBeEqualTo true
            it?.hasAllTags(KoKDocTag.SINCE, KoKDocTag.SUPPRESS) shouldBeEqualTo false
            it?.hasTags(KoKDocTag.SINCE) shouldBeEqualTo true
            it?.hasTags(KoKDocTag.SINCE, KoKDocTag.SEE) shouldBeEqualTo true
            it?.hasTags(KoKDocTag.SAMPLE) shouldBeEqualTo false
            it?.hasTags(KoKDocTag.SINCE, KoKDocTag.SAMPLE) shouldBeEqualTo false
            it?.hasTags(KoKDocTag.SINCE, KoKDocTag.SEE, KoKDocTag.SAMPLE) shouldBeEqualTo false
        }
    }

    @Test
    fun `class-has-one-line-kdoc`() {
        // given
        val sut =
            getSnippetFile("class-has-one-line-kdoc")
                .classes()
                .first()
                .kDoc

        // then
        assertSoftly(sut) {
            it?.numTags shouldBeEqualTo 1
            it?.hasTags() shouldBeEqualTo true
            it?.hasTags(KoKDocTag.SINCE) shouldBeEqualTo true
            it?.hasTags(KoKDocTag.SINCE, KoKDocTag.SEE) shouldBeEqualTo false
        }
    }

    @Test
    fun `kdoc-has-tags-without-description`() {
        // given
        val sut =
            getSnippetFile("kdoc-has-tags-without-description")
                .classes()
                .first()
                .kDoc

        // then
        assertSoftly(sut) {
            it?.description shouldBeEqualTo ""
            it?.numTags shouldBeEqualTo 2
            it?.hasTags(KoKDocTag.SINCE) shouldBeEqualTo true
            it?.hasTags(KoKDocTag.SINCE, KoKDocTag.SEE) shouldBeEqualTo true
            it?.hasTags(KoKDocTag.SAMPLE) shouldBeEqualTo false
            it?.hasTags(KoKDocTag.SINCE, KoKDocTag.SAMPLE) shouldBeEqualTo false
            it?.hasTags(KoKDocTag.SINCE, KoKDocTag.SEE, KoKDocTag.SAMPLE) shouldBeEqualTo false
        }
    }

    private fun getSnippetFile(fileName: String) = getSnippetKoScope("core/declaration/kokdoc/snippet/forkokdoctagprovider/", fileName)

    companion object {
        @Suppress("unused")
        @JvmStatic
        fun provideValues() =
            listOf(
                arguments("SampleClass", 10),
                arguments("sampleMethod", 2),
                arguments("sampleProperty", 2),
                arguments("SampleClassWithoutTags", 0),
            )
    }
}
