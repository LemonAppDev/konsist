package com.lemonappdev.konsist.core.container.koscope

import com.lemonappdev.konsist.TestSnippetProvider
import com.lemonappdev.konsist.core.ext.toNormalizedPath
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoScopeForTypeAliasTest {

    @Test
    fun `file-contains-typealias`() {
        // given
        val sut = getSnippetFile("file-contains-typealias")

        // then
        sut
            .typeAliases()
            .map { it.name }
            .toList()
            .shouldBeEqualTo(
                listOf("SampleTypeAlias"),
            )
    }

    @Test
    fun `file-contains-no-typealias`() {
        // given
        val sut = getSnippetFile("file-contains-no-typealias")

        // then
        sut
            .typeAliases()
            .toList()
            .shouldBeEqualTo(
                emptyList(),
            )
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("core/container/koscope/snippet/fortypealias/".toNormalizedPath(), fileName)
}
