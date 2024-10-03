package com.lemonappdev.konsist.core.declaration.type.kostarprojection

import com.lemonappdev.konsist.TestSnippetProvider
import com.lemonappdev.konsist.api.ext.list.provider.returnTypes
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoStarProjectionDeclarationForKoContainingFileProviderTest {
    @Test
    fun `star-projection-containing-file`() {
        // given
        val sut =
            getSnippetFile("star-projection-containing-file")
                .functions()
                .returnTypes
                .firstOrNull()
                ?.asGenericTypeDeclaration()
                ?.typeArguments
                ?.firstOrNull()
                ?.sourceDeclaration
                ?.asStarProjectionDeclaration()

        // then
        sut?.containingFile?.nameWithExtension shouldBeEqualTo "star-projection-containing-file.kt"
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("core/declaration/type/kostarprojection/snippet/forkocontainingfileprovider/", fileName)
}
