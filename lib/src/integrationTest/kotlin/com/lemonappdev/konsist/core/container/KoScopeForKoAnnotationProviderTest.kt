package com.lemonappdev.konsist.core.container

import com.lemonappdev.konsist.TestSnippetProvider
import com.lemonappdev.konsist.testdata.NonExistingAnnotation
import com.lemonappdev.konsist.testdata.SampleAnnotation
import com.lemonappdev.konsist.testdata.SampleAnnotation1
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoScopeForKoAnnotationProviderTest {

    @Test
    fun `scope-contains-no-annotation`() {
        // given
        val sut = getSnippetFile("scope-contains-no-annotation")

        // then
        assertSoftly(sut) {
            annotations.toList() shouldBeEqualTo emptyList()
            hasAnnotations("SampleAnnotation") shouldBeEqualTo false
            hasAnnotations("com.lemonappdev.konsist.testdata.SampleAnnotation") shouldBeEqualTo false
            hasAnnotationsOf(SampleAnnotation::class) shouldBeEqualTo false
        }
    }

    @Test
    fun `scope-contains-annotation`() {
        // given
        val sut = getSnippetFile("scope-contains-annotation")

        // then
        assertSoftly(sut) {
            annotations.map { it.name }.toList() shouldBeEqualTo listOf("SampleAnnotation1")
            hasAnnotations("SampleAnnotation1") shouldBeEqualTo true
            hasAnnotations("com.lemonappdev.konsist.testdata.SampleAnnotation1") shouldBeEqualTo true
            hasAnnotations("NonExistingAnnotation") shouldBeEqualTo false
            hasAnnotations("com.lemonappdev.konsist.testdata.NonExistingAnnotation") shouldBeEqualTo false
            hasAnnotationsOf(SampleAnnotation1::class) shouldBeEqualTo true
            hasAnnotationsOf(NonExistingAnnotation::class) shouldBeEqualTo false
        }
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("core/container/snippet/forkoannotationprovider/", fileName)
}
