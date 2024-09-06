package com.lemonappdev.konsist.core.declaration.kokdoc

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import com.lemonappdev.konsist.api.KoKDocTag.AUTHOR
import com.lemonappdev.konsist.api.provider.KoKDocProvider
import com.lemonappdev.konsist.api.provider.KoNameProvider
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.MethodSource

class KoKDocDeclarationForKoKDocAuthorTagProviderTest {
    @Test
    fun `kdoc-without-author-tag`() {
        // given
        val sut =
            getSnippetFile("kdoc-without-author-tag")
                .classes()
                .first()
                .kDoc

        // then
        assertSoftly(sut) {
            it?.authorTags shouldBeEqualTo emptyList()
            it?.numAuthorTags shouldBeEqualTo 0
            it?.hasAuthorTags shouldBeEqualTo false
        }
    }

    @ParameterizedTest
    @MethodSource("provideValues")
    fun `author-tag`(
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
            it?.numAuthorTags shouldBeEqualTo 2
            it?.authorTags?.get(0)?.name shouldBeEqualTo AUTHOR
            it?.authorTags?.get(0)?.description shouldBeEqualTo "Author1"
            it?.authorTags?.get(1)?.name shouldBeEqualTo AUTHOR
            it?.authorTags?.get(1)?.description shouldBeEqualTo "Author2"
            it?.hasAuthorTags shouldBeEqualTo true
        }
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/kokdoc/snippet/forkokdocauthortagprovider/", fileName)

    companion object {
        @Suppress("unused")
        @JvmStatic
        fun provideValues() =
            listOf(
                arguments("class-with-author-tag", "SampleClass"),
                arguments("function-with-author-tag", "sampleMethod"),
            )
    }
}
