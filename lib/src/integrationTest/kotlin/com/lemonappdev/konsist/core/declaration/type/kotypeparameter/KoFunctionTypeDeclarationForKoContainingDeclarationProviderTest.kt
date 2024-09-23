package com.lemonappdev.konsist.core.declaration.type.kotypeparameter

import com.lemonappdev.konsist.TestSnippetProvider
import com.lemonappdev.konsist.api.ext.list.returnTypes
import com.lemonappdev.konsist.api.provider.KoNameProvider
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.MethodSource

class KoTypeParameterDeclarationForKoContainingDeclarationProviderTest {
    @ParameterizedTest
    @MethodSource("provideValues")
    fun `type-containing-declaration`(fileName: String) {
        // given
        val sut =
            getSnippetFile(fileName)
                .functions()
                .returnTypes
                .firstOrNull()
                ?.asTypeParameterDeclaration()

        // then
        (sut?.containingDeclaration as? KoNameProvider)?.name shouldBeEqualTo fileName
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope(
            "core/declaration/type/kotypeparameter/snippet/forkocontainingdeclarationprovider/",
            fileName,
        )

    companion object {
        @Suppress("unused")
        @JvmStatic
        fun provideValues() =
            listOf(
                arguments("type-parameter-containing-declaration"),
                arguments("nullable-type-parameter-containing-declaration"),
            )
    }
}
