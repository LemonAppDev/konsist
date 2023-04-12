package com.lemon.konsist.core.declaration.kocomplexdeclaration

import com.lemon.konsist.TestSnippetProvider
import com.lemon.konsist.core.const.KoModifier
import com.lemon.konsist.testdata.SampleObject
import com.lemon.konsist.testdata.SampleType
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoComplexDeclarationForObjectTest {
    @Test
    fun `object-contains-no-declarations`() {
        // given
        val sut = getSut("object-contains-no-declarations")
            .objects()
            .first()

        // then
        sut
            .declarations(includeNested = true, includeLocal = true)
            .map { it.name } shouldBeEqualTo emptyList()
    }

    @Test
    fun `object-contains-declarations includeNested true includeLocal true`() {
        // given
        val sut = getSut("object-contains-declarations")
            .objects()
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
            .map { it.name } shouldBeEqualTo expected
    }

    @Test
    fun `object-contains-declarations includeNested true includeLocal false`() {
        // given
        val sut = getSut("object-contains-declarations")
            .objects()
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
            .map { it.name } shouldBeEqualTo expected
    }

    @Test
    fun `object-contains-declarations includeNested false includeLocal true`() {
        // given
        val sut = getSut("object-contains-declarations")
            .objects()
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
            .map { it.name } shouldBeEqualTo expected
    }

    @Test
    fun `object-contains-nested-declarations includeNested true`() {
        // given
        val sut = getSut("object-contains-nested-declarations")
            .objects()
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
            .map { it.name } shouldBeEqualTo expected
    }

    @Test
    fun `object-contains-nested-declarations includeNested false`() {
        // given
        val sut = getSut("object-contains-nested-declarations")
            .objects()
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
            .map { it.name } shouldBeEqualTo expected
    }

    @Test
    fun `object-contains-local-declarations includeLocal true`() {
        // given
        val sut = getSut("object-contains-local-declarations")
            .objects()
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
            .map { it.name } shouldBeEqualTo expected
    }

    @Test
    fun `object-contains-local-declarations includeLocal false`() {
        // given
        val sut = getSut("object-contains-local-declarations")
            .objects()
            .first()

        // then
        val expected = listOf("sampleFunction")

        sut
            .declarations(includeLocal = false)
            .map { it.name } shouldBeEqualTo expected
    }

    @Test
    fun `object-contains-declarations-heaving-visibility-modifiers includeNested = true`() {
        // given
        val sut = getSut("object-contains-declarations-heaving-visibility-modifiers")
            .objects()
            .first()

        // then
        sut
            .declarations(listOf(KoModifier.PRIVATE), includeNested = true)
            .map { it.name } shouldBeEqualTo listOf(
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
    }

    @Test
    fun `object-contains-declarations-heaving-visibility-modifiers includeNested = false`() {
        // given
        val sut = getSut("object-contains-declarations-heaving-visibility-modifiers")
            .objects()
            .first()

        // then
        sut
            .declarations(listOf(KoModifier.PRIVATE), includeNested = false)
            .map { it.name } shouldBeEqualTo listOf(
            "sampleFunction1",
            "SampleClass1",
            "SampleObject1",
            "SampleInterface1",
        )
    }

    @Test
    fun `object-represents-type`() {
        // given
        val sut = getSut("object-represents-type")
            .objects()
            .first()

        // then
        sut.run {
            representsType(SampleObject::class) shouldBeEqualTo true
            representsType(SampleType::class) shouldBeEqualTo false
        }
    }

    private fun getSut(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("core/declaration/kocomplexdeclaration/snippet/forobject/", fileName)
}
