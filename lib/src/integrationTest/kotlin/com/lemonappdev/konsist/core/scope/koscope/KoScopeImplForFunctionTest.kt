package com.lemonappdev.konsist.core.scope.koscope

import com.lemonappdev.konsist.TestSnippetProvider
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoScopeImplForFunctionTest {

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
    fun `file-contains-two-functions-with-nested-function includeNested true`() {
        // given
        val sut = getSnippetFile("file-contains-two-functions-with-nested-function")

        // then
        sut
            .functions(includeNested = true)
            .map { it.name }
            .toList()
            .shouldBeEqualTo(
                listOf(
                    "sampleFunction1",
                    "sampleFunction2",
                ),
            )
    }

    @Test
    fun `file-contains-two-functions-with-nested-function includeNested false`() {
        // given
        val sut = getSnippetFile("file-contains-two-functions-with-nested-function")

        // then
        sut
            .functions(includeNested = false)
            .map { it.name }
            .toList()
            .shouldBeEqualTo(
                listOf(
                    "sampleFunction1",
                    "sampleFunction2",
                ),
            )
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("core/scope/koscope/snippet/forfunction/", fileName)
}
