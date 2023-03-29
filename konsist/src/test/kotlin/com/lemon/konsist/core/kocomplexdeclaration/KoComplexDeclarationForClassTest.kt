package com.lemon.konsist.core.kocomplexdeclaration

import SampleClass
import SampleClass1
import com.lemon.konsist.TestSnippetProvider
import com.lemon.konsist.core.const.Modifier.PRIVATE
import com.lemon.konsist.core.const.Modifier.PUBLIC
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoComplexDeclarationForClassTest {
    @Test
    fun `class-with-class includeNested true`() {
        // given
        val sut =
            getSut("class-with-class")
                .classes()
                .first()

        // then
        sut
            .getClasses(includeNested = true)
            .map { it.name } shouldBeEqualTo listOf("SampleNestedClass")
    }

    @Test
    fun `class-with-class includeNested false`() {
        // given
        val sut =
            getSut("class-with-class")
                .classes()
                .first()

        // then
        sut
            .getClasses(includeNested = false)
            .map { it.name } shouldBeEqualTo listOf("SampleNestedClass")
    }

    @Test
    fun `class-with-interface includeNested true`() {
        // given
        val sut =
            getSut("class-with-interface")
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
        val sut =
            getSut("class-with-interface")
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
        val sut =
            getSut("class-with-object")
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
        val sut =
            getSut("class-with-object")
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
        val sut =
            getSut("class-with-property")
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
        val sut =
            getSut("class-with-property")
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
        val sut =
            getSut("class-with-function")
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
        val sut =
            getSut("class-with-function")
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
        val sut =
            getSut("class-with-all-declarations")
                .classes()
                .first()

        // then
        val expected = listOf(
            "SampleNestedClass",
            "SampleNestedInterface",
            "SampleNestedObject",
            "sampleNestedProperty",
            "sampleNestedFunction",
        )

        sut
            .declarations(includeNested = true)
            .map { it.name } shouldBeEqualTo expected
    }

    @Test
    fun `class-with-all-declarations includeNested false`() {
        // given
        val sut =
            getSut("class-with-all-declarations")
                .classes()
                .first()

        // then
        val expected = listOf(
            "SampleNestedClass",
            "SampleNestedInterface",
            "SampleNestedObject",
            "sampleNestedProperty",
            "sampleNestedFunction",
        )

        sut
            .declarations(includeNested = false)
            .map { it.name } shouldBeEqualTo expected
    }

    @Test
    fun `class-with-nested-classes includeNested true`() {
        // given
        val sut =
            getSut("class-with-nested-classes")
                .classes()
                .first()

        // then
        sut
            .getClasses(includeNested = true)
            .map { it.name } shouldBeEqualTo listOf("SampleNestedClass1", "SampleNestedClass2", "SampleNestedClass3")
    }

    @Test
    fun `class-with-nested-classes includeNested false`() {
        // given
        val sut =
            getSut("class-with-nested-classes")
                .classes()
                .first()

        // then
        sut
            .getClasses(includeNested = false)
            .map { it.name } shouldBeEqualTo listOf("SampleNestedClass1")
    }

    @Test
    fun `class-with-nested-interfaces includeNested true`() {
        // given
        val sut =
            getSut("class-with-nested-interfaces")
                .classes()
                .first()

        // then
        sut
            .interfaces(includeNested = true)
            .map { it.name } shouldBeEqualTo listOf("SampleNestedInterface3", "SampleNestedInterface1", "SampleNestedInterface2")
    }

    @Test
    fun `class-with-nested-interfaces includeNested false`() {
        // given
        val sut =
            getSut("class-with-nested-interfaces")
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
        val sut =
            getSut("class-with-nested-objects")
                .classes()
                .first()

        // then
        sut
            .objects(includeNested = true)
            .map { it.name } shouldBeEqualTo listOf("SampleNestedObject3", "SampleNestedObject1", "SampleNestedObject2")
    }

    @Test
    fun `class-with-nested-objects includeNested false`() {
        // given
        val sut =
            getSut("class-with-nested-objects")
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
        val sut =
            getSut("class-with-nested-properties")
                .classes()
                .first()

        // then
        sut
            .properties(includeNested = true)
            .map { it.name } shouldBeEqualTo listOf("sampleNestedProperty2", "sampleNestedProperty1")
    }

    @Test
    fun `class-with-nested-properties includeNested false`() {
        // given
        val sut =
            getSut("class-with-nested-properties")
                .classes()
                .first()

        // then
        sut
            .properties(includeNested = false)
            .map { it.name } shouldBeEqualTo listOf("sampleNestedProperty1")
    }

    @Test
    fun `class-with-nested-functions includeNested true`() {
        // given
        val sut =
            getSut("class-with-nested-functions")
                .classes()
                .first()

        // then
        sut
            .functions(includeNested = true)
            .map { it.name } shouldBeEqualTo listOf("sampleNestedFunction1", "sampleNestedFunction2", "sampleLocalFunction")
    }

    @Test
    fun `class-with-nested-functions includeNested false`() {
        // given
        val sut =
            getSut("class-with-nested-functions")
                .classes()
                .first()

        // then
        sut
            .functions(includeNested = false)
            .map { it.name } shouldBeEqualTo listOf("sampleNestedFunction1")
    }

    @Test
    fun `class-with-nested-declarations includeNested true`() {
        // given
        val sut =
            getSut("class-with-nested-declarations")
                .classes()
                .first()

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
            .declarations(includeNested = true)
            .map { it.name } shouldBeEqualTo expected
    }

    @Test
    fun `class-with-nested-declarations includeNested false`() {
        // given
        val sut =
            getSut("class-with-nested-declarations")
                .classes()
                .first()

        // then
        val expected =
            listOf("SampleNestedClass", "SampleNestedInterface", "SampleNestedObject", "sampleNestedProperty", "sampleNestedFunction")

        sut
            .declarations(includeNested = false)
            .map { it.name } shouldBeEqualTo expected
    }

    @Test
    fun `class-with-class-which-represents-type`() {
        // given
        val sut =
            getSut("class-with-class-which-represents-type")
                .classes()
                .first()

        // then
        sut.representsType(SampleClass::class) shouldBeEqualTo true
    }

    @Test
    fun `class-with-class-which-not-represents-type`() {
        // given
        val sut =
            getSut("class-with-class-which-not-represents-type")
                .classes()
                .first()

        // then
        sut.representsType(SampleClass1::class) shouldBeEqualTo false
    }

    @Test
    fun `class-with-function-with-modifier`() {
        // given
        val sut =
            getSut("class-with-function-with-modifier")
                .classes()
                .first()

        // then
        sut.apply {
            hasFunction("sampleNestedFunction", PRIVATE) shouldBeEqualTo true
            hasFunction("sampleNestedFunction", PUBLIC) shouldBeEqualTo false
        }
    }

    @Test
    fun `class-with-property-with-modifier`() {
        // given
        val sut =
            getSut("class-with-property-with-modifier")
                .classes()
                .first()

        // then
        sut.apply {
            hasProperty("sampleNestedProperty", PRIVATE) shouldBeEqualTo true
            hasProperty("sampleNestedProperty", PUBLIC) shouldBeEqualTo false
        }
    }

    private fun getSut(name: String) = TestSnippetProvider.getSnippetKoScope("core/kocomplexdeclaration/snippet/forclass/$name.kt.txt")
}
