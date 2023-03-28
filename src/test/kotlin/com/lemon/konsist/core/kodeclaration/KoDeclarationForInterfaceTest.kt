package com.lemon.konsist.core.kodeclaration

import NonExistingAnnotation
import SampleAnnotation
import SampleAnnotation1
import SampleAnnotation2
import com.lemon.konsist.TestSnippetProvider.getSnippetKoScope
import com.lemon.konsist.core.const.Modifier
import org.amshove.kluent.shouldBe
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoDeclarationForInterfaceTest {
    @Test
    fun `interface-is-top-level`() {
        // given
        val sut = getSut("interface-is-top-level")

        // then
        sut.interfaces(includeNested = true).first().isTopLevel shouldBe true
    }

    @Test
    fun `interface-is-not-top-level`() {
        // given
        val sut = getSut("interface-is-not-top-level")

        // then
        sut.interfaces(includeNested = true).first { it.name == "SampleNestedInterface" }.isTopLevel shouldBe false
    }

    @Test
    fun `interface-without-annotation`() {
        // given
        val sut = getSut("interface-without-annotation")

        // then
        sut.interfaces().first().annotations.isEmpty() shouldBe true
    }

    @Test
    fun `interface-with-annotation`() {
        // given
        val sut = getSut("interface-with-annotation")

        // then
        sut.interfaces().first().apply {
            hasAnnotation(SampleAnnotation::class) shouldBe true
            hasAnnotation(NonExistingAnnotation::class) shouldBe false
        }
    }

    @Test
    fun `interface-with-two-annotations`() {
        // given
        val sut = getSut("interface-with-two-annotations")

        // then
        sut.interfaces().first().apply {
            hasAnnotation(SampleAnnotation1::class) shouldBe true
            hasAnnotation(SampleAnnotation2::class) shouldBe true
            hasAnnotation(NonExistingAnnotation::class) shouldBe false
        }
    }

    @Test
    fun `interface-without-visibility-modifier`() {
        // given
        val sut = getSut("interface-without-visibility-modifier")

        // then
        sut.interfaces().first().apply {
            isPublic shouldBe true
            isPrivate shouldBe false
            isProtected shouldBe false
            isInternal shouldBe false
        }
    }

    @Test
    fun `interface-with-public-visibility-modifier`() {
        // given
        val sut = getSut("interface-with-public-visibility-modifier")

        // then
        sut.interfaces().first().apply {
            isPublic shouldBe true
            isPrivate shouldBe false
            isProtected shouldBe false
            isInternal shouldBe false
        }
    }

    @Test
    fun `interface-with-private-visibility-modifier`() {
        // given
        val sut = getSut("interface-with-private-visibility-modifier")

        // then
        sut.interfaces().first().apply {
            isPublic shouldBe false
            isPrivate shouldBe true
            isProtected shouldBe false
            isInternal shouldBe false
        }
    }

    @Test
    fun `interface-with-protected-visibility-modifier`() {
        // given
        val sut = getSut("interface-with-protected-visibility-modifier")

        // then
        sut.interfaces().first().apply {
            isPublic shouldBe false
            isPrivate shouldBe false
            isProtected shouldBe true
            isInternal shouldBe false
        }
    }

    @Test
    fun `interface-with-internal-visibility-modifier`() {
        // given
        val sut = getSut("interface-with-internal-visibility-modifier")

        // then
        sut.interfaces().first().apply {
            isPublic shouldBe false
            isPrivate shouldBe false
            isProtected shouldBe false
            isInternal shouldBe true
        }
    }

    @Test
    fun `interface-with-fully-qualified-name`() {
        // given
        val sut = getSut("interface-with-fully-qualified-name")

        // then
        sut.interfaces()
            .first()
            .fullyQualifiedName shouldBeEqualTo "com.samplepackage.SampleInterface"
    }

    @Test
    fun `interface-with-package`() {
        // given
        val sut = getSut("interface-with-package")

        // then
        sut.interfaces()
            .first()
            .packageDirective shouldBeEqualTo "com.samplepackage"
    }

    @Test
    fun `interface-without-package`() {
        // given
        val sut = getSut("interface-without-package")

        // then
        sut.interfaces()
            .first()
            .packageDirective shouldBeEqualTo ""
    }

    @Test
    fun `interface-with-protected-modifier`() {
        // given
        val sut = getSut("interface-with-protected-modifier")

        // then
        sut.interfaces()
            .first()
            .hasModifiers() shouldBe true
    }

    @Test
    fun `interface-with-public-modifier`() {
        // given
        val sut = getSut("interface-with-public-modifier")

        // then
        sut.interfaces()
            .first()
            .apply {
                hasModifiers(Modifier.PUBLIC) shouldBe true
                hasModifiers(Modifier.PRIVATE) shouldBe false
            }
    }

    @Test
    fun `interface-reside-in-package`() {
        // given
        val sut = getSut("interface-reside-in-package")

        // then
        sut.interfaces()
            .first()
            .resideInAPackages("samplepackage") shouldBe true
    }

    @Test
    fun `interface-not-reside-in-package`() {
        // given
        val sut = getSut("interface-not-reside-in-package")

        // then
        sut.interfaces()
            .first()
            .resideInAPackages("otherpackage") shouldBe false
    }

    private fun getSut(fileName: String) = getSnippetKoScope("core/kodeclaration/snippet/forinterface/$fileName.kt.txt")
}
