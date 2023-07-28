package com.lemonappdev.konsist.core.container.koscope

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoScopeForKoInterfaceProviderTest {
    @Test
    fun `scope-contains-no-interfaces`() {
        // given
        val sut = getSnippetFile("scope-contains-no-interfaces")

        // then
        sut.interfaces(includeNested = true).toList() shouldBeEqualTo emptyList()
    }

    @Test
    fun `scope-contains-interfaces includeNested true`() {
        // given
        val sut = getSnippetFile("scope-contains-interfaces")

        // then
        val expected = listOf("SampleInterface", "SampleNestedInterface")

        sut.interfaces(includeNested = true)
            .toList()
            .map { it.name }
            .shouldBeEqualTo(expected)
    }

    @Test
    fun `scope-contains-interfaces includeNested false`() {
        // given
        val sut = getSnippetFile("scope-contains-interfaces")

        // then
        val expected = listOf("SampleInterface")

        sut.interfaces(includeNested = false)
            .toList()
            .map { it.name }
            .shouldBeEqualTo(expected)
    }

    @Test
    fun `contains-interfaces`() {
        // given
        val sut = getSnippetFile("contains-interfaces")

        // then
        assertSoftly(sut) {
            numInterfaces(includeNested = false) shouldBeEqualTo 1
            numInterfaces(includeNested = true) shouldBeEqualTo 2
            containsInterface("SampleInterface", includeNested = false) shouldBeEqualTo true
            containsInterface("SampleNestedInterface", includeNested = false) shouldBeEqualTo false
            containsInterface("SampleNestedInterface", includeNested = true) shouldBeEqualTo true
            containsInterface("NonExisting") shouldBeEqualTo false
        }
    }

    private fun getSnippetFile(fileName: String) = getSnippetKoScope("core/container/koscope/snippet/forkointerfaceprovider/", fileName)
}
