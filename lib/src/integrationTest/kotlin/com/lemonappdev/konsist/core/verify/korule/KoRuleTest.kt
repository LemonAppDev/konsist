package com.lemonappdev.konsist.core.verify.korule

import com.lemonappdev.konsist.TestSnippetProvider
import com.lemonappdev.konsist.api.declaration.KoClassDeclaration
import com.lemonappdev.konsist.api.verify.KoRule
import com.lemonappdev.konsist.api.verify.assertFalse
import com.lemonappdev.konsist.api.verify.assertNotEmpty
import com.lemonappdev.konsist.api.verify.assertNull
import com.lemonappdev.konsist.api.verify.assertTrue
import com.lemonappdev.konsist.core.exception.KoAssertionFailedException
import com.lemonappdev.konsist.core.filesystem.PathProvider
import com.lemonappdev.konsist.core.util.HyperlinkUtil
import org.amshove.kluent.shouldContain
import org.amshove.kluent.shouldNotContain
import org.amshove.kluent.shouldThrow
import org.amshove.kluent.withMessage
import org.junit.jupiter.api.Test

class KoRuleTest {
    private val filepath =
        "${HyperlinkUtil.toHyperlink(PathProvider.rootProjectPath)}/lib/src/integrationTest/kotlin/com/lemonappdev/konsist/" +
            "core/verify/korule/snippet/assert-with-rule.kt"

    private val sut = getSnippetFile("assert-with-rule").classes()

    @Test
    fun `assert-true-displays-all-rule-fields`() {
        // given
        val rule =
            KoRule(
                title = "Classes must be named OtherName.",
                rationale = "Naming keeps classes easy to find.",
                howToFix = "Rename the class to OtherName.",
                goodExample = "class OtherName",
                badExample = "class FixtureClass",
            )

        // when
        val func = { sut.assertTrue(rule = rule) { it.name == "OtherName" } }

        // then
        func shouldThrow KoAssertionFailedException::class withMessage
            "Assert 'assert-true-displays-all-rule-fields' was violated (1 time).\n" +
            "RULE: Classes must be named OtherName.\n" +
            "WHY: Naming keeps classes easy to find.\n" +
            "HOW TO FIX: Rename the class to OtherName.\n" +
            "GOOD EXAMPLE:\n" +
            "class OtherName\n" +
            "BAD EXAMPLE:\n" +
            "class FixtureClass\n" +
            "Invalid declarations:\n" +
            "└── Class FixtureClass $filepath:1:1"
    }

    @Test
    fun `assert-true-displays-only-required-rule-fields`() {
        // given
        val rule = KoRule(title = "Classes must be named OtherName.", rationale = "Naming keeps classes easy to find.")

        // when
        val func = { sut.assertTrue(rule = rule) { it.name == "OtherName" } }

        // then
        func shouldThrow KoAssertionFailedException::class withMessage
            "Assert 'assert-true-displays-only-required-rule-fields' was violated (1 time).\n" +
            "RULE: Classes must be named OtherName.\n" +
            "WHY: Naming keeps classes easy to find.\n" +
            "Invalid declarations:\n" +
            "└── Class FixtureClass $filepath:1:1"
    }

    @Test
    fun `assert-true-skips-blank-optional-rule-fields`() {
        // given
        val rule =
            KoRule(
                title = "Classes must be named OtherName.",
                rationale = "Naming keeps classes easy to find.",
                howToFix = " ",
                goodExample = "",
                badExample = "\n",
            )

        // when
        val func = { sut.assertTrue(rule = rule) { it.name == "OtherName" } }

        // then
        func shouldThrow KoAssertionFailedException::class withMessage
            "Assert 'assert-true-skips-blank-optional-rule-fields' was violated (1 time).\n" +
            "RULE: Classes must be named OtherName.\n" +
            "WHY: Naming keeps classes easy to find.\n" +
            "Invalid declarations:\n" +
            "└── Class FixtureClass $filepath:1:1"
    }

    @Test
    fun `assert-true-removes-common-indent-from-examples`() {
        // given
        val rule =
            KoRule(
                title = "Classes must be named OtherName.",
                rationale = "Naming keeps classes easy to find.",
                goodExample =
                    """
                    class OtherName {
                        val a = 1
                    }
                    """,
            )

        // when
        val func = { sut.assertTrue(rule = rule) { it.name == "OtherName" } }

        // then
        func shouldThrow KoAssertionFailedException::class withMessage
            "Assert 'assert-true-removes-common-indent-from-examples' was violated (1 time).\n" +
            "RULE: Classes must be named OtherName.\n" +
            "WHY: Naming keeps classes easy to find.\n" +
            "GOOD EXAMPLE:\n" +
            "class OtherName {\n" +
            "    val a = 1\n" +
            "}\n" +
            "Invalid declarations:\n" +
            "└── Class FixtureClass $filepath:1:1"
    }

    @Test
    fun `assert-true-displays-rule-before-additional-message`() {
        // given
        val rule = KoRule(title = "Classes must be named OtherName.", rationale = "Naming keeps classes easy to find.")

        // when
        val func = { sut.assertTrue(additionalMessage = "CUSTOM MESSAGE", rule = rule) { it.name == "OtherName" } }

        // then
        func shouldThrow KoAssertionFailedException::class withMessage
            "Assert 'assert-true-displays-rule-before-additional-message' was violated (1 time).\n" +
            "RULE: Classes must be named OtherName.\n" +
            "WHY: Naming keeps classes easy to find.\n" +
            "CUSTOM MESSAGE\n" +
            "Invalid declarations:\n" +
            "└── Class FixtureClass $filepath:1:1"
    }

    @Test
    fun `assert-true-passes-with-rule`() {
        // given
        val rule = KoRule(title = "Classes must be named FixtureClass.", rationale = "Naming keeps classes easy to find.")

        // then
        sut.assertTrue(rule = rule) { it.name == "FixtureClass" }
    }

    @Test
    fun `assert-true-without-rule-does-not-display-rule`() {
        // when
        val func = { sut.assertTrue { it.name == "OtherName" } }

        // then
        func shouldThrow KoAssertionFailedException::class withMessage
            "Assert 'assert-true-without-rule-does-not-display-rule' was violated (1 time). Invalid declarations:\n" +
            "└── Class FixtureClass $filepath:1:1"
    }

    @Test
    fun `assert-true-on-sequence-displays-rule`() {
        // given
        val rule = KoRule(title = "Classes must be named OtherName.", rationale = "Naming keeps classes easy to find.")

        // when
        val func = { sut.asSequence().assertTrue(rule = rule) { it.name == "OtherName" } }

        // then
        func shouldThrow KoAssertionFailedException::class withMessage
            "Assert 'assert-true-on-sequence-displays-rule' was violated (1 time).\n" +
            "RULE: Classes must be named OtherName.\n" +
            "WHY: Naming keeps classes easy to find.\n" +
            "Invalid declarations:\n" +
            "└── Class FixtureClass $filepath:1:1"
    }

    @Test
    fun `assert-true-on-single-declaration-displays-rule`() {
        // given
        val rule = KoRule(title = "Classes must be named OtherName.", rationale = "Naming keeps classes easy to find.")

        // when
        val func = { sut.first().assertTrue(rule = rule) { it.name == "OtherName" } }

        // then
        func shouldThrow KoAssertionFailedException::class withMessage
            "Assert 'assert-true-on-single-declaration-displays-rule' was violated (1 time).\n" +
            "RULE: Classes must be named OtherName.\n" +
            "WHY: Naming keeps classes easy to find.\n" +
            "Invalid declarations:\n" +
            "└── Class FixtureClass $filepath:1:1"
    }

    @Test
    fun `assert-false-displays-rule`() {
        // given
        val rule = KoRule(title = "Classes must not be named FixtureClass.", rationale = "The name is reserved.")

        // when
        val func = { sut.assertFalse(rule = rule) { it.name == "FixtureClass" } }

        // then
        func shouldThrow KoAssertionFailedException::class withMessage
            "Assert 'assert-false-displays-rule' was violated (1 time).\n" +
            "RULE: Classes must not be named FixtureClass.\n" +
            "WHY: The name is reserved.\n" +
            "Invalid declarations:\n" +
            "└── Class FixtureClass $filepath:1:1"
    }

    @Test
    fun `assert-not-empty-displays-rule`() {
        // given
        val rule = KoRule(title = "Module must declare classes.", rationale = "Empty modules are removed.")

        // when
        val func = { emptyList<KoClassDeclaration>().assertNotEmpty(rule = rule) }

        // then
        func shouldThrow KoAssertionFailedException::class withMessage
            "Assert 'assert-not-empty-displays-rule' failed.\n" +
            "RULE: Module must declare classes.\n" +
            "WHY: Empty modules are removed.\n" +
            "Declaration list is empty."
    }

    @Test
    fun `assert-null-displays-rule`() {
        // given
        val rule = KoRule(title = "Class must not exist.", rationale = "It was replaced.")

        // when
        val func = { sut.first().assertNull(rule = rule) }

        // then
        val message = (func shouldThrow KoAssertionFailedException::class).exception.message.orEmpty()
        message shouldContain "failed.\nRULE: Class must not exist.\nWHY: It was replaced.\nDeclaration has not null value"
        message shouldNotContain "BAD EXAMPLE"
    }

    @Test
    fun `rule-with-blank-title-throws-exception`() {
        // when
        val func = { KoRule(title = " ", rationale = "Rationale.") }

        // then
        func shouldThrow IllegalArgumentException::class withMessage "KoRule title must not be blank."
    }

    @Test
    fun `rule-with-blank-rationale-throws-exception`() {
        // when
        val func = { KoRule(title = "Title.", rationale = "") }

        // then
        func shouldThrow IllegalArgumentException::class withMessage "KoRule rationale must not be blank."
    }

    private fun getSnippetFile(fileName: String) = TestSnippetProvider.getSnippetKoScope("core/verify/korule/snippet/", fileName)
}
