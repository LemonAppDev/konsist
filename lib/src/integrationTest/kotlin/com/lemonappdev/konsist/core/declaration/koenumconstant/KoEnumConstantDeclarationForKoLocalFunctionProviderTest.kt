package com.lemonappdev.konsist.core.declaration.koenumconstant

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import com.lemonappdev.konsist.api.ext.list.enumConstants
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoEnumConstantDeclarationForKoLocalFunctionProviderTest {
    @Test
    fun `enum-constant-contains-no-local-function`() {
        // given
        val sut =
            getSnippetFile("enum-constant-contains-no-local-function")
                .classes()
                .enumConstants
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
            containsLocalFunction { it.name == "sampleLocalFunction" } shouldBeEqualTo false
        }
    }

    @Test
    fun `enum-constant-contains-local-function`() {
        // given
        val sut =
            getSnippetFile("enum-constant-contains-local-function")
                .classes()
                .enumConstants
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
            containsLocalFunction { it.name == "sampleLocalFunction1" } shouldBeEqualTo true
            containsLocalFunction { it.name == "otherLocalFunction1" } shouldBeEqualTo false
            localFunctions
                .map { it.name }
                .shouldBeEqualTo(listOf("sampleLocalFunction1", "sampleLocalFunction2"))
        }
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/koenumconstant/snippet/forkolocalfunctionprovider/", fileName)
}
