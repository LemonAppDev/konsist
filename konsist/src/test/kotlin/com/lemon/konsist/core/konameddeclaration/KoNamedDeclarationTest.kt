package com.lemon.konsist.core.konameddeclaration

import com.lemon.konsist.TestSnippetProvider
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoNamedDeclarationTest {
    @Test
    fun `class`() {
        // given
        val sut = getSut("class")
            .classes()
            .first()

        // then
        sut.name shouldBeEqualTo "SampleClass"
    }

    @Test
    fun `interface`() {
        // given
        val sut = getSut("interface")
            .interfaces()
            .first()

        // then
        sut.name shouldBeEqualTo "SampleInterface"
    }

    @Test
    fun `object`() {
        // given
        val sut = getSut("object")
            .objects()
            .first()

        // then
        sut.name shouldBeEqualTo "SampleObject"
    }

    @Test
    fun `function`() {
        // given
        val sut = getSut("function")
            .functions()
            .first()

        // then
        sut.name shouldBeEqualTo "sampleFunction"
    }

    @Test
    fun `property`() {
        // given
        val sut = getSut("property")
            .properties()
            .first()

        // then
        sut.name shouldBeEqualTo "sampleProperty"
    }

    private fun getSut(fileName: String) = TestSnippetProvider.getSnippetKoScope("core/konameddeclaration/snippet/$fileName.kttxt")
}
