package com.lemonappdev.konsist.core.verify.kodeclarationassert.asserttrue

import com.lemonappdev.konsist.TestSnippetProvider
import com.lemonappdev.konsist.api.verify.assertTrue
import com.lemonappdev.konsist.core.exception.KoAssertionFailedException
import io.kotest.assertions.throwables.shouldThrow
import org.amshove.kluent.shouldContain
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.TestFactory

class AssertTrueTestNameTest {
    @TestFactory
    fun `assert-test-name-derived-from-dynamic-test`() =
        listOf(
            DynamicTest.dynamicTest("dynamic test") {
                // given
                val sut =
                    getSnippetFile("assert-test-name-derived-from-dynamic-test")
                        .classes()
                        .first()

                // when
                val result = shouldThrow<KoAssertionFailedException> { sut.assertTrue { false } }

                // then
                result.message?.shouldContain("Assert 'assert-test-name-derived-from-dynamic-test' was violated (1 time)")
            },
        )

    @TestFactory
    fun `assert-suppress-by-name-in-dynamic-test`() =
        listOf(
            DynamicTest.dynamicTest("dynamic test") {
                // given
                val sut =
                    getSnippetFile("assert-suppress-by-name-in-dynamic-test")
                        .classes()
                        .first()

                // then
                sut.assertTrue { false }
            },
        )

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("core/verify/kodeclarationassert/asserttrue/snippet/", fileName)
}
