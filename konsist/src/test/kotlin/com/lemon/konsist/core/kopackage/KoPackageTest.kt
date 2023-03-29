package com.lemon.konsist.core.kopackage

import com.lemon.konsist.TestSnippetProvider
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoPackageTest {
    @Test
    fun `package-name`() {
        // given
        val sut =
            getSut("package-name")
                .packages()
                .first()

        // then
        sut?.name shouldBeEqualTo "samplepackage"
    }

    @Test
    fun `package-without-name`() {
        // given
        val sut =
            getSut("package-without-name")
                .packages()
                .first()

        // then
        sut?.name shouldBeEqualTo null
    }

    @Test
    fun `package-with-name`() {
        // given
        val sut =
            getSut("package-with-name")
                .packages()
                .first()

        // then
        sut?.fullyQualifiedName shouldBeEqualTo "com.samplepackage"
    }

    @Test
    fun `package-without-fully-qualified-name`() {
        // given
        val sut =
            getSut("package-without-fully-qualified-name")
                .packages()
                .first()

        // then
        sut?.fullyQualifiedName shouldBeEqualTo null
    }

    private fun getSut(fileName: String) = TestSnippetProvider.getSnippetKoScope("core/kopackage/snippet/$fileName.kt.txt")
}
