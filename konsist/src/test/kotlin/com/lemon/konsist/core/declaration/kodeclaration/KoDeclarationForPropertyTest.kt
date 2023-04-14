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
        val sut = getSnippetFile("property-is-top-level")
            .properties(includeNested = true)
            .first()

        // then
        sut.isTopLevel() shouldBeEqualTo true
    }

    @Test
    fun `property-is-not-top-level`() {
        // given
        val sut = getSnippetFile("property-is-not-top-level")
            .properties(includeNested = true)
            .first { it.name == "sampleNestedProperty" }

        // then
        sut.isTopLevel() shouldBeEqualTo false
    }

    @Test
    fun `property-has-no-annotation`() {
        // given
        val sut = getSnippetFile("property-has-no-annotation")
            .properties()
            .first()

        // then
        sut.annotations shouldHaveSize 0
    }

    @Test
    fun `property-has-annotation`() {
        // given
        val sut = getSnippetFile("property-has-annotation")
            .properties()
            .first()

        // then
        sut.run {
            annotations shouldHaveSize 1
            hasAnnotation("SampleAnnotation") shouldBeEqualTo true
            hasAnnotation("NonExistingAnnotation") shouldBeEqualTo false
            hasAnnotation<SampleAnnotation>() shouldBeEqualTo true
            hasAnnotation<NonExistingAnnotation>() shouldBeEqualTo false
        }
    }

    @Test
    fun `property-has-two-annotations`() {
        // given
        val sut = getSnippetFile("property-has-two-annotations")
            .properties()
            .first()

        // then
        sut.run {
            annotations shouldHaveSize 2
            hasAnnotation("SampleAnnotation1") shouldBeEqualTo true
            hasAnnotation("SampleAnnotation2") shouldBeEqualTo true
            hasAnnotation("NonExistingAnnotation") shouldBeEqualTo false
            hasAnnotation<SampleAnnotation1>() shouldBeEqualTo true
            hasAnnotation<SampleAnnotation2>() shouldBeEqualTo true
            hasAnnotation<NonExistingAnnotation>() shouldBeEqualTo false
        }
    }

    @Test
    fun `property-has-no-visibility-modifier`() {
        // given
        val sut = getSnippetFile("property-has-no-visibility-modifier")
            .properties()
            .first()

        // then
        sut.run {
            isPublicOrDefault() shouldBeEqualTo true
            hasPublicModifier() shouldBeEqualTo false
            hasPrivateModifier() shouldBeEqualTo false
            hasProtectedModifier() shouldBeEqualTo false
            hasInternalModifier() shouldBeEqualTo false
        }
    }

    @Test
    fun `property-has-public-visibility-modifier`() {
        // given
        val sut = getSnippetFile("property-has-public-visibility-modifier")
            .properties()
            .first()

        // then
        sut.run {
            isPublicOrDefault() shouldBeEqualTo true
            hasPublicModifier() shouldBeEqualTo true
            hasPrivateModifier() shouldBeEqualTo false
            hasProtectedModifier() shouldBeEqualTo false
            hasInternalModifier() shouldBeEqualTo false
        }
    }

    @Test
    fun `property-has-private-visibility-modifier`() {
        // given
        val sut = getSnippetFile("property-has-private-visibility-modifier")
            .properties()
            .first()

        // then
        sut.run {
            isPublicOrDefault() shouldBeEqualTo false
            hasPublicModifier() shouldBeEqualTo false
            hasPrivateModifier() shouldBeEqualTo true
            hasProtectedModifier() shouldBeEqualTo false
            hasInternalModifier() shouldBeEqualTo false
        }
    }

    @Test
    fun `property-has-protected-visibility-modifier`() {
        // given
        val sut = getSnippetFile("property-has-protected-visibility-modifier")
            .properties()
            .first()

        // then
        sut.run {
            isPublicOrDefault() shouldBeEqualTo false
            hasPublicModifier() shouldBeEqualTo false
            hasPrivateModifier() shouldBeEqualTo false
            hasProtectedModifier() shouldBeEqualTo true
            hasInternalModifier() shouldBeEqualTo false
        }
    }

    @Test
    fun `property-has-internal-visibility-modifier`() {
        // given
        val sut = getSnippetFile("property-has-internal-visibility-modifier")
            .properties()
            .first()

        // then
        sut.run {
            isPublicOrDefault() shouldBeEqualTo false
            hasPublicModifier() shouldBeEqualTo false
            hasPrivateModifier() shouldBeEqualTo false
            hasProtectedModifier() shouldBeEqualTo false
            hasInternalModifier() shouldBeEqualTo true
        }
    }

    @Test
    fun `property-has-fully-qualified-name`() {
        // given
        val sut = getSnippetFile("property-has-fully-qualified-name")
            .properties()
            .first()

        // then
        sut.fullyQualifiedName shouldBeEqualTo "com.samplepackage.sampleProperty"
    }

    @Test
    fun `property-is-in-package`() {
        // given
        val sut = getSnippetFile("property-is-in-package")
            .properties()
            .first()

        // then
        sut.packageName shouldBeEqualTo "com.samplepackage"
    }

    @Test
    fun `property-is-not-in-package`() {
        // given
        val sut = getSnippetFile("property-is-not-in-package")
            .properties()
            .first()

        // then
        sut.packageName shouldBeEqualTo ""
    }

    @Test
    fun `property-has-protected-modifier`() {
        // given
        val sut = getSnippetFile("property-has-protected-modifier")
            .properties()
            .first()

        // then
        sut.hasModifiers() shouldBeEqualTo true
    }

    @Test
    fun `property-has-public-modifier`() {
        // given
        val sut = getSnippetFile("property-has-public-modifier")
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
        val sut = getSnippetFile("property-has-two-modifiers")
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
        val sut = getSnippetFile("property-has-no-modifier")
            .properties()
            .first()

        // then
        sut.run {
            hasModifiers() shouldBeEqualTo true
            hasModifiers(PRIVATE) shouldBeEqualTo false
        }
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/kodeclaration/snippet/forproperty/", fileName)
}
