package com.lemonappdev.konsist.core.declaration.kosetter

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoSetterDeclarationForKoLocalClassProviderTest {
    @Test
    fun `setter-contains-no-local-classes`() {
        // given
        val sut =
            getSnippetFile("setter-contains-no-local-classes")
                .properties()
                .first()
                .setter

        // then
        assertSoftly(sut) {
            it?.localClasses shouldBeEqualTo emptyList()
            it?.numLocalClasses shouldBeEqualTo 0
            it?.countLocalClasses { it.name == "FixtureClass" } shouldBeEqualTo 0
            it?.hasLocalClasses() shouldBeEqualTo false
            it?.hasLocalClassWithName(emptyList()) shouldBeEqualTo false
            it?.hasLocalClassWithName(emptySet()) shouldBeEqualTo false
            it?.hasLocalClassesWithAllNames(emptyList()) shouldBeEqualTo false
            it?.hasLocalClassesWithAllNames(emptySet()) shouldBeEqualTo false
            it?.hasLocalClassWithName("FixtureClass") shouldBeEqualTo false
            it?.hasLocalClassWithName(listOf("FixtureClass")) shouldBeEqualTo false
            it?.hasLocalClassWithName(setOf("FixtureClass")) shouldBeEqualTo false
            it?.hasLocalClassesWithAllNames("FixtureClass1", "FixtureClass2") shouldBeEqualTo false
            it?.hasLocalClassesWithAllNames(listOf("FixtureClass1", "FixtureClass2")) shouldBeEqualTo false
            it?.hasLocalClassesWithAllNames(setOf("FixtureClass1", "FixtureClass2")) shouldBeEqualTo false
            it?.hasLocalClass { it.name == "FixtureClass" } shouldBeEqualTo false
            it?.hasAllLocalClasses { it.name == "FixtureClass" } shouldBeEqualTo true
        }
    }

    @Test
    fun `setter-contains-local-class`() {
        // given
        val sut =
            getSnippetFile("setter-contains-local-class")
                .properties()
                .first()
                .setter

        // then
        assertSoftly(sut) {
            it?.localClasses?.map { localClass -> localClass.name } shouldBeEqualTo
                it?.localClasses?.map { it.name } shouldBeEqualTo listOf("FixtureClass1", "FixtureClass2")
            it?.numLocalClasses shouldBeEqualTo 2
            it?.countLocalClasses { it.name == "FixtureClass1" } shouldBeEqualTo 1
            it?.hasLocalClasses() shouldBeEqualTo true
            it?.hasLocalClassWithName(emptyList()) shouldBeEqualTo true
            it?.hasLocalClassWithName(emptySet()) shouldBeEqualTo true
            it?.hasLocalClassesWithAllNames(emptyList()) shouldBeEqualTo true
            it?.hasLocalClassesWithAllNames(emptySet()) shouldBeEqualTo true
            it?.hasLocalClassWithName("FixtureClass1") shouldBeEqualTo true
            it?.hasLocalClassWithName("OtherLocalClass") shouldBeEqualTo false
            it?.hasLocalClassWithName("FixtureClass1", "OtherLocalClass") shouldBeEqualTo true
            it?.hasLocalClassWithName(listOf("FixtureClass1")) shouldBeEqualTo true
            it?.hasLocalClassWithName(listOf("OtherLocalClass")) shouldBeEqualTo false
            it?.hasLocalClassWithName(listOf("FixtureClass1", "OtherLocalClass")) shouldBeEqualTo true
            it?.hasLocalClassWithName(setOf("FixtureClass1")) shouldBeEqualTo true
            it?.hasLocalClassWithName(setOf("OtherLocalClass")) shouldBeEqualTo false
            it?.hasLocalClassWithName(setOf("FixtureClass1", "OtherLocalClass")) shouldBeEqualTo true
            it?.hasLocalClassesWithAllNames("FixtureClass1") shouldBeEqualTo true
            it?.hasLocalClassesWithAllNames("FixtureClass1", "FixtureClass2") shouldBeEqualTo true
            it?.hasLocalClassesWithAllNames("FixtureClass1", "OtherLocalClass") shouldBeEqualTo false
            it?.hasLocalClassesWithAllNames(listOf("FixtureClass1")) shouldBeEqualTo true
            it?.hasLocalClassesWithAllNames(listOf("FixtureClass1", "FixtureClass2")) shouldBeEqualTo true
            it?.hasLocalClassesWithAllNames(listOf("FixtureClass1", "OtherLocalClass")) shouldBeEqualTo false
            it?.hasLocalClassesWithAllNames(setOf("FixtureClass1")) shouldBeEqualTo true
            it?.hasLocalClassesWithAllNames(setOf("FixtureClass1", "FixtureClass2")) shouldBeEqualTo true
            it?.hasLocalClassesWithAllNames(setOf("FixtureClass1", "OtherLocalClass")) shouldBeEqualTo false
            it?.hasLocalClass { it.name == "FixtureClass1" } shouldBeEqualTo true
            it?.hasLocalClass { it.name == "OtherLocalClass" } shouldBeEqualTo false
            it?.hasAllLocalClasses { it.name.endsWith("2") || it.name == "FixtureClass1" } shouldBeEqualTo true
            it?.hasAllLocalClasses { it.name.endsWith("2") } shouldBeEqualTo false
        }
    }

    @Test
    fun `setter-contains-no-local-classes-ignore-case`() {
        // given
        val sut =
            getSnippetFile("setter-contains-no-local-classes-ignore-case")
                .properties()
                .first()
                .setter

        // then
        assertSoftly(sut) {
            it?.hasLocalClassWithName("fixtureclass") shouldBeEqualTo false
            it?.hasLocalClassWithName("fixtureclass", ignoreCase = true) shouldBeEqualTo false
            it?.hasLocalClassWithName(listOf("fixtureclass")) shouldBeEqualTo false
            it?.hasLocalClassWithName(listOf("fixtureclass"), ignoreCase = true) shouldBeEqualTo false
            it?.hasLocalClassWithName(setOf("fixtureclass")) shouldBeEqualTo false
            it?.hasLocalClassWithName(setOf("fixtureclass"), ignoreCase = true) shouldBeEqualTo false
            it?.hasLocalClassesWithAllNames("fixtureclass1", "fixtureclass2") shouldBeEqualTo false
            it?.hasLocalClassesWithAllNames("fixtureclass1", "fixtureclass2", ignoreCase = true) shouldBeEqualTo false
            it?.hasLocalClassesWithAllNames(listOf("fixtureclass1", "fixtureclass2")) shouldBeEqualTo false
            it?.hasLocalClassesWithAllNames(listOf("fixtureclass1", "fixtureclass2"), ignoreCase = true) shouldBeEqualTo false
            it?.hasLocalClassesWithAllNames(setOf("fixtureclass1", "fixtureclass2")) shouldBeEqualTo false
            it?.hasLocalClassesWithAllNames(setOf("fixtureclass1", "fixtureclass2"), ignoreCase = true) shouldBeEqualTo false
        }
    }

    @Test
    fun `setter-contains-local-class-ignore-case`() {
        // given
        val sut =
            getSnippetFile("setter-contains-local-class-ignore-case")
                .properties()
                .first()
                .setter

        // then
        assertSoftly(sut) {
            it?.hasLocalClassWithName("fixtureclass1") shouldBeEqualTo false
            it?.hasLocalClassWithName("fixtureclass1", ignoreCase = true) shouldBeEqualTo true
            it?.hasLocalClassWithName("otherclass") shouldBeEqualTo false
            it?.hasLocalClassWithName("otherclass", ignoreCase = true) shouldBeEqualTo false
            it?.hasLocalClassWithName("fixtureclass1", "otherName") shouldBeEqualTo false
            it?.hasLocalClassWithName("fixtureclass1", "otherName", ignoreCase = true) shouldBeEqualTo true
            it?.hasLocalClassWithName(listOf("fixtureclass1")) shouldBeEqualTo false
            it?.hasLocalClassWithName(listOf("fixtureclass1"), ignoreCase = true) shouldBeEqualTo true
            it?.hasLocalClassWithName(listOf("otherclass")) shouldBeEqualTo false
            it?.hasLocalClassWithName(listOf("otherclass"), ignoreCase = true) shouldBeEqualTo false
            it?.hasLocalClassWithName(listOf("fixtureclass1", "otherName")) shouldBeEqualTo false
            it?.hasLocalClassWithName(listOf("fixtureclass1", "otherName"), ignoreCase = true) shouldBeEqualTo true
            it?.hasLocalClassesWithAllNames("fixtureclass1") shouldBeEqualTo false
            it?.hasLocalClassesWithAllNames("fixtureclass1", ignoreCase = true) shouldBeEqualTo true
            it?.hasLocalClassesWithAllNames("fixtureclass1", "fixtureclass2") shouldBeEqualTo false
            it?.hasLocalClassesWithAllNames("fixtureclass1", "fixtureclass2", ignoreCase = true) shouldBeEqualTo true
            it?.hasLocalClassesWithAllNames("fixtureclass1", "otherclass") shouldBeEqualTo false
            it?.hasLocalClassesWithAllNames("fixtureclass1", "otherclass", ignoreCase = true) shouldBeEqualTo false
            it?.hasLocalClassesWithAllNames(listOf("fixtureclass1")) shouldBeEqualTo false
            it?.hasLocalClassesWithAllNames(listOf("fixtureclass1"), ignoreCase = true) shouldBeEqualTo true
            it?.hasLocalClassesWithAllNames(listOf("fixtureclass1", "fixtureclass2")) shouldBeEqualTo false
            it?.hasLocalClassesWithAllNames(listOf("fixtureclass1", "fixtureclass2"), ignoreCase = true) shouldBeEqualTo true
            it?.hasLocalClassesWithAllNames(listOf("fixtureclass1", "otherclass")) shouldBeEqualTo false
            it?.hasLocalClassesWithAllNames(listOf("fixtureclass1", "otherclass"), ignoreCase = true) shouldBeEqualTo false
        }
    }

    private fun getSnippetFile(fileName: String) = getSnippetKoScope("core/declaration/kosetter/snippet/forkolocalclassprovider/", fileName)
}
