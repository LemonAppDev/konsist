package com.lemon.konsist.core.declaration.kodeclaration

import com.lemon.konsist.TestSnippetProvider
import com.lemon.konsist.testdata.SampleAnnotation
import com.lemon.konsist.testdata.SampleAnnotation1
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
    fun `primary-constructor-without-visibility-modifiers`() {
        // given
        val sut = getSnippetFile("primary-constructor-without-visibility-modifiers")
            .classes()
            .first()
            .primaryConstructor

        // then
        sut?.run {
            isPublicOrDefault() shouldBeEqualTo true
            hasPublicModifier() shouldBeEqualTo false
            hasPrivateModifier() shouldBeEqualTo false
            hasProtectedModifier() shouldBeEqualTo false
            hasInternalModifier() shouldBeEqualTo false
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
        sut?.run {
            isPublicOrDefault() shouldBeEqualTo true
            hasPublicModifier() shouldBeEqualTo true
            hasPrivateModifier() shouldBeEqualTo false
            hasProtectedModifier() shouldBeEqualTo false
            hasInternalModifier() shouldBeEqualTo false
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
        sut?.run {
            isPublicOrDefault() shouldBeEqualTo false
            hasPublicModifier() shouldBeEqualTo false
            hasPrivateModifier() shouldBeEqualTo true
            hasProtectedModifier() shouldBeEqualTo false
            hasInternalModifier() shouldBeEqualTo false
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
        sut?.run {
            isPublicOrDefault() shouldBeEqualTo false
            hasPublicModifier() shouldBeEqualTo false
            hasPrivateModifier() shouldBeEqualTo false
            hasProtectedModifier() shouldBeEqualTo true
            hasInternalModifier() shouldBeEqualTo false
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
        sut?.run {
            isPublicOrDefault() shouldBeEqualTo false
            hasPublicModifier() shouldBeEqualTo false
            hasPrivateModifier() shouldBeEqualTo false
            hasProtectedModifier() shouldBeEqualTo false
            hasInternalModifier() shouldBeEqualTo true
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
            it.hasAnnotation("SampleAnnotation") shouldBeEqualTo true
            it.hasAnnotation("SampleAnnotation1") shouldBeEqualTo false
            it.hasAnnotation<SampleAnnotation>() shouldBeEqualTo true
            it.hasAnnotation<SampleAnnotation1>() shouldBeEqualTo false
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
            it.hasAnnotation("SampleAnnotation") shouldBeEqualTo false
            it.hasAnnotation<SampleAnnotation>() shouldBeEqualTo false
        }
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("core/declaration/kodeclaration/snippet/forprimaryconstructor/", fileName)
}
