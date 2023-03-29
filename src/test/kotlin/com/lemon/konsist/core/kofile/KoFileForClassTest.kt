package com.lemon.konsist.core.kofile

import com.lemon.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoFileForClassTest {
    @Test
    fun `file-with-one-class includeNested true`() {
        // given
        val sut =
            getSut("file-with-one-class")
                .classes(includeNested = true)

        // then
        sut.map { it.name } shouldBeEqualTo listOf("SampleClass")
    }

    @Test
    fun `file-with-one-class includeNested false`() {
        // given
        val sut =
            getSut("file-with-one-class")
                .classes(includeNested = false)

        // then
        sut.map { it.name } shouldBeEqualTo listOf("SampleClass")
    }

    @Test
    fun `file-with-one-class-containing-class includeNested true`() {
        // given
        val sut =
            getSut("file-with-one-class-containing-class")
                .classes(includeNested = true)

        // then
        sut.map { it.name } shouldBeEqualTo listOf("SampleClass", "SampleNestedClass")
    }

    @Test
    fun `file-with-one-class-containing-class includeNested false`() {
        // given
        val sut =
            getSut("file-with-one-class-containing-class")
                .classes(includeNested = false)

        // then
        sut.map { it.name } shouldBeEqualTo listOf("SampleClass")
    }

    @Test
    fun `class-with-class-inside`() {
        // given
        val sut =
            getSut("class-with-class-inside")
                .files()
                .first()

        // then
        sut.apply {
            hasClass("SampleClass", false) shouldBeEqualTo true
            hasClass("SampleNestedClass", false) shouldBeEqualTo false
            hasClass("SampleNestedClass", true) shouldBeEqualTo true
        }
    }

    @Test
    fun `class-with-property-inside`() {
        // given
        val sut =
            getSut("class-with-property-inside")
                .files()
                .first()

        // then
        sut.apply {
            hasProperty("sampleNestedProperty", false) shouldBeEqualTo false
            hasProperty("sampleNestedProperty", true) shouldBeEqualTo true
        }
    }

    @Test
    fun `class-with-function-inside`() {
        // given
        val sut =
            getSut("class-with-function-inside")
                .files()
                .first()

        // then
        sut.apply {
            hasFunction("sampleNestedFunction", false) shouldBeEqualTo false
            hasFunction("sampleNestedFunction", true) shouldBeEqualTo true
        }
    }

    @Test
    fun `class-with-object-inside`() {
        // given
        val sut =
            getSut("class-with-object-inside")
                .files()
                .first()

        // then
        sut.apply {
            hasObject("SampleNestedObject", false) shouldBeEqualTo false
            hasObject("SampleNestedObject", true) shouldBeEqualTo true
        }
    }

    @Test
    fun `class-with-interface-inside`() {
        // given
        val sut =
            getSut("class-with-interface-inside")
                .files()
                .first()

        // then
        sut.apply {
            hasInterface("SampleNestedInterface", false) shouldBeEqualTo false
            hasInterface("SampleNestedInterface", true) shouldBeEqualTo true
        }
    }

    private fun getSut(fileName: String) = getSnippetKoScope("core/kofile/snippet/forclass/$fileName.kt.txt")
}
