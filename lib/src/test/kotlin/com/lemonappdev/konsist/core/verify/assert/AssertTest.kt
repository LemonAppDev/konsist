package com.lemonappdev.konsist.core.verify.assert

import com.lemonappdev.konsist.TestSnippetProvider
import com.lemonappdev.konsist.core.exception.KoCheckFailedException
import com.lemonappdev.konsist.core.exception.KoPreconditionFailedException
import com.lemonappdev.konsist.core.ext.withExplicitPrimaryConstructor
import com.lemonappdev.konsist.core.verify.assert
import com.lemonappdev.konsist.core.verify.assertNot
import org.amshove.kluent.shouldContain
import org.amshove.kluent.shouldThrow
import org.amshove.kluent.withMessage
import org.junit.jupiter.api.Test

class AssertTest {
    @Test
    fun `assert-test-method-name`() {
        // given
        val sut = getSnippetFile("assert-test-method-name")
            .classes()

        // then
        try {
            sut.assert { false }
        } catch (e: Exception) {
            e.message?.shouldContain("Check 'assert-test-method-name' has failed. Invalid declarations") ?: throw e
        }
    }

    @Test
    fun `assert-pass`() {
        // given
        val sut = getSnippetFile("assert-pass")
            .classes()

        // then
        sut.assert { it.name == "SampleClass" }
    }

    @Test
    fun `assert-fail`() {
        // given
        val sut = getSnippetFile("assert-fail")
            .classes()

        // when
        val func = {
            sut.assert { it.name == "OtherName" }
        }

        // then
        func shouldThrow KoCheckFailedException::class
    }

    @Test
    fun `assert-not-pass`() {
        // given
        val sut = getSnippetFile("assert-not-pass")
            .classes()

        // then
        sut.assertNot {
            it.name == "OtherName"
        }
    }

    @Test
    fun `assert-not-fail`() {
        // given
        val sut = getSnippetFile("assert-not-fail")
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
    fun `assert-pass-when-expression-is-nullable`() {
        // given
        val sut = getSnippetFile("assert-pass-when-expression-is-nullable")
            .classes()
            .withExplicitPrimaryConstructor()

        // then
        sut.assert { it.primaryConstructor?.hasParameterNamed("sampleParameter") }
    }

    @Test
    fun `assert-fail-when-expression-is-nullable`() {
        // given
        val sut = getSnippetFile("assert-fail-when-expression-is-nullable")
            .classes()
            .withExplicitPrimaryConstructor()

        // when
        val func = {
            sut.assert { it.primaryConstructor?.hasParameterNamed("sampleParameter") }
        }

        // then
        func shouldThrow KoCheckFailedException::class
    }

    @Test
    fun `assert-not-pass-when-expression-is-nullable`() {
        // given
        val sut = getSnippetFile("assert-not-pass-when-expression-is-nullable")
            .classes()
            .withExplicitPrimaryConstructor()

        // then
        sut.assertNot { it.primaryConstructor?.hasParameterNamed("otherParameter") }
    }

    @Test
    fun `assert-not-fail-when-expression-is-nullable`() {
        // given
        val sut = getSnippetFile("assert-not-fail-when-expression-is-nullable")
            .classes()
            .withExplicitPrimaryConstructor()

        // when
        val func = {
            sut.assertNot { it.primaryConstructor?.hasParameterNamed("sampleParameter") }
        }

        // then
        func shouldThrow KoCheckFailedException::class
    }

    @Test
    fun `assert-fail-declaration-list-empty`() {
        // given
        val sut = getSnippetFile("assert-fail-declaration-list-empty")
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
    fun `assert-not-fail-declaration-list-empty`() {
        // given
        val sut = getSnippetFile("assert-not-fail-declaration-list-empty")
            .classes()

        // when
        val func = {
            sut.assertNot { false }
        }

        // then
        func shouldThrow KoPreconditionFailedException::class withMessage
            "Declaration list is empty. Please make sure that list of declarations contain items before calling 'assertNot' method."
    }

    private fun getSnippetFile(fileName: String) = TestSnippetProvider.getSnippetKoScope("core/verify/assert/snippet/", fileName)
}
