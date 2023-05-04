package com.lemonappdev.konsist.core.scope.koscope

import com.lemonappdev.konsist.TestSnippetProvider
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoScopeForDeclarationTest {

    @Test
    fun `file-contains-class-function-object-interface-property`() {
        // given
        val sut = getSnippetFile("file-contains-class-function-object-interface-property")

        // then
        sut
            .declarations()
            .map { it.name }
            .toList()
            .shouldBeEqualTo(
                listOf(
                    "sampleProperty",
                    "sampleFunction",
                    "SampleClass",
                    "SampleInterface",
                    "SampleObject",
                ),
            )
    }

    @Test
    fun `file-contains-one-class-containing-function`() {
        // given
        val sut = getSnippetFile("file-contains-one-class-containing-function")

        // then
        sut
            .declarations(includeNested = true)
            .map { it.name }
            .toList()
            .shouldBeEqualTo(
                listOf(
                    "SampleClass",
                    "sampleNestedFunction",
                ),
            )
    }

    @Test
    fun `file-contains-one-class-containing-function-and-property includeNested true`() {
        // given
        val sut = getSnippetFile("file-contains-one-class-containing-function-and-property")

        // then
        sut
            .declarations(includeNested = true)
            .map { it.name }
            .toList()
            .shouldBeEqualTo(
                listOf(
                    "SampleClass",
                    "sampleNestedProperty",
                    "sampleNestedFunction",
                ),
            )
    }

    @Test
    fun `file-contains-one-class-containing-function-and-property includeNested false`() {
        // given
        val sut = getSnippetFile("file-contains-one-class-containing-function-and-property")

        // then
        sut
            .declarations(includeNested = false)
            .map { it.name }
            .toList()
            .shouldBeEqualTo(listOf("SampleClass"))
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("core/scope/koscope/snippet/fordeclaration/", fileName)
}
