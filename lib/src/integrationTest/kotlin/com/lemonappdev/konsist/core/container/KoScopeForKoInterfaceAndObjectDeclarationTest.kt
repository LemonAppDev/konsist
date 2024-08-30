package com.lemonappdev.konsist.core.container

import com.lemonappdev.konsist.TestSnippetProvider
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.provider.Arguments.arguments

class KoScopeForKoInterfaceAndObjectDeclarationTest {
    @Test
    fun `scope-contains-no-interface-and-object`() {
        // given
        val sut = getSnippetFile("scope-contains-no-interface-and-object")

        // then
        sut
            .interfacesAndObjects()
            .shouldBeEqualTo(emptyList())
    }

    @Test
    fun `scope-contains-nested-interfaces-and-objects includeNested true`() {
        // given
        val sut = getSnippetFile("scope-contains-nested-interfaces-and-objects")

        // then
        val expected =
            listOf(
                "SampleInterfaceNestedInsideObject",
                "SampleObject",
                "SampleObjectNestedInsideObject",
            )

        sut
            .interfacesAndObjects(includeNested = true)
            .map { it.name }
            .shouldBeEqualTo(expected)
    }

    @Test
    fun `scope-contains-nested-interfaces-and-objects includeNested false`() {
        // given
        val sut = getSnippetFile("scope-contains-nested-interfaces-and-objects")

        // then
        val expected = listOf("SampleObject")

        sut
            .interfacesAndObjects(includeNested = false)
            .map { it.name }
            .shouldBeEqualTo(expected)
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("core/container/snippet/forkointerfaceandobjectdeclaration/", fileName)

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
