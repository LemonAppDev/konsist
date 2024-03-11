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
                ?.sourceKotlinType

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
                ?.sourceKotlinType

        // then
        sut?.name shouldBeEqualTo "String"
    }

    @Test
    fun `nullable-kotlin-collection-type-name`() {
        // given
        val sut =
            getSnippetFile("nullable-kotlin-collection-type-name")
                .properties()
                .first()
                .type
                ?.sourceKotlinType

        // then
        sut?.name shouldBeEqualTo "List<String>"
    }

    @Test
    fun `not-nullable-kotlin-collection-type-name`() {
        // given
        val sut =
            getSnippetFile("not-nullable-kotlin-collection-type-name")
                .properties()
                .first()
                .type
                ?.sourceKotlinType

        // then
        sut?.name shouldBeEqualTo "List<String>"
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("core/declaration/type/kokotlintype/snippet/forkonameprovider/", fileName)
}
