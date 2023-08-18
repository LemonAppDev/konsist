package com.lemonappdev.konsist.core.declaration.koinitblock

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoInitBlockDeclarationForKoLocalClassProviderTest {
    @Test
    fun `init-block-contains-no-local-classes`() {
        // given
        val sut = getSnippetFile("init-block-contains-no-local-classes")
            .classes()
            .first()
            .initBlocks
            .first()

        // then
        assertSoftly(sut) {
            numLocalClasses shouldBeEqualTo 0
            containsLocalClass("SampleLocalClass") shouldBeEqualTo false
            localClasses
                .shouldBeEqualTo(emptyList())
        }
    }

    @Test
    fun `init-block-contains-local-class`() {
        // given
        val sut = getSnippetFile("init-block-contains-local-class")
            .classes()
            .first()
            .initBlocks
            .first()

        // then
        assertSoftly(sut) {
            numLocalClasses shouldBeEqualTo 1
            containsLocalClass("SampleLocalClass") shouldBeEqualTo true
            localClasses
                .map { it.name }
                .shouldBeEqualTo(listOf("SampleLocalClass"))
        }
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/koinitblock/snippet/forkolocalclassprovider/", fileName)
}
