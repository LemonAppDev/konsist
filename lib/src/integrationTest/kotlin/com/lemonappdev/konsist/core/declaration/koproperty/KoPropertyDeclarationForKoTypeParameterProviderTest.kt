package com.lemonappdev.konsist.core.declaration.koproperty

import com.lemonappdev.konsist.TestSnippetProvider
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoPropertyDeclarationForKoTypeParameterProviderTest {
    @Test
    fun `property-contains-no-type-parameters`() {
        // given
        val sut =
            getSnippetFile("property-contains-no-type-parameters")
                .properties()
                .first()

        // then
        assertSoftly(sut) {
            typeParameters shouldBeEqualTo emptyList()
            numTypeParameters shouldBeEqualTo 0
            countTypeParameters { it.name == "T" } shouldBeEqualTo 0
            hasTypeParameters() shouldBeEqualTo false
            hasTypeParameterWithName(emptyList()) shouldBeEqualTo false
            hasTypeParameterWithName(emptySet()) shouldBeEqualTo false
            hasTypeParametersWithAllNames(emptyList()) shouldBeEqualTo false
            hasTypeParametersWithAllNames(emptySet()) shouldBeEqualTo false
            hasTypeParameterWithName("T") shouldBeEqualTo false
            hasTypeParameterWithName(listOf("T")) shouldBeEqualTo false
            hasTypeParameterWithName(setOf("T")) shouldBeEqualTo false
            hasTypeParametersWithAllNames("T", "V") shouldBeEqualTo false
            hasTypeParametersWithAllNames(listOf("T", "V")) shouldBeEqualTo false
            hasTypeParametersWithAllNames(setOf("T", "V")) shouldBeEqualTo false
            hasTypeParameter { it.name == "T" } shouldBeEqualTo false
            hasAllTypeParameters { it.name == "T" } shouldBeEqualTo true
        }
    }

    @Test
    fun `property-contains-one-type-parameter`() {
        // given
        val sut =
            getSnippetFile("property-contains-one-type-parameter")
                .properties()
                .first()

        // then
        assertSoftly(sut) {
            typeParameters.size shouldBeEqualTo 1
            numTypeParameters shouldBeEqualTo 1
            countTypeParameters { it.name == "T" } shouldBeEqualTo 1
            hasTypeParameters() shouldBeEqualTo true
            hasTypeParameterWithName(emptyList()) shouldBeEqualTo true
            hasTypeParameterWithName(emptySet()) shouldBeEqualTo true
            hasTypeParametersWithAllNames(emptyList()) shouldBeEqualTo true
            hasTypeParametersWithAllNames(emptySet()) shouldBeEqualTo true
            hasTypeParameterWithName("T") shouldBeEqualTo true
            hasTypeParameterWithName("X") shouldBeEqualTo false
            hasTypeParameterWithName("T", "X") shouldBeEqualTo true
            hasTypeParameterWithName(listOf("T")) shouldBeEqualTo true
            hasTypeParameterWithName(listOf("X")) shouldBeEqualTo false
            hasTypeParameterWithName(listOf("T", "X")) shouldBeEqualTo true
            hasTypeParameterWithName(setOf("T")) shouldBeEqualTo true
            hasTypeParameterWithName(setOf("X")) shouldBeEqualTo false
            hasTypeParameterWithName(setOf("T", "X")) shouldBeEqualTo true
            hasTypeParametersWithAllNames("T") shouldBeEqualTo true
            hasTypeParametersWithAllNames("T", "X") shouldBeEqualTo false
            hasTypeParametersWithAllNames(listOf("T")) shouldBeEqualTo true
            hasTypeParametersWithAllNames(listOf("T", "X")) shouldBeEqualTo false
            hasTypeParametersWithAllNames(setOf("T")) shouldBeEqualTo true
            hasTypeParametersWithAllNames(setOf("T", "X")) shouldBeEqualTo false
            hasTypeParameter { it.name == "T" } shouldBeEqualTo true
            hasTypeParameter { it.hasNameStartingWith("other") } shouldBeEqualTo false
            hasAllTypeParameters { it.name == "T" } shouldBeEqualTo true
        }
    }

    @Test
    fun `property-contains-two-type-parameters`() {
        // given
        val sut =
            getSnippetFile("property-contains-two-type-parameters")
                .properties()
                .first()

        // then
        assertSoftly(sut) {
            typeParameters.size shouldBeEqualTo 2
            numTypeParameters shouldBeEqualTo 2
            countTypeParameters { it.name == "T" || it.name == "V" } shouldBeEqualTo 2
            countTypeParameters { param -> param.name == "T" } shouldBeEqualTo 1
            hasTypeParameters() shouldBeEqualTo true
            hasTypeParameterWithName(emptyList()) shouldBeEqualTo true
            hasTypeParameterWithName(emptySet()) shouldBeEqualTo true
            hasTypeParametersWithAllNames(emptyList()) shouldBeEqualTo true
            hasTypeParametersWithAllNames(emptySet()) shouldBeEqualTo true
            hasTypeParameterWithName("T") shouldBeEqualTo true
            hasTypeParameterWithName("X") shouldBeEqualTo false
            hasTypeParameterWithName("T", "otherName") shouldBeEqualTo true
            hasTypeParameterWithName(listOf("T")) shouldBeEqualTo true
            hasTypeParameterWithName(listOf("X")) shouldBeEqualTo false
            hasTypeParameterWithName(listOf("T", "otherName")) shouldBeEqualTo true
            hasTypeParameterWithName(setOf("T")) shouldBeEqualTo true
            hasTypeParameterWithName(setOf("X")) shouldBeEqualTo false
            hasTypeParameterWithName(setOf("T", "otherName")) shouldBeEqualTo true
            hasTypeParametersWithAllNames("T") shouldBeEqualTo true
            hasTypeParametersWithAllNames("T", "V") shouldBeEqualTo true
            hasTypeParametersWithAllNames("T", "X") shouldBeEqualTo false
            hasTypeParametersWithAllNames(listOf("T")) shouldBeEqualTo true
            hasTypeParametersWithAllNames(listOf("T", "V")) shouldBeEqualTo true
            hasTypeParametersWithAllNames(listOf("T", "X")) shouldBeEqualTo false
            hasTypeParametersWithAllNames(setOf("T")) shouldBeEqualTo true
            hasTypeParametersWithAllNames(setOf("T", "V")) shouldBeEqualTo true
            hasTypeParametersWithAllNames(setOf("T", "X")) shouldBeEqualTo false
            hasTypeParameter { it.name == "T" } shouldBeEqualTo true
            hasTypeParameter { param -> param.isTypeParameter } shouldBeEqualTo true
            hasAllTypeParameters { it.name == "T" || it.name == "V" } shouldBeEqualTo true
            hasAllTypeParameters { param -> param.isExternal } shouldBeEqualTo false
        }
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("core/declaration/koproperty/snippet/forkotypeparameterprovider/", fileName)
}
