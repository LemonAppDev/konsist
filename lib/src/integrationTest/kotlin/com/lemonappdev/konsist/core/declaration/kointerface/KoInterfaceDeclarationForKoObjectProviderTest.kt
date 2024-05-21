package com.lemonappdev.konsist.core.declaration.kointerface

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoInterfaceDeclarationForKoObjectProviderTest {
    @Test
    fun `interface-has-no-objects`() {
        // given
        val sut =
            getSnippetFile("interface-has-no-objects")
                .interfaces()
                .first()

        // then
        assertSoftly(sut) {
            objects() shouldBeEqualTo emptyList()
            hasObjects() shouldBeEqualTo false
            hasObjectWithName(emptyList()) shouldBeEqualTo false
            hasObjectWithName(emptySet()) shouldBeEqualTo false
            hasObjectsWithAllNames(emptyList()) shouldBeEqualTo false
            hasObjectsWithAllNames(emptySet()) shouldBeEqualTo false
            hasObjectWithName("SampleObject") shouldBeEqualTo false
            hasObjectWithName(listOf("SampleObject")) shouldBeEqualTo false
            hasObjectWithName(setOf("SampleObject")) shouldBeEqualTo false
            hasObjectsWithAllNames("SampleObject1", "SampleObject2") shouldBeEqualTo false
            hasObjectsWithAllNames(listOf("SampleObject1", "SampleObject2")) shouldBeEqualTo false
            hasObjectsWithAllNames(setOf("SampleObject1", "SampleObject2")) shouldBeEqualTo false
            hasObject { it.name == "SampleObject" } shouldBeEqualTo false
            hasAllObjects { it.hasNameStartingWith("Sample") } shouldBeEqualTo true
        }
    }

    @Test
    fun `interface-has-two-objects`() {
        // given
        val sut =
            getSnippetFile("interface-has-two-objects")
                .interfaces()
                .first()

        // then
        assertSoftly(sut) {
            hasObjects() shouldBeEqualTo true
            hasObjectWithName(emptyList()) shouldBeEqualTo true
            hasObjectWithName(emptySet()) shouldBeEqualTo true
            hasObjectsWithAllNames(emptyList()) shouldBeEqualTo true
            hasObjectsWithAllNames(emptySet()) shouldBeEqualTo true
            hasObjectWithName("SampleObject1") shouldBeEqualTo true
            hasObjectWithName("SampleObject1", "OtherObject") shouldBeEqualTo true
            hasObjectWithName(listOf("SampleObject1")) shouldBeEqualTo true
            hasObjectWithName(listOf("SampleObject1", "OtherObject")) shouldBeEqualTo true
            hasObjectWithName(setOf("SampleObject1")) shouldBeEqualTo true
            hasObjectWithName(setOf("SampleObject1", "OtherObject")) shouldBeEqualTo true
            hasObjectsWithAllNames("SampleObject1") shouldBeEqualTo true
            hasObjectsWithAllNames("SampleObject1", "SampleObject2") shouldBeEqualTo true
            hasObjectsWithAllNames("SampleObject1", "OtherObject") shouldBeEqualTo false
            hasObjectsWithAllNames(listOf("SampleObject1")) shouldBeEqualTo true
            hasObjectsWithAllNames(listOf("SampleObject1", "SampleObject2")) shouldBeEqualTo true
            hasObjectsWithAllNames(listOf("SampleObject1", "OtherObject")) shouldBeEqualTo false
            hasObjectsWithAllNames(setOf("SampleObject1")) shouldBeEqualTo true
            hasObjectsWithAllNames(setOf("SampleObject1", "SampleObject2")) shouldBeEqualTo true
            hasObjectsWithAllNames(setOf("SampleObject1", "OtherObject")) shouldBeEqualTo false
            hasObject { it.name == "SampleObject1" } shouldBeEqualTo true
            hasObject { it.hasNameEndingWith("Object1") } shouldBeEqualTo true
            hasAllObjects { it.hasNameStartingWith("Sample") } shouldBeEqualTo true
            hasAllObjects { it.hasNameEndingWith("Class1") } shouldBeEqualTo false
        }
    }

    @Test
    fun `interface-contains-objects includeNested true`() {
        // given
        val sut =
            getSnippetFile("interface-contains-objects")
                .interfaces()
                .first()

        // then
        val expected = listOf("SampleObject", "SampleNestedObject")

        sut.objects(includeNested = true)
            .map { it.name }
            .shouldBeEqualTo(expected)
    }

    @Test
    fun `interface-contains-objects includeNested false`() {
        // given
        val sut =
            getSnippetFile("interface-contains-objects")
                .interfaces()
                .first()

        // then
        val expected = listOf("SampleObject")

        sut.objects(includeNested = false)
            .map { it.name }
            .shouldBeEqualTo(expected)
    }

    @Test
    fun `count-objects`() {
        // given
        val sut =
            getSnippetFile("count-objects")
                .interfaces()
                .first()

        // then
        assertSoftly(sut) {
            numObjects(includeNested = true) shouldBeEqualTo 2
            numObjects(includeNested = false) shouldBeEqualTo 1
            countObjects { it.hasPrivateModifier } shouldBeEqualTo 2
            countObjects(includeNested = false) { it.hasPrivateModifier } shouldBeEqualTo 1
            countObjects { it.hasInternalModifier } shouldBeEqualTo 0
        }
    }

    private fun getSnippetFile(fileName: String) = getSnippetKoScope("core/declaration/kointerface/snippet/forkoobjectprovider/", fileName)
}
