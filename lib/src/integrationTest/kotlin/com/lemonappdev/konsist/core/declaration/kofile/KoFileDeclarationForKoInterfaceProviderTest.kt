package com.lemonappdev.konsist.core.declaration.kofile

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoFileDeclarationForKoInterfaceProviderTest {
    @Test
    fun `file-has-no-interfaces`() {
        // given
        val sut =
            getSnippetFile("file-has-no-interfaces")
                .files
                .first()

        // then
        assertSoftly(sut) {
            hasInterfaces() shouldBeEqualTo false
            hasInterfaceWithName(emptyList()) shouldBeEqualTo false
            hasInterfaceWithName(emptySet()) shouldBeEqualTo false
            hasInterfacesWithAllNames(emptyList()) shouldBeEqualTo false
            hasInterfacesWithAllNames(emptySet()) shouldBeEqualTo false
            hasInterfaceWithName("FixtureInterface") shouldBeEqualTo false
            hasInterfaceWithName(listOf("FixtureInterface")) shouldBeEqualTo false
            hasInterfaceWithName(setOf("FixtureInterface")) shouldBeEqualTo false
            hasInterfacesWithAllNames("FixtureInterface1", "FixtureInterface2") shouldBeEqualTo false
            hasInterfacesWithAllNames(listOf("FixtureInterface1", "FixtureInterface2")) shouldBeEqualTo false
            hasInterfacesWithAllNames(setOf("FixtureInterface1", "FixtureInterface2")) shouldBeEqualTo false
            hasInterface { it.name == "FixtureInterface" } shouldBeEqualTo false
            hasAllInterfaces { it.hasNameStartingWith("Fixture") } shouldBeEqualTo true
        }
    }

    @Test
    fun `file-has-two-interfaces`() {
        // given
        val sut =
            getSnippetFile("file-has-two-interfaces")
                .files
                .first()

        // then
        assertSoftly(sut) {
            hasInterfaces() shouldBeEqualTo true
            hasInterfaceWithName(emptyList()) shouldBeEqualTo true
            hasInterfaceWithName(emptySet()) shouldBeEqualTo true
            hasInterfacesWithAllNames(emptyList()) shouldBeEqualTo true
            hasInterfacesWithAllNames(emptySet()) shouldBeEqualTo true
            hasInterfaceWithName("FixtureInterface1") shouldBeEqualTo true
            hasInterfaceWithName("FixtureInterface1", "OtherInterface") shouldBeEqualTo true
            hasInterfaceWithName(listOf("FixtureInterface1")) shouldBeEqualTo true
            hasInterfaceWithName(listOf("FixtureInterface1", "OtherInterface")) shouldBeEqualTo true
            hasInterfaceWithName(setOf("FixtureInterface1")) shouldBeEqualTo true
            hasInterfaceWithName(setOf("FixtureInterface1", "OtherInterface")) shouldBeEqualTo true
            hasInterfacesWithAllNames("FixtureInterface1") shouldBeEqualTo true
            hasInterfacesWithAllNames("FixtureInterface1", "FixtureInterface2") shouldBeEqualTo true
            hasInterfacesWithAllNames("FixtureInterface1", "OtherInterface") shouldBeEqualTo false
            hasInterfacesWithAllNames(listOf("FixtureInterface1")) shouldBeEqualTo true
            hasInterfacesWithAllNames(listOf("FixtureInterface1", "FixtureInterface2")) shouldBeEqualTo true
            hasInterfacesWithAllNames(listOf("FixtureInterface1", "OtherInterface")) shouldBeEqualTo false
            hasInterfacesWithAllNames(setOf("FixtureInterface1")) shouldBeEqualTo true
            hasInterfacesWithAllNames(setOf("FixtureInterface1", "FixtureInterface2")) shouldBeEqualTo true
            hasInterfacesWithAllNames(setOf("FixtureInterface1", "OtherInterface")) shouldBeEqualTo false
            hasInterface { it.name == "FixtureInterface1" } shouldBeEqualTo true
            hasInterface { it.hasNameEndingWith("Interface1") } shouldBeEqualTo true
            hasAllInterfaces { it.hasNameStartingWith("Fixture") } shouldBeEqualTo true
            hasAllInterfaces { it.hasNameEndingWith("Class1") } shouldBeEqualTo false
        }
    }

    @Test
    fun `file-contains-interfaces includeNested true`() {
        // given
        val sut =
            getSnippetFile("file-contains-interfaces")
                .files
                .first()

        // then
        val expected = listOf("FixtureInterface", "FixtureNestedInterface")

        sut
            .interfaces(includeNested = true)
            .map { it.name }
            .shouldBeEqualTo(expected)
    }

    @Test
    fun `file-contains-interfaces includeNested false`() {
        // given
        val sut =
            getSnippetFile("file-contains-interfaces")
                .files
                .first()

        // then
        val expected = listOf("FixtureInterface")

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
                .files
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

    @Test
    fun `file-has-no-interfaces-ignore-case`() {
        // given
        val sut =
            getSnippetFile("file-has-no-interfaces-ignore-case")
                .files
                .first()

        // then
        assertSoftly(sut) {
            hasInterfaceWithName("fixtureinterface") shouldBeEqualTo false
            hasInterfaceWithName("fixtureinterface", ignoreCase = true) shouldBeEqualTo false
            hasInterfaceWithName(listOf("fixtureinterface")) shouldBeEqualTo false
            hasInterfaceWithName(listOf("fixtureinterface"), ignoreCase = true) shouldBeEqualTo false
            hasInterfaceWithName(setOf("fixtureinterface")) shouldBeEqualTo false
            hasInterfaceWithName(setOf("fixtureinterface"), ignoreCase = true) shouldBeEqualTo false
            hasInterfacesWithAllNames("fixtureinterface1", "fixtureinterface2") shouldBeEqualTo false
            hasInterfacesWithAllNames("fixtureinterface1", "fixtureinterface2", ignoreCase = true) shouldBeEqualTo false
            hasInterfacesWithAllNames(listOf("fixtureinterface1", "fixtureinterface2")) shouldBeEqualTo false
            hasInterfacesWithAllNames(listOf("fixtureinterface1", "fixtureinterface2"), ignoreCase = true) shouldBeEqualTo false
            hasInterfacesWithAllNames(setOf("fixtureinterface1", "fixtureinterface2")) shouldBeEqualTo false
            hasInterfacesWithAllNames(setOf("fixtureinterface1", "fixtureinterface2"), ignoreCase = true) shouldBeEqualTo false
        }
    }

    @Test
    fun `file-has-interfaces-ignore-case`() {
        // given
        val sut =
            getSnippetFile("file-has-interfaces-ignore-case")
                .files
                .first()

        // then
        assertSoftly(sut) {
            hasInterfaceWithName("fixtureinterface1") shouldBeEqualTo false
            hasInterfaceWithName("fixtureinterface1", ignoreCase = true) shouldBeEqualTo true
            hasInterfaceWithName("otherinterface") shouldBeEqualTo false
            hasInterfaceWithName("otherinterface", ignoreCase = true) shouldBeEqualTo false
            hasInterfaceWithName("fixtureinterface1", "otherName") shouldBeEqualTo false
            hasInterfaceWithName("fixtureinterface1", "otherName", ignoreCase = true) shouldBeEqualTo true
            hasInterfaceWithName(listOf("fixtureinterface1")) shouldBeEqualTo false
            hasInterfaceWithName(listOf("fixtureinterface1"), ignoreCase = true) shouldBeEqualTo true
            hasInterfaceWithName(listOf("otherinterface")) shouldBeEqualTo false
            hasInterfaceWithName(listOf("otherinterface"), ignoreCase = true) shouldBeEqualTo false
            hasInterfaceWithName(listOf("fixtureinterface1", "otherName")) shouldBeEqualTo false
            hasInterfaceWithName(listOf("fixtureinterface1", "otherName"), ignoreCase = true) shouldBeEqualTo true
            hasInterfacesWithAllNames("fixtureinterface1") shouldBeEqualTo false
            hasInterfacesWithAllNames("fixtureinterface1", ignoreCase = true) shouldBeEqualTo true
            hasInterfacesWithAllNames("fixtureinterface1", "fixtureinterface2") shouldBeEqualTo false
            hasInterfacesWithAllNames("fixtureinterface1", "fixtureinterface2", ignoreCase = true) shouldBeEqualTo true
            hasInterfacesWithAllNames("fixtureinterface1", "otherinterface") shouldBeEqualTo false
            hasInterfacesWithAllNames("fixtureinterface1", "otherinterface", ignoreCase = true) shouldBeEqualTo false
            hasInterfacesWithAllNames(listOf("fixtureinterface1")) shouldBeEqualTo false
            hasInterfacesWithAllNames(listOf("fixtureinterface1"), ignoreCase = true) shouldBeEqualTo true
            hasInterfacesWithAllNames(listOf("fixtureinterface1", "fixtureinterface2")) shouldBeEqualTo false
            hasInterfacesWithAllNames(listOf("fixtureinterface1", "fixtureinterface2"), ignoreCase = true) shouldBeEqualTo true
            hasInterfacesWithAllNames(listOf("fixtureinterface1", "otherinterface")) shouldBeEqualTo false
            hasInterfacesWithAllNames(listOf("fixtureinterface1", "otherinterface"), ignoreCase = true) shouldBeEqualTo false
        }
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope(
            "core/declaration/kofile/snippet/forkointerfaceprovider/",
            fileName,
        )
}
