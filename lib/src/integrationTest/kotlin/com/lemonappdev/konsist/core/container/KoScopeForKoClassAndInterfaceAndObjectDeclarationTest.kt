package com.lemonappdev.konsist.core.container

import com.lemonappdev.konsist.TestSnippetProvider
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.provider.Arguments.arguments

class KoScopeForKoClassAndInterfaceAndObjectDeclarationTest {
    @Test
    fun `scope-contains-no-class-and-interface-and-object`() {
        // given
        val sut = getSnippetFile("scope-contains-no-class-and-interface-and-object")

        // then
        sut
            .classesAndInterfacesAndObjects()
            .shouldBeEqualTo(emptyList())
    }

    @Test
    fun `scope-contains-nested-and-local-classes-and-interfaces-and-objects includeNested true includeLocal true`() {
        // given
        val sut = getSnippetFile("scope-contains-nested-and-local-classes-and-interfaces-and-objects")

        // then
        val expected =
            listOf(
                "SampleLocalClass",
                "SampleClassNestedInsideObject",
                "SampleInterfaceNestedInsideObject",
                "SampleObject",
                "SampleObjectNestedInsideObject",
            )

        sut
            .classesAndInterfacesAndObjects(includeNested = true, includeLocal = true)
            .map { it.name }
            .shouldBeEqualTo(expected)
    }

    @Test
    fun `scope-contains-nested-and-local-classes-and-interfaces-and-objects includeNested true includeLocal false`() {
        // given
        val sut = getSnippetFile("scope-contains-nested-and-local-classes-and-interfaces-and-objects")

        // then
        val expected =
            listOf(
                "SampleClassNestedInsideObject",
                "SampleInterfaceNestedInsideObject",
                "SampleObject",
                "SampleObjectNestedInsideObject",
            )

        sut
            .classesAndInterfacesAndObjects(includeNested = true, includeLocal = false)
            .map { it.name }
            .shouldBeEqualTo(expected)
    }

    @Test
    fun `scope-contains-nested-and-local-classes-and-interfaces-and-objects includeNested false includeLocal true`() {
        // given
        val sut = getSnippetFile("scope-contains-nested-and-local-classes-and-interfaces-and-objects")

        // then
        val expected = listOf("SampleLocalClass", "SampleObject")

        sut
            .classesAndInterfacesAndObjects(includeNested = false, includeLocal = true)
            .map { it.name }
            .shouldBeEqualTo(expected)
    }

    @Test
    fun `scope-contains-nested-and-local-classes-and-interfaces-and-objects includeNested false includeLocal false`() {
        // given
        val sut = getSnippetFile("scope-contains-nested-and-local-classes-and-interfaces-and-objects")

        // then
        val expected = listOf("SampleObject")

        sut
            .classesAndInterfacesAndObjects(includeNested = false, includeLocal = false)
            .map { it.name }
            .shouldBeEqualTo(expected)
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("core/container/snippet/forkoclassandinterfaceandobjectdeclaration/", fileName)

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
