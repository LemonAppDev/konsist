package com.lemonappdev.konsist.core.declaration.type.kofunctiontype

import com.lemonappdev.konsist.TestSnippetProvider
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoFunctionTypeDeclarationTest {
    @Test
    fun `type-to-string`() {
        // given
        val sut =
            getSnippetFile("type-to-string")
                .properties()
                .first()
                .type
                ?.asFunctionTypeDeclaration()

        // then
        sut.toString() shouldBeEqualTo "() -> Unit"
    }

    @Test
    fun `parameters-list-is-empty`() {
        // given
        val sut =
            getSnippetFile("parameters-list-is-empty")
                .properties()
                .first()
                .type
                ?.asFunctionTypeDeclaration()

        // then
        sut?.parameterTypes shouldBeEqualTo emptyList()
    }

    @Test
    fun `parameters-list-has-one-element`() {
        // given
        val sut =
            getSnippetFile("parameters-list-has-one-element")
                .properties()
                .first()
                .type
                ?.asFunctionTypeDeclaration()

        // then
        sut?.parameterTypes?.map { it.type.name } shouldBeEqualTo listOf("String")
    }

    @Test
    fun `parameters-list-has-two-elements`() {
        // given
        val sut =
            getSnippetFile("parameters-list-has-two-elements")
                .properties()
                .first()
                .type
                ?.asFunctionTypeDeclaration()

        // then
        sut?.parameterTypes?.map { it.type.name } shouldBeEqualTo listOf("String", "List<Int>")
    }

    @Test
    fun `return-type-is-kotlin-type`() {
        // given
        val sut =
            getSnippetFile("return-type-is-kotlin-type")
                .properties()
                .first()
                .type
                ?.asFunctionTypeDeclaration()

        // then
        sut?.returnType?.name shouldBeEqualTo "Unit"
    }

    @Test
    fun `return-type-is-collection-type`() {
        // given
        val sut =
            getSnippetFile("return-type-is-collection-type")
                .properties()
                .first()
                .type
                ?.asFunctionTypeDeclaration()

        // then
        sut?.returnType?.name shouldBeEqualTo "List<String>"
    }

    @Test
    fun `return-type-is-complex-type`() {
        // given
        val sut =
            getSnippetFile("return-type-is-complex-type")
                .properties()
                .first()
                .type
                ?.asFunctionTypeDeclaration()

        // then
        sut?.returnType?.name shouldBeEqualTo "SampleClass"
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("core/declaration/type/kofunctiontype/snippet/forgeneral/", fileName)
}
