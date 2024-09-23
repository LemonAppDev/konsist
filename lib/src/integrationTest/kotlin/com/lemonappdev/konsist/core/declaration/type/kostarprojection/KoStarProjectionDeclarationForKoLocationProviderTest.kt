package com.lemonappdev.konsist.core.declaration.type.kostarprojection

import com.lemonappdev.konsist.TestSnippetProvider
import com.lemonappdev.konsist.api.ext.list.returnTypes
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.MethodSource

class KoStarProjectionDeclarationForKoLocationProviderTest {
    @Test
    fun `star-projection-location`() {
        // given
        val sut =
            getSnippetFile("star-projection-location")
                .functions()
                .returnTypes
                .firstOrNull()
                                ?.asGenericTypeDeclaration()
                ?.typeArguments
                ?.firstOrNull()

        // then
        sut?.location shouldBeEqualTo "${sut?.path}:1:28"
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope(
            "core/declaration/type/kostarprojection/snippet/forkolocationprovider/",
            fileName,
        )
}
