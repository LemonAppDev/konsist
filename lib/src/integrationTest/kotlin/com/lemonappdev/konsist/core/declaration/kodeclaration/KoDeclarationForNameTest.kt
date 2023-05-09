package com.lemonappdev.konsist.core.declaration.kodeclaration

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoDeclarationForNameTest {
    @Test
    fun `primary-constructor`() {
        // given
        val sut = getSnippetFile("primary-constructor")
            .classes()
            .first()
            .primaryConstructor

        // then
        sut?.name shouldBeEqualTo "SampleClass"
    }

    @Test
    fun `secondary-constructor`() {
        // given
        val sut = getSnippetFile("secondary-constructor")
            .classes()
            .first()
            .secondaryConstructors
            .first()

        // then
        sut.name shouldBeEqualTo "SampleClass"
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/kodeclaration/snippet/forname/", fileName)
}
