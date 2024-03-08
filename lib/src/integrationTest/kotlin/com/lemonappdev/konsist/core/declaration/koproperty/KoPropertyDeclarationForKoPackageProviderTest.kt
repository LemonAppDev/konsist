package com.lemonappdev.konsist.core.declaration.koproperty

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoPropertyDeclarationForKoPackageProviderTest {
    @Test
    fun `property-is-not-in-package`() {
        // given
        val sut =
            getSnippetFile("property-is-not-in-package")
                .properties()
                .first()

        // then
        sut.packagee shouldBeEqualTo null
    }

    @Test
    fun `property-is-in-package`() {
        // given
        val sut =
            getSnippetFile("property-is-in-package")
                .properties()
                .first()

        // then
        sut.packagee?.fullyQualifiedName shouldBeEqualTo "com.samplepackage"
    }

    private fun getSnippetFile(fileName: String) = getSnippetKoScope("core/declaration/koproperty/snippet/forkopackageprovider/", fileName)
}
