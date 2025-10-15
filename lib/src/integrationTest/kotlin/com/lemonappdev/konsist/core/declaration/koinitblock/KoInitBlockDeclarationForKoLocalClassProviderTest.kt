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
            countLocalClasses { it.name == "SampleLocalClass" } shouldBeEqualTo 0
            hasLocalClasses() shouldBeEqualTo false
            hasLocalClassWithName(emptyList()) shouldBeEqualTo false
            hasLocalClassWithName(emptySet()) shouldBeEqualTo false
            hasLocalClassesWithAllNames(emptyList()) shouldBeEqualTo false
            hasLocalClassesWithAllNames(emptySet()) shouldBeEqualTo false
            hasLocalClassWithName("SampleLocalClass") shouldBeEqualTo false
            hasLocalClassWithName(listOf("SampleLocalClass")) shouldBeEqualTo false
            hasLocalClassWithName(setOf("SampleLocalClass")) shouldBeEqualTo false
            hasLocalClassesWithAllNames("SampleLocalClass1", "SampleLocalClass2") shouldBeEqualTo false
            hasLocalClassesWithAllNames(listOf("SampleLocalClass1", "SampleLocalClass2")) shouldBeEqualTo false
            hasLocalClassesWithAllNames(setOf("SampleLocalClass1", "SampleLocalClass2")) shouldBeEqualTo false
            hasLocalClass { it.name == "SampleLocalClass" } shouldBeEqualTo false
            hasAllLocalClasses { it.name == "SampleLocalClass" } shouldBeEqualTo true
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
            localClasses.map { it.name } shouldBeEqualTo listOf("SampleLocalClass1", "SampleLocalClass2")
            numLocalClasses shouldBeEqualTo 2
            countLocalClasses { it.name == "SampleLocalClass1" } shouldBeEqualTo 1
            hasLocalClasses() shouldBeEqualTo true
            hasLocalClassWithName(emptyList()) shouldBeEqualTo true
            hasLocalClassWithName(emptySet()) shouldBeEqualTo true
            hasLocalClassesWithAllNames(emptyList()) shouldBeEqualTo true
            hasLocalClassesWithAllNames(emptySet()) shouldBeEqualTo true
            hasLocalClassWithName("SampleLocalClass1") shouldBeEqualTo true
            hasLocalClassWithName("OtherLocalClass") shouldBeEqualTo false
            hasLocalClassWithName("SampleLocalClass1", "OtherLocalClass") shouldBeEqualTo true
            hasLocalClassWithName(listOf("SampleLocalClass1")) shouldBeEqualTo true
            hasLocalClassWithName(listOf("OtherLocalClass")) shouldBeEqualTo false
            hasLocalClassWithName(listOf("SampleLocalClass1", "OtherLocalClass")) shouldBeEqualTo true
            hasLocalClassWithName(setOf("SampleLocalClass1")) shouldBeEqualTo true
            hasLocalClassWithName(setOf("OtherLocalClass")) shouldBeEqualTo false
            hasLocalClassWithName(setOf("SampleLocalClass1", "OtherLocalClass")) shouldBeEqualTo true
            hasLocalClassesWithAllNames("SampleLocalClass1") shouldBeEqualTo true
            hasLocalClassesWithAllNames("SampleLocalClass1", "SampleLocalClass2") shouldBeEqualTo true
            hasLocalClassesWithAllNames("SampleLocalClass1", "OtherLocalClass") shouldBeEqualTo false
            hasLocalClassesWithAllNames(listOf("SampleLocalClass1")) shouldBeEqualTo true
            hasLocalClassesWithAllNames(listOf("SampleLocalClass1", "SampleLocalClass2")) shouldBeEqualTo true
            hasLocalClassesWithAllNames(listOf("SampleLocalClass1", "OtherLocalClass")) shouldBeEqualTo false
            hasLocalClassesWithAllNames(setOf("SampleLocalClass1")) shouldBeEqualTo true
            hasLocalClassesWithAllNames(setOf("SampleLocalClass1", "SampleLocalClass2")) shouldBeEqualTo true
            hasLocalClassesWithAllNames(setOf("SampleLocalClass1", "OtherLocalClass")) shouldBeEqualTo false
            hasLocalClass { it.name == "SampleLocalClass1" } shouldBeEqualTo true
            hasLocalClass { it.name == "OtherLocalClass" } shouldBeEqualTo false
            hasAllLocalClasses { it.name.endsWith("2") || it.name == "SampleLocalClass1" } shouldBeEqualTo true
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
            hasLocalClassWithName("samplelocalclass") shouldBeEqualTo false
            hasLocalClassWithName("samplelocalclass", ignoreCase = true) shouldBeEqualTo false
            hasLocalClassWithName(listOf("samplelocalclass")) shouldBeEqualTo false
            hasLocalClassWithName(listOf("samplelocalclass"), ignoreCase = true) shouldBeEqualTo false
            hasLocalClassWithName(setOf("samplelocalclass")) shouldBeEqualTo false
            hasLocalClassWithName(setOf("samplelocalclass"), ignoreCase = true) shouldBeEqualTo false
            hasLocalClassesWithAllNames("samplelocalclass1", "samplelocalclass2") shouldBeEqualTo false
            hasLocalClassesWithAllNames("samplelocalclass1", "samplelocalclass2", ignoreCase = true) shouldBeEqualTo false
            hasLocalClassesWithAllNames(listOf("samplelocalclass1", "samplelocalclass2")) shouldBeEqualTo false
            hasLocalClassesWithAllNames(listOf("samplelocalclass1", "samplelocalclass2"), ignoreCase = true) shouldBeEqualTo false
            hasLocalClassesWithAllNames(setOf("samplelocalclass1", "samplelocalclass2")) shouldBeEqualTo false
            hasLocalClassesWithAllNames(setOf("samplelocalclass1", "samplelocalclass2"), ignoreCase = true) shouldBeEqualTo false
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
            hasLocalClassWithName("samplelocalclass1") shouldBeEqualTo false
            hasLocalClassWithName("samplelocalclass1", ignoreCase = true) shouldBeEqualTo true
            hasLocalClassWithName("otherlocalclass") shouldBeEqualTo false
            hasLocalClassWithName("otherlocalclass", ignoreCase = true) shouldBeEqualTo false
            hasLocalClassWithName("samplelocalclass1", "otherName") shouldBeEqualTo false
            hasLocalClassWithName("samplelocalclass1", "otherName", ignoreCase = true) shouldBeEqualTo true
            hasLocalClassWithName(listOf("samplelocalclass1")) shouldBeEqualTo false
            hasLocalClassWithName(listOf("samplelocalclass1"), ignoreCase = true) shouldBeEqualTo true
            hasLocalClassWithName(listOf("otherlocalclass")) shouldBeEqualTo false
            hasLocalClassWithName(listOf("otherlocalclass"), ignoreCase = true) shouldBeEqualTo false
            hasLocalClassWithName(listOf("samplelocalclass1", "otherName")) shouldBeEqualTo false
            hasLocalClassWithName(listOf("samplelocalclass1", "otherName"), ignoreCase = true) shouldBeEqualTo true
            hasLocalClassesWithAllNames("samplelocalclass1") shouldBeEqualTo false
            hasLocalClassesWithAllNames("samplelocalclass1", ignoreCase = true) shouldBeEqualTo true
            hasLocalClassesWithAllNames("samplelocalclass1", "samplelocalclass2") shouldBeEqualTo false
            hasLocalClassesWithAllNames("samplelocalclass1", "samplelocalclass2", ignoreCase = true) shouldBeEqualTo true
            hasLocalClassesWithAllNames("samplelocalclass1", "otherlocalclass") shouldBeEqualTo false
            hasLocalClassesWithAllNames("samplelocalclass1", "otherlocalclass", ignoreCase = true) shouldBeEqualTo false
            hasLocalClassesWithAllNames(listOf("samplelocalclass1")) shouldBeEqualTo false
            hasLocalClassesWithAllNames(listOf("samplelocalclass1"), ignoreCase = true) shouldBeEqualTo true
            hasLocalClassesWithAllNames(listOf("samplelocalclass1", "samplelocalclass2")) shouldBeEqualTo false
            hasLocalClassesWithAllNames(listOf("samplelocalclass1", "samplelocalclass2"), ignoreCase = true) shouldBeEqualTo true
            hasLocalClassesWithAllNames(listOf("samplelocalclass1", "otherlocalclass")) shouldBeEqualTo false
            hasLocalClassesWithAllNames(listOf("samplelocalclass1", "otherlocalclass"), ignoreCase = true) shouldBeEqualTo false
        }
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/koinitblock/snippet/forkolocalclassprovider/", fileName)
}
