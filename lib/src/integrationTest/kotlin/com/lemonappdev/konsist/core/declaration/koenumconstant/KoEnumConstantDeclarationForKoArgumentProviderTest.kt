package com.lemonappdev.konsist.core.declaration.koenumconstant

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import com.lemonappdev.konsist.api.ext.list.enumConstants
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoEnumConstantDeclarationForKoArgumentProviderTest {
    @Test
    fun `enum-const-without-arguments`() {
        // given
        val sut =
            getSnippetFile("enum-const-without-arguments")
                .classes()
                .enumConstants
                .first()

        // then
        assertSoftly(sut) {
            arguments shouldBeEqualTo emptyList()
            numArguments shouldBeEqualTo 0
            countArguments { it.value == "text" } shouldBeEqualTo 0
            hasArguments() shouldBeEqualTo false
            hasArgumentWithName("sampleArgument") shouldBeEqualTo false
            hasArgumentsWithAllNames("sampleArgument1", "sampleArgument2") shouldBeEqualTo false
            hasArgument { it.value == "text" } shouldBeEqualTo false
            hasAllArguments { it.value == "text" } shouldBeEqualTo true
        }
    }

    @Test
    fun `enum-const-with-constructor-invocation-without-arguments`() {
        // given
        val sut =
            getSnippetFile("enum-const-with-constructor-invocation-without-arguments")
                .classes()
                .enumConstants
                .first()

        // then
        assertSoftly(sut) {
            arguments shouldBeEqualTo emptyList()
            numArguments shouldBeEqualTo 0
            countArguments { it.value == "text" } shouldBeEqualTo 0
            hasArguments() shouldBeEqualTo false
            hasArgumentWithName("sampleArgument") shouldBeEqualTo false
            hasArgumentsWithAllNames("sampleArgument1", "sampleArgument2") shouldBeEqualTo false
            hasArgument { it.value == "text" } shouldBeEqualTo false
            hasAllArguments { it.value == "text" } shouldBeEqualTo true
        }
    }

    @Test
    fun `enum-const-with-one-argument`() {
        // given
        val sut =
            getSnippetFile("enum-const-with-one-argument")
                .classes()
                .enumConstants
                .first()

        // then
        assertSoftly(sut) {
            arguments.map { it.value } shouldBeEqualTo listOf("0")
            numArguments shouldBeEqualTo 1
            countArguments { it.value == "0" } shouldBeEqualTo 1
            countArguments { it.value == "1" } shouldBeEqualTo 0
            hasArguments() shouldBeEqualTo true
            hasArgumentWithName("sampleParameter") shouldBeEqualTo true
            hasArgumentWithName("otherParameter") shouldBeEqualTo false
            hasArgumentWithName("sampleParameter", "otherParameter") shouldBeEqualTo true
            hasArgumentsWithAllNames("sampleParameter") shouldBeEqualTo true
            hasArgumentsWithAllNames("sampleParameter", "otherParameter") shouldBeEqualTo false
            hasArgument { it.value == "0" } shouldBeEqualTo true
            hasArgument { it.value == "1" } shouldBeEqualTo false
            hasAllArguments { it.value == "0" } shouldBeEqualTo true
            hasAllArguments { it.value == "1" } shouldBeEqualTo false
        }
    }

    @Test
    fun `enum-const-with-two-arguments`() {
        // given
        val sut =
            getSnippetFile("enum-const-with-two-arguments")
                .classes()
                .enumConstants
                .first()

        // then
        assertSoftly(sut) {
            arguments.map { it.value } shouldBeEqualTo listOf("0", "false")
            numArguments shouldBeEqualTo 2
            countArguments { it.value?.startsWith("fal") ?: false || it.value == "0" } shouldBeEqualTo 2
            countArguments { it.value == "0" } shouldBeEqualTo 1
            hasArguments() shouldBeEqualTo true
            hasArgumentWithName("sampleParameter1") shouldBeEqualTo true
            hasArgumentWithName("otherParameter") shouldBeEqualTo false
            hasArgumentWithName("sampleParameter1", "otherName") shouldBeEqualTo true
            hasArgumentsWithAllNames("sampleParameter1") shouldBeEqualTo true
            hasArgumentsWithAllNames("sampleParameter1", "sampleParameter2") shouldBeEqualTo false
            hasArgumentsWithAllNames("sampleParameter1", "otherParameter") shouldBeEqualTo false
            hasArgument { it.value == "0" } shouldBeEqualTo true
            hasArgument { it.value == "1" } shouldBeEqualTo false
            hasAllArguments { it.value?.startsWith("fal") ?: false || it.value == "0" } shouldBeEqualTo true
            hasAllArguments { it.value?.startsWith("k") ?: false } shouldBeEqualTo false
        }
    }

    @Test
    fun `enum-const-with-multiline-string-argument`() {
        // given
        val sut =
            getSnippetFile("enum-const-with-multiline-string-argument")
                .classes()
                .enumConstants
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

    private fun getSnippetFile(fileName: String) = getSnippetKoScope("core/declaration/koenumconstant/snippet/forkoargument/", fileName)
}
