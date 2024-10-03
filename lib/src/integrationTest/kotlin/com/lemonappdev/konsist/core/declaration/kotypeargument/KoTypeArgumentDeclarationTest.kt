package com.lemonappdev.konsist.core.declaration.kotypeargument

import com.lemonappdev.konsist.TestSnippetProvider
import com.lemonappdev.konsist.api.declaration.type.KoKotlinTypeDeclaration
import com.lemonappdev.konsist.api.ext.list.declaration.flatten
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldBeInstanceOf
import org.junit.jupiter.api.Test

class KoTypeArgumentDeclarationTest {
    @Test
    fun `not-generic-type-argument-to-string`() {
        // given
        val sut =
            getSnippetFile("not-generic-type-argument-to-string")
                .properties()
                .first()
                .type
                ?.asGenericTypeDeclaration()
                ?.typeArguments
                ?.firstOrNull()

        // then
        assertSoftly(sut) {
            it?.toString() shouldBeEqualTo "String"
            it?.name shouldBeEqualTo "String"
            it?.typeArguments shouldBeEqualTo null
        }
    }

    @Test
    fun `generic-type-argument-to-string`() {
        // given
        val sut =
            getSnippetFile("generic-type-argument-to-string")
                .properties()
                .first()
                .type
                ?.asGenericTypeDeclaration()
                ?.typeArguments
                ?.firstOrNull()

        // then
        assertSoftly(sut) {
            it?.toString() shouldBeEqualTo "Set<String>"
            it?.name shouldBeEqualTo "Set<String>"
            it?.typeArguments?.map { typeArgument -> typeArgument.name } shouldBeEqualTo listOf("String")
            it?.typeArguments?.map { typeArgument -> typeArgument.sourceDeclaration.name } shouldBeEqualTo listOf("String")
            it
                ?.typeArguments
                ?.flatten()
                ?.map { typeArgument -> typeArgument.name } shouldBeEqualTo listOf("String")
            it?.typeArguments?.firstOrNull()?.typeArguments shouldBeEqualTo null
            it?.sourceDeclaration?.name shouldBeEqualTo "Set"
            it?.sourceDeclaration shouldBeInstanceOf KoKotlinTypeDeclaration::class
        }
    }

    @Test
    fun `generic-complex-type-argument-to-string`() {
        // given
        val sut =
            getSnippetFile("generic-complex-type-argument-to-string")
                .properties()
                .first()
                .type
                ?.asGenericTypeDeclaration()
                ?.typeArguments
                ?.firstOrNull()

        // then
        assertSoftly(sut) {
            it?.toString() shouldBeEqualTo "Map<List<String>, Int>"
            it?.name shouldBeEqualTo "Map<List<String>, Int>"
            it?.typeArguments?.map { typeArgument -> typeArgument.name } shouldBeEqualTo listOf("List<String>", "Int")
            it?.typeArguments?.map { typeArgument -> typeArgument.sourceDeclaration.name } shouldBeEqualTo listOf("List", "Int")
            it
                ?.typeArguments
                ?.firstOrNull()
                ?.typeArguments
                ?.map { typeArgument -> typeArgument.name } shouldBeEqualTo listOf("String")
            it
                ?.typeArguments
                ?.flatten()
                ?.map { typeArgument -> typeArgument.name } shouldBeEqualTo listOf("List", "String", "Int")
            it
                ?.typeArguments
                ?.firstOrNull()
                ?.typeArguments
                ?.flatten()
                ?.map { typeArgument -> typeArgument.name } shouldBeEqualTo listOf("String")
            it?.sourceDeclaration?.name shouldBeEqualTo "Map"
            it?.sourceDeclaration shouldBeInstanceOf KoKotlinTypeDeclaration::class
        }
    }

    @Test
    fun `star-projection-type-argument-to-string`() {
        // given
        val sut =
            getSnippetFile("star-projection-type-argument-to-string")
                .properties()
                .first()
                .type
                ?.asGenericTypeDeclaration()
                ?.typeArguments
                ?.firstOrNull()

        // then
        assertSoftly(sut) {
            it?.toString() shouldBeEqualTo "*"
            it?.name shouldBeEqualTo "*"
            it?.typeArguments shouldBeEqualTo null
        }
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("core/declaration/kotypeargument/snippet/forgeneral/", fileName)
}
