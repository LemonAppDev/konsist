package com.lemonappdev.konsist.core.verify.kodeclarationassert.assertempty

import com.lemonappdev.konsist.TestSnippetProvider
import com.lemonappdev.konsist.api.ext.list.classes
import com.lemonappdev.konsist.api.verify.assertEmpty
import com.lemonappdev.konsist.api.verify.assertNotEmpty
import com.lemonappdev.konsist.core.exception.KoCheckFailedException
import org.amshove.kluent.shouldContain
import org.amshove.kluent.shouldThrow
import org.junit.jupiter.api.Test

class AssertEmptyOnDeclarationSequenceTest {
    @Test
    fun `declaration-assert-test-method-name`() {
        // given
        val sut = getSnippetFile("declaration-assert-test-method-name")
            .classes()
            .asSequence()

        // then
        try {
            sut.assertEmpty()
        } catch (e: Exception) {
            e.message?.shouldContain("Assert 'declaration-assert-test-method-name' failed.")
                ?: throw e
        }
    }

    @Test
    fun `declaration-assert-empty-error-on-list-containing-one-null-value`() {
        // given
        val sut = getSnippetFile("declaration-assert-empty-error-on-list-containing-one-null-value")
            .classes()
            .map { it.primaryConstructor }
            .asSequence()

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
        val sut = getSnippetFile("declaration-assert-empty-error-on-list-containing-two-null-values")
            .classes()
            .map { it.primaryConstructor }
            .asSequence()

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
        val sut = getSnippetFile("declaration-assert-empty-error-on-list-containing-non-null-values")
            .classes()
            .asSequence()

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
        val sut = getSnippetFile("declaration-assert-empty-error-on-list-containing-null-and-non-null-values")
            .functions()
            .map { it.returnType }
            .asSequence()

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
        val sut = getSnippetFile("declaration-assert-empty-error-with-custom-message")
            .classes()
            .asSequence()

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
        val sut = getSnippetFile("declaration-assert-empty-error-with-custom-message-and-strict-set-to-true")
            .classes()
            .asSequence()

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
        val sut = getSnippetFile("declaration-assert-not-empty-error-with-custom-message")
            .interfaces()
            .asSequence()

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
        val sut = getSnippetFile("declaration-assert-not-empty-error-with-custom-message-and-strict-set-to-true")
            .interfaces()
            .asSequence()

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
        val sut = getSnippetFile("assert-empty-passes-when-declaration-list-is-empty")
            .interfaces()
            .asSequence()

        // then
        sut.assertEmpty()
    }

    @Test
    fun `assert-empty-fails-when-declaration-list-has-item`() {
        // given
        val sut = getSnippetFile("assert-empty-fails-when-declaration-list-has-item")
            .classes()
            .asSequence()

        // when
        val func = {
            sut.assertEmpty()
        }

        // then
        func shouldThrow KoCheckFailedException::class
    }

    @Test
    fun `assert-empty-fails-when-declaration-list-has-only-nulls`() {
        // given
        val sut = getSnippetFile("assert-empty-fails-when-declaration-list-has-only-nulls")
            .classes()
            .map { it.primaryConstructor }
            .asSequence()

        // when
        val func = { sut.assertEmpty() }

        // then
        func shouldThrow KoCheckFailedException::class
    }

    @Test
    fun `assert-empty-passes-when-declaration-list-is-empty-and-strict-set-to-true`() {
        // given
        val sut = getSnippetFile("assert-empty-passes-when-declaration-list-is-empty-and-strict-set-to-true")
            .interfaces()
            .asSequence()

        // then
        sut.assertEmpty(strict = true)
    }

    @Test
    fun `assert-empty-fails-when-declaration-list-has-item-and-strict-set-to-true`() {
        // given
        val sut = getSnippetFile("assert-empty-fails-when-declaration-list-has-item-and-strict-set-to-true")
            .classes()
            .asSequence()

        // when
        val func = { sut.assertEmpty(strict = true) }

        // then
        func shouldThrow KoCheckFailedException::class
    }

    @Test
    fun `assert-empty-passes-when-declaration-list-has-only-nulls-and-strict-set-to-true`() {
        // given
        val sut = getSnippetFile("assert-empty-passes-when-declaration-list-has-only-nulls-and-strict-set-to-true")
            .classes()
            .map { it.primaryConstructor }
            .asSequence()

        // then
        sut.assertEmpty(strict = true)
    }

    @Test
    fun `assert-not-empty-passes-when-declaration-list-has-item`() {
        // given
        val sut = getSnippetFile("assert-not-empty-passes-when-declaration-list-has-item")
            .classes()
            .asSequence()

        // then
        sut.assertNotEmpty()
    }

    @Test
    fun `assert-not-empty-fails-when-declaration-list-is-empty`() {
        // given
        val sut = getSnippetFile("assert-not-empty-fails-when-declaration-list-is-empty")
            .interfaces()
            .asSequence()

        // when
        val func = {
            sut.assertNotEmpty()
        }

        // then
        func shouldThrow KoCheckFailedException::class
    }

    @Test
    fun `assert-not-empty-passes-when-declaration-list-has-only-nulls`() {
        // given
        val sut = getSnippetFile("assert-not-empty-passes-when-declaration-list-has-only-nulls")
            .classes()
            .map { it.primaryConstructor }
            .asSequence()

        // then
        sut.assertNotEmpty()
    }

    @Test
    fun `assert-not-empty-passes-when-declaration-list-has-item-and-strict-set-to-true`() {
        // given
        val sut = getSnippetFile("assert-not-empty-passes-when-declaration-list-has-item-and-strict-set-to-true")
            .classes()
            .asSequence()

        // then
        sut.assertNotEmpty(strict = true)
    }

    @Test
    fun `assert-not-empty-fails-when-declaration-list-is-empty-and-strict-set-to-true`() {
        // given
        val sut = getSnippetFile("assert-not-empty-fails-when-declaration-list-is-empty-and-strict-set-to-true")
            .interfaces()
            .asSequence()

        // when
        val func = {
            sut.assertNotEmpty(strict = true)
        }

        // then
        func shouldThrow KoCheckFailedException::class
    }

    @Test
    fun `assert-not-empty-fails-when-declaration-list-has-only-nulls-and-strict-set-to-true`() {
        // given
        val sut = getSnippetFile("assert-not-empty-fails-when-declaration-list-has-only-nulls-and-strict-set-to-true")
            .classes()
            .map { it.primaryConstructor }
            .asSequence()

        // when
        val func = {
            sut.assertNotEmpty(strict = true)
        }

        // then
        func shouldThrow KoCheckFailedException::class
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("core/verify/kodeclarationassert/assertempty/snippet/", fileName)
}
