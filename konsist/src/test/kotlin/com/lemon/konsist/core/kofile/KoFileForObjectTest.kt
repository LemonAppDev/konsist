package com.lemon.konsist.core.kofile

import com.lemon.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoFileForObjectTest {
    @Test
    fun `file-with-one-object includeNested true`() {
        // given
        val sut = getSut("file-with-one-object")
            .files()
            .first()

        // then
        sut
            .objects(includeNested = true)
            .map { it.name } shouldBeEqualTo listOf("SampleObject")
    }

    @Test
    fun `file-with-one-object includeNested false`() {
        // given
        val sut = getSut("file-with-one-object")
            .files()
            .first()

        // then
        sut
            .objects(includeNested = false)
            .map { it.name } shouldBeEqualTo listOf("SampleObject")
    }

    @Test
    fun `file-with-one-object-containing-object includeNested true`() {
        // given
        val sut = getSut("file-with-one-object-containing-object")
            .files()
            .first()

        // then
        sut
            .objects(includeNested = true)
            .map { it.name } shouldBeEqualTo listOf("SampleObject", "SampleNestedObject")
    }

    @Test
    fun `file-with-one-object-containing-object includeNested false`() {
        // given
        val sut = getSut("file-with-one-object-containing-object")
            .files()
            .first()

        // then
        sut
            .objects(includeNested = false)
            .map { it.name } shouldBeEqualTo listOf("SampleObject")
    }

    @Test
    fun `one-object includeNested true`() {
        // given
        val sut = getSut("one-object")
            .files()
            .first()

        // then
        sut.apply {
            containsObject("SampleObject", includeNested = true) shouldBeEqualTo true
            containsObject("OtherObject", includeNested = true) shouldBeEqualTo false
        }
    }

    @Test
    fun `one-object includeNested false`() {
        // given
        val sut = getSut("one-object")
            .files()
            .first()

        // then
        sut.apply {
            containsObject("SampleObject", includeNested = false) shouldBeEqualTo true
            containsObject("OtherObject", includeNested = false) shouldBeEqualTo false
        }
    }

    @Test
    fun `object-with-class-inside`() {
        // given
        val sut = getSut("object-with-class-inside")
            .files()
            .first()

        // then
        sut.apply {
            containsClass("SampleNestedClass", includeNested = false) shouldBeEqualTo false
            containsClass("SampleNestedClass", includeNested = true) shouldBeEqualTo true
        }
    }

    @Test
    fun `object-with-property-inside`() {
        // given
        val sut = getSut("object-with-property-inside")
            .files()
            .first()

        // then
        sut.apply {
            containsProperty("sampleNestedProperty", includeNested = false) shouldBeEqualTo false
            containsProperty("sampleNestedProperty", includeNested = true) shouldBeEqualTo true
        }
    }

    @Test
    fun `object-with-function-inside`() {
        // given
        val sut = getSut("object-with-function-inside")
            .files()
            .first()

        // then
        sut.apply {
            containsFunction("sampleNestedFunction", includeNested = false) shouldBeEqualTo false
            containsFunction("sampleNestedFunction", includeNested = true) shouldBeEqualTo true
        }
    }

    @Test
    fun `object-with-object-inside`() {
        // given
        val sut = getSut("object-with-object-inside")
            .files()
            .first()

        // then
        sut.apply {
            containsObject("SampleObject", includeNested = false) shouldBeEqualTo true
            containsObject("SampleNestedObject", includeNested = false) shouldBeEqualTo false
            containsObject("SampleNestedObject", includeNested = true) shouldBeEqualTo true
        }
    }

    @Test
    fun `object-with-interface-inside`() {
        // given
        val sut = getSut("object-with-interface-inside")
            .files()
            .first()

        // then
        sut.apply {
            containsInterface("SampleNestedInterface", includeNested = false) shouldBeEqualTo false
            containsInterface("SampleNestedInterface", includeNested = true) shouldBeEqualTo true
        }
    }

    @Test
    fun `object-with-nested-functions includeNested true and includeLocal true`() {
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
    fun `object-with-nested-functions includeNested true and includeLocal false`() {
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
    fun `object-with-nested-functions includeNested false and includeLocal true`() {
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
    fun `object-with-nested-functions includeNested false and includeLocal false`() {
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

    private fun getSut(fileName: String) = getSnippetKoScope("core/kofile/snippet/forobject/$fileName.kt.txt")
}
