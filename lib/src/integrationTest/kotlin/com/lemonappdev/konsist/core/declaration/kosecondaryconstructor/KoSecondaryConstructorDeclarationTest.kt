package com.lemonappdev.konsist.core.declaration.kosecondaryconstructor

import com.lemonappdev.konsist.TestSnippetProvider
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoSecondaryConstructorDeclarationTest {
    @Test
    fun `secondary-constructor-to-string`() {
        // given
        val sut = getSnippetFile("secondary-constructor-to-string")
            .classes()
            .first()
            .secondaryConstructors
            .first()

        // then
        sut.toString() shouldBeEqualTo sut.locationWithText
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("core/declaration/kosecondaryconstructordeclaration/snippet/forgeneral/", fileName)
}
