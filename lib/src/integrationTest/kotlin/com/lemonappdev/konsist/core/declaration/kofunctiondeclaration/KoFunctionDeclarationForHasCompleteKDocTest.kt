package com.lemonappdev.konsist.core.declaration.kofunctiondeclaration

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.MethodSource

class KoFunctionDeclarationForHasCompleteKDocTest {
    @ParameterizedTest
    @MethodSource("provideValuesForNoKDoc")
    fun `function-without-kdoc`(
        verifyDescription: Boolean,
        verifyParamTag: Boolean,
        verifyReturnTag: Boolean,
        value: Boolean,
    ) {
        // given
        val sut = getSnippetFile("function-without-kdoc")
            .functions()
            .first()

        // then
        sut
            .hasCompleteKDoc(
                verifyDescription = verifyDescription,
                verifyParamTag = verifyParamTag,
                verifyReturnTag = verifyReturnTag,
            )
            .shouldBeEqualTo(value)
    }

    @ParameterizedTest
    @MethodSource("provideValuesForEmptyKDoc")
    fun `function-with-empty-kdoc`(
        verifyDescription: Boolean,
        verifyParamTag: Boolean,
        verifyReturnTag: Boolean,
        value: Boolean,
    ) {
        // given
        val sut = getSnippetFile("function-with-empty-kdoc")
            .functions()
            .first()

        // then
        sut
            .hasCompleteKDoc(
                verifyDescription = verifyDescription,
                verifyParamTag = verifyParamTag,
                verifyReturnTag = verifyReturnTag,
            )
            .shouldBeEqualTo(value)
    }

    @ParameterizedTest
    @MethodSource("provideValuesForKDocWithDescription")
    fun `function-with-kdoc-with-description`(
        verifyDescription: Boolean,
        verifyParamTag: Boolean,
        verifyReturnTag: Boolean,
        value: Boolean,
    ) {
        // given
        val sut = getSnippetFile("function-with-kdoc-with-description")
            .functions()
            .first()

        // then
        sut
            .hasCompleteKDoc(
                verifyDescription = verifyDescription,
                verifyParamTag = verifyParamTag,
                verifyReturnTag = verifyReturnTag,
            )
            .shouldBeEqualTo(value)
    }

    @ParameterizedTest
    @MethodSource("provideValuesForKDocWithParamTags")
    fun `function-with-kdoc-with-param-tags`(
        declarationName: String,
        verifyDescription: Boolean,
        verifyParamTag: Boolean,
        verifyReturnTag: Boolean,
        value: Boolean,
    ) {
        // given
        val sut = getSnippetFile("function-with-kdoc-with-param-tags")
            .functions()
            .first { it.name == declarationName }

        // then
        sut
            .hasCompleteKDoc(
                verifyDescription = verifyDescription,
                verifyParamTag = verifyParamTag,
                verifyReturnTag = verifyReturnTag,
            )
            .shouldBeEqualTo(value)
    }

    @ParameterizedTest
    @MethodSource("provideValuesForKDocWithReturnTag")
    fun `function-with-kdoc-with-return-tag`(
        verifyDescription: Boolean,
        verifyParamTag: Boolean,
        verifyReturnTag: Boolean,
        value: Boolean,
    ) {
        // given
        val sut = getSnippetFile("function-with-kdoc-with-return-tag")
            .functions()
            .first()

        // then
        sut
            .hasCompleteKDoc(
                verifyDescription = verifyDescription,
                verifyParamTag = verifyParamTag,
                verifyReturnTag = verifyReturnTag,
            )
            .shouldBeEqualTo(value)
    }

    @ParameterizedTest
    @MethodSource("provideValuesForCompleteKDoc")
    fun `function-with-complete-kdoc`(
        verifyDescription: Boolean,
        verifyParamTag: Boolean,
        verifyReturnTag: Boolean,
        value: Boolean,
    ) {
        // given
        val sut = getSnippetFile("function-with-complete-kdoc")
            .functions()
            .first()

        // then
        sut
            .hasCompleteKDoc(
                verifyDescription = verifyDescription,
                verifyParamTag = verifyParamTag,
                verifyReturnTag = verifyReturnTag,
            )
            .shouldBeEqualTo(value)
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/kofunctiondeclaration/snippet/forhascompletekdoc/", fileName)

    companion object {
        @Suppress("unused")
        @JvmStatic
        fun provideValuesForNoKDoc() = listOf(
            arguments(true, true, true, false),
            arguments(true, false, false, false),
            arguments(false, true, false, false),
            arguments(false, false, true, false),
            arguments(false, false, false, false),
        )

        @Suppress("unused")
        @JvmStatic
        fun provideValuesForEmptyKDoc() = listOf(
            arguments(true, true, true, false),
            arguments(true, false, false, false),
            arguments(false, true, false, false),
            arguments(false, false, true, false),
            arguments(false, false, false, true),
        )

        @Suppress("unused")
        @JvmStatic
        fun provideValuesForKDocWithDescription() = listOf(
            arguments(true, true, true, false),
            arguments(true, false, false, true),
            arguments(false, true, false, false),
            arguments(false, false, true, false),
            arguments(true, true, false, false),
            arguments(true, false, true, false),
            arguments(false, true, true, false),
            arguments(false, false, false, true),
        )

        @Suppress("unused")
        @JvmStatic
        fun provideValuesForKDocWithParamTags() = listOf(
            arguments("sampleFunction1", true, true, true, false),
            arguments("sampleFunction1", true, false, false, false),
            arguments("sampleFunction1", false, true, false, true),
            arguments("sampleFunction1", false, false, true, false),
            arguments("sampleFunction1", true, true, false, false),
            arguments("sampleFunction1", true, false, true, false),
            arguments("sampleFunction1", false, true, true, false),
            arguments("sampleFunction1", false, false, false, true),
            arguments("sampleFunction2", false, true, false, false),
        )

        @Suppress("unused")
        @JvmStatic
        fun provideValuesForKDocWithReturnTag() = listOf(
            arguments(true, true, true, false),
            arguments(true, false, false, false),
            arguments(false, true, false, false),
            arguments(false, false, true, true),
            arguments(true, true, false, false),
            arguments(true, false, true, false),
            arguments(false, true, true, false),
            arguments(false, false, false, true),
        )

        @Suppress("unused")
        @JvmStatic
        fun provideValuesForCompleteKDoc() = listOf(
            arguments(true, true, true, true),
            arguments(true, false, false, true),
            arguments(false, true, false, true),
            arguments(false, false, true, true),
            arguments(true, true, false, true),
            arguments(true, false, true, true),
            arguments(false, true, true, true),
            arguments(false, false, false, true),
        )
    }
}
