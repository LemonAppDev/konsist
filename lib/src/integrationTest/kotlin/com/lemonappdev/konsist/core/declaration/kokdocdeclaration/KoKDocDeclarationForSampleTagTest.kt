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

class KoKDocDeclarationForSampleTagTest {
    @ParameterizedTest
    @MethodSource("provideValues")
    fun `sample-tag`(
        fileName: String,
        declarationName: String,
    ) {
        // given
        val sut = getSnippetFile(fileName)
            .namedDeclarations(includeNested = true)
            .first { it.name == declarationName }
            .kDoc

        // then
        assertSoftly(sut) {
            it?.sampleTags?.shouldHaveSize(2)
            it?.sampleTags?.get(0)?.name shouldBeEqualTo SAMPLE
            it?.sampleTags?.get(0)?.value shouldBeEqualTo "SampleClass.sampleMethod"
            it?.sampleTags?.get(0)?.description shouldBeEqualTo "sample description"
            it?.sampleTags?.get(1)?.name shouldBeEqualTo SAMPLE
            it?.sampleTags?.get(1)?.value shouldBeEqualTo "SampleClass.sampleProperty"
            it?.sampleTags?.get(1)?.description shouldBeEqualTo ""
        }
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("core/declaration/kokdocdeclaration/snippet/forsampletag/", fileName)

    companion object {
        @Suppress("unused")
        @JvmStatic
        fun provideValues() = listOf(
            arguments("class-with-sample-tag", "SampleClass"),
            arguments("function-with-sample-tag", "sampleMethod"),
        )
    }
}
