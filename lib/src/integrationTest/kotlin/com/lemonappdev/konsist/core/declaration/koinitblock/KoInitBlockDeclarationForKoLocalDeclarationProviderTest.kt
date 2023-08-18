package com.lemonappdev.konsist.core.declaration.koinitblock

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import com.lemonappdev.konsist.api.provider.KoNameProvider
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoInitBlockDeclarationForKoLocalDeclarationProviderTest {
    @Test
    fun `init-block-contains-no-local-declarations`() {
        // given
        val sut = getSnippetFile("init-block-contains-no-local-declarations")
            .classes()
            .first()
            .initBlocks
            .first()

        // then
        assertSoftly(sut) {
            numLocalDeclarations shouldBeEqualTo 0
            containsLocalDeclaration("sampleLocalProperty") shouldBeEqualTo false
            localDeclarations
                .filterIsInstance<KoNameProvider>()
                .shouldBeEqualTo(emptyList())
        }
    }

    @Test
    fun `init-block-contains-local-declarations`() {
        // given
        val sut = getSnippetFile("init-block-contains-local-declarations")
            .classes()
            .first()
            .initBlocks
            .first()

        // then
        assertSoftly(sut) {
            numLocalDeclarations shouldBeEqualTo 3
            containsLocalDeclaration("sampleLocalProperty") shouldBeEqualTo true
            containsLocalDeclaration("sampleLocalFunction") shouldBeEqualTo true
            containsLocalDeclaration("SampleLocalClass") shouldBeEqualTo true
            containsLocalDeclaration("sampleOtherDeclaration") shouldBeEqualTo false
            localDeclarations
                .filterIsInstance<KoNameProvider>()
                .map { it.name }
                .shouldBeEqualTo(
                    listOf(
                        "sampleLocalProperty",
                        "sampleLocalFunction",
                        "SampleLocalClass",
                    ),
                )
        }
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/koinitblock/snippet/forkolocaldeclarationprovider/", fileName)
}
