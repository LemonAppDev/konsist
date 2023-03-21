package com.lemon.konsist.core.kobasedeclaration

import com.lemon.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.shouldBe
import org.junit.jupiter.api.Test

class KoBaseDeclarationTest {
    @Test
    fun `file-path`() {
        // given
        val sut = getSut("file-path")

        // then
        sut.functions().first().filePath.endsWith("kobasedeclaration/snippet/file-path.kt") shouldBe true
    }

    @Test
    fun `text-with-location`() {
        // given
        val sut = getSut("text-with-location")

        // then
        val location = "'fun sampleFunction() {\n}' at (1,1) in //"
        sut.functions().first().textWithLocation.startsWith(location) shouldBe true
    }

    @Test
    fun `containing-file`() {
        // given
        val sut = getSut("containing-file")

        // then
        sut.files().first().containingFile.name.endsWith("file.kt") shouldBe true
    }

    private fun getSut(fileName: String) = getSnippetKoScope("core/kobasedeclaration/snippet/$fileName.kt.txt")
}
