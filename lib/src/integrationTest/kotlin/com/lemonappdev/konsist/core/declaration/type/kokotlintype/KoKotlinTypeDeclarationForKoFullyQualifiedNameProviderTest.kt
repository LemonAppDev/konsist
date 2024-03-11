package com.lemonappdev.konsist.core.declaration.type.kokotlintype

import com.lemonappdev.konsist.TestSnippetProvider
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoKotlinTypeDeclarationForKoFullyQualifiedNameProviderTest {
    @Test
    fun `nullable-kotlin-basic-type-fully-qualified-name`() {
        // given
        val sut =
            getSnippetFile("nullable-kotlin-basic-type-fully-qualified-name")
                .properties()
                .first()
                .type
                ?.sourceKotlinType

        // then
        sut?.fullyQualifiedName shouldBeEqualTo "kotlin.String"
    }

    @Test
    fun `not-nullable-kotlin-basic-type-fully-qualified-name`() {
        // given
        val sut =
            getSnippetFile("not-nullable-kotlin-basic-type-fully-qualified-name")
                .properties()
                .first()
                .type
                ?.sourceKotlinType

        // then
        sut?.fullyQualifiedName shouldBeEqualTo "kotlin.String"
    }

    @Test
    fun `nullable-kotlin-collection-type-fully-qualified-name`() {
        // given
        val sut =
            getSnippetFile("nullable-kotlin-collection-type-fully-qualified-name")
                .properties()
                .first()
                .type
                ?.sourceKotlinType

        // then
        sut?.fullyQualifiedName shouldBeEqualTo "kotlin.collections.List"
    }

    @Test
    fun `not-nullable-kotlin-collection-type-fully-qualified-name`() {
        // given
        val sut =
            getSnippetFile("not-nullable-kotlin-collection-type-fully-qualified-name")
                .properties()
                .first()
                .type
                ?.sourceKotlinType

        // then
        sut?.fullyQualifiedName shouldBeEqualTo "kotlin.collections.List"
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("core/declaration/type/kokotlintype/snippet/forkofullyqualifiednameprovider/", fileName)
}
