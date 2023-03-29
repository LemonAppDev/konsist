package com.lemon.konsist.core.kofile

import com.lemon.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoFileForInterfaceTest {
    @Test
    fun `file-with-one-interface includeNested true`() {
        // given
        val sut =
            getSut("file-with-one-interface")
                .interfaces(includeNested = true)

        // then
        sut.map { it.name } shouldBeEqualTo listOf("SampleInterface")
    }

    @Test
    fun `file-with-one-interface includeNested false`() {
        // given
        val sut =
            getSut("file-with-one-interface")
                .interfaces(includeNested = false)

        // then
        sut.map { it.name } shouldBeEqualTo listOf("SampleInterface")
    }

    @Test
    fun `file-with-one-interface-containing-interface includeNested true`() {
        // given
        val sut =
            getSut("file-with-one-interface-containing-interface")
                .interfaces(includeNested = true)

        // then
        sut.map { it.name } shouldBeEqualTo listOf("SampleInterface", "SampleNestedInterface")
    }

    @Test
    fun `file-with-one-interface-containing-interface includeNested false`() {
        // given
        val sut =
            getSut("file-with-one-interface-containing-interface")
                .interfaces(includeNested = false)

        // then
        sut.map { it.name } shouldBeEqualTo listOf("SampleInterface")
    }

    @Test
    fun `one-interface includeNested true`() {
        // given
        val sut =
            getSut("one-interface")
                .files()
                .first()

        // then
        sut.apply {
            hasInterface("SampleInterface", true) shouldBeEqualTo true
            hasInterface("OtherInterface", true) shouldBeEqualTo false
        }
    }

    @Test
    fun `one-interface includeNested false`() {
        // given
        val sut =
            getSut("one-interface")
                .files()
                .first()

        // then
        sut.apply {
            hasInterface("SampleInterface", false) shouldBeEqualTo true
            hasInterface("OtherInterface", false) shouldBeEqualTo false
        }
    }

    @Test
    fun `interface-with-class-inside`() {
        // given
        val sut =
            getSut("interface-with-class-inside")
                .files()
                .first()

        // then
        sut.apply {
            hasClass("SampleNestedClass", false) shouldBeEqualTo false
            hasClass("SampleNestedClass", true) shouldBeEqualTo true
        }
    }

    @Test
    fun `interface-with-property-inside`() {
        // given
        val sut =
            getSut("interface-with-property-inside")
                .files()
                .first()

        // then
        sut.apply {
            hasProperty("sampleNestedProperty", false) shouldBeEqualTo false
            hasProperty("sampleNestedProperty", true) shouldBeEqualTo true
        }
    }

    @Test
    fun `interface-with-function-inside`() {
        // given
        val sut =
            getSut("interface-with-function-inside")
                .files()
                .first()

        // then
        sut.apply {
            hasFunction("sampleNestedFunction", false) shouldBeEqualTo false
            hasFunction("sampleNestedFunction", true) shouldBeEqualTo true
        }
    }

    @Test
    fun `interface-with-object-inside`() {
        // given
        val sut =
            getSut("interface-with-object-inside")
                .files()
                .first()

        // then
        sut.apply {
            hasObject("SampleNestedObject", false) shouldBeEqualTo false
            hasObject("SampleNestedObject", true) shouldBeEqualTo true
        }
    }

    @Test
    fun `interface-with-interface-inside`() {
        // given
        val sut =
            getSut("interface-with-interface-inside")
                .files()
                .first()

        // then
        sut.apply {
            hasInterface("SampleInterface", false) shouldBeEqualTo true
            hasInterface("SampleNestedInterface", false) shouldBeEqualTo false
            hasInterface("SampleNestedInterface", true) shouldBeEqualTo true
        }
    }

    private fun getSut(fileName: String) = getSnippetKoScope("core/kofile/snippet/forinterface/$fileName.kt.txt")
}
