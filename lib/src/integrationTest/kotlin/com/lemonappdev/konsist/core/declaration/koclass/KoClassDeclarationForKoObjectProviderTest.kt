package com.lemonappdev.konsist.core.declaration.koclass

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import com.lemonappdev.konsist.api.KoModifier.COMPANION
import com.lemonappdev.konsist.api.KoModifier.INTERNAL
import com.lemonappdev.konsist.api.KoModifier.PRIVATE
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
            hasObjectWithName("SampleObject") shouldBeEqualTo false
            hasObjectsWithAllNames("SampleObject1", "SampleObject2") shouldBeEqualTo false
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
            hasObjectWithName("SampleObject1") shouldBeEqualTo true
            hasObjectWithName("SampleObject1", "OtherObject") shouldBeEqualTo true
            hasObjectsWithAllNames("SampleObject1") shouldBeEqualTo true
            hasObjectsWithAllNames("SampleObject1", "SampleObject2") shouldBeEqualTo true
            hasObjectsWithAllNames("SampleObject1", "OtherObject") shouldBeEqualTo false
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

        sut.objects(includeNested = true)
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

        sut.objects(includeNested = false)
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
    fun `contains-objects-with-specified-conditions`() {
        // given
        val sut =
            getSnippetFile("contains-objects-with-specified-conditions")
                .classes()
                .first()

        // then
        assertSoftly(sut) {
            containsObject {
                it.name == "SampleObject" && it.hasPrivateModifier
            } shouldBeEqualTo true
            containsObject {
                it.name == "SampleObject" && it.hasModifiers(PRIVATE, COMPANION)
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
                .classes()
                .first()

        // then
        assertSoftly(sut) {
            containsObject(includeNested = false) { it.name.matches(regex1) } shouldBeEqualTo true
            containsObject(includeNested = true) { it.name.matches(regex1) } shouldBeEqualTo true
            containsObject(includeNested = false) { it.name.matches(regex2) } shouldBeEqualTo false
            containsObject(includeNested = true) { it.name.matches(regex2) } shouldBeEqualTo false
        }
    }

    private fun getSnippetFile(fileName: String) = getSnippetKoScope("core/declaration/koclass/snippet/forkoobjectprovider/", fileName)
}
