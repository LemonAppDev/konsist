package com.lemonappdev.konsist.core.container

import com.lemonappdev.konsist.TestSnippetProvider
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoScopeForKoPackagesDeclarationTest {
    @Test
    fun `scope-has-no-package`() {
        // given
        val sut = getSnippetFile("scope-has-no-package")

        // then
        sut.packages shouldBeEqualTo emptyList()
    }

    @Test
    fun `scope-has-package`() {
        // given
        val sut = getSnippetFile("scope-has-package")

        // then
        sut.packages.map { it.name } shouldBeEqualTo listOf("com.samplepackage")
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("core/container/snippet/forkopackagesdeclaration/", fileName)
}
