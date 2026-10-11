package com.lemonappdev.konsist.core.verify.kodeclarationassert.asserttrue

import com.lemonappdev.konsist.TestSnippetProvider
import com.lemonappdev.konsist.api.verify.assertTrue
import com.lemonappdev.konsist.core.exception.KoAssertionFailedException
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FunSpec
import org.amshove.kluent.shouldNotContain
import org.amshove.kluent.shouldStartWith

class AssertTrueKotestTestNameTest :
    FunSpec({
        test("assert-test-name-is-omitted-in-kotest-test") {
            // given
            val sut = getSnippetFile("assert-test-name-derived-from-dynamic-test").classes().first()

            // when
            val result = shouldThrow<KoAssertionFailedException> { sut.assertTrue { false } }

            // then
            result.message?.shouldStartWith("Assert was violated (1 time)")
            result.message?.shouldNotContain("invokeSuspend")
        }

        test("assert-test-name-is-omitted-in-kotest-test-when-called-from-lambda") {
            // given
            val sut = getSnippetFile("assert-test-name-derived-from-dynamic-test").classes().first()

            val func = { sut.assertTrue { false } }

            // when
            val result = shouldThrow<KoAssertionFailedException> { func() }

            // then
            result.message?.shouldStartWith("Assert was violated (1 time)")
            result.message?.shouldNotContain("invokeSuspend")
        }

        test("assert-suppress-by-test-name-in-kotest-test") {
            // given
            val sut = getSnippetFile("assert-suppress-by-test-name-in-kotest-test").classes().first()

            // then
            sut.assertTrue(testName = "suppress-by-test-name-in-kotest-test") { false }
        }

        test("assert-suppress-by-konsist-and-test-name-in-kotest-test") {
            // given
            val sut = getSnippetFile("assert-suppress-by-konsist-and-test-name-in-kotest-test").classes().first()

            // then
            sut.assertTrue(testName = "suppress-by-konsist-and-test-name-in-kotest-test") { false }
        }

        test("assert-not-suppress-by-kotest-test-name-without-test-name") {
            // given
            val sut = getSnippetFile("assert-not-suppress-by-kotest-test-name-without-test-name").classes().first()

            // when
            val result = shouldThrow<KoAssertionFailedException> { sut.assertTrue { false } }

            // then
            result.message?.shouldStartWith("Assert was violated (1 time)")
        }

        test("assert-not-suppress-by-konsist-and-kotest-test-name-without-test-name") {
            // given
            val sut = getSnippetFile("assert-not-suppress-by-konsist-and-kotest-test-name-without-test-name").classes().first()

            // when
            val result = shouldThrow<KoAssertionFailedException> { sut.assertTrue { false } }

            // then
            result.message?.shouldStartWith("Assert was violated (1 time)")
        }
    })

private fun getSnippetFile(fileName: String) =
    TestSnippetProvider.getSnippetKoScope("core/verify/kodeclarationassert/asserttrue/snippet/", fileName)
