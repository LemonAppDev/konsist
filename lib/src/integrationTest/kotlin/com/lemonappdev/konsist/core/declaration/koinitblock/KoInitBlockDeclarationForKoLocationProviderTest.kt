package com.lemonappdev.konsist.core.declaration.koinitblock

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import com.lemonappdev.konsist.api.ext.list.initBlocks
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoInitBlockDeclarationForKoLocationProviderTest {
    @Test
    fun `init-block-location-with-single-digit`() {
        // given
        val sut =
            getSnippetFile("init-block-location-with-single-digit")
                .classes()
                .initBlocks
                .first()

        // then
        sut.location shouldBeEqualTo "${sut.path}:2:5"
    }

    @Test
    fun `init-block-location-with-text`() {
        // given
        val projectPath =
            getSnippetFile("init-block-location-with-text")
                .classes()
                .initBlocks
                .first()
                .projectPath

        val sut =
            getSnippetFile("init-block-location-with-text")
                .classes()
                .initBlocks
                .first()

        // then
        assertSoftly(sut.locationWithText) {
            startsWith("Location: /") shouldBeEqualTo true
            contains(projectPath) shouldBeEqualTo true
            contains("val sampleInitProperty = 6") shouldBeEqualTo true
        }
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/koinitblock/snippet/forkolocationprovider/", fileName)
}
