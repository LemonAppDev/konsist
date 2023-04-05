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

class KoDeclarationForFunctionTest {
    @Test
    fun `function-is-top-level`() {
        // given
        val sut = getSut("function-is-top-level")
            .functions(includeNested = true)
            .first()

        // then
        sut.isTopLevel shouldBeEqualTo true
    }

    @Test
    fun `function-is-not-top-level`() {
        // given
        val sut = getSut("function-is-not-top-level")
            .functions(includeNested = true)
            .first { it.name == "sampleNestedFunction" }

        // then
        sut.isTopLevel shouldBeEqualTo false
    }

    @Test
    fun `function-without-annotation`() {
        // given
        val sut = getSut("function-without-annotation")
            .functions()
            .first()

        // then
        sut.annotations shouldHaveSize 0
    }

    @Test
    fun `function-with-annotation`() {
        // given
        val sut = getSut("function-with-annotation")
            .functions()
            .first()

        // then
        with(sut) {
            annotations shouldHaveSize 1
            hasAnnotation(SampleAnnotation::class) shouldBeEqualTo true
            hasAnnotation(NonExistingAnnotation::class) shouldBeEqualTo false
        }
    }

    @Test
    fun `function-with-two-annotations`() {
        // given
        val sut = getSut("function-with-two-annotations")
            .functions()
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
    fun `function-without-visibility-modifier`() {
        // given
        val sut = getSut("function-without-visibility-modifier")
            .functions()
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
    fun `function-with-public-visibility-modifier`() {
        // given
        val sut = getSut("function-with-public-visibility-modifier")
            .functions()
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
    fun `function-with-private-visibility-modifier`() {
        // given
        val sut = getSut("function-with-private-visibility-modifier")
            .functions()
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
    fun `function-with-protected-visibility-modifier`() {
        // given
        val sut = getSut("function-with-protected-visibility-modifier")
            .functions()
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
    fun `function-with-internal-visibility-modifier`() {
        // given
        val sut = getSut("function-with-internal-visibility-modifier")
            .functions()
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
    fun `function-with-fully-qualified-name`() {
        // given
        val sut = getSut("function-with-fully-qualified-name")
            .functions()
            .first()

        // then
        sut.fullyQualifiedName shouldBeEqualTo "com.samplepackage.sampleFunction"
    }

    @Test
    fun `function-with-package`() {
        // given
        val sut = getSut("function-with-package")
            .functions()
            .first()

        // then
        sut.packageName shouldBeEqualTo "com.samplepackage"
    }

    @Test
    fun `function-without-package`() {
        // given
        val sut = getSut("function-without-package")
            .functions()
            .first()

        // then
        sut.packageName shouldBeEqualTo ""
    }

    @Test
    fun `function-with-protected-modifier`() {
        // given
        val sut = getSut("function-with-protected-modifier")
            .functions()
            .first()

        // then
        sut.hasModifiers() shouldBeEqualTo true
    }

    @Test
    fun `function-with-public-modifier`() {
        // given
        val sut = getSut("function-with-public-modifier")
            .functions()
            .first()

        // then
        with(sut) {
            hasModifiers(KoModifier.PUBLIC) shouldBeEqualTo true
            hasModifiers(KoModifier.PRIVATE) shouldBeEqualTo false
        }
    }

    @Test
    fun `function-reside-in-package`() {
        // given
        val sut = getSut("function-reside-in-package")
            .functions()
            .first()

        // then
        sut.resideInAPackages("samplepackage") shouldBeEqualTo true
    }

    @Test
    fun `function-not-reside-in-package`() {
        // given
        val sut = getSut("function-not-reside-in-package")
            .functions()
            .first()

        // then
        sut.resideInAPackages("otherpackage") shouldBeEqualTo false
    }

    private fun getSut(fileName: String) = getSnippetKoScope("kodeclaration/snippet/forfunction/$fileName.kttxt")
}
