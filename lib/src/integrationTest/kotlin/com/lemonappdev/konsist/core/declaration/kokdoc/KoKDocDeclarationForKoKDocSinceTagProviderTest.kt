package com.lemonappdev.konsist.core.declaration.kokdoc

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import com.lemonappdev.konsist.api.KoKDocTag.SINCE
import com.lemonappdev.konsist.api.provider.KoKDocProvider
import com.lemonappdev.konsist.api.provider.KoNameProvider
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.MethodSource

class KoKDocDeclarationForKoKDocSinceTagProviderTest {
    @Test
    fun `kdoc-without-since-tag`() {
        // given
        val sut =
            getSnippetFile("kdoc-without-since-tag")
                .classes()
                .first()
                .kDoc

        // then
        assertSoftly(sut) {
            it?.sinceTag shouldBeEqualTo null
            it?.hasSinceTag shouldBeEqualTo false
        }
    }

    @ParameterizedTest
    @MethodSource("provideValues")
    fun `since-tag`(
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
            it?.sinceTag?.name shouldBeEqualTo SINCE
            it?.sinceTag?.description shouldBeEqualTo "1.0.0"
            it?.hasSinceTag shouldBeEqualTo true
        }
    }

    private fun getSnippetFile(fileName: String) = getSnippetKoScope("core/declaration/kokdoc/snippet/forkokdocsincetagprovider/", fileName)

    companion object {
        @Suppress("unused")
        @JvmStatic
        fun provideValues() =
            listOf(
                arguments("class-with-since-tag", "SampleClass"),
                arguments("function-with-since-tag", "sampleMethod"),
            )
    }
}
