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
                "FixtureInterfaceNestedInsideObject",
                "FixtureObject",
                "FixtureObjectNestedInsideObject",
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
        val expected = listOf("FixtureObject")

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
                    listOf("FixtureClass"),
                ),
                arguments(
                    true,
                    false,
                    listOf(
                        "FixtureClass",
                        "FixtureNestedClass1",
                        "FixtureNestedClass2",
                    ),
                ),
                arguments(
                    false,
                    true,
                    listOf("FixtureClass"),
                ),
                arguments(
                    true,
                    true,
                    listOf(
                        "FixtureClass",
                        "FixtureLocalClass",
                        "FixtureNestedClass1",
                        "FixtureNestedClass2",
                    ),
                ),
            )
    }
}
