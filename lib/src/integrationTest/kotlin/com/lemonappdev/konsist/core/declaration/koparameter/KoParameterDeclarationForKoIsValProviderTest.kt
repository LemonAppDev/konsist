package com.lemonappdev.konsist.core.declaration.koparameter

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoParameterDeclarationForKoIsValProviderTest {
    @Test
    fun `parameter-without-val-keyword`() {
        // given
        val sut =
            getSnippetFile("parameter-without-val-keyword")
                .classes()
                .first()
                .primaryConstructor
                ?.parameters
                ?.first()

        // then
        sut?.isVal shouldBeEqualTo false
    }

    @Test
    fun `parameter-with-val-keyword`() {
        // given
        val sut =
            getSnippetFile("parameter-with-val-keyword")
                .classes()
                .first()
                .primaryConstructor
                ?.parameters
                ?.first()

        // then
        sut?.isVal shouldBeEqualTo true
    }

    private fun getSnippetFile(fileName: String) = getSnippetKoScope("core/declaration/koparameter/snippet/forkoisvalprovider/", fileName)
}
