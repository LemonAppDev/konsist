package com.lemonappdev.konsist.core.declaration.koclassdeclaration

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import com.lemonappdev.konsist.core.ext.toNormalizedPath
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.MethodSource

class KoClassDeclarationForHasValidKDocTest {
    @ParameterizedTest
    @MethodSource("provideValues")
    fun `class-with-kdoc-with-param-tags`(
        fileName: String,
        verifyParamTag: Boolean,
        value: Boolean,
    ) {
        // given
        val sut = getSnippetFile(fileName)
            .classes()
            .first()

        // then
        sut
            .hasValidKDoc(
                verifyDescription = false,
                verifyParamTag = verifyParamTag,
            )
            .shouldBeEqualTo(value)
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/koclassdeclaration/snippet/forhasvalidkdoc/".toNormalizedPath(), fileName)

    companion object {
        @Suppress("unused")
        @JvmStatic
        fun provideValues() = listOf(
            arguments("class-with-kdoc-with-complete-param-tags", true, true),
            arguments("class-with-kdoc-with-complete-param-tags", false, true),
            arguments("class-with-kdoc-with-not-complete-param-tags", true, false),
            arguments("class-with-kdoc-with-not-complete-param-tags", false, true),
            arguments("class-with-kdoc-with-too-many-param-tags", true, false),
            arguments("class-with-kdoc-with-too-many-param-tags", false, true),
            arguments("class-with-kdoc-without-param-tags", true, false),
            arguments("class-with-kdoc-without-param-tags", false, true),
        )
    }
}
