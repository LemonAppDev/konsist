package com.lemonappdev.konsist.core.declaration.kotypealiasdeclaration

import com.lemonappdev.konsist.TestSnippetProvider
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoTypeAliasDeclarationForNoTypeAliasTest {
    @Test
    fun `no-typealias`() {
        // given
        val sut = getSnippetFile("no-typealias")
            .files()
            .first()
            .typeAliases

        // then
        sut.isEmpty() shouldBeEqualTo true
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("core/declaration/kotypealiasdeclaration/snippet/fornotypealias/", fileName)
}
