package com.lemonappdev.konsist.core.declaration.kopropertydeclaration

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoPropertyDeclarationForKoDeclarationFullyQualifiedNameProviderTest {
    @Test
    fun `property-fully-qualified-name`() {
        // given
        val sut = getSnippetFile("property-fully-qualified-name")
            .properties()
            .first()

        // then
        sut.fullyQualifiedName shouldBeEqualTo "com.lemonappdev.konsist.testdata.sampleProperty"
    }

    @Test
    fun `property-fully-qualified-name-without-package`() {
        // given
        val sut = getSnippetFile("property-fully-qualified-name-without-package")
            .properties()
            .first()

        // then
        sut.fullyQualifiedName shouldBeEqualTo "sampleProperty"
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/kopropertydeclaration/snippet/forkodeclarationfullyqualifiednameprovider/", fileName)
}
