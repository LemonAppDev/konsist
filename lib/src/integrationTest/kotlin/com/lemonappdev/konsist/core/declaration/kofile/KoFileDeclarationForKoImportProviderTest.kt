package com.lemonappdev.konsist.core.declaration.kofile

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoFileDeclarationForKoImportProviderTest {
    @Test
    fun `file-has-no-import`() {
        // given
        val sut = getSnippetFile("file-has-no-import")
            .files
            .first()

        // then
        assertSoftly(sut) {
            imports.isEmpty() shouldBeEqualTo true
            numImports shouldBeEqualTo 0
            countImports { it.name == "com.lemonappdev.konsist.testdata.OtherImport" } shouldBeEqualTo 0
            hasImports() shouldBeEqualTo false
            hasImportWithName("com.lemonappdev.konsist.testdata.OtherImport") shouldBeEqualTo false
            hasImportsWithAllNames(
                "com.lemonappdev.konsist.testdata.SampleClass",
                "com.lemonappdev.konsist.testdata.SampleType",
            ).shouldBeEqualTo(false)
            hasImport { it.name == "com.lemonappdev.konsist.testdata.OtherImport" } shouldBeEqualTo false
            hasAllImports { it.hasNameStartingWith("com.lemonappdev.") } shouldBeEqualTo true
            hasImports("com.lemonappdev.konsist.testdata.OtherImport") shouldBeEqualTo false
        }
    }

    @Test
    fun `file-has-one-import`() {
        // given
        val sut = getSnippetFile("file-has-one-import")
            .files
            .first()

        // then
        assertSoftly(sut) {
            imports.size shouldBeEqualTo 1
            numImports shouldBeEqualTo 1
            countImports { it.hasNameStartingWith("com.lemonappdev.") } shouldBeEqualTo 1
            hasImports() shouldBeEqualTo true
            hasImportWithName("com.lemonappdev.konsist.testdata.SampleType") shouldBeEqualTo true
            hasImportWithName("com.lemonappdev.konsist.testdata.SampleClass") shouldBeEqualTo false
            hasImportWithName(
                "com.lemonappdev.konsist.testdata.SampleClass",
                "com.lemonappdev.konsist.testdata.SampleType",
            ).shouldBeEqualTo(true)
            hasImportsWithAllNames("com.lemonappdev.konsist.testdata.SampleType") shouldBeEqualTo true
            hasImportsWithAllNames(
                "com.lemonappdev.konsist.testdata.SampleClass",
                "com.lemonappdev.konsist.testdata.SampleType",
            ).shouldBeEqualTo(false)
            hasImport { it.hasNameStartingWith("com.lemonappdev.") } shouldBeEqualTo true
            hasImport { it.name == "com.lemonappdev.konsist.testdata.SampleClass" } shouldBeEqualTo false
            hasAllImports { it.hasNameStartingWith("com.lemonappdev.") } shouldBeEqualTo true
        }
    }

    @Test
    fun `file-has-two-imports`() {
        // given
        val sut = getSnippetFile("file-has-two-imports")
            .files
            .first()

        // then
        assertSoftly(sut) {
            numImports shouldBeEqualTo 2
            countImports { it.hasNameStartingWith("com.lemonappdev") } shouldBeEqualTo 2
            countImports { it.name == "com.lemonappdev.konsist.testdata.SampleType" } shouldBeEqualTo 1
            hasImports() shouldBeEqualTo true
            hasImportWithName("com.lemonappdev.konsist.testdata.SampleType") shouldBeEqualTo true
            hasImportWithName("com.lemonappdev.konsist.testdata.SampleClass") shouldBeEqualTo false
            hasImportWithName("com.lemonappdev.konsist.testdata.SampleType", "otherName") shouldBeEqualTo true
            hasImportsWithAllNames("com.lemonappdev.konsist.testdata.SampleType") shouldBeEqualTo true
            hasImportsWithAllNames(
                "com.lemonappdev.konsist.testdata.SampleType",
                "com.lemonappdev.konsist.testdata.SampleAnnotation",
            ).shouldBeEqualTo(true)
            hasImportsWithAllNames(
                "com.lemonappdev.konsist.testdata.SampleType",
                "com.lemonappdev.konsist.testdata.SampleClass",
            ).shouldBeEqualTo(false)
            hasImport { it.name == "com.lemonappdev.konsist.testdata.SampleType" } shouldBeEqualTo true
            hasImport { it.name == "com.lemonappdev.konsist.testdata.OtherType" } shouldBeEqualTo false
            hasAllImports { it.hasNameStartingWith("com.lemonappdev.") } shouldBeEqualTo true
            hasAllImports { it.hasNameStartingWith("com.other.") } shouldBeEqualTo false
            hasImports("com..") shouldBeEqualTo true
            hasImports("com..", "..testdata..") shouldBeEqualTo true
            hasImports("com") shouldBeEqualTo false
            hasImports("com", "..testdata..") shouldBeEqualTo false
            hasImports("com.lemonappdev..testdata.SampleType") shouldBeEqualTo true
            hasImports("com.lemonappdev.konsist.testdata.OtherImport") shouldBeEqualTo false
        }
    }

    private fun getSnippetFile(fileName: String) = getSnippetKoScope(
        "core/declaration/kofile/snippet/forkoimportprovider/",
        fileName,
    )
}
