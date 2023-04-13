package com.lemon.konsist.core.declaration.koscope

import com.lemon.konsist.TestSnippetProvider
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoScopeForImportTest {

    @Test
    fun `file-contains-import`() {
        // given
        val sut = getSut("file-contains-import")

        // then
        sut
            .imports()
            .map { it.name }
            .toList()
            .shouldBeEqualTo(listOf("com.lemon.konsist.testdata.SampleType"))
    }

    @Test
    fun `file-contains-no-import`() {
        // given
        val sut = getSut("file-contains-no-import")

        // then
        sut
            .imports()
            .toList()
            .shouldBeEqualTo(emptyList())
    }

    private fun getSut(fileName: String) = TestSnippetProvider.getSnippetKoScope("core/declaration/koscope/snippet/forimport/", fileName)
}
