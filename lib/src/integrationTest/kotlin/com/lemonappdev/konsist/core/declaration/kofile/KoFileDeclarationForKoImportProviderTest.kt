package com.lemonappdev.konsist.core.declaration.kofile

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoFileDeclarationForKoImportProviderTest {
    @Test
    fun `file-contains-no-import`() {
        // given
        val sut = getSnippetFile("file-contains-no-import")
            .files
            .first()

        // then
        assertSoftly(sut) {
            imports.isEmpty() shouldBeEqualTo true
            numImports shouldBeEqualTo 0
            countImports { it.name == "com.lemonappdev.konsist.testdata.OtherImport" } shouldBeEqualTo 0
            hasImports() shouldBeEqualTo false
            hasImport { it.name == "com.lemonappdev.konsist.testdata.OtherImport" } shouldBeEqualTo false
            hasAllImports { it.hasNameStartingWith("com.lemonappdev.") } shouldBeEqualTo true
            hasImports("com.lemonappdev.konsist.testdata.OtherImport") shouldBeEqualTo false
        }
    }

    @Test
    fun `file-contains-imports`() {
        // given
        val sut = getSnippetFile("file-contains-imports")
            .files
            .first()

        // then
        sut
            .imports
            .map { it.name }
            .shouldBeEqualTo(
                listOf(
                    "com.lemonappdev.konsist.testdata.SampleAnnotation",
                    "com.lemonappdev.konsist.testdata.SampleType",
                ),
            )
    }

    @Test
    fun `file-has-imports`() {
        // given
        val sut = getSnippetFile("file-has-imports")
            .files
            .first()

        // then
        assertSoftly(sut) {
            numImports shouldBeEqualTo 2
            countImports { it.hasNameStartingWith("com.lemonappdev") } shouldBeEqualTo 2
            countImports { it.name == "com.lemonappdev.konsist.testdata.SampleType" } shouldBeEqualTo 1
            hasImports() shouldBeEqualTo true
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
