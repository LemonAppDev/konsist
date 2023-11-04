package com.lemonappdev.konsist.core.declaration.koclass

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import com.lemonappdev.konsist.api.KoModifier.INTERNAL
import com.lemonappdev.konsist.api.KoModifier.OPEN
import com.lemonappdev.konsist.api.KoModifier.PRIVATE
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoClassDeclarationForKoPropertyProviderTest {
    @Test
    fun `class-has-no-properties`() {
        // given
        val sut =
            getSnippetFile("class-has-no-properties")
                .classes()
                .first()

        // then
        assertSoftly(sut) {
            properties() shouldBeEqualTo emptyList()
            hasProperties() shouldBeEqualTo false
            hasPropertyWithName("sampleProperty") shouldBeEqualTo false
            hasPropertiesWithAllNames("sampleProperty1", "sampleProperty2") shouldBeEqualTo false
            hasProperty { it.name == "sampleProperty" } shouldBeEqualTo false
            hasAllProperties { it.hasNameStartingWith("sample") } shouldBeEqualTo true
        }
    }

    @Test
    fun `class-has-two-properties`() {
        // given
        val sut =
            getSnippetFile("class-has-two-properties")
                .classes()
                .first()

        // then
        assertSoftly(sut) {
            hasProperties() shouldBeEqualTo true
            hasPropertyWithName("sampleProperty1") shouldBeEqualTo true
            hasPropertyWithName("sampleProperty1", "otherProperty") shouldBeEqualTo true
            hasPropertiesWithAllNames("sampleProperty1") shouldBeEqualTo true
            hasPropertiesWithAllNames("sampleProperty1", "sampleProperty2") shouldBeEqualTo true
            hasPropertiesWithAllNames("sampleProperty1", "otherProperty") shouldBeEqualTo false
            hasProperty { it.name == "sampleProperty1" } shouldBeEqualTo true
            hasProperty { it.hasNameEndingWith("Property1") } shouldBeEqualTo true
            hasAllProperties { it.hasNameStartingWith("sample") } shouldBeEqualTo true
            hasAllProperties { it.hasNameEndingWith("Class1") } shouldBeEqualTo false
        }
    }

    @Test
    fun `class-contains-nested-properties includeNested true`() {
        // given
        val sut =
            getSnippetFile("class-contains-nested-properties")
                .classes()
                .first()

        // then
        val expected = listOf("sampleProperty", "sampleNestedProperty")

        sut.properties(includeNested = true)
            .map { it.name }
            .shouldBeEqualTo(expected)
    }

    @Test
    fun `class-contains-nested-properties includeNested false`() {
        // given
        val sut =
            getSnippetFile("class-contains-nested-properties")
                .classes()
                .first()

        // then
        val expected = listOf("sampleProperty")

        sut.properties(includeNested = false)
            .map { it.name }
            .shouldBeEqualTo(expected)
    }

    @Test
    fun `count-properties`() {
        // given
        val sut =
            getSnippetFile("count-properties")
                .classes()
                .first()

        // then
        assertSoftly(sut) {
            numProperties(includeNested = true) shouldBeEqualTo 2
            numProperties(includeNested = false) shouldBeEqualTo 1
            countProperties(includeNested = false) { it.hasInternalModifier } shouldBeEqualTo 1
            countProperties { it.hasInternalModifier } shouldBeEqualTo 2
            countProperties { it.name == "sampleProperty" && it.hasPrivateModifier } shouldBeEqualTo 0
        }
    }

    @Test
    fun `contains-properties-with-specified-conditions`() {
        // given
        val sut =
            getSnippetFile("contains-properties-with-specified-conditions")
                .classes()
                .first()

        // then
        assertSoftly(sut) {
            containsProperty { it.name == "sampleProperty" && it.hasInternalModifier } shouldBeEqualTo true
            containsProperty { it.name == "sampleProperty" && it.hasModifiers(INTERNAL, OPEN) } shouldBeEqualTo true
            containsProperty { it.name == "sampleProperty" && it.hasPublicModifier } shouldBeEqualTo false
            containsProperty { it.name == "sampleProperty" && it.hasModifiers(INTERNAL, PRIVATE) } shouldBeEqualTo false
            containsProperty(includeNested = false) { it.name == "sampleOtherProperty" } shouldBeEqualTo false
            containsProperty(includeNested = true) { it.name == "sampleNestedProperty" && it.hasInternalModifier } shouldBeEqualTo true
            containsProperty(includeNested = false) { it.name == "sampleNestedProperty" && it.hasInternalModifier } shouldBeEqualTo false
        }
    }

    @Test
    fun `contains-properties-with-specified-regex`() {
        // given
        val regex1 = Regex("[a-zA-Z]+")
        val regex2 = Regex("[0-9]+")
        val sut =
            getSnippetFile("contains-properties-with-specified-regex")
                .classes()
                .first()

        // then
        assertSoftly(sut) {
            containsProperty(includeNested = false) { it.name.matches(regex1) } shouldBeEqualTo true
            containsProperty(includeNested = true) { it.name.matches(regex1) } shouldBeEqualTo true
            containsProperty(includeNested = false) { it.name.matches(regex2) } shouldBeEqualTo false
            containsProperty(includeNested = true) { it.name.matches(regex2) } shouldBeEqualTo false
        }
    }

    @Test
    fun `class-contains-property-defined-at-constructor`() {
        // given
        val sut =
            getSnippetFile("class-contains-property-defined-at-constructor")
                .classes()
                .first()

        // then
        sut.properties()
            .map { it.name }
            .shouldBeEqualTo(listOf("sampleProperty"))
    }

    @Test
    fun `class-contains-property-defined-at-constructor-and-in-body`() {
        // given
        val sut =
            getSnippetFile("class-contains-property-defined-at-constructor-and-in-body")
                .classes()
                .first()

        // then
        sut.properties()
            .map { it.name }
            .shouldBeEqualTo(listOf("sampleProperty1", "sampleProperty2"))
    }

    private fun getSnippetFile(fileName: String) = getSnippetKoScope("core/declaration/koclass/snippet/forkopropertyprovider/", fileName)
}
