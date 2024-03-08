package com.lemonappdev.konsist.core.declaration.koprimaryconstructor

import com.lemonappdev.konsist.TestSnippetProvider
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoPrimaryConstructorDeclarationTest {
    @Test
    fun `primary-constructor-to-string`() {
        // given
        val sut =
            getSnippetFile("primary-constructor-to-string")
                .classes()
                .first()
                .primaryConstructor

        // then
        val declaration = "Declaration:\n(val sampleParameter: Int)"
        assertSoftly(sut?.toString()) {
            it?.startsWith("Location: /") shouldBeEqualTo true
            it?.endsWith(declaration) shouldBeEqualTo true
        }
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("core/declaration/koprimaryconstructor/snippet/forgeneral/", fileName)
}
