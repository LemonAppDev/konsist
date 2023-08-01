package com.lemonappdev.konsist.core.declaration.kointerfacedeclaration

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoInterfaceDeclarationForKoInterfaceProviderTest {
    @Test
    fun `interface-contains-no-interfaces`() {
        // given
        val sut = getSnippetFile("interface-contains-no-interfaces")
            .interfaces()
            .first()

        // then
        sut.interfaces(includeNested = true) shouldBeEqualTo emptyList()
    }

    @Test
    fun `interface-contains-interfaces includeNested true`() {
        // given
        val sut = getSnippetFile("interface-contains-interfaces")
            .interfaces()
            .first()

        // then
        val expected = listOf("SampleInterface", "SampleNestedInterface")

        sut.interfaces(includeNested = true)
            .map { it.name }
            .shouldBeEqualTo(expected)
    }

    @Test
    fun `interface-contains-interfaces includeNested false`() {
        // given
        val sut = getSnippetFile("interface-contains-interfaces")
            .interfaces()
            .first()

        // then
        val expected = listOf("SampleInterface")

        sut.interfaces(includeNested = false)
            .map { it.name }
            .shouldBeEqualTo(expected)
    }

    @Test
    fun `contains-interfaces`() {
        // given
        val sut = getSnippetFile("contains-interfaces")
            .interfaces()
            .first()

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

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/kointerfacedeclaration/snippet/forkointerfaceprovider/", fileName)
}
