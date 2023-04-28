package com.lemonappdev.konsist.core.verify.assert

import com.lemonappdev.konsist.TestSnippetProvider
import com.lemonappdev.konsist.core.exception.KoCheckFailedException
import com.lemonappdev.konsist.core.exception.KoPreconditionFailedException
import com.lemonappdev.konsist.core.ext.withPrimaryConstructor
import com.lemonappdev.konsist.core.verify.assert
import com.lemonappdev.konsist.core.verify.assertNot
import org.amshove.kluent.shouldContain
import org.amshove.kluent.shouldThrow
import org.amshove.kluent.withMessage
import org.junit.jupiter.api.Test

class AssertTest {
    @Test
    fun `assert-test-method-name`() {
        // given
        val sut = getSnippetFile("assert-test-method-name")
            .classes()

        // then
        try {
            sut.assert { false }
        } catch (e: Exception) {
            e.message?.shouldContain("Assert 'assert-test-method-name' has failed. Invalid declarations") ?: throw e
        }
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
    fun `assert-fails-when-declaration-list-is-empty`() {
        // given
        val sut = getSnippetFile("assert-fails-when-declaration-list-is-empty")
            .classes()

        // when
        val func = {
            sut.assert { true }
        }

        // then
        func shouldThrow KoPreconditionFailedException::class withMessage
            "Declaration list is empty. Please make sure that list of declarations contain items before calling 'assert' method."
    }

    @Test
    fun `assert-not-fails-when-declaration-list-is-empty`() {
        // given
        val sut = getSnippetFile("assert-not-fails-when-declaration-list-is-empty")
            .classes()

        // when
        val func = {
            sut.assertNot { false }
        }

        // then
        func shouldThrow KoPreconditionFailedException::class withMessage
            "Declaration list is empty. Please make sure that list of declarations contain items before calling 'assertNot' method."
    }

    @Test
    fun `assert-not-check-declarations-with-suppress-annotation`() {
        // given
        val sut = getSnippetFile("assert-not-check-declarations-with-suppress-annotation")
            .classes()

        // then
        sut.assert { it.name.endsWith("Class") }
    }

    @Test
    fun `assert-not-check-declarations-in-file-with-suppress-annotation-with-konsist-word`() {
        // given
        val sut = getSnippetFile("assert-not-check-declarations-in-file-with-suppress-annotation-with-konsist-word")
            .classes()

        // then
        sut.assert { it.name.endsWith("Class") }
    }

    @Test
    fun `assert-not-check-declarations-in-file-with-suppress-annotation-without-konsist-word`() {
        // given
        val sut = getSnippetFile("assert-not-check-declarations-in-file-with-suppress-annotation-without-konsist-word")
            .classes()

        // then
        sut.assert { it.name.endsWith("Class") }
    }

    @Test
    fun `assert-not-check-nested-declarations-in-file-with-suppress-annotation`() {
        // given
        val sut = getSnippetFile("assert-not-check-nested-declarations-in-file-with-suppress-annotation")
            .declarations(includeNested = true)

        // then
        sut.assert { it.name.endsWith("Class") }
    }

    @Test
    fun `assert-not-check-nested-declarations-when-top-level-declaration-has-suppress-annotation`() {
        // given
        val sut = getSnippetFile("assert-not-check-nested-declarations-when-top-level-declaration-has-suppress-annotation")
            .declarations(includeNested = true)

        // then
        sut.assert { it.name.endsWith("WithSuffix") }
    }

    @Test
    fun `assert-not-check-nested-declarations-with-suppress-annotation`() {
        // given
        val sut = getSnippetFile("assert-not-check-nested-declarations-with-suppress-annotation")
            .declarations(includeNested = true)

        // then
        sut.assert { it.name.endsWith("WithSuffix") }
    }

    private fun getSnippetFile(fileName: String) = TestSnippetProvider.getSnippetKoScope("core/verify/assert/snippet/", fileName)
}
