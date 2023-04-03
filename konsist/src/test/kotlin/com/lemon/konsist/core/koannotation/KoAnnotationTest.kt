package com.lemon.konsist.core.koannotation

import com.lemon.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoAnnotationTest {
    @Test
    fun `annotation-type`() {
        // given
        val sut = getSut("annotation-type")
            .functions()
            .first()
            .annotations
            .first()

        // then
        sut.type shouldBeEqualTo "SampleAnnotation"
    }

    private fun getSut(fileName: String) = getSnippetKoScope("core/koannotation/snippet/$fileName.kttxt")
}
