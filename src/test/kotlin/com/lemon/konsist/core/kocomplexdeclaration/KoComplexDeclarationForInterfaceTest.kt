package com.lemon.konsist.core.kocomplexdeclaration

import SampleClass
import com.lemon.konsist.TestSnippetProvider
import com.lemon.konsist.core.const.KoModifier.PRIVATE
import com.lemon.konsist.core.const.KoModifier.PUBLIC
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoComplexDeclarationForInterfaceTest {
    @Test
    fun `interface-with-class includeNested true`() {
        // given
        val sut = getSut("interface-with-class")
            .interfaces()
            .first()

        // then
        sut
            .classes(includeNested = true)
            .map { it.name } shouldBeEqualTo listOf("SampleNestedClass")
    }

    @Test
    fun `interface-with-class includeNested false`() {
        // given
        val sut = getSut("interface-with-class")
            .interfaces()
            .first()

        // then
        sut
            .classes(includeNested = false)
            .map { it.name } shouldBeEqualTo listOf("SampleNestedClass")
    }

    @Test
    fun `interface-with-interface includeNested true`() {
        // given
        val sut = getSut("interface-with-interface")
            .interfaces()
            .first()

        // then
        sut
            .interfaces(includeNested = true)
            .map { it.name } shouldBeEqualTo listOf("SampleNestedInterface")
    }

    @Test
    fun `interface-with-interface includeNested false`() {
        // given
        val sut = getSut("interface-with-interface")
            .interfaces()
            .first()

        // then
        sut
            .interfaces(includeNested = false)
            .map { it.name } shouldBeEqualTo listOf("SampleNestedInterface")
    }

    @Test
    fun `interface-with-object includeNested true`() {
        // given
        val sut = getSut("interface-with-object")
            .interfaces()
            .first()

        // then
        sut
            .objects(includeNested = true)
            .map { it.name } shouldBeEqualTo listOf("SampleNestedObject")
    }

    @Test
    fun `interface-with-object includeNested false`() {
        // given
        val sut = getSut("interface-with-object")
            .interfaces()
            .first()

        // then
        sut
            .objects(includeNested = false)
            .map { it.name } shouldBeEqualTo listOf("SampleNestedObject")
    }

    @Test
    fun `interface-with-property includeNested true`() {
        // given
        val sut = getSut("interface-with-property")
            .interfaces()
            .first()

        // then
        sut
            .properties(includeNested = true)
            .map { it.name } shouldBeEqualTo listOf("sampleNestedProperty")
    }

    @Test
    fun `interface-with-property includeNested false`() {
        // given
        val sut = getSut("interface-with-property")
            .interfaces()
            .first()

        // then
        sut
            .properties(includeNested = false)
            .map { it.name } shouldBeEqualTo listOf("sampleNestedProperty")
    }

    @Test
    fun `interface-with-function includeNested true`() {
        // given
        val sut = getSut("interface-with-function")
            .interfaces()
            .first()

        // then
        sut
            .functions(includeNested = true)
            .map { it.name } shouldBeEqualTo listOf("sampleNestedFunction")
    }

    @Test
    fun `interface-with-function includeNested false`() {
        // given
        val sut = getSut("interface-with-function")
            .interfaces()
            .first()

        // then
        sut
            .functions(includeNested = false)
            .map { it.name } shouldBeEqualTo listOf("sampleNestedFunction")
    }

    @Test
    fun `interface-with-all-declarations includeNested true`() {
        // given
        val sut = getSut("interface-with-all-declarations")
            .interfaces()
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
    fun `interface-with-all-declarations includeNested false`() {
        // given
        val sut = getSut("interface-with-all-declarations")
            .interfaces()
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
    fun `interface-with-nested-classes includeNested true`() {
        // given
        val sut = getSut("interface-with-nested-classes")
            .interfaces()
            .first()

        // then
        sut
            .classes(includeNested = true)
            .map { it.name } shouldBeEqualTo listOf("SampleNestedClass1", "SampleNestedClass2", "SampleNestedClass3")
    }

    @Test
    fun `interface-with-nested-classes includeNested false`() {
        // given
        val sut = getSut("interface-with-nested-classes")
            .interfaces()
            .first()

        // then
        sut
            .classes(includeNested = false)
            .map { it.name } shouldBeEqualTo listOf("SampleNestedClass1")
    }

    @Test
    fun `interface-with-nested-interfaces includeNested true`() {
        // given
        val sut = getSut("interface-with-nested-interfaces")
            .interfaces()
            .first()

        // then
        sut
            .interfaces(includeNested = true)
            .map { it.name } shouldBeEqualTo listOf(
            "SampleNestedInterface1",
            "SampleNestedInterface2",
            "SampleNestedInterface3",
        )
    }

    @Test
    fun `interface-with-nested-interfaces includeNested false`() {
        // given
        val sut = getSut("interface-with-nested-interfaces")
            .interfaces()
            .first()

        // then
        sut
            .interfaces(includeNested = false)
            .map { it.name } shouldBeEqualTo listOf("SampleNestedInterface1")
    }

    @Test
    fun `interface-with-nested-objects includeNested true`() {
        // given
        val sut = getSut("interface-with-nested-objects")
            .interfaces()
            .first()

        // then
        sut
            .objects(includeNested = true)
            .map { it.name } shouldBeEqualTo listOf("SampleNestedObject1", "SampleNestedObject2", "SampleNestedObject3")
    }

    @Test
    fun `interface-with-nested-objects includeNested false`() {
        // given
        val sut = getSut("interface-with-nested-objects")
            .interfaces()
            .first()

        // then
        sut
            .objects(includeNested = false)
            .map { it.name } shouldBeEqualTo listOf("SampleNestedObject1")
    }

    @Test
    fun `interface-with-nested-properties includeNested true`() {
        // given
        val sut = getSut("interface-with-nested-properties")
            .interfaces()
            .first()

        // then
        sut
            .properties(includeNested = true)
            .map { it.name } shouldBeEqualTo listOf("sampleNestedProperty1", "sampleNestedProperty2")
    }

    @Test
    fun `interface-with-nested-properties includeNested false`() {
        // given
        val sut = getSut("interface-with-nested-properties")
            .interfaces()
            .first()

        // then
        sut
            .properties(includeNested = false)
            .map { it.name } shouldBeEqualTo listOf("sampleNestedProperty1")
    }

    @Test
    fun `interface-with-nested-functions includeNested false includeLocal true`() {
        // given
        val sut = getSut("interface-with-nested-functions")
            .interfaces()
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
    fun `interface-with-nested-declarations includeNested true`() {
        // given
        val sut = getSut("interface-with-nested-declarations")
            .interfaces()
            .first()

        // then
        val expected = listOf(
            "sampleNestedProperty1",
            "SampleNestedClass",
            "SampleNestedObject1",
            "SampleNestedInterface",
            "sampleNestedFunction",
            "SampleNestedObject2",
            "sampleNestedProperty2",
        )

        sut
            .declarations(includeNested = true)
            .map { it.name } shouldBeEqualTo expected
    }

    @Test
    fun `interface-with-nested-declarations includeNested false`() {
        // given
        val sut = getSut("interface-with-nested-declarations")
            .interfaces()
            .first()

        // then
        val expected =
            listOf(
                "sampleNestedProperty1",
                "SampleNestedClass",
                "SampleNestedInterface",
                "SampleNestedObject2",
            )

        sut
            .declarations(includeNested = false)
            .map { it.name } shouldBeEqualTo expected
    }

    @Test
    fun `interface-with-class-which-not-represents-type`() {
        // given
        val sut = getSut("interface-with-class-which-not-represents-type")
            .interfaces()
            .first()

        // then
        sut.representsType(SampleClass::class) shouldBeEqualTo false
    }

    @Test
    fun `interface-with-nested-properties-with-modifiers includeNested false`() {
        // given
        val sut = getSut("interface-with-nested-properties-with-modifiers")
            .interfaces()
            .first()

        // then
        sut.apply {
            containsProperty("sampleNestedProperty1", listOf(PRIVATE), includeNested = false) shouldBeEqualTo true
            containsProperty("sampleNestedProperty1", listOf(PUBLIC), includeNested = false) shouldBeEqualTo false
        }
    }

    @Test
    fun `interface-with-nested-properties-with-modifiers includeNested true`() {
        // given
        val sut = getSut("interface-with-nested-properties-with-modifiers")
            .interfaces()
            .first()

        // then
        sut.apply {
            containsProperty("sampleNestedProperty2", listOf(PRIVATE), includeNested = true) shouldBeEqualTo true
            containsProperty("sampleNestedProperty2", listOf(PUBLIC), includeNested = true) shouldBeEqualTo false
        }
    }

    @Test
    fun `interface-with-nested-classes-with-modifiers includeNested false`() {
        // given
        val sut = getSut("interface-with-nested-classes-with-modifiers")
            .interfaces()
            .first()

        // then
        sut.apply {
            containsClass("SampleNestedClass1", listOf(PRIVATE), includeNested = false) shouldBeEqualTo true
            containsClass("SampleNestedClass1", listOf(PUBLIC), includeNested = false) shouldBeEqualTo false
        }
    }

    @Test
    fun `interface-with-nested-classes-with-modifiers includeNested true`() {
        // given
        val sut = getSut("interface-with-nested-classes-with-modifiers")
            .interfaces()
            .first()

        // then
        sut.apply {
            containsClass("SampleNestedClass2", listOf(PRIVATE), includeNested = true) shouldBeEqualTo true
            containsClass("SampleNestedClass2", listOf(PUBLIC), includeNested = true) shouldBeEqualTo false
        }
    }

    @Test
    fun `interface-with-nested-interfaces-with-modifiers includeNested false`() {
        // given
        val sut = getSut("interface-with-nested-interfaces-with-modifiers")
            .interfaces()
            .first()

        // then
        sut.apply {
            containsInterface("SampleNestedInterface1", listOf(PRIVATE), includeNested = false) shouldBeEqualTo true
            containsInterface("SampleNestedInterface1", listOf(PUBLIC), includeNested = false) shouldBeEqualTo false
        }
    }

    @Test
    fun `interface-with-nested-interfaces-with-modifiers includeNested true`() {
        // given
        val sut = getSut("interface-with-nested-interfaces-with-modifiers")
            .interfaces()
            .first()

        // then
        sut.apply {
            containsInterface("SampleNestedInterface2", listOf(PRIVATE), includeNested = true) shouldBeEqualTo true
            containsInterface("SampleNestedInterface2", listOf(PUBLIC), includeNested = true) shouldBeEqualTo false
        }
    }

    @Test
    fun `interface-with-nested-objects-with-modifiers includeNested false`() {
        // given
        val sut = getSut("interface-with-nested-objects-with-modifiers")
            .interfaces()
            .first()

        // then
        sut.apply {
            containsObject("SampleNestedObject1", listOf(PRIVATE), includeNested = false) shouldBeEqualTo true
            containsObject("SampleNestedObject1", listOf(PUBLIC), includeNested = false) shouldBeEqualTo false
        }
    }

    @Test
    fun `interface-with-nested-objects-with-modifiers includeNested true`() {
        // given
        val sut = getSut("interface-with-nested-objects-with-modifiers")
            .interfaces()
            .first()

        // then
        sut.apply {
            containsObject("SampleNestedObject2", listOf(PRIVATE), includeNested = true) shouldBeEqualTo true
            containsObject("SampleNestedObject2", listOf(PUBLIC), includeNested = true) shouldBeEqualTo false
        }
    }

    private fun getSut(name: String) = TestSnippetProvider.getSnippetKoScope("core/kocomplexdeclaration/snippet/forinterface/$name.kt.txt")
}
