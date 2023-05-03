package com.lemonappdev.konsist.api.ext.declaration.kodeclaration

import com.lemonappdev.konsist.TestSnippetProvider
import com.lemonappdev.konsist.api.ext.declaration.hasAnnotationOf
import com.lemonappdev.konsist.testdata.NonExistingAnnotation
import com.lemonappdev.konsist.testdata.SampleAnnotation1
import com.lemonappdev.konsist.testdata.SampleAnnotation2
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoDeclarationExtForSecondaryConstructorTest {
    @Test
    fun `secondary-constructor-has-two-annotations-of`() {
        // given
        val sut = getSnippetFile("secondary-constructor-has-two-annotations-of")
            .classes()
            .first()
            .secondaryConstructors
            .first()

        // then
        assertSoftly(sut) {
            hasAnnotationOf<SampleAnnotation1>() shouldBeEqualTo true
            hasAnnotationOf<SampleAnnotation2>() shouldBeEqualTo true
            hasAnnotationOf<NonExistingAnnotation>() shouldBeEqualTo false
        }
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("api/ext/declaration/kodeclaration/snippet/forsecondaryconstructor/", fileName)
}
