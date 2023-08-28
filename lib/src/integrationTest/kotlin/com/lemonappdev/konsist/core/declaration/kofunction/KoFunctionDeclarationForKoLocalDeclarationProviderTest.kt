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
        val sut = getSnippetFile("function-contains-no-local-declarations")
            .functions()
            .first()

        // then
        assertSoftly(sut) {
            numLocalDeclarations shouldBeEqualTo 0
            countLocalClasses { it.name == "sampleOtherDeclaration" } shouldBeEqualTo 0
            containsLocalDeclaration { (it as KoNameProvider).name == "sampleLocalProperty" } shouldBeEqualTo false
            localDeclarations shouldBeEqualTo emptyList()
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
            countLocalDeclarations { (it as KoNameProvider).hasNameStartingWith("sampleLocal") } shouldBeEqualTo 2
            containsLocalDeclaration { (it as KoNameProvider).name == "sampleLocalProperty" } shouldBeEqualTo true
            containsLocalDeclaration { (it as KoNameProvider).name == "sampleLocalFunction" } shouldBeEqualTo true
            containsLocalDeclaration { (it as KoNameProvider).name == "SampleLocalClass" } shouldBeEqualTo true
            containsLocalDeclaration { (it as KoNameProvider).name == "sampleOtherDeclaration" } shouldBeEqualTo false
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
