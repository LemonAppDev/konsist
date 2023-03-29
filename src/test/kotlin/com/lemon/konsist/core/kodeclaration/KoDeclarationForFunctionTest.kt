package com.lemon.konsist.core.kodeclaration

import NonExistingAnnotation
import SampleAnnotation
import SampleAnnotation1
import SampleAnnotation2
import com.lemon.konsist.TestSnippetProvider.getSnippetKoScope
import com.lemon.konsist.core.const.Modifier
import org.amshove.kluent.shouldBe
import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldHaveSize
import org.junit.jupiter.api.Test

class KoDeclarationForFunctionTest {
    @Test
    fun `function-is-top-level`() {
        // given
        val sut = getSut("function-is-top-level")

        // then
        sut.functions(includeNested = true).first().isTopLevel shouldBe true
    }

    @Test
    fun `function-is-not-top-level`() {
        // given
        val sut = getSut("function-is-not-top-level")

        // then
        sut.functions(includeNested = true).first { it.name == "sampleNestedFunction" }.isTopLevel shouldBe false
    }

    @Test
    fun `function-without-annotation`() {
        // given
        val sut = getSut("function-without-annotation")

        // then
        sut.functions().first().annotations shouldHaveSize 0
    }

    @Test
    fun `function-with-annotation`() {
        // given
        val sut = getSut("function-with-annotation")

        // then
        sut.functions().first().apply {
            annotations shouldHaveSize 1
            hasAnnotation(SampleAnnotation::class) shouldBe true
            hasAnnotation(NonExistingAnnotation::class) shouldBe false
        }
    }

    @Test
    fun `function-with-two-annotations`() {
        // given
        val sut = getSut("function-with-two-annotations")

        // then
        sut.functions().first().apply {
            annotations shouldHaveSize 2
            hasAnnotation(SampleAnnotation1::class) shouldBe true
            hasAnnotation(SampleAnnotation2::class) shouldBe true
            hasAnnotation(NonExistingAnnotation::class) shouldBe false
        }
    }

    @Test
    fun `function-without-visibility-modifier`() {
        // given
        val sut = getSut("function-without-visibility-modifier")

        // then
        sut.functions().first().apply {
            isPublic shouldBe true
            isPrivate shouldBe false
            isProtected shouldBe false
            isInternal shouldBe false
        }
    }

    @Test
    fun `function-with-public-visibility-modifier`() {
        // given
        val sut = getSut("function-with-public-visibility-modifier")

        // then
        sut.functions().first().apply {
            isPublic shouldBe true
            isPrivate shouldBe false
            isProtected shouldBe false
            isInternal shouldBe false
        }
    }

    @Test
    fun `function-with-private-visibility-modifier`() {
        // given
        val sut = getSut("function-with-private-visibility-modifier")

        // then
        sut.functions().first().apply {
            isPublic shouldBe false
            isPrivate shouldBe true
            isProtected shouldBe false
            isInternal shouldBe false
        }
    }

    @Test
    fun `function-with-protected-visibility-modifier`() {
        // given
        val sut = getSut("function-with-protected-visibility-modifier")

        // then
        sut.functions().first().apply {
            isPublic shouldBe false
            isPrivate shouldBe false
            isProtected shouldBe true
            isInternal shouldBe false
        }
    }

    @Test
    fun `function-with-internal-visibility-modifier`() {
        // given
        val sut = getSut("function-with-internal-visibility-modifier")

        // then
        sut.functions().first().apply {
            isPublic shouldBe false
            isPrivate shouldBe false
            isProtected shouldBe false
            isInternal shouldBe true
        }
    }

    @Test
    fun `function-with-fully-qualified-name`() {
        // given
        val sut = getSut("function-with-fully-qualified-name")

        // then
        sut.functions()
            .first()
            .fullyQualifiedName shouldBeEqualTo "com.samplepackage.SampleFunction"
    }

    @Test
    fun `function-with-package`() {
        // given
        val sut = getSut("function-with-package")

        // then
        sut.functions()
            .first()
            .packageDirective shouldBeEqualTo "com.samplepackage"
    }

    @Test
    fun `function-without-package`() {
        // given
        val sut = getSut("function-without-package")

        // then
        sut.functions()
            .first()
            .packageDirective shouldBeEqualTo ""
    }

    @Test
    fun `function-with-protected-modifier`() {
        // given
        val sut = getSut("function-with-protected-modifier")

        // then
        sut.functions()
            .first()
            .hasModifiers() shouldBe true
    }

    @Test
    fun `function-with-public-modifier`() {
        // given
        val sut = getSut("function-with-public-modifier")

        // then
        sut.functions()
            .first()
            .apply {
                hasModifiers(Modifier.PUBLIC) shouldBe true
                hasModifiers(Modifier.PRIVATE) shouldBe false
            }
    }

    @Test
    fun `function-reside-in-package`() {
        // given
        val sut = getSut("function-reside-in-package")

        // then
        sut.functions()
            .first()
            .resideInAPackages("samplepackage") shouldBeEqualTo true
    }

    @Test
    fun `function-not-reside-in-package`() {
        // given
        val sut = getSut("function-not-reside-in-package")

        // then
        sut.functions()
            .first()
            .resideInAPackages("otherpackage") shouldBe false
    }

    private fun getSut(fileName: String) = getSnippetKoScope("core/kodeclaration/snippet/forfunction/$fileName.kt.txt")
}
