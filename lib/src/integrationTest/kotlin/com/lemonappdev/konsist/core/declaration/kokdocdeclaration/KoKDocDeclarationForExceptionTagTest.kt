package com.lemonappdev.konsist.core.declaration.kokdocdeclaration

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import com.lemonappdev.konsist.api.KoKDocTag.EXCEPTION
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldHaveSize
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.MethodSource

class KoKDocDeclarationForExceptionTagTest {
    @ParameterizedTest
    @MethodSource("provideValues")
    fun `exception-tag`(
        fileName: String,
        declarationName: String,
    ) {
        // given
        val sut = getSnippetFile(fileName)
            .baseDeclarations(includeNested = true)
            .first { it.name == declarationName }
            .kDoc

        // then
        assertSoftly(sut) {
            it?.exceptionTags?.shouldHaveSize(1)
            it?.exceptionTags?.get(0)?.name shouldBeEqualTo EXCEPTION
            it?.exceptionTags?.get(0)?.value shouldBeEqualTo "NullPointerException"
            it?.exceptionTags?.get(0)?.description shouldBeEqualTo "Second sample description"
        }
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/kokdocdeclaration/snippet/forexceptiontag/", fileName)

    companion object {
        @Suppress("unused")
        @JvmStatic
        fun provideValues() = listOf(
            arguments("class-with-exception-tag", "SampleClass"),
            arguments("function-with-exception-tag", "sampleMethod"),
        )
    }
}
