package com.lemonappdev.konsist.core.verify.koproviderassert.deprecated

import com.lemonappdev.konsist.TestSnippetProvider
import com.lemonappdev.konsist.api.declaration.KoFileDeclaration
import com.lemonappdev.konsist.api.provider.KoAnnotationProvider
import com.lemonappdev.konsist.api.provider.KoNameProvider
import com.lemonappdev.konsist.api.provider.KoPrimaryConstructorProvider
import com.lemonappdev.konsist.api.provider.KoPropertyProvider
import com.lemonappdev.konsist.api.provider.modifier.KoModifierProvider
import com.lemonappdev.konsist.api.verify.assert
import com.lemonappdev.konsist.api.verify.assertNot
import com.lemonappdev.konsist.core.exception.KoCheckFailedException
import com.lemonappdev.konsist.core.exception.KoPreconditionFailedException
import org.amshove.kluent.shouldContain
import org.amshove.kluent.shouldThrow
import org.amshove.kluent.withMessage
import org.junit.jupiter.api.Test

@Deprecated("Will be removed in v0.16.0")
class DeprecatedKoProviderAssertOnSequenceTest {
    @Test
    fun `provider-assert-test-method-name`() {
        // given
        val sut = getSnippetFile("provider-assert-test-method-name")
            .declarations()
            .filterIsInstance<KoNameProvider>()
            .asSequence()

        // then
        try {
            sut.assert { false }
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
            sut.assert(message) { false }
        } catch (e: Exception) {
            e.message?.shouldContain(
                "Assert 'provider-assert-error-with-custom-message' was violated (2 times)." +
                    "\n$message\nInvalid declarations:",
            )
                ?: throw e
        }
    }

    @Test
    fun `provider-assert-fails-when-declaration-list-is-empty`() {
        // given
        val sut = getSnippetFile("provider-assert-fails-when-declaration-list-is-empty")
            .declarations()
            .filterNot { it is KoFileDeclaration }
            .filterIsInstance<KoNameProvider>()
            .asSequence()

        // when
        val func = {
            sut.assert { true }
        }

        // then
        func shouldThrow KoPreconditionFailedException::class withMessage
            "Declaration list is empty. Please make sure that list of declarations contain items before calling the 'assert' method."
    }

    @Test
    fun `provider-assert-not-fails-when-declaration-list-is-empty`() {
        // given
        val sut = getSnippetFile("provider-assert-not-fails-when-declaration-list-is-empty")
            .declarations()
            .filterNot { it is KoFileDeclaration }
            .filterIsInstance<KoNameProvider>()
            .asSequence()

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
            .declarations()
            .filterIsInstance<KoAnnotationProvider>()
            .asSequence()

        // then
        sut.assert { it.hasAnnotations() }
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
            sut.assert { it.hasAnnotations() }
        }

        // then
        func shouldThrow KoCheckFailedException::class
    }

    @Test
    fun `assert-not-passes`() {
        // given
        val sut = getSnippetFile("assert-not-passes")
            .declarations()
            .filterIsInstance<KoAnnotationProvider>()
            .asSequence()

        // then
        sut.assertNot { it.hasAnnotations() }
    }

    @Test
    fun `assert-not-fails`() {
        // given
        val sut = getSnippetFile("assert-not-fails")
            .declarations()
            .filterIsInstance<KoAnnotationProvider>()
            .asSequence()

        // when
        val func = {
            sut.assertNot { it.hasAnnotations() }
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
        sut.assert { it.primaryConstructor?.hasParameterNamed("sampleParameter") }
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
            sut.assert { it.primaryConstructor?.hasParameterNamed("sampleParameter") }
        }

        // then
        func shouldThrow KoCheckFailedException::class
    }

    @Test
    fun `assert-not-passes-when-expression-is-nullable`() {
        // given
        val sut = getSnippetFile("assert-not-passes-when-expression-is-nullable")
            .declarations()
            .filterIsInstance<KoPrimaryConstructorProvider>()
            .asSequence()

        // then
        sut.assertNot { it.primaryConstructor?.hasParameterNamed("otherParameter") }
    }

    @Test
    fun `assert-not-fails-when-expression-is-nullable`() {
        // given
        val sut = getSnippetFile("assert-not-fails-when-expression-is-nullable")
            .declarations()
            .filterIsInstance<KoPrimaryConstructorProvider>()
            .asSequence()

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
                .declarations(includeNested = true)
                .filterIsInstance<KoModifierProvider>()
                .asSequence()

        // then
        sut.assert { it.hasModifiers() }
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
        sut.assert { it.hasModifiers() }
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
        sut.assert { it.hasModifiers() }
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
        sut.assert { it.hasModifiers() }
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
        sut.assert { it.hasModifiers() }
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
        sut.assert { it.hasModifiers() }
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
        sut.assert { it.containsProperty { property -> property.name == "otherProperty" } }
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
        sut.assert { it.containsProperty { property -> property.name == "otherProperty" } }
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
        sut.assert { it.containsProperty { property -> property.name == "otherProperty" } }
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
        sut.assert { it.containsProperty { property -> property.name == "otherProperty" } }
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
        sut.assert { it.containsProperty { property -> property.name == "otherProperty" } }
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
        sut.assert { it.containsProperty { property -> property.name == "otherProperty" } }
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
        sut.assert { it.containsProperty { property -> property.name == "otherProperty" } }
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
        sut.assert { it.containsProperty { property -> property.name == "otherProperty" } }
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
        sut.assert { it.containsProperty { property -> property.name == "otherProperty" } }
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
        sut.assert { it.containsProperty { property -> property.name == "otherProperty" } }
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
        sut.assert { it.hasModifiers() }
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("core/verify/koproviderassert/deprecated/snippet/", fileName)
}
