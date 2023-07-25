package com.lemonappdev.konsist.core.declaration.kosecondaryconstructordeclaration

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldNotBeEqualTo
import org.junit.jupiter.api.Test

class KoSecondaryConstructorDeclarationForKoPackageProviderTest {
    @Test
    fun `secondary-constructor-is-not-in-package`() {
        // given
        val sut = getSnippetFile("secondary-constructor-is-not-in-package")
            .classes()
            .first()
            .secondaryConstructors
            .first()

        // then
        sut.packagee shouldBeEqualTo null
    }

    @Test
    fun `secondary-constructor-is-in-package`() {
        // given
        val sut = getSnippetFile("secondary-constructor-is-in-package")
            .classes()
            .first()
            .secondaryConstructors
            .first()

        // then
        assertSoftly(sut) {
            it.packagee shouldNotBeEqualTo null
            it.packagee?.fullyQualifiedName shouldBeEqualTo "com.samplepackage"
        }
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/kosecondaryconstructordeclaration/snippet/forkopackageprovider/", fileName)
}
