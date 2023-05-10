package com.lemonappdev.konsist.core.scope.koscope

import com.lemonappdev.konsist.TestSnippetProvider
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoScopeForAnnotationTest {

    @Test
    fun `file-contains-annotation`() {
        // given
        val sut = getSnippetFile("file-contains-annotation")

        // then
        sut
            .annotations()
            .map { it.name }
            .toList()
            .shouldBeEqualTo(listOf("SampleAnnotation1"))
    }

    @Test
    fun `file-contains-no-annotation`() {
        // given
        val sut = getSnippetFile("file-contains-no-annotation")

        // then
        sut
            .annotations()
            .toList()
            .shouldBeEqualTo(emptyList())
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("core/scope/koscope/snippet/forannotation/", fileName)
}
