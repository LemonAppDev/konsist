package com.lemonappdev.konsist.core.verify.kodeclarationassert.assertempty

import com.lemonappdev.konsist.TestSnippetProvider
import com.lemonappdev.konsist.api.ext.list.initBlocks
import com.lemonappdev.konsist.api.ext.list.localFunctions
import com.lemonappdev.konsist.api.ext.list.withName
import com.lemonappdev.konsist.api.verify.assertNotEmpty
import com.lemonappdev.konsist.api.verify.assertEmpty
import com.lemonappdev.konsist.core.exception.KoCheckFailedException
import com.lemonappdev.konsist.core.exception.KoPreconditionFailedException
import org.amshove.kluent.shouldContain
import org.amshove.kluent.shouldThrow
import org.amshove.kluent.withMessage
import org.junit.jupiter.api.Test

class KoDeclarationAssertOnListTest {
    @Test
    fun `declaration-assert-test-method-name`() {
        // given
        val sut = getSnippetFile("declaration-assert-test-method-name")
            .classes()

        // then
        try {
            sut.assertEmpty()
        } catch (e: Exception) {
            e.message?.shouldContain("Assert 'declaration-assert-test-method-name' failed.")
                ?: throw e
        }
    }

    @Test
    fun `file-declaration-assert-test-method-name`() {
        // given
        val sut = getSnippetFile("file-declaration-assert-test-method-name")
            .files

        // then
        try {
            sut.assertEmpty()
        } catch (e: Exception) {
            e.message?.shouldContain("Assert 'file-declaration-assert-test-method-name' failed.")
                ?: throw e
        }
    }

    @Test
    fun `declaration-assert-empty-error-with-custom-message`() {
        // given
        val message = "CUSTOM ASSERT MESSAGE"
        val sut = getSnippetFile("declaration-assert-empty-error-with-custom-message")
            .classes()

        // then
        try {
            sut.assertEmpty(additionalMessage = message)
        } catch (e: Exception) {
            e.message?.shouldContain(
                "Assert 'declaration-assert-empty-error-with-custom-message' failed. Declaration list is not empty." +
                        "\n$message",
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

        // then
        try {
            sut.assertEmpty(strict = true, additionalMessage = message)
        } catch (e: Exception) {
            e.message?.shouldContain(
                "Assert 'declaration-assert-empty-error-with-custom-message-and-strict-set-to-true' failed. Declaration list is not empty." +
                        "\n$message",
            )
                ?: throw e
        }
    }

    @Test
    fun `file-declaration-assert-empty-error-with-custom-message`() {
        // given
        val message = "CUSTOM ASSERT MESSAGE"
        val sut = getSnippetFile("file-declaration-assert-empty-error-with-custom-message")
            .files

        // then
        try {
            sut.assertEmpty(additionalMessage = message)
        } catch (e: Exception) {
            e.message?.shouldContain(
                "Assert 'file-declaration-assert-empty-error-with-custom-message' failed. Declaration list is not empty." +
                        "\n$message",
            )
                ?: throw e
        }
    }

    @Test
    fun `file-declaration-assert-empty-error-with-custom-message-and-strict-set-to-true`() {
        // given
        val message = "CUSTOM ASSERT MESSAGE"
        val sut = getSnippetFile("file-declaration-assert-empty-error-with-custom-message-and-strict-set-to-true")
            .files

        // then
        try {
            sut.assertEmpty(strict = true, additionalMessage = message)
        } catch (e: Exception) {
            e.message?.shouldContain(
                "Assert 'file-declaration-assert-empty-error-with-custom-message-and-strict-set-to-true' failed. Declaration list is not empty." +
                        "\n$message",
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

        // then
        try {
            sut.assertNotEmpty(additionalMessage = message)
        } catch (e: Exception) {
            e.message?.shouldContain(
                "Assert 'declaration-assert-not-empty-error-with-custom-message' failed. Declaration list is empty." +
                        "\n$message",
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

        // then
        try {
            sut.assertNotEmpty(strict = true, additionalMessage = message)
        } catch (e: Exception) {
            e.message?.shouldContain(
                "Assert 'declaration-assert-not-empty-error-with-custom-message-and-strict-set-to-true' failed. Declaration list is empty." +
                        "\n$message",
            )
                ?: throw e
        }
    }

    @Test
    fun `file-declaration-assert-not-empty-error-with-custom-message`() {
        // given
        val message = "CUSTOM ASSERT MESSAGE"
        val sut = getSnippetFile("file-declaration-assert-not-empty-error-with-custom-message")
            .files
            .withName("sample-file-name")

        // then
        try {
            sut.assertNotEmpty(additionalMessage = message)
        } catch (e: Exception) {
            e.message?.shouldContain(
                "Assert 'file-declaration-assert-not-empty-error-with-custom-message' failed. Declaration list is empty." +
                        "\n$message",
            )
                ?: throw e
        }
    }

    @Test
    fun `file-declaration-assert-not-empty-error-with-custom-message-and-strict-set-to-true`() {
        // given
        val message = "CUSTOM ASSERT MESSAGE"
        val sut = getSnippetFile("file-declaration-assert-not-empty-error-with-custom-message-and-strict-set-to-true")
            .files
            .withName("sample-file-name")

        // then
        try {
            sut.assertNotEmpty(strict = true, additionalMessage = message)
        } catch (e: Exception) {
            e.message?.shouldContain(
                "Assert 'file-declaration-assert-not-empty-error-with-custom-message-and-strict-set-to-true' failed. Declaration list is empty." +
                        "\n$message",
            )
                ?: throw e
        }
    }

    @Test
    fun `assert-empty-passes-when-declaration-list-has-only-nulls`() {
        // given
        val sut = getSnippetFile("assert-empty-passes-when-declaration-list-has-only-nulls")
            .classes()
            .map { it.primaryConstructor }

        // then
        sut.assertEmpty()
    }

    @Test
    fun `assert-not-empty-fails-when-declaration-list-has-only-nulls`() {
        // given
        val sut = getSnippetFile("assert-not-empty-fails-when-declaration-list-has-only-nulls")
            .classes()
            .map { it.primaryConstructor }

        // when
        val func = { sut.assertNotEmpty() }

        // then
        func shouldThrow KoPreconditionFailedException::class withMessage
                "Declaration list contains only null elements. Please make sure that list of declarations contain items " +
                "before calling the 'assertEmpty' method."
    }

    @Test
    fun `assert-empty-strict-fails-when-declaration-list-has-only-nulls`() {
        // given
        val sut = getSnippetFile("assert-empty-strict-fails-when-declaration-list-has-only-nulls")
            .classes()
            .map { it.primaryConstructor }

        // when
        val func = {
            sut.assertEmpty(strict = true)
        }

        // then
        func shouldThrow KoPreconditionFailedException::class withMessage
                "Declaration list contains only null elements. Please make sure that list of declarations contain items " +
                "before calling the 'assertEmpty' method."
    }

    @Test
    fun `assert-not-empty-strict-passes-when-declaration-list-has-only-nulls`() {
        // given
        val sut = getSnippetFile("assert-not-empty-strict-passes-when-declaration-list-has-only-nulls")
            .classes()
            .map { it.primaryConstructor }

        // then
        sut.assertNotEmpty(strict = true)
    }

    @Test
    fun `assert-empty-passes-when-declaration-list-is-empty`() {
        // given
        val sut = getSnippetFile("assert-empty-passes-when-declaration-list-is-empty")
            .interfaces()

        // then
        sut.assertEmpty()
    }

    @Test
    fun `assert-empty-fails-when-declaration-list-is-not-empty`() {
        // given
        val sut = getSnippetFile("assert-empty-fails-when-declaration-list-is-not-empty")
            .classes()

        // when
        val func = {
            sut.assertEmpty()
        }

        // then
        func shouldThrow KoCheckFailedException::class
    }

    @Test
    fun `assert-not-empty-passes-when-declaration-list-is-not-empty`() {
        // given
        val sut = getSnippetFile("assert-not-empty-passes-when-declaration-list-is-not-empty")
            .classes()

        // then
        sut.assertNotEmpty()
    }

    @Test
    fun `assert-not-empty-fails-when-declaration-list-is-empty`() {
        // given
        val sut = getSnippetFile("assert-not-empty-fails-when-declaration-list-is-empty")
            .interfaces()

        // when
        val func = {
            sut.assertEmpty()
        }

        // then
        func shouldThrow KoCheckFailedException::class
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("core/verify/kodeclarationassert/assertempty/snippet/", fileName)
}
