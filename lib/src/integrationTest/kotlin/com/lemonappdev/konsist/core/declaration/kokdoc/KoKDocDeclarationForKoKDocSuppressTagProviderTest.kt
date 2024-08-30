package com.lemonappdev.konsist.core.declaration.kokdoc

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import com.lemonappdev.konsist.api.KoKDocTag.SUPPRESS
import com.lemonappdev.konsist.api.provider.KoKDocProvider
import com.lemonappdev.konsist.api.provider.KoNameProvider
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.MethodSource

class KoKDocDeclarationForKoKDocSuppressTagProviderTest {
    @Test
    fun `kdoc-without-suppress-tag`() {
        // given
        val sut =
            getSnippetFile("kdoc-without-suppress-tag")
                .classes()
                .first()
                .kDoc

        // then
        assertSoftly(sut) {
            it?.suppressTag shouldBeEqualTo null
            it?.hasSuppressTag shouldBeEqualTo false
        }
    }

    @ParameterizedTest
    @MethodSource("provideValues")
    fun `suppress-tag`(
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
            it?.suppressTag?.name shouldBeEqualTo SUPPRESS
            it?.suppressTag?.description shouldBeEqualTo "UnusedPrivateMember"
            it?.hasSuppressTag shouldBeEqualTo true
        }
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/kokdoc/snippet/forkokdocsuppresstagprovider/", fileName)

    companion object {
        @Suppress("unused")
        @JvmStatic
        fun provideValues() =
            listOf(
                arguments("class-with-suppress-tag", "SampleClass"),
                arguments("function-with-suppress-tag", "sampleMethod"),
            )
    }
}
