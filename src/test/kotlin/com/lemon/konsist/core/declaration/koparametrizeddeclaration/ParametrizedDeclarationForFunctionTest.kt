package com.lemon.konsist.core.declaration.koparametrizeddeclaration

import com.lemon.konsist.TestSnippetProvider
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class ParametrizedDeclarationForFunctionTest {
    @Test
    fun `function-contains-no-parameters`() {
        // given
        val sut = getSut("function-contains-no-parameters")
            .functions()
            .first()

        // then
        sut.run {
            parameters shouldBeEqualTo emptyList()
        }
    }

    @Test
    fun `function-contains-parameter`() {
        // given
        val sut = getSut("function-contains-parameter")
            .functions()
            .first()

        // then
        sut.parameters.run {
            size shouldBeEqualTo 1
            first().name shouldBeEqualTo "sampleParameter"
        }
    }

    @Test
    fun `function-has-parameter`() {
        // given
        val sut = getSut("function-has-parameter")
            .functions()
            .first()

        // then
        sut.run {
            hasParameterNamed("sampleProperty") shouldBeEqualTo true
            hasParameterNamed("otherProperty") shouldBeEqualTo false
        }
    }

    private fun getSut(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("koparametrizeddeclaration/snippet/forfunction/", fileName)
}
