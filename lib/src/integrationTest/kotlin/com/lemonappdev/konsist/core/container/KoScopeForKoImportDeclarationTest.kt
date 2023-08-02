package com.lemonappdev.konsist.core.container

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoScopeForKoImportDeclarationTest {
    @Test
    fun `scope-contains-no-import`() {
        // given
        val sut = getSnippetFile("scope-contains-no-import")

        // then
        sut.imports.isEmpty() shouldBeEqualTo true
    }

    @Test
    fun `scope-contains-imports`() {
        // given
        val sut = getSnippetFile("scope-contains-imports")

        // then
        sut
            .imports
            .map { it.name }
            .shouldBeEqualTo(
                listOf(
                    "com.lemonappdev.konsist.testdata.SampleAnnotation",
                    "com.lemonappdev.konsist.testdata.SampleType",
                ),
            )
    }

    private fun getSnippetFile(fileName: String) = getSnippetKoScope("core/container/snippet/forkoimportdeclaration/", fileName)
}
