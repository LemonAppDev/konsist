package com.lemon.konsist.core.koimport

import com.lemon.konsist.TestSnippetProvider
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoImportTest {

    @Test
    fun `import-name`() {
        // given
        val sut = getSut("import-name").imports()

        // then
        sut.map { it.name } shouldBeEqualTo listOf("com.lemon.konsist.testdata.SampleClass")
    }

    @Test
    fun `import-name-with-alias`() {
        // given
        val sut = getSut("import-name-with-alias").imports()

        // then
        with(sut) {
            this[0].alias shouldBeEqualTo "com.lemon.konsist.testdata.SampleClass"
            this[1].alias shouldBeEqualTo "AliasType"
        }
    }

    private fun getSut(fileName: String) = TestSnippetProvider.getSnippetKoScope("core/koimport/snippet/$fileName.kttxt")
}
