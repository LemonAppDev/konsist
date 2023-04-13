package com.lemon.konsist.core.check

import com.lemon.konsist.TestSnippetProvider
import com.lemon.konsist.exception.KoCheckFailedException
import com.lemon.konsist.exception.KoPreconditionFailedException
import org.amshove.kluent.shouldContain
import org.amshove.kluent.shouldThrow
import org.amshove.kluent.withMessage
import org.junit.jupiter.api.Test

class CheckTest {
    @Test
    fun `check-test-method-name`() {
        // given
        val sut = getSut("check-test-method-name")
            .classes()

        // then
        try {
            sut.check { false }
        } catch (e: Exception) {
            e.message?.shouldContain("Check 'check-test-method-name' has failed. Invalid declarations") ?: throw e
        }
    }

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

    @Test
    fun `check-fail-declaration-list-empty`() {
        // given
        val sut = getSut("check-fail-declaration-list-empty")
            .classes()

        // when
        val func = {
            sut.check { true }
        }

        // then
        func shouldThrow KoPreconditionFailedException::class withMessage
            "Declaration list is empty. Please make sure that list of declarations contain items before calling 'check' method."
    }

    @Test
    fun `check-not-fail-declaration-list-empty`() {
        // given
        val sut = getSut("check-not-fail-declaration-list-empty")
            .classes()

        // when
        val func = {
            sut.checkNot { false }
        }

        // then
        func shouldThrow KoPreconditionFailedException::class withMessage
            "Declaration list is empty. Please make sure that list of declarations contain items before calling 'checkNot' method."
    }

    private fun getSut(fileName: String) = TestSnippetProvider.getSnippetKoScope("core/check/snippet/", fileName)
}
