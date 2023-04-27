package com.lemonappdev.konsist.core.declaration.koscopedeclaration

import com.lemonappdev.konsist.TestSnippetProvider
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoScopeDeclarationForObjectTest {

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
    fun `file-contains-two-objects-with-nested-object includeNested true`() {
        // given
        val sut = getSnippetFile("file-contains-two-objects-with-nested-object")

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
        val sut = getSnippetFile("file-contains-two-objects-with-nested-object")

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

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("core/declaration/koscopedeclaration/snippet/forobject/", fileName)
}
