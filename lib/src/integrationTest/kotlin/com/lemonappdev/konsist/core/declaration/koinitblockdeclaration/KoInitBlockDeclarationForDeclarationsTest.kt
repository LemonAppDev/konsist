package com.lemonappdev.konsist.core.declaration.koinitblockdeclaration

import com.lemonappdev.konsist.TestSnippetProvider
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoInitBlockDeclarationForDeclarationsTest {
    @Test
    fun `init-block-has-no-declarations`() {
        // given
        val sut = getSnippetFile("init-block-has-no-declarations")
            .classes()
            .first()
            .initBlocks
            ?.first()

        // then
        sut?.declarations() shouldBeEqualTo emptySequence()
    }

    @Test
    fun `init-block-has-all-declarations`() {
        // given
        val sut = getSnippetFile("init-block-has-all-declarations")
            .classes()
            .first()
            .initBlocks
            ?.first()

        // then
        sut
            ?.declarations()
            ?.toList()
            ?.map { it.name }
            ?.shouldBeEqualTo(listOf("sampleProperty", "sampleFunction", "SampleClass"))
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("core/declaration/koinitblockdeclaration/snippet/fordeclarations/", fileName)

}
