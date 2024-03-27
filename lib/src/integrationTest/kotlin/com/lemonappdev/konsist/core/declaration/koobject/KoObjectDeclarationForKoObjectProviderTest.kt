package com.lemonappdev.konsist.core.declaration.koobject

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import com.lemonappdev.konsist.api.KoModifier.DATA
import com.lemonappdev.konsist.api.KoModifier.INTERNAL
import com.lemonappdev.konsist.api.KoModifier.PRIVATE
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoObjectDeclarationForKoObjectProviderTest {
    @Test
    fun `object-has-no-objects`() {
        // given
        val sut =
            getSnippetFile("object-has-no-objects")
                .objects()
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
    fun `object-has-two-objects`() {
        // given
        val sut =
            getSnippetFile("object-has-two-objects")
                .objects()
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
    fun `object-contains-objects includeNested true`() {
        // given
        val sut =
            getSnippetFile("object-contains-objects")
                .objects()
                .first()

        // then
        val expected = listOf("SampleObject", "SampleNestedObject")

        sut.objects(includeNested = true)
            .map { it.name }
            .shouldBeEqualTo(expected)
    }

    @Test
    fun `object-contains-objects includeNested false`() {
        // given
        val sut =
            getSnippetFile("object-contains-objects")
                .objects()
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
                .objects()
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
    fun `contains-objects-with-specified-conditions`() {
        // given
        val sut =
            getSnippetFile("contains-objects-with-specified-conditions")
                .objects()
                .first()

        // then
        assertSoftly(sut) {
            containsObject {
                it.name == "SampleObject" && it.hasPrivateModifier
            } shouldBeEqualTo true
            containsObject {
                it.name == "SampleObject" && it.hasModifiers(DATA, PRIVATE)
            } shouldBeEqualTo true
            containsObject {
                it.name == "SampleObject" && it.hasPublicModifier
            } shouldBeEqualTo false
            containsObject {
                it.name == "SampleObject" && it.hasModifiers(INTERNAL, PRIVATE)
            } shouldBeEqualTo false
            containsObject(includeNested = true) { it.name == "SampleNestedObject" && it.hasPrivateModifier } shouldBeEqualTo true
            containsObject(includeNested = false) { it.name == "SampleNestedObject" && it.hasPrivateModifier } shouldBeEqualTo false
            containsObject(includeNested = true) { it.name == "SampleNestedObject" && it.hasCompanionModifier } shouldBeEqualTo false
        }
    }

    @Test
    fun `contains-objects-with-specified-regex`() {
        // given
        val regex1 = Regex("[a-zA-Z]+")
        val regex2 = Regex("[0-9]+")
        val sut =
            getSnippetFile("contains-objects-with-specified-regex")
                .objects()
                .first()

        // then
        assertSoftly(sut) {
            containsObject(includeNested = false) { it.name.matches(regex1) } shouldBeEqualTo true
            containsObject(includeNested = true) { it.name.matches(regex1) } shouldBeEqualTo true
            containsObject(includeNested = false) { it.name.matches(regex2) } shouldBeEqualTo false
            containsObject(includeNested = true) { it.name.matches(regex2) } shouldBeEqualTo false
        }
    }

    private fun getSnippetFile(fileName: String) = getSnippetKoScope("core/declaration/koobject/snippet/forkoobjectprovider/", fileName)
}
