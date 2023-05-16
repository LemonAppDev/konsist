package com.lemonappdev.konsist.core.declaration.koparameterdeclaration

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.assertSoftly
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
        assertSoftly(sut){
            it?.representsType("SampleType") shouldBeEqualTo true
            it?.representsType("OtherType") shouldBeEqualTo false
        }
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/koparameterdeclaration/snippet/forrepresentstype/", fileName)
}
