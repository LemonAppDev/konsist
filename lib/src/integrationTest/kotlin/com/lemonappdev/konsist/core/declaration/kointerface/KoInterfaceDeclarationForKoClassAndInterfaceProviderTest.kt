package com.lemonappdev.konsist.core.declaration.kointerface

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import com.lemonappdev.konsist.api.declaration.KoClassDeclaration
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoInterfaceDeclarationForKoClassAndInterfaceProviderTest {
    @Test
    fun `interface-has-no-classes-and-interfaces`() {
        // given
        val sut =
            getSnippetFile("interface-has-no-classes-and-interfaces")
                .interfaces()
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
    fun `interface-has-class-and-interface`() {
        // given
        val sut =
            getSnippetFile("interface-has-class-and-interface")
                .interfaces()
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
    fun `interface-contains-nested-and-local-classes-and-interfaces includeNested true includeLocal true`() {
        // given
        val sut =
            getSnippetFile("interface-contains-nested-and-local-classes-and-interfaces")
                .interfaces()
                .first()

        // then
        val expected = listOf("SampleLocalClass", "SampleClassNestedInsideObject", "SampleInterfaceNestedInsideObject")

        sut.classesAndInterfaces(includeNested = true, includeLocal = true)
            .map { it.name }
            .shouldBeEqualTo(expected)
    }

    @Test
    fun `interface-contains-nested-and-local-classes-and-interfaces includeNested true includeLocal false`() {
        // given
        val sut =
            getSnippetFile("interface-contains-nested-and-local-classes-and-interfaces")
                .interfaces()
                .first()

        // then
        val expected = listOf("SampleClassNestedInsideObject", "SampleInterfaceNestedInsideObject")

        sut.classesAndInterfaces(includeNested = true, includeLocal = false)
            .map { it.name }
            .shouldBeEqualTo(expected)
    }

    @Test
    fun `interface-contains-nested-and-local-classes-and-interfaces includeNested false includeLocal true`() {
        // given
        val sut =
            getSnippetFile("interface-contains-nested-and-local-classes-and-interfaces")
                .interfaces()
                .first()

        // then
        val expected = listOf("SampleLocalClass")

        sut.classesAndInterfaces(includeNested = false, includeLocal = true)
            .map { it.name }
            .shouldBeEqualTo(expected)
    }

    @Test
    fun `interface-contains-nested-and-local-classes-and-interfaces includeNested false includeLocal false`() {
        // given
        val sut =
            getSnippetFile("interface-contains-nested-and-local-classes-and-interfaces")
                .interfaces()
                .first()

        // then
        val expected = emptyList<KoClassDeclaration>()

        sut.classesAndInterfaces(includeNested = false, includeLocal = false)
            .map { it.name }
            .shouldBeEqualTo(expected)
    }

    @Test
    fun `count-classes-and-interfaces`() {
        // given
        val sut =
            getSnippetFile("count-classes-and-interfaces")
                .interfaces()
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

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/kointerface/snippet/forkoclassandinterfaceprovider/", fileName)
}
