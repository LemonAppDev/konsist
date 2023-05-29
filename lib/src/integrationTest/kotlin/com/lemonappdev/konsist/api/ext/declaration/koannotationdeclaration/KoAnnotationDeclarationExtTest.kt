package com.lemonappdev.konsist.api.ext.declaration.koannotationdeclaration

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import com.lemonappdev.konsist.api.ext.declaration.representsTypeOf
import com.lemonappdev.konsist.core.ext.toNormalizedPath
import com.lemonappdev.konsist.testdata.NonExistingAnnotation
import com.lemonappdev.konsist.testdata.SampleAnnotation
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoAnnotationDeclarationExtTest {
    @Test
    fun `annotation-represents-type`() {
        // given
        val sut = getSnippetFile("annotation-represents-type")
            .functions()
            .first()
            .annotations
            .first()

        // then
        assertSoftly(sut) {
            representsTypeOf<SampleAnnotation>() shouldBeEqualTo true
            representsTypeOf<NonExistingAnnotation>() shouldBeEqualTo false
        }
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("api/ext/declaration/koannotationdeclaration/snippet/".toNormalizedPath(), fileName)
}
