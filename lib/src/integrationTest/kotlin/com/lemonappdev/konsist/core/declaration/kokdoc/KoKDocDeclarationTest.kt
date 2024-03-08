package com.lemonappdev.konsist.core.declaration.kokdoc

import com.lemonappdev.konsist.TestSnippetProvider
import com.lemonappdev.konsist.api.ext.list.kDocs
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoKDocDeclarationTest {
    @Test
    fun `kdoc-to-string`() {
        // given
        val sut =
            getSnippetFile("kdoc-to-string")
                .interfaces()
                .kDocs
                .first()

        // then
        val declaration = "Declaration:\nThis is a sample kdoc."
        assertSoftly(sut.toString()) {
            startsWith("Location: /") shouldBeEqualTo true
            endsWith(declaration) shouldBeEqualTo true
        }
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("core/declaration/kokdoc/snippet/forgeneral/", fileName)
}
