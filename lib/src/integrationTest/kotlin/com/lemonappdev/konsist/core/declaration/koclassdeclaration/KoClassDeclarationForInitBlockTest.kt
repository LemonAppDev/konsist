package com.lemonappdev.konsist.core.declaration.koclassdeclaration

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldHaveSize
import org.amshove.kluent.shouldNotBeEqualTo
import org.junit.jupiter.api.Test

class KoClassDeclarationForInitBlockTest {
    @Test
    fun `class-without-init-blocks`() {
        // given
        val sut = getSnippetFile("class-without-init-blocks")
            .classes()
            .first()

        // then
        assertSoftly(sut) {
            initBlocks shouldBeEqualTo null
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
            hasInitBlocks() shouldBeEqualTo true
        }
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/koclassdeclaration/snippet/forinitblock/", fileName)
}
