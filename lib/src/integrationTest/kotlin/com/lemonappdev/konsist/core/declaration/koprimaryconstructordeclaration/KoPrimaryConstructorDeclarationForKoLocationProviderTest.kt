package com.lemonappdev.konsist.core.declaration.koprimaryconstructordeclaration

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoPrimaryConstructorDeclarationForKoLocationProviderTest {
    @Test
    fun `primary-constructor-location-with-single-digit`() {
        // given
        val sut = getSnippetFile("primary-constructor-location-with-single-digit")
            .classes()
            .first()
            .primaryConstructor

        // then
        sut?.location shouldBeEqualTo "${sut?.path}:3:18"
    }

    @Test
    fun `primary-constructor-location-with-text`() {
        // given
        val projectPath = getSnippetFile("primary-constructor-location-with-text")
            .classes()
            .first()
            .primaryConstructor
            ?.projectPath

        val sut = getSnippetFile("primary-constructor-location-with-text")
            .classes()
            .first()
            .primaryConstructor

        // then
        val declaration = "Declaration:\n(val sampleParameter: SampleType)"
        assertSoftly(sut?.locationWithText) {
            it?.startsWith("Location: /") shouldBeEqualTo true
            projectPath?.let { string -> it?.contains(string) } shouldBeEqualTo true
            it?.endsWith(declaration) shouldBeEqualTo true
        }
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/koprimaryconstructordeclaration/snippet/forkolocationprovider/", fileName)
}
