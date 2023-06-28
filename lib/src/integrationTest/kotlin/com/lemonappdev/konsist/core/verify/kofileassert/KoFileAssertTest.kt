package com.lemonappdev.konsist.core.verify.kofileassert

import com.lemonappdev.konsist.TestSnippetProvider
import com.lemonappdev.konsist.api.Konsist
import com.lemonappdev.konsist.core.exception.KoCheckFailedException
import com.lemonappdev.konsist.core.exception.KoPreconditionFailedException
import com.lemonappdev.konsist.core.verify.assert
import com.lemonappdev.konsist.core.verify.assertNot
import org.amshove.kluent.shouldContain
import org.amshove.kluent.shouldThrow
import org.amshove.kluent.withMessage
import org.junit.jupiter.api.Test

class KoFileAssertTest {
    @Test
    fun `assert-passes`() {
        // given
        val sut = getSnippetFile("assert-passes")
            .files()

        // then
        sut.assert { it.name == "assert-passes" }
    }

    @Test
    fun `assert-fails`() {
        // given
        val sut = getSnippetFile("assert-fails")
            .files()

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
            .files()

        // then
        sut.assertNot {
            it.name == "OtherName"
        }
    }

    @Test
    fun `assert-not-fails`() {
        // given
        val sut = getSnippetFile("assert-not-fails")
            .files()

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
    fun `assert-fails-when-declaration-list-is-empty`() {
        // given
        val sut = Konsist
            .scopeFromDirectory("lib/src/integrationTest/kotlin/com/lemonappdev/konsist/core/verify/kofileassert/snippet/emptypackage")
            .files()

        // when
        val func = {
            sut.assert { true }
        }

        // then
        func shouldThrow KoPreconditionFailedException::class withMessage
                "File list is empty. Please make sure that list of files contain items before calling the 'assert' method."
    }

    @Test
    fun `assert-not-fails-when-declaration-list-is-empty`() {
        // given
        val sut = Konsist
            .scopeFromDirectory("lib/src/integrationTest/kotlin/com/lemonappdev/konsist/core/verify/kofileassert/snippet/emptypackage")
            .files()

        // when
        val func = {
            sut.assertNot { false }
        }

        // then
        func shouldThrow KoPreconditionFailedException::class withMessage
                "File list is empty. Please make sure that list of files contain items before calling the 'assertNot' method."
    }

    @Test
    fun `assert-suppress-by-konsist-and-name`() {
        // given
        val sut = Konsist
            .scopeFromDirectory("lib/src/integrationTest/kotlin/com/lemonappdev/konsist/core/verify/kofileassert/snippet/suppresspackage/withkonsist")
            .files()

        // then
        sut.assert { it.name.endsWith("suppress") }
    }

    @Test
    fun `assert-suppress-by-name`() {
        // given
        val sut = Konsist
            .scopeFromDirectory("lib/src/integrationTest/kotlin/com/lemonappdev/konsist/core/verify/kofileassert/snippet/suppresspackage/withoutkonsist")
            .files()

        // then
        sut.assert { it.name.endsWith("suppress") }
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("core/verify/kofileassert/snippet/", fileName)
}
