package com.lemonappdev.konsist.core.declaration.kogetter

import com.lemonappdev.konsist.TestSnippetProvider
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoGetterDeclarationTest {
    @Test
    fun `getter-to-string`() {
        // given
        val sut =
            getSnippetFile("getter-to-string")
                .properties()
                .first()
                .getter

        // then
        assertSoftly(sut.toString()) {
            startsWith("Location: /") shouldBeEqualTo true
            contains("get() = \"\"") shouldBeEqualTo true
        }
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("core/declaration/kogetter/snippet/forgeneral/", fileName)
}
