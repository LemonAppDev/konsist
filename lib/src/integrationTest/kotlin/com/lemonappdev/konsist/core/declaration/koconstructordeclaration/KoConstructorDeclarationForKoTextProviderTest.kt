package com.lemonappdev.konsist.core.declaration.koconstructordeclaration

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoConstructorDeclarationForKoTextProviderTest {
    @Test
    fun `constructor-text`() {
        // given
        val sut = getSnippetFile("constructor-text")
            .classes()
            .first()
            .constructors
            .first()

        // then
        sut
            .text
            .shouldBeEqualTo("(val sampleParameter: Int)")
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/koconstructordeclaration/snippet/forkotextprovider/", fileName)
}
