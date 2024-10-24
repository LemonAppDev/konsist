package com.lemonappdev.konsist.core.declaration.kotypeargument

import com.lemonappdev.konsist.TestSnippetProvider
import com.lemonappdev.konsist.testdata.SampleClass
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
            it?.parameterTypes shouldBeEqualTo null
            it?.numParameterTypes shouldBeEqualTo 0
            it?.countParameterTypes { parameter -> parameter.type.isKotlinType } shouldBeEqualTo 0
            it?.countParameterTypes { parameter -> parameter.type.isKotlinCollectionType } shouldBeEqualTo 0
            it?.countParameterTypes { parameter -> parameter.type.isKotlinBasicType } shouldBeEqualTo 0
            it?.hasParameterType { parameter -> parameter.type.isKotlinType } shouldBeEqualTo false
            it?.hasAllParameterTypes { parameter -> parameter.type.isKotlinType } shouldBeEqualTo false
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
            it?.parameterTypes shouldBeEqualTo emptyList()
            it?.numParameterTypes shouldBeEqualTo 0
            it?.countParameterTypes { parameter -> parameter.type.isKotlinType } shouldBeEqualTo 0
            it?.countParameterTypes { parameter -> parameter.type.isKotlinCollectionType } shouldBeEqualTo 0
            it?.countParameterTypes { parameter -> parameter.type.isKotlinBasicType } shouldBeEqualTo 0
            it?.hasParameterType { parameter -> parameter.type.isKotlinType } shouldBeEqualTo false
            it?.hasAllParameterTypes { parameter -> parameter.type.isKotlinType } shouldBeEqualTo true
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
            it?.parameterTypes?.map { parameter -> parameter.type.name } shouldBeEqualTo listOf("String")
            it?.numParameterTypes shouldBeEqualTo 1
            it?.countParameterTypes { parameter -> parameter.type.isKotlinType } shouldBeEqualTo 1
            it?.countParameterTypes { parameter -> parameter.type.isKotlinCollectionType } shouldBeEqualTo 0
            it?.countParameterTypes { parameter -> parameter.type.isKotlinBasicType } shouldBeEqualTo 1
            it?.countParameterTypes { parameter -> parameter.type.isClass } shouldBeEqualTo 0
            it?.hasParameterType { parameter -> parameter.type.isKotlinType } shouldBeEqualTo true
            it?.hasParameterType { parameter -> parameter.type.isExternal } shouldBeEqualTo false
            it?.hasAllParameterTypes { parameter -> parameter.type.isKotlinType } shouldBeEqualTo true
            it?.hasAllParameterTypes { parameter -> parameter.type.isExternal } shouldBeEqualTo false
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
            it?.parameterTypes?.map { parameter -> parameter.type.name } shouldBeEqualTo listOf("String", "List<Int>")
            it?.numParameterTypes shouldBeEqualTo 2
            it?.countParameterTypes { parameter -> parameter.type.isKotlinType } shouldBeEqualTo 2
            it?.countParameterTypes { parameter -> parameter.type.isKotlinCollectionType } shouldBeEqualTo 1
            it?.countParameterTypes { parameter -> parameter.type.isKotlinBasicType } shouldBeEqualTo 1
            it?.countParameterTypes { parameter -> parameter.type.isClass } shouldBeEqualTo 0
            it?.hasParameterType { parameter -> parameter.type.isKotlinType } shouldBeEqualTo true
            it?.hasParameterType { parameter -> parameter.type.isExternal } shouldBeEqualTo false
            it?.hasAllParameterTypes { parameter -> parameter.type.isKotlinType || parameter.type.isGenericType } shouldBeEqualTo true
            it?.hasAllParameterTypes { parameter -> parameter.type.isExternal } shouldBeEqualTo false
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
            it?.hasReturnType { type -> type.isKotlinType } shouldBeEqualTo false
            it?.hasReturnType { type -> type.isExternal } shouldBeEqualTo false
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
            it?.hasReturnType { type -> type.isKotlinType } shouldBeEqualTo true
            it?.hasReturnType { type -> type.isExternal } shouldBeEqualTo false
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
            it?.hasReturnType { type -> type.isGenericType } shouldBeEqualTo true
            it?.hasReturnType { type -> type.isExternal } shouldBeEqualTo false
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
            it?.returnType?.name shouldBeEqualTo "SampleClass"
            it?.hasReturnType { type -> type.isClass } shouldBeEqualTo true
            it?.hasReturnType { type -> type.isExternal } shouldBeEqualTo false
            it?.hasReturnTypeOf(SampleClass::class) shouldBeEqualTo true
            it?.hasReturnTypeOf(String::class) shouldBeEqualTo false
        }
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope(
            "core/declaration/kotypeargument/snippet/forkofunctiontypedeclarationprovider/",
            fileName,
        )
}
