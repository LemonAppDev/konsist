package com.lemon.konsist.core.declaration.kodeclaration

import com.lemon.konsist.TestSnippetProvider.getSnippetKoScope
import com.lemon.konsist.core.const.KoModifier
import com.lemon.konsist.testdata.NonExistingAnnotation
import com.lemon.konsist.testdata.SampleAnnotation
import com.lemon.konsist.testdata.SampleAnnotation1
import com.lemon.konsist.testdata.SampleAnnotation2
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
    fun `interface-has-no-annotation`() {
        // given
        val sut = getSut("interface-has-no-annotation")
            .interfaces()
            .first()

        // then
        sut.annotations.isEmpty() shouldBeEqualTo true
    }

    @Test
    fun `interface-has-annotation`() {
        // given
        val sut = getSut("interface-has-annotation")
            .interfaces()
            .first()

        // then
        with(sut) {
            hasAnnotation(SampleAnnotation::class) shouldBeEqualTo true
            hasAnnotation(NonExistingAnnotation::class) shouldBeEqualTo false
        }
    }

    @Test
    fun `interface-has-two-annotations`() {
        // given
        val sut = getSut("interface-has-two-annotations")
            .interfaces()
            .first()

        // then
        with(sut) {
            hasAnnotation(SampleAnnotation1::class) shouldBeEqualTo true
            hasAnnotation(SampleAnnotation2::class) shouldBeEqualTo true
            hasAnnotation(NonExistingAnnotation::class) shouldBeEqualTo false
        }
    }

    @Test
    fun `interface-has-no-visibility-modifier`() {
        // given
        val sut = getSut("interface-has-no-visibility-modifier")
            .interfaces()
            .first()

        // then
        with(sut) {
            isPublicOrDefault shouldBeEqualTo true
            isPublic shouldBeEqualTo false
            isPrivate shouldBeEqualTo false
            isProtected shouldBeEqualTo false
            isInternal shouldBeEqualTo false
        }
    }

    @Test
    fun `interface-has-public-visibility-modifier`() {
        // given
        val sut = getSut("interface-has-public-visibility-modifier")
            .interfaces()
            .first()

        // then
        with(sut) {
            isPublicOrDefault shouldBeEqualTo true
            isPublic shouldBeEqualTo true
            isPrivate shouldBeEqualTo false
            isProtected shouldBeEqualTo false
            isInternal shouldBeEqualTo false
        }
    }

    @Test
    fun `interface-has-private-visibility-modifier`() {
        // given
        val sut = getSut("interface-has-private-visibility-modifier")
            .interfaces()
            .first()

        // then
        with(sut) {
            isPublicOrDefault shouldBeEqualTo false
            isPublic shouldBeEqualTo false
            isPrivate shouldBeEqualTo true
            isProtected shouldBeEqualTo false
            isInternal shouldBeEqualTo false
        }
    }

    @Test
    fun `interface-has-protected-visibility-modifier`() {
        // given
        val sut = getSut("interface-has-protected-visibility-modifier")
            .interfaces()
            .first()

        // then
        with(sut) {
            isPublicOrDefault shouldBeEqualTo false
            isPublic shouldBeEqualTo false
            isPrivate shouldBeEqualTo false
            isProtected shouldBeEqualTo true
            isInternal shouldBeEqualTo false
        }
    }

    @Test
    fun `interface-has-internal-visibility-modifier`() {
        // given
        val sut = getSut("interface-has-internal-visibility-modifier")
            .interfaces()
            .first()

        // then
        with(sut) {
            isPublicOrDefault shouldBeEqualTo false
            isPublic shouldBeEqualTo false
            isPrivate shouldBeEqualTo false
            isProtected shouldBeEqualTo false
            isInternal shouldBeEqualTo true
        }
    }

    @Test
    fun `interface-has-fully-qualified-name`() {
        // given
        val sut = getSut("interface-has-fully-qualified-name")
            .interfaces()
            .first()

        // then
        sut.fullyQualifiedName shouldBeEqualTo "com.samplepackage.SampleInterface"
    }

    @Test
    fun `interface-is-in-package`() {
        // given
        val sut = getSut("interface-is-in-package")
            .interfaces()
            .first()

        // then
        sut.packageName shouldBeEqualTo "com.samplepackage"
    }

    @Test
    fun `interface-is-not-in-package`() {
        // given
        val sut = getSut("interface-is-not-in-package")
            .interfaces()
            .first()

        // then
        sut.packageName shouldBeEqualTo ""
    }

    @Test
    fun `interface-has-protected-modifier`() {
        // given
        val sut = getSut("interface-has-protected-modifier")
            .interfaces()
            .first()

        // then
        sut.hasModifiers() shouldBeEqualTo true
    }

    @Test
    fun `interface-has-public-modifier`() {
        // given
        val sut = getSut("interface-has-public-modifier")
            .interfaces()
            .first()

        // then
        with(sut) {
            hasModifiers(KoModifier.PUBLIC) shouldBeEqualTo true
            hasModifiers(KoModifier.PRIVATE) shouldBeEqualTo false
        }
    }

    private fun getSut(fileName: String) = getSnippetKoScope("kodeclaration/snippet/forinterface/", fileName)
}
