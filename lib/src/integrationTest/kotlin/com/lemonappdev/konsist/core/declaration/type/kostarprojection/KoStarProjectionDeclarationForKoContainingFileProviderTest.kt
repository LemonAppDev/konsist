package com.lemonappdev.konsist.core.declaration.type.kostarprojection

import com.lemonappdev.konsist.TestSnippetProvider
import com.lemonappdev.konsist.api.ext.list.returnTypes
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.MethodSource

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

        // then
        sut?.containingFile?.nameWithExtension shouldBeEqualTo "star-projection-containing-file.kt"
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("core/declaration/type/kostarprojection/snippet/forkocontainingfileprovider/", fileName)
}
