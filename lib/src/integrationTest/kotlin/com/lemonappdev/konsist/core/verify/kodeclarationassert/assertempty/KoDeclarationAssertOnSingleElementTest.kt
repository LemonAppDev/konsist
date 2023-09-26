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

class KoDeclarationAssertOnSingleElementTest {
    @Test
    fun `declaration-assert-test-method-name`() {
        // given
        val sut = getSnippetFile("declaration-assert-test-method-name")
            .classes()
            .first()

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
            .first()

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
            .first()

        // then
        try {
            sut.assertEmpty(additionalMessage = message)
        } catch (e: Exception) {
            e.message?.shouldContain(
                "Assert 'declaration-assert-empty-error-with-custom-message' failed. Declaration has not null value." +
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
            .first()

        // then
        try {
            sut.assertEmpty(additionalMessage = message)
        } catch (e: Exception) {
            e.message?.shouldContain(
                "Assert 'file-declaration-assert-empty-error-with-custom-message' failed. Declaration has not null value." +
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
            .first()

        // then
        try {
            sut.assertNotEmpty(additionalMessage = message)
        } catch (e: Exception) {
            e.message?.shouldContain(
                "Assert 'declaration-assert-not-empty-error-with-custom-message' failed. Declaration has null value." +
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
                "Assert 'file-declaration-assert-not-empty-error-with-custom-message' failed. Declaration has null value." +
                        "\n$message",
            )
                ?: throw e
        }
    }

    @Test
    fun `assert-empty-passes-when-declaration-has-null-value`() {
        // given
        val sut = getSnippetFile("assert-empty-passes-when-declaration-has-null-value")
            .classes()
            .map { it.primaryConstructor }
            .first()

        // then
        sut.assertEmpty()
    }

    @Test
    fun `assert-empty-fails-when-declaration-has-not-null-value`() {
        // given
        val sut = getSnippetFile("assert-empty-fails-when-declaration-has-not-null-value")
            .classes()
            .map { it.primaryConstructor }
            .first()

        // when
        val func = { sut.assertEmpty() }

        // then
        func shouldThrow KoCheckFailedException::class
    }

    @Test
    fun `assert-not-empty-fails-when-declaration-has-null-value`() {
        // given
        val sut = getSnippetFile("assert-not-empty-fails-when-declaration-has-null-value")
            .classes()
            .map { it.primaryConstructor }
            .first()

        // when
        val func = { sut.assertNotEmpty() }

        // then
        func shouldThrow KoCheckFailedException::class
    }

    @Test
    fun `assert-not-empty-passes-when-declaration-has-not-null-value`() {
        // given
        val sut = getSnippetFile("assert-not-empty-passes-when-declaration-has-not-null-value")
            .classes()
            .map { it.primaryConstructor }
            .first()

        // then
        sut.assertNotEmpty()
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("core/verify/kodeclarationassert/assertempty/snippet/", fileName)
}
