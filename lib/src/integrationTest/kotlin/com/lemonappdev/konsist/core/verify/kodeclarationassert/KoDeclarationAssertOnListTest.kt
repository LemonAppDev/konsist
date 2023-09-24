package com.lemonappdev.konsist.core.verify.kodeclarationassert

import com.lemonappdev.konsist.TestSnippetProvider
import com.lemonappdev.konsist.api.ext.list.initBlocks
import com.lemonappdev.konsist.api.ext.list.localFunctions
import com.lemonappdev.konsist.api.verify.assertFalse
import com.lemonappdev.konsist.api.verify.assertTrue
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
            sut.assertTrue { false }
        } catch (e: Exception) {
            e.message?.shouldContain("Assert 'declaration-assert-test-method-name' was violated (1 time)")
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
            sut.assertTrue { false }
        } catch (e: Exception) {
            e.message?.shouldContain("Assert 'file-declaration-assert-test-method-name' was violated (1 time)")
                ?: throw e
        }
    }

    @Test
    fun `declaration-assert-error-with-custom-message`() {
        // given
        val message = "CUSTOM ASSERT MESSAGE"
        val sut = getSnippetFile("declaration-assert-error-with-custom-message")
            .classes()

        // then
        try {
            sut.assertTrue(additionalMessage = message) { false }
        } catch (e: Exception) {
            e.message?.shouldContain(
                "Assert 'declaration-assert-error-with-custom-message' was violated (1 time)." +
                    "\n$message\nInvalid declarations",
            )
                ?: throw e
        }
    }

    @Test
    fun `declaration-assert-error-with-custom-message-and-strict-set-to-true`() {
        // given
        val message = "CUSTOM ASSERT MESSAGE"
        val sut = getSnippetFile("declaration-assert-error-with-custom-message-and-strict-set-to-true")
            .classes()

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
        val sut = getSnippetFile("file-declaration-assert-error-with-custom-message")
            .files

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
        val sut = getSnippetFile("file-declaration-assert-error-with-custom-message-and-strict-set-to-true")
            .files

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
        val sut = getSnippetFile("declaration-assert-displaying-correct-failed-declaration-type")
            .classes()

        // then
        try {
            sut.assertTrue { false }
        } catch (e: Exception) {
            e.message?.shouldContain("(SampleClass ClassDeclaration)")
                ?: throw e
        }
    }

    @Test
    fun `file-declaration-assert-displaying-correct-failed-declaration-type`() {
        // given
        val sut = getSnippetFile("file-declaration-assert-displaying-correct-failed-declaration-type")
            .files

        // then
        try {
            sut.assertTrue { false }
        } catch (e: Exception) {
            e.message?.shouldContain("(file-declaration-assert-displaying-correct-failed-declaration-type FileDeclaration)")
                ?: throw e
        }
    }

    @Test
    fun `declaration-assert-passes-when-declaration-list-is-empty`() {
        // given
        val sut = getSnippetFile("declaration-assert-passes-when-declaration-list-is-empty")
            .classes()

        // when
        sut.assertTrue { true }
    }

    @Test
    fun `declaration-assert-false-passes-when-declaration-list-is-empty`() {
        // given
        val sut = getSnippetFile("declaration-assert-false-passes-when-declaration-list-is-empty")
            .classes()

        // when
        sut.assertFalse { false }
    }

    @Test
    fun `declaration-assert-strict-fails-when-declaration-list-is-empty`() {
        // given
        val sut = getSnippetFile("declaration-assert-strict-fails-when-declaration-list-is-empty")
            .classes()

        // when
        val func = {
            sut.assertTrue(strict = true) { true }
        }

        // then
        func shouldThrow KoPreconditionFailedException::class withMessage
            "Declaration list is empty. Please make sure that list of declarations contain items before calling the 'assertTrue' method."
    }

    @Test
    fun `declaration-assert-false-strict-fails-when-declaration-list-is-empty`() {
        // given
        val sut = getSnippetFile("declaration-assert-false-strict-fails-when-declaration-list-is-empty")
            .classes()

        // when
        val func = {
            sut.assertFalse(strict = true) { false }
        }

        // then
        func shouldThrow KoPreconditionFailedException::class withMessage
            "Declaration list is empty. Please make sure that list of declarations contain items before calling the 'assertFalse' method."
    }

    @Test
    fun `declaration-assert-passes-when-declaration-list-has-only-nulls`() {
        // given
        val sut = getSnippetFile("declaration-assert-passes-when-declaration-list-has-only-nulls")
            .classes()
            .map { it.primaryConstructor }

        // when
        sut.assertTrue { true }
    }

    @Test
    fun `declaration-assert-false-passes-when-declaration-list-has-only-nulls`() {
        // given
        val sut = getSnippetFile("declaration-assert-false-passes-when-declaration-list-has-only-nulls")
            .classes()
            .map { it.primaryConstructor }

        // when
        sut.assertFalse { false }
    }

    @Test
    fun `declaration-assert-strict-fails-when-declaration-list-has-only-nulls`() {
        // given
        val sut = getSnippetFile("declaration-assert-strict-fails-when-declaration-list-has-only-nulls")
            .classes()
            .map { it.primaryConstructor }

        // when
        val func = {
            sut.assertTrue(strict = true) { true }
        }

        // then
        func shouldThrow KoPreconditionFailedException::class withMessage
            "Declaration list contains only null elements. Please make sure that list of declarations contain items " +
            "before calling the 'assertTrue' method."
    }

    @Test
    fun `declaration-assert-false-strict-fails-when-declaration-list-has-only-nulls`() {
        // given
        val sut = getSnippetFile("declaration-assert-false-strict-fails-when-declaration-list-has-only-nulls")
            .classes()
            .map { it.primaryConstructor }

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
        val sut = getSnippetFile("assert-passes")
            .classes()

        // then
        sut.assertTrue { it.name == "SampleClass" }
    }

    @Test
    fun `assert-fails`() {
        // given
        val sut = getSnippetFile("assert-fails")
            .classes()

        // when
        val func = {
            sut.assertTrue { it.name == "OtherName" }
        }

        // then
        func shouldThrow KoCheckFailedException::class
    }

    @Test
    fun `assert-false-passes`() {
        // given
        val sut = getSnippetFile("assert-false-passes")
            .classes()

        // then
        sut.assertFalse {
            it.name == "OtherName"
        }
    }

    @Test
    fun `assert-false-fails`() {
        // given
        val sut = getSnippetFile("assert-false-fails")
            .classes()

        // when
        val func = {
            sut.assertFalse {
                it.name == "SampleClass"
            }
        }

        // then
        func shouldThrow KoCheckFailedException::class
    }

    @Test
    fun `assert-passes-on-declarations-which-items-have-null-parent`() {
        // given
        val sut = getSnippetFile("assert-passes-on-declarations-which-items-have-null-parent")
            .files

        // then
        sut.assertTrue { it.name == "assert-passes-on-declarations-which-items-have-null-parent" }
    }

    @Test
    fun `assert-fails-on-declarations-which-items-have-null-parent`() {
        // given
        val sut = getSnippetFile("assert-fails-on-declarations-which-items-have-null-parent")
            .files

        // when
        val func = {
            sut.assertTrue { it.name == "OtherName" }
        }

        // then
        func shouldThrow KoCheckFailedException::class
    }

    @Test
    fun `assert-false-passes-on-declarations-which-items-have-null-parent`() {
        // given
        val sut = getSnippetFile("assert-false-passes-on-declarations-which-items-have-null-parent")
            .files

        // then
        sut.assertFalse {
            it.name == "OtherName"
        }
    }

    @Test
    fun `assert-false-fails-on-declarations-which-items-have-null-parent`() {
        // given
        val sut = getSnippetFile("assert-false-fails-on-declarations-which-items-have-null-parent")
            .files

        // when
        val func = {
            sut.assertFalse {
                it.name == "assert-false-fails-on-declarations-which-items-have-null-parent"
            }
        }

        // then
        func shouldThrow KoCheckFailedException::class
    }

    @Test
    fun `assert-passes-when-expression-is-nullable`() {
        // given
        val sut = getSnippetFile("assert-passes-when-expression-is-nullable")
            .classes()

        // then
        sut.assertTrue { it.primaryConstructor?.hasParameterWithName("sampleParameter") }
    }

    @Test
    fun `assert-fails-when-expression-is-nullable`() {
        // given
        val sut = getSnippetFile("assert-fails-when-expression-is-nullable")
            .classes()

        // when
        val func = {
            sut.assertTrue { it.primaryConstructor?.hasParameterWithName("sampleParameter") }
        }

        // then
        func shouldThrow KoCheckFailedException::class
    }

    @Test
    fun `assert-false-passes-when-expression-is-nullable`() {
        // given
        val sut = getSnippetFile("assert-false-passes-when-expression-is-nullable")
            .classes()

        // then
        sut.assertFalse { it.primaryConstructor?.hasParameterWithName("otherParameter") }
    }

    @Test
    fun `assert-false-fails-when-expression-is-nullable`() {
        // given
        val sut = getSnippetFile("assert-false-fails-when-expression-is-nullable")
            .classes()

        // when
        val func = {
            sut.assertFalse { it.primaryConstructor?.hasParameterWithName("sampleParameter") }
        }

        // then
        func shouldThrow KoCheckFailedException::class
    }

    @Test
    fun `assert-suppress-by-konsist-dot-name-and-name-at-file-level-when-all-declarations-are-KoAnnotationProvider`() {
        // given
        val sut =
            getSnippetFile("assert-suppress-by-konsist-dot-name-and-name-at-file-level-when-all-declarations-are-KoAnnotationProvider")
                .classes(includeNested = true)

        // then
        sut.assertTrue { it.name.endsWith("Text") }
    }

    @Test
    fun `assert-suppress-by-name-at-file-level-when-all-declarations-are-KoAnnotationProvider`() {
        // given
        val sut =
            getSnippetFile("assert-suppress-by-name-at-file-level-when-all-declarations-are-KoAnnotationProvider")
                .classes(includeNested = true)

        // then
        sut.assertTrue { it.name.endsWith("Text") }
    }

    @Test
    fun `assert-suppress-by-konsist-dot-name-and-name-at-declaration-parent-level-when-all-declarations-are-KoAnnotationProvider`() {
        // given
        val sut =
            getSnippetFile("assert-suppress-by-konsist-dot-name-and-name-at-declaration-parent-level-when-all-declarations-are-KoAnnotationProvider")
                .classes(includeNested = true)

        // then
        sut.assertTrue { it.name.endsWith("Text") }
    }

    @Test
    fun `assert-suppress-by-name-at-declaration-parent-level-when-all-declarations-are-KoAnnotationProvider`() {
        // given
        val sut =
            getSnippetFile("assert-suppress-by-name-at-declaration-parent-level-when-all-declarations-are-KoAnnotationProvider")
                .classes(includeNested = true)

        // then
        sut.assertTrue { it.name.endsWith("Text") }
    }

    @Test
    fun `assert-suppress-by-konsist-dot-name-and-name-at-declaration-level-when-all-declarations-are-KoAnnotationProvider`() {
        // given
        val sut =
            getSnippetFile("assert-suppress-by-konsist-dot-name-and-name-at-declaration-level-when-all-declarations-are-KoAnnotationProvider")
                .classes(includeNested = true)

        // then
        sut.assertTrue { it.name.endsWith("Text") }
    }

    @Test
    fun `assert-suppress-by-name-at-declaration-level-when-all-declarations-are-KoAnnotationProvider`() {
        // given
        val sut =
            getSnippetFile("assert-suppress-by-name-at-declaration-level-when-all-declarations-are-KoAnnotationProvider")
                .classes(includeNested = true)

        // then
        sut.assertTrue { it.name.endsWith("Text") }
    }

    @Test
    fun `assert-suppress-by-konsist-dot-name-and-name-at-declaration-parent-level-when-it-is-not-KoAnnotationProvider`() {
        // given
        val sut =
            getSnippetFile("assert-suppress-by-konsist-dot-name-and-name-at-declaration-parent-level-when-it-is-not-KoAnnotationProvider")
                .classes()
                .initBlocks

        // then
        sut.assertTrue { it.containsLocalFunction { function -> function.name == "otherFunction" } }
    }

    @Test
    fun `assert-suppress-by-name-at-declaration-parent-level-when-it-is-not-KoAnnotationProvider`() {
        // given
        val sut =
            getSnippetFile("assert-suppress-by-name-at-declaration-parent-level-when-it-is-not-KoAnnotationProvider")
                .classes()
                .initBlocks

        // then
        sut.assertTrue { it.containsLocalFunction { function -> function.name == "otherFunction" } }
    }

    @Test
    fun `assert-suppress-by-konsist-dot-name-and-name-at-file-level-when-it-is-not-KoAnnotationProvider`() {
        // given
        val sut =
            getSnippetFile("assert-suppress-by-konsist-dot-name-and-name-at-file-level-when-it-is-not-KoAnnotationProvider")
                .classes()
                .initBlocks

        // then
        sut.assertTrue { it.containsLocalFunction { function -> function.name == "otherFunction" } }
    }

    @Test
    fun `assert-suppress-by-name-at-file-level-when-it-is-not-KoAnnotationProvider`() {
        // given
        val sut =
            getSnippetFile("assert-suppress-by-name-at-file-level-when-it-is-not-KoAnnotationProvider")
                .classes()
                .initBlocks

        // then
        sut.assertTrue { it.containsLocalFunction { function -> function.name == "otherFunction" } }
    }

    @Test
    fun `assert-suppress-by-konsist-dot-name-and-name-at-declaration-level-when-it-is-at-not-KoAnnotationProvider-declaration`() {
        // given
        val sut =
            getSnippetFile("assert-suppress-by-konsist-dot-name-and-name-at-declaration-level-when-it-is-at-not-KoAnnotationProvider-declaration")
                .classes()
                .initBlocks
                .localFunctions

        // then
        sut.assertTrue { it.name.endsWith("Text") }
    }

    @Test
    fun `assert-suppress-by-name-at-declaration-level-when-it-is-at-not-KoAnnotationProvider-declaration`() {
        // given
        val sut =
            getSnippetFile("assert-suppress-by-name-at-declaration-level-when-it-is-at-not-KoAnnotationProvider-declaration")
                .classes()
                .initBlocks
                .localFunctions

        // then
        sut.assertTrue { it.name.endsWith("Text") }
    }

    @Test
    fun `assert-suppress-by-konsist-dot-name-and-name-at-declaration-parent-level-when-it-is-at-not-KoAnnotationProvider-declaration`() {
        // given
        val sut =
            getSnippetFile(
                "assert-suppress-by-konsist-dot-name-and-name-at-declaration-parent-level-when-it-is-at-not-KoAnnotationProvider-declaration",
            )
                .classes()
                .initBlocks
                .localFunctions

        // then
        sut.assertTrue { it.name.endsWith("Text") }
    }

    @Test
    fun `assert-suppress-by-name-at-declaration-parent-level-when-it-is-at-not-KoAnnotationProvider-declaration`() {
        // given
        val sut =
            getSnippetFile("assert-suppress-by-name-at-declaration-parent-level-when-it-is-at-not-KoAnnotationProvider-declaration")
                .classes()
                .initBlocks
                .localFunctions

        // then
        sut.assertTrue { it.name.endsWith("Text") }
    }

    @Test
    fun `assert-suppress-by-konsist-dot-name-and-name-at-file-level-when-it-is-at-not-KoAnnotationProvider-declaration`() {
        // given
        val sut =
            getSnippetFile("assert-suppress-by-konsist-dot-name-and-name-at-file-level-when-it-is-at-not-KoAnnotationProvider-declaration")
                .classes()
                .initBlocks
                .localFunctions

        // then
        sut.assertTrue { it.name.endsWith("Text") }
    }

    @Test
    fun `assert-suppress-by-name-at-file-level-when-it-is-at-not-KoAnnotationProvider-declaration`() {
        // given
        val sut =
            getSnippetFile("assert-suppress-by-name-at-file-level-when-it-is-at-not-KoAnnotationProvider-declaration")
                .classes()
                .initBlocks
                .localFunctions

        // then
        sut.assertTrue { it.name.endsWith("Text") }
    }

    @Test
    fun `assert-suppress-with-few-parameters`() {
        // given
        val sut =
            getSnippetFile("assert-suppress-with-few-parameters")
                .functions(includeNested = true)

        // then
        sut.assertTrue { it.name.endsWith("Text") }
    }

    @Test
    fun `assert-suppress-by-konsist-dot-name-and-name-on-declarations-which-items-have-null-parent`() {
        // given
        val scope1 = getSnippetFile("assert-suppress-by-konsist-dot-name-and-name-on-declarations-which-items-have-null-parent")
        val scope2 = getSnippetFile("file-without-suppress")

        val sut = (scope1 + scope2)
            .files

        // then
        sut.assertTrue { it.name.endsWith("suppress") }
    }

    @Test
    fun `assert-suppress-by-name-on-declarations-which-items-have-null-parent`() {
        // given
        val scope1 = getSnippetFile("assert-suppress-by-name-on-declarations-which-items-have-null-parent")
        val scope2 = getSnippetFile("file-without-suppress")

        val sut = (scope1 + scope2)
            .files

        // then
        sut.assertTrue { it.name.endsWith("suppress") }
    }

    @Test
    fun `assert-suppress-with-few-parameters-on-declarations-which-items-have-null-parent`() {
        // given
        val scope1 = getSnippetFile("assert-suppress-with-few-parameters-on-declarations-which-items-have-null-parent")
        val scope2 = getSnippetFile("file-without-suppress")

        val sut = (scope1 + scope2)
            .files

        // then
        sut.assertTrue { it.name.endsWith("suppress") }
    }

    @Test
    fun `assert-suppress-with-suppress-name-parameter`() {
        // given
        val sut =
            getSnippetFile("assert-suppress-with-suppress-name-parameter")
                .functions(includeNested = true)

        // then
        sut.assertTrue(suppressName = "konsist.assert-suppress-with-suppress-name-parameter") {
            it.name.endsWith("Kotest")
        }
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("core/verify/kodeclarationassert/snippet/", fileName)
}
