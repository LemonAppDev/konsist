package com.lemonappdev.konsist.core.declaration.kointerface

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

@Suppress("detekt.LongMethod")
class KoInterfaceDeclarationForKoCompanionObjectProviderTest {
    @Test
    fun `interface-has-no-direct-companion-object`() {
        // given
        val sut =
            getSnippetFile("interface-has-no-direct-companion-object")
                .interfaces()
                .first()

        // then
        assertSoftly(sut) {
            companionObject shouldBeEqualTo null
            hasCompanionObject() shouldBeEqualTo false
            hasCompanionObject(includeNested = false) { it.name == "FixtureCompanionObject" } shouldBeEqualTo false
            hasCompanionObjectWithName("FixtureCompanionObject", includeNested = false) shouldBeEqualTo false
            hasCompanionObjectWithName(listOf("FixtureCompanionObject"), includeNested = false) shouldBeEqualTo false
            hasCompanionObjectWithName(setOf("FixtureCompanionObject"), includeNested = false) shouldBeEqualTo false
            hasCompanionObjectsWithAllNames(
                "FixtureCompanionObject1",
                "FixtureCompanionObject2",
                includeNested = false,
            ) shouldBeEqualTo false
            hasCompanionObjectsWithAllNames(
                listOf(
                    "FixtureCompanionObject1",
                    "FixtureCompanionObject2",
                ),
                includeNested = false,
            ) shouldBeEqualTo false
            hasCompanionObjectsWithAllNames(
                setOf(
                    "FixtureCompanionObject1",
                    "FixtureCompanionObject2",
                ),
                includeNested = false,
            ) shouldBeEqualTo false
        }
    }

    @Test
    fun `interface-has-direct-companion-object-with-default-name`() {
        // given
        val sut =
            getSnippetFile("interface-has-direct-companion-object-with-default-name")
                .interfaces()
                .first()

        // then
        assertSoftly(sut) {
            hasCompanionObject() shouldBeEqualTo true
            hasCompanionObject(includeNested = false) { it.name == "Companion" } shouldBeEqualTo true
            hasCompanionObject(includeNested = false) { it.hasNameEndingWith("nion") } shouldBeEqualTo true
            hasCompanionObjectWithName("Companion", includeNested = false) shouldBeEqualTo true
            hasCompanionObjectWithName("OtherCompanionObject", includeNested = false) shouldBeEqualTo false
            hasCompanionObjectWithName(
                "Companion",
                "OtherCompanionObject",
                includeNested = false,
            ) shouldBeEqualTo true
            hasCompanionObjectWithName(listOf("Companion"), includeNested = false) shouldBeEqualTo true
            hasCompanionObjectWithName(listOf("OtherCompanionObject"), includeNested = false) shouldBeEqualTo false
            hasCompanionObjectWithName(
                listOf("Companion", "OtherCompanionObject"),
                includeNested = false,
            ) shouldBeEqualTo true
            hasCompanionObjectsWithAllNames("Companion", includeNested = false) shouldBeEqualTo true
            hasCompanionObjectsWithAllNames(
                "Companion",
                "FixtureCompanionObject2",
                includeNested = false,
            ) shouldBeEqualTo false
            hasCompanionObjectsWithAllNames(
                listOf("Companion"),
                includeNested = false,
            ) shouldBeEqualTo true
            hasCompanionObjectsWithAllNames(
                listOf("Companion", "FixtureCompanionObject2"),
                includeNested = false,
            ) shouldBeEqualTo false
        }
    }

    @Test
    fun `interface-has-direct-companion-object-with-given-name`() {
        // given
        val sut =
            getSnippetFile("interface-has-direct-companion-object-with-given-name")
                .interfaces()
                .first()

        // then
        assertSoftly(sut) {
            hasCompanionObject() shouldBeEqualTo true
            hasCompanionObject(includeNested = false) { it.name == "FixtureCompanionObject1" } shouldBeEqualTo true
            hasCompanionObject(includeNested = false) { it.hasNameEndingWith("CompanionObject1") } shouldBeEqualTo true
            hasCompanionObjectWithName("FixtureCompanionObject1", includeNested = false) shouldBeEqualTo true
            hasCompanionObjectWithName("OtherCompanionObject", includeNested = false) shouldBeEqualTo false
            hasCompanionObjectWithName(
                "FixtureCompanionObject1",
                "OtherCompanionObject",
                includeNested = false,
            ) shouldBeEqualTo true
            hasCompanionObjectWithName(listOf("FixtureCompanionObject1"), includeNested = false) shouldBeEqualTo true
            hasCompanionObjectWithName(listOf("OtherCompanionObject"), includeNested = false) shouldBeEqualTo false
            hasCompanionObjectWithName(
                listOf("FixtureCompanionObject1", "OtherCompanionObject"),
                includeNested = false,
            ) shouldBeEqualTo true
            hasCompanionObjectsWithAllNames("FixtureCompanionObject1", includeNested = false) shouldBeEqualTo true
            hasCompanionObjectsWithAllNames(
                "FixtureCompanionObject1",
                "FixtureCompanionObject2",
                includeNested = false,
            ) shouldBeEqualTo false
            hasCompanionObjectsWithAllNames(
                listOf("FixtureCompanionObject1"),
                includeNested = false,
            ) shouldBeEqualTo true
            hasCompanionObjectsWithAllNames(
                listOf("FixtureCompanionObject1", "FixtureCompanionObject2"),
                includeNested = false,
            ) shouldBeEqualTo false
        }
    }

    @Test
    fun `interface-contains-companion-objects includeNested true`() {
        // given
        val sut =
            getSnippetFile("interface-contains-companion-objects")
                .interfaces()
                .first()

        // then
        val expected = listOf("FixtureCompanionObject", "FixtureNestedCompanionObject")

        sut
            .companionObjects(includeNested = true)
            .map { it.name }
            .shouldBeEqualTo(expected)
    }

    @Test
    fun `interface-contains-companion-objects includeNested false`() {
        // given
        val sut =
            getSnippetFile("interface-contains-companion-objects")
                .interfaces()
                .first()

        // then
        val expected = listOf("FixtureCompanionObject")

        sut
            .companionObjects(includeNested = false)
            .map { it.name }
            .shouldBeEqualTo(expected)
    }

    @Test
    fun `count-companion-objects`() {
        // given
        val sut =
            getSnippetFile("count-companion-objects")
                .interfaces()
                .first()

        // then
        assertSoftly(sut) {
            numCompanionObjects(includeNested = true) shouldBeEqualTo 2
            numCompanionObjects(includeNested = false) shouldBeEqualTo 1
            countCompanionObjects { it.hasPrivateModifier } shouldBeEqualTo 2
            countCompanionObjects(includeNested = false) { it.hasPrivateModifier } shouldBeEqualTo 1
            countCompanionObjects { it.hasInternalModifier } shouldBeEqualTo 0
        }
    }

    @Test
    fun `interface-has-no-companion-objects-ignore-case`() {
        // given
        val sut =
            getSnippetFile("interface-has-no-companion-objects-ignore-case")
                .interfaces()
                .first()

        // then
        assertSoftly(sut) {
            hasCompanionObjectWithName("fixturecompanionobject") shouldBeEqualTo false
            hasCompanionObjectWithName("fixturecompanionobject", ignoreCase = true) shouldBeEqualTo false
            hasCompanionObjectWithName(listOf("fixturecompanionobject")) shouldBeEqualTo false
            hasCompanionObjectWithName(listOf("fixturecompanionobject"), ignoreCase = true) shouldBeEqualTo false
            hasCompanionObjectWithName(setOf("fixturecompanionobject")) shouldBeEqualTo false
            hasCompanionObjectWithName(setOf("fixturecompanionobject"), ignoreCase = true) shouldBeEqualTo false
            hasCompanionObjectsWithAllNames("fixturecompanionobject1", "fixturecompanionobject2") shouldBeEqualTo false
            hasCompanionObjectsWithAllNames(
                "fixturecompanionobject1",
                "fixturecompanionobject2",
                ignoreCase = true,
            ) shouldBeEqualTo false
            hasCompanionObjectsWithAllNames(
                listOf(
                    "fixturecompanionobject1",
                    "fixturecompanionobject2",
                ),
            ) shouldBeEqualTo false
            hasCompanionObjectsWithAllNames(
                listOf("fixturecompanionobject1", "fixturecompanionobject2"),
                ignoreCase = true,
            ) shouldBeEqualTo false
            hasCompanionObjectsWithAllNames(
                setOf(
                    "fixturecompanionobject1",
                    "fixturecompanionobject2",
                ),
            ) shouldBeEqualTo false
            hasCompanionObjectsWithAllNames(
                setOf("fixturecompanionobject1", "fixturecompanionobject2"),
                ignoreCase = true,
            ) shouldBeEqualTo false
        }
    }

    @Test
    fun `interface-has-companion-objects-ignore-case`() {
        // given
        val sut =
            getSnippetFile("interface-has-companion-objects-ignore-case")
                .interfaces()
                .first()

        // then
        assertSoftly(sut) {
            hasCompanionObjectWithName("fixturecompanionobject1") shouldBeEqualTo false
            hasCompanionObjectWithName(
                "fixturecompanionobject1",
                ignoreCase = true,
            ) shouldBeEqualTo true
            hasCompanionObjectWithName("othercompanionobject") shouldBeEqualTo false
            hasCompanionObjectWithName(
                "othercompanionobject",
                ignoreCase = true,
            ) shouldBeEqualTo false
            hasCompanionObjectWithName(
                "fixturecompanionobject1",
                "OtherCompanionObject",
            ) shouldBeEqualTo false
            hasCompanionObjectWithName(
                "fixturecompanionobject1",
                "OtherCompanionObject",
                ignoreCase = true,
            ) shouldBeEqualTo true
            hasCompanionObjectWithName(listOf("fixturecompanionobject1")) shouldBeEqualTo false
            hasCompanionObjectWithName(
                listOf("fixturecompanionobject1"),
                ignoreCase = true,
            ) shouldBeEqualTo true
            hasCompanionObjectWithName(listOf("othercompanionobject")) shouldBeEqualTo false
            hasCompanionObjectWithName(
                listOf("othercompanionobject"),
                ignoreCase = true,
            ) shouldBeEqualTo false
            hasCompanionObjectWithName(
                listOf("fixturecompanionobject1", "OtherCompanionObject"),
            ) shouldBeEqualTo false
            hasCompanionObjectWithName(
                listOf("fixturecompanionobject1", "OtherCompanionObject"),
                ignoreCase = true,
            ) shouldBeEqualTo true
            hasCompanionObjectsWithAllNames("fixturecompanionobject1") shouldBeEqualTo false
            hasCompanionObjectsWithAllNames(
                "fixturecompanionobject1",
                ignoreCase = true,
            ) shouldBeEqualTo true
            hasCompanionObjectsWithAllNames(
                "fixturecompanionobject1",
                "fixturecompanionobject2",
            ) shouldBeEqualTo false
            hasCompanionObjectsWithAllNames(
                "fixturecompanionobject1",
                "fixturecompanionobject2",
                ignoreCase = true,
            ) shouldBeEqualTo true
            hasCompanionObjectsWithAllNames(
                "fixturecompanionobject1",
                "othercompanionobject",
            ) shouldBeEqualTo false
            hasCompanionObjectsWithAllNames(
                "fixturecompanionobject1",
                "othercompanionobject",
                ignoreCase = true,
            ) shouldBeEqualTo false
            hasCompanionObjectsWithAllNames(
                listOf("fixturecompanionobject1"),
            ) shouldBeEqualTo false
            hasCompanionObjectsWithAllNames(
                listOf("fixturecompanionobject1"),
                ignoreCase = true,
            ) shouldBeEqualTo true
            hasCompanionObjectsWithAllNames(
                listOf("fixturecompanionobject1", "fixturecompanionobject2"),
            ) shouldBeEqualTo false
            hasCompanionObjectsWithAllNames(
                listOf("fixturecompanionobject1", "fixturecompanionobject2"),
                ignoreCase = true,
            ) shouldBeEqualTo true
            hasCompanionObjectsWithAllNames(
                listOf("fixturecompanionobject1", "othercompanionobject"),
            ) shouldBeEqualTo false
            hasCompanionObjectsWithAllNames(
                listOf("fixturecompanionobject1", "othercompanionobject"),
                ignoreCase = true,
            ) shouldBeEqualTo false
        }
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/kointerface/snippet/forkocompanionobjectprovider/", fileName)
}
