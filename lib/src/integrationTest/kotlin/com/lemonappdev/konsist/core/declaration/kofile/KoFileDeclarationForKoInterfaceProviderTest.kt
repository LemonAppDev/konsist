package com.lemonappdev.konsist.core.declaration.kofile

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import com.lemonappdev.konsist.api.KoModifier.ABSTRACT
import com.lemonappdev.konsist.api.KoModifier.INTERNAL
import com.lemonappdev.konsist.api.KoModifier.PRIVATE
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoFileDeclarationForKoInterfaceProviderTest {
    @Test
    fun `file-contains-no-interfaces`() {
        // given
        val sut = getSnippetFile("file-contains-no-interfaces")
            .files
            .first()

        // then
        sut.interfaces(includeNested = true) shouldBeEqualTo emptyList()
    }

    @Test
    fun `file-contains-interfaces includeNested true`() {
        // given
        val sut = getSnippetFile("file-contains-interfaces")
            .files
            .first()

        // then
        val expected = listOf("SampleInterface", "SampleNestedInterface")

        sut.interfaces(includeNested = true)
            .map { it.name }
            .shouldBeEqualTo(expected)
    }

    @Test
    fun `file-contains-interfaces includeNested false`() {
        // given
        val sut = getSnippetFile("file-contains-interfaces")
            .files
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
            .files
            .first()

        // then
        assertSoftly(sut) {
            countInterfaces(includeNested = true) shouldBeEqualTo 2
            countInterfaces(includeNested = false) shouldBeEqualTo 1
            countInterfaces { it.hasPrivateModifier } shouldBeEqualTo 1
            countInterfaces(includeNested = true) { it.hasPrivateModifier } shouldBeEqualTo 2
            countInterfaces { it.hasInternalModifier } shouldBeEqualTo 0
        }
    }

    @Test
    fun `contains-interfaces-with-specified-conditions`() {
        // given
        val sut = getSnippetFile("contains-interfaces-with-specified-conditions")
            .files
            .first()

        // then
        assertSoftly(sut) {
            containsInterface { it.name == "SampleInterface" && it.hasPrivateModifier } shouldBeEqualTo true
            containsInterface { it.name == "SampleInterface" && it.hasModifiers(PRIVATE, ABSTRACT) } shouldBeEqualTo true
            containsInterface { it.name == "SampleInterface" && it.hasPublicModifier } shouldBeEqualTo false
            containsInterface { it.name == "SampleInterface" && it.hasModifiers(INTERNAL, PRIVATE) } shouldBeEqualTo false
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
            .files
            .first()

        // then
        assertSoftly(sut) {
            containsInterface(includeNested = false) { it.name.matches(regex1) } shouldBeEqualTo true
            containsInterface(includeNested = true) { it.name.matches(regex1) } shouldBeEqualTo true
            containsInterface(includeNested = false) { it.name.matches(regex2) } shouldBeEqualTo false
            containsInterface(includeNested = true) { it.name.matches(regex2) } shouldBeEqualTo false
        }
    }

    private fun getSnippetFile(fileName: String) = getSnippetKoScope(
        "core/declaration/kofile/snippet/forkointerfaceprovider/",
        fileName,
    )
}
