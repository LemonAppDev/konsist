package com.lemonappdev.konsist.core.declaration.kofile

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

@Suppress("detekt.LongMethod")
class KoFileDeclarationForKoImportProviderTest {
    @Test
    fun `file-has-no-import`() {
        // given
        val sut =
            getSnippetFile("file-has-no-import")
                .files
                .first()

        // then
        assertSoftly(sut) {
            imports.isEmpty() shouldBeEqualTo true
            numImports shouldBeEqualTo 0
            countImports { it.name == "com.lemonappdev.konsist.testdata.OtherImport" } shouldBeEqualTo 0
            hasImports() shouldBeEqualTo false
            hasImportWithName(emptyList()) shouldBeEqualTo false
            hasImportWithName(emptySet()) shouldBeEqualTo false
            hasImportsWithAllNames(emptyList()) shouldBeEqualTo false
            hasImportsWithAllNames(emptySet()) shouldBeEqualTo false
            hasImportWithName("com.lemonappdev.konsist.testdata.OtherImport") shouldBeEqualTo false
            hasImportWithName(listOf("com.lemonappdev.konsist.testdata.OtherImport")) shouldBeEqualTo false
            hasImportWithName(setOf("com.lemonappdev.konsist.testdata.OtherImport")) shouldBeEqualTo false
            hasImportsWithAllNames(
                "com.lemonappdev.konsist.testdata.FixtureClass",
                "com.lemonappdev.konsist.testdata.FixtureType",
            ).shouldBeEqualTo(false)
            hasImportsWithAllNames(
                listOf(
                    "com.lemonappdev.konsist.testdata.FixtureClass",
                    "com.lemonappdev.konsist.testdata.FixtureType",
                ),
            ).shouldBeEqualTo(false)
            hasImportsWithAllNames(
                setOf(
                    "com.lemonappdev.konsist.testdata.FixtureClass",
                    "com.lemonappdev.konsist.testdata.FixtureType",
                ),
            ).shouldBeEqualTo(false)
            hasImport { it.name == "com.lemonappdev.konsist.testdata.OtherImport" } shouldBeEqualTo false
            hasAllImports { it.hasNameStartingWith("com.lemonappdev.") } shouldBeEqualTo true
        }
    }

    @Test
    fun `file-has-one-import`() {
        // given
        val sut =
            getSnippetFile("file-has-one-import")
                .files
                .first()

        // then
        assertSoftly(sut) {
            imports.size shouldBeEqualTo 1
            numImports shouldBeEqualTo 1
            countImports { it.hasNameStartingWith("com.lemonappdev.") } shouldBeEqualTo 1
            hasImports() shouldBeEqualTo true
            hasImportWithName(emptyList()) shouldBeEqualTo true
            hasImportWithName(emptySet()) shouldBeEqualTo true
            hasImportsWithAllNames(emptyList()) shouldBeEqualTo true
            hasImportsWithAllNames(emptySet()) shouldBeEqualTo true
            hasImportWithName("com.lemonappdev.konsist.testdata.FixtureType") shouldBeEqualTo true
            hasImportWithName("com.lemonappdev.konsist.testdata.FixtureClass") shouldBeEqualTo false
            hasImportWithName(
                "com.lemonappdev.konsist.testdata.FixtureClass",
                "com.lemonappdev.konsist.testdata.FixtureType",
            ).shouldBeEqualTo(true)
            hasImportWithName(listOf("com.lemonappdev.konsist.testdata.FixtureType")) shouldBeEqualTo true
            hasImportWithName(listOf("com.lemonappdev.konsist.testdata.FixtureClass")) shouldBeEqualTo false
            hasImportWithName(
                listOf(
                    "com.lemonappdev.konsist.testdata.FixtureClass",
                    "com.lemonappdev.konsist.testdata.FixtureType",
                ),
            ).shouldBeEqualTo(true)
            hasImportWithName(setOf("com.lemonappdev.konsist.testdata.FixtureType")) shouldBeEqualTo true
            hasImportWithName(setOf("com.lemonappdev.konsist.testdata.FixtureClass")) shouldBeEqualTo false
            hasImportWithName(
                setOf(
                    "com.lemonappdev.konsist.testdata.FixtureClass",
                    "com.lemonappdev.konsist.testdata.FixtureType",
                ),
            ).shouldBeEqualTo(true)
            hasImportsWithAllNames("com.lemonappdev.konsist.testdata.FixtureType") shouldBeEqualTo true
            hasImportsWithAllNames(
                "com.lemonappdev.konsist.testdata.FixtureClass",
                "com.lemonappdev.konsist.testdata.FixtureType",
            ).shouldBeEqualTo(false)
            hasImportsWithAllNames(listOf("com.lemonappdev.konsist.testdata.FixtureType")) shouldBeEqualTo true
            hasImportsWithAllNames(
                listOf(
                    "com.lemonappdev.konsist.testdata.FixtureClass",
                    "com.lemonappdev.konsist.testdata.FixtureType",
                ),
            ).shouldBeEqualTo(false)
            hasImportsWithAllNames(setOf("com.lemonappdev.konsist.testdata.FixtureType")) shouldBeEqualTo true
            hasImportsWithAllNames(
                setOf(
                    "com.lemonappdev.konsist.testdata.FixtureClass",
                    "com.lemonappdev.konsist.testdata.FixtureType",
                ),
            ).shouldBeEqualTo(false)
            hasImport { it.hasNameStartingWith("com.lemonappdev.") } shouldBeEqualTo true
            hasImport { it.name == "com.lemonappdev.konsist.testdata.FixtureClass" } shouldBeEqualTo false
            hasAllImports { it.hasNameStartingWith("com.lemonappdev.") } shouldBeEqualTo true
        }
    }

    @Test
    fun `file-has-two-imports`() {
        // given
        val sut =
            getSnippetFile("file-has-two-imports")
                .files
                .first()

        // then
        assertSoftly(sut) {
            numImports shouldBeEqualTo 2
            countImports { it.hasNameStartingWith("com.lemonappdev") } shouldBeEqualTo 2
            countImports { it.name == "com.lemonappdev.konsist.testdata.FixtureType" } shouldBeEqualTo 1
            hasImports() shouldBeEqualTo true
            hasImportWithName(emptyList()) shouldBeEqualTo true
            hasImportWithName(emptySet()) shouldBeEqualTo true
            hasImportsWithAllNames(emptyList()) shouldBeEqualTo true
            hasImportsWithAllNames(emptySet()) shouldBeEqualTo true
            hasImportWithName("com.lemonappdev.konsist.testdata.FixtureType") shouldBeEqualTo true
            hasImportWithName("com.lemonappdev.konsist.testdata.FixtureClass") shouldBeEqualTo false
            hasImportWithName("com.lemonappdev.konsist.testdata.FixtureType", "otherName") shouldBeEqualTo true
            hasImportWithName(listOf("com.lemonappdev.konsist.testdata.FixtureType")) shouldBeEqualTo true
            hasImportWithName(listOf("com.lemonappdev.konsist.testdata.FixtureClass")) shouldBeEqualTo false
            hasImportWithName(listOf("com.lemonappdev.konsist.testdata.FixtureType", "otherName")) shouldBeEqualTo true
            hasImportWithName(setOf("com.lemonappdev.konsist.testdata.FixtureType")) shouldBeEqualTo true
            hasImportWithName(setOf("com.lemonappdev.konsist.testdata.FixtureClass")) shouldBeEqualTo false
            hasImportWithName(setOf("com.lemonappdev.konsist.testdata.FixtureType", "otherName")) shouldBeEqualTo true
            hasImportsWithAllNames("com.lemonappdev.konsist.testdata.FixtureType") shouldBeEqualTo true
            hasImportsWithAllNames(
                "com.lemonappdev.konsist.testdata.FixtureType",
                "com.lemonappdev.konsist.testdata.FixtureAnnotation",
            ).shouldBeEqualTo(true)
            hasImportsWithAllNames(
                "com.lemonappdev.konsist.testdata.FixtureType",
                "com.lemonappdev.konsist.testdata.FixtureClass",
            ).shouldBeEqualTo(false)
            hasImportsWithAllNames(listOf("com.lemonappdev.konsist.testdata.FixtureType")) shouldBeEqualTo true
            hasImportsWithAllNames(
                listOf(
                    "com.lemonappdev.konsist.testdata.FixtureType",
                    "com.lemonappdev.konsist.testdata.FixtureAnnotation",
                ),
            ).shouldBeEqualTo(true)
            hasImportsWithAllNames(
                listOf(
                    "com.lemonappdev.konsist.testdata.FixtureType",
                    "com.lemonappdev.konsist.testdata.FixtureClass",
                ),
            ).shouldBeEqualTo(false)
            hasImportsWithAllNames(setOf("com.lemonappdev.konsist.testdata.FixtureType")) shouldBeEqualTo true
            hasImportsWithAllNames(
                setOf(
                    "com.lemonappdev.konsist.testdata.FixtureType",
                    "com.lemonappdev.konsist.testdata.FixtureAnnotation",
                ),
            ).shouldBeEqualTo(true)
            hasImportsWithAllNames(
                setOf(
                    "com.lemonappdev.konsist.testdata.FixtureType",
                    "com.lemonappdev.konsist.testdata.FixtureClass",
                ),
            ).shouldBeEqualTo(false)
            hasImport { it.name == "com.lemonappdev.konsist.testdata.FixtureType" } shouldBeEqualTo true
            hasImport { it.name == "com.lemonappdev.konsist.testdata.OtherType" } shouldBeEqualTo false
            hasAllImports { it.hasNameStartingWith("com.lemonappdev.") } shouldBeEqualTo true
            hasAllImports { it.hasNameStartingWith("com.other.") } shouldBeEqualTo false
        }
    }

    @Test
    fun `file-has-no-import-ignore-case`() {
        // given
        val sut =
            getSnippetFile("file-has-no-import-ignore-case")
                .files
                .first()

        // then
        assertSoftly(sut) {
            hasImportWithName("com.lemonappdev.konsist.testdata.otherimport") shouldBeEqualTo false
            hasImportWithName("com.lemonappdev.konsist.testdata.otherimport", ignoreCase = true) shouldBeEqualTo false
            hasImportWithName(listOf("com.lemonappdev.konsist.testdata.otherimport")) shouldBeEqualTo false
            hasImportWithName(
                listOf("com.lemonappdev.konsist.testdata.otherimport"),
                ignoreCase = true,
            ).shouldBeEqualTo(false)
            hasImportWithName(setOf("com.lemonappdev.konsist.testdata.otherimport")) shouldBeEqualTo false
            hasImportWithName(
                setOf("com.lemonappdev.konsist.testdata.otherimport"),
                ignoreCase = true,
            ).shouldBeEqualTo(false)
            hasImportsWithAllNames(
                "com.lemonappdev.konsist.testdata.fixtureclass",
                "com.lemonappdev.konsist.testdata.fixturetype",
            ).shouldBeEqualTo(false)
            hasImportsWithAllNames(
                "com.lemonappdev.konsist.testdata.fixtureclass",
                "com.lemonappdev.konsist.testdata.fixturetype",
                ignoreCase = true,
            ).shouldBeEqualTo(false)
            hasImportsWithAllNames(
                listOf(
                    "com.lemonappdev.konsist.testdata.fixtureclass",
                    "com.lemonappdev.konsist.testdata.fixturetype",
                ),
            ).shouldBeEqualTo(false)
            hasImportsWithAllNames(
                listOf(
                    "com.lemonappdev.konsist.testdata.fixtureclass",
                    "com.lemonappdev.konsist.testdata.fixturetype",
                ),
                ignoreCase = true,
            ).shouldBeEqualTo(false)
            hasImportsWithAllNames(
                setOf(
                    "com.lemonappdev.konsist.testdata.fixtureclass",
                    "com.lemonappdev.konsist.testdata.fixturetype",
                ),
            ).shouldBeEqualTo(false)
            hasImportsWithAllNames(
                setOf(
                    "com.lemonappdev.konsist.testdata.fixturetype",
                    "com.lemonappdev.konsist.testdata.fixtureclass",
                ),
                ignoreCase = true,
            ).shouldBeEqualTo(false)
        }
    }

    @Test
    fun `file-has-imports-ignore-case`() {
        // given
        val sut =
            getSnippetFile("file-has-imports-ignore-case")
                .files
                .first()

        // then
        assertSoftly(sut) {
            hasImportWithName("com.lemonappdev.konsist.testdata.fixturetype") shouldBeEqualTo false
            hasImportWithName("com.lemonappdev.konsist.testdata.fixturetype", ignoreCase = true) shouldBeEqualTo true
            hasImportWithName("com.lemonappdev.konsist.testdata.otherimport") shouldBeEqualTo false
            hasImportWithName("com.lemonappdev.konsist.testdata.otherimport", ignoreCase = true) shouldBeEqualTo false
            hasImportWithName("com.lemonappdev.konsist.testdata.fixturetype", "otherName") shouldBeEqualTo false
            hasImportWithName(
                "com.lemonappdev.konsist.testdata.fixturetype",
                "otherName",
                ignoreCase = true,
            ).shouldBeEqualTo(true)
            hasImportWithName(listOf("com.lemonappdev.konsist.testdata.fixturetype")) shouldBeEqualTo false
            hasImportWithName(
                listOf("com.lemonappdev.konsist.testdata.fixturetype"),
                ignoreCase = true,
            ).shouldBeEqualTo(true)
            hasImportWithName(listOf("com.lemonappdev.konsist.testdata.otherimport")) shouldBeEqualTo false
            hasImportWithName(
                listOf("com.lemonappdev.konsist.testdata.otherimport"),
                ignoreCase = true,
            ).shouldBeEqualTo(false)
            hasImportWithName(listOf("com.lemonappdev.konsist.testdata.fixturetype", "otherName")) shouldBeEqualTo false
            hasImportWithName(
                listOf("com.lemonappdev.konsist.testdata.fixturetype", "otherName"),
                ignoreCase = true,
            ).shouldBeEqualTo(true)
            hasImportsWithAllNames("com.lemonappdev.konsist.testdata.fixturetype") shouldBeEqualTo false
            hasImportsWithAllNames(
                "com.lemonappdev.konsist.testdata.fixturetype",
                ignoreCase = true,
            ).shouldBeEqualTo(true)
            hasImportsWithAllNames(
                "com.lemonappdev.konsist.testdata.fixtureannotation",
                "com.lemonappdev.konsist.testdata.fixturetype",
            ).shouldBeEqualTo(false)
            hasImportsWithAllNames(
                "com.lemonappdev.konsist.testdata.fixtureannotation",
                "com.lemonappdev.konsist.testdata.fixturetype",
                ignoreCase = true,
            ).shouldBeEqualTo(true)
            hasImportsWithAllNames(
                "com.lemonappdev.konsist.testdata.fixturetype",
                "com.lemonappdev.konsist.testdata.otherimport",
            ).shouldBeEqualTo(false)
            hasImportsWithAllNames(
                "com.lemonappdev.konsist.testdata.fixturetype",
                "com.lemonappdev.konsist.testdata.otherimport",
                ignoreCase = true,
            ).shouldBeEqualTo(false)
            hasImportsWithAllNames(listOf("com.lemonappdev.konsist.testdata.fixturetype")) shouldBeEqualTo false
            hasImportsWithAllNames(
                listOf("com.lemonappdev.konsist.testdata.fixturetype"),
                ignoreCase = true,
            ).shouldBeEqualTo(true)
            hasImportsWithAllNames(
                listOf(
                    "com.lemonappdev.konsist.testdata.fixturetype",
                    "com.lemonappdev.konsist.testdata.fixtureclass",
                ),
            ).shouldBeEqualTo(false)
            hasImportsWithAllNames(
                listOf(
                    "com.lemonappdev.konsist.testdata.fixtureannotation",
                    "com.lemonappdev.konsist.testdata.fixturetype",
                ),
                ignoreCase = true,
            ).shouldBeEqualTo(true)
            hasImportsWithAllNames(
                listOf(
                    "com.lemonappdev.konsist.testdata.fixtureannotation",
                    "com.lemonappdev.konsist.testdata.otherimport",
                ),
            ).shouldBeEqualTo(false)
            hasImportsWithAllNames(
                listOf(
                    "com.lemonappdev.konsist.testdata.fixturetype",
                    "com.lemonappdev.konsist.testdata.otherimport",
                ),
                ignoreCase = true,
            ).shouldBeEqualTo(false)
        }
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope(
            "core/declaration/kofile/snippet/forkoimportprovider/",
            fileName,
        )
}
