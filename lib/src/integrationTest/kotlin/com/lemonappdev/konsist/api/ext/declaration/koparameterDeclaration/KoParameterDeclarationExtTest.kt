package com.lemonappdev.konsist.api.ext.declaration.koparameterDeclaration

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import com.lemonappdev.konsist.api.ext.declaration.representsTypeOf
import com.lemonappdev.konsist.testdata.SampleType
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoParameterDeclarationExtTest {
    @Test
    fun `parameter-represents-type-of-type`() {
        // given
        val sut = getSnippetFile("parameter-represents-type-of-type")
            .functions()
            .first()
            .parameters
            .first()

        // then
        assertSoftly(sut) {
            it.representsTypeOf<SampleType>() shouldBeEqualTo true
        }
    }

    private fun getSnippetFile(fileName: String) = getSnippetKoScope("api/ext/declaration/koparameterdeclaration/snippet/", fileName)
}
