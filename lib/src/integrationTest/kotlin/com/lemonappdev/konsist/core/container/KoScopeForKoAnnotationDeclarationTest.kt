package com.lemonappdev.konsist.core.container

import com.lemonappdev.konsist.TestSnippetProvider
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoScopeForKoAnnotationDeclarationTest {
    @Test
    fun `scope-contains-no-annotation`() {
        // given
        val sut = getSnippetFile("scope-contains-no-annotation")

        // then
        sut.annotations shouldBeEqualTo emptyList()
    }

    @Test
    fun `scope-contains-annotation`() {
        // given
        val sut = getSnippetFile("scope-contains-annotation")

        // then
        sut.annotations.map { it.name } shouldBeEqualTo listOf("SampleAnnotation1")
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("core/container/snippet/forkoannotationdeclaration/", fileName)
}
