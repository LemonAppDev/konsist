package com.lemonappdev.konsist.core.declaration.koconstant

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoConstantDeclarationForKoEnumNameProviderTest {
    @Test
    fun `enum-name`() {
        // given
        val sut = getSnippetFile("enum-name")
            .classes()
            .first()
            .constants
            .first()

        // then
        sut.enumName shouldBeEqualTo "SampleClass"
    }

    @Test
    fun `full-enum-name`() {
        // given
        val sut = getSnippetFile("full-enum-name")
            .classes()
            .first()
            .constants
            .first()

        // then
        sut.fullEnumName shouldBeEqualTo "SampleClass.SAMPLE_CONSTANT_1"
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/koconstant/snippet/forkoenumnameprovider/", fileName)
}
