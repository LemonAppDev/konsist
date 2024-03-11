package com.lemonappdev.konsist.core.declaration.koparameter.forkomodifier

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoParameterDeclarationForKoNoInlineModifierProviderTest {
    @Test
    fun `parameter-without-noinline-modifier`() {
        // given
        val sut =
            getSnippetFile("parameter-without-noinline-modifier")
                .functions()
                .first()
                .parameters
                .first()

        // then
        sut.hasNoInlineModifier shouldBeEqualTo false
    }

    @Test
    fun `parameter-with-noinline-modifier`() {
        // given
        val sut =
            getSnippetFile("parameter-with-noinline-modifier")
                .functions()
                .first()
                .parameters
                .first()

        // then
        sut.hasNoInlineModifier shouldBeEqualTo true
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/koparameter/forkomodifier/snippet/forkonoinlinemodifierprovider/", fileName)
}
