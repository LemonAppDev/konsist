package com.lemonappdev.konsist.core.declaration.kofunction

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoFunctionDeclarationForKoTopLevelProviderTest {
    @Test
    fun `function-is-not-top-level`() {
        // given
        val sut = getSnippetFile("function-is-not-top-level")
            .classes()
            .flatMap { it.functions() }
            .first()

        // then
        sut.isTopLevel shouldBeEqualTo false
    }

    @Test
    fun `function-is-top-level`() {
        // given
        val sut = getSnippetFile("function-is-top-level")
            .functions()
            .first()

        // then
        sut.isTopLevel shouldBeEqualTo true
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/kofunctiondeclaration/snippet/forkotoplevelprovider/", fileName)
}
