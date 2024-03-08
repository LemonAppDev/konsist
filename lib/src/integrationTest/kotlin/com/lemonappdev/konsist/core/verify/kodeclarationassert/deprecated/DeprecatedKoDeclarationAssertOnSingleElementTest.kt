package com.lemonappdev.konsist.core.verify.kodeclarationassert.deprecated

import com.lemonappdev.konsist.TestSnippetProvider
import com.lemonappdev.konsist.api.ext.list.initBlocks
import com.lemonappdev.konsist.api.ext.list.localFunctions
import com.lemonappdev.konsist.api.verify.assert
import com.lemonappdev.konsist.api.verify.assertNot
import com.lemonappdev.konsist.core.exception.KoCheckFailedException
import org.amshove.kluent.shouldContain
import org.amshove.kluent.shouldThrow
import org.junit.jupiter.api.Test

@Deprecated("Will be removed in v0.16.0")
class DeprecatedKoDeclarationAssertOnSingleElementTest {
    @Test
    fun `declaration-assert-test-method-name`() {
        // given
        val sut = getSnippetFile("declaration-assert-test-method-name")
            .classes()
            .first()

        // then
        try {
            sut.assert { false }
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
            .first()

        // then
        try {
            sut.assert { false }
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
            .first()

        // then
        try {
            sut.assert(message) { false }
        } catch (e: Exception) {
            e.message?.shouldContain(
                "Assert 'declaration-assert-error-with-custom-message' was violated (1 time)." +
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
            .first()

        // then
        try {
            sut.assert(message) { false }
        } catch (e: Exception) {
            e.message?.shouldContain(
                "Assert 'file-declaration-assert-error-with-custom-message' was violated (1 time)." +
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
            .first()

        // then
        try {
            sut.assert { false }
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
            .first()

        // then
        try {
            sut.assert { false }
        } catch (e: Exception) {
            e.message?.shouldContain("(file-declaration-assert-displaying-correct-failed-declaration-type FileDeclaration)")
                ?: throw e
        }
    }

    @Test
    fun `assert-passes`() {
        // given
        val sut = getSnippetFile("assert-passes")
            .classes()
            .first()

        // then
        sut.assert { it.name == "SampleClass" }
    }

    @Test
    fun `assert-fails`() {
        // given
        val sut = getSnippetFile("assert-fails")
            .classes()
            .first()

        // when
        val func = {
            sut.assert { it.name == "OtherName" }
        }

        // then
        func shouldThrow KoCheckFailedException::class
    }

    @Test
    fun `assert-not-passes`() {
        // given
        val sut = getSnippetFile("assert-not-passes")
            .classes()
            .first()

        // then
        sut.assertNot {
            it.name == "OtherName"
        }
    }

    @Test
    fun `assert-not-fails`() {
        // given
        val sut = getSnippetFile("assert-not-fails")
            .classes()
            .first()

        // when
        val func = {
            sut.assertNot {
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
            .first()

        // then
        sut.assert { it.name == "assert-passes-on-declarations-which-items-have-null-parent" }
    }

    @Test
    fun `assert-fails-on-declarations-which-items-have-null-parent`() {
        // given
        val sut = getSnippetFile("assert-fails-on-declarations-which-items-have-null-parent")
            .files
            .first()

        // when
        val func = {
            sut.assert { it.name == "OtherName" }
        }

        // then
        func shouldThrow KoCheckFailedException::class
    }

    @Test
    fun `assert-not-passes-on-declarations-which-items-have-null-parent`() {
        // given
        val sut = getSnippetFile("assert-not-passes-on-declarations-which-items-have-null-parent")
            .files
            .first()

        // then
        sut.assertNot {
            it.name == "OtherName"
        }
    }

    @Test
    fun `assert-not-fails-on-declarations-which-items-have-null-parent`() {
        // given
        val sut = getSnippetFile("assert-not-fails-on-declarations-which-items-have-null-parent")
            .files
            .first()

        // when
        val func = {
            sut.assertNot {
                it.name == "assert-not-fails-on-declarations-which-items-have-null-parent"
            }
        }

        // then
        func shouldThrow KoCheckFailedException::class
    }

    @Test
    fun `assert-suppress-by-konsist-and-name-at-file-level-when-all-declarations-are-KoAnnotationProvider`() {
        // given
        val sut =
            getSnippetFile("assert-suppress-by-konsist-and-name-at-file-level-when-all-declarations-are-KoAnnotationProvider")
                .classes(includeNested = true)
                .last()

        // then
        sut.assert { it.name.endsWith("Text") }
    }

    @Test
    fun `assert-suppress-by-name-at-file-level-when-all-declarations-are-KoAnnotationProvider`() {
        // given
        val sut =
            getSnippetFile("assert-suppress-by-name-at-file-level-when-all-declarations-are-KoAnnotationProvider")
                .classes(includeNested = true)
                .last()

        // then
        sut.assert { it.name.endsWith("Text") }
    }

    @Test
    fun `assert-suppress-by-konsist-and-name-at-declaration-parent-level-when-all-declarations-are-KoAnnotationProvider`() {
        // given
        val sut =
            getSnippetFile("assert-suppress-by-konsist-and-name-at-declaration-parent-level-when-all-declarations-are-KoAnnotationProvider")
                .classes(includeNested = true)
                .last()

        // then
        sut.assert { it.name.endsWith("Text") }
    }

    @Test
    fun `assert-suppress-by-name-at-declaration-parent-level-when-all-declarations-are-KoAnnotationProvider`() {
        // given
        val sut =
            getSnippetFile("assert-suppress-by-name-at-declaration-parent-level-when-all-declarations-are-KoAnnotationProvider")
                .classes(includeNested = true)
                .last()

        // then
        sut.assert { it.name.endsWith("Text") }
    }

    @Test
    fun `assert-suppress-by-konsist-and-name-at-declaration-level-when-all-declarations-are-KoAnnotationProvider`() {
        // given
        val sut =
            getSnippetFile("assert-suppress-by-konsist-and-name-at-declaration-level-when-all-declarations-are-KoAnnotationProvider")
                .classes(includeNested = true)
                .last()

        // then
        sut.assert { it.name.endsWith("Text") }
    }

    @Test
    fun `assert-suppress-by-name-at-declaration-level-when-all-declarations-are-KoAnnotationProvider`() {
        // given
        val sut =
            getSnippetFile("assert-suppress-by-name-at-declaration-level-when-all-declarations-are-KoAnnotationProvider")
                .classes(includeNested = true)
                .last()

        // then
        sut.assert { it.name.endsWith("Text") }
    }

    @Test
    fun `assert-suppress-by-konsist-and-name-at-declaration-parent-level-when-it-is-not-KoAnnotationProvider`() {
        // given
        val sut =
            getSnippetFile("assert-suppress-by-konsist-and-name-at-declaration-parent-level-when-it-is-not-KoAnnotationProvider")
                .classes()
                .initBlocks
                .first()

        // then
        sut.assert { it.containsLocalFunction { function -> function.name == "otherFunction" } }
    }

    @Test
    fun `assert-suppress-by-name-at-declaration-parent-level-when-it-is-not-KoAnnotationProvider`() {
        // given
        val sut =
            getSnippetFile("assert-suppress-by-name-at-declaration-parent-level-when-it-is-not-KoAnnotationProvider")
                .classes()
                .initBlocks
                .first()

        // then
        sut.assert { it.containsLocalFunction { function -> function.name == "otherFunction" } }
    }

    @Test
    fun `assert-suppress-by-konsist-and-name-at-file-level-when-it-is-not-KoAnnotationProvider`() {
        // given
        val sut =
            getSnippetFile("assert-suppress-by-konsist-and-name-at-file-level-when-it-is-not-KoAnnotationProvider")
                .classes()
                .initBlocks
                .first()

        // then
        sut.assert { it.containsLocalFunction { function -> function.name == "otherFunction" } }
    }

    @Test
    fun `assert-suppress-by-name-at-file-level-when-it-is-not-KoAnnotationProvider`() {
        // given
        val sut =
            getSnippetFile("assert-suppress-by-name-at-file-level-when-it-is-not-KoAnnotationProvider")
                .classes()
                .initBlocks
                .first()

        // then
        sut.assert { it.containsLocalFunction { function -> function.name == "otherFunction" } }
    }

    @Test
    fun `assert-suppress-by-konsist-and-name-at-declaration-level-when-it-is-at-not-KoAnnotationProvider-declaration`() {
        // given
        val sut =
            getSnippetFile("assert-suppress-by-konsist-and-name-at-declaration-level-when-it-is-at-not-KoAnnotationProvider-declaration")
                .classes()
                .initBlocks
                .localFunctions
                .first()

        // then
        sut.assert { it.name.endsWith("Text") }
    }

    @Test
    fun `assert-suppress-by-name-at-declaration-level-when-it-is-at-not-KoAnnotationProvider-declaration`() {
        // given
        val sut =
            getSnippetFile("assert-suppress-by-name-at-declaration-level-when-it-is-at-not-KoAnnotationProvider-declaration")
                .classes()
                .initBlocks
                .localFunctions
                .first()

        // then
        sut.assert { it.name.endsWith("Text") }
    }

    @Test
    fun `assert-suppress-by-konsist-and-name-at-declaration-parent-level-when-it-is-at-not-KoAnnotationProvider-declaration`() {
        // given
        val sut =
            getSnippetFile(
                "assert-suppress-by-konsist-and-name-at-declaration-parent-level-when-it-is-at-not-KoAnnotationProvider-declaration",
            )
                .classes()
                .initBlocks
                .localFunctions
                .first()

        // then
        sut.assert { it.name.endsWith("Text") }
    }

    @Test
    fun `assert-suppress-by-name-at-declaration-parent-level-when-it-is-at-not-KoAnnotationProvider-declaration`() {
        // given
        val sut =
            getSnippetFile("assert-suppress-by-name-at-declaration-parent-level-when-it-is-at-not-KoAnnotationProvider-declaration")
                .classes()
                .initBlocks
                .localFunctions
                .first()

        // then
        sut.assert { it.name.endsWith("Text") }
    }

    @Test
    fun `assert-suppress-by-konsist-and-name-at-file-level-when-it-is-at-not-KoAnnotationProvider-declaration`() {
        // given
        val sut =
            getSnippetFile("assert-suppress-by-konsist-and-name-at-file-level-when-it-is-at-not-KoAnnotationProvider-declaration")
                .classes()
                .initBlocks
                .localFunctions
                .first()

        // then
        sut.assert { it.name.endsWith("Text") }
    }

    @Test
    fun `assert-suppress-by-name-at-file-level-when-it-is-at-not-KoAnnotationProvider-declaration`() {
        // given
        val sut =
            getSnippetFile("assert-suppress-by-name-at-file-level-when-it-is-at-not-KoAnnotationProvider-declaration")
                .classes()
                .initBlocks
                .localFunctions
                .first()

        // then
        sut.assert { it.name.endsWith("Text") }
    }

    @Test
    fun `assert-suppress-with-few-parameters`() {
        // given
        val sut =
            getSnippetFile("assert-suppress-with-few-parameters")
                .functions(includeNested = true)
                .first()

        // then
        sut.assert { it.name.endsWith("Text") }
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("core/verify/kodeclarationassert/deprecated/snippet/", fileName)
}
