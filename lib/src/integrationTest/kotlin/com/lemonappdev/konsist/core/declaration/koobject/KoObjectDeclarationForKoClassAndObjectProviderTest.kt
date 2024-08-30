package com.lemonappdev.konsist.core.declaration.koobject

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoObjectDeclarationForKoClassAndObjectProviderTest {
    @Test
    fun `object-has-no-classes-and-objects`() {
        // given
        val sut =
            getSnippetFile("object-has-no-classes-and-objects")
                .objects()
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
    fun `object-has-class-and-object`() {
        // given
        val sut =
            getSnippetFile("object-has-class-and-object")
                .objects()
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
    fun `object-contains-nested-and-local-classes-and-objects includeNested true includeLocal true`() {
        // given
        val sut =
            getSnippetFile("object-contains-nested-and-local-classes-and-objects")
                .objects()
                .first()

        // then
        val expected =
            listOf(
                "SampleLocalClass",
                "SampleClassNestedInsideObject",
                "SampleObject",
                "SampleObjectNestedInsideObject",
            )

        sut
            .classesAndObjects(includeNested = true, includeLocal = true)
            .map { it.name }
            .shouldBeEqualTo(expected)
    }

    @Test
    fun `object-contains-nested-and-local-classes-and-objects includeNested true includeLocal false`() {
        // given
        val sut =
            getSnippetFile("object-contains-nested-and-local-classes-and-objects")
                .objects()
                .first()

        // then
        val expected = listOf("SampleClassNestedInsideObject", "SampleObject", "SampleObjectNestedInsideObject")

        sut
            .classesAndObjects(includeNested = true, includeLocal = false)
            .map { it.name }
            .shouldBeEqualTo(expected)
    }

    @Test
    fun `object-contains-nested-and-local-classes-and-objects includeNested false includeLocal true`() {
        // given
        val sut =
            getSnippetFile("object-contains-nested-and-local-classes-and-objects")
                .objects()
                .first()

        // then
        val expected = listOf("SampleLocalClass", "SampleObject")

        sut
            .classesAndObjects(includeNested = false, includeLocal = true)
            .map { it.name }
            .shouldBeEqualTo(expected)
    }

    @Test
    fun `object-contains-nested-and-local-classes-and-objects includeNested false includeLocal false`() {
        // given
        val sut =
            getSnippetFile("object-contains-nested-and-local-classes-and-objects")
                .objects()
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
                .objects()
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
            countClassesAndObjects { it.name == "SampleClass" && it.hasInternalModifier } shouldBeEqualTo 0
        }
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/koobject/snippet/forkoclassandobjectprovider/", fileName)
}
