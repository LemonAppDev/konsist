package com.lemonappdev.konsist.core.declaration.kodeclaration

import com.lemonappdev.konsist.TestSnippetProvider
import com.lemonappdev.konsist.core.const.KoModifier.PRIVATE
import com.lemonappdev.konsist.testdata.SampleAnnotation
import com.lemonappdev.konsist.testdata.SampleAnnotation1
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoDeclarationForPrimaryConstructorTest {
    @Test
    fun `primary-constructor`() {
        // given
        val sut = getSnippetFile("primary-constructor")
            .classes()
            .first()
            .primaryConstructor

        // then
        sut?.name shouldBeEqualTo "SampleClass"
    }

    @Test
    fun `primary-constructor-has-modifier`() {
        // given
        val sut = getSnippetFile("primary-constructor-has-modifier")
            .classes()
            .first()
            .primaryConstructor

        // then
        sut?.modifiers shouldBeEqualTo listOf(PRIVATE)
    }

    @Test
    fun `primary-constructor-without-visibility-modifiers`() {
        // given
        val sut = getSnippetFile("primary-constructor-without-visibility-modifiers")
            .classes()
            .first()
            .primaryConstructor

        // then
        assertSoftly(sut) {
            it?.isPublicOrDefault() shouldBeEqualTo true
            it?.hasPublicModifier() shouldBeEqualTo false
            it?.hasPrivateModifier() shouldBeEqualTo false
            it?.hasProtectedModifier() shouldBeEqualTo false
            it?.hasInternalModifier() shouldBeEqualTo false
        }
    }

    @Test
    fun `public-primary-constructor`() {
        // given
        val sut = getSnippetFile("public-primary-constructor")
            .classes()
            .first()
            .primaryConstructor

        // then
        assertSoftly(sut) {
            it?.isPublicOrDefault() shouldBeEqualTo true
            it?.hasPublicModifier() shouldBeEqualTo true
            it?.hasPrivateModifier() shouldBeEqualTo false
            it?.hasProtectedModifier() shouldBeEqualTo false
            it?.hasInternalModifier() shouldBeEqualTo false
        }
    }

    @Test
    fun `private-primary-constructor`() {
        // given
        val sut = getSnippetFile("private-primary-constructor")
            .classes()
            .first()
            .primaryConstructor

        // then
        assertSoftly(sut) {
            it?.isPublicOrDefault() shouldBeEqualTo false
            it?.hasPublicModifier() shouldBeEqualTo false
            it?.hasPrivateModifier() shouldBeEqualTo true
            it?.hasProtectedModifier() shouldBeEqualTo false
            it?.hasInternalModifier() shouldBeEqualTo false
        }
    }

    @Test
    fun `protected-primary-constructor`() {
        // given
        val sut = getSnippetFile("protected-primary-constructor")
            .classes()
            .first()
            .primaryConstructor

        // then
        assertSoftly(sut) {
            it?.isPublicOrDefault() shouldBeEqualTo false
            it?.hasPublicModifier() shouldBeEqualTo false
            it?.hasPrivateModifier() shouldBeEqualTo false
            it?.hasProtectedModifier() shouldBeEqualTo true
            it?.hasInternalModifier() shouldBeEqualTo false
        }
    }

    @Test
    fun `internal-primary-constructor`() {
        // given
        val sut = getSnippetFile("internal-primary-constructor")
            .classes()
            .first()
            .primaryConstructor

        // then
        assertSoftly(sut) {
            it?.isPublicOrDefault() shouldBeEqualTo false
            it?.hasPublicModifier() shouldBeEqualTo false
            it?.hasPrivateModifier() shouldBeEqualTo false
            it?.hasProtectedModifier() shouldBeEqualTo false
            it?.hasInternalModifier() shouldBeEqualTo true
        }
    }

    @Test
    fun `primary-constructor-has-annotation`() {
        // given
        val sut = getSnippetFile("primary-constructor-has-annotation")
            .classes()
            .first()
            .primaryConstructor

        // then
        sut?.let {
            it.annotations.map { annotation -> annotation.name } shouldBeEqualTo listOf("SampleAnnotation")
            it.hasAnnotations("SampleAnnotation") shouldBeEqualTo true
            it.hasAnnotations("SampleAnnotation1") shouldBeEqualTo false
            it.hasAnnotations("com.lemonappdev.konsist.testdata.SampleAnnotation") shouldBeEqualTo true
            it.hasAnnotations("com.lemonappdev.konsist.testdata.SampleAnnotation1") shouldBeEqualTo false
            it.hasAnnotationOf<SampleAnnotation>() shouldBeEqualTo true
            it.hasAnnotationOf<SampleAnnotation1>() shouldBeEqualTo false
        }
    }

    @Test
    fun `primary-constructor-has-no-annotation`() {
        // given
        val sut = getSnippetFile("primary-constructor-has-no-annotation")
            .classes()
            .first()
            .primaryConstructor

        // then
        sut?.let {
            it.annotations.isEmpty() shouldBeEqualTo true
            it.hasAnnotations("SampleAnnotation") shouldBeEqualTo false
            it.hasAnnotations("com.lemonappdev.konsist.testdata.SampleAnnotation") shouldBeEqualTo false
            it.hasAnnotationOf<SampleAnnotation>() shouldBeEqualTo false
        }
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("core/declaration/kodeclaration/snippet/forprimaryconstructor/", fileName)
}
