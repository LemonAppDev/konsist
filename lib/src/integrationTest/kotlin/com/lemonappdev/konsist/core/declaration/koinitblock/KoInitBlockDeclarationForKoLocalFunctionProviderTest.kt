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
            countLocalFunctions { it.name == "sampleLocalFunction" } shouldBeEqualTo 0
            containsLocalFunction { it.name == "sampleLocalFunction" } shouldBeEqualTo false
            localFunctions shouldBeEqualTo emptyList()
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
            numLocalFunctions shouldBeEqualTo 2
            countLocalFunctions { it.name == "sampleLocalFunction1" } shouldBeEqualTo 1
            containsLocalFunction { it.name == "sampleLocalFunction1" } shouldBeEqualTo true
            containsLocalFunction { it.name == "otherLocalFunction1" } shouldBeEqualTo false
            localFunctions
                .map { it.name }
                .shouldBeEqualTo(listOf("sampleLocalFunction1", "sampleLocalFunction2"))
        }
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/koinitblock/snippet/forkolocalfunctionprovider/", fileName)
}
