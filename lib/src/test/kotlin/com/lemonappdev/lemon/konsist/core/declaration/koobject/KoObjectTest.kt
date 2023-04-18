package com.lemonappdev.konsist.core.declaration.koobject

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoObjectTest {
    @Test
    fun `object`() {
        // given
        val sut = getSnippetFile("object")
            .objects()
            .first()

        // then
        sut.name shouldBeEqualTo "SampleObject"
    }

    @Test
    fun `data-object`() {
        // given
        val sut = getSnippetFile("data-object")
            .objects()
            .first()

        // then
        sut.hasDataModifier() shouldBeEqualTo true
    }

    private fun getSnippetFile(fileName: String) = getSnippetKoScope("core/declaration/koobject/snippet/", fileName)
}
