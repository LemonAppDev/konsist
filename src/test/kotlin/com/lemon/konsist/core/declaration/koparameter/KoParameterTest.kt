package com.lemon.konsist.core.declaration.koparameter

import com.lemon.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoParameterTest {
    @Test
    fun `class-has-complex-default-parameter-value`() {
        // given
        val sut = getSut("class-has-complex-default-parameter-value")
            .classes()
            .first()
            .primaryConstructor
            ?.parameters
            ?.first()

        // then
        with(sut?.explicitType) {
            this?.name shouldBeEqualTo "SampleType"
            this?.aliasName shouldBeEqualTo null
            this?.isTypeAlias shouldBeEqualTo false
            this?.fullyQualifiedName shouldBeEqualTo "com.lemon.konsist.testdata.SampleType"
        }
    }

    @Test
    fun `class-has-one-parameter-with-alias-type`() {
        // given
        val sut = getSut("class-has-one-parameter-with-alias-type")
            .classes()
            .first()
            .primaryConstructor
            ?.parameters
            ?.first()

        // then
        with(sut?.explicitType) {
            this?.name shouldBeEqualTo "SampleType"
            this?.aliasName shouldBeEqualTo "AliasType"
            this?.isTypeAlias shouldBeEqualTo true
            this?.fullyQualifiedName shouldBeEqualTo "com.lemon.konsist.testdata.SampleType"
        }
    }

    @Test
    fun `class-has-primitive-default-parameter-value`() {
        // given
        val sut = getSut("class-has-primitive-default-parameter-value")
            .classes()
            .first()
            .primaryConstructor
            ?.parameters
            ?.first()

        // then
        sut?.run {
            hasDefaultValue shouldBeEqualTo true
            name shouldBeEqualTo "sampleParameter"
        }
    }

    @Test
    fun `class-has-no-parameters`() {
        // given
        val sut = getSut("class-has-no-parameters")
            .classes()
            .first()

        // then
        sut.primaryConstructor shouldBeEqualTo null
    }

    @Test
    fun `class-has-empty-primary-constructor`() {
        // given
        val sut = getSut("class-has-empty-primary-constructor")
            .classes()
            .first()
            .primaryConstructor
            ?.parameters

        // then
        sut?.isEmpty() shouldBeEqualTo true
    }

    @Test
    fun `function-has-primitive-default-parameter-value`() {
        // given
        val sut = getSut("function-has-primitive-default-parameter-value")
            .functions()
            .first()
            .parameters
            .first()

        // then
        sut.defaultValue shouldBeEqualTo "2"
    }

    @Test
    fun `function-has-complex-default-parameter-value`() {
        // given
        val sut = getSut("function-has-complex-default-parameter-value")
            .functions()
            .first()
            .parameters
            .first()

        // then
        sut.defaultValue shouldBeEqualTo "SampleType()"
    }

    @Test
    fun `function-has-null-default-parameter-value`() {
        // given
        val sut = getSut("function-has-null-default-parameter-value")
            .functions()
            .first()
            .parameters
            .first()

        // then
        sut.defaultValue shouldBeEqualTo "null"
    }

    @Test
    fun `function-has-no-default-parameter-value`() {
        // given
        val sut = getSut("function-has-no-default-parameter-value")
            .functions()
            .first()
            .parameters
            .first()

        // then
        sut.defaultValue shouldBeEqualTo null
    }

    private fun getSut(fileName: String) = getSnippetKoScope("koparameter/snippet/$fileName.kttxt")
}
