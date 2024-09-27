package com.lemonappdev.konsist.core.declaration.type.kostarprojection

import com.lemonappdev.konsist.TestSnippetProvider
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoStarProjectionDeclarationTest {
    @Test
    fun `type-to-string`() {
        // given
        val sut =
            getSnippetFile("type-to-string")
                .functions()
                .first()
                .returnType
                ?.asGenericTypeDeclaration()
                ?.typeArguments
                ?.firstOrNull()
                ?.sourceDeclaration

        // then
        sut.toString() shouldBeEqualTo "*"
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("core/declaration/type/kostarprojection/snippet/forgeneral/", fileName)
}
