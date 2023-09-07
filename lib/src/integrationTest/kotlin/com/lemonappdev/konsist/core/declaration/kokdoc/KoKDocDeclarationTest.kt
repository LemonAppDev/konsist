package com.lemonappdev.konsist.core.declaration.kokdoc

import com.lemonappdev.konsist.TestSnippetProvider
import com.lemonappdev.konsist.api.ext.list.kDocs
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoKDocDeclarationTest {
    @Test
    fun `kdoc-to-string`() {
        // given
        val sut = getSnippetFile("kdoc-to-string")
            .interfaces()
            .kDocs
            .first()

        // then
        sut.toString() shouldBeEqualTo "This is a sample kdoc."
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("core/declaration/kokdoc/snippet/forgeneral/", fileName)
}
