package com.lemonappdev.konsist.core.declaration.koobject

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoObjectDeclarationForKoClassProviderTest {
    @Test
    fun `object-has-no-classes`() {
        // given
        val sut =
            getSnippetFile("object-has-no-classes")
                .objects()
                .first()

        // then
        assertSoftly(sut) {
            classes() shouldBeEqualTo emptyList()
            hasClasses() shouldBeEqualTo false
            hasClassWithName(emptyList()) shouldBeEqualTo false
            hasClassWithName(emptySet()) shouldBeEqualTo false
            hasClassesWithAllNames(emptyList()) shouldBeEqualTo false
            hasClassesWithAllNames(emptySet()) shouldBeEqualTo false
            hasClassWithName("FixtureClass") shouldBeEqualTo false
            hasClassWithName(listOf("FixtureClass")) shouldBeEqualTo false
            hasClassWithName(setOf("FixtureClass")) shouldBeEqualTo false
            hasClassesWithAllNames("FixtureClass1", "FixtureClass2") shouldBeEqualTo false
            hasClassesWithAllNames(listOf("FixtureClass1", "FixtureClass2")) shouldBeEqualTo false
            hasClassesWithAllNames(setOf("FixtureClass1", "FixtureClass2")) shouldBeEqualTo false
            hasClass { it.name == "FixtureClass" } shouldBeEqualTo false
            hasAllClasses { it.hasNameStartingWith("Fixture") } shouldBeEqualTo true
        }
    }

    @Test
    fun `object-has-two-classes`() {
        // given
        val sut =
            getSnippetFile("object-has-two-classes")
                .objects()
                .first()

        // then
        assertSoftly(sut) {
            hasClasses() shouldBeEqualTo true
            hasClassWithName(emptyList()) shouldBeEqualTo true
            hasClassWithName(emptySet()) shouldBeEqualTo true
            hasClassesWithAllNames(emptyList()) shouldBeEqualTo true
            hasClassesWithAllNames(emptySet()) shouldBeEqualTo true
            hasClassWithName("FixtureClass1") shouldBeEqualTo true
            hasClassWithName("FixtureClass1", "OtherClass") shouldBeEqualTo true
            hasClassWithName(listOf("FixtureClass1")) shouldBeEqualTo true
            hasClassWithName(listOf("FixtureClass1", "OtherClass")) shouldBeEqualTo true
            hasClassWithName(setOf("FixtureClass1")) shouldBeEqualTo true
            hasClassWithName(setOf("FixtureClass1", "OtherClass")) shouldBeEqualTo true
            hasClassesWithAllNames("FixtureClass1") shouldBeEqualTo true
            hasClassesWithAllNames("FixtureClass1", "FixtureClass2") shouldBeEqualTo true
            hasClassesWithAllNames("FixtureClass1", "OtherClass") shouldBeEqualTo false
            hasClassesWithAllNames(listOf("FixtureClass1")) shouldBeEqualTo true
            hasClassesWithAllNames(listOf("FixtureClass1", "FixtureClass2")) shouldBeEqualTo true
            hasClassesWithAllNames(listOf("FixtureClass1", "OtherClass")) shouldBeEqualTo false
            hasClassesWithAllNames(setOf("FixtureClass1")) shouldBeEqualTo true
            hasClassesWithAllNames(setOf("FixtureClass1", "FixtureClass2")) shouldBeEqualTo true
            hasClassesWithAllNames(setOf("FixtureClass1", "OtherClass")) shouldBeEqualTo false
            hasClass { it.name == "FixtureClass1" } shouldBeEqualTo true
            hasClass { it.hasNameEndingWith("Class1") } shouldBeEqualTo true
            hasAllClasses { it.hasNameStartingWith("Fixture") } shouldBeEqualTo true
            hasAllClasses { it.hasNameEndingWith("Class1") } shouldBeEqualTo false
        }
    }

    @Test
    fun `object-contains-nested-and-local-classes includeNested true includeLocal true`() {
        // given
        val sut =
            getSnippetFile("object-contains-nested-and-local-classes")
                .objects()
                .first()

        // then
        val expected = listOf("FixtureNestedClass", "FixtureLocalClass")

        sut
            .classes(includeNested = true, includeLocal = true)
            .map { it.name }
            .shouldBeEqualTo(expected)
    }

    @Test
    fun `object-contains-nested-and-local-classes includeNested true includeLocal false`() {
        // given
        val sut =
            getSnippetFile("object-contains-nested-and-local-classes")
                .objects()
                .first()

        // then
        val expected = listOf("FixtureNestedClass")

        sut
            .classes(includeNested = true, includeLocal = false)
            .map { it.name }
            .shouldBeEqualTo(expected)
    }

    @Test
    fun `object-contains-nested-and-local-classes includeNested false includeLocal true`() {
        // given
        val sut =
            getSnippetFile("object-contains-nested-and-local-classes")
                .objects()
                .first()

        // then
        val expected = listOf("FixtureLocalClass")

        sut
            .classes(includeNested = false, includeLocal = true)
            .map { it.name }
            .shouldBeEqualTo(expected)
    }

    @Test
    fun `object-contains-nested-and-local-classes includeNested false includeLocal false`() {
        // given
        val sut =
            getSnippetFile("object-contains-nested-and-local-classes")
                .objects()
                .first()

        // then
        val expected = emptyList<String>()

        sut
            .classes(includeNested = false, includeLocal = false)
            .map { it.name }
            .shouldBeEqualTo(expected)
    }

    @Test
    fun `count-classes`() {
        // given
        val sut =
            getSnippetFile("count-classes")
                .objects()
                .first()

        // then
        assertSoftly(sut) {
            numClasses(includeNested = true, includeLocal = true) shouldBeEqualTo 3
            numClasses(includeNested = true, includeLocal = false) shouldBeEqualTo 2
            numClasses(includeNested = false, includeLocal = true) shouldBeEqualTo 2
            numClasses(includeNested = false, includeLocal = false) shouldBeEqualTo 1
            countClasses(includeNested = false, includeLocal = false) { it.hasPrivateModifier } shouldBeEqualTo 1
            countClasses { it.hasPrivateModifier } shouldBeEqualTo 2
            countClasses { it.name == "FixtureClass" && it.hasInternalModifier } shouldBeEqualTo 0
        }
    }

    @Test
    fun `object-has-no-classes-ignore-case`() {
        // given
        val sut =
            getSnippetFile("object-has-no-classes-ignore-case")
                .objects()
                .first()

        // then
        assertSoftly(sut) {
            hasClassWithName("fixtureclass") shouldBeEqualTo false
            hasClassWithName("fixtureclass", ignoreCase = true) shouldBeEqualTo false
            hasClassWithName(listOf("fixtureclass")) shouldBeEqualTo false
            hasClassWithName(listOf("fixtureclass"), ignoreCase = true) shouldBeEqualTo false
            hasClassWithName(setOf("fixtureclass")) shouldBeEqualTo false
            hasClassWithName(setOf("fixtureclass"), ignoreCase = true) shouldBeEqualTo false
            hasClassesWithAllNames("fixtureclass1", "fixtureclass2") shouldBeEqualTo false
            hasClassesWithAllNames("fixtureclass1", "fixtureclass2", ignoreCase = true) shouldBeEqualTo false
            hasClassesWithAllNames(listOf("fixtureclass1", "fixtureclass2")) shouldBeEqualTo false
            hasClassesWithAllNames(listOf("fixtureclass1", "fixtureclass2"), ignoreCase = true) shouldBeEqualTo false
            hasClassesWithAllNames(setOf("fixtureclass1", "fixtureclass2")) shouldBeEqualTo false
            hasClassesWithAllNames(setOf("fixtureclass1", "fixtureclass2"), ignoreCase = true) shouldBeEqualTo false
        }
    }

    @Test
    fun `object-has-classes-ignore-case`() {
        // given
        val sut =
            getSnippetFile("object-has-classes-ignore-case")
                .objects()
                .first()

        // then
        assertSoftly(sut) {
            hasClassWithName("fixtureclass1") shouldBeEqualTo false
            hasClassWithName("fixtureclass1", ignoreCase = true) shouldBeEqualTo true
            hasClassWithName("otherclass") shouldBeEqualTo false
            hasClassWithName("otherclass", ignoreCase = true) shouldBeEqualTo false
            hasClassWithName("fixtureclass1", "otherName") shouldBeEqualTo false
            hasClassWithName("fixtureclass1", "otherName", ignoreCase = true) shouldBeEqualTo true
            hasClassWithName(listOf("fixtureclass1")) shouldBeEqualTo false
            hasClassWithName(listOf("fixtureclass1"), ignoreCase = true) shouldBeEqualTo true
            hasClassWithName(listOf("otherclass")) shouldBeEqualTo false
            hasClassWithName(listOf("otherclass"), ignoreCase = true) shouldBeEqualTo false
            hasClassWithName(listOf("fixtureclass1", "otherName")) shouldBeEqualTo false
            hasClassWithName(listOf("fixtureclass1", "otherName"), ignoreCase = true) shouldBeEqualTo true
            hasClassesWithAllNames("fixtureclass1") shouldBeEqualTo false
            hasClassesWithAllNames("fixtureclass1", ignoreCase = true) shouldBeEqualTo true
            hasClassesWithAllNames("fixtureclass1", "fixtureclass2") shouldBeEqualTo false
            hasClassesWithAllNames("fixtureclass1", "fixtureclass2", ignoreCase = true) shouldBeEqualTo true
            hasClassesWithAllNames("fixtureclass1", "otherclass") shouldBeEqualTo false
            hasClassesWithAllNames("fixtureclass1", "otherclass", ignoreCase = true) shouldBeEqualTo false
            hasClassesWithAllNames(listOf("fixtureclass1")) shouldBeEqualTo false
            hasClassesWithAllNames(listOf("fixtureclass1"), ignoreCase = true) shouldBeEqualTo true
            hasClassesWithAllNames(listOf("fixtureclass1", "fixtureclass2")) shouldBeEqualTo false
            hasClassesWithAllNames(listOf("fixtureclass1", "fixtureclass2"), ignoreCase = true) shouldBeEqualTo true
            hasClassesWithAllNames(listOf("fixtureclass1", "otherclass")) shouldBeEqualTo false
            hasClassesWithAllNames(listOf("fixtureclass1", "otherclass"), ignoreCase = true) shouldBeEqualTo false
        }
    }

    private fun getSnippetFile(fileName: String) = getSnippetKoScope("core/declaration/koobject/snippet/forkoclassprovider/", fileName)
}
