package com.test.konsisttest.core.kodeclaration

import NonExistingAnnotation
import SampleAnnotation
import SampleAnnotation1
import SampleAnnotation2
import com.test.konsisttest.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.shouldBe
import org.amshove.kluent.shouldHaveSize
import org.junit.jupiter.api.Test

class KoDeclarationForFunctionTest {
    @Test
    fun `function-is-top-level`() {
        // given
        val sut = getSut("function-is-top-level")

        // then
        sut.functions(includeNested = true).first().isTopLevel shouldBe true
    }

    @Test
    fun `function-is-not-top-level`() {
        // given
        val sut = getSut("function-is-not-top-level")

        // then
        sut.functions(includeNested = true).first { it.name == "SampleNestedFunction" }.isTopLevel shouldBe false
    }

    @Test
    fun `function-without-annotation`() {
        // given
        val sut = getSut("function-without-annotation")

        // then
        sut.functions().first().annotations shouldHaveSize 0
    }

    @Test
    fun `function-with-annotation`() {
        // given
        val sut = getSut("function-with-annotation")

        // then
        sut.functions().first().apply {
            annotations shouldHaveSize 1
            hasAnnotation(SampleAnnotation::class) shouldBe true
            hasAnnotation(NonExistingAnnotation::class) shouldBe false
        }
    }

    @Test
    fun `function-with-two-annotations`() {
        // given
        val sut = getSut("function-with-two-annotations")

        // then
        sut.functions().first().apply {
            annotations shouldHaveSize 2
            hasAnnotation(SampleAnnotation1::class) shouldBe true
            hasAnnotation(SampleAnnotation2::class) shouldBe true
            hasAnnotation(NonExistingAnnotation::class) shouldBe false
        }
    }

    @Test
    fun `function-without-visibility-modifier`() {
        // given
        val sut = getSut("function-without-visibility-modifier")

        // then
        sut.functions().first().apply {
            isPublic shouldBe true
            isPrivate shouldBe false
            isProtected shouldBe false
            isInternal shouldBe false
        }
    }

    @Test
    fun `function-with-public-visibility-modifier`() {
        // given
        val sut = getSut("function-with-public-visibility-modifier")

        // then
        sut.functions().first().apply {
            isPublic shouldBe true
            isPrivate shouldBe false
            isProtected shouldBe false
            isInternal shouldBe false
        }
    }

    @Test
    fun `function-with-private-visibility-modifier`() {
        // given
        val sut = getSut("function-with-private-visibility-modifier")

        // then
        sut.functions().first().apply {
            isPublic shouldBe false
            isPrivate shouldBe true
            isProtected shouldBe false
            isInternal shouldBe false
        }
    }

    @Test
    fun `function-with-protected-visibility-modifier`() {
        // given
        val sut = getSut("function-with-protected-visibility-modifier")

        // then
        sut.functions().first().apply {
            isPublic shouldBe false
            isPrivate shouldBe false
            isProtected shouldBe true
            isInternal shouldBe false
        }
    }

    @Test
    fun `function-with-internal-visibility-modifier`() {
        // given
        val sut = getSut("function-with-internal-visibility-modifier")

        // then
        sut.functions().first().apply {
            isPublic shouldBe false
            isPrivate shouldBe false
            isProtected shouldBe false
            isInternal shouldBe true
        }
    }

    private fun getSut(fileName: String) = getSnippetKoScope("core/kodeclaration/snippet/forfunction/$fileName.kt.txt")
}
