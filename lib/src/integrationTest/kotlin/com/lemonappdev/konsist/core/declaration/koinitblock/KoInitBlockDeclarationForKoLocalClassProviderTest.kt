package com.lemonappdev.konsist.core.declaration.koinitblock

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import com.lemonappdev.konsist.api.ext.list.initBlocks
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoInitBlockDeclarationForKoLocalClassProviderTest {
    @Test
    fun `init-block-contains-no-local-classes`() {
        // given
        val sut =
            getSnippetFile("init-block-contains-no-local-classes")
                .classes()
                .initBlocks
                .first()

        // then
        assertSoftly(sut) {
            localClasses shouldBeEqualTo emptyList()
            numLocalClasses shouldBeEqualTo 0
            countLocalClasses { it.name == "FixtureLocalClass" } shouldBeEqualTo 0
            hasLocalClasses() shouldBeEqualTo false
            hasLocalClassWithName(emptyList()) shouldBeEqualTo false
            hasLocalClassWithName(emptySet()) shouldBeEqualTo false
            hasLocalClassesWithAllNames(emptyList()) shouldBeEqualTo false
            hasLocalClassesWithAllNames(emptySet()) shouldBeEqualTo false
            hasLocalClassWithName("FixtureLocalClass") shouldBeEqualTo false
            hasLocalClassWithName(listOf("FixtureLocalClass")) shouldBeEqualTo false
            hasLocalClassWithName(setOf("FixtureLocalClass")) shouldBeEqualTo false
            hasLocalClassesWithAllNames("FixtureLocalClass1", "FixtureLocalClass2") shouldBeEqualTo false
            hasLocalClassesWithAllNames(listOf("FixtureLocalClass1", "FixtureLocalClass2")) shouldBeEqualTo false
            hasLocalClassesWithAllNames(setOf("FixtureLocalClass1", "FixtureLocalClass2")) shouldBeEqualTo false
            hasLocalClass { it.name == "FixtureLocalClass" } shouldBeEqualTo false
            hasAllLocalClasses { it.name == "FixtureLocalClass" } shouldBeEqualTo true
        }
    }

    @Test
    fun `init-block-contains-local-class`() {
        // given
        val sut =
            getSnippetFile("init-block-contains-local-class")
                .classes()
                .initBlocks
                .first()

        // then
        assertSoftly(sut) {
            localClasses.map { it.name } shouldBeEqualTo listOf("FixtureLocalClass1", "FixtureLocalClass2")
            numLocalClasses shouldBeEqualTo 2
            countLocalClasses { it.name == "FixtureLocalClass1" } shouldBeEqualTo 1
            hasLocalClasses() shouldBeEqualTo true
            hasLocalClassWithName(emptyList()) shouldBeEqualTo true
            hasLocalClassWithName(emptySet()) shouldBeEqualTo true
            hasLocalClassesWithAllNames(emptyList()) shouldBeEqualTo true
            hasLocalClassesWithAllNames(emptySet()) shouldBeEqualTo true
            hasLocalClassWithName("FixtureLocalClass1") shouldBeEqualTo true
            hasLocalClassWithName("OtherLocalClass") shouldBeEqualTo false
            hasLocalClassWithName("FixtureLocalClass1", "OtherLocalClass") shouldBeEqualTo true
            hasLocalClassWithName(listOf("FixtureLocalClass1")) shouldBeEqualTo true
            hasLocalClassWithName(listOf("OtherLocalClass")) shouldBeEqualTo false
            hasLocalClassWithName(listOf("FixtureLocalClass1", "OtherLocalClass")) shouldBeEqualTo true
            hasLocalClassWithName(setOf("FixtureLocalClass1")) shouldBeEqualTo true
            hasLocalClassWithName(setOf("OtherLocalClass")) shouldBeEqualTo false
            hasLocalClassWithName(setOf("FixtureLocalClass1", "OtherLocalClass")) shouldBeEqualTo true
            hasLocalClassesWithAllNames("FixtureLocalClass1") shouldBeEqualTo true
            hasLocalClassesWithAllNames("FixtureLocalClass1", "FixtureLocalClass2") shouldBeEqualTo true
            hasLocalClassesWithAllNames("FixtureLocalClass1", "OtherLocalClass") shouldBeEqualTo false
            hasLocalClassesWithAllNames(listOf("FixtureLocalClass1")) shouldBeEqualTo true
            hasLocalClassesWithAllNames(listOf("FixtureLocalClass1", "FixtureLocalClass2")) shouldBeEqualTo true
            hasLocalClassesWithAllNames(listOf("FixtureLocalClass1", "OtherLocalClass")) shouldBeEqualTo false
            hasLocalClassesWithAllNames(setOf("FixtureLocalClass1")) shouldBeEqualTo true
            hasLocalClassesWithAllNames(setOf("FixtureLocalClass1", "FixtureLocalClass2")) shouldBeEqualTo true
            hasLocalClassesWithAllNames(setOf("FixtureLocalClass1", "OtherLocalClass")) shouldBeEqualTo false
            hasLocalClass { it.name == "FixtureLocalClass1" } shouldBeEqualTo true
            hasLocalClass { it.name == "OtherLocalClass" } shouldBeEqualTo false
            hasAllLocalClasses { it.name.endsWith("2") || it.name == "FixtureLocalClass1" } shouldBeEqualTo true
            hasAllLocalClasses { it.name.endsWith("2") } shouldBeEqualTo false
        }
    }

    @Test
    fun `init-block-contains-no-local-classes-ignore-case`() {
        // given
        val sut =
            getSnippetFile("init-block-contains-no-local-classes-ignore-case")
                .classes()
                .initBlocks
                .first()

        // then
        assertSoftly(sut) {
            hasLocalClassWithName("fixturelocalclass") shouldBeEqualTo false
            hasLocalClassWithName("fixturelocalclass", ignoreCase = true) shouldBeEqualTo false
            hasLocalClassWithName(listOf("fixturelocalclass")) shouldBeEqualTo false
            hasLocalClassWithName(listOf("fixturelocalclass"), ignoreCase = true) shouldBeEqualTo false
            hasLocalClassWithName(setOf("fixturelocalclass")) shouldBeEqualTo false
            hasLocalClassWithName(setOf("fixturelocalclass"), ignoreCase = true) shouldBeEqualTo false
            hasLocalClassesWithAllNames("fixturelocalclass1", "fixturelocalclass2") shouldBeEqualTo false
            hasLocalClassesWithAllNames("fixturelocalclass1", "fixturelocalclass2", ignoreCase = true) shouldBeEqualTo false
            hasLocalClassesWithAllNames(listOf("fixturelocalclass1", "fixturelocalclass2")) shouldBeEqualTo false
            hasLocalClassesWithAllNames(listOf("fixturelocalclass1", "fixturelocalclass2"), ignoreCase = true) shouldBeEqualTo false
            hasLocalClassesWithAllNames(setOf("fixturelocalclass1", "fixturelocalclass2")) shouldBeEqualTo false
            hasLocalClassesWithAllNames(setOf("fixturelocalclass1", "fixturelocalclass2"), ignoreCase = true) shouldBeEqualTo false
        }
    }

    @Test
    fun `init-block-contains-local-class-ignore-case`() {
        // given
        val sut =
            getSnippetFile("init-block-contains-local-class-ignore-case")
                .classes()
                .initBlocks
                .first()

        // then
        assertSoftly(sut) {
            hasLocalClassWithName("fixturelocalclass1") shouldBeEqualTo false
            hasLocalClassWithName("fixturelocalclass1", ignoreCase = true) shouldBeEqualTo true
            hasLocalClassWithName("otherlocalclass") shouldBeEqualTo false
            hasLocalClassWithName("otherlocalclass", ignoreCase = true) shouldBeEqualTo false
            hasLocalClassWithName("fixturelocalclass1", "otherName") shouldBeEqualTo false
            hasLocalClassWithName("fixturelocalclass1", "otherName", ignoreCase = true) shouldBeEqualTo true
            hasLocalClassWithName(listOf("fixturelocalclass1")) shouldBeEqualTo false
            hasLocalClassWithName(listOf("fixturelocalclass1"), ignoreCase = true) shouldBeEqualTo true
            hasLocalClassWithName(listOf("otherlocalclass")) shouldBeEqualTo false
            hasLocalClassWithName(listOf("otherlocalclass"), ignoreCase = true) shouldBeEqualTo false
            hasLocalClassWithName(listOf("fixturelocalclass1", "otherName")) shouldBeEqualTo false
            hasLocalClassWithName(listOf("fixturelocalclass1", "otherName"), ignoreCase = true) shouldBeEqualTo true
            hasLocalClassesWithAllNames("fixturelocalclass1") shouldBeEqualTo false
            hasLocalClassesWithAllNames("fixturelocalclass1", ignoreCase = true) shouldBeEqualTo true
            hasLocalClassesWithAllNames("fixturelocalclass1", "fixturelocalclass2") shouldBeEqualTo false
            hasLocalClassesWithAllNames("fixturelocalclass1", "fixturelocalclass2", ignoreCase = true) shouldBeEqualTo true
            hasLocalClassesWithAllNames("fixturelocalclass1", "otherlocalclass") shouldBeEqualTo false
            hasLocalClassesWithAllNames("fixturelocalclass1", "otherlocalclass", ignoreCase = true) shouldBeEqualTo false
            hasLocalClassesWithAllNames(listOf("fixturelocalclass1")) shouldBeEqualTo false
            hasLocalClassesWithAllNames(listOf("fixturelocalclass1"), ignoreCase = true) shouldBeEqualTo true
            hasLocalClassesWithAllNames(listOf("fixturelocalclass1", "fixturelocalclass2")) shouldBeEqualTo false
            hasLocalClassesWithAllNames(listOf("fixturelocalclass1", "fixturelocalclass2"), ignoreCase = true) shouldBeEqualTo true
            hasLocalClassesWithAllNames(listOf("fixturelocalclass1", "otherlocalclass")) shouldBeEqualTo false
            hasLocalClassesWithAllNames(listOf("fixturelocalclass1", "otherlocalclass"), ignoreCase = true) shouldBeEqualTo false
        }
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/koinitblock/snippet/forkolocalclassprovider/", fileName)
}
