package com.lemonappdev.konsist.core.declaration.kofile

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import com.lemonappdev.konsist.api.KoModifier
import com.lemonappdev.konsist.api.KoModifier.INTERNAL
import com.lemonappdev.konsist.api.KoModifier.OPEN
import com.lemonappdev.konsist.api.KoModifier.PRIVATE
import com.lemonappdev.konsist.api.KoModifier.SUSPEND
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoFileDeclarationForKoFunctionProviderTest {
    @Test
    fun `file-contains-no-functions`() {
        // given
        val sut = getSnippetFile("file-contains-no-functions")
            .files
            .first()

        // then
        sut.functions(includeNested = true, includeLocal = true) shouldBeEqualTo emptyList()
    }

    @Test
    fun `file-contains-nested-and-local-functions includeNested true includeLocal true`() {
        // given
        val sut = getSnippetFile("file-contains-nested-and-local-functions")
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
        val sut = getSnippetFile("file-contains-nested-and-local-functions")
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
        val sut = getSnippetFile("file-contains-nested-and-local-functions")
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
        val sut = getSnippetFile("file-contains-nested-and-local-functions")
            .files
            .first()

        // then
        val expected = listOf("sampleFunction")

        sut.functions(includeNested = false, includeLocal = false)
            .map { it.name }
            .shouldBeEqualTo(expected)
    }

    @Test
    fun `contains-functions`() {
        // given
        val sut = getSnippetFile("contains-functions")
            .files
            .first()

        // then
        assertSoftly(sut) {
            numFunctions(includeNested = true, includeLocal = true) shouldBeEqualTo 3
            numFunctions(includeNested = true, includeLocal = false) shouldBeEqualTo 2
            numFunctions(includeNested = false, includeLocal = true) shouldBeEqualTo 2
            numFunctions(includeNested = false, includeLocal = false) shouldBeEqualTo 1
            containsFunction("sampleFunction", includeNested = false, includeLocal = false) shouldBeEqualTo true
            containsFunction("sampleLocalFunction", includeNested = false, includeLocal = true) shouldBeEqualTo true
            containsFunction("sampleLocalFunction", includeNested = false, includeLocal = false) shouldBeEqualTo false
            containsFunction("sampleNestedFunction", includeNested = true, includeLocal = false) shouldBeEqualTo true
            containsFunction("sampleNestedFunction", includeNested = false, includeLocal = false) shouldBeEqualTo false
            containsFunction("NonExisting") shouldBeEqualTo false
            containsFunction(Regex("[a-zA-Z]+"), includeNested = false, includeLocal = false) shouldBeEqualTo true
            containsFunction(Regex("[a-zA-Z]+"), includeNested = false, includeLocal = true) shouldBeEqualTo true
            containsFunction(Regex("[0-9]+"), includeNested = false, includeLocal = false) shouldBeEqualTo false
            containsFunction(Regex("[a-zA-Z]+"), includeNested = true, includeLocal = false) shouldBeEqualTo true
            containsFunction(Regex("[0-9]+"), includeNested = false, includeLocal = false) shouldBeEqualTo false
        }
    }

    @Test
    fun `contains-functions-with-modifiers`() {
        // given
        val sut = getSnippetFile("contains-functions-with-modifiers")
            .files
            .first()

        // then
        assertSoftly(sut) {
            containsFunction(
                "sampleFunction",
                INTERNAL,
                includeNested = false,
                includeLocal = false
            ) shouldBeEqualTo true
            containsFunction(
                "sampleFunction",
                INTERNAL,
                SUSPEND,
                includeNested = false,
                includeLocal = false
            ) shouldBeEqualTo true
            containsFunction(
                "sampleFunction",
                PRIVATE,
                includeNested = false,
                includeLocal = false
            ) shouldBeEqualTo false
            containsFunction(
                "sampleFunction",
                INTERNAL,
                PRIVATE,
                includeNested = false,
                includeLocal = false
            ) shouldBeEqualTo false
            containsFunction(
                "sampleLocalFunction",
                SUSPEND,
                includeNested = false,
                includeLocal = true
            ) shouldBeEqualTo true
            containsFunction(
                "sampleLocalFunction",
                SUSPEND,
                includeNested = false,
                includeLocal = false
            ) shouldBeEqualTo false
            containsFunction(
                "sampleLocalFunction",
                PRIVATE,
                includeNested = false,
                includeLocal = true
            ) shouldBeEqualTo false
            containsFunction(
                "sampleNestedFunction",
                OPEN,
                includeNested = true,
                includeLocal = false
            ) shouldBeEqualTo true
            containsFunction(
                "sampleNestedFunction",
                OPEN,
                includeNested = false,
                includeLocal = false
            ) shouldBeEqualTo false
            containsFunction(
                "sampleNestedFunction",
                PRIVATE,
                includeNested = true,
                includeLocal = false
            ) shouldBeEqualTo false
            containsFunction(Regex("[a-zA-Z]+"), INTERNAL, includeNested = false, includeLocal = false) shouldBeEqualTo true
            containsFunction(Regex("[0-9]+"), INTERNAL, includeNested = false, includeLocal = false) shouldBeEqualTo false
            containsFunction(Regex("[a-zA-Z]+"), PRIVATE, includeNested = false, includeLocal = false) shouldBeEqualTo false
            containsFunction(Regex("[a-zA-Z]+"), INTERNAL, SUSPEND, includeNested = false, includeLocal = false) shouldBeEqualTo true
            containsFunction(Regex("[a-zA-Z]+"), INTERNAL, PRIVATE, includeNested = false, includeLocal = false) shouldBeEqualTo false
            containsFunction(Regex("[a-zA-Z]+"), SUSPEND, includeNested = false, includeLocal = true) shouldBeEqualTo true
            containsFunction(Regex("[0-9]+"), SUSPEND, includeNested = false, includeLocal = true) shouldBeEqualTo false
            containsFunction(Regex("[a-zA-Z]+"), PRIVATE, includeNested = false, includeLocal = true) shouldBeEqualTo false
            containsFunction(Regex("[a-zA-Z]+"), OPEN, includeNested = true, includeLocal = false) shouldBeEqualTo true
            containsFunction(Regex("[0-9]+"), OPEN, includeNested = true, includeLocal = false) shouldBeEqualTo false
            containsFunction(Regex("[a-zA-Z]+"), PRIVATE, includeNested = true, includeLocal = false) shouldBeEqualTo false
        }
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/kofile/snippet/forkofunctionprovider/", fileName)
}
