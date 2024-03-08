package com.lemonappdev.konsist.core.declaration.koparameter

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoParameterDeclarationForKoTextProviderTest {
    @Test
    fun `parameter-text`() {
        // given
        val sut =
            getSnippetFile("parameter-text")
                .classes()
                .first()
                .primaryConstructor
                ?.parameters
                ?.first()

        // then
        sut
            ?.text
            .shouldBeEqualTo("val sampleParameter: Int")
    }

    private fun getSnippetFile(fileName: String) = getSnippetKoScope("core/declaration/koparameter/snippet/forkotextprovider/", fileName)
}
