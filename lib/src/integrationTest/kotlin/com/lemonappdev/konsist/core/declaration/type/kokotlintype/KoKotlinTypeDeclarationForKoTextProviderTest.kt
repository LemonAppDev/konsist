package com.lemonappdev.konsist.core.declaration.type.kokotlintype

import com.lemonappdev.konsist.TestSnippetProvider
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoKotlinTypeDeclarationForKoTextProviderTest {
    @Test
    fun `nullable-kotlin-basic-type-text`() {
        // given
        val sut =
            getSnippetFile("nullable-kotlin-basic-type-text")
                .properties()
                .first()
                .type
                ?.sourceKotlinType

        // then
        sut?.text shouldBeEqualTo "String"
    }

    @Test
    fun `not-nullable-kotlin-basic-type-text`() {
        // given
        val sut =
            getSnippetFile("not-nullable-kotlin-basic-type-text")
                .properties()
                .first()
                .type
                ?.sourceKotlinType

        // then
        sut?.text shouldBeEqualTo "String"
    }

    @Test
    fun `nullable-kotlin-collection-type-text`() {
        // given
        val sut =
            getSnippetFile("nullable-kotlin-collection-type-text")
                .properties()
                .first()
                .type
                ?.sourceKotlinType

        // then
        sut?.text shouldBeEqualTo "List<String>"
    }

    @Test
    fun `not-nullable-kotlin-collection-type-text`() {
        // given
        val sut =
            getSnippetFile("not-nullable-kotlin-collection-type-text")
                .properties()
                .first()
                .type
                ?.sourceKotlinType

        // then
        sut?.text shouldBeEqualTo "List<String>"
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("core/declaration/type/kokotlintype/snippet/forkotextprovider/", fileName)
}
