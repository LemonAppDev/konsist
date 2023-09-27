package com.lemonappdev.konsist.core.verify.koproviderassert.assertnull

import com.lemonappdev.konsist.TestSnippetProvider
import com.lemonappdev.konsist.api.declaration.KoFileDeclaration
import com.lemonappdev.konsist.api.provider.KoKotlinTypeProvider
import com.lemonappdev.konsist.api.provider.KoNameProvider
import com.lemonappdev.konsist.api.provider.KoPrimaryConstructorProvider
import com.lemonappdev.konsist.api.verify.assertNotNull
import com.lemonappdev.konsist.api.verify.assertNull
import com.lemonappdev.konsist.core.exception.KoCheckFailedException
import org.amshove.kluent.shouldContain
import org.amshove.kluent.shouldThrow
import org.junit.jupiter.api.Test

class KoProviderAssertOnSingleElementTest {
    @Test
    fun `provider-assert-test-method-name`() {
        // given
        val sut = getSnippetFile("provider-assert-test-method-name")
            .declarations()
            .filterIsInstance<KoNameProvider>()
            .firstOrNull()

        // then
        try {
            sut.assertNull()
        } catch (e: Exception) {
            e.message?.shouldContain("Assert `provider-assert-test-method-name` failed.")
                ?: throw e
        }
    }

    @Test
    fun `provider-assert-null-error-with-custom-message`() {
        // given
        val message = "CUSTOM ASSERT MESSAGE"
        val sut = getSnippetFile("provider-assert-null-error-with-custom-message")
            .declarations()
            .filterIsInstance<KoNameProvider>()
            .firstOrNull()

        // then
        try {
            sut.assertNull(additionalMessage = message)
        } catch (e: Exception) {
            e.message?.shouldContain(
                "Assert `provider-assert-null-error-with-custom-message` failed. Declaration has not null value." +
                        "\n$message",
            )
                ?: throw e
        }
    }

    @Test
    fun `provider-assert-not-null-error-with-custom-message`() {
        // given
        val message = "CUSTOM ASSERT MESSAGE"
        val sut = getSnippetFile("provider-assert-not-null-error-with-custom-message")
            .declarations()
            .filterIsInstance<KoKotlinTypeProvider>()
            .firstOrNull()

        // then
        try {
            sut.assertNotNull(additionalMessage = message)
        } catch (e: Exception) {
            e.message?.shouldContain(
                "Assert `provider-assert-not-null-error-with-custom-message` failed. Declaration has null value." +
                        "\n$message",
            )
                ?: throw e
        }
    }

    @Test
    fun `assert-null-passes-when-item-has-null-value`() {
        // given
        val sut = getSnippetFile("assert-null-passes-when-item-has-null-value")
            .declarations()
            .filterIsInstance<KoKotlinTypeProvider>()
            .firstOrNull()

        // then
        sut.assertNull()
    }

    @Test
    fun `assert-null-fails-when-item-has-not-null-value`() {
        // given
        val sut = getSnippetFile("assert-null-fails-when-item-has-not-null-value")
            .declarations()
            .filterIsInstance<KoNameProvider>()
            .firstOrNull()

        // when
        val func = { sut.assertNull() }

        // then
        func shouldThrow KoCheckFailedException::class
    }

    @Test
    fun `assert-not-null-fails-when-item-has-null-value`() {
        // given
        val sut = getSnippetFile("assert-not-null-fails-when-item-has-null-value")
            .declarations()
            .filterIsInstance<KoKotlinTypeProvider>()
            .firstOrNull()

        // when
        val func = { sut.assertNotNull() }

        // then
        func shouldThrow KoCheckFailedException::class
    }

    @Test
    fun `assert-not-null-passes-when-item-has-not-null-value`() {
        // given
        val sut = getSnippetFile("assert-not-null-passes-when-item-has-not-null-value")
            .declarations()
            .filterIsInstance<KoNameProvider>()
            .firstOrNull()

        // then
        sut.assertNotNull()
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("core/verify/koproviderassert/assertnull/snippet/", fileName)
}
