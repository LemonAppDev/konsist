package com.lemonappdev.konsist.core.declaration.kofile

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

@Suppress("detekt.LongMethod")
class KoFileDeclarationForKoImportAliasProviderTest {
    @Test
    fun `file-has-no-import-alias`() {
        // given
        val sut =
            getSnippetFile("file-has-no-import-alias")
                .files
                .first()

        // then
        assertSoftly(sut) {
            importAliases.isEmpty() shouldBeEqualTo true
            numImportAliases shouldBeEqualTo 0
            countImportAliases { it.name == "FixtureImportAlias" } shouldBeEqualTo 0
            hasImportAliases() shouldBeEqualTo false
            hasImportAliasWithName(emptyList()) shouldBeEqualTo false
            hasImportAliasWithName(emptySet()) shouldBeEqualTo false
            hasImportAliasesWithAllNames(emptyList()) shouldBeEqualTo false
            hasImportAliasesWithAllNames(emptySet()) shouldBeEqualTo false
            hasImportAliasWithName("FixtureImportAlias") shouldBeEqualTo false
            hasImportAliasWithName(listOf("FixtureImportAlias")) shouldBeEqualTo false
            hasImportAliasWithName(setOf("FixtureImportAlias")) shouldBeEqualTo false
            hasImportAliasesWithAllNames(
                "FixtureImportAlias1",
                "FixtureImportAlias2",
            ).shouldBeEqualTo(false)
            hasImportAliasesWithAllNames(
                listOf(
                    "FixtureImportAlias1",
                    "FixtureImportAlias2",
                ),
            ).shouldBeEqualTo(false)
            hasImportAliasesWithAllNames(
                setOf(
                    "FixtureImportAlias1",
                    "FixtureImportAlias2",
                ),
            ).shouldBeEqualTo(false)
            hasImportAlias { it.name == "FixtureImportAlias" } shouldBeEqualTo false
            hasAllImportAliases { it.hasNameStartingWith("FixtureImport") } shouldBeEqualTo true
        }
    }

    @Test
    fun `file-has-one-import-alias`() {
        // given
        val sut =
            getSnippetFile("file-has-one-import-alias")
                .files
                .first()

        // then
        assertSoftly(sut) {
            importAliases.size shouldBeEqualTo 1
            numImportAliases shouldBeEqualTo 1
            countImportAliases { it.hasNameStartingWith("FixtureImport") } shouldBeEqualTo 1
            hasImportAliases() shouldBeEqualTo true
            hasImportAliasWithName(emptyList()) shouldBeEqualTo true
            hasImportAliasWithName(emptySet()) shouldBeEqualTo true
            hasImportAliasesWithAllNames(emptyList()) shouldBeEqualTo true
            hasImportAliasesWithAllNames(emptySet()) shouldBeEqualTo true
            hasImportAliasWithName("FixtureImportAlias") shouldBeEqualTo true
            hasImportAliasWithName("FixtureOtherImportAlias") shouldBeEqualTo false
            hasImportAliasWithName(
                "FixtureOtherImportAlias",
                "FixtureImportAlias",
            ).shouldBeEqualTo(true)
            hasImportAliasWithName(listOf("FixtureImportAlias")) shouldBeEqualTo true
            hasImportAliasWithName(listOf("FixtureOtherImportAlias")) shouldBeEqualTo false
            hasImportAliasWithName(
                listOf(
                    "FixtureOtherImportAlias",
                    "FixtureImportAlias",
                ),
            ).shouldBeEqualTo(true)
            hasImportAliasWithName(setOf("FixtureImportAlias")) shouldBeEqualTo true
            hasImportAliasWithName(setOf("FixtureOtherImportAlias")) shouldBeEqualTo false
            hasImportAliasWithName(
                setOf(
                    "FixtureOtherImportAlias",
                    "FixtureImportAlias",
                ),
            ).shouldBeEqualTo(true)
            hasImportAliasesWithAllNames("FixtureImportAlias") shouldBeEqualTo true
            hasImportAliasesWithAllNames(
                "FixtureOtherImportAlias",
                "FixtureImportAlias",
            ).shouldBeEqualTo(false)
            hasImportAliasesWithAllNames(listOf("FixtureImportAlias")) shouldBeEqualTo true
            hasImportAliasesWithAllNames(
                listOf(
                    "FixtureOtherImportAlias",
                    "FixtureImportAlias",
                ),
            ).shouldBeEqualTo(false)
            hasImportAliasesWithAllNames(setOf("FixtureImportAlias")) shouldBeEqualTo true
            hasImportAliasesWithAllNames(
                setOf(
                    "FixtureOtherImportAlias",
                    "FixtureImportAlias",
                ),
            ).shouldBeEqualTo(false)
            hasImportAlias { it.hasNameStartingWith("FixtureImport") } shouldBeEqualTo true
            hasImportAlias { it.name == "FixtureOtherImportAlias" } shouldBeEqualTo false
            hasAllImportAliases { it.hasNameStartingWith("FixtureImport") } shouldBeEqualTo true
        }
    }

    @Test
    fun `file-has-two-import-aliases`() {
        // given
        val sut =
            getSnippetFile("file-has-two-import-aliases")
                .files
                .first()

        // then
        assertSoftly(sut) {
            numImportAliases shouldBeEqualTo 2
            countImportAliases { it.hasNameStartingWith("FixtureImport") } shouldBeEqualTo 2
            countImportAliases { it.name == "FixtureImportAlias1" } shouldBeEqualTo 1
            hasImportAliases() shouldBeEqualTo true
            hasImportAliasWithName(emptyList()) shouldBeEqualTo true
            hasImportAliasWithName(emptySet()) shouldBeEqualTo true
            hasImportAliasesWithAllNames(emptyList()) shouldBeEqualTo true
            hasImportAliasesWithAllNames(emptySet()) shouldBeEqualTo true
            hasImportAliasWithName("FixtureImportAlias1") shouldBeEqualTo true
            hasImportAliasWithName("FixtureOtherImportAlias") shouldBeEqualTo false
            hasImportAliasWithName("FixtureImportAlias1", "otherName") shouldBeEqualTo true
            hasImportAliasWithName(listOf("FixtureImportAlias1")) shouldBeEqualTo true
            hasImportAliasWithName(listOf("FixtureOtherImportAlias")) shouldBeEqualTo false
            hasImportAliasWithName(listOf("FixtureImportAlias1", "otherName")) shouldBeEqualTo true
            hasImportAliasWithName(setOf("FixtureImportAlias1")) shouldBeEqualTo true
            hasImportAliasWithName(setOf("FixtureOtherImportAlias")) shouldBeEqualTo false
            hasImportAliasWithName(setOf("FixtureImportAlias1", "otherName")) shouldBeEqualTo true
            hasImportAliasesWithAllNames("FixtureImportAlias1") shouldBeEqualTo true
            hasImportAliasesWithAllNames(
                "FixtureImportAlias1",
                "FixtureImportAlias2",
            ).shouldBeEqualTo(true)
            hasImportAliasesWithAllNames(
                "FixtureImportAlias1",
                "FixtureOtherImportAlias",
            ).shouldBeEqualTo(false)
            hasImportAliasesWithAllNames(listOf("FixtureImportAlias1")) shouldBeEqualTo true
            hasImportAliasesWithAllNames(
                listOf(
                    "FixtureImportAlias1",
                    "FixtureImportAlias2",
                ),
            ).shouldBeEqualTo(true)
            hasImportAliasesWithAllNames(
                listOf(
                    "FixtureImportAlias1",
                    "FixtureOtherImportAlias",
                ),
            ).shouldBeEqualTo(false)
            hasImportAliasesWithAllNames(setOf("FixtureImportAlias1")) shouldBeEqualTo true
            hasImportAliasesWithAllNames(
                setOf(
                    "FixtureImportAlias1",
                    "FixtureImportAlias2",
                ),
            ).shouldBeEqualTo(true)
            hasImportAliasesWithAllNames(
                setOf(
                    "FixtureImportAlias1",
                    "FixtureOtherImportAlias",
                ),
            ).shouldBeEqualTo(false)
            hasImportAlias { it.name == "FixtureImportAlias1" } shouldBeEqualTo true
            hasImportAlias { it.name == "FixtureOtherImportAlias" } shouldBeEqualTo false
            hasAllImportAliases { it.hasNameStartingWith("FixtureImport") } shouldBeEqualTo true
            hasAllImportAliases { it.hasNameStartingWith("FixtureOtherImport") } shouldBeEqualTo false
        }
    }

    @Test
    fun `file-has-no-import-alias-ignore-case`() {
        // given
        val sut =
            getSnippetFile("file-has-no-import-alias-ignore-case")
                .files
                .first()

        // then
        assertSoftly(sut) {
            hasImportAliasWithName("fixtureimportalias") shouldBeEqualTo false
            hasImportAliasWithName("fixtureimportalias", ignoreCase = true) shouldBeEqualTo false
            hasImportAliasWithName(listOf("fixtureimportalias")) shouldBeEqualTo false
            hasImportAliasWithName(listOf("fixtureimportalias"), ignoreCase = true) shouldBeEqualTo false
            hasImportAliasWithName(setOf("fixtureimportalias")) shouldBeEqualTo false
            hasImportAliasWithName(setOf("fixtureimportalias"), ignoreCase = true) shouldBeEqualTo false
            hasImportAliasesWithAllNames("fixtureimportalias1", "fixtureimportalias2") shouldBeEqualTo false
            hasImportAliasesWithAllNames("fixtureimportalias1", "fixtureimportalias2", ignoreCase = true) shouldBeEqualTo false
            hasImportAliasesWithAllNames(listOf("fixtureimportalias1", "fixtureimportalias2")) shouldBeEqualTo false
            hasImportAliasesWithAllNames(listOf("fixtureimportalias1", "fixtureimportalias2"), ignoreCase = true) shouldBeEqualTo false
            hasImportAliasesWithAllNames(setOf("fixtureimportalias1", "fixtureimportalias2")) shouldBeEqualTo false
            hasImportAliasesWithAllNames(setOf("fixtureimportalias1", "fixtureimportalias2"), ignoreCase = true) shouldBeEqualTo false
        }
    }

    @Test
    fun `file-has-import-aliases-ignore-case`() {
        // given
        val sut =
            getSnippetFile("file-has-import-aliases-ignore-case")
                .files
                .first()

        // then
        assertSoftly(sut) {
            hasImportAliasWithName("fixtureimportalias1") shouldBeEqualTo false
            hasImportAliasWithName("fixtureimportalias1", ignoreCase = true) shouldBeEqualTo true
            hasImportAliasWithName("otherimportalias") shouldBeEqualTo false
            hasImportAliasWithName("otherimportalias", ignoreCase = true) shouldBeEqualTo false
            hasImportAliasWithName("fixtureimportalias1", "otherName") shouldBeEqualTo false
            hasImportAliasWithName("fixtureimportalias1", "otherName", ignoreCase = true) shouldBeEqualTo true
            hasImportAliasWithName(listOf("fixtureimportalias1")) shouldBeEqualTo false
            hasImportAliasWithName(listOf("fixtureimportalias1"), ignoreCase = true) shouldBeEqualTo true
            hasImportAliasWithName(listOf("otherimportalias")) shouldBeEqualTo false
            hasImportAliasWithName(listOf("otherimportalias"), ignoreCase = true) shouldBeEqualTo false
            hasImportAliasWithName(listOf("fixtureimportalias1", "otherName")) shouldBeEqualTo false
            hasImportAliasWithName(listOf("fixtureimportalias1", "otherName"), ignoreCase = true) shouldBeEqualTo true
            hasImportAliasesWithAllNames("fixtureimportalias1") shouldBeEqualTo false
            hasImportAliasesWithAllNames("fixtureimportalias1", ignoreCase = true) shouldBeEqualTo true
            hasImportAliasesWithAllNames("fixtureimportalias1", "fixtureimportalias2") shouldBeEqualTo false
            hasImportAliasesWithAllNames("fixtureimportalias1", "fixtureimportalias2", ignoreCase = true) shouldBeEqualTo true
            hasImportAliasesWithAllNames("fixtureimportalias1", "otherimportalias") shouldBeEqualTo false
            hasImportAliasesWithAllNames("fixtureimportalias1", "otherimportalias", ignoreCase = true) shouldBeEqualTo false
            hasImportAliasesWithAllNames(listOf("fixtureimportalias1")) shouldBeEqualTo false
            hasImportAliasesWithAllNames(listOf("fixtureimportalias1"), ignoreCase = true) shouldBeEqualTo true
            hasImportAliasesWithAllNames(listOf("fixtureimportalias1", "fixtureimportalias2")) shouldBeEqualTo false
            hasImportAliasesWithAllNames(listOf("fixtureimportalias1", "fixtureimportalias2"), ignoreCase = true) shouldBeEqualTo true
            hasImportAliasesWithAllNames(listOf("fixtureimportalias1", "otherimportalias")) shouldBeEqualTo false
            hasImportAliasesWithAllNames(listOf("fixtureimportalias1", "otherimportalias"), ignoreCase = true) shouldBeEqualTo false
        }
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope(
            "core/declaration/kofile/snippet/forkoimportaliasprovider/",
            fileName,
        )
}
