package com.lemonappdev.konsist.core.declaration.type.kokotlintype

import com.lemonappdev.konsist.TestSnippetProvider
import org.amshove.kluent.assertSoftly
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
                ?.asKotlinTypeDeclaration()

        // then
        assertSoftly(sut) {
            it?.text shouldBeEqualTo "String"
            it?.hasTextStartingWith("St") shouldBeEqualTo true
            it?.hasTextStartingWith("Other") shouldBeEqualTo false
            it?.hasTextEndingWith("ing") shouldBeEqualTo true
            it?.hasTextEndingWith("other") shouldBeEqualTo false
            it?.hasTextContaining("rin") shouldBeEqualTo true
            it?.hasTextContaining("anno") shouldBeEqualTo false
            it?.hasTextMatching(Regex("^[^@]*\$")) shouldBeEqualTo true
            it?.hasTextMatching(Regex("[0-9]+")) shouldBeEqualTo false
        }
    }

    @Test
    fun `not-nullable-kotlin-basic-type-text`() {
        // given
        val sut =
            getSnippetFile("not-nullable-kotlin-basic-type-text")
                .properties()
                .first()
                .type
                ?.asKotlinTypeDeclaration()

        // then
        assertSoftly(sut) {
            it?.text shouldBeEqualTo "String"
            it?.hasTextStartingWith("St") shouldBeEqualTo true
            it?.hasTextStartingWith("Other") shouldBeEqualTo false
            it?.hasTextEndingWith("ing") shouldBeEqualTo true
            it?.hasTextEndingWith("other") shouldBeEqualTo false
            it?.hasTextContaining("rin") shouldBeEqualTo true
            it?.hasTextContaining("anno") shouldBeEqualTo false
            it?.hasTextMatching(Regex("^[^@]*\$")) shouldBeEqualTo true
            it?.hasTextMatching(Regex("[0-9]+")) shouldBeEqualTo false
        }
    }

//    @Test
//    fun `nullable-kotlin-collection-type-text`() {
//        // given
//        val sut =
//            getSnippetFile("nullable-kotlin-collection-type-text")
//                .properties()
//                .first()
//                .type
//                ?.asKotlinTypeDeclaration()
//
//        // then
//        assertSoftly(sut) {
//            it?.text shouldBeEqualTo "List<String>"
//            it?.hasTextStartingWith("List<St") shouldBeEqualTo true
//            it?.hasTextStartingWith("Other") shouldBeEqualTo false
//            it?.hasTextEndingWith("ing>") shouldBeEqualTo true
//            it?.hasTextEndingWith("other") shouldBeEqualTo false
//            it?.hasTextContaining("rin") shouldBeEqualTo true
//            it?.hasTextContaining("anno") shouldBeEqualTo false
//            it?.hasTextMatching(Regex("^[^@]*\$")) shouldBeEqualTo true
//            it?.hasTextMatching(Regex("[0-9]+")) shouldBeEqualTo false
//        }
//    }
//
//    @Test
//    fun `not-nullable-kotlin-collection-type-text`() {
//        // given
//        val sut =
//            getSnippetFile("not-nullable-kotlin-collection-type-text")
//                .properties()
//                .first()
//                .type
//                ?.asKotlinTypeDeclaration()
//
//        // then
//        assertSoftly(sut) {
//            it?.text shouldBeEqualTo "List<String>"
//            it?.hasTextStartingWith("List<St") shouldBeEqualTo true
//            it?.hasTextStartingWith("Other") shouldBeEqualTo false
//            it?.hasTextEndingWith("ing>") shouldBeEqualTo true
//            it?.hasTextEndingWith("other") shouldBeEqualTo false
//            it?.hasTextContaining("rin") shouldBeEqualTo true
//            it?.hasTextContaining("anno") shouldBeEqualTo false
//            it?.hasTextMatching(Regex("^[^@]*\$")) shouldBeEqualTo true
//            it?.hasTextMatching(Regex("[0-9]+")) shouldBeEqualTo false
//        }
//    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("core/declaration/type/kokotlintype/snippet/forkotextprovider/", fileName)
}
