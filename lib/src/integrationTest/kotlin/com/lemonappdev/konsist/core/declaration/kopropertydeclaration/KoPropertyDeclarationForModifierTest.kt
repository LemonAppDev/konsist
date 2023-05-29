package com.lemonappdev.konsist.core.declaration.kopropertydeclaration

import com.lemonappdev.konsist.TestSnippetProvider
import com.lemonappdev.konsist.core.ext.toNormalizedPath
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoPropertyDeclarationForModifierTest {
    @Test
    fun `property-has-lateinit-modifier`() {
        // given
        val sut = getSnippetFile("property-has-lateinit-modifier")
            .properties()
            .first()

        // then
        sut.hasLateinitModifier() shouldBeEqualTo true
    }

    @Test
    fun `property-has-override-modifier`() {
        // given
        val sut = getSnippetFile("property-has-override-modifier")
            .properties(includeNested = true)
            .first()

        // then
        sut.hasOverrideModifier() shouldBeEqualTo true
    }

    @Test
    fun `property-has-abstract-modifier`() {
        // given
        val sut = getSnippetFile("property-has-abstract-modifier")
            .properties(includeNested = true)
            .first()

        // then
        sut.hasAbstractModifier() shouldBeEqualTo true
    }

    @Test
    fun `property-has-open-modifier`() {
        // given
        val sut = getSnippetFile("property-has-open-modifier")
            .properties(includeNested = true)
            .first()

        // then
        sut.hasOpenModifier() shouldBeEqualTo true
    }

    @Test
    fun `property-has-final-modifier`() {
        // given
        val sut = getSnippetFile("property-has-final-modifier")
            .properties(includeNested = true)
            .first()

        // then
        sut.hasFinalModifier() shouldBeEqualTo true
    }

    @Test
    fun `property-is-const`() {
        // given
        val sut = getSnippetFile("property-is-const")
            .properties(includeNested = true)
            .first()

        // then
        sut.hasConstModifier() shouldBeEqualTo true
    }

    @Test
    fun `property-has-actual-modifier`() {
        // given
        val sut = getSnippetFile("property-has-actual-modifier")
            .properties(includeNested = true)
            .first()

        // then
        sut.hasActualModifier() shouldBeEqualTo true
    }

    @Test
    fun `property-has-expect-modifier`() {
        // given
        val sut = getSnippetFile("property-has-expect-modifier")
            .properties(includeNested = true)
            .first()

        // then
        sut.hasExpectModifier() shouldBeEqualTo true
    }

    @Test
    fun `property-has-no-modifiers`() {
        // given
        val sut = getSnippetFile("property-has-no-modifiers")
            .properties()
            .first()

        // then
        assertSoftly(sut) {
            hasLateinitModifier() shouldBeEqualTo false
            hasOverrideModifier() shouldBeEqualTo false
            hasAbstractModifier() shouldBeEqualTo false
            hasOpenModifier() shouldBeEqualTo false
            hasFinalModifier() shouldBeEqualTo false
            hasConstModifier() shouldBeEqualTo false
            hasActualModifier() shouldBeEqualTo false
            hasExpectModifier() shouldBeEqualTo false
        }
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("core/declaration/kopropertydeclaration/snippet/formodifier/".toNormalizedPath(), fileName)
}
