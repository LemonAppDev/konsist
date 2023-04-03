package com.lemon.konsist.core.koparametrizeddeclaration

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
        with(sut) {
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
        with(sut.parameters) {
            size shouldBeEqualTo 1
            first().name shouldBeEqualTo "sampleParameter"
        }
    }

    private fun getSut(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("core/koparametrizeddeclaration/snippet/forfunction/$fileName.kttxt")
}
