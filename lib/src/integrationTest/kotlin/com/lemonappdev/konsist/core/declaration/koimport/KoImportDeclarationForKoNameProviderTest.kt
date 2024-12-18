package com.lemonappdev.konsist.core.declaration.koimport

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.assertSoftly
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

    @Test
    fun `import-has-name-with-prefix`() {
        // given
        val sut =
            getSnippetFile("import-has-name-with-prefix")
                .imports
                .first()

        // then
        assertSoftly(sut) {
            hasNameStartingWith("com.lemonappdev") shouldBeEqualTo true
            hasNameStartingWith("wrong-prefix") shouldBeEqualTo false
            hasNameStartingWith("COM.lemonappdev", ignoreCase = false) shouldBeEqualTo false
            hasNameStartingWith("COM.lemonappdev", ignoreCase = true) shouldBeEqualTo true
        }
    }

    @Test
    fun `import-has-name-with-suffix`() {
        // given
        val sut =
            getSnippetFile("import-has-name-with-suffix")
                .imports
                .first()

        // then
        assertSoftly(sut) {
            hasNameEndingWith("testdata.SampleClass") shouldBeEqualTo true
            hasNameEndingWith("wrong-suffix") shouldBeEqualTo false
            hasNameEndingWith("TESTDATA.SampleClass", ignoreCase = false) shouldBeEqualTo false
            hasNameEndingWith("TESTDATA.SampleClass", ignoreCase = true) shouldBeEqualTo true
        }
    }

    @Test
    fun `import-has-name-containing-text`() {
        // given
        val sut =
            getSnippetFile("import-has-name-containing-text")
                .imports
                .first()

        // then
        assertSoftly(sut) {
            hasNameContaining("konsist.testdata.") shouldBeEqualTo true
            hasNameContaining("not-containing") shouldBeEqualTo false
            hasNameContaining("konsist.TESTDATA.", ignoreCase = false) shouldBeEqualTo false
            hasNameContaining("konsist.TESTDATA.", ignoreCase = true) shouldBeEqualTo true
        }
    }

    @Test
    fun `import-has-name-matching-regex`() {
        // given
        val sut =
            getSnippetFile("import-has-name-matching-regex")
                .imports
                .first()

        // then
        assertSoftly(sut) {
            hasNameMatching(Regex("^[a-zA-Z[^0-9]]+")) shouldBeEqualTo true
            hasNameMatching(Regex("[0-9]+")) shouldBeEqualTo false
        }
    }

    private fun getSnippetFile(importName: String) = getSnippetKoScope("core/declaration/koimport/snippet/forkonameprovider/", importName)
}
