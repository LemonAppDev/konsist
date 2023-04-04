package com.lemon.konsist.core.koimport

import com.lemon.konsist.TestSnippetProvider
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoImportTest {

    @Test
    fun `import-name`() {
        // given
        val sut = getSut("import-name")
            .imports()

        // then
        sut.map { it.name } shouldBeEqualTo listOf("com.lemon.konsist.testdata.SampleClass")
    }

    private fun getSut(fileName: String) = TestSnippetProvider.getSnippetKoScope("core/koimport/snippet/$fileName.kttxt")
}
