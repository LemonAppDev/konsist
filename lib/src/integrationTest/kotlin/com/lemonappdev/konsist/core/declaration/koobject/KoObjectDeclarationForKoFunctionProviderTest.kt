package com.lemonappdev.konsist.core.declaration.koobject

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import com.lemonappdev.konsist.api.KoModifier.FINAL
import com.lemonappdev.konsist.api.KoModifier.INTERNAL
import com.lemonappdev.konsist.api.KoModifier.PRIVATE
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoObjectDeclarationForKoFunctionProviderTest {
    @Test
    fun `object-contains-no-functions`() {
        // given
        val sut = getSnippetFile("object-contains-no-functions")
            .objects()
            .first()

        // then
        sut.functions(includeNested = true, includeLocal = true) shouldBeEqualTo emptyList()
    }

    @Test
    fun `object-contains-nested-and-local-functions includeNested true includeLocal true`() {
        // given
        val sut = getSnippetFile("object-contains-nested-and-local-functions")
            .objects()
            .first()

        // then
        val expected = listOf("sampleFunction", "sampleLocalFunction", "sampleNestedFunction")

        sut.functions(includeNested = true, includeLocal = true)
            .map { it.name }
            .shouldBeEqualTo(expected)
    }

    @Test
    fun `object-contains-nested-and-local-functions includeNested true includeLocal false`() {
        // given
        val sut = getSnippetFile("object-contains-nested-and-local-functions")
            .objects()
            .first()

        // then
        val expected = listOf("sampleFunction", "sampleNestedFunction")

        sut.functions(includeNested = true, includeLocal = false)
            .map { it.name }
            .shouldBeEqualTo(expected)
    }

    @Test
    fun `object-contains-nested-and-local-functions includeNested false includeLocal true`() {
        // given
        val sut = getSnippetFile("object-contains-nested-and-local-functions")
            .objects()
            .first()

        // then
        val expected = listOf("sampleFunction", "sampleLocalFunction")

        sut.functions(includeNested = false, includeLocal = true)
            .map { it.name }
            .shouldBeEqualTo(expected)
    }

    @Test
    fun `object-contains-nested-and-local-functions includeNested false includeLocal false`() {
        // given
        val sut = getSnippetFile("object-contains-nested-and-local-functions")
            .objects()
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
        val sut = getSnippetFile("count-functions")
            .objects()
            .first()

        // then
        assertSoftly(sut) {
            numFunctions(includeNested = true, includeLocal = true) shouldBeEqualTo 3
            numFunctions(includeNested = true, includeLocal = false) shouldBeEqualTo 2
            numFunctions(includeNested = false, includeLocal = true) shouldBeEqualTo 2
            numFunctions(includeNested = false, includeLocal = false) shouldBeEqualTo 1
            countFunctions { it.hasPrivateModifier } shouldBeEqualTo 1
            countFunctions(includeNested = true, includeLocal = true) { it.hasPrivateModifier } shouldBeEqualTo 2
            countFunctions { it.name == "sampleFunction" && it.hasSuspendModifier } shouldBeEqualTo 0
        }
    }

    @Test
    fun `contains-functions-with-specified-name-and-modifiers`() {
        // given
        val sut = getSnippetFile("contains-functions-with-specified-name-and-modifiers")
            .objects()
            .first()

        // then
        assertSoftly(sut) {
            containsFunction { it.name == "sampleFunction" && it.hasInternalModifier } shouldBeEqualTo true
            containsFunction { it.name == "sampleFunction" && it.hasModifiers(INTERNAL, FINAL) } shouldBeEqualTo true
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
        val sut = getSnippetFile("contains-functions-with-specified-regex")
            .objects()
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

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/koobject/snippet/forkofunctionprovider/", fileName)
}
