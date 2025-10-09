package com.lemonappdev.konsist.core.declaration.kointerface

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoInterfaceDeclarationForKoClassAndObjectProviderTest {
    @Test
    fun `interface-has-no-classes-and-objects`() {
        // given
        val sut =
            getSnippetFile("interface-has-no-classes-and-objects")
                .interfaces()
                .first()

        // then
        assertSoftly(sut) {
            classesAndObjects() shouldBeEqualTo emptyList()
            hasClassesOrObjects() shouldBeEqualTo false
            hasClassOrObjectWithName(emptyList()) shouldBeEqualTo false
            hasClassOrObjectWithName(emptySet()) shouldBeEqualTo false
            hasClassesAndObjectsWithAllNames(emptyList()) shouldBeEqualTo false
            hasClassesAndObjectsWithAllNames(emptySet()) shouldBeEqualTo false
            hasClassOrObjectWithName("SampleClass") shouldBeEqualTo false
            hasClassOrObjectWithName(listOf("SampleClass")) shouldBeEqualTo false
            hasClassOrObjectWithName(setOf("SampleClass")) shouldBeEqualTo false
            hasClassOrObjectWithName("SampleObject") shouldBeEqualTo false
            hasClassOrObjectWithName(listOf("SampleObject")) shouldBeEqualTo false
            hasClassOrObjectWithName(setOf("SampleObject")) shouldBeEqualTo false
            hasClassesAndObjectsWithAllNames("SampleClass", "SampleObject") shouldBeEqualTo false
            hasClassesAndObjectsWithAllNames(listOf("SampleClass", "SampleObject")) shouldBeEqualTo false
            hasClassesAndObjectsWithAllNames(setOf("SampleClass", "SampleObject")) shouldBeEqualTo false
            hasClassOrObject { it.name == "SampleClass" } shouldBeEqualTo false
            hasClassOrObject { it.name == "SampleObject" } shouldBeEqualTo false
            hasAllClassesAndObjects { it.hasNameStartingWith("Sample") } shouldBeEqualTo true
        }
    }

    @Test
    fun `interface-has-class-and-object`() {
        // given
        val sut =
            getSnippetFile("interface-has-class-and-object")
                .interfaces()
                .first()

        // then
        assertSoftly(sut) {
            hasClassesOrObjects() shouldBeEqualTo true
            hasClassOrObjectWithName(emptyList()) shouldBeEqualTo true
            hasClassOrObjectWithName(emptySet()) shouldBeEqualTo true
            hasClassesAndObjectsWithAllNames(emptyList()) shouldBeEqualTo true
            hasClassesAndObjectsWithAllNames(emptySet()) shouldBeEqualTo true
            hasClassOrObjectWithName("SampleClass") shouldBeEqualTo true
            hasClassOrObjectWithName("SampleObject") shouldBeEqualTo true
            hasClassOrObjectWithName("SampleClass", "OtherObject") shouldBeEqualTo true
            hasClassOrObjectWithName(listOf("SampleClass")) shouldBeEqualTo true
            hasClassOrObjectWithName(listOf("SampleObject")) shouldBeEqualTo true
            hasClassOrObjectWithName(listOf("SampleClass", "OtherObject")) shouldBeEqualTo true
            hasClassOrObjectWithName(setOf("SampleClass")) shouldBeEqualTo true
            hasClassOrObjectWithName(setOf("SampleObject")) shouldBeEqualTo true
            hasClassOrObjectWithName(setOf("SampleClass", "OtherObject")) shouldBeEqualTo true
            hasClassesAndObjectsWithAllNames("SampleClass") shouldBeEqualTo true
            hasClassesAndObjectsWithAllNames("SampleClass", "SampleObject") shouldBeEqualTo true
            hasClassesAndObjectsWithAllNames("SampleClass", "OtherObject") shouldBeEqualTo false
            hasClassesAndObjectsWithAllNames(listOf("SampleClass")) shouldBeEqualTo true
            hasClassesAndObjectsWithAllNames(listOf("SampleClass", "SampleObject")) shouldBeEqualTo true
            hasClassesAndObjectsWithAllNames(listOf("SampleClass", "OtherObject")) shouldBeEqualTo false
            hasClassesAndObjectsWithAllNames(setOf("SampleClass")) shouldBeEqualTo true
            hasClassesAndObjectsWithAllNames(setOf("SampleClass", "SampleObject")) shouldBeEqualTo true
            hasClassesAndObjectsWithAllNames(setOf("SampleClass", "OtherObject")) shouldBeEqualTo false
            hasClassOrObject { it.name == "SampleClass" } shouldBeEqualTo true
            hasClassOrObject { it.name == "SampleObject" } shouldBeEqualTo true
            hasClassOrObject { it.hasNameEndingWith("Class") } shouldBeEqualTo true
            hasClassOrObject { it.hasNameEndingWith("Class") || it.hasNameEndingWith("Object") } shouldBeEqualTo true
            hasAllClassesAndObjects { it.hasNameStartingWith("Sample") } shouldBeEqualTo true
            hasAllClassesAndObjects { it.hasNameEndingWith("Class") } shouldBeEqualTo false
        }
    }

    @Test
    fun `interface-contains-nested-and-local-classes-and-objects includeNested true includeLocal true`() {
        // given
        val sut =
            getSnippetFile("interface-contains-nested-and-local-classes-and-objects")
                .interfaces()
                .first()

        // then
        val expected = listOf("SampleLocalClass", "SampleClassNestedInsideObject", "SampleObject", "SampleObjectNestedInsideObject")

        sut
            .classesAndObjects(includeNested = true, includeLocal = true)
            .map { it.name }
            .shouldBeEqualTo(expected)
    }

    @Test
    fun `interface-contains-nested-and-local-classes-and-objects includeNested true includeLocal false`() {
        // given
        val sut =
            getSnippetFile("interface-contains-nested-and-local-classes-and-objects")
                .interfaces()
                .first()

        // then
        val expected = listOf("SampleClassNestedInsideObject", "SampleObject", "SampleObjectNestedInsideObject")

        sut
            .classesAndObjects(includeNested = true, includeLocal = false)
            .map { it.name }
            .shouldBeEqualTo(expected)
    }

    @Test
    fun `interface-contains-nested-and-local-classes-and-objects includeNested false includeLocal true`() {
        // given
        val sut =
            getSnippetFile("interface-contains-nested-and-local-classes-and-objects")
                .interfaces()
                .first()

        // then
        val expected = listOf("SampleLocalClass", "SampleObject")

        sut
            .classesAndObjects(includeNested = false, includeLocal = true)
            .map { it.name }
            .shouldBeEqualTo(expected)
    }

    @Test
    fun `interface-contains-nested-and-local-classes-and-objects includeNested false includeLocal false`() {
        // given
        val sut =
            getSnippetFile("interface-contains-nested-and-local-classes-and-objects")
                .interfaces()
                .first()

        // then
        val expected = listOf("SampleObject")

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
                .interfaces()
                .first()

        // then
        assertSoftly(sut) {
            numClassesAndObjects(includeNested = true, includeLocal = true) shouldBeEqualTo 4
            numClassesAndObjects(includeNested = true, includeLocal = false) shouldBeEqualTo 3
            numClassesAndObjects(includeNested = false, includeLocal = true) shouldBeEqualTo 2
            numClassesAndObjects(includeNested = false, includeLocal = false) shouldBeEqualTo 1
            countClassesAndObjects(includeNested = false, includeLocal = false) { it.hasPrivateModifier } shouldBeEqualTo 1
            countClassesAndObjects { it.hasPrivateModifier } shouldBeEqualTo 3
            countClassesAndObjects { it.name == "SampleClass" && it.hasInternalModifier } shouldBeEqualTo 0
        }
    }

    @Test
    fun `interface-has-no-classes-and-objects-ignore-case`() {
        // given
        val sut =
            getSnippetFile("interface-has-no-classes-and-objects-ignore-case")
                .interfaces()
                .first()

        // then
        assertSoftly(sut) {
            hasClassOrObjectWithName("sampleclass") shouldBeEqualTo false
            hasClassOrObjectWithName("sampleclass", ignoreCase = true) shouldBeEqualTo false
            hasClassOrObjectWithName(listOf("sampleclass")) shouldBeEqualTo false
            hasClassOrObjectWithName(listOf("sampleclass"), ignoreCase = true) shouldBeEqualTo false
            hasClassOrObjectWithName(setOf("sampleclass")) shouldBeEqualTo false
            hasClassOrObjectWithName(setOf("sampleclass"), ignoreCase = true) shouldBeEqualTo false
            hasClassesAndObjectsWithAllNames("sampleclass1", "sampleobject") shouldBeEqualTo false
            hasClassesAndObjectsWithAllNames("sampleclass1", "sampleobject", ignoreCase = true) shouldBeEqualTo false
            hasClassesAndObjectsWithAllNames(listOf("sampleclass", "sampleobject")) shouldBeEqualTo false
            hasClassesAndObjectsWithAllNames(listOf("sampleclass", "sampleobject"), ignoreCase = true) shouldBeEqualTo false
            hasClassesAndObjectsWithAllNames(setOf("sampleclass", "sampleobject")) shouldBeEqualTo false
            hasClassesAndObjectsWithAllNames(setOf("sampleclass", "sampleobject"), ignoreCase = true) shouldBeEqualTo false
        }
    }

    @Test
    fun `interface-has-class-and-object-ignore-case`() {
        // given
        val sut =
            getSnippetFile("interface-has-class-and-object-ignore-case")
                .interfaces()
                .first()

        // then
        assertSoftly(sut) {
            hasClassOrObjectWithName("sampleclass") shouldBeEqualTo false
            hasClassOrObjectWithName("sampleclass", ignoreCase = true) shouldBeEqualTo true
            hasClassOrObjectWithName("otherclass") shouldBeEqualTo false
            hasClassOrObjectWithName("otherclass", ignoreCase = true) shouldBeEqualTo false
            hasClassOrObjectWithName("sampleclass", "otherName") shouldBeEqualTo false
            hasClassOrObjectWithName("sampleclass", "otherName", ignoreCase = true) shouldBeEqualTo true
            hasClassOrObjectWithName(listOf("sampleclass")) shouldBeEqualTo false
            hasClassOrObjectWithName(listOf("sampleclass"), ignoreCase = true) shouldBeEqualTo true
            hasClassOrObjectWithName(listOf("otherclass")) shouldBeEqualTo false
            hasClassOrObjectWithName(listOf("otherclass"), ignoreCase = true) shouldBeEqualTo false
            hasClassOrObjectWithName(listOf("sampleclass", "otherName")) shouldBeEqualTo false
            hasClassOrObjectWithName(listOf("sampleclass", "otherName"), ignoreCase = true) shouldBeEqualTo true
            hasClassesAndObjectsWithAllNames("sampleclass") shouldBeEqualTo false
            hasClassesAndObjectsWithAllNames("sampleclass", ignoreCase = true) shouldBeEqualTo true
            hasClassesAndObjectsWithAllNames("sampleclass", "sampleobject") shouldBeEqualTo false
            hasClassesAndObjectsWithAllNames("sampleclass", "sampleobject", ignoreCase = true) shouldBeEqualTo true
            hasClassesAndObjectsWithAllNames("sampleclass", "otherclass") shouldBeEqualTo false
            hasClassesAndObjectsWithAllNames("sampleclass", "otherclass", ignoreCase = true) shouldBeEqualTo false
            hasClassesAndObjectsWithAllNames(listOf("sampleclass")) shouldBeEqualTo false
            hasClassesAndObjectsWithAllNames(listOf("sampleclass"), ignoreCase = true) shouldBeEqualTo true
            hasClassesAndObjectsWithAllNames(listOf("sampleclass", "sampleobject")) shouldBeEqualTo false
            hasClassesAndObjectsWithAllNames(listOf("sampleclass", "sampleobject"), ignoreCase = true) shouldBeEqualTo true
            hasClassesAndObjectsWithAllNames(listOf("sampleclass", "otherclass")) shouldBeEqualTo false
            hasClassesAndObjectsWithAllNames(listOf("sampleclass", "otherclass"), ignoreCase = true) shouldBeEqualTo false
        }
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/kointerface/snippet/forkoclassandobjectprovider/", fileName)
}
