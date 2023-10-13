package com.lemonappdev.konsist.core.declaration.kovariable

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import com.lemonappdev.konsist.api.ext.list.variables
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoVariableDeclarationForKoLocationProviderTest {
    @Test
    fun `variable-location-with-single-digit`() {
        // given
        val sut = getSnippetFile("variable-location-with-single-digit")
            .functions()
            .variables
            .first()

        // then
        sut.location shouldBeEqualTo "${sut.path}:2:5"
    }

    @Test
    fun `variable-location-with-text`() {
        // given
        val projectPath = getSnippetFile("variable-location-with-text")
            .functions()
            .variables
            .first()
            .projectPath

        val sut = getSnippetFile("variable-location-with-text")
            .functions()
            .variables
            .first()

        // then
        val declaration = "Declaration:\nval sampleProperty = \"\""
        assertSoftly(sut.locationWithText) {
            startsWith("Location: /") shouldBeEqualTo true
            contains(projectPath) shouldBeEqualTo true
            endsWith(declaration) shouldBeEqualTo true
        }
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/kovariable/snippet/forkolocationprovider/", fileName)
}
