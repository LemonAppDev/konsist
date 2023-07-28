package com.lemonappdev.konsist.core.declaration.koprimaryconstructordeclaration

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldNotBeEqualTo
import org.junit.jupiter.api.Test

class KoPrimaryConstructorDeclarationForKoPackageProviderTest {
    @Test
    fun `primary-constructor-is-not-in-package`() {
        // given
        val sut = getSnippetFile("primary-constructor-is-not-in-package")
            .classes()
            .first()
            .primaryConstructor

        // then
        sut?.packagee shouldBeEqualTo null
    }

    @Test
    fun `primary-constructor-is-in-package`() {
        // given
        val sut = getSnippetFile("primary-constructor-is-in-package")
            .classes()
            .first()
            .primaryConstructor

        // then
        assertSoftly(sut) {
            it?.packagee shouldNotBeEqualTo null
            it?.packagee?.fullyQualifiedName shouldBeEqualTo "com.samplepackage"
        }
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/koprimaryconstructordeclaration/snippet/forkopackageprovider/", fileName)
}
