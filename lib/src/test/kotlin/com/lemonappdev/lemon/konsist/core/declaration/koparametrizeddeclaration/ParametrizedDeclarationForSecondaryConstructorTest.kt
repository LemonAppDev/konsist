package com.lemonappdev.konsist.core.declaration.koparametrizeddeclaration

import com.lemonappdev.konsist.TestSnippetProvider
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class ParametrizedDeclarationForSecondaryConstructorTest {
    @Test
    fun `secondary-constructor-contains-no-parameters`() {
        // given
        val sut = getSnippetFile("secondary-constructor-contains-no-parameters")
            .classes()
            .flatMap { it.secondaryConstructors }
            .first()

        // then
        sut.parameters shouldBeEqualTo emptyList()
    }

    @Test
    fun `secondary-constructor-contains-parameter`() {
        // given
        val sut = getSnippetFile("secondary-constructor-contains-parameter")
            .classes()
            .flatMap { it.secondaryConstructors }
            .first()

        // then
        sut
            .parameters
            .run {
                size shouldBeEqualTo 1
                first().name shouldBeEqualTo "sampleParameter"
            }
    }

    @Test
    fun `secondary-constructor-has-parameter`() {
        // given
        val sut = getSnippetFile("secondary-constructor-has-parameter")
            .classes()
            .flatMap { it.secondaryConstructors }
            .first()

        // then
        sut.run {
            hasParameterNamed("sampleParameter") shouldBeEqualTo true
            hasParameterNamed("otherParameter") shouldBeEqualTo false
        }
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("core/declaration/koparametrizeddeclaration/snippet/forsecondaryconstructor/", fileName)
}
