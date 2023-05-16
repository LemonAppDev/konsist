package com.lemonappdev.konsist.core.scope.koscope

import com.lemonappdev.konsist.TestSnippetProvider
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoScopeForObjectTest {

    @Test
    fun `file-contains-one-object`() {
        // given
        val sut = getSnippetFile("file-contains-one-object")

        // then
        sut
            .objects()
            .map { it.name }
            .toList()
            .shouldBeEqualTo(listOf("SampleObject"))
    }

    @Test
    fun `file-contains-no-object`() {
        // given
        val sut = getSnippetFile("file-contains-no-object")

        // then
        sut
            .objects()
            .toList()
            .shouldBeEqualTo(emptyList())
    }

    @Test
    fun `file-contains-object-with-nested-objects includeNested true`() {
        // given
        val sut = getSnippetFile("file-contains-object-with-nested-objects")

        // then
        sut
            .objects(includeNested = true)
            .map { it.name }
            .toList()
            .shouldBeEqualTo(
                listOf(
                    "SampleObject",
                    "SampleNestedObject1",
                    "SampleNestedObject2",
                ),
            )
    }

    @Test
    fun `file-contains-object-with-nested-objects includeNested false`() {
        // given
        val sut = getSnippetFile("file-contains-object-with-nested-objects")

        // then
        sut
            .objects(includeNested = false)
            .map { it.name }
            .toList()
            .shouldBeEqualTo(listOf("SampleObject"))
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("core/scope/koscope/snippet/forobject/", fileName)
}
