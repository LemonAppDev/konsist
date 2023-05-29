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
            resideInFilePath("..snippet..") shouldBeEqualTo true
            resideInFilePath("..kopsideclaration/snippet..") shouldBeEqualTo true
            resideInFilePath("..kopsideclaration..reside-in-file-path.kt") shouldBeEqualTo true
            resideInFilePath("kopsideclaration/snippet/") shouldBeEqualTo false
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
            resideInProjectFilePath("..snippet..") shouldBeEqualTo true
            resideInProjectFilePath("..kopsideclaration/snippet..") shouldBeEqualTo true
            resideInProjectFilePath("..kopsideclaration..reside-in-project-file-path.kt") shouldBeEqualTo true
            resideInProjectFilePath("kopsideclaration/snippet/") shouldBeEqualTo false
        }
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("core/declaration/kopsideclaration/snippet/forresidein/", fileName)
}
