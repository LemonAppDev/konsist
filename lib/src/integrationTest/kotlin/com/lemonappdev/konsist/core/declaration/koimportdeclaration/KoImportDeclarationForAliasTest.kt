package com.lemonappdev.konsist.core.declaration.koimportdeclaration

import com.lemonappdev.konsist.TestSnippetProvider
import com.lemonappdev.konsist.core.ext.toNormalizedPath
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoImportDeclarationForAliasTest {
    @Test
    fun `import-without-import-alias`() {
        // given
        val sut = getSnippetFile("import-without-import-alias")
            .imports()
            .first()

        // then
        sut.alias shouldBeEqualTo "com.lemonappdev.konsist.testdata.SampleClass"
    }

    @Test
    fun `import-with-import-alias`() {
        // given
        val sut = getSnippetFile("import-with-import-alias")
            .imports()

        // then
        assertSoftly(sut.toList()) {
            get(0).alias shouldBeEqualTo "com.lemonappdev.konsist.testdata.SampleClass"
            get(1).alias shouldBeEqualTo "ImportAlias"
        }
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("core/declaration/koimportdeclaration/snippet/foralias/".toNormalizedPath(), fileName)
}
