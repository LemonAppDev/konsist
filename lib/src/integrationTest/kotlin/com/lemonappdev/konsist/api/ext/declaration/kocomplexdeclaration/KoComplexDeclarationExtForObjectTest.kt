package com.lemonappdev.konsist.api.ext.declaration.kocomplexdeclaration

import com.lemonappdev.konsist.TestSnippetProvider
import com.lemonappdev.konsist.api.ext.declaration.representsTypeOf
import com.lemonappdev.konsist.testdata.SampleObject
import com.lemonappdev.konsist.testdata.SampleType
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoComplexDeclarationExtForObjectTest {
    @Test
    fun `object-represents-type`() {
        // given
        val sut = getSnippetFile("object-represents-type")
            .objects()
            .first()

        // then
        assertSoftly(sut) {
            representsTypeOf<SampleObject>() shouldBeEqualTo true
            representsTypeOf<SampleType>() shouldBeEqualTo false
        }
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("api/ext/declaration/kocomplexdeclaration/snippet/forobject/", fileName)
}
