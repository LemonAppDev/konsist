package com.lemon.konsist.core.declaration.kodeclaration

import com.lemon.konsist.TestSnippetProvider.getSnippetKoScope
import com.lemon.konsist.core.const.KoModifier.OPEN
import com.lemon.konsist.core.const.KoModifier.PRIVATE
import com.lemon.konsist.core.const.KoModifier.PROTECTED
import com.lemon.konsist.core.const.KoModifier.PUBLIC
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
        val sut = getSut("class-is-top-level")
            .classes(includeNested = true)
            .first()

        // then
        sut.isTopLevel shouldBeEqualTo true
    }

    @Test
    fun `class-is-not-top-level`() {
        // given
        val sut = getSut("class-is-not-top-level")
            .classes(includeNested = true)
            .first { it.name == "SampleNestedClass" }

        // then
        sut.isTopLevel shouldBeEqualTo false
    }

    @Test
    fun `class-has-no-annotation`() {
        // given
        val sut = getSut("class-has-no-annotation")
            .classes()
            .first()

        // then
        sut.annotations shouldHaveSize 0
    }

    @Test
    fun `class-has-annotation`() {
        // given
        val sut = getSut("class-has-annotation")
            .classes()
            .first()

        // then
        sut.run {
            annotations shouldHaveSize 1
            hasAnnotation(SampleAnnotation::class) shouldBeEqualTo true
            hasAnnotation(NonExistingAnnotation::class) shouldBeEqualTo false
        }
    }

    @Test
    fun `class-has-two-annotations`() {
        // given
        val sut = getSut("class-has-two-annotations")
            .classes()
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
    fun `class-has-no-visibility-modifier`() {
        // given
        val sut = getSut("class-has-no-visibility-modifier")
            .classes()
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
    fun `class-has-public-visibility-modifier`() {
        // given
        val sut = getSut("class-has-public-visibility-modifier")
            .classes()
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
    fun `class-has-private-visibility-modifier`() {
        // given
        val sut = getSut("class-has-private-visibility-modifier")
            .classes()
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
    fun `class-has-protected-visibility-modifier`() {
        // given
        val sut = getSut("class-has-protected-visibility-modifier")
            .classes()
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
    fun `class-has-internal-visibility-modifier`() {
        // given
        val sut = getSut("class-has-internal-visibility-modifier")
            .classes()
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
    fun `class-has-fully-qualified-name`() {
        // given
        val sut = getSut("class-has-fully-qualified-name")
            .classes()
            .first()

        // then
        sut.fullyQualifiedName shouldBeEqualTo "com.samplepackage.SampleClass"
    }

    @Test
    fun `class-is-in-package`() {
        // given
        val sut = getSut("class-is-in-package")
            .classes()
            .first()

        // then
        sut.packageName shouldBeEqualTo "com.samplepackage"
    }

    @Test
    fun `class-is-not-in-package`() {
        // given
        val sut = getSut("class-is-not-in-package")
            .classes()
            .first()

        // then
        sut.packageName shouldBeEqualTo ""
    }

    @Test
    fun `class-has-protected-modifier`() {
        // given
        val sut = getSut("class-has-protected-modifier")
            .classes()
            .first()

        // then
        sut.hasModifiers() shouldBeEqualTo true
    }

    @Test
    fun `class-has-public-modifier`() {
        // given
        val sut = getSut("class-has-public-modifier")
            .classes()
            .first()

        // then
        sut.run {
            hasModifiers(PUBLIC) shouldBeEqualTo true
            hasModifiers(PRIVATE) shouldBeEqualTo false
        }
    }

    @Test
    fun `class-has-two-modifiers`() {
        // given
        val sut = getSut("class-has-two-modifiers")
            .classes()
            .first()

        // then
        sut.run {
            hasModifiers(PRIVATE) shouldBeEqualTo true
            hasModifiers(OPEN) shouldBeEqualTo true
            hasModifiers(PROTECTED) shouldBeEqualTo false
            hasModifiers(PRIVATE, OPEN) shouldBeEqualTo true
            hasModifiers(OPEN, PRIVATE) shouldBeEqualTo true
            hasModifiers(PROTECTED, OPEN) shouldBeEqualTo false
            hasModifiers(PROTECTED, OPEN, PRIVATE) shouldBeEqualTo false
            hasModifiers() shouldBeEqualTo true
        }
    }

    @Test
    fun `class-has-no-modifier`() {
        // given
        val sut = getSut("class-has-no-modifier")
            .classes()
            .first()

        // then
        sut.run {
            hasModifiers() shouldBeEqualTo true
            hasModifiers(PRIVATE) shouldBeEqualTo false
        }
    }

    private fun getSut(fileName: String) = getSnippetKoScope("kodeclaration/snippet/forclass/", fileName)
}
