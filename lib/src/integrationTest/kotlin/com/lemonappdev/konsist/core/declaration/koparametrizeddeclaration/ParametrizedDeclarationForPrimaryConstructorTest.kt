package com.lemonappdev.konsist.core.declaration.koparametrizeddeclaration

import com.lemonappdev.konsist.TestSnippetProvider
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class ParametrizedDeclarationForPrimaryConstructorTest {
    @Test
    fun `primary-constructor-contains-no-parameters`() {
        // given
        val sut = getSnippetFile("primary-constructor-contains-no-parameters")
            .classes()
            .first()
            .primaryConstructor

        // then
        assertSoftly(sut) {
            it?.parameters shouldBeEqualTo null
            it?.hasParameterNamed() shouldBeEqualTo false
        }
    }

    @Test
    fun `primary-constructor-contains-parameter`() {
        // given
        val sut = getSnippetFile("primary-constructor-contains-parameter")
            .classes()
            .firstNotNullOf { it.primaryConstructor }

        // then
        assertSoftly(sut.parameters!!) {
            size shouldBeEqualTo 1
            first().name shouldBeEqualTo "sampleParameter"
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

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("core/declaration/koparametrizeddeclaration/snippet/forprimaryconstructor/", fileName)
}
