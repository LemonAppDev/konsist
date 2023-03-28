package com.lemon.konsist.core.kodeclaration

import NonExistingAnnotation
import SampleAnnotation
import SampleAnnotation1
import SampleAnnotation2
import com.lemon.konsist.TestSnippetProvider.getSnippetKoScope
import com.lemon.konsist.core.const.Modifier.PRIVATE
import com.lemon.konsist.core.const.Modifier.PUBLIC
import org.amshove.kluent.shouldBe
import org.amshove.kluent.shouldBeEqualTo
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

    @Test
    fun `class-with-fully-qualified-name`() {
        // given
        val sut = getSut("class-with-fully-qualified-name")

        // then
        sut.classes()
            .first()
            .fullyQualifiedName shouldBeEqualTo "com.samplepackage.SampleClass"
    }

    @Test
    fun `class-with-package`() {
        // given
        val sut = getSut("class-with-package")

        // then
        sut.classes()
            .first()
            .packageDirective shouldBeEqualTo "com.samplepackage"
    }

    @Test
    fun `class-without-package`() {
        // given
        val sut = getSut("class-without-package")

        // then
        sut.classes()
            .first()
            .packageDirective shouldBeEqualTo ""
    }

    @Test
    fun `class-with-protected-modifier`() {
        // given
        val sut = getSut("class-with-protected-modifier")

        // then
        sut.classes()
            .first()
            .hasModifiers() shouldBe true
    }

    @Test
    fun `class-with-public-modifier`() {
        // given
        val sut = getSut("class-with-public-modifier")

        // then
        sut.classes()
            .first()
            .apply {
                hasModifiers(PUBLIC) shouldBe true
                hasModifiers(PRIVATE) shouldBe false
            }
    }

    @Test
    fun `class-reside-in-package`() {
        // given
        val sut = getSut("class-reside-in-package")

        // then
        sut.classes()
            .first()
            .resideInAPackages("samplepackage") shouldBe true
    }

    @Test
    fun `class-not-reside-in-package`() {
        // given
        val sut = getSut("class-not-reside-in-package")

        // then
        sut.classes()
            .first()
            .resideInAPackages("otherpackage") shouldBe false
    }

    private fun getSut(fileName: String) = getSnippetKoScope("core/kodeclaration/snippet/forclass/$fileName.kt.txt")
}
