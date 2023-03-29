package com.lemon.konsist.core.kofile

import com.lemon.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoFileForPropertyTest {
    @Test
    fun `file-with-one-property`() {
        // given
        val sut =
            getSut("file-with-one-property")
                .properties()

        // then
        sut.map { it.name } shouldBeEqualTo listOf("sampleProperty")
    }

    @Test
    fun `file-with-one-class-containing-property`() {
        // given
        val sut =
            getSut("file-with-one-property")
                .properties()

        // then
        sut.map { it.name } shouldBeEqualTo listOf("sampleProperty")
    }

    @Test
    fun `one-property includeNested true`() {
        // given
        val sut =
            getSut("one-property")
                .files()
                .first()

        // then
        sut.apply {
            hasProperty("sampleProperty", true) shouldBeEqualTo true
            hasProperty("otherProperty", true) shouldBeEqualTo false
        }
    }

    @Test
    fun `one-property includeNested false`() {
        // given
        val sut =
            getSut("one-property")
                .files()
                .first()

        // then
        sut.apply {
            hasProperty("sampleProperty", false) shouldBeEqualTo true
            hasProperty("otherProperty", false) shouldBeEqualTo false
        }
    }

    private fun getSut(fileName: String) = getSnippetKoScope("core/kofile/snippet/forproperty/$fileName.kt.txt")
}
