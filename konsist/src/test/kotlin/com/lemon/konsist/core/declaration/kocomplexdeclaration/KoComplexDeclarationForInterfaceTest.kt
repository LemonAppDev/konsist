package com.lemon.konsist.core.declaration.kocomplexdeclaration

import com.lemon.konsist.TestSnippetProvider
import com.lemon.konsist.core.const.KoModifier
import com.lemon.konsist.testdata.SampleInterface
import com.lemon.konsist.testdata.SampleType
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoComplexDeclarationForInterfaceTest {
    @Test
    fun `interface-contains-no-declarations`() {
        // given
        val sut = getSut("interface-contains-no-declarations")
            .interfaces()
            .first()

        // then
        sut
            .declarations(includeNested = true, includeLocal = true)
            .map { it.name }
            .shouldBeEqualTo(emptyList())
    }

    @Test
    fun `interface-contains-declarations includeNested true includeLocal true`() {
        // given
        val sut = getSut("interface-contains-declarations")
            .interfaces()
            .first()

        // then
        val expected = listOf(
            "sampleProperty",
            "sampleFunction",
            "SampleClass",
            "SampleObject",
            "SampleInterface",
            "SampleCompanionObject",
        )

        sut
            .declarations(includeNested = true, includeLocal = true)
            .map { it.name }
            .shouldBeEqualTo(expected)
    }

    @Test
    fun `interface-contains-declarations includeNested true includeLocal false`() {
        // given
        val sut = getSut("interface-contains-declarations")
            .interfaces()
            .first()

        // then
        val expected = listOf(
            "sampleProperty",
            "sampleFunction",
            "SampleClass",
            "SampleObject",
            "SampleInterface",
            "SampleCompanionObject",
        )

        sut
            .declarations(includeNested = true, includeLocal = false)
            .map { it.name }
            .shouldBeEqualTo(expected)
    }

    @Test
    fun `interface-contains-declarations includeNested false includeLocal true`() {
        // given
        val sut = getSut("interface-contains-declarations")
            .interfaces()
            .first()

        // then
        val expected = listOf(
            "sampleProperty",
            "sampleFunction",
            "SampleClass",
            "SampleObject",
            "SampleInterface",
            "SampleCompanionObject",
        )

        sut
            .declarations(includeNested = false, includeLocal = true)
            .map { it.name }
            .shouldBeEqualTo(expected)
    }

    @Test
    fun `interface-contains-nested-declarations includeNested true`() {
        // given
        val sut = getSut("interface-contains-nested-declarations")
            .interfaces()
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
            .map { it.name }
            .shouldBeEqualTo(expected)
    }

    @Test
    fun `interface-contains-nested-declarations includeNested false`() {
        // given
        val sut = getSut("interface-contains-nested-declarations")
            .interfaces()
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
            .map { it.name }
            .shouldBeEqualTo(expected)
    }

    @Test
    fun `interface-contains-local-declarations includeLocal true`() {
        // given
        val sut = getSut("interface-contains-local-declarations")
            .interfaces()
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
            .map { it.name }
            .shouldBeEqualTo(expected)
    }

    @Test
    fun `interface-contains-local-declarations includeLocal false`() {
        // given
        val sut = getSut("interface-contains-local-declarations")
            .interfaces()
            .first()

        // then
        val expected = listOf("sampleFunction")

        sut
            .declarations(includeLocal = false)
            .map { it.name }
            .shouldBeEqualTo(expected)
    }

    @Test
    fun `interface-contains-declarations-heaving-visibility-modifiers includeNested = true`() {
        // given
        val sut = getSut("interface-contains-declarations-heaving-visibility-modifiers")
            .interfaces()
            .first()

        // then
        val expected = listOf(
            "sampleFunction1",
            "SampleClass1",
            "SampleClassNestedInsideClass2",
            "SampleObjectNestedInsideClass2",
            "SampleInterfaceNestedInsideClass2",
            "SampleCompanionObjectNestedInsideClass2",
            "SampleObject1",
            "SampleClassNestedInsideObject2",
            "SampleObjectNestedInsideObject2",
            "SampleInterfaceNestedInsideObject2",
            "SampleInterface1",
            "SampleClassNestedInsideInterface2",
            "SampleObjectNestedInsideInterface2",
            "SampleInterfaceNestedInsideInterface2",
        )

        sut
            .declarations(listOf(KoModifier.PRIVATE), includeNested = true)
            .map { it.name }
            .shouldBeEqualTo(expected)
    }

    @Test
    fun `interface-contains-declarations-heaving-visibility-modifiers includeNested = false`() {
        // given
        val sut = getSut("interface-contains-declarations-heaving-visibility-modifiers")
            .interfaces()
            .first()

        // then
        val expected = listOf(
            "sampleFunction1",
            "SampleClass1",
            "SampleObject1",
            "SampleInterface1",
        )

        sut
            .declarations(listOf(KoModifier.PRIVATE), includeNested = false)
            .map { it.name }
            .shouldBeEqualTo(expected)
    }

    @Test
    fun `interface-represents-type`() {
        // given
        val sut = getSut("interface-represents-type")
            .interfaces()
            .first()

        // then
        sut.run {
            representsType("SampleInterface") shouldBeEqualTo true
            representsType("SampleType") shouldBeEqualTo false
            representsType("com.lemon.konsist.testdata.SampleInterface") shouldBeEqualTo true
            representsType("com.lemon.konsist.testdata.SampleType") shouldBeEqualTo false
            representsType<SampleInterface>() shouldBeEqualTo true
            representsType<SampleType>() shouldBeEqualTo false
        }
    }

    private fun getSut(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("core/declaration/kocomplexdeclaration/snippet/forinterface/", fileName)
}
