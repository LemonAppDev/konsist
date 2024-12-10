package com.lemonappdev.konsist.core.declaration.koenumconstant

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import com.lemonappdev.konsist.api.ext.list.enumConstants
import com.lemonappdev.konsist.api.provider.KoNameProvider
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoEnumConstantDeclarationForKoDeclarationProviderTest {
    @Test
    fun `enum-constant-contains-no-declarations`() {
        // given
        val sut =
            getSnippetFile("enum-constant-contains-no-declarations")
                .classes()
                .enumConstants
                .first()

        // then
        assertSoftly(sut) {
            declarations() shouldBeEqualTo emptyList()
            numDeclarations() shouldBeEqualTo 0
            countDeclarations { (it as KoNameProvider).name == "sampleProperty" } shouldBeEqualTo 0
            hasDeclarations() shouldBeEqualTo false
            hasDeclaration { (it as KoNameProvider).name == "SampleDeclaration" } shouldBeEqualTo false
            hasAllDeclarations { (it as KoNameProvider).name == "SampleDeclaration" } shouldBeEqualTo true
        }
    }

    @Test
    fun `enum-constant-contains-declarations`() {
        // given
        val sut =
            getSnippetFile("enum-constant-contains-declarations")
                .classes()
                .enumConstants
                .first()

        // then
        assertSoftly(sut) {
            numDeclarations() shouldBeEqualTo 3
            countDeclarations { (it as KoNameProvider).hasNameStartingWith("sample") } shouldBeEqualTo 2
            hasDeclarations() shouldBeEqualTo true
            hasDeclaration { (it as KoNameProvider).name == "sampleProperty" } shouldBeEqualTo true
            hasDeclaration { (it as KoNameProvider).name == "otherProperty" } shouldBeEqualTo false
            hasAllDeclarations { (it as KoNameProvider).hasNameContaining("") } shouldBeEqualTo true
            hasAllDeclarations { (it as KoNameProvider).hasNameStartingWith("sample") } shouldBeEqualTo false
            declarations()
                .filterIsInstance<KoNameProvider>()
                .map { it.name }
                .shouldBeEqualTo(
                    listOf(
                        "sampleProperty",
                        "sampleFunction",
                        "SampleInnerClass",
                    ),
                )
        }
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/koenumconstant/snippet/forkodeclarationprovider/", fileName)
}
