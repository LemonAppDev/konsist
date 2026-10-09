package com.lemonappdev.konsist.core.declaration.kofile

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import com.lemonappdev.konsist.api.declaration.KoClassDeclaration
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoFileDeclarationForKoClassAndInterfaceProviderTest {
    @Test
    fun `file-has-no-classes-and-interfaces`() {
        // given
        val sut =
            getSnippetFile("file-has-no-classes-and-interfaces")
                .files
                .first()

        // then
        assertSoftly(sut) {
            classesAndInterfaces() shouldBeEqualTo emptyList()
            hasClassesOrInterfaces() shouldBeEqualTo false
            hasClassOrInterfaceWithName(emptyList()) shouldBeEqualTo false
            hasClassOrInterfaceWithName(emptySet()) shouldBeEqualTo false
            hasClassesAndInterfacesWithAllNames(emptyList()) shouldBeEqualTo false
            hasClassesAndInterfacesWithAllNames(emptySet()) shouldBeEqualTo false
            hasClassOrInterfaceWithName("FixtureClass") shouldBeEqualTo false
            hasClassOrInterfaceWithName(listOf("FixtureClass")) shouldBeEqualTo false
            hasClassOrInterfaceWithName(setOf("FixtureClass")) shouldBeEqualTo false
            hasClassOrInterfaceWithName("FixtureInterface") shouldBeEqualTo false
            hasClassOrInterfaceWithName(listOf("FixtureInterface")) shouldBeEqualTo false
            hasClassOrInterfaceWithName(setOf("FixtureInterface")) shouldBeEqualTo false
            hasClassesAndInterfacesWithAllNames("FixtureClass", "FixtureInterface") shouldBeEqualTo false
            hasClassesAndInterfacesWithAllNames(listOf("FixtureClass", "FixtureInterface")) shouldBeEqualTo false
            hasClassesAndInterfacesWithAllNames(setOf("FixtureClass", "FixtureInterface")) shouldBeEqualTo false
            hasClassOrInterface { it.name == "FixtureClass" } shouldBeEqualTo false
            hasClassOrInterface { it.name == "FixtureInterface" } shouldBeEqualTo false
            hasAllClassesAndInterfaces { it.hasNameStartingWith("Fixture") } shouldBeEqualTo true
        }
    }

    @Test
    fun `file-has-class-and-interface`() {
        // given
        val sut =
            getSnippetFile("file-has-class-and-interface")
                .files
                .first()

        // then
        assertSoftly(sut) {
            hasClassesOrInterfaces() shouldBeEqualTo true
            hasClassOrInterfaceWithName(emptyList()) shouldBeEqualTo true
            hasClassOrInterfaceWithName(emptySet()) shouldBeEqualTo true
            hasClassesAndInterfacesWithAllNames(emptyList()) shouldBeEqualTo true
            hasClassesAndInterfacesWithAllNames(emptySet()) shouldBeEqualTo true
            hasClassOrInterfaceWithName("FixtureClass") shouldBeEqualTo true
            hasClassOrInterfaceWithName("FixtureInterface") shouldBeEqualTo true
            hasClassOrInterfaceWithName("FixtureClass", "OtherInterface") shouldBeEqualTo true
            hasClassOrInterfaceWithName(listOf("FixtureClass")) shouldBeEqualTo true
            hasClassOrInterfaceWithName(listOf("FixtureInterface")) shouldBeEqualTo true
            hasClassOrInterfaceWithName(listOf("FixtureClass", "OtherInterface")) shouldBeEqualTo true
            hasClassOrInterfaceWithName(setOf("FixtureClass")) shouldBeEqualTo true
            hasClassOrInterfaceWithName(setOf("FixtureInterface")) shouldBeEqualTo true
            hasClassOrInterfaceWithName(setOf("FixtureClass", "OtherInterface")) shouldBeEqualTo true
            hasClassesAndInterfacesWithAllNames("FixtureClass") shouldBeEqualTo true
            hasClassesAndInterfacesWithAllNames("FixtureClass", "FixtureInterface") shouldBeEqualTo true
            hasClassesAndInterfacesWithAllNames("FixtureClass", "OtherInterface") shouldBeEqualTo false
            hasClassesAndInterfacesWithAllNames(listOf("FixtureClass")) shouldBeEqualTo true
            hasClassesAndInterfacesWithAllNames(listOf("FixtureClass", "FixtureInterface")) shouldBeEqualTo true
            hasClassesAndInterfacesWithAllNames(listOf("FixtureClass", "OtherInterface")) shouldBeEqualTo false
            hasClassesAndInterfacesWithAllNames(setOf("FixtureClass")) shouldBeEqualTo true
            hasClassesAndInterfacesWithAllNames(setOf("FixtureClass", "FixtureInterface")) shouldBeEqualTo true
            hasClassesAndInterfacesWithAllNames(setOf("FixtureClass", "OtherInterface")) shouldBeEqualTo false
            hasClassOrInterface { it.name == "FixtureClass" } shouldBeEqualTo true
            hasClassOrInterface { it.name == "FixtureInterface" } shouldBeEqualTo true
            hasClassOrInterface { it.hasNameEndingWith("Class") } shouldBeEqualTo true
            hasClassOrInterface { it.hasNameEndingWith("Class") || it.hasNameEndingWith("Interface") } shouldBeEqualTo true
            hasAllClassesAndInterfaces { it.hasNameStartingWith("Fixture") } shouldBeEqualTo true
            hasAllClassesAndInterfaces { it.hasNameEndingWith("Class") } shouldBeEqualTo false
        }
    }

    @Test
    fun `file-contains-nested-and-local-classes-and-interfaces includeNested true includeLocal true`() {
        // given
        val sut =
            getSnippetFile("file-contains-nested-and-local-classes-and-interfaces")
                .files
                .first()

        // then
        val expected = listOf("FixtureLocalClass", "FixtureClassNestedInsideObject", "FixtureInterfaceNestedInsideObject")

        sut
            .classesAndInterfaces(includeNested = true, includeLocal = true)
            .map { it.name }
            .shouldBeEqualTo(expected)
    }

    @Test
    fun `file-contains-nested-and-local-classes-and-interfaces includeNested true includeLocal false`() {
        // given
        val sut =
            getSnippetFile("file-contains-nested-and-local-classes-and-interfaces")
                .files
                .first()

        // then
        val expected = listOf("FixtureClassNestedInsideObject", "FixtureInterfaceNestedInsideObject")

        sut
            .classesAndInterfaces(includeNested = true, includeLocal = false)
            .map { it.name }
            .shouldBeEqualTo(expected)
    }

    @Test
    fun `file-contains-nested-and-local-classes-and-interfaces includeNested false includeLocal true`() {
        // given
        val sut =
            getSnippetFile("file-contains-nested-and-local-classes-and-interfaces")
                .files
                .first()

        // then
        val expected = listOf("FixtureLocalClass")

        sut
            .classesAndInterfaces(includeNested = false, includeLocal = true)
            .map { it.name }
            .shouldBeEqualTo(expected)
    }

    @Test
    fun `file-contains-nested-and-local-classes-and-interfaces includeNested false includeLocal false`() {
        // given
        val sut =
            getSnippetFile("file-contains-nested-and-local-classes-and-interfaces")
                .files
                .first()

        // then
        val expected = emptyList<KoClassDeclaration>()

        sut
            .classesAndInterfaces(includeNested = false, includeLocal = false)
            .map { it.name }
            .shouldBeEqualTo(expected)
    }

    @Test
    fun `count-classes-and-interfaces`() {
        // given
        val sut =
            getSnippetFile("count-classes-and-interfaces")
                .files
                .first()

        // then
        assertSoftly(sut) {
            numClassesAndInterfaces(includeNested = true, includeLocal = true) shouldBeEqualTo 4
            numClassesAndInterfaces(includeNested = true, includeLocal = false) shouldBeEqualTo 3
            numClassesAndInterfaces(includeNested = false, includeLocal = true) shouldBeEqualTo 2
            numClassesAndInterfaces(includeNested = false, includeLocal = false) shouldBeEqualTo 1
            countClassesAndInterfaces(includeNested = false, includeLocal = false) { it.hasPrivateModifier } shouldBeEqualTo 1
            countClassesAndInterfaces { it.hasPrivateModifier } shouldBeEqualTo 3
            countClassesAndInterfaces { it.name == "FixtureClass" && it.hasInternalModifier } shouldBeEqualTo 0
        }
    }

    @Test
    fun `file-has-no-classes-and-interfaces-ignore-case`() {
        // given
        val sut =
            getSnippetFile("file-has-no-classes-and-interfaces-ignore-case")
                .files
                .first()

        // then
        assertSoftly(sut) {
            hasClassOrInterfaceWithName("fixtureclass") shouldBeEqualTo false
            hasClassOrInterfaceWithName("fixtureclass", ignoreCase = true) shouldBeEqualTo false
            hasClassOrInterfaceWithName(listOf("fixtureclass")) shouldBeEqualTo false
            hasClassOrInterfaceWithName(listOf("fixtureclass"), ignoreCase = true) shouldBeEqualTo false
            hasClassOrInterfaceWithName(setOf("fixtureclass")) shouldBeEqualTo false
            hasClassOrInterfaceWithName(setOf("fixtureclass"), ignoreCase = true) shouldBeEqualTo false
            hasClassesAndInterfacesWithAllNames("fixtureclass1", "fixtureinterface") shouldBeEqualTo false
            hasClassesAndInterfacesWithAllNames("fixtureclass1", "fixtureinterface", ignoreCase = true) shouldBeEqualTo false
            hasClassesAndInterfacesWithAllNames(listOf("fixtureclass", "fixtureinterface")) shouldBeEqualTo false
            hasClassesAndInterfacesWithAllNames(listOf("fixtureclass", "fixtureinterface"), ignoreCase = true) shouldBeEqualTo false
            hasClassesAndInterfacesWithAllNames(setOf("fixtureclass", "fixtureinterface")) shouldBeEqualTo false
            hasClassesAndInterfacesWithAllNames(setOf("fixtureclass", "fixtureinterface"), ignoreCase = true) shouldBeEqualTo false
        }
    }

    @Test
    fun `file-has-class-and-interface-ignore-case`() {
        // given
        val sut =
            getSnippetFile("file-has-class-and-interface-ignore-case")
                .files
                .first()

        // then
        assertSoftly(sut) {
            hasClassOrInterfaceWithName("fixtureclass") shouldBeEqualTo false
            hasClassOrInterfaceWithName("fixtureclass", ignoreCase = true) shouldBeEqualTo true
            hasClassOrInterfaceWithName("otherclass") shouldBeEqualTo false
            hasClassOrInterfaceWithName("otherclass", ignoreCase = true) shouldBeEqualTo false
            hasClassOrInterfaceWithName("fixtureclass", "otherName") shouldBeEqualTo false
            hasClassOrInterfaceWithName("fixtureclass", "otherName", ignoreCase = true) shouldBeEqualTo true
            hasClassOrInterfaceWithName(listOf("fixtureclass")) shouldBeEqualTo false
            hasClassOrInterfaceWithName(listOf("fixtureclass"), ignoreCase = true) shouldBeEqualTo true
            hasClassOrInterfaceWithName(listOf("otherclass")) shouldBeEqualTo false
            hasClassOrInterfaceWithName(listOf("otherclass"), ignoreCase = true) shouldBeEqualTo false
            hasClassOrInterfaceWithName(listOf("fixtureclass", "otherName")) shouldBeEqualTo false
            hasClassOrInterfaceWithName(listOf("fixtureclass", "otherName"), ignoreCase = true) shouldBeEqualTo true
            hasClassesAndInterfacesWithAllNames("fixtureclass") shouldBeEqualTo false
            hasClassesAndInterfacesWithAllNames("fixtureclass", ignoreCase = true) shouldBeEqualTo true
            hasClassesAndInterfacesWithAllNames("fixtureclass", "fixtureinterface") shouldBeEqualTo false
            hasClassesAndInterfacesWithAllNames("fixtureclass", "fixtureinterface", ignoreCase = true) shouldBeEqualTo true
            hasClassesAndInterfacesWithAllNames("fixtureclass", "otherclass") shouldBeEqualTo false
            hasClassesAndInterfacesWithAllNames("fixtureclass", "otherclass", ignoreCase = true) shouldBeEqualTo false
            hasClassesAndInterfacesWithAllNames(listOf("fixtureclass")) shouldBeEqualTo false
            hasClassesAndInterfacesWithAllNames(listOf("fixtureclass"), ignoreCase = true) shouldBeEqualTo true
            hasClassesAndInterfacesWithAllNames(listOf("fixtureclass", "fixtureinterface")) shouldBeEqualTo false
            hasClassesAndInterfacesWithAllNames(listOf("fixtureclass", "fixtureinterface"), ignoreCase = true) shouldBeEqualTo true
            hasClassesAndInterfacesWithAllNames(listOf("fixtureclass", "otherclass")) shouldBeEqualTo false
            hasClassesAndInterfacesWithAllNames(listOf("fixtureclass", "otherclass"), ignoreCase = true) shouldBeEqualTo false
        }
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/kofile/snippet/forkoclassandinterfaceprovider/", fileName)
}
