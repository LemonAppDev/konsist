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
            hasParameterWithName("sampleParameter") shouldBeEqualTo false
            hasParametersWithAllNames("sampleParameter1", "sampleParameter2") shouldBeEqualTo false
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
            hasParameterWithName("sampleParameter") shouldBeEqualTo true
            hasParameterWithName("otherParameter") shouldBeEqualTo false
            hasParameterWithName("sampleParameter", "otherParameter") shouldBeEqualTo true
            hasParametersWithAllNames("sampleParameter") shouldBeEqualTo true
            hasParametersWithAllNames("sampleParameter", "otherParameter") shouldBeEqualTo false
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
            hasParameterWithName("sampleParameter1") shouldBeEqualTo true
            hasParameterWithName("otherParameter") shouldBeEqualTo false
            hasParameterWithName("sampleParameter1", "otherName") shouldBeEqualTo true
            hasParametersWithAllNames("sampleParameter1") shouldBeEqualTo true
            hasParametersWithAllNames("sampleParameter1", "sampleParameter2") shouldBeEqualTo true
            hasParametersWithAllNames("sampleParameter1", "otherParameter") shouldBeEqualTo false
            hasParameter { it.hasNameStartingWith("sample") } shouldBeEqualTo true
            hasParameter { param -> param.hasType { it.name == "Int" } } shouldBeEqualTo true
            hasAllParameters { it.hasNameStartingWith("sample") } shouldBeEqualTo true
            hasAllParameters { param -> param.hasType { it.name == "Int" } } shouldBeEqualTo false
        }
    }

    @Test
    fun `function-has-parameter`() {
        // given
        val sut =
            getSnippetFile("function-has-parameter")
                .functions()
                .first()

        // then
        assertSoftly(sut) {
            hasParameterNamed("sampleProperty") shouldBeEqualTo true
            hasParameterNamed("otherProperty") shouldBeEqualTo false
        }
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("core/declaration/kofunction/snippet/forkoparametersprovider/", fileName)
}
