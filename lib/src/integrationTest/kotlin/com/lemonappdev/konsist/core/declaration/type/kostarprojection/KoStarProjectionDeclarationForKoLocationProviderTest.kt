package com.lemonappdev.konsist.core.declaration.type.kostarprojection

import com.lemonappdev.konsist.TestSnippetProvider
import com.lemonappdev.konsist.api.ext.list.provider.returnTypes
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

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
                ?.sourceDeclaration
                ?.asStarProjectionDeclaration()

        // then
        sut?.location shouldBeEqualTo "${sut?.path}:1:28"
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope(
            "core/declaration/type/kostarprojection/snippet/forkolocationprovider/",
            fileName,
        )
}
