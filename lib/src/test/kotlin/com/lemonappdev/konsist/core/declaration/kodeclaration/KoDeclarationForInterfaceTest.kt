package com.lemonappdev.konsist.core.declaration.kodeclaration

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import com.lemonappdev.konsist.core.const.KoModifier.ABSTRACT
import com.lemonappdev.konsist.core.const.KoModifier.PRIVATE
import com.lemonappdev.konsist.core.const.KoModifier.PUBLIC
import com.lemonappdev.konsist.testdata.NonExistingAnnotation
import com.lemonappdev.konsist.testdata.SampleAnnotation1
import com.lemonappdev.konsist.testdata.SampleAnnotation2
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldHaveSize
import org.amshove.kluent.shouldNotBeEqualTo
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
            hasAnnotations("SampleAnnotation") shouldBeEqualTo true
            hasAnnotations("NonExistingAnnotation") shouldBeEqualTo false
            hasAnnotations("com.lemonappdev.konsist.testdata.SampleAnnotation") shouldBeEqualTo true
            hasAnnotations("com.lemonappdev.konsist.testdata.NonExistingAnnotation") shouldBeEqualTo false
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
            hasAnnotations("SampleAnnotation1") shouldBeEqualTo true
            hasAnnotations("SampleAnnotation2") shouldBeEqualTo true
            hasAnnotations("SampleAnnotation1", "SampleAnnotation1") shouldBeEqualTo true
            hasAnnotations("NonExistingAnnotation") shouldBeEqualTo false
            hasAnnotations("SampleAnnotation1", "NonExistingAnnotation") shouldBeEqualTo false
            hasAnnotations("com.lemonappdev.konsist.testdata.SampleAnnotation1") shouldBeEqualTo true
            hasAnnotations("com.lemonappdev.konsist.testdata.SampleAnnotation2") shouldBeEqualTo true
            hasAnnotations("com.lemonappdev.konsist.testdata.NonExistingAnnotation") shouldBeEqualTo false
        }
    }

    @Test
    fun `interface-has-two-annotations-with-KClass`() {
        // given
        val sut = getSnippetFile("interface-has-two-annotations-with-KClass")
            .interfaces()
            .first()

        // then
        sut.run {
            annotations shouldHaveSize 2
            hasAnnotationsOf(SampleAnnotation1::class) shouldBeEqualTo true
            hasAnnotationsOf(SampleAnnotation2::class) shouldBeEqualTo true
            hasAnnotationsOf(SampleAnnotation1::class, SampleAnnotation2::class) shouldBeEqualTo true
            hasAnnotationsOf(NonExistingAnnotation::class) shouldBeEqualTo false
            hasAnnotationsOf(SampleAnnotation1::class, NonExistingAnnotation::class) shouldBeEqualTo false
            hasAnnotationOf<SampleAnnotation1>() shouldBeEqualTo true
            hasAnnotationOf<SampleAnnotation2>() shouldBeEqualTo true
            hasAnnotationOf<NonExistingAnnotation>() shouldBeEqualTo false
        }
    }

    @Test
    fun `interface-has-modifiers`() {
        // given
        val sut = getSnippetFile("interface-has-modifiers")
            .interfaces()
            .first()

        // then
        sut.modifiers shouldBeEqualTo listOf(PUBLIC, ABSTRACT)
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
        sut.hasModifiers() shouldBeEqualTo true
    }

    @Test
    fun `interface-has-public-modifier`() {
        // given
        val sut = getSnippetFile("interface-has-public-modifier")
            .interfaces()
            .first()

        // then
        sut.run {
            hasModifiers(PUBLIC) shouldBeEqualTo true
            hasModifiers(PRIVATE) shouldBeEqualTo false
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
            hasModifiers(ABSTRACT) shouldBeEqualTo true
            hasModifiers(PUBLIC) shouldBeEqualTo true
            hasModifiers(PRIVATE) shouldBeEqualTo false
            hasModifiers(ABSTRACT, PUBLIC) shouldBeEqualTo true
            hasModifiers(PUBLIC, ABSTRACT) shouldBeEqualTo true
            hasModifiers(PRIVATE, ABSTRACT) shouldBeEqualTo false
            hasModifiers(ABSTRACT, PUBLIC, PRIVATE) shouldBeEqualTo false
            hasModifiers() shouldBeEqualTo true
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
            hasModifiers() shouldBeEqualTo false
            hasModifiers(PRIVATE) shouldBeEqualTo false
        }
    }

    @Test
    fun `interface-has-kdoc`() {
        // given
        val sut = getSnippetFile("interface-has-kdoc")
            .interfaces()
            .first()

        // then
        assertSoftly(sut) {
            koDoc shouldNotBeEqualTo null
            hasKoDoc() shouldBeEqualTo true
        }
    }

    @Test
    fun `interface-has-not-kdoc`() {
        // given
        val sut = getSnippetFile("interface-has-not-kdoc")
            .interfaces()
            .first()

        // then
        assertSoftly(sut) {
            koDoc shouldBeEqualTo null
            hasKoDoc() shouldBeEqualTo false
        }
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/kodeclaration/snippet/forinterface/", fileName)
}
