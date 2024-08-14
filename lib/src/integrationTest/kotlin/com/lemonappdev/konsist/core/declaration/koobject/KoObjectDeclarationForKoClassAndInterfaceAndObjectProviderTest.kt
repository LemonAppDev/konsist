package com.lemonappdev.konsist.core.declaration.koobject

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoObjectDeclarationForKoClassAndInterfaceAndObjectProviderTest {
    @Test
    fun `object-has-no-classes-and-interfaces-and-objects`() {
        // given
        val sut =
            getSnippetFile("object-has-no-classes-and-interfaces-and-objects")
                .objects()
                .first()

        // then
        assertSoftly(sut) {
            classesAndInterfacesAndObjects() shouldBeEqualTo emptyList()
            hasClassesOrInterfacesOrObjects() shouldBeEqualTo false
            hasClassOrInterfaceOrObjectWithName(emptyList()) shouldBeEqualTo false
            hasClassOrInterfaceOrObjectWithName(emptySet()) shouldBeEqualTo false
            hasClassesAndInterfacesAndObjectsWithAllNames(emptyList()) shouldBeEqualTo false
            hasClassesAndInterfacesAndObjectsWithAllNames(emptySet()) shouldBeEqualTo false
            hasClassOrInterfaceOrObjectWithName("SampleClass") shouldBeEqualTo false
            hasClassOrInterfaceOrObjectWithName(listOf("SampleClass")) shouldBeEqualTo false
            hasClassOrInterfaceOrObjectWithName(setOf("SampleClass")) shouldBeEqualTo false
            hasClassOrInterfaceOrObjectWithName("SampleInterface") shouldBeEqualTo false
            hasClassOrInterfaceOrObjectWithName(listOf("SampleInterface")) shouldBeEqualTo false
            hasClassOrInterfaceOrObjectWithName(setOf("SampleInterface")) shouldBeEqualTo false
            hasClassesAndInterfacesAndObjectsWithAllNames("SampleClass", "SampleInterface") shouldBeEqualTo false
            hasClassesAndInterfacesAndObjectsWithAllNames(
                listOf(
                    "SampleClass",
                    "SampleInterface"
                )
            ) shouldBeEqualTo false
            hasClassesAndInterfacesAndObjectsWithAllNames(setOf("SampleClass", "SampleInterface")) shouldBeEqualTo false
            hasClassOrInterfaceOrObject { it.name == "SampleClass" } shouldBeEqualTo false
            hasClassOrInterfaceOrObject { it.name == "SampleInterface" } shouldBeEqualTo false
            hasAllClassesAndInterfacesAndObjects { it.hasNameStartingWith("Sample") } shouldBeEqualTo true
        }
    }

    @Test
    fun `object-has-class-and-interface-and-object`() {
        // given
        val sut =
            getSnippetFile("object-has-class-and-interface-and-object")
                .objects()
                .first()

        // then
        assertSoftly(sut) {
            hasClassesOrInterfacesOrObjects() shouldBeEqualTo true
            hasClassOrInterfaceOrObjectWithName(emptyList()) shouldBeEqualTo true
            hasClassOrInterfaceOrObjectWithName(emptySet()) shouldBeEqualTo true
            hasClassesAndInterfacesAndObjectsWithAllNames(emptyList()) shouldBeEqualTo true
            hasClassesAndInterfacesAndObjectsWithAllNames(emptySet()) shouldBeEqualTo true
            hasClassOrInterfaceOrObjectWithName("SampleClass") shouldBeEqualTo true
            hasClassOrInterfaceOrObjectWithName("SampleInterface") shouldBeEqualTo true
            hasClassOrInterfaceOrObjectWithName("SampleObject") shouldBeEqualTo true
            hasClassOrInterfaceOrObjectWithName("SampleClass", "OtherInterface") shouldBeEqualTo true
            hasClassOrInterfaceOrObjectWithName(listOf("SampleClass")) shouldBeEqualTo true
            hasClassOrInterfaceOrObjectWithName(listOf("SampleInterface")) shouldBeEqualTo true
            hasClassOrInterfaceOrObjectWithName(listOf("SampleObject")) shouldBeEqualTo true
            hasClassOrInterfaceOrObjectWithName(listOf("SampleClass", "OtherInterface")) shouldBeEqualTo true
            hasClassOrInterfaceOrObjectWithName(setOf("SampleClass")) shouldBeEqualTo true
            hasClassOrInterfaceOrObjectWithName(setOf("SampleInterface")) shouldBeEqualTo true
            hasClassOrInterfaceOrObjectWithName(setOf("SampleObject")) shouldBeEqualTo true
            hasClassOrInterfaceOrObjectWithName(setOf("SampleClass", "OtherInterface")) shouldBeEqualTo true
            hasClassesAndInterfacesAndObjectsWithAllNames("SampleClass") shouldBeEqualTo true
            hasClassesAndInterfacesAndObjectsWithAllNames(
                "SampleClass",
                "SampleInterface",
                "SampleObject"
            ) shouldBeEqualTo true
            hasClassesAndInterfacesAndObjectsWithAllNames("SampleClass", "SampleInterface") shouldBeEqualTo true
            hasClassesAndInterfacesAndObjectsWithAllNames("SampleClass", "OtherInterface") shouldBeEqualTo false
            hasClassesAndInterfacesAndObjectsWithAllNames(listOf("SampleClass")) shouldBeEqualTo true
            hasClassesAndInterfacesAndObjectsWithAllNames(listOf("SampleClass", "SampleInterface")) shouldBeEqualTo true
            hasClassesAndInterfacesAndObjectsWithAllNames(listOf("SampleClass", "OtherInterface")) shouldBeEqualTo false
            hasClassesAndInterfacesAndObjectsWithAllNames(
                listOf(
                    "SampleClass",
                    "SampleInterface",
                    "SampleObject"
                )
            ) shouldBeEqualTo true
            hasClassesAndInterfacesAndObjectsWithAllNames(setOf("SampleClass")) shouldBeEqualTo true
            hasClassesAndInterfacesAndObjectsWithAllNames(setOf("SampleClass", "SampleInterface")) shouldBeEqualTo true
            hasClassesAndInterfacesAndObjectsWithAllNames(setOf("SampleClass", "OtherInterface")) shouldBeEqualTo false
            hasClassesAndInterfacesAndObjectsWithAllNames(
                setOf(
                    "SampleClass",
                    "SampleInterface",
                    "SampleObject"
                )
            ) shouldBeEqualTo true
            hasClassOrInterfaceOrObject { it.name == "SampleClass" } shouldBeEqualTo true
            hasClassOrInterfaceOrObject { it.name == "SampleInterface" } shouldBeEqualTo true
            hasClassOrInterfaceOrObject { it.hasNameEndingWith("Class") } shouldBeEqualTo true
            hasClassOrInterfaceOrObject { it.hasNameEndingWith("OtherClass") } shouldBeEqualTo false
            hasAllClassesAndInterfacesAndObjects { it.hasNameStartingWith("Sample") } shouldBeEqualTo true
            hasAllClassesAndInterfacesAndObjects { it.hasNameEndingWith("Class") } shouldBeEqualTo false
        }
    }

    @Test
    fun `object-contains-nested-and-local-classes-and-interfaces-and-objects includeNested true includeLocal true`() {
        // given
        val sut =
            getSnippetFile("object-contains-nested-and-local-classes-and-interfaces-and-objects")
                .objects()
                .first()

        // then
        val expected = listOf(
            "SampleLocalClass",
            "SampleClassNestedInsideObject",
            "SampleInterfaceNestedInsideObject",
            "SampleObject",
            "SampleObjectNestedInsideObject"
        )

        sut.classesAndInterfacesAndObjects(includeNested = true, includeLocal = true)
            .map { it.name }
            .shouldBeEqualTo(expected)
    }

    @Test
    fun `object-contains-nested-and-local-classes-and-interfaces-and-objects includeNested true includeLocal false`() {
        // given
        val sut =
            getSnippetFile("object-contains-nested-and-local-classes-and-interfaces-and-objects")
                .objects()
                .first()

        // then
        val expected = listOf(
            "SampleClassNestedInsideObject",
            "SampleInterfaceNestedInsideObject",
            "SampleObject",
            "SampleObjectNestedInsideObject"
        )

        sut.classesAndInterfacesAndObjects(includeNested = true, includeLocal = false)
            .map { it.name }
            .shouldBeEqualTo(expected)
    }

    @Test
    fun `object-contains-nested-and-local-classes-and-interfaces-and-objects includeNested false includeLocal true`() {
        // given
        val sut =
            getSnippetFile("object-contains-nested-and-local-classes-and-interfaces-and-objects")
                .objects()
                .first()

        // then
        val expected = listOf("SampleLocalClass", "SampleObject")

        sut.classesAndInterfacesAndObjects(includeNested = false, includeLocal = true)
            .map { it.name }
            .shouldBeEqualTo(expected)
    }

    @Test
    fun `object-contains-nested-and-local-classes-and-interfaces-and-objects includeNested false includeLocal false`() {
        // given
        val sut =
            getSnippetFile("object-contains-nested-and-local-classes-and-interfaces-and-objects")
                .objects()
                .first()

        // then
        val expected = listOf("SampleObject")

        sut.classesAndInterfacesAndObjects(includeNested = false, includeLocal = false)
            .map { it.name }
            .shouldBeEqualTo(expected)
    }

    @Test
    fun `count-classes-and-interfaces-and-objects`() {
        // given
        val sut =
            getSnippetFile("count-classes-and-interfaces-and-objects")
                .objects()
                .first()

        // then
        assertSoftly(sut) {
            numClassesAndInterfacesAndObjects(includeNested = true, includeLocal = true) shouldBeEqualTo 5
            numClassesAndInterfacesAndObjects(includeNested = true, includeLocal = false) shouldBeEqualTo 4
            numClassesAndInterfacesAndObjects(includeNested = false, includeLocal = true) shouldBeEqualTo 2
            numClassesAndInterfacesAndObjects(includeNested = false, includeLocal = false) shouldBeEqualTo 1
            countClassesAndInterfacesAndObjects(
                includeNested = false,
                includeLocal = false
            ) { it.hasPrivateModifier } shouldBeEqualTo 1
            countClassesAndInterfacesAndObjects { it.hasPrivateModifier } shouldBeEqualTo 4
            countClassesAndInterfacesAndObjects { it.name == "SampleClass" && it.hasInternalModifier } shouldBeEqualTo 0
        }
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/koobject/snippet/forkoclassandinterfaceandobjectprovider/", fileName)
}
