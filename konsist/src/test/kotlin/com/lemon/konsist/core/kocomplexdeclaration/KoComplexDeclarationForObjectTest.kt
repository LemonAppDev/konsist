package com.lemon.konsist.core.kocomplexdeclaration

import SampleClass
import com.lemon.konsist.TestSnippetProvider
import com.lemon.konsist.core.const.KoModifier.PRIVATE
import com.lemon.konsist.core.const.KoModifier.PUBLIC
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoComplexDeclarationForObjectTest {
    @Test
    fun `object-with-class includeNested true`() {
        // given
        val sut = getSut("object-with-class")
            .objects()
            .first()

        // then
        sut
            .classes(includeNested = true)
            .map { it.name } shouldBeEqualTo listOf("SampleNestedClass")
    }

    @Test
    fun `object-with-class includeNested false`() {
        // given
        val sut = getSut("object-with-class")
            .objects()
            .first()

        // then
        sut
            .classes(includeNested = false)
            .map { it.name } shouldBeEqualTo listOf("SampleNestedClass")
    }

    @Test
    fun `object-with-interface includeNested true`() {
        // given
        val sut = getSut("object-with-interface")
            .objects()
            .first()

        // then
        sut
            .interfaces(includeNested = true)
            .map { it.name } shouldBeEqualTo listOf("SampleNestedInterface")
    }

    @Test
    fun `object-with-interface includeNested false`() {
        // given
        val sut = getSut("object-with-interface")
            .objects()
            .first()

        // then
        sut
            .interfaces(includeNested = false)
            .map { it.name } shouldBeEqualTo listOf("SampleNestedInterface")
    }

    @Test
    fun `object-with-object includeNested true`() {
        // given
        val sut = getSut("object-with-object")
            .objects()
            .first()

        // then
        sut
            .objects(includeNested = true)
            .map { it.name } shouldBeEqualTo listOf("SampleNestedObject")
    }

    @Test
    fun `object-with-object includeNested false`() {
        // given
        val sut = getSut("object-with-object")
            .objects()
            .first()

        // then
        sut
            .objects(includeNested = false)
            .map { it.name } shouldBeEqualTo listOf("SampleNestedObject")
    }

    @Test
    fun `object-with-property includeNested true`() {
        // given
        val sut = getSut("object-with-property")
            .objects()
            .first()

        // then
        sut
            .properties(includeNested = true)
            .map { it.name } shouldBeEqualTo listOf("sampleNestedProperty")
    }

    @Test
    fun `object-with-property includeNested false`() {
        // given
        val sut = getSut("object-with-property")
            .objects()
            .first()

        // then
        sut
            .properties(includeNested = false)
            .map { it.name } shouldBeEqualTo listOf("sampleNestedProperty")
    }

    @Test
    fun `object-with-nested-functions includeNested true includeLocal true`() {
        // given
        val sut = getSut("object-with-nested-functions")
            .objects()
            .first()

        // then
        sut
            .functions(includeNested = true, includeLocal = true)
            .map { it.name } shouldBeEqualTo listOf(
            "sampleFunction",
            "sampleLocalFunction1",
            "sampleNestedFunction",
            "sampleLocalFunction2",
        )
    }

    @Test
    fun `object-with-nested-functions includeNested true includeLocal false`() {
        // given
        val sut = getSut("object-with-nested-functions")
            .objects()
            .first()

        // then
        sut
            .functions(includeNested = true, includeLocal = false)
            .map { it.name } shouldBeEqualTo listOf(
            "sampleFunction",
            "sampleNestedFunction",
        )
    }

    @Test
    fun `object-with-nested-functions includeNested false includeLocal false`() {
        // given
        val sut = getSut("object-with-nested-functions")
            .objects()
            .first()

        // then
        sut
            .functions(includeNested = false, includeLocal = false)
            .map { it.name } shouldBeEqualTo listOf(
            "sampleFunction",
        )
    }

    @Test
    fun `object-with-nested-functions includeNested false includeLocal true`() {
        // given
        val sut = getSut("object-with-nested-functions")
            .objects()
            .first()

        // then
        sut
            .functions(includeNested = false, includeLocal = true)
            .map { it.name } shouldBeEqualTo listOf(
            "sampleFunction",
            "sampleLocalFunction1",
        )
    }

    @Test
    fun `object-with-all-declarations includeNested true`() {
        // given
        val sut = getSut("object-with-all-declarations")
            .objects()
            .first()

        // then
        val expected = listOf(
            "sampleNestedProperty",
            "SampleNestedClass",
            "sampleNestedFunction",
            "SampleNestedInterface",
            "SampleNestedObject",
        )

        sut
            .declarations(includeNested = true)
            .map { it.name } shouldBeEqualTo expected
    }

    @Test
    fun `object-with-all-declarations includeNested false`() {
        // given
        val sut = getSut("object-with-all-declarations")
            .objects()
            .first()

        // then
        val expected = listOf(
            "sampleNestedProperty",
            "SampleNestedClass",
            "sampleNestedFunction",
            "SampleNestedInterface",
            "SampleNestedObject",
        )

        sut
            .declarations(includeNested = false)
            .map { it.name } shouldBeEqualTo expected
    }

    @Test
    fun `object-with-nested-classes includeNested true`() {
        // given
        val sut = getSut("object-with-nested-classes")
            .objects()
            .first()

        // then
        sut
            .classes(includeNested = true)
            .map { it.name } shouldBeEqualTo listOf("SampleNestedClass1", "SampleNestedClass2", "SampleNestedClass3")
    }

    @Test
    fun `object-with-nested-classes includeNested false`() {
        // given
        val sut = getSut("object-with-nested-classes")
            .objects()
            .first()

        // then
        sut
            .classes(includeNested = false)
            .map { it.name } shouldBeEqualTo listOf("SampleNestedClass1")
    }

    @Test
    fun `object-with-nested-interfaces includeNested true`() {
        // given
        val sut = getSut("object-with-nested-interfaces")
            .objects()
            .first()

        // then
        sut
            .interfaces(includeNested = true)
            .map { it.name } shouldBeEqualTo listOf("SampleNestedInterface1", "SampleNestedInterface2", "SampleNestedInterface3")
    }

    @Test
    fun `object-with-nested-interfaces includeNested false`() {
        // given
        val sut = getSut("object-with-nested-interfaces")
            .objects()
            .first()

        // then
        sut
            .interfaces(includeNested = false)
            .map { it.name } shouldBeEqualTo listOf("SampleNestedInterface1")
    }

    @Test
    fun `object-with-nested-objects includeNested true`() {
        // given
        val sut = getSut("object-with-nested-objects")
            .objects()
            .first()

        // then
        sut
            .objects(includeNested = true)
            .map { it.name } shouldBeEqualTo listOf("SampleNestedObject1", "SampleNestedObject2", "SampleNestedObject3")
    }

    @Test
    fun `object-with-nested-objects includeNested false`() {
        // given
        val sut = getSut("object-with-nested-objects")
            .objects()
            .first()

        // then
        sut
            .objects(includeNested = false)
            .map { it.name } shouldBeEqualTo listOf("SampleNestedObject1")
    }

    @Test
    fun `object-with-nested-properties includeNested true`() {
        // given
        val sut = getSut("object-with-nested-properties")
            .objects()
            .first()

        // then
        sut
            .properties(includeNested = true)
            .map { it.name } shouldBeEqualTo listOf("sampleNestedProperty1", "sampleNestedProperty2")
    }

    @Test
    fun `object-with-nested-properties includeNested false`() {
        // given
        val sut = getSut("object-with-nested-properties")
            .objects()
            .first()

        // then
        sut
            .properties(includeNested = false)
            .map { it.name } shouldBeEqualTo listOf("sampleNestedProperty1")
    }

    @Test
    fun `object-with-nested-declarations includeNested true`() {
        // given
        val sut = getSut("object-with-nested-declarations")
            .objects()
            .first()

        // then
        val expected = listOf(
            "sampleNestedProperty1",
            "SampleClass",
            "SampleNestedObject",
            "sampleNestedFunction1",
            "SampleInterface",
            "sampleNestedFunction2",
            "SampleObject",
            "sampleNestedProperty2",
        )

        sut
            .declarations(includeNested = true)
            .map { it.name } shouldBeEqualTo expected
    }

    @Test
    fun `object-with-nested-declarations includeNested false`() {
        // given
        val sut = getSut("object-with-nested-declarations")
            .objects()
            .first()

        // then
        val expected = listOf(
            "sampleNestedProperty1",
            "SampleClass",
            "sampleNestedFunction1",
            "SampleInterface",
            "SampleObject",
        )

        sut
            .declarations(includeNested = false)
            .map { it.name } shouldBeEqualTo expected
    }

    @Test
    fun `object-with-class-which-not-represents-type`() {
        // given
        val sut = getSut("object-with-class-which-not-represents-type")
            .objects()
            .first()

        // then
        sut.representsType(SampleClass::class) shouldBeEqualTo false
    }

    @Test
    fun `object-with-nested-properties-with-modifiers includeNested false`() {
        // given
        val sut = getSut("object-with-nested-properties-with-modifiers")
            .objects()
            .first()

        // then
        sut.apply {
            containsProperty("sampleNestedProperty1", listOf(PRIVATE), includeNested = false) shouldBeEqualTo true
            containsProperty("sampleNestedProperty1", listOf(PUBLIC), includeNested = false) shouldBeEqualTo false
        }
    }

    @Test
    fun `object-with-nested-properties-with-modifiers includeNested true`() {
        // given
        val sut = getSut("object-with-nested-properties-with-modifiers")
            .objects()
            .first()

        // then
        sut.apply {
            containsProperty("sampleNestedProperty2", listOf(PRIVATE), includeNested = true) shouldBeEqualTo true
            containsProperty("sampleNestedProperty2", listOf(PUBLIC), includeNested = true) shouldBeEqualTo false
        }
    }

    @Test
    fun `object-with-nested-classes-with-modifiers includeNested false`() {
        // given
        val sut = getSut("object-with-nested-classes-with-modifiers")
            .objects()
            .first()

        // then
        sut.apply {
            containsClass("SampleNestedClass1", listOf(PRIVATE), includeNested = false) shouldBeEqualTo true
            containsClass("SampleNestedClass1", listOf(PUBLIC), includeNested = false) shouldBeEqualTo false
        }
    }

    @Test
    fun `object-with-nested-classes-with-modifiers includeNested true`() {
        // given
        val sut = getSut("object-with-nested-classes-with-modifiers")
            .objects()
            .first()

        // then
        sut.apply {
            containsClass("SampleNestedClass2", listOf(PRIVATE), includeNested = true) shouldBeEqualTo true
            containsClass("SampleNestedClass2", listOf(PUBLIC), includeNested = true) shouldBeEqualTo false
        }
    }

    @Test
    fun `object-with-nested-interfaces-with-modifiers includeNested false`() {
        // given
        val sut = getSut("object-with-nested-interfaces-with-modifiers")
            .objects()
            .first()

        // then
        sut.apply {
            containsInterface("SampleNestedInterface1", listOf(PRIVATE), includeNested = false) shouldBeEqualTo true
            containsInterface("SampleNestedInterface1", listOf(PUBLIC), includeNested = false) shouldBeEqualTo false
        }
    }

    @Test
    fun `object-with-nested-interfaces-with-modifiers includeNested true`() {
        // given
        val sut = getSut("object-with-nested-interfaces-with-modifiers")
            .objects()
            .first()

        // then
        sut.apply {
            containsInterface("SampleNestedInterface2", listOf(PRIVATE), includeNested = true) shouldBeEqualTo true
            containsInterface("SampleNestedInterface2", listOf(PUBLIC), includeNested = true) shouldBeEqualTo false
        }
    }

    @Test
    fun `object-with-nested-objects-with-modifiers includeNested false`() {
        // given
        val sut = getSut("object-with-nested-objects-with-modifiers")
            .objects()
            .first()

        // then
        sut.apply {
            containsObject("SampleNestedObject1", listOf(PRIVATE), includeNested = false) shouldBeEqualTo true
            containsObject("SampleNestedObject1", listOf(PUBLIC), includeNested = false) shouldBeEqualTo false
        }
    }

    @Test
    fun `object-with-nested-objects-with-modifiers includeNested true`() {
        // given
        val sut = getSut("object-with-nested-objects-with-modifiers")
            .objects()
            .first()

        // then
        sut.apply {
            containsObject("SampleNestedObject2", listOf(PRIVATE), includeNested = true) shouldBeEqualTo true
            containsObject("SampleNestedObject2", listOf(PUBLIC), includeNested = true) shouldBeEqualTo false
        }
    }

    private fun getSut(name: String) = TestSnippetProvider.getSnippetKoScope("core/kocomplexdeclaration/snippet/forobject/$name.kt.txt")
}
