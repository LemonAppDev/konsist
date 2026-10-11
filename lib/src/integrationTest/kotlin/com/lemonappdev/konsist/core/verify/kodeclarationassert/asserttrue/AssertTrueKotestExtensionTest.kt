package com.lemonappdev.konsist.core.verify.kodeclarationassert.asserttrue

import com.lemonappdev.konsist.TestSnippetProvider
import com.lemonappdev.konsist.api.verify.KoKotestExtension
import com.lemonappdev.konsist.api.verify.assertTrue
import com.lemonappdev.konsist.core.exception.KoAssertionFailedException
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FunSpec
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.amshove.kluent.shouldStartWith

class AssertTrueKotestExtensionTest :
    FunSpec({
        extension(KoKotestExtension)

        test("assert test name derived from kotest test with extension") {
            // given
            val sut = getSnippetFile("assert-test-name-derived-from-dynamic-test").classes().first()

            // when
            val result = shouldThrow<KoAssertionFailedException> { sut.assertTrue { false } }

            // then
            result.message?.shouldStartWith("Assert 'assert test name derived from kotest test with extension' was violated (1 time)")
        }

        context("container") {
            test("nested kotest test") {
                // given
                val sut = getSnippetFile("assert-test-name-derived-from-dynamic-test").classes().first()

                // when
                val result = shouldThrow<KoAssertionFailedException> { sut.assertTrue { false } }

                // then
                result.message?.shouldStartWith("Assert 'nested kotest test' was violated (1 time)")
            }
        }

        test("kotest test switching threads") {
            // given
            val sut = getSnippetFile("assert-test-name-derived-from-dynamic-test").classes().first()

            // when
            val result =
                withContext(Dispatchers.IO) {
                    shouldThrow<KoAssertionFailedException> { sut.assertTrue { false } }
                }

            // then
            result.message?.shouldStartWith("Assert 'kotest test switching threads' was violated (1 time)")
        }

        test("suppressed kotest test") {
            // given
            val sut = getSnippetFile("assert-suppress-by-kotest-test-name-with-extension").classes().first()

            // then
            sut.assertTrue { false }
        }

        context("with konsist prefix") {
            test("suppressed kotest test") {
                // given
                val sut = getSnippetFile("assert-suppress-by-konsist-and-kotest-test-name-with-extension").classes().first()

                // then
                sut.assertTrue { false }
            }
        }
    })

private fun getSnippetFile(fileName: String) =
    TestSnippetProvider.getSnippetKoScope("core/verify/kodeclarationassert/asserttrue/snippet/", fileName)
