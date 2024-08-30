package com.lemonappdev.konsist.core.declaration.koenumconstant

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoEnumConstantDeclarationForKoPackageProviderTest {
    @Test
    fun `enum-const-is-not-in-package`() {
        // given
        val sut =
            getSnippetFile("enum-const-is-not-in-package")
                .classes()
                .first()
                .enumConstants
                .first()

        // then
        sut.packagee shouldBeEqualTo null
    }

    @Test
    fun `enum-const-is-in-package`() {
        // given
        val sut =
            getSnippetFile("enum-const-is-in-package")
                .classes()
                .first()
                .enumConstants
                .first()

        // then
        sut.packagee?.fullyQualifiedName shouldBeEqualTo "com.samplepackage"
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/koenumconstant/snippet/forkopackageprovider/", fileName)
}
