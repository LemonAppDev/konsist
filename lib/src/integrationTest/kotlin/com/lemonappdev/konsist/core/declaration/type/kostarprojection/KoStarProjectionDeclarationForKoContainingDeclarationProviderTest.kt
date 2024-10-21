package com.lemonappdev.konsist.core.declaration.type.kostarprojection

import com.lemonappdev.konsist.TestSnippetProvider
import com.lemonappdev.konsist.api.declaration.type.KoStarProjectionDeclaration
import com.lemonappdev.konsist.api.ext.list.returnTypes
import com.lemonappdev.konsist.api.provider.KoNameProvider
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoStarProjectionDeclarationForKoContainingDeclarationProviderTest {
    @Test
    fun `type-parameter-containing-declaration`() {
        // given
        val sut =
            getSnippetFile("star-projection-containing-declaration")
                .functions()
                .returnTypes
                .firstOrNull()
                ?.typeArguments
                ?.firstOrNull()
                ?.sourceDeclaration as? KoStarProjectionDeclaration

        // then
        (sut?.containingDeclaration as? KoNameProvider)?.name shouldBeEqualTo "sampleFunction"
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope(
            "core/declaration/type/kostarprojection/snippet/forkocontainingdeclarationprovider/",
            fileName,
        )
}
