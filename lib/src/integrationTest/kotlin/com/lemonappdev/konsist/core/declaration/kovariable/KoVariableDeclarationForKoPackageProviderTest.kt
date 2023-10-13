package com.lemonappdev.konsist.core.declaration.kovariable

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import com.lemonappdev.konsist.api.ext.list.variables
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoVariableDeclarationForKoPackageProviderTest {
    @Test
    fun `variable-is-not-in-package`() {
        // given
        val sut = getSnippetFile("variable-is-not-in-package")
            .functions()
            .variables
            .first()

        // then
        sut.packagee shouldBeEqualTo null
    }

    @Test
    fun `variable-is-in-package`() {
        // given
        val sut = getSnippetFile("variable-is-in-package")
            .functions()
            .variables
            .first()

        // then
        sut.packagee?.fullyQualifiedName shouldBeEqualTo "com.samplepackage"
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/kovariable/snippet/forkopackageprovider/", fileName)
}
