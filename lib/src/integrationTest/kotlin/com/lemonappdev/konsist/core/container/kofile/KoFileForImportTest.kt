package com.lemonappdev.konsist.core.container.kofile

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoFileForImportTest {
    @Test
    fun `file-contains-no-import`() {
        // given
        val sut = getSnippetFile("file-contains-no-import")
            .files()
            .first()

        // then
        assertSoftly(sut) {
            imports.toList().isEmpty() shouldBeEqualTo true
            hasImports() shouldBeEqualTo false
            hasImports("com.lemonappdev.konsist.testdata.OtherImport") shouldBeEqualTo false
        }
    }

    @Test
    fun `file-contains-imports`() {
        // given
        val sut = getSnippetFile("file-contains-imports")
            .files()
            .first()

        // then
        sut
            .imports
            .map { it.name }
            .toList()
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
            .files()
            .first()

        // then
        assertSoftly(sut) {
            hasImports() shouldBeEqualTo true
            hasImports("com..") shouldBeEqualTo true
            hasImports("com..", "..testdata..") shouldBeEqualTo true
            hasImports("com") shouldBeEqualTo false
            hasImports("com", "..testdata..") shouldBeEqualTo false
            hasImports("com.lemonappdev.konsist.testdata.SampleType") shouldBeEqualTo true
            hasImports("com.lemonappdev.konsist.testdata.OtherImport") shouldBeEqualTo false
        }
    }

    private fun getSnippetFile(fileName: String) = getSnippetKoScope("core/container/kofile/snippet/forimport/", fileName)
}
