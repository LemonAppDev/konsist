package com.lemonappdev.konsist.core.declaration.kofile

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoFileDeclarationForKoCompanionObjectProviderTest {
    @Test
    fun `file-has-no-direct-companion-object`() {
        // given
        val sut =
            getSnippetFile("file-has-no-direct-companion-object")
                .files
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
    fun `file-contains-companion-objects includeNested true`() {
        // given
        val sut =
            getSnippetFile("file-contains-companion-objects")
                .files
                .first()

        // then
        val expected = listOf("SampleCompanionObject1", "SampleCompanionObject2")

        sut
            .companionObjects()
            .map { it.name }
            .shouldBeEqualTo(expected)
    }

    @Test
    fun `file-contains-companion-objects includeNested false`() {
        // given
        val sut =
            getSnippetFile("file-contains-companion-objects")
                .files
                .first()

        // then
        val expected = emptyList<String>()

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
                .files
                .first()

        // then
        assertSoftly(sut) {
            numCompanionObjects() shouldBeEqualTo 2
            numCompanionObjects(includeNested = false) shouldBeEqualTo 0
            countCompanionObjects { it.hasPrivateModifier } shouldBeEqualTo 2
            countCompanionObjects(includeNested = false) { it.hasPrivateModifier } shouldBeEqualTo 0
            countCompanionObjects { it.hasInternalModifier } shouldBeEqualTo 0
        }
    }

    @Test
    fun `file-has-no-direct-companion-objects-ignore-case`() {
        // given
        val sut =
            getSnippetFile("file-has-no-direct-companion-objects-ignore-case")
                .files
                .first()

        // then
        assertSoftly(sut) {
            hasCompanionObjectWithName("samplecompanionobject", includeNested = false) shouldBeEqualTo false
            hasCompanionObjectWithName(
                "samplecompanionobject",
                ignoreCase = true,
                includeNested = false,
            ) shouldBeEqualTo false
            hasCompanionObjectWithName(listOf("samplecompanionobject"), includeNested = false) shouldBeEqualTo false
            hasCompanionObjectWithName(
                listOf("samplecompanionobject"),
                ignoreCase = true,
                includeNested = false,
            ) shouldBeEqualTo false
            hasCompanionObjectWithName(setOf("samplecompanionobject"), includeNested = false) shouldBeEqualTo false
            hasCompanionObjectWithName(
                setOf("samplecompanionobject"),
                ignoreCase = true,
                includeNested = false,
            ) shouldBeEqualTo false
            hasCompanionObjectsWithAllNames(
                "samplecompanionobject1",
                "samplecompanionobject2",
                includeNested = false,
            ) shouldBeEqualTo false
            hasCompanionObjectsWithAllNames(
                "samplecompanionobject1",
                "samplecompanionobject2",
                ignoreCase = true,
                includeNested = false,
            ) shouldBeEqualTo false
            hasCompanionObjectsWithAllNames(
                listOf(
                    "samplecompanionobject1",
                    "samplecompanionobject2",
                ),
                includeNested = false,
            ) shouldBeEqualTo false
            hasCompanionObjectsWithAllNames(
                listOf("samplecompanionobject1", "samplecompanionobject2"),
                ignoreCase = true,
                includeNested = false,
            ) shouldBeEqualTo false
            hasCompanionObjectsWithAllNames(
                setOf(
                    "samplecompanionobject1",
                    "samplecompanionobject2",
                ),
                includeNested = false,
            ) shouldBeEqualTo false
            hasCompanionObjectsWithAllNames(
                setOf("samplecompanionobject1", "samplecompanionobject2"),
                ignoreCase = true,
                includeNested = false,
            ) shouldBeEqualTo false
        }
    }

    @Test
    fun `file-has-companion-objects-ignore-case`() {
        // given
        val sut =
            getSnippetFile("file-has-companion-objects-ignore-case")
                .files
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
        getSnippetKoScope("core/declaration/kofile/snippet/forkocompanionobjectprovider/", fileName)
}
