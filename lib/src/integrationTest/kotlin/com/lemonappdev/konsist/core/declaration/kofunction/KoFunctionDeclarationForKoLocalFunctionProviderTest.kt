package com.lemonappdev.konsist.core.declaration.kofunction

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoFunctionDeclarationForKoLocalFunctionProviderTest {
    @Test
    fun `function-contains-no-local-function`() {
        // given
        val sut = getSnippetFile("function-contains-no-local-function")
            .functions()
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
    fun `function-contains-local-function`() {
        // given
        val sut = getSnippetFile("function-contains-local-function")
            .functions()
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
        getSnippetKoScope("core/declaration/kofunction/snippet/forkolocalfunctionprovider/", fileName)
}
