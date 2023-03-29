package com.lemon.konsist.core.kofile

import com.lemon.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoFileForObjectTest {
    @Test
    fun `file-with-one-object includeNested true`() {
        // given
        val sut =
            getSut("file-with-one-object")
                .objects(includeNested = true)

        // then
        sut.map { it.name } shouldBeEqualTo listOf("SampleObject")
    }

    @Test
    fun `file-with-one-object includeNested false`() {
        // given
        val sut =
            getSut("file-with-one-object")
                .objects(includeNested = false)

        // then
        sut.map { it.name } shouldBeEqualTo listOf("SampleObject")
    }

    @Test
    fun `file-with-one-object-containing-object includeNested true`() {
        // given
        val sut =
            getSut("file-with-one-object-containing-object")
                .objects(includeNested = true)

        // then
        sut.map { it.name } shouldBeEqualTo listOf("SampleObject", "SampleNestedObject")
    }

    @Test
    fun `file-with-one-object-containing-object includeNested false`() {
        // given
        val sut =
            getSut("file-with-one-object-containing-object")
                .objects(includeNested = false)

        // then
        sut.map { it.name } shouldBeEqualTo listOf("SampleObject")
    }

    @Test
    fun `one-object includeNested true`() {
        // given
        val sut =
            getSut("one-object")
                .files()
                .first()

        // then
        sut.apply {
            hasObject("SampleObject", true) shouldBeEqualTo true
            hasObject("OtherObject", true) shouldBeEqualTo false
        }
    }

    @Test
    fun `one-object includeNested false`() {
        // given
        val sut =
            getSut("one-object")
                .files()
                .first()

        // then
        sut.apply {
            hasObject("SampleObject", false) shouldBeEqualTo true
            hasObject("OtherObject", false) shouldBeEqualTo false
        }
    }

    @Test
    fun `object-with-class-inside`() {
        // given
        val sut =
            getSut("object-with-class-inside")
                .files()
                .first()

        // then
        sut.apply {
            hasClass("SampleNestedClass", false) shouldBeEqualTo false
            hasClass("SampleNestedClass", true) shouldBeEqualTo true
        }
    }

    @Test
    fun `object-with-property-inside`() {
        // given
        val sut =
            getSut("object-with-property-inside")
                .files()
                .first()

        // then
        sut.apply {
            hasProperty("sampleNestedProperty", false) shouldBeEqualTo false
            hasProperty("sampleNestedProperty", true) shouldBeEqualTo true
        }
    }

    @Test
    fun `object-with-function-inside`() {
        // given
        val sut =
            getSut("object-with-function-inside")
                .files()
                .first()

        // then
        sut.apply {
            hasFunction("sampleNestedFunction", false) shouldBeEqualTo false
            hasFunction("sampleNestedFunction", true) shouldBeEqualTo true
        }
    }

    @Test
    fun `object-with-object-inside`() {
        // given
        val sut =
            getSut("object-with-object-inside")
                .files()
                .first()

        // then
        sut.apply {
            hasObject("SampleObject", false) shouldBeEqualTo true
            hasObject("SampleNestedObject", false) shouldBeEqualTo false
            hasObject("SampleNestedObject", true) shouldBeEqualTo true
        }
    }

    @Test
    fun `object-with-interface-inside`() {
        // given
        val sut =
            getSut("object-with-interface-inside")
                .files()
                .first()

        // then
        sut.apply {
            hasInterface("SampleNestedInterface", false) shouldBeEqualTo false
            hasInterface("SampleNestedInterface", true) shouldBeEqualTo true
        }
    }

    private fun getSut(fileName: String) = getSnippetKoScope("core/kofile/snippet/forobject/$fileName.kt.txt")
}
