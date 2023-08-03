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
            containsLocalFunction("sampleLocalFunction") shouldBeEqualTo false
            localFunctions
                .shouldBeEqualTo(emptyList())
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
            numLocalFunctions shouldBeEqualTo 1
            containsLocalFunction("sampleLocalFunction") shouldBeEqualTo true
            localFunctions
                .map { it.name }
                .shouldBeEqualTo(listOf("sampleLocalFunction"))
        }
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/kofunction/snippet/forkolocalfunctionprovider/", fileName)
}
