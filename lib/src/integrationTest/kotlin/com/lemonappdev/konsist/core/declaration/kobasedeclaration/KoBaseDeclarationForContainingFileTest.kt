package com.lemonappdev.konsist.core.declaration.kobasedeclaration

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import com.lemonappdev.konsist.core.ext.toNormalizedPath
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoBaseDeclarationForContainingFileTest {
    @Test
    fun `containing-file`() {
        // given
        val sut = getSnippetFile("containing-file")
            .declarations()
            .first()

        // then
        sut
            .containingFile
            .name
            .endsWith("file")
            .shouldBeEqualTo(true)
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/kobasedeclaration/snippet/forcontainingfile/".toNormalizedPath(), fileName)
}
