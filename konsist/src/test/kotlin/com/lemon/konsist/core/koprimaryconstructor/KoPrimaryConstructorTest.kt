package com.lemon.konsist.core.koprimaryconstructor

import com.lemon.konsist.TestSnippetProvider
import com.lemon.konsist.testdata.SampleAnnotation
import com.lemon.konsist.testdata.SampleAnnotation1
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoPrimaryConstructorTest {
    @Test
    fun `class-with-primary-constructor`() {
        // given
        val sut = getSut("class-with-primary-constructor")
            .classes()
            .first()
            .primaryConstructor

        // then
        sut?.name shouldBeEqualTo "SampleClass"
    }

    @Test
    fun `class-with-public-primary-constructor`() {
        // given
        val sut = getSut("class-with-public-primary-constructor")
            .classes()
            .first()
            .primaryConstructor

        // then
        sut?.isPublic shouldBeEqualTo true
    }

    @Test
    fun `class-with-private-primary-constructor`() {
        // given
        val sut = getSut("class-with-private-primary-constructor")
            .classes()
            .first()
            .primaryConstructor

        // then
        sut?.isPrivate shouldBeEqualTo true
    }

    @Test
    fun `class-with-protected-primary-constructor`() {
        // given
        val sut = getSut("class-with-protected-primary-constructor")
            .classes()
            .first()
            .primaryConstructor

        // then
        sut?.isProtected shouldBeEqualTo true
    }

    @Test
    fun `class-with-internal-primary-constructor`() {
        // given
        val sut = getSut("class-with-internal-primary-constructor")
            .classes()
            .first()
            .primaryConstructor

        // then
        sut?.isInternal shouldBeEqualTo true
    }

    @Test
    fun `class-with-parameter-in-primary-constructor`() {
        // given
        val sut = getSut("class-with-parameter-in-primary-constructor")
            .classes()
            .first()
            .primaryConstructor
            ?.parameters

        // then
        sut?.map { it.name } shouldBeEqualTo listOf("sampleParameter")
    }

    @Test
    fun `class-without-parameter-in-primary-constructor`() {
        // given
        val sut = getSut("class-without-parameter-in-primary-constructor")
            .classes()
            .first()
            .primaryConstructor
            ?.parameters

        // then
        sut?.isEmpty() shouldBeEqualTo true
    }

    @Test
    fun `class-with-primary-constructor-has-parameter-named`() {
        // given
        val sut = getSut("class-with-primary-constructor-has-parameter-named")
            .classes()
            .first()
            .primaryConstructor

        // then
        sut?.hasParameterNamed("sampleName") shouldBeEqualTo true
    }

    @Test
    fun `class-with-primary-constructor-has-not-parameter-named`() {
        // given
        val sut = getSut("class-with-primary-constructor-has-not-parameter-named")
            .classes()
            .first()
            .primaryConstructor

        // then
        sut?.hasParameterNamed("otherName") shouldBeEqualTo false
    }

    @Test
    fun `class-with-primary-constructor-with-annotation`() {
        // given
        val sut = getSut("class-with-primary-constructor-with-annotation")
            .classes()
            .first()
            .primaryConstructor

        // then
        sut?.apply {
            annotations.map { it.name } shouldBeEqualTo listOf("SampleAnnotation")
            hasAnnotation(SampleAnnotation::class) shouldBeEqualTo true
            hasAnnotation(SampleAnnotation1::class) shouldBeEqualTo false
        }
    }

    @Test
    fun `class-with-primary-constructor-without-annotation`() {
        // given
        val sut = getSut("class-with-primary-constructor-without-annotation")
            .classes()
            .first()
            .primaryConstructor

        // then
        sut?.apply {
            annotations.isEmpty() shouldBeEqualTo true
            hasAnnotation(SampleAnnotation::class) shouldBeEqualTo false
        }
    }

    private fun getSut(fileName: String) = TestSnippetProvider.getSnippetKoScope("core/koprimaryconstructor/snippet/$fileName.kt.txt")
}
