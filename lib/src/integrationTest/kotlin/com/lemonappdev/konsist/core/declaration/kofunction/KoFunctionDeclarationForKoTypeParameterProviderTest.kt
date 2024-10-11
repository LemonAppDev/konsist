package com.lemonappdev.konsist.core.declaration.kofunction

import com.lemonappdev.konsist.TestSnippetProvider
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoFunctionDeclarationForKoTypeParameterProviderTest {
    @Test
    fun `function-contains-no-type-parameters`() {
        // given
        val sut =
            getSnippetFile("function-contains-no-type-parameters")
                .functions()
                .first()

        // then
        assertSoftly(sut) {
            typeParameters shouldBeEqualTo emptyList()
            numTypeParameters shouldBeEqualTo 0
            countTypeParameters { it.hasNameStartingWith("sample") } shouldBeEqualTo 0
            hasTypeParameters() shouldBeEqualTo false
            hasTypeParameterWithName(emptyList()) shouldBeEqualTo false
            hasTypeParameterWithName(emptySet()) shouldBeEqualTo false
            hasTypeParametersWithAllNames(emptyList()) shouldBeEqualTo false
            hasTypeParametersWithAllNames(emptySet()) shouldBeEqualTo false
            hasTypeParameterWithName("sampleTypeParameter") shouldBeEqualTo false
            hasTypeParameterWithName(listOf("sampleTypeParameter")) shouldBeEqualTo false
            hasTypeParameterWithName(setOf("sampleTypeParameter")) shouldBeEqualTo false
            hasTypeParametersWithAllNames("sampleTypeParameter1", "sampleTypeParameter2") shouldBeEqualTo false
            hasTypeParametersWithAllNames(listOf("sampleTypeParameter1", "sampleTypeParameter2")) shouldBeEqualTo false
            hasTypeParametersWithAllNames(setOf("sampleTypeParameter1", "sampleTypeParameter2")) shouldBeEqualTo false
            hasTypeParameter { it.hasNameStartingWith("other") } shouldBeEqualTo false
            hasAllTypeParameters { it.hasNameStartingWith("sample") } shouldBeEqualTo true
        }
    }

    @Test
    fun `function-contains-one-parameter`() {
        // given
        val sut =
            getSnippetFile("function-contains-one-parameter")
                .functions()
                .first()

        // then
        assertSoftly(sut) {
            typeParameters.size shouldBeEqualTo 1
            numTypeParameters shouldBeEqualTo 1
            countTypeParameters { it.hasNameStartingWith("sample") } shouldBeEqualTo 1
            hasTypeParameters() shouldBeEqualTo true
            hasTypeParameterWithName(emptyList()) shouldBeEqualTo true
            hasTypeParameterWithName(emptySet()) shouldBeEqualTo true
            hasTypeParametersWithAllNames(emptyList()) shouldBeEqualTo true
            hasTypeParametersWithAllNames(emptySet()) shouldBeEqualTo true
            hasTypeParameterWithName("sampleTypeParameter") shouldBeEqualTo true
            hasTypeParameterWithName("otherTypeParameter") shouldBeEqualTo false
            hasTypeParameterWithName("sampleTypeParameter", "otherTypeParameter") shouldBeEqualTo true
            hasTypeParameterWithName(listOf("sampleTypeParameter")) shouldBeEqualTo true
            hasTypeParameterWithName(listOf("otherTypeParameter")) shouldBeEqualTo false
            hasTypeParameterWithName(listOf("sampleTypeParameter", "otherTypeParameter")) shouldBeEqualTo true
            hasTypeParameterWithName(setOf("sampleTypeParameter")) shouldBeEqualTo true
            hasTypeParameterWithName(setOf("otherTypeParameter")) shouldBeEqualTo false
            hasTypeParameterWithName(setOf("sampleTypeParameter", "otherTypeParameter")) shouldBeEqualTo true
            hasTypeParametersWithAllNames("sampleTypeParameter") shouldBeEqualTo true
            hasTypeParametersWithAllNames("sampleTypeParameter", "otherTypeParameter") shouldBeEqualTo false
            hasTypeParametersWithAllNames(listOf("sampleTypeParameter")) shouldBeEqualTo true
            hasTypeParametersWithAllNames(listOf("sampleTypeParameter", "otherTypeParameter")) shouldBeEqualTo false
            hasTypeParametersWithAllNames(setOf("sampleTypeParameter")) shouldBeEqualTo true
            hasTypeParametersWithAllNames(setOf("sampleTypeParameter", "otherTypeParameter")) shouldBeEqualTo false
            hasTypeParameter { it.hasNameStartingWith("sample") } shouldBeEqualTo true
            hasTypeParameter { it.hasNameStartingWith("other") } shouldBeEqualTo false
            hasAllTypeParameters { it.hasNameStartingWith("sample") } shouldBeEqualTo true
        }
    }

    @Test
    fun `function-contains-two-type-parameters`() {
        // given
        val sut =
            getSnippetFile("function-contains-two-type-parameters")
                .functions()
                .first()

        // then
        assertSoftly(sut) {
            typeParameters.size shouldBeEqualTo 2
            numTypeParameters shouldBeEqualTo 2
            countTypeParameters { it.hasNameStartingWith("sample") } shouldBeEqualTo 2
            countTypeParameters { param -> param.hasInModifier } shouldBeEqualTo 1
            hasTypeParameters() shouldBeEqualTo true
            hasTypeParameterWithName(emptyList()) shouldBeEqualTo true
            hasTypeParameterWithName(emptySet()) shouldBeEqualTo true
            hasTypeParametersWithAllNames(emptyList()) shouldBeEqualTo true
            hasTypeParametersWithAllNames(emptySet()) shouldBeEqualTo true
            hasTypeParameterWithName("sampleTypeParameter1") shouldBeEqualTo true
            hasTypeParameterWithName("otherTypeParameter") shouldBeEqualTo false
            hasTypeParameterWithName("sampleTypeParameter1", "otherName") shouldBeEqualTo true
            hasTypeParameterWithName(listOf("sampleTypeParameter1")) shouldBeEqualTo true
            hasTypeParameterWithName(listOf("otherTypeParameter")) shouldBeEqualTo false
            hasTypeParameterWithName(listOf("sampleTypeParameter1", "otherName")) shouldBeEqualTo true
            hasTypeParameterWithName(setOf("sampleTypeParameter1")) shouldBeEqualTo true
            hasTypeParameterWithName(setOf("otherTypeParameter")) shouldBeEqualTo false
            hasTypeParameterWithName(setOf("sampleTypeParameter1", "otherName")) shouldBeEqualTo true
            hasTypeParametersWithAllNames("sampleTypeParameter1") shouldBeEqualTo true
            hasTypeParametersWithAllNames("sampleTypeParameter1", "sampleTypeParameter2") shouldBeEqualTo true
            hasTypeParametersWithAllNames("sampleTypeParameter1", "otherTypeParameter") shouldBeEqualTo false
            hasTypeParametersWithAllNames(listOf("sampleTypeParameter1")) shouldBeEqualTo true
            hasTypeParametersWithAllNames(listOf("sampleTypeParameter1", "sampleTypeParameter2")) shouldBeEqualTo true
            hasTypeParametersWithAllNames(listOf("sampleTypeParameter1", "otherTypeParameter")) shouldBeEqualTo false
            hasTypeParametersWithAllNames(setOf("sampleTypeParameter1")) shouldBeEqualTo true
            hasTypeParametersWithAllNames(setOf("sampleTypeParameter1", "sampleTypeParameter2")) shouldBeEqualTo true
            hasTypeParametersWithAllNames(setOf("sampleTypeParameter1", "otherTypeParameter")) shouldBeEqualTo false
            hasTypeParameter { it.hasNameStartingWith("sample") } shouldBeEqualTo true
            hasTypeParameter { param -> param.isTypeParameter } shouldBeEqualTo true
            hasAllTypeParameters { it.hasNameStartingWith("sample") } shouldBeEqualTo true
            hasAllTypeParameters { param -> param.isExternalType } shouldBeEqualTo false
        }
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("core/declaration/kofunction/snippet/forkotypeparameterprovider/", fileName)
}
