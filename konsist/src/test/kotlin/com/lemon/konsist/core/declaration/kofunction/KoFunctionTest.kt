package com.lemon.konsist.core.declaration.kofunction

import com.lemon.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoFunctionTest {
    @Test
    fun `function-with-operator-modifier`() {
        // given
        val sut = getSut("function-with-operator-modifier")
            .functions()
            .first()

        // then
        sut.isOperator shouldBeEqualTo true
    }

    @Test
    fun `function-without-operator-modifier`() {
        // given
        val sut = getSut("function-without-operator-modifier")
            .functions()
            .first()

        // then
        sut.isOperator shouldBeEqualTo false
    }

    @Test
    fun `function-with-inline-modifier`() {
        // given
        val sut = getSut("function-with-inline-modifier")
            .functions()
            .first()

        // then
        sut.isInline shouldBeEqualTo true
    }

    @Test
    fun `function-without-inline-modifier`() {
        // given
        val sut = getSut("function-without-inline-modifier")
            .functions()
            .first()

        // then
        sut.isInline shouldBeEqualTo false
    }

    @Test
    fun `function-with-return-type`() {
        // given
        val sut = getSut("function-with-return-type")
            .functions()
            .first()

        // then
        with(sut) {
            hasExplicitReturnType shouldBeEqualTo true
            getExplicitReturnType shouldBeEqualTo "SampleType"
        }
    }

    @Test
    fun `function-without-return-type`() {
        // given
        val sut = getSut("function-without-return-type")
            .functions()
            .first()

        // then
        sut.hasExplicitReturnType shouldBeEqualTo false
    }

    @Test
    fun `function-with-local-property`() {
        // given
        val sut = getSut("function-with-local-property")
            .functions()
            .first()

        // then
        sut.apply {
            containsLocalProperty("sampleLocalProperty") shouldBeEqualTo true
            localProperties().map { it.name } shouldBeEqualTo listOf("sampleLocalProperty")
        }
    }

    @Test
    fun `function-with-local-function`() {
        // given
        val sut = getSut("function-with-local-function")
            .functions()
            .first()

        // then
        sut.apply {
            containsLocalFunction("sampleLocalFunction") shouldBeEqualTo true
            localFunctions().map { it.name } shouldBeEqualTo listOf("sampleLocalFunction")
        }
    }

    @Test
    fun `function-with-local-class`() {
        // given
        val sut = getSut("function-with-local-class")
            .functions()
            .first()

        // then
        sut.apply {
            containsLocalClass("SampleClass") shouldBeEqualTo true
            localClasses().map { it.name } shouldBeEqualTo listOf("SampleClass")
        }
    }

    @Test
    fun `function-with-local-declarations`() {
        // given
        val sut = getSut("function-with-local-declarations")
            .functions()
            .first()

        // then
        sut
            .localDeclarations()
            .map { it.name } shouldBeEqualTo listOf(
            "sampleLocalProperty",
            "sampleLocalFunction",
            "SampleClass",
        )
    }

    private fun getSut(fileName: String) = getSnippetKoScope("kofunction/snippet/$fileName.kttxt")
}
