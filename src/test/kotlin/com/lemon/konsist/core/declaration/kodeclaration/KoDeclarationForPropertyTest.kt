package com.lemon.konsist.core.declaration.kodeclaration

import com.lemon.konsist.TestSnippetProvider.getSnippetKoScope
import com.lemon.konsist.core.const.KoModifier
import com.lemon.konsist.core.const.KoModifier.OPEN
import com.lemon.konsist.core.const.KoModifier.PRIVATE
import com.lemon.konsist.core.const.KoModifier.PROTECTED
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
        sut.run {
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
        sut.run {
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
        sut.run {
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
        sut.run {
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
        sut.run {
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
        sut.run {
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
        sut.run {
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
        sut.run {
            hasModifiers(KoModifier.PUBLIC) shouldBeEqualTo true
            hasModifiers(PRIVATE) shouldBeEqualTo false
        }
    }

    @Test
    fun `property-has-two-modifiers`() {
        // given
        val sut = getSut("property-has-two-modifiers")
            .properties(includeNested = true)
            .first()

        // then
        sut.run {
            hasModifiers(PROTECTED) shouldBeEqualTo true
            hasModifiers(OPEN) shouldBeEqualTo true
            hasModifiers(PRIVATE) shouldBeEqualTo false
            hasModifiers(PROTECTED, OPEN) shouldBeEqualTo true
            hasModifiers(OPEN, PROTECTED) shouldBeEqualTo true
            hasModifiers(PRIVATE, OPEN) shouldBeEqualTo false
            hasModifiers(PROTECTED, OPEN, PRIVATE) shouldBeEqualTo false
            hasModifiers() shouldBeEqualTo true
        }
    }

    @Test
    fun `property-has-no-modifier`() {
        // given
        val sut = getSut("property-has-no-modifier")
            .properties()
            .first()

        // then
        sut.run {
            hasModifiers() shouldBeEqualTo true
            hasModifiers(PRIVATE) shouldBeEqualTo false
        }
    }

    @Test
    fun `property-reside-in-path`() {
        // given
        val sut = getSut("property-reside-in-path")
            .properties()
            .first()

        // then
        sut.run {
            resideInPath("Test/") shouldBeEqualTo true
            resideInPath("TEST/") shouldBeEqualTo true
            resideInPath("TEST/", false) shouldBeEqualTo false
            resideInPath("Main/") shouldBeEqualTo false
            resideInPath("") shouldBeEqualTo true
        }
    }

    @Test
    fun `property-reside-outside-path`() {
        // given
        val sut = getSut("property-reside-outside-path")
            .properties()
            .first()

        // then
        sut.run {
            resideOutsidePath("Main/") shouldBeEqualTo true
            resideOutsidePath("MAIN/") shouldBeEqualTo true
            resideOutsidePath("MAIN/", false) shouldBeEqualTo true
            resideOutsidePath("Test/") shouldBeEqualTo false
            resideOutsidePath("TEST/") shouldBeEqualTo false
            resideOutsidePath("TEST/", false) shouldBeEqualTo true
            resideOutsidePath("") shouldBeEqualTo false
        }
    }

    private fun getSut(fileName: String) = getSnippetKoScope("core/declaration/kodeclaration/snippet/forproperty/", fileName)
}
