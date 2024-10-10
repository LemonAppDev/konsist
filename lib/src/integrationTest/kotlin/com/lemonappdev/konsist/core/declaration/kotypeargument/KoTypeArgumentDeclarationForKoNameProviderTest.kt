package com.lemonappdev.konsist.core.declaration.kotypeargument

import com.lemonappdev.konsist.TestSnippetProvider
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoTypeArgumentDeclarationForKoNameProviderTest {
    @Test
    fun `not-generic-type-argument-name`() {
        // given
        val sut =
            getSnippetFile("not-generic-type-argument-name")
                .properties()
                .first()
                .type
                ?.asGenericTypeDeclaration()
                ?.typeArguments
                ?.firstOrNull()

        // then
        assertSoftly(sut) {
            it?.name shouldBeEqualTo "String"
            it?.hasNameStartingWith("Str") shouldBeEqualTo true
            it?.hasNameStartingWith("other") shouldBeEqualTo false
            it?.hasNameEndingWith("ing") shouldBeEqualTo true
            it?.hasNameEndingWith("other") shouldBeEqualTo false
            it?.hasNameContaining("rin") shouldBeEqualTo true
            it?.hasNameContaining("levari") shouldBeEqualTo false
            it?.hasNameMatching(Regex("[a-zA-Z<>]+")) shouldBeEqualTo true
            it?.hasNameMatching(Regex("[0-9]+")) shouldBeEqualTo false
        }
    }

    @Test
    fun `generic-type-argument-name`() {
        // given
        val sut =
            getSnippetFile("generic-type-argument-name")
                .properties()
                .first()
                .type
                ?.asGenericTypeDeclaration()
                ?.typeArguments
                ?.firstOrNull()

        // then
        assertSoftly(sut) {
            it?.name shouldBeEqualTo "Set<String>"
            it?.hasNameStartingWith("Set<") shouldBeEqualTo true
            it?.hasNameStartingWith("other") shouldBeEqualTo false
            it?.hasNameEndingWith("ing>") shouldBeEqualTo true
            it?.hasNameEndingWith("other") shouldBeEqualTo false
            it?.hasNameContaining("String>") shouldBeEqualTo true
            it?.hasNameContaining("levari") shouldBeEqualTo false
            it?.hasNameMatching(Regex("[a-zA-Z<>]+")) shouldBeEqualTo true
            it?.hasNameMatching(Regex("[0-9]+")) shouldBeEqualTo false
        }
    }

    @Test
    fun `generic-complex-type-argument-name`() {
        // given
        val sut =
            getSnippetFile("generic-complex-type-argument-name")
                .properties()
                .first()
                .type
                ?.asGenericTypeDeclaration()
                ?.typeArguments
                ?.firstOrNull()

        // then
        assertSoftly(sut) {
            it?.name shouldBeEqualTo "Map<List<String>, Int>"
            it?.hasNameStartingWith("Map<") shouldBeEqualTo true
            it?.hasNameStartingWith("other") shouldBeEqualTo false
            it?.hasNameEndingWith("Int>") shouldBeEqualTo true
            it?.hasNameEndingWith("other") shouldBeEqualTo false
            it?.hasNameContaining("<String>") shouldBeEqualTo true
            it?.hasNameContaining("levari") shouldBeEqualTo false
            it?.hasNameMatching(Regex("[a-zA-Z<>, ]+")) shouldBeEqualTo true
            it?.hasNameMatching(Regex("[0-9]+")) shouldBeEqualTo false
        }
    }

    @Test
    fun `star-projection-type-argument-name`() {
        // given
        val sut =
            getSnippetFile("star-projection-type-argument-name")
                .properties()
                .first()
                .type
                ?.asGenericTypeDeclaration()
                ?.typeArguments
                ?.firstOrNull()

        // then
        assertSoftly(sut) {
            it?.name shouldBeEqualTo "*"
            it?.hasNameStartingWith("*") shouldBeEqualTo true
            it?.hasNameStartingWith("other") shouldBeEqualTo false
            it?.hasNameEndingWith("*") shouldBeEqualTo true
            it?.hasNameEndingWith("other") shouldBeEqualTo false
            it?.hasNameContaining("*") shouldBeEqualTo true
            it?.hasNameContaining("levari") shouldBeEqualTo false
            it?.hasNameMatching(Regex("[a-zA-Z*]+")) shouldBeEqualTo true
            it?.hasNameMatching(Regex("[0-9]+")) shouldBeEqualTo false
        }
    }

    @Test
    fun `out-projection-type-argument-name`() {
        // given
        val sut =
            getSnippetFile("out-projection-type-argument-name")
                .properties()
                .first()
                .type
                ?.asGenericTypeDeclaration()
                ?.typeArguments
                ?.firstOrNull()

        // then
        assertSoftly(sut) {
            it?.name shouldBeEqualTo "String"
            it?.hasNameStartingWith("Str") shouldBeEqualTo true
            it?.hasNameStartingWith("other") shouldBeEqualTo false
            it?.hasNameEndingWith("ing") shouldBeEqualTo true
            it?.hasNameEndingWith("other") shouldBeEqualTo false
            it?.hasNameContaining("rin") shouldBeEqualTo true
            it?.hasNameContaining("levari") shouldBeEqualTo false
            it?.hasNameMatching(Regex("[a-zA-Z<>]+")) shouldBeEqualTo true
            it?.hasNameMatching(Regex("[0-9]+")) shouldBeEqualTo false
        }
    }

    @Test
    fun `in-projection-type-argument-name`() {
        // given
        val sut =
            getSnippetFile("in-projection-type-argument-name")
                .properties()
                .first()
                .type
                ?.asGenericTypeDeclaration()
                ?.typeArguments
                ?.firstOrNull()

        // then
        assertSoftly(sut) {
            it?.name shouldBeEqualTo "String"
            it?.hasNameStartingWith("Str") shouldBeEqualTo true
            it?.hasNameStartingWith("other") shouldBeEqualTo false
            it?.hasNameEndingWith("ing") shouldBeEqualTo true
            it?.hasNameEndingWith("other") shouldBeEqualTo false
            it?.hasNameContaining("rin") shouldBeEqualTo true
            it?.hasNameContaining("levari") shouldBeEqualTo false
            it?.hasNameMatching(Regex("[a-zA-Z<>]+")) shouldBeEqualTo true
            it?.hasNameMatching(Regex("[0-9]+")) shouldBeEqualTo false
        }
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("core/declaration/kotypeargument/snippet/forkonameprovider/", fileName)
}
