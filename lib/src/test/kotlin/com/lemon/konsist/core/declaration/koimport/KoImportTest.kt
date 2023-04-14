package com.lemon.konsist.core.declaration.koimport

import com.lemon.konsist.TestSnippetProvider
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoImportTest {

    @Test
    fun `import-name`() {
        // given
        val sut = getSnippetFile("import-name")
            .imports()
            .first()

        // then
        sut.run {
            name shouldBeEqualTo "com.lemon.konsist.testdata.SampleClass"
            alias shouldBeEqualTo "com.lemon.konsist.testdata.SampleClass"
        }
    }

    @Test
    fun `import-name-has-import-alias`() {
        // given
        val sut = getSnippetFile("import-name-has-import-alias").imports()

        // then
        sut
            .toList()
            .run {
                get(0).alias shouldBeEqualTo "com.lemon.konsist.testdata.SampleClass"
                get(1).alias shouldBeEqualTo "ImportAlias"
                get(1).name shouldBeEqualTo "com.lemon.konsist.testdata.SampleType"
            }
    }

    private fun getSnippetFile(fileName: String) = TestSnippetProvider.getSnippetKoScope("core/declaration/koimport/snippet/", fileName)
}
