package com.lemonappdev.konsist.core.declaration.koinitblock

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoInitBlockDeclarationForKoLocationProviderTest {
    @Test
    fun `init-block-location-with-single-digit`() {
        // given
        val sut = getSnippetFile("init-block-location-with-single-digit")
            .classes()
            .first()
            .initBlocks
            ?.first()

        // then
        sut?.location shouldBeEqualTo "${sut?.path}:2:5"
    }

    @Test
    fun `init-block-location-with-text`() {
        // given
        val projectPath = getSnippetFile("init-block-location-with-text")
            .classes()
            .first()
            .initBlocks
            ?.first()
            ?.projectPath

        val sut = getSnippetFile("init-block-location-with-text")
            .classes()
            .first()
            .initBlocks
            ?.first()

        // then
        assertSoftly(sut?.locationWithText) {
            (this?.startsWith("Location: /") ?: false) shouldBeEqualTo true
            (projectPath?.let { string -> this?.contains(string) } ?: false) shouldBeEqualTo true
            (this?.contains("val sampleInitProperty = 6") ?: false) shouldBeEqualTo true
        }
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/koinitblockdeclaration/snippet/forkolocationprovider/", fileName)
}
