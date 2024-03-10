package com.lemonappdev.konsist.core.verify.koproviderassert.assertnull

import com.lemonappdev.konsist.TestSnippetProvider
import com.lemonappdev.konsist.api.declaration.KoFileDeclaration
import com.lemonappdev.konsist.api.provider.KoNameProvider
import com.lemonappdev.konsist.api.provider.KoTypeProvider
import com.lemonappdev.konsist.api.verify.assertNotNull
import com.lemonappdev.konsist.api.verify.assertNull
import com.lemonappdev.konsist.core.exception.KoAssertionFailedException
import org.amshove.kluent.shouldContain
import org.amshove.kluent.shouldThrow
import org.junit.jupiter.api.Test

class AssertNullOnSingleProviderTest {
    @Test
    fun `provider-assert-test-method-name-derived-from-junit-method-name`() {
        // given
        val sut =
            getSnippetFile("provider-assert-test-method-name-derived-from-junit-method-name")
                .declarations()
                .filterIsInstance<KoNameProvider>()
                .firstOrNull()

        // then
        try {
            sut.assertNull()
        } catch (e: Exception) {
            e.message?.shouldContain("Assert `provider-assert-test-method-name-derived-from-junit-method-name` failed.")
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
                .firstOrNull()

        // then
        try {
            sut.assertNull(testName = "sample test")
        } catch (e: Exception) {
            e.message?.shouldContain("Assert `sample test` failed.")
                ?: throw e
        }
    }

    @Test
    fun `provider-assert-null-error-with-custom-message`() {
        // given
        val message = "CUSTOM ASSERT MESSAGE"
        val sut =
            getSnippetFile("provider-assert-null-error-with-custom-message")
                .declarations()
                .filterNot { it is KoFileDeclaration }
                .filterIsInstance<KoNameProvider>()
                .firstOrNull()

        // then
        try {
            sut.assertNull(additionalMessage = message)
        } catch (e: Exception) {
            e.message?.shouldContain(
                "Assert `provider-assert-null-error-with-custom-message` failed.\n" +
                    "$message\nDeclaration has not null value: SampleClass.",
            )
                ?: throw e
        }
    }

    @Test
    fun `provider-assert-not-null-error-with-custom-message`() {
        // given
        val message = "CUSTOM ASSERT MESSAGE"
        val sut =
            getSnippetFile("provider-assert-not-null-error-with-custom-message")
                .declarations()
                .filterNot { it is KoFileDeclaration }
                .filterIsInstance<KoTypeProvider>()
                .firstOrNull()

        // then
        try {
            sut.assertNotNull(additionalMessage = message)
        } catch (e: Exception) {
            e.message?.shouldContain(
                "Assert `provider-assert-not-null-error-with-custom-message` failed.\n" +
                    "$message\nDeclaration has null value.",
            )
                ?: throw e
        }
    }

    @Test
    fun `assert-null-passes-when-item-has-null-value`() {
        // given
        val sut =
            getSnippetFile("assert-null-passes-when-item-has-null-value")
                .declarations()
                .filterIsInstance<KoTypeProvider>()
                .firstOrNull()

        // then
        sut.assertNull()
    }

    @Test
    fun `assert-null-fails-when-item-has-not-null-value`() {
        // given
        val sut =
            getSnippetFile("assert-null-fails-when-item-has-not-null-value")
                .declarations()
                .filterIsInstance<KoNameProvider>()
                .firstOrNull()

        // when
        val func = { sut.assertNull() }

        // then
        func shouldThrow KoAssertionFailedException::class
    }

    @Test
    fun `assert-not-null-fails-when-item-has-null-value`() {
        // given
        val sut =
            getSnippetFile("assert-not-null-fails-when-item-has-null-value")
                .declarations()
                .filterIsInstance<KoTypeProvider>()
                .firstOrNull()

        // when
        val func = { sut.assertNotNull() }

        // then
        func shouldThrow KoAssertionFailedException::class
    }

    @Test
    fun `assert-not-null-passes-when-item-has-not-null-value`() {
        // given
        val sut =
            getSnippetFile("assert-not-null-passes-when-item-has-not-null-value")
                .declarations()
                .filterIsInstance<KoNameProvider>()
                .firstOrNull()

        // then
        sut.assertNotNull()
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("core/verify/koproviderassert/assertnull/snippet/", fileName)
}
