package com.lemonappdev.konsist.core.declaration.koobject

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoObjectDeclarationForKoFullyQualifiedNameProviderTest {
    @Test
    fun `object-fully-qualified-name`() {
        // given
        val sut =
            getSnippetFile("object-fully-qualified-name")
                .objects()
                .first()

        // then
        sut.fullyQualifiedName shouldBeEqualTo "com.fixturepackage.FixtureObject"
    }

    @Test
    fun `object-fully-qualified-name-without-package`() {
        // given
        val sut =
            getSnippetFile("object-fully-qualified-name-without-package")
                .objects()
                .first()

        // then
        sut.fullyQualifiedName shouldBeEqualTo "FixtureObject"
    }

    @Test
    fun `nested-object-fully-qualified-name`() {
        // given
        val sut =
            getSnippetFile("nested-object-fully-qualified-name")
                .objects()
                .first()

        // then
        sut.fullyQualifiedName shouldBeEqualTo "com.fixturepackage.FixtureClass.FixtureObject"
    }

    @Test
    fun `nested-object-fully-qualified-name-without-package`() {
        // given
        val sut =
            getSnippetFile("nested-object-fully-qualified-name-without-package")
                .objects()
                .first()

        // then
        sut.fullyQualifiedName shouldBeEqualTo "FixtureClass.FixtureObject"
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/koobject/snippet/forkodeclarationfullyqualifiednameprovider/", fileName)
}
