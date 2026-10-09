package com.lemonappdev.konsist.core.declaration.kointerface

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoInterfaceDeclarationForKoFunctionProviderTest {
    @Test
    fun `interface-has-no-functions`() {
        // given
        val sut =
            getSnippetFile("interface-has-no-functions")
                .interfaces()
                .first()

        // then
        assertSoftly(sut) {
            functions() shouldBeEqualTo emptyList()
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
            hasAllFunctions { it.hasNameStartingWith("fixture") } shouldBeEqualTo true
        }
    }

    @Test
    fun `interface-has-two-functions`() {
        // given
        val sut =
            getSnippetFile("interface-has-two-functions")
                .interfaces()
                .first()

        // then
        assertSoftly(sut) {
            hasFunctions() shouldBeEqualTo true
            hasFunctionWithName(emptyList()) shouldBeEqualTo true
            hasFunctionWithName(emptySet()) shouldBeEqualTo true
            hasFunctionsWithAllNames(emptyList()) shouldBeEqualTo true
            hasFunctionsWithAllNames(emptySet()) shouldBeEqualTo true
            hasFunctionWithName("fixtureFunction1") shouldBeEqualTo true
            hasFunctionWithName("fixtureFunction1", "otherFunction") shouldBeEqualTo true
            hasFunctionWithName(listOf("fixtureFunction1")) shouldBeEqualTo true
            hasFunctionWithName(listOf("fixtureFunction1", "otherFunction")) shouldBeEqualTo true
            hasFunctionWithName(setOf("fixtureFunction1")) shouldBeEqualTo true
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
            hasFunction { it.hasNameEndingWith("Function1") } shouldBeEqualTo true
            hasAllFunctions { it.hasNameStartingWith("fixture") } shouldBeEqualTo true
            hasAllFunctions { it.hasNameEndingWith("Class1") } shouldBeEqualTo false
        }
    }

    @Test
    fun `interface-contains-nested-and-local-functions includeNested true includeLocal true`() {
        // given
        val sut =
            getSnippetFile("interface-contains-nested-and-local-functions")
                .interfaces()
                .first()

        // then
        val expected = listOf("fixtureFunction", "fixtureLocalFunction", "fixtureNestedFunction")

        sut
            .functions(includeNested = true, includeLocal = true)
            .map { it.name }
            .shouldBeEqualTo(expected)
    }

    @Test
    fun `interface-contains-nested-and-local-functions includeNested true includeLocal false`() {
        // given
        val sut =
            getSnippetFile("interface-contains-nested-and-local-functions")
                .interfaces()
                .first()

        // then
        val expected = listOf("fixtureFunction", "fixtureNestedFunction")

        sut
            .functions(includeNested = true, includeLocal = false)
            .map { it.name }
            .shouldBeEqualTo(expected)
    }

    @Test
    fun `interface-contains-nested-and-local-functions includeNested false includeLocal true`() {
        // given
        val sut =
            getSnippetFile("interface-contains-nested-and-local-functions")
                .interfaces()
                .first()

        // then
        val expected = listOf("fixtureFunction", "fixtureLocalFunction")

        sut
            .functions(includeNested = false, includeLocal = true)
            .map { it.name }
            .shouldBeEqualTo(expected)
    }

    @Test
    fun `interface-contains-nested-and-local-functions includeNested false includeLocal false`() {
        // given
        val sut =
            getSnippetFile("interface-contains-nested-and-local-functions")
                .interfaces()
                .first()

        // then
        val expected = listOf("fixtureFunction")

        sut
            .functions(includeNested = false, includeLocal = false)
            .map { it.name }
            .shouldBeEqualTo(expected)
    }

    @Test
    fun `count-functions`() {
        // given
        val sut =
            getSnippetFile("count-functions")
                .interfaces()
                .first()

        // then
        assertSoftly(sut) {
            numFunctions(includeNested = true, includeLocal = true) shouldBeEqualTo 3
            numFunctions(includeNested = true, includeLocal = false) shouldBeEqualTo 2
            numFunctions(includeNested = false, includeLocal = true) shouldBeEqualTo 2
            numFunctions(includeNested = false, includeLocal = false) shouldBeEqualTo 1
            countFunctions(includeNested = false, includeLocal = false) { it.hasPrivateModifier } shouldBeEqualTo 1
            countFunctions { it.hasPrivateModifier } shouldBeEqualTo 2
            countFunctions { it.name == "fixtureFunction" && it.hasSuspendModifier } shouldBeEqualTo 0
        }
    }

    @Test
    fun `interface-has-no-functions-ignore-case`() {
        // given
        val sut =
            getSnippetFile("interface-has-no-functions-ignore-case")
                .interfaces()
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
    fun `interface-has-functions-ignore-case`() {
        // given
        val sut =
            getSnippetFile("interface-has-functions-ignore-case")
                .interfaces()
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
        getSnippetKoScope("core/declaration/kointerface/snippet/forkofunctionprovider/", fileName)
}
