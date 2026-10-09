package com.lemonappdev.konsist.core.declaration.type.kostarprojection

import com.lemonappdev.konsist.TestSnippetProvider
import com.lemonappdev.konsist.api.declaration.type.KoStarProjectionDeclaration
import com.lemonappdev.konsist.api.ext.list.returnTypes
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoStarProjectionDeclarationForKoNameProviderTest {
    @Test
    fun `star-projection-name`() {
        // given
        val sut =
            getSnippetFile("star-projection-name")
                .functions()
                .returnTypes
                .firstOrNull()
                ?.typeArguments
                ?.firstOrNull()
                ?.sourceDeclaration as? KoStarProjectionDeclaration

        // then
        assertSoftly(sut) {
            it?.name shouldBeEqualTo "*"
            it?.hasName("String") shouldBeEqualTo false
            it?.hasName("String", ignoreCase = true) shouldBeEqualTo false
        }
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope(
            "core/declaration/type/kostarprojection/snippet/forkonameprovider/",
            fileName,
        )
}
