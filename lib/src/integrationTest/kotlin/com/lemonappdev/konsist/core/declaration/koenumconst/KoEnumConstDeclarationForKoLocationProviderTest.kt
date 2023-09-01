package com.lemonappdev.konsist.core.declaration.koenumconst

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoEnumConstDeclarationForKoLocationProviderTest {
    @Test
    fun `enum-const-location-with-single-digit`() {
        // given
        val sut = getSnippetFile("enum-const-location-with-single-digit")
            .classes()
            .first()
            .constants
            .first()

        // then
        sut.location shouldBeEqualTo "${sut.path}:2:5"
    }

    @Test
    fun `enum-const-location-with-text`() {
        // given
        val projectPath = getSnippetFile("enum-const-location-with-text")
            .classes()
            .first()
            .constants
            .first()
            .projectPath

        val sut = getSnippetFile("enum-const-location-with-text")
            .classes()
            .first()
            .constants
            .first()

        // then
        val declaration = "Declaration:\nSAMPLE_CONSTANT_1"
        assertSoftly(sut.locationWithText) {
            startsWith("Location: /") shouldBeEqualTo true
            contains(projectPath) shouldBeEqualTo true
            endsWith(declaration) shouldBeEqualTo true
        }
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/koenumconst/snippet/forkolocationprovider/", fileName)
}
