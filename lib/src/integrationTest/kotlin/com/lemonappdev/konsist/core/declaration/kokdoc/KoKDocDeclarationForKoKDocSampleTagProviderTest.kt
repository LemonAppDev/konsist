package com.lemonappdev.konsist.core.declaration.kokdoc

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import com.lemonappdev.konsist.api.KoKDocTag.SAMPLE
import com.lemonappdev.konsist.api.provider.KoKDocProvider
import com.lemonappdev.konsist.api.provider.KoNameProvider
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.MethodSource

class KoKDocDeclarationForKoKDocSampleTagProviderTest {
    @Test
    fun `kdoc-without-sample-tag`() {
        // given
        val sut =
            getSnippetFile("kdoc-without-sample-tag")
                .classes()
                .first()
                .kDoc

        // then
        assertSoftly(sut) {
            it?.sampleTags shouldBeEqualTo emptyList()
            it?.numSampleTags shouldBeEqualTo 0
            it?.hasSampleTags shouldBeEqualTo false
        }
    }

    @ParameterizedTest
    @MethodSource("provideValues")
    fun `sample-tag`(
        fileName: String,
        declarationName: String,
    ) {
        // given
        val sut =
            (
                getSnippetFile(fileName)
                    .declarations(includeNested = true)
                    .filterIsInstance<KoNameProvider>()
                    .first { it.name == declarationName } as KoKDocProvider
            ).kDoc

        // then
        assertSoftly(sut) {
            it?.numSampleTags shouldBeEqualTo 2
            it?.sampleTags?.get(0)?.name shouldBeEqualTo SAMPLE
            it?.sampleTags?.get(0)?.value shouldBeEqualTo "SampleClass.sampleMethod"
            it?.sampleTags?.get(0)?.description shouldBeEqualTo "sample description"
            it?.sampleTags?.get(1)?.name shouldBeEqualTo SAMPLE
            it?.sampleTags?.get(1)?.value shouldBeEqualTo "SampleClass.sampleProperty"
            it?.sampleTags?.get(1)?.description shouldBeEqualTo ""
            it?.hasSampleTags shouldBeEqualTo true
        }
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/kokdoc/snippet/forkokdocsampletagprovider/", fileName)

    companion object {
        @Suppress("unused")
        @JvmStatic
        fun provideValues() =
            listOf(
                arguments("class-with-sample-tag", "SampleClass"),
                arguments("function-with-sample-tag", "sampleMethod"),
            )
    }
}
