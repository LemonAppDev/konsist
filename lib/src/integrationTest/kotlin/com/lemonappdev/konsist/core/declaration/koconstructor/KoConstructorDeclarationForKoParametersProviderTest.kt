package com.lemonappdev.konsist.core.declaration.koconstructor

import com.lemonappdev.konsist.TestSnippetProvider
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoConstructorDeclarationForKoParametersProviderTest {
    @Test
    fun `constructor-contains-no-parameters`() {
        // given
        val sut =
            getSnippetFile("constructor-contains-no-parameters")
                .classes()
                .first()
                .constructors
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
    fun `constructor-contains-one-parameter`() {
        // given
        val sut =
            getSnippetFile("constructor-contains-one-parameter")
                .classes()
                .first()
                .constructors
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
    fun `constructor-contains-two-parameters`() {
        // given
        val sut =
            getSnippetFile("constructor-contains-two-parameters")
                .classes()
                .first()
                .constructors
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

    @Test
    fun `constructor-contains-no-parameters-ignore-case`() {
        // given
        val sut =
            getSnippetFile("constructor-contains-no-parameters-ignore-case")
                .classes()
                .first()
                .constructors
                .first()

        // then
        assertSoftly(sut) {
            hasParameterWithName("sampleparameter") shouldBeEqualTo false
            hasParameterWithName("sampleparameter", ignoreCase = true) shouldBeEqualTo false
            hasParameterWithName(listOf("sampleparameter")) shouldBeEqualTo false
            hasParameterWithName(listOf("sampleparameter"), ignoreCase = true) shouldBeEqualTo false
            hasParameterWithName(setOf("sampleparameter")) shouldBeEqualTo false
            hasParameterWithName(setOf("sampleparameter"), ignoreCase = true) shouldBeEqualTo false
            hasParametersWithAllNames("sampleparameter1", "sampleparameter2") shouldBeEqualTo false
            hasParametersWithAllNames("sampleparameter1", "sampleparameter2", ignoreCase = true) shouldBeEqualTo false
            hasParametersWithAllNames(listOf("sampleparameter1", "sampleparameter2")) shouldBeEqualTo false
            hasParametersWithAllNames(listOf("sampleparameter1", "sampleparameter2"), ignoreCase = true) shouldBeEqualTo false
            hasParametersWithAllNames(setOf("sampleparameter1", "sampleparameter2")) shouldBeEqualTo false
            hasParametersWithAllNames(setOf("sampleparameter1", "sampleparameter2"), ignoreCase = true) shouldBeEqualTo false
        }
    }

    @Test
    fun `constructor-contains-parameters-ignore-case`() {
        // given
        val sut =
            getSnippetFile("constructor-contains-parameters-ignore-case")
                .classes()
                .first()
                .constructors
                .first()

        // then
        assertSoftly(sut) {
            hasParameterWithName("sampleparameter1") shouldBeEqualTo false
            hasParameterWithName("sampleparameter1", ignoreCase = true) shouldBeEqualTo true
            hasParameterWithName("otherparameter") shouldBeEqualTo false
            hasParameterWithName("otherparameter", ignoreCase = true) shouldBeEqualTo false
            hasParameterWithName("sampleparameter1", "otherName") shouldBeEqualTo false
            hasParameterWithName("sampleparameter1", "otherName", ignoreCase = true) shouldBeEqualTo true
            hasParameterWithName(listOf("sampleparameter1")) shouldBeEqualTo false
            hasParameterWithName(listOf("sampleparameter1"), ignoreCase = true) shouldBeEqualTo true
            hasParameterWithName(listOf("otherparameter")) shouldBeEqualTo false
            hasParameterWithName(listOf("otherparameter"), ignoreCase = true) shouldBeEqualTo false
            hasParameterWithName(listOf("sampleparameter1", "otherName")) shouldBeEqualTo false
            hasParameterWithName(listOf("sampleparameter1", "otherName"), ignoreCase = true) shouldBeEqualTo true
            hasParameterWithName(setOf("sampleparameter1")) shouldBeEqualTo false
            hasParameterWithName(setOf("sampleparameter1"), ignoreCase = true) shouldBeEqualTo true
            hasParameterWithName(setOf("otherparameter")) shouldBeEqualTo false
            hasParameterWithName(setOf("otherparameter"), ignoreCase = true) shouldBeEqualTo false
            hasParameterWithName(setOf("sampleparameter1", "otherName")) shouldBeEqualTo false
            hasParameterWithName(setOf("sampleparameter1", "otherName"), ignoreCase = true) shouldBeEqualTo true
            hasParametersWithAllNames("sampleparameter1") shouldBeEqualTo false
            hasParametersWithAllNames("sampleparameter1", ignoreCase = true) shouldBeEqualTo true
            hasParametersWithAllNames("sampleparameter1", "sampleparameter2") shouldBeEqualTo false
            hasParametersWithAllNames("sampleparameter1", "sampleparameter2", ignoreCase = true) shouldBeEqualTo true
            hasParametersWithAllNames("sampleparameter1", "otherparameter") shouldBeEqualTo false
            hasParametersWithAllNames("sampleparameter1", "otherparameter", ignoreCase = true) shouldBeEqualTo false
            hasParametersWithAllNames(listOf("sampleparameter1")) shouldBeEqualTo false
            hasParametersWithAllNames(listOf("sampleparameter1"), ignoreCase = true) shouldBeEqualTo true
            hasParametersWithAllNames(listOf("sampleparameter1", "sampleparameter2")) shouldBeEqualTo false
            hasParametersWithAllNames(listOf("sampleparameter1", "sampleparameter2"), ignoreCase = true) shouldBeEqualTo true
            hasParametersWithAllNames(listOf("sampleparameter1", "otherparameter")) shouldBeEqualTo false
            hasParametersWithAllNames(listOf("sampleparameter1", "otherparameter"), ignoreCase = true) shouldBeEqualTo false
            hasParametersWithAllNames(setOf("sampleparameter1")) shouldBeEqualTo false
            hasParametersWithAllNames(setOf("sampleparameter1"), ignoreCase = true) shouldBeEqualTo true
            hasParametersWithAllNames(setOf("sampleparameter1", "sampleparameter2")) shouldBeEqualTo false
            hasParametersWithAllNames(setOf("sampleparameter1", "sampleparameter2"), ignoreCase = true) shouldBeEqualTo true
            hasParametersWithAllNames(setOf("sampleparameter1", "otherparameter")) shouldBeEqualTo false
            hasParametersWithAllNames(setOf("sampleparameter1", "otherparameter"), ignoreCase = true) shouldBeEqualTo false
        }
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("core/declaration/koconstructor/snippet/forkoparametersprovider/", fileName)
}
