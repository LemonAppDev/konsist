package com.lemonappdev.konsist.core.declaration.kokdoctag

import com.lemonappdev.konsist.TestSnippetProvider
import com.lemonappdev.konsist.api.ext.list.kDocs
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoKDocTagDeclarationTest {
    @Test
    fun `kdoc-tag-to-string`() {
        // given
        val sut =
            getSnippetFile("kdoc-tag-to-string")
                .functions()
                .kDocs
                .first()
                .tags
                .first()

        // then
        sut.toString() shouldBeEqualTo "@return sample text"
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("core/declaration/kokdoctag/snippet/forgeneral/", fileName)
}
