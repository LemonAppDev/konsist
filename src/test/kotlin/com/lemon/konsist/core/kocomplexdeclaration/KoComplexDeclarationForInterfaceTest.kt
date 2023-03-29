package com.lemon.konsist.core.kocomplexdeclaration

import SampleClass
import com.lemon.konsist.TestSnippetProvider
import com.lemon.konsist.core.const.Modifier
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoComplexDeclarationForInterfaceTest {
    @Test
    fun `interface-with-class includeNested true`() {
        // given
        val sut = getSut("interface-with-class")

        // then
        sut
            .interfaces()
            .first()
            .getClasses(includeNested = true)
            .map { it.name } shouldBeEqualTo listOf("SampleNestedClass")
    }

    @Test
    fun `interface-with-class includeNested false`() {
        // given
        val sut = getSut("interface-with-class")

        // then
        sut
            .interfaces()
            .first()
            .getClasses(includeNested = false)
            .map { it.name } shouldBeEqualTo listOf("SampleNestedClass")
    }

    @Test
    fun `interface-with-interface includeNested true`() {
        // given
        val sut = getSut("interface-with-interface")

        // then
        sut
            .interfaces()
            .first()
            .interfaces(includeNested = true)
            .map { it.name } shouldBeEqualTo listOf("SampleNestedInterface")
    }

    @Test
    fun `interface-with-interface includeNested false`() {
        // given
        val sut = getSut("interface-with-interface")

        // then
        sut
            .interfaces()
            .first()
            .interfaces(includeNested = false)
            .map { it.name } shouldBeEqualTo listOf("SampleNestedInterface")
    }

    @Test
    fun `interface-with-object includeNested true`() {
        // given
        val sut = getSut("interface-with-object")

        // then
        sut
            .interfaces()
            .first()
            .objects(includeNested = true)
            .map { it.name } shouldBeEqualTo listOf("SampleNestedObject")
    }

    @Test
    fun `interface-with-object includeNested false`() {
        // given
        val sut = getSut("interface-with-object")

        // then
        sut
            .interfaces()
            .first()
            .objects(includeNested = false)
            .map { it.name } shouldBeEqualTo listOf("SampleNestedObject")
    }

    @Test
    fun `interface-with-property includeNested true`() {
        // given
        val sut = getSut("interface-with-property")

        // then
        sut
            .interfaces()
            .first()
            .properties(includeNested = true)
            .map { it.name } shouldBeEqualTo listOf("sampleNestedProperty")
    }

    @Test
    fun `interface-with-property includeNested false`() {
        // given
        val sut = getSut("interface-with-property")

        // then
        sut
            .interfaces()
            .first()
            .properties(includeNested = false)
            .map { it.name } shouldBeEqualTo listOf("sampleNestedProperty")
    }

    @Test
    fun `interface-with-function includeNested true`() {
        // given
        val sut = getSut("interface-with-function")

        // then
        sut
            .interfaces()
            .first()
            .functions(includeNested = true)
            .map { it.name } shouldBeEqualTo listOf("sampleNestedFunction")
    }

    @Test
    fun `interface-with-function includeNested false`() {
        // given
        val sut = getSut("interface-with-function")

        // then
        sut
            .interfaces()
            .first()
            .functions(includeNested = false)
            .map { it.name } shouldBeEqualTo listOf("sampleNestedFunction")
    }

    @Test
    fun `interface-with-all-declarations includeNested true`() {
        // given
        val sut = getSut("interface-with-all-declarations")

        // then
        val expected = listOf(
            "SampleNestedClass",
            "SampleNestedInterface",
            "SampleNestedObject",
            "sampleNestedProperty",
            "sampleNestedFunction",
        )

        sut
            .interfaces()
            .first()
            .declarations(includeNested = true)
            .map { it.name } shouldBeEqualTo expected
    }

    @Test
    fun `interface-with-all-declarations includeNested false`() {
        // given
        val sut = getSut("interface-with-all-declarations")

        // then
        val expected = listOf(
            "SampleNestedClass",
            "SampleNestedInterface",
            "SampleNestedObject",
            "sampleNestedProperty",
            "sampleNestedFunction",
        )

        sut
            .interfaces()
            .first()
            .declarations(includeNested = false)
            .map { it.name } shouldBeEqualTo expected
    }

    @Test
    fun `interface-with-nested-classes includeNested true`() {
        // given
        val sut = getSut("interface-with-nested-classes")

        // then
        sut
            .interfaces()
            .first()
            .getClasses(includeNested = true)
            .map { it.name } shouldBeEqualTo listOf("SampleNestedClass1", "SampleNestedClass2", "SampleNestedClass3")
    }

    @Test
    fun `interface-with-nested-classes includeNested false`() {
        // given
        val sut = getSut("interface-with-nested-classes")

        // then
        sut
            .interfaces()
            .first()
            .getClasses(includeNested = false)
            .map { it.name } shouldBeEqualTo listOf("SampleNestedClass1")
    }

    @Test
    fun `interface-with-nested-interfaces includeNested true`() {
        // given
        val sut = getSut("interface-with-nested-interfaces")

        // then
        sut
            .interfaces()
            .first()
            .interfaces(includeNested = true)
            .map { it.name } shouldBeEqualTo listOf("SampleNestedInterface3", "SampleNestedInterface1", "SampleNestedInterface2")
    }

    @Test
    fun `interface-with-nested-interfaces includeNested false`() {
        // given
        val sut = getSut("interface-with-nested-interfaces")

        // then
        sut
            .interfaces()
            .first()
            .interfaces(includeNested = false)
            .map { it.name } shouldBeEqualTo listOf("SampleNestedInterface1")
    }

    @Test
    fun `interface-with-nested-objects includeNested true`() {
        // given
        val sut = getSut("interface-with-nested-objects")

        // then
        sut
            .interfaces()
            .first()
            .objects(includeNested = true)
            .map { it.name } shouldBeEqualTo listOf("SampleNestedObject3", "SampleNestedObject1", "SampleNestedObject2")
    }

    @Test
    fun `interface-with-nested-objects includeNested false`() {
        // given
        val sut = getSut("interface-with-nested-objects")

        // then
        sut
            .interfaces()
            .first()
            .objects(includeNested = false)
            .map { it.name } shouldBeEqualTo listOf("SampleNestedObject1")
    }

    @Test
    fun `interface-with-nested-properties includeNested true`() {
        // given
        val sut = getSut("interface-with-nested-properties")

        // then
        sut
            .interfaces()
            .first()
            .properties(includeNested = true)
            .map { it.name } shouldBeEqualTo listOf("sampleNestedProperty2", "sampleNestedProperty1")
    }

    @Test
    fun `interface-with-nested-properties includeNested false`() {
        // given
        val sut = getSut("interface-with-nested-properties")

        // then
        sut
            .interfaces()
            .first()
            .properties(includeNested = false)
            .map { it.name } shouldBeEqualTo listOf("sampleNestedProperty1")
    }

    @Test
    fun `interface-with-nested-functions includeNested true`() {
        // given
        val sut = getSut("interface-with-nested-functions")

        // then
        sut
            .interfaces()
            .first()
            .functions(includeNested = true)
            .map { it.name } shouldBeEqualTo listOf("sampleNestedFunction1", "sampleNestedFunction2", "sampleLocalFunction")
    }

    @Test
    fun `interface-with-nested-functions includeNested false`() {
        // given
        val sut = getSut("interface-with-nested-functions")

        // then
        sut
            .interfaces()
            .first()
            .functions(includeNested = false)
            .map { it.name } shouldBeEqualTo listOf("sampleNestedFunction1")
    }

    @Test
    fun `interface-with-nested-declarations includeNested true`() {
        // given
        val sut = getSut("interface-with-nested-declarations")

        // then
        val expected = listOf(
            "SampleNestedClass",
            "SampleNestedObject",
            "SampleNestedInterface",
            "sampleNestedFunction",
            "SampleNestedObject",
            "sampleNestedProperty",
            "sampleNestedProperty",
            "sampleNestedFunction",
        )
        sut
            .interfaces()
            .first()
            .declarations(includeNested = true)
            .map { it.name } shouldBeEqualTo expected
    }

    @Test
    fun `interface-with-nested-declarations includeNested false`() {
        // given
        val sut = getSut("interface-with-nested-declarations")

        // then
        val expected =
            listOf("SampleNestedClass", "SampleNestedInterface", "SampleNestedObject", "sampleNestedProperty", "sampleNestedFunction")

        sut
            .interfaces()
            .first()
            .declarations(includeNested = false)
            .map { it.name } shouldBeEqualTo expected
    }

    @Test
    fun `interface-with-class-which-not-represents-type`() {
        // given
        val sut = getSut("interface-with-class-which-not-represents-type")

        // then
        sut
            .interfaces()
            .first()
            .representsType(SampleClass::class) shouldBeEqualTo false
    }

    @Test
    fun `interface-with-function-with-modifier`() {
        // given
        val sut = getSut("interface-with-function-with-modifier")

        // then
        sut
            .interfaces()
            .first()
            .apply {
                hasFunction("sampleNestedFunction", Modifier.PRIVATE) shouldBeEqualTo true
                hasFunction("sampleNestedFunction", Modifier.PUBLIC) shouldBeEqualTo false
            }
    }

    @Test
    fun `interface-with-property-with-modifier`() {
        // given
        val sut = getSut("interface-with-property-with-modifier")

        // then
        sut
            .interfaces()
            .first()
            .apply {
                hasProperty("sampleNestedProperty", Modifier.PRIVATE) shouldBeEqualTo true
                hasProperty("sampleNestedProperty", Modifier.PUBLIC) shouldBeEqualTo false
            }
    }

    private fun getSut(name: String) = TestSnippetProvider.getSnippetKoScope("core/kocomplexdeclaration/snippet/forinterface/$name.kt.txt")
}
