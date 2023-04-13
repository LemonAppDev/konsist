package com.lemon.konsist.core.declaration.kotypealias

import com.lemon.konsist.TestSnippetProvider
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoTypeAliasTest {

    @Test
    fun `typealias`() {
        // given
        val sut = getSut("typealias")
            .files()
            .first()
            .typeAliases
            .first()

        // then
        sut.run {
            name shouldBeEqualTo "SampleTypeAlias"
            type shouldBeEqualTo "() -> Int"
        }
    }

    @Test
    fun `no-typealias`() {
        // given
        val sut = getSut("no-typealias")
            .files()
            .first()
            .typeAliases

        // then
        sut.isEmpty() shouldBeEqualTo true
    }

    private fun getSut(fileName: String) = TestSnippetProvider.getSnippetKoScope("core/declaration/kotypealias/snippet/", fileName)
}
