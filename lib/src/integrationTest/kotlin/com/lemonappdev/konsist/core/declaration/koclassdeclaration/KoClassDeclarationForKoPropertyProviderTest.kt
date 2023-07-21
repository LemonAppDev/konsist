package com.lemonappdev.konsist.core.declaration.koclassdeclaration

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import com.lemonappdev.konsist.api.declaration.KoClassDeclaration
import com.lemonappdev.konsist.api.declaration.KoPropertyDeclaration
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

class KoClassDeclarationForKoPropertyProviderTest {
    @Test
    fun `class-contains-no-properties`() {
        // given
        val sut = getSnippetFile("class-contains-no-properties")
            .classes()
            .first()

        // then
        sut.properties(includeNested = true, includeLocal = true).toList() shouldBeEqualTo emptyList()
    }

    @ParameterizedTest
    @MethodSource("provideValues")
    fun `class-contains-properties includeNested true includeLocal true`(
        includeNested: Boolean,
        includeLocal: Boolean
    ) {
        // given
        val sut = getSnippetFile("class-contains-properties")
            .classes()
            .first()

        // then
        val expected = listOf("sampleProperty")

        sut.properties(includeNested = includeNested, includeLocal = includeLocal)
            .toList()
            .map { it.name }
            .shouldBeEqualTo(expected)
    }

    @Test
    fun `class-contains-nested-and-local-properties includeNested true includeLocal true`() {
        // given
        val sut = getSnippetFile("class-contains-nested-and-local-properties")
            .classes()
            .first()

        // then
        val expected = listOf("sampleLocalProperty", "sampleNestedProperty")

        sut.properties(includeNested = true, includeLocal = true)
            .toList()
            .map { it.name }
            .shouldBeEqualTo(expected)
    }

    @Test
    fun `class-contains-nested-and-local-properties includeNested true includeLocal false`() {
        // given
        val sut = getSnippetFile("class-contains-nested-and-local-properties")
            .classes()
            .first()

        // then
        val expected = listOf("sampleNestedProperty")

        sut.properties(includeNested = true, includeLocal = false)
            .toList()
            .map { it.name }
            .shouldBeEqualTo(expected)
    }

    @Test
    fun `class-contains-nested-and-local-properties includeNested false includeLocal true`() {
        // given
        val sut = getSnippetFile("class-contains-nested-and-local-properties")
            .classes()
            .first()

        // then
        val expected = listOf("sampleLocalProperty")

        sut.properties(includeNested = false, includeLocal = true)
            .toList()
            .map { it.name }
            .shouldBeEqualTo(expected)
    }

    @Test
    fun `class-contains-nested-and-local-properties includeNested false includeLocal false`() {
        // given
        val sut = getSnippetFile("class-contains-nested-and-local-properties")
            .classes()
            .first()

        // then
        val expected = emptyList<KoPropertyDeclaration>()

        sut.properties(includeNested = false, includeLocal = false)
            .toList()
            .map { it.name }
            .shouldBeEqualTo(expected)
    }

    @Test
    fun `contains-properties`() {
        // given
        val sut = getSnippetFile("contains-properties")
            .classes()
            .first()

        // then
        assertSoftly(sut) {
            numProperties(includeNested = true, includeLocal = true) shouldBeEqualTo 3
            numProperties(includeNested = true, includeLocal = false) shouldBeEqualTo 2
            numProperties(includeNested = false, includeLocal = true) shouldBeEqualTo 2
            numProperties(includeNested = false, includeLocal = false) shouldBeEqualTo 1
            containsProperty("sampleProperty", includeNested = false, includeLocal = false) shouldBeEqualTo true
            containsProperty("sampleLocalProperty", includeNested = false, includeLocal = true) shouldBeEqualTo true
            containsProperty("sampleLocalProperty", includeNested = false, includeLocal = false) shouldBeEqualTo false
            containsProperty("sampleNestedProperty", includeNested = true, includeLocal = false) shouldBeEqualTo true
            containsProperty("sampleNestedProperty", includeNested = false, includeLocal = false) shouldBeEqualTo false
            containsProperty("NonExisting") shouldBeEqualTo false
        }
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/koclassdeclaration/snippet/forkopropertyprovider/", fileName)

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
