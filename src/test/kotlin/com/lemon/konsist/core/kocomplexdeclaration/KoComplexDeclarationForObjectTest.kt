package com.lemon.konsist.core.kocomplexdeclaration

import SampleClass
import com.lemon.konsist.TestSnippetProvider
import com.lemon.konsist.core.const.Modifier.PRIVATE
import com.lemon.konsist.core.const.Modifier.PUBLIC
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoComplexDeclarationForObjectTest {
    @Test
    fun `object-with-class includeNested true`() {
        // given
        val sut = getSut("object-with-class")

        // then
        sut
            .objects()
            .first()
            .getClasses(includeNested = true)
            .map { it.name } shouldBeEqualTo listOf("SampleNestedClass")
    }

    @Test
    fun `object-with-class includeNested false`() {
        // given
        val sut = getSut("object-with-class")

        // then
        sut
            .objects()
            .first()
            .getClasses(includeNested = false)
            .map { it.name } shouldBeEqualTo listOf("SampleNestedClass")
    }

    @Test
    fun `object-with-interface includeNested true`() {
        // given
        val sut = getSut("object-with-interface")

        // then
        sut
            .objects()
            .first()
            .interfaces(includeNested = true)
            .map { it.name } shouldBeEqualTo listOf("SampleNestedInterface")
    }

    @Test
    fun `object-with-interface includeNested false`() {
        // given
        val sut = getSut("object-with-interface")

        // then
        sut
            .objects()
            .first()
            .interfaces(includeNested = false)
            .map { it.name } shouldBeEqualTo listOf("SampleNestedInterface")
    }

    @Test
    fun `object-with-object includeNested true`() {
        // given
        val sut = getSut("object-with-object")

        // then
        sut
            .objects()
            .first()
            .objects(includeNested = true)
            .map { it.name } shouldBeEqualTo listOf("SampleNestedObject")
    }

    @Test
    fun `object-with-object includeNested false`() {
        // given
        val sut = getSut("object-with-object")

        // then
        sut
            .objects()
            .first()
            .objects(includeNested = false)
            .map { it.name } shouldBeEqualTo listOf("SampleNestedObject")
    }

    @Test
    fun `object-with-property includeNested true`() {
        // given
        val sut = getSut("object-with-property")

        // then
        sut
            .objects()
            .first()
            .properties(includeNested = true)
            .map { it.name } shouldBeEqualTo listOf("sampleNestedProperty")
    }

    @Test
    fun `object-with-property includeNested false`() {
        // given
        val sut = getSut("object-with-property")

        // then
        sut
            .objects()
            .first()
            .properties(includeNested = false)
            .map { it.name } shouldBeEqualTo listOf("sampleNestedProperty")
    }

    @Test
    fun `object-with-function includeNested true`() {
        // given
        val sut = getSut("object-with-function")

        // then
        sut
            .objects()
            .first()
            .functions(includeNested = true)
            .map { it.name } shouldBeEqualTo listOf("sampleNestedFunction")
    }

    @Test
    fun `object-with-function includeNested false`() {
        // given
        val sut = getSut("object-with-function")

        // then
        sut
            .objects()
            .first()
            .functions(includeNested = false)
            .map { it.name } shouldBeEqualTo listOf("sampleNestedFunction")
    }

    @Test
    fun `object-with-all-declarations includeNested true`() {
        // given
        val sut = getSut("object-with-all-declarations")

        // then
        val expected = listOf(
            "SampleNestedClass",
            "SampleNestedInterface",
            "SampleNestedObject",
            "sampleNestedProperty",
            "sampleNestedFunction",
        )

        sut
            .objects()
            .first()
            .declarations(includeNested = true)
            .map { it.name } shouldBeEqualTo expected
    }

    @Test
    fun `object-with-all-declarations includeNested false`() {
        // given
        val sut = getSut("object-with-all-declarations")

        // then
        val expected = listOf(
            "SampleNestedClass",
            "SampleNestedInterface",
            "SampleNestedObject",
            "sampleNestedProperty",
            "sampleNestedFunction",
        )

        sut
            .objects()
            .first()
            .declarations(includeNested = false)
            .map { it.name } shouldBeEqualTo expected
    }

    @Test
    fun `object-with-nested-classes includeNested true`() {
        // given
        val sut = getSut("object-with-nested-classes")

        // then
        sut
            .objects()
            .first()
            .getClasses(includeNested = true)
            .map { it.name } shouldBeEqualTo listOf("SampleNestedClass1", "SampleNestedClass2", "SampleNestedClass3")
    }

    @Test
    fun `object-with-nested-classes includeNested false`() {
        // given
        val sut = getSut("object-with-nested-classes")

        // then
        sut
            .objects()
            .first()
            .getClasses(includeNested = false)
            .map { it.name } shouldBeEqualTo listOf("SampleNestedClass1")
    }

    @Test
    fun `object-with-nested-interfaces includeNested true`() {
        // given
        val sut = getSut("object-with-nested-interfaces")

        // then
        sut
            .objects()
            .first()
            .interfaces(includeNested = true)
            .map { it.name } shouldBeEqualTo listOf("SampleNestedInterface3", "SampleNestedInterface1", "SampleNestedInterface2")
    }

    @Test
    fun `object-with-nested-interfaces includeNested false`() {
        // given
        val sut = getSut("object-with-nested-interfaces")

        // then
        sut
            .objects()
            .first()
            .interfaces(includeNested = false)
            .map { it.name } shouldBeEqualTo listOf("SampleNestedInterface1")
    }

    @Test
    fun `object-with-nested-objects includeNested true`() {
        // given
        val sut = getSut("object-with-nested-objects")

        // then
        sut
            .objects()
            .first()
            .objects(includeNested = true)
            .map { it.name } shouldBeEqualTo listOf("SampleNestedObject3", "SampleNestedObject1", "SampleNestedObject2")
    }

    @Test
    fun `object-with-nested-objects includeNested false`() {
        // given
        val sut = getSut("object-with-nested-objects")

        // then
        sut
            .objects()
            .first()
            .objects(includeNested = false)
            .map { it.name } shouldBeEqualTo listOf("SampleNestedObject1")
    }

    @Test
    fun `object-with-nested-properties includeNested true`() {
        // given
        val sut = getSut("object-with-nested-properties")

        // then
        sut
            .objects()
            .first()
            .properties(includeNested = true)
            .map { it.name } shouldBeEqualTo listOf("sampleNestedProperty2", "sampleNestedProperty1")
    }

    @Test
    fun `object-with-nested-properties includeNested false`() {
        // given
        val sut = getSut("object-with-nested-properties")

        // then
        sut
            .objects()
            .first()
            .properties(includeNested = false)
            .map { it.name } shouldBeEqualTo listOf("sampleNestedProperty1")
    }

    @Test
    fun `object-with-nested-functions includeNested true`() {
        // given
        val sut = getSut("object-with-nested-functions")

        // then
        sut
            .objects()
            .first()
            .functions(includeNested = true)
            .map { it.name } shouldBeEqualTo listOf("sampleNestedFunction1", "sampleNestedFunction2", "sampleLocalFunction")
    }

    @Test
    fun `object-with-nested-functions includeNested false`() {
        // given
        val sut = getSut("object-with-nested-functions")

        // then
        sut
            .objects()
            .first()
            .functions(includeNested = false)
            .map { it.name } shouldBeEqualTo listOf("sampleNestedFunction1")
    }

    @Test
    fun `object-with-nested-declarations includeNested true`() {
        // given
        val sut = getSut("object-with-nested-declarations")

        // then
        val expected = listOf(
            "SampleClass",
            "SampleNestedObject",
            "SampleInterface",
            "sampleNestedFunction",
            "SampleObject",
            "sampleNestedProperty",
            "sampleNestedProperty",
            "sampleNestedFunction",
        )
        sut
            .objects()
            .first()
            .declarations(includeNested = true)
            .map { it.name } shouldBeEqualTo expected
    }

    @Test
    fun `object-with-nested-declarations includeNested false`() {
        // given
        val sut = getSut("object-with-nested-declarations")

        // then
        val expected = listOf("SampleClass", "SampleInterface", "SampleObject", "sampleNestedProperty", "sampleNestedFunction")

        sut
            .objects()
            .first()
            .declarations(includeNested = false)
            .map { it.name } shouldBeEqualTo expected
    }

    @Test
    fun `object-with-class-which-not-represents-type`() {
        // given
        val sut = getSut("object-with-class-which-not-represents-type")

        // then
        sut
            .objects()
            .first()
            .representsType(SampleClass::class) shouldBeEqualTo false
    }

    @Test
    fun `object-with-function-with-modifier`() {
        // given
        val sut = getSut("object-with-function-with-modifier")

        // then
        sut
            .objects()
            .first()
            .apply {
                hasFunction("sampleNestedFunction", PRIVATE) shouldBeEqualTo true
                hasFunction("sampleNestedFunction", PUBLIC) shouldBeEqualTo false
            }
    }

    @Test
    fun `object-with-property-with-modifier`() {
        // given
        val sut = getSut("object-with-property-with-modifier")

        // then
        sut
            .objects()
            .first()
            .apply {
                hasProperty("sampleNestedProperty", PRIVATE) shouldBeEqualTo true
                hasProperty("sampleNestedProperty", PUBLIC) shouldBeEqualTo false
            }
    }

    private fun getSut(name: String) = TestSnippetProvider.getSnippetKoScope("core/kocomplexdeclaration/snippet/forobject/$name.kt.txt")
}
