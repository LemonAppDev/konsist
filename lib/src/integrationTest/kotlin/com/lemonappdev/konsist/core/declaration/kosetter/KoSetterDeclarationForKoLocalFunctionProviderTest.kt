package com.lemonappdev.konsist.core.declaration.kosetter

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoSetterDeclarationForKoLocalFunctionProviderTest {
    @Test
    fun `setter-contains-no-local-function`() {
        // given
        val sut =
            getSnippetFile("setter-contains-no-local-function")
                .properties()
                .first()
                .setter

        // then
        assertSoftly(sut) {
            it?.localFunctions shouldBeEqualTo emptyList()
            it?.numLocalFunctions shouldBeEqualTo 0
            it?.countLocalFunctions { it.name == "sampleLocalFunction" } shouldBeEqualTo 0
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
            it?.hasLocalFunction { it.name == "sampleLocalFunction" } shouldBeEqualTo false
            it?.hasAllLocalFunctions { it.name == "sampleLocalFunction" } shouldBeEqualTo true
            it?.containsLocalFunction { it.name == "sampleLocalFunction" } shouldBeEqualTo false
        }
    }

    @Test
    fun `setter-contains-local-function`() {
        // given
        val sut =
            getSnippetFile("setter-contains-local-function")
                .properties()
                .first()
                .setter

        // then
        assertSoftly(sut) {
            it?.numLocalFunctions shouldBeEqualTo 2
            it?.countLocalFunctions { it.name == "sampleLocalFunction1" } shouldBeEqualTo 1
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
            it?.hasLocalFunction { it.name == "sampleLocalFunction1" } shouldBeEqualTo true
            it?.hasLocalFunction { it.name == "otherLocalFunction" } shouldBeEqualTo false
            it?.hasAllLocalFunctions { it.name.endsWith("2") || it.name == "sampleLocalFunction1" } shouldBeEqualTo true
            it?.hasAllLocalFunctions { it.name.endsWith("2") } shouldBeEqualTo false
            it?.containsLocalFunction { it.name == "sampleLocalFunction1" } shouldBeEqualTo true
            it?.containsLocalFunction { it.name == "otherLocalFunction1" } shouldBeEqualTo false
            it?.localFunctions
                ?.map { it.name }
                .shouldBeEqualTo(listOf("sampleLocalFunction1", "sampleLocalFunction2"))
        }
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/kosetter/snippet/forkolocalfunctionprovider/", fileName)
}
