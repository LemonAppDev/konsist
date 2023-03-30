package com.lemon.konsist.core.kofile

import com.lemon.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoFileForFunctionTest {
    @Test
    fun `file-with-one-function includeNested true`() {
        // given
        val sut = getSut("file-with-one-function")
            .files()
            .first()

        // then
        sut
            .functions(includeNested = true)
            .map { it.name } shouldBeEqualTo listOf("sampleFunction")
    }

    @Test
    fun `file-with-one-function includeNested false`() {
        // given
        val sut = getSut("file-with-one-function")
            .files()
            .first()

        // then
        sut
            .functions(includeNested = false)
            .map { it.name } shouldBeEqualTo listOf("sampleFunction")
    }

    @Test
    fun `file-with-one-class-containing-function includeNested true`() {
        // given
        val sut = getSut("file-with-one-class-containing-function")
            .files()
            .first()

        // then
        sut
            .functions(includeNested = true)
            .map { it.name } shouldBeEqualTo listOf("sampleNestedFunction")
    }

    @Test
    fun `file-with-one-class-containing-function includeNested false`() {
        // given
        val sut = getSut("file-with-one-class-containing-function")
            .files()
            .first()

        // then
        sut
            .functions(includeNested = false)
            .map { it.name } shouldBeEqualTo emptyList()
    }

    @Test
    fun `one-function includeNested true`() {
        // given
        val sut = getSut("one-function")
            .files()
            .first()

        // then
        sut.apply {
            containsFunction("sampleFunction", includeNested = true) shouldBeEqualTo true
            containsFunction("OtherFunction", includeNested = true) shouldBeEqualTo false
        }
    }

    @Test
    fun `function-with-property-inside`() {
        // given
        val sut = getSut("function-with-property-inside")
            .functions()
            .first()

        // then
        sut.apply {
            getLocalProperties().first().name shouldBeEqualTo "sampleLocalProperty"
        }
    }

    @Test
    fun `one-function includeNested false`() {
        // given
        val sut = getSut("one-function")
            .files()
            .first()

        // then
        sut.apply {
            containsFunction("sampleFunction", includeNested = false) shouldBeEqualTo true
            containsFunction("OtherFunction", includeNested = false) shouldBeEqualTo false
        }
    }

    @Test
    fun `function-with-local-function`() {
        // given
        val sut = getSut("function-with-local-function")
            .files()
            .first()

        // then
        sut.apply {
            containsFunction("sampleFunction", includeNested = false) shouldBeEqualTo true
            containsFunction("sampleLocalFunction", includeNested = false) shouldBeEqualTo false
            containsFunction("sampleLocalFunction", includeNested = true) shouldBeEqualTo false
        }
    }

    @Test
    fun `file-with-nested-functions includeNested true and includeLocal true`() {
        // given
        val sut = getSut("file-with-nested-functions")
            .files()
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
    fun `file-with-nested-functions includeNested true and includeLocal false`() {
        // given
        val sut = getSut("file-with-nested-functions")
            .files()
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
    fun `file-with-nested-functions includeNested false and includeLocal true`() {
        // given
        val sut = getSut("file-with-nested-functions")
            .files()
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
    fun `file-with-nested-functions includeNested false and includeLocal false`() {
        // given
        val sut = getSut("file-with-nested-functions")
            .files()
            .first()

        // then
        sut
            .functions(includeNested = false, includeLocal = false)
            .map { it.name } shouldBeEqualTo listOf(
            "sampleFunction",
        )
    }

    private fun getSut(fileName: String) = getSnippetKoScope("core/kofile/snippet/forfunction/$fileName.kt.txt")
}
