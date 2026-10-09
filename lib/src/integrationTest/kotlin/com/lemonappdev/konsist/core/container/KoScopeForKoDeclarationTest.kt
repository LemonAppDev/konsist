package com.lemonappdev.konsist.core.container

import com.lemonappdev.konsist.TestSnippetProvider
import com.lemonappdev.konsist.api.provider.KoNameProvider
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.MethodSource

class KoScopeForKoDeclarationTest {
    @Test
    fun `scope-contains-all-type-of-declarations`() {
        // given
        val sut = getSnippetFile("scope-contains-all-type-of-declarations")

        // then
        sut
            .declarations()
            .filterIsInstance<KoNameProvider>()
            .map { it.name }
            .shouldBeEqualTo(
                listOf(
                    "scope-contains-all-type-of-declarations",
                    "FixtureAnnotation1",
                    "FixtureAnnotation2",
                    "com.fixturepackage",
                    "com.lemonappdev.konsist.testdata.FixtureAnnotation1",
                    "com.lemonappdev.konsist.testdata.FixtureAnnotation2",
                    "fixtureProperty",
                    "fixtureFunction",
                    "FixtureClass",
                    "FixtureInterface",
                    "FixtureObject",
                    "FixtureTypeAlias",
                ),
            )
    }

    @ParameterizedTest
    @MethodSource("provideValues")
    fun `scope-contains-all-type-of-declarations-with-nested-and-local-declarations`(
        includeNested: Boolean,
        includeLocal: Boolean,
        expected: List<String>,
    ) {
        // given
        val sut = getSnippetFile("scope-contains-all-type-of-declarations-with-nested-and-local-declarations")

        // then
        sut
            .declarations(includeNested = includeNested, includeLocal = includeLocal)
            .filterIsInstance<KoNameProvider>()
            .map { it.name }
            .shouldBeEqualTo(expected)
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("core/container/snippet/forkodeclaration/", fileName)

    companion object {
        @Suppress("unused", "detekt.LongMethod")
        @JvmStatic
        fun provideValues() =
            listOf(
                arguments(
                    false,
                    false,
                    listOf(
                        "scope-contains-all-type-of-declarations-with-nested-and-local-declarations",
                        "fixtureProperty",
                        "fixtureFunction",
                        "FixtureClass",
                        "FixtureInterface",
                        "FixtureObject",
                        "FixtureTypeAlias",
                    ),
                ),
                arguments(
                    true,
                    false,
                    listOf(
                        "scope-contains-all-type-of-declarations-with-nested-and-local-declarations",
                        "fixtureProperty",
                        "fixtureFunction",
                        "FixtureClass",
                        "fixtureNestedPropertyInsideClass",
                        "fixtureNestedFunctionInsideClass",
                        "fixtureNestedClassInsideClass",
                        "FixtureInterface",
                        "fixtureNestedPropertyInsideInterface",
                        "fixtureNestedFunctionInsideInterface",
                        "fixtureNestedClassInsideInterface",
                        "FixtureObject",
                        "fixtureNestedPropertyInsideObject",
                        "fixtureNestedFunctionInsideObject",
                        "fixtureNestedClassInsideObject",
                        "FixtureTypeAlias",
                    ),
                ),
                arguments(
                    false,
                    true,
                    listOf(
                        "scope-contains-all-type-of-declarations-with-nested-and-local-declarations",
                        "fixtureProperty",
                        "fixtureFunction",
                        "fixtureLocalProperty1",
                        "fixtureLocalFunction",
                        "fixtureLocalClass2",
                        "fixtureLocalProperty2",
                        "fixtureLocalClass1",
                        "FixtureClass",
                        "FixtureInterface",
                        "FixtureObject",
                        "FixtureTypeAlias",
                    ),
                ),
                arguments(
                    true,
                    true,
                    listOf(
                        "scope-contains-all-type-of-declarations-with-nested-and-local-declarations",
                        "fixtureProperty",
                        "fixtureFunction",
                        "fixtureLocalProperty1",
                        "fixtureLocalFunction",
                        "fixtureLocalClass2",
                        "fixtureLocalProperty2",
                        "fixtureLocalClass1",
                        "fixtureNestedFunction",
                        "FixtureClass",
                        "fixtureNestedPropertyInsideClass",
                        "fixtureNestedFunctionInsideClass",
                        "fixtureLocalProperty3",
                        "fixtureLocalClass3",
                        "fixtureNestedClassInsideClass",
                        "FixtureInterface",
                        "fixtureNestedPropertyInsideInterface",
                        "fixtureNestedFunctionInsideInterface",
                        "fixtureNestedClassInsideInterface",
                        "FixtureObject",
                        "fixtureNestedPropertyInsideObject",
                        "fixtureNestedFunctionInsideObject",
                        "fixtureNestedClassInsideObject",
                        "FixtureTypeAlias",
                    ),
                ),
            )
    }
}
