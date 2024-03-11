package com.lemonappdev.konsist.core.declaration.type.kofunctiontype

import com.lemonappdev.konsist.TestSnippetProvider
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoFunctionTypeDeclarationForKoPackageProviderTest {
    @Test
    fun `function-type-is-not-in-package`() {
        // given
        val sut =
            getSnippetFile("function-type-is-not-in-package")
                .classes()
                .first()
                .primaryConstructor
                ?.parameters
                ?.first()
                ?.type
                ?.asFunctionTypeDeclaration()

        // then
        sut?.packagee shouldBeEqualTo null
    }

    @Test
    fun `function-type-is-in-package`() {
        // given
        val sut =
            getSnippetFile("function-type-is-in-package")
                .classes()
                .first()
                .primaryConstructor
                ?.parameters
                ?.first()
                ?.type
                ?.asFunctionTypeDeclaration()

        // then
        sut?.packagee?.fullyQualifiedName shouldBeEqualTo "com.samplepackage"
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("core/declaration/type/kofunctiontype/snippet/forkopackageprovider/", fileName)
}
