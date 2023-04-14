package com.lemon.konsist.core.declaration.koscope

import com.lemon.konsist.TestSnippetProvider
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoScopeForPackageTest {

    @Test
    fun `file-contains-package`() {
        // given
        val sut = getSnippetFile("file-contains-package")

        // then
        sut
            .packages()
            .map { it.name }
            .toList()
            .shouldBeEqualTo(listOf("samplepackage"))
    }

    @Test
    fun `file-contains-no-package`() {
        // given
        val sut = getSnippetFile("file-contains-no-package")

        // then
        sut
            .packages()
            .toList()
            .shouldBeEqualTo(emptyList())
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("core/declaration/koscope/snippet/forpackage/", fileName)
}
