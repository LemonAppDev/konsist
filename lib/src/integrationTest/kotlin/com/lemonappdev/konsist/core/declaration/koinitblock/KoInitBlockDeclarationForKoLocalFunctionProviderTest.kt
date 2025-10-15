package com.lemonappdev.konsist.core.declaration.koinitblock

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import com.lemonappdev.konsist.api.ext.list.initBlocks
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoInitBlockDeclarationForKoLocalFunctionProviderTest {
    @Test
    fun `init-block-contains-no-local-function`() {
        // given
        val sut =
            getSnippetFile("init-block-contains-no-local-function")
                .classes()
                .initBlocks
                .first()

        // then
        assertSoftly(sut) {
            localFunctions shouldBeEqualTo emptyList()
            numLocalFunctions shouldBeEqualTo 0
            countLocalFunctions { it.name == "sampleLocalFunction" } shouldBeEqualTo 0
            hasLocalFunctions() shouldBeEqualTo false
            hasLocalFunctionWithName(emptyList()) shouldBeEqualTo false
            hasLocalFunctionWithName(emptySet()) shouldBeEqualTo false
            hasLocalFunctionsWithAllNames(emptyList()) shouldBeEqualTo false
            hasLocalFunctionsWithAllNames(emptySet()) shouldBeEqualTo false
            hasLocalFunctionWithName("sampleLocalFunction") shouldBeEqualTo false
            hasLocalFunctionWithName(listOf("sampleLocalFunction")) shouldBeEqualTo false
            hasLocalFunctionWithName(setOf("sampleLocalFunction")) shouldBeEqualTo false
            hasLocalFunctionsWithAllNames("sampleLocalFunction1", "sampleLocalFunction2") shouldBeEqualTo false
            hasLocalFunctionsWithAllNames(listOf("sampleLocalFunction1", "sampleLocalFunction2")) shouldBeEqualTo false
            hasLocalFunctionsWithAllNames(setOf("sampleLocalFunction1", "sampleLocalFunction2")) shouldBeEqualTo false
            hasLocalFunction { it.name == "sampleLocalFunction" } shouldBeEqualTo false
            hasAllLocalFunctions { it.name == "sampleLocalFunction" } shouldBeEqualTo true
        }
    }

    @Test
    fun `init-block-contains-local-function`() {
        // given
        val sut =
            getSnippetFile("init-block-contains-local-function")
                .classes()
                .initBlocks
                .first()

        // then
        assertSoftly(sut) {
            numLocalFunctions shouldBeEqualTo 2
            countLocalFunctions { it.name == "sampleLocalFunction1" } shouldBeEqualTo 1
            hasLocalFunctions() shouldBeEqualTo true
            hasLocalFunctionWithName(emptyList()) shouldBeEqualTo true
            hasLocalFunctionWithName(emptySet()) shouldBeEqualTo true
            hasLocalFunctionsWithAllNames(emptyList()) shouldBeEqualTo true
            hasLocalFunctionsWithAllNames(emptySet()) shouldBeEqualTo true
            hasLocalFunctionWithName("sampleLocalFunction1") shouldBeEqualTo true
            hasLocalFunctionWithName("otherLocalFunction") shouldBeEqualTo false
            hasLocalFunctionWithName("sampleLocalFunction1", "otherLocalFunction") shouldBeEqualTo true
            hasLocalFunctionWithName(listOf("sampleLocalFunction1")) shouldBeEqualTo true
            hasLocalFunctionWithName(listOf("otherLocalFunction")) shouldBeEqualTo false
            hasLocalFunctionWithName(listOf("sampleLocalFunction1", "otherLocalFunction")) shouldBeEqualTo true
            hasLocalFunctionWithName(setOf("sampleLocalFunction1")) shouldBeEqualTo true
            hasLocalFunctionWithName(setOf("otherLocalFunction")) shouldBeEqualTo false
            hasLocalFunctionWithName(setOf("sampleLocalFunction1", "otherLocalFunction")) shouldBeEqualTo true
            hasLocalFunctionsWithAllNames("sampleLocalFunction1") shouldBeEqualTo true
            hasLocalFunctionsWithAllNames("sampleLocalFunction1", "sampleLocalFunction2") shouldBeEqualTo true
            hasLocalFunctionsWithAllNames("sampleLocalFunction1", "otherLocalFunction") shouldBeEqualTo false
            hasLocalFunctionsWithAllNames(listOf("sampleLocalFunction1")) shouldBeEqualTo true
            hasLocalFunctionsWithAllNames(listOf("sampleLocalFunction1", "sampleLocalFunction2")) shouldBeEqualTo true
            hasLocalFunctionsWithAllNames(listOf("sampleLocalFunction1", "otherLocalFunction")) shouldBeEqualTo false
            hasLocalFunctionsWithAllNames(setOf("sampleLocalFunction1")) shouldBeEqualTo true
            hasLocalFunctionsWithAllNames(setOf("sampleLocalFunction1", "sampleLocalFunction2")) shouldBeEqualTo true
            hasLocalFunctionsWithAllNames(setOf("sampleLocalFunction1", "otherLocalFunction")) shouldBeEqualTo false
            hasLocalFunction { it.name == "sampleLocalFunction1" } shouldBeEqualTo true
            hasLocalFunction { it.name == "otherLocalFunction" } shouldBeEqualTo false
            hasAllLocalFunctions { it.name.endsWith("2") || it.name == "sampleLocalFunction1" } shouldBeEqualTo true
            hasAllLocalFunctions { it.name.endsWith("2") } shouldBeEqualTo false
            localFunctions
                .map { it.name }
                .shouldBeEqualTo(listOf("sampleLocalFunction1", "sampleLocalFunction2"))
        }
    }

    @Test
    fun `init-block-contains-no-local-function-ignore-case`() {
        // given
        val sut =
            getSnippetFile("init-block-contains-no-local-function-ignore-case")
                .classes()
                .initBlocks
                .first()

        // then
        assertSoftly(sut) {
            hasLocalFunctionWithName("samplelocalfunction") shouldBeEqualTo false
            hasLocalFunctionWithName("samplelocalfunction", ignoreCase = true) shouldBeEqualTo false
            hasLocalFunctionWithName(listOf("samplelocalfunction")) shouldBeEqualTo false
            hasLocalFunctionWithName(listOf("samplelocalfunction"), ignoreCase = true) shouldBeEqualTo false
            hasLocalFunctionWithName(setOf("samplelocalfunction")) shouldBeEqualTo false
            hasLocalFunctionWithName(setOf("samplelocalfunction"), ignoreCase = true) shouldBeEqualTo false
            hasLocalFunctionsWithAllNames("samplelocalfunction1", "samplelocalfunction2") shouldBeEqualTo false
            hasLocalFunctionsWithAllNames("samplelocalfunction1", "samplelocalfunction2", ignoreCase = true) shouldBeEqualTo false
            hasLocalFunctionsWithAllNames(listOf("samplelocalfunction1", "samplelocalfunction2")) shouldBeEqualTo false
            hasLocalFunctionsWithAllNames(listOf("samplelocalfunction1", "samplelocalfunction2"), ignoreCase = true) shouldBeEqualTo false
            hasLocalFunctionsWithAllNames(setOf("samplelocalfunction1", "samplelocalfunction2")) shouldBeEqualTo false
            hasLocalFunctionsWithAllNames(setOf("samplelocalfunction1", "samplelocalfunction2"), ignoreCase = true) shouldBeEqualTo false
        }
    }

    @Test
    fun `init-block-contains-local-function-ignore-case`() {
        // given
        val sut =
            getSnippetFile("init-block-contains-local-function-ignore-case")
                .classes()
                .initBlocks
                .first()

        // then
        assertSoftly(sut) {
            hasLocalFunctionWithName("samplelocalfunction1") shouldBeEqualTo false
            hasLocalFunctionWithName("samplelocalfunction1", ignoreCase = true) shouldBeEqualTo true
            hasLocalFunctionWithName("otherlocalfunction") shouldBeEqualTo false
            hasLocalFunctionWithName("otherlocalfunction", ignoreCase = true) shouldBeEqualTo false
            hasLocalFunctionWithName("samplelocalfunction1", "otherName") shouldBeEqualTo false
            hasLocalFunctionWithName("samplelocalfunction1", "otherName", ignoreCase = true) shouldBeEqualTo true
            hasLocalFunctionWithName(listOf("samplelocalfunction1")) shouldBeEqualTo false
            hasLocalFunctionWithName(listOf("samplelocalfunction1"), ignoreCase = true) shouldBeEqualTo true
            hasLocalFunctionWithName(listOf("otherlocalfunction")) shouldBeEqualTo false
            hasLocalFunctionWithName(listOf("otherlocalfunction"), ignoreCase = true) shouldBeEqualTo false
            hasLocalFunctionWithName(listOf("samplelocalfunction1", "otherName")) shouldBeEqualTo false
            hasLocalFunctionWithName(listOf("samplelocalfunction1", "otherName"), ignoreCase = true) shouldBeEqualTo true
            hasLocalFunctionsWithAllNames("samplelocalfunction1") shouldBeEqualTo false
            hasLocalFunctionsWithAllNames("samplelocalfunction1", ignoreCase = true) shouldBeEqualTo true
            hasLocalFunctionsWithAllNames("samplelocalfunction1", "samplelocalfunction2") shouldBeEqualTo false
            hasLocalFunctionsWithAllNames("samplelocalfunction1", "samplelocalfunction2", ignoreCase = true) shouldBeEqualTo true
            hasLocalFunctionsWithAllNames("samplelocalfunction1", "otherlocalfunction") shouldBeEqualTo false
            hasLocalFunctionsWithAllNames("samplelocalfunction1", "otherlocalfunction", ignoreCase = true) shouldBeEqualTo false
            hasLocalFunctionsWithAllNames(listOf("samplelocalfunction1")) shouldBeEqualTo false
            hasLocalFunctionsWithAllNames(listOf("samplelocalfunction1"), ignoreCase = true) shouldBeEqualTo true
            hasLocalFunctionsWithAllNames(listOf("samplelocalfunction1", "samplelocalfunction2")) shouldBeEqualTo false
            hasLocalFunctionsWithAllNames(listOf("samplelocalfunction1", "samplelocalfunction2"), ignoreCase = true) shouldBeEqualTo true
            hasLocalFunctionsWithAllNames(listOf("samplelocalfunction1", "otherlocalfunction")) shouldBeEqualTo false
            hasLocalFunctionsWithAllNames(listOf("samplelocalfunction1", "otherlocalfunction"), ignoreCase = true) shouldBeEqualTo false
        }
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/koinitblock/snippet/forkolocalfunctionprovider/", fileName)
}
