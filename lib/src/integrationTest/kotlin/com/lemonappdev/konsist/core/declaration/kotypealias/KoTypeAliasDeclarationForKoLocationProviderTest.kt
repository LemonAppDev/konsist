package com.lemonappdev.konsist.core.declaration.kotypealias

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoTypeAliasDeclarationForKoLocationProviderTest {
    @Test
    fun `typealias-location-with-single-digit`() {
        // given
        val sut =
            getSnippetFile("typealias-location-with-single-digit")
                .typeAliases
                .first()

        // then
        sut.location shouldBeEqualTo "${sut.path}:1:1"
    }

    @Test
    fun `typealias-location-with-text`() {
        // given
        val projectPath =
            getSnippetFile("typealias-location-with-text")
                .typeAliases
                .first()
                .projectPath

        val sut =
            getSnippetFile("typealias-location-with-text")
                .typeAliases
                .first()

        // then
        val declaration = "Declaration:\ntypealias SampleTypeAlias = () -> Int"
        assertSoftly(sut.locationWithText) {
            startsWith("Location: /") shouldBeEqualTo true
            contains(projectPath) shouldBeEqualTo true
            endsWith(declaration) shouldBeEqualTo true
        }
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/kotypealias/snippet/forkolocationprovider/", fileName)
}
