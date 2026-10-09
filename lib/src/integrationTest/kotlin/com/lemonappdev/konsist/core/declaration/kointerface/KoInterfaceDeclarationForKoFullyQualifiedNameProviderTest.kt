package com.lemonappdev.konsist.core.declaration.kointerface

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoInterfaceDeclarationForKoFullyQualifiedNameProviderTest {
    @Test
    fun `interface-fully-qualified-name`() {
        // given
        val sut =
            getSnippetFile("interface-fully-qualified-name")
                .interfaces()
                .first()

        // then
        sut.fullyQualifiedName shouldBeEqualTo "com.fixturepackage.FixtureInterface"
    }

    @Test
    fun `interface-fully-qualified-name-without-package`() {
        // given
        val sut =
            getSnippetFile("interface-fully-qualified-name-without-package")
                .interfaces()
                .first()

        // then
        sut.fullyQualifiedName shouldBeEqualTo "FixtureInterface"
    }

    @Test
    fun `nested-interface-fully-qualified-name`() {
        // given
        val sut =
            getSnippetFile("nested-interface-fully-qualified-name")
                .interfaces()
                .first()

        // then
        sut.fullyQualifiedName shouldBeEqualTo "com.fixturepackage.FixtureClass.FixtureInterface"
    }

    @Test
    fun `nested-interface-fully-qualified-name-without-package`() {
        // given
        val sut =
            getSnippetFile("nested-interface-fully-qualified-name-without-package")
                .interfaces()
                .first()

        // then
        sut.fullyQualifiedName shouldBeEqualTo "FixtureClass.FixtureInterface"
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/kointerface/snippet/forkodeclarationfullyqualifiednameprovider/", fileName)
}
