package com.lemonappdev.konsist.core.declaration.koobject

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import com.lemonappdev.konsist.api.KoModifier.INTERNAL
import com.lemonappdev.konsist.api.KoModifier.PRIVATE
import com.lemonappdev.konsist.api.declaration.KoPropertyDeclaration
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoObjectDeclarationForKoPropertyProviderTest {
    @Test
    fun `object-contains-no-properties`() {
        // given
        val sut = getSnippetFile("object-contains-no-properties")
            .objects()
            .first()

        // then
        sut.properties(includeNested = true, includeLocal = true) shouldBeEqualTo emptyList()
    }

    @Test
    fun `object-contains-nested-and-local-properties includeNested true includeLocal true`() {
        // given
        val sut = getSnippetFile("object-contains-nested-and-local-properties")
            .objects()
            .first()

        // then
        val expected = listOf("sampleLocalProperty", "sampleNestedProperty")

        sut.properties(includeNested = true, includeLocal = true)
            .map { it.name }
            .shouldBeEqualTo(expected)
    }

    @Test
    fun `object-contains-nested-and-local-properties includeNested true includeLocal false`() {
        // given
        val sut = getSnippetFile("object-contains-nested-and-local-properties")
            .objects()
            .first()

        // then
        val expected = listOf("sampleNestedProperty")

        sut.properties(includeNested = true, includeLocal = false)
            .map { it.name }
            .shouldBeEqualTo(expected)
    }

    @Test
    fun `object-contains-nested-and-local-properties includeNested false includeLocal true`() {
        // given
        val sut = getSnippetFile("object-contains-nested-and-local-properties")
            .objects()
            .first()

        // then
        val expected = listOf("sampleLocalProperty")

        sut.properties(includeNested = false, includeLocal = true)
            .map { it.name }
            .shouldBeEqualTo(expected)
    }

    @Test
    fun `object-contains-nested-and-local-properties includeNested false includeLocal false`() {
        // given
        val sut = getSnippetFile("object-contains-nested-and-local-properties")
            .objects()
            .first()

        // then
        val expected = emptyList<KoPropertyDeclaration>()

        sut.properties(includeNested = false, includeLocal = false)
            .map { it.name }
            .shouldBeEqualTo(expected)
    }

    @Test
    fun `count-properties`() {
        // given
        val sut = getSnippetFile("count-properties")
            .objects()
            .first()

        // then
        assertSoftly(sut) {
            numProperties(includeNested = true, includeLocal = true) shouldBeEqualTo 3
            numProperties(includeNested = true, includeLocal = false) shouldBeEqualTo 2
            numProperties(includeNested = false, includeLocal = true) shouldBeEqualTo 2
            numProperties(includeNested = false, includeLocal = false) shouldBeEqualTo 1
            countProperties(includeNested = false, includeLocal = false) { it.hasValModifier } shouldBeEqualTo 1
            countProperties { it.hasValModifier } shouldBeEqualTo 3
            countProperties { it.name == "sampleProperty" && it.hasVarModifier } shouldBeEqualTo 0
        }
    }

    @Test
    fun `contains-properties-with-specified-conditions`() {
        // given
        val sut = getSnippetFile("contains-properties-with-specified-conditions")
            .objects()
            .first()

        // then
        assertSoftly(sut) {
            containsProperty { it.name == "sampleProperty" && it.hasInternalModifier } shouldBeEqualTo true
            containsProperty { it.name == "sampleProperty" && it.hasPublicModifier } shouldBeEqualTo false
            containsProperty { it.name == "sampleProperty" && it.hasModifiersHHH(INTERNAL, PRIVATE) } shouldBeEqualTo false
            containsProperty(
                includeNested = false,
                includeLocal = true,
            ) { it.name == "sampleLocalProperty" } shouldBeEqualTo true
            containsProperty(
                includeNested = false,
                includeLocal = false,
            ) { it.name == "sampleLocalProperty" } shouldBeEqualTo false
            containsProperty(
                includeNested = false,
                includeLocal = true,
            ) { it.name == "sampleOtherProperty" } shouldBeEqualTo false
            containsProperty(
                includeNested = true,
                includeLocal = false,
            ) { it.name == "sampleNestedProperty" && it.hasInternalModifier } shouldBeEqualTo true
            containsProperty(
                includeNested = false,
                includeLocal = false,
            ) { it.name == "sampleNestedProperty" && it.hasInternalModifier } shouldBeEqualTo false
            containsProperty(
                includeNested = true,
                includeLocal = false,
            ) { it.name == "sampleNestedProperty" && it.hasOpenModifier } shouldBeEqualTo false
        }
    }

    @Test
    fun `contains-properties-with-specified-regex`() {
        // given
        val regex1 = Regex("[a-zA-Z]+")
        val regex2 = Regex("[0-9]+")
        val sut = getSnippetFile("contains-properties-with-specified-regex")
            .objects()
            .first()

        // then
        assertSoftly(sut) {
            containsProperty(
                includeNested = false,
                includeLocal = false,
            ) { it.name.matches(regex1) } shouldBeEqualTo true
            containsProperty(
                includeNested = false,
                includeLocal = true,
            ) { it.name.matches(regex1) } shouldBeEqualTo true
            containsProperty(
                includeNested = true,
                includeLocal = false,
            ) { it.name.matches(regex1) } shouldBeEqualTo true
            containsProperty(
                includeNested = false,
                includeLocal = false,
            ) { it.name.matches(regex2) } shouldBeEqualTo false
            containsProperty(
                includeNested = false,
                includeLocal = true,
            ) { it.name.matches(regex2) } shouldBeEqualTo false
            containsProperty(
                includeNested = true,
                includeLocal = false,
            ) { it.name.matches(regex2) } shouldBeEqualTo false
        }
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/koobject/snippet/forkopropertyprovider/", fileName)
}
