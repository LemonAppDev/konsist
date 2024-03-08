package com.lemonappdev.konsist.core.declaration.koobject

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import com.lemonappdev.konsist.api.KoModifier.INTERNAL
import com.lemonappdev.konsist.api.KoModifier.PRIVATE
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoObjectDeclarationForKoPropertyProviderTest {
    @Test
    fun `object-has-no-properties`() {
        // given
        val sut =
            getSnippetFile("object-has-no-properties")
                .objects()
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
    fun `object-has-two-properties`() {
        // given
        val sut =
            getSnippetFile("object-has-two-properties")
                .objects()
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
    fun `object-contains-nested-properties includeNested true`() {
        // given
        val sut =
            getSnippetFile("object-contains-nested-properties")
                .objects()
                .first()

        // then
        val expected = listOf("sampleProperty", "sampleNestedProperty")

        sut.properties(includeNested = true)
            .map { it.name }
            .shouldBeEqualTo(expected)
    }

    @Test
    fun `object-contains-nested-properties includeNested false`() {
        // given
        val sut =
            getSnippetFile("object-contains-nested-properties")
                .objects()
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
                .objects()
                .first()

        // then
        assertSoftly(sut) {
            numProperties(includeNested = true) shouldBeEqualTo 2
            numProperties(includeNested = false) shouldBeEqualTo 1
            countProperties(includeNested = false) { it.hasValModifier } shouldBeEqualTo 1
            countProperties { it.hasValModifier } shouldBeEqualTo 2
            countProperties { it.name == "sampleProperty" && it.hasVarModifier } shouldBeEqualTo 0
        }
    }

    @Test
    fun `contains-properties-with-specified-conditions`() {
        // given
        val sut =
            getSnippetFile("contains-properties-with-specified-conditions")
                .objects()
                .first()

        // then
        assertSoftly(sut) {
            containsProperty { it.name == "sampleProperty" && it.hasInternalModifier } shouldBeEqualTo true
            containsProperty { it.name == "sampleProperty" && it.hasPublicModifier } shouldBeEqualTo false
            containsProperty { it.name == "sampleProperty" && it.hasModifiers(INTERNAL, PRIVATE) } shouldBeEqualTo false
            containsProperty(includeNested = false) { it.name == "sampleOtherProperty" } shouldBeEqualTo false
            containsProperty(includeNested = true) { it.name == "sampleNestedProperty" && it.hasInternalModifier } shouldBeEqualTo true
            containsProperty(includeNested = false) { it.name == "sampleNestedProperty" && it.hasInternalModifier } shouldBeEqualTo false
            containsProperty(includeNested = true) { it.name == "sampleNestedProperty" && it.hasOpenModifier } shouldBeEqualTo false
        }
    }

    @Test
    fun `contains-properties-with-specified-regex`() {
        // given
        val regex1 = Regex("[a-zA-Z]+")
        val regex2 = Regex("[0-9]+")
        val sut =
            getSnippetFile("contains-properties-with-specified-regex")
                .objects()
                .first()

        // then
        assertSoftly(sut) {
            containsProperty(includeNested = false) { it.name.matches(regex1) } shouldBeEqualTo true
            containsProperty(includeNested = true) { it.name.matches(regex1) } shouldBeEqualTo true
            containsProperty(includeNested = false) { it.name.matches(regex2) } shouldBeEqualTo false
            containsProperty(includeNested = true) { it.name.matches(regex2) } shouldBeEqualTo false
        }
    }

    private fun getSnippetFile(fileName: String) = getSnippetKoScope("core/declaration/koobject/snippet/forkopropertyprovider/", fileName)
}
