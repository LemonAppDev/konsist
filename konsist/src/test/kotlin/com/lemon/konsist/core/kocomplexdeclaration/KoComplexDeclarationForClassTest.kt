package com.lemon.konsist.core.kocomplexdeclaration

import SampleClass
import SampleClass1
import com.lemon.konsist.TestSnippetProvider
import com.lemon.konsist.core.const.KoModifier.PRIVATE
import com.lemon.konsist.core.const.KoModifier.PUBLIC
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoComplexDeclarationForClassTest {
    @Test
    fun `class-with-class includeNested true`() {
        // given
        val sut = getSut("class-with-class")
            .classes()
            .first()

        // then
        sut
            .classes(includeNested = true)
            .map { it.name } shouldBeEqualTo listOf("SampleNestedClass")
    }

    @Test
    fun `class-with-class includeNested false`() {
        // given
        val sut = getSut("class-with-class")
            .classes()
            .first()

        // then
        sut
            .classes(includeNested = false)
            .map { it.name } shouldBeEqualTo listOf("SampleNestedClass")
    }

    @Test
    fun `class-with-interface includeNested true`() {
        // given
        val sut = getSut("class-with-interface")
            .classes()
            .first()

        // then
        sut
            .interfaces(includeNested = true)
            .map { it.name } shouldBeEqualTo listOf("SampleNestedInterface")
    }

    @Test
    fun `class-with-interface includeNested false`() {
        // given
        val sut = getSut("class-with-interface")
            .classes()
            .first()

        // then
        sut
            .interfaces(includeNested = false)
            .map { it.name } shouldBeEqualTo listOf("SampleNestedInterface")
    }

    @Test
    fun `class-with-object includeNested true`() {
        // given
        val sut = getSut("class-with-object")
            .classes()
            .first()

        // then
        sut
            .objects(includeNested = true)
            .map { it.name } shouldBeEqualTo listOf("SampleNestedObject")
    }

    @Test
    fun `class-with-object includeNested false`() {
        // given
        val sut = getSut("class-with-object")
            .classes()
            .first()

        // then
        sut
            .objects(includeNested = false)
            .map { it.name } shouldBeEqualTo listOf("SampleNestedObject")
    }

    @Test
    fun `class-with-property includeNested true`() {
        // given
        val sut = getSut("class-with-property")
            .classes()
            .first()

        // then
        sut
            .properties(includeNested = true)
            .map { it.name } shouldBeEqualTo listOf("sampleNestedProperty")
    }

    @Test
    fun `class-with-property includeNested false`() {
        // given
        val sut = getSut("class-with-property")
            .classes()
            .first()

        // then
        sut
            .properties(includeNested = false)
            .map { it.name } shouldBeEqualTo listOf("sampleNestedProperty")
    }

    @Test
    fun `class-with-function includeNested true`() {
        // given
        val sut = getSut("class-with-function")
            .classes()
            .first()

        // then
        sut
            .functions(includeNested = true)
            .map { it.name } shouldBeEqualTo listOf("sampleNestedFunction")
    }

    @Test
    fun `class-with-function includeNested false`() {
        // given
        val sut = getSut("class-with-function")
            .classes()
            .first()

        // then
        sut
            .functions(includeNested = false)
            .map { it.name } shouldBeEqualTo listOf("sampleNestedFunction")
    }

    @Test
    fun `class-with-all-declarations includeNested true`() {
        // given
        val sut = getSut("class-with-all-declarations")
            .classes()
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
    fun `class-with-all-declarations includeNested false`() {
        // given
        val sut = getSut("class-with-all-declarations")
            .classes()
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
    fun `class-with-nested-classes includeNested true`() {
        // given
        val sut = getSut("class-with-nested-classes")
            .classes()
            .first()

        // then
        sut
            .classes(includeNested = true)
            .map { it.name } shouldBeEqualTo listOf("SampleNestedClass1", "SampleNestedClass2", "SampleNestedClass3")
    }

    @Test
    fun `class-with-nested-classes includeNested false`() {
        // given
        val sut = getSut("class-with-nested-classes")
            .classes()
            .first()

        // then
        sut
            .classes(includeNested = false)
            .map { it.name } shouldBeEqualTo listOf("SampleNestedClass1")
    }

    @Test
    fun `class-with-nested-interfaces includeNested true`() {
        // given
        val sut = getSut("class-with-nested-interfaces")
            .classes()
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
    fun `class-with-nested-interfaces includeNested false`() {
        // given
        val sut = getSut("class-with-nested-interfaces")
            .classes()
            .first()

        // then
        sut
            .interfaces(includeNested = false)
            .map { it.name } shouldBeEqualTo listOf("SampleNestedInterface1")
    }

    @Test
    fun `class-with-nested-objects includeNested true`() {
        // given
        val sut = getSut("class-with-nested-objects")
            .classes()
            .first()

        // then
        sut
            .objects(includeNested = true)
            .map { it.name } shouldBeEqualTo listOf(
            "SampleNestedObject1",
            "SampleNestedObject2",
            "SampleNestedObject3",
        )
    }

    @Test
    fun `class-with-nested-objects includeNested false`() {
        // given
        val sut = getSut("class-with-nested-objects")
            .classes()
            .first()

        // then
        sut
            .objects(includeNested = false)
            .map { it.name } shouldBeEqualTo listOf("SampleNestedObject1")
    }

    @Test
    fun `class-with-nested-properties includeNested true`() {
        // given
        val sut = getSut("class-with-nested-properties")
            .classes()
            .first()

        // then
        sut
            .properties(includeNested = true)
            .map { it.name } shouldBeEqualTo listOf("sampleNestedProperty1", "sampleNestedProperty2")
    }

    @Test
    fun `class-with-nested-properties includeNested false`() {
        // given
        val sut = getSut("class-with-nested-properties")
            .classes()
            .first()

        // then
        sut
            .properties(includeNested = false)
            .map { it.name } shouldBeEqualTo listOf("sampleNestedProperty1")
    }

    @Test
    fun `class-with-nested-functions includeNested true and includeLocal true`() {
        // given
        val sut = getSut("class-with-nested-functions")
            .classes()
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
    fun `class-with-nested-functions includeNested true and includeLocal false`() {
        // given
        val sut = getSut("class-with-nested-functions")
            .classes()
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
    fun `class-with-nested-functions includeNested false and includeLocal true`() {
        // given
        val sut = getSut("class-with-nested-functions")
            .classes()
            .first()

        // then
        sut
            .functions(includeNested = false, includeLocal = true)
            .map { it.name } shouldBeEqualTo listOf("sampleFunction", "sampleLocalFunction1")
    }

    @Test
    fun `class-with-nested-functions includeNested false and includeLocal false`() {
        // given
        val sut = getSut("class-with-nested-functions")
            .classes()
            .first()

        // then
        sut
            .functions(includeNested = false, includeLocal = false)
            .map { it.name } shouldBeEqualTo listOf("sampleFunction")
    }

    @Test
    fun `class-with-nested-functions includeNested false includeLocal true`() {
        // given
        val sut = getSut("class-with-nested-functions")
            .classes()
            .first()

        // then
        sut
            .functions(includeNested = false, includeLocal = true)
            .map { it.name } shouldBeEqualTo listOf("sampleFunction", "sampleLocalFunction1")
    }

    @Test
    fun `class-with-nested-functions includeNested false includeLocal false`() {
        // given
        val sut = getSut("class-with-nested-functions")
            .classes()
            .first()

        // then
        sut
            .functions(includeNested = false, includeLocal = false)
            .map { it.name } shouldBeEqualTo listOf("sampleFunction")
    }

    @Test
    fun `class-with-nested-declarations includeNested true`() {
        // given
        val sut = getSut("class-with-nested-declarations")
            .classes()
            .first()

        // then
        val expected = listOf(
            "sampleNestedProperty",
            "SampleNestedClass",
            "SampleNestedObject1",
            "sampleNestedFunction",
            "SampleNestedInterface",
            "sampleNestedFunction",
            "SampleNestedObject2",
            "sampleNestedProperty",
        )
        sut
            .declarations(includeNested = true)
            .map { it.name } shouldBeEqualTo expected
    }

    @Test
    fun `class-with-nested-declarations includeNested false`() {
        // given
        val sut = getSut("class-with-nested-declarations")
            .classes()
            .first()

        // then
        val expected =
            listOf(
                "sampleNestedProperty",
                "SampleNestedClass",
                "sampleNestedFunction",
                "SampleNestedInterface",
                "SampleNestedObject2",
            )

        sut
            .declarations(includeNested = false)
            .map { it.name } shouldBeEqualTo expected
    }

    @Test
    fun `class-with-class-which-represents-type`() {
        // given
        val sut = getSut("class-with-class-which-represents-type")
            .classes()
            .first()

        // then
        sut.representsType(SampleClass::class) shouldBeEqualTo true
    }

    @Test
    fun `class-with-class-which-not-represents-type`() {
        // given
        val sut = getSut("class-with-class-which-not-represents-type")
            .classes()
            .first()

        // then
        sut.representsType(SampleClass1::class) shouldBeEqualTo false
    }

    @Test
    fun `class-with-nested-functions-with-modifiers includeNested false`() {
        // given
        val sut = getSut("class-with-nested-functions-with-modifiers")
            .classes()
            .first()

        // then
        sut.apply {
            containsFunction("sampleNestedFunction", listOf(PRIVATE), includeNested = true) shouldBeEqualTo true
            containsFunction("sampleNestedFunction", listOf(PUBLIC), includeNested = true) shouldBeEqualTo false
        }
    }

    @Test
    fun `class-with-nested-functions-with-modifiers includeNested true`() {
        // given
        val sut = getSut("class-with-nested-functions-with-modifiers")
            .classes()
            .first()

        // then
        sut.apply {
            containsFunction("sampleNestedFunction", listOf(PRIVATE), includeNested = true) shouldBeEqualTo true
            containsFunction("sampleNestedFunction2", listOf(PUBLIC), includeNested = true) shouldBeEqualTo false
        }
    }

    @Test
    fun `class-with-nested-properties-with-modifiers includeNested false`() {
        // given
        val sut = getSut("class-with-nested-properties-with-modifiers")
            .classes()
            .first()

        // then
        sut.apply {
            containsProperty("sampleNestedProperty1", listOf(PRIVATE), includeNested = false) shouldBeEqualTo true
            containsProperty("sampleNestedProperty1", listOf(PUBLIC), includeNested = false) shouldBeEqualTo false
        }
    }

    @Test
    fun `class-with-nested-properties-with-modifiers includeNested true`() {
        // given
        val sut = getSut("class-with-nested-properties-with-modifiers")
            .classes()
            .first()

        // then
        sut.apply {
            containsProperty("sampleNestedProperty2", listOf(PRIVATE), includeNested = true) shouldBeEqualTo true
            containsProperty("sampleNestedProperty2", listOf(PUBLIC), includeNested = true) shouldBeEqualTo false
        }
    }

    @Test
    fun `class-with-nested-classes-with-modifiers includeNested false`() {
        // given
        val sut = getSut("class-with-nested-classes-with-modifiers")
            .classes()
            .first()

        // then
        sut.apply {
            containsClass("SampleNestedClass1", listOf(PRIVATE), includeNested = false) shouldBeEqualTo true
            containsClass("SampleNestedClass1", listOf(PUBLIC), includeNested = false) shouldBeEqualTo false
        }
    }

    @Test
    fun `class-with-nested-classes-with-modifiers includeNested true`() {
        // given
        val sut = getSut("class-with-nested-classes-with-modifiers")
            .classes()
            .first()

        // then
        sut.apply {
            containsClass("SampleNestedClass2", listOf(PRIVATE), includeNested = true) shouldBeEqualTo true
            containsClass("SampleNestedClass2", listOf(PUBLIC), includeNested = true) shouldBeEqualTo false
        }
    }

    @Test
    fun `class-with-nested-interfaces-with-modifiers includeNested false`() {
        // given
        val sut = getSut("class-with-nested-interfaces-with-modifiers")
            .classes()
            .first()

        // then
        sut.apply {
            containsInterface("SampleNestedInterface1", listOf(PRIVATE), includeNested = false) shouldBeEqualTo true
            containsInterface("SampleNestedInterface1", listOf(PUBLIC), includeNested = false) shouldBeEqualTo false
        }
    }

    @Test
    fun `class-with-nested-interfaces-with-modifiers includeNested true`() {
        // given
        val sut = getSut("class-with-nested-interfaces-with-modifiers")
            .classes()
            .first()

        // then
        sut.apply {
            containsInterface("SampleNestedInterface2", listOf(PRIVATE), includeNested = true) shouldBeEqualTo true
            containsInterface("SampleNestedInterface2", listOf(PUBLIC), includeNested = true) shouldBeEqualTo false
        }
    }

    @Test
    fun `class-with-nested-objects-with-modifiers includeNested false`() {
        // given
        val sut = getSut("class-with-nested-objects-with-modifiers")
            .classes()
            .first()

        // then
        sut.apply {
            containsObject("SampleNestedObject1", listOf(PRIVATE), includeNested = false) shouldBeEqualTo true
            containsObject("SampleNestedObject1", listOf(PUBLIC), includeNested = false) shouldBeEqualTo false
        }
    }

    @Test
    fun `class-with-nested-objects-with-modifiers includeNested true`() {
        // given
        val sut = getSut("class-with-nested-objects-with-modifiers")
            .classes()
            .first()

        // then
        sut.apply {
            containsObject("SampleNestedObject2", listOf(PRIVATE), includeNested = true) shouldBeEqualTo true
            containsObject("SampleNestedObject2", listOf(PUBLIC), includeNested = true) shouldBeEqualTo false
        }
    }

    private fun getSut(name: String) = TestSnippetProvider.getSnippetKoScope("core/kocomplexdeclaration/snippet/forclass/$name.kt.txt")
}
