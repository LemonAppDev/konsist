package com.lemonappdev.konsist.core.declaration.koparametrizeddeclaration

import com.lemonappdev.konsist.TestSnippetProvider
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class ParametrizedDeclarationForParameterTest {
    @Test
    fun `function-contains-parameter`() {
        // given
        val sut = getSnippetFile("function-contains-parameter")
            .functions()
            .first()

        // then
        assertSoftly(sut.parameters) {
            it?.size shouldBeEqualTo 1
            it?.first()?.name shouldBeEqualTo "sampleParameter"
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
            hasParameterNamed() shouldBeEqualTo true
            hasParameterNamed("sampleProperty") shouldBeEqualTo true
            hasParameterNamed("otherProperty") shouldBeEqualTo false
        }
    }

    @Test
    fun `primary-constructor-contains-parameter`() {
        // given
        val sut = getSnippetFile("primary-constructor-contains-parameter")
            .classes()
            .firstNotNullOf { it.primaryConstructor }

        // then
        assertSoftly(sut.parameters) {
            it?.size shouldBeEqualTo 1
            it?.first()?.name shouldBeEqualTo "sampleParameter"
        }
    }

    @Test
    fun `primary-constructor-has-parameter`() {
        // given
        val sut = getSnippetFile("primary-constructor-has-parameter")
            .classes()
            .firstNotNullOf { it.primaryConstructor }

        // then
        assertSoftly(sut) {
            hasParameterNamed() shouldBeEqualTo true
            hasParameterNamed("sampleParameter") shouldBeEqualTo true
            hasParameterNamed("otherParameter") shouldBeEqualTo false
        }
    }

    @Test
    fun `secondary-constructor-contains-parameter`() {
        // given
        val sut = getSnippetFile("secondary-constructor-contains-parameter")
            .classes()
            .flatMap { it.secondaryConstructors }
            .first()

        // then
        assertSoftly(sut.parameters) {
            it?.size shouldBeEqualTo 1
            it?.first()?.name shouldBeEqualTo "sampleParameter"
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
        assertSoftly(sut) {
            hasParameterNamed() shouldBeEqualTo true
            hasParameterNamed("sampleParameter") shouldBeEqualTo true
            hasParameterNamed("otherParameter") shouldBeEqualTo false
        }
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("core/declaration/koparametrizeddeclaration/snippet/forparameter/", fileName)
}
