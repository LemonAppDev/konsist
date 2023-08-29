package com.lemonappdev.konsist.core.declaration.koparent

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldNotBeEqualTo
import org.junit.jupiter.api.Test

class KoParentDeclarationForKoPackageProviderTest {
    @Test
    fun `parent-is-not-in-package`() {
        // given
        val sut = getSnippetFile("parent-is-not-in-package")
            .classes()
            .first()
            .parents
            .first()

        // then
        sut.packagee shouldBeEqualTo null
    }

    @Test
    fun `parent-is-in-package`() {
        // given
        val sut = getSnippetFile("parent-is-in-package")
            .classes()
            .first()
            .parents
            .first()

        // then
        assertSoftly(sut) {
            packagee shouldNotBeEqualTo null
            packagee?.fullyQualifiedName shouldBeEqualTo "com.samplepackage"
        }
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/koparent/snippet/forkopackageprovider/", fileName)
}
