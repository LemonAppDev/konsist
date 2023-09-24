package com.lemonappdev.konsist.core.declaration.kofile

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import com.lemonappdev.konsist.api.KoModifier.DATA
import com.lemonappdev.konsist.api.KoModifier.INTERNAL
import com.lemonappdev.konsist.api.KoModifier.PRIVATE
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoFileDeclarationForKoObjectProviderTest {
    @Test
    fun `file-contains-no-objects`() {
        // given
        val sut = getSnippetFile("file-contains-no-objects")
            .files
            .first()

        // then
        sut.objects(includeNested = true) shouldBeEqualTo emptyList()
    }

    @Test
    fun `file-contains-objects includeNested true`() {
        // given
        val sut = getSnippetFile("file-contains-objects")
            .files
            .first()

        // then
        val expected = listOf("SampleObject", "SampleNestedObject")

        sut.objects(includeNested = true)
            .map { it.name }
            .shouldBeEqualTo(expected)
    }

    @Test
    fun `file-contains-objects includeNested false`() {
        // given
        val sut = getSnippetFile("file-contains-objects")
            .files
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
        val sut = getSnippetFile("count-objects")
            .files
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
        val sut = getSnippetFile("contains-objects-with-specified-conditions")
            .files
            .first()

        // then
        assertSoftly(sut) {
            containsObject {
                it.name == "SampleObject" && it.hasPrivateModifier
            } shouldBeEqualTo true
            containsObject {
                it.name == "SampleObject" && it.hasModifiersHHH(PRIVATE, DATA)
            } shouldBeEqualTo true
            containsObject {
                it.name == "SampleObject" && it.hasPublicModifier
            } shouldBeEqualTo false
            containsObject {
                it.name == "SampleObject" && it.hasModifiersHHH(INTERNAL, PRIVATE)
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
        val sut = getSnippetFile("contains-objects-with-specified-regex")
            .files
            .first()

        // then
        assertSoftly(sut) {
            containsObject(includeNested = false) { it.name.matches(regex1) } shouldBeEqualTo true
            containsObject(includeNested = true) { it.name.matches(regex1) } shouldBeEqualTo true
            containsObject(includeNested = false) { it.name.matches(regex2) } shouldBeEqualTo false
            containsObject(includeNested = true) { it.name.matches(regex2) } shouldBeEqualTo false
        }
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/kofile/snippet/forkoobjectprovider/", fileName)
}
