package com.lemonappdev.konsist.core.declaration.kofile

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoFileDeclarationForKoClassAndObjectProviderTest {
    @Test
    fun `file-has-no-classes-and-objects`() {
        // given
        val sut =
            getSnippetFile("file-has-no-classes-and-objects")
                .files
                .first()

        // then
        assertSoftly(sut) {
            classesAndObjects() shouldBeEqualTo emptyList()
            hasClassesOrObjects() shouldBeEqualTo false
            hasClassOrObjectWithName(emptyList()) shouldBeEqualTo false
            hasClassOrObjectWithName(emptySet()) shouldBeEqualTo false
            hasClassesAndObjectsWithAllNames(emptyList()) shouldBeEqualTo false
            hasClassesAndObjectsWithAllNames(emptySet()) shouldBeEqualTo false
            hasClassOrObjectWithName("FixtureClass") shouldBeEqualTo false
            hasClassOrObjectWithName(listOf("FixtureClass")) shouldBeEqualTo false
            hasClassOrObjectWithName(setOf("FixtureClass")) shouldBeEqualTo false
            hasClassOrObjectWithName("FixtureObject") shouldBeEqualTo false
            hasClassOrObjectWithName(listOf("FixtureObject")) shouldBeEqualTo false
            hasClassOrObjectWithName(setOf("FixtureObject")) shouldBeEqualTo false
            hasClassesAndObjectsWithAllNames("FixtureClass", "FixtureObject") shouldBeEqualTo false
            hasClassesAndObjectsWithAllNames(listOf("FixtureClass", "FixtureObject")) shouldBeEqualTo false
            hasClassesAndObjectsWithAllNames(setOf("FixtureClass", "FixtureObject")) shouldBeEqualTo false
            hasClassOrObject { it.name == "FixtureClass" } shouldBeEqualTo false
            hasClassOrObject { it.name == "FixtureObject" } shouldBeEqualTo false
            hasAllClassesAndObjects { it.hasNameStartingWith("Fixture") } shouldBeEqualTo true
        }
    }

    @Test
    fun `file-has-class-and-object`() {
        // given
        val sut =
            getSnippetFile("file-has-class-and-object")
                .files
                .first()

        // then
        assertSoftly(sut) {
            hasClassesOrObjects() shouldBeEqualTo true
            hasClassOrObjectWithName(emptyList()) shouldBeEqualTo true
            hasClassOrObjectWithName(emptySet()) shouldBeEqualTo true
            hasClassesAndObjectsWithAllNames(emptyList()) shouldBeEqualTo true
            hasClassesAndObjectsWithAllNames(emptySet()) shouldBeEqualTo true
            hasClassOrObjectWithName("FixtureClass") shouldBeEqualTo true
            hasClassOrObjectWithName("FixtureObject") shouldBeEqualTo true
            hasClassOrObjectWithName("FixtureClass", "OtherObject") shouldBeEqualTo true
            hasClassOrObjectWithName(listOf("FixtureClass")) shouldBeEqualTo true
            hasClassOrObjectWithName(listOf("FixtureObject")) shouldBeEqualTo true
            hasClassOrObjectWithName(listOf("FixtureClass", "OtherObject")) shouldBeEqualTo true
            hasClassOrObjectWithName(setOf("FixtureClass")) shouldBeEqualTo true
            hasClassOrObjectWithName(setOf("FixtureObject")) shouldBeEqualTo true
            hasClassOrObjectWithName(setOf("FixtureClass", "OtherObject")) shouldBeEqualTo true
            hasClassesAndObjectsWithAllNames("FixtureClass") shouldBeEqualTo true
            hasClassesAndObjectsWithAllNames("FixtureClass", "FixtureObject") shouldBeEqualTo true
            hasClassesAndObjectsWithAllNames("FixtureClass", "OtherObject") shouldBeEqualTo false
            hasClassesAndObjectsWithAllNames(listOf("FixtureClass")) shouldBeEqualTo true
            hasClassesAndObjectsWithAllNames(listOf("FixtureClass", "FixtureObject")) shouldBeEqualTo true
            hasClassesAndObjectsWithAllNames(listOf("FixtureClass", "OtherObject")) shouldBeEqualTo false
            hasClassesAndObjectsWithAllNames(setOf("FixtureClass")) shouldBeEqualTo true
            hasClassesAndObjectsWithAllNames(setOf("FixtureClass", "FixtureObject")) shouldBeEqualTo true
            hasClassesAndObjectsWithAllNames(setOf("FixtureClass", "OtherObject")) shouldBeEqualTo false
            hasClassOrObject { it.name == "FixtureClass" } shouldBeEqualTo true
            hasClassOrObject { it.name == "FixtureObject" } shouldBeEqualTo true
            hasClassOrObject { it.hasNameEndingWith("Class") } shouldBeEqualTo true
            hasClassOrObject { it.hasNameEndingWith("Class") || it.hasNameEndingWith("Object") } shouldBeEqualTo true
            hasAllClassesAndObjects { it.hasNameStartingWith("Fixture") } shouldBeEqualTo true
            hasAllClassesAndObjects { it.hasNameEndingWith("Class") } shouldBeEqualTo false
        }
    }

    @Test
    fun `file-contains-nested-and-local-classes-and-objects includeNested true includeLocal true`() {
        // given
        val sut =
            getSnippetFile("file-contains-nested-and-local-classes-and-objects")
                .files
                .first()

        // then
        val expected =
            listOf(
                "FixtureLocalClass",
                "FixtureClassNestedInsideObject",
                "FixtureObject",
                "FixtureObjectNestedInsideObject",
            )

        sut
            .classesAndObjects(includeNested = true, includeLocal = true)
            .map { it.name }
            .shouldBeEqualTo(expected)
    }

    @Test
    fun `file-contains-nested-and-local-classes-and-objects includeNested true includeLocal false`() {
        // given
        val sut =
            getSnippetFile("file-contains-nested-and-local-classes-and-objects")
                .files
                .first()

        // then
        val expected = listOf("FixtureClassNestedInsideObject", "FixtureObject", "FixtureObjectNestedInsideObject")

        sut
            .classesAndObjects(includeNested = true, includeLocal = false)
            .map { it.name }
            .shouldBeEqualTo(expected)
    }

    @Test
    fun `file-contains-nested-and-local-classes-and-objects includeNested false includeLocal true`() {
        // given
        val sut =
            getSnippetFile("file-contains-nested-and-local-classes-and-objects")
                .files
                .first()

        // then
        val expected = listOf("FixtureLocalClass", "FixtureObject")

        sut
            .classesAndObjects(includeNested = false, includeLocal = true)
            .map { it.name }
            .shouldBeEqualTo(expected)
    }

    @Test
    fun `file-contains-nested-and-local-classes-and-objects includeNested false includeLocal false`() {
        // given
        val sut =
            getSnippetFile("file-contains-nested-and-local-classes-and-objects")
                .files
                .first()

        // then
        val expected = listOf("FixtureObject")

        sut
            .classesAndObjects(includeNested = false, includeLocal = false)
            .map { it.name }
            .shouldBeEqualTo(expected)
    }

    @Test
    fun `count-classes-and-objects`() {
        // given
        val sut =
            getSnippetFile("count-classes-and-objects")
                .files
                .first()

        // then
        assertSoftly(sut) {
            numClassesAndObjects(includeNested = true, includeLocal = true) shouldBeEqualTo 4
            numClassesAndObjects(includeNested = true, includeLocal = false) shouldBeEqualTo 3
            numClassesAndObjects(includeNested = false, includeLocal = true) shouldBeEqualTo 2
            numClassesAndObjects(includeNested = false, includeLocal = false) shouldBeEqualTo 1
            countClassesAndObjects(
                includeNested = false,
                includeLocal = false,
            ) { it.hasPrivateModifier } shouldBeEqualTo 1
            countClassesAndObjects { it.hasPrivateModifier } shouldBeEqualTo 3
            countClassesAndObjects { it.name == "FixtureClass" && it.hasInternalModifier } shouldBeEqualTo 0
        }
    }

    @Test
    fun `file-has-no-classes-and-objects-ignore-case`() {
        // given
        val sut =
            getSnippetFile("file-has-no-classes-and-objects-ignore-case")
                .files
                .first()

        // then
        assertSoftly(sut) {
            hasClassOrObjectWithName("fixtureclass") shouldBeEqualTo false
            hasClassOrObjectWithName("fixtureclass", ignoreCase = true) shouldBeEqualTo false
            hasClassOrObjectWithName(listOf("fixtureclass")) shouldBeEqualTo false
            hasClassOrObjectWithName(listOf("fixtureclass"), ignoreCase = true) shouldBeEqualTo false
            hasClassOrObjectWithName(setOf("fixtureclass")) shouldBeEqualTo false
            hasClassOrObjectWithName(setOf("fixtureclass"), ignoreCase = true) shouldBeEqualTo false
            hasClassesAndObjectsWithAllNames("fixtureclass1", "fixtureobject") shouldBeEqualTo false
            hasClassesAndObjectsWithAllNames("fixtureclass1", "fixtureobject", ignoreCase = true) shouldBeEqualTo false
            hasClassesAndObjectsWithAllNames(listOf("fixtureclass", "fixtureobject")) shouldBeEqualTo false
            hasClassesAndObjectsWithAllNames(listOf("fixtureclass", "fixtureobject"), ignoreCase = true) shouldBeEqualTo false
            hasClassesAndObjectsWithAllNames(setOf("fixtureclass", "fixtureobject")) shouldBeEqualTo false
            hasClassesAndObjectsWithAllNames(setOf("fixtureclass", "fixtureobject"), ignoreCase = true) shouldBeEqualTo false
        }
    }

    @Test
    fun `file-has-class-and-object-ignore-case`() {
        // given
        val sut =
            getSnippetFile("file-has-class-and-object-ignore-case")
                .files
                .first()

        // then
        assertSoftly(sut) {
            hasClassOrObjectWithName("fixtureclass") shouldBeEqualTo false
            hasClassOrObjectWithName("fixtureclass", ignoreCase = true) shouldBeEqualTo true
            hasClassOrObjectWithName("otherclass") shouldBeEqualTo false
            hasClassOrObjectWithName("otherclass", ignoreCase = true) shouldBeEqualTo false
            hasClassOrObjectWithName("fixtureclass", "otherName") shouldBeEqualTo false
            hasClassOrObjectWithName("fixtureclass", "otherName", ignoreCase = true) shouldBeEqualTo true
            hasClassOrObjectWithName(listOf("fixtureclass")) shouldBeEqualTo false
            hasClassOrObjectWithName(listOf("fixtureclass"), ignoreCase = true) shouldBeEqualTo true
            hasClassOrObjectWithName(listOf("otherclass")) shouldBeEqualTo false
            hasClassOrObjectWithName(listOf("otherclass"), ignoreCase = true) shouldBeEqualTo false
            hasClassOrObjectWithName(listOf("fixtureclass", "otherName")) shouldBeEqualTo false
            hasClassOrObjectWithName(listOf("fixtureclass", "otherName"), ignoreCase = true) shouldBeEqualTo true
            hasClassesAndObjectsWithAllNames("fixtureclass") shouldBeEqualTo false
            hasClassesAndObjectsWithAllNames("fixtureclass", ignoreCase = true) shouldBeEqualTo true
            hasClassesAndObjectsWithAllNames("fixtureclass", "fixtureobject") shouldBeEqualTo false
            hasClassesAndObjectsWithAllNames("fixtureclass", "fixtureobject", ignoreCase = true) shouldBeEqualTo true
            hasClassesAndObjectsWithAllNames("fixtureclass", "otherclass") shouldBeEqualTo false
            hasClassesAndObjectsWithAllNames("fixtureclass", "otherclass", ignoreCase = true) shouldBeEqualTo false
            hasClassesAndObjectsWithAllNames(listOf("fixtureclass")) shouldBeEqualTo false
            hasClassesAndObjectsWithAllNames(listOf("fixtureclass"), ignoreCase = true) shouldBeEqualTo true
            hasClassesAndObjectsWithAllNames(listOf("fixtureclass", "fixtureobject")) shouldBeEqualTo false
            hasClassesAndObjectsWithAllNames(listOf("fixtureclass", "fixtureobject"), ignoreCase = true) shouldBeEqualTo true
            hasClassesAndObjectsWithAllNames(listOf("fixtureclass", "otherclass")) shouldBeEqualTo false
            hasClassesAndObjectsWithAllNames(listOf("fixtureclass", "otherclass"), ignoreCase = true) shouldBeEqualTo false
        }
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/kofile/snippet/forkoclassandobjectprovider/", fileName)
}
