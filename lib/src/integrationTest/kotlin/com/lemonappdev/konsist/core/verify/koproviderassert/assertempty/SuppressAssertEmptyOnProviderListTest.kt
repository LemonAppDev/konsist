package com.lemonappdev.konsist.core.verify.koproviderassert.assertempty

import com.lemonappdev.konsist.TestSnippetProvider
import com.lemonappdev.konsist.api.ext.list.localDeclarations
import com.lemonappdev.konsist.api.provider.KoLocalDeclarationProvider
import com.lemonappdev.konsist.api.provider.KoPropertyProvider
import com.lemonappdev.konsist.api.provider.modifier.KoModifierProvider
import com.lemonappdev.konsist.api.verify.assertEmpty
import org.junit.jupiter.api.Test

class SuppressAssertEmptyOnProviderListTest {
    @Test
    fun `assert-suppress-by-konsist-and-name-at-file-level-when-all-declarations-are-KoAnnotationProvider`() {
        // given
        val sut =
            getSnippetFile("assert-suppress-by-konsist-and-name-at-file-level-when-all-declarations-are-KoAnnotationProvider")
                .declarations(includeNested = true)
                .filterIsInstance<KoModifierProvider>()

        // then
        sut.assertEmpty()
    }

    @Test
    fun `assert-suppress-by-name-at-file-level-when-all-declarations-are-KoAnnotationProvider`() {
        // given
        val sut =
            getSnippetFile("assert-suppress-by-name-at-file-level-when-all-declarations-are-KoAnnotationProvider")
                .declarations(includeNested = true)
                .filterIsInstance<KoModifierProvider>()

        // then
        sut.assertEmpty()
    }

    @Test
    fun `assert-suppress-by-konsist-and-parameter-at-file-level-when-all-declarations-are-KoAnnotationProvider`() {
        // given
        val sut =
            getSnippetFile("assert-suppress-by-konsist-and-parameter-at-file-level-when-all-declarations-are-KoAnnotationProvider")
                .declarations(includeNested = true)
                .filterIsInstance<KoModifierProvider>()

        // then
        sut.assertEmpty(testName = "suppress-text")
    }

    @Test
    fun `assert-suppress-by-parameter-at-file-level-when-all-declarations-are-KoAnnotationProvider`() {
        // given
        val sut =
            getSnippetFile("assert-suppress-by-parameter-at-file-level-when-all-declarations-are-KoAnnotationProvider")
                .declarations(includeNested = true)
                .filterIsInstance<KoModifierProvider>()

        // then
        sut.assertEmpty(testName = "suppress-text")
    }

    @Test
    fun `assert-suppress-by-konsist-and-name-at-declaration-parent-level-when-all-declarations-are-KoAnnotationProvider`() {
        // given
        val sut =
            getSnippetFile("assert-suppress-by-konsist-and-name-at-declaration-parent-level-when-all-declarations-are-KoAnnotationProvider")
                .declarations(includeNested = true)
                .filterIsInstance<KoModifierProvider>()

        // then
        sut.assertEmpty()
    }

    @Test
    fun `assert-suppress-by-name-at-declaration-parent-level-when-all-declarations-are-KoAnnotationProvider`() {
        // given
        val sut =
            getSnippetFile("assert-suppress-by-name-at-declaration-parent-level-when-all-declarations-are-KoAnnotationProvider")
                .declarations(includeNested = true)
                .filterIsInstance<KoModifierProvider>()

        // then
        sut.assertEmpty()
    }

    @Test
    fun `assert-suppress-by-konsist-and-parameter-at-declaration-parent-level-when-all-declarations-are-KoAnnotationProvider`() {
        // given
        val sut =
            getSnippetFile(
                "assert-suppress-by-konsist-and-parameter-at-declaration-parent-level-when-all-declarations-are-KoAnnotationProvider",
            )
                .declarations(includeNested = true)
                .filterIsInstance<KoModifierProvider>()

        // then
        sut.assertEmpty(testName = "suppress-text")
    }

    @Test
    fun `assert-suppress-by-parameter-at-declaration-parent-level-when-all-declarations-are-KoAnnotationProvider`() {
        // given
        val sut =
            getSnippetFile("assert-suppress-by-parameter-at-declaration-parent-level-when-all-declarations-are-KoAnnotationProvider")
                .declarations(includeNested = true)
                .filterIsInstance<KoModifierProvider>()

        // then
        sut.assertEmpty(testName = "suppress-text")
    }

    @Test
    fun `assert-suppress-by-konsist-and-name-at-declaration-level-when-all-declarations-are-KoAnnotationProvider`() {
        // given
        val sut =
            getSnippetFile("assert-suppress-by-konsist-and-name-at-declaration-level-when-all-declarations-are-KoAnnotationProvider")
                .declarations(includeNested = true)
                .filterIsInstance<KoLocalDeclarationProvider>()

        // then
        sut.assertEmpty()
    }

    @Test
    fun `assert-suppress-by-name-at-declaration-level-when-all-declarations-are-KoAnnotationProvider`() {
        // given
        val sut =
            getSnippetFile("assert-suppress-by-name-at-declaration-level-when-all-declarations-are-KoAnnotationProvider")
                .declarations(includeNested = true)
                .filterIsInstance<KoLocalDeclarationProvider>()

        // then
        sut.assertEmpty()
    }

    @Test
    fun `assert-suppress-by-konsist-and-parameter-at-declaration-level-when-all-declarations-are-KoAnnotationProvider`() {
        // given
        val sut =
            getSnippetFile("assert-suppress-by-konsist-and-parameter-at-declaration-level-when-all-declarations-are-KoAnnotationProvider")
                .declarations(includeNested = true)
                .filterIsInstance<KoLocalDeclarationProvider>()

        // then
        sut.assertEmpty(testName = "suppress-text")
    }

    @Test
    fun `assert-suppress-by-parameter-at-declaration-level-when-all-declarations-are-KoAnnotationProvider`() {
        // given
        val sut =
            getSnippetFile("assert-suppress-by-parameter-at-declaration-level-when-all-declarations-are-KoAnnotationProvider")
                .declarations(includeNested = true)
                .filterIsInstance<KoLocalDeclarationProvider>()

        // then
        sut.assertEmpty(testName = "suppress-text")
    }

    @Test
    fun `assert-suppress-by-konsist-and-name-at-declaration-parent-level-when-it-is-not-KoAnnotationProvider`() {
        // given
        val sut =
            getSnippetFile("assert-suppress-by-konsist-and-name-at-declaration-parent-level-when-it-is-not-KoAnnotationProvider")
                .declarations(includeNested = true)
                .filterIsInstance<KoLocalDeclarationProvider>()

        // then
        sut.assertEmpty()
    }

    @Test
    fun `assert-suppress-by-name-at-declaration-parent-level-when-it-is-not-KoAnnotationProvider`() {
        // given
        val sut =
            getSnippetFile("assert-suppress-by-name-at-declaration-parent-level-when-it-is-not-KoAnnotationProvider")
                .declarations(includeNested = true)
                .filterIsInstance<KoLocalDeclarationProvider>()

        // then
        sut.assertEmpty()
    }

    @Test
    fun `assert-suppress-by-konsist-and-parameter-at-declaration-parent-level-when-it-is-not-KoAnnotationProvider`() {
        // given
        val sut =
            getSnippetFile("assert-suppress-by-konsist-and-parameter-at-declaration-parent-level-when-it-is-not-KoAnnotationProvider")
                .declarations(includeNested = true)
                .filterIsInstance<KoLocalDeclarationProvider>()

        // then
        sut.assertEmpty(testName = "suppress-text")
    }

    @Test
    fun `assert-suppress-by-parameter-at-declaration-parent-level-when-it-is-not-KoAnnotationProvider`() {
        // given
        val sut =
            getSnippetFile("assert-suppress-by-parameter-at-declaration-parent-level-when-it-is-not-KoAnnotationProvider")
                .declarations(includeNested = true)
                .filterIsInstance<KoLocalDeclarationProvider>()

        // then
        sut.assertEmpty(testName = "suppress-text")
    }

    @Test
    fun `assert-suppress-by-konsist-and-name-at-file-level-when-it-is-not-KoAnnotationProvider`() {
        // given
        val sut =
            getSnippetFile("assert-suppress-by-konsist-and-name-at-file-level-when-it-is-not-KoAnnotationProvider")
                .declarations(includeNested = true)
                .filterIsInstance<KoPropertyProvider>()

        // then
        sut.assertEmpty()
    }

    @Test
    fun `assert-suppress-by-name-at-file-level-when-it-is-not-KoAnnotationProvider`() {
        // given
        val sut =
            getSnippetFile("assert-suppress-by-name-at-file-level-when-it-is-not-KoAnnotationProvider")
                .declarations(includeNested = true)
                .filterIsInstance<KoPropertyProvider>()

        // then
        sut.assertEmpty()
    }

    @Test
    fun `assert-suppress-by-konsist-and-parameter-at-file-level-when-it-is-not-KoAnnotationProvider`() {
        // given
        val sut =
            getSnippetFile("assert-suppress-by-konsist-and-parameter-at-file-level-when-it-is-not-KoAnnotationProvider")
                .declarations(includeNested = true)
                .filterIsInstance<KoPropertyProvider>()

        // then
        sut.assertEmpty(testName = "suppress-text")
    }

    @Test
    fun `assert-suppress-by-parameter-at-file-level-when-it-is-not-KoAnnotationProvider`() {
        // given
        val sut =
            getSnippetFile("assert-suppress-by-parameter-at-file-level-when-it-is-not-KoAnnotationProvider")
                .declarations(includeNested = true)
                .filterIsInstance<KoPropertyProvider>()

        // then
        sut.assertEmpty(testName = "suppress-text")
    }

    @Test
    fun `assert-suppress-by-konsist-and-name-at-declaration-level-when-it-is-at-not-KoAnnotationProvider-declaration`() {
        // given
        val sut =
            getSnippetFile("assert-suppress-by-konsist-and-name-at-declaration-level-when-it-is-at-not-KoAnnotationProvider-declaration")
                .declarations(includeNested = true)
                .filterIsInstance<KoLocalDeclarationProvider>()
                .localDeclarations
                .filterIsInstance<KoLocalDeclarationProvider>()

        // then
        sut.assertEmpty()
    }

    @Test
    fun `assert-suppress-by-name-at-declaration-level-when-it-is-at-not-KoAnnotationProvider-declaration`() {
        // given
        val sut =
            getSnippetFile("assert-suppress-by-name-at-declaration-level-when-it-is-at-not-KoAnnotationProvider-declaration")
                .declarations(includeNested = true)
                .filterIsInstance<KoLocalDeclarationProvider>()
                .localDeclarations
                .filterIsInstance<KoLocalDeclarationProvider>()

        // then
        sut.assertEmpty()
    }

    @Test
    fun `assert-suppress-by-konsist-and-parameter-at-declaration-level-when-it-is-at-not-KoAnnotationProvider-declaration`() {
        // given
        val sut =
            getSnippetFile(
                "assert-suppress-by-konsist-and-parameter-at-declaration-level-when-it-is-at-not-KoAnnotationProvider-declaration",
            )
                .declarations(includeNested = true)
                .filterIsInstance<KoLocalDeclarationProvider>()
                .localDeclarations
                .filterIsInstance<KoLocalDeclarationProvider>()

        // then
        sut.assertEmpty(testName = "suppress-text")
    }

    @Test
    fun `assert-suppress-by-parameter-at-declaration-level-when-it-is-at-not-KoAnnotationProvider-declaration`() {
        // given
        val sut =
            getSnippetFile("assert-suppress-by-parameter-at-declaration-level-when-it-is-at-not-KoAnnotationProvider-declaration")
                .declarations(includeNested = true)
                .filterIsInstance<KoLocalDeclarationProvider>()
                .localDeclarations
                .filterIsInstance<KoLocalDeclarationProvider>()

        // then
        sut.assertEmpty(testName = "suppress-text")
    }

    @Test
    fun `assert-suppress-by-konsist-and-name-at-declaration-parent-level-when-it-is-at-not-KoAnnotationProvider-declaration`() {
        // given
        val sut =
            getSnippetFile(
                "assert-suppress-by-konsist-and-name-at-declaration-parent-level-when-it-is-at-not-KoAnnotationProvider-declaration",
            )
                .declarations(includeNested = true)
                .filterIsInstance<KoLocalDeclarationProvider>()

        // then
        sut.assertEmpty()
    }

    @Test
    fun `assert-suppress-by-name-at-declaration-parent-level-when-it-is-at-not-KoAnnotationProvider-declaration`() {
        // given
        val sut =
            getSnippetFile("assert-suppress-by-name-at-declaration-parent-level-when-it-is-at-not-KoAnnotationProvider-declaration")
                .declarations(includeNested = true)
                .filterIsInstance<KoLocalDeclarationProvider>()

        // then
        sut.assertEmpty()
    }

    @Test
    fun `assert-suppress-by-konsist-and-parameter-at-declaration-parent-level-when-it-is-at-not-KoAnnotationProvider-declaration`() {
        // given
        val sut =
            getSnippetFile(
                "assert-suppress-by-konsist-and-parameter-at-declaration-parent-level-when-it-is-at-not-KoAnnotationProvider-declaration",
            )
                .declarations(includeNested = true)
                .filterIsInstance<KoLocalDeclarationProvider>()

        // then
        sut.assertEmpty(testName = "suppress-text")
    }

    @Test
    fun `assert-suppress-by-parameter-at-declaration-parent-level-when-it-is-at-not-KoAnnotationProvider-declaration`() {
        // given
        val sut =
            getSnippetFile("assert-suppress-by-parameter-at-declaration-parent-level-when-it-is-at-not-KoAnnotationProvider-declaration")
                .declarations(includeNested = true)
                .filterIsInstance<KoLocalDeclarationProvider>()

        // then
        sut.assertEmpty(testName = "suppress-text")
    }

    @Test
    fun `assert-suppress-by-konsist-and-name-at-file-level-when-it-is-at-not-KoAnnotationProvider-declaration`() {
        // given
        val sut =
            getSnippetFile("assert-suppress-by-konsist-and-name-at-file-level-when-it-is-at-not-KoAnnotationProvider-declaration")
                .declarations(includeNested = true)
                .filterIsInstance<KoPropertyProvider>()

        // then
        sut.assertEmpty()
    }

    @Test
    fun `assert-suppress-by-name-at-file-level-when-it-is-at-not-KoAnnotationProvider-declaration`() {
        // given
        val sut =
            getSnippetFile("assert-suppress-by-name-at-file-level-when-it-is-at-not-KoAnnotationProvider-declaration")
                .declarations(includeNested = true)
                .filterIsInstance<KoPropertyProvider>()

        // then
        sut.assertEmpty()
    }

    @Test
    fun `assert-suppress-by-konsist-and-parameter-at-file-level-when-it-is-at-not-KoAnnotationProvider-declaration`() {
        // given
        val sut =
            getSnippetFile("assert-suppress-by-konsist-and-parameter-at-file-level-when-it-is-at-not-KoAnnotationProvider-declaration")
                .declarations(includeNested = true)
                .filterIsInstance<KoPropertyProvider>()

        // then
        sut.assertEmpty(testName = "suppress-text")
    }

    @Test
    fun `assert-suppress-by-parameter-at-file-level-when-it-is-at-not-KoAnnotationProvider-declaration`() {
        // given
        val sut =
            getSnippetFile("assert-suppress-by-parameter-at-file-level-when-it-is-at-not-KoAnnotationProvider-declaration")
                .declarations(includeNested = true)
                .filterIsInstance<KoPropertyProvider>()

        // then
        sut.assertEmpty(testName = "suppress-text")
    }

    @Test
    fun `assert-suppress-with-few-parameters`() {
        // given
        val sut =
            getSnippetFile("assert-suppress-with-few-parameters")
                .declarations(includeNested = true)
                .filterIsInstance<KoLocalDeclarationProvider>()

        // then
        sut.assertEmpty()
    }

    @Test
    fun `assert-suppress-by-parameter-with-few-parameters`() {
        // given
        val sut =
            getSnippetFile("assert-suppress-by-parameter-with-few-parameters")
                .declarations(includeNested = true)
                .filterIsInstance<KoLocalDeclarationProvider>()

        // then
        sut.assertEmpty(testName = "suppress-text")
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("core/verify/koproviderassert/assertempty/snippet/", fileName)
}
