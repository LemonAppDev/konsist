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
        sut?.type?.run {
            name shouldBeEqualTo "SampleType"
            aliasName shouldBeEqualTo null
            isTypeAlias shouldBeEqualTo false
            fullyQualifiedName shouldBeEqualTo "com.lemon.konsist.testdata.SampleType"
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
        sut?.type?.run {
            name shouldBeEqualTo "SampleType"
            aliasName shouldBeEqualTo "AliasType"
            isTypeAlias shouldBeEqualTo true
            fullyQualifiedName shouldBeEqualTo "com.lemon.konsist.testdata.SampleType"
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

    @Test
    fun `parameter-has-vararg-modifier`() {
        // given
        val sut = getSut("parameter-has-vararg-modifier")
            .classes()
            .first()
            .primaryConstructor
            ?.parameters
            ?.first()

        // then
        sut?.isVarArg shouldBeEqualTo true
    }

    @Test
    fun `parameter-has-noinline-modifier`() {
        // given
        val sut = getSut("parameter-has-noinline-modifier")
            .functions()
            .first()
            .parameters
            .first()

        // then
        sut.isNoInline shouldBeEqualTo true
    }

    @Test
    fun `parameter-has-crossinline-modifier`() {
        // given
        val sut = getSut("parameter-has-crossinline-modifier")
            .functions()
            .first()
            .parameters
            .first()

        // then
        sut.isCrossInline shouldBeEqualTo true
    }

    @Test
    fun `parameter-has-no-modifiers`() {
        // given
        val sut = getSut("parameter-has-no-modifiers")
            .classes()
            .first()
            .primaryConstructor
            ?.parameters
            ?.first()

        // then
        sut?.run {
            isVarArg shouldBeEqualTo false
            isNoInline shouldBeEqualTo false
            isCrossInline shouldBeEqualTo false
        }
    }

    private fun getSut(fileName: String) = getSnippetKoScope("koparameter/snippet/", fileName)
}
