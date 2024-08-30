package com.lemonappdev.konsist.core.declaration.koparameter

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldNotBeEqualTo
import org.junit.jupiter.api.Test

class KoParameterDeclarationForKoPackageProviderTest {
    @Test
    fun `parameter-is-not-in-package`() {
        // given
        val sut =
            getSnippetFile("parameter-is-not-in-package")
                .classes()
                .first()
                .primaryConstructor
                ?.parameters
                ?.first()

        // then
        sut?.packagee shouldBeEqualTo null
    }

    @Test
    fun `parameter-is-in-package`() {
        // given
        val sut =
            getSnippetFile("parameter-is-in-package")
                .classes()
                .first()
                .primaryConstructor
                ?.parameters
                ?.first()

        // then
        assertSoftly(sut) {
            it?.packagee shouldNotBeEqualTo null
            it?.packagee?.fullyQualifiedName shouldBeEqualTo "com.samplepackage"
        }
    }

    private fun getSnippetFile(fileName: String) = getSnippetKoScope("core/declaration/koparameter/snippet/forkopackageprovider/", fileName)
}
