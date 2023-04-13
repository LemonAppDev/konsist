package com.lemon.konsist.core.declaration.koscope

import com.lemon.konsist.TestSnippetProvider
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoScopeForObjectTest {

    @Test
    fun `file-contains-one-object`() {
        // given
        val sut = getSut("file-contains-one-object")

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
        val sut = getSut("file-contains-no-object")

        // then
        sut
            .objects()
            .toList()
            .shouldBeEqualTo(emptyList())
    }

    @Test
    fun `file-contains-two-objects-with-nested-object includeNested true`() {
        // given
        val sut = getSut("file-contains-two-objects-with-nested-object")

        // then
        sut
            .objects(includeNested = true)
            .map { it.name }
            .toList()
            .shouldBeEqualTo(
                listOf(
                    "SampleObject1",
                    "SampleNestedObject",
                    "SampleObject2",
                ),
            )
    }

    @Test
    fun `file-contains-two-objects-with-nested-object includeNested false`() {
        // given
        val sut = getSut("file-contains-two-objects-with-nested-object")

        // then
        sut
            .objects(includeNested = false)
            .map { it.name }
            .toList()
            .shouldBeEqualTo(
                listOf(
                    "SampleObject1",
                    "SampleObject2",
                ),
            )
    }

    private fun getSut(fileName: String) = TestSnippetProvider.getSnippetKoScope("core/declaration/koscope/snippet/forobject/", fileName)
}
