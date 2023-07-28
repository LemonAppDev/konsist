package com.lemonappdev.konsist.core.declaration.koobjectdeclaration

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
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
        sut.properties(includeNested = true, includeLocal = true).toList() shouldBeEqualTo emptyList()
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
            .toList()
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
            .toList()
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
            .toList()
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
            .toList()
            .map { it.name }
            .shouldBeEqualTo(expected)
    }

    @Test
    fun `contains-properties`() {
        // given
        val sut = getSnippetFile("contains-properties")
            .objects()
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
        getSnippetKoScope("core/declaration/koobjectdeclaration/snippet/forkopropertyprovider/", fileName)
}
