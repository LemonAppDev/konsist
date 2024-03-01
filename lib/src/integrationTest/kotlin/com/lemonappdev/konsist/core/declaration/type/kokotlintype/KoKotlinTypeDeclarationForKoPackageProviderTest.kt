package com.lemonappdev.konsist.core.declaration.type.kokotlintype

import com.lemonappdev.konsist.TestSnippetProvider
import com.lemonappdev.konsist.api.declaration.type.KoKotlinTypeDeclaration
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoKotlinTypeDeclarationForKoPackageProviderTest {
    @Test
    fun `nullable-kotlin-basic-type-package`() {
        // given
        val sut = getSnippetFile("nullable-kotlin-basic-type-package")
            .properties()
            .first()
            .type
            ?.declaration as? KoKotlinTypeDeclaration

        // then
        sut?.packagee?.fullyQualifiedName shouldBeEqualTo "kotlin"
    }

    @Test
    fun `not-nullable-kotlin-basic-type-package`() {
        // given
        val sut = getSnippetFile("not-nullable-kotlin-basic-type-package")
            .properties()
            .first()
            .type
            ?.declaration as? KoKotlinTypeDeclaration

        // then
        sut?.packagee?.fullyQualifiedName shouldBeEqualTo "kotlin"
    }

    @Test
    fun `nullable-kotlin-collection-type-package`() {
        // given
        val sut = getSnippetFile("nullable-kotlin-collection-type-package")
            .properties()
            .first()
            .type
            ?.declaration as? KoKotlinTypeDeclaration

        // then
        sut?.packagee?.fullyQualifiedName shouldBeEqualTo "kotlin.collections"
    }

    @Test
    fun `not-nullable-kotlin-collection-type-package`() {
        // given
        val sut = getSnippetFile("not-nullable-kotlin-collection-type-package")
            .properties()
            .first()
            .type
            ?.declaration as? KoKotlinTypeDeclaration

        // then
        sut?.packagee?.fullyQualifiedName shouldBeEqualTo "kotlin.collections"
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("core/declaration/type/kokotlintype/snippet/forkopackageprovider/", fileName)
}
