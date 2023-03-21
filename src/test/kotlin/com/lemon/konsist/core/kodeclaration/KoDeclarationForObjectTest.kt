package com.lemon.konsist.core.kodeclaration

import NonExistingAnnotation
import SampleAnnotation
import SampleAnnotation1
import SampleAnnotation2
import com.lemon.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.shouldBe
import org.amshove.kluent.shouldHaveSize
import org.junit.jupiter.api.Test

class KoDeclarationForObjectTest {
    @Test
    fun `object-is-top-level`() {
        // given
        val sut = getSut("object-is-top-level")

        // then
        sut.objects(includeNested = true).first().isTopLevel shouldBe true
    }

    @Test
    fun `object-is-not-top-level`() {
        // given
        val sut = getSut("object-is-not-top-level")

        // then
        sut.objects(includeNested = true).first { it.name == "SampleNestedObject" }.isTopLevel shouldBe false
    }

    @Test
    fun `object-without-annotation`() {
        // given
        val sut = getSut("object-without-annotation")

        // then
        sut.objects().first().annotations shouldHaveSize 0
    }

    @Test
    fun `object-with-annotation`() {
        // given
        val sut = getSut("object-with-annotation")

        // then
        sut.objects().first().apply {
            annotations shouldHaveSize 1
            hasAnnotation(SampleAnnotation::class) shouldBe true
            hasAnnotation(NonExistingAnnotation::class) shouldBe false
        }
    }

    @Test
    fun `object-with-two-annotations`() {
        // given
        val sut = getSut("object-with-two-annotations")

        // then
        sut.objects().first().apply {
            annotations shouldHaveSize 2
            hasAnnotation(SampleAnnotation1::class) shouldBe true
            hasAnnotation(SampleAnnotation2::class) shouldBe true
            hasAnnotation(NonExistingAnnotation::class) shouldBe false
        }
    }

    @Test
    fun `object-without-visibility-modifier`() {
        // given
        val sut = getSut("object-without-visibility-modifier")

        // then
        sut.objects().first().apply {
            isPublic shouldBe true
            isPrivate shouldBe false
            isProtected shouldBe false
            isInternal shouldBe false
        }
    }

    @Test
    fun `object-with-public-visibility-modifier`() {
        // given
        val sut = getSut("object-with-public-visibility-modifier")

        // then
        sut.objects().first().apply {
            isPublic shouldBe true
            isPrivate shouldBe false
            isProtected shouldBe false
            isInternal shouldBe false
        }
    }

    @Test
    fun `object-with-private-visibility-modifier`() {
        // given
        val sut = getSut("object-with-private-visibility-modifier")

        // then
        sut.objects().first().apply {
            isPublic shouldBe false
            isPrivate shouldBe true
            isProtected shouldBe false
            isInternal shouldBe false
        }
    }

    @Test
    fun `object-with-protected-visibility-modifier`() {
        // given
        val sut = getSut("object-with-protected-visibility-modifier")

        // then
        sut.objects().first().apply {
            isPublic shouldBe false
            isPrivate shouldBe false
            isProtected shouldBe true
            isInternal shouldBe false
        }
    }

    @Test
    fun `object-with-internal-visibility-modifier`() {
        // given
        val sut = getSut("object-with-internal-visibility-modifier")

        // then
        sut.objects().first().apply {
            isPublic shouldBe false
            isPrivate shouldBe false
            isProtected shouldBe false
            isInternal shouldBe true
        }
    }

    private fun getSut(fileName: String) = getSnippetKoScope("core/kodeclaration/snippet/forobject/$fileName.kt.txt")
}
