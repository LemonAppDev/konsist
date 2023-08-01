package com.lemonappdev.konsist.core.container.koscope

import com.lemonappdev.konsist.TestSnippetProvider
import com.lemonappdev.konsist.api.declaration.KoClassDeclaration
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.provider.Arguments.arguments

class KoScopeForKoClassProviderTest {
    @Test
    fun `scope-contains-no-class`() {
        // given
        val sut = getSnippetFile("scope-contains-no-class")

        // then
        sut
            .classes()
            .shouldBeEqualTo(emptyList())
    }

    @Test
    fun `scope-contains-nested-and-local-classes includeNested true includeLocal true`() {
        // given
        val sut = getSnippetFile("scope-contains-nested-and-local-classes")

        // then
        val expected = listOf("SampleLocalClass", "SampleClassNestedInsideObject")

        sut.classes(includeNested = true, includeLocal = true)
            .map { it.name }
            .shouldBeEqualTo(expected)
    }

    @Test
    fun `scope-contains-nested-and-local-classes includeNested true includeLocal false`() {
        // given
        val sut = getSnippetFile("scope-contains-nested-and-local-classes")

        // then
        val expected = listOf("SampleClassNestedInsideObject")

        sut.classes(includeNested = true, includeLocal = false)
            .map { it.name }
            .shouldBeEqualTo(expected)
    }

    @Test
    fun `scope-contains-nested-and-local-classes includeNested false includeLocal true`() {
        // given
        val sut = getSnippetFile("scope-contains-nested-and-local-classes")

        // then
        val expected = listOf("SampleLocalClass")

        sut.classes(includeNested = false, includeLocal = true)
            .map { it.name }
            .shouldBeEqualTo(expected)
    }

    @Test
    fun `scope-contains-nested-and-local-classes includeNested false includeLocal false`() {
        // given
        val sut = getSnippetFile("scope-contains-nested-and-local-classes")

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
        TestSnippetProvider.getSnippetKoScope("core/container/koscope/snippet/forkoclassprovider/", fileName)

    companion object {
        @Suppress("unused")
        @JvmStatic
        fun provideValues() = listOf(
            arguments(
                false,
                false,
                listOf("SampleClass"),
            ),
            arguments(
                true,
                false,
                listOf(
                    "SampleClass",
                    "SampleNestedClass1",
                    "SampleNestedClass2",
                ),
            ),
            arguments(
                false,
                true,
                listOf("SampleClass"),
            ),
            arguments(
                true,
                true,
                listOf(
                    "SampleClass",
                    "SampleLocalClass",
                    "SampleNestedClass1",
                    "SampleNestedClass2",
                ),
            ),
        )
    }
}
