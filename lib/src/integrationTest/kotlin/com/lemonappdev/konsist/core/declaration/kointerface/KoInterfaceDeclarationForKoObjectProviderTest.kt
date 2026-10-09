package com.lemonappdev.konsist.core.declaration.kointerface

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoInterfaceDeclarationForKoObjectProviderTest {
    @Test
    fun `interface-has-no-objects`() {
        // given
        val sut =
            getSnippetFile("interface-has-no-objects")
                .interfaces()
                .first()

        // then
        assertSoftly(sut) {
            objects() shouldBeEqualTo emptyList()
            hasObjects() shouldBeEqualTo false
            hasObjectWithName(emptyList()) shouldBeEqualTo false
            hasObjectWithName(emptySet()) shouldBeEqualTo false
            hasObjectsWithAllNames(emptyList()) shouldBeEqualTo false
            hasObjectsWithAllNames(emptySet()) shouldBeEqualTo false
            hasObjectWithName("FixtureObject") shouldBeEqualTo false
            hasObjectWithName(listOf("FixtureObject")) shouldBeEqualTo false
            hasObjectWithName(setOf("FixtureObject")) shouldBeEqualTo false
            hasObjectsWithAllNames("FixtureObject1", "FixtureObject2") shouldBeEqualTo false
            hasObjectsWithAllNames(listOf("FixtureObject1", "FixtureObject2")) shouldBeEqualTo false
            hasObjectsWithAllNames(setOf("FixtureObject1", "FixtureObject2")) shouldBeEqualTo false
            hasObject { it.name == "FixtureObject" } shouldBeEqualTo false
            hasAllObjects { it.hasNameStartingWith("Fixture") } shouldBeEqualTo true
        }
    }

    @Test
    fun `interface-has-two-objects`() {
        // given
        val sut =
            getSnippetFile("interface-has-two-objects")
                .interfaces()
                .first()

        // then
        assertSoftly(sut) {
            hasObjects() shouldBeEqualTo true
            hasObjectWithName(emptyList()) shouldBeEqualTo true
            hasObjectWithName(emptySet()) shouldBeEqualTo true
            hasObjectsWithAllNames(emptyList()) shouldBeEqualTo true
            hasObjectsWithAllNames(emptySet()) shouldBeEqualTo true
            hasObjectWithName("FixtureObject1") shouldBeEqualTo true
            hasObjectWithName("FixtureObject1", "OtherObject") shouldBeEqualTo true
            hasObjectWithName(listOf("FixtureObject1")) shouldBeEqualTo true
            hasObjectWithName(listOf("FixtureObject1", "OtherObject")) shouldBeEqualTo true
            hasObjectWithName(setOf("FixtureObject1")) shouldBeEqualTo true
            hasObjectWithName(setOf("FixtureObject1", "OtherObject")) shouldBeEqualTo true
            hasObjectsWithAllNames("FixtureObject1") shouldBeEqualTo true
            hasObjectsWithAllNames("FixtureObject1", "FixtureObject2") shouldBeEqualTo true
            hasObjectsWithAllNames("FixtureObject1", "OtherObject") shouldBeEqualTo false
            hasObjectsWithAllNames(listOf("FixtureObject1")) shouldBeEqualTo true
            hasObjectsWithAllNames(listOf("FixtureObject1", "FixtureObject2")) shouldBeEqualTo true
            hasObjectsWithAllNames(listOf("FixtureObject1", "OtherObject")) shouldBeEqualTo false
            hasObjectsWithAllNames(setOf("FixtureObject1")) shouldBeEqualTo true
            hasObjectsWithAllNames(setOf("FixtureObject1", "FixtureObject2")) shouldBeEqualTo true
            hasObjectsWithAllNames(setOf("FixtureObject1", "OtherObject")) shouldBeEqualTo false
            hasObject { it.name == "FixtureObject1" } shouldBeEqualTo true
            hasObject { it.hasNameEndingWith("Object1") } shouldBeEqualTo true
            hasAllObjects { it.hasNameStartingWith("Fixture") } shouldBeEqualTo true
            hasAllObjects { it.hasNameEndingWith("Class1") } shouldBeEqualTo false
        }
    }

    @Test
    fun `interface-contains-objects includeNested true`() {
        // given
        val sut =
            getSnippetFile("interface-contains-objects")
                .interfaces()
                .first()

        // then
        val expected = listOf("FixtureObject", "FixtureNestedObject")

        sut
            .objects(includeNested = true)
            .map { it.name }
            .shouldBeEqualTo(expected)
    }

    @Test
    fun `interface-contains-objects includeNested false`() {
        // given
        val sut =
            getSnippetFile("interface-contains-objects")
                .interfaces()
                .first()

        // then
        val expected = listOf("FixtureObject")

        sut
            .objects(includeNested = false)
            .map { it.name }
            .shouldBeEqualTo(expected)
    }

    @Test
    fun `count-objects`() {
        // given
        val sut =
            getSnippetFile("count-objects")
                .interfaces()
                .first()

        // then
        assertSoftly(sut) {
            numObjects(includeNested = true) shouldBeEqualTo 2
            numObjects(includeNested = false) shouldBeEqualTo 1
            countObjects { it.hasPrivateModifier } shouldBeEqualTo 2
            countObjects(includeNested = false) { it.hasPrivateModifier } shouldBeEqualTo 1
            countObjects { it.hasInternalModifier } shouldBeEqualTo 0
        }
    }

    @Test
    fun `interface-has-no-objects-ignore-case`() {
        // given
        val sut =
            getSnippetFile("interface-has-no-objects-ignore-case")
                .interfaces()
                .first()

        // then
        assertSoftly(sut) {
            hasObjectWithName("fixtureobject") shouldBeEqualTo false
            hasObjectWithName("fixtureobject", ignoreCase = true) shouldBeEqualTo false
            hasObjectWithName(listOf("fixtureobject")) shouldBeEqualTo false
            hasObjectWithName(listOf("fixtureobject"), ignoreCase = true) shouldBeEqualTo false
            hasObjectWithName(setOf("fixtureobject")) shouldBeEqualTo false
            hasObjectWithName(setOf("fixtureobject"), ignoreCase = true) shouldBeEqualTo false
            hasObjectsWithAllNames("fixtureobject1", "fixtureobject2") shouldBeEqualTo false
            hasObjectsWithAllNames("fixtureobject1", "fixtureobject2", ignoreCase = true) shouldBeEqualTo false
            hasObjectsWithAllNames(listOf("fixtureobject1", "fixtureobject2")) shouldBeEqualTo false
            hasObjectsWithAllNames(listOf("fixtureobject1", "fixtureobject2"), ignoreCase = true) shouldBeEqualTo false
            hasObjectsWithAllNames(setOf("fixtureobject1", "fixtureobject2")) shouldBeEqualTo false
            hasObjectsWithAllNames(setOf("fixtureobject1", "fixtureobject2"), ignoreCase = true) shouldBeEqualTo false
        }
    }

    @Test
    fun `interface-has-objects-ignore-case`() {
        // given
        val sut =
            getSnippetFile("interface-has-objects-ignore-case")
                .interfaces()
                .first()

        // then
        assertSoftly(sut) {
            hasObjectWithName("fixtureobject1") shouldBeEqualTo false
            hasObjectWithName("fixtureobject1", ignoreCase = true) shouldBeEqualTo true
            hasObjectWithName("otherobject") shouldBeEqualTo false
            hasObjectWithName("otherobject", ignoreCase = true) shouldBeEqualTo false
            hasObjectWithName("fixtureobject1", "otherName") shouldBeEqualTo false
            hasObjectWithName("fixtureobject1", "otherName", ignoreCase = true) shouldBeEqualTo true
            hasObjectWithName(listOf("fixtureobject1")) shouldBeEqualTo false
            hasObjectWithName(listOf("fixtureobject1"), ignoreCase = true) shouldBeEqualTo true
            hasObjectWithName(listOf("otherobject")) shouldBeEqualTo false
            hasObjectWithName(listOf("otherobject"), ignoreCase = true) shouldBeEqualTo false
            hasObjectWithName(listOf("fixtureobject1", "otherName")) shouldBeEqualTo false
            hasObjectWithName(listOf("fixtureobject1", "otherName"), ignoreCase = true) shouldBeEqualTo true
            hasObjectsWithAllNames("fixtureobject1") shouldBeEqualTo false
            hasObjectsWithAllNames("fixtureobject1", ignoreCase = true) shouldBeEqualTo true
            hasObjectsWithAllNames("fixtureobject1", "fixtureobject2") shouldBeEqualTo false
            hasObjectsWithAllNames("fixtureobject1", "fixtureobject2", ignoreCase = true) shouldBeEqualTo true
            hasObjectsWithAllNames("fixtureobject1", "otherobject") shouldBeEqualTo false
            hasObjectsWithAllNames("fixtureobject1", "otherobject", ignoreCase = true) shouldBeEqualTo false
            hasObjectsWithAllNames(listOf("fixtureobject1")) shouldBeEqualTo false
            hasObjectsWithAllNames(listOf("fixtureobject1"), ignoreCase = true) shouldBeEqualTo true
            hasObjectsWithAllNames(listOf("fixtureobject1", "fixtureobject2")) shouldBeEqualTo false
            hasObjectsWithAllNames(listOf("fixtureobject1", "fixtureobject2"), ignoreCase = true) shouldBeEqualTo true
            hasObjectsWithAllNames(listOf("fixtureobject1", "otherobject")) shouldBeEqualTo false
            hasObjectsWithAllNames(listOf("fixtureobject1", "otherobject"), ignoreCase = true) shouldBeEqualTo false
        }
    }

    private fun getSnippetFile(fileName: String) = getSnippetKoScope("core/declaration/kointerface/snippet/forkoobjectprovider/", fileName)
}
