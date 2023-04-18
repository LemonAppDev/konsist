package com.lemon.konsist.core.declaration.kodeclaration

import com.lemon.konsist.TestSnippetProvider.getSnippetKoScope
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
        val sut = getSnippetFile("interface-is-top-level")
            .interfaces(includeNested = true)
            .first()

        // then
        sut.isTopLevel() shouldBeEqualTo true
    }

    @Test
    fun `interface-is-not-top-level`() {
        // given
        val sut = getSnippetFile("interface-is-not-top-level")
            .interfaces(includeNested = true)
            .first { it.name == "SampleNestedInterface" }

        // then
        sut.isTopLevel() shouldBeEqualTo false
    }

    @Test
    fun `interface-has-no-annotation`() {
        // given
        val sut = getSnippetFile("interface-has-no-annotation")
            .interfaces()
            .first()

        // then
        sut.annotations.isEmpty() shouldBeEqualTo true
    }

    @Test
    fun `interface-has-annotation`() {
        // given
        val sut = getSnippetFile("interface-has-annotation")
            .interfaces()
            .first()

        // then
        sut.run {
            hasAnnotation("SampleAnnotation") shouldBeEqualTo true
            hasAnnotation("NonExistingAnnotation") shouldBeEqualTo false
            hasAnnotation("com.lemon.konsist.testdata.SampleAnnotation") shouldBeEqualTo true
            hasAnnotation("com.lemon.konsist.testdata.NonExistingAnnotation") shouldBeEqualTo false
            hasAnnotation<SampleAnnotation>() shouldBeEqualTo true
            hasAnnotation<NonExistingAnnotation>() shouldBeEqualTo false
        }
    }

    @Test
    fun `interface-has-two-annotations`() {
        // given
        val sut = getSnippetFile("interface-has-two-annotations")
            .interfaces()
            .first()

        // then
        sut.run {
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
    fun `interface-has-no-visibility-modifier`() {
        // given
        val sut = getSnippetFile("interface-has-no-visibility-modifier")
            .interfaces()
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
    fun `interface-has-public-visibility-modifier`() {
        // given
        val sut = getSnippetFile("interface-has-public-visibility-modifier")
            .interfaces()
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
    fun `interface-has-private-visibility-modifier`() {
        // given
        val sut = getSnippetFile("interface-has-private-visibility-modifier")
            .interfaces()
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
    fun `interface-has-protected-visibility-modifier`() {
        // given
        val sut = getSnippetFile("interface-has-protected-visibility-modifier")
            .interfaces()
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
    fun `interface-has-internal-visibility-modifier`() {
        // given
        val sut = getSnippetFile("interface-has-internal-visibility-modifier")
            .interfaces()
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
    fun `interface-has-fully-qualified-name`() {
        // given
        val sut = getSnippetFile("interface-has-fully-qualified-name")
            .interfaces()
            .first()

        // then
        sut.fullyQualifiedName shouldBeEqualTo "com.samplepackage.SampleInterface"
    }

    @Test
    fun `interface-is-in-package`() {
        // given
        val sut = getSnippetFile("interface-is-in-package")
            .interfaces()
            .first()

        // then
        sut.packageName shouldBeEqualTo "com.samplepackage"
    }

    @Test
    fun `interface-is-not-in-package`() {
        // given
        val sut = getSnippetFile("interface-is-not-in-package")
            .interfaces()
            .first()

        // then
        sut.packageName shouldBeEqualTo ""
    }

    @Test
    fun `interface-has-protected-modifier`() {
        // given
        val sut = getSnippetFile("interface-has-protected-modifier")
            .interfaces()
            .first()

        // then
        sut.hasKoModifiers() shouldBeEqualTo true
    }

    @Test
    fun `interface-has-public-modifier`() {
        // given
        val sut = getSnippetFile("interface-has-public-modifier")
            .interfaces()
            .first()

        // then
        sut.run {
            hasModifiers("public") shouldBeEqualTo true
            hasModifiers("private") shouldBeEqualTo false
        }
    }

    @Test
    fun `interface-has-two-modifiers`() {
        // given
        val sut = getSnippetFile("interface-has-two-modifiers")
            .interfaces()
            .first()

        // then
        sut.run {
            hasModifiers("abstract") shouldBeEqualTo true
            hasModifiers("public") shouldBeEqualTo true
            hasModifiers("private") shouldBeEqualTo false
            hasModifiers("abstract", "public") shouldBeEqualTo true
            hasModifiers("public", "abstract") shouldBeEqualTo true
            hasModifiers("private", "abstract") shouldBeEqualTo false
            hasModifiers("abstract", "public", "private") shouldBeEqualTo false
            hasKoModifiers() shouldBeEqualTo true
        }
    }

    @Test
    fun `interface-has-no-modifiers`() {
        // given
        val sut = getSnippetFile("interface-has-no-modifiers")
            .interfaces()
            .first()

        // then
        sut.run {
            hasKoModifiers() shouldBeEqualTo true
            hasModifiers("private") shouldBeEqualTo false
        }
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/kodeclaration/snippet/forinterface/", fileName)
}
