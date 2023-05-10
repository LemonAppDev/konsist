package com.lemonappdev.konsist.core.scope.koscope

import com.lemonappdev.konsist.TestSnippetProvider
import com.lemonappdev.konsist.api.declaration.KoImportDeclaration
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoScopeForDeclarationTest {

    @Test
    fun `file-contains-all-type-of-declarations`() {
        // given
        val sut = getSnippetFile("file-contains-all-type-of-declarations")

        // then
        sut
            .declarations()
            .map { it.name }
            .toList()
            .shouldBeEqualTo(
                listOf(
                    "sampleProperty",
                    "sampleFunction",
                    "SampleClass",
                    "SampleInterface",
                    "SampleObject",
                    "SampleTypeAlias",
                ),
            )
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("core/scope/koscope/snippet/fordeclaration/", fileName)
}
