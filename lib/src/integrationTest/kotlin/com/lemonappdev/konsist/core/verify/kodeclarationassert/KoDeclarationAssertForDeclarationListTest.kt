package com.lemonappdev.konsist.core.verify.kodeclarationassert

import com.lemonappdev.konsist.TestSnippetProvider
import com.lemonappdev.konsist.api.ext.list.withPrimaryConstructor
import com.lemonappdev.konsist.api.verify.assert
import com.lemonappdev.konsist.api.verify.assertNot
import com.lemonappdev.konsist.core.exception.KoCheckFailedException
import com.lemonappdev.konsist.core.exception.KoPreconditionFailedException
import org.amshove.kluent.shouldContain
import org.amshove.kluent.shouldThrow
import org.amshove.kluent.withMessage
import org.junit.jupiter.api.Test

class KoDeclarationAssertForDeclarationListTest {
    @Test
    fun `declaration-assert-test-method-name`() {
        // given
        val sut = getSnippetFile("declaration-assert-test-method-name")
            .classes()

        // then
        try {
            sut.assert { false }
        } catch (e: Exception) {
            e.message?.shouldContain("Assert 'declaration-assert-test-method-name' has failed. Invalid declarations (1)")
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
            sut.assert { false }
        } catch (e: Exception) {
            e.message?.shouldContain("Assert 'file-declaration-assert-test-method-name' has failed. Invalid files (1)")
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

        // then
        try {
            sut.assert { false }
        } catch (e: Exception) {
            e.message?.shouldContain("(file-declaration-assert-displaying-correct-failed-declaration-type FileDeclaration)")
                ?: throw e
        }
    }

    @Test
    fun `declaration-assert-fails-when-declaration-list-is-empty`() {
        // given
        val sut = getSnippetFile("declaration-assert-fails-when-declaration-list-is-empty")
            .classes()

        // when
        val func = {
            sut.assert { true }
        }

        // then
        func shouldThrow KoPreconditionFailedException::class withMessage
            "Declaration list is empty. Please make sure that list of declarations contain items before calling the 'assert' method."
    }

    @Test
    fun `declaration-assert-not-fails-when-declaration-list-is-empty`() {
        // given
        val sut = getSnippetFile("declaration-assert-not-fails-when-declaration-list-is-empty")
            .classes()

        // when
        val func = {
            sut.assertNot { false }
        }

        // then
        func shouldThrow KoPreconditionFailedException::class withMessage
            "Declaration list is empty. Please make sure that list of declarations contain items before calling the 'assertNot' method."
    }

    @Test
    fun `assert-passes`() {
        // given
        val sut = getSnippetFile("assert-passes")
            .classes()

        // then
        sut.assert { it.name == "SampleClass" }
    }

    @Test
    fun `assert-fails`() {
        // given
        val sut = getSnippetFile("assert-fails")
            .classes()

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

        // then
        sut.assert { it.name == "assert-passes-on-declarations-which-items-have-null-parent" }
    }

    @Test
    fun `assert-fails-on-declarations-which-items-have-null-parent`() {
        // given
        val sut = getSnippetFile("assert-fails-on-declarations-which-items-have-null-parent")
            .files

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
    fun `assert-passes-when-expression-is-nullable`() {
        // given
        val sut = getSnippetFile("assert-passes-when-expression-is-nullable")
            .classes()
            .withPrimaryConstructor()

        // then
        sut.assert { it.primaryConstructor?.hasParameterNamed("sampleParameter") }
    }

    @Test
    fun `assert-fails-when-expression-is-nullable`() {
        // given
        val sut = getSnippetFile("assert-fails-when-expression-is-nullable")
            .classes()
            .withPrimaryConstructor()

        // when
        val func = {
            sut.assert { it.primaryConstructor?.hasParameterNamed("sampleParameter") }
        }

        // then
        func shouldThrow KoCheckFailedException::class
    }

    @Test
    fun `assert-not-passes-when-expression-is-nullable`() {
        // given
        val sut = getSnippetFile("assert-not-passes-when-expression-is-nullable")
            .classes()
            .withPrimaryConstructor()

        // then
        sut.assertNot { it.primaryConstructor?.hasParameterNamed("otherParameter") }
    }

    @Test
    fun `assert-not-fails-when-expression-is-nullable`() {
        // given
        val sut = getSnippetFile("assert-not-fails-when-expression-is-nullable")
            .classes()
            .withPrimaryConstructor()

        // when
        val func = {
            sut.assertNot { it.primaryConstructor?.hasParameterNamed("sampleParameter") }
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

        // then
        sut.assert { it.name.endsWith("Text") }
    }

    @Test
    fun `assert-suppress-by-name-at-file-level-when-all-declarations-are-KoAnnotationProvider`() {
        // given
        val sut =
            getSnippetFile("assert-suppress-by-name-at-file-level-when-all-declarations-are-KoAnnotationProvider")
                .classes(includeNested = true)

        // then
        sut.assert { it.name.endsWith("Text") }
    }

    @Test
    fun `assert-suppress-by-konsist-and-name-at-declaration-parent-level-when-all-declarations-are-KoAnnotationProvider`() {
        // given
        val sut =
            getSnippetFile("assert-suppress-by-konsist-and-name-at-declaration-parent-level-when-all-declarations-are-KoAnnotationProvider")
                .classes(includeNested = true)

        // then
        sut.assert { it.name.endsWith("Text") }
    }

    @Test
    fun `assert-suppress-by-name-at-declaration-parent-level-when-all-declarations-are-KoAnnotationProvider`() {
        // given
        val sut =
            getSnippetFile("assert-suppress-by-name-at-declaration-parent-level-when-all-declarations-are-KoAnnotationProvider")
                .classes(includeNested = true)

        // then
        sut.assert { it.name.endsWith("Text") }
    }

    @Test
    fun `assert-suppress-by-konsist-and-name-at-declaration-level-when-all-declarations-are-KoAnnotationProvider`() {
        // given
        val sut =
            getSnippetFile("assert-suppress-by-konsist-and-name-at-declaration-level-when-all-declarations-are-KoAnnotationProvider")
                .classes(includeNested = true)

        // then
        sut.assert { it.name.endsWith("Text") }
    }

    @Test
    fun `assert-suppress-by-name-at-declaration-level-when-all-declarations-are-KoAnnotationProvider`() {
        // given
        val sut =
            getSnippetFile("assert-suppress-by-name-at-declaration-level-when-all-declarations-are-KoAnnotationProvider")
                .classes(includeNested = true)

        // then
        sut.assert { it.name.endsWith("Text") }
    }

    @Test
    fun `assert-suppress-by-konsist-and-name-at-declaration-parent-level-when-it-is-not-KoAnnotationProvider`() {
        // given
        val sut =
            getSnippetFile("assert-suppress-by-konsist-and-name-at-declaration-parent-level-when-it-is-not-KoAnnotationProvider")
                .classes()
                .first()
                .initBlocks

        // then
        sut.assert { it.containsLocalFunction { function -> function.name == "otherFunction" } }
    }

    @Test
    fun `assert-suppress-by-name-at-declaration-parent-level-when-it-is-not-KoAnnotationProvider`() {
        // given
        val sut =
            getSnippetFile("assert-suppress-by-name-at-declaration-parent-level-when-it-is-not-KoAnnotationProvider")
                .classes()
                .first()
                .initBlocks

        // then
        sut.assert { it.containsLocalFunction { function -> function.name == "otherFunction" } }
    }

    @Test
    fun `assert-suppress-by-konsist-and-name-at-file-level-when-it-is-not-KoAnnotationProvider`() {
        // given
        val sut =
            getSnippetFile("assert-suppress-by-konsist-and-name-at-file-level-when-it-is-not-KoAnnotationProvider")
                .classes()
                .first()
                .initBlocks

        // then
        sut.assert { it.containsLocalFunction { function -> function.name == "otherFunction" } }
    }

    @Test
    fun `assert-suppress-by-name-at-file-level-when-it-is-not-KoAnnotationProvider`() {
        // given
        val sut =
            getSnippetFile("assert-suppress-by-name-at-file-level-when-it-is-not-KoAnnotationProvider")
                .classes()
                .first()
                .initBlocks

        // then
        sut.assert { it.containsLocalFunction { function -> function.name == "otherFunction" } }
    }

    @Test
    fun `assert-suppress-by-konsist-and-name-at-declaration-level-when-it-is-at-not-KoAnnotationProvider-declaration`() {
        // given
        val sut =
            getSnippetFile("assert-suppress-by-konsist-and-name-at-declaration-level-when-it-is-at-not-KoAnnotationProvider-declaration")
                .classes()
                .first()
                .initBlocks
                .first()
                .localFunctions

        // then
        sut.assert { it.name.endsWith("Text") }
    }

    @Test
    fun `assert-suppress-by-name-at-declaration-level-when-it-is-at-not-KoAnnotationProvider-declaration`() {
        // given
        val sut =
            getSnippetFile("assert-suppress-by-name-at-declaration-level-when-it-is-at-not-KoAnnotationProvider-declaration")
                .classes()
                .first()
                .initBlocks
                .first()
                .localFunctions

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
                .first()
                .initBlocks
                .first()
                .localFunctions

        // then
        sut.assert { it.name.endsWith("Text") }
    }

    @Test
    fun `assert-suppress-by-name-at-declaration-parent-level-when-it-is-at-not-KoAnnotationProvider-declaration`() {
        // given
        val sut =
            getSnippetFile("assert-suppress-by-name-at-declaration-parent-level-when-it-is-at-not-KoAnnotationProvider-declaration")
                .classes()
                .first()
                .initBlocks
                .first()
                .localFunctions

        // then
        sut.assert { it.name.endsWith("Text") }
    }

    @Test
    fun `assert-suppress-by-konsist-and-name-at-file-level-when-it-is-at-not-KoAnnotationProvider-declaration`() {
        // given
        val sut =
            getSnippetFile("assert-suppress-by-konsist-and-name-at-file-level-when-it-is-at-not-KoAnnotationProvider-declaration")
                .classes()
                .first()
                .initBlocks
                .first()
                .localFunctions

        // then
        sut.assert { it.name.endsWith("Text") }
    }

    @Test
    fun `assert-suppress-by-name-at-file-level-when-it-is-at-not-KoAnnotationProvider-declaration`() {
        // given
        val sut =
            getSnippetFile("assert-suppress-by-name-at-file-level-when-it-is-at-not-KoAnnotationProvider-declaration")
                .classes()
                .first()
                .initBlocks
                .first()
                .localFunctions

        // then
        sut.assert { it.name.endsWith("Text") }
    }

    @Test
    fun `assert-suppress-with-few-parameters`() {
        // given
        val sut =
            getSnippetFile("assert-suppress-with-few-parameters")
                .functions(includeNested = true)

        // then
        sut.assert { it.name.endsWith("Text") }
    }

    @Test
    fun `assert-suppress-by-konsist-and-name-on-declarations-which-items-have-null-parent`() {
        // given
        val scope1 = getSnippetFile("assert-suppress-by-konsist-and-name-on-declarations-which-items-have-null-parent")
        val scope2 = getSnippetFile("file-without-suppress")

        val sut = (scope1 + scope2)
            .files

        // then
        sut.assert { it.name.endsWith("suppress") }
    }

    @Test
    fun `assert-suppress-by-name-on-declarations-which-items-have-null-parent`() {
        // given
        val scope1 = getSnippetFile("assert-suppress-by-name-on-declarations-which-items-have-null-parent")
        val scope2 = getSnippetFile("file-without-suppress")

        val sut = (scope1 + scope2)
            .files

        // then
        sut.assert { it.name.endsWith("suppress") }
    }

    @Test
    fun `assert-suppress-with-few-parameters-on-declarations-which-items-have-null-parent`() {
        // given
        val scope1 = getSnippetFile("assert-suppress-with-few-parameters-on-declarations-which-items-have-null-parent")
        val scope2 = getSnippetFile("file-without-suppress")

        val sut = (scope1 + scope2)
            .files

        // then
        sut.assert { it.name.endsWith("suppress") }
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("core/verify/kodeclarationassert/snippet/", fileName)
}
