package com.lemonappdev.konsist.core.container

import com.lemonappdev.konsist.TestSnippetProvider
import com.lemonappdev.konsist.api.declaration.combined.KoClassAndInterfaceDeclaration
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.provider.Arguments.arguments

class KoScopeForKoClassAndInterfaceDeclarationTest {
    @Test
    fun `scope-contains-no-class-and-interface`() {
        // given
        val sut = getSnippetFile("scope-contains-no-class-and-interface")

        // then
        sut
            .classesAndInterfaces()
            .shouldBeEqualTo(emptyList())
    }

    @Test
    fun `scope-contains-nested-and-local-classes-and-interfaces includeNested true includeLocal true`() {
        // given
        val sut = getSnippetFile("scope-contains-nested-and-local-classes-and-interfaces")

        // then
        val expected =
            listOf(
                "SampleLocalClass",
                "SampleClassNestedInsideObject",
                "SampleInterfaceNestedInsideObject",
            )

        sut.classesAndInterfaces(includeNested = true, includeLocal = true)
            .map { it.name }
            .shouldBeEqualTo(expected)
    }

    @Test
    fun `scope-contains-nested-and-local-classes-and-interfaces includeNested true includeLocal false`() {
        // given
        val sut = getSnippetFile("scope-contains-nested-and-local-classes-and-interfaces")

        // then
        val expected =
            listOf(
                "SampleClassNestedInsideObject",
                "SampleInterfaceNestedInsideObject",
            )

        sut.classesAndInterfaces(includeNested = true, includeLocal = false)
            .map { it.name }
            .shouldBeEqualTo(expected)
    }

    @Test
    fun `scope-contains-nested-and-local-classes-and-interfaces includeNested false includeLocal true`() {
        // given
        val sut = getSnippetFile("scope-contains-nested-and-local-classes-and-interfaces")

        // then
        val expected = listOf("SampleLocalClass")

        sut.classesAndInterfaces(includeNested = false, includeLocal = true)
            .map { it.name }
            .shouldBeEqualTo(expected)
    }

    @Test
    fun `scope-contains-nested-and-local-classes-and-interfaces includeNested false includeLocal false`() {
        // given
        val sut = getSnippetFile("scope-contains-nested-and-local-classes-and-interfaces")

        // then
        val expected = emptyList<KoClassAndInterfaceDeclaration>()

        sut.classesAndInterfaces(includeNested = false, includeLocal = false)
            .map { it.name }
            .shouldBeEqualTo(expected)
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("core/container/snippet/forkoclassandinterfacedeclaration/", fileName)

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
