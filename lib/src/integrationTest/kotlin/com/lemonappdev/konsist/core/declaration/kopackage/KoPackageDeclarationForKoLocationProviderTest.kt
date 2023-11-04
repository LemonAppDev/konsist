package com.lemonappdev.konsist.core.declaration.kopackage

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoPackageDeclarationForKoLocationProviderTest {
    @Test
    fun `package-location-with-single-digit`() {
        // given
        val sut =
            getSnippetFile("package-location-with-single-digit")
                .packages
                .first()

        // then
        sut.location shouldBeEqualTo "${sut.path}:1:1"
    }

    @Test
    fun `package-location-with-text`() {
        // given
        val projectPath =
            getSnippetFile("package-location-with-text")
                .packages
                .first()
                .projectPath

        val sut =
            getSnippetFile("package-location-with-text")
                .packages
                .first()

        // then
        val declaration = "Declaration:\npackage com.samplepackage"
        assertSoftly(sut.locationWithText) {
            startsWith("Location: /") shouldBeEqualTo true
            contains(projectPath) shouldBeEqualTo true
            endsWith(declaration) shouldBeEqualTo true
        }
    }

    private fun getSnippetFile(fileName: String) = getSnippetKoScope("core/declaration/kopackage/snippet/forkolocationprovider/", fileName)
}
