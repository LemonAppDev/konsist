package com.lemonappdev.konsist.core.container.koscope

import com.lemonappdev.konsist.TestSnippetProvider
import com.lemonappdev.konsist.core.ext.toNormalizedPath
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
        TestSnippetProvider.getSnippetKoScope("core/container/koscope/snippet/forannotation/".toNormalizedPath(), fileName)
}
