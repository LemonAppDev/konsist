package com.lemonappdev.konsist.core.verify.kodeclarationassert.asserttrue

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

class SuppressAssertTrueOnSingleDeclarationTest {
    @Test
    fun `assert-suppress-by-konsist-and-name-at-file-level-when-all-declarations-are-KoAnnotationProvider`() {
        // given
        val sut =
            getSnippetFile("assert-suppress-by-konsist-and-name-at-file-level-when-all-declarations-are-KoAnnotationProvider")
                .classes(includeNested = true)
                .last()

        // then
        sut.assertTrue { it.name.endsWith("Text") }
    }

    @Test
    fun `assert-suppress-by-name-at-file-level-when-all-declarations-are-KoAnnotationProvider`() {
        // given
        val sut =
            getSnippetFile("assert-suppress-by-name-at-file-level-when-all-declarations-are-KoAnnotationProvider")
                .classes(includeNested = true)
                .last()

        // then
        sut.assertTrue { it.name.endsWith("Text") }
    }

    @Test
    fun `assert-suppress-by-konsist-and-suppress-name-parameter-at-file-level-when-all-declarations-are-KoAnnotationProvider`() {
        // given
        val sut =
            getSnippetFile("assert-suppress-by-konsist-and-suppress-name-parameter-at-file-level-when-all-declarations-are-KoAnnotationProvider")
                .classes(includeNested = true)
                .last()

        // then
        sut.assertTrue(suppressName = "suppress-text") { it.name.endsWith("Text") }
    }

    @Test
    fun `assert-suppress-by-suppress-name-parameter-at-file-level-when-all-declarations-are-KoAnnotationProvider`() {
        // given
        val sut =
            getSnippetFile("assert-suppress-by-suppress-name-parameter-at-file-level-when-all-declarations-are-KoAnnotationProvider")
                .classes(includeNested = true)
                .last()

        // then
        sut.assertTrue(suppressName = "suppress-text") { it.name.endsWith("Text") }
    }

    @Test
    fun `assert-suppress-by-konsist-and-name-at-declaration-parent-level-when-all-declarations-are-KoAnnotationProvider`() {
        // given
        val sut =
            getSnippetFile("assert-suppress-by-konsist-and-name-at-declaration-parent-level-when-all-declarations-are-KoAnnotationProvider")
                .classes(includeNested = true)
                .last()

        // then
        sut.assertTrue { it.name.endsWith("Text") }
    }

    @Test
    fun `assert-suppress-by-name-at-declaration-parent-level-when-all-declarations-are-KoAnnotationProvider`() {
        // given
        val sut =
            getSnippetFile("assert-suppress-by-name-at-declaration-parent-level-when-all-declarations-are-KoAnnotationProvider")
                .classes(includeNested = true)
                .last()

        // then
        sut.assertTrue { it.name.endsWith("Text") }
    }

    @Test
    fun `assert-suppress-by-konsist-and-suppress-name-parameter-at-declaration-parent-level-when-all-declarations-are-KoAnnotationProvider`() {
        // given
        val sut =
            getSnippetFile("assert-suppress-by-konsist-and-suppress-name-parameter-at-declaration-parent-level-when-all-declarations-are-KoAnnotationProvider")
                .classes(includeNested = true)
                .last()

        // then
        sut.assertTrue(suppressName = "suppress-text") { it.name.endsWith("Text") }
    }

    @Test
    fun `assert-suppress-by-suppress-name-parameter-at-declaration-parent-level-when-all-declarations-are-KoAnnotationProvider`() {
        // given
        val sut =
            getSnippetFile("assert-suppress-by-suppress-name-parameter-at-declaration-parent-level-when-all-declarations-are-KoAnnotationProvider")
                .classes(includeNested = true)
                .last()

        // then
        sut.assertTrue(suppressName = "suppress-text") { it.name.endsWith("Text") }
    }

    @Test
    fun `assert-suppress-by-konsist-and-name-at-declaration-level-when-all-declarations-are-KoAnnotationProvider`() {
        // given
        val sut =
            getSnippetFile("assert-suppress-by-konsist-and-name-at-declaration-level-when-all-declarations-are-KoAnnotationProvider")
                .classes(includeNested = true)
                .last()

        // then
        sut.assertTrue { it.name.endsWith("Text") }
    }

    @Test
    fun `assert-suppress-by-name-at-declaration-level-when-all-declarations-are-KoAnnotationProvider`() {
        // given
        val sut =
            getSnippetFile("assert-suppress-by-name-at-declaration-level-when-all-declarations-are-KoAnnotationProvider")
                .classes(includeNested = true)
                .last()

        // then
        sut.assertTrue { it.name.endsWith("Text") }
    }

    @Test
    fun `assert-suppress-by-konsist-and-suppress-name-parameter-at-declaration-level-when-all-declarations-are-KoAnnotationProvider`() {
        // given
        val sut =
            getSnippetFile("assert-suppress-by-konsist-and-suppress-name-parameter-at-declaration-level-when-all-declarations-are-KoAnnotationProvider")
                .classes(includeNested = true)
                .last()

        // then
        sut.assertTrue(suppressName = "suppress-text") { it.name.endsWith("Text") }
    }

    @Test
    fun `assert-suppress-by-suppress-name-parameter-at-declaration-level-when-all-declarations-are-KoAnnotationProvider`() {
        // given
        val sut =
            getSnippetFile("assert-suppress-by-suppress-name-parameter-at-declaration-level-when-all-declarations-are-KoAnnotationProvider")
                .classes(includeNested = true)
                .last()

        // then
        sut.assertTrue(suppressName = "suppress-text") { it.name.endsWith("Text") }
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
        sut.assertTrue { it.containsLocalFunction { function -> function.name == "otherFunction" } }
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
        sut.assertTrue { it.containsLocalFunction { function -> function.name == "otherFunction" } }
    }

    @Test
    fun `assert-suppress-by-konsist-and-suppress-name-parameter-at-declaration-parent-level-when-it-is-not-KoAnnotationProvider`() {
        // given
        val sut =
            getSnippetFile("assert-suppress-by-konsist-and-suppress-name-parameter-at-declaration-parent-level-when-it-is-not-KoAnnotationProvider")
                .classes()
                .initBlocks
                .first()

        // then
        sut.assertTrue(suppressName = "suppress-text") { it.containsLocalFunction { function -> function.name == "otherFunction" } }
    }

    @Test
    fun `assert-suppress-by-suppress-name-parameter-at-declaration-parent-level-when-it-is-not-KoAnnotationProvider`() {
        // given
        val sut =
            getSnippetFile("assert-suppress-by-suppress-name-parameter-at-declaration-parent-level-when-it-is-not-KoAnnotationProvider")
                .classes()
                .initBlocks
                .first()

        // then
        sut.assertTrue(suppressName = "suppress-text") { it.containsLocalFunction { function -> function.name == "otherFunction" } }
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
        sut.assertTrue { it.containsLocalFunction { function -> function.name == "otherFunction" } }
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
        sut.assertTrue { it.containsLocalFunction { function -> function.name == "otherFunction" } }
    }

    @Test
    fun `assert-suppress-by-konsist-and-suppress-name-parameter-at-file-level-when-it-is-not-KoAnnotationProvider`() {
        // given
        val sut =
            getSnippetFile("assert-suppress-by-konsist-and-suppress-name-parameter-at-file-level-when-it-is-not-KoAnnotationProvider")
                .classes()
                .initBlocks
                .first()

        // then
        sut.assertTrue(suppressName = "suppress-text") { it.containsLocalFunction { function -> function.name == "otherFunction" } }
    }

    @Test
    fun `assert-suppress-by-suppress-name-parameter-at-file-level-when-it-is-not-KoAnnotationProvider`() {
        // given
        val sut =
            getSnippetFile("assert-suppress-by-suppress-name-parameter-at-file-level-when-it-is-not-KoAnnotationProvider")
                .classes()
                .initBlocks
                .first()

        // then
        sut.assertTrue(suppressName = "suppress-text") { it.containsLocalFunction { function -> function.name == "otherFunction" } }
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
                .first()

        // then
        sut.assertTrue { it.name.endsWith("Text") }
    }

    @Test
    fun `assert-suppress-by-konsist-and-suppress-name-parameter-at-declaration-level-when-it-is-at-not-KoAnnotationProvider-declaration`() {
        // given
        val sut =
            getSnippetFile("assert-suppress-by-konsist-and-suppress-name-parameter-at-declaration-level-when-it-is-at-not-KoAnnotationProvider-declaration")
                .classes()
                .initBlocks
                .localFunctions
                .first()

        // then
        sut.assertTrue(suppressName = "suppress-text") { it.name.endsWith("Text") }
    }

    @Test
    fun `assert-suppress-by-suppress-name-parameter-at-declaration-level-when-it-is-at-not-KoAnnotationProvider-declaration`() {
        // given
        val sut =
            getSnippetFile("assert-suppress-by-suppress-name-parameter-at-declaration-level-when-it-is-at-not-KoAnnotationProvider-declaration")
                .classes()
                .initBlocks
                .localFunctions
                .first()

        // then
        sut.assertTrue(suppressName = "suppress-text") { it.name.endsWith("Text") }
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
                .first()

        // then
        sut.assertTrue { it.name.endsWith("Text") }
    }

    @Test
    fun `assert-suppress-by-konsist-and-suppress-name-parameter-at-declaration-parent-level-when-it-is-at-not-KoAnnotationProvider-declaration`() {
        // given
        val sut =
            getSnippetFile(
                "assert-suppress-by-konsist-and-suppress-name-parameter-at-declaration-parent-level-when-it-is-at-not-KoAnnotationProvider-declaration",
            )
                .classes()
                .initBlocks
                .localFunctions
                .first()

        // then
        sut.assertTrue(suppressName = "suppress-text") { it.name.endsWith("Text") }
    }

    @Test
    fun `assert-suppress-by-suppress-name-parameter-at-declaration-parent-level-when-it-is-at-not-KoAnnotationProvider-declaration`() {
        // given
        val sut =
            getSnippetFile("assert-suppress-by-suppress-name-parameter-at-declaration-parent-level-when-it-is-at-not-KoAnnotationProvider-declaration")
                .classes()
                .initBlocks
                .localFunctions
                .first()

        // then
        sut.assertTrue(suppressName = "suppress-text") { it.name.endsWith("Text") }
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
                .first()

        // then
        sut.assertTrue { it.name.endsWith("Text") }
    }

    @Test
    fun `assert-suppress-by-konsist-and-suppress-name-parameter-at-file-level-when-it-is-at-not-KoAnnotationProvider-declaration`() {
        // given
        val sut =
            getSnippetFile("assert-suppress-by-konsist-and-suppress-name-parameter-at-file-level-when-it-is-at-not-KoAnnotationProvider-declaration")
                .classes()
                .initBlocks
                .localFunctions
                .first()

        // then
        sut.assertTrue(suppressName = "suppress-text") { it.name.endsWith("Text") }
    }

    @Test
    fun `assert-suppress-by-suppress-name-parameter-at-file-level-when-it-is-at-not-KoAnnotationProvider-declaration`() {
        // given
        val sut =
            getSnippetFile("assert-suppress-by-suppress-name-parameter-at-file-level-when-it-is-at-not-KoAnnotationProvider-declaration")
                .classes()
                .initBlocks
                .localFunctions
                .first()

        // then
        sut.assertTrue(suppressName = "suppress-text") { it.name.endsWith("Text") }
    }

    @Test
    fun `assert-suppress-with-few-parameters`() {
        // given
        val sut =
            getSnippetFile("assert-suppress-with-few-parameters")
                .functions(includeNested = true)
                .first()

        // then
        sut.assertTrue { it.name.endsWith("Text") }
    }

    @Test
    fun `assert-suppress-by-parameter-with-few-parameters`() {
        // given
        val sut =
            getSnippetFile("assert-suppress-by-parameter-with-few-parameters")
                .functions(includeNested = true)
                .first()

        // then
        sut.assertTrue(suppressName = "suppress-text") { it.name.endsWith("Text") }
    }

    @Test
    fun `assert-suppress-with-suppress-name-parameter`() {
        // given
        val sut =
            getSnippetFile("assert-suppress-with-suppress-name-parameter")
                .functions(includeNested = true)
                .first()

        // then
        sut.assertTrue(suppressName = "konsist.assert-suppress-with-suppress-name-parameter") {
            it.name.endsWith("Kotest")
        }
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("core/verify/kodeclarationassert/asserttrue/snippet/", fileName)
}
