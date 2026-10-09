package com.lemonappdev.konsist.core.declaration.koobject

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

@Suppress("detekt.LongMethod")
class KoObjectDeclarationForKoCompanionObjectProviderTest {
    @Test
    fun `object-has-no-direct-companion-object`() {
        // given
        val sut =
            getSnippetFile("object-has-no-direct-companion-object")
                .objects()
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
    fun `object-contains-companion-objects includeNested true`() {
        // given
        val sut =
            getSnippetFile("object-contains-companion-objects")
                .objects()
                .first()

        // then
        val expected = listOf("FixtureCompanionObject", "FixtureNestedCompanionObject")

        sut
            .companionObjects()
            .map { it.name }
            .shouldBeEqualTo(expected)
    }

    @Test
    fun `object-contains-companion-objects includeNested false`() {
        // given
        val sut =
            getSnippetFile("object-contains-companion-objects")
                .objects()
                .first()

        // then
        val expected = emptyList<String>()

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
                .objects()
                .first()

        // then
        assertSoftly(sut) {
            numCompanionObjects() shouldBeEqualTo 2
            numCompanionObjects(includeNested = false) shouldBeEqualTo 0
            countCompanionObjects { it.hasPrivateModifier } shouldBeEqualTo 2
            countCompanionObjects(includeNested = false) { it.hasPrivateModifier } shouldBeEqualTo 0
            countCompanionObjects { it.hasInternalModifier } shouldBeEqualTo 0
        }
    }

    @Test
    fun `object-has-no-direct-companion-objects-ignore-case`() {
        // given
        val sut =
            getSnippetFile("object-has-no-direct-companion-objects-ignore-case")
                .objects()
                .first()

        // then
        assertSoftly(sut) {
            hasCompanionObjectWithName("fixturecompanionobject", includeNested = false) shouldBeEqualTo false
            hasCompanionObjectWithName(
                "fixturecompanionobject",
                ignoreCase = true,
                includeNested = false,
            ) shouldBeEqualTo false
            hasCompanionObjectWithName(listOf("fixturecompanionobject"), includeNested = false) shouldBeEqualTo false
            hasCompanionObjectWithName(
                listOf("fixturecompanionobject"),
                ignoreCase = true,
                includeNested = false,
            ) shouldBeEqualTo false
            hasCompanionObjectWithName(setOf("fixturecompanionobject"), includeNested = false) shouldBeEqualTo false
            hasCompanionObjectWithName(
                setOf("fixturecompanionobject"),
                ignoreCase = true,
                includeNested = false,
            ) shouldBeEqualTo false
            hasCompanionObjectsWithAllNames(
                "fixturecompanionobject1",
                "fixturecompanionobject2",
                includeNested = false,
            ) shouldBeEqualTo false
            hasCompanionObjectsWithAllNames(
                "fixturecompanionobject1",
                "fixturecompanionobject2",
                ignoreCase = true,
                includeNested = false,
            ) shouldBeEqualTo false
            hasCompanionObjectsWithAllNames(
                listOf(
                    "fixturecompanionobject1",
                    "fixturecompanionobject2",
                ),
                includeNested = false,
            ) shouldBeEqualTo false
            hasCompanionObjectsWithAllNames(
                listOf("fixturecompanionobject1", "fixturecompanionobject2"),
                ignoreCase = true,
                includeNested = false,
            ) shouldBeEqualTo false
            hasCompanionObjectsWithAllNames(
                setOf(
                    "fixturecompanionobject1",
                    "fixturecompanionobject2",
                ),
                includeNested = false,
            ) shouldBeEqualTo false
            hasCompanionObjectsWithAllNames(
                setOf("fixturecompanionobject1", "fixturecompanionobject2"),
                ignoreCase = true,
                includeNested = false,
            ) shouldBeEqualTo false
        }
    }

    @Test
    fun `object-has-companion-objects-ignore-case`() {
        // given
        val sut =
            getSnippetFile("object-has-companion-objects-ignore-case")
                .objects()
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
        getSnippetKoScope("core/declaration/koobject/snippet/forkocompanionobjectprovider/", fileName)
}
