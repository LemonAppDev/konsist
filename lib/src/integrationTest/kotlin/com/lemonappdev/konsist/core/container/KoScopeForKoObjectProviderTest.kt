package com.lemonappdev.konsist.core.container

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoScopeForKoObjectProviderTest {
    @Test
    fun `scope-contains-no-objects`() {
        // given
        val sut = getSnippetFile("scope-contains-no-objects")

        // then
        sut.objects(includeNested = true).toList() shouldBeEqualTo emptyList()
    }

    @Test
    fun `scope-contains-objects includeNested true`() {
        // given
        val sut = getSnippetFile("scope-contains-objects")

        // then
        val expected = listOf("SampleObject", "SampleNestedObject")

        sut.objects(includeNested = true)
            .toList()
            .map { it.name }
            .shouldBeEqualTo(expected)
    }

    @Test
    fun `scope-contains-objects includeNested false`() {
        // given
        val sut = getSnippetFile("scope-contains-objects")

        // then
        val expected = listOf("SampleObject")

        sut.objects(includeNested = false)
            .toList()
            .map { it.name }
            .shouldBeEqualTo(expected)
    }

    @Test
    fun `contains-objects`() {
        // given
        val sut = getSnippetFile("contains-objects")

        // then
        assertSoftly(sut) {
            numObjects(includeNested = false) shouldBeEqualTo 1
            numObjects(includeNested = true) shouldBeEqualTo 2
            containsObject("SampleObject", includeNested = false) shouldBeEqualTo true
            containsObject("SampleNestedObject", includeNested = false) shouldBeEqualTo false
            containsObject("SampleNestedObject", includeNested = true) shouldBeEqualTo true
            containsObject("NonExisting") shouldBeEqualTo false
        }
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/container/snippet/forkoobjectprovider/", fileName)
}
