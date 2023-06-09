package com.lemonappdev.konsist.core.declaration.kotypedeclaration

import com.lemonappdev.konsist.TestSnippetProvider
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoTypeDeclarationForImportAliasTest {
    @Test
    fun `simple-type`() {
        // given
        val sut = getSnippetFile("simple-type")
            .classes()
            .first()
            .primaryConstructor
            ?.parameters
            ?.first()
            ?.type

        // then
        assertSoftly(sut) {
            it?.importAliasName shouldBeEqualTo ""
            it?.isImportAlias() shouldBeEqualTo false
        }
    }

    @Test
    fun `nullable-simple-type`() {
        // given
        val sut = getSnippetFile("nullable-simple-type")
            .classes()
            .first()
            .primaryConstructor
            ?.parameters
            ?.first()
            ?.type

        // then
        assertSoftly(sut) {
            it?.importAliasName shouldBeEqualTo ""
            it?.isImportAlias() shouldBeEqualTo false
        }
    }

    @Test
    fun `import-alias`() {
        // given
        val sut = getSnippetFile("import-alias")
            .classes()
            .first()
            .primaryConstructor
            ?.parameters
            ?.first()
            ?.type

        // then
        assertSoftly(sut) {
            it?.importAliasName shouldBeEqualTo "ImportAlias"
            it?.isImportAlias() shouldBeEqualTo true
        }
    }

    @Test
    fun `nullable-import-alias`() {
        // given
        val sut = getSnippetFile("nullable-import-alias")
            .classes()
            .first()
            .primaryConstructor
            ?.parameters
            ?.first()
            ?.type

        // then
        assertSoftly(sut) {
            it?.importAliasName shouldBeEqualTo "ImportAlias"
            it?.isImportAlias() shouldBeEqualTo true
        }
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("core/declaration/kotypedeclaration/snippet/forimportalias/", fileName)
}
