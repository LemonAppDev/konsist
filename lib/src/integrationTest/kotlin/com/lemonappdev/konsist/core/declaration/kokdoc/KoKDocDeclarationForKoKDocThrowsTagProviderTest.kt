package com.lemonappdev.konsist.core.declaration.kokdoc

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import com.lemonappdev.konsist.api.KoKDocTag.THROWS
import com.lemonappdev.konsist.api.provider.KoKDocProvider
import com.lemonappdev.konsist.api.provider.KoNameProvider
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.MethodSource

class KoKDocDeclarationForKoKDocThrowsTagProviderTest {
    @Test
    fun `kdoc-without-throws-tag`() {
        // given
        val sut =
            getSnippetFile("kdoc-without-throws-tag")
                .classes()
                .first()
                .kDoc

        // then
        assertSoftly(sut) {
            it?.throwsTags shouldBeEqualTo emptyList()
            it?.numThrowsTags shouldBeEqualTo 0
            it?.hasThrowsTags shouldBeEqualTo false
        }
    }

    @ParameterizedTest
    @MethodSource("provideValues")
    fun `throws-tag`(
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
            it?.numThrowsTags shouldBeEqualTo 1
            it?.throwsTags?.get(0)?.name shouldBeEqualTo THROWS
            it?.throwsTags?.get(0)?.value shouldBeEqualTo "IllegalArgumentException"
            it?.throwsTags?.get(0)?.description shouldBeEqualTo "First sample description"
            it?.hasThrowsTags shouldBeEqualTo true
        }
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/kokdoc/snippet/forkokdocthrowstagprovider/", fileName)

    companion object {
        @Suppress("unused")
        @JvmStatic
        fun provideValues() =
            listOf(
                arguments("class-with-throws-tag", "SampleClass"),
                arguments("function-with-throws-tag", "sampleMethod"),
            )
    }
}
