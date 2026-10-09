package com.lemonappdev.konsist.core.declaration.koobject

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
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
            hasPropertyWithName(emptyList()) shouldBeEqualTo false
            hasPropertyWithName(emptySet()) shouldBeEqualTo false
            hasPropertiesWithAllNames(emptyList()) shouldBeEqualTo false
            hasPropertiesWithAllNames(emptySet()) shouldBeEqualTo false
            hasPropertyWithName("fixtureProperty") shouldBeEqualTo false
            hasPropertyWithName(listOf("fixtureProperty")) shouldBeEqualTo false
            hasPropertyWithName(setOf("fixtureProperty")) shouldBeEqualTo false
            hasPropertiesWithAllNames("fixtureProperty1", "fixtureProperty2") shouldBeEqualTo false
            hasPropertiesWithAllNames(listOf("fixtureProperty1", "fixtureProperty2")) shouldBeEqualTo false
            hasPropertiesWithAllNames(setOf("fixtureProperty1", "fixtureProperty2")) shouldBeEqualTo false
            hasProperty { it.name == "fixtureProperty" } shouldBeEqualTo false
            hasAllProperties { it.hasNameStartingWith("fixture") } shouldBeEqualTo true
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
            hasPropertyWithName(emptyList()) shouldBeEqualTo true
            hasPropertyWithName(emptySet()) shouldBeEqualTo true
            hasPropertiesWithAllNames(emptyList()) shouldBeEqualTo true
            hasPropertiesWithAllNames(emptySet()) shouldBeEqualTo true
            hasPropertyWithName("fixtureProperty1") shouldBeEqualTo true
            hasPropertyWithName("fixtureProperty1", "otherProperty") shouldBeEqualTo true
            hasPropertyWithName(listOf("fixtureProperty1")) shouldBeEqualTo true
            hasPropertyWithName(listOf("fixtureProperty1", "otherProperty")) shouldBeEqualTo true
            hasPropertyWithName(setOf("fixtureProperty1")) shouldBeEqualTo true
            hasPropertyWithName(setOf("fixtureProperty1", "otherProperty")) shouldBeEqualTo true
            hasPropertiesWithAllNames("fixtureProperty1") shouldBeEqualTo true
            hasPropertiesWithAllNames("fixtureProperty1", "fixtureProperty2") shouldBeEqualTo true
            hasPropertiesWithAllNames("fixtureProperty1", "otherProperty") shouldBeEqualTo false
            hasPropertiesWithAllNames(listOf("fixtureProperty1")) shouldBeEqualTo true
            hasPropertiesWithAllNames(listOf("fixtureProperty1", "fixtureProperty2")) shouldBeEqualTo true
            hasPropertiesWithAllNames(listOf("fixtureProperty1", "otherProperty")) shouldBeEqualTo false
            hasPropertiesWithAllNames(setOf("fixtureProperty1")) shouldBeEqualTo true
            hasPropertiesWithAllNames(setOf("fixtureProperty1", "fixtureProperty2")) shouldBeEqualTo true
            hasPropertiesWithAllNames(setOf("fixtureProperty1", "otherProperty")) shouldBeEqualTo false
            hasProperty { it.name == "fixtureProperty1" } shouldBeEqualTo true
            hasProperty { it.hasNameEndingWith("Property1") } shouldBeEqualTo true
            hasAllProperties { it.hasNameStartingWith("fixture") } shouldBeEqualTo true
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
        val expected = listOf("fixtureProperty", "fixtureNestedProperty")

        sut
            .properties(includeNested = true)
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
        val expected = listOf("fixtureProperty")

        sut
            .properties(includeNested = false)
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
            countProperties { it.name == "fixtureProperty" && it.hasVarModifier } shouldBeEqualTo 0
        }
    }

    @Test
    fun `object-has-no-properties-ignore-case`() {
        // given
        val sut =
            getSnippetFile("object-has-no-properties-ignore-case")
                .objects()
                .first()

        // then
        assertSoftly(sut) {
            hasPropertyWithName("fixtureproperty") shouldBeEqualTo false
            hasPropertyWithName("fixtureproperty", ignoreCase = true) shouldBeEqualTo false
            hasPropertyWithName(listOf("fixtureproperty")) shouldBeEqualTo false
            hasPropertyWithName(listOf("fixtureproperty"), ignoreCase = true) shouldBeEqualTo false
            hasPropertyWithName(setOf("fixtureproperty")) shouldBeEqualTo false
            hasPropertyWithName(setOf("fixtureproperty"), ignoreCase = true) shouldBeEqualTo false
            hasPropertiesWithAllNames("fixtureproperty1", "fixtureproperty2") shouldBeEqualTo false
            hasPropertiesWithAllNames("fixtureproperty1", "fixtureproperty2", ignoreCase = true) shouldBeEqualTo false
            hasPropertiesWithAllNames(listOf("fixtureproperty1", "fixtureproperty2")) shouldBeEqualTo false
            hasPropertiesWithAllNames(listOf("fixtureproperty1", "fixtureproperty2"), ignoreCase = true) shouldBeEqualTo false
            hasPropertiesWithAllNames(setOf("fixtureproperty1", "fixtureproperty2")) shouldBeEqualTo false
            hasPropertiesWithAllNames(setOf("fixtureproperty1", "fixtureproperty2"), ignoreCase = true) shouldBeEqualTo false
        }
    }

    @Test
    fun `object-has-properties-ignore-case`() {
        // given
        val sut =
            getSnippetFile("object-has-properties-ignore-case")
                .objects()
                .first()

        // then
        assertSoftly(sut) {
            hasPropertyWithName("fixtureproperty1", ignoreCase = true) shouldBeEqualTo true
            hasPropertyWithName("otherproperty") shouldBeEqualTo false
            hasPropertyWithName("otherproperty", ignoreCase = true) shouldBeEqualTo false
            hasPropertyWithName("fixtureproperty1", "otherName") shouldBeEqualTo false
            hasPropertyWithName("fixtureproperty1", "otherName", ignoreCase = true) shouldBeEqualTo true
            hasPropertyWithName(listOf("fixtureproperty1")) shouldBeEqualTo false
            hasPropertyWithName(listOf("fixtureproperty1"), ignoreCase = true) shouldBeEqualTo true
            hasPropertyWithName(listOf("otherproperty")) shouldBeEqualTo false
            hasPropertyWithName(listOf("otherproperty"), ignoreCase = true) shouldBeEqualTo false
            hasPropertyWithName(listOf("fixtureproperty1", "otherName")) shouldBeEqualTo false
            hasPropertyWithName(listOf("fixtureproperty1", "otherName"), ignoreCase = true) shouldBeEqualTo true
            hasPropertiesWithAllNames("fixtureproperty1") shouldBeEqualTo false
            hasPropertiesWithAllNames("fixtureproperty1", ignoreCase = true) shouldBeEqualTo true
            hasPropertiesWithAllNames("fixtureproperty1", "fixtureproperty2") shouldBeEqualTo false
            hasPropertiesWithAllNames("fixtureproperty1", "fixtureproperty2", ignoreCase = true) shouldBeEqualTo true
            hasPropertiesWithAllNames("fixtureproperty1", "otherproperty") shouldBeEqualTo false
            hasPropertiesWithAllNames("fixtureproperty1", "otherproperty", ignoreCase = true) shouldBeEqualTo false
            hasPropertiesWithAllNames(listOf("fixtureproperty1")) shouldBeEqualTo false
            hasPropertiesWithAllNames(listOf("fixtureproperty1"), ignoreCase = true) shouldBeEqualTo true
            hasPropertiesWithAllNames(listOf("fixtureproperty1", "fixtureproperty2")) shouldBeEqualTo false
            hasPropertiesWithAllNames(listOf("fixtureproperty1", "fixtureproperty2"), ignoreCase = true) shouldBeEqualTo true
            hasPropertiesWithAllNames(listOf("fixtureproperty1", "otherproperty")) shouldBeEqualTo false
            hasPropertiesWithAllNames(listOf("fixtureproperty1", "otherproperty"), ignoreCase = true) shouldBeEqualTo false
        }
    }

    private fun getSnippetFile(fileName: String) = getSnippetKoScope("core/declaration/koobject/snippet/forkopropertyprovider/", fileName)
}
