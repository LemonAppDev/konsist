package com.lemonappdev.konsist.core.declaration.kovaluedkdoctag

import com.lemonappdev.konsist.TestSnippetProvider
import com.lemonappdev.konsist.api.ext.list.provider.kDocs
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoValuedKDocTagDeclarationTest {
    @Test
    fun `kdoc-valued-tag-to-string`() {
        // given
        val sut =
            getSnippetFile("kdoc-valued-tag-to-string")
                .functions()
                .kDocs
                .first()
                .paramTags
                .first()

        // then
        sut.toString() shouldBeEqualTo "@param sampleParameter sample text"
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("core/declaration/kovaluedkdoctag/snippet/forgeneral/", fileName)
}
