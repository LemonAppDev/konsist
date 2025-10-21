package com.lemonappdev.konsist.core.declaration.koclass

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

@Suppress("detekt.LongMethod")
class KoClassDeclarationForKoCompanionObjectProviderTest {
    @Test
    fun `class-has-no-direct-companion-object`() {
        // given
        val sut =
            getSnippetFile("class-has-no-direct-companion-object")
                .classes()
                .first()

        // then
        assertSoftly(sut) {
            companionObject shouldBeEqualTo null
            hasCompanionObject() shouldBeEqualTo false
            hasCompanionObject(includeNested = false) { it.name == "SampleCompanionObject" } shouldBeEqualTo false
            hasCompanionObjectWithName("SampleCompanionObject", includeNested = false) shouldBeEqualTo false
            hasCompanionObjectWithName(listOf("SampleCompanionObject"), includeNested = false) shouldBeEqualTo false
            hasCompanionObjectWithName(setOf("SampleCompanionObject"), includeNested = false) shouldBeEqualTo false
            hasCompanionObjectsWithAllNames(
                "SampleCompanionObject1",
                "SampleCompanionObject2",
                includeNested = false,
            ) shouldBeEqualTo false
            hasCompanionObjectsWithAllNames(
                listOf(
                    "SampleCompanionObject1",
                    "SampleCompanionObject2",
                ),
                includeNested = false,
            ) shouldBeEqualTo false
            hasCompanionObjectsWithAllNames(
                setOf(
                    "SampleCompanionObject1",
                    "SampleCompanionObject2",
                ),
                includeNested = false,
            ) shouldBeEqualTo false
        }
    }

    @Test
    fun `class-has-direct-companion-object-with-default-name`() {
        // given
        val sut =
            getSnippetFile("class-has-direct-companion-object-with-default-name")
                .classes()
                .first()

        // then
        assertSoftly(sut) {
            hasCompanionObject() shouldBeEqualTo true
            hasCompanionObject(includeNested = false) { it.name == "Companion" } shouldBeEqualTo true
            hasCompanionObject(includeNested = false) { it.hasNameEndingWith("nion") } shouldBeEqualTo true
            hasCompanionObjectWithName("Companion", includeNested = false) shouldBeEqualTo true
            hasCompanionObjectWithName("OtherCompanionObject", includeNested = false) shouldBeEqualTo false
            hasCompanionObjectWithName(
                "Companion",
                "OtherCompanionObject",
                includeNested = false,
            ) shouldBeEqualTo true
            hasCompanionObjectWithName(listOf("Companion"), includeNested = false) shouldBeEqualTo true
            hasCompanionObjectWithName(listOf("OtherCompanionObject"), includeNested = false) shouldBeEqualTo false
            hasCompanionObjectWithName(
                listOf("Companion", "OtherCompanionObject"),
                includeNested = false,
            ) shouldBeEqualTo true
            hasCompanionObjectsWithAllNames("Companion", includeNested = false) shouldBeEqualTo true
            hasCompanionObjectsWithAllNames(
                "Companion",
                "SampleCompanionObject2",
                includeNested = false,
            ) shouldBeEqualTo false
            hasCompanionObjectsWithAllNames(
                listOf("Companion"),
                includeNested = false,
            ) shouldBeEqualTo true
            hasCompanionObjectsWithAllNames(
                listOf("Companion", "SampleCompanionObject2"),
                includeNested = false,
            ) shouldBeEqualTo false
        }
    }

    @Test
    fun `class-has-direct-companion-object-with-given-name`() {
        // given
        val sut =
            getSnippetFile("class-has-direct-companion-object-with-given-name")
                .classes()
                .first()

        // then
        assertSoftly(sut) {
            hasCompanionObject() shouldBeEqualTo true
            hasCompanionObject(includeNested = false) { it.name == "SampleCompanionObject1" } shouldBeEqualTo true
            hasCompanionObject(includeNested = false) { it.hasNameEndingWith("CompanionObject1") } shouldBeEqualTo true
            hasCompanionObjectWithName("SampleCompanionObject1", includeNested = false) shouldBeEqualTo true
            hasCompanionObjectWithName("OtherCompanionObject", includeNested = false) shouldBeEqualTo false
            hasCompanionObjectWithName(
                "SampleCompanionObject1",
                "OtherCompanionObject",
                includeNested = false,
            ) shouldBeEqualTo true
            hasCompanionObjectWithName(listOf("SampleCompanionObject1"), includeNested = false) shouldBeEqualTo true
            hasCompanionObjectWithName(listOf("OtherCompanionObject"), includeNested = false) shouldBeEqualTo false
            hasCompanionObjectWithName(
                listOf("SampleCompanionObject1", "OtherCompanionObject"),
                includeNested = false,
            ) shouldBeEqualTo true
            hasCompanionObjectsWithAllNames("SampleCompanionObject1", includeNested = false) shouldBeEqualTo true
            hasCompanionObjectsWithAllNames(
                "SampleCompanionObject1",
                "SampleCompanionObject2",
                includeNested = false,
            ) shouldBeEqualTo false
            hasCompanionObjectsWithAllNames(
                listOf("SampleCompanionObject1"),
                includeNested = false,
            ) shouldBeEqualTo true
            hasCompanionObjectsWithAllNames(
                listOf("SampleCompanionObject1", "SampleCompanionObject2"),
                includeNested = false,
            ) shouldBeEqualTo false
        }
    }

    @Test
    fun `class-contains-companion-objects includeNested true`() {
        // given
        val sut =
            getSnippetFile("class-contains-companion-objects")
                .classes()
                .first()

        // then
        val expected = listOf("SampleCompanionObject", "SampleNestedCompanionObject")

        sut
            .companionObjects(includeNested = true)
            .map { it.name }
            .shouldBeEqualTo(expected)
    }

    @Test
    fun `class-contains-companion-objects includeNested false`() {
        // given
        val sut =
            getSnippetFile("class-contains-companion-objects")
                .classes()
                .first()

        // then
        val expected = listOf("SampleCompanionObject")

        sut
            .companionObjects(includeNested = false)
            .map { it.name }
            .shouldBeEqualTo(expected)
    }

    @Test
    fun `count-companion-objects`() {
        // given
        val sut =
            getSnippetFile("count-companion-objects")
                .classes()
                .first()

        // then
        assertSoftly(sut) {
            numCompanionObjects(includeNested = true) shouldBeEqualTo 2
            numCompanionObjects(includeNested = false) shouldBeEqualTo 1
            countCompanionObjects { it.hasPrivateModifier } shouldBeEqualTo 2
            countCompanionObjects(includeNested = false) { it.hasPrivateModifier } shouldBeEqualTo 1
            countCompanionObjects { it.hasInternalModifier } shouldBeEqualTo 0
        }
    }

    @Test
    fun `class-has-no-companion-objects-ignore-case`() {
        // given
        val sut =
            getSnippetFile("class-has-no-companion-objects-ignore-case")
                .classes()
                .first()

        // then
        assertSoftly(sut) {
            hasCompanionObjectWithName("samplecompanionobject") shouldBeEqualTo false
            hasCompanionObjectWithName("samplecompanionobject", ignoreCase = true) shouldBeEqualTo false
            hasCompanionObjectWithName(listOf("samplecompanionobject")) shouldBeEqualTo false
            hasCompanionObjectWithName(listOf("samplecompanionobject"), ignoreCase = true) shouldBeEqualTo false
            hasCompanionObjectWithName(setOf("samplecompanionobject")) shouldBeEqualTo false
            hasCompanionObjectWithName(setOf("samplecompanionobject"), ignoreCase = true) shouldBeEqualTo false
            hasCompanionObjectsWithAllNames("samplecompanionobject1", "samplecompanionobject2") shouldBeEqualTo false
            hasCompanionObjectsWithAllNames(
                "samplecompanionobject1",
                "samplecompanionobject2",
                ignoreCase = true,
            ) shouldBeEqualTo false
            hasCompanionObjectsWithAllNames(
                listOf(
                    "samplecompanionobject1",
                    "samplecompanionobject2",
                ),
            ) shouldBeEqualTo false
            hasCompanionObjectsWithAllNames(
                listOf("samplecompanionobject1", "samplecompanionobject2"),
                ignoreCase = true,
            ) shouldBeEqualTo false
            hasCompanionObjectsWithAllNames(
                setOf(
                    "samplecompanionobject1",
                    "samplecompanionobject2",
                ),
            ) shouldBeEqualTo false
            hasCompanionObjectsWithAllNames(
                setOf("samplecompanionobject1", "samplecompanionobject2"),
                ignoreCase = true,
            ) shouldBeEqualTo false
        }
    }

    @Test
    fun `class-has-companion-objects-ignore-case`() {
        // given
        val sut =
            getSnippetFile("class-has-companion-objects-ignore-case")
                .classes()
                .first()

        // then
        assertSoftly(sut) {
            hasCompanionObjectWithName("samplecompanionobject1") shouldBeEqualTo false
            hasCompanionObjectWithName(
                "samplecompanionobject1",
                ignoreCase = true,
            ) shouldBeEqualTo true
            hasCompanionObjectWithName("othercompanionobject") shouldBeEqualTo false
            hasCompanionObjectWithName(
                "othercompanionobject",
                ignoreCase = true,
            ) shouldBeEqualTo false
            hasCompanionObjectWithName(
                "samplecompanionobject1",
                "OtherCompanionObject",
            ) shouldBeEqualTo false
            hasCompanionObjectWithName(
                "samplecompanionobject1",
                "OtherCompanionObject",
                ignoreCase = true,
            ) shouldBeEqualTo true
            hasCompanionObjectWithName(listOf("samplecompanionobject1")) shouldBeEqualTo false
            hasCompanionObjectWithName(
                listOf("samplecompanionobject1"),
                ignoreCase = true,
            ) shouldBeEqualTo true
            hasCompanionObjectWithName(listOf("othercompanionobject")) shouldBeEqualTo false
            hasCompanionObjectWithName(
                listOf("othercompanionobject"),
                ignoreCase = true,
            ) shouldBeEqualTo false
            hasCompanionObjectWithName(
                listOf("samplecompanionobject1", "OtherCompanionObject"),
            ) shouldBeEqualTo false
            hasCompanionObjectWithName(
                listOf("samplecompanionobject1", "OtherCompanionObject"),
                ignoreCase = true,
            ) shouldBeEqualTo true
            hasCompanionObjectsWithAllNames("samplecompanionobject1") shouldBeEqualTo false
            hasCompanionObjectsWithAllNames(
                "samplecompanionobject1",
                ignoreCase = true,
            ) shouldBeEqualTo true
            hasCompanionObjectsWithAllNames(
                "samplecompanionobject1",
                "samplecompanionobject2",
            ) shouldBeEqualTo false
            hasCompanionObjectsWithAllNames(
                "samplecompanionobject1",
                "samplecompanionobject2",
                ignoreCase = true,
            ) shouldBeEqualTo true
            hasCompanionObjectsWithAllNames(
                "samplecompanionobject1",
                "othercompanionobject",
            ) shouldBeEqualTo false
            hasCompanionObjectsWithAllNames(
                "samplecompanionobject1",
                "othercompanionobject",
                ignoreCase = true,
            ) shouldBeEqualTo false
            hasCompanionObjectsWithAllNames(
                listOf("samplecompanionobject1"),
            ) shouldBeEqualTo false
            hasCompanionObjectsWithAllNames(
                listOf("samplecompanionobject1"),
                ignoreCase = true,
            ) shouldBeEqualTo true
            hasCompanionObjectsWithAllNames(
                listOf("samplecompanionobject1", "samplecompanionobject2"),
            ) shouldBeEqualTo false
            hasCompanionObjectsWithAllNames(
                listOf("samplecompanionobject1", "samplecompanionobject2"),
                ignoreCase = true,
            ) shouldBeEqualTo true
            hasCompanionObjectsWithAllNames(
                listOf("samplecompanionobject1", "othercompanionobject"),
            ) shouldBeEqualTo false
            hasCompanionObjectsWithAllNames(
                listOf("samplecompanionobject1", "othercompanionobject"),
                ignoreCase = true,
            ) shouldBeEqualTo false
        }
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/koclass/snippet/forkocompanionobjectprovider/", fileName)
}
