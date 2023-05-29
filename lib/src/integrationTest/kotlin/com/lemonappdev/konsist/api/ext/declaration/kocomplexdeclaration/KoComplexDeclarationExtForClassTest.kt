package com.lemonappdev.konsist.api.ext.declaration.kocomplexdeclaration

import com.lemonappdev.konsist.TestSnippetProvider
import com.lemonappdev.konsist.api.ext.declaration.representsTypeOf
import com.lemonappdev.konsist.core.ext.toNormalizedPath
import com.lemonappdev.konsist.testdata.SampleClass
import com.lemonappdev.konsist.testdata.SampleType
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoComplexDeclarationExtForClassTest {
    @Test
    fun `class-represents-type`() {
        // given
        val sut = getSnippetFile("class-represents-type")
            .classes()
            .first()

        // then
        assertSoftly(sut) {
            representsTypeOf<SampleClass>() shouldBeEqualTo true
            representsTypeOf<SampleType>() shouldBeEqualTo false
        }
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("api/ext/declaration/kocomplexdeclaration/snippet/forclass/".toNormalizedPath(), fileName)
}
