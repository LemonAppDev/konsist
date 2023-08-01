package com.lemonappdev.konsist.core.container

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoScopeForKoImportProviderTest {
    @Test
    fun `scope-contains-no-import`() {
        // given
        val sut = getSnippetFile("scope-contains-no-import")

        // then
        assertSoftly(sut) {
            imports.toList().isEmpty() shouldBeEqualTo true
            hasImports() shouldBeEqualTo false
            hasImports("com.lemonappdev.konsist.testdata.OtherImport") shouldBeEqualTo false
        }
    }

    @Test
    fun `scope-contains-imports`() {
        // given
        val sut = getSnippetFile("scope-contains-imports")

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
    fun `scope-has-imports`() {
        // given
        val sut = getSnippetFile("scope-has-imports")

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

    private fun getSnippetFile(fileName: String) = getSnippetKoScope("core/container/snippet/forkoimportprovider/", fileName)
}
