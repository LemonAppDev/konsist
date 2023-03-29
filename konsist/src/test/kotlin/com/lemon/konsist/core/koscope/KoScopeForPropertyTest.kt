package com.lemon.konsist.core.koscope

import com.lemon.konsist.TestSnippetProvider
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoScopeForPropertyTest {

    @Test
    fun `file-with-one-property`() {
        // given
        val sut = getSut("file-with-one-property")

        // then
        sut
            .properties()
            .map { it.name } shouldBeEqualTo listOf("sampleProperty")
    }

    @Test
    fun `file-without-property`() {
        // given
        val sut = getSut("file-without-property")

        // then
        sut
            .properties()
            .isEmpty()
    }

    @Test
    fun `file-with-two-properties-with-nested-property`() {
        // given
        val sut = getSut("file-with-one-class-containing-property")

        // then
        sut
            .properties(includeNested = true)
            .map { it.name } shouldBeEqualTo listOf("sampleProperty")
    }

    private fun getSut(fileName: String) = TestSnippetProvider.getSnippetKoScope("core/koscope/snippet/forproperty/$fileName.kt.txt")
}
