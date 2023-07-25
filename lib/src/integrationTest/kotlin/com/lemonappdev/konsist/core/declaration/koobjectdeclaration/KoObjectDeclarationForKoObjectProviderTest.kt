package com.lemonappdev.konsist.core.declaration.koobjectdeclaration

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoObjectDeclarationForKoObjectProviderTest {
    @Test
    fun `object-contains-no-objects`() {
        // given
        val sut = getSnippetFile("object-contains-no-objects")
            .objects()
            .first()

        // then
        sut.objects(includeNested = true).toList() shouldBeEqualTo emptyList()
    }

    @Test
    fun `object-contains-objects includeNested true`() {
        // given
        val sut = getSnippetFile("object-contains-objects")
            .objects()
            .first()

        // then
        val expected = listOf("SampleObject", "SampleNestedObject")

        sut.objects(includeNested = true)
            .toList()
            .map { it.name }
            .shouldBeEqualTo(expected)
    }

    @Test
    fun `object-contains-objects includeNested false`() {
        // given
        val sut = getSnippetFile("object-contains-objects")
            .objects()
            .first()

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
            .objects()
            .first()

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
        getSnippetKoScope("core/declaration/koobjectdeclaration/snippet/forkoobjectprovider/", fileName)
}
