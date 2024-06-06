package com.lemonappdev.konsist.core.declaration.koparameter

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoParameterDeclarationForKoContainingFileProviderTest {
    @Test
    fun `parameter-in-constructor-containing-file`() {
        // given
        val sut =
            getSnippetFile("parameter-in-constructor-containing-file")
                .classes()
                .first()
                .primaryConstructor
                ?.parameters
                ?.first()

        // then
        sut
            ?.containingFile
            ?.nameWithExtension
            ?.endsWith("file.kt")
            .shouldBeEqualTo(true)
    }

    @Test
    fun `parameter-in-function-invocation-containing-file`() {
        // given
        val sut =
            getSnippetFile("parameter-in-function-invocation-containing-file")
                .functions()
                .first()
                .parameters
                .first()

        // then
        sut
            .containingFile
            .nameWithExtension
            .endsWith("file.kt")
            .shouldBeEqualTo(true)
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/koparameter/snippet/forkocontainingfileprovider/", fileName)
}
