package com.lemonappdev.konsist.core.declaration.kointerface

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import com.lemonappdev.konsist.api.KoModifier.COMPANION
import com.lemonappdev.konsist.api.KoModifier.INTERNAL
import com.lemonappdev.konsist.api.KoModifier.PRIVATE
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoInterfaceDeclarationForKoObjectProviderTest {
    @Test
    fun `interface-contains-no-objects`() {
        // given
        val sut = getSnippetFile("interface-contains-no-objects")
            .interfaces()
            .first()

        // then
        sut.objects(includeNested = true) shouldBeEqualTo emptyList()
    }

    @Test
    fun `interface-contains-objects includeNested true`() {
        // given
        val sut = getSnippetFile("interface-contains-objects")
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
        val sut = getSnippetFile("interface-contains-objects")
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
        val sut = getSnippetFile("count-objects")
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

    @Test
    fun `contains-objects-with-specified-conditions`() {
        // given
        val sut = getSnippetFile("contains-objects-with-specified-conditions")
            .interfaces()
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
        val sut = getSnippetFile("contains-objects-with-specified-regex")
            .interfaces()
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
        getSnippetKoScope("core/declaration/kointerface/snippet/forkoobjectprovider/", fileName)
}
