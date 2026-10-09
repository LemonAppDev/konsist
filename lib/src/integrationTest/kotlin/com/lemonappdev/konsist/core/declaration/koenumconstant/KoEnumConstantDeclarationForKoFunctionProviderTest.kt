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
            countFunctions { it.name == "fixtureFunction" } shouldBeEqualTo 0
            hasFunctions() shouldBeEqualTo false
            hasFunctionWithName(emptyList()) shouldBeEqualTo false
            hasFunctionWithName(emptySet()) shouldBeEqualTo false
            hasFunctionsWithAllNames(emptyList()) shouldBeEqualTo false
            hasFunctionsWithAllNames(emptySet()) shouldBeEqualTo false
            hasFunctionWithName("fixtureFunction") shouldBeEqualTo false
            hasFunctionWithName(listOf("fixtureFunction")) shouldBeEqualTo false
            hasFunctionWithName(setOf("fixtureFunction")) shouldBeEqualTo false
            hasFunctionsWithAllNames("fixtureFunction1", "fixtureFunction2") shouldBeEqualTo false
            hasFunctionsWithAllNames(listOf("fixtureFunction1", "fixtureFunction2")) shouldBeEqualTo false
            hasFunctionsWithAllNames(setOf("fixtureFunction1", "fixtureFunction2")) shouldBeEqualTo false
            hasFunction { it.name == "fixtureFunction" } shouldBeEqualTo false
            hasAllFunctions { it.name == "fixtureFunction" } shouldBeEqualTo true
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
            countFunctions { it.name == "fixtureFunction1" } shouldBeEqualTo 1
            hasFunctions() shouldBeEqualTo true
            hasFunctionWithName(emptyList()) shouldBeEqualTo true
            hasFunctionWithName(emptySet()) shouldBeEqualTo true
            hasFunctionsWithAllNames(emptyList()) shouldBeEqualTo true
            hasFunctionsWithAllNames(emptySet()) shouldBeEqualTo true
            hasFunctionWithName("fixtureFunction1") shouldBeEqualTo true
            hasFunctionWithName("otherFunction") shouldBeEqualTo false
            hasFunctionWithName("fixtureFunction1", "otherFunction") shouldBeEqualTo true
            hasFunctionWithName(listOf("fixtureFunction1")) shouldBeEqualTo true
            hasFunctionWithName(listOf("otherFunction")) shouldBeEqualTo false
            hasFunctionWithName(listOf("fixtureFunction1", "otherFunction")) shouldBeEqualTo true
            hasFunctionWithName(setOf("fixtureFunction1")) shouldBeEqualTo true
            hasFunctionWithName(setOf("otherFunction")) shouldBeEqualTo false
            hasFunctionWithName(setOf("fixtureFunction1", "otherFunction")) shouldBeEqualTo true
            hasFunctionsWithAllNames("fixtureFunction1") shouldBeEqualTo true
            hasFunctionsWithAllNames("fixtureFunction1", "fixtureFunction2") shouldBeEqualTo true
            hasFunctionsWithAllNames("fixtureFunction1", "otherFunction") shouldBeEqualTo false
            hasFunctionsWithAllNames(listOf("fixtureFunction1")) shouldBeEqualTo true
            hasFunctionsWithAllNames(listOf("fixtureFunction1", "fixtureFunction2")) shouldBeEqualTo true
            hasFunctionsWithAllNames(listOf("fixtureFunction1", "otherFunction")) shouldBeEqualTo false
            hasFunctionsWithAllNames(setOf("fixtureFunction1")) shouldBeEqualTo true
            hasFunctionsWithAllNames(setOf("fixtureFunction1", "fixtureFunction2")) shouldBeEqualTo true
            hasFunctionsWithAllNames(setOf("fixtureFunction1", "otherFunction")) shouldBeEqualTo false
            hasFunction { it.name == "fixtureFunction1" } shouldBeEqualTo true
            hasFunction { it.name == "otherFunction" } shouldBeEqualTo false
            hasAllFunctions { it.name.endsWith("2") || it.name == "fixtureFunction1" } shouldBeEqualTo true
            hasAllFunctions { it.name.endsWith("2") } shouldBeEqualTo false
            functions()
                .map { it.name }
                .shouldBeEqualTo(listOf("fixtureFunction1", "fixtureFunction2"))
        }
    }

    @Test
    fun `enum-constant-contains-no-function-ignore-case`() {
        // given
        val sut =
            getSnippetFile("enum-constant-contains-no-function-ignore-case")
                .classes()
                .enumConstants
                .first()

        // then
        assertSoftly(sut) {
            hasFunctionWithName("fixturefunction") shouldBeEqualTo false
            hasFunctionWithName("fixturefunction", ignoreCase = true) shouldBeEqualTo false
            hasFunctionWithName(listOf("fixturefunction")) shouldBeEqualTo false
            hasFunctionWithName(listOf("fixturefunction"), ignoreCase = true) shouldBeEqualTo false
            hasFunctionWithName(setOf("fixturefunction")) shouldBeEqualTo false
            hasFunctionWithName(setOf("fixturefunction"), ignoreCase = true) shouldBeEqualTo false
            hasFunctionsWithAllNames("fixturefunction1", "fixturefunction2") shouldBeEqualTo false
            hasFunctionsWithAllNames("fixturefunction1", "fixturefunction2", ignoreCase = true) shouldBeEqualTo false
            hasFunctionsWithAllNames(listOf("fixturefunction1", "fixturefunction2")) shouldBeEqualTo false
            hasFunctionsWithAllNames(listOf("fixturefunction1", "fixturefunction2"), ignoreCase = true) shouldBeEqualTo false
            hasFunctionsWithAllNames(setOf("fixturefunction1", "fixturefunction2")) shouldBeEqualTo false
            hasFunctionsWithAllNames(setOf("fixturefunction1", "fixturefunction2"), ignoreCase = true) shouldBeEqualTo false
        }
    }

    @Test
    fun `enum-constant-contains-function-ignore-case`() {
        // given
        val sut =
            getSnippetFile("enum-constant-contains-function-ignore-case")
                .classes()
                .enumConstants
                .first()

        // then
        assertSoftly(sut) {
            hasFunctionWithName("fixturefunction1") shouldBeEqualTo false
            hasFunctionWithName("fixturefunction1", ignoreCase = true) shouldBeEqualTo true
            hasFunctionWithName("otherfunction") shouldBeEqualTo false
            hasFunctionWithName("otherfunction", ignoreCase = true) shouldBeEqualTo false
            hasFunctionWithName("fixturefunction1", "otherName") shouldBeEqualTo false
            hasFunctionWithName("fixturefunction1", "otherName", ignoreCase = true) shouldBeEqualTo true
            hasFunctionWithName(listOf("fixturefunction1")) shouldBeEqualTo false
            hasFunctionWithName(listOf("fixturefunction1"), ignoreCase = true) shouldBeEqualTo true
            hasFunctionWithName(listOf("otherfunction")) shouldBeEqualTo false
            hasFunctionWithName(listOf("otherfunction"), ignoreCase = true) shouldBeEqualTo false
            hasFunctionWithName(listOf("fixturefunction1", "otherName")) shouldBeEqualTo false
            hasFunctionWithName(listOf("fixturefunction1", "otherName"), ignoreCase = true) shouldBeEqualTo true
            hasFunctionsWithAllNames("fixturefunction1") shouldBeEqualTo false
            hasFunctionsWithAllNames("fixturefunction1", ignoreCase = true) shouldBeEqualTo true
            hasFunctionsWithAllNames("fixturefunction1", "fixturefunction2") shouldBeEqualTo false
            hasFunctionsWithAllNames("fixturefunction1", "fixturefunction2", ignoreCase = true) shouldBeEqualTo true
            hasFunctionsWithAllNames("fixturefunction1", "otherfunction") shouldBeEqualTo false
            hasFunctionsWithAllNames("fixturefunction1", "otherfunction", ignoreCase = true) shouldBeEqualTo false
            hasFunctionsWithAllNames(listOf("fixturefunction1")) shouldBeEqualTo false
            hasFunctionsWithAllNames(listOf("fixturefunction1"), ignoreCase = true) shouldBeEqualTo true
            hasFunctionsWithAllNames(listOf("fixturefunction1", "fixturefunction2")) shouldBeEqualTo false
            hasFunctionsWithAllNames(listOf("fixturefunction1", "fixturefunction2"), ignoreCase = true) shouldBeEqualTo true
            hasFunctionsWithAllNames(listOf("fixturefunction1", "otherfunction")) shouldBeEqualTo false
            hasFunctionsWithAllNames(listOf("fixturefunction1", "otherfunction"), ignoreCase = true) shouldBeEqualTo false
        }
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/koenumconstant/snippet/forkofunctionprovider/", fileName)
}
