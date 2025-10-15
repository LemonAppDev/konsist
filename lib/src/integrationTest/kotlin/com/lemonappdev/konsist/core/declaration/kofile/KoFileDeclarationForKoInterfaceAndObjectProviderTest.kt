package com.lemonappdev.konsist.core.declaration.kofile

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoFileDeclarationForKoInterfaceAndObjectProviderTest {
    @Test
    fun `file-has-no-interfaces-and-objects`() {
        // given
        val sut =
            getSnippetFile("file-has-no-interfaces-and-objects")
                .files
                .first()

        // then
        assertSoftly(sut) {
            interfacesAndObjects() shouldBeEqualTo emptyList()
            hasInterfacesOrObjects() shouldBeEqualTo false
            hasInterfaceOrObjectWithName(emptyList()) shouldBeEqualTo false
            hasInterfaceOrObjectWithName(emptySet()) shouldBeEqualTo false
            hasInterfacesAndObjectsWithAllNames(emptyList()) shouldBeEqualTo false
            hasInterfacesAndObjectsWithAllNames(emptySet()) shouldBeEqualTo false
            hasInterfaceOrObjectWithName("SampleInterface") shouldBeEqualTo false
            hasInterfaceOrObjectWithName(listOf("SampleInterface")) shouldBeEqualTo false
            hasInterfaceOrObjectWithName(setOf("SampleInterface")) shouldBeEqualTo false
            hasInterfaceOrObjectWithName("SampleObject") shouldBeEqualTo false
            hasInterfaceOrObjectWithName(listOf("SampleObject")) shouldBeEqualTo false
            hasInterfaceOrObjectWithName(setOf("SampleObject")) shouldBeEqualTo false
            hasInterfacesAndObjectsWithAllNames("SampleInterface", "SampleObject") shouldBeEqualTo false
            hasInterfacesAndObjectsWithAllNames(listOf("SampleInterface", "SampleObject")) shouldBeEqualTo false
            hasInterfacesAndObjectsWithAllNames(setOf("SampleInterface", "SampleObject")) shouldBeEqualTo false
            hasInterfaceOrObject { it.name == "SampleInterface" } shouldBeEqualTo false
            hasInterfaceOrObject { it.name == "SampleObject" } shouldBeEqualTo false
            hasAllInterfacesAndObjects { it.hasNameStartingWith("Sample") } shouldBeEqualTo true
        }
    }

    @Test
    fun `file-has-interface-and-object`() {
        // given
        val sut =
            getSnippetFile("file-has-interface-and-object")
                .files
                .first()

        // then
        assertSoftly(sut) {
            hasInterfacesOrObjects() shouldBeEqualTo true
            hasInterfaceOrObjectWithName(emptyList()) shouldBeEqualTo true
            hasInterfaceOrObjectWithName(emptySet()) shouldBeEqualTo true
            hasInterfacesAndObjectsWithAllNames(emptyList()) shouldBeEqualTo true
            hasInterfacesAndObjectsWithAllNames(emptySet()) shouldBeEqualTo true
            hasInterfaceOrObjectWithName("SampleInterface") shouldBeEqualTo true
            hasInterfaceOrObjectWithName("SampleObject") shouldBeEqualTo true
            hasInterfaceOrObjectWithName("SampleInterface", "OtherObject") shouldBeEqualTo true
            hasInterfaceOrObjectWithName(listOf("SampleInterface")) shouldBeEqualTo true
            hasInterfaceOrObjectWithName(listOf("SampleObject")) shouldBeEqualTo true
            hasInterfaceOrObjectWithName(listOf("SampleInterface", "OtherObject")) shouldBeEqualTo true
            hasInterfaceOrObjectWithName(setOf("SampleInterface")) shouldBeEqualTo true
            hasInterfaceOrObjectWithName(setOf("SampleObject")) shouldBeEqualTo true
            hasInterfaceOrObjectWithName(setOf("SampleInterface", "OtherObject")) shouldBeEqualTo true
            hasInterfacesAndObjectsWithAllNames("SampleInterface") shouldBeEqualTo true
            hasInterfacesAndObjectsWithAllNames("SampleInterface", "SampleObject") shouldBeEqualTo true
            hasInterfacesAndObjectsWithAllNames("SampleInterface", "OtherObject") shouldBeEqualTo false
            hasInterfacesAndObjectsWithAllNames(listOf("SampleInterface")) shouldBeEqualTo true
            hasInterfacesAndObjectsWithAllNames(listOf("SampleInterface", "SampleObject")) shouldBeEqualTo true
            hasInterfacesAndObjectsWithAllNames(listOf("SampleInterface", "OtherObject")) shouldBeEqualTo false
            hasInterfacesAndObjectsWithAllNames(setOf("SampleInterface")) shouldBeEqualTo true
            hasInterfacesAndObjectsWithAllNames(setOf("SampleInterface", "SampleObject")) shouldBeEqualTo true
            hasInterfacesAndObjectsWithAllNames(setOf("SampleInterface", "OtherObject")) shouldBeEqualTo false
            hasInterfaceOrObject { it.name == "SampleInterface" } shouldBeEqualTo true
            hasInterfaceOrObject { it.name == "SampleObject" } shouldBeEqualTo true
            hasInterfaceOrObject { it.hasNameEndingWith("Interface") } shouldBeEqualTo true
            hasInterfaceOrObject { it.hasNameEndingWith("Interface") || it.hasNameEndingWith("Object") } shouldBeEqualTo true
            hasAllInterfacesAndObjects { it.hasNameStartingWith("Sample") } shouldBeEqualTo true
            hasAllInterfacesAndObjects { it.hasNameEndingWith("Interface") } shouldBeEqualTo false
        }
    }

    @Test
    fun `file-contains-nested-interfaces-and-objects includeNested true`() {
        // given
        val sut =
            getSnippetFile("file-contains-nested-interfaces-and-objects")
                .files
                .first()

        // then
        val expected = listOf("SampleInterfaceNestedInsideObject", "SampleObject", "SampleObjectNestedInsideObject")

        sut
            .interfacesAndObjects(includeNested = true)
            .map { it.name }
            .shouldBeEqualTo(expected)
    }

    @Test
    fun `file-contains-nested-interfaces-and-objects includeNested false`() {
        // given
        val sut =
            getSnippetFile("file-contains-nested-interfaces-and-objects")
                .files
                .first()

        // then
        val expected = listOf("SampleObject")

        sut
            .interfacesAndObjects(includeNested = false)
            .map { it.name }
            .shouldBeEqualTo(expected)
    }

    @Test
    fun `count-interfaces-and-objects`() {
        // given
        val sut =
            getSnippetFile("count-interfaces-and-objects")
                .files
                .first()

        // then
        assertSoftly(sut) {
            numInterfacesAndObjects(includeNested = true) shouldBeEqualTo 2
            numInterfacesAndObjects(includeNested = false) shouldBeEqualTo 0
            countInterfacesAndObjects(includeNested = false) { it.hasPrivateModifier } shouldBeEqualTo 0
            countInterfacesAndObjects { it.hasPrivateModifier } shouldBeEqualTo 2
            countInterfacesAndObjects { it.name == "SampleInterface" && it.hasInternalModifier } shouldBeEqualTo 0
        }
    }

    @Test
    fun `file-has-no-interfaces-and-objects-ignore-case`() {
        // given
        val sut =
            getSnippetFile("file-has-no-interfaces-and-objects-ignore-case")
                .files
                .first()

        // then
        assertSoftly(sut) {
            hasInterfaceOrObjectWithName("sampleobject") shouldBeEqualTo false
            hasInterfaceOrObjectWithName("sampleobject", ignoreCase = true) shouldBeEqualTo false
            hasInterfaceOrObjectWithName(listOf("sampleobject")) shouldBeEqualTo false
            hasInterfaceOrObjectWithName(listOf("sampleobject"), ignoreCase = true) shouldBeEqualTo false
            hasInterfaceOrObjectWithName(setOf("sampleobject")) shouldBeEqualTo false
            hasInterfaceOrObjectWithName(setOf("sampleobject"), ignoreCase = true) shouldBeEqualTo false
            hasInterfacesAndObjectsWithAllNames("sampleobject1", "sampleinterface") shouldBeEqualTo false
            hasInterfacesAndObjectsWithAllNames("sampleobject1", "sampleinterface", ignoreCase = true) shouldBeEqualTo false
            hasInterfacesAndObjectsWithAllNames(listOf("sampleobject", "sampleinterface")) shouldBeEqualTo false
            hasInterfacesAndObjectsWithAllNames(listOf("sampleobject", "sampleinterface"), ignoreCase = true) shouldBeEqualTo false
            hasInterfacesAndObjectsWithAllNames(setOf("sampleobject", "sampleinterface")) shouldBeEqualTo false
            hasInterfacesAndObjectsWithAllNames(setOf("sampleobject", "sampleinterface"), ignoreCase = true) shouldBeEqualTo false
        }
    }

    @Test
    fun `file-has-interface-and-object-ignore-case`() {
        // given
        val sut =
            getSnippetFile("file-has-interface-and-object-ignore-case")
                .files
                .first()

        // then
        assertSoftly(sut) {
            hasInterfaceOrObjectWithName("sampleobject") shouldBeEqualTo false
            hasInterfaceOrObjectWithName("sampleobject", ignoreCase = true) shouldBeEqualTo true
            hasInterfaceOrObjectWithName("otherclass") shouldBeEqualTo false
            hasInterfaceOrObjectWithName("otherclass", ignoreCase = true) shouldBeEqualTo false
            hasInterfaceOrObjectWithName("sampleobject", "otherName") shouldBeEqualTo false
            hasInterfaceOrObjectWithName("sampleobject", "otherName", ignoreCase = true) shouldBeEqualTo true
            hasInterfaceOrObjectWithName(listOf("sampleobject")) shouldBeEqualTo false
            hasInterfaceOrObjectWithName(listOf("sampleobject"), ignoreCase = true) shouldBeEqualTo true
            hasInterfaceOrObjectWithName(listOf("otherclass")) shouldBeEqualTo false
            hasInterfaceOrObjectWithName(listOf("otherclass"), ignoreCase = true) shouldBeEqualTo false
            hasInterfaceOrObjectWithName(listOf("sampleobject", "otherName")) shouldBeEqualTo false
            hasInterfaceOrObjectWithName(listOf("sampleobject", "otherName"), ignoreCase = true) shouldBeEqualTo true
            hasInterfacesAndObjectsWithAllNames("sampleobject") shouldBeEqualTo false
            hasInterfacesAndObjectsWithAllNames("sampleobject", ignoreCase = true) shouldBeEqualTo true
            hasInterfacesAndObjectsWithAllNames("sampleobject", "sampleinterface") shouldBeEqualTo false
            hasInterfacesAndObjectsWithAllNames("sampleobject", "sampleinterface", ignoreCase = true) shouldBeEqualTo true
            hasInterfacesAndObjectsWithAllNames("sampleobject", "otherclass") shouldBeEqualTo false
            hasInterfacesAndObjectsWithAllNames("sampleobject", "otherclass", ignoreCase = true) shouldBeEqualTo false
            hasInterfacesAndObjectsWithAllNames(listOf("sampleobject")) shouldBeEqualTo false
            hasInterfacesAndObjectsWithAllNames(listOf("sampleobject"), ignoreCase = true) shouldBeEqualTo true
            hasInterfacesAndObjectsWithAllNames(listOf("sampleobject", "sampleinterface")) shouldBeEqualTo false
            hasInterfacesAndObjectsWithAllNames(listOf("sampleobject", "sampleinterface"), ignoreCase = true) shouldBeEqualTo true
            hasInterfacesAndObjectsWithAllNames(listOf("sampleobject", "otherclass")) shouldBeEqualTo false
            hasInterfacesAndObjectsWithAllNames(listOf("sampleobject", "otherclass"), ignoreCase = true) shouldBeEqualTo false
        }
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/kofile/snippet/forkointerfaceandobjectprovider/", fileName)
}
