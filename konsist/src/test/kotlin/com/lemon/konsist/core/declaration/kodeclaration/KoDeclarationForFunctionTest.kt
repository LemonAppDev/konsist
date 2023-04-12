package com.lemon.konsist.core.declaration.kodeclaration

import com.lemon.konsist.TestSnippetProvider.getSnippetKoScope
import com.lemon.konsist.core.const.KoModifier.PRIVATE
import com.lemon.konsist.core.const.KoModifier.PROTECTED
import com.lemon.konsist.core.const.KoModifier.PUBLIC
import com.lemon.konsist.core.const.KoModifier.SUSPEND
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
        sut.run {
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
        sut.run {
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
        sut.run {
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
        sut.run {
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
        sut.run {
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
        sut.run {
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
        sut.run {
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
        sut.run {
            hasModifiers(PUBLIC) shouldBeEqualTo true
            hasModifiers(PRIVATE) shouldBeEqualTo false
        }
    }

    @Test
    fun `function-has-two-modifiers`() {
        // given
        val sut = getSut("function-has-two-modifiers")
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
            hasModifiers() shouldBeEqualTo true
        }
    }

    @Test
    fun `function-has-no-modifier`() {
        // given
        val sut = getSut("function-has-no-modifier")
            .functions()
            .first()

        // then
        sut.run {
            hasModifiers() shouldBeEqualTo true
            hasModifiers(PRIVATE) shouldBeEqualTo false
        }
    }

    @Test
    fun `function-reside-in-path`() {
        // given
        val sut = getSut("function-reside-in-path")
            .functions()
            .first()

        // then
        sut.run {
            resideInPath("Test/") shouldBeEqualTo true
            resideInPath("TEST/") shouldBeEqualTo true
            resideInPath("TEST/", false) shouldBeEqualTo false
            resideInPath("Main/") shouldBeEqualTo false
            resideInPath("") shouldBeEqualTo true
        }
    }

    @Test
    fun `function-reside-outside-path`() {
        // given
        val sut = getSut("function-reside-outside-path")
            .functions()
            .first()

        // then
        sut.run {
            resideOutsidePath("Main/") shouldBeEqualTo true
            resideOutsidePath("MAIN/") shouldBeEqualTo true
            resideOutsidePath("MAIN/", false) shouldBeEqualTo true
            resideOutsidePath("Test/") shouldBeEqualTo false
            resideOutsidePath("TEST/") shouldBeEqualTo false
            resideOutsidePath("TEST/", false) shouldBeEqualTo true
            resideOutsidePath("") shouldBeEqualTo false
        }
    }

    private fun getSut(fileName: String) = getSnippetKoScope("kodeclaration/snippet/forfunction/", fileName)
}
