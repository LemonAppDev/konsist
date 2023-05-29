package com.lemonappdev.konsist.core.declaration.koclassdeclaration

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoClassDeclarationForModifierTest {
    @Test
    fun `abstract-class`() {
        // given
        val sut = getSnippetFile("abstract-class")
            .classes()
            .first()

        // then
        sut.hasAbstractModifier() shouldBeEqualTo true
    }

    @Test
    fun `annotation-class`() {
        // given
        val sut = getSnippetFile("annotation-class")
            .classes()
            .first()

        // then
        sut.hasAnnotationModifier() shouldBeEqualTo true
    }

    @Test
    fun `data-class`() {
        // given
        val sut = getSnippetFile("data-class")
            .classes()
            .first()

        // then
        sut.hasDataModifier() shouldBeEqualTo true
    }

    @Test
    fun `enum-class`() {
        // given
        val sut = getSnippetFile("enum-class")
            .classes()
            .first()

        // then
        sut.hasEnumModifier() shouldBeEqualTo true
    }

    @Test
    fun `sealed-class`() {
        // given
        val sut = getSnippetFile("sealed-class")
            .classes()
            .first()

        // then
        sut.hasSealedModifier() shouldBeEqualTo true
    }

    @Test
    fun `value-class`() {
        // given
        val sut = getSnippetFile("value-class")
            .classes()
            .first()

        // then
        sut.hasValueModifier() shouldBeEqualTo true
    }

    @Test
    fun `open-class`() {
        // given
        val sut = getSnippetFile("open-class")
            .classes()
            .first()

        // then
        sut.hasOpenModifier() shouldBeEqualTo true
    }

    @Test
    fun `final-class`() {
        // given
        val sut = getSnippetFile("final-class")
            .classes()
            .first()

        // then
        sut.hasFinalModifier() shouldBeEqualTo true
    }

    @Test
    fun `nested-inner-class`() {
        // given
        val sut = getSnippetFile("nested-inner-class")
            .classes(includeNested = true)
            .first { it.name == "InnerClass" }

        // then
        sut.hasInnerModifier() shouldBeEqualTo true
    }

    @Test
    fun `class-has-actual-modifier`() {
        // given
        val sut = getSnippetFile("class-has-actual-modifier")
            .classes(includeNested = true)
            .first()

        // then
        sut.hasActualModifier() shouldBeEqualTo true
    }

    @Test
    fun `class-has-expect-modifier`() {
        // given
        val sut = getSnippetFile("class-has-expect-modifier")
            .classes(includeNested = true)
            .first()

        // then
        sut.hasExpectModifier() shouldBeEqualTo true
    }

    private fun getSnippetFile(fileName: String) = getSnippetKoScope("core/declaration/koclassdeclaration/snippet/formodifier/", fileName)
}
