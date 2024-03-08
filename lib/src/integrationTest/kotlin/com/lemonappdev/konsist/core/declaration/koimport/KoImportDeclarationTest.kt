package com.lemonappdev.konsist.core.declaration.koimport

import com.lemonappdev.konsist.TestSnippetProvider
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoImportDeclarationTest {
    @Test
    fun `import-to-string`() {
        // given
        val sut =
            getSnippetFile("import-to-string")
                .imports
                .first()

        // then
        sut.toString() shouldBeEqualTo "com.lemonappdev.konsist.testdata.SampleAnnotation"
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("core/declaration/koimport/snippet/forgeneral/", fileName)
}
