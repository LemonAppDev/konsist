package com.lemon.konsist.core.declaration.koclass

import com.lemon.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldHaveSize
import org.junit.jupiter.api.Test

class KoClassTest {
    @Test
    fun `abstract-class`() {
        // given
        val sut = getSut("abstract-class")
            .classes()
            .first()

        // then
        sut.isAbstract shouldBeEqualTo true
    }

    @Test
    fun `annotation-class`() {
        // given
        val sut = getSut("annotation-class")
            .classes()
            .first()

        // then
        sut.isAnnotation shouldBeEqualTo true
    }

    @Test
    fun `class`() {
        // given
        val sut = getSut("class")
            .classes()
            .first()

        // then
        sut.name shouldBeEqualTo "SampleClass"
    }

    @Test
    fun `data-class`() {
        // given
        val sut = getSut("data-class")
            .classes()
            .first()

        // then
        sut.isData shouldBeEqualTo true
    }

    @Test
    fun `enum-class`() {
        // given
        val sut = getSut("enum-class")
            .classes()
            .first()

        // then
        sut.isEnum shouldBeEqualTo true
    }

    @Test
    fun `sealed-class`() {
        // given
        val sut = getSut("sealed-class")
            .classes()
            .first()

        // then
        sut.isSealed shouldBeEqualTo true
    }

    @Test
    fun `value-class`() {
        // given
        val sut = getSut("value-class")
            .classes()
            .first()

        // then
        sut.isValue shouldBeEqualTo true
    }

    @Test
    fun `open-class`() {
        // given
        val sut = getSut("open-class")
            .classes()
            .first()

        // then
        sut.isOpen shouldBeEqualTo true
    }

    @Test
    fun `final-class`() {
        // given
        val sut = getSut("final-class")
            .classes()
            .first()

        // then
        sut.isFinal shouldBeEqualTo true
    }

    @Test
    fun `nested-inner-class`() {
        // given
        val sut = getSut("nested-inner-class")
            .classes(includeNested = true)
            .first { it.name == "InnerClass" }

        // then
        sut.isInner shouldBeEqualTo true
    }

    @Test
    fun `class-has-primary-constructor`() {
        // given
        val sut = getSut("class-has-primary-constructor")
            .classes()
            .first()

        // then
        sut.run {
            primaryConstructor?.name shouldBeEqualTo "SampleClass"
            hasExplicitPrimaryConstructor shouldBeEqualTo true
        }
    }

    @Test
    fun `class-has-no-primary-constructor`() {
        // given
        val sut = getSut("class-has-no-primary-constructor")
            .classes()
            .first()

        // then
        sut.run {
            primaryConstructor?.name shouldBeEqualTo null
            hasExplicitPrimaryConstructor shouldBeEqualTo false
        }
    }

    @Test
    fun `class-has-secondary-constructor`() {
        // given
        val sut = getSut("class-has-secondary-constructor")
            .classes()
            .first()

        // then
        sut.run {
            secondaryConstructors.first().name shouldBeEqualTo "SampleClass"
            hasSecondaryConstructors shouldBeEqualTo true
        }
    }

    @Test
    fun `class-has-no-secondary-constructor`() {
        // given
        val sut = getSut("class-has-no-secondary-constructor")
            .classes()
            .first()

        // then
        sut.run {
            secondaryConstructors.isEmpty()
            hasSecondaryConstructors shouldBeEqualTo false
        }
    }

    @Test
    fun `class-has-primary-and-secondary-constructor`() {
        // given
        val sut = getSut("class-has-primary-and-secondary-constructor")
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
        val sut = getSut("class-has-class-and-interface")
            .classes()
            .first()

        // then
        sut.run {
            parents shouldBeEqualTo listOf("SampleParentClass", "SampleParentInterface")
            hasParent() shouldBeEqualTo true
            parentClass shouldBeEqualTo "SampleParentClass"
            hasParentClass() shouldBeEqualTo true
            hasParentClass("SampleParentClass") shouldBeEqualTo true
            hasParentClass("OtherClass") shouldBeEqualTo false
            parentInterfaces shouldBeEqualTo listOf("SampleParentInterface")
            hasParentInterface() shouldBeEqualTo true
            hasParentInterface("SampleParentInterface") shouldBeEqualTo true
            hasParentInterface("OtherInterface") shouldBeEqualTo false
        }
    }

    @Test
    fun `class-has-no-interface-and-class`() {
        // given
        val sut = getSut("class-has-no-interface-and-class")
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

    private fun getSut(fileName: String) = getSnippetKoScope("core/declaration/koclass/snippet/", fileName)
}
