package com.lemonappdev.konsist.core.declaration.kointerface

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoInterfaceDeclarationForKoFullyQualifiedNameProviderTest {
    @Test
    fun `interface-fully-qualified-name`() {
        // given
        val sut = getSnippetFile("interface-fully-qualified-name")
            .interfaces()
            .first()

        // then
        sut.fullyQualifiedName shouldBeEqualTo "com.samplepackage.SampleInterface"
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
        getSnippetKoScope("core/declaration/kointerface/snippet/forkodeclarationfullyqualifiednameprovider/", fileName)
}
