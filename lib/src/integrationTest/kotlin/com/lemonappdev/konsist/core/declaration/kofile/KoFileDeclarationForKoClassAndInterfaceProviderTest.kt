package com.lemonappdev.konsist.core.declaration.kofile

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import com.lemonappdev.konsist.api.declaration.KoClassDeclaration
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoFileDeclarationForKoClassAndInterfaceProviderTest {
    @Test
    fun `file-has-no-classes-and-interfaces`() {
        // given
        val sut =
            getSnippetFile("file-has-no-classes-and-interfaces")
                .files
                .first()

        // then
        assertSoftly(sut) {
            classesAndInterfaces() shouldBeEqualTo emptyList()
            hasClassesOrInterfaces() shouldBeEqualTo false
            hasClassOrInterfaceWithName(emptyList()) shouldBeEqualTo false
            hasClassOrInterfaceWithName(emptySet()) shouldBeEqualTo false
            hasClassesAndInterfacesWithAllNames(emptyList()) shouldBeEqualTo false
            hasClassesAndInterfacesWithAllNames(emptySet()) shouldBeEqualTo false
            hasClassOrInterfaceWithName("SampleClass") shouldBeEqualTo false
            hasClassOrInterfaceWithName(listOf("SampleClass")) shouldBeEqualTo false
            hasClassOrInterfaceWithName(setOf("SampleClass")) shouldBeEqualTo false
            hasClassOrInterfaceWithName("SampleInterface") shouldBeEqualTo false
            hasClassOrInterfaceWithName(listOf("SampleInterface")) shouldBeEqualTo false
            hasClassOrInterfaceWithName(setOf("SampleInterface")) shouldBeEqualTo false
            hasClassesAndInterfacesWithAllNames("SampleClass", "SampleInterface") shouldBeEqualTo false
            hasClassesAndInterfacesWithAllNames(listOf("SampleClass", "SampleInterface")) shouldBeEqualTo false
            hasClassesAndInterfacesWithAllNames(setOf("SampleClass", "SampleInterface")) shouldBeEqualTo false
            hasClassOrInterface { it.name == "SampleClass" } shouldBeEqualTo false
            hasClassOrInterface { it.name == "SampleInterface" } shouldBeEqualTo false
            hasAllClassesAndInterfaces { it.hasNameStartingWith("Sample") } shouldBeEqualTo true
        }
    }

    @Test
    fun `file-has-class-and-interface`() {
        // given
        val sut =
            getSnippetFile("file-has-class-and-interface")
                .files
                .first()

        // then
        assertSoftly(sut) {
            hasClassesOrInterfaces() shouldBeEqualTo true
            hasClassOrInterfaceWithName(emptyList()) shouldBeEqualTo true
            hasClassOrInterfaceWithName(emptySet()) shouldBeEqualTo true
            hasClassesAndInterfacesWithAllNames(emptyList()) shouldBeEqualTo true
            hasClassesAndInterfacesWithAllNames(emptySet()) shouldBeEqualTo true
            hasClassOrInterfaceWithName("SampleClass") shouldBeEqualTo true
            hasClassOrInterfaceWithName("SampleInterface") shouldBeEqualTo true
            hasClassOrInterfaceWithName("SampleClass", "OtherInterface") shouldBeEqualTo true
            hasClassOrInterfaceWithName(listOf("SampleClass")) shouldBeEqualTo true
            hasClassOrInterfaceWithName(listOf("SampleInterface")) shouldBeEqualTo true
            hasClassOrInterfaceWithName(listOf("SampleClass", "OtherInterface")) shouldBeEqualTo true
            hasClassOrInterfaceWithName(setOf("SampleClass")) shouldBeEqualTo true
            hasClassOrInterfaceWithName(setOf("SampleInterface")) shouldBeEqualTo true
            hasClassOrInterfaceWithName(setOf("SampleClass", "OtherInterface")) shouldBeEqualTo true
            hasClassesAndInterfacesWithAllNames("SampleClass") shouldBeEqualTo true
            hasClassesAndInterfacesWithAllNames("SampleClass", "SampleInterface") shouldBeEqualTo true
            hasClassesAndInterfacesWithAllNames("SampleClass", "OtherInterface") shouldBeEqualTo false
            hasClassesAndInterfacesWithAllNames(listOf("SampleClass")) shouldBeEqualTo true
            hasClassesAndInterfacesWithAllNames(listOf("SampleClass", "SampleInterface")) shouldBeEqualTo true
            hasClassesAndInterfacesWithAllNames(listOf("SampleClass", "OtherInterface")) shouldBeEqualTo false
            hasClassesAndInterfacesWithAllNames(setOf("SampleClass")) shouldBeEqualTo true
            hasClassesAndInterfacesWithAllNames(setOf("SampleClass", "SampleInterface")) shouldBeEqualTo true
            hasClassesAndInterfacesWithAllNames(setOf("SampleClass", "OtherInterface")) shouldBeEqualTo false
            hasClassOrInterface { it.name == "SampleClass" } shouldBeEqualTo true
            hasClassOrInterface { it.name == "SampleInterface" } shouldBeEqualTo true
            hasClassOrInterface { it.hasNameEndingWith("Class") } shouldBeEqualTo true
            hasClassOrInterface { it.hasNameEndingWith("Class") || it.hasNameEndingWith("Interface") } shouldBeEqualTo true
            hasAllClassesAndInterfaces { it.hasNameStartingWith("Sample") } shouldBeEqualTo true
            hasAllClassesAndInterfaces { it.hasNameEndingWith("Class") } shouldBeEqualTo false
        }
    }

    @Test
    fun `file-contains-nested-and-local-classes-and-interfaces includeNested true includeLocal true`() {
        // given
        val sut =
            getSnippetFile("file-contains-nested-and-local-classes-and-interfaces")
                .files
                .first()

        // then
        val expected = listOf("SampleLocalClass", "SampleClassNestedInsideObject", "SampleInterfaceNestedInsideObject")

        sut
            .classesAndInterfaces(includeNested = true, includeLocal = true)
            .map { it.name }
            .shouldBeEqualTo(expected)
    }

    @Test
    fun `file-contains-nested-and-local-classes-and-interfaces includeNested true includeLocal false`() {
        // given
        val sut =
            getSnippetFile("file-contains-nested-and-local-classes-and-interfaces")
                .files
                .first()

        // then
        val expected = listOf("SampleClassNestedInsideObject", "SampleInterfaceNestedInsideObject")

        sut
            .classesAndInterfaces(includeNested = true, includeLocal = false)
            .map { it.name }
            .shouldBeEqualTo(expected)
    }

    @Test
    fun `file-contains-nested-and-local-classes-and-interfaces includeNested false includeLocal true`() {
        // given
        val sut =
            getSnippetFile("file-contains-nested-and-local-classes-and-interfaces")
                .files
                .first()

        // then
        val expected = listOf("SampleLocalClass")

        sut
            .classesAndInterfaces(includeNested = false, includeLocal = true)
            .map { it.name }
            .shouldBeEqualTo(expected)
    }

    @Test
    fun `file-contains-nested-and-local-classes-and-interfaces includeNested false includeLocal false`() {
        // given
        val sut =
            getSnippetFile("file-contains-nested-and-local-classes-and-interfaces")
                .files
                .first()

        // then
        val expected = emptyList<KoClassDeclaration>()

        sut
            .classesAndInterfaces(includeNested = false, includeLocal = false)
            .map { it.name }
            .shouldBeEqualTo(expected)
    }

    @Test
    fun `count-classes-and-interfaces`() {
        // given
        val sut =
            getSnippetFile("count-classes-and-interfaces")
                .files
                .first()

        // then
        assertSoftly(sut) {
            numClassesAndInterfaces(includeNested = true, includeLocal = true) shouldBeEqualTo 4
            numClassesAndInterfaces(includeNested = true, includeLocal = false) shouldBeEqualTo 3
            numClassesAndInterfaces(includeNested = false, includeLocal = true) shouldBeEqualTo 2
            numClassesAndInterfaces(includeNested = false, includeLocal = false) shouldBeEqualTo 1
            countClassesAndInterfaces(includeNested = false, includeLocal = false) { it.hasPrivateModifier } shouldBeEqualTo 1
            countClassesAndInterfaces { it.hasPrivateModifier } shouldBeEqualTo 3
            countClassesAndInterfaces { it.name == "SampleClass" && it.hasInternalModifier } shouldBeEqualTo 0
        }
    }

    @Test
    fun `file-has-no-classes-and-interfaces-ignore-case`() {
        // given
        val sut =
            getSnippetFile("file-has-no-classes-and-interfaces-ignore-case")
                .files
                .first()

        // then
        assertSoftly(sut) {
            hasClassOrInterfaceWithName("sampleclass") shouldBeEqualTo false
            hasClassOrInterfaceWithName("sampleclass", ignoreCase = true) shouldBeEqualTo false
            hasClassOrInterfaceWithName(listOf("sampleclass")) shouldBeEqualTo false
            hasClassOrInterfaceWithName(listOf("sampleclass"), ignoreCase = true) shouldBeEqualTo false
            hasClassOrInterfaceWithName(setOf("sampleclass")) shouldBeEqualTo false
            hasClassOrInterfaceWithName(setOf("sampleclass"), ignoreCase = true) shouldBeEqualTo false
            hasClassesAndInterfacesWithAllNames("sampleclass1", "sampleinterface") shouldBeEqualTo false
            hasClassesAndInterfacesWithAllNames("sampleclass1", "sampleinterface", ignoreCase = true) shouldBeEqualTo false
            hasClassesAndInterfacesWithAllNames(listOf("sampleclass", "sampleinterface")) shouldBeEqualTo false
            hasClassesAndInterfacesWithAllNames(listOf("sampleclass", "sampleinterface"), ignoreCase = true) shouldBeEqualTo false
            hasClassesAndInterfacesWithAllNames(setOf("sampleclass", "sampleinterface")) shouldBeEqualTo false
            hasClassesAndInterfacesWithAllNames(setOf("sampleclass", "sampleinterface"), ignoreCase = true) shouldBeEqualTo false
        }
    }

    @Test
    fun `file-has-class-and-interface-ignore-case`() {
        // given
        val sut =
            getSnippetFile("file-has-class-and-interface-ignore-case")
                .files
                .first()

        // then
        assertSoftly(sut) {
            hasClassOrInterfaceWithName("sampleclass") shouldBeEqualTo false
            hasClassOrInterfaceWithName("sampleclass", ignoreCase = true) shouldBeEqualTo true
            hasClassOrInterfaceWithName("otherclass") shouldBeEqualTo false
            hasClassOrInterfaceWithName("otherclass", ignoreCase = true) shouldBeEqualTo false
            hasClassOrInterfaceWithName("sampleclass", "otherName") shouldBeEqualTo false
            hasClassOrInterfaceWithName("sampleclass", "otherName", ignoreCase = true) shouldBeEqualTo true
            hasClassOrInterfaceWithName(listOf("sampleclass")) shouldBeEqualTo false
            hasClassOrInterfaceWithName(listOf("sampleclass"), ignoreCase = true) shouldBeEqualTo true
            hasClassOrInterfaceWithName(listOf("otherclass")) shouldBeEqualTo false
            hasClassOrInterfaceWithName(listOf("otherclass"), ignoreCase = true) shouldBeEqualTo false
            hasClassOrInterfaceWithName(listOf("sampleclass", "otherName")) shouldBeEqualTo false
            hasClassOrInterfaceWithName(listOf("sampleclass", "otherName"), ignoreCase = true) shouldBeEqualTo true
            hasClassesAndInterfacesWithAllNames("sampleclass") shouldBeEqualTo false
            hasClassesAndInterfacesWithAllNames("sampleclass", ignoreCase = true) shouldBeEqualTo true
            hasClassesAndInterfacesWithAllNames("sampleclass", "sampleinterface") shouldBeEqualTo false
            hasClassesAndInterfacesWithAllNames("sampleclass", "sampleinterface", ignoreCase = true) shouldBeEqualTo true
            hasClassesAndInterfacesWithAllNames("sampleclass", "otherclass") shouldBeEqualTo false
            hasClassesAndInterfacesWithAllNames("sampleclass", "otherclass", ignoreCase = true) shouldBeEqualTo false
            hasClassesAndInterfacesWithAllNames(listOf("sampleclass")) shouldBeEqualTo false
            hasClassesAndInterfacesWithAllNames(listOf("sampleclass"), ignoreCase = true) shouldBeEqualTo true
            hasClassesAndInterfacesWithAllNames(listOf("sampleclass", "sampleinterface")) shouldBeEqualTo false
            hasClassesAndInterfacesWithAllNames(listOf("sampleclass", "sampleinterface"), ignoreCase = true) shouldBeEqualTo true
            hasClassesAndInterfacesWithAllNames(listOf("sampleclass", "otherclass")) shouldBeEqualTo false
            hasClassesAndInterfacesWithAllNames(listOf("sampleclass", "otherclass"), ignoreCase = true) shouldBeEqualTo false
        }
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/kofile/snippet/forkoclassandinterfaceprovider/", fileName)
}
