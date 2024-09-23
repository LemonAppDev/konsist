package com.lemonappdev.konsist.core.declaration.type.kostarprojection

import com.lemonappdev.konsist.TestSnippetProvider
import com.lemonappdev.konsist.api.ext.list.returnTypes
import com.lemonappdev.konsist.api.provider.KoNameProvider
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.MethodSource

class KoStarProjectionDeclarationForKoContainingDeclarationProviderTest {
    @Test
    fun `type-parameter-containing-declaration`() {
        // given
        val sut =
            getSnippetFile("star-projection-containing-declaration")
                .functions()
                .returnTypes
                .firstOrNull()
                ?.asGenericTypeDeclaration()
                ?.typeArguments
                ?.firstOrNull()

        // then
        (sut?.containingDeclaration as? KoNameProvider)?.name shouldBeEqualTo "List<*>"
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope(
            "core/declaration/type/kostarprojection/snippet/forkocontainingdeclarationprovider/",
            fileName,
        )
}
