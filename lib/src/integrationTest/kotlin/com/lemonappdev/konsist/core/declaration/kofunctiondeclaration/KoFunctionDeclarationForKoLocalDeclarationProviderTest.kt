package com.lemonappdev.konsist.core.declaration.kofunctiondeclaration

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import com.lemonappdev.konsist.api.provider.KoNameProvider
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoFunctionDeclarationForKoLocalDeclarationProviderTest {
    @Test
    fun `function-contains-no-local-declarations`() {
        // given
        val sut = getSnippetFile("function-contains-no-local-declarations")
            .functions()
            .first()

        // then
        assertSoftly(sut) {
            numLocalDeclarations shouldBeEqualTo 0
            containsLocalDeclarations("sampleLocalProperty") shouldBeEqualTo false
            localDeclarations()
                .toList()
                .filterIsInstance<KoNameProvider>()
                .shouldBeEqualTo(emptyList())
        }
    }

    @Test
    fun `function-contains-local-declarations`() {
        // given
        val sut = getSnippetFile("function-contains-local-declarations")
            .functions()
            .first()

        // then
        assertSoftly(sut) {
            numLocalDeclarations shouldBeEqualTo 3
            containsLocalDeclarations("sampleLocalProperty") shouldBeEqualTo true
            containsLocalDeclarations("sampleLocalFunction") shouldBeEqualTo true
            containsLocalDeclarations("SampleLocalClass") shouldBeEqualTo true
            containsLocalDeclarations("sampleOtherDeclaration") shouldBeEqualTo false
            localDeclarations()
                .toList()
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
        getSnippetKoScope("core/declaration/kofunctiondeclaration/snippet/forkolocaldeclarationprovider/", fileName)
}
