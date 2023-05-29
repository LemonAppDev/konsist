package com.lemonappdev.konsist.api.ext.declaration.kodeclaration

import com.lemonappdev.konsist.TestSnippetProvider
import com.lemonappdev.konsist.api.ext.declaration.hasAnnotationOf
import com.lemonappdev.konsist.core.ext.toNormalizedPath
import com.lemonappdev.konsist.testdata.NonExistingAnnotation
import com.lemonappdev.konsist.testdata.SampleAnnotation1
import com.lemonappdev.konsist.testdata.SampleAnnotation2
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoDeclarationExtForPrimaryConstructorTest {
    @Test
    fun `primary-constructor-has-two-annotations-of-type`() {
        // given
        val sut = getSnippetFile("primary-constructor-has-two-annotations-of-type")
            .classes()
            .first()
            .primaryConstructor

        // then
        sut?.let {
            it.hasAnnotationOf<SampleAnnotation1>() shouldBeEqualTo true
            it.hasAnnotationOf<SampleAnnotation2>() shouldBeEqualTo true
            it.hasAnnotationOf<NonExistingAnnotation>() shouldBeEqualTo false
        }
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope(
            "api/ext/declaration/kodeclaration/snippet/forprimaryconstructor/".toNormalizedPath(),
            fileName,
        )
}
