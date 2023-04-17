package com.lemon.konsist.core.check

import com.lemon.konsist.TestSnippetProvider
import com.lemon.konsist.core.exception.KoCheckFailedException
import com.lemon.konsist.core.exception.KoPreconditionFailedException
import org.amshove.kluent.shouldContain
import org.amshove.kluent.shouldThrow
import org.amshove.kluent.withMessage
import org.junit.jupiter.api.Test

class AssertTest {
    @Test
    fun `check-test-method-name`() {
        // given
        val sut = getSnippetFile("check-test-method-name")
            .classes()

        // then
        try {
            sut.assert { false }
        } catch (e: Exception) {
            e.message?.shouldContain("Check 'check-test-method-name' has failed. Invalid declarations") ?: throw e
        }
    }

    @Test
    fun `check-pass`() {
        // given
        val sut = getSnippetFile("check-pass")
            .classes()

        // then
        sut.assert {
            it.name == "SampleClass"
        }
    }

    @Test
    fun `check-fail`() {
        // given
        val sut = getSnippetFile("check-fail")
            .classes()

        // when
        val func = {
            sut.assert {
                it.name == "OtherName"
            }
        }

        // then
        func shouldThrow KoCheckFailedException::class
    }

    @Test
    fun `check-not-pass`() {
        // given
        val sut = getSnippetFile("check-not-pass")
            .classes()

        // then
        sut.assertNot {
            it.name == "OtherName"
        }
    }

    @Test
    fun `check-not-fail`() {
        // given
        val sut = getSnippetFile("check-not-fail")
            .classes()

        // when
        val func = {
            sut.assertNot {
                it.name == "SampleClass"
            }
        }

        // then
        func shouldThrow KoCheckFailedException::class
    }

    @Test
    fun `check-fail-declaration-list-empty`() {
        // given
        val sut = getSnippetFile("check-fail-declaration-list-empty")
            .classes()

        // when
        val func = {
            sut.assert { true }
        }

        // then
        func shouldThrow KoPreconditionFailedException::class withMessage
            "Declaration list is empty. Please make sure that list of declarations contain items before calling 'assert' method."
    }

    @Test
    fun `check-not-fail-declaration-list-empty`() {
        // given
        val sut = getSnippetFile("check-not-fail-declaration-list-empty")
            .classes()

        // when
        val func = {
            sut.assertNot { false }
        }

        // then
        func shouldThrow KoPreconditionFailedException::class withMessage
            "Declaration list is empty. Please make sure that list of declarations contain items before calling 'assertNot' method."
    }

    private fun getSnippetFile(fileName: String) = TestSnippetProvider.getSnippetKoScope("core/check/snippet/", fileName)
}
