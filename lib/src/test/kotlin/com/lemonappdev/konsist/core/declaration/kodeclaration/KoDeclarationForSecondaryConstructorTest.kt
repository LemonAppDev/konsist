package com.lemonappdev.konsist.core.declaration.kodeclaration

import com.lemonappdev.konsist.TestSnippetProvider
import com.lemonappdev.konsist.core.const.KoModifier.PRIVATE
import com.lemonappdev.konsist.testdata.SampleAnnotation
import com.lemonappdev.konsist.testdata.SampleAnnotation1
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoDeclarationForSecondaryConstructorTest {
    @Test
    fun `secondary-constructor-has-modifier`() {
        // given
        val sut = getSnippetFile("secondary-constructor-has-modifier")
            .classes()
            .first()
            .secondaryConstructors
            .first()

        // then
        sut.modifiers shouldBeEqualTo listOf(PRIVATE)
    }

    @Test
    fun `secondary-constructor-without-visibility-modifiers`() {
        // given
        val sut = getSnippetFile("secondary-constructor-without-visibility-modifiers")
            .classes()
            .first()
            .secondaryConstructors
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
    fun `public-secondary-constructor`() {
        // given
        val sut = getSnippetFile("public-secondary-constructor")
            .classes()
            .first()
            .secondaryConstructors
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
    fun `private-secondary-constructor`() {
        // given
        val sut = getSnippetFile("private-secondary-constructor")
            .classes()
            .first()
            .secondaryConstructors
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
    fun `protected-secondary-constructor`() {
        // given
        val sut = getSnippetFile("protected-secondary-constructor")
            .classes()
            .first()
            .secondaryConstructors
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
    fun `internal-secondary-constructor`() {
        // given
        val sut = getSnippetFile("internal-secondary-constructor")
            .classes()
            .first()
            .secondaryConstructors
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
    fun `secondary-constructor`() {
        // given
        val sut = getSnippetFile("secondary-constructor")
            .classes()
            .first()
            .secondaryConstructors
            .first()

        // then
        sut.name shouldBeEqualTo "SampleClass"
    }

    @Test
    fun `secondary-constructor-has-annotation`() {
        // given
        val sut = getSnippetFile("secondary-constructor-has-annotation")
            .classes()
            .first()
            .secondaryConstructors
            .first()

        // then
        sut.run {
            annotations.map { it.name } shouldBeEqualTo listOf("SampleAnnotation")
            hasAnnotation("SampleAnnotation") shouldBeEqualTo true
            hasAnnotation("SampleAnnotation1") shouldBeEqualTo false
            hasAnnotation("com.lemonappdev.konsist.testdata.SampleAnnotation") shouldBeEqualTo true
            hasAnnotation("com.lemonappdev.konsist.testdata.SampleAnnotation1") shouldBeEqualTo false
            hasAnnotation<SampleAnnotation>() shouldBeEqualTo true
            hasAnnotation<SampleAnnotation1>() shouldBeEqualTo false
        }
    }

    @Test
    fun `secondary-constructor-has-no-annotation`() {
        // given
        val sut = getSnippetFile("secondary-constructor-has-no-annotation")
            .classes()
            .first()
            .secondaryConstructors
            .first()

        // then
        sut.run {
            annotations.isEmpty() shouldBeEqualTo true
            hasAnnotation("SampleAnnotation") shouldBeEqualTo false
            hasAnnotation("com.lemonappdev.konsist.testdata.SampleAnnotation") shouldBeEqualTo false
            hasAnnotation<SampleAnnotation>() shouldBeEqualTo false
        }
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("core/declaration/kodeclaration/snippet/forsecondaryconstructor/", fileName)
}
