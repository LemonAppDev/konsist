package com.lemonappdev.konsist.core.declaration.koclass

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldNotBeEqualTo
import org.junit.jupiter.api.Test

class KoClassDeclarationForKoInitBlockProviderTest {
    @Test
    fun `class-without-init-blocks`() {
        // given
        val sut =
            getSnippetFile("class-without-init-blocks")
                .classes()
                .first()

        // then
        assertSoftly(sut) {
            initBlocks shouldBeEqualTo emptyList()
            numInitBlocks shouldBeEqualTo 0
            countInitBlocks { it.localFunctions.isEmpty() } shouldBeEqualTo 0
            hasInitBlocks() shouldBeEqualTo false
            hasInitBlock { it.localFunctions.isNotEmpty() } shouldBeEqualTo false
            hasAllInitBlocks { it.localFunctions.isNotEmpty() } shouldBeEqualTo true
        }
    }

    @Test
    fun `class-with-one-init-block`() {
        // given
        val sut =
            getSnippetFile("class-with-one-init-block")
                .classes()
                .first()

        // then
        assertSoftly(sut) {
            initBlocks shouldNotBeEqualTo emptyList()
            numInitBlocks shouldBeEqualTo 1
            countInitBlocks { it.localFunctions.isEmpty() } shouldBeEqualTo 1
            hasInitBlocks() shouldBeEqualTo true
            hasInitBlock { it.localDeclarations.isNotEmpty() } shouldBeEqualTo true
            hasInitBlock { it.localFunctions.isNotEmpty() } shouldBeEqualTo false
            hasAllInitBlocks { it.localDeclarations.isNotEmpty() } shouldBeEqualTo true
            hasAllInitBlocks { it.localFunctions.isNotEmpty() } shouldBeEqualTo false
        }
    }

    @Test
    fun `class-with-two-init-blocks`() {
        // given
        val sut =
            getSnippetFile("class-with-two-init-blocks")
                .classes()
                .first()

        // then
        assertSoftly(sut) {
            initBlocks shouldNotBeEqualTo emptyList()
            numInitBlocks shouldBeEqualTo 2
            countInitBlocks { it.localDeclarations.isNotEmpty() } shouldBeEqualTo 2
            countInitBlocks { it.localFunctions.isNotEmpty() } shouldBeEqualTo 1
            hasInitBlocks() shouldBeEqualTo true
            hasInitBlock { it.localFunctions.isNotEmpty() } shouldBeEqualTo true
            hasInitBlock { it.localClasses.isNotEmpty() } shouldBeEqualTo false
            hasAllInitBlocks { it.localDeclarations.isNotEmpty() } shouldBeEqualTo true
            hasAllInitBlocks { it.localFunctions.isNotEmpty() } shouldBeEqualTo false
        }
    }

    private fun getSnippetFile(fileName: String) = getSnippetKoScope("core/declaration/koclass/snippet/forkoinitblockprovider/", fileName)
}
