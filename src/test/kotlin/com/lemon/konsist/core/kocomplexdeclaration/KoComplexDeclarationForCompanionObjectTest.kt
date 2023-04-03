package com.lemon.konsist.core.kocomplexdeclaration

import com.lemon.konsist.TestSnippetProvider
import com.lemon.konsist.core.const.KoModifier
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoComplexDeclarationForCompanionObjectTest {

    @Test
    fun `companion-object-without-declared-name`() {
        // given
        val sut = getSut("companion-object-without-declarations")
            .classes()
            .first()
            .companionObjects()
            .first()

        // then
        sut
            .declarations(includeNested = true, includeLocal = true)
            .map { it.name } shouldBeEqualTo emptyList()
    }

    @Test
    fun `companion-object-without-declarations`() {
        // given
        val sut = getSut("companion-object-without-declarations")
            .classes()
            .first()
            .companionObjects()
            .first()

        // then
        sut
            .declarations(includeNested = true, includeLocal = true)
            .map { it.name } shouldBeEqualTo emptyList()
    }

    @Test
    fun `companion-object-containing-declarations includeNested true includeLocal true`() {
        // given
        val sut = getSut("companion-object-containing-declarations")
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
            .map { it.name } shouldBeEqualTo expected
    }

    @Test
    fun `companion-object-containing-declarations includeNested true includeLocal false`() {
        // given
        val sut = getSut("companion-object-containing-declarations")
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
            .map { it.name } shouldBeEqualTo expected
    }

    @Test
    fun `companion-object-containing-declarations includeNested false includeLocal true`() {
        // given
        val sut = getSut("companion-object-containing-declarations")
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
            .map { it.name } shouldBeEqualTo expected
    }

    @Test
    fun `companion-object-containing-nested-declarations includeNested true`() {
        // given
        val sut = getSut("companion-object-containing-nested-declarations")
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
            .map { it.name } shouldBeEqualTo expected
    }

    @Test
    fun `companion-object-containing-nested-declarations includeNested false`() {
        // given
        val sut = getSut("companion-object-containing-nested-declarations")
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
            .map { it.name } shouldBeEqualTo expected
    }

    @Test
    fun `companion-object-containing-local-declarations includeLocal true`() {
        // given
        val sut = getSut("companion-object-containing-local-declarations")
            .classes()
            .first()
            .companionObjects()
            .first()

        // then
        val expected = listOf(
            "sampleFunction",
            "sampleLocalProperty1",
            "sampleLocalFunction1",
        )

        sut
            .declarations(includeLocal = true)
            .map { it.name } shouldBeEqualTo expected
    }

    @Test
    fun `companion-object-containing-local-declarations includeLocal false`() {
        // given
        val sut = getSut("companion-object-containing-local-declarations")
            .classes()
            .first()
            .companionObjects()
            .first()

        // then
        val expected = listOf("sampleFunction")

        sut
            .declarations(includeLocal = false)
            .map { it.name } shouldBeEqualTo expected
    }

    @Test
    fun `companion-object-containing-declarations-heaving-visibility-modifiers includeNested = true`() {
        // given
        val sut = getSut("companion-object-containing-declarations-heaving-visibility-modifiers")
            .classes()
            .first()
            .companionObjects()
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
    fun `companion-object-containing-declarations-heaving-visibility-modifiers includeNested = false`() {
        // given
        val sut = getSut("companion-object-containing-declarations-heaving-visibility-modifiers")
            .classes()
            .first()
            .companionObjects()
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

    private fun getSut(name: String) =
        TestSnippetProvider.getSnippetKoScope("core/kocomplexdeclaration/snippet/forcompanionobject/$name.kt.txt")
}
