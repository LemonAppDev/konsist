package com.lemonappdev.konsist.core.declaration.kofile

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoFileDeclarationForKoPropertyProviderTest {
    @Test
    fun `file-has-no-properties`() {
        // given
        val sut =
            getSnippetFile("file-has-no-properties")
                .files
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
    fun `file-has-two-properties`() {
        // given
        val sut =
            getSnippetFile("file-has-two-properties")
                .files
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
    fun `file-contains-nested-properties includeNested true`() {
        // given
        val sut =
            getSnippetFile("file-contains-nested-properties")
                .files
                .first()

        // then
        val expected = listOf("fixtureProperty", "fixtureNestedProperty")

        sut
            .properties(includeNested = true)
            .map { it.name }
            .shouldBeEqualTo(expected)
    }

    @Test
    fun `file-contains-nested-properties includeNested false`() {
        // given
        val sut =
            getSnippetFile("file-contains-nested-properties")
                .files
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
                .files
                .first()

        // then
        assertSoftly(sut) {
            numProperties(includeNested = true) shouldBeEqualTo 2
            numProperties(includeNested = false) shouldBeEqualTo 1
            countProperties(includeNested = false) { it.hasInternalModifier } shouldBeEqualTo 1
            countProperties { it.hasInternalModifier } shouldBeEqualTo 2
            countProperties { it.name == "fixtureProperty" && it.hasPrivateModifier } shouldBeEqualTo 0
        }
    }

    @Test
    fun `file-has-no-properties-ignore-case`() {
        // given
        val sut =
            getSnippetFile("file-has-no-properties-ignore-case")
                .files
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
    fun `file-has-properties-ignore-case`() {
        // given
        val sut =
            getSnippetFile("file-has-properties-ignore-case")
                .files
                .first()

        // then
        assertSoftly(sut) {
            hasPropertyWithName("fixtureproperty1") shouldBeEqualTo false
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

    private fun getSnippetFile(fileName: String) = getSnippetKoScope("core/declaration/kofile/snippet/forkopropertyprovider/", fileName)
}
