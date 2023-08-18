package com.lemonappdev.konsist.core.declaration.koinitblock

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoInitBlockDeclarationForKoLocalFunctionProviderTest {
    @Test
    fun `init-block-contains-no-local-function`() {
        // given
        val sut = getSnippetFile("init-block-contains-no-local-function")
            .classes()
            .first()
            .initBlocks
            .first()

        // then
        assertSoftly(sut) {
            numLocalFunctions shouldBeEqualTo 0
            containsLocalFunction("sampleLocalFunction") shouldBeEqualTo false
            localFunctions
                .shouldBeEqualTo(emptyList())
        }
    }

    @Test
    fun `init-block-contains-local-function`() {
        // given
        val sut = getSnippetFile("init-block-contains-local-function")
            .classes()
            .first()
            .initBlocks
            .first()

        // then
        assertSoftly(sut) {
            numLocalFunctions shouldBeEqualTo 1
            containsLocalFunction("sampleLocalFunction") shouldBeEqualTo true
            localFunctions
                .map { it.name }
                .shouldBeEqualTo(listOf("sampleLocalFunction"))
        }
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/koinitblock/snippet/forkolocalfunctionprovider/", fileName)
}
