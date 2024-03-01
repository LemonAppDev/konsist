package com.lemonappdev.konsist.core.declaration.type.kokotlintype

import com.lemonappdev.konsist.TestSnippetProvider
import com.lemonappdev.konsist.api.declaration.type.KoKotlinTypeDeclaration
import com.lemonappdev.konsist.api.ext.list.withPrimaryConstructor
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.MethodSource

class KoKotlinTypeDeclarationForKoFullyQualifiedNameProviderTest {
    @Test
    fun `nullable-kotlin-basic-type-fully-qualified-name`() {
        // given
        val sut = getSnippetFile("nullable-kotlin-basic-type-fully-qualified-name")
            .properties()
            .first()
            .type
            ?.declaration as? KoKotlinTypeDeclaration

        // then
        sut?.fullyQualifiedName shouldBeEqualTo "kotlin.String"
    }

    @Test
    fun `not-nullable-kotlin-basic-type-fully-qualified-name`() {
        // given
        val sut = getSnippetFile("not-nullable-kotlin-basic-type-fully-qualified-name")
            .properties()
            .first()
            .type
            ?.declaration as? KoKotlinTypeDeclaration

        // then
        sut?.fullyQualifiedName shouldBeEqualTo "kotlin.String"
    }

    @Test
    fun `nullable-kotlin-collection-type-fully-qualified-name`() {
        // given
        val sut = getSnippetFile("nullable-kotlin-collection-type-fully-qualified-name")
            .properties()
            .first()
            .type
            ?.declaration as? KoKotlinTypeDeclaration

        // then
        sut?.fullyQualifiedName shouldBeEqualTo "kotlin.collections.List"
    }

    @Test
    fun `not-nullable-kotlin-collection-type-fully-qualified-name`() {
        // given
        val sut = getSnippetFile("not-nullable-kotlin-collection-type-fully-qualified-name")
            .properties()
            .first()
            .type
            ?.declaration as? KoKotlinTypeDeclaration

        // then
        sut?.fullyQualifiedName shouldBeEqualTo "kotlin.collections.List"
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("core/declaration/type/kokotlintype/snippet/forkofullyqualifiednameprovider/", fileName)
}
