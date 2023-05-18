package com.lemonappdev.konsist.core.declaration.kofunctiondeclaration

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.MethodSource

class KoFunctionDeclarationforhasvalidkdocTest {
    @ParameterizedTest
    @MethodSource("provideValuesForParamTag")
    fun `function-with-kdoc-with-param-tags`(
        fileName: String,
        verifyParamTag: Boolean,
        value: Boolean,
    ) {
        // given
        val sut = getSnippetFile(fileName)
            .functions()
            .first()

        // then
        sut
            .hasValidKDoc(
                verifyDescription = false,
                verifyParamTag = verifyParamTag,
            )
            .shouldBeEqualTo(value)
    }

    @ParameterizedTest
    @MethodSource("provideValuesForReturnTag")
    fun `function-with-kdoc-with-return-tag`(
        fileName: String,
        verifyReturnTag: Boolean,
        value: Boolean,
    ) {
        // given
        val sut = getSnippetFile(fileName)
            .functions()
            .first()

        // then
        sut
            .hasValidKDoc(
                verifyDescription = false,
                verifyReturnTag = verifyReturnTag,
            )
            .shouldBeEqualTo(value)
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/kofunctiondeclaration/snippet/forhasvalidkdoc/", fileName)

    companion object {
        @Suppress("unused")
        @JvmStatic
        fun provideValuesForParamTag() = listOf(
            arguments("function-with-kdoc-with-complete-param-tags", true, true),
            arguments("function-with-kdoc-with-complete-param-tags", false, true),
            arguments("function-with-kdoc-with-not-complete-param-tags", true, false),
            arguments("function-with-kdoc-with-not-complete-param-tags", false, true),
            arguments("function-with-kdoc-without-param-tags", true, false),
            arguments("function-with-kdoc-without-param-tags", false, true),
        )

        @Suppress("unused")
        @JvmStatic
        fun provideValuesForReturnTag() = listOf(
            arguments("function-with-kdoc-with-return-tag", true, true),
            arguments("function-with-kdoc-with-return-tag", false, true),
            arguments("function-with-kdoc-without-return-tag", true, false),
            arguments("function-with-kdoc-without-return-tag", false, true),
        )
    }
}
