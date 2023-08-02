package com.lemonappdev.konsist.core.verify

import com.lemonappdev.konsist.TestSnippetProvider
import com.lemonappdev.konsist.core.exception.KoCheckFailedException
import org.amshove.kluent.shouldThrow
import org.junit.jupiter.api.Test

class KoFileAssertForListTest {
    @Test
    fun `assert-passes`() {
        // given
        val sut = getSnippetFile("assert-passes")
            .files

        // then
        sut.assert { it.name == "assert-passes" }
    }

    @Test
    fun `assert-fails`() {
        // given
        val sut = getSnippetFile("assert-fails")
            .files

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
            .files

        // then
        sut.assertNot {
            it.name == "OtherName"
        }
    }

    @Test
    fun `assert-not-fails`() {
        // given
        val sut = getSnippetFile("assert-not-fails")
            .files

        // when
        val func = {
            sut.assertNot {
                it.name == "assert-not-fails"
            }
        }

        // then
        func shouldThrow KoCheckFailedException::class
    }

    @Test
    fun `assert-suppress-by-konsist-and-name`() {
        // given
        val scope1 = getSnippetFile("assert-suppress-by-konsist-and-name")
        val scope2 = getSnippetFile("file-without-suppress")

        val sut = (scope1 + scope2)
            .files

        // then
        sut.assert { it.name.endsWith("suppress") }
    }

    @Test
    fun `assert-suppress-by-name`() {
        // given
        val scope1 = getSnippetFile("assert-suppress-by-name")
        val scope2 = getSnippetFile("file-without-suppress")

        val sut = (scope1 + scope2)
            .files

        // then
        sut.assert { it.name.endsWith("suppress") }
    }

    @Test
    fun `assert-suppress-with-few-parameters`() {
        // given
        val scope1 = getSnippetFile("assert-suppress-with-few-parameters")
        val scope2 = getSnippetFile("file-without-suppress")

        val sut = (scope1 + scope2)
            .files

        // then
        sut.assert { it.name.endsWith("suppress") }
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("core/verify/snippet/", fileName)
}