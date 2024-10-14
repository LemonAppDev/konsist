package com.lemonappdev.konsist.core.verify.kodeclarationassert.asserttrue

import com.lemonappdev.konsist.TestSnippetProvider
import com.lemonappdev.konsist.api.verify.assertFalse
import com.lemonappdev.konsist.api.verify.assertTrue
import com.lemonappdev.konsist.core.exception.KoAssertionFailedException
import com.lemonappdev.konsist.core.exception.KoPreconditionFailedException
import org.amshove.kluent.shouldContain
import org.amshove.kluent.shouldThrow
import org.amshove.kluent.withMessage
import org.junit.jupiter.api.Test

class AssertTrueOnDeclarationSequenceTest {
    @Test
    fun `declaration-assert-test-method-name-derived-from-junit-method-name`() {
        // given
        val sut =
            getSnippetFile("declaration-assert-test-method-name-derived-from-junit-method-name")
                .classes()
                .asSequence()

        // then
        try {
            sut.assertTrue { false }
        } catch (e: Exception) {
            e.message?.shouldContain(
                "Assert 'declaration-assert-test-method-name-derived-from-junit-method-name' was violated (1 time)",
            )
                ?: throw e
        }
    }

    @Test
    fun `declaration-assert-test-method-name-derived-from-test-name-parameter`() {
        // given
        val sut =
            getSnippetFile("declaration-assert-test-method-name-derived-from-test-name-parameter")
                .classes()
                .asSequence()

        // then
        try {
            sut.assertTrue(testName = "sample test") { false }
        } catch (e: Exception) {
            e.message?.shouldContain("Assert 'sample test' was violated (1 time)")
                ?: throw e
        }
    }

    @Test
    fun `file-declaration-assert-test-method-name-derived-from-junit-method-name`() {
        // given
        val sut =
            getSnippetFile("file-declaration-assert-test-method-name-derived-from-junit-method-name")
                .files
                .asSequence()

        // then
        try {
            sut.assertTrue { false }
        } catch (e: Exception) {
            e.message?.shouldContain(
                "Assert 'file-declaration-assert-test-method-name-derived-from-junit-method-name' was violated (1 time)",
            )
                ?: throw e
        }
    }

    @Test
    fun `file-declaration-assert-test-method-name-derived-from-test-name-parameter`() {
        // given
        val sut =
            getSnippetFile("file-declaration-assert-test-method-name-derived-from-test-name-parameter")
                .files
                .asSequence()

        // then
        try {
            sut.assertTrue(testName = "sample test") { false }
        } catch (e: Exception) {
            e.message?.shouldContain("Assert 'sample test' was violated (1 time)")
                ?: throw e
        }
    }

    @Test
    fun `declaration-assert-error-with-custom-message`() {
        // given
        val message = "CUSTOM ASSERT MESSAGE"
        val sut =
            getSnippetFile("declaration-assert-error-with-custom-message")
                .classes()
                .asSequence()

        // then
        try {
            sut.assertTrue(additionalMessage = message) { false }
        } catch (e: Exception) {
            e.message?.shouldContain(
                "Assert 'declaration-assert-error-with-custom-message' was violated (1 time)." +
                    "\n$message\nInvalid declarations:",
            )
                ?: throw e
        }
    }

    @Test
    fun `declaration-assert-error-with-custom-message-and-strict-set-to-true`() {
        // given
        val message = "CUSTOM ASSERT MESSAGE"
        val sut =
            getSnippetFile("declaration-assert-error-with-custom-message-and-strict-set-to-true")
                .classes()
                .asSequence()

        // then
        try {
            sut.assertTrue(strict = true, additionalMessage = message) { false }
        } catch (e: Exception) {
            e.message?.shouldContain(
                "Assert 'declaration-assert-error-with-custom-message-and-strict-set-to-true' was violated (1 time)." +
                    "\n$message\nInvalid declarations",
            )
                ?: throw e
        }
    }

    @Test
    fun `file-declaration-assert-error-with-custom-message`() {
        // given
        val message = "CUSTOM ASSERT MESSAGE"
        val sut =
            getSnippetFile("file-declaration-assert-error-with-custom-message")
                .files
                .asSequence()

        // then
        try {
            sut.assertTrue(additionalMessage = message) { false }
        } catch (e: Exception) {
            e.message?.shouldContain(
                "Assert 'file-declaration-assert-error-with-custom-message' was violated (1 time)." +
                    "\n$message\nInvalid files:",
            )
                ?: throw e
        }
    }

    @Test
    fun `file-declaration-assert-error-with-custom-message-and-strict-set-to-true`() {
        // given
        val message = "CUSTOM ASSERT MESSAGE"
        val sut =
            getSnippetFile("file-declaration-assert-error-with-custom-message-and-strict-set-to-true")
                .files
                .asSequence()

        // then
        try {
            sut.assertTrue(strict = true, additionalMessage = message) { false }
        } catch (e: Exception) {
            e.message?.shouldContain(
                "Assert 'file-declaration-assert-error-with-custom-message-and-strict-set-to-true' was violated (1 time)." +
                    "\n$message\nInvalid files:",
            )
                ?: throw e
        }
    }

    @Test
    fun `declaration-assert-displaying-correct-failed-declaration-type`() {
        // given
        val sut =
            getSnippetFile("declaration-assert-displaying-correct-failed-declaration-type")
                .classes()
                .asSequence()

        // then
        try {
            sut.assertTrue { false }
        } catch (e: Exception) {
            e.message?.shouldContain("(Class 'SampleClass')")
                ?: throw e
        }
    }

    @Test
    fun `file-declaration-assert-displaying-correct-failed-declaration-type`() {
        // given
        val sut =
            getSnippetFile("file-declaration-assert-displaying-correct-failed-declaration-type")
                .files
                .asSequence()

        // then
        try {
            sut.assertTrue { false }
        } catch (e: Exception) {
            e.message?.shouldContain("(File 'file-declaration-assert-displaying-correct-failed-declaration-type'")
                ?: throw e
        }
    }

    @Test
    fun `declaration-assert-passes-when-declaration-list-is-empty`() {
        // given
        val sut =
            getSnippetFile("declaration-assert-passes-when-declaration-list-is-empty")
                .classes()
                .asSequence()

        // when
        sut.assertTrue { true }
    }

    @Test
    fun `declaration-assert-false-passes-when-declaration-list-is-empty`() {
        // given
        val sut =
            getSnippetFile("declaration-assert-false-passes-when-declaration-list-is-empty")
                .classes()
                .asSequence()

        // when
        sut.assertFalse { false }
    }

    @Test
    fun `declaration-assert-strict-fails-when-declaration-list-is-empty`() {
        // given
        val sut =
            getSnippetFile("declaration-assert-strict-fails-when-declaration-list-is-empty")
                .classes()
                .asSequence()

        // when
        val func = {
            sut.assertTrue(strict = true) { true }
        }

        // then
        func shouldThrow KoPreconditionFailedException::class withMessage
            "Declaration list is empty. Please make sure that list of declarations contain items before calling " +
            "the 'assertTrue' method."
    }

    @Test
    fun `declaration-assert-false-strict-fails-when-declaration-list-is-empty`() {
        // given
        val sut =
            getSnippetFile("declaration-assert-false-strict-fails-when-declaration-list-is-empty")
                .classes()
                .asSequence()

        // when
        val func = {
            sut.assertFalse(strict = true) { false }
        }

        // then
        func shouldThrow KoPreconditionFailedException::class withMessage
            "Declaration list is empty. Please make sure that list of declarations contain items before calling " +
            "the 'assertFalse' method."
    }

    @Test
    fun `declaration-assert-passes-when-declaration-list-has-only-nulls`() {
        // given
        val sut =
            getSnippetFile("declaration-assert-passes-when-declaration-list-has-only-nulls")
                .classes()
                .map { it.primaryConstructor }
                .asSequence()

        // when
        sut.assertTrue { true }
    }

    @Test
    fun `declaration-assert-false-passes-when-declaration-list-has-only-nulls`() {
        // given
        val sut =
            getSnippetFile("declaration-assert-false-passes-when-declaration-list-has-only-nulls")
                .classes()
                .map { it.primaryConstructor }
                .asSequence()

        // when
        sut.assertFalse { false }
    }

    @Test
    fun `declaration-assert-strict-fails-when-declaration-list-has-only-nulls`() {
        // given
        val sut =
            getSnippetFile("declaration-assert-strict-fails-when-declaration-list-has-only-nulls")
                .classes()
                .map { it.primaryConstructor }
                .asSequence()

        // when
        val func = {
            sut.assertTrue(strict = true) { true }
        }

        // then
        func shouldThrow KoPreconditionFailedException::class withMessage
            "Declaration list contains only null elements. Please make sure that list of declarations contain items" +
            " before calling the 'assertTrue' method."
    }

    @Test
    fun `declaration-assert-false-strict-fails-when-declaration-list-has-only-nulls`() {
        // given
        val sut =
            getSnippetFile("declaration-assert-false-strict-fails-when-declaration-list-has-only-nulls")
                .classes()
                .map { it.primaryConstructor }
                .asSequence()

        // when
        val func = {
            sut.assertFalse(strict = true) { false }
        }

        // then
        func shouldThrow KoPreconditionFailedException::class withMessage
            "Declaration list contains only null elements. Please make sure that list of declarations contain items" +
            " before calling the 'assertFalse' method."
    }

    @Test
    fun `assert-passes`() {
        // given
        val sut =
            getSnippetFile("assert-passes")
                .classes()
                .asSequence()

        // then
        sut.assertTrue { it.name == "SampleClass" }
    }

    @Test
    fun `assert-fails`() {
        // given
        val sut =
            getSnippetFile("assert-fails")
                .classes()
                .asSequence()

        // when
        val func = {
            sut.assertTrue { it.name == "OtherName" }
        }

        // then
        func shouldThrow KoAssertionFailedException::class
    }

    @Test
    fun `assert-false-passes`() {
        // given
        val sut =
            getSnippetFile("assert-false-passes")
                .classes()
                .asSequence()

        // then
        sut.assertFalse {
            it.name == "OtherName"
        }
    }

    @Test
    fun `assert-false-fails`() {
        // given
        val sut =
            getSnippetFile("assert-false-fails")
                .classes()
                .asSequence()

        // when
        val func = {
            sut.assertFalse {
                it.name == "SampleClass"
            }
        }

        // then
        func shouldThrow KoAssertionFailedException::class
    }

    @Test
    fun `assert-passes-on-declarations-which-items-have-null-parent`() {
        // given
        val sut =
            getSnippetFile("assert-passes-on-declarations-which-items-have-null-parent")
                .files
                .asSequence()

        // then
        sut.assertTrue { it.name == "assert-passes-on-declarations-which-items-have-null-parent" }
    }

    @Test
    fun `assert-fails-on-declarations-which-items-have-null-parent`() {
        // given
        val sut =
            getSnippetFile("assert-fails-on-declarations-which-items-have-null-parent")
                .files
                .asSequence()

        // when
        val func = {
            sut.assertTrue { it.name == "OtherName" }
        }

        // then
        func shouldThrow KoAssertionFailedException::class
    }

    @Test
    fun `assert-false-passes-on-declarations-which-items-have-null-parent`() {
        // given
        val sut =
            getSnippetFile("assert-false-passes-on-declarations-which-items-have-null-parent")
                .files
                .asSequence()

        // then
        sut.assertFalse {
            it.name == "OtherName"
        }
    }

    @Test
    fun `assert-false-fails-on-declarations-which-items-have-null-parent`() {
        // given
        val sut =
            getSnippetFile("assert-false-fails-on-declarations-which-items-have-null-parent")
                .files
                .asSequence()

        // when
        val func = {
            sut.assertFalse {
                it.name == "assert-false-fails-on-declarations-which-items-have-null-parent"
            }
        }

        // then
        func shouldThrow KoAssertionFailedException::class
    }

    @Test
    fun `assert-passes-when-expression-is-nullable`() {
        // given
        val sut =
            getSnippetFile("assert-passes-when-expression-is-nullable")
                .classes()
                .asSequence()

        // then
        sut.assertTrue { it.primaryConstructor?.hasParameterWithName("sampleParameter") }
    }

    @Test
    fun `assert-fails-when-expression-is-nullable`() {
        // given
        val sut =
            getSnippetFile("assert-fails-when-expression-is-nullable")
                .classes()
                .asSequence()

        // when
        val func = {
            sut.assertTrue { it.primaryConstructor?.hasParameterWithName("sampleParameter") }
        }

        // then
        func shouldThrow KoAssertionFailedException::class
    }

    @Test
    fun `assert-false-passes-when-expression-is-nullable`() {
        // given
        val sut =
            getSnippetFile("assert-false-passes-when-expression-is-nullable")
                .classes()
                .asSequence()

        // then
        sut.assertFalse { it.primaryConstructor?.hasParameterWithName("otherParameter") }
    }

    @Test
    fun `assert-false-fails-when-expression-is-nullable`() {
        // given
        val sut =
            getSnippetFile("assert-false-fails-when-expression-is-nullable")
                .classes()
                .asSequence()

        // when
        val func = {
            sut.assertFalse { it.primaryConstructor?.hasParameterWithName("sampleParameter") }
        }

        // then
        func shouldThrow KoAssertionFailedException::class
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("core/verify/kodeclarationassert/asserttrue/snippet/", fileName)
}
