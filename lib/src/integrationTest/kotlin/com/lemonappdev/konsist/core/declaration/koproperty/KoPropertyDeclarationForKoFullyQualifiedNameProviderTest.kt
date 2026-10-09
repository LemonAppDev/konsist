package com.lemonappdev.konsist.core.declaration.koproperty

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoPropertyDeclarationForKoFullyQualifiedNameProviderTest {
    @Test
    fun `property-fully-qualified-name`() {
        // given
        val sut =
            getSnippetFile("property-fully-qualified-name")
                .properties()
                .first()

        // then
        sut.fullyQualifiedName shouldBeEqualTo "com.fixturepackage.fixtureProperty"
    }

    @Test
    fun `property-fully-qualified-name-without-package`() {
        // given
        val sut =
            getSnippetFile("property-fully-qualified-name-without-package")
                .properties()
                .first()

        // then
        sut.fullyQualifiedName shouldBeEqualTo "fixtureProperty"
    }

    @Test
    fun `nested-property-fully-qualified-name`() {
        // given
        val sut =
            getSnippetFile("nested-property-fully-qualified-name")
                .properties()
                .first()

        // then
        sut.fullyQualifiedName shouldBeEqualTo null
    }

    @Test
    fun `nested-property-fully-qualified-name-without-package`() {
        // given
        val sut =
            getSnippetFile("nested-property-fully-qualified-name-without-package")
                .properties()
                .first()

        // then
        sut.fullyQualifiedName shouldBeEqualTo null
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/koproperty/snippet/forkodeclarationfullyqualifiednameprovider/", fileName)
}
