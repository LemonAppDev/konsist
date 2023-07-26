package com.lemonappdev.konsist.core.declaration.koobjectdeclaration

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoObjectDeclarationForKoFullyQualifiedNameProviderTest {
    @Test
    fun `object-fully-qualified-name`() {
        // given
        val sut = getSnippetFile("object-fully-qualified-name")
            .objects()
            .first()

        // then
        sut.fullyQualifiedName shouldBeEqualTo "com.samplepackage.SampleObject"
    }

    @Test
    fun `object-fully-qualified-name-without-package`() {
        // given
        val sut = getSnippetFile("object-fully-qualified-name-without-package")
            .objects()
            .first()

        // then
        sut.fullyQualifiedName shouldBeEqualTo "SampleObject"
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/koobjectdeclaration/snippet/forkodeclarationfullyqualifiednameprovider/", fileName)
}
