package com.lemonappdev.konsist.core.declaration.kointerfacedeclaration

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoInterfaceDeclarationForKoObjectProviderTest {
    @Test
    fun `interface-contains-no-objects`() {
        // given
        val sut = getSnippetFile("interface-contains-no-objects")
            .interfaces()
            .first()

        // then
        sut.objects(includeNested = true) shouldBeEqualTo emptyList()
    }

    @Test
    fun `interface-contains-objects includeNested true`() {
        // given
        val sut = getSnippetFile("interface-contains-objects")
            .interfaces()
            .first()

        // then
        val expected = listOf("SampleObject", "SampleNestedObject")

        sut.objects(includeNested = true)
            .map { it.name }
            .shouldBeEqualTo(expected)
    }

    @Test
    fun `interface-contains-objects includeNested false`() {
        // given
        val sut = getSnippetFile("interface-contains-objects")
            .interfaces()
            .first()

        // then
        val expected = listOf("SampleObject")

        sut.objects(includeNested = false)
            .map { it.name }
            .shouldBeEqualTo(expected)
    }

    @Test
    fun `contains-objects`() {
        // given
        val sut = getSnippetFile("contains-objects")
            .interfaces()
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
        getSnippetKoScope("core/declaration/kointerfacedeclaration/snippet/forkoobjectprovider/", fileName)
}
