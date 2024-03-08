package com.lemonappdev.konsist.core.declaration.kokdoc

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import com.lemonappdev.konsist.api.KoKDocTag.SEE
import com.lemonappdev.konsist.api.provider.KoKDocProvider
import com.lemonappdev.konsist.api.provider.KoNameProvider
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.MethodSource

class KoKDocDeclarationForKoKDocSeeTagProviderTest {
    @Test
    fun `kdoc-without-see-tag`() {
        // given
        val sut =
            getSnippetFile("kdoc-without-see-tag")
                .classes()
                .first()
                .kDoc

        // then
        assertSoftly(sut) {
            it?.seeTags shouldBeEqualTo emptyList()
            it?.numSeeTags shouldBeEqualTo 0
            it?.hasSeeTags shouldBeEqualTo false
        }
    }

    @ParameterizedTest
    @MethodSource("provideValues")
    fun `see-tag`(
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
            )
                .kDoc

        // then
        assertSoftly(sut) {
            it?.numSeeTags shouldBeEqualTo 2
            it?.seeTags?.get(0)?.name shouldBeEqualTo SEE
            it?.seeTags?.get(0)?.value shouldBeEqualTo "AnotherClass1"
            it?.seeTags?.get(0)?.description shouldBeEqualTo "sample description"
            it?.seeTags?.get(1)?.name shouldBeEqualTo SEE
            it?.seeTags?.get(1)?.value shouldBeEqualTo "AnotherClass2"
            it?.seeTags?.get(1)?.description shouldBeEqualTo ""
            it?.hasSeeTags shouldBeEqualTo true
        }
    }

    private fun getSnippetFile(fileName: String) = getSnippetKoScope("core/declaration/kokdoc/snippet/forkokdocseetagprovider/", fileName)

    companion object {
        @Suppress("unused")
        @JvmStatic
        fun provideValues() =
            listOf(
                arguments("class-with-see-tag", "SampleClass"),
                arguments("function-with-see-tag", "sampleMethod"),
            )
    }
}
