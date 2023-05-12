package com.lemonappdev.konsist.core.declaration.konameddeclaration

import com.lemonappdev.konsist.TestSnippetProvider
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoNamedDeclarationForNameTest {
    @Test
    fun `class`() {
        // given
        val sut = getSnippetFile("class")
            .classes()
            .first()

        // then
        sut.name shouldBeEqualTo "SampleClass"
    }

    @Test
    fun `interface`() {
        // given
        val sut = getSnippetFile("interface")
            .interfaces()
            .first()

        // then
        sut.name shouldBeEqualTo "SampleInterface"
    }

    @Test
    fun `object`() {
        // given
        val sut = getSnippetFile("object")
            .objects()
            .first()

        // then
        sut.name shouldBeEqualTo "SampleObject"
    }

    @Test
    fun `function`() {
        // given
        val sut = getSnippetFile("function")
            .functions()
            .first()

        // then
        sut.name shouldBeEqualTo "sampleFunction"
    }

    @Test
    fun `property`() {
        // given
        val sut = getSnippetFile("property")
            .properties()
            .first()

        // then
        sut.name shouldBeEqualTo "sampleProperty"
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("core/declaration/konameddeclaration/snippet/forname/", fileName)
}
