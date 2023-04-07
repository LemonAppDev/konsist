package com.lemon.konsist.core.declaration.koparametrizeddeclaration

import com.lemon.konsist.TestSnippetProvider
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class ParametrizedDeclarationForSecondaryConstructorTest {
    @Test
    fun `secondary-constructor-contains-no-parameters`() {
        // given
        val sut = getSut("secondary-constructor-contains-no-parameters")
            .classes()
            .flatMap { it.secondaryConstructors }
            .first()

        // then
        with(sut) {
            parameters shouldBeEqualTo emptyList()
        }
    }

    @Test
    fun `secondary-constructor-contains-parameter`() {
        // given
        val sut = getSut("secondary-constructor-contains-parameter")
            .classes()
            .flatMap { it.secondaryConstructors }
            .first()

        // then
        with(sut.parameters) {
            size shouldBeEqualTo 1
            first().name shouldBeEqualTo "sampleParameter"
        }
    }

    @Test
    fun `secondary-constructor-has-parameter`() {
        // given
        val sut = getSut("secondary-constructor-has-parameter")
            .classes()
            .flatMap { it.secondaryConstructors }
            .first()

        // then
        with(sut) {
            hasParameterNamed("sampleParameter") shouldBeEqualTo true
            hasParameterNamed("otherParameter") shouldBeEqualTo false
        }
    }

    private fun getSut(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("koparametrizeddeclaration/snippet/forsecondaryconstructor/$fileName.kttxt")
}
