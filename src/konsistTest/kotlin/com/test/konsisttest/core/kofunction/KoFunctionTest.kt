package com.test.konsisttest.core.kofunction

import com.test.konsisttest.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.shouldBe
import org.junit.jupiter.api.Test

class KoFunctionTest {
    @Test
    fun `function-with-operator-modifier`() {
        // given
        val sut = getSut("function-with-operator-modifier")

        // then
        sut.functions().first().isOperator shouldBe true
    }

    @Test
    fun `function-without-operator-modifier`() {
        // given
        val sut = getSut("function-without-operator-modifier")

        // then
        sut.functions().first().isOperator shouldBe false
    }

    @Test
    fun `function-with-inline-modifier`() {
        // given
        val sut = getSut("function-with-inline-modifier")

        // then
        sut.functions().first().isInline shouldBe true
    }

    @Test
    fun `function-without-inline-modifier`() {
        // given
        val sut = getSut("function-without-inline-modifier")

        // then
        sut.functions().first().isInline shouldBe false
    }

    private fun getSut(fileName: String) = getSnippetKoScope("core/kofunction/snippet/$fileName.kt.txt")
}
