package com.lemonappdev.konsist.core.declaration.kotypealias

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoTypeAliasDeclarationForKoTextProviderTest {
    @Test
    fun `typealias-text`() {
        // given
        val sut =
            getSnippetFile("typealias-text")
                .typeAliases
                .first()

        // then
        sut
            .text
            .shouldBeEqualTo("typealias SampleTypeAlias = () -> Int")
    }

    private fun getSnippetFile(fileName: String) = getSnippetKoScope("core/declaration/kotypealias/snippet/forkotextprovider/", fileName)
}
