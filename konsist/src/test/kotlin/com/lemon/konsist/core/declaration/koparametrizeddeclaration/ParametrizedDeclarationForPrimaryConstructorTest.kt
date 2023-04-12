package com.lemon.konsist.core.declaration.koparametrizeddeclaration

import com.lemon.konsist.TestSnippetProvider
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class ParametrizedDeclarationForPrimaryConstructorTest {
    @Test
    fun `primary-constructor-contains-no-parameters`() {
        // given
        val sut = getSut("primary-constructor-contains-no-parameters")
            .classes()
            .firstNotNullOf { it.primaryConstructor }

        // then
        sut.run {
            parameters shouldBeEqualTo emptyList()
        }
    }

    @Test
    fun `primary-constructor-contains-parameter`() {
        // given
        val sut = getSut("primary-constructor-contains-parameter")
            .classes()
            .firstNotNullOf { it.primaryConstructor }

        // then
        sut.parameters.run {
            size shouldBeEqualTo 1
            first().name shouldBeEqualTo "sampleParameter"
        }
    }

    @Test
    fun `primary-constructor-has-parameter`() {
        // given
        val sut = getSut("primary-constructor-has-parameter")
            .classes()
            .firstNotNullOf { it.primaryConstructor }

        // then
        sut.run {
            hasParameterNamed("sampleParameter") shouldBeEqualTo true
            hasParameterNamed("otherParameter") shouldBeEqualTo false
        }
    }

    private fun getSut(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("core/declaration/koparametrizeddeclaration/snippet/forprimaryconstructor/", fileName)
}
