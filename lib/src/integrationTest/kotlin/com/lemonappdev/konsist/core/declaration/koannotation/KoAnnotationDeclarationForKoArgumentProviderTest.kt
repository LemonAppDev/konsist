package com.lemonappdev.konsist.core.declaration.koannotation

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import com.lemonappdev.konsist.api.ext.list.provider.annotations
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
            hasArgumentWithName("sampleArgument") shouldBeEqualTo false
            hasArgumentWithName(listOf("sampleArgument")) shouldBeEqualTo false
            hasArgumentWithName(setOf("sampleArgument")) shouldBeEqualTo false
            hasArgumentsWithAllNames("sampleArgument1", "sampleArgument2") shouldBeEqualTo false
            hasArgumentsWithAllNames(listOf("sampleArgument1", "sampleArgument2")) shouldBeEqualTo false
            hasArgumentsWithAllNames(setOf("sampleArgument1", "sampleArgument2")) shouldBeEqualTo false
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
            hasArgumentWithName("sampleArgument") shouldBeEqualTo false
            hasArgumentWithName(listOf("sampleArgument")) shouldBeEqualTo false
            hasArgumentWithName(setOf("sampleArgument")) shouldBeEqualTo false
            hasArgumentsWithAllNames("sampleArgument1", "sampleArgument2") shouldBeEqualTo false
            hasArgumentsWithAllNames(listOf("sampleArgument1", "sampleArgument2")) shouldBeEqualTo false
            hasArgumentsWithAllNames(setOf("sampleArgument1", "sampleArgument2")) shouldBeEqualTo false
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
            hasArgumentWithName("sampleParameter") shouldBeEqualTo true
            hasArgumentWithName("otherParameter") shouldBeEqualTo false
            hasArgumentWithName("sampleParameter", "otherParameter") shouldBeEqualTo true
            hasArgumentWithName(listOf("sampleParameter")) shouldBeEqualTo true
            hasArgumentWithName(listOf("otherParameter")) shouldBeEqualTo false
            hasArgumentWithName(listOf("sampleParameter", "otherParameter")) shouldBeEqualTo true
            hasArgumentWithName(setOf("sampleParameter")) shouldBeEqualTo true
            hasArgumentWithName(setOf("otherParameter")) shouldBeEqualTo false
            hasArgumentWithName(setOf("sampleParameter", "otherParameter")) shouldBeEqualTo true
            hasArgumentsWithAllNames("sampleParameter") shouldBeEqualTo true
            hasArgumentsWithAllNames("sampleParameter", "otherParameter") shouldBeEqualTo false
            hasArgumentsWithAllNames(listOf("sampleParameter")) shouldBeEqualTo true
            hasArgumentsWithAllNames(listOf("sampleParameter", "otherParameter")) shouldBeEqualTo false
            hasArgumentsWithAllNames(setOf("sampleParameter")) shouldBeEqualTo true
            hasArgumentsWithAllNames(setOf("sampleParameter", "otherParameter")) shouldBeEqualTo false
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
            hasArgumentWithName("sampleParameter1") shouldBeEqualTo true
            hasArgumentWithName("otherParameter") shouldBeEqualTo false
            hasArgumentWithName("sampleParameter1", "otherName") shouldBeEqualTo true
            hasArgumentWithName(listOf("sampleParameter1")) shouldBeEqualTo true
            hasArgumentWithName(listOf("otherParameter")) shouldBeEqualTo false
            hasArgumentWithName(listOf("sampleParameter1", "otherName")) shouldBeEqualTo true
            hasArgumentWithName(setOf("sampleParameter1")) shouldBeEqualTo true
            hasArgumentWithName(setOf("otherParameter")) shouldBeEqualTo false
            hasArgumentWithName(setOf("sampleParameter1", "otherName")) shouldBeEqualTo true
            hasArgumentsWithAllNames("sampleParameter1") shouldBeEqualTo true
            hasArgumentsWithAllNames("sampleParameter1", "sampleParameter2") shouldBeEqualTo false
            hasArgumentsWithAllNames("sampleParameter1", "otherParameter") shouldBeEqualTo false
            hasArgumentsWithAllNames(listOf("sampleParameter1")) shouldBeEqualTo true
            hasArgumentsWithAllNames(listOf("sampleParameter1", "sampleParameter2")) shouldBeEqualTo false
            hasArgumentsWithAllNames(listOf("sampleParameter1", "otherParameter")) shouldBeEqualTo false
            hasArgumentsWithAllNames(setOf("sampleParameter1")) shouldBeEqualTo true
            hasArgumentsWithAllNames(setOf("sampleParameter1", "sampleParameter2")) shouldBeEqualTo false
            hasArgumentsWithAllNames(setOf("sampleParameter1", "otherParameter")) shouldBeEqualTo false
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

    private fun getSnippetFile(fileName: String) = getSnippetKoScope("core/declaration/koannotation/snippet/forkoargument/", fileName)
}
