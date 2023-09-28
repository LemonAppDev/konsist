package com.lemonappdev.konsist.core.verify.kodeclarationassert.assertempty

import com.lemonappdev.konsist.TestSnippetProvider
import com.lemonappdev.konsist.api.ext.list.classes
import com.lemonappdev.konsist.api.ext.list.initBlocks
import com.lemonappdev.konsist.api.ext.list.localFunctions
import com.lemonappdev.konsist.api.verify.assertEmpty
import com.lemonappdev.konsist.api.verify.assertNotEmpty
import com.lemonappdev.konsist.core.exception.KoCheckFailedException
import org.amshove.kluent.shouldContain
import org.amshove.kluent.shouldThrow
import org.junit.jupiter.api.Test

class SuppressAssertEmptyOnSequenceListTest {
    @Test
    fun `assert-suppress-by-konsist-and-name-at-file-level-when-all-declarations-are-KoAnnotationProvider`() {
        // given
        val sut =
            getSnippetFile("assert-suppress-by-konsist-and-name-at-file-level-when-all-declarations-are-KoAnnotationProvider")
                .classes(includeNested = true)
                .asSequence()

        // then
        sut.assertEmpty()
    }

    @Test
    fun `assert-suppress-by-name-at-file-level-when-all-declarations-are-KoAnnotationProvider`() {
        // given
        val sut =
            getSnippetFile("assert-suppress-by-name-at-file-level-when-all-declarations-are-KoAnnotationProvider")
                .classes(includeNested = true)
                .asSequence()

        // then
        sut.assertEmpty()
    }

    @Test
    fun `assert-suppress-by-konsist-and-suppress-name-parameter-at-file-level-when-all-declarations-are-KoAnnotationProvider`() {
        // given
        val sut =
            getSnippetFile("assert-suppress-by-konsist-and-suppress-name-parameter-at-file-level-when-all-declarations-are-KoAnnotationProvider")
                .classes(includeNested = true)
                .asSequence()

        // then
        sut.assertEmpty(suppressName = "suppress-text")
    }

    @Test
    fun `assert-suppress-by-suppress-name-parameter-at-file-level-when-all-declarations-are-KoAnnotationProvider`() {
        // given
        val sut =
            getSnippetFile("assert-suppress-by-suppress-name-parameter-at-file-level-when-all-declarations-are-KoAnnotationProvider")
                .classes(includeNested = true)
                .asSequence()

        // then
        sut.assertEmpty(suppressName = "suppress-text")
    }

    @Test
    fun `assert-suppress-by-konsist-and-name-at-declaration-parent-level-when-all-declarations-are-KoAnnotationProvider`() {
        // given
        val sut =
            getSnippetFile("assert-suppress-by-konsist-and-name-at-declaration-parent-level-when-all-declarations-are-KoAnnotationProvider")
                .classes(includeNested = true)
                .asSequence()

        // then
        sut.assertEmpty()
    }

    @Test
    fun `assert-suppress-by-name-at-declaration-parent-level-when-all-declarations-are-KoAnnotationProvider`() {
        // given
        val sut =
            getSnippetFile("assert-suppress-by-name-at-declaration-parent-level-when-all-declarations-are-KoAnnotationProvider")
                .classes(includeNested = true)
                .asSequence()

        // then
        sut.assertEmpty()
    }

    @Test
    fun `assert-suppress-by-konsist-and-suppress-name-parameter-at-declaration-parent-level-when-all-declarations-are-KoAnnotationProvider`() {
        // given
        val sut =
            getSnippetFile("assert-suppress-by-konsist-and-suppress-name-parameter-at-declaration-parent-level-when-all-declarations-are-KoAnnotationProvider")
                .classes(includeNested = true)
                .asSequence()

        // then
        sut.assertEmpty(suppressName = "suppress-text")
    }

    @Test
    fun `assert-suppress-by-suppress-name-parameter-at-declaration-parent-level-when-all-declarations-are-KoAnnotationProvider`() {
        // given
        val sut =
            getSnippetFile("assert-suppress-by-suppress-name-parameter-at-declaration-parent-level-when-all-declarations-are-KoAnnotationProvider")
                .classes(includeNested = true)
                .asSequence()

        // then
        sut.assertEmpty(suppressName = "suppress-text")
    }

    @Test
    fun `assert-suppress-by-konsist-and-name-at-declaration-level-when-all-declarations-are-KoAnnotationProvider`() {
        // given
        val sut =
            getSnippetFile("assert-suppress-by-konsist-and-name-at-declaration-level-when-all-declarations-are-KoAnnotationProvider")
                .classes()
                .classes()
                .asSequence()

        // then
        sut.assertEmpty()
    }

    @Test
    fun `assert-suppress-by-name-at-declaration-level-when-all-declarations-are-KoAnnotationProvider`() {
        // given
        val sut =
            getSnippetFile("assert-suppress-by-name-at-declaration-level-when-all-declarations-are-KoAnnotationProvider")
                .classes()
                .classes()
                .asSequence()

        // then
        sut.assertEmpty()
    }

    @Test
    fun `assert-suppress-by-konsist-and-suppress-name-parameter-at-declaration-level-when-all-declarations-are-KoAnnotationProvider`() {
        // given
        val sut =
            getSnippetFile("assert-suppress-by-konsist-and-suppress-name-parameter-at-declaration-level-when-all-declarations-are-KoAnnotationProvider")
                .classes()
                .classes()
                .asSequence()

        // then
        sut.assertEmpty(suppressName = "suppress-text")
    }

    @Test
    fun `assert-suppress-by-suppress-name-parameter-at-declaration-level-when-all-declarations-are-KoAnnotationProvider`() {
        // given
        val sut =
            getSnippetFile("assert-suppress-by-suppress-name-parameter-at-declaration-level-when-all-declarations-are-KoAnnotationProvider")
                .classes()
                .classes()
                .asSequence()

        // then
        sut.assertEmpty(suppressName = "suppress-text")
    }

    @Test
    fun `assert-suppress-by-konsist-and-name-at-declaration-parent-level-when-it-is-not-KoAnnotationProvider`() {
        // given
        val sut =
            getSnippetFile("assert-suppress-by-konsist-and-name-at-declaration-parent-level-when-it-is-not-KoAnnotationProvider")
                .classes()
                .initBlocks
                .asSequence()

        // then
        sut.assertEmpty()
    }

    @Test
    fun `assert-suppress-by-name-at-declaration-parent-level-when-it-is-not-KoAnnotationProvider`() {
        // given
        val sut =
            getSnippetFile("assert-suppress-by-name-at-declaration-parent-level-when-it-is-not-KoAnnotationProvider")
                .classes()
                .initBlocks
                .asSequence()

        // then
        sut.assertEmpty()
    }

    @Test
    fun `assert-suppress-by-konsist-and-suppress-name-parameter-at-declaration-parent-level-when-it-is-not-KoAnnotationProvider`() {
        // given
        val sut =
            getSnippetFile("assert-suppress-by-konsist-and-suppress-name-parameter-at-declaration-parent-level-when-it-is-not-KoAnnotationProvider")
                .classes()
                .initBlocks
                .asSequence()

        // then
        sut.assertEmpty(suppressName = "suppress-text")
    }

    @Test
    fun `assert-suppress-by-suppress-name-parameter-at-declaration-parent-level-when-it-is-not-KoAnnotationProvider`() {
        // given
        val sut =
            getSnippetFile("assert-suppress-by-suppress-name-parameter-at-declaration-parent-level-when-it-is-not-KoAnnotationProvider")
                .classes()
                .initBlocks
                .asSequence()

        // then
        sut.assertEmpty(suppressName = "suppress-text")
    }

    @Test
    fun `assert-suppress-by-konsist-and-name-at-file-level-when-it-is-not-KoAnnotationProvider`() {
        // given
        val sut =
            getSnippetFile("assert-suppress-by-konsist-and-name-at-file-level-when-it-is-not-KoAnnotationProvider")
                .classes()
                .initBlocks
                .asSequence()

        // then
        sut.assertEmpty()
    }

    @Test
    fun `assert-suppress-by-name-at-file-level-when-it-is-not-KoAnnotationProvider`() {
        // given
        val sut =
            getSnippetFile("assert-suppress-by-name-at-file-level-when-it-is-not-KoAnnotationProvider")
                .classes()
                .initBlocks
                .asSequence()

        // then
        sut.assertEmpty()
    }

    @Test
    fun `assert-suppress-by-konsist-and-suppress-name-parameter-at-file-level-when-it-is-not-KoAnnotationProvider`() {
        // given
        val sut =
            getSnippetFile("assert-suppress-by-konsist-and-suppress-name-parameter-at-file-level-when-it-is-not-KoAnnotationProvider")
                .classes()
                .initBlocks
                .asSequence()

        // then
        sut.assertEmpty(suppressName = "suppress-text")
    }

    @Test
    fun `assert-suppress-by-suppress-name-parameter-at-file-level-when-it-is-not-KoAnnotationProvider`() {
        // given
        val sut =
            getSnippetFile("assert-suppress-by-suppress-name-parameter-at-file-level-when-it-is-not-KoAnnotationProvider")
                .classes()
                .initBlocks
                .asSequence()

        // then
        sut.assertEmpty(suppressName = "suppress-text")
    }

    @Test
    fun `assert-suppress-by-konsist-and-name-at-declaration-level-when-it-is-at-not-KoAnnotationProvider-declaration`() {
        // given
        val sut =
            getSnippetFile("assert-suppress-by-konsist-and-name-at-declaration-level-when-it-is-at-not-KoAnnotationProvider-declaration")
                .classes()
                .initBlocks
                .localFunctions
                .asSequence()

        // then
        sut.assertEmpty()
    }

    @Test
    fun `assert-suppress-by-name-at-declaration-level-when-it-is-at-not-KoAnnotationProvider-declaration`() {
        // given
        val sut =
            getSnippetFile("assert-suppress-by-name-at-declaration-level-when-it-is-at-not-KoAnnotationProvider-declaration")
                .classes()
                .initBlocks
                .localFunctions
                .asSequence()

        // then
        sut.assertEmpty()
    }

    @Test
    fun `assert-suppress-by-konsist-and-suppress-name-parameter-at-declaration-level-when-it-is-at-not-KoAnnotationProvider-declaration`() {
        // given
        val sut =
            getSnippetFile("assert-suppress-by-konsist-and-suppress-name-parameter-at-declaration-level-when-it-is-at-not-KoAnnotationProvider-declaration")
                .classes()
                .initBlocks
                .localFunctions
                .asSequence()

        // then
        sut.assertEmpty(suppressName = "suppress-text")
    }

    @Test
    fun `assert-suppress-by-suppress-name-parameter-at-declaration-level-when-it-is-at-not-KoAnnotationProvider-declaration`() {
        // given
        val sut =
            getSnippetFile("assert-suppress-by-suppress-name-parameter-at-declaration-level-when-it-is-at-not-KoAnnotationProvider-declaration")
                .classes()
                .initBlocks
                .localFunctions
                .asSequence()

        // then
        sut.assertEmpty(suppressName = "suppress-text")
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
                .asSequence()

        // then
        sut.assertEmpty()
    }

    @Test
    fun `assert-suppress-by-name-at-declaration-parent-level-when-it-is-at-not-KoAnnotationProvider-declaration`() {
        // given
        val sut =
            getSnippetFile("assert-suppress-by-name-at-declaration-parent-level-when-it-is-at-not-KoAnnotationProvider-declaration")
                .classes()
                .initBlocks
                .localFunctions
                .asSequence()

        // then
        sut.assertEmpty()
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
                .asSequence()

        // then
        sut.assertEmpty(suppressName = "suppress-text")
    }

    @Test
    fun `assert-suppress-by-suppress-name-parameter-at-declaration-parent-level-when-it-is-at-not-KoAnnotationProvider-declaration`() {
        // given
        val sut =
            getSnippetFile("assert-suppress-by-suppress-name-parameter-at-declaration-parent-level-when-it-is-at-not-KoAnnotationProvider-declaration")
                .classes()
                .initBlocks
                .localFunctions
                .asSequence()

        // then
        sut.assertEmpty(suppressName = "suppress-text")
    }

    @Test
    fun `assert-suppress-by-konsist-and-name-at-file-level-when-it-is-at-not-KoAnnotationProvider-declaration`() {
        // given
        val sut =
            getSnippetFile("assert-suppress-by-konsist-and-name-at-file-level-when-it-is-at-not-KoAnnotationProvider-declaration")
                .classes()
                .initBlocks
                .localFunctions
                .asSequence()

        // then
        sut.assertEmpty()
    }

    @Test
    fun `assert-suppress-by-name-at-file-level-when-it-is-at-not-KoAnnotationProvider-declaration`() {
        // given
        val sut =
            getSnippetFile("assert-suppress-by-name-at-file-level-when-it-is-at-not-KoAnnotationProvider-declaration")
                .classes()
                .initBlocks
                .localFunctions
                .asSequence()

        // then
        sut.assertEmpty()
    }

    @Test
    fun `assert-suppress-by-konsist-and-suppress-name-parameter-at-file-level-when-it-is-at-not-KoAnnotationProvider-declaration`() {
        // given
        val sut =
            getSnippetFile("assert-suppress-by-konsist-and-suppress-name-parameter-at-file-level-when-it-is-at-not-KoAnnotationProvider-declaration")
                .classes()
                .initBlocks
                .localFunctions
                .asSequence()

        // then
        sut.assertEmpty(suppressName = "suppress-text")
    }

    @Test
    fun `assert-suppress-by-suppress-name-parameter-at-file-level-when-it-is-at-not-KoAnnotationProvider-declaration`() {
        // given
        val sut =
            getSnippetFile("assert-suppress-by-suppress-name-parameter-at-file-level-when-it-is-at-not-KoAnnotationProvider-declaration")
                .classes()
                .initBlocks
                .localFunctions
                .asSequence()

        // then
        sut.assertEmpty(suppressName = "suppress-text")
    }

    @Test
    fun `assert-suppress-with-few-parameters`() {
        // given
        val sut =
            getSnippetFile("assert-suppress-with-few-parameters")
                .functions(includeNested = true)
                .asSequence()

        // then
        sut.assertEmpty()
    }

    @Test
    fun `assert-suppress-by-parameter-with-few-parameters`() {
        // given
        val sut =
            getSnippetFile("assert-suppress-by-parameter-with-few-parameters")
                .functions(includeNested = true)
                .asSequence()

        // then
        sut.assertEmpty(suppressName = "suppress-text")
    }

    @Test
    fun `assert-suppress-with-suppress-name-parameter`() {
        // given
        val sut =
            getSnippetFile("assert-suppress-with-suppress-name-parameter")
                .functions(includeNested = true)
                .asSequence()

        // then
        sut.assertEmpty(suppressName = "konsist.assert-suppress-with-suppress-name-parameter")
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("core/verify/kodeclarationassert/assertempty/snippet/", fileName)
}
