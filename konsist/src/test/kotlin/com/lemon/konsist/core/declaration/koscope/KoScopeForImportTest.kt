package com.lemon.konsist.core.declaration.koscope

import com.lemon.konsist.TestSnippetProvider
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoScopeForImportTest {

    @Test
    fun `file-with-import`() {
        // given
        val sut = getSut("file-with-import")

        // then
        sut
            .imports()
            .map { it.name } shouldBeEqualTo listOf("com.sampleimport")
    }

    @Test
    fun `file-without-import`() {
        // given
        val sut = getSut("file-without-import")

        // then
        sut
            .imports()
            .isEmpty()
    }

    private fun getSut(fileName: String) = TestSnippetProvider.getSnippetKoScope("koscope/snippet/forimport/$fileName.kttxt")
}
