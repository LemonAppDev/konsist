package com.lemonappdev.konsist.core.declaration.koenumconstant

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.MethodSource

class KoEnumConstantDeclarationForKoOrdinalProviderTest {
    @ParameterizedTest
    @MethodSource("provideValues")
    fun `enum-constant-ordinal`(fileName: String) {
        // given
        val sut =
            getSnippetFile(fileName)
                .classes()
                .first { it.name == "SampleClass" }
                .enumConstants

        // then
        sut.map { it.name to it.ordinal } shouldBeEqualTo
            listOf(
                "SAMPLE_CONSTANT_1" to 0,
                "SAMPLE_CONSTANT_2" to 1,
                "SAMPLE_CONSTANT_3" to 2,
            )
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/koenumconstant/snippet/forkoordinalprovider/", fileName)

    companion object {
        @Suppress("unused")
        @JvmStatic
        fun provideValues() =
            listOf(
                arguments("enum-constant-ordinal"),
                arguments("enum-constant-with-body-and-arguments-ordinal"),
                arguments("nested-enum-constant-ordinal"),
            )
    }
}
