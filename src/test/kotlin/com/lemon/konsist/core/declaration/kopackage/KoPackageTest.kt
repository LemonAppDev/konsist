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
        sut.name shouldBeEqualTo "samplepackage"
    }

    @Test
    fun `package-does-not-exists`() {
        // given
        val sut = getSut("package-does-not-exists")
            .packages()
            .toList()
            .firstOrNull()

        // then
        sut?.name shouldBeEqualTo null
    }

    @Test
    fun `package-has-fully-qualified-name`() {
        // given
        val sut = getSut("package-has-fully-qualified-name")
            .packages()

        // then
        sut
            .map { it.qualifiedName }
            .toList()
            .first() shouldBeEqualTo "com.samplepackage"
    }

    private fun getSut(fileName: String) = TestSnippetProvider.getSnippetKoScope("core/declaration/kopackage/snippet/", fileName)
}
