package com.lemonappdev.konsist.core.declaration.kokdocdeclaration

import com.lemonappdev.konsist.TestSnippetProvider
import com.lemonappdev.konsist.api.KoKDocTag.AUTHOR
import com.lemonappdev.konsist.api.KoKDocTag.CONSTRUCTOR
import com.lemonappdev.konsist.api.KoKDocTag.EXCEPTION
import com.lemonappdev.konsist.api.KoKDocTag.PARAM
import com.lemonappdev.konsist.api.KoKDocTag.PROPERTY
import com.lemonappdev.konsist.api.KoKDocTag.PROPERTY_GETTER
import com.lemonappdev.konsist.api.KoKDocTag.PROPERTY_SETTER
import com.lemonappdev.konsist.api.KoKDocTag.RECEIVER
import com.lemonappdev.konsist.api.KoKDocTag.RETURN
import com.lemonappdev.konsist.api.KoKDocTag.SAMPLE
import com.lemonappdev.konsist.api.KoKDocTag.SEE
import com.lemonappdev.konsist.api.KoKDocTag.SINCE
import com.lemonappdev.konsist.api.KoKDocTag.SUPPRESS
import com.lemonappdev.konsist.api.KoKDocTag.THROWS
import com.lemonappdev.konsist.api.KoKDocTag.VERSION
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldHaveSize
import org.junit.jupiter.api.Test
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
        val sut = getSnippetFile(fileName)
            .namedDeclarations(includeNested = true)
            .first { it.name == declarationName }
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
        TestSnippetProvider.getSnippetKoScope("core/declaration/kokdocdeclaration/snippet/forparamtag/", fileName)

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
