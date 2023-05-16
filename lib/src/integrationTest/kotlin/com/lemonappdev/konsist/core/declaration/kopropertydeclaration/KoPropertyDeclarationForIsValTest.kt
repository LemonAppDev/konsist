package com.lemonappdev.konsist.core.declaration.kopropertydeclaration

import com.lemonappdev.konsist.TestSnippetProvider
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoPropertyDeclarationForIsValTest {
    @Test
    fun `property-is-val`() {
        // given
        val sut = getSnippetFile("property-is-val")
            .properties()
            .first()

        // then
        sut.isVal shouldBeEqualTo true
    }

    @Test
    fun `property-is-not-val`() {
        // given
        val sut = getSnippetFile("property-is-not-val")
            .properties()
            .first()

        // then
        sut.isVal shouldBeEqualTo false
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("core/declaration/kopropertydeclaration/snippet/forisval/", fileName)
}
