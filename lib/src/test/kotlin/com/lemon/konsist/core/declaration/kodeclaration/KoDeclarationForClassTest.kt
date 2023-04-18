package com.lemon.konsist.core.declaration.kodeclaration

import com.lemon.konsist.TestSnippetProvider.getSnippetKoScope
import com.lemon.konsist.testdata.NonExistingAnnotation
import com.lemon.konsist.testdata.SampleAnnotation
import com.lemon.konsist.testdata.SampleAnnotation1
import com.lemon.konsist.testdata.SampleAnnotation2
import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldHaveSize
import org.junit.jupiter.api.Test

class KoDeclarationForClassTest {
    @Test
    fun `class-is-top-level`() {
        // given
        val sut = getSnippetFile("class-is-top-level")
            .classes(includeNested = true)
            .first()

        // then
        sut.isTopLevel() shouldBeEqualTo true
    }

    @Test
    fun `class-is-not-top-level`() {
        // given
        val sut = getSnippetFile("class-is-not-top-level")
            .classes(includeNested = true)
            .first { it.name == "SampleNestedClass" }

        // then
        sut.isTopLevel() shouldBeEqualTo false
    }

    @Test
    fun `class-has-no-annotation`() {
        // given
        val sut = getSnippetFile("class-has-no-annotation")
            .classes()
            .first()

        // then
        sut.annotations shouldHaveSize 0
    }

    @Test
    fun `class-has-annotation`() {
        // given
        val sut = getSnippetFile("class-has-annotation")
            .classes()
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
    fun `class-has-two-annotations`() {
        // given
        val sut = getSnippetFile("class-has-two-annotations")
            .classes()
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
    fun `class-has-no-visibility-modifier`() {
        // given
        val sut = getSnippetFile("class-has-no-visibility-modifier")
            .classes()
            .first()

        // then
        sut.run {
            isPublicOrDefault() shouldBeEqualTo true
            hasInternalModifier() shouldBeEqualTo false
            hasInternalModifier() shouldBeEqualTo false
            hasInternalModifier() shouldBeEqualTo false
            hasInternalModifier() shouldBeEqualTo false
        }
    }

    @Test
    fun `class-has-public-visibility-modifier`() {
        // given
        val sut = getSnippetFile("class-has-public-visibility-modifier")
            .classes()
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
    fun `class-has-private-visibility-modifier`() {
        // given
        val sut = getSnippetFile("class-has-private-visibility-modifier")
            .classes()
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
    fun `class-has-protected-visibility-modifier`() {
        // given
        val sut = getSnippetFile("class-has-protected-visibility-modifier")
            .classes()
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
    fun `class-has-internal-visibility-modifier`() {
        // given
        val sut = getSnippetFile("class-has-internal-visibility-modifier")
            .classes()
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
    fun `class-has-fully-qualified-name`() {
        // given
        val sut = getSnippetFile("class-has-fully-qualified-name")
            .classes()
            .first()

        // then
        sut.fullyQualifiedName shouldBeEqualTo "com.samplepackage.SampleClass"
    }

    @Test
    fun `class-is-in-package`() {
        // given
        val sut = getSnippetFile("class-is-in-package")
            .classes()
            .first()

        // then
        sut.packageName shouldBeEqualTo "com.samplepackage"
    }

    @Test
    fun `class-is-not-in-package`() {
        // given
        val sut = getSnippetFile("class-is-not-in-package")
            .classes()
            .first()

        // then
        sut.packageName shouldBeEqualTo ""
    }

    @Test
    fun `class-has-protected-modifier`() {
        // given
        val sut = getSnippetFile("class-has-protected-modifier")
            .classes()
            .first()

        // then
        sut.hasKoModifiers() shouldBeEqualTo true
    }

    @Test
    fun `class-has-public-modifier`() {
        // given
        val sut = getSnippetFile("class-has-public-modifier")
            .classes()
            .first()

        // then
        sut.run {
            hasModifiers("public") shouldBeEqualTo true
            hasModifiers("private") shouldBeEqualTo false
        }
    }

    @Test
    fun `class-has-two-modifiers`() {
        // given
        val sut = getSnippetFile("class-has-two-modifiers")
            .classes()
            .first()

        // then
        sut.run {
            hasModifiers("private") shouldBeEqualTo true
            hasModifiers("open") shouldBeEqualTo true
            hasModifiers("protected") shouldBeEqualTo false
            hasModifiers("private", "open") shouldBeEqualTo true
            hasModifiers("open", "private") shouldBeEqualTo true
            hasModifiers("protected", "open") shouldBeEqualTo false
            hasModifiers("protected", "open", "private") shouldBeEqualTo false
        }
    }

    @Test
    fun `class-has-no-modifier`() {
        // given
        val sut = getSnippetFile("class-has-no-modifier")
            .classes()
            .first()

        // then
        sut.hasModifiers("private") shouldBeEqualTo false
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/kodeclaration/snippet/forclass/", fileName)
}
