package com.lemonappdev.konsist.core.declaration.koobject

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoObjectDeclarationForKoClassProviderTest {
    @Test
    fun `object-contains-no-classes`() {
        // given
        val sut = getSnippetFile("object-contains-no-classes")
            .objects()
            .first()

        // then
        sut.classes(includeNested = true, includeLocal = true) shouldBeEqualTo emptyList()
    }

    @Test
    fun `object-contains-nested-and-local-classes includeNested true includeLocal true`() {
        // given
        val sut = getSnippetFile("object-contains-nested-and-local-classes")
            .objects()
            .first()

        // then
        val expected = listOf("SampleNestedClass", "SampleLocalClass")

        sut.classes(includeNested = true, includeLocal = true)
            .map { it.name }
            .shouldBeEqualTo(expected)
    }

    @Test
    fun `object-contains-nested-and-local-classes includeNested true includeLocal false`() {
        // given
        val sut = getSnippetFile("object-contains-nested-and-local-classes")
            .objects()
            .first()

        // then
        val expected = listOf("SampleNestedClass")

        sut.classes(includeNested = true, includeLocal = false)
            .map { it.name }
            .shouldBeEqualTo(expected)
    }

    @Test
    fun `object-contains-nested-and-local-classes includeNested false includeLocal true`() {
        // given
        val sut = getSnippetFile("object-contains-nested-and-local-classes")
            .objects()
            .first()

        // then
        val expected = listOf("SampleLocalClass")

        sut.classes(includeNested = false, includeLocal = true)
            .map { it.name }
            .shouldBeEqualTo(expected)
    }

    @Test
    fun `object-contains-nested-and-local-classes includeNested false includeLocal false`() {
        // given
        val sut = getSnippetFile("object-contains-nested-and-local-classes")
            .objects()
            .first()

        // then
        val expected = emptyList<String>()

        sut.classes(includeNested = false, includeLocal = false)
            .map { it.name }
            .shouldBeEqualTo(expected)
    }

    @Test
    fun `contains-classes`() {
        // given
        val sut = getSnippetFile("contains-classes")
            .objects()
            .first()

        // then
        assertSoftly(sut) {
            numClasses() shouldBeEqualTo 1
            numClasses(includeNested = true) shouldBeEqualTo 2
            numClasses(includeLocal = true) shouldBeEqualTo 2
            numClasses(includeNested = true, includeLocal = true) shouldBeEqualTo 3
            containsClass("SampleClass") shouldBeEqualTo true
            containsClass("SampleNestedClass", includeNested = false) shouldBeEqualTo false
            containsClass("SampleNestedClass", includeNested = true) shouldBeEqualTo true
            containsClass("SampleLocalClass", includeLocal = false) shouldBeEqualTo false
            containsClass("SampleLocalClass", includeLocal = true) shouldBeEqualTo true
            containsClass("NonExisting") shouldBeEqualTo false
        }
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/koobject/snippet/forkoclassprovider/", fileName)
}
