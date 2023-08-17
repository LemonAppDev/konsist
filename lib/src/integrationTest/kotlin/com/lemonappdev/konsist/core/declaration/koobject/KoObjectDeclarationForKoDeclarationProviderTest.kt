package com.lemonappdev.konsist.core.declaration.koobject

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import com.lemonappdev.konsist.api.KoModifier
import com.lemonappdev.konsist.api.provider.KoNameProvider
import com.lemonappdev.konsist.api.provider.modifier.KoModifierProvider
import com.lemonappdev.konsist.api.provider.modifier.KoVisibilityModifierProvider
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoObjectDeclarationForKoDeclarationProviderTest {
    @Test
    fun `object-contains-no-declarations`() {
        // given
        val sut = getSnippetFile("object-contains-no-declarations")
            .objects()
            .first()

        // then
        sut.declarations(includeNested = true, includeLocal = true) shouldBeEqualTo emptyList()
    }

    @Test
    fun `object-contains-nested-and-local-declarations includeNested true includeLocal true`() {
        // given
        val sut = getSnippetFile("object-contains-nested-and-local-declarations")
            .objects()
            .first()

        // then
        val expected = listOf(
            "sampleFunction",
            "sampleLocalProperty",
            "SampleLocalClass",
            "sampleLocalFunction",
            "SampleClass",
            "SampleClassNestedInsideClass",
            "SampleObjectNestedInsideClass",
            "SampleInterfaceNestedInsideClass",
            "SampleObject",
            "SampleClassNestedInsideObject",
            "SampleObjectNestedInsideObject",
            "SampleInterfaceNestedInsideObject",
            "SampleInterface",
            "SampleClassNestedInsideInterface",
            "SampleObjectNestedInsideInterface",
            "SampleInterfaceNestedInsideInterface",
        )

        sut.declarations(includeNested = true, includeLocal = true)
            .filterIsInstance<KoNameProvider>()
            .map { it.name }
            .shouldBeEqualTo(expected)
    }

    @Test
    fun `object-contains-nested-and-local-declarations includeNested true includeLocal false`() {
        // given
        val sut = getSnippetFile("object-contains-nested-and-local-declarations")
            .objects()
            .first()

        // then
        val expected = listOf(
            "sampleFunction",
            "SampleClass",
            "SampleClassNestedInsideClass",
            "SampleObjectNestedInsideClass",
            "SampleInterfaceNestedInsideClass",
            "SampleObject",
            "SampleClassNestedInsideObject",
            "SampleObjectNestedInsideObject",
            "SampleInterfaceNestedInsideObject",
            "SampleInterface",
            "SampleClassNestedInsideInterface",
            "SampleObjectNestedInsideInterface",
            "SampleInterfaceNestedInsideInterface",
        )

        sut.declarations(includeNested = true, includeLocal = false)
            .filterIsInstance<KoNameProvider>()
            .map { it.name }
            .shouldBeEqualTo(expected)
    }

    @Test
    fun `object-contains-nested-and-local-declarations includeNested false includeLocal true`() {
        // given
        val sut = getSnippetFile("object-contains-nested-and-local-declarations")
            .objects()
            .first()

        // then
        val expected = listOf(
            "sampleFunction",
            "sampleLocalProperty",
            "SampleLocalClass",
            "sampleLocalFunction",
            "SampleClass",
            "SampleObject",
            "SampleInterface",
        )

        sut.declarations(includeNested = false, includeLocal = true)
            .filterIsInstance<KoNameProvider>()
            .map { it.name }
            .shouldBeEqualTo(expected)
    }

    @Test
    fun `object-contains-nested-and-local-declarations includeNested false includeLocal false`() {
        // given
        val sut = getSnippetFile("object-contains-nested-and-local-declarations")
            .objects()
            .first()

        // then
        val expected = listOf(
            "sampleFunction",
            "SampleClass",
            "SampleObject",
            "SampleInterface",
        )

        sut.declarations(includeNested = false, includeLocal = false)
            .filterIsInstance<KoNameProvider>()
            .map { it.name }
            .shouldBeEqualTo(expected)
    }

    @Test
    fun `count-declarations`() {
        // given
        val sut = getSnippetFile("count-declarations")
            .objects()
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
    fun `contains-declarations-with-specified-conditions`() {
        // given
        val sut = getSnippetFile("contains-declarations-with-specified-conditions")
            .objects()
            .first()

        // then
        assertSoftly(sut) {
            containsDeclaration {
                (it as? KoVisibilityModifierProvider)?.hasInternalModifier ?: false
            } shouldBeEqualTo true
            containsDeclaration {
                (it as? KoModifierProvider)?.hasModifiers(KoModifier.INTERNAL, KoModifier.OPEN) ?: false
            } shouldBeEqualTo true
            containsDeclaration {
                (it as? KoVisibilityModifierProvider)?.hasPrivateModifier ?: false
            } shouldBeEqualTo false
            containsDeclaration {
                (it as? KoModifierProvider)?.hasModifiers(KoModifier.INTERNAL, KoModifier.PRIVATE) ?: false
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

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/koobject/snippet/forkodeclarationprovider/", fileName)
}
