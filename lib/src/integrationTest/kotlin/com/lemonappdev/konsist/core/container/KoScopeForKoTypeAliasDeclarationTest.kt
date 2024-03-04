package com.lemonappdev.konsist.core.container

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoScopeForKoTypeAliasDeclarationTest {
    @Test
    fun `scope-has-no-typealias`() {
        // given
        val sut = getSnippetFile("scope-has-no-typealias")

        // then
        sut.typeAliases shouldBeEqualTo emptyList()
    }

    @Test
    fun `scope-contains-typealias`() {
        // given
        val sut = getSnippetFile("scope-contains-typealias")

        // then
        assertSoftly(
            sut
                .typeAliases
                .first(),
        ) {
            name shouldBeEqualTo "SampleTypeAlias"
            type.sourceType shouldBeEqualTo "() -> Int"
        }
    }

    private fun getSnippetFile(fileName: String) = getSnippetKoScope("core/container/snippet/forkotypealiasdeclaration/", fileName)
}
