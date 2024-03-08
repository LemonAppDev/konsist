package com.lemonappdev.konsist.core.declaration.kofunction.forkomodifier

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoFunctionDeclarationForKoAbstractModifierProviderTest {
    @Test
    fun `function-without-abstract-modifier`() {
        // given
        val sut =
            getSnippetFile("function-without-abstract-modifier")
                .functions()
                .first()

        // then
        sut.hasAbstractModifier shouldBeEqualTo false
    }

    @Test
    fun `function-with-abstract-modifier`() {
        // given
        val sut =
            getSnippetFile("function-with-abstract-modifier")
                .functions(includeNested = true)
                .first()

        // then
        sut.hasAbstractModifier shouldBeEqualTo true
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/kofunction/forkomodifier/snippet/forkoabstractmodifierprovider/", fileName)
}
