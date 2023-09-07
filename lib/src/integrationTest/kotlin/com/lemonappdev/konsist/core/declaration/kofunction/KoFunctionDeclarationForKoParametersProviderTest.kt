package com.lemonappdev.konsist.core.declaration.kofunction

import com.lemonappdev.konsist.TestSnippetProvider
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoFunctionDeclarationForKoParametersProviderTest {
    @Test
    fun `function-contains-no-parameters`() {
        // given
        val sut = getSnippetFile("function-contains-no-parameters")
            .functions()
            .first()

        // then
        assertSoftly(sut) {
            parameters shouldBeEqualTo emptyList()
            numParameters shouldBeEqualTo 0
            countParameters { it.hasPublicOrDefaultModifier } shouldBeEqualTo 0
        }
    }

    @Test
    fun `function-contains-parameter`() {
        // given
        val sut = getSnippetFile("function-contains-parameter")
            .functions()
            .first()

        // then
        assertSoftly(sut) {
            parameters.size shouldBeEqualTo 1
            numParameters shouldBeEqualTo 1
            countParameters { it.hasPublicOrDefaultModifier } shouldBeEqualTo 1
            parameters.first().name shouldBeEqualTo "sampleParameter"
        }
    }

    @Test
    fun `function-has-parameter`() {
        // given
        val sut = getSnippetFile("function-has-parameter")
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
