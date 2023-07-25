package com.lemonappdev.konsist.core.declaration.koprimaryconstructordeclaration

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoPrimaryConstructorDeclarationForKoTextProviderTest {
    @Test
    fun `primary-constructor-text`() {
        // given
        val sut = getSnippetFile("primary-constructor-text")
            .classes()
            .first()
            .primaryConstructor

        // then
        sut
            ?.text
            .shouldBeEqualTo("(val sampleParameter: Int)")
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/koprimaryconstructordeclaration/snippet/forkotextprovider/", fileName)
}
