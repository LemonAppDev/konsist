package com.lemonappdev.konsist.core.container.kofile

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoFileDeclarationForKoFileExtensionProviderTest {
    @Test
    fun `file-extension`() {
        // given
        val sut = getSnippetFile("file-extension")
            .files
            .first()

        // then
        // The snippet file has '.kttxt' extension, but in name we have '.kt' because KotlinFileParser replace the first one into second one
        sut.extension shouldBeEqualTo "kt"
    }

    @Test
    fun `file-name-with-extension`() {
        // given
        val sut = getSnippetFile("file-name-with-extension")
            .files
            .first()

        // then
        // The snippet file has '.kttxt' extension, but in name we have '.kt' because KotlinFileParser replace the first one into second one
        sut.nameWithExtension shouldBeEqualTo "file-name-with-extension.kt"
    }

    @Test
    fun `file-has-extension`() {
        // given
        val sut = getSnippetFile("file-has-extension")
            .files
            .first()

        // then
        assertSoftly(sut) {
            hasExtension("kt") shouldBeEqualTo true
            hasExtension("java") shouldBeEqualTo false
        }
    }

    private fun getSnippetFile(fileName: String) = getSnippetKoScope("core/declaration/kofiledeclaration/snippet/forkofileextensionprovider/", fileName)
}
