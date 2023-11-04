package com.lemonappdev.konsist.core.declaration.kosetter

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoSetterDeclarationForKoLocationProviderTest {
    @Test
    fun `setter-location-with-single-digit`() {
        // given
        val sut =
            getSnippetFile("setter-location-with-single-digit")
                .properties()
                .first()
                .setter

        // then
        sut?.location shouldBeEqualTo "${sut?.path}:2:5"
    }

    @Test
    fun `setter-location-with-text`() {
        // given
        val projectPath =
            getSnippetFile("setter-location-with-text")
                .properties()
                .first()
                .setter
                ?.projectPath

        val sut =
            getSnippetFile("setter-location-with-text")
                .properties()
                .first()
                .setter

        // then
        assertSoftly(sut?.locationWithText) {
            it?.startsWith("Location: /") shouldBeEqualTo true
            projectPath?.let { path -> it?.contains(path) } shouldBeEqualTo true
            it?.contains("set(value) {") shouldBeEqualTo true
        }
    }

    private fun getSnippetFile(fileName: String) = getSnippetKoScope("core/declaration/kosetter/snippet/forkolocationprovider/", fileName)
}
