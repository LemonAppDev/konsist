package com.lemonappdev.konsist.core.declaration.kosecondaryconstructordeclaration

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoSecondaryConstructorDeclarationForKoLocationProviderTest {
    @Test
    fun `secondary-constructor-location-with-single-digit`() {
        // given
        val sut = getSnippetFile("secondary-constructor-location-with-single-digit")
            .classes()
            .first()
            .secondaryConstructors
            .first()

        // then
        sut.location shouldBeEqualTo "${sut.path}:2:5"
    }

    @Test
    fun `secondary-constructor-location-with-text`() {
        // given
        val projectPath = getSnippetFile("secondary-constructor-location-with-text")
            .classes()
            .first()
            .secondaryConstructors
            .first()
            .projectPath

        val sut = getSnippetFile("secondary-constructor-location-with-text")
            .classes()
            .first()
            .secondaryConstructors
            .first()

        // then
        val declaration = "Declaration:\nconstructor(sampleProperty1: Int, sampleProperty2: Int) : this(sampleProperty1)"
        assertSoftly(sut.locationWithText) {
            it.startsWith("Location: /") shouldBeEqualTo true
            projectPath.let { string -> it.contains(string) } shouldBeEqualTo true
            it.endsWith(declaration) shouldBeEqualTo true
        }
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/kosecondaryconstructordeclaration/snippet/forkolocationprovider/", fileName)
}
