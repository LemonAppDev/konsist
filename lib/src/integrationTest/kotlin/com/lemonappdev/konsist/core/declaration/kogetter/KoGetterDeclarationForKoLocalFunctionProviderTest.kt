package com.lemonappdev.konsist.core.declaration.kogetter

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoGetterDeclarationForKoLocalFunctionProviderTest {
    @Test
    fun `getter-contains-no-local-function`() {
        // given
        val sut =
            getSnippetFile("getter-contains-no-local-function")
                .properties()
                .first()
                .getter

        // then
        assertSoftly(sut) {
            it?.localFunctions shouldBeEqualTo emptyList()
            it?.numLocalFunctions shouldBeEqualTo 0
            it?.countLocalFunctions { function -> function.name == "sampleLocalFunction" } shouldBeEqualTo 0
            it?.hasLocalFunctions() shouldBeEqualTo false
            it?.hasLocalFunctionWithName("sampleLocalFunction") shouldBeEqualTo false
            it?.hasLocalFunctionsWithAllNames("sampleLocalFunction1", "sampleLocalFunction2") shouldBeEqualTo false
            it?.hasLocalFunction { function -> function.name == "sampleLocalFunction" } shouldBeEqualTo false
            it?.hasAllLocalFunctions { function -> function.name == "sampleLocalFunction" } shouldBeEqualTo true
        }
    }

    @Test
    fun `getter-contains-local-function`() {
        // given
        val sut =
            getSnippetFile("getter-contains-local-function")
                .properties()
                .first()
                .getter

        // then
        assertSoftly(sut) {
            it?.numLocalFunctions shouldBeEqualTo 2
            it?.countLocalFunctions { function -> function.name == "sampleLocalFunction1" } shouldBeEqualTo 1
            it?.hasLocalFunctions() shouldBeEqualTo true
            it?.hasLocalFunctionWithName("sampleLocalFunction1") shouldBeEqualTo true
            it?.hasLocalFunctionWithName("otherLocalFunction") shouldBeEqualTo false
            it?.hasLocalFunctionWithName("sampleLocalFunction1", "otherLocalFunction") shouldBeEqualTo true
            it?.hasLocalFunctionsWithAllNames("sampleLocalFunction1") shouldBeEqualTo true
            it?.hasLocalFunctionsWithAllNames("sampleLocalFunction1", "sampleLocalFunction2") shouldBeEqualTo true
            it?.hasLocalFunctionsWithAllNames("sampleLocalFunction1", "otherLocalFunction") shouldBeEqualTo false
            it?.hasLocalFunction { function -> function.name == "sampleLocalFunction1" } shouldBeEqualTo true
            it?.hasLocalFunction { function -> function.name == "otherLocalFunction" } shouldBeEqualTo false
            it?.hasAllLocalFunctions { function,
                ->
                function.name.endsWith("2") || function.name == "sampleLocalFunction1"
            }.shouldBeEqualTo(true)
            it?.hasAllLocalFunctions { function -> function.name.endsWith("2") } shouldBeEqualTo false
        }
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/kogetter/snippet/forkolocalfunctionprovider/", fileName)
}
