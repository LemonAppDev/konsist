package com.lemonappdev.konsist.core.verify.koproviderassert.assertempty

import com.lemonappdev.konsist.TestSnippetProvider
import com.lemonappdev.konsist.api.declaration.KoFileDeclaration
import com.lemonappdev.konsist.api.ext.list.localDeclarations
import com.lemonappdev.konsist.api.provider.KoKotlinTypeProvider
import com.lemonappdev.konsist.api.provider.KoLocalDeclarationProvider
import com.lemonappdev.konsist.api.provider.KoNameProvider
import com.lemonappdev.konsist.api.provider.KoPropertyProvider
import com.lemonappdev.konsist.api.provider.KoReturnTypeProvider
import com.lemonappdev.konsist.api.provider.modifier.KoModifierProvider
import com.lemonappdev.konsist.api.verify.assertEmpty
import com.lemonappdev.konsist.api.verify.assertNotEmpty
import com.lemonappdev.konsist.core.exception.KoCheckFailedException
import org.amshove.kluent.shouldContain
import org.amshove.kluent.shouldThrow
import org.junit.jupiter.api.Test

class SuppressAssertEmptyOnProviderSequenceTest {
    @Test
    fun `assert-suppress-by-konsist-and-name-at-file-level-when-all-declarations-are-KoAnnotationProvider`() {
        // given
        val sut =
            getSnippetFile("assert-suppress-by-konsist-and-name-at-file-level-when-all-declarations-are-KoAnnotationProvider")
                .declarations(includeNested = true)
                .filterIsInstance<KoModifierProvider>()
                .asSequence()

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
                .asSequence()

        // then
        sut.assertEmpty()
    }

    @Test
    fun `assert-suppress-by-konsist-and-name-at-declaration-parent-level-when-all-declarations-are-KoAnnotationProvider`() {
        // given
        val sut =
            getSnippetFile("assert-suppress-by-konsist-and-name-at-declaration-parent-level-when-all-declarations-are-KoAnnotationProvider")
                .declarations(includeNested = true)
                .filterIsInstance<KoModifierProvider>()
                .asSequence()

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
                .asSequence()

        // then
        sut.assertEmpty()
    }

    @Test
    fun `assert-suppress-by-konsist-and-name-at-declaration-level-when-all-declarations-are-KoAnnotationProvider`() {
        // given
        val sut =
            getSnippetFile("assert-suppress-by-konsist-and-name-at-declaration-level-when-all-declarations-are-KoAnnotationProvider")
                .declarations(includeNested = true)
                .filterIsInstance<KoLocalDeclarationProvider>()
                .asSequence()

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
                .asSequence()

        // then
        sut.assertEmpty()
    }

    @Test
    fun `assert-suppress-by-konsist-and-name-at-declaration-parent-level-when-it-is-not-KoAnnotationProvider`() {
        // given
        val sut =
            getSnippetFile("assert-suppress-by-konsist-and-name-at-declaration-parent-level-when-it-is-not-KoAnnotationProvider")
                .declarations(includeNested = true)
                .filterIsInstance<KoLocalDeclarationProvider>()
                .asSequence()

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
                .asSequence()

        // then
        sut.assertEmpty()
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
    fun `assert-suppress-by-konsist-and-name-at-declaration-level-when-it-is-at-not-KoAnnotationProvider-declaration`() {
        // given
        val sut =
            getSnippetFile("assert-suppress-by-konsist-and-name-at-declaration-level-when-it-is-at-not-KoAnnotationProvider-declaration")
                .declarations(includeNested = true)
                .filterIsInstance<KoLocalDeclarationProvider>()
                .localDeclarations
                .filterIsInstance<KoLocalDeclarationProvider>()
                .asSequence()

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
                .asSequence()

        // then
        sut.assertEmpty()
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
                .asSequence()

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
                .asSequence()

        // then
        sut.assertEmpty()
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
    fun `assert-suppress-with-few-parameters`() {
        // given
        val sut =
            getSnippetFile("assert-suppress-with-few-parameters")
                .declarations(includeNested = true)
                .filterIsInstance<KoLocalDeclarationProvider>()
                .asSequence()

        // then
        sut.assertEmpty()
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("core/verify/koproviderassert/assertempty/snippet/", fileName)
}
