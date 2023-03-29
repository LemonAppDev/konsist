package com.lemon.konsist.core.koscope

import com.lemon.konsist.TestSnippetProvider
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoScopeForDeclarationTest {

    @Test
    fun `file-with-class-function-object-interface-property`() {
        // given
        val sut = getSut("file-with-class-function-object-interface-property")

        // then
        val expected = listOf("SampleClass", "SampleInterface", "SampleObject", "sampleProperty", "SampleFunction")

        sut
            .declarations()
            .map { it.name } shouldBeEqualTo expected
    }

    @Test
    fun `file-with-one-class-containing-function`() {
        // given
        val sut = getSut("file-with-one-class-containing-function")

        // then
        sut
            .declarations(includeNested = true)
            .map { it.name } shouldBeEqualTo listOf("sampleNestedFunction")
    }

    @Test
    fun `file-with-one-class-containing-function-and-property`() {
        // given
        val sut = getSut("file-with-one-class-containing-function-and-property")

        // then
        val expected = listOf("sampleProperty", "sampleNestedFunction")

        sut
            .declarations(includeNested = true)
            .map { it.name } shouldBeEqualTo expected
    }

    private fun getSut(fileName: String) = TestSnippetProvider.getSnippetKoScope("core/koscope/snippet/fordeclaration/$fileName.kt.txt")
}
