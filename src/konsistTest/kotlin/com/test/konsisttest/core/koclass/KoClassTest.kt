package com.test.konsisttest.core.koclass

import com.test.konsisttest.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.shouldBe
import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldNotBe
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
        sut.classes().first().primaryConstructor shouldNotBe null
    }

    @Test
    fun `class-with-primary-and-secondary-constructors`() {
        // given
        val sut = getSut("class-with-primary-and-secondary-constructors")

        // then
        sut.classes().first().apply {
            primaryConstructor shouldNotBe null
            secondaryConstructors.size shouldBeEqualTo 1
            allConstructors.size shouldBeEqualTo 2
        }
    }

    private fun getSut(fileName: String) = getSnippetKoScope("core/koclass/snippet/$fileName.kt.txt")
}
