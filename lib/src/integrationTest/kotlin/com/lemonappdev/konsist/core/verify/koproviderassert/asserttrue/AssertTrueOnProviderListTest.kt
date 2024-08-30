package com.lemonappdev.konsist.core.verify.koproviderassert.asserttrue

import com.lemonappdev.konsist.TestSnippetProvider
import com.lemonappdev.konsist.api.declaration.KoFileDeclaration
import com.lemonappdev.konsist.api.provider.KoAnnotationProvider
import com.lemonappdev.konsist.api.provider.KoNameProvider
import com.lemonappdev.konsist.api.provider.KoPrimaryConstructorProvider
import com.lemonappdev.konsist.api.verify.assertFalse
import com.lemonappdev.konsist.api.verify.assertTrue
import com.lemonappdev.konsist.core.exception.KoAssertionFailedException
import com.lemonappdev.konsist.core.exception.KoPreconditionFailedException
import org.amshove.kluent.shouldContain
import org.amshove.kluent.shouldThrow
import org.amshove.kluent.withMessage
import org.junit.jupiter.api.Test

class AssertTrueOnProviderListTest {
    @Test
    fun `provider-assert-test-method-name-derived-from-junit-method-name`() {
        // given
        val sut =
            getSnippetFile("provider-assert-test-method-name-derived-from-junit-method-name")
                .declarations()
                .filterIsInstance<KoNameProvider>()

        // then
        try {
            sut.assertTrue { false }
        } catch (e: Exception) {
            e.message?.shouldContain("Assert 'provider-assert-test-method-name-derived-from-junit-method-name' was violated (2 times)")
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
            sut.assertTrue(testName = "sample test") { false }
        } catch (e: Exception) {
            e.message?.shouldContain("Assert 'sample test' was violated (2 times)")
                ?: throw e
        }
    }

    @Test
    fun `provider-assert-error-with-custom-message`() {
        // given
        val message = "CUSTOM ASSERT MESSAGE"
        val sut =
            getSnippetFile("provider-assert-error-with-custom-message")
                .declarations()
                .filterIsInstance<KoNameProvider>()

        // then
        try {
            sut.assertTrue(additionalMessage = message) { false }
        } catch (e: Exception) {
            e.message?.shouldContain(
                "Assert 'provider-assert-error-with-custom-message' was violated (2 times)." +
                    "\n$message\nInvalid declarations:",
            )
                ?: throw e
        }
    }

    @Test
    fun `provider-assert-error-with-custom-message-and-strict-set-to-true`() {
        // given
        val message = "CUSTOM ASSERT MESSAGE"
        val sut =
            getSnippetFile("provider-assert-error-with-custom-message-and-strict-set-to-true")
                .declarations()
                .filterIsInstance<KoNameProvider>()

        // then
        try {
            sut.assertTrue(strict = true, additionalMessage = message) { false }
        } catch (e: Exception) {
            e.message?.shouldContain(
                "Assert 'provider-assert-error-with-custom-message-and-strict-set-to-true' was violated (2 times)." +
                    "\n$message\nInvalid declarations:",
            )
                ?: throw e
        }
    }

    @Test
    fun `provider-assert-displaying-correct-failed-declaration-type`() {
        // given
        val sut =
            getSnippetFile("provider-assert-displaying-correct-failed-declaration-type")
                .declarations()
                .filterNot { it is KoFileDeclaration }
                .filterIsInstance<KoNameProvider>()

        // then
        try {
            sut.assertTrue { false }
        } catch (e: Exception) {
            e.message?.shouldContain("(SampleClass ClassDeclaration)")
                ?: throw e
        }
    }

    @Test
    fun `provider-assert-passes-when-declaration-list-is-empty`() {
        // given
        val sut =
            getSnippetFile("provider-assert-passes-when-declaration-list-is-empty")
                .declarations()
                .filterNot { it is KoFileDeclaration }
                .filterIsInstance<KoNameProvider>()

        // when
        sut.assertTrue { true }
    }

    @Test
    fun `provider-assert-false-passes-when-declaration-list-is-empty`() {
        // given
        val sut =
            getSnippetFile("provider-assert-false-passes-when-declaration-list-is-empty")
                .declarations()
                .filterNot { it is KoFileDeclaration }
                .filterIsInstance<KoNameProvider>()

        // when
        sut.assertFalse { false }
    }

    @Test
    fun `provider-assert-strict-fails-when-declaration-list-is-empty`() {
        // given
        val sut =
            getSnippetFile("provider-assert-strict-fails-when-declaration-list-is-empty")
                .declarations()
                .filterNot { it is KoFileDeclaration }
                .filterIsInstance<KoNameProvider>()

        // when
        val func = {
            sut.assertTrue(strict = true) { true }
        }

        // then
        func shouldThrow KoPreconditionFailedException::class withMessage
            "Declaration list is empty. Please make sure that list of declarations contain items before calling the 'assertTrue' method."
    }

    @Test
    fun `provider-assert-false-strict-fails-when-declaration-list-is-empty`() {
        // given
        val sut =
            getSnippetFile("provider-assert-false-strict-fails-when-declaration-list-is-empty")
                .declarations()
                .filterNot { it is KoFileDeclaration }
                .filterIsInstance<KoNameProvider>()

        // when
        val func = {
            sut.assertFalse(strict = true) { false }
        }

        // then
        func shouldThrow KoPreconditionFailedException::class withMessage
            "Declaration list is empty. Please make sure that list of declarations contain items before calling the 'assertFalse' method."
    }

    @Test
    fun `assert-passes`() {
        // given
        val sut =
            getSnippetFile("assert-passes")
                .declarations()
                .filterIsInstance<KoAnnotationProvider>()

        // then
        sut.assertTrue { it.hasAnnotations() }
    }

    @Test
    fun `assert-fails`() {
        // given
        val sut =
            getSnippetFile("assert-fails")
                .declarations()
                .filterIsInstance<KoAnnotationProvider>()

        // when
        val func = {
            sut.assertTrue { it.hasAnnotations() }
        }

        // then
        func shouldThrow KoAssertionFailedException::class
    }

    @Test
    fun `assert-false-passes`() {
        // given
        val sut =
            getSnippetFile("assert-false-passes")
                .declarations()
                .filterIsInstance<KoAnnotationProvider>()

        // then
        sut.assertFalse { it.hasAnnotations() }
    }

    @Test
    fun `assert-false-fails`() {
        // given
        val sut =
            getSnippetFile("assert-false-fails")
                .declarations()
                .filterIsInstance<KoAnnotationProvider>()

        // when
        val func = {
            sut.assertFalse { it.hasAnnotations() }
        }

        // then
        func shouldThrow KoAssertionFailedException::class
    }

    @Test
    fun `assert-passes-when-expression-is-nullable`() {
        // given
        val sut =
            getSnippetFile("assert-passes-when-expression-is-nullable")
                .declarations()
                .filterIsInstance<KoPrimaryConstructorProvider>()

        // then
        sut.assertTrue { it.primaryConstructor?.hasParameterWithName("sampleParameter") ?: true }
    }

    @Test
    fun `assert-fails-when-expression-is-nullable`() {
        // given
        val sut =
            getSnippetFile("assert-fails-when-expression-is-nullable")
                .declarations()
                .filterIsInstance<KoPrimaryConstructorProvider>()

        // when
        val func = {
            sut.assertTrue { it.primaryConstructor?.hasParameterWithName("sampleParameter") ?: true }
        }

        // then
        func shouldThrow KoAssertionFailedException::class
    }

    @Test
    fun `assert-false-passes-when-expression-is-nullable`() {
        // given
        val sut =
            getSnippetFile("assert-false-passes-when-expression-is-nullable")
                .declarations()
                .filterIsInstance<KoPrimaryConstructorProvider>()

        // then
        sut.assertFalse { it.primaryConstructor?.hasParameterWithName("otherParameter") ?: false }
    }

    @Test
    fun `assert-false-fails-when-expression-is-nullable`() {
        // given
        val sut =
            getSnippetFile("assert-false-fails-when-expression-is-nullable")
                .declarations()
                .filterIsInstance<KoPrimaryConstructorProvider>()

        // when
        val func = {
            sut.assertFalse { it.primaryConstructor?.hasParameterWithName("sampleParameter") ?: false }
        }

        // then
        func shouldThrow KoAssertionFailedException::class
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("core/verify/koproviderassert/asserttrue/snippet/", fileName)
}
