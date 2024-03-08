package com.lemonappdev.konsist.core.declaration.kotypealias

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoTypeAliasDeclarationForKoFullyQualifiedNameProviderTest {
    @Test
    fun `typealias-fully-qualified-name`() {
        // given
        val sut =
            getSnippetFile("typealias-fully-qualified-name")
                .typeAliases
                .first()

        // then
        sut.fullyQualifiedName shouldBeEqualTo "com.samplepackage.SampleTypeAlias"
    }

    @Test
    fun `typealias-fully-qualified-name-without-package`() {
        // given
        val sut =
            getSnippetFile("typealias-fully-qualified-name-without-package")
                .typeAliases
                .first()

        // then
        sut.fullyQualifiedName shouldBeEqualTo "SampleTypeAlias"
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/kotypealias/snippet/forkodeclarationfullyqualifiednameprovider/", fileName)
}
