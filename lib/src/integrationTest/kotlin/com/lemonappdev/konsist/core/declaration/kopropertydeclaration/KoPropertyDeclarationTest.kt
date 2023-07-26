package com.lemonappdev.konsist.core.declaration.kopropertydeclaration

import com.lemonappdev.konsist.TestSnippetProvider
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoPropertyDeclarationTest {
    @Test
    fun `property-to-string`() {
        // given
        val sut = getSnippetFile("property-to-string")
            .properties()
            .first()

        // then
        sut.toString() shouldBeEqualTo sut.locationWithText
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("core/declaration/kopropertydeclaration/snippet/forgeneral/", fileName)
}
