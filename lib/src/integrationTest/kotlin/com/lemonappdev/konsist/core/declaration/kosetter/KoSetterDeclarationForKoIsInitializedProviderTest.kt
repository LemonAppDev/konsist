package com.lemonappdev.konsist.core.declaration.kosetter

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoSetterDeclarationForKoIsInitializedProviderTest {
    @Test
    fun `setter-is-initialized-in-block-body`() {
        // given
        val sut =
            getSnippetFile("setter-is-initialized-in-block-body")
                .properties()
                .first()
                .setter

        // then
        sut?.isInitialized shouldBeEqualTo true
    }

    @Test
    fun `setter-is-initialized-in-expression-body`() {
        // given
        val sut =
            getSnippetFile("setter-is-initialized-in-expression-body")
                .properties()
                .first()
                .setter

        // then
        sut?.isInitialized shouldBeEqualTo true
    }

    @Test
    fun `setter-is-not-initialized`() {
        // given
        val sut =
            getSnippetFile("setter-is-not-initialized")
                .properties()
                .first()
                .setter

        // then
        sut?.isInitialized shouldBeEqualTo false
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/kosetter/snippet/forkoisinitializedprovider/", fileName)
}
