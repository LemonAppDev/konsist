package com.lemonappdev.konsist.core.declaration.kodeclaration

import com.lemonappdev.konsist.TestSnippetProvider
import com.lemonappdev.konsist.api.KoModifier.PRIVATE
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldHaveSize
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
    fun `primary-constructor-has-modifiers-and-annotation-with-parameter`() {
        // given
        val sut = getSnippetFile("primary-constructor-has-modifiers-and-annotation-with-parameter")
            .classes()
            .first()
            .primaryConstructor

        // then
        sut?.modifiers shouldBeEqualTo listOf(PRIVATE)
    }

    @Test
    fun `primary-constructor-has-modifiers-and-annotation-without-parameter`() {
        // given
        val sut = getSnippetFile("primary-constructor-has-modifiers-and-annotation-without-parameter")
            .classes()
            .first()
            .primaryConstructor

        // then
        sut?.modifiers shouldBeEqualTo listOf(PRIVATE)
    }

    @Test
    fun `primary-constructor-has-modifiers-and-annotations`() {
        // given
        val sut = getSnippetFile("primary-constructor-has-modifiers-and-annotations")
            .classes()
            .first()
            .primaryConstructor

        // then
        sut?.modifiers shouldBeEqualTo listOf(PRIVATE)
    }

    @Test
    fun `primary-constructor-has-two-annotations`() {
        // given
        val sut = getSnippetFile("primary-constructor-two-has-annotations")
            .classes()
            .first()
            .primaryConstructor

        // then
        sut?.let {
            it.annotations shouldHaveSize 2
            it.hasAnnotations("SampleAnnotation1") shouldBeEqualTo true
            it.hasAnnotations("SampleAnnotation2") shouldBeEqualTo true
            it.hasAnnotations("SampleAnnotation1", "SampleAnnotation1") shouldBeEqualTo true
            it.hasAnnotations("NonExistingAnnotation") shouldBeEqualTo false
            it.hasAnnotations("SampleAnnotation1", "NonExistingAnnotation") shouldBeEqualTo false
            it.hasAnnotations("com.lemonappdev.konsist.testdata.SampleAnnotation1") shouldBeEqualTo true
            it.hasAnnotations("com.lemonappdev.konsist.testdata.SampleAnnotation2") shouldBeEqualTo true
            it.hasAnnotations("com.lemonappdev.konsist.testdata.NonExistingAnnotation") shouldBeEqualTo false
        }
    }

    @Test
    fun `primary-constructor-two-has-annotations-of-kclass`() {
        // given
        val sut = getSnippetFile("primary-constructor-two-has-annotations-of-kclass")
            .classes()
            .first()
            .primaryConstructor

        // then
        sut?.let {
            it.annotations shouldHaveSize 2
            it.hasAnnotations("SampleAnnotation1") shouldBeEqualTo true
            it.hasAnnotations("SampleAnnotation2") shouldBeEqualTo true
            it.hasAnnotations("SampleAnnotation1", "SampleAnnotation1") shouldBeEqualTo true
            it.hasAnnotations("NonExistingAnnotation") shouldBeEqualTo false
            it.hasAnnotations("SampleAnnotation1", "NonExistingAnnotation") shouldBeEqualTo false
            it.hasAnnotations("com.lemonappdev.konsist.testdata.SampleAnnotation1") shouldBeEqualTo true
            it.hasAnnotations("com.lemonappdev.konsist.testdata.SampleAnnotation2") shouldBeEqualTo true
            it.hasAnnotations("com.lemonappdev.konsist.testdata.NonExistingAnnotation") shouldBeEqualTo false
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
        }
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("core/declaration/kodeclaration/snippet/forprimaryconstructor/", fileName)
}
