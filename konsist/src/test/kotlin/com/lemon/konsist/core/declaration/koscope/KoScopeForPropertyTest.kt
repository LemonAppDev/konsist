package com.lemon.konsist.core.declaration.koscope

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
    fun `file-with-one-class-containing-property includeNested true`() {
        // given
        val sut = getSut("file-with-one-class-containing-property")
            .properties(includeNested = true)

        // then
        sut
            .map { it.name } shouldBeEqualTo listOf("sampleNestedProperty")
    }

    @Test
    fun `file-with-one-class-containing-property includeNested false`() {
        // given
        val sut = getSut("file-with-one-class-containing-property")
            .properties(includeNested = false)
        // then
        sut
            .map { it.name } shouldBeEqualTo listOf()
    }

    private fun getSut(fileName: String) = TestSnippetProvider.getSnippetKoScope("koscope/snippet/forproperty/$fileName.kttxt")
}
