package com.lemonappdev.konsist.core.declaration.koparentdeclaration

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoParentDeclarationForKoLocationProviderTest {
    @Test
    fun `parent-location`() {
        // given
        val sut = getSnippetFile("parent-location")
            .classes()
            .first()
            .parents
            .first()

        // then
        sut.location shouldBeEqualTo "${sut.path}:3:23"
    }

    @Test
    fun `parent-location-with-text`() {
        // given
        val projectPath = getSnippetFile("parent-location-with-text")
            .classes()
            .first()
            .parents
            .first()
            .projectPath

        val sut = getSnippetFile("parent-location-with-text")
            .classes()
            .first()
            .parents
            .first()

        // then
        val declaration = "Declaration:\nSampleSuperInterface"
        assertSoftly(sut.locationWithText) {
            startsWith("Location: /") shouldBeEqualTo true
            contains(projectPath) shouldBeEqualTo true
            endsWith(declaration) shouldBeEqualTo true
        }
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/koparentdeclaration/snippet/forkolocationprovider/", fileName)
}
