package com.lemonappdev.konsist.core.declaration.type.kotypeparameter

import com.lemonappdev.konsist.TestSnippetProvider
import com.lemonappdev.konsist.api.ext.list.returnTypes
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.MethodSource

class KoTypeParameterDeclarationForKoLocationProviderTest {
    @ParameterizedTest
    @MethodSource("provideValues")
    fun `type-location`(
        fileName: String,
        value: String,
    ) {
        // given
        val sut =
            getSnippetFile(fileName)
                .functions()
                .returnTypes
                .firstOrNull()
                ?.asTypeParameterDeclaration()

        // then
        sut?.location shouldBeEqualTo "${sut?.path}:$value"
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope(
            "core/declaration/type/kotypeparameter/snippet/forkolocationprovider/",
            fileName
        )

    companion object {
        @Suppress("unused")
        @JvmStatic
        fun provideValues() =
            listOf(
                arguments("type-parameter-location", "1:43"),
                arguments("nullable-type-parameter-location", "1:43"),
            )
    }
}
