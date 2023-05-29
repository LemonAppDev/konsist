package com.lemonappdev.konsist.api.ext.declaration.koparameterdeclaration

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import com.lemonappdev.konsist.api.ext.declaration.representsTypeOf
import com.lemonappdev.konsist.core.ext.toNormalizedPath
import com.lemonappdev.konsist.testdata.SampleType
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
        sut.representsTypeOf<SampleType>() shouldBeEqualTo true
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("api/ext/declaration/koparameterdeclaration/snippet/".toNormalizedPath(), fileName)
}
