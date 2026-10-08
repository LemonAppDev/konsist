package com.lemonappdev.konsist.core.declaration.koenumconstant

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class KoEnumConstantDeclarationForKoOrdinalProviderTest {
    @ParameterizedTest
    @ValueSource(
        strings = [
            "enum-constant-ordinal",
            "enum-constant-with-body-and-arguments-ordinal",
            "nested-enum-constant-ordinal",
        ],
    )
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
}
