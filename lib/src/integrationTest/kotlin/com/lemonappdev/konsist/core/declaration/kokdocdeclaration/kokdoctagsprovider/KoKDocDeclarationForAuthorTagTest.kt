package com.lemonappdev.konsist.core.declaration.kokdocdeclaration.kokdoctagsprovider

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import com.lemonappdev.konsist.api.KoKDocTag.AUTHOR
import com.lemonappdev.konsist.api.provider.KoKDocProvider
import com.lemonappdev.konsist.api.provider.KoNameProvider
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldHaveSize
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.MethodSource

class KoKDocDeclarationForAuthorTagTest {
    @ParameterizedTest
    @MethodSource("provideValues")
    fun `author-tag`(
        fileName: String,
        declarationName: String,
    ) {
        // given
        val sut = (
                getSnippetFile(fileName)
                    .declarations(includeNested = true)
                    .filterIsInstance<KoNameProvider>()
                    .first { it.name == declarationName } as KoKDocProvider
                )
            .kDoc

        // then
        assertSoftly(sut) {
            it?.authorTags?.shouldHaveSize(2)
            it?.authorTags?.get(0)?.name shouldBeEqualTo AUTHOR
            it?.authorTags?.get(0)?.description shouldBeEqualTo "Author1"
            it?.authorTags?.get(1)?.name shouldBeEqualTo AUTHOR
            it?.authorTags?.get(1)?.description shouldBeEqualTo "Author2"
        }
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/kokdocdeclaration/kokdoctagsprovider/snippet/forauthortag/", fileName)

    companion object {
        @Suppress("unused")
        @JvmStatic
        fun provideValues() = listOf(
            arguments("class-with-author-tag", "SampleClass"),
            arguments("function-with-author-tag", "sampleMethod"),
        )
    }
}
