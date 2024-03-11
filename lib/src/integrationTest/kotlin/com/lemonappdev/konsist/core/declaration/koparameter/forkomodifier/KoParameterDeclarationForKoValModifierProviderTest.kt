package com.lemonappdev.konsist.core.declaration.koparameter.forkomodifier

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoParameterDeclarationForKoValModifierProviderTest {
    @Test
    fun `parameter-without-val-modifier`() {
        // given
        val sut =
            getSnippetFile("parameter-without-val-modifier")
                .classes()
                .first()
                .primaryConstructor
                ?.parameters
                ?.first()

        // then
        sut?.hasValModifier shouldBeEqualTo false
    }

    @Test
    fun `parameter-with-val-modifier`() {
        // given
        val sut =
            getSnippetFile("parameter-with-val-modifier")
                .classes()
                .first()
                .primaryConstructor
                ?.parameters
                ?.first()

        // then
        sut?.hasValModifier shouldBeEqualTo true
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/koparameter/forkomodifier/snippet/forkovalmodifierprovider/", fileName)
}
