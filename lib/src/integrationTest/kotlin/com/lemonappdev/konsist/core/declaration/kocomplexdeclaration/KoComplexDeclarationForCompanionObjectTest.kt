package com.lemonappdev.konsist.core.declaration.kocomplexdeclaration

import com.lemonappdev.konsist.TestSnippetProvider
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoComplexDeclarationForCompanionObjectTest {

    @Test
    fun `companion-object-contains-no-declared-name`() {
        // given
        val sut = getSnippetFile("companion-object-contains-no-declarations")
            .classes()
            .first()
            .companionObjects()
            .first()

        // then
        sut
            .declarations(includeNested = true, includeLocal = true)
            .toList()
            .map { it.name }
            .shouldBeEqualTo(emptyList())
    }

    @Test
    fun `companion-object-contains-no-declarations`() {
        // given
        val sut = getSnippetFile("companion-object-contains-no-declarations")
            .classes()
            .first()
            .companionObjects()
            .first()

        // then
        sut
            .declarations(includeNested = true, includeLocal = true)
            .toList()
            .map { it.name }
            .shouldBeEqualTo(emptyList())
    }

    @Test
    fun `companion-object-contains-declarations includeNested true includeLocal true`() {
        // given
        val sut = getSnippetFile("companion-object-contains-declarations")
            .classes()
            .first()
            .companionObjects()
            .first()

        // then
        val expected = listOf(
            "sampleProperty",
            "sampleFunction",
            "SampleClass",
            "SampleObject",
            "SampleInterface",
        )

        sut
            .declarations(includeNested = true, includeLocal = true)
            .toList()
            .map { it.name }
            .shouldBeEqualTo(expected)
    }

    @Test
    fun `companion-object-contains-declarations includeNested true includeLocal false`() {
        // given
        val sut = getSnippetFile("companion-object-contains-declarations")
            .classes()
            .first()
            .companionObjects()
            .first()

        // then
        val expected = listOf(
            "sampleProperty",
            "sampleFunction",
            "SampleClass",
            "SampleObject",
            "SampleInterface",
        )

        sut
            .declarations(includeNested = true, includeLocal = false)
            .toList()
            .map { it.name }
            .shouldBeEqualTo(expected)
    }

    @Test
    fun `companion-object-contains-declarations includeNested false includeLocal true`() {
        // given
        val sut = getSnippetFile("companion-object-contains-declarations")
            .classes()
            .first()
            .companionObjects()
            .first()

        // then
        val expected = listOf(
            "sampleProperty",
            "sampleFunction",
            "SampleClass",
            "SampleObject",
            "SampleInterface",
        )

        sut
            .declarations(includeNested = false, includeLocal = true)
            .toList()
            .map { it.name }
            .shouldBeEqualTo(expected)
    }

    @Test
    fun `companion-object-contains-nested-declarations includeNested true`() {
        // given
        val sut = getSnippetFile("companion-object-contains-nested-declarations")
            .classes()
            .first()
            .companionObjects()
            .first()

        // then
        val expected = listOf(
            "sampleFunction",
            "SampleClass",
            "SampleClassNestedInsideClass",
            "SampleObjectNestedInsideClass",
            "SampleInterfaceNestedInsideClass",
            "SampleCompanionObjectNestedInsideClass",
            "SampleObject",
            "SampleClassNestedInsideObject",
            "SampleObjectNestedInsideObject",
            "SampleInterfaceNestedInsideObject",
            "SampleInterface",
            "SampleClassNestedInsideInterface",
            "SampleObjectNestedInsideInterface",
            "SampleInterfaceNestedInsideInterface",
        )

        sut
            .declarations(includeNested = true)
            .toList()
            .map { it.name }
            .shouldBeEqualTo(expected)
    }

    @Test
    fun `companion-object-contains-nested-declarations includeNested false`() {
        // given
        val sut = getSnippetFile("companion-object-contains-nested-declarations")
            .classes()
            .first()
            .companionObjects()
            .first()

        // then
        val expected = listOf(
            "sampleFunction",
            "SampleClass",
            "SampleObject",
            "SampleInterface",
        )

        sut
            .declarations(includeNested = false)
            .toList()
            .map { it.name }
            .shouldBeEqualTo(expected)
    }

    @Test
    fun `companion-object-contains-local-declarations includeLocal true`() {
        // given
        val sut = getSnippetFile("companion-object-contains-local-declarations")
            .classes()
            .first()
            .companionObjects()
            .first()

        // then
        val expected = listOf(
            "sampleFunction",
            "sampleLocalProperty1",
            "sampleLocalFunction1",
            "sampleLocalProperty2",
            "sampleLocalFunction2",
            "SampleLocalClass1",
            "sampleLocalFunction2",
        )

        sut
            .declarations(includeLocal = true)
            .toList()
            .map { it.name }
            .shouldBeEqualTo(expected)
    }

    @Test
    fun `companion-object-contains-local-declarations includeLocal false`() {
        // given
        val sut = getSnippetFile("companion-object-contains-local-declarations")
            .classes()
            .first()
            .companionObjects()
            .first()

        // then
        val expected = listOf("sampleFunction")

        sut
            .declarations(includeLocal = false)
            .toList()
            .map { it.name }
            .shouldBeEqualTo(expected)
    }

    @Test
    fun `companion-object-represents-type`() {
        // given
        val sut = getSnippetFile("companion-object-represents-type")
            .interfaces()
            .first()
            .companionObjects()
            .first()

        // then
        assertSoftly(sut) {
            representsType("SampleCompanionObject") shouldBeEqualTo true
            representsType("SampleType") shouldBeEqualTo false
            representsType("com.lemonappdev.konsist.testdata.SampleTopLevelInterface.SampleCompanionObject") shouldBeEqualTo true
            representsType("com.lemonappdev.konsist.testdata.SampleType") shouldBeEqualTo false
        }
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("core/declaration/kocomplexdeclaration/snippet/forcompanionobject/", fileName)
}
