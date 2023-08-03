package com.lemonappdev.konsist.core.declaration.koimport

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoImportDeclarationForKoAliasProviderTest {
    @Test
    fun `import-without-import-alias`() {
        // given
        val sut = getSnippetFile("import-without-import-alias")
            .imports
            .first()

        // then
        sut.alias shouldBeEqualTo null
    }

    @Test
    fun `import-with-import-alias`() {
        // given
        val sut = getSnippetFile("import-with-import-alias")
            .imports

        // then
        assertSoftly(sut) {
            get(0).alias shouldBeEqualTo null
            get(1).alias shouldBeEqualTo "ImportAlias"
        }
    }

    private fun getSnippetFile(fileName: String) = getSnippetKoScope(
        "core/declaration/koimportdeclaration/snippet/forkoaliasprovider/",
        fileName,
    )
}
