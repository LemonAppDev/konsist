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
            it?.containsLocalClass { it.name == "SampleClass" } shouldBeEqualTo false
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
            it?.containsLocalClass { it.name == "SampleClass1" } shouldBeEqualTo true
            it?.containsLocalClass { it.name == "OtherLocalClass" } shouldBeEqualTo false
        }
    }

    private fun getSnippetFile(fileName: String) = getSnippetKoScope("core/declaration/kogetter/snippet/forkolocalclassprovider/", fileName)
}
