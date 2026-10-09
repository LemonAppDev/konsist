package com.lemonappdev.konsist.core.declaration.type.kokotlintype

import com.lemonappdev.konsist.TestSnippetProvider
import org.amshove.kluent.assertSoftly
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
        assertSoftly(sut) {
            it?.name shouldBeEqualTo "String"
            it?.hasName("String") shouldBeEqualTo true
            it?.hasName("Int") shouldBeEqualTo false
            it?.hasName("string", ignoreCase = false) shouldBeEqualTo false
            it?.hasName("string", ignoreCase = true) shouldBeEqualTo true
        }
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
        assertSoftly(sut) {
            it?.name shouldBeEqualTo "String"
            it?.hasName("String") shouldBeEqualTo true
            it?.hasName("Int") shouldBeEqualTo false
            it?.hasName("string", ignoreCase = false) shouldBeEqualTo false
            it?.hasName("string", ignoreCase = true) shouldBeEqualTo true
        }
    }

    @Test
    fun `kotlin-type-name-with-brackets`() {
        // given
        val sut =
            getSnippetFile("kotlin-type-name-with-brackets")
                .classes()
                .first()
                .parents()
                .firstOrNull()
                ?.sourceDeclaration
                ?.asKotlinTypeDeclaration()

        // then
        assertSoftly(sut) {
            it?.name shouldBeEqualTo "Throwable"
            it?.hasName("Throwable") shouldBeEqualTo true
            it?.hasName("Int") shouldBeEqualTo false
            it?.hasName("throwable", ignoreCase = false) shouldBeEqualTo false
            it?.hasName("throwable", ignoreCase = true) shouldBeEqualTo true
        }
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("core/declaration/type/kokotlintype/snippet/forkonameprovider/", fileName)
}
