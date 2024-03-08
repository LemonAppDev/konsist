package com.lemonappdev.konsist.core.verify.koproviderassert.asserttrue

import com.lemonappdev.konsist.TestSnippetProvider
import com.lemonappdev.konsist.api.declaration.KoFileDeclaration
import com.lemonappdev.konsist.api.provider.KoAnnotationProvider
import com.lemonappdev.konsist.api.provider.KoNameProvider
import com.lemonappdev.konsist.api.provider.KoPrimaryConstructorProvider
import com.lemonappdev.konsist.api.provider.KoTypeAliasProvider
import com.lemonappdev.konsist.api.verify.assertFalse
import com.lemonappdev.konsist.api.verify.assertTrue
import com.lemonappdev.konsist.core.exception.KoAssertionFailedException
import com.lemonappdev.konsist.core.exception.KoPreconditionFailedException
import org.amshove.kluent.shouldContain
import org.amshove.kluent.shouldThrow
import org.amshove.kluent.withMessage
import org.junit.jupiter.api.Test

class AssertTrueOnSingleProviderTest {
    @Test
    fun `provider-assert-test-method-name-derived-from-junit-method-name`() {
        // given
        val sut =
            getSnippetFile("provider-assert-test-method-name-derived-from-junit-method-name")
                .declarations()
                .filterNot { it is KoFileDeclaration }
                .filterIsInstance<KoNameProvider>()
                .first()

        // then
        try {
            sut.assertTrue { false }
        } catch (e: Exception) {
            e.message?.shouldContain("Assert 'provider-assert-test-method-name-derived-from-junit-method-name' was violated (1 time)")
                ?: throw e
        }
    }

    @Test
    fun `provider-assert-test-method-name-derived-from-test-name-parameter`() {
        // given
        val sut =
            getSnippetFile("provider-assert-test-method-name-derived-from-test-name-parameter")
                .declarations()
                .filterNot { it is KoFileDeclaration }
                .filterIsInstance<KoNameProvider>()
                .first()

        // then
        try {
            sut.assertTrue(testName = "sample test") { false }
        } catch (e: Exception) {
            e.message?.shouldContain("Assert 'sample test' was violated (1 time)")
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
                .filterNot { it is KoFileDeclaration }
                .filterIsInstance<KoNameProvider>()
                .first()

        // then
        try {
            sut.assertTrue(additionalMessage = message) { false }
        } catch (e: Exception) {
            e.message?.shouldContain(
                "Assert 'provider-assert-error-with-custom-message' was violated (1 time)." +
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
                .filterNot { it is KoFileDeclaration }
                .filterIsInstance<KoNameProvider>()
                .first()

        // then
        try {
            sut.assertTrue(strict = true, additionalMessage = message) { false }
        } catch (e: Exception) {
            e.message?.shouldContain(
                "Assert 'provider-assert-error-with-custom-message-and-strict-set-to-true' was violated (1 time)." +
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
                .first()

        // then
        try {
            sut.assertTrue { false }
        } catch (e: Exception) {
            e.message?.shouldContain("(SampleClass ClassDeclaration)")
                ?: throw e
        }
    }

    @Test
    fun `provider-assert-passes-when-declaration-list-has-only-nulls`() {
        // given
        val sut =
            getSnippetFile("provider-assert-passes-when-declaration-list-has-only-nulls")
                .declarations()
                .filterNot { it is KoFileDeclaration }
                .firstOrNull { it is KoTypeAliasProvider }

        // when
        sut.assertTrue { true }
    }

    @Test
    fun `provider-assert-false-passes-when-declaration-list-has-only-nulls`() {
        // given
        val sut =
            getSnippetFile("provider-assert-false-passes-when-declaration-list-has-only-nulls")
                .declarations()
                .filterNot { it is KoFileDeclaration }
                .firstOrNull { it is KoTypeAliasProvider }

        // when
        sut.assertFalse { false }
    }

    @Test
    fun `provider-assert-strict-fails-when-declaration-list-has-only-nulls`() {
        // given
        val sut =
            getSnippetFile("provider-assert-strict-fails-when-declaration-list-has-only-nulls")
                .declarations()
                .filterNot { it is KoFileDeclaration }
                .firstOrNull { it is KoTypeAliasProvider }

        // when
        val func = {
            sut.assertTrue(strict = true) { true }
        }

        // then
        func shouldThrow KoPreconditionFailedException::class withMessage
            "Method 'assertTrue' was called on a null value. Please ensure that the declaration is not null before " +
            "calling this method."
    }

    @Test
    fun `provider-assert-false-strict-fails-when-declaration-list-has-only-nulls`() {
        // given
        val sut =
            getSnippetFile("provider-assert-false-strict-fails-when-declaration-list-has-only-nulls")
                .declarations()
                .filterNot { it is KoFileDeclaration }
                .firstOrNull { it is KoTypeAliasProvider }

        // when
        val func = {
            sut.assertFalse(strict = true) { false }
        }

        // then
        func shouldThrow KoPreconditionFailedException::class withMessage
            "Method 'assertFalse' was called on a null value. Please ensure that the declaration is not null before " +
            "calling this method."
    }

    @Test
    fun `assert-passes`() {
        // given
        val sut =
            getSnippetFile("assert-passes")
                .declarations()
                .filterNot { it is KoFileDeclaration }
                .filterIsInstance<KoAnnotationProvider>()
                .first()

        // then
        sut.assertTrue { it.hasAnnotations() }
    }

    @Test
    fun `assert-fails`() {
        // given
        val sut =
            getSnippetFile("assert-fails")
                .declarations()
                .filterNot { it is KoFileDeclaration }
                .filterIsInstance<KoAnnotationProvider>()
                .first()

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
                .filterNot { it is KoFileDeclaration }
                .filterIsInstance<KoAnnotationProvider>()
                .first()

        // then
        sut.assertFalse { it.hasAnnotations() }
    }

    @Test
    fun `assert-false-fails`() {
        // given
        val sut =
            getSnippetFile("assert-false-fails")
                .declarations()
                .filterNot { it is KoFileDeclaration }
                .filterIsInstance<KoAnnotationProvider>()
                .first()

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
                .first()

        // then
        sut.assertTrue { it.primaryConstructor?.hasParameterNamed("sampleParameter") ?: true }
    }

    @Test
    fun `assert-fails-when-expression-is-nullable`() {
        // given
        val sut =
            getSnippetFile("assert-fails-when-expression-is-nullable")
                .declarations()
                .filterIsInstance<KoPrimaryConstructorProvider>()
                .first()

        // when
        val func = {
            sut.assertTrue { it.primaryConstructor?.hasParameterNamed("sampleParameter") ?: true }
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
                .first()

        // then
        sut.assertFalse { it.primaryConstructor?.hasParameterNamed("otherParameter") ?: false }
    }

    @Test
    fun `assert-false-fails-when-expression-is-nullable`() {
        // given
        val sut =
            getSnippetFile("assert-false-fails-when-expression-is-nullable")
                .declarations()
                .filterIsInstance<KoPrimaryConstructorProvider>()
                .first()

        // when
        val func = {
            sut.assertFalse { it.primaryConstructor?.hasParameterNamed("sampleParameter") ?: false }
        }

        // then
        func shouldThrow KoAssertionFailedException::class
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("core/verify/koproviderassert/asserttrue/snippet/", fileName)
}
