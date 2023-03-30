package com.lemon.konsist.core.kodeclaration

import NonExistingAnnotation
import SampleAnnotation
import SampleAnnotation1
import SampleAnnotation2
import com.lemon.konsist.TestSnippetProvider.getSnippetKoScope
import com.lemon.konsist.core.const.KoModifier
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoDeclarationForInterfaceTest {
    @Test
    fun `interface-is-top-level`() {
        // given
        val sut = getSut("interface-is-top-level")
            .interfaces(includeNested = true)
            .first()

        // then
        sut.isTopLevel shouldBeEqualTo true
    }

    @Test
    fun `interface-is-not-top-level`() {
        // given
        val sut = getSut("interface-is-not-top-level")
            .interfaces(includeNested = true)
            .first { it.name == "SampleNestedInterface" }

        // then
        sut.isTopLevel shouldBeEqualTo false
    }

    @Test
    fun `interface-without-annotation`() {
        // given
        val sut = getSut("interface-without-annotation")
            .interfaces()
            .first()

        // then
        sut.annotations.isEmpty() shouldBeEqualTo true
    }

    @Test
    fun `interface-with-annotation`() {
        // given
        val sut = getSut("interface-with-annotation")
            .interfaces()
            .first()

        // then
        sut.apply {
            hasAnnotation(SampleAnnotation::class) shouldBeEqualTo true
            hasAnnotation(NonExistingAnnotation::class) shouldBeEqualTo false
        }
    }

    @Test
    fun `interface-with-two-annotations`() {
        // given
        val sut = getSut("interface-with-two-annotations")
            .interfaces()
            .first()

        // then
        sut.apply {
            hasAnnotation(SampleAnnotation1::class) shouldBeEqualTo true
            hasAnnotation(SampleAnnotation2::class) shouldBeEqualTo true
            hasAnnotation(NonExistingAnnotation::class) shouldBeEqualTo false
        }
    }

    @Test
    fun `interface-without-visibility-modifier`() {
        // given
        val sut = getSut("interface-without-visibility-modifier")
            .interfaces()
            .first()

        // then
        sut.apply {
            isPublic shouldBeEqualTo true
            isPrivate shouldBeEqualTo false
            isProtected shouldBeEqualTo false
            isInternal shouldBeEqualTo false
        }
    }

    @Test
    fun `interface-with-public-visibility-modifier`() {
        // given
        val sut = getSut("interface-with-public-visibility-modifier")
            .interfaces()
            .first()

        // then
        sut.apply {
            isPublic shouldBeEqualTo true
            isPrivate shouldBeEqualTo false
            isProtected shouldBeEqualTo false
            isInternal shouldBeEqualTo false
        }
    }

    @Test
    fun `interface-with-private-visibility-modifier`() {
        // given
        val sut = getSut("interface-with-private-visibility-modifier")
            .interfaces()
            .first()

        // then
        sut.apply {
            isPublic shouldBeEqualTo false
            isPrivate shouldBeEqualTo true
            isProtected shouldBeEqualTo false
            isInternal shouldBeEqualTo false
        }
    }

    @Test
    fun `interface-with-protected-visibility-modifier`() {
        // given
        val sut = getSut("interface-with-protected-visibility-modifier")
            .interfaces()
            .first()

        // then
        sut.apply {
            isPublic shouldBeEqualTo false
            isPrivate shouldBeEqualTo false
            isProtected shouldBeEqualTo true
            isInternal shouldBeEqualTo false
        }
    }

    @Test
    fun `interface-with-internal-visibility-modifier`() {
        // given
        val sut = getSut("interface-with-internal-visibility-modifier")
            .interfaces()
            .first()

        // then
        sut.apply {
            isPublic shouldBeEqualTo false
            isPrivate shouldBeEqualTo false
            isProtected shouldBeEqualTo false
            isInternal shouldBeEqualTo true
        }
    }

    @Test
    fun `interface-with-fully-qualified-name`() {
        // given
        val sut = getSut("interface-with-fully-qualified-name")
            .interfaces()
            .first()

        // then
        sut.fullyQualifiedName shouldBeEqualTo "com.samplepackage.SampleInterface"
    }

    @Test
    fun `interface-with-package`() {
        // given
        val sut = getSut("interface-with-package")
            .interfaces()
            .first()

        // then
        sut.packageDirective shouldBeEqualTo "com.samplepackage"
    }

    @Test
    fun `interface-without-package`() {
        // given
        val sut = getSut("interface-without-package")
            .interfaces()
            .first()

        // then
        sut.packageDirective shouldBeEqualTo ""
    }

    @Test
    fun `interface-with-protected-modifier`() {
        // given
        val sut = getSut("interface-with-protected-modifier")
            .interfaces()
            .first()

        // then
        sut.hasModifiers() shouldBeEqualTo true
    }

    @Test
    fun `interface-with-public-modifier`() {
        // given
        val sut = getSut("interface-with-public-modifier")
            .interfaces()
            .first()

        // then
        sut.apply {
            hasModifiers(KoModifier.PUBLIC) shouldBeEqualTo true
            hasModifiers(KoModifier.PRIVATE) shouldBeEqualTo false
        }
    }

    @Test
    fun `interface-reside-in-package`() {
        // given
        val sut = getSut("interface-reside-in-package")
            .interfaces()
            .first()

        // then
        sut.resideInAPackages("samplepackage") shouldBeEqualTo true
    }

    @Test
    fun `interface-not-reside-in-package`() {
        // given
        val sut = getSut("interface-not-reside-in-package")
            .interfaces()
            .first()

        // then
        sut.resideInAPackages("otherpackage") shouldBeEqualTo false
    }

    private fun getSut(fileName: String) = getSnippetKoScope("core/kodeclaration/snippet/forinterface/$fileName.kt.txt")
}
