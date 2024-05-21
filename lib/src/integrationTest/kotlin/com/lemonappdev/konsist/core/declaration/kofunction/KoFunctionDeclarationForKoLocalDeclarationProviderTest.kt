package com.lemonappdev.konsist.core.declaration.kofunction

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import com.lemonappdev.konsist.api.provider.KoNameProvider
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoFunctionDeclarationForKoLocalDeclarationProviderTest {
    @Test
    fun `function-contains-no-local-declarations`() {
        // given
        val sut =
            getSnippetFile("function-contains-no-local-declarations")
                .functions()
                .first()

        // then
        assertSoftly(sut) {
            localDeclarations shouldBeEqualTo emptyList()
            numLocalDeclarations shouldBeEqualTo 0
            countLocalDeclarations { (it as KoNameProvider).name == "sampleLocalProperty" } shouldBeEqualTo 0
            hasLocalDeclarations() shouldBeEqualTo false
            hasLocalDeclaration { (it as KoNameProvider).name == "SampleLocalDeclaration" } shouldBeEqualTo false
            hasAllLocalDeclarations { (it as KoNameProvider).name == "SampleLocalDeclaration" } shouldBeEqualTo true
        }
    }

    @Test
    fun `function-contains-local-declarations`() {
        // given
        val sut =
            getSnippetFile("function-contains-local-declarations")
                .functions()
                .first()

        // then
        assertSoftly(sut) {
            numLocalDeclarations shouldBeEqualTo 3
            countLocalDeclarations { (it as KoNameProvider).hasNameStartingWith("sampleLocal") } shouldBeEqualTo 2
            hasLocalDeclarations() shouldBeEqualTo true
            hasLocalDeclaration { (it as KoNameProvider).name == "sampleLocalProperty" } shouldBeEqualTo true
            hasLocalDeclaration { (it as KoNameProvider).name == "otherLocalProperty" } shouldBeEqualTo false
            hasAllLocalDeclarations { (it as KoNameProvider).hasNameContaining("Local") } shouldBeEqualTo true
            hasAllLocalDeclarations { (it as KoNameProvider).hasNameStartingWith("sample") } shouldBeEqualTo false
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
        getSnippetKoScope("core/declaration/kofunction/snippet/forkolocaldeclarationprovider/", fileName)
}
