package com.lemonappdev.konsist.core.declaration.koparameter.forkomodifier

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoParameterDeclarationForKoVarArgModifierProviderTest {
    @Test
    fun `parameter-without-vararg-modifier`() {
        // given
        val sut =
            getSnippetFile("parameter-without-vararg-modifier")
                .classes()
                .first()
                .primaryConstructor
                ?.parameters
                ?.first()

        // then
        sut?.hasVarArgModifier shouldBeEqualTo false
    }

    @Test
    fun `parameter-with-vararg-modifier`() {
        // given
        val sut =
            getSnippetFile("parameter-with-vararg-modifier")
                .classes()
                .first()
                .primaryConstructor
                ?.parameters
                ?.first()

        // then
        sut?.hasVarArgModifier shouldBeEqualTo true
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/koparameter/forkomodifier/snippet/forkovarargmodifierprovider/", fileName)
}
