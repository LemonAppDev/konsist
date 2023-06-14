package com.lemonappdev.konsist.api.ext.declaration.kodeclaration

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import com.lemonappdev.konsist.api.ext.declaration.hasAnnotationOf
import com.lemonappdev.konsist.testdata.NonExistingAnnotation
import com.lemonappdev.konsist.testdata.SampleAnnotation1
import com.lemonappdev.konsist.testdata.SampleAnnotation2
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoDeclarationExtForFunctionTest {
    @Test
    fun `function-has-two-annotations-of-type`() {
        // given
        val sut = getSnippetFile("function-has-two-annotations-of-type")
            .functions()
            .first()

        // then
        assertSoftly(sut) {
            hasAnnotationOf<SampleAnnotation1>() shouldBeEqualTo true
            hasAnnotationOf<SampleAnnotation2>() shouldBeEqualTo true
            hasAnnotationOf<NonExistingAnnotation>() shouldBeEqualTo false
        }
    }

    @Test
    fun `function-has-suppress-annotation-without-import`() {
        // given
        val sut = getSnippetFile("function-has-suppress-annotation-without-import")
            .functions()
            .first()

        // then
        assertSoftly(sut) {
            hasAnnotationOf<Suppress>() shouldBeEqualTo true
            hasAnnotationOf<NonExistingAnnotation>() shouldBeEqualTo false
        }
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("api/ext/declaration/kodeclaration/snippet/forfunction/", fileName)
}
