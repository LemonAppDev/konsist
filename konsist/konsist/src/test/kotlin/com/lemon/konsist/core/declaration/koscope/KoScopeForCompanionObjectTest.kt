package com.lemon.konsist.core.declaration.koscope

import com.lemon.konsist.TestSnippetProvider
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoScopeForCompanionObjectTest {
    @Test
    fun `file-contains-one-companion-object`() {
        // given
        val sut = getSut("file-contains-one-companion-object")

        // then
        sut
            .companionObjects(includeNested = true)
            .toList()
            .map { it.name }
            .shouldBeEqualTo(
                listOf("SampleCompanionObject"),
            )
    }

    @Test
    fun `file-contains-no-companion-object`() {
        // given
        val sut = getSut("file-contains-no-companion-object")

        // then
        sut
            .companionObjects(includeNested = true)
            .toList()
            .shouldBeEqualTo(
                emptyList(),
            )
    }

    @Test
    fun `file-contains-two-companion-objects includeNested true`() {
        // given
        val sut = getSut("file-contains-two-companion-objects")

        // then
        sut
            .companionObjects(includeNested = true)
            .toList()
            .map { it.name }
            .shouldBeEqualTo(
                listOf("SampleCompanionObject1", "SampleCompanionObject2"),
            )
    }

    @Test
    fun `file-contains-two-companion-objects includeNested false`() {
        // given
        val sut = getSut("file-contains-two-companion-objects")

        // then
        sut
            .companionObjects(includeNested = false)
            .toList()
            .shouldBeEqualTo(
                emptyList(),
            )
    }

    private fun getSut(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("core/declaration/koscope/snippet/forcompanionobject/", fileName)
}
