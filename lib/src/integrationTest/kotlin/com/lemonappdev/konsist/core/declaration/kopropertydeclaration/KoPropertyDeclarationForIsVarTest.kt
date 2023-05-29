package com.lemonappdev.konsist.core.declaration.kopropertydeclaration

import com.lemonappdev.konsist.TestSnippetProvider
import com.lemonappdev.konsist.core.ext.toNormalizedPath
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoPropertyDeclarationForIsVarTest {
    @Test
    fun `property-is-var`() {
        // given
        val sut = getSnippetFile("property-is-var")
            .properties()
            .first()

        // then
        sut.isVar shouldBeEqualTo true
    }

    @Test
    fun `property-is-not-var`() {
        // given
        val sut = getSnippetFile("property-is-not-var")
            .properties()
            .first()

        // then
        sut.isVar shouldBeEqualTo false
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("core/declaration/kopropertydeclaration/snippet/forisvar/".toNormalizedPath(), fileName)
}
