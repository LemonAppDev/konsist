package com.lemonappdev.konsist.core.declaration.koconstructor

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoConstructorDeclarationForKoPackageProviderTest {
    @Test
    fun `constructor-is-not-in-package`() {
        // given
        val sut =
            getSnippetFile("constructor-is-not-in-package")
                .classes()
                .first()
                .constructors
                .first()

        // then
        sut.packagee shouldBeEqualTo null
    }

    @Test
    fun `constructor-is-in-package`() {
        // given
        val sut =
            getSnippetFile("constructor-is-in-package")
                .classes()
                .first()
                .constructors
                .first()

        // then
        sut.packagee?.fullyQualifiedName shouldBeEqualTo "com.samplepackage"
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/koconstructor/snippet/forkopackageprovider/", fileName)
}
