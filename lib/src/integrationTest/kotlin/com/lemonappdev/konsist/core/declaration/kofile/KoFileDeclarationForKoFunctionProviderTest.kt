package com.lemonappdev.konsist.core.declaration.kofile

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import com.lemonappdev.konsist.api.KoModifier.INTERNAL
import com.lemonappdev.konsist.api.KoModifier.PRIVATE
import com.lemonappdev.konsist.api.KoModifier.SUSPEND
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoFileDeclarationForKoFunctionProviderTest {
    @Test
    fun `file-has-no-functions`() {
        // given
        val sut =
            getSnippetFile("file-has-no-functions")
                .files
                .first()

        // then
        assertSoftly(sut) {
            functions() shouldBeEqualTo emptyList()
            hasFunctions() shouldBeEqualTo false
            hasFunctionWithName("sampleFunction") shouldBeEqualTo false
            hasFunctionsWithAllNames("sampleFunction1", "sampleFunction2") shouldBeEqualTo false
            hasFunction { it.name == "sampleFunction" } shouldBeEqualTo false
            hasAllFunctions { it.hasNameStartingWith("sample") } shouldBeEqualTo true
        }
    }

    @Test
    fun `file-has-two-functions`() {
        // given
        val sut =
            getSnippetFile("file-has-two-functions")
                .files
                .first()

        // then
        assertSoftly(sut) {
            hasFunctions() shouldBeEqualTo true
            hasFunctionWithName("sampleFunction1") shouldBeEqualTo true
            hasFunctionWithName("sampleFunction1", "otherFunction") shouldBeEqualTo true
            hasFunctionsWithAllNames("sampleFunction1") shouldBeEqualTo true
            hasFunctionsWithAllNames("sampleFunction1", "sampleFunction2") shouldBeEqualTo true
            hasFunctionsWithAllNames("sampleFunction1", "otherFunction") shouldBeEqualTo false
            hasFunction { it.name == "sampleFunction1" } shouldBeEqualTo true
            hasFunction { it.hasNameEndingWith("Function1") } shouldBeEqualTo true
            hasAllFunctions { it.hasNameStartingWith("sample") } shouldBeEqualTo true
            hasAllFunctions { it.hasNameEndingWith("Class1") } shouldBeEqualTo false
        }
    }

    @Test
    fun `file-contains-nested-and-local-functions includeNested true includeLocal true`() {
        // given
        val sut =
            getSnippetFile("file-contains-nested-and-local-functions")
                .files
                .first()

        // then
        val expected = listOf("sampleFunction", "sampleLocalFunction", "sampleNestedFunction")

        sut.functions(includeNested = true, includeLocal = true)
            .map { it.name }
            .shouldBeEqualTo(expected)
    }

    @Test
    fun `file-contains-nested-and-local-functions includeNested true includeLocal false`() {
        // given
        val sut =
            getSnippetFile("file-contains-nested-and-local-functions")
                .files
                .first()

        // then
        val expected = listOf("sampleFunction", "sampleNestedFunction")

        sut.functions(includeNested = true, includeLocal = false)
            .map { it.name }
            .shouldBeEqualTo(expected)
    }

    @Test
    fun `file-contains-nested-and-local-functions includeNested false includeLocal true`() {
        // given
        val sut =
            getSnippetFile("file-contains-nested-and-local-functions")
                .files
                .first()

        // then
        val expected = listOf("sampleFunction", "sampleLocalFunction")

        sut.functions(includeNested = false, includeLocal = true)
            .map { it.name }
            .shouldBeEqualTo(expected)
    }

    @Test
    fun `file-contains-nested-and-local-functions includeNested false includeLocal false`() {
        // given
        val sut =
            getSnippetFile("file-contains-nested-and-local-functions")
                .files
                .first()

        // then
        val expected = listOf("sampleFunction")

        sut.functions(includeNested = false, includeLocal = false)
            .map { it.name }
            .shouldBeEqualTo(expected)
    }

    @Test
    fun `count-functions`() {
        // given
        val sut =
            getSnippetFile("count-functions")
                .files
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

    @Test
    fun `contains-functions-with-specified-name-and-modifiers`() {
        // given
        val sut =
            getSnippetFile("contains-functions-with-specified-name-and-modifiers")
                .files
                .first()

        // then
        assertSoftly(sut) {
            containsFunction { it.name == "sampleFunction" && it.hasInternalModifier } shouldBeEqualTo true
            containsFunction { it.name == "sampleFunction" && it.hasModifiers(INTERNAL, SUSPEND) } shouldBeEqualTo true
            containsFunction { it.name == "sampleFunction" && it.hasPrivateModifier } shouldBeEqualTo false
            containsFunction { it.name == "sampleFunction" && it.hasModifiers(INTERNAL, PRIVATE) } shouldBeEqualTo false
            containsFunction(
                includeNested = false,
                includeLocal = true,
            ) { it.name == "sampleLocalFunction" && it.hasSuspendModifier } shouldBeEqualTo true
            containsFunction(
                includeNested = false,
                includeLocal = false,
            ) { it.name == "sampleLocalFunction" && it.hasSuspendModifier } shouldBeEqualTo false
            containsFunction(
                includeNested = false,
                includeLocal = true,
            ) { it.name == "sampleLocalFunction" && it.hasPrivateModifier } shouldBeEqualTo false
            containsFunction(
                includeNested = true,
                includeLocal = false,
            ) { it.name == "sampleNestedFunction" && it.hasOpenModifier } shouldBeEqualTo true
            containsFunction(
                includeNested = false,
                includeLocal = false,
            ) { it.name == "sampleNestedFunction" && it.hasOpenModifier } shouldBeEqualTo false
            containsFunction(
                includeNested = true,
                includeLocal = false,
            ) { it.name == "sampleNestedFunction" && it.hasPrivateModifier } shouldBeEqualTo false
        }
    }

    @Test
    fun `contains-functions-with-specified-regex`() {
        // given
        val regex1 = Regex("[a-zA-Z]+")
        val regex2 = Regex("[0-9]+")
        val sut =
            getSnippetFile("contains-functions-with-specified-regex")
                .files
                .first()

        // then
        assertSoftly(sut) {
            containsFunction(
                includeNested = false,
                includeLocal = false,
            ) { it.name.matches(regex1) } shouldBeEqualTo true
            containsFunction(
                includeNested = false,
                includeLocal = true,
            ) { it.name.matches(regex1) } shouldBeEqualTo true
            containsFunction(
                includeNested = false,
                includeLocal = false,
            ) { it.name.matches(regex2) } shouldBeEqualTo false
            containsFunction(
                includeNested = true,
                includeLocal = false,
            ) { it.name.matches(regex1) } shouldBeEqualTo true
            containsFunction(
                includeNested = false,
                includeLocal = false,
            ) { it.name.matches(regex2) } shouldBeEqualTo false
        }
    }

    private fun getSnippetFile(fileName: String) = getSnippetKoScope("core/declaration/kofile/snippet/forkofunctionprovider/", fileName)
}
