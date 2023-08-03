package com.lemonappdev.konsist.core.declaration.kotype

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoTypeDeclarationForKoTextProviderTest {
    @Test
    fun `type-text`() {
        // given
        val sut = getSnippetFile("type-text")
            .properties()
            .first()
            .explicitType

        // then
        sut
            ?.text
            .shouldBeEqualTo("String")
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/kotypedeclaration/snippet/forkotextprovider/", fileName)
}
