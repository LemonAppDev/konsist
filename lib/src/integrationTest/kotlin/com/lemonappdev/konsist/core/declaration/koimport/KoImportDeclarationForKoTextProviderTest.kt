package com.lemonappdev.konsist.core.declaration.koimport

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.assertSoftly
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

    @Test
    fun `import-has-text-with-prefix`() {
        // given
        val sut =
            getSnippetFile("import-has-text-with-prefix")
                .imports
                .first()

        // then
        assertSoftly(sut) {
            hasTextStartingWith("import com.lemonappdev") shouldBeEqualTo true
            hasTextStartingWith("wrong-prefix") shouldBeEqualTo false
        }
    }

    @Test
    fun `import-has-text-with-suffix`() {
        // given
        val sut =
            getSnippetFile("import-has-text-with-suffix")
                .imports
                .first()

        // then
        assertSoftly(sut) {
            hasTextEndingWith("testdata.SampleClass") shouldBeEqualTo true
            hasTextEndingWith("wrong-suffix") shouldBeEqualTo false
        }
    }

    @Test
    fun `import-has-text-containing-text`() {
        // given
        val sut =
            getSnippetFile("import-has-text-containing-text")
                .imports
                .first()

        // then
        assertSoftly(sut) {
            hasTextContaining(" com.") shouldBeEqualTo true
            hasTextContaining("not-containing") shouldBeEqualTo false
        }
    }

    @Test
    fun `import-has-text-matching-regex`() {
        // given
        val sut =
            getSnippetFile("import-has-text-matching-regex")
                .imports
                .first()

        // then
        assertSoftly(sut) {
            hasTextMatching(Regex("^[a-zA-Z[^0-9]]+")) shouldBeEqualTo true
            hasTextMatching(Regex("[0-9]+")) shouldBeEqualTo false
        }
    }

    private fun getSnippetFile(fileName: String) = getSnippetKoScope("core/declaration/koimport/snippet/forkotextprovider/", fileName)
}
