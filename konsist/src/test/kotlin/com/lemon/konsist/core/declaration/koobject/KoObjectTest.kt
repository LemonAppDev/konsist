package com.lemon.konsist.core.declaration.koobject

import com.lemon.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoObjectTest {
    @Test
    fun `object`() {
        // given
        val sut = getSut("object")
            .objects()
            .first()

        // then
        sut.name shouldBeEqualTo "SampleObject"
    }

    @Test
    fun `data-object`() {
        // given
        val sut = getSut("data-object")
            .objects()
            .first()

        // then
        sut.isData shouldBeEqualTo true
    }

    private fun getSut(fileName: String) = getSnippetKoScope("core/declaration/koobject/snippet/", fileName)
}
