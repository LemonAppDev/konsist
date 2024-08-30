package com.lemonappdev.konsist.core.declaration.kokdoc

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import com.lemonappdev.konsist.api.KoKDocTag.EXCEPTION
import com.lemonappdev.konsist.api.provider.KoKDocProvider
import com.lemonappdev.konsist.api.provider.KoNameProvider
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.MethodSource

class KoKDocDeclarationForKoKDocExceptionTagProviderTest {
    @Test
    fun `kdoc-without-exception-tag`() {
        // given
        val sut =
            getSnippetFile("kdoc-without-exception-tag")
                .classes()
                .first()
                .kDoc

        // then
        assertSoftly(sut) {
            it?.exceptionTags shouldBeEqualTo emptyList()
            it?.numExceptionTags shouldBeEqualTo 0
            it?.hasExceptionTags shouldBeEqualTo false
        }
    }

    @ParameterizedTest
    @MethodSource("provideValues")
    fun `exception-tag`(
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
            it?.numExceptionTags shouldBeEqualTo 1
            it?.exceptionTags?.get(0)?.name shouldBeEqualTo EXCEPTION
            it?.exceptionTags?.get(0)?.value shouldBeEqualTo "NullPointerException"
            it?.exceptionTags?.get(0)?.description shouldBeEqualTo "Second sample description"
            it?.hasExceptionTags shouldBeEqualTo true
        }
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/kokdoc/snippet/forkokdocexceptiontagprovider/", fileName)

    companion object {
        @Suppress("unused")
        @JvmStatic
        fun provideValues() =
            listOf(
                arguments("class-with-exception-tag", "SampleClass"),
                arguments("function-with-exception-tag", "sampleMethod"),
            )
    }
}
