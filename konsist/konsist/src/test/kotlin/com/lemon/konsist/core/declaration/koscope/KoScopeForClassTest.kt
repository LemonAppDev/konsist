package com.lemon.konsist.core.declaration.koscope

import com.lemon.konsist.TestSnippetProvider
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoScopeForClassTest {

    @Test
    fun `file-contains-one-class`() {
        // given
        val sut = getSut("file-contains-one-class")

        // then
        sut
            .classes()
            .toList()
            .map { it.name }
            .shouldBeEqualTo(listOf("SampleClass"))
    }

    @Test
    fun `file-contains-no-class`() {
        // given
        val sut = getSut("file-contains-no-class")

        // then
        sut
            .classes()
            .toList()
            .shouldBeEqualTo(emptyList())
    }

    @Test
    fun `file-contains-two-classes-with-nested-class includeNested true`() {
        // given
        val sut = getSut("file-contains-two-classes-with-nested-class")

        // then
        sut
            .classes(includeNested = true)
            .toList()
            .map { it.name }
            .shouldBeEqualTo(
                listOf(
                    "SampleClass1",
                    "SampleNestedClass",
                    "SampleClass2",
                ),
            )
    }

    @Test
    fun `file-contains-two-classes-with-nested-class includeNested false`() {
        // given
        val sut = getSut("file-contains-two-classes-with-nested-class")

        // then
        sut
            .classes(includeNested = false)
            .toList()
            .map { it.name }
            .shouldBeEqualTo(
                listOf(
                    "SampleClass1",
                    "SampleClass2",
                ),
            )
    }

    private fun getSut(fileName: String) = TestSnippetProvider.getSnippetKoScope("core/declaration/koscope/snippet/forclass/", fileName)
}
