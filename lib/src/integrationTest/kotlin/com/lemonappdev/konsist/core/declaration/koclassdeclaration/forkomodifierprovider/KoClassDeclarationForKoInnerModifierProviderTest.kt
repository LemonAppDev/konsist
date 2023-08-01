package com.lemonappdev.konsist.core.declaration.koclassdeclaration.forkomodifierprovider

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoClassDeclarationForKoInnerModifierProviderTest {
    @Test
    fun `class-without-inner-modifier`() {
        // given
        val sut = getSnippetFile("class-without-inner-modifier")
            .classes(includeNested = true)
            .first { it.name == "InnerClass" }

        // then
        sut.hasInnerModifier shouldBeEqualTo false
    }

    @Test
    fun `inner-class`() {
        // given
        val sut = getSnippetFile("inner-class")
            .classes(includeNested = true)
            .first { it.name == "InnerClass" }

        // then
        sut.hasInnerModifier shouldBeEqualTo true
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/koclassdeclaration/forkomodifierprovider/snippet/forkoinnermodifierprovider/", fileName)
}
