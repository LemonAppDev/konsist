package com.lemonappdev.konsist.core.declaration.koparent

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoParentDeclarationForKoFullyQualifiedNameProviderTest {
    @Test
    fun `class-fully-qualified-name`() {
        // given
        val sut = getSnippetFile("class-fully-qualified-name")
            .classes()
            .first()
            .parents
            .first()

        // then
        sut.fullyQualifiedName shouldBeEqualTo "com.samplepackage.SampleClass"
    }

    @Test
    fun `class-fully-qualified-name-without-package`() {
        // given
        val sut = getSnippetFile("class-fully-qualified-name-without-package")
            .classes()
            .first()
            .parents
            .first()

        // then
        sut.fullyQualifiedName shouldBeEqualTo "SampleClass"
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/koparent/snippet/forkodeclarationfullyqualifiednameprovider/", fileName)
}
