package com.lemon.konsist.core.kofile

import com.lemon.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoFileForInterfaceTest {
    @Test
    fun `file-with-one-interface includeNested true`() {
        // given
        val sut = getSut("file-with-one-interface")
            .files()
            .first()

        // then
        sut
            .interfaces(includeNested = true)
            .map { it.name } shouldBeEqualTo listOf("SampleInterface")
    }

    @Test
    fun `file-with-one-interface includeNested false`() {
        // given
        val sut = getSut("file-with-one-interface")
            .files()
            .first()

        // then
        sut
            .interfaces(includeNested = false)
            .map { it.name } shouldBeEqualTo listOf("SampleInterface")
    }

    @Test
    fun `file-with-one-interface-containing-interface includeNested true`() {
        // given
        val sut = getSut("file-with-one-interface-containing-interface")
            .files()
            .first()

        // then
        sut
            .interfaces(includeNested = true)
            .map { it.name } shouldBeEqualTo listOf("SampleInterface", "SampleNestedInterface")
    }

    @Test
    fun `file-with-one-interface-containing-interface includeNested false`() {
        // given
        val sut = getSut("file-with-one-interface-containing-interface")
            .files()
            .first()

        // then
        sut
            .interfaces(includeNested = false)
            .map { it.name } shouldBeEqualTo listOf("SampleInterface")
    }

    @Test
    fun `one-interface includeNested true`() {
        // given
        val sut = getSut("one-interface")
            .files()
            .first()

        // then
        sut.apply {
            containsInterface("SampleInterface", includeNested = true) shouldBeEqualTo true
            containsInterface("OtherInterface", includeNested = true) shouldBeEqualTo false
        }
    }

    @Test
    fun `one-interface includeNested false`() {
        // given
        val sut = getSut("one-interface")
            .files()
            .first()

        // then
        sut.apply {
            containsInterface("SampleInterface", includeNested = false) shouldBeEqualTo true
            containsInterface("OtherInterface", includeNested = false) shouldBeEqualTo false
        }
    }

    @Test
    fun `interface-with-class-inside`() {
        // given
        val sut = getSut("interface-with-class-inside")
            .files()
            .first()

        // then
        sut.apply {
            containsClass("SampleNestedClass", includeNested = false) shouldBeEqualTo false
            containsClass("SampleNestedClass", includeNested = true) shouldBeEqualTo true
        }
    }

    @Test
    fun `interface-with-property-inside`() {
        // given
        val sut = getSut("interface-with-property-inside")
            .files()
            .first()

        // then
        sut.apply {
            containsProperty("sampleNestedProperty", includeNested = false) shouldBeEqualTo false
            containsProperty("sampleNestedProperty", includeNested = true) shouldBeEqualTo true
        }
    }

    @Test
    fun `interface-with-function-inside`() {
        // given
        val sut = getSut("interface-with-function-inside")
            .files()
            .first()

        // then
        sut.apply {
            containsFunction("sampleNestedFunction", includeNested = false) shouldBeEqualTo false
            containsFunction("sampleNestedFunction", includeNested = true) shouldBeEqualTo true
        }
    }

    @Test
    fun `interface-with-object-inside`() {
        // given
        val sut = getSut("interface-with-object-inside")
            .files()
            .first()

        // then
        sut.apply {
            containsObject("SampleNestedObject", includeNested = false) shouldBeEqualTo false
            containsObject("SampleNestedObject", includeNested = true) shouldBeEqualTo true
        }
    }

    @Test
    fun `interface-with-interface-inside`() {
        // given
        val sut = getSut("interface-with-interface-inside")
            .files()
            .first()

        // then
        sut.apply {
            containsInterface("SampleInterface", includeNested = false) shouldBeEqualTo true
            containsInterface("SampleNestedInterface", includeNested = false) shouldBeEqualTo false
            containsInterface("SampleNestedInterface", includeNested = true) shouldBeEqualTo true
        }
    }

    @Test
    fun `interface-with-nested-functions includeNested true and includeLocal true`() {
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
    fun `interface-with-nested-functions includeNested true and includeLocal false`() {
        // given
        val sut = getSut("interface-with-nested-functions")
            .interfaces()
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
    fun `interface-with-nested-functions includeNested false and includeLocal true`() {
        // given
        val sut = getSut("interface-with-nested-functions")
            .interfaces()
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
    fun `interface-with-nested-functions includeNested false and includeLocal false`() {
        // given
        val sut = getSut("interface-with-nested-functions")
            .interfaces()
            .first()

        // then
        sut
            .functions(includeNested = false, includeLocal = false)
            .map { it.name } shouldBeEqualTo listOf(
            "sampleFunction",
        )
    }

    private fun getSut(fileName: String) = getSnippetKoScope("core/kofile/snippet/forinterface/$fileName.kt.txt")
}
