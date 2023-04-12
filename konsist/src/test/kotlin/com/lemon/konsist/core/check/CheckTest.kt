package com.lemon.konsist.core.check

import com.lemon.konsist.TestSnippetProvider
import com.lemon.konsist.exception.KoCheckFailedException
import org.amshove.kluent.shouldThrow
import org.junit.jupiter.api.Test

class CheckTest {
    @Test
    fun `check-pass`() {
        // given
        val sut = getSut("check-pass")
            .classes()

        // then
        sut.check {
            it.name == "SampleClass"
        }
    }

    @Test
    fun `check-fail`() {
        // given
        val sut = getSut("check-fail")
            .classes()

        // when
        val func = {
            sut.check {
                it.name == "OtherName"
            }
        }

        // then
        func shouldThrow KoCheckFailedException::class
    }

    @Test
    fun `check-not-pass`() {
        // given
        val sut = getSut("check-not-pass")
            .classes()

        // then
        sut.checkNot {
            it.name == "OtherName"
        }
    }

    @Test
    fun `check-not-fail`() {
        // given
        val sut = getSut("check-not-fail")
            .classes()

        // when
        val func = {
            sut.checkNot {
                it.name == "SampleClass"
            }
        }

        // then
        func shouldThrow KoCheckFailedException::class
    }

    private fun getSut(fileName: String) = TestSnippetProvider.getSnippetKoScope("core/check/snippet/", fileName)
}
