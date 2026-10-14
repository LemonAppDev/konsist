package com.lemonappdev.konsist.core.declaration.kotypeargument

import com.lemonappdev.konsist.TestSnippetProvider
import com.lemonappdev.konsist.testdata.FixtureClass
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoTypeArgumentDeclarationForKoFunctionTypeDeclarationProviderTest {
    @Test
    fun `type-argument-without-parameters-list`() {
        // given
        val sut =
            getSnippetFile("type-argument-without-parameters-list")
                .properties()
                .first()
                .type
                ?.typeArguments
                ?.firstOrNull()

        // then
        assertSoftly(sut) {
            it?.parameters shouldBeEqualTo null
            it?.numParameters shouldBeEqualTo 0
            it?.countParameters { parameter -> parameter.type.sourceDeclaration?.isKotlinType == true } shouldBeEqualTo 0
            it?.countParameters { parameter -> parameter.type.sourceDeclaration?.isKotlinCollectionType == true } shouldBeEqualTo 0
            it?.countParameters { parameter -> parameter.type.sourceDeclaration?.isKotlinBasicType == true } shouldBeEqualTo 0
            it?.hasParameter { parameter -> parameter.type.sourceDeclaration?.isKotlinType == true } shouldBeEqualTo false
            it?.hasAllParameters { parameter -> parameter.type.sourceDeclaration?.isKotlinType == true } shouldBeEqualTo false
        }
    }

    @Test
    fun `nullable-type-argument-without-parameters-list`() {
        // given
        val sut =
            getSnippetFile("nullable-type-argument-without-parameters-list")
                .properties()
                .first()
                .type
                ?.typeArguments
                ?.firstOrNull()

        // then
        assertSoftly(sut) {
            it?.parameters shouldBeEqualTo null
            it?.numParameters shouldBeEqualTo 0
            it?.countParameters { parameter -> parameter.type.sourceDeclaration?.isKotlinType == true } shouldBeEqualTo 0
            it?.countParameters { parameter -> parameter.type.sourceDeclaration?.isKotlinCollectionType == true } shouldBeEqualTo 0
            it?.countParameters { parameter -> parameter.type.sourceDeclaration?.isKotlinBasicType == true } shouldBeEqualTo 0
            it?.hasParameter { parameter -> parameter.type.sourceDeclaration?.isKotlinType == true } shouldBeEqualTo false
            it?.hasAllParameters { parameter -> parameter.type.sourceDeclaration?.isKotlinType == true } shouldBeEqualTo false
        }
    }

    @Test
    fun `parameters-list-is-empty`() {
        // given
        val sut =
            getSnippetFile("parameters-list-is-empty")
                .properties()
                .first()
                .type
                ?.typeArguments
                ?.firstOrNull()

        // then
        assertSoftly(sut) {
            it?.parameters shouldBeEqualTo emptyList()
            it?.numParameters shouldBeEqualTo 0
            it?.countParameters { parameter -> parameter.type.sourceDeclaration?.isKotlinType == true } shouldBeEqualTo 0
            it?.countParameters { parameter -> parameter.type.sourceDeclaration?.isKotlinCollectionType == true } shouldBeEqualTo 0
            it?.countParameters { parameter -> parameter.type.sourceDeclaration?.isKotlinBasicType == true } shouldBeEqualTo 0
            it?.hasParameter { parameter -> parameter.type.sourceDeclaration?.isKotlinType == true } shouldBeEqualTo false
            it?.hasAllParameters { parameter -> parameter.type.sourceDeclaration?.isKotlinType == true } shouldBeEqualTo true
        }
    }

    @Test
    fun `parameters-list-is-empty-in-nullable-type-argument`() {
        // given
        val sut =
            getSnippetFile("parameters-list-is-empty-in-nullable-type-argument")
                .properties()
                .first()
                .type
                ?.typeArguments
                ?.firstOrNull()

        // then
        assertSoftly(sut) {
            it?.parameters shouldBeEqualTo emptyList()
            it?.numParameters shouldBeEqualTo 0
            it?.countParameters { parameter -> parameter.type.sourceDeclaration?.isKotlinType == true } shouldBeEqualTo 0
            it?.countParameters { parameter -> parameter.type.sourceDeclaration?.isKotlinCollectionType == true } shouldBeEqualTo 0
            it?.countParameters { parameter -> parameter.type.sourceDeclaration?.isKotlinBasicType == true } shouldBeEqualTo 0
            it?.hasParameter { parameter -> parameter.type.sourceDeclaration?.isKotlinType == true } shouldBeEqualTo false
            it?.hasAllParameters { parameter -> parameter.type.sourceDeclaration?.isKotlinType == true } shouldBeEqualTo true
        }
    }

    @Test
    fun `parameters-list-has-one-element`() {
        // given
        val sut =
            getSnippetFile("parameters-list-has-one-element")
                .properties()
                .first()
                .type
                ?.typeArguments
                ?.firstOrNull()

        // then
        assertSoftly(sut) {
            it?.parameters?.map { parameter -> parameter.type.name } shouldBeEqualTo listOf("String")
            it?.numParameters shouldBeEqualTo 1
            it?.countParameters { parameter -> parameter.type.sourceDeclaration?.isKotlinType == true } shouldBeEqualTo 1
            it?.countParameters { parameter -> parameter.type.sourceDeclaration?.isKotlinCollectionType == true } shouldBeEqualTo 0
            it?.countParameters { parameter -> parameter.type.sourceDeclaration?.isKotlinBasicType == true } shouldBeEqualTo 1
            it?.countParameters { parameter -> parameter.type.sourceDeclaration?.isClass == true } shouldBeEqualTo 1
            it?.hasParameter { parameter -> parameter.type.sourceDeclaration?.isKotlinType == true } shouldBeEqualTo true
            it?.hasParameter { parameter -> parameter.type.sourceDeclaration?.isExternal == true } shouldBeEqualTo false
            it?.hasAllParameters { parameter -> parameter.type.sourceDeclaration?.isKotlinType == true } shouldBeEqualTo true
            it?.hasAllParameters { parameter -> parameter.type.sourceDeclaration?.isExternal == true } shouldBeEqualTo false
        }
    }

    @Test
    fun `parameters-list-has-one-element-in-nullable-type-argument`() {
        // given
        val sut =
            getSnippetFile("parameters-list-has-one-element-in-nullable-type-argument")
                .properties()
                .first()
                .type
                ?.typeArguments
                ?.firstOrNull()

        // then
        assertSoftly(sut) {
            it?.parameters?.map { parameter -> parameter.type.name } shouldBeEqualTo listOf("String")
            it?.numParameters shouldBeEqualTo 1
            it?.countParameters { parameter -> parameter.type.sourceDeclaration?.isKotlinType == true } shouldBeEqualTo 1
            it?.countParameters { parameter -> parameter.type.sourceDeclaration?.isKotlinCollectionType == true } shouldBeEqualTo 0
            it?.countParameters { parameter -> parameter.type.sourceDeclaration?.isKotlinBasicType == true } shouldBeEqualTo 1
            it?.countParameters { parameter -> parameter.type.sourceDeclaration?.isClass == true } shouldBeEqualTo 1
            it?.hasParameter { parameter -> parameter.type.sourceDeclaration?.isKotlinType == true } shouldBeEqualTo true
            it?.hasParameter { parameter -> parameter.type.sourceDeclaration?.isExternal == true } shouldBeEqualTo false
            it?.hasAllParameters { parameter -> parameter.type.sourceDeclaration?.isKotlinType == true } shouldBeEqualTo true
            it?.hasAllParameters { parameter -> parameter.type.sourceDeclaration?.isExternal == true } shouldBeEqualTo false
        }
    }

    @Test
    fun `parameters-list-has-two-elements`() {
        // given
        val sut =
            getSnippetFile("parameters-list-has-two-elements")
                .properties()
                .first()
                .type
                ?.typeArguments
                ?.firstOrNull()

        // then
        assertSoftly(sut) {
            it?.parameters?.map { parameter -> parameter.type.name } shouldBeEqualTo listOf("String", "List<Int>")
            it?.numParameters shouldBeEqualTo 2
            it?.countParameters { parameter -> parameter.type.sourceDeclaration?.isKotlinType == true } shouldBeEqualTo 2
            it?.countParameters { parameter -> parameter.type.sourceDeclaration?.isKotlinCollectionType == true } shouldBeEqualTo 1
            it?.countParameters { parameter -> parameter.type.sourceDeclaration?.isKotlinBasicType == true } shouldBeEqualTo 1
            it?.countParameters { parameter -> parameter.type.sourceDeclaration?.isClass == true } shouldBeEqualTo 1
            it?.hasParameter { parameter -> parameter.type.sourceDeclaration?.isKotlinType == true } shouldBeEqualTo true
            it?.hasParameter { parameter -> parameter.type.sourceDeclaration?.isExternal == true } shouldBeEqualTo false
            it?.hasAllParameters { parameter ->
                parameter.type.sourceDeclaration?.isKotlinType == true || parameter.type.isGeneric
            } shouldBeEqualTo
                true
            it?.hasAllParameters { parameter -> parameter.type.sourceDeclaration?.isExternal == true } shouldBeEqualTo false
        }
    }

    @Test
    fun `parameters-list-has-two-elements-in-nullable-type-argument`() {
        // given
        val sut =
            getSnippetFile("parameters-list-has-two-elements-in-nullable-type-argument")
                .properties()
                .first()
                .type
                ?.typeArguments
                ?.firstOrNull()

        // then
        assertSoftly(sut) {
            it?.parameters?.map { parameter -> parameter.type.name } shouldBeEqualTo listOf("String", "List<Int>")
            it?.numParameters shouldBeEqualTo 2
            it?.countParameters { parameter -> parameter.type.sourceDeclaration?.isKotlinType == true } shouldBeEqualTo 2
            it?.countParameters { parameter -> parameter.type.sourceDeclaration?.isKotlinCollectionType == true } shouldBeEqualTo 1
            it?.countParameters { parameter -> parameter.type.sourceDeclaration?.isKotlinBasicType == true } shouldBeEqualTo 1
            it?.countParameters { parameter -> parameter.type.sourceDeclaration?.isClass == true } shouldBeEqualTo 1
            it?.hasParameter { parameter -> parameter.type.sourceDeclaration?.isKotlinType == true } shouldBeEqualTo true
            it?.hasParameter { parameter -> parameter.type.sourceDeclaration?.isExternal == true } shouldBeEqualTo false
            it?.hasAllParameters { parameter ->
                parameter.type.sourceDeclaration?.isKotlinType == true || parameter.type.isGeneric
            } shouldBeEqualTo
                true
            it?.hasAllParameters { parameter -> parameter.type.sourceDeclaration?.isExternal == true } shouldBeEqualTo false
        }
    }

    @Test
    fun `parameters-list-has-one-element-with-name`() {
        // given
        val sut =
            getSnippetFile("parameters-list-has-one-element-with-name")
                .properties()
                .first()
                .type
                ?.typeArguments
                ?.firstOrNull()

        // then
        assertSoftly(sut) {
            it?.parameters?.map { parameter -> parameter.name } shouldBeEqualTo listOf("fixtureParameter")
            it?.parameters?.map { parameter -> parameter.type.name } shouldBeEqualTo listOf("String")
        }
    }

    @Test
    fun `parameters-list-has-one-element-with-name-in-nullable-type-argument`() {
        // given
        val sut =
            getSnippetFile("parameters-list-has-one-element-with-name-in-nullable-type-argument")
                .properties()
                .first()
                .type
                ?.typeArguments
                ?.firstOrNull()

        // then
        assertSoftly(sut) {
            it?.parameters?.map { parameter -> parameter.name } shouldBeEqualTo listOf("fixtureParameter")
            it?.parameters?.map { parameter -> parameter.type.name } shouldBeEqualTo listOf("String")
        }
    }

    @Test
    fun `type-argument-without-return-type`() {
        // given
        val sut =
            getSnippetFile("type-argument-without-return-type")
                .properties()
                .first()
                .type
                ?.typeArguments
                ?.firstOrNull()

        // then
        assertSoftly(sut) {
            it?.returnType shouldBeEqualTo null
            it?.hasReturnType { type -> type.sourceDeclaration?.isKotlinType == true } shouldBeEqualTo false
            it?.hasReturnType { type -> type.sourceDeclaration?.isExternal == true } shouldBeEqualTo false
            it?.hasReturnTypeOf(Unit::class) shouldBeEqualTo false
            it?.hasReturnTypeOf(String::class) shouldBeEqualTo false
        }
    }

    @Test
    fun `return-type-is-kotlin-type`() {
        // given
        val sut =
            getSnippetFile("return-type-is-kotlin-type")
                .properties()
                .first()
                .type
                ?.typeArguments
                ?.firstOrNull()

        // then
        assertSoftly(sut) {
            it?.returnType?.name shouldBeEqualTo "Unit"
            it?.hasReturnType { type -> type.sourceDeclaration?.isKotlinType == true } shouldBeEqualTo true
            it?.hasReturnType { type -> type.sourceDeclaration?.isExternal == true } shouldBeEqualTo false
            it?.hasReturnTypeOf(Unit::class) shouldBeEqualTo true
            it?.hasReturnTypeOf(String::class) shouldBeEqualTo false
        }
    }

    @Test
    fun `return-type-is-collection-type`() {
        // given
        val sut =
            getSnippetFile("return-type-is-collection-type")
                .properties()
                .first()
                .type
                ?.typeArguments
                ?.firstOrNull()

        // then
        assertSoftly(sut) {
            it?.returnType?.name shouldBeEqualTo "List<String>"
            it?.hasReturnType { type -> type.isGeneric } shouldBeEqualTo true
            it?.hasReturnType { type -> type.sourceDeclaration?.isExternal == true } shouldBeEqualTo false
            it?.hasReturnTypeOf(String::class) shouldBeEqualTo false
        }
    }

    @Test
    fun `return-type-is-complex-type`() {
        // given
        val sut =
            getSnippetFile("return-type-is-complex-type")
                .properties()
                .first()
                .type
                ?.typeArguments
                ?.firstOrNull()

        // then
        assertSoftly(sut) {
            it?.returnType?.name shouldBeEqualTo "FixtureClass"
            it?.hasReturnType { type -> type.sourceDeclaration?.isClass == true } shouldBeEqualTo true
            it?.hasReturnType { type -> type.sourceDeclaration?.isExternal == true } shouldBeEqualTo false
            it?.hasReturnTypeOf(FixtureClass::class) shouldBeEqualTo true
            it?.hasReturnTypeOf(String::class) shouldBeEqualTo false
        }
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope(
            "core/declaration/kotypeargument/snippet/forkofunctiontypedeclarationprovider/",
            fileName,
        )
}
