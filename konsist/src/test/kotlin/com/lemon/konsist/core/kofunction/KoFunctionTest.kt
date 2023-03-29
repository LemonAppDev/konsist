package com.lemon.konsist.core.kofunction

import com.lemon.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoFunctionTest {
    @Test
    fun `function-with-operator-modifier`() {
        // given
        val sut =
            getSut("function-with-operator-modifier")
                .functions()
                .first()

        // then
        sut.isOperator shouldBeEqualTo true
    }

    @Test
    fun `function-without-operator-modifier`() {
        // given
        val sut =
            getSut("function-without-operator-modifier")
                .functions()
                .first()

        // then
        sut.isOperator shouldBeEqualTo false
    }

    @Test
    fun `function-with-inline-modifier`() {
        // given
        val sut =
            getSut("function-with-inline-modifier")
                .functions()
                .first()

        // then
        sut.isInline shouldBeEqualTo true
    }

    @Test
    fun `function-without-inline-modifier`() {
        // given
        val sut =
            getSut("function-without-inline-modifier")
                .functions()
                .first()

        // then
        sut.isInline shouldBeEqualTo false
    }

    @Test
    fun `function-with-nested-local-functions`() {
        // given
        val sut =
            getSut("function-with-nested-local-functions")
                .functions()
                .first()

        // then
        sut
            .getLocalFunctions(includeNested = true)
            .map { it.name } shouldBeEqualTo listOf("LocalSampleFunction1", "LocalSampleFunction2")
    }

    @Test
    fun `function-with-local-function`() {
        // given
        val sut =
            getSut("function-with-local-function")
                .functions()
                .first()

        // then
        sut
            .getLocalFunctions()
            .map { it.name } shouldBeEqualTo listOf("localFunction")
    }

    @Test
    fun `function-without-local-functions`() {
        // given
        val sut =
            getSut("function-without-local-functions")
                .functions()
                .first()

        // then
        sut
            .getLocalFunctions()
            .isEmpty() shouldBeEqualTo true
    }

    @Test
    fun `function-with-return-type`() {
        // given
        val sut =
            getSut("function-with-return-type")
                .functions()
                .first()

        // then
        sut.apply {
            hasExplicitReturnType shouldBeEqualTo true
            getExplicitReturnType shouldBeEqualTo "SampleType"
        }
    }

    @Test
    fun `function-without-return-type`() {
        // given
        val sut =
            getSut("function-without-return-type")
                .functions()
                .first()

        // then
        sut.hasExplicitReturnType shouldBeEqualTo false
    }

    @Test
    fun `function-with-two-parameters-and-return-type`() {
        // given
        val sut =
            getSut("function-with-two-parameters-and-return-type")
                .functions()
                .first()

        // then
        sut.apply {
            getParameters.size shouldBeEqualTo 2
            getExplicitReturnType shouldBeEqualTo "Boolean"
        }
    }

    @Test
    fun `function-with-parameter`() {
        // given
        val sut =
            getSut("function-with-parameter")
                .functions()
                .first()

        // then
        sut
            .getParameters
            .size shouldBeEqualTo 1
    }

    private fun getSut(fileName: String) = getSnippetKoScope("core/kofunction/snippet/$fileName.kt.txt")
}
