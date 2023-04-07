package com.lemon.konsist.core.declaration.kofunction

import com.lemon.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoFunctionTest {
    @Test
    fun `function-has-operator-modifier`() {
        // given
        val sut = getSut("function-has-operator-modifier")
            .functions()
            .first()

        // then
        sut.isOperator shouldBeEqualTo true
    }

    @Test
    fun `function-has-no-operator-modifier`() {
        // given
        val sut = getSut("function-has-no-operator-modifier")
            .functions()
            .first()

        // then
        sut.isOperator shouldBeEqualTo false
    }

    @Test
    fun `function-has-inline-modifier`() {
        // given
        val sut = getSut("function-has-inline-modifier")
            .functions()
            .first()

        // then
        sut.isInline shouldBeEqualTo true
    }

    @Test
    fun `function-has-no-inline-modifier`() {
        // given
        val sut = getSut("function-has-no-inline-modifier")
            .functions()
            .first()

        // then
        sut.isInline shouldBeEqualTo false
    }

    @Test
    fun `function-return-type`() {
        // given
        val sut = getSut("function-return-type")
            .functions()
            .first()

        // then
        with(sut) {
            hasExplicitReturnType shouldBeEqualTo true
            explicitReturnType?.name shouldBeEqualTo "SampleType"
            explicitReturnType?.aliasName shouldBeEqualTo null
            explicitReturnType?.isTypeAlias shouldBeEqualTo false
            explicitReturnType?.fullyQualifiedName shouldBeEqualTo "com.lemon.konsist.testdata.SampleType"
        }
    }

    @Test
    fun `function-return-alias-type`() {
        // given
        val sut = getSut("function-return-alias-type")
            .functions()
            .first()

        // then
        with(sut) {
            hasExplicitReturnType shouldBeEqualTo true
            explicitReturnType?.name shouldBeEqualTo "SampleType"
            explicitReturnType?.aliasName shouldBeEqualTo "AliasType"
            explicitReturnType?.isTypeAlias shouldBeEqualTo true
        }
    }

    @Test
    fun `function-not-return-type`() {
        // given
        val sut = getSut("function-not-return-type")
            .functions()
            .first()

        // then
        with(sut) {
            hasExplicitReturnType shouldBeEqualTo false
            explicitReturnType?.name shouldBeEqualTo null
            explicitReturnType?.aliasName shouldBeEqualTo null
            explicitReturnType?.isTypeAlias shouldBeEqualTo null
        }
    }

    @Test
    fun `function-contains-local-property`() {
        // given
        val sut = getSut("function-contains-local-property")
            .functions()
            .first()

        // then
        sut.apply {
            containsLocalProperty("sampleLocalProperty") shouldBeEqualTo true
            localProperties().map { it.name } shouldBeEqualTo listOf("sampleLocalProperty")
        }
    }

    @Test
    fun `function-contains-local-function`() {
        // given
        val sut = getSut("function-contains-local-function")
            .functions()
            .first()

        // then
        sut.apply {
            containsLocalFunction("sampleLocalFunction") shouldBeEqualTo true
            localFunctions().map { it.name } shouldBeEqualTo listOf("sampleLocalFunction")
        }
    }

    @Test
    fun `function-contains-local-class`() {
        // given
        val sut = getSut("function-contains-local-class")
            .functions()
            .first()

        // then
        sut.apply {
            containsLocalClass("SampleClass") shouldBeEqualTo true
            localClasses().map { it.name } shouldBeEqualTo listOf("SampleClass")
        }
    }

    @Test
    fun `function-contains-local-declarations`() {
        // given
        val sut = getSut("function-contains-local-declarations")
            .functions()
            .first()

        // then
        sut
            .localDeclarations()
            .map { it.name } shouldBeEqualTo listOf(
            "sampleLocalProperty",
            "sampleLocalFunction",
            "SampleNestedClass",
        )
    }

    private fun getSut(fileName: String) = getSnippetKoScope("kofunction/snippet/", fileName)
}
