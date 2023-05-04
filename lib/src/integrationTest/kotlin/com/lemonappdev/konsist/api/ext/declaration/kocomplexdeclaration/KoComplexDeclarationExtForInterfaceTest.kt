package com.lemonappdev.konsist.api.ext.declaration.kocomplexdeclaration

import com.lemonappdev.konsist.TestSnippetProvider
import com.lemonappdev.konsist.api.ext.declaration.representsTypeOf
import com.lemonappdev.konsist.testdata.SampleInterface
import com.lemonappdev.konsist.testdata.SampleType
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoComplexDeclarationExtForInterfaceTest {
    @Test
    fun `interface-represents-type`() {
        // given
        val sut = getSnippetFile("interface-represents-type")
            .interfaces()
            .first()

        // then
        assertSoftly(sut) {
            representsTypeOf<SampleInterface>() shouldBeEqualTo true
            representsTypeOf<SampleType>() shouldBeEqualTo false
        }
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("api/ext/declaration/kocomplexdeclaration/snippet/forinterface/", fileName)
}
