package com.lemonappdev.konsist.core.declaration.kosetter

import com.lemonappdev.konsist.TestSnippetProvider
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoSetterDeclarationTest {
    @Test
    fun `setter-to-string`() {
        // given
        val sut =
            getSnippetFile("setter-to-string")
                .properties()
                .first()
                .setter

        // then
        assertSoftly(sut.toString()) {
            startsWith("Location: /") shouldBeEqualTo true
            contains("set(value) {") shouldBeEqualTo true
            contains("if (true) field = value") shouldBeEqualTo true
        }
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("core/declaration/kosetter/snippet/forgeneral/", fileName)
}
