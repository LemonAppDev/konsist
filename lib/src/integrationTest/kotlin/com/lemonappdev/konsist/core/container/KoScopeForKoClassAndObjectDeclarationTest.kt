package com.lemonappdev.konsist.core.container

import com.lemonappdev.konsist.TestSnippetProvider
import com.lemonappdev.konsist.api.declaration.KoClassDeclaration
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.provider.Arguments.arguments

class KoScopeForKoClassAndObjectDeclarationTest {
    @Test
    fun `scope-contains-no-class-and-object`() {
        // given
        val sut = getSnippetFile("scope-contains-no-class-and-object")

        // then
        sut
            .classesAndObjects()
            .shouldBeEqualTo(emptyList())
    }

    @Test
    fun `scope-contains-nested-and-local-classes-and-objects includeNested true includeLocal true`() {
        // given
        val sut = getSnippetFile("scope-contains-nested-and-local-classes-and-objects")

        // then
        val expected = listOf(
            "SampleLocalClass",
            "SampleClassNestedInsideObject",
            "SampleObject",
            "SampleObjectNestedInsideObject"
        )

        sut.classesAndObjects(includeNested = true, includeLocal = true)
            .map { it.name }
            .shouldBeEqualTo(expected)
    }

    @Test
    fun `scope-contains-nested-and-local-classes-and-objects includeNested true includeLocal false`() {
        // given
        val sut = getSnippetFile("scope-contains-nested-and-local-classes-and-objects")

        // then
        val expected = listOf(
            "SampleClassNestedInsideObject",
            "SampleObject",
            "SampleObjectNestedInsideObject"
        )

        sut.classesAndObjects(includeNested = true, includeLocal = false)
            .map { it.name }
            .shouldBeEqualTo(expected)
    }

    @Test
    fun `scope-contains-nested-and-local-classes-and-objects includeNested false includeLocal true`() {
        // given
        val sut = getSnippetFile("scope-contains-nested-and-local-classes-and-objects")

        // then
        val expected = listOf("SampleLocalClass", "SampleObject")

        sut.classesAndObjects(includeNested = false, includeLocal = true)
            .map { it.name }
            .shouldBeEqualTo(expected)
    }

    @Test
    fun `scope-contains-nested-and-local-classes-and-objects includeNested false includeLocal false`() {
        // given
        val sut = getSnippetFile("scope-contains-nested-and-local-classes-and-objects")

        // then
        val expected = listOf("SampleObject")

        sut.classesAndObjects(includeNested = false, includeLocal = false)
            .map { it.name }
            .shouldBeEqualTo(expected)
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("core/container/snippet/forkoclassandobjectdeclaration/", fileName)

    companion object {
        @Suppress("unused")
        @JvmStatic
        fun provideValues() =
            listOf(
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
