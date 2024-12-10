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
            hasAllDeclarations { (it as KoNameProvider).hasNameContaining("ample") } shouldBeEqualTo true
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

    @Test
    fun `enum-constant-contains-nested-and-local-declarations includeLocal=true`() {
        // given
        val sut =
            getSnippetFile("enum-constant-contains-nested-and-local-declarations")
                .classes()
                .enumConstants
                .first()

        // then
        assertSoftly(sut) {
            numDeclarations(includeLocal = true, includeNested = false) shouldBeEqualTo 6

            countDeclarations(
                includeLocal = true,
                includeNested = false
            ) { (it as KoNameProvider).hasNameStartingWith("sampleLocal") }
                .shouldBeEqualTo(2)

            hasDeclarations(includeLocal = true, includeNested = false) shouldBeEqualTo true
            hasDeclaration(
                includeLocal = true,
                includeNested = false
            ) { (it as KoNameProvider).name == "sampleLocalProperty" }
                .shouldBeEqualTo(true)

            hasDeclaration(
                includeLocal = true,
                includeNested = false
            ) { (it as KoNameProvider).name == "otherLocalProperty" }
                .shouldBeEqualTo(false)

            hasAllDeclarations(
                includeLocal = true,
                includeNested = false
            ) { (it as KoNameProvider).hasNameContaining("ample") }
                .shouldBeEqualTo(true)

            hasAllDeclarations(includeLocal = true, includeNested = false) {
                (it as KoNameProvider).hasNameStartingWith(
                    "sample"
                )
            }
                .shouldBeEqualTo(false)

            declarations(includeLocal = true, includeNested = false)
                .filterIsInstance<KoNameProvider>()
                .map { it.name }
                .shouldBeEqualTo(
                    listOf(
                        "sampleProperty",
                        "sampleFunction",
                        "sampleLocalProperty",
                        "sampleLocalFunction",
                        "SampleLocalClass",
                        "SampleInnerClass",
                    ),
                )
        }
    }

    @Test
    fun `enum-constant-contains-nested-and-local-declarations includeNested=true`() {
        // given
        val sut =
            getSnippetFile("enum-constant-contains-nested-and-local-declarations")
                .classes()
                .enumConstants
                .first()

        // then
        assertSoftly(sut) {
            numDeclarations(includeLocal = false, includeNested = true) shouldBeEqualTo 6

            countDeclarations(
                includeLocal = false,
                includeNested = true
            ) { (it as KoNameProvider).hasNameStartingWith("sampleNested") }
                .shouldBeEqualTo(2)

            hasDeclarations(includeLocal = false, includeNested = true) shouldBeEqualTo true
            hasDeclaration(
                includeLocal = false,
                includeNested = true
            ) { (it as KoNameProvider).name == "sampleNestedProperty" }
                .shouldBeEqualTo(true)

            hasDeclaration(
                includeLocal = false,
                includeNested = true
            ) { (it as KoNameProvider).name == "otherNestedProperty" }
                .shouldBeEqualTo(false)

            hasAllDeclarations(
                includeLocal = false,
                includeNested = true
            ) { (it as KoNameProvider).hasNameContaining("ample") }
                .shouldBeEqualTo(true)

            hasAllDeclarations(includeLocal = false, includeNested = true) {
                (it as KoNameProvider).hasNameStartingWith(
                    "sample"
                )
            }
                .shouldBeEqualTo(false)

            declarations(includeLocal = false, includeNested = true)
                .filterIsInstance<KoNameProvider>()
                .map { it.name }
                .shouldBeEqualTo(
                    listOf(
                        "sampleProperty",
                        "sampleFunction",
                        "SampleInnerClass",
                        "sampleNestedProperty",
                        "sampleNestedFunction",
                        "SampleNestedClass",
                    ),
                )
        }
    }

    @Test
    fun `enum-constant-contains-nested-and-local-declarations includeLocal=true and includeNested=true`() {
        // given
        val sut =
            getSnippetFile("enum-constant-contains-nested-and-local-declarations")
                .classes()
                .enumConstants
                .first()

        // then
        assertSoftly(sut) {
            numDeclarations(includeLocal = true, includeNested = true) shouldBeEqualTo 9

            countDeclarations(
                includeLocal = true,
                includeNested = true
            ) { (it as KoNameProvider).hasNameStartingWith("sample") }
                .shouldBeEqualTo(6)

            hasDeclarations(includeLocal = true, includeNested = true) shouldBeEqualTo true
            hasDeclaration(
                includeLocal = true,
                includeNested = true
            ) { (it as KoNameProvider).name == "sampleLocalProperty" }
                .shouldBeEqualTo(true)

            hasDeclaration(
                includeLocal = true,
                includeNested = true
            ) { (it as KoNameProvider).name == "otherLocalProperty" }
                .shouldBeEqualTo(false)

            hasAllDeclarations(
                includeLocal = true,
                includeNested = true
            ) { (it as KoNameProvider).hasNameContaining("ample") }
                .shouldBeEqualTo(true)

            hasAllDeclarations(includeLocal = true, includeNested = true) {
                (it as KoNameProvider).hasNameStartingWith(
                    "sample"
                )
            }
                .shouldBeEqualTo(false)

            declarations(includeLocal = true, includeNested = true)
                .filterIsInstance<KoNameProvider>()
                .map { it.name }
                .shouldBeEqualTo(
                    listOf(
                        "sampleProperty",
                        "sampleFunction",
                        "sampleLocalProperty",
                        "sampleLocalFunction",
                        "SampleLocalClass",
                        "SampleInnerClass",
                        "sampleNestedProperty",
                        "sampleNestedFunction",
                        "SampleNestedClass",
                    ),
                )
        }
    }


    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/koenumconstant/snippet/forkodeclarationprovider/", fileName)
}
