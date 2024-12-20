package com.lemonappdev.konsist.core.declaration.type.kokotlintype

import com.lemonappdev.konsist.TestSnippetProvider
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoKotlinTypeDeclarationForKoNameProviderTest {
    @Test
    fun `nullable-kotlin-basic-type-name`() {
        // given
        val sut =
            getSnippetFile("nullable-kotlin-basic-type-name")
                .properties()
                .first()
                .type
                ?.asKotlinTypeDeclaration()

        // then
        sut?.name shouldBeEqualTo "String"
    }

    @Test
    fun `not-nullable-kotlin-basic-type-name`() {
        // given
        val sut =
            getSnippetFile("not-nullable-kotlin-basic-type-name")
                .properties()
                .first()
                .type
                ?.asKotlinTypeDeclaration()

        // then
        sut?.name shouldBeEqualTo "String"
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("core/declaration/type/kokotlintype/snippet/forkonameprovider/", fileName)
}
