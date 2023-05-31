package com.lemonappdev.konsist.core.declaration.kopsideclaration

import com.lemonappdev.konsist.TestSnippetProvider
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoPsiDeclarationForResideInTest {
    @Test
    fun `reside-in-file-path`() {
        // given
        val sut = getSnippetFile("reside-in-file-path")
            .functions()
            .first()

        // then
        assertSoftly(sut) {
            resideInFilePath("..snippet..", true) shouldBeEqualTo true
            resideInFilePath("..kopsideclaration/snippet..", true) shouldBeEqualTo true
            resideInFilePath("..kopsideclaration..reside-in-file-path.kt", true) shouldBeEqualTo true
            resideInFilePath("kopsideclaration/snippet/", true) shouldBeEqualTo false
        }
    }

    @Test
    fun `reside-in-project-file-path`() {
        // given
        val sut = getSnippetFile("reside-in-project-file-path")
            .functions()
            .first()

        // then
        assertSoftly(sut) {
            resideInFilePath("..snippet..", false) shouldBeEqualTo true
            resideInFilePath("..kopsideclaration/snippet..", false) shouldBeEqualTo true
            resideInFilePath("..kopsideclaration..reside-in-project-file-path.kt", false) shouldBeEqualTo true
            resideInFilePath("kopsideclaration/snippet/", false) shouldBeEqualTo false
        }
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("core/declaration/kopsideclaration/snippet/forresidein/", fileName)
}
