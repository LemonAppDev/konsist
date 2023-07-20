package com.lemonappdev.konsist.core.declaration.kobasedeclaration

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import com.lemonappdev.konsist.api.provider.KoContainingFileProvider
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoBaseDeclarationForContainingFileTest {
    @Test
    fun `containing-file`() {
        // given
        val sut = getSnippetFile("containing-file")
            .declarations()
            .filterIsInstance<KoContainingFileProvider>()
            .first()

        // then
        sut
            .containingFile
            .nameWithExtension
            .endsWith("file.kt")
            .shouldBeEqualTo(true)
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/kobasedeclaration/snippet/forcontainingfile/", fileName)
}
