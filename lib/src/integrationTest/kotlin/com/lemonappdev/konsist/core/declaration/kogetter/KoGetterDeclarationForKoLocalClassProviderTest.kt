package com.lemonappdev.konsist.core.declaration.kogetter

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoGetterDeclarationForKoLocalClassProviderTest {
    @Test
    fun `getter-contains-no-local-classes`() {
        // given
        val sut =
            getSnippetFile("getter-contains-no-local-classes")
                .properties()
                .first()
                .getter

        // then
        assertSoftly(sut) {
            it?.localClasses shouldBeEqualTo emptyList()
            it?.numLocalClasses shouldBeEqualTo 0
            it?.countLocalClasses { it.name == "SampleClass" } shouldBeEqualTo 0
            it?.hasLocalClasses() shouldBeEqualTo false
            it?.hasLocalClassWithName(emptyList()) shouldBeEqualTo false
            it?.hasLocalClassWithName(emptySet()) shouldBeEqualTo false
            it?.hasLocalClassesWithAllNames(emptyList()) shouldBeEqualTo false
            it?.hasLocalClassesWithAllNames(emptySet()) shouldBeEqualTo false
            it?.hasLocalClassWithName("SampleClass") shouldBeEqualTo false
            it?.hasLocalClassWithName(listOf("SampleClass")) shouldBeEqualTo false
            it?.hasLocalClassWithName(setOf("SampleClass")) shouldBeEqualTo false
            it?.hasLocalClassesWithAllNames("SampleClass1", "SampleClass2") shouldBeEqualTo false
            it?.hasLocalClassesWithAllNames(listOf("SampleClass1", "SampleClass2")) shouldBeEqualTo false
            it?.hasLocalClassesWithAllNames(setOf("SampleClass1", "SampleClass2")) shouldBeEqualTo false
            it?.hasLocalClass { it.name == "SampleClass" } shouldBeEqualTo false
            it?.hasAllLocalClasses { it.name == "SampleClass" } shouldBeEqualTo true
        }
    }

    @Test
    fun `getter-contains-local-class`() {
        // given
        val sut =
            getSnippetFile("getter-contains-local-class")
                .properties()
                .first()
                .getter

        // then
        assertSoftly(sut) {
            it?.localClasses?.map { it.name } shouldBeEqualTo listOf("SampleClass1", "SampleClass2")
            it?.numLocalClasses shouldBeEqualTo 2
            it?.countLocalClasses { it.name == "SampleClass1" } shouldBeEqualTo 1
            it?.hasLocalClasses() shouldBeEqualTo true
            it?.hasLocalClassWithName(emptyList()) shouldBeEqualTo true
            it?.hasLocalClassWithName(emptySet()) shouldBeEqualTo true
            it?.hasLocalClassesWithAllNames(emptyList()) shouldBeEqualTo true
            it?.hasLocalClassesWithAllNames(emptySet()) shouldBeEqualTo true
            it?.hasLocalClassWithName("SampleClass1") shouldBeEqualTo true
            it?.hasLocalClassWithName("OtherLocalClass") shouldBeEqualTo false
            it?.hasLocalClassWithName("SampleClass1", "OtherLocalClass") shouldBeEqualTo true
            it?.hasLocalClassWithName(listOf("SampleClass1")) shouldBeEqualTo true
            it?.hasLocalClassWithName(listOf("OtherLocalClass")) shouldBeEqualTo false
            it?.hasLocalClassWithName(listOf("SampleClass1", "OtherLocalClass")) shouldBeEqualTo true
            it?.hasLocalClassWithName(setOf("SampleClass1")) shouldBeEqualTo true
            it?.hasLocalClassWithName(setOf("OtherLocalClass")) shouldBeEqualTo false
            it?.hasLocalClassWithName(setOf("SampleClass1", "OtherLocalClass")) shouldBeEqualTo true
            it?.hasLocalClassesWithAllNames("SampleClass1") shouldBeEqualTo true
            it?.hasLocalClassesWithAllNames("SampleClass1", "SampleClass2") shouldBeEqualTo true
            it?.hasLocalClassesWithAllNames("SampleClass1", "OtherLocalClass") shouldBeEqualTo false
            it?.hasLocalClassesWithAllNames(listOf("SampleClass1")) shouldBeEqualTo true
            it?.hasLocalClassesWithAllNames(listOf("SampleClass1", "SampleClass2")) shouldBeEqualTo true
            it?.hasLocalClassesWithAllNames(listOf("SampleClass1", "OtherLocalClass")) shouldBeEqualTo false
            it?.hasLocalClassesWithAllNames(setOf("SampleClass1")) shouldBeEqualTo true
            it?.hasLocalClassesWithAllNames(setOf("SampleClass1", "SampleClass2")) shouldBeEqualTo true
            it?.hasLocalClassesWithAllNames(setOf("SampleClass1", "OtherLocalClass")) shouldBeEqualTo false
            it?.hasLocalClass { it.name == "SampleClass1" } shouldBeEqualTo true
            it?.hasLocalClass { it.name == "OtherLocalClass" } shouldBeEqualTo false
            it?.hasAllLocalClasses { it.name.endsWith("2") || it.name == "SampleClass1" } shouldBeEqualTo true
            it?.hasAllLocalClasses { it.name.endsWith("2") } shouldBeEqualTo false
        }
    }

    @Test
    fun `getter-contains-no-local-classes-ignore-case`() {
        // given
        val sut =
            getSnippetFile("getter-contains-no-local-classes-ignore-case")
                .properties()
                .first()
                .getter

        // then
        assertSoftly(sut) {
            it?.hasLocalClassWithName("sampleclass") shouldBeEqualTo false
            it?.hasLocalClassWithName("sampleclass", ignoreCase = true) shouldBeEqualTo false
            it?.hasLocalClassWithName(listOf("sampleclass")) shouldBeEqualTo false
            it?.hasLocalClassWithName(listOf("sampleclass"), ignoreCase = true) shouldBeEqualTo false
            it?.hasLocalClassWithName(setOf("sampleclass")) shouldBeEqualTo false
            it?.hasLocalClassWithName(setOf("sampleclass"), ignoreCase = true) shouldBeEqualTo false
            it?.hasLocalClassesWithAllNames("sampleclass1", "sampleclass2") shouldBeEqualTo false
            it?.hasLocalClassesWithAllNames("sampleclass1", "sampleclass2", ignoreCase = true) shouldBeEqualTo false
            it?.hasLocalClassesWithAllNames(listOf("sampleclass1", "sampleclass2")) shouldBeEqualTo false
            it?.hasLocalClassesWithAllNames(listOf("sampleclass1", "sampleclass2"), ignoreCase = true) shouldBeEqualTo false
            it?.hasLocalClassesWithAllNames(setOf("sampleclass1", "sampleclass2")) shouldBeEqualTo false
            it?.hasLocalClassesWithAllNames(setOf("sampleclass1", "sampleclass2"), ignoreCase = true) shouldBeEqualTo false
        }
    }

    @Test
    fun `getter-contains-local-class-ignore-case`() {
        // given
        val sut =
            getSnippetFile("getter-contains-local-class-ignore-case")
                .properties()
                .first()
                .getter

        // then
        assertSoftly(sut) {
            it?.hasLocalClassWithName("sampleclass1") shouldBeEqualTo false
            it?.hasLocalClassWithName("sampleclass1", ignoreCase = true) shouldBeEqualTo true
            it?.hasLocalClassWithName("otherclass") shouldBeEqualTo false
            it?.hasLocalClassWithName("otherclass", ignoreCase = true) shouldBeEqualTo false
            it?.hasLocalClassWithName("sampleclass1", "otherName") shouldBeEqualTo false
            it?.hasLocalClassWithName("sampleclass1", "otherName", ignoreCase = true) shouldBeEqualTo true
            it?.hasLocalClassWithName(listOf("sampleclass1")) shouldBeEqualTo false
            it?.hasLocalClassWithName(listOf("sampleclass1"), ignoreCase = true) shouldBeEqualTo true
            it?.hasLocalClassWithName(listOf("otherclass")) shouldBeEqualTo false
            it?.hasLocalClassWithName(listOf("otherclass"), ignoreCase = true) shouldBeEqualTo false
            it?.hasLocalClassWithName(listOf("sampleclass1", "otherName")) shouldBeEqualTo false
            it?.hasLocalClassWithName(listOf("sampleclass1", "otherName"), ignoreCase = true) shouldBeEqualTo true
            it?.hasLocalClassesWithAllNames("sampleclass1") shouldBeEqualTo false
            it?.hasLocalClassesWithAllNames("sampleclass1", ignoreCase = true) shouldBeEqualTo true
            it?.hasLocalClassesWithAllNames("sampleclass1", "sampleclass2") shouldBeEqualTo false
            it?.hasLocalClassesWithAllNames("sampleclass1", "sampleclass2", ignoreCase = true) shouldBeEqualTo true
            it?.hasLocalClassesWithAllNames("sampleclass1", "otherclass") shouldBeEqualTo false
            it?.hasLocalClassesWithAllNames("sampleclass1", "otherclass", ignoreCase = true) shouldBeEqualTo false
            it?.hasLocalClassesWithAllNames(listOf("sampleclass1")) shouldBeEqualTo false
            it?.hasLocalClassesWithAllNames(listOf("sampleclass1"), ignoreCase = true) shouldBeEqualTo true
            it?.hasLocalClassesWithAllNames(listOf("sampleclass1", "sampleclass2")) shouldBeEqualTo false
            it?.hasLocalClassesWithAllNames(listOf("sampleclass1", "sampleclass2"), ignoreCase = true) shouldBeEqualTo true
            it?.hasLocalClassesWithAllNames(listOf("sampleclass1", "otherclass")) shouldBeEqualTo false
            it?.hasLocalClassesWithAllNames(listOf("sampleclass1", "otherclass"), ignoreCase = true) shouldBeEqualTo false
        }
    }

    private fun getSnippetFile(fileName: String) = getSnippetKoScope("core/declaration/kogetter/snippet/forkolocalclassprovider/", fileName)
}
