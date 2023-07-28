package com.lemonappdev.konsist.core.declaration.koclassdeclaration

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldNotBeEqualTo
import org.junit.jupiter.api.Test

class KoClassDeclarationForKoInitBlockProviderTest {
    @Test
    fun `class-without-init-blocks`() {
        // given
        val sut = getSnippetFile("class-without-init-blocks")
            .classes()
            .first()

        // then
        assertSoftly(sut) {
            initBlocks shouldBeEqualTo null
            numInitBlocks shouldBeEqualTo 0
            hasInitBlocks() shouldBeEqualTo false
        }
    }

    @Test
    fun `class-with-one-init-block`() {
        // given
        val sut = getSnippetFile("class-with-one-init-block")
            .classes()
            .first()

        // then
        assertSoftly(sut) {
            initBlocks shouldNotBeEqualTo null
            numInitBlocks shouldBeEqualTo 1
            hasInitBlocks() shouldBeEqualTo true
        }
    }

    @Test
    fun `class-with-two-init-blocks`() {
        // given
        val sut = getSnippetFile("class-with-two-init-blocks")
            .classes()
            .first()

        // then
        assertSoftly(sut) {
            initBlocks shouldNotBeEqualTo null
            numInitBlocks shouldBeEqualTo 2
            hasInitBlocks() shouldBeEqualTo true
        }
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/koclassdeclaration/snippet/forkoinitblockprovider/", fileName)
}
