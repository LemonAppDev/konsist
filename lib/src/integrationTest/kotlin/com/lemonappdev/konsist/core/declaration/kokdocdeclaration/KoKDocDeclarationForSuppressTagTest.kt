package com.lemonappdev.konsist.core.declaration.kokdocdeclaration

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import com.lemonappdev.konsist.api.KoKDocTag.SUPPRESS
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.MethodSource

class KoKDocDeclarationForSuppressTagTest {
    @ParameterizedTest
    @MethodSource("provideValues")
    fun `suppress-tag`(
        fileName: String,
        declarationName: String,
    ) {
        // given
        val sut = getSnippetFile(fileName)
            .namedDeclarations(includeNested = true)
            .first { it.name == declarationName }
            .kDoc

        // then
        assertSoftly(sut) {
            it?.suppressTag?.name shouldBeEqualTo SUPPRESS
            it?.suppressTag?.description shouldBeEqualTo "UnusedPrivateMember"
        }
    }

    private fun getSnippetFile(fileName: String) = getSnippetKoScope("core/declaration/kokdocdeclaration/snippet/forsuppresstag/", fileName)

    companion object {
        @Suppress("unused")
        @JvmStatic
        fun provideValues() = listOf(
            arguments("class-with-suppress-tag", "SampleClass"),
            arguments("function-with-suppress-tag", "sampleMethod"),
        )
    }
}
