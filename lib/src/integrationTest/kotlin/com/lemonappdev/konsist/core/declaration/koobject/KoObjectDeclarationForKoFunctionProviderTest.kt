package com.lemonappdev.konsist.core.declaration.koobject

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoObjectDeclarationForKoFunctionProviderTest {
    @Test
    fun `object-has-no-functions`() {
        // given
        val sut =
            getSnippetFile("object-has-no-functions")
                .objects()
                .first()

        // then
        assertSoftly(sut) {
            functions() shouldBeEqualTo emptyList()
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
            hasAllFunctions { it.hasNameStartingWith("sample") } shouldBeEqualTo true
        }
    }

    @Test
    fun `object-has-two-functions`() {
        // given
        val sut =
            getSnippetFile("object-has-two-functions")
                .objects()
                .first()

        // then
        assertSoftly(sut) {
            hasFunctions() shouldBeEqualTo true
            hasFunctionWithName(emptyList()) shouldBeEqualTo true
            hasFunctionWithName(emptySet()) shouldBeEqualTo true
            hasFunctionsWithAllNames(emptyList()) shouldBeEqualTo true
            hasFunctionsWithAllNames(emptySet()) shouldBeEqualTo true
            hasFunctionWithName("sampleFunction1") shouldBeEqualTo true
            hasFunctionWithName("sampleFunction1", "otherFunction") shouldBeEqualTo true
            hasFunctionWithName(listOf("sampleFunction1")) shouldBeEqualTo true
            hasFunctionWithName(listOf("sampleFunction1", "otherFunction")) shouldBeEqualTo true
            hasFunctionWithName(setOf("sampleFunction1")) shouldBeEqualTo true
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
            hasFunction { it.hasNameEndingWith("Function1") } shouldBeEqualTo true
            hasAllFunctions { it.hasNameStartingWith("sample") } shouldBeEqualTo true
            hasAllFunctions { it.hasNameEndingWith("Class1") } shouldBeEqualTo false
        }
    }

    @Test
    fun `object-contains-nested-and-local-functions includeNested true includeLocal true`() {
        // given
        val sut =
            getSnippetFile("object-contains-nested-and-local-functions")
                .objects()
                .first()

        // then
        val expected = listOf("sampleFunction", "sampleLocalFunction", "sampleNestedFunction")

        sut
            .functions(includeNested = true, includeLocal = true)
            .map { it.name }
            .shouldBeEqualTo(expected)
    }

    @Test
    fun `object-contains-nested-and-local-functions includeNested true includeLocal false`() {
        // given
        val sut =
            getSnippetFile("object-contains-nested-and-local-functions")
                .objects()
                .first()

        // then
        val expected = listOf("sampleFunction", "sampleNestedFunction")

        sut
            .functions(includeNested = true, includeLocal = false)
            .map { it.name }
            .shouldBeEqualTo(expected)
    }

    @Test
    fun `object-contains-nested-and-local-functions includeNested false includeLocal true`() {
        // given
        val sut =
            getSnippetFile("object-contains-nested-and-local-functions")
                .objects()
                .first()

        // then
        val expected = listOf("sampleFunction", "sampleLocalFunction")

        sut
            .functions(includeNested = false, includeLocal = true)
            .map { it.name }
            .shouldBeEqualTo(expected)
    }

    @Test
    fun `object-contains-nested-and-local-functions includeNested false includeLocal false`() {
        // given
        val sut =
            getSnippetFile("object-contains-nested-and-local-functions")
                .objects()
                .first()

        // then
        val expected = listOf("sampleFunction")

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
                .objects()
                .first()

        // then
        assertSoftly(sut) {
            numFunctions(includeNested = true, includeLocal = true) shouldBeEqualTo 3
            numFunctions(includeNested = true, includeLocal = false) shouldBeEqualTo 2
            numFunctions(includeNested = false, includeLocal = true) shouldBeEqualTo 2
            numFunctions(includeNested = false, includeLocal = false) shouldBeEqualTo 1
            countFunctions(includeNested = false, includeLocal = false) { it.hasPrivateModifier } shouldBeEqualTo 1
            countFunctions { it.hasPrivateModifier } shouldBeEqualTo 2
            countFunctions { it.name == "sampleFunction" && it.hasSuspendModifier } shouldBeEqualTo 0
        }
    }

    private fun getSnippetFile(fileName: String) = getSnippetKoScope("core/declaration/koobject/snippet/forkofunctionprovider/", fileName)
}
