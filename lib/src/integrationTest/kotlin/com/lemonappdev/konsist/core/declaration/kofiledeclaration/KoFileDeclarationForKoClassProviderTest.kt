package com.lemonappdev.konsist.core.container.kofile

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import com.lemonappdev.konsist.api.declaration.KoClassDeclaration
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoFileDeclarationForKoClassProviderTest {
    @Test
    fun `file-contains-no-classes`() {
        // given
        val sut = getSnippetFile("file-contains-no-classes")
            .files
            .first()

        // then
        sut.classes(includeNested = true, includeLocal = true).toList() shouldBeEqualTo emptyList()
    }

    @Test
    fun `file-contains-nested-and-local-classes includeNested true includeLocal true`() {
        // given
        val sut = getSnippetFile("file-contains-nested-and-local-classes")
            .files
            .first()

        // then
        val expected = listOf("SampleLocalClass", "SampleClassNestedInsideObject")

        sut.classes(includeNested = true, includeLocal = true)
            .toList()
            .map { it.name }
            .shouldBeEqualTo(expected)
    }

    @Test
    fun `file-contains-nested-and-local-classes includeNested true includeLocal false`() {
        // given
        val sut = getSnippetFile("file-contains-nested-and-local-classes")
            .files
            .first()

        // then
        val expected = listOf("SampleClassNestedInsideObject")

        sut.classes(includeNested = true, includeLocal = false)
            .toList()
            .map { it.name }
            .shouldBeEqualTo(expected)
    }

    @Test
    fun `file-contains-nested-and-local-classes includeNested false includeLocal true`() {
        // given
        val sut = getSnippetFile("file-contains-nested-and-local-classes")
            .files
            .first()

        // then
        val expected = listOf("SampleLocalClass")

        sut.classes(includeNested = false, includeLocal = true)
            .toList()
            .map { it.name }
            .shouldBeEqualTo(expected)
    }

    @Test
    fun `file-contains-nested-and-local-classes includeNested false includeLocal false`() {
        // given
        val sut = getSnippetFile("file-contains-nested-and-local-classes")
            .files
            .first()

        // then
        val expected = emptyList<KoClassDeclaration>()

        sut.classes(includeNested = false, includeLocal = false)
            .toList()
            .map { it.name }
            .shouldBeEqualTo(expected)
    }

    @Test
    fun `contains-classes`() {
        // given
        val sut = getSnippetFile("contains-classes")
            .files
            .first()

        // then
        assertSoftly(sut) {
            numClasses(includeNested = false) shouldBeEqualTo 1
            numClasses(includeNested = true) shouldBeEqualTo 2
            containsClass("SampleClass", includeNested = false) shouldBeEqualTo true
            containsClass("SampleNestedClass", includeNested = false) shouldBeEqualTo false
            containsClass("SampleNestedClass", includeNested = true) shouldBeEqualTo true
            containsClass("NonExisting") shouldBeEqualTo false
        }
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/kofiledeclaration/snippet/forkoclassprovider/", fileName)
}
