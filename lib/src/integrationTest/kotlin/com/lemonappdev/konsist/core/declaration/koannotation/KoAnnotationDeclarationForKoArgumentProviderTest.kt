package com.lemonappdev.konsist.core.declaration.koannotation

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import com.lemonappdev.konsist.api.ext.list.annotations
import com.lemonappdev.konsist.api.ext.list.enumConstants
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoAnnotationDeclarationForKoArgumentProviderTest {
    @Test
    fun `annotation-without-arguments`() {
        // given
        val sut =
            getSnippetFile("annotation-without-arguments")
                .functions()
                .annotations
                .first()

        // then
        assertSoftly(sut) {
            arguments shouldBeEqualTo emptyList()
            numArguments shouldBeEqualTo 0
            countArguments { it.value == "text" } shouldBeEqualTo 0
            hasArguments() shouldBeEqualTo false
            hasArgumentWithName(emptyList()) shouldBeEqualTo false
            hasArgumentWithName(emptySet()) shouldBeEqualTo false
            hasArgumentsWithAllNames(emptyList()) shouldBeEqualTo false
            hasArgumentsWithAllNames(emptySet()) shouldBeEqualTo false
            hasArgumentWithName("fixtureArgument") shouldBeEqualTo false
            hasArgumentWithName(listOf("fixtureArgument")) shouldBeEqualTo false
            hasArgumentWithName(setOf("fixtureArgument")) shouldBeEqualTo false
            hasArgumentsWithAllNames("fixtureArgument1", "fixtureArgument2") shouldBeEqualTo false
            hasArgumentsWithAllNames(listOf("fixtureArgument1", "fixtureArgument2")) shouldBeEqualTo false
            hasArgumentsWithAllNames(setOf("fixtureArgument1", "fixtureArgument2")) shouldBeEqualTo false
            hasArgument { it.value == "text" } shouldBeEqualTo false
            hasAllArguments { it.value == "text" } shouldBeEqualTo true
        }
    }

    @Test
    fun `annotation-with-constructor-invocation-without-arguments`() {
        // given
        val sut =
            getSnippetFile("annotation-with-constructor-invocation-without-arguments")
                .functions()
                .annotations
                .first()

        // then
        assertSoftly(sut) {
            arguments shouldBeEqualTo emptyList()
            numArguments shouldBeEqualTo 0
            countArguments { it.value == "text" } shouldBeEqualTo 0
            hasArguments() shouldBeEqualTo false
            hasArgumentWithName(emptyList()) shouldBeEqualTo false
            hasArgumentWithName(emptySet()) shouldBeEqualTo false
            hasArgumentsWithAllNames(emptyList()) shouldBeEqualTo false
            hasArgumentsWithAllNames(emptySet()) shouldBeEqualTo false
            hasArgumentWithName("fixtureArgument") shouldBeEqualTo false
            hasArgumentWithName(listOf("fixtureArgument")) shouldBeEqualTo false
            hasArgumentWithName(setOf("fixtureArgument")) shouldBeEqualTo false
            hasArgumentsWithAllNames("fixtureArgument1", "fixtureArgument2") shouldBeEqualTo false
            hasArgumentsWithAllNames(listOf("fixtureArgument1", "fixtureArgument2")) shouldBeEqualTo false
            hasArgumentsWithAllNames(setOf("fixtureArgument1", "fixtureArgument2")) shouldBeEqualTo false
            hasArgument { it.value == "text" } shouldBeEqualTo false
            hasAllArguments { it.value == "text" } shouldBeEqualTo true
        }
    }

    @Test
    fun `annotation-with-one-argument`() {
        // given
        val sut =
            getSnippetFile("annotation-with-one-argument")
                .functions()
                .annotations
                .first()

        // then
        assertSoftly(sut) {
            arguments.map { it.value } shouldBeEqualTo listOf("text")
            numArguments shouldBeEqualTo 1
            countArguments { it.value == "text" } shouldBeEqualTo 1
            countArguments { it.value == "other" } shouldBeEqualTo 0
            hasArguments() shouldBeEqualTo true
            hasArgumentWithName(emptyList()) shouldBeEqualTo true
            hasArgumentWithName(emptySet()) shouldBeEqualTo true
            hasArgumentsWithAllNames(emptyList()) shouldBeEqualTo true
            hasArgumentsWithAllNames(emptySet()) shouldBeEqualTo true
            hasArgumentWithName("fixtureParameter") shouldBeEqualTo true
            hasArgumentWithName("otherParameter") shouldBeEqualTo false
            hasArgumentWithName("fixtureParameter", "otherParameter") shouldBeEqualTo true
            hasArgumentWithName(listOf("fixtureParameter")) shouldBeEqualTo true
            hasArgumentWithName(listOf("otherParameter")) shouldBeEqualTo false
            hasArgumentWithName(listOf("fixtureParameter", "otherParameter")) shouldBeEqualTo true
            hasArgumentWithName(setOf("fixtureParameter")) shouldBeEqualTo true
            hasArgumentWithName(setOf("otherParameter")) shouldBeEqualTo false
            hasArgumentWithName(setOf("fixtureParameter", "otherParameter")) shouldBeEqualTo true
            hasArgumentsWithAllNames("fixtureParameter") shouldBeEqualTo true
            hasArgumentsWithAllNames("fixtureParameter", "otherParameter") shouldBeEqualTo false
            hasArgumentsWithAllNames(listOf("fixtureParameter")) shouldBeEqualTo true
            hasArgumentsWithAllNames(listOf("fixtureParameter", "otherParameter")) shouldBeEqualTo false
            hasArgumentsWithAllNames(setOf("fixtureParameter")) shouldBeEqualTo true
            hasArgumentsWithAllNames(setOf("fixtureParameter", "otherParameter")) shouldBeEqualTo false
            hasArgument { it.value == "text" } shouldBeEqualTo true
            hasArgument { it.value == "other" } shouldBeEqualTo false
            hasAllArguments { it.value == "text" } shouldBeEqualTo true
            hasAllArguments { it.value == "other" } shouldBeEqualTo false
        }
    }

    @Test
    fun `annotation-with-two-arguments`() {
        // given
        val sut =
            getSnippetFile("annotation-with-two-arguments")
                .functions()
                .annotations
                .first()

        // then
        assertSoftly(sut) {
            arguments.map { it.value } shouldBeEqualTo listOf("text", "true")
            numArguments shouldBeEqualTo 2
            countArguments { it.value?.startsWith("t") ?: false } shouldBeEqualTo 2
            countArguments { it.value == "text" } shouldBeEqualTo 1
            hasArguments() shouldBeEqualTo true
            hasArgumentWithName(emptyList()) shouldBeEqualTo true
            hasArgumentWithName(emptySet()) shouldBeEqualTo true
            hasArgumentsWithAllNames(emptyList()) shouldBeEqualTo true
            hasArgumentsWithAllNames(emptySet()) shouldBeEqualTo true
            hasArgumentWithName("fixtureParameter1") shouldBeEqualTo true
            hasArgumentWithName("otherParameter") shouldBeEqualTo false
            hasArgumentWithName("fixtureParameter1", "otherName") shouldBeEqualTo true
            hasArgumentWithName(listOf("fixtureParameter1")) shouldBeEqualTo true
            hasArgumentWithName(listOf("otherParameter")) shouldBeEqualTo false
            hasArgumentWithName(listOf("fixtureParameter1", "otherName")) shouldBeEqualTo true
            hasArgumentWithName(setOf("fixtureParameter1")) shouldBeEqualTo true
            hasArgumentWithName(setOf("otherParameter")) shouldBeEqualTo false
            hasArgumentWithName(setOf("fixtureParameter1", "otherName")) shouldBeEqualTo true
            hasArgumentsWithAllNames("fixtureParameter1") shouldBeEqualTo true
            hasArgumentsWithAllNames("fixtureParameter1", "fixtureParameter2") shouldBeEqualTo false
            hasArgumentsWithAllNames("fixtureParameter1", "otherParameter") shouldBeEqualTo false
            hasArgumentsWithAllNames(listOf("fixtureParameter1")) shouldBeEqualTo true
            hasArgumentsWithAllNames(listOf("fixtureParameter1", "fixtureParameter2")) shouldBeEqualTo false
            hasArgumentsWithAllNames(listOf("fixtureParameter1", "otherParameter")) shouldBeEqualTo false
            hasArgumentsWithAllNames(setOf("fixtureParameter1")) shouldBeEqualTo true
            hasArgumentsWithAllNames(setOf("fixtureParameter1", "fixtureParameter2")) shouldBeEqualTo false
            hasArgumentsWithAllNames(setOf("fixtureParameter1", "otherParameter")) shouldBeEqualTo false
            hasArgument { it.value == "text" } shouldBeEqualTo true
            hasArgument { it.value == "other" } shouldBeEqualTo false
            hasAllArguments { it.value?.startsWith("t") ?: false } shouldBeEqualTo true
            hasAllArguments { it.value?.startsWith("k") ?: false } shouldBeEqualTo false
        }
    }

    @Test
    fun `annotation-with-multiline-string-argument`() {
        // given
        val sut =
            getSnippetFile("annotation-with-multiline-string-argument")
                .functions()
                .annotations
                .first()

        // then
        assertSoftly(sut) {
            arguments.map { it.value } shouldBeEqualTo
                listOf(
                    "first line\n    second line",
                )
            numArguments shouldBeEqualTo 1
            hasArguments() shouldBeEqualTo true
        }
    }

    @Test
    fun `annotation-without-arguments-ignore-case`() {
        // given
        val sut =
            getSnippetFile("annotation-without-arguments-ignore-case")
                .functions()
                .annotations
                .first()

        // then
        assertSoftly(sut) {
            hasArgumentWithName("fixtureparameter") shouldBeEqualTo false
            hasArgumentWithName("fixtureparameter", ignoreCase = true) shouldBeEqualTo false
            hasArgumentWithName(listOf("fixtureparameter")) shouldBeEqualTo false
            hasArgumentWithName(listOf("fixtureparameter"), ignoreCase = true) shouldBeEqualTo false
            hasArgumentWithName(setOf("fixtureparameter")) shouldBeEqualTo false
            hasArgumentWithName(setOf("fixtureparameter"), ignoreCase = true) shouldBeEqualTo false
            hasArgumentsWithAllNames("fixtureparameter1", "fixtureparameter2") shouldBeEqualTo false
            hasArgumentsWithAllNames("fixtureparameter1", "fixtureparameter2", ignoreCase = true) shouldBeEqualTo false
            hasArgumentsWithAllNames(listOf("fixtureparameter1", "fixtureparameter2")) shouldBeEqualTo false
            hasArgumentsWithAllNames(listOf("fixtureparameter1", "fixtureparameter2"), ignoreCase = true) shouldBeEqualTo false
            hasArgumentsWithAllNames(setOf("fixtureparameter1", "fixtureparameter2")) shouldBeEqualTo false
            hasArgumentsWithAllNames(setOf("fixtureparameter1", "fixtureparameter2"), ignoreCase = true) shouldBeEqualTo false
        }
    }

    @Test
    fun `annotation-with-constructor-invocation-without-arguments-ignore-case`() {
        // given
        val sut =
            getSnippetFile("annotation-with-constructor-invocation-without-arguments-ignore-case")
                .functions()
                .annotations
                .first()

        // then
        assertSoftly(sut) {
            hasArgumentWithName("fixtureparameter") shouldBeEqualTo false
            hasArgumentWithName("fixtureparameter", ignoreCase = true) shouldBeEqualTo false
            hasArgumentWithName(listOf("fixtureparameter")) shouldBeEqualTo false
            hasArgumentWithName(listOf("fixtureparameter"), ignoreCase = true) shouldBeEqualTo false
            hasArgumentWithName(setOf("fixtureparameter")) shouldBeEqualTo false
            hasArgumentWithName(setOf("fixtureparameter"), ignoreCase = true) shouldBeEqualTo false
            hasArgumentsWithAllNames("fixtureparameter1", "fixtureparameter2") shouldBeEqualTo false
            hasArgumentsWithAllNames("fixtureparameter1", "fixtureparameter2", ignoreCase = true) shouldBeEqualTo false
            hasArgumentsWithAllNames(listOf("fixtureparameter1", "fixtureparameter2")) shouldBeEqualTo false
            hasArgumentsWithAllNames(listOf("fixtureparameter1", "fixtureparameter2"), ignoreCase = true) shouldBeEqualTo false
            hasArgumentsWithAllNames(setOf("fixtureparameter1", "fixtureparameter2")) shouldBeEqualTo false
            hasArgumentsWithAllNames(setOf("fixtureparameter1", "fixtureparameter2"), ignoreCase = true) shouldBeEqualTo false
        }
    }

    @Test
    fun `annotation-with-arguments-ignore-case`() {
        // given
        val sut =
            getSnippetFile("annotation-with-arguments-ignore-case")
                .functions()
                .annotations
                .first()

        // then
        assertSoftly(sut) {
            hasArgumentWithName("fixtureparameter1") shouldBeEqualTo false
            hasArgumentWithName("fixtureparameter1", ignoreCase = true) shouldBeEqualTo true
            hasArgumentWithName("otherparameter") shouldBeEqualTo false
            hasArgumentWithName("otherparameter", ignoreCase = true) shouldBeEqualTo false
            hasArgumentWithName("fixtureparameter1", "otherName") shouldBeEqualTo false
            hasArgumentWithName("fixtureparameter1", "otherName", ignoreCase = true) shouldBeEqualTo true
            hasArgumentWithName(listOf("fixtureparameter1")) shouldBeEqualTo false
            hasArgumentWithName(listOf("fixtureparameter1"), ignoreCase = true) shouldBeEqualTo true
            hasArgumentWithName(listOf("otherparameter")) shouldBeEqualTo false
            hasArgumentWithName(listOf("otherparameter"), ignoreCase = true) shouldBeEqualTo false
            hasArgumentWithName(listOf("fixtureparameter1", "otherName")) shouldBeEqualTo false
            hasArgumentWithName(listOf("fixtureparameter1", "otherName"), ignoreCase = true) shouldBeEqualTo true
            hasArgumentWithName(setOf("fixtureparameter1")) shouldBeEqualTo false
            hasArgumentWithName(setOf("fixtureparameter1"), ignoreCase = true) shouldBeEqualTo true
            hasArgumentWithName(setOf("otherparameter")) shouldBeEqualTo false
            hasArgumentWithName(setOf("otherparameter"), ignoreCase = true) shouldBeEqualTo false
            hasArgumentWithName(setOf("fixtureparameter1", "otherName")) shouldBeEqualTo false
            hasArgumentWithName(setOf("fixtureparameter1", "otherName"), ignoreCase = true) shouldBeEqualTo true
            hasArgumentsWithAllNames("fixtureparameter1") shouldBeEqualTo false
            hasArgumentsWithAllNames("fixtureparameter1", ignoreCase = true) shouldBeEqualTo true
            hasArgumentsWithAllNames("fixtureparameter1", "fixtureparameter2") shouldBeEqualTo false
            hasArgumentsWithAllNames("fixtureparameter1", "fixtureparameter2", ignoreCase = true) shouldBeEqualTo false
            hasArgumentsWithAllNames("fixtureparameter1", "otherparameter") shouldBeEqualTo false
            hasArgumentsWithAllNames("fixtureparameter1", "otherparameter", ignoreCase = true) shouldBeEqualTo false
            hasArgumentsWithAllNames(listOf("fixtureparameter1")) shouldBeEqualTo false
            hasArgumentsWithAllNames(listOf("fixtureparameter1"), ignoreCase = true) shouldBeEqualTo true
            hasArgumentsWithAllNames(listOf("fixtureparameter1", "fixtureparameter2")) shouldBeEqualTo false
            hasArgumentsWithAllNames(listOf("fixtureparameter1", "fixtureparameter2"), ignoreCase = true) shouldBeEqualTo false
            hasArgumentsWithAllNames(listOf("fixtureparameter1", "otherparameter")) shouldBeEqualTo false
            hasArgumentsWithAllNames(listOf("fixtureparameter1", "otherparameter"), ignoreCase = true) shouldBeEqualTo false
            hasArgumentsWithAllNames(setOf("fixtureparameter1")) shouldBeEqualTo false
            hasArgumentsWithAllNames(setOf("fixtureparameter1"), ignoreCase = true) shouldBeEqualTo true
            hasArgumentsWithAllNames(setOf("fixtureparameter1", "fixtureparameter2")) shouldBeEqualTo false
            hasArgumentsWithAllNames(setOf("fixtureparameter1", "fixtureparameter2"), ignoreCase = true) shouldBeEqualTo false
            hasArgumentsWithAllNames(setOf("fixtureparameter1", "otherparameter")) shouldBeEqualTo false
            hasArgumentsWithAllNames(setOf("fixtureparameter1", "otherparameter"), ignoreCase = true) shouldBeEqualTo false
        }
    }

    private fun getSnippetFile(fileName: String) = getSnippetKoScope("core/declaration/koannotation/snippet/forkoargument/", fileName)
}
