package com.lemonappdev.konsist.core.declaration.kokdoc

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.MethodSource

class KoKDocDeclarationForKoKDocDescriptionProviderTest {
    @ParameterizedTest
    @MethodSource("provideValuesForClass")
    fun `kdoc-description-for-class`(
        fileName: String,
        value: String,
    ) {
        // given
        val sut =
            getSnippetFile(fileName)
                .classes()
                .first()
                .kDoc

        // then
        sut?.description shouldBeEqualTo value
    }

    @ParameterizedTest
    @MethodSource("provideValuesForFunction")
    fun `kdoc-description-for-function`(
        fileName: String,
        value: String,
    ) {
        // given
        val sut =
            getSnippetFile(fileName)
                .functions(includeNested = true)
                .first()
                .kDoc

        // then
        sut?.description shouldBeEqualTo value
    }

    @ParameterizedTest
    @MethodSource("provideValuesForProperty")
    fun `kdoc-description-for-property`(
        fileName: String,
        value: String,
    ) {
        // given
        val sut =
            getSnippetFile(fileName)
                .properties(includeNested = true)
                .first()
                .kDoc

        // then
        sut?.description shouldBeEqualTo value
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/kokdoc/snippet/forkokdocdescriptionprovider/", fileName)

    companion object {
        @Suppress("unused")
        @JvmStatic
        fun provideValuesForClass() =
            listOf(
                arguments(
                    "class-with-description-and-tags",
                    "This is a sample class that demonstrates the usage of KDoc tags.",
                ),
                arguments(
                    "class-with-description-and-without-tags",
                    "This is a sample class that demonstrates the usage of KDoc tags.",
                ),
                arguments("class-without-description-and-with-tags", ""),
                arguments("class-with-empty-kdoc", ""),
            )

        @Suppress("unused")
        @JvmStatic
        fun provideValuesForFunction() =
            listOf(
                arguments(
                    "function-with-description-and-tags",
                    "This is a sample method that demonstrates the usage of KDoc tags.",
                ),
                arguments(
                    "function-with-description-and-without-tags",
                    "This is a sample method that demonstrates the usage of KDoc tags.",
                ),
                arguments("function-without-description-and-with-tags", ""),
                arguments("function-with-empty-kdoc", ""),
            )

        @Suppress("unused")
        @JvmStatic
        fun provideValuesForProperty() =
            listOf(
                arguments(
                    "property-with-description-and-tags",
                    "This is a sample property that demonstrates the usage of KDoc tags.",
                ),
                arguments(
                    "property-with-description-and-without-tags",
                    "This is a sample property that demonstrates the usage of KDoc tags.",
                ),
                arguments("property-without-description-and-with-tags", ""),
                arguments("property-with-empty-kdoc", ""),
            )
    }
}
