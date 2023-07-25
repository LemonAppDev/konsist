package com.lemonappdev.konsist.core.declaration.kointerfacedeclaration

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoInterfaceDeclarationForKoDeclarationFullyQualifiedNameProviderTest {
    @Test
    fun `interface-fully-qualified-name`() {
        // given
        val sut = getSnippetFile("interface-fully-qualified-name")
            .interfaces()
            .first()

        // then
        sut.fullyQualifiedName shouldBeEqualTo "com.lemonappdev.konsist.testdata.SampleInterface"
    }

    @Test
    fun `interface-fully-qualified-name-without-package`() {
        // given
        val sut = getSnippetFile("interface-fully-qualified-name-without-package")
            .interfaces()
            .first()

        // then
        sut.fullyQualifiedName shouldBeEqualTo "SampleInterface"
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/kointerfacedeclaration/snippet/forkodeclarationfullyqualifiednameprovider/", fileName)
}
