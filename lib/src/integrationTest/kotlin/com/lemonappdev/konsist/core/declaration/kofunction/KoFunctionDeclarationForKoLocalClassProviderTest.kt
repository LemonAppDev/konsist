package com.lemonappdev.konsist.core.declaration.kofunction

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoFunctionDeclarationForKoLocalClassProviderTest {
    @Test
    fun `function-contains-no-local-classes`() {
        // given
        val sut =
            getSnippetFile("function-contains-no-local-classes")
                .functions()
                .first()

        // then
        assertSoftly(sut) {
            localClasses shouldBeEqualTo emptyList()
            numLocalClasses shouldBeEqualTo 0
            countLocalClasses { it.name == "FixtureClass" } shouldBeEqualTo 0
            hasLocalClasses() shouldBeEqualTo false
            hasLocalClassWithName(emptyList()) shouldBeEqualTo false
            hasLocalClassWithName(emptySet()) shouldBeEqualTo false
            hasLocalClassesWithAllNames(emptyList()) shouldBeEqualTo false
            hasLocalClassesWithAllNames(emptySet()) shouldBeEqualTo false
            hasLocalClassWithName("FixtureClass") shouldBeEqualTo false
            hasLocalClassWithName(listOf("FixtureClass")) shouldBeEqualTo false
            hasLocalClassWithName(setOf("FixtureClass")) shouldBeEqualTo false
            hasLocalClassesWithAllNames("FixtureClass1", "FixtureClass2") shouldBeEqualTo false
            hasLocalClassesWithAllNames(listOf("FixtureClass1", "FixtureClass2")) shouldBeEqualTo false
            hasLocalClassesWithAllNames(setOf("FixtureClass1", "FixtureClass2")) shouldBeEqualTo false
            hasLocalClass { it.name == "FixtureClass" } shouldBeEqualTo false
            hasAllLocalClasses { it.name == "FixtureClass" } shouldBeEqualTo true
        }
    }

    @Test
    fun `function-contains-local-class`() {
        // given
        val sut =
            getSnippetFile("function-contains-local-class")
                .functions()
                .first()

        // then
        assertSoftly(sut) {
            localClasses.map { it.name } shouldBeEqualTo listOf("FixtureClass1", "FixtureClass2")
            numLocalClasses shouldBeEqualTo 2
            countLocalClasses { it.name == "FixtureClass1" } shouldBeEqualTo 1
            hasLocalClasses() shouldBeEqualTo true
            hasLocalClassWithName(emptyList()) shouldBeEqualTo true
            hasLocalClassWithName(emptySet()) shouldBeEqualTo true
            hasLocalClassesWithAllNames(emptyList()) shouldBeEqualTo true
            hasLocalClassesWithAllNames(emptySet()) shouldBeEqualTo true
            hasLocalClassWithName("FixtureClass1") shouldBeEqualTo true
            hasLocalClassWithName("OtherLocalClass") shouldBeEqualTo false
            hasLocalClassWithName("FixtureClass1", "OtherLocalClass") shouldBeEqualTo true
            hasLocalClassWithName(listOf("FixtureClass1")) shouldBeEqualTo true
            hasLocalClassWithName(listOf("OtherLocalClass")) shouldBeEqualTo false
            hasLocalClassWithName(listOf("FixtureClass1", "OtherLocalClass")) shouldBeEqualTo true
            hasLocalClassWithName(setOf("FixtureClass1")) shouldBeEqualTo true
            hasLocalClassWithName(setOf("OtherLocalClass")) shouldBeEqualTo false
            hasLocalClassWithName(setOf("FixtureClass1", "OtherLocalClass")) shouldBeEqualTo true
            hasLocalClassesWithAllNames("FixtureClass1") shouldBeEqualTo true
            hasLocalClassesWithAllNames("FixtureClass1", "FixtureClass2") shouldBeEqualTo true
            hasLocalClassesWithAllNames("FixtureClass1", "OtherLocalClass") shouldBeEqualTo false
            hasLocalClassesWithAllNames(listOf("FixtureClass1")) shouldBeEqualTo true
            hasLocalClassesWithAllNames(listOf("FixtureClass1", "FixtureClass2")) shouldBeEqualTo true
            hasLocalClassesWithAllNames(listOf("FixtureClass1", "OtherLocalClass")) shouldBeEqualTo false
            hasLocalClassesWithAllNames(setOf("FixtureClass1")) shouldBeEqualTo true
            hasLocalClassesWithAllNames(setOf("FixtureClass1", "FixtureClass2")) shouldBeEqualTo true
            hasLocalClassesWithAllNames(setOf("FixtureClass1", "OtherLocalClass")) shouldBeEqualTo false
            hasLocalClass { it.name == "FixtureClass1" } shouldBeEqualTo true
            hasLocalClass { it.name == "OtherLocalClass" } shouldBeEqualTo false
            hasAllLocalClasses { it.name.endsWith("2") || it.name == "FixtureClass1" } shouldBeEqualTo true
            hasAllLocalClasses { it.name.endsWith("2") } shouldBeEqualTo false
        }
    }

    @Test
    fun `function-contains-no-local-classes-ignore-case`() {
        // given
        val sut =
            getSnippetFile("function-contains-no-local-classes-ignore-case")
                .functions()
                .first()

        // then
        assertSoftly(sut) {
            hasLocalClassWithName("fixtureclass") shouldBeEqualTo false
            hasLocalClassWithName("fixtureclass", ignoreCase = true) shouldBeEqualTo false
            hasLocalClassWithName(listOf("fixtureclass")) shouldBeEqualTo false
            hasLocalClassWithName(listOf("fixtureclass"), ignoreCase = true) shouldBeEqualTo false
            hasLocalClassWithName(setOf("fixtureclass")) shouldBeEqualTo false
            hasLocalClassWithName(setOf("fixtureclass"), ignoreCase = true) shouldBeEqualTo false
            hasLocalClassesWithAllNames("fixtureclass1", "fixtureclass2") shouldBeEqualTo false
            hasLocalClassesWithAllNames("fixtureclass1", "fixtureclass2", ignoreCase = true) shouldBeEqualTo false
            hasLocalClassesWithAllNames(listOf("fixtureclass1", "fixtureclass2")) shouldBeEqualTo false
            hasLocalClassesWithAllNames(listOf("fixtureclass1", "fixtureclass2"), ignoreCase = true) shouldBeEqualTo false
            hasLocalClassesWithAllNames(setOf("fixtureclass1", "fixtureclass2")) shouldBeEqualTo false
            hasLocalClassesWithAllNames(setOf("fixtureclass1", "fixtureclass2"), ignoreCase = true) shouldBeEqualTo false
        }
    }

    @Test
    fun `function-contains-local-class-ignore-case`() {
        // given
        val sut =
            getSnippetFile("function-contains-local-class-ignore-case")
                .functions()
                .first()

        // then
        assertSoftly(sut) {
            hasLocalClassWithName("fixtureclass1") shouldBeEqualTo false
            hasLocalClassWithName("fixtureclass1", ignoreCase = true) shouldBeEqualTo true
            hasLocalClassWithName("otherclass") shouldBeEqualTo false
            hasLocalClassWithName("otherclass", ignoreCase = true) shouldBeEqualTo false
            hasLocalClassWithName("fixtureclass1", "otherName") shouldBeEqualTo false
            hasLocalClassWithName("fixtureclass1", "otherName", ignoreCase = true) shouldBeEqualTo true
            hasLocalClassWithName(listOf("fixtureclass1")) shouldBeEqualTo false
            hasLocalClassWithName(listOf("fixtureclass1"), ignoreCase = true) shouldBeEqualTo true
            hasLocalClassWithName(listOf("otherclass")) shouldBeEqualTo false
            hasLocalClassWithName(listOf("otherclass"), ignoreCase = true) shouldBeEqualTo false
            hasLocalClassWithName(listOf("fixtureclass1", "otherName")) shouldBeEqualTo false
            hasLocalClassWithName(listOf("fixtureclass1", "otherName"), ignoreCase = true) shouldBeEqualTo true
            hasLocalClassesWithAllNames("fixtureclass1") shouldBeEqualTo false
            hasLocalClassesWithAllNames("fixtureclass1", ignoreCase = true) shouldBeEqualTo true
            hasLocalClassesWithAllNames("fixtureclass1", "fixtureclass2") shouldBeEqualTo false
            hasLocalClassesWithAllNames("fixtureclass1", "fixtureclass2", ignoreCase = true) shouldBeEqualTo true
            hasLocalClassesWithAllNames("fixtureclass1", "otherclass") shouldBeEqualTo false
            hasLocalClassesWithAllNames("fixtureclass1", "otherclass", ignoreCase = true) shouldBeEqualTo false
            hasLocalClassesWithAllNames(listOf("fixtureclass1")) shouldBeEqualTo false
            hasLocalClassesWithAllNames(listOf("fixtureclass1"), ignoreCase = true) shouldBeEqualTo true
            hasLocalClassesWithAllNames(listOf("fixtureclass1", "fixtureclass2")) shouldBeEqualTo false
            hasLocalClassesWithAllNames(listOf("fixtureclass1", "fixtureclass2"), ignoreCase = true) shouldBeEqualTo true
            hasLocalClassesWithAllNames(listOf("fixtureclass1", "otherclass")) shouldBeEqualTo false
            hasLocalClassesWithAllNames(listOf("fixtureclass1", "otherclass"), ignoreCase = true) shouldBeEqualTo false
        }
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/kofunction/snippet/forkolocalclassprovider/", fileName)
}
