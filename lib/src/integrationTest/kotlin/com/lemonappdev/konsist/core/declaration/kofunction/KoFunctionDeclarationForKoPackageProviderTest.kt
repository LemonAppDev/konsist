package com.lemonappdev.konsist.core.declaration.kofunction

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoFunctionDeclarationForKoPackageProviderTest {
    @Test
    fun `function-is-not-in-package`() {
        // given
        val sut =
            getSnippetFile("function-is-not-in-package")
                .functions()
                .first()

        // then
        sut.packagee shouldBeEqualTo null
    }

    @Test
    fun `function-is-in-package`() {
        // given
        val sut =
            getSnippetFile("function-is-in-package")
                .functions()
                .first()

        // then
        sut.packagee?.fullyQualifiedName shouldBeEqualTo "com.samplepackage"
    }

    private fun getSnippetFile(fileName: String) = getSnippetKoScope("core/declaration/kofunction/snippet/forkopackageprovider/", fileName)
}
