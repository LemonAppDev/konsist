package com.lemonappdev.konsist.core.declaration.koconstructor

import com.lemonappdev.konsist.TestSnippetProvider
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoConstructorDeclarationForKoParametersProviderTest {
    @Test
    fun `constructor-contains-no-parameters`() {
        // given
        val sut = getSnippetFile("constructor-contains-no-parameters")
            .classes()
            .first()
            .constructors
            .first()

        // then
        assertSoftly(sut) {
            parameters shouldBeEqualTo emptyList()
            numParameters shouldBeEqualTo 0
            countParameters { it.hasPublicOrDefaultModifier } shouldBeEqualTo 0
        }
    }

    @Test
    fun `constructor-contains-parameter`() {
        // given
        val sut = getSnippetFile("constructor-contains-parameter")
            .classes()
            .first()
            .constructors
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
    fun `constructor-has-parameter`() {
        // given
        val sut = getSnippetFile("constructor-has-parameter")
            .classes()
            .first()
            .constructors
            .first()

        // then
        assertSoftly(sut) {
            hasParameterNamed("sampleParameter") shouldBeEqualTo true
            hasParameterNamed("otherParameter") shouldBeEqualTo false
        }
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("core/declaration/koconstructor/snippet/forkoparametersprovider/", fileName)
}
