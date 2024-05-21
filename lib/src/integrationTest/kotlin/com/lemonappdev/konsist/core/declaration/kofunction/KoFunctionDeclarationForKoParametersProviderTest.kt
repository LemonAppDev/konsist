package com.lemonappdev.konsist.core.declaration.kofunction

import com.lemonappdev.konsist.TestSnippetProvider
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoFunctionDeclarationForKoParametersProviderTest {
    @Test
    fun `function-contains-no-parameters`() {
        // given
        val sut =
            getSnippetFile("function-contains-no-parameters")
                .functions()
                .first()

        // then
        assertSoftly(sut) {
            parameters shouldBeEqualTo emptyList()
            numParameters shouldBeEqualTo 0
            countParameters { it.hasNameStartingWith("sample") } shouldBeEqualTo 0
            hasParameters() shouldBeEqualTo false
            hasParameterWithName(emptyList()) shouldBeEqualTo false
            hasParameterWithName(emptySet()) shouldBeEqualTo false
            hasParametersWithAllNames(emptyList()) shouldBeEqualTo false
            hasParametersWithAllNames(emptySet()) shouldBeEqualTo false
            hasParameterWithName("sampleParameter") shouldBeEqualTo false
            hasParameterWithName(listOf("sampleParameter")) shouldBeEqualTo false
            hasParameterWithName(setOf("sampleParameter")) shouldBeEqualTo false
            hasParametersWithAllNames("sampleParameter1", "sampleParameter2") shouldBeEqualTo false
            hasParametersWithAllNames(listOf("sampleParameter1", "sampleParameter2")) shouldBeEqualTo false
            hasParametersWithAllNames(setOf("sampleParameter1", "sampleParameter2")) shouldBeEqualTo false
            hasParameter { it.hasNameStartingWith("other") } shouldBeEqualTo false
            hasAllParameters { it.hasNameStartingWith("sample") } shouldBeEqualTo true
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
            parameters.size shouldBeEqualTo 1
            numParameters shouldBeEqualTo 1
            countParameters { it.hasNameStartingWith("sample") } shouldBeEqualTo 1
            hasParameters() shouldBeEqualTo true
            hasParameterWithName(emptyList()) shouldBeEqualTo true
            hasParameterWithName(emptySet()) shouldBeEqualTo true
            hasParametersWithAllNames(emptyList()) shouldBeEqualTo true
            hasParametersWithAllNames(emptySet()) shouldBeEqualTo true
            hasParameterWithName("sampleParameter") shouldBeEqualTo true
            hasParameterWithName("otherParameter") shouldBeEqualTo false
            hasParameterWithName("sampleParameter", "otherParameter") shouldBeEqualTo true
            hasParameterWithName(listOf("sampleParameter")) shouldBeEqualTo true
            hasParameterWithName(listOf("otherParameter")) shouldBeEqualTo false
            hasParameterWithName(listOf("sampleParameter", "otherParameter")) shouldBeEqualTo true
            hasParameterWithName(setOf("sampleParameter")) shouldBeEqualTo true
            hasParameterWithName(setOf("otherParameter")) shouldBeEqualTo false
            hasParameterWithName(setOf("sampleParameter", "otherParameter")) shouldBeEqualTo true
            hasParametersWithAllNames("sampleParameter") shouldBeEqualTo true
            hasParametersWithAllNames("sampleParameter", "otherParameter") shouldBeEqualTo false
            hasParametersWithAllNames(listOf("sampleParameter")) shouldBeEqualTo true
            hasParametersWithAllNames(listOf("sampleParameter", "otherParameter")) shouldBeEqualTo false
            hasParametersWithAllNames(setOf("sampleParameter")) shouldBeEqualTo true
            hasParametersWithAllNames(setOf("sampleParameter", "otherParameter")) shouldBeEqualTo false
            hasParameter { it.hasNameStartingWith("sample") } shouldBeEqualTo true
            hasParameter { it.hasNameStartingWith("other") } shouldBeEqualTo false
            hasAllParameters { it.hasNameStartingWith("sample") } shouldBeEqualTo true
        }
    }

    @Test
    fun `function-contains-two-parameters`() {
        // given
        val sut =
            getSnippetFile("function-contains-two-parameters")
                .functions()
                .first()

        // then
        assertSoftly(sut) {
            parameters.size shouldBeEqualTo 2
            numParameters shouldBeEqualTo 2
            countParameters { it.hasNameStartingWith("sample") } shouldBeEqualTo 2
            countParameters { param -> param.hasType { it.name == "Int" } } shouldBeEqualTo 1
            hasParameters() shouldBeEqualTo true
            hasParameterWithName(emptyList()) shouldBeEqualTo true
            hasParameterWithName(emptySet()) shouldBeEqualTo true
            hasParametersWithAllNames(emptyList()) shouldBeEqualTo true
            hasParametersWithAllNames(emptySet()) shouldBeEqualTo true
            hasParameterWithName("sampleParameter1") shouldBeEqualTo true
            hasParameterWithName("otherParameter") shouldBeEqualTo false
            hasParameterWithName("sampleParameter1", "otherName") shouldBeEqualTo true
            hasParameterWithName(listOf("sampleParameter1")) shouldBeEqualTo true
            hasParameterWithName(listOf("otherParameter")) shouldBeEqualTo false
            hasParameterWithName(listOf("sampleParameter1", "otherName")) shouldBeEqualTo true
            hasParameterWithName(setOf("sampleParameter1")) shouldBeEqualTo true
            hasParameterWithName(setOf("otherParameter")) shouldBeEqualTo false
            hasParameterWithName(setOf("sampleParameter1", "otherName")) shouldBeEqualTo true
            hasParametersWithAllNames("sampleParameter1") shouldBeEqualTo true
            hasParametersWithAllNames("sampleParameter1", "sampleParameter2") shouldBeEqualTo true
            hasParametersWithAllNames("sampleParameter1", "otherParameter") shouldBeEqualTo false
            hasParametersWithAllNames(listOf("sampleParameter1")) shouldBeEqualTo true
            hasParametersWithAllNames(listOf("sampleParameter1", "sampleParameter2")) shouldBeEqualTo true
            hasParametersWithAllNames(listOf("sampleParameter1", "otherParameter")) shouldBeEqualTo false
            hasParametersWithAllNames(setOf("sampleParameter1")) shouldBeEqualTo true
            hasParametersWithAllNames(setOf("sampleParameter1", "sampleParameter2")) shouldBeEqualTo true
            hasParametersWithAllNames(setOf("sampleParameter1", "otherParameter")) shouldBeEqualTo false
            hasParameter { it.hasNameStartingWith("sample") } shouldBeEqualTo true
            hasParameter { param -> param.hasType { it.name == "Int" } } shouldBeEqualTo true
            hasAllParameters { it.hasNameStartingWith("sample") } shouldBeEqualTo true
            hasAllParameters { param -> param.hasType { it.name == "Int" } } shouldBeEqualTo false
        }
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("core/declaration/kofunction/snippet/forkoparametersprovider/", fileName)
}
