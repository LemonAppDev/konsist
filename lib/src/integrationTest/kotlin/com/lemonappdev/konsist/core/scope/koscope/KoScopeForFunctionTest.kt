package com.lemonappdev.konsist.core.scope.koscope

import com.lemonappdev.konsist.TestSnippetProvider
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoScopeForFunctionTest {

    @Test
    fun `file-contains-one-function`() {
        // given
        val sut = getSnippetFile("file-contains-one-function")

        // then
        sut
            .functions()
            .map { it.name }
            .toList()
            .shouldBeEqualTo(listOf("sampleFunction"))
    }

    @Test
    fun `file-contains-no-function`() {
        // given
        val sut = getSnippetFile("file-contains-no-function")

        // then
        sut
            .functions()
            .toList()
            .shouldBeEqualTo(emptyList())
    }

    @Test
    fun `file-contains-function-with-local-and-nested-functions includeNested false includeLocal false`() {
        // given
        val sut = getSnippetFile("file-contains-function-with-local-and-nested-functions")

        // then
        sut
            .functions(includeNested = false, includeLocal = false)
            .map { it.name }
            .toList()
            .shouldBeEqualTo(listOf("sampleFunction"))
    }

    @Test
    fun `file-contains-function-with-local-and-nested-functions includeNested true includeLocal false`() {
        // given
        val sut = getSnippetFile("file-contains-function-with-local-and-nested-functions")

        // then
        sut
            .functions(includeNested = true, includeLocal = false)
            .map { it.name }
            .toList()
            .shouldBeEqualTo(listOf("sampleFunction"))
    }

    @Test
    fun `file-contains-function-with-local-and-nested-functions includeNested false includeLocal true`() {
        // given
        val sut = getSnippetFile("file-contains-function-with-local-and-nested-functions")

        // then
        sut
            .functions(includeNested = false, includeLocal = true)
            .map { it.name }
            .toList()
            .shouldBeEqualTo(
                listOf(
                    "sampleFunction",
                    "sampleLocalFunction",
                ),
            )
    }

    @Test
    fun `file-contains-function-with-local-and-nested-functions includeNested true includeLocal true`() {
        // given
        val sut = getSnippetFile("file-contains-function-with-local-and-nested-functions")

        // then
        sut
            .functions(includeNested = true, includeLocal = true)
            .map { it.name }
            .toList()
            .shouldBeEqualTo(
                listOf(
                    "sampleFunction",
                    "sampleLocalFunction",
                    "sampleNestedFunction"
                ),
            )
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("core/scope/koscope/snippet/forfunction/", fileName)
}
