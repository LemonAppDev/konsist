package com.lemonappdev.konsist.core.verify.kodeclarationassert.asserttrue

import com.lemonappdev.konsist.TestSnippetProvider
import com.lemonappdev.konsist.api.verify.KoJUnitExtension
import com.lemonappdev.konsist.api.verify.assertTrue
import com.lemonappdev.konsist.core.exception.KoAssertionFailedException
import io.kotest.assertions.throwables.shouldThrow
import org.amshove.kluent.shouldStartWith
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestFactory
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(KoJUnitExtension::class)
class AssertTrueJUnitExtensionTest {
    @TestFactory
    fun `assert-test-name-derived-from-dynamic-test-with-extension`() =
        listOf("first dynamic test", "second dynamic test").map { name ->
            DynamicTest.dynamicTest(name) {
                // given
                val sut = getSnippetFile("assert-test-name-derived-from-dynamic-test").classes().first()

                // when
                val result = shouldThrow<KoAssertionFailedException> { sut.assertTrue { false } }

                // then
                result.message?.shouldStartWith("Assert '$name' was violated (1 time)")
            }
        }

    @TestFactory
    fun `assert-test-name-parameter-overrides-dynamic-test-name`() =
        listOf(
            DynamicTest.dynamicTest("dynamic test") {
                // given
                val sut = getSnippetFile("assert-test-name-derived-from-dynamic-test").classes().first()

                // when
                val result = shouldThrow<KoAssertionFailedException> { sut.assertTrue(testName = "custom name") { false } }

                // then
                result.message?.shouldStartWith("Assert 'custom name' was violated (1 time)")
            },
        )

    @TestFactory
    fun `assert-suppress-by-dynamic-test-name-with-extension`() =
        listOf(
            DynamicTest.dynamicTest("suppressed dynamic test") {
                // given
                val sut = getSnippetFile("assert-suppress-by-dynamic-test-name-with-extension").classes().first()

                // then
                sut.assertTrue { false }
            },
        )

    @TestFactory
    fun `assert-suppress-by-konsist-and-dynamic-test-name-with-extension`() =
        listOf(
            DynamicTest.dynamicTest("suppressed dynamic test") {
                // given
                val sut = getSnippetFile("assert-suppress-by-konsist-and-dynamic-test-name-with-extension").classes().first()

                // then
                sut.assertTrue { false }
            },
        )

    @TestFactory
    fun `assert-suppress-by-factory-name-with-extension`() =
        listOf(
            DynamicTest.dynamicTest("dynamic test") {
                // given
                val sut = getSnippetFile("assert-suppress-by-factory-name-with-extension").classes().first()

                // then
                sut.assertTrue { false }
            },
        )

    @TestFactory
    fun `assert-suppress-by-konsist-and-factory-name-with-extension`() =
        listOf(
            DynamicTest.dynamicTest("dynamic test") {
                // given
                val sut = getSnippetFile("assert-suppress-by-konsist-and-factory-name-with-extension").classes().first()

                // then
                sut.assertTrue { false }
            },
        )

    @Test
    fun `assert-test-name-derived-from-test-method-with-extension`() {
        // given
        val sut = getSnippetFile("assert-test-name-derived-from-dynamic-test").classes().first()

        // when
        val result = shouldThrow<KoAssertionFailedException> { sut.assertTrue { false } }

        // then
        result.message?.shouldStartWith("Assert 'assert-test-name-derived-from-test-method-with-extension' was violated (1 time)")
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("core/verify/kodeclarationassert/asserttrue/snippet/", fileName)
}
