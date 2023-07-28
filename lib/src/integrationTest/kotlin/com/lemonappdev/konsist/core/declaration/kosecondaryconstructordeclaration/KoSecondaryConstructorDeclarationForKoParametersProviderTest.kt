package com.lemonappdev.konsist.core.declaration.kosecondaryconstructordeclaration

import com.lemonappdev.konsist.TestSnippetProvider
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoSecondaryConstructorDeclarationForKoParametersProviderTest {
    @Test
    fun `secondary-constructor-contains-no-parameters`() {
        // given
        val sut = getSnippetFile("secondary-constructor-contains-no-parameters")
            .classes()
            .first()
            .secondaryConstructors
            .first()

        // then
        sut.parameters.toList() shouldBeEqualTo emptyList()
    }

    @Test
    fun `secondary-constructor-contains-parameter`() {
        // given
        val sut = getSnippetFile("secondary-constructor-contains-parameter")
            .classes()
            .first()
            .secondaryConstructors
            .first()

        // then
        assertSoftly(sut.parameters) {
            it.toList().size shouldBeEqualTo 2
            it.first().name shouldBeEqualTo "sampleParameter1"
        }
    }

    @Test
    fun `secondary-constructor-has-parameter`() {
        // given
        val sut = getSnippetFile("secondary-constructor-has-parameter")
            .classes()
            .first()
            .secondaryConstructors
            .first()

        // then
        assertSoftly(sut) {
            it.hasParameterNamed("sampleParameter1") shouldBeEqualTo true
            it.hasParameterNamed("otherParameter") shouldBeEqualTo false
        }
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope(
            "core/declaration/kosecondaryconstructordeclaration/snippet/forkoparametersprovider/",
            fileName,
        )
}
