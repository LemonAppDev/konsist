package com.lemonappdev.konsist.core.declaration.kofile

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import com.lemonappdev.konsist.api.provider.KoNameProvider
import com.lemonappdev.konsist.api.provider.modifier.KoVisibilityModifierProvider
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.MethodSource

class KoFileDeclarationForKoDeclarationProviderTest {
    @Test
    fun `file-has-two-declarations`() {
        // given
        val sut =
            getSnippetFile("file-has-two-declarations")
                .files
                .first()

        // then
        assertSoftly(sut) {
            hasDeclarations() shouldBeEqualTo true
            hasDeclaration { (it as KoNameProvider).name == "fixtureProperty" } shouldBeEqualTo true
            hasDeclaration { (it as KoNameProvider).hasNameEndingWith("Property") } shouldBeEqualTo true
            hasAllDeclarations { (it as KoNameProvider).hasNameStartingWith("fixture") } shouldBeEqualTo true
            hasAllDeclarations { (it as KoNameProvider).hasNameEndingWith("Class1") } shouldBeEqualTo false
        }
    }

    @Test
    fun `count-declarations`() {
        // given
        val sut =
            getSnippetFile("count-declarations")
                .files
                .first()

        // then
        assertSoftly(sut) {
            numDeclarations(includeNested = false, includeLocal = false) shouldBeEqualTo 2
            numDeclarations(includeLocal = false) shouldBeEqualTo 3
            numDeclarations(includeNested = false) shouldBeEqualTo 3
            numDeclarations() shouldBeEqualTo 4
            countDeclarations(includeNested = false, includeLocal = false) {
                (it as? KoVisibilityModifierProvider)?.hasPrivateModifier ?: false
            } shouldBeEqualTo 2
            countDeclarations {
                (it as? KoVisibilityModifierProvider)?.hasPrivateModifier ?: false
            } shouldBeEqualTo 3
            countDeclarations {
                (it as? KoVisibilityModifierProvider)?.hasInternalModifier ?: false
            } shouldBeEqualTo 0
        }
    }

    @Test
    fun `count-declarations-with-visibility-modifiers`() {
        // given
        val sut =
            getSnippetFile("count-declarations-with-visibility-modifiers")
                .files
                .first()

        // then
        assertSoftly(sut) {
            numDeclarations() shouldBeEqualTo 4
            numPublicDeclarations() shouldBeEqualTo 1
            numPublicOrDefaultDeclarations() shouldBeEqualTo 2
            numPrivateDeclarations() shouldBeEqualTo 1
            numProtectedDeclarations() shouldBeEqualTo 0
            numInternalDeclarations() shouldBeEqualTo 1
        }
    }

    @Test
    fun `file-contains-all-type-of-declarations`() {
        // given
        val sut =
            getSnippetFile("file-contains-all-type-of-declarations")
                .files
                .first()

        // then
        sut
            .declarations()
            .filterIsInstance<KoNameProvider>()
            .map { it.name }
            .shouldBeEqualTo(
                listOf(
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
    fun `file-contains-all-type-of-declarations-with-nested-and-local-declarations`(
        includeNested: Boolean,
        includeLocal: Boolean,
        expected: List<String>,
    ) {
        // given
        val sut =
            getSnippetFile("file-contains-all-type-of-declarations-with-nested-and-local-declarations")
                .files
                .first()

        // then
        sut
            .declarations(includeNested = includeNested, includeLocal = includeLocal)
            .filterIsInstance<KoNameProvider>()
            .map { it.name }
            .shouldBeEqualTo(expected)
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope(
            "core/declaration/kofile/snippet/forkodeclarationprovider/",
            fileName,
        )

    companion object {
        @Suppress("unused", "detekt.LongMethod")
        @JvmStatic
        fun provideValues() =
            listOf(
                arguments(
                    false,
                    false,
                    listOf(
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
