package com.lemonappdev.konsist.core.declaration.koclass

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoClassDeclarationForKoFullyQualifiedNameProviderTest {
    @Test
    fun `class-fully-qualified-name`() {
        // given
        val sut =
            getSnippetFile("class-fully-qualified-name")
                .classes()
                .first()

        // then
        sut.fullyQualifiedName shouldBeEqualTo "com.fixturepackage.FixtureClass"
    }

    @Test
    fun `class-fully-qualified-name-without-package`() {
        // given
        val sut =
            getSnippetFile("class-fully-qualified-name-without-package")
                .classes()
                .first()

        // then
        sut.fullyQualifiedName shouldBeEqualTo "FixtureClass"
    }

    @Test
    fun `nested-class-fully-qualified-name`() {
        // given
        val sut =
            getSnippetFile("nested-class-fully-qualified-name")
                .classes()
                .first()

        // then
        sut.fullyQualifiedName shouldBeEqualTo "com.fixturepackage.FixtureInterface.FixtureClass"
    }

    @Test
    fun `nested-class-fully-qualified-name-without-package`() {
        // given
        val sut =
            getSnippetFile("nested-class-fully-qualified-name-without-package")
                .classes()
                .first()

        // then
        sut.fullyQualifiedName shouldBeEqualTo "FixtureInterface.FixtureClass"
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/koclass/snippet/forkodeclarationfullyqualifiednameprovider/", fileName)
}
