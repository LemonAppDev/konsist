package com.lemonappdev.konsist.core.declaration.koobject

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldNotBeEqualTo
import org.junit.jupiter.api.Test

class KoObjectDeclarationForKoInitBlockProviderTest {
    @Test
    fun `object-without-init-blocks`() {
        // given
        val sut =
            getSnippetFile("object-without-init-blocks")
                .objects()
                .first()

        // then
        assertSoftly(sut) {
            initBlocks shouldBeEqualTo emptyList()
            numInitBlocks shouldBeEqualTo 0
            countInitBlocks { it.localFunctions.isEmpty() } shouldBeEqualTo 0
            hasInitBlocks() shouldBeEqualTo false
            hasInitBlock { it.localFunctions.isNotEmpty() } shouldBeEqualTo false
            hasAllInitBlocks { it.localFunctions.isNotEmpty() } shouldBeEqualTo true
            hasInitBlocks shouldBeEqualTo false
        }
    }

    @Test
    fun `object-with-one-init-block`() {
        // given
        val sut =
            getSnippetFile("object-with-one-init-block")
                .objects()
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
            hasInitBlocks shouldBeEqualTo true
        }
    }

    @Test
    fun `object-with-two-init-blocks`() {
        // given
        val sut =
            getSnippetFile("object-with-two-init-blocks")
                .objects()
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
            hasInitBlocks shouldBeEqualTo true
        }
    }

    private fun getSnippetFile(fileName: String) = getSnippetKoScope("core/declaration/koobject/snippet/forkoinitblockprovider/", fileName)
}
