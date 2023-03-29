package com.lemon.konsist.core.koscope

import com.lemon.konsist.TestSnippetProvider
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoScopeForObjectTest {

    @Test
    fun `file-with-one-object`() {
        // given
        val sut = getSut("file-with-one-object")

        // then
        sut
            .objects()
            .map { it.name } shouldBeEqualTo listOf("SampleObject")
    }

    @Test
    fun `file-without-object`() {
        // given
        val sut = getSut("file-without-object")

        // then
        sut
            .objects()
            .isEmpty()
    }

    @Test
    fun `file-with-two-objects-with-nested-object`() {
        // given
        val sut = getSut("file-with-two-objects-with-nested-object")

        // then
        sut
            .objects(includeNested = true)
            .map { it.name } shouldBeEqualTo listOf("SampleObject1", "SampleObject2", "NestedSampleObject")
    }

    private fun getSut(fileName: String) = TestSnippetProvider.getSnippetKoScope("core/koscope/snippet/forobject/$fileName.kt.txt")
}
