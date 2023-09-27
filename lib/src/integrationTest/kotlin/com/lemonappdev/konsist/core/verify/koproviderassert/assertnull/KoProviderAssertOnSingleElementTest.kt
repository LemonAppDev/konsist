package com.lemonappdev.konsist.core.verify.koproviderassert.assertnull

import com.lemonappdev.konsist.TestSnippetProvider
import com.lemonappdev.konsist.api.declaration.KoFileDeclaration
import com.lemonappdev.konsist.api.ext.list.declarations
import com.lemonappdev.konsist.api.provider.KoKotlinTypeProvider
import com.lemonappdev.konsist.api.provider.KoNameProvider
import com.lemonappdev.konsist.api.provider.KoPropertyProvider
import com.lemonappdev.konsist.api.provider.modifier.KoModifierProvider
import com.lemonappdev.konsist.api.verify.assertNotNull
import com.lemonappdev.konsist.api.verify.assertNull
import com.lemonappdev.konsist.api.verify.assertTrue
import com.lemonappdev.konsist.core.exception.KoCheckFailedException
import io.kotest.assertions.print.print
import org.amshove.kluent.shouldContain
import org.amshove.kluent.shouldThrow
import org.junit.jupiter.api.Test

class KoProviderAssertOnSingleElementTest {
    @Test
    fun `provider-assert-test-method-name`() {
        // given
        val sut = getSnippetFile("provider-assert-test-method-name")
            .declarations()
            .filterIsInstance<KoNameProvider>()
            .firstOrNull()

        // then
        try {
            sut.assertNull()
        } catch (e: Exception) {
            e.message?.shouldContain("Assert `provider-assert-test-method-name` failed.")
                ?: throw e
        }
    }

    @Test
    fun `provider-assert-null-error-with-custom-message`() {
        // given
        val message = "CUSTOM ASSERT MESSAGE"
        val sut = getSnippetFile("provider-assert-null-error-with-custom-message")
            .declarations()
            .filterNot { it is KoFileDeclaration }
            .filterIsInstance<KoNameProvider>()
            .firstOrNull()

        // then
        try {
            sut.assertNull(additionalMessage = message)
        } catch (e: Exception) {
            e.message?.shouldContain(
                "Assert `provider-assert-null-error-with-custom-message` failed.\n" +
                        "$message\nDeclaration has not null value: SampleClass.",
            )
                ?: throw e
        }
    }

    @Test
    fun `provider-assert-not-null-error-with-custom-message`() {
        // given
        val message = "CUSTOM ASSERT MESSAGE"
        val sut = getSnippetFile("provider-assert-not-null-error-with-custom-message")
            .declarations()
            .filterNot { it is KoFileDeclaration }
            .filterIsInstance<KoKotlinTypeProvider>()
            .firstOrNull()

        // then
        try {
            sut.assertNotNull(additionalMessage = message)
        } catch (e: Exception) {
            e.message?.shouldContain(
                "Assert `provider-assert-not-null-error-with-custom-message` failed.\n" +
                        "$message\nDeclaration has null value.",
            )
                ?: throw e
        }
    }

    @Test
    fun `assert-null-passes-when-item-has-null-value`() {
        // given
        val sut = getSnippetFile("assert-null-passes-when-item-has-null-value")
            .declarations()
            .filterIsInstance<KoKotlinTypeProvider>()
            .firstOrNull()

        // then
        sut.assertNull()
    }

    @Test
    fun `assert-null-fails-when-item-has-not-null-value`() {
        // given
        val sut = getSnippetFile("assert-null-fails-when-item-has-not-null-value")
            .declarations()
            .filterIsInstance<KoNameProvider>()
            .firstOrNull()

        // when
        val func = { sut.assertNull() }

        // then
        func shouldThrow KoCheckFailedException::class
    }

    @Test
    fun `assert-not-null-fails-when-item-has-null-value`() {
        // given
        val sut = getSnippetFile("assert-not-null-fails-when-item-has-null-value")
            .declarations()
            .filterIsInstance<KoKotlinTypeProvider>()
            .firstOrNull()

        // when
        val func = { sut.assertNotNull() }

        // then
        func shouldThrow KoCheckFailedException::class
    }

    @Test
    fun `assert-not-null-passes-when-item-has-not-null-value`() {
        // given
        val sut = getSnippetFile("assert-not-null-passes-when-item-has-not-null-value")
            .declarations()
            .filterIsInstance<KoNameProvider>()
            .firstOrNull()

        // then
        sut.assertNotNull()
    }

    @Test
    fun `assert-suppress-by-konsist-and-name-at-file-level-when-all-declarations-are-KoAnnotationProvider`() {
        // given
        val sut =
            getSnippetFile("assert-suppress-by-konsist-and-name-at-file-level-when-all-declarations-are-KoAnnotationProvider")
                .declarations(includeNested = true)
                .filterIsInstance<KoModifierProvider>()
                .last()

        // then
        sut.assertNull()
    }

    @Test
    fun `assert-suppress-by-name-at-file-level-when-all-declarations-are-KoAnnotationProvider`() {
        // given
        val sut =
            getSnippetFile("assert-suppress-by-name-at-file-level-when-all-declarations-are-KoAnnotationProvider")
                .declarations(includeNested = true)
                .filterIsInstance<KoModifierProvider>()
                .last()

        // then
        sut.assertNull()
    }

    @Test
    fun `assert-suppress-by-konsist-and-name-at-declaration-parent-level-when-all-declarations-are-KoAnnotationProvider`() {
        // given
        val sut =
            getSnippetFile("assert-suppress-by-konsist-and-name-at-declaration-parent-level-when-all-declarations-are-KoAnnotationProvider")
                .declarations(includeNested = true)
                .filterIsInstance<KoModifierProvider>()
                .last()

        // then
        sut.assertNull()
    }

    @Test
    fun `assert-suppress-by-name-at-declaration-parent-level-when-all-declarations-are-KoAnnotationProvider`() {
        // given
        val sut =
            getSnippetFile("assert-suppress-by-name-at-declaration-parent-level-when-all-declarations-are-KoAnnotationProvider")
                .declarations(includeNested = true)
                .filterIsInstance<KoModifierProvider>()
                .last()

        // then
        sut.assertNull()
    }

    @Test
    fun `assert-suppress-by-konsist-and-name-at-declaration-level-when-all-declarations-are-KoAnnotationProvider`() {
        // given
        val sut =
            getSnippetFile("assert-suppress-by-konsist-and-name-at-declaration-level-when-all-declarations-are-KoAnnotationProvider")
                .classes()
                .declarations()
                .filterIsInstance<KoModifierProvider>()
                .last()

        // then
        sut.assertNull()
    }

    @Test
    fun `assert-suppress-by-name-at-declaration-level-when-all-declarations-are-KoAnnotationProvider`() {
        // given
        val sut =
            getSnippetFile("assert-suppress-by-name-at-declaration-level-when-all-declarations-are-KoAnnotationProvider")
                .classes()
                .declarations()
                .filterIsInstance<KoModifierProvider>()
                .last()

        // then
        sut.assertNull()
    }

    @Test
    fun `assert-suppress-by-konsist-and-name-at-declaration-parent-level-when-it-is-not-KoAnnotationProvider`() {
        // given
        val sut =
            getSnippetFile("assert-suppress-by-konsist-and-name-at-declaration-parent-level-when-it-is-not-KoAnnotationProvider")
                .declarations(includeNested = true)
                .filterIsInstance<KoPropertyProvider>()
                .last()

        // then
        sut.assertNull()
    }

    @Test
    fun `assert-suppress-by-name-at-declaration-parent-level-when-it-is-not-KoAnnotationProvider`() {
        // given
        val sut =
            getSnippetFile("assert-suppress-by-name-at-declaration-parent-level-when-it-is-not-KoAnnotationProvider")
                .declarations(includeNested = true)
                .filterIsInstance<KoPropertyProvider>()
                .last()

        // then
        sut.assertNull()
    }

    @Test
    fun `assert-suppress-by-konsist-and-name-at-file-level-when-it-is-not-KoAnnotationProvider`() {
        // given
        val sut =
            getSnippetFile("assert-suppress-by-konsist-and-name-at-file-level-when-it-is-not-KoAnnotationProvider")
                .declarations(includeNested = true)
                .filterIsInstance<KoPropertyProvider>()
                .last()

        // then
        sut.assertNull()
    }

    @Test
    fun `assert-suppress-by-name-at-file-level-when-it-is-not-KoAnnotationProvider`() {
        // given
        val sut =
            getSnippetFile("assert-suppress-by-name-at-file-level-when-it-is-not-KoAnnotationProvider")
                .declarations(includeNested = true)
                .filterIsInstance<KoPropertyProvider>()
                .last()

        // then
        sut.assertNull()
    }

    @Test
    fun `assert-suppress-by-konsist-and-name-at-declaration-level-when-it-is-at-not-KoAnnotationProvider-declaration`() {
        // given
        val sut =
            getSnippetFile("assert-suppress-by-konsist-and-name-at-declaration-level-when-it-is-at-not-KoAnnotationProvider-declaration")
                .declarations(includeNested = true)
                .filterIsInstance<KoPropertyProvider>()
                .lastOrNull()

        // then
        sut.assertNull()
    }

    @Test
    fun `assert-suppress-by-name-at-declaration-level-when-it-is-at-not-KoAnnotationProvider-declaration`() {
        // given
        val sut =
            getSnippetFile("assert-suppress-by-name-at-declaration-level-when-it-is-at-not-KoAnnotationProvider-declaration")
                .declarations(includeNested = true)
                .filterIsInstance<KoPropertyProvider>()
                .lastOrNull()

        // then
        sut.assertNull()
    }

    @Test
    fun `assert-suppress-by-konsist-and-name-at-declaration-parent-level-when-it-is-at-not-KoAnnotationProvider-declaration`() {
        // given
        val sut =
            getSnippetFile(
                "assert-suppress-by-konsist-and-name-at-declaration-parent-level-when-it-is-at-not-KoAnnotationProvider-declaration",
            )
                .declarations(includeNested = true)
                .filterIsInstance<KoPropertyProvider>()
                .last()

        // then
        sut.assertNull()
    }

    @Test
    fun `assert-suppress-by-name-at-declaration-parent-level-when-it-is-at-not-KoAnnotationProvider-declaration`() {
        // given
        val sut =
            getSnippetFile("assert-suppress-by-name-at-declaration-parent-level-when-it-is-at-not-KoAnnotationProvider-declaration")
                .declarations(includeNested = true)
                .filterIsInstance<KoPropertyProvider>()
                .last()

        // then
        sut.assertNull()
    }

    @Test
    fun `assert-suppress-by-konsist-and-name-at-file-level-when-it-is-at-not-KoAnnotationProvider-declaration`() {
        // given
        val sut =
            getSnippetFile("assert-suppress-by-konsist-and-name-at-file-level-when-it-is-at-not-KoAnnotationProvider-declaration")
                .declarations(includeNested = true)
                .filterIsInstance<KoPropertyProvider>()
                .last()

        // then
        sut.assertNull()
    }

    @Test
    fun `assert-suppress-by-name-at-file-level-when-it-is-at-not-KoAnnotationProvider-declaration`() {
        // given
        val sut =
            getSnippetFile("assert-suppress-by-name-at-file-level-when-it-is-at-not-KoAnnotationProvider-declaration")
                .declarations(includeNested = true)
                .filterIsInstance<KoPropertyProvider>()
                .last()

        // then
        sut.assertNull()
    }

    @Test
    fun `assert-suppress-with-few-parameters`() {
        // given
        val sut =
            getSnippetFile("assert-suppress-with-few-parameters")
                .classes()
                .declarations()
                .filterIsInstance<KoModifierProvider>()
                .last()

        // then
        sut.assertNull()
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("core/verify/koproviderassert/assertnull/snippet/", fileName)
}
