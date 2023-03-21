package com.lemon.konsist.core.koannotation

import com.lemon.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoAnnotationTest {
    @Test
    fun `annotation-type`() {
        // given
        val sut = getSut("annotation-type")

        // then
        sut.functions().first().annotations.first().type shouldBeEqualTo "SampleAnnotation"
    }

    private fun getSut(fileName: String) = getSnippetKoScope("core/koannotation/snippet/$fileName.kt.txt")
}
