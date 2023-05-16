package com.lemonappdev.konsist.core.declaration.koparameterdeclaration

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoParameterDeclarationForRepresentsTypeTest {
    @Test
    fun `parameter-represents-type`() {
        // given
        val sut = getSnippetFile("parameter-represents-type")
            .functions()
            .first()
            .parameters
            ?.first()

        // then
        sut?.representsType("SampleType") shouldBeEqualTo true
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/koparameterdeclaration/snippet/forrepresentstype/", fileName)
}
