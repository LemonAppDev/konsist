package com.lemonappdev.konsist.core.declaration.koenumconstant

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import com.lemonappdev.konsist.api.ext.list.enumConstants
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoEnumConstantDeclarationForKoClassProviderTest {
    @Test
    fun `enum-constant-contains-no-classes`() {
        // given
        val sut =
            getSnippetFile("enum-constant-contains-no-classes")
                .classes()
                .enumConstants
                .first()

        // then
        assertSoftly(sut) {
            classes() shouldBeEqualTo emptyList()
            numClasses() shouldBeEqualTo 0
            countClasses { it.name == "FixtureInnerClass" } shouldBeEqualTo 0
            hasClasses() shouldBeEqualTo false
            hasClassWithName(emptyList()) shouldBeEqualTo false
            hasClassWithName(emptySet()) shouldBeEqualTo false
            hasClassesWithAllNames(emptyList()) shouldBeEqualTo false
            hasClassesWithAllNames(emptySet()) shouldBeEqualTo false
            hasClassWithName("FixtureInnerClass") shouldBeEqualTo false
            hasClassWithName(listOf("FixtureInnerClass")) shouldBeEqualTo false
            hasClassWithName(setOf("FixtureInnerClass")) shouldBeEqualTo false
            hasClassesWithAllNames("FixtureInnerClass1", "FixtureInnerClass2") shouldBeEqualTo false
            hasClassesWithAllNames(listOf("FixtureInnerClass1", "FixtureInnerClass2")) shouldBeEqualTo false
            hasClassesWithAllNames(setOf("FixtureInnerClass1", "FixtureInnerClass2")) shouldBeEqualTo false
            hasClass { it.name == "FixtureInnerClass" } shouldBeEqualTo false
            hasAllClasses { it.name == "FixtureInnerClass" } shouldBeEqualTo true
        }
    }

    @Test
    fun `enum-constant-contains-class`() {
        // given
        val sut =
            getSnippetFile("enum-constant-contains-class")
                .classes()
                .enumConstants
                .first()

        // then
        assertSoftly(sut) {
            classes().map { it.name } shouldBeEqualTo listOf("FixtureInnerClass1", "FixtureInnerClass2")
            numClasses() shouldBeEqualTo 2
            countClasses { it.name == "FixtureInnerClass1" } shouldBeEqualTo 1
            hasClasses() shouldBeEqualTo true
            hasClassWithName(emptyList()) shouldBeEqualTo true
            hasClassWithName(emptySet()) shouldBeEqualTo true
            hasClassesWithAllNames(emptyList()) shouldBeEqualTo true
            hasClassesWithAllNames(emptySet()) shouldBeEqualTo true
            hasClassWithName("FixtureInnerClass1") shouldBeEqualTo true
            hasClassWithName("OtherClass") shouldBeEqualTo false
            hasClassWithName("FixtureInnerClass1", "OtherClass") shouldBeEqualTo true
            hasClassWithName(listOf("FixtureInnerClass1")) shouldBeEqualTo true
            hasClassWithName(listOf("OtherClass")) shouldBeEqualTo false
            hasClassWithName(listOf("FixtureInnerClass1", "OtherClass")) shouldBeEqualTo true
            hasClassWithName(setOf("FixtureInnerClass1")) shouldBeEqualTo true
            hasClassWithName(setOf("OtherClass")) shouldBeEqualTo false
            hasClassWithName(setOf("FixtureInnerClass1", "OtherClass")) shouldBeEqualTo true
            hasClassesWithAllNames("FixtureInnerClass1") shouldBeEqualTo true
            hasClassesWithAllNames("FixtureInnerClass1", "FixtureInnerClass2") shouldBeEqualTo true
            hasClassesWithAllNames("FixtureInnerClass1", "OtherClass") shouldBeEqualTo false
            hasClassesWithAllNames(listOf("FixtureInnerClass1")) shouldBeEqualTo true
            hasClassesWithAllNames(listOf("FixtureInnerClass1", "FixtureInnerClass2")) shouldBeEqualTo true
            hasClassesWithAllNames(listOf("FixtureInnerClass1", "OtherClass")) shouldBeEqualTo false
            hasClassesWithAllNames(setOf("FixtureInnerClass1")) shouldBeEqualTo true
            hasClassesWithAllNames(setOf("FixtureInnerClass1", "FixtureInnerClass2")) shouldBeEqualTo true
            hasClassesWithAllNames(setOf("FixtureInnerClass1", "OtherClass")) shouldBeEqualTo false
            hasClass { it.name == "FixtureInnerClass1" } shouldBeEqualTo true
            hasClass { it.name == "OtherClass" } shouldBeEqualTo false
            hasAllClasses { it.name.endsWith("2") || it.name == "FixtureInnerClass1" } shouldBeEqualTo true
            hasAllClasses { it.name.endsWith("2") } shouldBeEqualTo false
        }
    }

    @Test
    fun `enum-constant-contains-no-classes-ignore-case`() {
        // given
        val sut =
            getSnippetFile("enum-constant-contains-no-classes-ignore-case")
                .classes()
                .enumConstants
                .first()

        // then
        assertSoftly(sut) {
            hasClassWithName("fixtureinnerclass") shouldBeEqualTo false
            hasClassWithName("fixtureinnerclass", ignoreCase = true) shouldBeEqualTo false
            hasClassWithName(listOf("fixtureinnerclass")) shouldBeEqualTo false
            hasClassWithName(listOf("fixtureinnerclass"), ignoreCase = true) shouldBeEqualTo false
            hasClassWithName(setOf("fixtureinnerclass")) shouldBeEqualTo false
            hasClassWithName(setOf("fixtureinnerclass"), ignoreCase = true) shouldBeEqualTo false
            hasClassesWithAllNames("fixtureinnerclass1", "fixtureinnerclass2") shouldBeEqualTo false
            hasClassesWithAllNames("fixtureinnerclass1", "fixtureinnerclass2", ignoreCase = true) shouldBeEqualTo false
            hasClassesWithAllNames(listOf("fixtureinnerclass1", "fixtureinnerclass2")) shouldBeEqualTo false
            hasClassesWithAllNames(listOf("fixtureinnerclass1", "fixtureinnerclass2"), ignoreCase = true) shouldBeEqualTo false
            hasClassesWithAllNames(setOf("fixtureinnerclass1", "fixtureinnerclass2")) shouldBeEqualTo false
            hasClassesWithAllNames(setOf("fixtureinnerclass1", "fixtureinnerclass2"), ignoreCase = true) shouldBeEqualTo false
        }
    }

    @Test
    fun `enum-constant-contains-class-ignore-case`() {
        // given
        val sut =
            getSnippetFile("enum-constant-contains-class-ignore-case")
                .classes()
                .enumConstants
                .first()

        // then
        assertSoftly(sut) {
            hasClassWithName("fixtureinnerclass1") shouldBeEqualTo false
            hasClassWithName("fixtureinnerclass1", ignoreCase = true) shouldBeEqualTo true
            hasClassWithName("otherclass") shouldBeEqualTo false
            hasClassWithName("otherclass", ignoreCase = true) shouldBeEqualTo false
            hasClassWithName("fixtureinnerclass1", "otherName") shouldBeEqualTo false
            hasClassWithName("fixtureinnerclass1", "otherName", ignoreCase = true) shouldBeEqualTo true
            hasClassWithName(listOf("fixtureinnerclass1")) shouldBeEqualTo false
            hasClassWithName(listOf("fixtureinnerclass1"), ignoreCase = true) shouldBeEqualTo true
            hasClassWithName(listOf("otherclass")) shouldBeEqualTo false
            hasClassWithName(listOf("otherclass"), ignoreCase = true) shouldBeEqualTo false
            hasClassWithName(listOf("fixtureinnerclass1", "otherName")) shouldBeEqualTo false
            hasClassWithName(listOf("fixtureinnerclass1", "otherName"), ignoreCase = true) shouldBeEqualTo true
            hasClassesWithAllNames("fixtureinnerclass1") shouldBeEqualTo false
            hasClassesWithAllNames("fixtureinnerclass1", ignoreCase = true) shouldBeEqualTo true
            hasClassesWithAllNames("fixtureinnerclass1", "fixtureinnerclass2") shouldBeEqualTo false
            hasClassesWithAllNames("fixtureinnerclass1", "fixtureinnerclass2", ignoreCase = true) shouldBeEqualTo true
            hasClassesWithAllNames("fixtureinnerclass1", "otherclass") shouldBeEqualTo false
            hasClassesWithAllNames("fixtureinnerclass1", "otherclass", ignoreCase = true) shouldBeEqualTo false
            hasClassesWithAllNames(listOf("fixtureinnerclass1")) shouldBeEqualTo false
            hasClassesWithAllNames(listOf("fixtureinnerclass1"), ignoreCase = true) shouldBeEqualTo true
            hasClassesWithAllNames(listOf("fixtureinnerclass1", "fixtureinnerclass2")) shouldBeEqualTo false
            hasClassesWithAllNames(listOf("fixtureinnerclass1", "fixtureinnerclass2"), ignoreCase = true) shouldBeEqualTo true
            hasClassesWithAllNames(listOf("fixtureinnerclass1", "otherclass")) shouldBeEqualTo false
            hasClassesWithAllNames(listOf("fixtureinnerclass1", "otherclass"), ignoreCase = true) shouldBeEqualTo false
        }
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/koenumconstant/snippet/forkoclassprovider/", fileName)
}
