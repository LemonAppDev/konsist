package com.lemonappdev.konsist.core.declaration.type.kofunctiontype

import com.lemonappdev.konsist.TestSnippetProvider
import com.lemonappdev.konsist.api.declaration.type.KoFunctionTypeDeclaration
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoFunctionTypeDeclarationTest {
    @Test
    fun `type-to-string`() {
        // given
        val sut = getSnippetFile("type-to-string")
            .properties()
            .first()
            .type

        // then
        sut.toString() shouldBeEqualTo "() -> Unit"
    }

    @Test
    fun `parameters-list-is-empty`() {
        // given
        val type = getSnippetFile("parameters-list-is-empty")
            .properties()
            .first()
            .type

        val sut = (type as? KoFunctionTypeDeclaration)?.parameterTypes

        // then
        sut shouldBeEqualTo emptyList()
    }

    @Test
    fun `parameters-list-has-one-element`() {
        // given
        val type = getSnippetFile("parameters-list-has-one-element")
            .properties()
            .first()
            .type

        val sut = (type as? KoFunctionTypeDeclaration)?.parameterTypes

        // then
        sut?.map { it.type.name } shouldBeEqualTo listOf("String")
    }

    @Test
    fun `parameters-list-has-two-elements`() {
        // given
        val type = getSnippetFile("parameters-list-has-two-elements")
            .properties()
            .first()
            .type

        val sut = (type as? KoFunctionTypeDeclaration)?.parameterTypes

        // then
        sut?.map { it.type.name } shouldBeEqualTo listOf("String", "List<Int>")
    }

    @Test
    fun `return-type-is-kotlin-type`() {
        // given
        val type = getSnippetFile("return-type-is-kotlin-type")
            .properties()
            .first()
            .type

        val sut = (type as? KoFunctionTypeDeclaration)?.returnType

        // then
        sut?.name shouldBeEqualTo "Unit"
    }

    @Test
    fun `return-type-is-collection-type`() {
        // given
        val type = getSnippetFile("return-type-is-collection-type")
            .properties()
            .first()
            .type

        val sut = (type as? KoFunctionTypeDeclaration)?.returnType

        // then
        sut?.name shouldBeEqualTo "List<String>"
    }

    @Test
    fun `return-type-is-complex-type`() {
        // given
        val type = getSnippetFile("return-type-is-complex-type")
            .properties()
            .first()
            .type

        val sut = (type as? KoFunctionTypeDeclaration)?.returnType

        // then
        sut?.name shouldBeEqualTo "SampleClass"
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("core/declaration/type/kofunctiontype/snippet/forgeneral/", fileName)
}
