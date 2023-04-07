package com.lemon.konsist.core.declaration.kobasedeclaration

import com.lemon.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoBaseDeclarationTest {
    @Test
    fun `file-path`() {
        // given
        val sut = getSut("file-path")
            .functions()
            .first()

        // then
        sut.filePath.startsWith("//") shouldBeEqualTo false
        sut.filePath.endsWith("kobasedeclaration/snippet/file-path.kt") shouldBeEqualTo true
    }

    @Test
    fun `text-with-location`() {
        // given
        val sut = getSut("text-with-location")
            .functions()
            .first()

        // then
        val location = "'fun sampleFunction() {\n}' at (1,1) in //"
        sut.textWithLocation.startsWith(location) shouldBeEqualTo true
    }

    @Test
    fun `containing-file`() {
        // given
        val sut = getSut("containing-file")
            .files()
            .first()

        // then
        sut.containingFile.name.endsWith("file.kt") shouldBeEqualTo true
    }

    @Test
    fun `location`() {
        // given
        val sut = getSut("location")
            .functions()
            .first()

        // then
        sut.location shouldBeEqualTo "${sut.filePath}:3:1"
    }

    @Test
    fun `text`() {
        // given
        val sut = getSut("text")
            .functions()
            .first()

        // then
        sut.text shouldBeEqualTo "fun sampleFunction()"
    }

    @Test
    fun `to-string`() {
        // given
        val sut = getSut("to-string")
            .functions()
            .first()

        // then
        sut.toString() shouldBeEqualTo sut.textWithLocation
    }

    private fun getSut(fileName: String) = getSnippetKoScope("kobasedeclaration/snippet/", fileName)
}
