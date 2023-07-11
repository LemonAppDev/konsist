package com.lemonappdev.konsist.core.verify.kodeclarationassert

import com.lemonappdev.konsist.TestSnippetProvider
import com.lemonappdev.konsist.api.ext.sequence.withPrimaryConstructor
import com.lemonappdev.konsist.core.exception.KoCheckFailedException
import com.lemonappdev.konsist.core.verify.assert
import com.lemonappdev.konsist.core.verify.assertNot
import org.amshove.kluent.shouldThrow
import org.junit.jupiter.api.Test

class KoDeclarationAssertTest {
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
    fun `assert-suppress-by-konsist-and-name-at-file-level`() {
        // given
        val sut = getSnippetFile("assert-suppress-by-konsist-and-name-at-file-level")
            .declarations(includeNested = true)

        // then
        sut.assert { it.name.endsWith("Text") }
    }

    @Test
    fun `assert-suppress-by-name-at-file-level`() {
        // given
        val sut = getSnippetFile("assert-suppress-by-name-at-file-level")
            .declarations(includeNested = true)

        // then
        sut.assert { it.name.endsWith("Text") }
    }

    @Test
    fun `assert-suppress-by-konsist-and-name-at-declaration-parent-level`() {
        // given
        val sut =
            getSnippetFile("assert-suppress-by-konsist-and-name-at-declaration-parent-level")
                .declarations(includeNested = true)

        // then
        sut.assert { it.name.endsWith("Text") }
    }

    @Test
    fun `assert-suppress-by-name-at-declaration-parent-level`() {
        // given
        val sut =
            getSnippetFile("assert-suppress-by-name-at-declaration-parent-level")
                .declarations(includeNested = true)

        // then
        sut.assert { it.name.endsWith("Text") }
    }

    @Test
    fun `assert-suppress-by-konsist-and-name-at-declaration-level`() {
        // given
        val sut = getSnippetFile("assert-suppress-by-konsist-and-name-at-declaration-level")
            .declarations(includeNested = true)

        // then
        sut.assert { it.name.endsWith("Text") }
    }

    @Test
    fun `assert-suppress-by-name-at-declaration-level`() {
        // given
        val sut = getSnippetFile("assert-suppress-by-name-at-declaration-level")
            .declarations(includeNested = true)

        // then
        sut.assert { it.name.endsWith("Text") }
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("core/verify/kodeclarationassert/snippet/", fileName)
}
