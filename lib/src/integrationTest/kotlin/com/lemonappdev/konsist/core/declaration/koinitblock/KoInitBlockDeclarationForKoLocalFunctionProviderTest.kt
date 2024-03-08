package com.lemonappdev.konsist.core.declaration.koinitblock

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import com.lemonappdev.konsist.api.ext.list.initBlocks
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoInitBlockDeclarationForKoLocalFunctionProviderTest {
    @Test
    fun `init-block-contains-no-local-function`() {
        // given
        val sut =
            getSnippetFile("init-block-contains-no-local-function")
                .classes()
                .initBlocks
                .first()

        // then
        assertSoftly(sut) {
            localFunctions shouldBeEqualTo emptyList()
            numLocalFunctions shouldBeEqualTo 0
            countLocalFunctions { it.name == "sampleLocalFunction" } shouldBeEqualTo 0
            hasLocalFunctions() shouldBeEqualTo false
            hasLocalFunctionWithName("sampleLocalFunction") shouldBeEqualTo false
            hasLocalFunctionsWithAllNames("sampleLocalFunction1", "sampleLocalFunction2") shouldBeEqualTo false
            hasLocalFunction { it.name == "sampleLocalFunction" } shouldBeEqualTo false
            hasAllLocalFunctions { it.name == "sampleLocalFunction" } shouldBeEqualTo true
            containsLocalFunction { it.name == "sampleLocalFunction" } shouldBeEqualTo false
        }
    }

    @Test
    fun `init-block-contains-local-function`() {
        // given
        val sut =
            getSnippetFile("init-block-contains-local-function")
                .classes()
                .initBlocks
                .first()

        // then
        assertSoftly(sut) {
            numLocalFunctions shouldBeEqualTo 2
            countLocalFunctions { it.name == "sampleLocalFunction1" } shouldBeEqualTo 1
            hasLocalFunctions() shouldBeEqualTo true
            hasLocalFunctionWithName("sampleLocalFunction1") shouldBeEqualTo true
            hasLocalFunctionWithName("otherLocalFunction") shouldBeEqualTo false
            hasLocalFunctionWithName("sampleLocalFunction1", "otherLocalFunction") shouldBeEqualTo true
            hasLocalFunctionsWithAllNames("sampleLocalFunction1") shouldBeEqualTo true
            hasLocalFunctionsWithAllNames("sampleLocalFunction1", "sampleLocalFunction2") shouldBeEqualTo true
            hasLocalFunctionsWithAllNames("sampleLocalFunction1", "otherLocalFunction") shouldBeEqualTo false
            hasLocalFunction { it.name == "sampleLocalFunction1" } shouldBeEqualTo true
            hasLocalFunction { it.name == "otherLocalFunction" } shouldBeEqualTo false
            hasAllLocalFunctions { it.name.endsWith("2") || it.name == "sampleLocalFunction1" } shouldBeEqualTo true
            hasAllLocalFunctions { it.name.endsWith("2") } shouldBeEqualTo false
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
