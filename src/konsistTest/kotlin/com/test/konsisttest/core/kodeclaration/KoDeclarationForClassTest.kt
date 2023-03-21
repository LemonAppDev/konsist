package com.test.konsisttest.core.kodeclaration

import NonExistingAnnotation
import SampleAnnotation
import SampleAnnotation1
import SampleAnnotation2
import com.test.konsisttest.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.shouldBe
import org.amshove.kluent.shouldHaveSize
import org.junit.jupiter.api.Test

class KoDeclarationForClassTest {
    @Test
    fun `class-is-top-level`() {
        // given
        val sut = getSut("class-is-top-level")

        // then
        sut.classes(includeNested = true).first().isTopLevel shouldBe true
    }

    @Test
    fun `class-is-not-top-level`() {
        // given
        val sut = getSut("class-is-not-top-level")

        // then
        sut.classes(includeNested = true).first { it.name == "SampleNestedClass" }.isTopLevel shouldBe false
    }

    @Test
    fun `class-without-annotation`() {
        // given
        val sut = getSut("class-without-annotation")

        // then
        sut.classes().first().annotations shouldHaveSize 0
    }

    @Test
    fun `class-with-annotation`() {
        // given
        val sut = getSut("class-with-annotation")

        // then
        sut.classes().first().apply {
            annotations shouldHaveSize 1
            hasAnnotation(SampleAnnotation::class) shouldBe true
            hasAnnotation(NonExistingAnnotation::class) shouldBe false
        }
    }

    @Test
    fun `class-with-two-annotations`() {
        // given
        val sut = getSut("class-with-two-annotations")

        // then
        sut.classes().first().apply {
            annotations shouldHaveSize 2
            hasAnnotation(SampleAnnotation1::class) shouldBe true
            hasAnnotation(SampleAnnotation2::class) shouldBe true
            hasAnnotation(NonExistingAnnotation::class) shouldBe false
        }
    }

    @Test
    fun `class-without-visibility-modifier`() {
        // given
        val sut = getSut("class-without-visibility-modifier")

        // then
        sut.classes().first().apply {
            isPublic shouldBe true
            isPrivate shouldBe false
            isProtected shouldBe false
            isInternal shouldBe false
        }
    }

    @Test
    fun `class-with-public-visibility-modifier`() {
        // given
        val sut = getSut("class-with-public-visibility-modifier")

        // then
        sut.classes().first().apply {
            isPublic shouldBe true
            isPrivate shouldBe false
            isProtected shouldBe false
            isInternal shouldBe false
        }
    }

    @Test
    fun `class-with-private-visibility-modifier`() {
        // given
        val sut = getSut("class-with-private-visibility-modifier")

        // then
        sut.classes().first().apply {
            isPublic shouldBe false
            isPrivate shouldBe true
            isProtected shouldBe false
            isInternal shouldBe false
        }
    }

    @Test
    fun `class-with-protected-visibility-modifier`() {
        // given
        val sut = getSut("class-with-protected-visibility-modifier")

        // then
        sut.classes().first().apply {
            isPublic shouldBe false
            isPrivate shouldBe false
            isProtected shouldBe true
            isInternal shouldBe false
        }
    }

    @Test
    fun `class-with-internal-visibility-modifier`() {
        // given
        val sut = getSut("class-with-internal-visibility-modifier")

        // then
        sut.classes().first().apply {
            isPublic shouldBe false
            isPrivate shouldBe false
            isProtected shouldBe false
            isInternal shouldBe true
        }
    }

    private fun getSut(fileName: String) = getSnippetKoScope("core/kodeclaration/snippet/forclass/$fileName.kt.txt")
}
