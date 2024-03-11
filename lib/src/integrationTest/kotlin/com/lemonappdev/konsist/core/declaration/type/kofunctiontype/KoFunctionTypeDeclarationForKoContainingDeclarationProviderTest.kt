package com.lemonappdev.konsist.core.declaration.type.kofunctiontype

import com.lemonappdev.konsist.TestSnippetProvider
import com.lemonappdev.konsist.api.provider.KoNameProvider
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.MethodSource

class KoFunctionTypeDeclarationForKoContainingDeclarationProviderTest {
    @ParameterizedTest
    @MethodSource("provideValues")
    fun `type-containing-declaration`(fileName: String) {
        // given
        val sut =
            getSnippetFile(fileName)
                .classes()
                .first()
                .primaryConstructor
                ?.parameters
                ?.first()
                ?.type
                ?.asFunctionTypeDeclaration()

        // then
        (sut?.containingDeclaration as? KoNameProvider)?.name shouldBeEqualTo fileName
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope(
            "core/declaration/type/kofunctiontype/snippet/forkocontainingdeclarationprovider/",
            fileName,
        )

    companion object {
        @Suppress("unused")
        @JvmStatic
        fun provideValues() =
            listOf(
                arguments("function-type-containing-declaration"),
                arguments("nullable-function-type-containing-declaration"),
            )
    }
}
