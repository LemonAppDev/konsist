package com.lemonappdev.konsist.core.declaration.kofunctiondeclaration

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
            containsLocalFunction("sampleLocalFunction") shouldBeEqualTo false
            localFunctions()
                .toList()
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
            containsLocalFunction("sampleLocalFunction") shouldBeEqualTo true
            localFunctions()
                .toList()
                .map { it.name }
                .shouldBeEqualTo(listOf("sampleLocalFunction"))
        }
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/kofunctiondeclaration/snippet/forkolocalfunctionprovider/", fileName)
}
