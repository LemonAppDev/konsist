package com.lemonappdev.konsist.core.declaration.koprimaryconstructordeclaration

import com.lemonappdev.konsist.TestSnippetProvider
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoPrimaryConstructorDeclarationForKoParametersProviderTest {
    @Test
    fun `primary-constructor-contains-no-parameters`() {
        // given
        val sut = getSnippetFile("primary-constructor-contains-no-parameters")
            .classes()
            .first()
            .primaryConstructor

        // then
        assertSoftly(sut) {
            it?.parameters?.toList() shouldBeEqualTo emptyList()
            it?.numParameters shouldBeEqualTo 0
        }
    }

    @Test
    fun `primary-constructor-contains-parameter`() {
        // given
        val sut = getSnippetFile("primary-constructor-contains-parameter")
            .classes()
            .first()
            .primaryConstructor

        // then
        assertSoftly(sut) {
            it?.parameters?.toList()?.size shouldBeEqualTo 1
            it?.numParameters shouldBeEqualTo 1
            it?.parameters?.first()?.name shouldBeEqualTo "sampleParameter"
        }
    }

    @Test
    fun `primary-constructor-has-parameter`() {
        // given
        val sut = getSnippetFile("primary-constructor-has-parameter")
            .classes()
            .first()
            .primaryConstructor

        // then
        assertSoftly(sut) {
            it?.hasParameterNamed("sampleParameter") shouldBeEqualTo true
            it?.hasParameterNamed("otherParameter") shouldBeEqualTo false
        }
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("core/declaration/koprimaryconstructordeclaration/snippet/forkoparametersprovider/", fileName)
}
