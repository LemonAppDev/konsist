package com.lemon.konsist.core.koclass

import com.lemon.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.shouldBe
import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldHaveSize
import org.junit.jupiter.api.Test

class KoClassTest {
    @Test
    fun `abstract-class`() {
        // given
        val sut = getSut("abstract-class")

        // then
        sut.classes().first().isAbstract shouldBe true
    }

    @Test
    fun `annotation-class`() {
        // given
        val sut = getSut("annotation-class")

        // then
        sut.classes().first().isAnnotation shouldBe true
    }

    @Test
    fun `class`() {
        // given
        val sut = getSut("class")

        // then
        sut.classes().first().name shouldBeEqualTo "SampleClass"
    }

    @Test
    fun `data-class`() {
        // given
        val sut = getSut("data-class")

        // then
        sut.classes().first().isData shouldBe true
    }

    @Test
    fun `enum-class`() {
        // given
        val sut = getSut("enum-class")

        // then
        sut.classes().first().isEnum shouldBe true
    }

    @Test
    fun `sealed-class`() {
        // given
        val sut = getSut("sealed-class")

        // then
        sut.classes().first().isSealed shouldBe true
    }

    @Test
    fun `value-class`() {
        // given
        val sut = getSut("value-class")

        // then
        sut.classes().first().isValue shouldBe true
    }

    @Test
    fun `nested-inner-class`() {
        // given
        val sut = getSut("nested-inner-class")

        // then
        sut.classes(includeNested = true).first { it.name == "InnerClass" }.isInner shouldBe true
    }

    @Test
    fun `class-with-primary-constructor`() {
        // given
        val sut = getSut("class-with-primary-constructor")

        // then
        sut.classes().first().apply {
            primaryConstructor?.name shouldBeEqualTo "SampleClass"
            hasExplicitPrimaryConstructor shouldBe true
        }
    }

    @Test
    fun `class-without-primary-constructor`() {
        // given
        val sut = getSut("class-without-primary-constructor")

        // then
        sut.classes().first().apply {
            primaryConstructor?.name shouldBeEqualTo null
            hasExplicitPrimaryConstructor shouldBeEqualTo false
        }
    }

    @Test
    fun `class-with-secondary-constructor`() {
        // given
        val sut = getSut("class-with-secondary-constructor")

        // then
        sut.classes().first().apply {
            secondaryConstructors.first().name shouldBeEqualTo "SampleClass"
            hasSecondaryConstructors shouldBe true
        }
    }

    @Test
    fun `class-without-secondary-constructor`() {
        // given
        val sut = getSut("class-without-secondary-constructor")

        // then
        sut.classes().first().apply {
            secondaryConstructors.isEmpty()
            hasSecondaryConstructors shouldBe false
        }
    }

    @Test
    fun `class-with-primary-and-secondary-constructor`() {
        // given
        val sut = getSut("class-with-primary-and-secondary-constructor")

        // then
        sut.classes().first().apply {
            primaryConstructor?.name shouldBeEqualTo "SampleClass"
            secondaryConstructors shouldHaveSize 1
            allConstructors shouldHaveSize 2
        }
    }

    private fun getSut(fileName: String) = getSnippetKoScope("core/koclass/snippet/$fileName.kt.txt")
}
