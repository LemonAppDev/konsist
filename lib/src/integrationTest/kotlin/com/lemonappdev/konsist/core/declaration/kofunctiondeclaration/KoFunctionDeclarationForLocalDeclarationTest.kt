package com.lemonappdev.konsist.core.declaration.kofunctiondeclaration

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import com.lemonappdev.konsist.core.ext.toNormalizedPath
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoFunctionDeclarationForLocalDeclarationTest {
    @Test
    fun `function-contains-local-property`() {
        // given
        val sut = getSnippetFile("function-contains-local-property")
            .functions()
            .first()

        // then
        assertSoftly(sut) {
            containsLocalProperty("sampleLocalProperty") shouldBeEqualTo true
            localProperties()
                .toList()
                .map { it.name }
                .shouldBeEqualTo(listOf("sampleLocalProperty"))
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

    @Test
    fun `function-contains-local-class`() {
        // given
        val sut = getSnippetFile("function-contains-local-class")
            .functions()
            .first()

        // then
        assertSoftly(sut) {
            containsLocalClass("SampleClass") shouldBeEqualTo true
            localClasses()
                .toList()
                .map { it.name }
                .shouldBeEqualTo(listOf("SampleClass"))
        }
    }

    @Test
    fun `function-contains-local-declarations`() {
        // given
        val sut = getSnippetFile("function-contains-local-declarations")
            .functions()
            .first()

        // then
        sut
            .localDeclarations()
            .toList()
            .map { it.name }
            .shouldBeEqualTo(
                listOf(
                    "sampleLocalProperty",
                    "sampleLocalFunction",
                    "SampleNestedClass",
                ),
            )
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/kofunctiondeclaration/snippet/forlocaldeclaration/".toNormalizedPath(), fileName)
}
