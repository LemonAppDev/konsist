package com.lemon.konsist.core.declaration.koclass

import com.lemon.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldHaveSize
import org.junit.jupiter.api.Test

class KoClassTest {
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
    fun `class`() {
        // given
        val sut = getSnippetFile("class")
            .classes()
            .first()

        // then
        sut.name shouldBeEqualTo "SampleClass"
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

    @Test
    fun `class-has-primary-constructor`() {
        // given
        val sut = getSnippetFile("class-has-primary-constructor")
            .classes()
            .first()

        // then
        sut.run {
            primaryConstructor?.name shouldBeEqualTo "SampleClass"
            hasExplicitPrimaryConstructor() shouldBeEqualTo true
        }
    }

    @Test
    fun `class-has-no-primary-constructor`() {
        // given
        val sut = getSnippetFile("class-has-no-primary-constructor")
            .classes()
            .first()

        // then
        sut.run {
            primaryConstructor?.name shouldBeEqualTo null
            hasExplicitPrimaryConstructor() shouldBeEqualTo false
        }
    }

    @Test
    fun `class-has-secondary-constructor`() {
        // given
        val sut = getSnippetFile("class-has-secondary-constructor")
            .classes()
            .first()

        // then
        sut.run {
            secondaryConstructors.first().name shouldBeEqualTo "SampleClass"
            hasSecondaryConstructors() shouldBeEqualTo true
        }
    }

    @Test
    fun `class-has-no-secondary-constructor`() {
        // given
        val sut = getSnippetFile("class-has-no-secondary-constructor")
            .classes()
            .first()

        // then
        sut.run {
            secondaryConstructors.isEmpty() shouldBeEqualTo true
            hasSecondaryConstructors() shouldBeEqualTo false
        }
    }

    @Test
    fun `class-has-primary-and-secondary-constructor`() {
        // given
        val sut = getSnippetFile("class-has-primary-and-secondary-constructor")
            .classes()
            .first()

        // then
        sut.run {
            primaryConstructor?.name shouldBeEqualTo "SampleClass"
            secondaryConstructors shouldHaveSize 1
            allConstructors shouldHaveSize 2
        }
    }

    @Test
    fun `class-has-class-and-interface`() {
        // given
        val sut = getSnippetFile("class-has-class-and-interface")
            .classes()
            .first()

        // then
        sut.run {
            parents.map { it.name } shouldBeEqualTo listOf("SampleParentClass", "SampleParentInterface")
            hasParent() shouldBeEqualTo true
            parentClass?.name shouldBeEqualTo "SampleParentClass"
            hasParentClass() shouldBeEqualTo true
            hasParentClass("SampleParentClass") shouldBeEqualTo true
            hasParentClass("OtherClass") shouldBeEqualTo false
            parentInterfaces.map { it.name } shouldBeEqualTo listOf("SampleParentInterface")
            hasParentInterface() shouldBeEqualTo true
            hasParentInterface("SampleParentInterface") shouldBeEqualTo true
            hasParentInterface("OtherInterface") shouldBeEqualTo false
        }
    }

    @Test
    fun `class-has-no-interface-and-class`() {
        // given
        val sut = getSnippetFile("class-has-no-interface-and-class")
            .classes()
            .first()

        // then
        sut.run {
            parents shouldBeEqualTo emptyList()
            hasParent() shouldBeEqualTo false
            parentClass shouldBeEqualTo null
            hasParentClass() shouldBeEqualTo false
            hasParentClass("SampleParentClass") shouldBeEqualTo false
            parentInterfaces shouldBeEqualTo emptyList()
            hasParentInterface() shouldBeEqualTo false
            hasParentInterface("SampleInterface") shouldBeEqualTo false
        }
    }

    private fun getSnippetFile(fileName: String) = getSnippetKoScope("core/declaration/koclass/snippet/", fileName)
}
