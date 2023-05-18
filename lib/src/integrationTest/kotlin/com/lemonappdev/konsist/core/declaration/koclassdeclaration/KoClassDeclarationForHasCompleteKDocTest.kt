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
        verifyDescription: Boolean,
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
                verifyDescription = verifyDescription,
                verifyParamTag = verifyParamTag,
            )
            .shouldBeEqualTo(value)
    }

    @ParameterizedTest
    @MethodSource("provideValuesForEmptyKDoc")
    fun `class-with-empty-kdoc`(
        verifyDescription: Boolean,
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
                verifyDescription = verifyDescription,
                verifyParamTag = verifyParamTag,
            )
            .shouldBeEqualTo(value)
    }

    @ParameterizedTest
    @MethodSource("provideValuesForKDocWithDescription")
    fun `class-with-kdoc-with-description`(
        verifyDescription: Boolean,
        verifyParamTag: Boolean,
        value: Boolean,
    ) {
        // given
        val sut = getSnippetFile("class-with-kdoc-with-description")
            .classes()
            .first()

        // then
        sut
            .hasValidKDoc(
                verifyDescription = verifyDescription,
                verifyParamTag = verifyParamTag,
            )
            .shouldBeEqualTo(value)
    }

    @ParameterizedTest
    @MethodSource("provideValuesForKDocWithParamTags")
    fun `class-with-kdoc-with-param-tags`(
        declarationName: String,
        verifyDescription: Boolean,
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
                verifyDescription = verifyDescription,
                verifyParamTag = verifyParamTag,
            )
            .shouldBeEqualTo(value)
    }

    @ParameterizedTest
    @MethodSource("provideValuesForCompleteKDoc")
    fun `class-with-complete-kdoc`(
        verifyDescription: Boolean,
        verifyParamTag: Boolean,
        value: Boolean,
    ) {
        // given
        val sut = getSnippetFile("class-with-complete-kdoc")
            .classes()
            .first()

        // then
        sut
            .hasValidKDoc(
                verifyDescription = verifyDescription,
                verifyParamTag = verifyParamTag,
            )
            .shouldBeEqualTo(value)
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/koclassdeclaration/snippet/forhascompletekdoc/", fileName)

    companion object {
        @Suppress("unused")
        @JvmStatic
        fun provideValuesForNoKDoc() = listOf(
            arguments(true, true, false),
            arguments(true, false, false),
            arguments(false, true, false),
            arguments(false, false, false),
        )

        @Suppress("unused")
        @JvmStatic
        fun provideValuesForEmptyKDoc() = listOf(
            arguments(true, true, false),
            arguments(true, false, false),
            arguments(false, true, false),
            arguments(false, false, true),
        )

        @Suppress("unused")
        @JvmStatic
        fun provideValuesForKDocWithDescription() = listOf(
            arguments(true, true, false),
            arguments(true, false, true),
            arguments(false, true, false),
            arguments(false, false, true),
        )

        @Suppress("unused")
        @JvmStatic
        fun provideValuesForKDocWithParamTags() = listOf(
            arguments("SampleClass1", true, true, false),
            arguments("SampleClass1", true, false, false),
            arguments("SampleClass1", false, true, true),
            arguments("SampleClass1", false, false, true),
            arguments("SampleClass2", false, true, false),
        )

        @Suppress("unused")
        @JvmStatic
        fun provideValuesForCompleteKDoc() = listOf(
            arguments(true, true, true),
            arguments(true, false, true),
            arguments(false, true, true),
            arguments(false, false, true),
        )
    }
}
