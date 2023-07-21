package com.lemonappdev.konsist.core.declaration.koclassdeclaration

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import com.lemonappdev.konsist.api.declaration.KoClassDeclaration
import com.lemonappdev.konsist.api.provider.KoDeclarationProvider
import com.lemonappdev.konsist.api.provider.KoNameProvider
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldNotBeEqualTo
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.MethodSource
import org.junit.jupiter.params.provider.ValueSource

class KoClassDeclarationForKoFunctionsProviderTest {
    @Test
    fun `class-contains-no-functions`() {
        // given
        val sut = getSnippetFile("class-contains-no-functions")
            .classes()
            .first()

        // then
        sut.functions(includeNested = true, includeLocal = true).toList() shouldBeEqualTo emptyList()
    }

    @ParameterizedTest
    @MethodSource("provideValues")
    fun `class-contains-functions includeNested true includeLocal true`(
        includeNested: Boolean,
        includeLocal: Boolean
    ) {
        // given
        val sut = getSnippetFile("class-contains-functions")
            .classes()
            .first()

        // then
        val expected = listOf("sampleFunction")

        sut.functions(includeNested = includeNested, includeLocal = includeLocal)
            .toList()
            .map { it.name }
            .shouldBeEqualTo(expected)
    }

    @Test
    fun `class-contains-nested-and-local-functions includeNested true includeLocal true`() {
        // given
        val sut = getSnippetFile("class-contains-nested-and-local-functions")
            .classes()
            .first()

        // then
        val expected = listOf("sampleFunction", "sampleLocalFunction", "sampleNestedFunction")

        sut.functions(includeNested = true, includeLocal = true)
            .toList()
            .map { it.name }
            .shouldBeEqualTo(expected)
    }

    @Test
    fun `class-contains-nested-and-local-functions includeNested true includeLocal false`() {
        // given
        val sut = getSnippetFile("class-contains-nested-and-local-functions")
            .classes()
            .first()

        // then
        val expected = listOf("sampleFunction", "sampleNestedFunction")

        sut.functions(includeNested = true, includeLocal = false)
            .toList()
            .map { it.name }
            .shouldBeEqualTo(expected)
    }

    @Test
    fun `class-contains-nested-and-local-functions includeNested false includeLocal true`() {
        // given
        val sut = getSnippetFile("class-contains-nested-and-local-functions")
            .classes()
            .first()

        // then
        val expected = listOf("sampleFunction", "sampleLocalFunction")

        sut.functions(includeNested = false, includeLocal = true)
            .toList()
            .map { it.name }
            .shouldBeEqualTo(expected)
    }

    @Test
    fun `class-contains-nested-and-local-functions includeNested false includeLocal false`() {
        // given
        val sut = getSnippetFile("class-contains-nested-and-local-functions")
            .classes()
            .first()

        // then
        val expected = listOf("sampleFunction")

        sut.functions(includeNested = false, includeLocal = false)
            .toList()
            .map { it.name }
            .shouldBeEqualTo(expected)
    }

    @Test
    fun `contains-functions`() {
        // given
        val sut = getSnippetFile("contains-functions")
            .classes()
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
        }
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/koclassdeclaration/snippet/forkofunctionprovider/", fileName)

    companion object {
        @Suppress("unused")
        @JvmStatic
        fun provideValues() = listOf(
            arguments(true, true),
            arguments(true, false),
            arguments(false, true),
            arguments(false, false),
        )
    }
}
