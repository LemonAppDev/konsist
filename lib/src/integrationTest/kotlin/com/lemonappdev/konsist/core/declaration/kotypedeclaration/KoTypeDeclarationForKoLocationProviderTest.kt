package com.lemonappdev.konsist.core.declaration.kotypedeclaration

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoTypeDeclarationForKoLocationProviderTest {
    @Test
    fun `type-location-with-single-digit`() {
        // given
        val sut = getSnippetFile("type-location-with-single-digit")
            .properties()
            .first()
            .explicitType

        // then
        sut?.location shouldBeEqualTo "${sut?.path}:1:21"
    }

    @Test
    fun `type-location-with-double-digit`() {
        // given
        val sut = getSnippetFile("type-location-with-double-digit")
            .properties(includeNested = true)
            .first()
            .explicitType

        // then
        sut?.location shouldBeEqualTo "${sut?.path}:10:57"
    }

    @Test
    fun `type-location-with-text`() {
        // given
        val projectPath = getSnippetFile("type-location-with-text")
            .properties()
            .first()
            .explicitType
            ?.projectPath

        val sut = getSnippetFile("type-location-with-text")
            .properties()
            .first()
            .explicitType

        // then
        val declaration = "Declaration:\nString"
        assertSoftly(sut?.locationWithText) {
            it?.startsWith("Location: /") shouldBeEqualTo true
            projectPath?.let { string -> it?.contains(string) } shouldBeEqualTo true
            it?.endsWith(declaration) shouldBeEqualTo true
        }
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/kotypedeclaration/snippet/forkolocationprovider/", fileName)
}
