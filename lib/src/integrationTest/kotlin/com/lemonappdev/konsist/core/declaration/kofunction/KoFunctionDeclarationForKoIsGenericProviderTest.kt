package com.lemonappdev.konsist.core.declaration.kofunction

import com.lemonappdev.konsist.TestSnippetProvider
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoFunctionDeclarationForKoIsGenericProviderTest {
    @Test
    fun `function-is-not-generic`() {
        // given
        val sut =
            getSnippetFile("function-is-not-generic")
                .functions()
                .first()

        // then
        sut.isGeneric shouldBeEqualTo false
    }

    @Test
    fun `function-is-generic`() {
        // given
        val sut =
            getSnippetFile("function-is-generic")
                .functions()
                .first()

        // then
        sut.isGeneric shouldBeEqualTo true
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("core/declaration/kofunction/snippet/forkoisgenericprovider/", fileName)
}
