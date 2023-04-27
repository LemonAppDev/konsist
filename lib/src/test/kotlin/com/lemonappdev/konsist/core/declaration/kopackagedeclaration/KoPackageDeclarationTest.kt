package com.lemonappdev.konsist.core.declaration.kopackagedeclaration

import com.lemonappdev.konsist.TestSnippetProvider
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoPackageDeclarationTest {
    @Test
    fun `package-has-name`() {
        // given
        val sut = getSnippetFile("package-has-name")
            .packages()
            .first()

        // then
        sut.name shouldBeEqualTo "samplepackage"
    }

    @Test
    fun `package-does-not-exists`() {
        // given
        val sut = getSnippetFile("package-does-not-exists")
            .packages()
            .toList()
            .firstOrNull()

        // then
        sut?.name shouldBeEqualTo null
    }

    @Test
    fun `package-has-fully-qualified-name`() {
        // given
        val sut = getSnippetFile("package-has-fully-qualified-name")
            .packages()

        // then
        sut
            .map { it.qualifiedName }
            .toList()
            .first()
            .shouldBeEqualTo("com.samplepackage")
    }

    private fun getSnippetFile(fileName: String) = TestSnippetProvider.getSnippetKoScope("core/declaration/kopackagedeclaration/snippet/", fileName)
}
