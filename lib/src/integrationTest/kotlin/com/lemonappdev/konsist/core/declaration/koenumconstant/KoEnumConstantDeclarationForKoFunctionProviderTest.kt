package com.lemonappdev.konsist.core.declaration.koenumconstant

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import com.lemonappdev.konsist.api.ext.list.enumConstants
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoEnumConstantDeclarationForKoFunctionProviderTest {
    @Test
    fun `enum-constant-contains-no-function`() {
        // given
        val sut =
            getSnippetFile("enum-constant-contains-no-function")
                .classes()
                .enumConstants
                .first()

        // then
        assertSoftly(sut) {
            functions() shouldBeEqualTo emptyList()
            numFunctions() shouldBeEqualTo 0
            countFunctions { it.name == "sampleFunction" } shouldBeEqualTo 0
            hasFunctions() shouldBeEqualTo false
            hasFunctionWithName(emptyList()) shouldBeEqualTo false
            hasFunctionWithName(emptySet()) shouldBeEqualTo false
            hasFunctionsWithAllNames(emptyList()) shouldBeEqualTo false
            hasFunctionsWithAllNames(emptySet()) shouldBeEqualTo false
            hasFunctionWithName("sampleFunction") shouldBeEqualTo false
            hasFunctionWithName(listOf("sampleFunction")) shouldBeEqualTo false
            hasFunctionWithName(setOf("sampleFunction")) shouldBeEqualTo false
            hasFunctionsWithAllNames("sampleFunction1", "sampleFunction2") shouldBeEqualTo false
            hasFunctionsWithAllNames(listOf("sampleFunction1", "sampleFunction2")) shouldBeEqualTo false
            hasFunctionsWithAllNames(setOf("sampleFunction1", "sampleFunction2")) shouldBeEqualTo false
            hasFunction { it.name == "sampleFunction" } shouldBeEqualTo false
            hasAllFunctions { it.name == "sampleFunction" } shouldBeEqualTo true
        }
    }

    @Test
    fun `enum-constant-contains-function`() {
        // given
        val sut =
            getSnippetFile("enum-constant-contains-function")
                .classes()
                .enumConstants
                .first()

        // then
        assertSoftly(sut) {
            numFunctions() shouldBeEqualTo 2
            countFunctions { it.name == "sampleFunction1" } shouldBeEqualTo 1
            hasFunctions() shouldBeEqualTo true
            hasFunctionWithName(emptyList()) shouldBeEqualTo true
            hasFunctionWithName(emptySet()) shouldBeEqualTo true
            hasFunctionsWithAllNames(emptyList()) shouldBeEqualTo true
            hasFunctionsWithAllNames(emptySet()) shouldBeEqualTo true
            hasFunctionWithName("sampleFunction1") shouldBeEqualTo true
            hasFunctionWithName("otherFunction") shouldBeEqualTo false
            hasFunctionWithName("sampleFunction1", "otherFunction") shouldBeEqualTo true
            hasFunctionWithName(listOf("sampleFunction1")) shouldBeEqualTo true
            hasFunctionWithName(listOf("otherFunction")) shouldBeEqualTo false
            hasFunctionWithName(listOf("sampleFunction1", "otherFunction")) shouldBeEqualTo true
            hasFunctionWithName(setOf("sampleFunction1")) shouldBeEqualTo true
            hasFunctionWithName(setOf("otherFunction")) shouldBeEqualTo false
            hasFunctionWithName(setOf("sampleFunction1", "otherFunction")) shouldBeEqualTo true
            hasFunctionsWithAllNames("sampleFunction1") shouldBeEqualTo true
            hasFunctionsWithAllNames("sampleFunction1", "sampleFunction2") shouldBeEqualTo true
            hasFunctionsWithAllNames("sampleFunction1", "otherFunction") shouldBeEqualTo false
            hasFunctionsWithAllNames(listOf("sampleFunction1")) shouldBeEqualTo true
            hasFunctionsWithAllNames(listOf("sampleFunction1", "sampleFunction2")) shouldBeEqualTo true
            hasFunctionsWithAllNames(listOf("sampleFunction1", "otherFunction")) shouldBeEqualTo false
            hasFunctionsWithAllNames(setOf("sampleFunction1")) shouldBeEqualTo true
            hasFunctionsWithAllNames(setOf("sampleFunction1", "sampleFunction2")) shouldBeEqualTo true
            hasFunctionsWithAllNames(setOf("sampleFunction1", "otherFunction")) shouldBeEqualTo false
            hasFunction { it.name == "sampleFunction1" } shouldBeEqualTo true
            hasFunction { it.name == "otherFunction" } shouldBeEqualTo false
            hasAllFunctions { it.name.endsWith("2") || it.name == "sampleFunction1" } shouldBeEqualTo true
            hasAllFunctions { it.name.endsWith("2") } shouldBeEqualTo false
            functions()
                .map { it.name }
                .shouldBeEqualTo(listOf("sampleFunction1", "sampleFunction2"))
        }
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/koenumconstant/snippet/forkofunctionprovider/", fileName)
}
