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
        sut
            .declarations()
            .map { it.name } shouldBeEqualTo listOf("sampleProperty", "sampleFunction", "SampleClass", "SampleInterface", "SampleObject")
    }

    @Test
    fun `file-with-one-class-containing-function`() {
        // given
        val sut = getSut("file-with-one-class-containing-function")

        // then
        sut
            .declarations(includeNested = true)
            .map { it.name } shouldBeEqualTo listOf("SampleClass", "sampleNestedFunction")
    }

    @Test
    fun `file-with-one-class-containing-function-and-property includeNested true`() {
        // given
        val sut = getSut("file-with-one-class-containing-function-and-property")

        // then
        sut
            .declarations(includeNested = true)
            .map { it.name } shouldBeEqualTo listOf("SampleClass", "sampleNestedProperty", "sampleNestedFunction")
    }

    @Test
    fun `file-with-one-class-containing-function-and-property includeNested false`() {
        // given
        val sut = getSut("file-with-one-class-containing-function-and-property")

        // then
        sut
            .declarations(includeNested = false)
            .map { it.name } shouldBeEqualTo listOf("SampleClass")
    }

    private fun getSut(fileName: String) = TestSnippetProvider.getSnippetKoScope("core/koscope/snippet/fordeclaration/$fileName.kt.txt")
}
