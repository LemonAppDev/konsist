package com.lemonappdev.konsist.core.declaration.kofiledeclaration

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoFileForNameTest {
    @Test
    fun `file-name`() {
        // given
        val sut = getSnippetFile("file-name")
            .files()
            .first()

        // then
        sut.name shouldBeEqualTo "file-name.kt"
    }

    private fun getSnippetFile(fileName: String) = getSnippetKoScope("core/declaration/kofiledeclaration/snippet/forname/", fileName)
}
