package com.lemonappdev.konsist.core.declaration.koscopedeclaration

import com.lemonappdev.konsist.TestSnippetProvider
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoScopeDeclarationForImportTest {

    @Test
    fun `file-contains-import`() {
        // given
        val sut = getSnippetFile("file-contains-import")

        // then
        sut
            .imports()
            .map { it.name }
            .toList()
            .shouldBeEqualTo(listOf("com.lemonappdev.konsist.testdata.SampleType"))
    }

    @Test
    fun `file-contains-no-import`() {
        // given
        val sut = getSnippetFile("file-contains-no-import")

        // then
        sut
            .imports()
            .toList()
            .shouldBeEqualTo(emptyList())
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("core/declaration/koscopedeclaration/snippet/forimport/", fileName)
}
