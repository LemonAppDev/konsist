package com.lemon.konsist.core.declaration.koscope

import com.lemon.konsist.TestSnippetProvider
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoScopeForPackageTest {

    @Test
    fun `file-contains-package`() {
        // given
        val sut = getSut("file-contains-package")

        // then
        sut
            .packages()
            .map { it?.name } shouldBeEqualTo listOf("samplepackage")
    }

    @Test
    fun `file-contains-no-package`() {
        // given
        val sut = getSut("file-contains-no-package")

        // then
        sut
            .packages()
            .isEmpty()
    }

    private fun getSut(fileName: String) = TestSnippetProvider.getSnippetKoScope("koscope/snippet/forpackage/", fileName)
}
