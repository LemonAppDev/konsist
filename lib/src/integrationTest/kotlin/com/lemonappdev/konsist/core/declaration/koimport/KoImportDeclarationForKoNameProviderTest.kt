package com.lemonappdev.konsist.core.declaration.koimport

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoImportDeclarationForKoNameProviderTest {
    @Test
    fun `import-without-import-alias`() {
        // given
        val sut =
            getSnippetFile("import-without-import-alias")
                .imports
                .first()

        // then
        sut.name shouldBeEqualTo "com.lemonappdev.konsist.testdata.SampleClass"
    }

    @Test
    fun `import-with-import-alias`() {
        // given
        val sut =
            getSnippetFile("import-with-import-alias")
                .imports
                .first()

        // then
        sut.name shouldBeEqualTo "com.lemonappdev.konsist.testdata.SampleType"
    }

    private fun getSnippetFile(fileName: String) = getSnippetKoScope("core/declaration/koimport/snippet/forkonameprovider/", fileName)
}
