package com.lemonappdev.konsist.core.verify.kodeclarationassert.assertempty

import com.lemonappdev.konsist.TestSnippetProvider
import com.lemonappdev.konsist.api.ext.list.classes
import com.lemonappdev.konsist.api.verify.assertEmpty
import com.lemonappdev.konsist.api.verify.assertNotEmpty
import com.lemonappdev.konsist.core.exception.KoAssertionFailedException
import org.amshove.kluent.shouldContain
import org.amshove.kluent.shouldThrow
import org.junit.jupiter.api.Test

class AssertEmptyOnDeclarationListTest {
    @Test
    fun `declaration-assert-test-method-name-derived-from-junit-method-name`() {
        // given
        val sut =
            getSnippetFile("declaration-assert-test-method-name-derived-from-junit-method-name")
                .classes()

        // then
        try {
            sut.assertEmpty()
        } catch (e: Exception) {
            e.message?.shouldContain(
                "Assert 'declaration-assert-test-method-name-derived-from-junit-method-name' failed.",
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

        // then
        try {
            sut.assertEmpty(testName = "sample test")
        } catch (e: Exception) {
            e.message?.shouldContain("Assert 'sample test' failed.")
                ?: throw e
        }
    }

    @Test
    fun `declaration-assert-empty-error-on-list-containing-one-null-value`() {
        // given
        val sut =
            getSnippetFile("declaration-assert-empty-error-on-list-containing-one-null-value")
                .classes()
                .map { it.primaryConstructor }

        // then
        try {
            sut.assertEmpty()
        } catch (e: Exception) {
            e.message?.shouldContain(
                "Assert 'declaration-assert-empty-error-on-list-containing-one-null-value' failed. " +
                    "Declaration list is not empty. It contains 1 null value.",
            )
                ?: throw e
        }
    }

    @Test
    fun `declaration-assert-empty-error-on-list-containing-two-null-values`() {
        // given
        val sut =
            getSnippetFile("declaration-assert-empty-error-on-list-containing-two-null-values")
                .classes()
                .map { it.primaryConstructor }

        // then
        try {
            sut.assertEmpty()
        } catch (e: Exception) {
            e.message?.shouldContain(
                "Assert 'declaration-assert-empty-error-on-list-containing-two-null-values' failed. " +
                    "Declaration list is not empty. It contains 2 null values.",
            )
                ?: throw e
        }
    }

    @Test
    fun `declaration-assert-empty-error-on-list-containing-non-null-values`() {
        // given
        val sut =
            getSnippetFile("declaration-assert-empty-error-on-list-containing-non-null-values")
                .classes()

        // then
        try {
            sut.assertEmpty()
        } catch (e: Exception) {
            e.message?.shouldContain(
                "Assert 'declaration-assert-empty-error-on-list-containing-non-null-values' failed. " +
                    "Declaration list is not empty. It contains values:\nSampleClass1,\nSampleClass2.",
            )
                ?: throw e
        }
    }

    @Test
    fun `declaration-assert-empty-error-on-list-containing-null-and-non-null-values`() {
        // given
        val sut =
            getSnippetFile("declaration-assert-empty-error-on-list-containing-null-and-non-null-values")
                .functions()
                .map { it.returnType }

        // then
        try {
            sut.assertEmpty()
        } catch (e: Exception) {
            e.message?.shouldContain(
                "Assert 'declaration-assert-empty-error-on-list-containing-null-and-non-null-values' failed. " +
                    "Declaration list is not empty. It contains 1 null value and values:\nInt.",
            )
                ?: throw e
        }
    }

    @Test
    fun `declaration-assert-empty-error-with-custom-message`() {
        // given
        val message = "CUSTOM ASSERT MESSAGE"
        val sut =
            getSnippetFile("declaration-assert-empty-error-with-custom-message")
                .classes()

        // then
        try {
            sut.assertEmpty(additionalMessage = message)
        } catch (e: Exception) {
            e.message?.shouldContain(
                "Assert 'declaration-assert-empty-error-with-custom-message' failed.\n$message\n" +
                    "Declaration list is not empty. It contains values:\nSampleClass.",
            )
                ?: throw e
        }
    }

    @Test
    fun `declaration-assert-empty-error-with-custom-message-and-strict-set-to-true`() {
        // given
        val message = "CUSTOM ASSERT MESSAGE"
        val sut =
            getSnippetFile("declaration-assert-empty-error-with-custom-message-and-strict-set-to-true")
                .classes()

        // then
        try {
            sut.assertEmpty(strict = true, additionalMessage = message)
        } catch (e: Exception) {
            e.message?.shouldContain(
                "Assert 'declaration-assert-empty-error-with-custom-message-and-strict-set-to-true' failed.\n$message\n" +
                    "Declaration list is not empty. It contains values:\nSampleClass.",
            )
                ?: throw e
        }
    }

    @Test
    fun `declaration-assert-not-empty-error-with-custom-message`() {
        // given
        val message = "CUSTOM ASSERT MESSAGE"
        val sut =
            getSnippetFile("declaration-assert-not-empty-error-with-custom-message")
                .interfaces()

        // then
        try {
            sut.assertNotEmpty(additionalMessage = message)
        } catch (e: Exception) {
            e.message?.shouldContain(
                "Assert 'declaration-assert-not-empty-error-with-custom-message' failed.\n" +
                    "$message\nDeclaration list is empty.",
            )
                ?: throw e
        }
    }

    @Test
    fun `declaration-assert-not-empty-error-with-custom-message-and-strict-set-to-true`() {
        // given
        val message = "CUSTOM ASSERT MESSAGE"
        val sut =
            getSnippetFile("declaration-assert-not-empty-error-with-custom-message-and-strict-set-to-true")
                .interfaces()

        // then
        try {
            sut.assertNotEmpty(strict = true, additionalMessage = message)
        } catch (e: Exception) {
            e.message?.shouldContain(
                "Assert 'declaration-assert-not-empty-error-with-custom-message-and-strict-set-to-true' failed.\n" +
                    "$message\nDeclaration list is empty.",
            )
                ?: throw e
        }
    }

    @Test
    fun `assert-empty-passes-when-declaration-list-is-empty`() {
        // given
        val sut =
            getSnippetFile("assert-empty-passes-when-declaration-list-is-empty")
                .interfaces()

        // then
        sut.assertEmpty()
    }

    @Test
    fun `assert-empty-fails-when-declaration-list-has-item`() {
        // given
        val sut =
            getSnippetFile("assert-empty-fails-when-declaration-list-has-item")
                .classes()

        // when
        val func = {
            sut.assertEmpty()
        }

        // then
        func shouldThrow KoAssertionFailedException::class
    }

    @Test
    fun `assert-empty-fails-when-declaration-list-has-only-nulls`() {
        // given
        val sut =
            getSnippetFile("assert-empty-fails-when-declaration-list-has-only-nulls")
                .classes()
                .map { it.primaryConstructor }

        // when
        val func = {
            sut.assertEmpty()
        }

        // then
        func shouldThrow KoAssertionFailedException::class
    }

    @Test
    fun `assert-empty-passes-when-declaration-list-is-empty-and-strict-set-to-true`() {
        // given
        val sut =
            getSnippetFile("assert-empty-passes-when-declaration-list-is-empty-and-strict-set-to-true")
                .interfaces()

        // then
        sut.assertEmpty(strict = true)
    }

    @Test
    fun `assert-empty-fails-when-declaration-list-has-item-and-strict-set-to-true`() {
        // given
        val sut =
            getSnippetFile("assert-empty-fails-when-declaration-list-has-item-and-strict-set-to-true")
                .classes()

        // when
        val func = { sut.assertEmpty(strict = true) }

        // then
        func shouldThrow KoAssertionFailedException::class
    }

    @Test
    fun `assert-empty-passes-when-declaration-list-has-only-nulls-and-strict-set-to-true`() {
        // given
        val sut =
            getSnippetFile("assert-empty-passes-when-declaration-list-has-only-nulls-and-strict-set-to-true")
                .classes()
                .map { it.primaryConstructor }

        // then
        sut.assertEmpty(strict = true)
    }

    @Test
    fun `assert-not-empty-passes-when-declaration-list-has-item`() {
        // given
        val sut =
            getSnippetFile("assert-not-empty-passes-when-declaration-list-has-item")
                .classes()

        // then
        sut.assertNotEmpty()
    }

    @Test
    fun `assert-not-empty-fails-when-declaration-list-is-empty`() {
        // given
        val sut =
            getSnippetFile("assert-not-empty-fails-when-declaration-list-is-empty")
                .interfaces()

        // when
        val func = {
            sut.assertNotEmpty()
        }

        // then
        func shouldThrow KoAssertionFailedException::class
    }

    @Test
    fun `assert-not-empty-passes-when-declaration-list-has-only-nulls`() {
        // given
        val sut =
            getSnippetFile("assert-not-empty-passes-when-declaration-list-has-only-nulls")
                .classes()
                .map { it.primaryConstructor }

        // then
        sut.assertNotEmpty()
    }

    @Test
    fun `assert-not-empty-passes-when-declaration-list-has-item-and-strict-set-to-true`() {
        // given
        val sut =
            getSnippetFile("assert-not-empty-passes-when-declaration-list-has-item-and-strict-set-to-true")
                .classes()

        // then
        sut.assertNotEmpty(strict = true)
    }

    @Test
    fun `assert-not-empty-fails-when-declaration-list-is-empty-and-strict-set-to-true`() {
        // given
        val sut =
            getSnippetFile("assert-not-empty-fails-when-declaration-list-is-empty-and-strict-set-to-true")
                .interfaces()

        // when
        val func = {
            sut.assertNotEmpty(strict = true)
        }

        // then
        func shouldThrow KoAssertionFailedException::class
    }

    @Test
    fun `assert-not-empty-fails-when-declaration-list-has-only-nulls-and-strict-set-to-true`() {
        // given
        val sut =
            getSnippetFile("assert-not-empty-fails-when-declaration-list-has-only-nulls-and-strict-set-to-true")
                .classes()
                .map { it.primaryConstructor }

        // when
        val func = {
            sut.assertNotEmpty(strict = true)
        }

        // then
        func shouldThrow KoAssertionFailedException::class
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("core/verify/kodeclarationassert/assertempty/snippet/", fileName)
}
