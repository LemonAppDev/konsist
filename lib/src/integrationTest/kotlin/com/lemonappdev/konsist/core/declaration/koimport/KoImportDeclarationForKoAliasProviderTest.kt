package com.lemonappdev.konsist.core.declaration.koimport

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoImportDeclarationForKoAliasProviderTest {
    @Test
    fun `import-without-import-alias`() {
        // given
        val sut =
            getSnippetFile("import-without-import-alias")
                .imports
                .first()

        // then
        assertSoftly(sut) {
            alias shouldBeEqualTo null
            hasAlias() shouldBeEqualTo false
            hasAlias { it.name == "Alias" } shouldBeEqualTo false
        }
    }

    @Test
    fun `import-with-import-alias`() {
        // given
        val sut =
            getSnippetFile("import-with-import-alias")
                .imports

        // then
        assertSoftly(sut) {
            get(0).alias shouldBeEqualTo null
            get(0).hasAlias() shouldBeEqualTo false
            get(0).hasAlias { it.name == "ImportAlias" } shouldBeEqualTo false
            get(1).alias?.name shouldBeEqualTo "ImportAlias"
            get(1).hasAlias() shouldBeEqualTo true
            get(1).hasAlias { it.name == "ImportAlias" } shouldBeEqualTo true
        }
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope(
            "core/declaration/koimport/snippet/forkoaliasprovider/",
            fileName,
        )
}
