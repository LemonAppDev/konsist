package com.lemonappdev.konsist.core.declaration.koparameter.forkomodifier

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoParameterDeclarationForKoCrossInlineModifierProviderTest {
    @Test
    fun `parameter-without-crossinline-modifier`() {
        // given
        val sut =
            getSnippetFile("parameter-without-crossinline-modifier")
                .functions()
                .first()
                .parameters
                .first()

        // then
        sut.hasCrossInlineModifier shouldBeEqualTo false
    }

    @Test
    fun `parameter-with-crossinline-modifier`() {
        // given
        val sut =
            getSnippetFile("parameter-with-crossinline-modifier")
                .functions()
                .first()
                .parameters
                .first()

        // then
        sut.hasCrossInlineModifier shouldBeEqualTo true
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope(
            "core/declaration/koparameter/forkomodifier/snippet/forkocrossinlinemodifierprovider/",
            fileName,
        )
}
