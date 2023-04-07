package com.lemon.konsist.core.declaration.koimport

import com.lemon.konsist.TestSnippetProvider
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoImportTest {

    @Test
    fun `import-name`() {
        // given
        val sut = getSut("import-name")
            .imports()
            .first()

        // then
        with(sut) {
            name shouldBeEqualTo "com.lemon.konsist.testdata.SampleClass"
            alias shouldBeEqualTo "com.lemon.konsist.testdata.SampleClass"
        }
    }

    @Test
    fun `import-name-has-alias-type`() {
        // given
        val sut = getSut("import-name-has-alias-type").imports()

        // then
        with(sut) {
            this[0].alias shouldBeEqualTo "com.lemon.konsist.testdata.SampleClass"
            this[1].alias shouldBeEqualTo "AliasType"
            this[1].name shouldBeEqualTo "com.lemon.konsist.testdata.SampleType"
        }
    }

    private fun getSut(fileName: String) = TestSnippetProvider.getSnippetKoScope("koimport/snippet/$fileName.kttxt")
}
