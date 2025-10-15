package com.lemonappdev.konsist.core.declaration.koclass

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoClassDeclarationForKoObjectProviderTest {
    @Test
    fun `class-has-no-objects`() {
        // given
        val sut =
            getSnippetFile("class-has-no-objects")
                .classes()
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
    fun `class-has-two-objects`() {
        // given
        val sut =
            getSnippetFile("class-has-two-objects")
                .classes()
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
    fun `class-contains-objects includeNested true`() {
        // given
        val sut =
            getSnippetFile("class-contains-objects")
                .classes()
                .first()

        // then
        val expected = listOf("SampleObject", "SampleNestedObject")

        sut
            .objects(includeNested = true)
            .map { it.name }
            .shouldBeEqualTo(expected)
    }

    @Test
    fun `class-contains-objects includeNested false`() {
        // given
        val sut =
            getSnippetFile("class-contains-objects")
                .classes()
                .first()

        // then
        val expected = listOf("SampleObject")

        sut
            .objects(includeNested = false)
            .map { it.name }
            .shouldBeEqualTo(expected)
    }

    @Test
    fun `count-objects`() {
        // given
        val sut =
            getSnippetFile("count-objects")
                .classes()
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

    @Test
    fun `class-has-no-objects-ignore-case`() {
        // given
        val sut =
            getSnippetFile("class-has-no-objects-ignore-case")
                .classes()
                .first()

        // then
        assertSoftly(sut) {
            hasObjectWithName("sampleobject") shouldBeEqualTo false
            hasObjectWithName("sampleobject", ignoreCase = true) shouldBeEqualTo false
            hasObjectWithName(listOf("sampleobject")) shouldBeEqualTo false
            hasObjectWithName(listOf("sampleobject"), ignoreCase = true) shouldBeEqualTo false
            hasObjectWithName(setOf("sampleobject")) shouldBeEqualTo false
            hasObjectWithName(setOf("sampleobject"), ignoreCase = true) shouldBeEqualTo false
            hasObjectsWithAllNames("sampleobject1", "sampleobject2") shouldBeEqualTo false
            hasObjectsWithAllNames("sampleobject1", "sampleobject2", ignoreCase = true) shouldBeEqualTo false
            hasObjectsWithAllNames(listOf("sampleobject1", "sampleobject2")) shouldBeEqualTo false
            hasObjectsWithAllNames(listOf("sampleobject1", "sampleobject2"), ignoreCase = true) shouldBeEqualTo false
            hasObjectsWithAllNames(setOf("sampleobject1", "sampleobject2")) shouldBeEqualTo false
            hasObjectsWithAllNames(setOf("sampleobject1", "sampleobject2"), ignoreCase = true) shouldBeEqualTo false
        }
    }

    @Test
    fun `class-has-objects-ignore-case`() {
        // given
        val sut =
            getSnippetFile("class-has-objects-ignore-case")
                .classes()
                .first()

        // then
        assertSoftly(sut) {
            hasObjectWithName("sampleobject1") shouldBeEqualTo false
            hasObjectWithName("sampleobject1", ignoreCase = true) shouldBeEqualTo true
            hasObjectWithName("otherobject") shouldBeEqualTo false
            hasObjectWithName("otherobject", ignoreCase = true) shouldBeEqualTo false
            hasObjectWithName("sampleobject1", "otherName") shouldBeEqualTo false
            hasObjectWithName("sampleobject1", "otherName", ignoreCase = true) shouldBeEqualTo true
            hasObjectWithName(listOf("sampleobject1")) shouldBeEqualTo false
            hasObjectWithName(listOf("sampleobject1"), ignoreCase = true) shouldBeEqualTo true
            hasObjectWithName(listOf("otherobject")) shouldBeEqualTo false
            hasObjectWithName(listOf("otherobject"), ignoreCase = true) shouldBeEqualTo false
            hasObjectWithName(listOf("sampleobject1", "otherName")) shouldBeEqualTo false
            hasObjectWithName(listOf("sampleobject1", "otherName"), ignoreCase = true) shouldBeEqualTo true
            hasObjectsWithAllNames("sampleobject1") shouldBeEqualTo false
            hasObjectsWithAllNames("sampleobject1", ignoreCase = true) shouldBeEqualTo true
            hasObjectsWithAllNames("sampleobject1", "sampleobject2") shouldBeEqualTo false
            hasObjectsWithAllNames("sampleobject1", "sampleobject2", ignoreCase = true) shouldBeEqualTo true
            hasObjectsWithAllNames("sampleobject1", "otherobject") shouldBeEqualTo false
            hasObjectsWithAllNames("sampleobject1", "otherobject", ignoreCase = true) shouldBeEqualTo false
            hasObjectsWithAllNames(listOf("sampleobject1")) shouldBeEqualTo false
            hasObjectsWithAllNames(listOf("sampleobject1"), ignoreCase = true) shouldBeEqualTo true
            hasObjectsWithAllNames(listOf("sampleobject1", "sampleobject2")) shouldBeEqualTo false
            hasObjectsWithAllNames(listOf("sampleobject1", "sampleobject2"), ignoreCase = true) shouldBeEqualTo true
            hasObjectsWithAllNames(listOf("sampleobject1", "otherobject")) shouldBeEqualTo false
            hasObjectsWithAllNames(listOf("sampleobject1", "otherobject"), ignoreCase = true) shouldBeEqualTo false
        }
    }

    private fun getSnippetFile(fileName: String) = getSnippetKoScope("core/declaration/koclass/snippet/forkoobjectprovider/", fileName)
}
