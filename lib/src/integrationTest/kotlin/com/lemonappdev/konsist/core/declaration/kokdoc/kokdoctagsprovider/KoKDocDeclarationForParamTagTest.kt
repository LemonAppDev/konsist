package com.lemonappdev.konsist.core.declaration.kokdoc.kokdoctagsprovider

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import com.lemonappdev.konsist.api.KoKDocTag.PARAM
import com.lemonappdev.konsist.api.provider.KoKDocProvider
import com.lemonappdev.konsist.api.provider.KoNameProvider
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldHaveSize
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.MethodSource

class KoKDocDeclarationForParamTagTest {
    @ParameterizedTest
    @MethodSource("provideValues")
    @Suppress("detekt.LongParameterList")
    fun `param-tag`(
        fileName: String,
        declarationName: String,
        value1: String,
        description1: String,
        value2: String,
        description2: String,
    ) {
        // given
        val sut = (
            getSnippetFile(fileName)
                .declarations(includeNested = true)
                .filterIsInstance<KoNameProvider>()
                .first { it.name == declarationName } as KoKDocProvider
            )
            .kDoc

        // then
        assertSoftly(sut) {
            it?.paramTags?.shouldHaveSize(2)
            it?.paramTags?.get(0)?.name shouldBeEqualTo PARAM
            it?.paramTags?.get(0)?.value shouldBeEqualTo value1
            it?.paramTags?.get(0)?.description shouldBeEqualTo description1
            it?.paramTags?.get(1)?.name shouldBeEqualTo PARAM
            it?.paramTags?.get(1)?.value shouldBeEqualTo value2
            it?.paramTags?.get(1)?.description shouldBeEqualTo description2
        }
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/kokdocdeclaration/kokdoctagsprovider/snippet/forparamtag/", fileName)

    companion object {
        @Suppress("unused")
        @JvmStatic
        fun provideValues() = listOf(
            arguments(
                "class-with-param-tag",
                "SampleClass",
                "SampleType1",
                "The first type parameter for this class.",
                "SampleType2",
                "The second type parameter for this class.",
            ),
            arguments(
                "function-with-param-tag",
                "sampleMethod",
                "sampleArgument1",
                "The first argument.",
                "sampleArgument2",
                "The second argument.",
            ),
        )
    }
}
