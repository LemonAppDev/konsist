package com.lemonappdev.konsist.core.declaration.koparameterdeclaration

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import com.lemonappdev.konsist.core.ext.toNormalizedPath
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
            .first()

        // then
        assertSoftly(sut) {
            representsType("SampleType") shouldBeEqualTo true
            representsType("OtherType") shouldBeEqualTo false
        }
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/koparameterdeclaration/snippet/forrepresentstype/".toNormalizedPath(), fileName)
}
