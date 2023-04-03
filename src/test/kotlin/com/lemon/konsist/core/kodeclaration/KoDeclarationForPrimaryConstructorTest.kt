package com.lemon.konsist.core.kodeclaration

import com.lemon.konsist.TestSnippetProvider
import com.lemon.konsist.testdata.SampleAnnotation
import com.lemon.konsist.testdata.SampleAnnotation1
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoDeclarationForPrimaryConstructorTest {
    @Test
    fun `primary-constructor`() {
        // given
        val sut = getSut("primary-constructor")
            .classes()
            .first()
            .primaryConstructor

        // then
        sut?.name shouldBeEqualTo "SampleClass"
    }

    @Test
    fun `public-primary-constructor`() {
        // given
        val sut = getSut("public-primary-constructor")
            .classes()
            .first()
            .primaryConstructor

        // then
        sut?.isPublic shouldBeEqualTo true
    }

    @Test
    fun `private-primary-constructor`() {
        // given
        val sut = getSut("private-primary-constructor")
            .classes()
            .first()
            .primaryConstructor

        // then
        sut?.isPrivate shouldBeEqualTo true
    }

    @Test
    fun `protected-primary-constructor`() {
        // given
        val sut = getSut("protected-primary-constructor")
            .classes()
            .first()
            .primaryConstructor

        // then
        sut?.isProtected shouldBeEqualTo true
    }

    @Test
    fun `internal-primary-constructor`() {
        // given
        val sut = getSut("internal-primary-constructor")
            .classes()
            .first()
            .primaryConstructor

        // then
        sut?.isInternal shouldBeEqualTo true
    }

    @Test
    fun `primary-constructor-with-annotation`() {
        // given
        val sut = getSut("primary-constructor-with-annotation")
            .classes()
            .first()
            .primaryConstructor

        // then
        sut?.let {
            it.annotations.map { annotation -> annotation.name } shouldBeEqualTo listOf("SampleAnnotation")
            it.hasAnnotation(SampleAnnotation::class) shouldBeEqualTo true
            it.hasAnnotation(SampleAnnotation1::class) shouldBeEqualTo false
        }
    }

    @Test
    fun `primary-constructor-without-annotation`() {
        // given
        val sut = getSut("primary-constructor-without-annotation")
            .classes()
            .first()
            .primaryConstructor

        // then
        sut?.let {
            it.annotations.isEmpty() shouldBeEqualTo true
            it.hasAnnotation(SampleAnnotation::class) shouldBeEqualTo false
        }
    }

    private fun getSut(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("core/kodeclaration/snippet/forprimaryconstructor/$fileName.kttxt")
}
