package com.lemonappdev.konsist.core.verify.koproviderassert.assertempty

import com.lemonappdev.konsist.TestSnippetProvider
import com.lemonappdev.konsist.api.declaration.KoFileDeclaration
import com.lemonappdev.konsist.api.ext.list.initBlocks
import com.lemonappdev.konsist.api.ext.list.localFunctions
import com.lemonappdev.konsist.api.ext.list.withName
import com.lemonappdev.konsist.api.provider.KoKotlinTypeProvider
import com.lemonappdev.konsist.api.provider.KoNameProvider
import com.lemonappdev.konsist.api.verify.assertNotEmpty
import com.lemonappdev.konsist.api.verify.assertEmpty
import com.lemonappdev.konsist.core.exception.KoCheckFailedException
import com.lemonappdev.konsist.core.exception.KoPreconditionFailedException
import org.amshove.kluent.shouldContain
import org.amshove.kluent.shouldThrow
import org.amshove.kluent.withMessage
import org.junit.jupiter.api.Test

class KoProviderAssertOnSequenceTest {
    @Test
    fun `provider-assert-test-method-name`() {
        // given
        val sut = getSnippetFile("provider-assert-test-method-name")
            .declarations()
            .filterIsInstance<KoNameProvider>()
            .asSequence()

        // then
        try {
            sut.assertEmpty()
        } catch (e: Exception) {
            e.message?.shouldContain("Assert 'provider-assert-test-method-name' failed.")
                ?: throw e
        }
    }

    @Test
    fun `provider-assert-empty-error-with-custom-message`() {
        // given
        val message = "CUSTOM ASSERT MESSAGE"
        val sut = getSnippetFile("provider-assert-empty-error-with-custom-message")
            .declarations()
            .filterIsInstance<KoNameProvider>()
            .asSequence()

        // then
        try {
            sut.assertEmpty(additionalMessage = message)
        } catch (e: Exception) {
            e.message?.shouldContain(
                "Assert 'provider-assert-empty-error-with-custom-message' failed. Declaration list is not empty." +
                        "\n$message",
            )
                ?: throw e
        }
    }

    @Test
    fun `provider-assert-empty-error-with-custom-message-and-strict-set-to-true`() {
        // given
        val message = "CUSTOM ASSERT MESSAGE"
        val sut = getSnippetFile("provider-assert-empty-error-with-custom-message-and-strict-set-to-true")
            .declarations()
            .filterIsInstance<KoNameProvider>()
            .asSequence()

        // then
        try {
            sut.assertEmpty(strict = true, additionalMessage = message)
        } catch (e: Exception) {
            e.message?.shouldContain(
                "Assert 'provider-assert-empty-error-with-custom-message-and-strict-set-to-true' failed. Declaration list is not empty." +
                        "\n$message",
            )
                ?: throw e
        }
    }

    @Test
    fun `provider-assert-not-empty-error-with-custom-message`() {
        // given
        val message = "CUSTOM ASSERT MESSAGE"
        val sut = getSnippetFile("provider-assert-not-empty-error-with-custom-message")
            .declarations()
            .filterIsInstance<KoKotlinTypeProvider>()
            .asSequence()

        // then
        try {
            sut.assertNotEmpty(additionalMessage = message)
        } catch (e: Exception) {
            e.message?.shouldContain(
                "Assert 'provider-assert-not-empty-error-with-custom-message' failed. Declaration list is empty." +
                        "\n$message",
            )
                ?: throw e
        }
    }

    @Test
    fun `provider-assert-not-empty-error-with-custom-message-and-strict-set-to-true`() {
        // given
        val message = "CUSTOM ASSERT MESSAGE"
        val sut = getSnippetFile("provider-assert-not-empty-error-with-custom-message-and-strict-set-to-true")
            .declarations()
            .filterIsInstance<KoKotlinTypeProvider>()
            .asSequence()

        // then
        try {
            sut.assertNotEmpty(strict = true, additionalMessage = message)
        } catch (e: Exception) {
            e.message?.shouldContain(
                "Assert 'provider-assert-not-empty-error-with-custom-message-and-strict-set-to-true' failed. Declaration list is empty." +
                        "\n$message",
            )
                ?: throw e
        }
    }

    @Test
    fun `assert-empty-passes-when-item-list-is-empty`() {
        // given
        val sut = getSnippetFile("assert-empty-passes-when-item-list-is-empty")
            .declarations()
            .filterIsInstance<KoKotlinTypeProvider>()
            .asSequence()

        // then
        sut.assertEmpty()
    }

    @Test
    fun `assert-empty-fails-when-item-list-has-item`() {
        // given
        val sut = getSnippetFile("assert-empty-fails-when-item-list-has-item")
            .declarations()
            .filterIsInstance<KoNameProvider>()
            .asSequence()

        // when
        val func = {
            sut.assertEmpty()
        }

        // then
        func shouldThrow KoCheckFailedException::class
    }

    @Test
    fun `assert-empty-fails-when-item-list-has-only-nulls`() {
        // given
        val sut = getSnippetFile("assert-empty-fails-when-item-list-has-only-nulls")
            .declarations()
            .map { it as? KoKotlinTypeProvider }
            .asSequence()

        // when
        val func = {
            sut.assertEmpty()
        }

        // then
        func shouldThrow KoCheckFailedException::class
    }

    @Test
    fun `assert-empty-passes-when-item-list-is-empty-and-strict-set-to-true`() {
        // given
        val sut = getSnippetFile("assert-empty-passes-when-item-list-is-empty-and-strict-set-to-true")
            .declarations()
            .filterIsInstance<KoKotlinTypeProvider>()
            .asSequence()

        // then
        sut.assertEmpty(strict = true)
    }

    @Test
    fun `assert-empty-fails-when-item-list-has-item-and-strict-set-to-true`() {
        // given
        val sut = getSnippetFile("assert-empty-fails-when-item-list-has-item-and-strict-set-to-true")
            .declarations()
            .filterIsInstance<KoNameProvider>()
            .asSequence()

        // when
        val func = { sut.assertEmpty(strict = true) }

        // then
        func shouldThrow KoCheckFailedException::class
    }

    @Test
    fun `assert-empty-passes-when-item-list-has-only-nulls-and-strict-set-to-true`() {
        // given
        val sut = getSnippetFile("assert-empty-passes-when-item-list-has-only-nulls-and-strict-set-to-true")
            .declarations()
            .map { it as? KoKotlinTypeProvider }
            .asSequence()

        // then
        sut.assertEmpty(strict = true)
    }

    @Test
    fun `assert-not-empty-passes-when-item-list-has-item`() {
        // given
        val sut = getSnippetFile("assert-not-empty-passes-when-item-list-has-item")
            .declarations()
            .filterIsInstance<KoNameProvider>()
            .asSequence()

        // then
        sut.assertNotEmpty()
    }

    @Test
    fun `assert-not-empty-fails-when-item-list-is-empty`() {
        // given
        val sut = getSnippetFile("assert-not-empty-fails-when-item-list-is-empty")
            .declarations()
            .filterIsInstance<KoKotlinTypeProvider>()
            .asSequence()

        // when
        val func = {
            sut.assertNotEmpty()
        }

        // then
        func shouldThrow KoCheckFailedException::class
    }

    @Test
    fun `assert-not-empty-passes-when-item-list-has-only-nulls`() {
        // given
        val sut = getSnippetFile("assert-not-empty-passes-when-item-list-has-only-nulls")
            .declarations()
            .map { it as? KoKotlinTypeProvider }
            .asSequence()

        // then
        sut.assertNotEmpty()
    }

    @Test
    fun `assert-not-empty-passes-when-item-list-has-item-and-strict-set-to-true`() {
        // given
        val sut = getSnippetFile("assert-not-empty-passes-when-item-list-has-item-and-strict-set-to-true")
            .declarations()
            .filterIsInstance<KoNameProvider>()
            .asSequence()

        // then
        sut.assertNotEmpty(strict = true)
    }

    @Test
    fun `assert-not-empty-fails-when-item-list-is-empty-and-strict-set-to-true`() {
        // given
        val sut = getSnippetFile("assert-not-empty-fails-when-item-list-is-empty-and-strict-set-to-true")
            .declarations()
            .filterIsInstance<KoKotlinTypeProvider>()
            .asSequence()

        // when
        val func = {
            sut.assertNotEmpty(strict = true)
        }

        // then
        func shouldThrow KoCheckFailedException::class
    }

    @Test
    fun `assert-not-empty-fails-when-item-list-has-only-nulls-and-strict-set-to-true`() {
        // given
        val sut = getSnippetFile("assert-not-empty-fails-when-item-list-has-only-nulls-and-strict-set-to-true")
            .declarations()
            .map { it as? KoKotlinTypeProvider }
            .asSequence()

        // when
        val func = {
            sut.assertNotEmpty(strict = true)
        }

        // then
        func shouldThrow KoCheckFailedException::class
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("core/verify/koproviderassert/assertempty/snippet/", fileName)
}
