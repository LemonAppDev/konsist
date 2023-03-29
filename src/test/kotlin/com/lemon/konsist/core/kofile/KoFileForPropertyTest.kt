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

    private fun getSut(fileName: String) = getSnippetKoScope("core/kofile/snippet/forproperty/$fileName.kt.txt")
}
