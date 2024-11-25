package com.lemonappdev.konsist.core.declaration.kotypealias

import com.lemonappdev.konsist.TestSnippetProvider
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoTypeAliasDeclarationForKoIsGenericProviderTest {
    @Test
    fun `typealias-is-not-generic`() {
        // given
        val sut =
            getSnippetFile("typealias-is-not-generic")
                .typeAliases
                .first()

        // then
        sut.isGeneric shouldBeEqualTo false
    }

    @Test
    fun `typealias-is-generic`() {
        // given
        val sut =
            getSnippetFile("typealias-is-generic")
                .typeAliases
                .first()

        // then
        sut.isGeneric shouldBeEqualTo true
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("core/declaration/kotypealias/snippet/forkoisgenericprovider/", fileName)
}
