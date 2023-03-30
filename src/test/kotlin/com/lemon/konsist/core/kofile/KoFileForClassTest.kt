package com.lemon.konsist.core.kofile

import com.lemon.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoFileForClassTest {
    @Test
    fun `file-with-one-class includeNested true`() {
        // given
        val sut = getSut("file-with-one-class")
            .files()
            .first()

        // then
        sut
            .classes(includeNested = true)
            .map { it.name } shouldBeEqualTo listOf("SampleClass")
    }

    @Test
    fun `file-with-one-class includeNested false`() {
        // given
        val sut = getSut("file-with-one-class")
            .files()
            .first()

        // then
        sut
            .classes(includeNested = false)
            .map { it.name } shouldBeEqualTo listOf("SampleClass")
    }

    @Test
    fun `file-with-one-class-containing-class includeNested true`() {
        // given
        val sut = getSut("file-with-one-class-containing-class")
            .files()
            .first()

        // then
        sut
            .classes(includeNested = true)
            .map { it.name } shouldBeEqualTo listOf("SampleClass", "SampleNestedClass")
    }

    @Test
    fun `file-with-one-class-containing-class includeNested false`() {
        // given
        val sut = getSut("file-with-one-class-containing-class")
            .files()
            .first()

        // then
        sut
            .classes(includeNested = false)
            .map { it.name } shouldBeEqualTo listOf("SampleClass")
    }

    @Test
    fun `class-with-class-inside`() {
        // given
        val sut = getSut("class-with-class-inside")
            .files()
            .first()

        // then
        sut.apply {
            containsClass("SampleClass", includeNested = false) shouldBeEqualTo true
            containsClass("SampleNestedClass", includeNested = false) shouldBeEqualTo false
            containsClass("SampleNestedClass", includeNested = true) shouldBeEqualTo true
        }
    }

    @Test
    fun `class-with-property-inside`() {
        // given
        val sut = getSut("class-with-property-inside")
            .files()
            .first()

        // then
        sut.apply {
            containsProperty("sampleNestedProperty", includeNested = false) shouldBeEqualTo false
            containsProperty("sampleNestedProperty", includeNested = true) shouldBeEqualTo true
        }
    }

    @Test
    fun `class-with-function-inside`() {
        // given
        val sut = getSut("class-with-function-inside")
            .files()
            .first()

        // then
        sut.apply {
            containsFunction("sampleNestedFunction", includeNested = false) shouldBeEqualTo false
            containsFunction("sampleNestedFunction", includeNested = true) shouldBeEqualTo true
        }
    }

    @Test
    fun `class-with-object-inside`() {
        // given
        val sut = getSut("class-with-object-inside")
            .files()
            .first()

        // then
        sut.apply {
            containsObject("SampleNestedObject", includeNested = false) shouldBeEqualTo false
            containsObject("SampleNestedObject", includeNested = true) shouldBeEqualTo true
        }
    }

    @Test
    fun `class-with-interface-inside`() {
        // given
        val sut = getSut("class-with-interface-inside")
            .files()
            .first()

        // then
        sut.apply {
            containsInterface("SampleNestedInterface", includeNested = false) shouldBeEqualTo false
            containsInterface("SampleNestedInterface", includeNested = true) shouldBeEqualTo true
        }
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
            .map { it.name } shouldBeEqualTo listOf(
            "sampleFunction",
            "sampleLocalFunction1",
        )
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
            .map { it.name } shouldBeEqualTo listOf(
            "sampleFunction",
        )
    }

    private fun getSut(fileName: String) = getSnippetKoScope("core/kofile/snippet/forclass/$fileName.kt.txt")
}
