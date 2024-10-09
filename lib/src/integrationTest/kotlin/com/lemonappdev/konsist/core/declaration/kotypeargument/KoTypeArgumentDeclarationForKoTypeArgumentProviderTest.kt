package com.lemonappdev.konsist.core.declaration.kotypeargument

import com.lemonappdev.konsist.TestSnippetProvider
import com.lemonappdev.konsist.api.ext.list.declaration.flatten
import com.lemonappdev.konsist.testdata.SampleClass
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoTypeArgumentDeclarationForKoTypeArgumentProviderTest {
    @Test
    fun `not-generic-type-argument-type-arguments`() {
        // given
        val sut =
            getSnippetFile("not-generic-type-argument-type-arguments")
                .properties()
                .first()
                .type
                ?.asGenericTypeDeclaration()
                ?.typeArguments
                ?.firstOrNull()

        // then
        assertSoftly(sut) {
            it?.typeArguments shouldBeEqualTo null
            it?.numTypeArguments shouldBeEqualTo 0
            it?.countTypeArguments { typeArgument -> typeArgument.name == "String" } shouldBeEqualTo 0
            it?.hasTypeArguments() shouldBeEqualTo false
            it?.hasTypeArgumentWithName("String", "Int") shouldBeEqualTo false
            it?.hasTypeArgumentWithName(listOf("String", "Int")) shouldBeEqualTo false
            it?.hasTypeArgumentsWithAllNames("String") shouldBeEqualTo false
            it?.hasTypeArgumentsWithAllNames(listOf("String")) shouldBeEqualTo false
            it?.hasTypeArgumentOf(String::class, Int::class) shouldBeEqualTo false
            it?.hasTypeArgumentOf(listOf(SampleClass::class, Int::class)) shouldBeEqualTo false
            it?.hasAllTypeArgumentsOf(String::class) shouldBeEqualTo false
            it?.hasAllTypeArgumentsOf(listOf(String::class)) shouldBeEqualTo false
            it?.hasTypeArgument { type -> type.sourceDeclaration.isKotlinType } shouldBeEqualTo false
            it?.hasAllTypeArguments { type -> type.sourceDeclaration.isKotlinType } shouldBeEqualTo false
        }
    }

    @Test
    fun `generic-type-argument-type-arguments`() {
        // given
        val sut =
            getSnippetFile("generic-type-argument-type-arguments")
                .properties()
                .first()
                .type
                ?.asGenericTypeDeclaration()
                ?.typeArguments
                ?.firstOrNull()

        // then
        assertSoftly(sut) {
            it?.typeArguments?.map { typeArgument -> typeArgument.name } shouldBeEqualTo listOf("String")
            it?.typeArguments?.map { typeArgument -> typeArgument.sourceDeclaration.name } shouldBeEqualTo listOf("String")
            it
                ?.typeArguments
                ?.flatten()
                ?.map { typeArgument -> typeArgument.name } shouldBeEqualTo listOf("String")
            it?.typeArguments?.firstOrNull()?.typeArguments shouldBeEqualTo null
            it?.numTypeArguments shouldBeEqualTo 1
            it?.countTypeArguments { type -> type.sourceDeclaration.isKotlinType } shouldBeEqualTo 1
            it?.countTypeArguments { type -> type.sourceDeclaration.isClass } shouldBeEqualTo 0
            it?.hasTypeArguments() shouldBeEqualTo true
            it?.hasTypeArgumentWithName("String", "Int") shouldBeEqualTo true
            it?.hasTypeArgumentWithName("OtherClass", "Int") shouldBeEqualTo false
            it?.hasTypeArgumentWithName(listOf("String", "Int")) shouldBeEqualTo true
            it?.hasTypeArgumentWithName(listOf("OtherClass", "Int")) shouldBeEqualTo false
            it?.hasTypeArgumentsWithAllNames("String") shouldBeEqualTo true
            it?.hasTypeArgumentsWithAllNames("String", "Int") shouldBeEqualTo false
            it?.hasTypeArgumentsWithAllNames("OtherClass", "Int") shouldBeEqualTo false
            it?.hasTypeArgumentsWithAllNames(listOf("String")) shouldBeEqualTo true
            it?.hasTypeArgumentsWithAllNames(listOf("String", "Int")) shouldBeEqualTo false
            it?.hasTypeArgumentsWithAllNames(listOf("OtherClass", "Int")) shouldBeEqualTo false
            it?.hasTypeArgumentOf(String::class, Int::class) shouldBeEqualTo true
            it?.hasTypeArgumentOf(SampleClass::class, Int::class) shouldBeEqualTo false
            it?.hasTypeArgumentOf(listOf(String::class, Int::class)) shouldBeEqualTo true
            it?.hasTypeArgumentOf(listOf(SampleClass::class, Int::class)) shouldBeEqualTo false
            it?.hasAllTypeArgumentsOf(String::class) shouldBeEqualTo true
            it?.hasAllTypeArgumentsOf(String::class, Int::class) shouldBeEqualTo false
            it?.hasAllTypeArgumentsOf(SampleClass::class, Int::class) shouldBeEqualTo false
            it?.hasAllTypeArgumentsOf(listOf(String::class)) shouldBeEqualTo true
            it?.hasAllTypeArgumentsOf(listOf(String::class, Int::class)) shouldBeEqualTo false
            it?.hasAllTypeArgumentsOf(listOf(SampleClass::class, Int::class)) shouldBeEqualTo false
            it?.hasTypeArgument { type -> type.sourceDeclaration.isKotlinType } shouldBeEqualTo true
            it?.hasTypeArgument { type -> type.sourceDeclaration.isExternalType } shouldBeEqualTo false
            it?.hasAllTypeArguments { type -> type.sourceDeclaration.isKotlinType } shouldBeEqualTo true
            it?.hasAllTypeArguments { type -> type.sourceDeclaration.isExternalType } shouldBeEqualTo false
        }
    }

    @Test
    fun `generic-complex-type-argument-type-arguments`() {
        // given
        val sut =
            getSnippetFile("generic-complex-type-argument-type-arguments")
                .properties()
                .first()
                .type
                ?.asGenericTypeDeclaration()
                ?.typeArguments
                ?.firstOrNull()

        // then
        assertSoftly(sut) {
            it?.typeArguments?.map { typeArgument -> typeArgument.name } shouldBeEqualTo listOf("List<String>", "Int")
            it?.typeArguments?.map { typeArgument -> typeArgument.genericType.name } shouldBeEqualTo listOf("List", "Int")
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
            it?.numTypeArguments shouldBeEqualTo 2
            it?.countTypeArguments { type -> type.sourceDeclaration.isKotlinType } shouldBeEqualTo 1
            it?.countTypeArguments { type -> type.sourceDeclaration.isClass } shouldBeEqualTo 0
            it?.hasTypeArguments() shouldBeEqualTo true
            it?.hasTypeArgumentWithName("Int", "String") shouldBeEqualTo true
            it?.hasTypeArgumentWithName("OtherClass", "String") shouldBeEqualTo false
            it?.hasTypeArgumentWithName(listOf("Int", "String")) shouldBeEqualTo true
            it?.hasTypeArgumentWithName(listOf("OtherClass", "String")) shouldBeEqualTo false
            it?.hasTypeArgumentsWithAllNames("Int") shouldBeEqualTo true
            it?.hasTypeArgumentsWithAllNames("Int", "String") shouldBeEqualTo false
            it?.hasTypeArgumentsWithAllNames("OtherClass", "String") shouldBeEqualTo false
            it?.hasTypeArgumentsWithAllNames(listOf("Int")) shouldBeEqualTo true
            it?.hasTypeArgumentsWithAllNames(listOf("Int", "String")) shouldBeEqualTo false
            it?.hasTypeArgumentsWithAllNames(listOf("OtherClass", "String")) shouldBeEqualTo false
            it?.hasTypeArgumentOf(Int::class, String::class) shouldBeEqualTo true
            it?.hasTypeArgumentOf(SampleClass::class, String::class) shouldBeEqualTo false
            it?.hasTypeArgumentOf(listOf(Int::class, String::class)) shouldBeEqualTo true
            it?.hasTypeArgumentOf(listOf(SampleClass::class, String::class)) shouldBeEqualTo false
            it?.hasAllTypeArgumentsOf(Int::class) shouldBeEqualTo true
            it?.hasAllTypeArgumentsOf(Int::class, String::class) shouldBeEqualTo false
            it?.hasAllTypeArgumentsOf(SampleClass::class, String::class) shouldBeEqualTo false
            it?.hasAllTypeArgumentsOf(listOf(Int::class)) shouldBeEqualTo true
            it?.hasAllTypeArgumentsOf(listOf(Int::class, String::class)) shouldBeEqualTo false
            it?.hasAllTypeArgumentsOf(listOf(SampleClass::class, String::class)) shouldBeEqualTo false
            it?.hasTypeArgument { type -> type.sourceDeclaration.isKotlinType } shouldBeEqualTo true
            it?.hasTypeArgument { type -> type.sourceDeclaration.isExternalType } shouldBeEqualTo false
            it?.hasAllTypeArguments { type -> type.genericType.isKotlinType } shouldBeEqualTo true
            it?.hasAllTypeArguments { type -> type.sourceDeclaration.isExternalType } shouldBeEqualTo false
        }
    }

    @Test
    fun `star-projection-type-argument-type-arguments`() {
        // given
        val sut =
            getSnippetFile("star-projection-type-argument-type-arguments")
                .properties()
                .first()
                .type
                ?.asGenericTypeDeclaration()
                ?.typeArguments
                ?.firstOrNull()

        // then
        assertSoftly(sut) {
            it?.typeArguments shouldBeEqualTo null
            it?.numTypeArguments shouldBeEqualTo 0
            it?.countTypeArguments { typeArgument -> typeArgument.name == "*" } shouldBeEqualTo 0
            it?.hasTypeArguments() shouldBeEqualTo false
            it?.hasTypeArgumentWithName("*", "Int") shouldBeEqualTo false
            it?.hasTypeArgumentWithName(listOf("*", "Int")) shouldBeEqualTo false
            it?.hasTypeArgumentsWithAllNames("*") shouldBeEqualTo false
            it?.hasTypeArgumentsWithAllNames(listOf("*")) shouldBeEqualTo false
            it?.hasTypeArgumentOf(String::class, Int::class) shouldBeEqualTo false
            it?.hasTypeArgumentOf(listOf(SampleClass::class, Int::class)) shouldBeEqualTo false
            it?.hasAllTypeArgumentsOf(String::class) shouldBeEqualTo false
            it?.hasAllTypeArgumentsOf(listOf(String::class)) shouldBeEqualTo false
            it?.hasTypeArgument { type -> type.sourceDeclaration.isExternalType } shouldBeEqualTo false
            it?.hasAllTypeArguments { type -> type.sourceDeclaration.isExternalType } shouldBeEqualTo false
        }
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("core/declaration/kotypeargument/snippet/forkotypeargumentprovider/", fileName)
}
