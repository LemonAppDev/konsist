package com.lemonappdev.konsist.core.declaration.koenumconstant

import com.lemonappdev.konsist.TestSnippetProvider
import com.lemonappdev.konsist.api.ext.list.enumConstants
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoEnumConstantDeclarationForKoPropertyProviderTest {
    @Test
    fun `enum-constant-has-no-properties`() {
        // given
        val sut =
            getSnippetFile("enum-constant-has-no-properties")
                .classes()
                .enumConstants
                .first()

        // then
        assertSoftly(sut) {
            properties() shouldBeEqualTo emptyList()
            hasProperties() shouldBeEqualTo false
            hasPropertyWithName(emptyList()) shouldBeEqualTo false
            hasPropertyWithName(emptySet()) shouldBeEqualTo false
            hasPropertiesWithAllNames(emptyList()) shouldBeEqualTo false
            hasPropertiesWithAllNames(emptySet()) shouldBeEqualTo false
            hasPropertyWithName("sampleProperty") shouldBeEqualTo false
            hasPropertyWithName(listOf("sampleProperty")) shouldBeEqualTo false
            hasPropertyWithName(setOf("sampleProperty")) shouldBeEqualTo false
            hasPropertiesWithAllNames("sampleProperty1", "sampleProperty2") shouldBeEqualTo false
            hasPropertiesWithAllNames(listOf("sampleProperty1", "sampleProperty2")) shouldBeEqualTo false
            hasPropertiesWithAllNames(setOf("sampleProperty1", "sampleProperty2")) shouldBeEqualTo false
            hasProperty { it.name == "sampleProperty" } shouldBeEqualTo false
            hasAllProperties { it.hasNameStartingWith("sample") } shouldBeEqualTo true
        }
    }

    @Test
    fun `enum-constant-has-two-properties`() {
        // given
        val sut =
            getSnippetFile("enum-constant-has-two-properties")
                .classes()
                .enumConstants
                .first()

        // then
        assertSoftly(sut) {
            hasProperties() shouldBeEqualTo true
            hasPropertyWithName(emptyList()) shouldBeEqualTo true
            hasPropertyWithName(emptySet()) shouldBeEqualTo true
            hasPropertiesWithAllNames(emptyList()) shouldBeEqualTo true
            hasPropertiesWithAllNames(emptySet()) shouldBeEqualTo true
            hasPropertyWithName("sampleProperty1") shouldBeEqualTo true
            hasPropertyWithName("sampleProperty1", "otherProperty") shouldBeEqualTo true
            hasPropertyWithName(listOf("sampleProperty1")) shouldBeEqualTo true
            hasPropertyWithName(listOf("sampleProperty1", "otherProperty")) shouldBeEqualTo true
            hasPropertyWithName(setOf("sampleProperty1")) shouldBeEqualTo true
            hasPropertyWithName(setOf("sampleProperty1", "otherProperty")) shouldBeEqualTo true
            hasPropertiesWithAllNames("sampleProperty1") shouldBeEqualTo true
            hasPropertiesWithAllNames("sampleProperty1", "sampleProperty2") shouldBeEqualTo true
            hasPropertiesWithAllNames("sampleProperty1", "otherProperty") shouldBeEqualTo false
            hasPropertiesWithAllNames(listOf("sampleProperty1")) shouldBeEqualTo true
            hasPropertiesWithAllNames(listOf("sampleProperty1", "sampleProperty2")) shouldBeEqualTo true
            hasPropertiesWithAllNames(listOf("sampleProperty1", "otherProperty")) shouldBeEqualTo false
            hasPropertiesWithAllNames(setOf("sampleProperty1")) shouldBeEqualTo true
            hasPropertiesWithAllNames(setOf("sampleProperty1", "sampleProperty2")) shouldBeEqualTo true
            hasPropertiesWithAllNames(setOf("sampleProperty1", "otherProperty")) shouldBeEqualTo false
            hasProperty { it.name == "sampleProperty1" } shouldBeEqualTo true
            hasProperty { it.hasNameEndingWith("Property1") } shouldBeEqualTo true
            hasAllProperties { it.hasNameStartingWith("sample") } shouldBeEqualTo true
            hasAllProperties { it.hasNameEndingWith("Class1") } shouldBeEqualTo false
        }
    }

    @Test
    fun `enum-constant-contains-nested-properties includeNested true`() {
        // given
        val sut =
            getSnippetFile("enum-constant-contains-nested-properties")
                .classes()
                .enumConstants
                .first()

        // then
        val expected = listOf("sampleProperty1", "sampleInnerProperty")

        sut
            .properties(includeNested = true)
            .map { it.name }
            .shouldBeEqualTo(expected)
    }

    @Test
    fun `enum-constant-contains-nested-properties includeNested false`() {
        // given
        val sut =
            getSnippetFile("enum-constant-contains-nested-properties")
                .classes()
                .enumConstants
                .first()

        // then
        val expected = listOf("sampleProperty1")

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
                .classes()
                .enumConstants
                .first()

        // then
        assertSoftly(sut) {
            numProperties(includeNested = true) shouldBeEqualTo 2
            numProperties(includeNested = false) shouldBeEqualTo 1
            countProperties(includeNested = false) { it.isVal } shouldBeEqualTo 1
            countProperties { it.isVal } shouldBeEqualTo 2
            countProperties { it.name == "sampleProperty" && it.isVar } shouldBeEqualTo 0
        }
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("core/declaration/koenumconstant/snippet/forkopropertyprovider/", fileName)
}
