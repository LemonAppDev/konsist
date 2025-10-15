package com.lemonappdev.konsist.core.declaration.kogetter

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoGetterDeclarationForKoLocalFunctionProviderTest {
    @Test
    fun `getter-contains-no-local-function`() {
        // given
        val sut =
            getSnippetFile("getter-contains-no-local-function")
                .properties()
                .first()
                .getter

        // then
        assertSoftly(sut) {
            it?.localFunctions shouldBeEqualTo emptyList()
            it?.numLocalFunctions shouldBeEqualTo 0
            it?.countLocalFunctions { localFunction -> localFunction.name == "sampleLocalFunction" } shouldBeEqualTo 0
            it?.hasLocalFunctions() shouldBeEqualTo false
            it?.hasLocalFunctionWithName(emptyList()) shouldBeEqualTo false
            it?.hasLocalFunctionWithName(emptySet()) shouldBeEqualTo false
            it?.hasLocalFunctionsWithAllNames(emptyList()) shouldBeEqualTo false
            it?.hasLocalFunctionsWithAllNames(emptySet()) shouldBeEqualTo false
            it?.hasLocalFunctionWithName("sampleLocalFunction") shouldBeEqualTo false
            it?.hasLocalFunctionWithName(listOf("sampleLocalFunction")) shouldBeEqualTo false
            it?.hasLocalFunctionWithName(setOf("sampleLocalFunction")) shouldBeEqualTo false
            it?.hasLocalFunctionsWithAllNames("sampleLocalFunction1", "sampleLocalFunction2") shouldBeEqualTo false
            it?.hasLocalFunctionsWithAllNames(listOf("sampleLocalFunction1", "sampleLocalFunction2")) shouldBeEqualTo false
            it?.hasLocalFunctionsWithAllNames(setOf("sampleLocalFunction1", "sampleLocalFunction2")) shouldBeEqualTo false
            it?.hasLocalFunction { localFunction -> localFunction.name == "sampleLocalFunction" } shouldBeEqualTo false
            it?.hasAllLocalFunctions { localFunction -> localFunction.name == "sampleLocalFunction" } shouldBeEqualTo true
        }
    }

    @Test
    fun `getter-contains-local-function`() {
        // given
        val sut =
            getSnippetFile("getter-contains-local-function")
                .properties()
                .first()
                .getter

        // then
        assertSoftly(sut) {
            it?.numLocalFunctions shouldBeEqualTo 2
            it?.countLocalFunctions { localFunction -> localFunction.name == "sampleLocalFunction1" } shouldBeEqualTo 1
            it?.hasLocalFunctions() shouldBeEqualTo true
            it?.hasLocalFunctionWithName(emptyList()) shouldBeEqualTo true
            it?.hasLocalFunctionWithName(emptySet()) shouldBeEqualTo true
            it?.hasLocalFunctionsWithAllNames(emptyList()) shouldBeEqualTo true
            it?.hasLocalFunctionsWithAllNames(emptySet()) shouldBeEqualTo true
            it?.hasLocalFunctionWithName("sampleLocalFunction1") shouldBeEqualTo true
            it?.hasLocalFunctionWithName("otherLocalFunction") shouldBeEqualTo false
            it?.hasLocalFunctionWithName("sampleLocalFunction1", "otherLocalFunction") shouldBeEqualTo true
            it?.hasLocalFunctionWithName(listOf("sampleLocalFunction1")) shouldBeEqualTo true
            it?.hasLocalFunctionWithName(listOf("otherLocalFunction")) shouldBeEqualTo false
            it?.hasLocalFunctionWithName(listOf("sampleLocalFunction1", "otherLocalFunction")) shouldBeEqualTo true
            it?.hasLocalFunctionWithName(setOf("sampleLocalFunction1")) shouldBeEqualTo true
            it?.hasLocalFunctionWithName(setOf("otherLocalFunction")) shouldBeEqualTo false
            it?.hasLocalFunctionWithName(setOf("sampleLocalFunction1", "otherLocalFunction")) shouldBeEqualTo true
            it?.hasLocalFunctionsWithAllNames("sampleLocalFunction1") shouldBeEqualTo true
            it?.hasLocalFunctionsWithAllNames("sampleLocalFunction1", "sampleLocalFunction2") shouldBeEqualTo true
            it?.hasLocalFunctionsWithAllNames("sampleLocalFunction1", "otherLocalFunction") shouldBeEqualTo false
            it?.hasLocalFunctionsWithAllNames(listOf("sampleLocalFunction1")) shouldBeEqualTo true
            it?.hasLocalFunctionsWithAllNames(listOf("sampleLocalFunction1", "sampleLocalFunction2")) shouldBeEqualTo true
            it?.hasLocalFunctionsWithAllNames(listOf("sampleLocalFunction1", "otherLocalFunction")) shouldBeEqualTo false
            it?.hasLocalFunctionsWithAllNames(setOf("sampleLocalFunction1")) shouldBeEqualTo true
            it?.hasLocalFunctionsWithAllNames(setOf("sampleLocalFunction1", "sampleLocalFunction2")) shouldBeEqualTo true
            it?.hasLocalFunctionsWithAllNames(setOf("sampleLocalFunction1", "otherLocalFunction")) shouldBeEqualTo false
            it?.hasLocalFunction { localFunction -> localFunction.name == "sampleLocalFunction1" } shouldBeEqualTo true
            it?.hasLocalFunction { localFunction -> localFunction.name == "otherLocalFunction" } shouldBeEqualTo false
            it?.hasAllLocalFunctions { localFunction ->
                localFunction.name.endsWith("2") || localFunction.name == "sampleLocalFunction1"
            } shouldBeEqualTo
                true
            it?.hasAllLocalFunctions { localFunction -> localFunction.name.endsWith("2") } shouldBeEqualTo false
            it
                ?.localFunctions
                ?.map { localFunction -> localFunction.name }
                .shouldBeEqualTo(listOf("sampleLocalFunction1", "sampleLocalFunction2"))
        }
    }

    @Test
    fun `getter-contains-no-local-function-ignore-case`() {
        // given
        val sut =
            getSnippetFile("getter-contains-no-local-function-ignore-case")
                .properties()
                .first()
                .getter

        // then
        assertSoftly(sut) {
            it?.hasLocalFunctionWithName("samplelocalfunction") shouldBeEqualTo false
            it?.hasLocalFunctionWithName("samplelocalfunction", ignoreCase = true) shouldBeEqualTo false
            it?.hasLocalFunctionWithName(listOf("samplelocalfunction")) shouldBeEqualTo false
            it?.hasLocalFunctionWithName(listOf("samplelocalfunction"), ignoreCase = true) shouldBeEqualTo false
            it?.hasLocalFunctionWithName(setOf("samplelocalfunction")) shouldBeEqualTo false
            it?.hasLocalFunctionWithName(setOf("samplelocalfunction"), ignoreCase = true) shouldBeEqualTo false
            it?.hasLocalFunctionsWithAllNames("samplelocalfunction1", "samplelocalfunction2") shouldBeEqualTo false
            it?.hasLocalFunctionsWithAllNames("samplelocalfunction1", "samplelocalfunction2", ignoreCase = true) shouldBeEqualTo false
            it?.hasLocalFunctionsWithAllNames(listOf("samplelocalfunction1", "samplelocalfunction2")) shouldBeEqualTo false
            it?.hasLocalFunctionsWithAllNames(listOf("samplelocalfunction1", "samplelocalfunction2"), ignoreCase = true) shouldBeEqualTo
                false
            it?.hasLocalFunctionsWithAllNames(setOf("samplelocalfunction1", "samplelocalfunction2")) shouldBeEqualTo false
            it?.hasLocalFunctionsWithAllNames(setOf("samplelocalfunction1", "samplelocalfunction2"), ignoreCase = true) shouldBeEqualTo
                false
        }
    }

    @Test
    fun `getter-contains-local-function-ignore-case`() {
        // given
        val sut =
            getSnippetFile("getter-contains-local-function-ignore-case")
                .properties()
                .first()
                .getter

        // then
        assertSoftly(sut) {
            it?.hasLocalFunctionWithName("samplelocalfunction1") shouldBeEqualTo false
            it?.hasLocalFunctionWithName("samplelocalfunction1", ignoreCase = true) shouldBeEqualTo true
            it?.hasLocalFunctionWithName("otherlocalfunction") shouldBeEqualTo false
            it?.hasLocalFunctionWithName("otherlocalfunction", ignoreCase = true) shouldBeEqualTo false
            it?.hasLocalFunctionWithName("samplelocalfunction1", "otherName") shouldBeEqualTo false
            it?.hasLocalFunctionWithName("samplelocalfunction1", "otherName", ignoreCase = true) shouldBeEqualTo true
            it?.hasLocalFunctionWithName(listOf("samplelocalfunction1")) shouldBeEqualTo false
            it?.hasLocalFunctionWithName(listOf("samplelocalfunction1"), ignoreCase = true) shouldBeEqualTo true
            it?.hasLocalFunctionWithName(listOf("otherlocalfunction")) shouldBeEqualTo false
            it?.hasLocalFunctionWithName(listOf("otherlocalfunction"), ignoreCase = true) shouldBeEqualTo false
            it?.hasLocalFunctionWithName(listOf("samplelocalfunction1", "otherName")) shouldBeEqualTo false
            it?.hasLocalFunctionWithName(listOf("samplelocalfunction1", "otherName"), ignoreCase = true) shouldBeEqualTo true
            it?.hasLocalFunctionsWithAllNames("samplelocalfunction1") shouldBeEqualTo false
            it?.hasLocalFunctionsWithAllNames("samplelocalfunction1", ignoreCase = true) shouldBeEqualTo true
            it?.hasLocalFunctionsWithAllNames("samplelocalfunction1", "samplelocalfunction2") shouldBeEqualTo false
            it?.hasLocalFunctionsWithAllNames("samplelocalfunction1", "samplelocalfunction2", ignoreCase = true) shouldBeEqualTo true
            it?.hasLocalFunctionsWithAllNames("samplelocalfunction1", "otherlocalfunction") shouldBeEqualTo false
            it?.hasLocalFunctionsWithAllNames("samplelocalfunction1", "otherlocalfunction", ignoreCase = true) shouldBeEqualTo false
            it?.hasLocalFunctionsWithAllNames(listOf("samplelocalfunction1")) shouldBeEqualTo false
            it?.hasLocalFunctionsWithAllNames(listOf("samplelocalfunction1"), ignoreCase = true) shouldBeEqualTo true
            it?.hasLocalFunctionsWithAllNames(listOf("samplelocalfunction1", "samplelocalfunction2")) shouldBeEqualTo false
            it?.hasLocalFunctionsWithAllNames(listOf("samplelocalfunction1", "samplelocalfunction2"), ignoreCase = true) shouldBeEqualTo
                true
            it?.hasLocalFunctionsWithAllNames(listOf("samplelocalfunction1", "otherlocalfunction")) shouldBeEqualTo false
            it?.hasLocalFunctionsWithAllNames(listOf("samplelocalfunction1", "otherlocalfunction"), ignoreCase = true) shouldBeEqualTo false
        }
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/kogetter/snippet/forkolocalfunctionprovider/", fileName)
}
