package com.lemonappdev.konsist.core.declaration.koclass

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import com.lemonappdev.konsist.api.declaration.KoClassDeclaration
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoClassDeclarationForKoClassProviderTest {
    @Test
    fun `class-contains-no-classes`() {
        // given
        val sut = getSnippetFile("class-contains-no-classes")
            .classes()
            .first()

        // then
        sut.classes(includeNested = true, includeLocal = true) shouldBeEqualTo emptyList()
    }

    @Test
    fun `class-contains-nested-and-local-classes includeNested true includeLocal true`() {
        // given
        val sut = getSnippetFile("class-contains-nested-and-local-classes")
            .classes()
            .first()

        // then
        val expected = listOf("SampleLocalClass", "SampleClassNestedInsideObject")

        sut.classes(includeNested = true, includeLocal = true)
            .map { it.name }
            .shouldBeEqualTo(expected)
    }

    @Test
    fun `class-contains-nested-and-local-classes includeNested true includeLocal false`() {
        // given
        val sut = getSnippetFile("class-contains-nested-and-local-classes")
            .classes()
            .first()

        // then
        val expected = listOf("SampleClassNestedInsideObject")

        sut.classes(includeNested = true, includeLocal = false)
            .map { it.name }
            .shouldBeEqualTo(expected)
    }

    @Test
    fun `class-contains-nested-and-local-classes includeNested false includeLocal true`() {
        // given
        val sut = getSnippetFile("class-contains-nested-and-local-classes")
            .classes()
            .first()

        // then
        val expected = listOf("SampleLocalClass")

        sut.classes(includeNested = false, includeLocal = true)
            .map { it.name }
            .shouldBeEqualTo(expected)
    }

    @Test
    fun `class-contains-nested-and-local-classes includeNested false includeLocal false`() {
        // given
        val sut = getSnippetFile("class-contains-nested-and-local-classes")
            .classes()
            .first()

        // then
        val expected = emptyList<KoClassDeclaration>()

        sut.classes(includeNested = false, includeLocal = false)
            .map { it.name }
            .shouldBeEqualTo(expected)
    }

    @Test
    fun `contains-classes`() {
        // given
        val sut = getSnippetFile("contains-classes")
            .classes()
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
        getSnippetKoScope("core/declaration/koclassdeclaration/snippet/forkoclassprovider/", fileName)
}
