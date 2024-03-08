package com.lemonappdev.konsist.core.declaration.kotypealias

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoTypeAliasDeclarationForKoPackageProviderTest {
    @Test
    fun `typealias-is-not-in-package`() {
        // given
        val sut =
            getSnippetFile("typealias-is-not-in-package")
                .typeAliases
                .first()

        // then
        sut.packagee shouldBeEqualTo null
    }

    @Test
    fun `typealias-is-in-package`() {
        // given
        val sut =
            getSnippetFile("typealias-is-in-package")
                .typeAliases
                .first()

        // then
        sut.packagee?.fullyQualifiedName shouldBeEqualTo "com.samplepackage"
    }

    private fun getSnippetFile(fileName: String) = getSnippetKoScope("core/declaration/kotypealias/snippet/forkopackageprovider/", fileName)
}
