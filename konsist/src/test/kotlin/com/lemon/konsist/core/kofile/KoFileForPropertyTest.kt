package com.lemon.konsist.core.kofile

import com.lemon.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoFileForPropertyTest {
    @Test
    fun `file-with-one-property`() {
        // given
        val sut = getSut("file-with-one-property")
            .files()
            .first()

        // then
        sut
            .properties()
            .map { it.name } shouldBeEqualTo listOf("sampleProperty")
    }

    @Test
    fun `file-with-one-class-containing-property`() {
        // given
        val sut = getSut("file-with-one-property")
            .files()
            .first()

        // then
        sut
            .properties()
            .map { it.name } shouldBeEqualTo listOf("sampleProperty")
    }

    @Test
    fun `one-property includeNested true`() {
        // given
        val sut = getSut("one-property")
            .files()
            .first()

        // then
        sut.apply {
            containsProperty("sampleProperty", includeNested = true) shouldBeEqualTo true
            containsProperty("otherProperty", includeNested = true) shouldBeEqualTo false
        }
    }

    @Test
    fun `one-property includeNested false`() {
        // given
        val sut = getSut("one-property")
            .files()
            .first()

        // then
        sut.apply {
            containsProperty("sampleProperty", includeNested = false) shouldBeEqualTo true
            containsProperty("otherProperty", includeNested = false) shouldBeEqualTo false
        }
    }

    @Test
    fun `file-with-nested-properties includeNested true and includeLocal false`() {
        // given
        val sut = getSut("file-with-nested-properties")
            .files()
            .first()

        // then
        sut
            .properties(includeNested = true, includeLocal = false)
            .map { it.name } shouldBeEqualTo listOf(
            "sampleProperty",
            "sampleNestedProperty",
        )
    }

    @Test
    fun `file-with-nested-properties includeNested false and includeLocal false`() {
        // given
        val sut = getSut("file-with-nested-properties")
            .files()
            .first()

        // then
        sut
            .properties(includeNested = false, includeLocal = false)
            .map { it.name } shouldBeEqualTo listOf("sampleProperty")
    }

    private fun getSut(fileName: String) = getSnippetKoScope("core/kofile/snippet/forproperty/$fileName.kt.txt")
}
