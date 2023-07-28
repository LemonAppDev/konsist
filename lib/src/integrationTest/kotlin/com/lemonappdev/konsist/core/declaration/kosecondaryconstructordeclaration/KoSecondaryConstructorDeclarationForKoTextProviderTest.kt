package com.lemonappdev.konsist.core.declaration.kosecondaryconstructordeclaration

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoSecondaryConstructorDeclarationForKoTextProviderTest {
    @Test
    fun `secondary-constructor-text`() {
        // given
        val sut = getSnippetFile("secondary-constructor-text")
            .classes()
            .first()
            .secondaryConstructors
            .first()

        // then
        sut
            .text
            .shouldBeEqualTo("constructor(sampleProperty1: Int, sampleProperty2: Int) : this(sampleProperty1)")
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/kosecondaryconstructordeclaration/snippet/forkotextprovider/", fileName)
}
