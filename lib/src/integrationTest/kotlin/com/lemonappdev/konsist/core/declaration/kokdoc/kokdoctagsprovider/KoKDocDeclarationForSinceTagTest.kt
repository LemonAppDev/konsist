package com.lemonappdev.konsist.core.declaration.kokdoc.kokdoctagsprovider

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import com.lemonappdev.konsist.api.KoKDocTag.SINCE
import com.lemonappdev.konsist.api.provider.KoKDocProvider
import com.lemonappdev.konsist.api.provider.KoNameProvider
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.MethodSource

class KoKDocDeclarationForSinceTagTest {
    @ParameterizedTest
    @MethodSource("provideValues")
    fun `since-tag`(
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
            it?.sinceTag?.name shouldBeEqualTo SINCE
            it?.sinceTag?.description shouldBeEqualTo "1.0.0"
        }
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/kokdocdeclaration/kokdoctagsprovider/snippet/forsincetag/", fileName)

    companion object {
        @Suppress("unused")
        @JvmStatic
        fun provideValues() = listOf(
            arguments("class-with-since-tag", "SampleClass"),
            arguments("function-with-since-tag", "sampleMethod"),
        )
    }
}
