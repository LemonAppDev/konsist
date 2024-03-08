package com.lemonappdev.konsist.core.declaration.kopackage

import com.lemonappdev.konsist.TestSnippetProvider
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoPackageDeclarationForKoContainingFileProviderTest {
    @Test
    fun `package-containing-file`() {
        // given
        val sut =
            getSnippetFile("package-containing-file")
                .packages
                .first()

        // then
        sut
            .containingFile
            .nameWithExtension
            .endsWith("file.kt")
            .shouldBeEqualTo(true)
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("core/declaration/kopackage/snippet/forkocontainingfileprovider/", fileName)
}
