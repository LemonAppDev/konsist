package com.lemonappdev.konsist.core.declaration.koimport

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoImportDeclarationForKoTextProviderTest {
    @Test
    fun `import-text`() {
        // given
        val sut =
            getSnippetFile("import-text")
                .imports
                .first()

        // then
        sut.text shouldBeEqualTo "import com.lemonappdev.konsist.testdata.SampleClass"
    }

    private fun getSnippetFile(fileName: String) = getSnippetKoScope("core/declaration/koimport/snippet/forkotextprovider/", fileName)
}
