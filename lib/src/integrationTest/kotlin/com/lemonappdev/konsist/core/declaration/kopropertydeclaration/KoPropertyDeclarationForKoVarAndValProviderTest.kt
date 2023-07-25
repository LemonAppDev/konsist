package com.lemonappdev.konsist.core.declaration.kopropertydeclaration

import com.lemonappdev.konsist.TestSnippetProvider
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoPropertyDeclarationForKoVarAndValProviderTest {
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
        TestSnippetProvider.getSnippetKoScope("core/declaration/kopropertydeclaration/snippet/forkovarandvalprovider/", fileName)
}
