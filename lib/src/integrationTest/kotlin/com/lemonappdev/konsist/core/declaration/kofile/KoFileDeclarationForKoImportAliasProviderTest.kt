package com.lemonappdev.konsist.core.declaration.kofile

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoFileDeclarationForKoImportAliasProviderTest {
    @Test
    fun `file-has-no-import-alias`() {
        // given
        val sut = getSnippetFile("file-has-no-import-alias")
            .files
            .first()

        // then
        assertSoftly(sut) {
            importAliases.isEmpty() shouldBeEqualTo true
            numImportAliases shouldBeEqualTo 0
            countImportAliases { it.name == "SampleImportAlias" } shouldBeEqualTo 0
            hasImportAliases() shouldBeEqualTo false
            hasImportAliasWithName("SampleImportAlias") shouldBeEqualTo false
            hasImportAliasesWithAllNames(
                "SampleImportAlias1",
                "SampleImportAlias2",
            ).shouldBeEqualTo(false)
            hasImportAlias { it.name == "SampleImportAlias" } shouldBeEqualTo false
            hasAllImportAliases { it.hasNameStartingWith("SampleImport") } shouldBeEqualTo true
        }
    }

    @Test
    fun `file-has-one-import-alias`() {
        // given
        val sut = getSnippetFile("file-has-one-import-alias")
            .files
            .first()

        // then
        assertSoftly(sut) {
            importAliases.size shouldBeEqualTo 1
            numImportAliases shouldBeEqualTo 1
            countImportAliases { it.hasNameStartingWith("SampleImport") } shouldBeEqualTo 1
            hasImportAliases() shouldBeEqualTo true
            hasImportAliasWithName("SampleImportAlias") shouldBeEqualTo true
            hasImportAliasWithName("SampleOtherImportAlias") shouldBeEqualTo false
            hasImportAliasWithName(
                "SampleOtherImportAlias",
                "SampleImportAlias",
            ).shouldBeEqualTo(true)
            hasImportAliasesWithAllNames("SampleImportAlias") shouldBeEqualTo true
            hasImportAliasesWithAllNames(
                "SampleOtherImportAlias",
                "SampleImportAlias",
            ).shouldBeEqualTo(false)
            hasImportAlias { it.hasNameStartingWith("SampleImport") } shouldBeEqualTo true
            hasImportAlias { it.name == "SampleOtherImportAlias" } shouldBeEqualTo false
            hasAllImportAliases { it.hasNameStartingWith("SampleImport") } shouldBeEqualTo true
        }
    }

    @Test
    fun `file-has-two-import-aliases`() {
        // given
        val sut = getSnippetFile("file-has-two-import-aliases")
            .files
            .first()

        // then
        assertSoftly(sut) {
            numImportAliases shouldBeEqualTo 2
            countImportAliases { it.hasNameStartingWith("SampleImport") } shouldBeEqualTo 2
            countImportAliases { it.name == "SampleImportAlias1" } shouldBeEqualTo 1
            hasImportAliases() shouldBeEqualTo true
            hasImportAliasWithName("SampleImportAlias1") shouldBeEqualTo true
            hasImportAliasWithName("SampleOtherImportAlias") shouldBeEqualTo false
            hasImportAliasWithName("SampleImportAlias1", "otherName") shouldBeEqualTo true
            hasImportAliasesWithAllNames("SampleImportAlias1") shouldBeEqualTo true
            hasImportAliasesWithAllNames(
                "SampleImportAlias1",
                "SampleImportAlias2",
            ).shouldBeEqualTo(true)
            hasImportAliasesWithAllNames(
                "SampleImportAlias1",
                "SampleOtherImportAlias",
            ).shouldBeEqualTo(false)
            hasImportAlias { it.name == "SampleImportAlias1" } shouldBeEqualTo true
            hasImportAlias { it.name == "SampleOtherImportAlias" } shouldBeEqualTo false
            hasAllImportAliases { it.hasNameStartingWith("SampleImport") } shouldBeEqualTo true
            hasAllImportAliases { it.hasNameStartingWith("SampleOtherImport") } shouldBeEqualTo false
        }
    }

    private fun getSnippetFile(fileName: String) = getSnippetKoScope(
        "core/declaration/kofile/snippet/forkoimportaliasprovider/",
        fileName,
    )
}
