package com.lemonappdev.konsist.core.declaration.kotypealiasdeclaration

import com.lemonappdev.konsist.TestSnippetProvider
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoTypeAliasDeclarationForTypeTest {
    @Test
    fun `typealias`() {
        // given
        val sut = getSnippetFile("typealias")
            .files
            .first()
            .typeAliases
            .first()

        // then
        sut
            .type
            .sourceType
            .shouldBeEqualTo("() -> Int")
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("core/declaration/kotypealiasdeclaration/snippet/fortype/", fileName)
}
