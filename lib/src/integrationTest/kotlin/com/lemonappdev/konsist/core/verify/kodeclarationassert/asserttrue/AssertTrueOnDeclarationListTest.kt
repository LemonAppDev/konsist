package com.lemonappdev.konsist.core.verify.kodeclarationassert.asserttrue

import com.lemonappdev.konsist.TestSnippetProvider
import com.lemonappdev.konsist.api.verify.assertFalse
import com.lemonappdev.konsist.api.verify.assertTrue
import com.lemonappdev.konsist.core.exception.KoAssertionFailedException
import com.lemonappdev.konsist.core.exception.KoPreconditionFailedException
import com.lemonappdev.konsist.core.filesystem.PathProvider
import com.lemonappdev.konsist.core.util.HyperlinkUtil
import org.amshove.kluent.shouldContain
import org.amshove.kluent.shouldThrow
import org.amshove.kluent.withMessage
import org.junit.jupiter.api.Test

class AssertTrueOnDeclarationListTest {
    private val rootPathUrl = HyperlinkUtil.toHyperlink(PathProvider.rootProjectPath)

    @Test
    fun `declaration-assert-test-method-name-derived-from-junit-method-name`() {
        // given
        val sut =
            getSnippetFile("declaration-assert-test-method-name-derived-from-junit-method-name")
                .classes()

        // then
        try {
            sut.assertTrue { false }
        } catch (e: Exception) {
            e.message?.shouldContain(
                "Assert 'declaration-assert-test-method-name-derived-from-junit-method-name' was violated (1 time)",
            )
                ?: throw e
        }
    }

    @Test
    fun `declaration-assert-test-method-name-derived-from-test-name-parameter`() {
        // given
        val sut =
            getSnippetFile("declaration-assert-test-method-name-derived-from-test-name-parameter")
                .classes()

        // then
        try {
            sut.assertTrue(testName = "fixture test") { false }
        } catch (e: Exception) {
            e.message?.shouldContain("Assert 'fixture test' was violated (1 time)")
                ?: throw e
        }
    }

    @Test
    fun `file-declaration-assert-test-method-name-derived-from-junit-method-name`() {
        // given
        val sut =
            getSnippetFile("file-declaration-assert-test-method-name-derived-from-junit-method-name")
                .files

        // then
        try {
            sut.assertTrue { false }
        } catch (e: Exception) {
            e.message?.shouldContain(
                "Assert 'file-declaration-assert-test-method-name-derived-from-junit-method-name' was violated (1 time)",
            )
                ?: throw e
        }
    }

    @Test
    fun `file-declaration-assert-test-method-name-derived-from-test-name-parameter`() {
        // given
        val sut =
            getSnippetFile("file-declaration-assert-test-method-name-derived-from-test-name-parameter")
                .files

        // then
        try {
            sut.assertTrue(testName = "fixture test") { false }
        } catch (e: Exception) {
            e.message?.shouldContain("Assert 'fixture test' was violated (1 time)")
                ?: throw e
        }
    }

    @Test
    fun `declaration-assert-error-with-custom-message`() {
        // given
        val message = "CUSTOM ASSERT MESSAGE"
        val sut =
            getSnippetFile("declaration-assert-error-with-custom-message")
                .classes()

        // then
        try {
            sut.assertTrue(additionalMessage = message) { false }
        } catch (e: Exception) {
            e.message?.shouldContain(
                "Assert 'declaration-assert-error-with-custom-message' was violated (1 time)." +
                    "\n$message\nInvalid declarations",
            )
                ?: throw e
        }
    }

    @Test
    fun `declaration-assert-error-with-custom-message-and-strict-set-to-true`() {
        // given
        val message = "CUSTOM ASSERT MESSAGE"
        val sut =
            getSnippetFile("declaration-assert-error-with-custom-message-and-strict-set-to-true")
                .classes()

        // then
        try {
            sut.assertTrue(strict = true, additionalMessage = message) { false }
        } catch (e: Exception) {
            e.message?.shouldContain(
                "Assert 'declaration-assert-error-with-custom-message-and-strict-set-to-true' was violated (1 time)." +
                    "\n$message\nInvalid declarations",
            )
                ?: throw e
        }
    }

    @Test
    fun `file-declaration-assert-error-with-custom-message`() {
        // given
        val message = "CUSTOM ASSERT MESSAGE"
        val sut =
            getSnippetFile("file-declaration-assert-error-with-custom-message")
                .files

        // then
        try {
            sut.assertTrue(additionalMessage = message) { false }
        } catch (e: Exception) {
            e.message?.shouldContain(
                "Assert 'file-declaration-assert-error-with-custom-message' was violated (1 time)." +
                    "\n$message\nInvalid files:",
            )
                ?: throw e
        }
    }

    @Test
    fun `file-declaration-assert-error-with-custom-message-and-strict-set-to-true`() {
        // given
        val message = "CUSTOM ASSERT MESSAGE"
        val sut =
            getSnippetFile("file-declaration-assert-error-with-custom-message-and-strict-set-to-true")
                .files

        // then
        try {
            sut.assertTrue(strict = true, additionalMessage = message) { false }
        } catch (e: Exception) {
            e.message?.shouldContain(
                "Assert 'file-declaration-assert-error-with-custom-message-and-strict-set-to-true' was violated (1 time)." +
                    "\n$message\nInvalid files:",
            )
                ?: throw e
        }
    }

    @Test
    fun `declaration-assert-displaying-correct-failed-declaration-type`() {
        // given
        val sut =
            getSnippetFile("declaration-assert-displaying-correct-failed-declaration-type")
                .classes()

        // then
        try {
            sut.assertTrue { false }
        } catch (e: Exception) {
            e.message?.shouldContain("Class FixtureClass")
                ?: throw e
        }
    }

    @Test
    fun `file-declaration-assert-displaying-correct-failed-declaration-type`() {
        // given
        val sut =
            getSnippetFile("file-declaration-assert-displaying-correct-failed-declaration-type")
                .files

        // then
        try {
            sut.assertTrue { false }
        } catch (e: Exception) {
            e.message?.shouldContain("File file-declaration-assert-displaying-correct-failed-declaration-type.kt")
                ?: throw e
        }
    }

    @Test
    fun `declaration-assert-passes-when-declaration-list-is-empty`() {
        // given
        val sut =
            getSnippetFile("declaration-assert-passes-when-declaration-list-is-empty")
                .classes()

        // when
        sut.assertTrue { true }
    }

    @Test
    fun `declaration-assert-false-passes-when-declaration-list-is-empty`() {
        // given
        val sut =
            getSnippetFile("declaration-assert-false-passes-when-declaration-list-is-empty")
                .classes()

        // when
        sut.assertFalse { false }
    }

    @Test
    fun `declaration-assert-strict-fails-when-declaration-list-is-empty`() {
        // given
        val sut =
            getSnippetFile("declaration-assert-strict-fails-when-declaration-list-is-empty")
                .classes()

        // when
        val func = {
            sut.assertTrue(strict = true) { true }
        }

        // then
        func shouldThrow KoPreconditionFailedException::class withMessage
            "Declaration list is empty. Please make sure that list of declarations contain items before calling the 'assertTrue' method."
    }

    @Test
    fun `declaration-assert-false-strict-fails-when-declaration-list-is-empty`() {
        // given
        val sut =
            getSnippetFile("declaration-assert-false-strict-fails-when-declaration-list-is-empty")
                .classes()

        // when
        val func = {
            sut.assertFalse(strict = true) { false }
        }

        // then
        func shouldThrow KoPreconditionFailedException::class withMessage
            "Declaration list is empty. Please make sure that list of declarations contain items before calling the 'assertFalse' method."
    }

    @Test
    fun `declaration-assert-passes-when-declaration-list-has-only-nulls`() {
        // given
        val sut =
            getSnippetFile("declaration-assert-passes-when-declaration-list-has-only-nulls")
                .classes()
                .map { it.primaryConstructor }

        // when
        sut.assertTrue { true }
    }

    @Test
    fun `declaration-assert-false-passes-when-declaration-list-has-only-nulls`() {
        // given
        val sut =
            getSnippetFile("declaration-assert-false-passes-when-declaration-list-has-only-nulls")
                .classes()
                .map { it.primaryConstructor }

        // when
        sut.assertFalse { false }
    }

    @Test
    fun `declaration-assert-strict-fails-when-declaration-list-has-only-nulls`() {
        // given
        val sut =
            getSnippetFile("declaration-assert-strict-fails-when-declaration-list-has-only-nulls")
                .classes()
                .map { it.primaryConstructor }

        // when
        val func = {
            sut.assertTrue(strict = true) { true }
        }

        // then
        func shouldThrow KoPreconditionFailedException::class withMessage
            "Declaration list contains only null elements. Please make sure that list of declarations contain items " +
            "before calling the 'assertTrue' method."
    }

    @Test
    fun `declaration-assert-false-strict-fails-when-declaration-list-has-only-nulls`() {
        // given
        val sut =
            getSnippetFile("declaration-assert-false-strict-fails-when-declaration-list-has-only-nulls")
                .classes()
                .map { it.primaryConstructor }

        // when
        val func = {
            sut.assertFalse(strict = true) { false }
        }

        // then
        func shouldThrow KoPreconditionFailedException::class withMessage
            "Declaration list contains only null elements. Please make sure that list of declarations contain items" +
            " before calling the 'assertFalse' method."
    }

    @Test
    fun `assert-passes`() {
        // given
        val sut =
            getSnippetFile("assert-passes")
                .classes()

        // then
        sut.assertTrue { it.name == "FixtureClass" }
    }

    @Test
    fun `assert-fails`() {
        // given
        val sut =
            getSnippetFile("assert-fails")
                .classes()

        // when
        val func = {
            sut.assertTrue { it.name == "OtherName" }
        }

        // then
        func shouldThrow KoAssertionFailedException::class
    }

    @Test
    fun `assert-false-passes`() {
        // given
        val sut =
            getSnippetFile("assert-false-passes")
                .classes()

        // then
        sut.assertFalse {
            it.name == "OtherName"
        }
    }

    @Test
    fun `assert-false-fails`() {
        // given
        val sut =
            getSnippetFile("assert-false-fails")
                .classes()

        // when
        val func = {
            sut.assertFalse {
                it.name == "FixtureClass"
            }
        }

        // then
        func shouldThrow KoAssertionFailedException::class
    }

    @Test
    fun `assert-passes-on-declarations-which-items-have-null-parent`() {
        // given
        val sut =
            getSnippetFile("assert-passes-on-declarations-which-items-have-null-parent")
                .files

        // then
        sut.assertTrue { it.name == "assert-passes-on-declarations-which-items-have-null-parent" }
    }

    @Test
    fun `assert-fails-on-declarations-which-items-have-null-parent`() {
        // given
        val sut =
            getSnippetFile("assert-fails-on-declarations-which-items-have-null-parent")
                .files

        // when
        val func = {
            sut.assertTrue { it.name == "OtherName" }
        }

        // then
        func shouldThrow KoAssertionFailedException::class
    }

    @Test
    fun `assert-false-passes-on-declarations-which-items-have-null-parent`() {
        // given
        val sut =
            getSnippetFile("assert-false-passes-on-declarations-which-items-have-null-parent")
                .files

        // then
        sut.assertFalse {
            it.name == "OtherName"
        }
    }

    @Test
    fun `assert-false-fails-on-declarations-which-items-have-null-parent`() {
        // given
        val sut =
            getSnippetFile("assert-false-fails-on-declarations-which-items-have-null-parent")
                .files

        // when
        val func = {
            sut.assertFalse {
                it.name == "assert-false-fails-on-declarations-which-items-have-null-parent"
            }
        }

        // then
        func shouldThrow KoAssertionFailedException::class
    }

    @Test
    fun `assert-passes-when-expression-is-nullable`() {
        // given
        val sut =
            getSnippetFile("assert-passes-when-expression-is-nullable")
                .classes()

        // then
        sut.assertTrue { it.primaryConstructor?.hasParameterWithName("fixtureParameter") }
    }

    @Test
    fun `assert-fails-when-expression-is-nullable`() {
        // given
        val sut =
            getSnippetFile("assert-fails-when-expression-is-nullable")
                .classes()

        // when
        val func = {
            sut.assertTrue { it.primaryConstructor?.hasParameterWithName("fixtureParameter") }
        }

        // then
        func shouldThrow KoAssertionFailedException::class
    }

    @Test
    fun `assert-false-passes-when-expression-is-nullable`() {
        // given
        val sut =
            getSnippetFile("assert-false-passes-when-expression-is-nullable")
                .classes()

        // then
        sut.assertFalse { it.primaryConstructor?.hasParameterWithName("otherParameter") }
    }

    @Test
    fun `assert-false-fails-when-expression-is-nullable`() {
        // given
        val sut =
            getSnippetFile("assert-false-fails-when-expression-is-nullable")
                .classes()

        // when
        val func = {
            sut.assertFalse { it.primaryConstructor?.hasParameterWithName("fixtureParameter") }
        }

        // then
        func shouldThrow KoAssertionFailedException::class
    }

    @Test
    fun `assert-fails-with-added-message`() {
        // given
        val sut =
            getSnippetFile("assert-fails-with-added-message")
                .classes()
        val filepath =
            "$rootPathUrl/lib/src/integrationTest/kotlin/com/lemonappdev/konsist/core/verify/kodeclarationassert/" +
                "asserttrue/snippet/assert-fails-with-added-message.kt"

        // when
        val func = {
            sut.assertTrue {
                addMessage("${it.name} message")
                it.name == "FixtureClass1"
            }
        }

        // then
        func shouldThrow KoAssertionFailedException::class withMessage
            "Assert 'assert-fails-with-added-message' was violated (1 time). Invalid declarations:\n" +
            "└── Class FixtureClass2 $filepath:3:1\n" +
            "    └── FixtureClass2 message"
    }

    @Test
    fun `assert-fails-with-multiple-added-messages`() {
        // given
        val sut =
            getSnippetFile("assert-fails-with-multiple-added-messages")
                .classes()
        val filepath =
            "$rootPathUrl/lib/src/integrationTest/kotlin/com/lemonappdev/konsist/core/verify/kodeclarationassert/" +
                "asserttrue/snippet/assert-fails-with-multiple-added-messages.kt"

        // when
        val func = {
            sut.assertTrue {
                if (it.name == "FixtureClass1") {
                    addMessage("first message")
                    addMessage("second message")
                }
                false
            }
        }

        // then
        func shouldThrow KoAssertionFailedException::class withMessage
            "Assert 'assert-fails-with-multiple-added-messages' was violated (3 times). Invalid declarations:\n" +
            "├── Class FixtureClass1 $filepath:1:1\n" +
            "│   ├── first message\n" +
            "│   └── second message\n" +
            "├── Class FixtureClass2 $filepath:3:1\n" +
            "└── Class FixtureClass3 $filepath:5:1"
    }

    @Test
    fun `assert-false-fails-with-added-message`() {
        // given
        val message = "CUSTOM ASSERT MESSAGE"
        val sut =
            getSnippetFile("assert-false-fails-with-added-message")
                .classes()
        val filepath =
            "$rootPathUrl/lib/src/integrationTest/kotlin/com/lemonappdev/konsist/core/verify/kodeclarationassert/" +
                "asserttrue/snippet/assert-false-fails-with-added-message.kt"

        // when
        val func = {
            sut.assertFalse(additionalMessage = message) {
                addMessage("${it.name} message")
                it.name == "FixtureClass2"
            }
        }

        // then
        func shouldThrow KoAssertionFailedException::class withMessage
            "Assert 'assert-false-fails-with-added-message' was violated (1 time).\n$message\nInvalid declarations:\n" +
            "└── Class FixtureClass2 $filepath:3:1\n" +
            "    └── FixtureClass2 message"
    }

    @Test
    fun `assert-passes-with-added-message`() {
        // given
        val sut =
            getSnippetFile("assert-passes-with-added-message")
                .classes()

        // then
        sut.assertTrue {
            addMessage("message")
            it.name == "FixtureClass"
        }
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("core/verify/kodeclarationassert/asserttrue/snippet/", fileName)
}
