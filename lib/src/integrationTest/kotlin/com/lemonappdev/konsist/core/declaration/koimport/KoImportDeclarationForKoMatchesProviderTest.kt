package com.lemonappdev.konsist.core.declaration.koimport

import com.lemonappdev.konsist.TestSnippetProvider
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoImportDeclarationForKoMatchesProviderTest {
    @Test
    fun `import-matches`() {
        // given
        val sut =
            getSnippetFile("import-matches")
                .imports
                .first()

        // then
        assertSoftly(sut) {
            matches("com..") shouldBeEqualTo true
            matches("com") shouldBeEqualTo false
            matches("..testdata..") shouldBeEqualTo true
            matches("com.lemonappdev..testdata.SampleType") shouldBeEqualTo true
            matches("com.lemonappdev.konsist.testdata.OtherImport") shouldBeEqualTo false
        }
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("core/declaration/koimport/snippet/forkomatchesprovider/", fileName)
}
