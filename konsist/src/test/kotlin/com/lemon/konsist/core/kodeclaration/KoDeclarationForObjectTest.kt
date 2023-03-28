package com.lemon.konsist.core.kodeclaration

import NonExistingAnnotation
import SampleAnnotation
import SampleAnnotation1
import SampleAnnotation2
import com.lemon.konsist.TestSnippetProvider.getSnippetKoScope
import com.lemon.konsist.core.const.Modifier
import org.amshove.kluent.shouldBe
import org.amshove.kluent.shouldBeEqualTo
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

    @Test
    fun `object-with-fully-qualified-name`() {
        // given
        val sut = getSut("object-with-fully-qualified-name")

        // then
        sut.objects()
            .first()
            .fullyQualifiedName shouldBeEqualTo "com.samplepackage.SampleObject"
    }

    @Test
    fun `object-with-package`() {
        // given
        val sut = getSut("object-with-package")

        // then
        sut.objects()
            .first()
            .packageDirective shouldBeEqualTo "com.samplepackage"
    }

    @Test
    fun `object-without-package`() {
        // given
        val sut = getSut("object-without-package")

        // then
        sut.objects()
            .first()
            .packageDirective shouldBeEqualTo ""
    }

    @Test
    fun `object-with-protected-modifier`() {
        // given
        val sut = getSut("object-with-protected-modifier")

        // then
        sut.objects()
            .first()
            .hasModifiers() shouldBe true
    }

    @Test
    fun `object-with-public-modifier`() {
        // given
        val sut = getSut("object-with-public-modifier")

        // then
        sut.objects()
            .first()
            .apply {
                hasModifiers(Modifier.PUBLIC) shouldBe true
                hasModifiers(Modifier.PRIVATE) shouldBe false
            }
    }

    @Test
    fun `object-reside-in-package`() {
        // given
        val sut = getSut("object-reside-in-package")

        // then
        sut.objects()
            .first()
            .resideInAPackages("samplepackage") shouldBe true
    }

    @Test
    fun `object-not-reside-in-package`() {
        // given
        val sut = getSut("object-not-reside-in-package")

        // then
        sut.objects()
            .first()
            .resideInAPackages("otherpackage") shouldBe false
    }

    private fun getSut(fileName: String) = getSnippetKoScope("core/kodeclaration/snippet/forobject/$fileName.kt.txt")
}
