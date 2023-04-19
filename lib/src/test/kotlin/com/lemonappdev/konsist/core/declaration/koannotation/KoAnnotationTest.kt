package com.lemonappdev.konsist.core.declaration.koannotation

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoAnnotationTest {
    @Test
    fun `annotation-type`() {
        // given
        val sut = getSnippetFile("annotation-type")
            .functions()
            .first()
            .annotations
            .first()

        // then
        sut.type shouldBeEqualTo "SampleAnnotation"
    }

    private fun getSnippetFile(fileName: String) = getSnippetKoScope("core/declaration/koannotation/snippet/", fileName)
}
