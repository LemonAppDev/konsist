package com.lemonappdev.konsist.core.verify.koproviderassert.assertempty

import com.lemonappdev.konsist.TestSnippetProvider
import com.lemonappdev.konsist.api.declaration.KoFileDeclaration
import com.lemonappdev.konsist.api.provider.KoNameProvider
import com.lemonappdev.konsist.api.provider.KoReturnProvider
import com.lemonappdev.konsist.api.provider.KoSourceAndAliasTypeProvider
import com.lemonappdev.konsist.api.verify.assertEmpty
import com.lemonappdev.konsist.api.verify.assertNotEmpty
import com.lemonappdev.konsist.core.exception.KoAssertionFailedException
import org.amshove.kluent.shouldContain
import org.amshove.kluent.shouldThrow
import org.junit.jupiter.api.Test

class AssertEmptyOnProviderListTest {
    @Test
    fun `provider-assert-test-method-name-derived-from-junit-method-name`() {
        // given
        val sut =
            getSnippetFile("provider-assert-test-method-name-derived-from-junit-method-name")
                .declarations()
                .filterIsInstance<KoNameProvider>()

        // then
        try {
            sut.assertEmpty()
        } catch (e: Exception) {
            e.message?.shouldContain("Assert 'provider-assert-test-method-name-derived-from-junit-method-name' failed.")
                ?: throw e
        }
    }

    @Test
    fun `provider-assert-test-method-name-derived-from-test-name-parameter`() {
        // given
        val sut =
            getSnippetFile("provider-assert-test-method-name-derived-from-test-name-parameter")
                .declarations()
                .filterIsInstance<KoNameProvider>()

        // then
        try {
            sut.assertEmpty(testName = "sample test")
        } catch (e: Exception) {
            e.message?.shouldContain("Assert 'sample test' failed.")
                ?: throw e
        }
    }

    @Test
    fun `provider-assert-empty-error-on-list-containing-one-null-value`() {
        // given
        val sut =
            getSnippetFile("provider-assert-empty-error-on-list-containing-one-null-value")
                .declarations()
                .filterNot { it is KoFileDeclaration }
                .map { it as? KoSourceAndAliasTypeProvider }

        // then
        try {
            sut.assertEmpty()
        } catch (e: Exception) {
            e.message?.shouldContain(
                "Assert 'provider-assert-empty-error-on-list-containing-one-null-value' failed. " +
                    "Declaration list is not empty. It contains 1 null value.",
            )
                ?: throw e
        }
    }

    @Test
    fun `provider-assert-empty-error-on-list-containing-two-null-values`() {
        // given
        val sut =
            getSnippetFile("provider-assert-empty-error-on-list-containing-two-null-values")
                .declarations()
                .map { it as? KoSourceAndAliasTypeProvider }

        // then
        try {
            sut.assertEmpty()
        } catch (e: Exception) {
            e.message?.shouldContain(
                "Assert 'provider-assert-empty-error-on-list-containing-two-null-values' failed. " +
                    "Declaration list is not empty. It contains 2 null values.",
            )
                ?: throw e
        }
    }

    @Test
    fun `provider-assert-empty-error-on-list-containing-non-null-values`() {
        // given
        val sut =
            getSnippetFile("provider-assert-empty-error-on-list-containing-non-null-values")
                .declarations()
                .filterNot { it is KoFileDeclaration }
                .filterIsInstance<KoNameProvider>()

        // then
        try {
            sut.assertEmpty()
        } catch (e: Exception) {
            e.message?.shouldContain(
                "Assert 'provider-assert-empty-error-on-list-containing-non-null-values' failed. " +
                    "Declaration list is not empty. It contains values:\nSampleClass1,\nSampleClass2.",
            )
                ?: throw e
        }
    }

    @Test
    fun `provider-assert-empty-error-on-list-containing-null-and-non-null-values`() {
        // given
        val sut =
            getSnippetFile("provider-assert-empty-error-on-list-containing-null-and-non-null-values")
                .declarations()
                .filterNot { it is KoFileDeclaration }
                .map { it as? KoReturnProvider }

        // then
        try {
            sut.assertEmpty()
        } catch (e: Exception) {
            e.message?.shouldContain(
                "Assert 'provider-assert-empty-error-on-list-containing-null-and-non-null-values' failed. " +
                    "Declaration list is not empty. It contains 1 null value and values:\nsampleFunction.",
            )
                ?: throw e
        }
    }

    @Test
    fun `provider-assert-empty-error-with-custom-message`() {
        // given
        val message = "CUSTOM ASSERT MESSAGE"
        val sut =
            getSnippetFile("provider-assert-empty-error-with-custom-message")
                .declarations()
                .filterNot { it is KoFileDeclaration }
                .filterIsInstance<KoNameProvider>()

        // then
        try {
            sut.assertEmpty(additionalMessage = message)
        } catch (e: Exception) {
            e.message?.shouldContain(
                "Assert 'provider-assert-empty-error-with-custom-message' failed.\n$message\n" +
                    "Declaration list is not empty. It contains values:\nSampleClass.",
            )
                ?: throw e
        }
    }

    @Test
    fun `provider-assert-empty-error-with-custom-message-and-strict-set-to-true`() {
        // given
        val message = "CUSTOM ASSERT MESSAGE"
        val sut =
            getSnippetFile("provider-assert-empty-error-with-custom-message-and-strict-set-to-true")
                .declarations()
                .filterNot { it is KoFileDeclaration }
                .filterIsInstance<KoNameProvider>()

        // then
        try {
            sut.assertEmpty(strict = true, additionalMessage = message)
        } catch (e: Exception) {
            e.message?.shouldContain(
                "Assert 'provider-assert-empty-error-with-custom-message-and-strict-set-to-true' failed.\n$message\n" +
                    "Declaration list is not empty. It contains values:\nSampleClass.",
            )
                ?: throw e
        }
    }

    @Test
    fun `provider-assert-not-empty-error-with-custom-message`() {
        // given
        val message = "CUSTOM ASSERT MESSAGE"
        val sut =
            getSnippetFile("provider-assert-not-empty-error-with-custom-message")
                .declarations()
                .filterIsInstance<KoSourceAndAliasTypeProvider>()

        // then
        try {
            sut.assertNotEmpty(additionalMessage = message)
        } catch (e: Exception) {
            e.message?.shouldContain(
                "Assert 'provider-assert-not-empty-error-with-custom-message' failed.\n$message\n" +
                    "Declaration list is empty.",
            )
                ?: throw e
        }
    }

    @Test
    fun `provider-assert-not-empty-error-with-custom-message-and-strict-set-to-true`() {
        // given
        val message = "CUSTOM ASSERT MESSAGE"
        val sut =
            getSnippetFile("provider-assert-not-empty-error-with-custom-message-and-strict-set-to-true")
                .declarations()
                .filterIsInstance<KoSourceAndAliasTypeProvider>()

        // then
        try {
            sut.assertNotEmpty(strict = true, additionalMessage = message)
        } catch (e: Exception) {
            e.message?.shouldContain(
                "Assert 'provider-assert-not-empty-error-with-custom-message-and-strict-set-to-true' failed.\n$message\n" +
                    "Declaration list is empty.",
            )
                ?: throw e
        }
    }

    @Test
    fun `assert-empty-passes-when-item-list-is-empty`() {
        // given
        val sut =
            getSnippetFile("assert-empty-passes-when-item-list-is-empty")
                .declarations()
                .filterIsInstance<KoSourceAndAliasTypeProvider>()

        // then
        sut.assertEmpty()
    }

    @Test
    fun `assert-empty-fails-when-item-list-has-item`() {
        // given
        val sut =
            getSnippetFile("assert-empty-fails-when-item-list-has-item")
                .declarations()
                .filterIsInstance<KoNameProvider>()

        // when
        val func = {
            sut.assertEmpty()
        }

        // then
        func shouldThrow KoAssertionFailedException::class
    }

    @Test
    fun `assert-empty-fails-when-item-list-has-only-nulls`() {
        // given
        val sut =
            getSnippetFile("assert-empty-fails-when-item-list-has-only-nulls")
                .declarations()
                .map { it as? KoSourceAndAliasTypeProvider }

        // when
        val func = {
            sut.assertEmpty()
        }

        // then
        func shouldThrow KoAssertionFailedException::class
    }

    @Test
    fun `assert-empty-passes-when-item-list-is-empty-and-strict-set-to-true`() {
        // given
        val sut =
            getSnippetFile("assert-empty-passes-when-item-list-is-empty-and-strict-set-to-true")
                .declarations()
                .filterIsInstance<KoSourceAndAliasTypeProvider>()

        // then
        sut.assertEmpty(strict = true)
    }

    @Test
    fun `assert-empty-fails-when-item-list-has-item-and-strict-set-to-true`() {
        // given
        val sut =
            getSnippetFile("assert-empty-fails-when-item-list-has-item-and-strict-set-to-true")
                .declarations()
                .filterIsInstance<KoNameProvider>()

        // when
        val func = { sut.assertEmpty(strict = true) }

        // then
        func shouldThrow KoAssertionFailedException::class
    }

    @Test
    fun `assert-empty-passes-when-item-list-has-only-nulls-and-strict-set-to-true`() {
        // given
        val sut =
            getSnippetFile("assert-empty-passes-when-item-list-has-only-nulls-and-strict-set-to-true")
                .declarations()
                .map { it as? KoSourceAndAliasTypeProvider }

        // then
        sut.assertEmpty(strict = true)
    }

    @Test
    fun `assert-not-empty-passes-when-item-list-has-item`() {
        // given
        val sut =
            getSnippetFile("assert-not-empty-passes-when-item-list-has-item")
                .declarations()
                .filterIsInstance<KoNameProvider>()

        // then
        sut.assertNotEmpty()
    }

    @Test
    fun `assert-not-empty-fails-when-item-list-is-empty`() {
        // given
        val sut =
            getSnippetFile("assert-not-empty-fails-when-item-list-is-empty")
                .declarations()
                .filterIsInstance<KoSourceAndAliasTypeProvider>()

        // when
        val func = {
            sut.assertNotEmpty()
        }

        // then
        func shouldThrow KoAssertionFailedException::class
    }

    @Test
    fun `assert-not-empty-passes-when-item-list-has-only-nulls`() {
        // given
        val sut =
            getSnippetFile("assert-not-empty-passes-when-item-list-has-only-nulls")
                .declarations()
                .map { it as? KoSourceAndAliasTypeProvider }

        // then
        sut.assertNotEmpty()
    }

    @Test
    fun `assert-not-empty-passes-when-item-list-has-item-and-strict-set-to-true`() {
        // given
        val sut =
            getSnippetFile("assert-not-empty-passes-when-item-list-has-item-and-strict-set-to-true")
                .declarations()
                .filterIsInstance<KoNameProvider>()

        // then
        sut.assertNotEmpty(strict = true)
    }

    @Test
    fun `assert-not-empty-fails-when-item-list-is-empty-and-strict-set-to-true`() {
        // given
        val sut =
            getSnippetFile("assert-not-empty-fails-when-item-list-is-empty-and-strict-set-to-true")
                .declarations()
                .filterIsInstance<KoSourceAndAliasTypeProvider>()

        // when
        val func = {
            sut.assertNotEmpty(strict = true)
        }

        // then
        func shouldThrow KoAssertionFailedException::class
    }

    @Test
    fun `assert-not-empty-fails-when-item-list-has-only-nulls-and-strict-set-to-true`() {
        // given
        val sut =
            getSnippetFile("assert-not-empty-fails-when-item-list-has-only-nulls-and-strict-set-to-true")
                .declarations()
                .map { it as? KoSourceAndAliasTypeProvider }

        // when
        val func = {
            sut.assertNotEmpty(strict = true)
        }

        // then
        func shouldThrow KoAssertionFailedException::class
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("core/verify/koproviderassert/assertempty/snippet/", fileName)
}
