package com.lemon.konsist.core.declaration.kodeclaration

import com.lemon.konsist.TestSnippetProvider.getSnippetKoScope
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
            hasAnnotation("com.lemon.konsist.testdata.SampleAnnotation") shouldBeEqualTo true
            hasAnnotation("com.lemon.konsist.testdata.NonExistingAnnotation") shouldBeEqualTo false
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
            hasAnnotation("com.lemon.konsist.testdata.SampleAnnotation1") shouldBeEqualTo true
            hasAnnotation("com.lemon.konsist.testdata.SampleAnnotation2") shouldBeEqualTo true
            hasAnnotation("com.lemon.konsist.testdata.NonExistingAnnotation") shouldBeEqualTo false
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
        sut.hasKoModifiers() shouldBeEqualTo true
    }

    @Test
    fun `property-has-public-modifier`() {
        // given
        val sut = getSnippetFile("property-has-public-modifier")
            .properties()
            .first()

        // then
        sut.run {
            hasModifiers("public") shouldBeEqualTo true
            hasModifiers("private") shouldBeEqualTo false
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
            hasModifiers("protected") shouldBeEqualTo true
            hasModifiers("open") shouldBeEqualTo true
            hasModifiers("private") shouldBeEqualTo false
            hasModifiers("protected", "open") shouldBeEqualTo true
            hasModifiers("open", "protected") shouldBeEqualTo true
            hasModifiers("private", "open") shouldBeEqualTo false
            hasModifiers("protected", "open", "private") shouldBeEqualTo false
            hasKoModifiers() shouldBeEqualTo true
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
            hasKoModifiers() shouldBeEqualTo true
            hasModifiers("private") shouldBeEqualTo false
        }
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/kodeclaration/snippet/forproperty/", fileName)
}
