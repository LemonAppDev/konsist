package com.lemon.konsist.core.konameddeclaration

import com.lemon.konsist.TestSnippetProvider
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoNamedDeclarationTest {
    @Test
    fun `class`() {
        // given
        val sut = getSut("class")

        // then
        sut.classes().first().name shouldBeEqualTo "SampleClass"
    }

    @Test
    fun `interface`() {
        // given
        val sut = getSut("interface")

        // then
        sut.interfaces().first().name shouldBeEqualTo "SampleInterface"
    }

    @Test
    fun `object`() {
        // given
        val sut = getSut("object")

        // then
        sut.objects().first().name shouldBeEqualTo "SampleObject"
    }

    @Test
    fun `function`() {
        // given
        val sut = getSut("function")

        // then
        sut.functions().first().name shouldBeEqualTo "SampleFunction"
    }

    @Test
    fun `property`() {
        // given
        val sut = getSut("property")

        // then
        sut.properties().first().name shouldBeEqualTo "sampleProperty"
    }

    private fun getSut(fileName: String) = TestSnippetProvider.getSnippetKoScope("core/konameddeclaration/snippet/$fileName.kt.txt")
}
