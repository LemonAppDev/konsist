package com.lemonappdev.konsist.core.declaration.type.kostarprojection

import com.lemonappdev.konsist.TestSnippetProvider
import com.lemonappdev.konsist.api.declaration.type.KoStarProjectionDeclaration
import com.lemonappdev.konsist.api.ext.list.returnTypes
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoStarProjectionDeclarationForKoTypeProviderTest {
    @Test
    fun `star-projection-type`() {
        // given
        val sut =
            getSnippetFile("star-projection-type")
                .functions()
                .returnTypes
                .firstOrNull()
                ?.typeArguments
                ?.firstOrNull()
                ?.sourceDeclaration as? KoStarProjectionDeclaration

        // then
        assertSoftly(sut) {
            it?.isClass shouldBeEqualTo false
            it?.isObject shouldBeEqualTo false
            it?.isInterface shouldBeEqualTo false
            it?.isTypeAlias shouldBeEqualTo false
            it?.isImportAlias shouldBeEqualTo false
            it?.isKotlinType shouldBeEqualTo false
            it?.isKotlinBasicType shouldBeEqualTo false
            it?.isKotlinCollectionType shouldBeEqualTo false
            it?.isTypeParameter shouldBeEqualTo false
            it?.isExternalType shouldBeEqualTo false
        }
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope(
            "core/declaration/type/kostarprojection/snippet/forkotypeprovider/",
            fileName,
        )
}
