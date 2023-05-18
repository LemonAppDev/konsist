package com.lemonappdev.konsist.core.declaration.koclassdeclaration

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.MethodSource

class KoClassDeclarationForHasCompleteKDocTest {
    @ParameterizedTest
    @MethodSource("provideValuesForNoKDoc")
    fun `class-without-kdoc`(
        verifyParamTag: Boolean,
        value: Boolean,
    ) {
        // given
        val sut = getSnippetFile("class-without-kdoc")
            .classes()
            .first()

        // then
        sut
            .hasValidKDoc(
                verifyDescription = false,
                verifyParamTag = verifyParamTag
            )
            .shouldBeEqualTo(value)
    }

    @ParameterizedTest
    @MethodSource("provideValuesForEmptyKDoc")
    fun `class-with-empty-kdoc`(
        verifyParamTag: Boolean,
        value: Boolean,
    ) {
        // given
        val sut = getSnippetFile("class-with-empty-kdoc")
            .classes()
            .first()

        // then
        sut
            .hasValidKDoc(
                verifyDescription = false,
                verifyParamTag = verifyParamTag
            )
            .shouldBeEqualTo(value)
    }

    @ParameterizedTest
    @MethodSource("provideValuesForKDocWithParamTags")
    fun `class-with-kdoc-with-param-tags`(
        declarationName: String,
        verifyParamTag: Boolean,
        value: Boolean,
    ) {
        // given
        val sut = getSnippetFile("class-with-kdoc-with-param-tags")
            .classes()
            .first { it.name == declarationName }

        // then
        sut
            .hasValidKDoc(
                verifyDescription = false,
                verifyParamTag = verifyParamTag
            )
            .shouldBeEqualTo(value)
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/koclassdeclaration/snippet/forhascompletekdoc/", fileName)

    companion object {
        @Suppress("unused")
        @JvmStatic
        fun provideValuesForNoKDoc() = listOf(
            arguments(true, false),
            arguments(false, false),
        )

        @Suppress("unused")
        @JvmStatic
        fun provideValuesForEmptyKDoc() = listOf(
            arguments(true, false),
            arguments(false, true),
        )

        @Suppress("unused")
        @JvmStatic
        fun provideValuesForKDocWithParamTags() = listOf(
            arguments("SampleClass1", true, true),
            arguments("SampleClass1", false, true),
            arguments("SampleClass2", true, false),
        )
    }
}
