package com.lemonappdev.konsist.core.declaration.kopackagedeclaration

import com.lemonappdev.konsist.TestSnippetProvider
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoPackageDeclarationForMatchingFilePath {
    @Test
    fun `package-with-matching-file-path`() {
        // given
        val sut = getSnippetFile("package-with-matching-file-path")
            .packages()
            .first()

        // then
        sut.hasMatchingFilePath shouldBeEqualTo true
    }

    @Test
    fun `package-without-matching-file-path`() {
        // given
        val sut = getSnippetFile("package-without-matching-file-path")
            .packages()
            .first()

        // then
        sut.hasMatchingFilePath shouldBeEqualTo false
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("core/declaration/kopackagedeclaration/snippet/formatchingfilepath/", fileName)
}
