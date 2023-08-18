package com.lemonappdev.konsist.core.declaration.kofile

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import com.lemonappdev.konsist.api.KoModifier.INTERNAL
import com.lemonappdev.konsist.api.KoModifier.OPEN
import com.lemonappdev.konsist.api.KoModifier.PRIVATE
import com.lemonappdev.konsist.api.provider.KoNameProvider
import com.lemonappdev.konsist.api.provider.modifier.KoModifierProvider
import com.lemonappdev.konsist.api.provider.modifier.KoVisibilityModifierProvider
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.MethodSource

class KoFileDeclarationForKoDeclarationProviderTest {
    @Test
    fun `count-declarations`() {
        // given
        val sut = getSnippetFile("count-declarations")
            .files
            .first()

        // then
        assertSoftly(sut) {
            countDeclarations() shouldBeEqualTo 2
            countDeclarations(includeNested = true) shouldBeEqualTo 3
            countDeclarations(includeLocal = true) shouldBeEqualTo 3
            countDeclarations(includeNested = true, includeLocal = true) shouldBeEqualTo 4
            countDeclarations {
                (it as? KoVisibilityModifierProvider)?.hasPrivateModifier ?: false
            } shouldBeEqualTo 2
            countDeclarations(includeNested = true, includeLocal = true) {
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
        val sut = getSnippetFile("count-declarations-with-visibility-modifiers")
            .files
            .first()

        // then
        assertSoftly(sut) {
            countDeclarations() shouldBeEqualTo 4
            countPublic() shouldBeEqualTo 1
            countPublicOrDefault() shouldBeEqualTo 2
            countPrivate() shouldBeEqualTo 1
            countProtected() shouldBeEqualTo 0
            countInternal() shouldBeEqualTo 1
        }
    }

    @Test
    fun `contains-declarations-with-specified-conditions`() {
        // given
        val sut = getSnippetFile("contains-declarations-with-specified-conditions")
            .files
            .first()

        // then
        assertSoftly(sut) {
            containsDeclaration {
                (it as? KoVisibilityModifierProvider)?.hasInternalModifier ?: false
            } shouldBeEqualTo true
            containsDeclaration {
                (it as? KoModifierProvider)?.hasModifiers(INTERNAL, OPEN) ?: false
            } shouldBeEqualTo true
            containsDeclaration {
                (it as? KoVisibilityModifierProvider)?.hasPrivateModifier ?: false
            } shouldBeEqualTo false
            containsDeclaration {
                (it as? KoModifierProvider)?.hasModifiers(INTERNAL, PRIVATE) ?: false
            } shouldBeEqualTo false
            containsDeclaration(
                includeNested = false,
                includeLocal = true,
            ) { (it as? KoNameProvider)?.name == "sampleLocalProperty" } shouldBeEqualTo true
            containsDeclaration(
                includeNested = false,
                includeLocal = false,
            ) { (it as? KoNameProvider)?.name == "sampleLocalProperty" } shouldBeEqualTo false
            containsDeclaration(
                includeNested = false,
                includeLocal = true,
            ) { (it as? KoNameProvider)?.name == "sampleOtherProperty" } shouldBeEqualTo false
            containsDeclaration(
                includeNested = true,
                includeLocal = false,
            ) { (it as? KoNameProvider)?.name == "sampleNestedProperty" } shouldBeEqualTo true
            containsDeclaration(
                includeNested = false,
                includeLocal = false,
            ) { (it as? KoNameProvider)?.name == "sampleNestedProperty" } shouldBeEqualTo false
            containsDeclaration(
                includeNested = true,
                includeLocal = false,
            ) { (it as? KoNameProvider)?.name == "sampleOtherProperty" } shouldBeEqualTo false
        }
    }

    @Test
    fun `file-contains-all-type-of-declarations`() {
        // given
        val sut = getSnippetFile("file-contains-all-type-of-declarations")
            .files
            .first()

        // then
        sut
            .declarations()
            .filterIsInstance<KoNameProvider>()
            .map { it.name }
            .shouldBeEqualTo(
                listOf(
                    "SampleAnnotation1",
                    "SampleAnnotation2",
                    "samplepackage",
                    "com.lemonappdev.konsist.testdata.SampleAnnotation1",
                    "com.lemonappdev.konsist.testdata.SampleAnnotation2",
                    "sampleProperty",
                    "sampleFunction",
                    "SampleClass",
                    "SampleInterface",
                    "SampleObject",
                    "SampleTypeAlias",
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
        val sut = getSnippetFile("file-contains-all-type-of-declarations-with-nested-and-local-declarations")
            .files
            .first()

        // then
        sut
            .declarations(includeNested = includeNested, includeLocal = includeLocal)
            .filterIsInstance<KoNameProvider>()
            .map { it.name }
            .shouldBeEqualTo(expected)
    }

    private fun getSnippetFile(fileName: String) = getSnippetKoScope(
        "core/declaration/kofile/snippet/forkodeclarationprovider/",
        fileName,
    )

    companion object {
        @Suppress("unused", "detekt.LongMethod")
        @JvmStatic
        fun provideValues() = listOf(
            arguments(
                false,
                false,
                listOf(
                    "sampleProperty",
                    "sampleFunction",
                    "SampleClass",
                    "SampleInterface",
                    "SampleObject",
                    "SampleTypeAlias",
                ),
            ),
            arguments(
                true,
                false,
                listOf(
                    "sampleProperty",
                    "sampleFunction",
                    "SampleClass",
                    "sampleNestedPropertyInsideClass",
                    "sampleNestedFunctionInsideClass",
                    "sampleNestedClassInsideClass",
                    "SampleInterface",
                    "sampleNestedPropertyInsideInterface",
                    "sampleNestedFunctionInsideInterface",
                    "sampleNestedClassInsideInterface",
                    "SampleObject",
                    "sampleNestedPropertyInsideObject",
                    "sampleNestedFunctionInsideObject",
                    "sampleNestedClassInsideObject",
                    "SampleTypeAlias",
                ),
            ),
            arguments(
                false,
                true,
                listOf(
                    "sampleProperty",
                    "sampleFunction",
                    "sampleLocalProperty1",
                    "sampleLocalFunction",
                    "sampleLocalClass2",
                    "sampleLocalProperty2",
                    "sampleLocalClass1",
                    "SampleClass",
                    "SampleInterface",
                    "SampleObject",
                    "SampleTypeAlias",
                ),
            ),
            arguments(
                true,
                true,
                listOf(
                    "sampleProperty",
                    "sampleFunction",
                    "sampleLocalProperty1",
                    "sampleLocalFunction",
                    "sampleLocalClass2",
                    "sampleLocalProperty2",
                    "sampleLocalClass1",
                    "sampleNestedFunction",
                    "SampleClass",
                    "sampleNestedPropertyInsideClass",
                    "sampleNestedFunctionInsideClass",
                    "sampleLocalProperty3",
                    "sampleLocalClass3",
                    "sampleNestedClassInsideClass",
                    "SampleInterface",
                    "sampleNestedPropertyInsideInterface",
                    "sampleNestedFunctionInsideInterface",
                    "sampleNestedClassInsideInterface",
                    "SampleObject",
                    "sampleNestedPropertyInsideObject",
                    "sampleNestedFunctionInsideObject",
                    "sampleNestedClassInsideObject",
                    "SampleTypeAlias",
                ),
            ),
        )
    }
}
