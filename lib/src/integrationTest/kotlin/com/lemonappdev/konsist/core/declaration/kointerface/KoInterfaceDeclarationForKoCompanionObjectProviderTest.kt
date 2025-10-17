package com.lemonappdev.konsist.core.declaration.kointerface

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoInterfaceDeclarationForKoCompanionObjectProviderTest {
    @Test
    fun `interface-has-no-companion-object`() {
        // given
        val sut =
            getSnippetFile("interface-has-no-companion-object")
                .interfaces()
                .first()

        // then
        assertSoftly(sut) {
            companionObject shouldBeEqualTo null
            hasCompanionObject() shouldBeEqualTo false
            hasCompanionObject { it.name == "SampleCompanionObject" } shouldBeEqualTo false
            hasCompanionObjectWithName("SampleCompanionObject") shouldBeEqualTo false
            hasCompanionObjectWithName(listOf("SampleCompanionObject")) shouldBeEqualTo false
            hasCompanionObjectWithName(setOf("SampleCompanionObject")) shouldBeEqualTo false
            hasCompanionObjectsWithAllNames("SampleCompanionObject1", "SampleCompanionObject2") shouldBeEqualTo false
            hasCompanionObjectsWithAllNames(
                listOf(
                    "SampleCompanionObject1",
                    "SampleCompanionObject2"
                )
            ) shouldBeEqualTo false
            hasCompanionObjectsWithAllNames(
                setOf(
                    "SampleCompanionObject1",
                    "SampleCompanionObject2"
                )
            ) shouldBeEqualTo false
        }
    }

    @Test
    fun `interface-has-companion-object-with-default-name`() {
        // given
        val sut =
            getSnippetFile("interface-has-companion-object-with-default-name")
                .interfaces()
                .first()

        // then
        assertSoftly(sut) {
            hasCompanionObject() shouldBeEqualTo true
            hasCompanionObject { it.name == "Companion" } shouldBeEqualTo true
            hasCompanionObject { it.hasNameEndingWith("nion") } shouldBeEqualTo true
            hasCompanionObjectWithName("Companion", includeNested = true) shouldBeEqualTo true
            hasCompanionObjectWithName("OtherCompanionObject", includeNested = true) shouldBeEqualTo false
            hasCompanionObjectWithName(
                "Companion",
                "OtherCompanionObject",
                includeNested = true
            ) shouldBeEqualTo true
            hasCompanionObjectWithName(listOf("Companion"), includeNested = true) shouldBeEqualTo true
            hasCompanionObjectWithName(listOf("OtherCompanionObject"), includeNested = true) shouldBeEqualTo false
            hasCompanionObjectWithName(
                listOf("Companion", "OtherCompanionObject"),
                includeNested = true
            ) shouldBeEqualTo true
            hasCompanionObjectsWithAllNames("Companion", includeNested = true) shouldBeEqualTo true
            hasCompanionObjectsWithAllNames(
                "Companion",
                "SampleCompanionObject2",
                includeNested = true
            ) shouldBeEqualTo true
            hasCompanionObjectsWithAllNames(
                listOf("Companion"),
                includeNested = true
            ) shouldBeEqualTo true
            hasCompanionObjectsWithAllNames(
                listOf("Companion", "SampleCompanionObject2"),
                includeNested = true
            ) shouldBeEqualTo true
        }
    }

    @Test
    fun `interface-has-companion-object-with-given-name`() {
        // given
        val sut =
            getSnippetFile("interface-has-companion-object-with-given-name")
                .interfaces()
                .first()

        // then
        assertSoftly(sut) {
            hasCompanionObject() shouldBeEqualTo true
            hasCompanionObject { it.name == "SampleCompanionObject1" } shouldBeEqualTo true
            hasCompanionObject { it.hasNameEndingWith("CompanionObject1") } shouldBeEqualTo true
            hasCompanionObjectWithName("SampleCompanionObject1", includeNested = true) shouldBeEqualTo true
            hasCompanionObjectWithName("OtherCompanionObject", includeNested = true) shouldBeEqualTo false
            hasCompanionObjectWithName(
                "SampleCompanionObject1",
                "OtherCompanionObject",
                includeNested = true
            ) shouldBeEqualTo true
            hasCompanionObjectWithName(listOf("SampleCompanionObject1"), includeNested = true) shouldBeEqualTo true
            hasCompanionObjectWithName(listOf("OtherCompanionObject"), includeNested = true) shouldBeEqualTo false
            hasCompanionObjectWithName(
                listOf("SampleCompanionObject1", "OtherCompanionObject"),
                includeNested = true
            ) shouldBeEqualTo true
            hasCompanionObjectsWithAllNames("SampleCompanionObject1", includeNested = true) shouldBeEqualTo true
            hasCompanionObjectsWithAllNames(
                "SampleCompanionObject1",
                "SampleCompanionObject2",
                includeNested = true
            ) shouldBeEqualTo true
            hasCompanionObjectsWithAllNames(
                listOf("SampleCompanionObject1"),
                includeNested = true
            ) shouldBeEqualTo true
            hasCompanionObjectsWithAllNames(
                listOf("SampleCompanionObject1", "SampleCompanionObject2"),
                includeNested = true
            ) shouldBeEqualTo true
        }
    }

    @Test
    fun `interface-contains-companion-objects includeNested true`() {
        // given
        val sut =
            getSnippetFile("interface-contains-companion-objects")
                .interfaces()
                .first()

        // then
        val expected = listOf("SampleCompanionObject", "SampleNestedCompanionObject")

        sut
            .companionObjects(includeNested = true)
            .map { it.name }
            .shouldBeEqualTo(expected)
    }

    @Test
    fun `interface-contains-companion-objects includeNested false`() {
        // given
        val sut =
            getSnippetFile("interface-contains-companion-objects")
                .interfaces()
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
                .interfaces()
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
    fun `interface-has-no-companion-objects-ignore-case`() {
        // given
        val sut =
            getSnippetFile("interface-has-no-companion-objects-ignore-case")
                .interfaces()
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
                ignoreCase = true
            ) shouldBeEqualTo false
            hasCompanionObjectsWithAllNames(
                listOf(
                    "samplecompanionobject1",
                    "samplecompanionobject2"
                )
            ) shouldBeEqualTo false
            hasCompanionObjectsWithAllNames(
                listOf("samplecompanionobject1", "samplecompanionobject2"),
                ignoreCase = true
            ) shouldBeEqualTo false
            hasCompanionObjectsWithAllNames(
                setOf(
                    "samplecompanionobject1",
                    "samplecompanionobject2"
                )
            ) shouldBeEqualTo false
            hasCompanionObjectsWithAllNames(
                setOf("samplecompanionobject1", "samplecompanionobject2"),
                ignoreCase = true
            ) shouldBeEqualTo false
        }
    }

    @Test
    fun `interface-has-companion-objects-ignore-case`() {
        // given
        val sut =
            getSnippetFile("interface-has-companion-objects-ignore-case")
                .interfaces()
                .first()

        // then
        assertSoftly(sut) {
            hasCompanionObjectWithName("samplecompanionobject1", includeNested = true) shouldBeEqualTo false
            hasCompanionObjectWithName(
                "samplecompanionobject1",
                ignoreCase = true,
                includeNested = true
            ) shouldBeEqualTo true
            hasCompanionObjectWithName("othercompanionobject", includeNested = true) shouldBeEqualTo false
            hasCompanionObjectWithName(
                "othercompanionobject",
                ignoreCase = true,
                includeNested = true
            ) shouldBeEqualTo false
            hasCompanionObjectWithName(
                "samplecompanionobject1",
                "OtherCompanionObject",
                includeNested = true
            ) shouldBeEqualTo false
            hasCompanionObjectWithName(
                "samplecompanionobject1",
                "OtherCompanionObject",
                ignoreCase = true,
                includeNested = true
            ) shouldBeEqualTo true
            hasCompanionObjectWithName(listOf("samplecompanionobject1"), includeNested = true) shouldBeEqualTo false
            hasCompanionObjectWithName(
                listOf("samplecompanionobject1"),
                ignoreCase = true,
                includeNested = true
            ) shouldBeEqualTo true
            hasCompanionObjectWithName(listOf("othercompanionobject"), includeNested = true) shouldBeEqualTo false
            hasCompanionObjectWithName(
                listOf("othercompanionobject"),
                ignoreCase = true,
                includeNested = true
            ) shouldBeEqualTo false
            hasCompanionObjectWithName(
                listOf("samplecompanionobject1", "OtherCompanionObject"),
                includeNested = true
            ) shouldBeEqualTo false
            hasCompanionObjectWithName(
                listOf("samplecompanionobject1", "OtherCompanionObject"),
                ignoreCase = true,
                includeNested = true
            ) shouldBeEqualTo true
            hasCompanionObjectsWithAllNames("samplecompanionobject1", includeNested = true) shouldBeEqualTo false
            hasCompanionObjectsWithAllNames(
                "samplecompanionobject1",
                ignoreCase = true,
                includeNested = true
            ) shouldBeEqualTo true
            hasCompanionObjectsWithAllNames(
                "samplecompanionobject1",
                "samplecompanionobject2",
                includeNested = true
            ) shouldBeEqualTo false
            hasCompanionObjectsWithAllNames(
                "samplecompanionobject1",
                "samplecompanionobject2",
                ignoreCase = true,
                includeNested = true
            ) shouldBeEqualTo true
            hasCompanionObjectsWithAllNames(
                "samplecompanionobject1",
                "othercompanionobject",
                includeNested = true
            ) shouldBeEqualTo false
            hasCompanionObjectsWithAllNames(
                "samplecompanionobject1",
                "othercompanionobject",
                ignoreCase = true,
                includeNested = true
            ) shouldBeEqualTo false
            hasCompanionObjectsWithAllNames(
                listOf("samplecompanionobject1"),
                includeNested = true
            ) shouldBeEqualTo false
            hasCompanionObjectsWithAllNames(
                listOf("samplecompanionobject1"),
                ignoreCase = true,
                includeNested = true
            ) shouldBeEqualTo true
            hasCompanionObjectsWithAllNames(
                listOf("samplecompanionobject1", "samplecompanionobject2"),
                includeNested = true
            ) shouldBeEqualTo false
            hasCompanionObjectsWithAllNames(
                listOf("samplecompanionobject1", "samplecompanionobject2"),
                ignoreCase = true,
                includeNested = true
            ) shouldBeEqualTo true
            hasCompanionObjectsWithAllNames(
                listOf("samplecompanionobject1", "othercompanionobject"),
                includeNested = true
            ) shouldBeEqualTo false
            hasCompanionObjectsWithAllNames(
                listOf("samplecompanionobject1", "othercompanionobject"),
                ignoreCase = true,
                includeNested = true
            ) shouldBeEqualTo false
        }
    }

    private fun getSnippetFile(fileName: String) = getSnippetKoScope("core/declaration/kointerface/snippet/forkocompanionobjectprovider/", fileName)
}
