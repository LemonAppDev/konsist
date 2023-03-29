package com.lemon.konsist.core.koparameter

import com.lemon.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoParameterTest {
    @Test
    fun `class-with-one-typed-parameter`() {
        // given
        val sut = getSut("class-with-one-typed-parameter")
            .classes()
            .first()
            .primaryConstructor
            ?.parameters
            ?.first()

        // then
        sut?.type shouldBeEqualTo "SampleParameter"
    }

    @Test
    fun `class-with-one-name-parameter`() {
        // given
        val sut = getSut("class-with-one-name-parameter")
            .classes()
            .first()
            .primaryConstructor
            ?.parameters
            ?.first()

        // then
        sut?.name shouldBeEqualTo "sampleParameter"
    }

    @Test
    fun `class-one-parameter-with-default-value`() {
        // given
        val sut = getSut("class-one-parameter-with-default-value")
            .classes()
            .first()
            .primaryConstructor
            ?.parameters
            ?.first()

        // then
        sut?.hasDefaultValue shouldBeEqualTo true
    }

    @Test
    fun `class-without-parameters`() {
        // given
        val sut = getSut("class-without-parameters")
            .classes()
            .first()

        // then
        sut.primaryConstructor shouldBeEqualTo null
    }

    @Test
    fun `class-with-empty-primary-constructor`() {
        // given
        val sut = getSut("class-with-empty-primary-constructor")
            .classes()
            .first()
            .primaryConstructor
            ?.parameters

        // then
        sut?.isEmpty() shouldBeEqualTo true
    }

    @Test
    fun `function-with-primitive-default-parameter-value`() {
        // given
        val sut = getSut("function-with-primitive-default-parameter-value")
            .functions()
            .first()
            .parameters
            .first()

        // then
        sut.defaultValue shouldBeEqualTo "2"
    }

    @Test
    fun `function-with-complex-default-parameter-value`() {
        // given
        val sut = getSut("function-with-complex-default-parameter-value")
            .functions()
            .first()
            .parameters
            .first()

        // then
        sut.defaultValue shouldBeEqualTo "sampleFunction2()"
    }

    @Test
    fun `function-with-null-default-parameter-value`() {
        // given
        val sut = getSut("function-with-null-default-parameter-value")
            .functions()
            .first()
            .parameters
            .first()

        // then
        sut.defaultValue shouldBeEqualTo "null"
    }

    @Test
    fun `function-without-default-parameter-value`() {
        // given
        val sut = getSut("function-without-default-parameter-value")
            .functions()
            .first()
            .parameters
            .first()

        // then
        sut.defaultValue shouldBeEqualTo null
    }

    private fun getSut(fileName: String) = getSnippetKoScope("core/koparameter/snippet/$fileName.kt.txt")
}
