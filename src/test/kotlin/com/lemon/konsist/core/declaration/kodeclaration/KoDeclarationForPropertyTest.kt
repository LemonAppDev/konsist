package com.lemon.konsist.core.declaration.kodeclaration

import com.lemon.konsist.TestSnippetProvider.getSnippetKoScope
import com.lemon.konsist.core.const.KoModifier
import com.lemon.konsist.testdata.NonExistingAnnotation
import com.lemon.konsist.testdata.SampleAnnotation
import com.lemon.konsist.testdata.SampleAnnotation1
import com.lemon.konsist.testdata.SampleAnnotation2
import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldHaveSize
import org.junit.jupiter.api.Test

class KoDeclarationForPropertyTest {
    @Test
    fun `property-is-top-level`() {
        // given
        val sut = getSut("property-is-top-level")
            .properties(includeNested = true)
            .first()

        // then
        sut.isTopLevel shouldBeEqualTo true
    }

    @Test
    fun `property-is-not-top-level`() {
        // given
        val sut = getSut("property-is-not-top-level")
            .properties(includeNested = true)
            .first { it.name == "sampleNestedProperty" }

        // then
        sut.isTopLevel shouldBeEqualTo false
    }

    @Test
    fun `property-has-no-annotation`() {
        // given
        val sut = getSut("property-has-no-annotation")
            .properties()
            .first()

        // then
        sut.annotations shouldHaveSize 0
    }

    @Test
    fun `property-has-annotation`() {
        // given
        val sut = getSut("property-has-annotation")
            .properties()
            .first()

        // then
        with(sut) {
            annotations shouldHaveSize 1
            hasAnnotation(SampleAnnotation::class) shouldBeEqualTo true
            hasAnnotation(NonExistingAnnotation::class) shouldBeEqualTo false
        }
    }

    @Test
    fun `property-has-two-annotations`() {
        // given
        val sut = getSut("property-has-two-annotations")
            .properties()
            .first()

        // then
        with(sut) {
            annotations shouldHaveSize 2
            hasAnnotation(SampleAnnotation1::class) shouldBeEqualTo true
            hasAnnotation(SampleAnnotation2::class) shouldBeEqualTo true
            hasAnnotation(NonExistingAnnotation::class) shouldBeEqualTo false
        }
    }

    @Test
    fun `property-has-no-visibility-modifier`() {
        // given
        val sut = getSut("property-has-no-visibility-modifier")
            .properties()
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
    fun `property-has-public-visibility-modifier`() {
        // given
        val sut = getSut("property-has-public-visibility-modifier")
            .properties()
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
    fun `property-has-private-visibility-modifier`() {
        // given
        val sut = getSut("property-has-private-visibility-modifier")
            .properties()
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
    fun `property-has-protected-visibility-modifier`() {
        // given
        val sut = getSut("property-has-protected-visibility-modifier")
            .properties()
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
    fun `property-has-internal-visibility-modifier`() {
        // given
        val sut = getSut("property-has-internal-visibility-modifier")
            .properties()
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
    fun `property-has-fully-qualified-name`() {
        // given
        val sut = getSut("property-has-fully-qualified-name")
            .properties()
            .first()

        // then
        sut.fullyQualifiedName shouldBeEqualTo "com.samplepackage.sampleProperty"
    }

    @Test
    fun `property-is-in-package`() {
        // given
        val sut = getSut("property-is-in-package")
            .properties()
            .first()

        // then
        sut.packageName shouldBeEqualTo "com.samplepackage"
    }

    @Test
    fun `property-is-not-in-package`() {
        // given
        val sut = getSut("property-is-not-in-package")
            .properties()
            .first()

        // then
        sut.packageName shouldBeEqualTo ""
    }

    @Test
    fun `property-has-protected-modifier`() {
        // given
        val sut = getSut("property-has-protected-modifier")
            .properties()
            .first()

        // then
        sut.hasModifiers() shouldBeEqualTo true
    }

    @Test
    fun `property-has-public-modifier`() {
        // given
        val sut = getSut("property-has-public-modifier")
            .properties()
            .first()

        // then
        with(sut) {
            hasModifiers(KoModifier.PUBLIC) shouldBeEqualTo true
            hasModifiers(KoModifier.PRIVATE) shouldBeEqualTo false
        }
    }

    @Test
    fun `property-reside-in-package`() {
        // given
        val sut = getSut("property-reside-in-package")
            .properties()
            .first()

        // then
        sut.resideInAPackages("samplepackage") shouldBeEqualTo true
    }

    @Test
    fun `property-not-reside-in-package`() {
        // given
        val sut = getSut("property-not-reside-in-package")
            .properties()
            .first()

        // then
        sut.resideInAPackages("otherpackage") shouldBeEqualTo false
    }

    private fun getSut(fileName: String) = getSnippetKoScope("kodeclaration/snippet/forproperty/", fileName)
}
