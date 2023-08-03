package com.lemonappdev.konsist.core.declaration.koparameter

import com.lemonappdev.konsist.TestSnippetProvider
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoParameterDeclarationTest {
    @Test
    fun `parameter-to-string`() {
        // given
        val sut = getSnippetFile("parameter-to-string")
            .classes()
            .first()
            .primaryConstructor
            ?.parameters
            ?.first()

        // then
        sut.toString() shouldBeEqualTo sut?.locationWithText
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("core/declaration/koparameterdeclaration/snippet/forgeneral/", fileName)
}
