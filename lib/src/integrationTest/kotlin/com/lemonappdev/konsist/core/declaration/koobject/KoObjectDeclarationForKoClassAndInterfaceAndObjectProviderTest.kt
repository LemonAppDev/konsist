package com.lemonappdev.konsist.core.declaration.koobject

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoObjectDeclarationForKoClassAndInterfaceAndObjectProviderTest {
    @Test
    fun `object-has-no-classes-and-interfaces-and-objects`() {
        // given
        val sut =
            getSnippetFile("object-has-no-classes-and-interfaces-and-objects")
                .objects()
                .first()

        // then
        assertSoftly(sut) {
            classesAndInterfacesAndObjects() shouldBeEqualTo emptyList()
            hasClassesOrInterfacesOrObjects() shouldBeEqualTo false
            hasClassOrInterfaceOrObjectWithName(emptyList()) shouldBeEqualTo false
            hasClassOrInterfaceOrObjectWithName(emptySet()) shouldBeEqualTo false
            hasClassesAndInterfacesAndObjectsWithAllNames(emptyList()) shouldBeEqualTo false
            hasClassesAndInterfacesAndObjectsWithAllNames(emptySet()) shouldBeEqualTo false
            hasClassOrInterfaceOrObjectWithName("FixtureClass") shouldBeEqualTo false
            hasClassOrInterfaceOrObjectWithName(listOf("FixtureClass")) shouldBeEqualTo false
            hasClassOrInterfaceOrObjectWithName(setOf("FixtureClass")) shouldBeEqualTo false
            hasClassOrInterfaceOrObjectWithName("FixtureInterface") shouldBeEqualTo false
            hasClassOrInterfaceOrObjectWithName(listOf("FixtureInterface")) shouldBeEqualTo false
            hasClassOrInterfaceOrObjectWithName(setOf("FixtureInterface")) shouldBeEqualTo false
            hasClassesAndInterfacesAndObjectsWithAllNames("FixtureClass", "FixtureInterface") shouldBeEqualTo false
            hasClassesAndInterfacesAndObjectsWithAllNames(
                listOf(
                    "FixtureClass",
                    "FixtureInterface",
                ),
            ) shouldBeEqualTo false
            hasClassesAndInterfacesAndObjectsWithAllNames(setOf("FixtureClass", "FixtureInterface")) shouldBeEqualTo false
            hasClassOrInterfaceOrObject { it.name == "FixtureClass" } shouldBeEqualTo false
            hasClassOrInterfaceOrObject { it.name == "FixtureInterface" } shouldBeEqualTo false
            hasAllClassesAndInterfacesAndObjects { it.hasNameStartingWith("Fixture") } shouldBeEqualTo true
        }
    }

    @Test
    fun `object-has-class-and-interface-and-object`() {
        // given
        val sut =
            getSnippetFile("object-has-class-and-interface-and-object")
                .objects()
                .first()

        // then
        assertSoftly(sut) {
            hasClassesOrInterfacesOrObjects() shouldBeEqualTo true
            hasClassOrInterfaceOrObjectWithName(emptyList()) shouldBeEqualTo true
            hasClassOrInterfaceOrObjectWithName(emptySet()) shouldBeEqualTo true
            hasClassesAndInterfacesAndObjectsWithAllNames(emptyList()) shouldBeEqualTo true
            hasClassesAndInterfacesAndObjectsWithAllNames(emptySet()) shouldBeEqualTo true
            hasClassOrInterfaceOrObjectWithName("FixtureClass") shouldBeEqualTo true
            hasClassOrInterfaceOrObjectWithName("FixtureInterface") shouldBeEqualTo true
            hasClassOrInterfaceOrObjectWithName("FixtureObject") shouldBeEqualTo true
            hasClassOrInterfaceOrObjectWithName("FixtureClass", "OtherInterface") shouldBeEqualTo true
            hasClassOrInterfaceOrObjectWithName(listOf("FixtureClass")) shouldBeEqualTo true
            hasClassOrInterfaceOrObjectWithName(listOf("FixtureInterface")) shouldBeEqualTo true
            hasClassOrInterfaceOrObjectWithName(listOf("FixtureObject")) shouldBeEqualTo true
            hasClassOrInterfaceOrObjectWithName(listOf("FixtureClass", "OtherInterface")) shouldBeEqualTo true
            hasClassOrInterfaceOrObjectWithName(setOf("FixtureClass")) shouldBeEqualTo true
            hasClassOrInterfaceOrObjectWithName(setOf("FixtureInterface")) shouldBeEqualTo true
            hasClassOrInterfaceOrObjectWithName(setOf("FixtureObject")) shouldBeEqualTo true
            hasClassOrInterfaceOrObjectWithName(setOf("FixtureClass", "OtherInterface")) shouldBeEqualTo true
            hasClassesAndInterfacesAndObjectsWithAllNames("FixtureClass") shouldBeEqualTo true
            hasClassesAndInterfacesAndObjectsWithAllNames(
                "FixtureClass",
                "FixtureInterface",
                "FixtureObject",
            ) shouldBeEqualTo true
            hasClassesAndInterfacesAndObjectsWithAllNames("FixtureClass", "FixtureInterface") shouldBeEqualTo true
            hasClassesAndInterfacesAndObjectsWithAllNames("FixtureClass", "OtherInterface") shouldBeEqualTo false
            hasClassesAndInterfacesAndObjectsWithAllNames(listOf("FixtureClass")) shouldBeEqualTo true
            hasClassesAndInterfacesAndObjectsWithAllNames(listOf("FixtureClass", "FixtureInterface")) shouldBeEqualTo true
            hasClassesAndInterfacesAndObjectsWithAllNames(listOf("FixtureClass", "OtherInterface")) shouldBeEqualTo false
            hasClassesAndInterfacesAndObjectsWithAllNames(
                listOf(
                    "FixtureClass",
                    "FixtureInterface",
                    "FixtureObject",
                ),
            ) shouldBeEqualTo true
            hasClassesAndInterfacesAndObjectsWithAllNames(setOf("FixtureClass")) shouldBeEqualTo true
            hasClassesAndInterfacesAndObjectsWithAllNames(setOf("FixtureClass", "FixtureInterface")) shouldBeEqualTo true
            hasClassesAndInterfacesAndObjectsWithAllNames(setOf("FixtureClass", "OtherInterface")) shouldBeEqualTo false
            hasClassesAndInterfacesAndObjectsWithAllNames(
                setOf(
                    "FixtureClass",
                    "FixtureInterface",
                    "FixtureObject",
                ),
            ) shouldBeEqualTo true
            hasClassOrInterfaceOrObject { it.name == "FixtureClass" } shouldBeEqualTo true
            hasClassOrInterfaceOrObject { it.name == "FixtureInterface" } shouldBeEqualTo true
            hasClassOrInterfaceOrObject { it.hasNameEndingWith("Class") } shouldBeEqualTo true
            hasClassOrInterfaceOrObject { it.hasNameEndingWith("OtherClass") } shouldBeEqualTo false
            hasAllClassesAndInterfacesAndObjects { it.hasNameStartingWith("Fixture") } shouldBeEqualTo true
            hasAllClassesAndInterfacesAndObjects { it.hasNameEndingWith("Class") } shouldBeEqualTo false
        }
    }

    @Test
    fun `object-contains-nested-and-local-classes-and-interfaces-and-objects includeNested true includeLocal true`() {
        // given
        val sut =
            getSnippetFile("object-contains-nested-and-local-classes-and-interfaces-and-objects")
                .objects()
                .first()

        // then
        val expected =
            listOf(
                "FixtureLocalClass",
                "FixtureClassNestedInsideObject",
                "FixtureInterfaceNestedInsideObject",
                "FixtureObject",
                "FixtureObjectNestedInsideObject",
            )

        sut
            .classesAndInterfacesAndObjects(includeNested = true, includeLocal = true)
            .map { it.name }
            .shouldBeEqualTo(expected)
    }

    @Test
    fun `object-contains-nested-and-local-classes-and-interfaces-and-objects includeNested true includeLocal false`() {
        // given
        val sut =
            getSnippetFile("object-contains-nested-and-local-classes-and-interfaces-and-objects")
                .objects()
                .first()

        // then
        val expected =
            listOf(
                "FixtureClassNestedInsideObject",
                "FixtureInterfaceNestedInsideObject",
                "FixtureObject",
                "FixtureObjectNestedInsideObject",
            )

        sut
            .classesAndInterfacesAndObjects(includeNested = true, includeLocal = false)
            .map { it.name }
            .shouldBeEqualTo(expected)
    }

    @Test
    fun `object-contains-nested-and-local-classes-and-interfaces-and-objects includeNested false includeLocal true`() {
        // given
        val sut =
            getSnippetFile("object-contains-nested-and-local-classes-and-interfaces-and-objects")
                .objects()
                .first()

        // then
        val expected = listOf("FixtureLocalClass", "FixtureObject")

        sut
            .classesAndInterfacesAndObjects(includeNested = false, includeLocal = true)
            .map { it.name }
            .shouldBeEqualTo(expected)
    }

    @Test
    fun `object-contains-nested-and-local-classes-and-interfaces-and-objects includeNested false includeLocal false`() {
        // given
        val sut =
            getSnippetFile("object-contains-nested-and-local-classes-and-interfaces-and-objects")
                .objects()
                .first()

        // then
        val expected = listOf("FixtureObject")

        sut
            .classesAndInterfacesAndObjects(includeNested = false, includeLocal = false)
            .map { it.name }
            .shouldBeEqualTo(expected)
    }

    @Test
    fun `count-classes-and-interfaces-and-objects`() {
        // given
        val sut =
            getSnippetFile("count-classes-and-interfaces-and-objects")
                .objects()
                .first()

        // then
        assertSoftly(sut) {
            numClassesAndInterfacesAndObjects(includeNested = true, includeLocal = true) shouldBeEqualTo 5
            numClassesAndInterfacesAndObjects(includeNested = true, includeLocal = false) shouldBeEqualTo 4
            numClassesAndInterfacesAndObjects(includeNested = false, includeLocal = true) shouldBeEqualTo 2
            numClassesAndInterfacesAndObjects(includeNested = false, includeLocal = false) shouldBeEqualTo 1
            countClassesAndInterfacesAndObjects(
                includeNested = false,
                includeLocal = false,
            ) { it.hasPrivateModifier } shouldBeEqualTo 1
            countClassesAndInterfacesAndObjects { it.hasPrivateModifier } shouldBeEqualTo 4
            countClassesAndInterfacesAndObjects { it.name == "FixtureClass" && it.hasInternalModifier } shouldBeEqualTo 0
        }
    }

    @Test
    fun `object-has-no-classes-and-interfaces-and-objects-ignore-case`() {
        // given
        val sut =
            getSnippetFile("object-has-no-classes-and-interfaces-and-objects-ignore-case")
                .objects()
                .first()

        // then
        assertSoftly(sut) {
            hasClassOrInterfaceOrObjectWithName("fixtureclass") shouldBeEqualTo false
            hasClassOrInterfaceOrObjectWithName("fixtureclass", ignoreCase = true) shouldBeEqualTo false
            hasClassOrInterfaceOrObjectWithName(listOf("fixtureclass")) shouldBeEqualTo false
            hasClassOrInterfaceOrObjectWithName(listOf("fixtureclass"), ignoreCase = true) shouldBeEqualTo false
            hasClassOrInterfaceOrObjectWithName(setOf("fixtureclass")) shouldBeEqualTo false
            hasClassOrInterfaceOrObjectWithName(setOf("fixtureclass"), ignoreCase = true) shouldBeEqualTo false
            hasClassesAndInterfacesAndObjectsWithAllNames("fixtureclass1", "fixtureinterface") shouldBeEqualTo false
            hasClassesAndInterfacesAndObjectsWithAllNames(
                "fixtureclass1",
                "fixtureinterface",
                ignoreCase = true,
            ) shouldBeEqualTo false
            hasClassesAndInterfacesAndObjectsWithAllNames(
                listOf(
                    "fixtureclass",
                    "fixtureinterface",
                ),
            ) shouldBeEqualTo false
            hasClassesAndInterfacesAndObjectsWithAllNames(
                listOf("fixtureclass", "fixtureinterface"),
                ignoreCase = true,
            ) shouldBeEqualTo false
            hasClassesAndInterfacesAndObjectsWithAllNames(setOf("fixtureclass", "fixtureinterface")) shouldBeEqualTo false
            hasClassesAndInterfacesAndObjectsWithAllNames(
                setOf("fixtureclass", "fixtureinterface"),
                ignoreCase = true,
            ) shouldBeEqualTo false
        }
    }

    @Test
    fun `object-has-class-and-interface-and-object-ignore-case`() {
        // given
        val sut =
            getSnippetFile("object-has-class-and-interface-and-object-ignore-case")
                .objects()
                .first()

        // then
        assertSoftly(sut) {
            hasClassOrInterfaceOrObjectWithName("fixtureclass") shouldBeEqualTo false
            hasClassOrInterfaceOrObjectWithName("fixtureclass", ignoreCase = true) shouldBeEqualTo true
            hasClassOrInterfaceOrObjectWithName("otherclass") shouldBeEqualTo false
            hasClassOrInterfaceOrObjectWithName("otherclass", ignoreCase = true) shouldBeEqualTo false
            hasClassOrInterfaceOrObjectWithName("fixtureclass", "otherName") shouldBeEqualTo false
            hasClassOrInterfaceOrObjectWithName("fixtureclass", "otherName", ignoreCase = true) shouldBeEqualTo true
            hasClassOrInterfaceOrObjectWithName(listOf("fixtureclass")) shouldBeEqualTo false
            hasClassOrInterfaceOrObjectWithName(listOf("fixtureclass"), ignoreCase = true) shouldBeEqualTo true
            hasClassOrInterfaceOrObjectWithName(listOf("otherclass")) shouldBeEqualTo false
            hasClassOrInterfaceOrObjectWithName(listOf("otherclass"), ignoreCase = true) shouldBeEqualTo false
            hasClassOrInterfaceOrObjectWithName(listOf("fixtureclass", "otherName")) shouldBeEqualTo false
            hasClassOrInterfaceOrObjectWithName(
                listOf("fixtureclass", "otherName"),
                ignoreCase = true,
            ) shouldBeEqualTo true
            hasClassesAndInterfacesAndObjectsWithAllNames("fixtureclass") shouldBeEqualTo false
            hasClassesAndInterfacesAndObjectsWithAllNames("fixtureclass", ignoreCase = true) shouldBeEqualTo true
            hasClassesAndInterfacesAndObjectsWithAllNames("fixtureclass", "fixtureinterface") shouldBeEqualTo false
            hasClassesAndInterfacesAndObjectsWithAllNames(
                "fixtureclass",
                "fixtureinterface",
                ignoreCase = true,
            ) shouldBeEqualTo true
            hasClassesAndInterfacesAndObjectsWithAllNames("fixtureclass", "otherclass") shouldBeEqualTo false
            hasClassesAndInterfacesAndObjectsWithAllNames(
                "fixtureclass",
                "otherclass",
                ignoreCase = true,
            ) shouldBeEqualTo false
            hasClassesAndInterfacesAndObjectsWithAllNames(listOf("fixtureclass")) shouldBeEqualTo false
            hasClassesAndInterfacesAndObjectsWithAllNames(listOf("fixtureclass"), ignoreCase = true) shouldBeEqualTo true
            hasClassesAndInterfacesAndObjectsWithAllNames(
                listOf(
                    "fixtureclass",
                    "fixtureinterface",
                ),
            ) shouldBeEqualTo false
            hasClassesAndInterfacesAndObjectsWithAllNames(
                listOf("fixtureclass", "fixtureinterface"),
                ignoreCase = true,
            ) shouldBeEqualTo true
            hasClassesAndInterfacesAndObjectsWithAllNames(listOf("fixtureclass", "otherclass")) shouldBeEqualTo false
            hasClassesAndInterfacesAndObjectsWithAllNames(
                listOf("fixtureclass", "otherclass"),
                ignoreCase = true,
            ) shouldBeEqualTo false
        }
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/koobject/snippet/forkoclassandinterfaceandobjectprovider/", fileName)
}
