package com.lemonappdev.konsist.core.declaration.koobject

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoObjectDeclarationForKoInterfaceAndObjectProviderTest {
    @Test
    fun `object-has-no-interfaces-and-objects`() {
        // given
        val sut =
            getSnippetFile("object-has-no-interfaces-and-objects")
                .objects()
                .first()

        // then
        assertSoftly(sut) {
            interfacesAndObjects() shouldBeEqualTo emptyList()
            hasInterfacesOrObjects() shouldBeEqualTo false
            hasInterfaceOrObjectWithName(emptyList()) shouldBeEqualTo false
            hasInterfaceOrObjectWithName(emptySet()) shouldBeEqualTo false
            hasInterfacesAndObjectsWithAllNames(emptyList()) shouldBeEqualTo false
            hasInterfacesAndObjectsWithAllNames(emptySet()) shouldBeEqualTo false
            hasInterfaceOrObjectWithName("FixtureInterface") shouldBeEqualTo false
            hasInterfaceOrObjectWithName(listOf("FixtureInterface")) shouldBeEqualTo false
            hasInterfaceOrObjectWithName(setOf("FixtureInterface")) shouldBeEqualTo false
            hasInterfaceOrObjectWithName("FixtureObject") shouldBeEqualTo false
            hasInterfaceOrObjectWithName(listOf("FixtureObject")) shouldBeEqualTo false
            hasInterfaceOrObjectWithName(setOf("FixtureObject")) shouldBeEqualTo false
            hasInterfacesAndObjectsWithAllNames("FixtureInterface", "FixtureObject") shouldBeEqualTo false
            hasInterfacesAndObjectsWithAllNames(listOf("FixtureInterface", "FixtureObject")) shouldBeEqualTo false
            hasInterfacesAndObjectsWithAllNames(setOf("FixtureInterface", "FixtureObject")) shouldBeEqualTo false
            hasInterfaceOrObject { it.name == "FixtureInterface" } shouldBeEqualTo false
            hasInterfaceOrObject { it.name == "FixtureObject" } shouldBeEqualTo false
            hasAllInterfacesAndObjects { it.hasNameStartingWith("Fixture") } shouldBeEqualTo true
        }
    }

    @Test
    fun `object-has-interface-and-object`() {
        // given
        val sut =
            getSnippetFile("object-has-interface-and-object")
                .objects()
                .first()

        // then
        assertSoftly(sut) {
            hasInterfacesOrObjects() shouldBeEqualTo true
            hasInterfaceOrObjectWithName(emptyList()) shouldBeEqualTo true
            hasInterfaceOrObjectWithName(emptySet()) shouldBeEqualTo true
            hasInterfacesAndObjectsWithAllNames(emptyList()) shouldBeEqualTo true
            hasInterfacesAndObjectsWithAllNames(emptySet()) shouldBeEqualTo true
            hasInterfaceOrObjectWithName("FixtureInterface") shouldBeEqualTo true
            hasInterfaceOrObjectWithName("FixtureObject") shouldBeEqualTo true
            hasInterfaceOrObjectWithName("FixtureInterface", "OtherObject") shouldBeEqualTo true
            hasInterfaceOrObjectWithName(listOf("FixtureInterface")) shouldBeEqualTo true
            hasInterfaceOrObjectWithName(listOf("FixtureObject")) shouldBeEqualTo true
            hasInterfaceOrObjectWithName(listOf("FixtureInterface", "OtherObject")) shouldBeEqualTo true
            hasInterfaceOrObjectWithName(setOf("FixtureInterface")) shouldBeEqualTo true
            hasInterfaceOrObjectWithName(setOf("FixtureObject")) shouldBeEqualTo true
            hasInterfaceOrObjectWithName(setOf("FixtureInterface", "OtherObject")) shouldBeEqualTo true
            hasInterfacesAndObjectsWithAllNames("FixtureInterface") shouldBeEqualTo true
            hasInterfacesAndObjectsWithAllNames("FixtureInterface", "FixtureObject") shouldBeEqualTo true
            hasInterfacesAndObjectsWithAllNames("FixtureInterface", "OtherObject") shouldBeEqualTo false
            hasInterfacesAndObjectsWithAllNames(listOf("FixtureInterface")) shouldBeEqualTo true
            hasInterfacesAndObjectsWithAllNames(listOf("FixtureInterface", "FixtureObject")) shouldBeEqualTo true
            hasInterfacesAndObjectsWithAllNames(listOf("FixtureInterface", "OtherObject")) shouldBeEqualTo false
            hasInterfacesAndObjectsWithAllNames(setOf("FixtureInterface")) shouldBeEqualTo true
            hasInterfacesAndObjectsWithAllNames(setOf("FixtureInterface", "FixtureObject")) shouldBeEqualTo true
            hasInterfacesAndObjectsWithAllNames(setOf("FixtureInterface", "OtherObject")) shouldBeEqualTo false
            hasInterfaceOrObject { it.name == "FixtureInterface" } shouldBeEqualTo true
            hasInterfaceOrObject { it.name == "FixtureObject" } shouldBeEqualTo true
            hasInterfaceOrObject { it.hasNameEndingWith("Interface") } shouldBeEqualTo true
            hasInterfaceOrObject { it.hasNameEndingWith("Interface") || it.hasNameEndingWith("Object") } shouldBeEqualTo true
            hasAllInterfacesAndObjects { it.hasNameStartingWith("Fixture") } shouldBeEqualTo true
            hasAllInterfacesAndObjects { it.hasNameEndingWith("Interface") } shouldBeEqualTo false
        }
    }

    @Test
    fun `object-contains-nested-interfaces-and-objects includeNested true`() {
        // given
        val sut =
            getSnippetFile("object-contains-nested-interfaces-and-objects")
                .objects()
                .first()

        // then
        val expected = listOf("FixtureInterfaceNestedInsideObject", "FixtureObject", "FixtureObjectNestedInsideObject")

        sut
            .interfacesAndObjects(includeNested = true)
            .map { it.name }
            .shouldBeEqualTo(expected)
    }

    @Test
    fun `object-contains-nested-interfaces-and-objects includeNested false`() {
        // given
        val sut =
            getSnippetFile("object-contains-nested-interfaces-and-objects")
                .objects()
                .first()

        // then
        val expected = listOf("FixtureObject")

        sut
            .interfacesAndObjects(includeNested = false)
            .map { it.name }
            .shouldBeEqualTo(expected)
    }

    @Test
    fun `count-interfaces-and-objects`() {
        // given
        val sut =
            getSnippetFile("count-interfaces-and-objects")
                .objects()
                .first()

        // then
        assertSoftly(sut) {
            numInterfacesAndObjects(includeNested = true) shouldBeEqualTo 2
            numInterfacesAndObjects(includeNested = false) shouldBeEqualTo 0
            countInterfacesAndObjects(includeNested = false) { it.hasPrivateModifier } shouldBeEqualTo 0
            countInterfacesAndObjects { it.hasPrivateModifier } shouldBeEqualTo 2
            countInterfacesAndObjects { it.name == "FixtureInterface" && it.hasInternalModifier } shouldBeEqualTo 0
        }
    }

    @Test
    fun `object-has-no-interfaces-and-objects-ignore-case`() {
        // given
        val sut =
            getSnippetFile("object-has-no-interfaces-and-objects-ignore-case")
                .objects()
                .first()

        // then
        assertSoftly(sut) {
            hasInterfaceOrObjectWithName("fixtureobject") shouldBeEqualTo false
            hasInterfaceOrObjectWithName("fixtureobject", ignoreCase = true) shouldBeEqualTo false
            hasInterfaceOrObjectWithName(listOf("fixtureobject")) shouldBeEqualTo false
            hasInterfaceOrObjectWithName(listOf("fixtureobject"), ignoreCase = true) shouldBeEqualTo false
            hasInterfaceOrObjectWithName(setOf("fixtureobject")) shouldBeEqualTo false
            hasInterfaceOrObjectWithName(setOf("fixtureobject"), ignoreCase = true) shouldBeEqualTo false
            hasInterfacesAndObjectsWithAllNames("fixtureobject1", "fixtureinterface") shouldBeEqualTo false
            hasInterfacesAndObjectsWithAllNames("fixtureobject1", "fixtureinterface", ignoreCase = true) shouldBeEqualTo false
            hasInterfacesAndObjectsWithAllNames(listOf("fixtureobject", "fixtureinterface")) shouldBeEqualTo false
            hasInterfacesAndObjectsWithAllNames(listOf("fixtureobject", "fixtureinterface"), ignoreCase = true) shouldBeEqualTo false
            hasInterfacesAndObjectsWithAllNames(setOf("fixtureobject", "fixtureinterface")) shouldBeEqualTo false
            hasInterfacesAndObjectsWithAllNames(setOf("fixtureobject", "fixtureinterface"), ignoreCase = true) shouldBeEqualTo false
        }
    }

    @Test
    fun `object-has-interface-and-object-ignore-case`() {
        // given
        val sut =
            getSnippetFile("object-has-interface-and-object-ignore-case")
                .objects()
                .first()

        // then
        assertSoftly(sut) {
            hasInterfaceOrObjectWithName("fixtureobject") shouldBeEqualTo false
            hasInterfaceOrObjectWithName("fixtureobject", ignoreCase = true) shouldBeEqualTo true
            hasInterfaceOrObjectWithName("otherclass") shouldBeEqualTo false
            hasInterfaceOrObjectWithName("otherclass", ignoreCase = true) shouldBeEqualTo false
            hasInterfaceOrObjectWithName("fixtureobject", "otherName") shouldBeEqualTo false
            hasInterfaceOrObjectWithName("fixtureobject", "otherName", ignoreCase = true) shouldBeEqualTo true
            hasInterfaceOrObjectWithName(listOf("fixtureobject")) shouldBeEqualTo false
            hasInterfaceOrObjectWithName(listOf("fixtureobject"), ignoreCase = true) shouldBeEqualTo true
            hasInterfaceOrObjectWithName(listOf("otherclass")) shouldBeEqualTo false
            hasInterfaceOrObjectWithName(listOf("otherclass"), ignoreCase = true) shouldBeEqualTo false
            hasInterfaceOrObjectWithName(listOf("fixtureobject", "otherName")) shouldBeEqualTo false
            hasInterfaceOrObjectWithName(listOf("fixtureobject", "otherName"), ignoreCase = true) shouldBeEqualTo true
            hasInterfacesAndObjectsWithAllNames("fixtureobject") shouldBeEqualTo false
            hasInterfacesAndObjectsWithAllNames("fixtureobject", ignoreCase = true) shouldBeEqualTo true
            hasInterfacesAndObjectsWithAllNames("fixtureobject", "fixtureinterface") shouldBeEqualTo false
            hasInterfacesAndObjectsWithAllNames("fixtureobject", "fixtureinterface", ignoreCase = true) shouldBeEqualTo true
            hasInterfacesAndObjectsWithAllNames("fixtureobject", "otherclass") shouldBeEqualTo false
            hasInterfacesAndObjectsWithAllNames("fixtureobject", "otherclass", ignoreCase = true) shouldBeEqualTo false
            hasInterfacesAndObjectsWithAllNames(listOf("fixtureobject")) shouldBeEqualTo false
            hasInterfacesAndObjectsWithAllNames(listOf("fixtureobject"), ignoreCase = true) shouldBeEqualTo true
            hasInterfacesAndObjectsWithAllNames(listOf("fixtureobject", "fixtureinterface")) shouldBeEqualTo false
            hasInterfacesAndObjectsWithAllNames(listOf("fixtureobject", "fixtureinterface"), ignoreCase = true) shouldBeEqualTo true
            hasInterfacesAndObjectsWithAllNames(listOf("fixtureobject", "otherclass")) shouldBeEqualTo false
            hasInterfacesAndObjectsWithAllNames(listOf("fixtureobject", "otherclass"), ignoreCase = true) shouldBeEqualTo false
        }
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/koobject/snippet/forkointerfaceandobjectprovider/", fileName)
}
