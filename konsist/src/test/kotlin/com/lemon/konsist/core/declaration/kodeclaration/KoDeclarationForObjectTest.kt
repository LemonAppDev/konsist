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

class KoDeclarationForObjectTest {
    @Test
    fun `object-is-top-level`() {
        // given
        val sut = getSut("object-is-top-level")
            .objects(includeNested = true)
            .first()

        // then
        sut.isTopLevel shouldBeEqualTo true
    }

    @Test
    fun `object-is-not-top-level`() {
        // given
        val sut = getSut("object-is-not-top-level")
            .objects(includeNested = true)
            .first { it.name == "SampleNestedObject" }

        // then
        sut.isTopLevel shouldBeEqualTo false
    }

    @Test
    fun `object-without-annotation`() {
        // given
        val sut = getSut("object-without-annotation")
            .objects()
            .first()

        // then
        sut.annotations shouldHaveSize 0
    }

    @Test
    fun `object-with-annotation`() {
        // given
        val sut = getSut("object-with-annotation")
            .objects()
            .first()

        // then
        with(sut) {
            annotations shouldHaveSize 1
            hasAnnotation(SampleAnnotation::class) shouldBeEqualTo true
            hasAnnotation(NonExistingAnnotation::class) shouldBeEqualTo false
        }
    }

    @Test
    fun `object-with-two-annotations`() {
        // given
        val sut = getSut("object-with-two-annotations")
            .objects()
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
    fun `object-without-visibility-modifier`() {
        // given
        val sut = getSut("object-without-visibility-modifier")
            .objects()
            .first()

        // then
        with(sut) {
            isPublic shouldBeEqualTo true
            isPrivate shouldBeEqualTo false
            isProtected shouldBeEqualTo false
            isInternal shouldBeEqualTo false
        }
    }

    @Test
    fun `object-with-public-visibility-modifier`() {
        // given
        val sut = getSut("object-with-public-visibility-modifier")
            .objects()
            .first()

        // then
        with(sut) {
            isPublic shouldBeEqualTo true
            isPrivate shouldBeEqualTo false
            isProtected shouldBeEqualTo false
            isInternal shouldBeEqualTo false
        }
    }

    @Test
    fun `object-with-private-visibility-modifier`() {
        // given
        val sut = getSut("object-with-private-visibility-modifier")
            .objects()
            .first()

        // then
        with(sut) {
            isPublic shouldBeEqualTo false
            isPrivate shouldBeEqualTo true
            isProtected shouldBeEqualTo false
            isInternal shouldBeEqualTo false
        }
    }

    @Test
    fun `object-with-protected-visibility-modifier`() {
        // given
        val sut = getSut("object-with-protected-visibility-modifier")
            .objects()
            .first()

        // then
        with(sut) {
            isPublic shouldBeEqualTo false
            isPrivate shouldBeEqualTo false
            isProtected shouldBeEqualTo true
            isInternal shouldBeEqualTo false
        }
    }

    @Test
    fun `object-with-internal-visibility-modifier`() {
        // given
        val sut = getSut("object-with-internal-visibility-modifier")
            .objects()
            .first()

        // then
        with(sut) {
            isPublic shouldBeEqualTo false
            isPrivate shouldBeEqualTo false
            isProtected shouldBeEqualTo false
            isInternal shouldBeEqualTo true
        }
    }

    @Test
    fun `object-with-fully-qualified-name`() {
        // given
        val sut = getSut("object-with-fully-qualified-name")
            .objects()
            .first()

        // then
        sut.fullyQualifiedName shouldBeEqualTo "com.samplepackage.SampleObject"
    }

    @Test
    fun `object-with-package`() {
        // given
        val sut = getSut("object-with-package")
            .objects()
            .first()

        // then
        sut.packageName shouldBeEqualTo "com.samplepackage"
    }

    @Test
    fun `object-without-package`() {
        // given
        val sut = getSut("object-without-package")
            .objects()
            .first()

        // then
        sut.packageName shouldBeEqualTo ""
    }

    @Test
    fun `object-with-protected-modifier`() {
        // given
        val sut = getSut("object-with-protected-modifier")
            .objects()
            .first()

        // then
        sut.hasModifiers() shouldBeEqualTo true
    }

    @Test
    fun `object-with-public-modifier`() {
        // given
        val sut = getSut("object-with-public-modifier")
            .objects()
            .first()

        // then
        with(sut) {
            hasModifiers(KoModifier.PUBLIC) shouldBeEqualTo true
            hasModifiers(KoModifier.PRIVATE) shouldBeEqualTo false
        }
    }

    @Test
    fun `object-reside-in-package`() {
        // given
        val sut = getSut("object-reside-in-package")
            .objects()
            .first()

        // then
        sut.resideInAPackages("samplepackage") shouldBeEqualTo true
    }

    @Test
    fun `object-not-reside-in-package`() {
        // given
        val sut = getSut("object-not-reside-in-package")
            .objects()
            .first()

        // then
        sut.resideInAPackages("otherpackage") shouldBeEqualTo false
    }

    private fun
    getSut(fileName: String) = getSnippetKoScope("kodeclaration/snippet/forobject/$fileName.kttxt")
}
