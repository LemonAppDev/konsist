package com.lemonappdev.konsist.core.verify.kodeclarationassert.assertnull

import com.lemonappdev.konsist.TestSnippetProvider
import com.lemonappdev.konsist.api.verify.assertNotNull
import com.lemonappdev.konsist.api.verify.assertNull
import com.lemonappdev.konsist.core.exception.KoAssertionFailedException
import org.amshove.kluent.shouldContain
import org.amshove.kluent.shouldThrow
import org.junit.jupiter.api.Test

class AssertNullOnSingleDeclarationTest {
    @Test
    fun `declaration-assert-test-method-name-derived-from-junit-method-name`() {
        // given
        val sut =
            getSnippetFile("declaration-assert-test-method-name-derived-from-junit-method-name")
                .classes()
                .first()

        // then
        try {
            sut.assertNull()
        } catch (e: Exception) {
            e.message?.shouldContain(
                "Assert `declaration-assert-test-method-name-derived-from-junit-method-name` failed.",
            )
                ?: throw e
        }
    }

    @Test
    fun `declaration-assert-test-method-name-derived-from-test-name-parameter`() {
        // given
        val sut =
            getSnippetFile("declaration-assert-test-method-name-derived-from-test-name-parameter")
                .classes()
                .first()

        // then
        try {
            sut.assertNull(testName = "sample test")
        } catch (e: Exception) {
            e.message?.shouldContain("Assert `sample test` failed.")
                ?: throw e
        }
    }

    @Test
    fun `declaration-assert-null-error-with-custom-message`() {
        // given
        val message = "CUSTOM ASSERT MESSAGE"
        val sut =
            getSnippetFile("declaration-assert-null-error-with-custom-message")
                .classes()
                .first()

        // then
        try {
            sut.assertNull(additionalMessage = message)
        } catch (e: Exception) {
            e.message?.shouldContain(
                "Assert `declaration-assert-null-error-with-custom-message` failed.\n" +
                    "$message\nDeclaration has not null value: SampleClass.",
            )
                ?: throw e
        }
    }

    @Test
    fun `declaration-assert-not-null-error-with-custom-message`() {
        // given
        val message = "CUSTOM ASSERT MESSAGE"
        val sut =
            getSnippetFile("declaration-assert-not-null-error-with-custom-message")
                .classes()
                .map { it.primaryConstructor }
                .first()

        // then
        try {
            sut.assertNotNull(additionalMessage = message)
        } catch (e: Exception) {
            e.message?.shouldContain(
                "Assert `declaration-assert-not-null-error-with-custom-message` failed.\n" +
                    "$message\nDeclaration has null value.",
            )
                ?: throw e
        }
    }

    @Test
    fun `assert-null-passes-when-declaration-has-null-value`() {
        // given
        val sut =
            getSnippetFile("assert-null-passes-when-declaration-has-null-value")
                .classes()
                .map { it.primaryConstructor }
                .first()

        // then
        sut.assertNull()
    }

    @Test
    fun `assert-null-fails-when-declaration-has-not-null-value`() {
        // given
        val sut =
            getSnippetFile("assert-null-fails-when-declaration-has-not-null-value")
                .classes()
                .map { it.primaryConstructor }
                .first()

        // when
        val func = { sut.assertNull() }

        // then
        func shouldThrow KoAssertionFailedException::class
    }

    @Test
    fun `assert-not-null-fails-when-declaration-has-null-value`() {
        // given
        val sut =
            getSnippetFile("assert-not-null-fails-when-declaration-has-null-value")
                .classes()
                .map { it.primaryConstructor }
                .first()

        // when
        val func = { sut.assertNotNull() }

        // then
        func shouldThrow KoAssertionFailedException::class
    }

    @Test
    fun `assert-not-null-passes-when-declaration-has-not-null-value`() {
        // given
        val sut =
            getSnippetFile("assert-not-null-passes-when-declaration-has-not-null-value")
                .classes()
                .map { it.primaryConstructor }
                .first()

        // then
        sut.assertNotNull()
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("core/verify/kodeclarationassert/assertnull/snippet/", fileName)
}
