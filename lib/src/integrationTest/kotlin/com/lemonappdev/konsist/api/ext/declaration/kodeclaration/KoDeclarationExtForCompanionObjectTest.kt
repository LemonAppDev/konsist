package com.lemonappdev.konsist.api.ext.declaration.kodeclaration

import com.lemonappdev.konsist.TestSnippetProvider
import com.lemonappdev.konsist.api.ext.declaration.hasAnnotationOf
import com.lemonappdev.konsist.api.ext.declaration.representsTypeOf
import com.lemonappdev.konsist.testdata.NonExistingAnnotation
import com.lemonappdev.konsist.testdata.SampleAnnotation1
import com.lemonappdev.konsist.testdata.SampleAnnotation2
import com.lemonappdev.konsist.testdata.SampleTopLevelInterface.SampleCompanionObject
import com.lemonappdev.konsist.testdata.SampleType
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoDeclarationExtForCompanionObjectTest {
    @Test
    fun `companion-object-has-two-annotations-of`() {
        // given
        val sut = getSnippetFile("companion-object-has-two-annotations-of")
            .companionObjects()
            .first()

        // then
        assertSoftly(sut) {
            hasAnnotationOf<SampleAnnotation1>() shouldBeEqualTo true
            hasAnnotationOf<SampleAnnotation2>() shouldBeEqualTo true
            hasAnnotationOf<NonExistingAnnotation>() shouldBeEqualTo false
        }
    }

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
        TestSnippetProvider.getSnippetKoScope("api/ext/declaration/kodeclaration/snippet/forcompanionobject/", fileName)
}
