package com.lemon.konsist.core.kodeclaration

import com.lemon.konsist.TestSnippetProvider.getSnippetKoScope
import com.lemon.konsist.core.const.KoModifier.PRIVATE
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
    fun `class-without-annotation`() {
        // given
        val sut = getSut("class-without-annotation")
            .classes()
            .first()

        // then
        sut.annotations shouldHaveSize 0
    }

    @Test
    fun `class-with-annotation`() {
        // given
        val sut = getSut("class-with-annotation")
            .classes()
            .first()

        // then
        sut.apply {
            annotations shouldHaveSize 1
            hasAnnotation(SampleAnnotation::class) shouldBeEqualTo true
            hasAnnotation(NonExistingAnnotation::class) shouldBeEqualTo false
        }
    }

    @Test
    fun `class-with-two-annotations`() {
        // given
        val sut = getSut("class-with-two-annotations")
            .classes()
            .first()

        // then
        sut.apply {
            annotations shouldHaveSize 2
            hasAnnotation(SampleAnnotation1::class) shouldBeEqualTo true
            hasAnnotation(SampleAnnotation2::class) shouldBeEqualTo true
            hasAnnotation(NonExistingAnnotation::class) shouldBeEqualTo false
        }
    }

    @Test
    fun `class-without-visibility-modifier`() {
        // given
        val sut = getSut("class-without-visibility-modifier")
            .classes()
            .first()

        // then
        sut.apply {
            isPublic shouldBeEqualTo true
            isPrivate shouldBeEqualTo false
            isProtected shouldBeEqualTo false
            isInternal shouldBeEqualTo false
        }
    }

    @Test
    fun `class-with-public-visibility-modifier`() {
        // given
        val sut = getSut("class-with-public-visibility-modifier")
            .classes()
            .first()

        // then
        sut.apply {
            isPublic shouldBeEqualTo true
            isPrivate shouldBeEqualTo false
            isProtected shouldBeEqualTo false
            isInternal shouldBeEqualTo false
        }
    }

    @Test
    fun `class-with-private-visibility-modifier`() {
        // given
        val sut = getSut("class-with-private-visibility-modifier")
            .classes()
            .first()

        // then
        sut.apply {
            isPublic shouldBeEqualTo false
            isPrivate shouldBeEqualTo true
            isProtected shouldBeEqualTo false
            isInternal shouldBeEqualTo false
        }
    }

    @Test
    fun `class-with-protected-visibility-modifier`() {
        // given
        val sut = getSut("class-with-protected-visibility-modifier")
            .classes()
            .first()

        // then
        sut.apply {
            isPublic shouldBeEqualTo false
            isPrivate shouldBeEqualTo false
            isProtected shouldBeEqualTo true
            isInternal shouldBeEqualTo false
        }
    }

    @Test
    fun `class-with-internal-visibility-modifier`() {
        // given
        val sut = getSut("class-with-internal-visibility-modifier")
            .classes()
            .first()

        // then
        sut.apply {
            isPublic shouldBeEqualTo false
            isPrivate shouldBeEqualTo false
            isProtected shouldBeEqualTo false
            isInternal shouldBeEqualTo true
        }
    }

    @Test
    fun `class-with-fully-qualified-name`() {
        // given
        val sut = getSut("class-with-fully-qualified-name")
            .classes()
            .first()

        // then
        sut.fullyQualifiedName shouldBeEqualTo "com.samplepackage.SampleClass"
    }

    @Test
    fun `class-with-package`() {
        // given
        val sut = getSut("class-with-package")
            .classes()
            .first()

        // then
        sut.packageDirective shouldBeEqualTo "com.samplepackage"
    }

    @Test
    fun `class-without-package`() {
        // given
        val sut = getSut("class-without-package")
            .classes()
            .first()

        // then
        sut.packageDirective shouldBeEqualTo ""
    }

    @Test
    fun `class-with-protected-modifier`() {
        // given
        val sut = getSut("class-with-protected-modifier")
            .classes()
            .first()

        // then
        sut.hasModifiers() shouldBeEqualTo true
    }

    @Test
    fun `class-with-public-modifier`() {
        // given
        val sut = getSut("class-with-public-modifier")
            .classes()
            .first()

        // then
        sut.apply {
            hasModifiers(PUBLIC) shouldBeEqualTo true
            hasModifiers(PRIVATE) shouldBeEqualTo false
        }
    }

    @Test
    fun `class-reside-in-package`() {
        // given
        val sut = getSut("class-reside-in-package")
            .classes()
            .first()

        // then
        sut.resideInAPackages("samplepackage") shouldBeEqualTo true
    }

    @Test
    fun `class-not-reside-in-package`() {
        // given
        val sut = getSut("class-not-reside-in-package")
            .classes()
            .first()

        // then
        sut.resideInAPackages("otherpackage") shouldBeEqualTo false
    }

    private fun getSut(fileName: String) = getSnippetKoScope("core/kodeclaration/snippet/forclass/$fileName.kt.txt")
}
