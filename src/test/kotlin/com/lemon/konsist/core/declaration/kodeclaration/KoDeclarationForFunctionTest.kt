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
    fun `function-has-no-annotation`() {
        // given
        val sut = getSut("function-has-no-annotation")
            .functions()
            .first()

        // then
        sut.annotations shouldHaveSize 0
    }

    @Test
    fun `function-has-annotation`() {
        // given
        val sut = getSut("function-has-annotation")
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
    fun `function-has-two-annotations`() {
        // given
        val sut = getSut("function-has-two-annotations")
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
    fun `function-has-no-visibility-modifier`() {
        // given
        val sut = getSut("function-has-no-visibility-modifier")
            .functions()
            .first()

        // then
        with(sut) {
            isPublicOrDefault shouldBeEqualTo true
            isPublic shouldBeEqualTo false
            isPrivate shouldBeEqualTo false
            isProtected shouldBeEqualTo false
            isInternal shouldBeEqualTo false
        }
    }

    @Test
    fun `function-has-public-visibility-modifier`() {
        // given
        val sut = getSut("function-has-public-visibility-modifier")
            .functions(includeNested = true)
            .first()

        // then
        with(sut) {
            isPublicOrDefault shouldBeEqualTo true
            isPublic shouldBeEqualTo true
            isPrivate shouldBeEqualTo false
            isProtected shouldBeEqualTo false
            isInternal shouldBeEqualTo false
        }
    }

    @Test
    fun `function-has-private-visibility-modifier`() {
        // given
        val sut = getSut("function-has-private-visibility-modifier")
            .functions(includeNested = true)
            .first()

        // then
        with(sut) {
            isPublicOrDefault shouldBeEqualTo false
            isPublic shouldBeEqualTo false
            isPrivate shouldBeEqualTo true
            isProtected shouldBeEqualTo false
            isInternal shouldBeEqualTo false
        }
    }

    @Test
    fun `function-has-protected-visibility-modifier`() {
        // given
        val sut = getSut("function-has-protected-visibility-modifier")
            .functions(includeNested = true)
            .first()

        // then
        with(sut) {
            isPublicOrDefault shouldBeEqualTo false
            isPublic shouldBeEqualTo false
            isPrivate shouldBeEqualTo false
            isProtected shouldBeEqualTo true
            isInternal shouldBeEqualTo false
        }
    }

    @Test
    fun `function-has-internal-visibility-modifier`() {
        // given
        val sut = getSut("function-has-internal-visibility-modifier")
            .functions(includeNested = true)
            .first()

        // then
        with(sut) {
            isPublicOrDefault shouldBeEqualTo false
            isPublic shouldBeEqualTo false
            isPrivate shouldBeEqualTo false
            isProtected shouldBeEqualTo false
            isInternal shouldBeEqualTo true
        }
    }

    @Test
    fun `function-has-fully-qualified-name`() {
        // given
        val sut = getSut("function-has-fully-qualified-name")
            .functions()
            .first()

        // then
        sut.fullyQualifiedName shouldBeEqualTo "com.samplepackage.sampleFunction"
    }

    @Test
    fun `function-is-in-package`() {
        // given
        val sut = getSut("function-is-in-package")
            .functions()
            .first()

        // then
        sut.packageName shouldBeEqualTo "com.samplepackage"
    }

    @Test
    fun `function-is-not-in-package`() {
        // given
        val sut = getSut("function-is-not-in-package")
            .functions()
            .first()

        // then
        sut.packageName shouldBeEqualTo ""
    }

    @Test
    fun `function-has-protected-modifier`() {
        // given
        val sut = getSut("function-has-protected-modifier")
            .functions(includeNested = true)
            .first()

        // then
        sut.hasModifiers() shouldBeEqualTo true
    }

    @Test
    fun `function-has-public-modifier`() {
        // given
        val sut = getSut("function-has-public-modifier")
            .functions(includeNested = true)
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
