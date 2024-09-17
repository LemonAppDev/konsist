package com.lemonappdev.konsist.core.declaration.type.kogenerictype

import com.lemonappdev.konsist.TestSnippetProvider
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoGenericTypeDeclarationForKoPackageProviderTest {
    @Test
    fun `generic-type-is-not-in-package`() {
        // given
        val sut =
            getSnippetFile("generic-type-is-not-in-package")
                .classes()
                .first()
                .primaryConstructor
                ?.parameters
                ?.first()
                ?.type
                ?.asGenericTypeDeclaration()

        // then
        sut?.packagee shouldBeEqualTo null
    }

    @Test
    fun `generic-type-is-in-package`() {
        // given
        val sut =
            getSnippetFile("generic-type-is-in-package")
                .classes()
                .first()
                .primaryConstructor
                ?.parameters
                ?.first()
                ?.type
                ?.asGenericTypeDeclaration()

        // then
        sut?.packagee?.name shouldBeEqualTo "com.samplepackage"
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("core/declaration/type/kogenerictype/snippet/forkopackageprovider/", fileName)
}
