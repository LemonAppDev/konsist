package com.lemonappdev.konsist.core.declaration.koimportdeclaration

import com.lemonappdev.konsist.TestSnippetProvider
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoImportDeclarationTest {

    @Test
    fun `import-name`() {
        // given
        val sut = getSnippetFile("import-name")
            .imports()
            .first()

        // then
        assertSoftly(sut) {
            name shouldBeEqualTo "com.lemonappdev.konsist.testdata.SampleClass"
            alias shouldBeEqualTo "com.lemonappdev.konsist.testdata.SampleClass"
        }
    }

    @Test
    fun `import-name-has-import-alias`() {
        // given
        val sut = getSnippetFile("import-name-has-import-alias")
            .imports()

        // then
        assertSoftly(sut.toList()) {
            get(0).alias shouldBeEqualTo "com.lemonappdev.konsist.testdata.SampleClass"
            get(1).alias shouldBeEqualTo "ImportAlias"
            get(1).name shouldBeEqualTo "com.lemonappdev.konsist.testdata.SampleType"
        }
    }

    @Test
    fun `import-with-wildcard`() {
        // given
        val sut = getSnippetFile("import-with-wildcard")
            .imports()
            .first()

        // then
        sut.isWildcard shouldBeEqualTo true
    }

    @Test
    fun `import-without-wildcard`() {
        // given
        val sut = getSnippetFile("import-without-wildcard")
            .imports()
            .first()

        // then
        sut.isWildcard shouldBeEqualTo false
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("core/declaration/koimportdeclaration/snippet/", fileName)
}
