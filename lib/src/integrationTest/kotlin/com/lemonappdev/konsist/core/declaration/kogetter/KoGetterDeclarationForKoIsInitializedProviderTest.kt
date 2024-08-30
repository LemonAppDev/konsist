package com.lemonappdev.konsist.core.declaration.kogetter

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoGetterDeclarationForKoIsInitializedProviderTest {
    @Test
    fun `getter-is-initialized-in-block-body`() {
        // given
        val sut =
            getSnippetFile("getter-is-initialized-in-block-body")
                .properties()
                .first()
                .getter

        // then
        sut?.isInitialized shouldBeEqualTo true
    }

    @Test
    fun `getter-is-initialized-in-expression-body`() {
        // given
        val sut =
            getSnippetFile("getter-is-initialized-in-expression-body")
                .properties()
                .first()
                .getter

        // then
        sut?.isInitialized shouldBeEqualTo true
    }

    @Test
    fun `getter-is-not-initialized`() {
        // given
        val sut =
            getSnippetFile("getter-is-not-initialized")
                .properties()
                .first()
                .getter

        // then
        sut?.isInitialized shouldBeEqualTo false
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/kogetter/snippet/forkoisinitializedprovider/", fileName)
}
