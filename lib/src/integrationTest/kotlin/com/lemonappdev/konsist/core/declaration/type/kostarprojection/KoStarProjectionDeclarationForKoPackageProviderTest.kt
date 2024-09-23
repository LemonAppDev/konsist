package com.lemonappdev.konsist.core.declaration.type.kostarprojection

import com.lemonappdev.konsist.TestSnippetProvider
import com.lemonappdev.konsist.api.ext.list.returnTypes
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoStarProjectionDeclarationForKoPackageProviderTest {
    @Test
    fun `star-projection-is-not-in-package`() {
        // given
        val sut =
            getSnippetFile("star-projection-is-not-in-package")
                .functions()
                .returnTypes
                .firstOrNull()
                                ?.asGenericTypeDeclaration()
                ?.typeArguments
                ?.firstOrNull()

        // then
        sut?.packagee shouldBeEqualTo null
    }

    @Test
    fun `star-projection-is-in-package`() {
        // given
        val sut =
            getSnippetFile("star-projection-is-in-package")
                .functions()
                .returnTypes
                .firstOrNull()
                                ?.asGenericTypeDeclaration()
                ?.typeArguments
                ?.firstOrNull()

        // then
        sut?.packagee?.name shouldBeEqualTo "com.samplepackage"
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope(
            "core/declaration/type/kostarprojection/snippet/forkopackageprovider/",
            fileName,
        )
}
