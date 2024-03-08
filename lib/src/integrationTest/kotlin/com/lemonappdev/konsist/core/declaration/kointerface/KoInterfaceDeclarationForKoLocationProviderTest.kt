package com.lemonappdev.konsist.core.declaration.kointerface

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoInterfaceDeclarationForKoLocationProviderTest {
    @Test
    fun `interface-location-with-single-digit`() {
        // given
        val sut =
            getSnippetFile("interface-location-with-single-digit")
                .interfaces()
                .first()

        // then
        sut.location shouldBeEqualTo "${sut.path}:1:1"
    }

    @Test
    fun `interface-location-with-double-digit`() {
        // given
        val sut =
            getSnippetFile("interface-location-with-double-digit")
                .interfaces(includeNested = true)
                .first()

        // then
        sut.location shouldBeEqualTo "${sut.path}:11:41"
    }

    @Test
    fun `interface-location-with-text`() {
        // given
        val projectPath =
            getSnippetFile("interface-location-with-text")
                .interfaces()
                .first()
                .projectPath

        val sut =
            getSnippetFile("interface-location-with-text")
                .interfaces()
                .first()

        // then
        val declaration = "Declaration:\ninterface SampleInterface {\n}"
        assertSoftly(sut.locationWithText) {
            startsWith("Location: /") shouldBeEqualTo true
            contains(projectPath) shouldBeEqualTo true
            endsWith(declaration) shouldBeEqualTo true
        }
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/kointerface/snippet/forkolocationprovider/", fileName)
}
