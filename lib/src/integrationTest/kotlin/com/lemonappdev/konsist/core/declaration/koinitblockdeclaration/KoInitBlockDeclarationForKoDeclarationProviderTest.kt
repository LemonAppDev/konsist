package com.lemonappdev.konsist.core.declaration.koinitblockdeclaration

import com.lemonappdev.konsist.TestSnippetProvider
import com.lemonappdev.konsist.api.provider.KoNameProvider
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoInitBlockDeclarationForKoDeclarationProviderTest {
    // ToDo: uzupelnij to
    @Test
    fun `init-block-has-no-declarations`() {
        // given
        val sut = getSnippetFile("init-block-has-no-declarations")
            .classes()
            .first()
            .initBlocks
            ?.first()

        // then
        assertSoftly {
            sut?.declarations()?.toList() shouldBeEqualTo emptyList()
            sut?.containsDeclarations("sampleProperty") shouldBeEqualTo false
            sut?.numDeclarations(includeNested = true) shouldBeEqualTo 0
        }
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
            ?.filterIsInstance<KoNameProvider>()
            ?.map { it.name }
            ?.shouldBeEqualTo(listOf("sampleProperty", "sampleFunction", "SampleClass"))
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("core/declaration/koinitblockdeclaration/snippet/forkodeclarationprovider/", fileName)
}
