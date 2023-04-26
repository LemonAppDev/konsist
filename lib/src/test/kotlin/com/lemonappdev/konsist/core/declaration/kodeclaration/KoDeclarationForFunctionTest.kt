package com.lemonappdev.konsist.core.declaration.kodeclaration

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import com.lemonappdev.konsist.core.const.KoModifier.INLINE
import com.lemonappdev.konsist.core.const.KoModifier.OPEN
import com.lemonappdev.konsist.core.const.KoModifier.OPERATOR
import com.lemonappdev.konsist.core.const.KoModifier.PRIVATE
import com.lemonappdev.konsist.core.const.KoModifier.PROTECTED
import com.lemonappdev.konsist.core.const.KoModifier.PUBLIC
import com.lemonappdev.konsist.core.const.KoModifier.SUSPEND
import com.lemonappdev.konsist.testdata.NonExistingAnnotation
import com.lemonappdev.konsist.testdata.SampleAnnotation1
import com.lemonappdev.konsist.testdata.SampleAnnotation2
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldHaveSize
import org.amshove.kluent.shouldNotBeEqualTo
import org.junit.jupiter.api.Test

class KoDeclarationForFunctionTest {
    @Test
    fun `function-is-top-level`() {
        // given
        val sut = getSnippetFile("function-is-top-level")
            .functions(includeNested = true)
            .first()

        // then
        sut.isTopLevel() shouldBeEqualTo true
    }

    @Test
    fun `function-is-not-top-level`() {
        // given
        val sut = getSnippetFile("function-is-not-top-level")
            .functions(includeNested = true)
            .first { it.name == "sampleNestedFunction" }

        // then
        sut.isTopLevel() shouldBeEqualTo false
    }

    @Test
    fun `function-has-no-annotation`() {
        // given
        val sut = getSnippetFile("function-has-no-annotation")
            .functions()
            .first()

        // then
        sut.annotations shouldHaveSize 0
    }

    @Test
    fun `function-has-annotation`() {
        // given
        val sut = getSnippetFile("function-has-annotation")
            .functions()
            .first()

        // then
        sut.run {
            annotations shouldHaveSize 1
            hasAnnotations("SampleAnnotation") shouldBeEqualTo true
            hasAnnotations("NonExistingAnnotation") shouldBeEqualTo false
            hasAnnotations("com.lemonappdev.konsist.testdata.SampleAnnotation") shouldBeEqualTo true
            hasAnnotations("com.lemonappdev.konsist.testdata.NonExistingAnnotation") shouldBeEqualTo false
        }
    }

    @Test
    fun `function-has-two-annotations`() {
        // given
        val sut = getSnippetFile("function-has-two-annotations")
            .functions()
            .first()

        // then
        sut.run {
            annotations shouldHaveSize 2
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
    fun `function-has-two-annotations-with-KClass`() {
        // given
        val sut = getSnippetFile("function-has-two-annotations-with-KClass")
            .functions()
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
    fun `function-has-modifiers`() {
        // given
        val sut = getSnippetFile("function-has-modifiers")
            .functions(includeNested = true)
            .first()

        // then
        sut.modifiers shouldBeEqualTo listOf(PROTECTED, OPEN, SUSPEND, INLINE, OPERATOR)
    }

    @Test
    fun `function-has-no-visibility-modifier`() {
        // given
        val sut = getSnippetFile("function-has-no-visibility-modifier")
            .functions()
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
    fun `function-has-public-visibility-modifier`() {
        // given
        val sut = getSnippetFile("function-has-public-visibility-modifier")
            .functions(includeNested = true)
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
    fun `function-has-private-visibility-modifier`() {
        // given
        val sut = getSnippetFile("function-has-private-visibility-modifier")
            .functions(includeNested = true)
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
    fun `function-has-protected-visibility-modifier`() {
        // given
        val sut = getSnippetFile("function-has-protected-visibility-modifier")
            .functions(includeNested = true)
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
    fun `function-has-internal-visibility-modifier`() {
        // given
        val sut = getSnippetFile("function-has-internal-visibility-modifier")
            .functions(includeNested = true)
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
    fun `function-has-fully-qualified-name`() {
        // given
        val sut = getSnippetFile("function-has-fully-qualified-name")
            .functions()
            .first()

        // then
        sut.fullyQualifiedName shouldBeEqualTo "com.samplepackage.sampleFunction"
    }

    @Test
    fun `function-is-in-package`() {
        // given
        val sut = getSnippetFile("function-is-in-package")
            .functions()
            .first()

        // then
        sut.packageName shouldBeEqualTo "com.samplepackage"
    }

    @Test
    fun `function-is-not-in-package`() {
        // given
        val sut = getSnippetFile("function-is-not-in-package")
            .functions()
            .first()

        // then
        sut.packageName shouldBeEqualTo ""
    }

    @Test
    fun `function-has-protected-modifier`() {
        // given
        val sut = getSnippetFile("function-has-protected-modifier")
            .functions(includeNested = true)
            .first()

        // then
        sut.hasModifiers() shouldBeEqualTo true
    }

    @Test
    fun `function-has-public-modifier`() {
        // given
        val sut = getSnippetFile("function-has-public-modifier")
            .functions(includeNested = true)
            .first()

        // then
        sut.run {
            hasModifiers(PUBLIC) shouldBeEqualTo true
            hasModifiers(PRIVATE) shouldBeEqualTo false
        }
    }

    @Test
    fun `function-has-two-modifiers`() {
        // given
        val sut = getSnippetFile("function-has-two-modifiers")
            .functions(includeNested = true)
            .first()

        // then
        sut.run {
            hasModifiers(PROTECTED) shouldBeEqualTo true
            hasModifiers(SUSPEND) shouldBeEqualTo true
            hasModifiers(PRIVATE) shouldBeEqualTo false
            hasModifiers(PROTECTED, SUSPEND) shouldBeEqualTo true
            hasModifiers(SUSPEND, PROTECTED) shouldBeEqualTo true
            hasModifiers(PRIVATE, SUSPEND) shouldBeEqualTo false
            hasModifiers(PROTECTED, SUSPEND, PRIVATE) shouldBeEqualTo false
        }
    }

    @Test
    fun `function-has-no-modifier`() {
        // given
        val sut = getSnippetFile("function-has-no-modifier")
            .functions()
            .first()

        // then
        sut.run {
            hasModifiers() shouldBeEqualTo false
            hasModifiers(PRIVATE) shouldBeEqualTo false
        }
    }

    @Test
    fun `function-has-kdoc`() {
        // given
        val sut = getSnippetFile("function-has-kdoc")
            .functions()
            .first()

        // then
        assertSoftly(sut) {
            koDoc shouldNotBeEqualTo null
            hasKoDoc() shouldBeEqualTo true
            hasKoDocWithTags("@since") shouldBeEqualTo true
            hasKoDocWithTags("@since", "@see") shouldBeEqualTo true
            hasKoDocWithTags("@sample") shouldBeEqualTo false
            hasKoDocWithTags("@since", "@sample") shouldBeEqualTo false
            hasKoDocWithTags("@since", "@see", "sample") shouldBeEqualTo false
        }
    }

    @Test
    fun `function-has-not-kdoc`() {
        // given
        val sut = getSnippetFile("function-has-not-kdoc")
            .functions()
            .first()

        // then
        assertSoftly(sut) {
            koDoc shouldBeEqualTo null
            hasKoDoc() shouldBeEqualTo false
            hasKoDocWithTags("@since") shouldBeEqualTo false
            hasKoDocWithTags("@since", "@see") shouldBeEqualTo false
        }
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/kodeclaration/snippet/forfunction/", fileName)
}
