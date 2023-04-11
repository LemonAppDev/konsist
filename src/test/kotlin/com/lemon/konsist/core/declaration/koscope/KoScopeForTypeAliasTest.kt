package com.lemon.konsist.core.declaration.koscope

import com.lemon.konsist.TestSnippetProvider
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoScopeForTypeAliasTest {

    @Test
    fun `file-contains-typealias`() {
        // given
        val sut = getSut("file-contains-typealias")

        // then
        sut
            .typeAliases()
            .map { it.name } shouldBeEqualTo listOf("SampleTypeAlias")
    }

    @Test
    fun `file-contains-no-typealias`() {
        // given
        val sut = getSut("file-contains-no-typealias")

        // then
        sut
            .typeAliases()
            .isEmpty() shouldBeEqualTo true
    }

    private fun getSut(fileName: String) = TestSnippetProvider.getSnippetKoScope("koscope/snippet/fortypealias/", fileName)
}
