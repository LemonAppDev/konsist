package com.lemonappdev.konsist.core.declaration.koobject

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoObjectDeclarationForKoInterfaceProviderTest {
    @Test
    fun `object-has-no-interfaces`() {
        // given
        val sut =
            getSnippetFile("object-has-no-interfaces")
                .objects()
                .first()

        // then
        assertSoftly(sut) {
            hasInterfaces() shouldBeEqualTo false
            hasInterfaceWithName(emptyList()) shouldBeEqualTo false
            hasInterfaceWithName(emptySet()) shouldBeEqualTo false
            hasInterfacesWithAllNames(emptyList()) shouldBeEqualTo false
            hasInterfacesWithAllNames(emptySet()) shouldBeEqualTo false
            hasInterfaceWithName("SampleInterface") shouldBeEqualTo false
            hasInterfaceWithName(listOf("SampleInterface")) shouldBeEqualTo false
            hasInterfaceWithName(setOf("SampleInterface")) shouldBeEqualTo false
            hasInterfacesWithAllNames("SampleInterface1", "SampleInterface2") shouldBeEqualTo false
            hasInterfacesWithAllNames(listOf("SampleInterface1", "SampleInterface2")) shouldBeEqualTo false
            hasInterfacesWithAllNames(setOf("SampleInterface1", "SampleInterface2")) shouldBeEqualTo false
            hasInterface { it.name == "SampleInterface" } shouldBeEqualTo false
            hasAllInterfaces { it.hasNameStartingWith("Sample") } shouldBeEqualTo true
        }
    }

    @Test
    fun `object-has-two-interfaces`() {
        // given
        val sut =
            getSnippetFile("object-has-two-interfaces")
                .objects()
                .first()

        // then
        assertSoftly(sut) {
            hasInterfaces() shouldBeEqualTo true
            hasInterfaceWithName(emptyList()) shouldBeEqualTo true
            hasInterfaceWithName(emptySet()) shouldBeEqualTo true
            hasInterfacesWithAllNames(emptyList()) shouldBeEqualTo true
            hasInterfacesWithAllNames(emptySet()) shouldBeEqualTo true
            hasInterfaceWithName("SampleInterface1") shouldBeEqualTo true
            hasInterfaceWithName("SampleInterface1", "OtherInterface") shouldBeEqualTo true
            hasInterfaceWithName(listOf("SampleInterface1")) shouldBeEqualTo true
            hasInterfaceWithName(listOf("SampleInterface1", "OtherInterface")) shouldBeEqualTo true
            hasInterfaceWithName(setOf("SampleInterface1")) shouldBeEqualTo true
            hasInterfaceWithName(setOf("SampleInterface1", "OtherInterface")) shouldBeEqualTo true
            hasInterfacesWithAllNames("SampleInterface1") shouldBeEqualTo true
            hasInterfacesWithAllNames("SampleInterface1", "SampleInterface2") shouldBeEqualTo true
            hasInterfacesWithAllNames("SampleInterface1", "OtherInterface") shouldBeEqualTo false
            hasInterfacesWithAllNames(listOf("SampleInterface1")) shouldBeEqualTo true
            hasInterfacesWithAllNames(listOf("SampleInterface1", "SampleInterface2")) shouldBeEqualTo true
            hasInterfacesWithAllNames(listOf("SampleInterface1", "OtherInterface")) shouldBeEqualTo false
            hasInterfacesWithAllNames(setOf("SampleInterface1")) shouldBeEqualTo true
            hasInterfacesWithAllNames(setOf("SampleInterface1", "SampleInterface2")) shouldBeEqualTo true
            hasInterfacesWithAllNames(setOf("SampleInterface1", "OtherInterface")) shouldBeEqualTo false
            hasInterface { it.name == "SampleInterface1" } shouldBeEqualTo true
            hasInterface { it.hasNameEndingWith("Interface1") } shouldBeEqualTo true
            hasAllInterfaces { it.hasNameStartingWith("Sample") } shouldBeEqualTo true
            hasAllInterfaces { it.hasNameEndingWith("Class1") } shouldBeEqualTo false
        }
    }

    @Test
    fun `object-contains-interfaces includeNested true`() {
        // given
        val sut =
            getSnippetFile("object-contains-interfaces")
                .objects()
                .first()

        // then
        val expected = listOf("SampleInterface", "SampleNestedInterface")

        sut
            .interfaces(includeNested = true)
            .map { it.name }
            .shouldBeEqualTo(expected)
    }

    @Test
    fun `object-contains-interfaces includeNested false`() {
        // given
        val sut =
            getSnippetFile("object-contains-interfaces")
                .objects()
                .first()

        // then
        val expected = listOf("SampleInterface")

        sut
            .interfaces(includeNested = false)
            .map { it.name }
            .shouldBeEqualTo(expected)
    }

    @Test
    fun `count-interfaces`() {
        // given
        val sut =
            getSnippetFile("count-interfaces")
                .objects()
                .first()

        // then
        assertSoftly(sut) {
            numInterfaces(includeNested = true) shouldBeEqualTo 2
            numInterfaces(includeNested = false) shouldBeEqualTo 1
            countInterfaces { it.hasPrivateModifier } shouldBeEqualTo 2
            countInterfaces(includeNested = false) { it.hasPrivateModifier } shouldBeEqualTo 1
            countInterfaces { it.hasInternalModifier } shouldBeEqualTo 0
        }
    }

    private fun getSnippetFile(fileName: String) = getSnippetKoScope("core/declaration/koobject/snippet/forkointerfaceprovider/", fileName)
}
