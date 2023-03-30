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
            hasFunction("sampleFunction", true) shouldBeEqualTo true
            hasFunction("OtherFunction", true) shouldBeEqualTo false
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
            hasFunction("sampleFunction", false) shouldBeEqualTo true
            hasFunction("OtherFunction", false) shouldBeEqualTo false
        }
    }

    @Test
    fun `function-with-function-inside`() {
        // given
        val sut = getSut("function-with-function-inside")
            .files()
            .first()

        // then
        sut.apply {
            hasFunction("sampleFunction", false) shouldBeEqualTo true
            hasFunction("sampleNestedFunction", false) shouldBeEqualTo false
            hasFunction("sampleNestedFunction", true) shouldBeEqualTo true
        }
    }

    private fun getSut(fileName: String) = getSnippetKoScope("core/kofile/snippet/forfunction/$fileName.kt.txt")
}
