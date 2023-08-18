package com.lemonappdev.konsist.core.declaration.koobject

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import com.lemonappdev.konsist.api.KoModifier
import com.lemonappdev.konsist.api.KoModifier.ABSTRACT
import com.lemonappdev.konsist.api.KoModifier.PRIVATE
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoObjectDeclarationForKoInterfaceProviderTest {
    @Test
    fun `object-contains-no-interfaces`() {
        // given
        val sut = getSnippetFile("object-contains-no-interfaces")
            .objects()
            .first()

        // then
        sut.interfaces(includeNested = true) shouldBeEqualTo emptyList()
    }

    @Test
    fun `object-contains-interfaces includeNested true`() {
        // given
        val sut = getSnippetFile("object-contains-interfaces")
            .objects()
            .first()

        // then
        val expected = listOf("SampleInterface", "SampleNestedInterface")

        sut.interfaces(includeNested = true)
            .map { it.name }
            .shouldBeEqualTo(expected)
    }

    @Test
    fun `object-contains-interfaces includeNested false`() {
        // given
        val sut = getSnippetFile("object-contains-interfaces")
            .objects()
            .first()

        // then
        val expected = listOf("SampleInterface")

        sut.interfaces(includeNested = false)
            .map { it.name }
            .shouldBeEqualTo(expected)
    }

    @Test
    fun `count-interfaces`() {
        // given
        val sut = getSnippetFile("count-interfaces")
            .objects()
            .first()

        // then
        assertSoftly(sut) {
            numInterfaces(includeNested = true) shouldBeEqualTo 2
            numInterfaces(includeNested = false) shouldBeEqualTo 1
            countInterfaces { it.hasPrivateModifier } shouldBeEqualTo 1
            countInterfaces(includeNested = true) { it.hasPrivateModifier } shouldBeEqualTo 2
            countInterfaces { it.hasInternalModifier } shouldBeEqualTo 0
        }
    }

    @Test
    fun `contains-interfaces-with-specified-conditions`() {
        // given
        val sut = getSnippetFile("contains-interfaces-with-specified-conditions")
            .objects()
            .first()

        // then
        assertSoftly(sut) {
            containsInterface {
                it.name == "SampleInterface" && it.hasPrivateModifier
            } shouldBeEqualTo true
            containsInterface {
                it.name == "SampleInterface" && it.hasModifiers(PRIVATE, ABSTRACT)
            } shouldBeEqualTo true
            containsInterface {
                it.name == "SampleInterface" && it.hasPublicModifier
            } shouldBeEqualTo false
            containsInterface {
                it.name == "SampleInterface" && it.hasModifiers(KoModifier.INTERNAL, PRIVATE)
            } shouldBeEqualTo false
            containsInterface(includeNested = true) { it.name == "SampleNestedInterface" && it.hasPrivateModifier } shouldBeEqualTo true
            containsInterface(includeNested = false) { it.name == "SampleNestedInterface" && it.hasPrivateModifier } shouldBeEqualTo false
            containsInterface(includeNested = true) { it.name == "SampleNestedInterface" && it.hasActualModifier } shouldBeEqualTo false
        }
    }

    @Test
    fun `contains-interfaces-with-specified-regex`() {
        // given
        val regex1 = Regex("[a-zA-Z]+")
        val regex2 = Regex("[0-9]+")
        val sut = getSnippetFile("contains-interfaces-with-specified-regex")
            .objects()
            .first()

        // then
        assertSoftly(sut) {
            containsInterface(includeNested = false) { it.name.matches(regex1) } shouldBeEqualTo true
            containsInterface(includeNested = true) { it.name.matches(regex1) } shouldBeEqualTo true
            containsInterface(includeNested = false) { it.name.matches(regex2) } shouldBeEqualTo false
            containsInterface(includeNested = true) { it.name.matches(regex2) } shouldBeEqualTo false
        }
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/koobject/snippet/forkointerfaceprovider/", fileName)
}
