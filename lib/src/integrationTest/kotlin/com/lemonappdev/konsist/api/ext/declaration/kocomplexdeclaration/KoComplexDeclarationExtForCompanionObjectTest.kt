package com.lemonappdev.konsist.api.ext.declaration.kocomplexdeclaration

import com.lemonappdev.konsist.TestSnippetProvider
import com.lemonappdev.konsist.api.ext.declaration.representsTypeOf
import com.lemonappdev.konsist.testdata.SampleTopLevelInterface.SampleCompanionObject
import com.lemonappdev.konsist.testdata.SampleType
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoComplexDeclarationExtForCompanionObjectTest {
    @Test
    fun `companion-object-represents-type`() {
        // given
        val sut = getSnippetFile("companion-object-represents-type")
            .interfaces()
            .first()
            .companionObjects()
            .first()

        // then
        assertSoftly(sut) {
            representsTypeOf<SampleCompanionObject>() shouldBeEqualTo true
            representsTypeOf<SampleType>() shouldBeEqualTo false
        }
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("api/ext/declaration/kocomplexdeclaration/snippet/forcompanionobject/", fileName)
}
