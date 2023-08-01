package com.lemonappdev.konsist.core.declaration.koobjectdeclaration

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldNotBeEqualTo
import org.junit.jupiter.api.Test

class KoObjectDeclarationForKoInitBlockProviderTest {
    @Test
    fun `object-without-init-blocks`() {
        // given
        val sut = getSnippetFile("object-without-init-blocks")
            .objects()
            .first()

        // then
        assertSoftly(sut) {
            initBlocks shouldBeEqualTo emptyList()
            numInitBlocks shouldBeEqualTo 0
            hasInitBlocks shouldBeEqualTo false
        }
    }

    @Test
    fun `object-with-one-init-block`() {
        // given
        val sut = getSnippetFile("object-with-one-init-block")
            .objects()
            .first()

        // then
        assertSoftly(sut) {
            initBlocks shouldNotBeEqualTo emptyList()
            numInitBlocks shouldBeEqualTo 1
            hasInitBlocks shouldBeEqualTo true
        }
    }

    @Test
    fun `object-with-two-init-blocks`() {
        // given
        val sut = getSnippetFile("object-with-two-init-blocks")
            .objects()
            .first()

        // then
        assertSoftly(sut) {
            initBlocks shouldNotBeEqualTo emptyList()
            numInitBlocks shouldBeEqualTo 2
            hasInitBlocks shouldBeEqualTo true
        }
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/koobjectdeclaration/snippet/forkoinitblockprovider/", fileName)
}
