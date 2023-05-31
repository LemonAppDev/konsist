package com.lemonappdev.konsist.core.declaration.kokdocdeclaration

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import com.lemonappdev.konsist.api.KoKDocTag.SAMPLE
import com.lemonappdev.konsist.api.KoKDocTag.SEE
import com.lemonappdev.konsist.api.KoKDocTag.SINCE
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.provider.Arguments.arguments

class KoKDocDeclarationForHasTagTest {
    @Test
    fun `class-has-tags`() {
        // given
        val sut = getSnippetFile("class-has-tags")
            .classes()
            .first()
            .kDoc

        // then
        assertSoftly(sut) {
            it?.hasTags(SINCE) shouldBeEqualTo true
            it?.hasTags(SINCE, SEE) shouldBeEqualTo true
            it?.hasTags(SAMPLE) shouldBeEqualTo false
            it?.hasTags(SINCE, SAMPLE) shouldBeEqualTo false
            it?.hasTags(SINCE, SEE, SAMPLE) shouldBeEqualTo false
        }
    }

    @Test
    fun `class-has-tags-without-description`() {
        // given
        val sut = getSnippetFile("class-has-tags-without-description")
            .classes()
            .first()
            .kDoc

        // then
        assertSoftly(sut) {
            it?.description shouldBeEqualTo ""
            it?.hasTags(SINCE) shouldBeEqualTo true
            it?.hasTags(SINCE, SEE) shouldBeEqualTo true
            it?.hasTags(SAMPLE) shouldBeEqualTo false
            it?.hasTags(SINCE, SAMPLE) shouldBeEqualTo false
            it?.hasTags(SINCE, SEE, SAMPLE) shouldBeEqualTo false
        }
    }

    private fun getSnippetFile(fileName: String) = getSnippetKoScope("core/declaration/kokdocdeclaration/snippet/forhastag/", fileName)

    companion object {
        @Suppress("unused")
        @JvmStatic
        fun provideValuesForTagsSize() = listOf(
            arguments("SampleClass", 10),
            arguments("sampleMethod", 2),
            arguments("sampleProperty", 2),
            arguments("SampleClassWithoutTags", 0),
        )

        @Suppress("unused")
        @JvmStatic
        fun provideValuesForParamTag() = listOf(
            arguments(
                "class-with-tags",
                "SampleClass",
                "SampleType1",
                "The first type parameter for this class.",
                "SampleType2",
                "The second type parameter for this class.",
            ),
            arguments(
                "function-with-tags",
                "sampleMethod",
                "sampleArgument1",
                "The first argument.",
                "sampleArgument2",
                "The second argument.",
            ),
        )

        @Suppress("unused")
        @JvmStatic
        fun provideValuesForTags() = listOf(
            arguments("class-with-tags", "SampleClass"),
            arguments("function-with-tags", "sampleMethod"),
        )
    }
}
