package com.lemonappdev.konsist.core.declaration.kokdoc

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldContain
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.MethodSource

class KoKDocDeclarationForKoTextProviderTest {
    @ParameterizedTest
    @MethodSource("provideValuesForClass")
    fun `kdoc-description-for-class`(
        fileName: String,
        value: String,
        isBlank: Boolean,
    ) {
        // given
        val sut =
            getSnippetFile(fileName)
                .classes()
                .first()
                .kDoc

        // then
        assertSoftly(sut?.text) {
            it?.shouldContain(value)
            it?.isBlank() shouldBeEqualTo isBlank
        }
    }

    @ParameterizedTest
    @MethodSource("provideValuesForFunction")
    fun `kdoc-description-for-function`(
        fileName: String,
        value: String,
        isBlank: Boolean,
    ) {
        // given
        val sut =
            getSnippetFile(fileName)
                .functions(includeNested = true)
                .first()
                .kDoc

        // then
        assertSoftly(sut?.text) {
            it?.shouldContain(value)
            it?.isBlank() shouldBeEqualTo isBlank
        }
    }

    @ParameterizedTest
    @MethodSource("provideValuesForProperty")
    fun `kdoc-description-for-property`(
        fileName: String,
        value: String,
        isBlank: Boolean,
    ) {
        // given
        val sut =
            getSnippetFile(fileName)
                .properties(includeNested = true)
                .first()
                .kDoc

        // then
        assertSoftly(sut?.text) {
            it?.shouldContain(value)
            it?.isBlank() shouldBeEqualTo isBlank
        }
    }

    private fun getSnippetFile(fileName: String) = getSnippetKoScope("core/declaration/kokdoc/snippet/forkotextprovider/", fileName)

    companion object {
        @Suppress("unused")
        @JvmStatic
        fun provideValuesForClass() =
            listOf(
                arguments("class-with-description-and-tags", "This is a sample class that demonstrates the usage of KDoc tags.", false),
                arguments(
                    "class-with-description-and-without-tags",
                    "This is a sample class that demonstrates the usage of KDoc tags.",
                    false,
                ),
                arguments("class-without-description-and-with-tags", "", false),
                arguments("class-with-empty-kdoc", "", true),
            )

        @Suppress("unused")
        @JvmStatic
        fun provideValuesForFunction() =
            listOf(
                arguments("function-with-description-and-tags", "This is a sample method that demonstrates the usage of KDoc tags.", false),
                arguments(
                    "function-with-description-and-without-tags",
                    "This is a sample method that demonstrates the usage of KDoc tags.",
                    false,
                ),
                arguments("function-without-description-and-with-tags", "", false),
                arguments("function-with-empty-kdoc", "", true),
            )

        @Suppress("unused")
        @JvmStatic
        fun provideValuesForProperty() =
            listOf(
                arguments(
                    "property-with-description-and-tags",
                    "This is a sample property that demonstrates the usage of KDoc tags.",
                    false,
                ),
                arguments(
                    "property-with-description-and-without-tags",
                    "This is a sample property that demonstrates the usage of KDoc tags.",
                    false,
                ),
                arguments("property-without-description-and-with-tags", "", false),
                arguments("property-with-empty-kdoc", "", true),
            )
    }
}
