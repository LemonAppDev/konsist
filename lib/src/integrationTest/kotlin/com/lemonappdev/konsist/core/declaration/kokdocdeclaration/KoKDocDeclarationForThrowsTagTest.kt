package com.lemonappdev.konsist.core.declaration.kokdocdeclaration

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import com.lemonappdev.konsist.api.KoKDocTag.THROWS
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldHaveSize
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.MethodSource

class KoKDocDeclarationForThrowsTagTest {
    @ParameterizedTest
    @MethodSource("provideValues")
    fun `throws-tag`(
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
            it?.throwsTags?.shouldHaveSize(1)
            it?.throwsTags?.get(0)?.name shouldBeEqualTo THROWS
            it?.throwsTags?.get(0)?.value shouldBeEqualTo "IllegalArgumentException"
            it?.throwsTags?.get(0)?.description shouldBeEqualTo "First sample description"
        }
    }

    private fun getSnippetFile(fileName: String) = getSnippetKoScope("core/declaration/kokdocdeclaration/snippet/forthrowstag/", fileName)

    companion object {
        @Suppress("unused")
        @JvmStatic
        fun provideValues() = listOf(
            arguments("class-with-throws-tag", "SampleClass"),
            arguments("function-with-throws-tag", "sampleMethod"),
        )
    }
}
