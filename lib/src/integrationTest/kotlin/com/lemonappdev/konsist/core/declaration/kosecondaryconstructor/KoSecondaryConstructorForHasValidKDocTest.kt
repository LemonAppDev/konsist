package com.lemonappdev.konsist.core.declaration.kosecondaryconstructor

import com.lemonappdev.konsist.TestSnippetProvider
import com.lemonappdev.konsist.core.ext.toNormalizedPath
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.MethodSource

class KoSecondaryConstructorForHasValidKDocTest {
    @ParameterizedTest
    @MethodSource("provideValues")
    fun `secondary-constructor-with-kdoc-with-param-tags`(
        fileName: String,
        verifyParamTag: Boolean,
        value: Boolean,
    ) {
        // given
        val sut = getSnippetFile(fileName)
            .classes()
            .first()
            .secondaryConstructors
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
        TestSnippetProvider.getSnippetKoScope(
            "core/declaration/kosecondaryconstructor/snippet/forhasvalidkdoc/".toNormalizedPath(),
            fileName
        )

    companion object {
        @Suppress("unused")
        @JvmStatic
        fun provideValues() = listOf(
            arguments("secondary-constructor-with-kdoc-with-complete-param-tags", true, true),
            arguments("secondary-constructor-with-kdoc-with-complete-param-tags", false, true),
            arguments("secondary-constructor-with-kdoc-with-not-complete-param-tags", true, false),
            arguments("secondary-constructor-with-kdoc-with-not-complete-param-tags", false, true),
            arguments("secondary-constructor-with-kdoc-with-too-many-param-tags", true, false),
            arguments("secondary-constructor-with-kdoc-with-too-many-param-tags", false, true),
            arguments("secondary-constructor-with-kdoc-without-param-tags", true, false),
            arguments("secondary-constructor-with-kdoc-without-param-tags", false, true),
        )
    }
}
