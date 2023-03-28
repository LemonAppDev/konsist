package com.lemon.konsist.core.kofunction

import com.lemon.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoFunctionTest {
    @Test
    fun `function-with-operator-modifier`() {
        // given
        val sut = getSut("function-with-operator-modifier")

        // then
        sut.functions().first().isOperator shouldBeEqualTo true
    }

    @Test
    fun `function-without-operator-modifier`() {
        // given
        val sut = getSut("function-without-operator-modifier")

        // then
        sut.functions().first().isOperator shouldBeEqualTo false
    }

    @Test
    fun `function-with-inline-modifier`() {
        // given
        val sut = getSut("function-with-inline-modifier")

        // then
        sut.functions().first().isInline shouldBeEqualTo true
    }

    @Test
    fun `function-without-inline-modifier`() {
        // given
        val sut = getSut("function-without-inline-modifier")

        // then
        sut.functions().first().isInline shouldBeEqualTo false
    }

    @Test
    fun `function-with-local-function`() {
        // given
        val sut = getSut("function-with-local-function")

        // then
        sut.functions()
            .first()
            .getLocalFunctions()
            .map { it.name } shouldBeEqualTo listOf("localFunction")
    }

    @Test
    fun `function-with-nested-local-functions`() {
        // given
        val sut = getSut("function-with-nested-local-functions")

        // then
        val actual = sut
            .functions()
            .first()
            .getLocalFunctions(includeNested = true)
            .map { it.name }

        actual shouldBeEqualTo listOf("LocalSampleFunction1", "LocalSampleFunction2")
    }

    @Test
    fun `function-without-local-functions`() {
        // given
        val sut = getSut("function-without-local-functions")

        // then
        sut.functions()
            .first()
            .getLocalFunctions()
            .isEmpty() shouldBeEqualTo true
    }

    @Test
    fun `function-with-return-type`() {
        // given
        val sut = getSut("function-with-return-type")

        // then
        sut.functions().first().hasExplicitReturnType shouldBeEqualTo true
        sut.functions().first().getExplicitReturnType shouldBeEqualTo "SampleType"
    }

    @Test
    fun `function-without-return-type`() {
        // given
        val sut = getSut("function-without-return-type")

        // then
        sut.functions().first().hasExplicitReturnType shouldBeEqualTo false
    }

    @Test
    fun `function-with-two-parameters-and-return-type`() {
        // given
        val sut = getSut("function-with-two-parameters-and-return-type")

        // then
        sut.functions().first().getParameters.size shouldBeEqualTo 2
        sut.functions().first().getExplicitReturnType shouldBeEqualTo "Boolean"
    }

    @Test
    fun `function-with-parameter`() {
        // given
        val sut = getSut("function-with-parameter")

        // then
        sut.functions().first().getParameters.size shouldBeEqualTo 1
    }

    private fun getSut(fileName: String) = getSnippetKoScope("core/kofunction/snippet/$fileName.kt.txt")
}
