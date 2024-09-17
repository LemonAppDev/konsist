package com.lemonappdev.konsist.core.declaration.type.kokotlintype

import com.lemonappdev.konsist.TestSnippetProvider
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoKotlinTypeDeclarationTest {
    @Test
    fun `nullable-basic-type-to-string`() {
        // given
        val sut =
            getSnippetFile("nullable-basic-type-to-string")
                .properties()
                .first()
                .type
                ?.asKotlinTypeDeclaration()

        // then
        sut?.toString() shouldBeEqualTo "String"
    }

    @Test
    fun `not-nullable-basic-type-to-string`() {
        // given
        val sut =
            getSnippetFile("not-nullable-basic-type-to-string")
                .properties()
                .first()
                .type
                ?.asKotlinTypeDeclaration()

        // then
        sut?.toString() shouldBeEqualTo "String"
    }
//
//    @Test
//    fun `nullable-collection-type-to-string`() {
//        // given
//        val sut =
//            getSnippetFile("nullable-collection-type-to-string")
//                .properties()
//                .first()
//                .type
//                ?.asKotlinTypeDeclaration()
//
//        // then
//        sut?.toString() shouldBeEqualTo "List"
//    }
//
//    @Test
//    fun `not-nullable-collection-type-to-string`() {
//        // given
//        val sut =
//            getSnippetFile("not-nullable-collection-type-to-string")
//                .properties()
//                .first()
//                .type
//                ?.asKotlinTypeDeclaration()
//
//        // then
//        sut?.toString() shouldBeEqualTo "List"
//    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("core/declaration/type/kokotlintype/snippet/forgeneral/", fileName)
}
