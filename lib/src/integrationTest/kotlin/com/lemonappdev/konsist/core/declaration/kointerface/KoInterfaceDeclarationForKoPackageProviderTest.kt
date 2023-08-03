package com.lemonappdev.konsist.core.declaration.kointerface

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldNotBeEqualTo
import org.junit.jupiter.api.Test

class KoInterfaceDeclarationForKoPackageProviderTest {
    @Test
    fun `interface-is-not-in-package`() {
        // given
        val sut = getSnippetFile("interface-is-not-in-package")
            .interfaces()
            .first()

        // then
        sut.packagee shouldBeEqualTo null
    }

    @Test
    fun `interface-is-in-package`() {
        // given
        val sut = getSnippetFile("interface-is-in-package")
            .interfaces()
            .first()

        // then
        assertSoftly(sut) {
            packagee shouldNotBeEqualTo null
            packagee?.fullyQualifiedName shouldBeEqualTo "com.samplepackage"
        }
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/kointerface/snippet/forkopackageprovider/", fileName)
}
