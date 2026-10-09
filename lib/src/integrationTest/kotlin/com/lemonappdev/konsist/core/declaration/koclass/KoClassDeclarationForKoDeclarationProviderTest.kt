package com.lemonappdev.konsist.core.declaration.koclass

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import com.lemonappdev.konsist.api.provider.KoNameProvider
import com.lemonappdev.konsist.api.provider.modifier.KoVisibilityModifierProvider
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoClassDeclarationForKoDeclarationProviderTest {
    @Test
    fun `class-has-no-declarations`() {
        // given
        val sut =
            getSnippetFile("class-has-no-declarations")
                .classes()
                .first()

        // then
        assertSoftly(sut) {
            declarations() shouldBeEqualTo emptyList()
            hasDeclarations() shouldBeEqualTo false
            hasDeclaration { (it as KoNameProvider).name == "fixtureProperty" } shouldBeEqualTo false
            hasAllDeclarations { (it as KoNameProvider).hasNameStartingWith("fixture") } shouldBeEqualTo true
        }
    }

    @Test
    fun `class-has-two-declarations`() {
        // given
        val sut =
            getSnippetFile("class-has-two-declarations")
                .classes()
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
    fun `class-contains-nested-and-local-declarations includeNested true includeLocal true`() {
        // given
        val sut =
            getSnippetFile("class-contains-nested-and-local-declarations")
                .classes()
                .first()

        // then
        val expected =
            listOf(
                "fixtureFunction",
                "fixtureLocalProperty",
                "FixtureLocalClass",
                "fixtureLocalFunction",
                "FixtureClass",
                "FixtureClassNestedInsideClass",
                "FixtureObjectNestedInsideClass",
                "FixtureInterfaceNestedInsideClass",
                "FixtureObject",
                "FixtureClassNestedInsideObject",
                "FixtureObjectNestedInsideObject",
                "FixtureInterfaceNestedInsideObject",
                "FixtureInterface",
                "FixtureClassNestedInsideInterface",
                "FixtureObjectNestedInsideInterface",
                "FixtureInterfaceNestedInsideInterface",
            )

        sut
            .declarations(includeNested = true, includeLocal = true)
            .filterIsInstance<KoNameProvider>()
            .map { it.name }
            .shouldBeEqualTo(expected)
    }

    @Test
    fun `class-contains-nested-and-local-declarations includeNested true includeLocal false`() {
        // given
        val sut =
            getSnippetFile("class-contains-nested-and-local-declarations")
                .classes()
                .first()

        // then
        val expected =
            listOf(
                "fixtureFunction",
                "FixtureClass",
                "FixtureClassNestedInsideClass",
                "FixtureObjectNestedInsideClass",
                "FixtureInterfaceNestedInsideClass",
                "FixtureObject",
                "FixtureClassNestedInsideObject",
                "FixtureObjectNestedInsideObject",
                "FixtureInterfaceNestedInsideObject",
                "FixtureInterface",
                "FixtureClassNestedInsideInterface",
                "FixtureObjectNestedInsideInterface",
                "FixtureInterfaceNestedInsideInterface",
            )

        sut
            .declarations(includeNested = true, includeLocal = false)
            .filterIsInstance<KoNameProvider>()
            .map { it.name }
            .shouldBeEqualTo(expected)
    }

    @Test
    fun `class-contains-nested-and-local-declarations includeNested false includeLocal true`() {
        // given
        val sut =
            getSnippetFile("class-contains-nested-and-local-declarations")
                .classes()
                .first()

        // then
        val expected =
            listOf(
                "fixtureFunction",
                "fixtureLocalProperty",
                "FixtureLocalClass",
                "fixtureLocalFunction",
                "FixtureClass",
                "FixtureObject",
                "FixtureInterface",
            )

        sut
            .declarations(includeNested = false, includeLocal = true)
            .filterIsInstance<KoNameProvider>()
            .map { it.name }
            .shouldBeEqualTo(expected)
    }

    @Test
    fun `class-contains-nested-and-local-declarations includeNested false includeLocal false`() {
        // given
        val sut =
            getSnippetFile("class-contains-nested-and-local-declarations")
                .classes()
                .first()

        // then
        val expected =
            listOf(
                "fixtureFunction",
                "FixtureClass",
                "FixtureObject",
                "FixtureInterface",
            )

        sut
            .declarations(includeNested = false, includeLocal = false)
            .filterIsInstance<KoNameProvider>()
            .map { it.name }
            .shouldBeEqualTo(expected)
    }

    @Test
    fun `count-declarations`() {
        // given
        val sut =
            getSnippetFile("count-declarations")
                .classes()
                .first()

        // then
        assertSoftly(sut) {
            numDeclarations(includeNested = false, includeLocal = false) shouldBeEqualTo 3
            numDeclarations(includeLocal = false) shouldBeEqualTo 4
            numDeclarations(includeNested = false) shouldBeEqualTo 4
            numDeclarations() shouldBeEqualTo 5
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
                .classes()
                .first()

        // then
        assertSoftly(sut) {
            numDeclarations() shouldBeEqualTo 6
            numPublicDeclarations() shouldBeEqualTo 1
            numPublicOrDefaultDeclarations() shouldBeEqualTo 3
            numPrivateDeclarations() shouldBeEqualTo 1
            numProtectedDeclarations() shouldBeEqualTo 1
            numInternalDeclarations() shouldBeEqualTo 1
        }
    }

    private fun getSnippetFile(fileName: String) = getSnippetKoScope("core/declaration/koclass/snippet/forkodeclarationprovider/", fileName)
}
