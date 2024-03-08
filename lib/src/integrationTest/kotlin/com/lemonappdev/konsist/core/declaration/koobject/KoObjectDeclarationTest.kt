package com.lemonappdev.konsist.core.declaration.koobject

import com.lemonappdev.konsist.TestSnippetProvider
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoObjectDeclarationTest {
    @Test
    fun `object-to-string`() {
        // given
        val sut =
            getSnippetFile("object-to-string")
                .objects()
                .first()

        // then
        sut.toString() shouldBeEqualTo "SampleObject"
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("core/declaration/koobject/snippet/forgeneral/", fileName)
}
