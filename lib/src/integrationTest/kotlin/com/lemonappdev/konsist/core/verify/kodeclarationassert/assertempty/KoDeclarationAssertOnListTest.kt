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
    fun `assert-empty-passes-when-declaration-list-is-empty`() {
        // given
        val sut = getSnippetFile("assert-empty-passes-when-declaration-list-is-empty")
            .interfaces()

        // then
        sut.assertEmpty()
    }

    @Test
    fun `assert-empty-fails-when-declaration-list-has-item`() {
        // given
        val sut = getSnippetFile("assert-empty-fails-when-declaration-list-has-item")
            .classes()

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

        // when
        val func = {
            sut.assertEmpty()
        }

        // then
        func shouldThrow KoCheckFailedException::class
    }

    @Test
    fun `assert-empty-passes-when-declaration-list-is-empty-and-strict-set-to-true`() {
        // given
        val sut = getSnippetFile("assert-empty-passes-when-declaration-list-is-empty-and-strict-set-to-true")
            .interfaces()

        // then
        sut.assertEmpty(strict = true)
    }

    @Test
    fun `assert-empty-fails-when-declaration-list-has-item-and-strict-set-to-true`() {
        // given
        val sut = getSnippetFile("assert-empty-fails-when-declaration-list-has-item-and-strict-set-to-true")
            .classes()

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

        // then
        sut.assertEmpty(strict = true)
    }

    @Test
    fun `assert-not-empty-passes-when-declaration-list-has-item`() {
        // given
        val sut = getSnippetFile("assert-not-empty-passes-when-declaration-list-has-item")
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

        // then
        sut.assertNotEmpty()
    }

    @Test
    fun `assert-not-empty-passes-when-declaration-list-has-item-and-strict-set-to-true`() {
        // given
        val sut = getSnippetFile("assert-not-empty-passes-when-declaration-list-has-item-and-strict-set-to-true")
            .classes()

        // then
        sut.assertNotEmpty(strict = true)
    }

    @Test
    fun `assert-not-empty-fails-when-declaration-list-is-empty-and-strict-set-to-true`() {
        // given
        val sut = getSnippetFile("assert-not-empty-fails-when-declaration-list-is-empty-and-strict-set-to-true")
            .interfaces()

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
