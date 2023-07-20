package com.lemonappdev.konsist.core.declaration.kokdocdeclaration

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import com.lemonappdev.konsist.api.provider.KoKDocProvider
import com.lemonappdev.konsist.api.provider.KoNameProvider
import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldHaveSize
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.MethodSource

class KoKDocDeclarationForTagTest {
    @ParameterizedTest
    @MethodSource("provideValues")
    fun `tags-size`(
        declarationName: String,
        size: Int,
    ) {
        // given
        val sut = (
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
        val sut = getSnippetFile("class-with-unknown-tag")
            .classes()
            .first()
            .kDoc

        // then
        sut?.tags shouldBeEqualTo listOf()
    }

    @Test
    fun `tags with multiline param tag`() {
        // given
        val sut = getSnippetFile("tags")
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
        val sut = getSnippetFile("tags")
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

    private fun getSnippetFile(fileName: String) = getSnippetKoScope("core/declaration/kokdocdeclaration/snippet/fortag/", fileName)

    companion object {
        @Suppress("unused")
        @JvmStatic
        fun provideValues() = listOf(
            arguments("SampleClass", 10),
            arguments("sampleMethod", 2),
            arguments("sampleProperty", 2),
            arguments("SampleClassWithoutTags", 0),
        )
    }
}
