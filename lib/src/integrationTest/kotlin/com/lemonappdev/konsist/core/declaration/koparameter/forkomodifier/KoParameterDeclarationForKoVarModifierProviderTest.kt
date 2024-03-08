package com.lemonappdev.konsist.core.declaration.koparameter.forkomodifier

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoParameterDeclarationForKoVarModifierProviderTest {
    @Test
    fun `parameter-without-var-modifier`() {
        // given
        val sut =
            getSnippetFile("parameter-without-var-modifier")
                .classes()
                .first()
                .primaryConstructor
                ?.parameters
                ?.first()

        // then
        sut?.hasVarModifier shouldBeEqualTo false
    }

    @Test
    fun `parameter-with-var-modifier`() {
        // given
        val sut =
            getSnippetFile("parameter-with-var-modifier")
                .classes()
                .first()
                .primaryConstructor
                ?.parameters
                ?.first()

        // then
        sut?.hasVarModifier shouldBeEqualTo true
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/koparameter/forkomodifier/snippet/forkovarmodifierprovider/", fileName)
}
