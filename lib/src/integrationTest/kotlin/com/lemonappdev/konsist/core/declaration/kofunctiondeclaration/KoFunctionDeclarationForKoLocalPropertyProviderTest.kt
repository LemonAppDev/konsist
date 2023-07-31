package com.lemonappdev.konsist.core.declaration.kofunctiondeclaration

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoFunctionDeclarationForKoLocalPropertyProviderTest {
    @Test
    fun `function-contains-no-local-property`() {
        // given
        val sut = getSnippetFile("function-contains-no-local-property")
            .functions()
            .first()

        // then
        assertSoftly(sut) {
            numLocalProperties shouldBeEqualTo 0
            containsLocalProperty("sampleLocalProperty") shouldBeEqualTo false
            localProperties()
                .toList()
                .shouldBeEqualTo(emptyList())
        }
    }

    @Test
    fun `function-contains-local-property`() {
        // given
        val sut = getSnippetFile("function-contains-local-property")
            .functions()
            .first()

        // then
        assertSoftly(sut) {
            numLocalProperties shouldBeEqualTo 1
            containsLocalProperty("sampleLocalProperty") shouldBeEqualTo true
            localProperties()
                .toList()
                .map { it.name }
                .shouldBeEqualTo(listOf("sampleLocalProperty"))
        }
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/kofunctiondeclaration/snippet/forkolocalpropertyprovider/", fileName)
}
