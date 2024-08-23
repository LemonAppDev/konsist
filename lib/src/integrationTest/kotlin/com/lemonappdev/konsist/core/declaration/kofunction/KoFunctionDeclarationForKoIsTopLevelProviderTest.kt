package com.lemonappdev.konsist.core.declaration.kofunction

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import com.lemonappdev.konsist.api.ext.list.functions
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoFunctionDeclarationForKoIsTopLevelProviderTest {
    @Test
    fun `function-is-not-top-level`() {
        // given
        val sut =
            getSnippetFile("function-is-not-top-level")
                .classes()
                .functions()
                .first()

        // then
        sut.isTopLevel shouldBeEqualTo false
    }

    @Test
    fun `function-is-top-level`() {
        // given
        val sut =
            getSnippetFile("function-is-top-level")
                .functions()
                .first()

        // then
        sut.isTopLevel shouldBeEqualTo true
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/kofunction/snippet/forkoistoplevelprovider/", fileName)
}
