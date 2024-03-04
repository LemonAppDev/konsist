package com.lemonappdev.konsist.core.declaration.kointerface

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

class KoInterfaceDeclarationForKoFileDeclarationProviderTest {
    @Test
    fun `interface-has-no-declarations`() {
        // given
        val sut = getSnippetFile("interface-has-no-declarations")
            .interfaces()
            .first()

        // then
        assertSoftly(sut) {
            declarations() shouldBeEqualTo emptyList()
            hasDeclarations() shouldBeEqualTo false
            hasDeclaration { (it as KoNameProvider).name == "sampleProperty" } shouldBeEqualTo false
            hasAllDeclarations { (it as KoNameProvider).hasNameStartingWith("sample") } shouldBeEqualTo true
        }
    }

    @Test
    fun `interface-has-two-declarations`() {
        // given
        val sut = getSnippetFile("interface-has-two-declarations")
            .interfaces()
            .first()

        // then
        assertSoftly(sut) {
            hasDeclarations() shouldBeEqualTo true
            hasDeclaration { (it as KoNameProvider).name == "sampleProperty" } shouldBeEqualTo true
            hasDeclaration { (it as KoNameProvider).hasNameEndingWith("Property") } shouldBeEqualTo true
            hasAllDeclarations { (it as KoNameProvider).hasNameStartingWith("sample") } shouldBeEqualTo true
            hasAllDeclarations { (it as KoNameProvider).hasNameEndingWith("Class1") } shouldBeEqualTo false
        }
    }

    @Test
    fun `interface-contains-nested-and-local-declarations includeNested true includeLocal true`() {
        // given
        val sut = getSnippetFile("interface-contains-nested-and-local-declarations")
            .interfaces()
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
    fun `interface-contains-nested-and-local-declarations includeNested true includeLocal false`() {
        // given
        val sut = getSnippetFile("interface-contains-nested-and-local-declarations")
            .interfaces()
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
    fun `interface-contains-nested-and-local-declarations includeNested false includeLocal true`() {
        // given
        val sut = getSnippetFile("interface-contains-nested-and-local-declarations")
            .interfaces()
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
    fun `interface-contains-nested-and-local-declarations includeNested false includeLocal false`() {
        // given
        val sut = getSnippetFile("interface-contains-nested-and-local-declarations")
            .interfaces()
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
            .interfaces()
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
        val sut = getSnippetFile("count-declarations-with-visibility-modifiers")
            .interfaces()
            .first()

        // then
        assertSoftly(sut) {
            numDeclarations() shouldBeEqualTo 3
            numPublicDeclarations() shouldBeEqualTo 1
            numPublicOrDefaultDeclarations() shouldBeEqualTo 2
            numPrivateDeclarations() shouldBeEqualTo 1
            numProtectedDeclarations() shouldBeEqualTo 0
            numInternalDeclarations() shouldBeEqualTo 0
        }
    }

    @Test
    fun `contains-declarations-with-specified-conditions`() {
        // given
        val sut = getSnippetFile("contains-declarations-with-specified-conditions")
            .interfaces()
            .first()

        // then
        assertSoftly(sut) {
            containsDeclaration {
                (it as? KoVisibilityModifierProvider)?.hasPrivateModifier ?: false
            } shouldBeEqualTo true
            containsDeclaration {
                (it as? KoModifierProvider)?.hasModifiers(PRIVATE, OPEN) ?: false
            } shouldBeEqualTo true
            containsDeclaration {
                (it as? KoVisibilityModifierProvider)?.hasInternalModifier ?: false
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

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/kointerface/snippet/forkodeclarationprovider/", fileName)
}
