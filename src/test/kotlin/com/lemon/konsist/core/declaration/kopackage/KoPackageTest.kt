package com.lemon.konsist.core.declaration.kopackage

import com.lemon.konsist.TestSnippetProvider
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoPackageTest {
    @Test
    fun `package-has-name`() {
        // given
        val sut = getSut("package-has-name")
            .packages()
            .first()

        // then
        sut?.name shouldBeEqualTo "samplepackage"
    }

    @Test
    fun `package-has-no-name`() {
        // given
        val sut = getSut("package-has-no-name")
            .packages()
            .first()

        // then
        sut?.name shouldBeEqualTo null
    }

    @Test
    fun `package-has-fully-qualified-name`() {
        // given
        val sut = getSut("package-has-fully-qualified-name")
            .packages()
            .first()

        // then
        sut?.qualifiedName shouldBeEqualTo "com.samplepackage"
    }

    @Test
    fun `package-has-no-fully-qualified-name`() {
        // given
        val sut = getSut("package-has-no-fully-qualified-name")
            .packages()
            .first()

        // then
        sut?.qualifiedName shouldBeEqualTo null
    }

    private fun getSut(fileName: String) = TestSnippetProvider.getSnippetKoScope("kopackage/snippet/", fileName)
}
