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
    fun `kdoc-without-tag`() {
        // given
        val sut =
            getSnippetFile("kdoc-without-tag")
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
            it?.sampleTags?.get(0)?.value shouldBeEqualTo "FixtureClass.fixtureMethod"
            it?.sampleTags?.get(0)?.description shouldBeEqualTo "fixture description"
            it?.sampleTags?.get(1)?.name shouldBeEqualTo SAMPLE
            it?.sampleTags?.get(1)?.value shouldBeEqualTo "FixtureClass.fixtureProperty"
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
                arguments("class-with-tag", "FixtureClass"),
                arguments("function-with-tag", "fixtureMethod"),
            )
    }
}
