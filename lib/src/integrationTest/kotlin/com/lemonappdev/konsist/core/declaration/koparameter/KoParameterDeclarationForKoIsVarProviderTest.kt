package com.lemonappdev.konsist.core.declaration.koparameter

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoParameterDeclarationForKoIsVarProviderTest {
    @Test
    fun `parameter-without-var-keyword`() {
        // given
        val sut =
            getSnippetFile("parameter-without-var-keyword")
                .classes()
                .first()
                .primaryConstructor
                ?.parameters
                ?.first()

        // then
        sut?.isVar shouldBeEqualTo false
    }

    @Test
    fun `parameter-with-var-keyword`() {
        // given
        val sut =
            getSnippetFile("parameter-with-var-keyword")
                .classes()
                .first()
                .primaryConstructor
                ?.parameters
                ?.first()

        // then
        sut?.isVar shouldBeEqualTo true
    }

    private fun getSnippetFile(fileName: String) = getSnippetKoScope("core/declaration/koparameter/snippet/forkoisvarprovider/", fileName)
}
