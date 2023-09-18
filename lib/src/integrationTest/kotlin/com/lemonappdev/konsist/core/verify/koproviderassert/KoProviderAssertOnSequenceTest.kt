package com.lemonappdev.konsist.core.verify.koproviderassert

import com.lemonappdev.konsist.TestSnippetProvider
import com.lemonappdev.konsist.api.declaration.KoFileDeclaration
import com.lemonappdev.konsist.api.provider.KoAnnotationProvider
import com.lemonappdev.konsist.api.provider.KoNameProvider
import com.lemonappdev.konsist.api.provider.KoPrimaryConstructorProvider
import com.lemonappdev.konsist.api.provider.KoPropertyProvider
import com.lemonappdev.konsist.api.provider.modifier.KoModifierProvider
import com.lemonappdev.konsist.api.verify.assertFalse
import com.lemonappdev.konsist.api.verify.assertTrue
import com.lemonappdev.konsist.core.exception.KoCheckFailedException
import com.lemonappdev.konsist.core.exception.KoPreconditionFailedException
import org.amshove.kluent.shouldContain
import org.amshove.kluent.shouldThrow
import org.amshove.kluent.withMessage
import org.junit.jupiter.api.Test

class KoProviderAssertOnSequenceTest {
    @Test
    fun `provider-assert-test-method-name`() {
        // given
        val sut = getSnippetFile("provider-assert-test-method-name")
            .declarations()
            .filterIsInstance<KoNameProvider>()
            .asSequence()

        // then
        try {
            sut.assertTrue { false }
        } catch (e: Exception) {
            e.message?.shouldContain("Assert 'provider-assert-test-method-name' was violated (2 times)")
                ?: throw e
        }
    }

    @Test
    fun `provider-assert-error-with-custom-message`() {
        // given
        val message = "CUSTOM ASSERT MESSAGE"
        val sut = getSnippetFile("provider-assert-error-with-custom-message")
            .declarations()
            .filterIsInstance<KoNameProvider>()
            .asSequence()

        // then
        try {
            sut.assertTrue(additionalMessage = message) { false }
        } catch (e: Exception) {
            e.message?.shouldContain(
                "Assert 'provider-assert-error-with-custom-message' was violated (2 times)." +
                    "\n$message\nInvalid declarations:",
            )
                ?: throw e
        }
    }

    @Test
    fun `provider-assert-passes-when-declaration-list-is-empty`() {
        // given
        val sut = getSnippetFile("provider-assert-passes-when-declaration-list-is-empty")
            .declarations()
            .filterNot { it is KoFileDeclaration }
            .filterIsInstance<KoNameProvider>()
            .asSequence()

        // when
        sut.assertTrue { true }
    }

    @Test
    fun `provider-assert-false-passes-when-declaration-list-is-empty`() {
        // given
        val sut = getSnippetFile("provider-assert-false-passes-when-declaration-list-is-empty")
            .declarations()
            .filterNot { it is KoFileDeclaration }
            .filterIsInstance<KoNameProvider>()
            .asSequence()

        // when
        sut.assertFalse { false }
    }

    @Test
    fun `provider-assert-strict-fails-when-declaration-list-is-empty`() {
        // given
        val sut = getSnippetFile("provider-assert-strict-fails-when-declaration-list-is-empty")
            .declarations()
            .filterNot { it is KoFileDeclaration }
            .filterIsInstance<KoNameProvider>()
            .asSequence()

        // when
        val func = {
            sut.assertTrue(strict = true) { true }
        }

        // then
        func shouldThrow KoPreconditionFailedException::class withMessage
            "Declaration list is empty. Please make sure that list of declarations contain items before calling the 'assertTrue' method."
    }

    @Test
    fun `provider-assert-false-strict-fails-when-declaration-list-is-empty`() {
        // given
        val sut = getSnippetFile("provider-assert-false-strict-fails-when-declaration-list-is-empty")
            .declarations()
            .filterNot { it is KoFileDeclaration }
            .filterIsInstance<KoNameProvider>()
            .asSequence()

        // when
        val func = {
            sut.assertFalse(strict = true) { false }
        }

        // then
        func shouldThrow KoPreconditionFailedException::class withMessage
            "Declaration list is empty. Please make sure that list of declarations contain items before calling the 'assertFalse' method."
    }

    @Test
    fun `assert-passes`() {
        // given
        val sut = getSnippetFile("assert-passes")
            .declarations()
            .filterIsInstance<KoAnnotationProvider>()
            .asSequence()

        // then
        sut.assertTrue { it.hasAnnotations() }
    }

    @Test
    fun `assert-fails`() {
        // given
        val sut = getSnippetFile("assert-fails")
            .declarations()
            .filterIsInstance<KoAnnotationProvider>()
            .asSequence()

        // when
        val func = {
            sut.assertTrue { it.hasAnnotations() }
        }

        // then
        func shouldThrow KoCheckFailedException::class
    }

    @Test
    fun `assert-false-passes`() {
        // given
        val sut = getSnippetFile("assert-false-passes")
            .declarations()
            .filterIsInstance<KoAnnotationProvider>()
            .asSequence()

        // then
        sut.assertFalse { it.hasAnnotations() }
    }

    @Test
    fun `assert-false-fails`() {
        // given
        val sut = getSnippetFile("assert-false-fails")
            .declarations()
            .filterIsInstance<KoAnnotationProvider>()
            .asSequence()

        // when
        val func = {
            sut.assertFalse { it.hasAnnotations() }
        }

        // then
        func shouldThrow KoCheckFailedException::class
    }

    @Test
    fun `assert-passes-when-expression-is-nullable`() {
        // given
        val sut = getSnippetFile("assert-passes-when-expression-is-nullable")
            .declarations()
            .filterIsInstance<KoPrimaryConstructorProvider>()
            .asSequence()

        // then
        sut.assertTrue { it.primaryConstructor?.hasParameterNamed("sampleParameter") }
    }

    @Test
    fun `assert-fails-when-expression-is-nullable`() {
        // given
        val sut = getSnippetFile("assert-fails-when-expression-is-nullable")
            .declarations()
            .filterIsInstance<KoPrimaryConstructorProvider>()
            .asSequence()

        // when
        val func = {
            sut.assertTrue { it.primaryConstructor?.hasParameterNamed("sampleParameter") }
        }

        // then
        func shouldThrow KoCheckFailedException::class
    }

    @Test
    fun `assert-false-passes-when-expression-is-nullable`() {
        // given
        val sut = getSnippetFile("assert-false-passes-when-expression-is-nullable")
            .declarations()
            .filterIsInstance<KoPrimaryConstructorProvider>()
            .asSequence()

        // then
        sut.assertFalse { it.primaryConstructor?.hasParameterNamed("otherParameter") }
    }

    @Test
    fun `assert-false-fails-when-expression-is-nullable`() {
        // given
        val sut = getSnippetFile("assert-false-fails-when-expression-is-nullable")
            .declarations()
            .filterIsInstance<KoPrimaryConstructorProvider>()
            .asSequence()

        // when
        val func = {
            sut.assertFalse { it.primaryConstructor?.hasParameterNamed("sampleParameter") }
        }

        // then
        func shouldThrow KoCheckFailedException::class
    }

    @Test
    fun `assert-suppress-by-konsist-and-name-at-file-level-when-all-declarations-are-KoAnnotationProvider`() {
        // given
        val sut =
            getSnippetFile("assert-suppress-by-konsist-and-name-at-file-level-when-all-declarations-are-KoAnnotationProvider")
                .declarations(includeNested = true)
                .filterIsInstance<KoModifierProvider>()
                .asSequence()

        // then
        sut.assertTrue { it.hasModifiers() }
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
        sut.assertTrue { it.hasModifiers() }
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
        sut.assertTrue { it.hasModifiers() }
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
        sut.assertTrue { it.hasModifiers() }
    }

    @Test
    fun `assert-suppress-by-konsist-and-name-at-declaration-level-when-all-declarations-are-KoAnnotationProvider`() {
        // given
        val sut =
            getSnippetFile("assert-suppress-by-konsist-and-name-at-declaration-level-when-all-declarations-are-KoAnnotationProvider")
                .declarations(includeNested = true)
                .filterIsInstance<KoModifierProvider>()
                .asSequence()

        // then
        sut.assertTrue { it.hasModifiers() }
    }

    @Test
    fun `assert-suppress-by-name-at-declaration-level-when-all-declarations-are-KoAnnotationProvider`() {
        // given
        val sut =
            getSnippetFile("assert-suppress-by-name-at-declaration-level-when-all-declarations-are-KoAnnotationProvider")
                .declarations(includeNested = true)
                .filterIsInstance<KoModifierProvider>()
                .asSequence()

        // then
        sut.assertTrue { it.hasModifiers() }
    }

    @Test
    fun `assert-suppress-by-konsist-and-name-at-declaration-parent-level-when-it-is-not-KoAnnotationProvider`() {
        // given
        val sut =
            getSnippetFile("assert-suppress-by-konsist-and-name-at-declaration-parent-level-when-it-is-not-KoAnnotationProvider")
                .declarations(includeNested = true)
                .filterIsInstance<KoPropertyProvider>()
                .asSequence()

        // then
        sut.assertTrue { it.containsProperty { property -> property.name == "otherProperty" } }
    }

    @Test
    fun `assert-suppress-by-name-at-declaration-parent-level-when-it-is-not-KoAnnotationProvider`() {
        // given
        val sut =
            getSnippetFile("assert-suppress-by-name-at-declaration-parent-level-when-it-is-not-KoAnnotationProvider")
                .declarations(includeNested = true)
                .filterIsInstance<KoPropertyProvider>()
                .asSequence()

        // then
        sut.assertTrue { it.containsProperty { property -> property.name == "otherProperty" } }
    }

    @Test
    fun `assert-suppress-by-konsist-and-name-at-file-level-when-it-is-not-KoAnnotationProvider`() {
        // given
        val sut =
            getSnippetFile("assert-suppress-by-konsist-and-name-at-file-level-when-it-is-not-KoAnnotationProvider")
                .declarations(includeNested = true)
                .filterIsInstance<KoPropertyProvider>()
                .asSequence()

        // then
        sut.assertTrue { it.containsProperty { property -> property.name == "otherProperty" } }
    }

    @Test
    fun `assert-suppress-by-name-at-file-level-when-it-is-not-KoAnnotationProvider`() {
        // given
        val sut =
            getSnippetFile("assert-suppress-by-name-at-file-level-when-it-is-not-KoAnnotationProvider")
                .declarations(includeNested = true)
                .filterIsInstance<KoPropertyProvider>()
                .asSequence()

        // then
        sut.assertTrue { it.containsProperty { property -> property.name == "otherProperty" } }
    }

    @Test
    fun `assert-suppress-by-konsist-and-name-at-declaration-level-when-it-is-at-not-KoAnnotationProvider-declaration`() {
        // given
        val sut =
            getSnippetFile("assert-suppress-by-konsist-and-name-at-declaration-level-when-it-is-at-not-KoAnnotationProvider-declaration")
                .declarations(includeNested = true)
                .filterIsInstance<KoPropertyProvider>()
                .asSequence()

        // then
        sut.assertTrue { it.containsProperty { property -> property.name == "otherProperty" } }
    }

    @Test
    fun `assert-suppress-by-name-at-declaration-level-when-it-is-at-not-KoAnnotationProvider-declaration`() {
        // given
        val sut =
            getSnippetFile("assert-suppress-by-name-at-declaration-level-when-it-is-at-not-KoAnnotationProvider-declaration")
                .declarations(includeNested = true)
                .filterIsInstance<KoPropertyProvider>()
                .asSequence()

        // then
        sut.assertTrue { it.containsProperty { property -> property.name == "otherProperty" } }
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
                .asSequence()

        // then
        sut.assertTrue { it.containsProperty { property -> property.name == "otherProperty" } }
    }

    @Test
    fun `assert-suppress-by-name-at-declaration-parent-level-when-it-is-at-not-KoAnnotationProvider-declaration`() {
        // given
        val sut =
            getSnippetFile("assert-suppress-by-name-at-declaration-parent-level-when-it-is-at-not-KoAnnotationProvider-declaration")
                .declarations(includeNested = true)
                .filterIsInstance<KoPropertyProvider>()
                .asSequence()

        // then
        sut.assertTrue { it.containsProperty { property -> property.name == "otherProperty" } }
    }

    @Test
    fun `assert-suppress-by-konsist-and-name-at-file-level-when-it-is-at-not-KoAnnotationProvider-declaration`() {
        // given
        val sut =
            getSnippetFile("assert-suppress-by-konsist-and-name-at-file-level-when-it-is-at-not-KoAnnotationProvider-declaration")
                .declarations(includeNested = true)
                .filterIsInstance<KoPropertyProvider>()
                .asSequence()

        // then
        sut.assertTrue { it.containsProperty { property -> property.name == "otherProperty" } }
    }

    @Test
    fun `assert-suppress-by-name-at-file-level-when-it-is-at-not-KoAnnotationProvider-declaration`() {
        // given
        val sut =
            getSnippetFile("assert-suppress-by-name-at-file-level-when-it-is-at-not-KoAnnotationProvider-declaration")
                .declarations(includeNested = true)
                .filterIsInstance<KoPropertyProvider>()
                .asSequence()

        // then
        sut.assertTrue { it.containsProperty { property -> property.name == "otherProperty" } }
    }

    @Test
    fun `assert-suppress-with-few-parameters`() {
        // given
        val sut =
            getSnippetFile("assert-suppress-with-few-parameters")
                .declarations(includeNested = true)
                .filterIsInstance<KoModifierProvider>()
                .asSequence()

        // then
        sut.assertTrue { it.hasModifiers() }
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("core/verify/koproviderassert/snippet/", fileName)
}
