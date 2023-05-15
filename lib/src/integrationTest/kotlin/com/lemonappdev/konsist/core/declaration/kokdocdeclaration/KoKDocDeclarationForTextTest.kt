package com.lemonappdev.konsist.core.declaration.kokdocdeclaration

import com.lemonappdev.konsist.TestSnippetProvider
import org.amshove.kluent.shouldContain
import org.junit.jupiter.api.Test

class KoKDocDeclarationForTextTest {
    @Test
    fun `class-with-tags`() {
        // given
        val sut = getSnippetFile("class-with-tags")
            .classes()
            .first()
            .kDoc

        // then
        sut?.text?.shouldContain("This is a sample class that demonstrates the usage of KDoc tags.")
    }

    @Test
    fun `function-with-tags`() {
        // given
        val sut = getSnippetFile("function-with-tags")
            .functions(includeNested = true)
            .first()
            .kDoc

        // then
        sut?.text?.shouldContain("This is a sample method that demonstrates the usage of KDoc tags.")
    }

    @Test
    fun `property-with-tags`() {
        // given
        val sut = getSnippetFile("property-with-tags")
            .properties(includeNested = true)
            .first()
            .kDoc

        // then
        sut?.text?.shouldContain("This is a sample property that demonstrates the usage of KDoc tags.")
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("core/declaration/kokdocdeclaration/snippet/fortext/", fileName)
}
