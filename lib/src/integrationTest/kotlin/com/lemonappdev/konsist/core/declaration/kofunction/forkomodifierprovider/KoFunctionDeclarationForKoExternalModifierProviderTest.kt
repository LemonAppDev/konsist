package com.lemonappdev.konsist.core.declaration.kofunction.forkomodifierprovider

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoFunctionDeclarationForKoExternalModifierProviderTest {
    @Test
    fun `function-without-external-modifier`() {
        // given
        val sut = getSnippetFile("function-without-external-modifier")
            .functions()
            .first()

        // then
        sut.hasExternalModifier shouldBeEqualTo false
    }

    @Test
    fun `function-with-external-modifier`() {
        // given
        val sut = getSnippetFile("function-with-external-modifier")
            .functions()
            .first()

        // then
        sut.hasExternalModifier shouldBeEqualTo true
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/kofunctiondeclaration/forkomodifierprovider/snippet/forkoexternalmodifierprovider/", fileName)
}
