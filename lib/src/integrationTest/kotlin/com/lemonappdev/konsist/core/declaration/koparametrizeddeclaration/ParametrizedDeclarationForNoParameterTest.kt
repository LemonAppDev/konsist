package com.lemonappdev.konsist.core.declaration.koparametrizeddeclaration

import com.lemonappdev.konsist.TestSnippetProvider
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class ParametrizedDeclarationForNoParameterTest {
    @Test
    fun `function-contains-no-parameters`() {
        // given
        val sut = getSnippetFile("function-contains-no-parameters")
            .functions()
            .first()

        // then
        assertSoftly(sut) {
            parameters shouldBeEqualTo null
            hasParameterNamed() shouldBeEqualTo false
        }
    }

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

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("core/declaration/koparametrizeddeclaration/snippet/fornoparameter/", fileName)
}
