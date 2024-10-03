package com.lemonappdev.konsist.core.verify.kodeclarationassert.assertempty

import com.lemonappdev.konsist.TestSnippetProvider
import com.lemonappdev.konsist.api.ext.list.provider.classes
import com.lemonappdev.konsist.api.ext.list.provider.initBlocks
import com.lemonappdev.konsist.api.ext.list.provider.localFunctions
import com.lemonappdev.konsist.api.verify.assertEmpty
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
    fun `assert-suppress-by-konsist-and-parameter-at-file-level-when-all-declarations-are-KoAnnotationProvider`() {
        // given
        val sut =
            getSnippetFile("assert-suppress-by-konsist-and-parameter-at-file-level-when-all-declarations-are-KoAnnotationProvider")
                .classes(includeNested = true)
                .asSequence()

        // then
        sut.assertEmpty(testName = "suppress-text")
    }

    @Test
    fun `assert-suppress-by-parameter-at-file-level-when-all-declarations-are-KoAnnotationProvider`() {
        // given
        val sut =
            getSnippetFile("assert-suppress-by-parameter-at-file-level-when-all-declarations-are-KoAnnotationProvider")
                .classes(includeNested = true)
                .asSequence()

        // then
        sut.assertEmpty(testName = "suppress-text")
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
    fun `assert-suppress-by-konsist-and-parameter-at-declaration-parent-level-when-all-declarations-are-KoAnnotationProvider`() {
        // given
        val sut =
            getSnippetFile(
                "assert-suppress-by-konsist-and-parameter-at-declaration-parent-level-when-all-declarations-are-KoAnnotationProvider",
            ).classes(includeNested = true)
                .asSequence()

        // then
        sut.assertEmpty(testName = "suppress-text")
    }

    @Test
    fun `assert-suppress-by-parameter-at-declaration-parent-level-when-all-declarations-are-KoAnnotationProvider`() {
        // given
        val sut =
            getSnippetFile("assert-suppress-by-parameter-at-declaration-parent-level-when-all-declarations-are-KoAnnotationProvider")
                .classes(includeNested = true)
                .asSequence()

        // then
        sut.assertEmpty(testName = "suppress-text")
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
    fun `assert-suppress-by-konsist-and-parameter-at-declaration-level-when-all-declarations-are-KoAnnotationProvider`() {
        // given
        val sut =
            getSnippetFile("assert-suppress-by-konsist-and-parameter-at-declaration-level-when-all-declarations-are-KoAnnotationProvider")
                .classes()
                .classes()
                .asSequence()

        // then
        sut.assertEmpty(testName = "suppress-text")
    }

    @Test
    fun `assert-suppress-by-parameter-at-declaration-level-when-all-declarations-are-KoAnnotationProvider`() {
        // given
        val sut =
            getSnippetFile("assert-suppress-by-parameter-at-declaration-level-when-all-declarations-are-KoAnnotationProvider")
                .classes()
                .classes()
                .asSequence()

        // then
        sut.assertEmpty(testName = "suppress-text")
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
    fun `assert-suppress-by-konsist-and-parameter-at-declaration-parent-level-when-it-is-not-KoAnnotationProvider`() {
        // given
        val sut =
            getSnippetFile("assert-suppress-by-konsist-and-parameter-at-declaration-parent-level-when-it-is-not-KoAnnotationProvider")
                .classes()
                .initBlocks
                .asSequence()

        // then
        sut.assertEmpty(testName = "suppress-text")
    }

    @Test
    fun `assert-suppress-by-parameter-at-declaration-parent-level-when-it-is-not-KoAnnotationProvider`() {
        // given
        val sut =
            getSnippetFile("assert-suppress-by-parameter-at-declaration-parent-level-when-it-is-not-KoAnnotationProvider")
                .classes()
                .initBlocks
                .asSequence()

        // then
        sut.assertEmpty(testName = "suppress-text")
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
    fun `assert-suppress-by-konsist-and-parameter-at-file-level-when-it-is-not-KoAnnotationProvider`() {
        // given
        val sut =
            getSnippetFile("assert-suppress-by-konsist-and-parameter-at-file-level-when-it-is-not-KoAnnotationProvider")
                .classes()
                .initBlocks
                .asSequence()

        // then
        sut.assertEmpty(testName = "suppress-text")
    }

    @Test
    fun `assert-suppress-by-parameter-at-file-level-when-it-is-not-KoAnnotationProvider`() {
        // given
        val sut =
            getSnippetFile("assert-suppress-by-parameter-at-file-level-when-it-is-not-KoAnnotationProvider")
                .classes()
                .initBlocks
                .asSequence()

        // then
        sut.assertEmpty(testName = "suppress-text")
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
    fun `assert-suppress-by-konsist-and-parameter-at-declaration-level-when-it-is-at-not-KoAnnotationProvider-declaration`() {
        // given
        val sut =
            getSnippetFile(
                "assert-suppress-by-konsist-and-parameter-at-declaration-level-when-it-is-at-not-KoAnnotationProvider-declaration",
            ).classes()
                .initBlocks
                .localFunctions
                .asSequence()

        // then
        sut.assertEmpty(testName = "suppress-text")
    }

    @Test
    fun `assert-suppress-by-parameter-at-declaration-level-when-it-is-at-not-KoAnnotationProvider-declaration`() {
        // given
        val sut =
            getSnippetFile("assert-suppress-by-parameter-at-declaration-level-when-it-is-at-not-KoAnnotationProvider-declaration")
                .classes()
                .initBlocks
                .localFunctions
                .asSequence()

        // then
        sut.assertEmpty(testName = "suppress-text")
    }

    @Test
    fun `assert-suppress-by-konsist-and-name-at-declaration-parent-level-when-it-is-at-not-KoAnnotationProvider-declaration`() {
        // given
        val sut =
            getSnippetFile(
                "assert-suppress-by-konsist-and-name-at-declaration-parent-level-when-it-is-at-not-KoAnnotationProvider-declaration",
            ).classes()
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
    fun `assert-suppress-by-konsist-and-parameter-at-declaration-parent-level-when-it-is-at-not-KoAnnotationProvider-declaration`() {
        // given
        val sut =
            getSnippetFile(
                "assert-suppress-by-konsist-and-parameter-at-declaration-parent-level-when-it-is-at-not-KoAnnotationProvider-declaration",
            ).classes()
                .initBlocks
                .localFunctions
                .asSequence()

        // then
        sut.assertEmpty(testName = "suppress-text")
    }

    @Test
    fun `assert-suppress-by-parameter-at-declaration-parent-level-when-it-is-at-not-KoAnnotationProvider-declaration`() {
        // given
        val sut =
            getSnippetFile("assert-suppress-by-parameter-at-declaration-parent-level-when-it-is-at-not-KoAnnotationProvider-declaration")
                .classes()
                .initBlocks
                .localFunctions
                .asSequence()

        // then
        sut.assertEmpty(testName = "suppress-text")
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
    fun `assert-suppress-by-konsist-and-parameter-at-file-level-when-it-is-at-not-KoAnnotationProvider-declaration`() {
        // given
        val sut =
            getSnippetFile("assert-suppress-by-konsist-and-parameter-at-file-level-when-it-is-at-not-KoAnnotationProvider-declaration")
                .classes()
                .initBlocks
                .localFunctions
                .asSequence()

        // then
        sut.assertEmpty(testName = "suppress-text")
    }

    @Test
    fun `assert-suppress-by-parameter-at-file-level-when-it-is-at-not-KoAnnotationProvider-declaration`() {
        // given
        val sut =
            getSnippetFile("assert-suppress-by-parameter-at-file-level-when-it-is-at-not-KoAnnotationProvider-declaration")
                .classes()
                .initBlocks
                .localFunctions
                .asSequence()

        // then
        sut.assertEmpty(testName = "suppress-text")
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
        sut.assertEmpty(testName = "suppress-text")
    }

    @Test
    fun `assert-suppress-with-suppress-name-parameter`() {
        // given
        val sut =
            getSnippetFile("assert-suppress-with-suppress-name-parameter")
                .functions(includeNested = true)
                .asSequence()

        // then
        sut.assertEmpty(testName = "konsist.assert-suppress-with-suppress-name-parameter")
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("core/verify/kodeclarationassert/assertempty/snippet/", fileName)
}
