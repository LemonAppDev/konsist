package com.lemonappdev.konsist.core.declaration.type.kogenerictype

import com.lemonappdev.konsist.TestSnippetProvider
import com.lemonappdev.konsist.api.declaration.KoClassDeclaration
import com.lemonappdev.konsist.api.declaration.KoExternalDeclaration
import com.lemonappdev.konsist.api.declaration.KoImportAliasDeclaration
import com.lemonappdev.konsist.api.declaration.KoInterfaceDeclaration
import com.lemonappdev.konsist.api.declaration.KoObjectDeclaration
import com.lemonappdev.konsist.api.declaration.KoTypeAliasDeclaration
import com.lemonappdev.konsist.api.declaration.type.KoFunctionTypeDeclaration
import com.lemonappdev.konsist.api.declaration.type.KoGenericTypeDeclaration
import com.lemonappdev.konsist.api.declaration.type.KoKotlinTypeDeclaration
import com.lemonappdev.konsist.externalsample.SampleExternalClass
import com.lemonappdev.konsist.externalsample.SampleExternalGenericClass
import com.lemonappdev.konsist.testdata.SampleClass
import com.lemonappdev.konsist.testdata.SampleGenericClassWithParameter
import com.lemonappdev.konsist.testdata.SampleGenericSuperInterface
import com.lemonappdev.konsist.testdata.SampleInterface
import com.lemonappdev.konsist.testdata.SampleObject
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldBeInstanceOf
import org.junit.jupiter.api.Test

@Suppress("detekt.LongMethod")
class KoGenericTypeDeclarationForKoGenericTypeDeclarationProviderTest {
    @Test
    fun `kotlin-generic-type`() {
        // given
        val sut =
            getSnippetFile("kotlin-type")
                .properties()
                .first()
                .type
                ?.asGenericTypeDeclaration()

        // then
        assertSoftly(sut) {
            it?.type?.declaration shouldBeInstanceOf KoKotlinTypeDeclaration::class
            it?.type?.name shouldBeEqualTo "List"
            it?.hasType { type -> type.isKotlinCollectionType } shouldBeEqualTo true
            it?.hasType { type -> type.isClass } shouldBeEqualTo false
            it?.hasTypeOf(List::class) shouldBeEqualTo true
            it?.hasTypeOf(Map::class) shouldBeEqualTo false
        }
    }

    @Test
    fun `class-type`() {
        // given
        val sut =
            getSnippetFile("class-type")
                .properties()
                .first()
                .type
                ?.asGenericTypeDeclaration()

        // then
        assertSoftly(sut) {
            it?.type?.declaration shouldBeInstanceOf KoClassDeclaration::class
            it?.type?.name shouldBeEqualTo "SampleGenericClassWithParameter"
            it?.hasType { type -> type.isClass } shouldBeEqualTo true
            it?.hasType { type -> type.isKotlinType } shouldBeEqualTo false
            it?.hasTypeOf(SampleGenericClassWithParameter::class) shouldBeEqualTo true
            it?.hasTypeOf(Map::class) shouldBeEqualTo false
        }
    }

    @Test
    fun `interface-type`() {
        // given
        val sut =
            getSnippetFile("interface-type")
                .properties()
                .first()
                .type
                ?.asGenericTypeDeclaration()

        // then
        assertSoftly(sut) {
            it?.type?.declaration shouldBeInstanceOf KoInterfaceDeclaration::class
            it?.type?.name shouldBeEqualTo "SampleGenericSuperInterface"
            it?.hasType { type -> type.isInterface } shouldBeEqualTo true
            it?.hasType { type -> type.isClass } shouldBeEqualTo false
            it?.hasTypeOf(SampleGenericSuperInterface::class) shouldBeEqualTo true
            it?.hasTypeOf(Map::class) shouldBeEqualTo false
        }
    }

    @Test
    fun `nested-generic-type`() {
        // given
        val sut =
            getSnippetFile("nested-generic-type")
                .properties()
                .first()
                .type
                ?.asGenericTypeDeclaration()

        // then
        assertSoftly(sut) {
            it
                ?.typeArguments
                ?.firstOrNull()
                ?.asGenericTypeDeclaration()
                ?.type
                ?.declaration
                ?.shouldBeInstanceOf(KoKotlinTypeDeclaration::class)
            it
                ?.typeArguments
                ?.firstOrNull()
                ?.asGenericTypeDeclaration()
                ?.type
                ?.name
                ?.shouldBeEqualTo("Set")
        }
    }

    @Test
    fun `import-alias-type`() {
        // given
        val sut =
            getSnippetFile("import-alias-type")
                .properties()
                .first()
                .type
                ?.asGenericTypeDeclaration()

        // then
        assertSoftly(sut) {
            it?.type?.declaration shouldBeInstanceOf KoImportAliasDeclaration::class
            it?.type?.name shouldBeEqualTo "SampleImportAlias"
            it?.hasType { type -> type.isImportAlias } shouldBeEqualTo true
            it?.hasType { type -> type.isClass } shouldBeEqualTo false
            it?.hasTypeOf(Map::class) shouldBeEqualTo false
        }
    }

    @Test
    fun `external-type`() {
        // given
        val sut =
            getSnippetFile("external-type")
                .properties()
                .first()
                .type
                ?.asGenericTypeDeclaration()

        // then
        assertSoftly(sut) {
            it?.type?.declaration shouldBeInstanceOf KoExternalDeclaration::class
            it?.type?.name shouldBeEqualTo "SampleExternalGenericClass"
            it?.hasType { type -> type.isExternalType } shouldBeEqualTo true
            it?.hasType { type -> type.isClass } shouldBeEqualTo false
            it?.hasTypeOf(SampleExternalGenericClass::class) shouldBeEqualTo true
            it?.hasTypeOf(Map::class) shouldBeEqualTo false
        }
    }

    @Test
    fun `kotlin-type-argument`() {
        // given
        val sut =
            getSnippetFile("kotlin-type-argument")
                .properties()
                .first()
                .type
                ?.asGenericTypeDeclaration()

        // then
        assertSoftly(sut) {
            it?.typeArguments?.firstOrNull()?.declaration shouldBeInstanceOf KoKotlinTypeDeclaration::class
            it?.typeArguments?.firstOrNull()?.name shouldBeEqualTo "String"
            it?.typeArgumentsFlatten?.map { typeArgument -> typeArgument.name } shouldBeEqualTo listOf("String")

            it?.numTypeArguments shouldBeEqualTo 1
            it?.countTypeArguments { type -> type.isKotlinType } shouldBeEqualTo 1
            it?.countTypeArguments { type -> type.isClass } shouldBeEqualTo 0
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
            it?.hasTypeArgument { type -> type.isKotlinType } shouldBeEqualTo true
            it?.hasTypeArgument { type -> type.isExternalType } shouldBeEqualTo false
            it?.hasAllTypeArguments { type -> type.isKotlinType } shouldBeEqualTo true
            it?.hasAllTypeArguments { type -> type.isExternalType } shouldBeEqualTo false

            it?.numTypeArgumentsFlatten shouldBeEqualTo 1
            it?.countTypeArgumentsFlatten { type -> type.isKotlinType } shouldBeEqualTo 1
            it?.countTypeArgumentsFlatten { type -> type.isClass } shouldBeEqualTo 0
            it?.hasTypeArgumentFlattenWithName("String", "Int") shouldBeEqualTo true
            it?.hasTypeArgumentFlattenWithName("OtherClass", "Int") shouldBeEqualTo false
            it?.hasTypeArgumentFlattenWithName(listOf("String", "Int")) shouldBeEqualTo true
            it?.hasTypeArgumentFlattenWithName(listOf("OtherClass", "Int")) shouldBeEqualTo false
            it?.hasTypeArgumentsFlattenWithAllNames("String") shouldBeEqualTo true
            it?.hasTypeArgumentsFlattenWithAllNames("String", "Int") shouldBeEqualTo false
            it?.hasTypeArgumentsFlattenWithAllNames("OtherClass", "Int") shouldBeEqualTo false
            it?.hasTypeArgumentsFlattenWithAllNames(listOf("String")) shouldBeEqualTo true
            it?.hasTypeArgumentsFlattenWithAllNames(listOf("String", "Int")) shouldBeEqualTo false
            it?.hasTypeArgumentsFlattenWithAllNames(listOf("OtherClass", "Int")) shouldBeEqualTo false
            it?.hasTypeArgumentFlattenOf(String::class) shouldBeEqualTo true
            it?.hasTypeArgumentFlattenOf(String::class, Int::class) shouldBeEqualTo true
            it?.hasTypeArgumentFlattenOf(SampleClass::class, Int::class) shouldBeEqualTo false
            it?.hasTypeArgumentFlattenOf(listOf(String::class)) shouldBeEqualTo true
            it?.hasTypeArgumentFlattenOf(listOf(String::class, Int::class)) shouldBeEqualTo true
            it?.hasTypeArgumentFlattenOf(listOf(SampleClass::class, Int::class)) shouldBeEqualTo false
            it?.hasAllTypeArgumentsFlattenOf(String::class) shouldBeEqualTo true
            it?.hasAllTypeArgumentsFlattenOf(String::class, Int::class) shouldBeEqualTo false
            it?.hasAllTypeArgumentsFlattenOf(SampleClass::class, Int::class) shouldBeEqualTo false
            it?.hasAllTypeArgumentsFlattenOf(listOf(String::class)) shouldBeEqualTo true
            it?.hasAllTypeArgumentsFlattenOf(listOf(String::class, Int::class)) shouldBeEqualTo false
            it?.hasAllTypeArgumentsFlattenOf(listOf(SampleClass::class, Int::class)) shouldBeEqualTo false
            it?.hasTypeArgumentFlatten { type -> type.isKotlinType } shouldBeEqualTo true
            it?.hasTypeArgumentFlatten { type -> type.isExternalType } shouldBeEqualTo false
            it?.hasAllTypeArgumentsFlatten { type -> type.isKotlinType } shouldBeEqualTo true
            it?.hasAllTypeArgumentsFlatten { type -> type.isExternalType } shouldBeEqualTo false
        }
    }

    @Test
    fun `class-type-argument`() {
        // given
        val sut =
            getSnippetFile("class-type-argument")
                .properties()
                .first()
                .type
                ?.asGenericTypeDeclaration()

        // then
        assertSoftly(sut) {
            it?.typeArguments?.firstOrNull()?.declaration shouldBeInstanceOf KoClassDeclaration::class
            it?.typeArguments?.firstOrNull()?.name shouldBeEqualTo "SampleClass"
            it?.typeArgumentsFlatten?.map { typeArgument -> typeArgument.name } shouldBeEqualTo listOf("SampleClass")

            it?.numTypeArguments shouldBeEqualTo 1
            it?.countTypeArguments { type -> type.isClass } shouldBeEqualTo 1
            it?.countTypeArguments { type -> type.isExternalType } shouldBeEqualTo 0
            it?.hasTypeArgumentWithName("SampleClass", "Int") shouldBeEqualTo true
            it?.hasTypeArgumentWithName("OtherClass", "Int") shouldBeEqualTo false
            it?.hasTypeArgumentWithName(listOf("SampleClass", "Int")) shouldBeEqualTo true
            it?.hasTypeArgumentWithName(listOf("OtherClass", "Int")) shouldBeEqualTo false
            it?.hasTypeArgumentsWithAllNames("SampleClass") shouldBeEqualTo true
            it?.hasTypeArgumentsWithAllNames("SampleClass", "Int") shouldBeEqualTo false
            it?.hasTypeArgumentsWithAllNames("OtherClass", "Int") shouldBeEqualTo false
            it?.hasTypeArgumentsWithAllNames(listOf("SampleClass")) shouldBeEqualTo true
            it?.hasTypeArgumentsWithAllNames(listOf("SampleClass", "Int")) shouldBeEqualTo false
            it?.hasTypeArgumentsWithAllNames(listOf("OtherClass", "Int")) shouldBeEqualTo false
            it?.hasTypeArgumentOf(SampleClass::class, Int::class) shouldBeEqualTo true
            it?.hasTypeArgumentOf(SampleInterface::class, Int::class) shouldBeEqualTo false
            it?.hasTypeArgumentOf(listOf(SampleClass::class, Int::class)) shouldBeEqualTo true
            it?.hasTypeArgumentOf(listOf(SampleInterface::class, Int::class)) shouldBeEqualTo false
            it?.hasAllTypeArgumentsOf(SampleClass::class) shouldBeEqualTo true
            it?.hasAllTypeArgumentsOf(SampleClass::class, Int::class) shouldBeEqualTo false
            it?.hasAllTypeArgumentsOf(SampleInterface::class, Int::class) shouldBeEqualTo false
            it?.hasAllTypeArgumentsOf(listOf(SampleClass::class)) shouldBeEqualTo true
            it?.hasAllTypeArgumentsOf(listOf(SampleClass::class, Int::class)) shouldBeEqualTo false
            it?.hasAllTypeArgumentsOf(listOf(SampleInterface::class, Int::class)) shouldBeEqualTo false
            it?.hasTypeArgument { type -> type.isClass } shouldBeEqualTo true
            it?.hasTypeArgument { type -> type.isExternalType } shouldBeEqualTo false
            it?.hasAllTypeArguments { type -> type.isClass } shouldBeEqualTo true
            it?.hasAllTypeArguments { type -> type.isExternalType } shouldBeEqualTo false

            it?.numTypeArgumentsFlatten shouldBeEqualTo 1
            it?.countTypeArgumentsFlatten { type -> type.isClass } shouldBeEqualTo 1
            it?.countTypeArgumentsFlatten { type -> type.isExternalType } shouldBeEqualTo 0
            it?.hasTypeArgumentFlattenWithName("SampleClass", "Int") shouldBeEqualTo true
            it?.hasTypeArgumentFlattenWithName("OtherClass", "Int") shouldBeEqualTo false
            it?.hasTypeArgumentFlattenWithName(listOf("SampleClass", "Int")) shouldBeEqualTo true
            it?.hasTypeArgumentFlattenWithName(listOf("OtherClass", "Int")) shouldBeEqualTo false
            it?.hasTypeArgumentsFlattenWithAllNames("SampleClass") shouldBeEqualTo true
            it?.hasTypeArgumentsFlattenWithAllNames("SampleClass", "Int") shouldBeEqualTo false
            it?.hasTypeArgumentsFlattenWithAllNames("OtherClass", "Int") shouldBeEqualTo false
            it?.hasTypeArgumentsFlattenWithAllNames(listOf("SampleClass")) shouldBeEqualTo true
            it?.hasTypeArgumentsFlattenWithAllNames(listOf("SampleClass", "Int")) shouldBeEqualTo false
            it?.hasTypeArgumentsFlattenWithAllNames(listOf("OtherClass", "Int")) shouldBeEqualTo false
            it?.hasTypeArgumentFlattenOf(SampleClass::class) shouldBeEqualTo true
            it?.hasTypeArgumentFlattenOf(SampleClass::class, Int::class) shouldBeEqualTo true
            it?.hasTypeArgumentFlattenOf(SampleInterface::class, Int::class) shouldBeEqualTo false
            it?.hasTypeArgumentFlattenOf(listOf(SampleClass::class)) shouldBeEqualTo true
            it?.hasTypeArgumentFlattenOf(listOf(SampleClass::class, Int::class)) shouldBeEqualTo true
            it?.hasTypeArgumentFlattenOf(listOf(SampleInterface::class, Int::class)) shouldBeEqualTo false
            it?.hasAllTypeArgumentsFlattenOf(SampleClass::class) shouldBeEqualTo true
            it?.hasAllTypeArgumentsFlattenOf(SampleClass::class, Int::class) shouldBeEqualTo false
            it?.hasAllTypeArgumentsFlattenOf(SampleInterface::class, Int::class) shouldBeEqualTo false
            it?.hasAllTypeArgumentsFlattenOf(listOf(SampleClass::class)) shouldBeEqualTo true
            it?.hasAllTypeArgumentsFlattenOf(listOf(SampleClass::class, Int::class)) shouldBeEqualTo false
            it?.hasAllTypeArgumentsFlattenOf(listOf(SampleInterface::class, Int::class)) shouldBeEqualTo false
            it?.hasTypeArgumentFlatten { type -> type.isClass } shouldBeEqualTo true
            it?.hasTypeArgumentFlatten { type -> type.isExternalType } shouldBeEqualTo false
            it?.hasAllTypeArgumentsFlatten { type -> type.isClass } shouldBeEqualTo true
            it?.hasAllTypeArgumentsFlatten { type -> type.isExternalType } shouldBeEqualTo false
        }
    }

    @Test
    fun `interface-type-argument`() {
        // given
        val sut =
            getSnippetFile("interface-type-argument")
                .properties()
                .first()
                .type
                ?.asGenericTypeDeclaration()

        // then
        assertSoftly(sut) {
            it?.typeArguments?.firstOrNull()?.declaration shouldBeInstanceOf KoInterfaceDeclaration::class
            it?.typeArguments?.firstOrNull()?.name shouldBeEqualTo "SampleInterface"
            it?.typeArgumentsFlatten?.map { typeArgument -> typeArgument.name } shouldBeEqualTo listOf("SampleInterface")

            it?.numTypeArguments shouldBeEqualTo 1
            it?.countTypeArguments { type -> type.isInterface } shouldBeEqualTo 1
            it?.countTypeArguments { type -> type.isClass } shouldBeEqualTo 0
            it?.hasTypeArgumentWithName("SampleInterface", "Int") shouldBeEqualTo true
            it?.hasTypeArgumentWithName("OtherClass", "Int") shouldBeEqualTo false
            it?.hasTypeArgumentWithName(listOf("SampleInterface", "Int")) shouldBeEqualTo true
            it?.hasTypeArgumentWithName(listOf("OtherClass", "Int")) shouldBeEqualTo false
            it?.hasTypeArgumentsWithAllNames("SampleInterface") shouldBeEqualTo true
            it?.hasTypeArgumentsWithAllNames("SampleInterface", "Int") shouldBeEqualTo false
            it?.hasTypeArgumentsWithAllNames("OtherClass", "Int") shouldBeEqualTo false
            it?.hasTypeArgumentsWithAllNames(listOf("SampleInterface")) shouldBeEqualTo true
            it?.hasTypeArgumentsWithAllNames(listOf("SampleInterface", "Int")) shouldBeEqualTo false
            it?.hasTypeArgumentsWithAllNames(listOf("OtherClass", "Int")) shouldBeEqualTo false
            it?.hasTypeArgumentOf(SampleInterface::class, Int::class) shouldBeEqualTo true
            it?.hasTypeArgumentOf(SampleClass::class, Int::class) shouldBeEqualTo false
            it?.hasTypeArgumentOf(listOf(SampleInterface::class, Int::class)) shouldBeEqualTo true
            it?.hasTypeArgumentOf(listOf(SampleClass::class, Int::class)) shouldBeEqualTo false
            it?.hasAllTypeArgumentsOf(SampleInterface::class) shouldBeEqualTo true
            it?.hasAllTypeArgumentsOf(SampleInterface::class, Int::class) shouldBeEqualTo false
            it?.hasAllTypeArgumentsOf(SampleClass::class, Int::class) shouldBeEqualTo false
            it?.hasAllTypeArgumentsOf(listOf(SampleInterface::class)) shouldBeEqualTo true
            it?.hasAllTypeArgumentsOf(listOf(SampleInterface::class, Int::class)) shouldBeEqualTo false
            it?.hasAllTypeArgumentsOf(listOf(SampleClass::class, Int::class)) shouldBeEqualTo false
            it?.hasTypeArgument { type -> type.isInterface } shouldBeEqualTo true
            it?.hasTypeArgument { type -> type.isExternalType } shouldBeEqualTo false
            it?.hasAllTypeArguments { type -> type.isInterface } shouldBeEqualTo true
            it?.hasAllTypeArguments { type -> type.isExternalType } shouldBeEqualTo false

            it?.numTypeArgumentsFlatten shouldBeEqualTo 1
            it?.countTypeArgumentsFlatten { type -> type.isInterface } shouldBeEqualTo 1
            it?.countTypeArgumentsFlatten { type -> type.isClass } shouldBeEqualTo 0
            it?.hasTypeArgumentFlattenWithName("SampleInterface", "Int") shouldBeEqualTo true
            it?.hasTypeArgumentFlattenWithName("OtherClass", "Int") shouldBeEqualTo false
            it?.hasTypeArgumentFlattenWithName(listOf("SampleInterface", "Int")) shouldBeEqualTo true
            it?.hasTypeArgumentFlattenWithName(listOf("OtherClass", "Int")) shouldBeEqualTo false
            it?.hasTypeArgumentsFlattenWithAllNames("SampleInterface") shouldBeEqualTo true
            it?.hasTypeArgumentsFlattenWithAllNames("SampleInterface", "Int") shouldBeEqualTo false
            it?.hasTypeArgumentsFlattenWithAllNames("OtherClass", "Int") shouldBeEqualTo false
            it?.hasTypeArgumentsFlattenWithAllNames(listOf("SampleInterface")) shouldBeEqualTo true
            it?.hasTypeArgumentsFlattenWithAllNames(listOf("SampleInterface", "Int")) shouldBeEqualTo false
            it?.hasTypeArgumentsFlattenWithAllNames(listOf("OtherClass", "Int")) shouldBeEqualTo false
            it?.hasTypeArgumentFlattenOf(SampleInterface::class) shouldBeEqualTo true
            it?.hasTypeArgumentFlattenOf(SampleInterface::class, Int::class) shouldBeEqualTo true
            it?.hasTypeArgumentFlattenOf(SampleClass::class, Int::class) shouldBeEqualTo false
            it?.hasTypeArgumentFlattenOf(listOf(SampleInterface::class)) shouldBeEqualTo true
            it?.hasTypeArgumentFlattenOf(listOf(SampleInterface::class, Int::class)) shouldBeEqualTo true
            it?.hasTypeArgumentFlattenOf(listOf(SampleClass::class, Int::class)) shouldBeEqualTo false
            it?.hasAllTypeArgumentsFlattenOf(SampleInterface::class) shouldBeEqualTo true
            it?.hasAllTypeArgumentsFlattenOf(SampleInterface::class, Int::class) shouldBeEqualTo false
            it?.hasAllTypeArgumentsFlattenOf(SampleClass::class, Int::class) shouldBeEqualTo false
            it?.hasAllTypeArgumentsFlattenOf(listOf(SampleInterface::class)) shouldBeEqualTo true
            it?.hasAllTypeArgumentsFlattenOf(listOf(SampleInterface::class, Int::class)) shouldBeEqualTo false
            it?.hasAllTypeArgumentsFlattenOf(listOf(SampleClass::class, Int::class)) shouldBeEqualTo false
            it?.hasTypeArgumentFlatten { type -> type.isInterface } shouldBeEqualTo true
            it?.hasTypeArgumentFlatten { type -> type.isExternalType } shouldBeEqualTo false
            it?.hasAllTypeArgumentsFlatten { type -> type.isInterface } shouldBeEqualTo true
            it?.hasAllTypeArgumentsFlatten { type -> type.isExternalType } shouldBeEqualTo false
        }
    }

    @Test
    fun `object-type-argument`() {
        // given
        val sut =
            getSnippetFile("object-type-argument")
                .properties()
                .first()
                .type
                ?.asGenericTypeDeclaration()

        // then
        assertSoftly(sut) {
            it?.typeArguments?.firstOrNull()?.declaration shouldBeInstanceOf KoObjectDeclaration::class
            it?.typeArguments?.firstOrNull()?.name shouldBeEqualTo "SampleObject"
            it?.typeArgumentsFlatten?.map { typeArgument -> typeArgument.name } shouldBeEqualTo listOf("SampleObject")

            it?.numTypeArguments shouldBeEqualTo 1
            it?.countTypeArguments { type -> type.isObject } shouldBeEqualTo 1
            it?.countTypeArguments { type -> type.isClass } shouldBeEqualTo 0
            it?.hasTypeArgumentWithName("SampleObject", "Int") shouldBeEqualTo true
            it?.hasTypeArgumentWithName("OtherClass", "Int") shouldBeEqualTo false
            it?.hasTypeArgumentWithName(listOf("SampleObject", "Int")) shouldBeEqualTo true
            it?.hasTypeArgumentWithName(listOf("OtherClass", "Int")) shouldBeEqualTo false
            it?.hasTypeArgumentsWithAllNames("SampleObject") shouldBeEqualTo true
            it?.hasTypeArgumentsWithAllNames("SampleObject", "Int") shouldBeEqualTo false
            it?.hasTypeArgumentsWithAllNames("OtherClass", "Int") shouldBeEqualTo false
            it?.hasTypeArgumentsWithAllNames(listOf("SampleObject")) shouldBeEqualTo true
            it?.hasTypeArgumentsWithAllNames(listOf("SampleObject", "Int")) shouldBeEqualTo false
            it?.hasTypeArgumentsWithAllNames(listOf("OtherClass", "Int")) shouldBeEqualTo false
            it?.hasTypeArgumentOf(SampleObject::class, Int::class) shouldBeEqualTo true
            it?.hasTypeArgumentOf(SampleClass::class, Int::class) shouldBeEqualTo false
            it?.hasTypeArgumentOf(listOf(SampleObject::class, Int::class)) shouldBeEqualTo true
            it?.hasTypeArgumentOf(listOf(SampleClass::class, Int::class)) shouldBeEqualTo false
            it?.hasAllTypeArgumentsOf(SampleObject::class) shouldBeEqualTo true
            it?.hasAllTypeArgumentsOf(SampleObject::class, Int::class) shouldBeEqualTo false
            it?.hasAllTypeArgumentsOf(SampleClass::class, Int::class) shouldBeEqualTo false
            it?.hasAllTypeArgumentsOf(listOf(SampleObject::class)) shouldBeEqualTo true
            it?.hasAllTypeArgumentsOf(listOf(SampleObject::class, Int::class)) shouldBeEqualTo false
            it?.hasAllTypeArgumentsOf(listOf(SampleClass::class, Int::class)) shouldBeEqualTo false
            it?.hasTypeArgument { type -> type.isObject } shouldBeEqualTo true
            it?.hasTypeArgument { type -> type.isExternalType } shouldBeEqualTo false
            it?.hasAllTypeArguments { type -> type.isObject } shouldBeEqualTo true
            it?.hasAllTypeArguments { type -> type.isExternalType } shouldBeEqualTo false

            it?.numTypeArgumentsFlatten shouldBeEqualTo 1
            it?.countTypeArgumentsFlatten { type -> type.isObject } shouldBeEqualTo 1
            it?.countTypeArgumentsFlatten { type -> type.isClass } shouldBeEqualTo 0
            it?.hasTypeArgumentFlattenWithName("SampleObject", "Int") shouldBeEqualTo true
            it?.hasTypeArgumentFlattenWithName("OtherClass", "Int") shouldBeEqualTo false
            it?.hasTypeArgumentFlattenWithName(listOf("SampleObject", "Int")) shouldBeEqualTo true
            it?.hasTypeArgumentFlattenWithName(listOf("OtherClass", "Int")) shouldBeEqualTo false
            it?.hasTypeArgumentsFlattenWithAllNames("SampleObject") shouldBeEqualTo true
            it?.hasTypeArgumentsFlattenWithAllNames("SampleObject", "Int") shouldBeEqualTo false
            it?.hasTypeArgumentsFlattenWithAllNames("OtherClass", "Int") shouldBeEqualTo false
            it?.hasTypeArgumentsFlattenWithAllNames(listOf("SampleObject")) shouldBeEqualTo true
            it?.hasTypeArgumentsFlattenWithAllNames(listOf("SampleObject", "Int")) shouldBeEqualTo false
            it?.hasTypeArgumentsFlattenWithAllNames(listOf("OtherClass", "Int")) shouldBeEqualTo false
            it?.hasTypeArgumentFlattenOf(SampleObject::class) shouldBeEqualTo true
            it?.hasTypeArgumentFlattenOf(SampleObject::class, Int::class) shouldBeEqualTo true
            it?.hasTypeArgumentFlattenOf(SampleClass::class, Int::class) shouldBeEqualTo false
            it?.hasTypeArgumentFlattenOf(listOf(SampleObject::class)) shouldBeEqualTo true
            it?.hasTypeArgumentFlattenOf(listOf(SampleObject::class, Int::class)) shouldBeEqualTo true
            it?.hasTypeArgumentFlattenOf(listOf(SampleClass::class, Int::class)) shouldBeEqualTo false
            it?.hasAllTypeArgumentsFlattenOf(SampleObject::class) shouldBeEqualTo true
            it?.hasAllTypeArgumentsFlattenOf(SampleObject::class, Int::class) shouldBeEqualTo false
            it?.hasAllTypeArgumentsFlattenOf(SampleClass::class, Int::class) shouldBeEqualTo false
            it?.hasAllTypeArgumentsFlattenOf(listOf(SampleObject::class)) shouldBeEqualTo true
            it?.hasAllTypeArgumentsFlattenOf(listOf(SampleObject::class, Int::class)) shouldBeEqualTo false
            it?.hasAllTypeArgumentsFlattenOf(listOf(SampleClass::class, Int::class)) shouldBeEqualTo false
            it?.hasTypeArgumentFlatten { type -> type.isObject } shouldBeEqualTo true
            it?.hasTypeArgumentFlatten { type -> type.isExternalType } shouldBeEqualTo false
            it?.hasAllTypeArgumentsFlatten { type -> type.isObject } shouldBeEqualTo true
            it?.hasAllTypeArgumentsFlatten { type -> type.isExternalType } shouldBeEqualTo false
        }
    }

    @Test
    fun `generic-type-argument`() {
        // given
        val sut =
            getSnippetFile("generic-type-argument")
                .properties()
                .first()
                .type
                ?.asGenericTypeDeclaration()

        // then
        assertSoftly(sut) {
            it?.typeArguments?.firstOrNull()?.declaration shouldBeInstanceOf KoGenericTypeDeclaration::class
            it?.typeArguments?.firstOrNull()?.name shouldBeEqualTo "Set<String>"
            it?.typeArgumentsFlatten?.map { typeArgument -> typeArgument.name } shouldBeEqualTo listOf("Set", "String")

            it?.numTypeArguments shouldBeEqualTo 1
            it?.countTypeArguments { type -> type.isGenericType } shouldBeEqualTo 1
            it?.countTypeArguments { type -> type.isClass } shouldBeEqualTo 0
            it?.hasTypeArgumentWithName("Set<String>", "Int") shouldBeEqualTo true
            it?.hasTypeArgumentWithName("OtherClass", "Int") shouldBeEqualTo false
            it?.hasTypeArgumentWithName(listOf("Set<String>", "Int")) shouldBeEqualTo true
            it?.hasTypeArgumentWithName(listOf("OtherClass", "Int")) shouldBeEqualTo false
            it?.hasTypeArgumentsWithAllNames("Set<String>") shouldBeEqualTo true
            it?.hasTypeArgumentsWithAllNames("Set<String>", "Int") shouldBeEqualTo false
            it?.hasTypeArgumentsWithAllNames("OtherClass", "Int") shouldBeEqualTo false
            it?.hasTypeArgumentsWithAllNames(listOf("Set<String>")) shouldBeEqualTo true
            it?.hasTypeArgumentsWithAllNames(listOf("Set<String>", "Int")) shouldBeEqualTo false
            it?.hasTypeArgumentsWithAllNames(listOf("OtherClass", "Int")) shouldBeEqualTo false
            it?.hasTypeArgumentOf(Set::class, Int::class) shouldBeEqualTo false
            it?.hasTypeArgumentOf(SampleClass::class, Int::class) shouldBeEqualTo false
            it?.hasTypeArgumentOf(listOf(Set::class, Int::class)) shouldBeEqualTo false
            it?.hasTypeArgumentOf(listOf(SampleClass::class, Int::class)) shouldBeEqualTo false
            it?.hasAllTypeArgumentsOf(Set::class) shouldBeEqualTo false
            it?.hasAllTypeArgumentsOf(Set::class, Int::class) shouldBeEqualTo false
            it?.hasAllTypeArgumentsOf(SampleClass::class, Int::class) shouldBeEqualTo false
            it?.hasAllTypeArgumentsOf(listOf(Set::class)) shouldBeEqualTo false
            it?.hasAllTypeArgumentsOf(listOf(Set::class, Int::class)) shouldBeEqualTo false
            it?.hasAllTypeArgumentsOf(listOf(SampleClass::class, Int::class)) shouldBeEqualTo false
            it?.hasTypeArgument { type -> type.isGenericType } shouldBeEqualTo true
            it?.hasTypeArgument { type -> type.isExternalType } shouldBeEqualTo false
            it?.hasAllTypeArguments { type -> type.isGenericType } shouldBeEqualTo true
            it?.hasAllTypeArguments { type -> type.isExternalType } shouldBeEqualTo false

            it?.numTypeArgumentsFlatten shouldBeEqualTo 2
            it?.countTypeArgumentsFlatten { type -> type.isKotlinType } shouldBeEqualTo 2
            it?.countTypeArgumentsFlatten { type -> type.isKotlinCollectionType } shouldBeEqualTo 1
            it?.hasTypeArgumentFlattenWithName("String", "Int") shouldBeEqualTo true
            it?.hasTypeArgumentFlattenWithName("OtherClass", "Int") shouldBeEqualTo false
            it?.hasTypeArgumentFlattenWithName(listOf("String", "Int")) shouldBeEqualTo true
            it?.hasTypeArgumentFlattenWithName(listOf("OtherClass", "Int")) shouldBeEqualTo false
            it?.hasTypeArgumentsFlattenWithAllNames("String") shouldBeEqualTo true
            it?.hasTypeArgumentsFlattenWithAllNames("String", "Set") shouldBeEqualTo true
            it?.hasTypeArgumentsFlattenWithAllNames("String", "Int") shouldBeEqualTo false
            it?.hasTypeArgumentsFlattenWithAllNames(listOf("String")) shouldBeEqualTo true
            it?.hasTypeArgumentsFlattenWithAllNames(listOf("String", "Set")) shouldBeEqualTo true
            it?.hasTypeArgumentsFlattenWithAllNames(listOf("String", "Int")) shouldBeEqualTo false
            it?.hasTypeArgumentFlattenOf(String::class) shouldBeEqualTo true
            it?.hasTypeArgumentFlattenOf(String::class, Set::class) shouldBeEqualTo true
            it?.hasTypeArgumentFlattenOf(String::class, Int::class) shouldBeEqualTo true
            it?.hasTypeArgumentFlattenOf(SampleClass::class, Int::class) shouldBeEqualTo false
            it?.hasTypeArgumentFlattenOf(listOf(String::class)) shouldBeEqualTo true
            it?.hasTypeArgumentFlattenOf(listOf(String::class, Set::class)) shouldBeEqualTo true
            it?.hasTypeArgumentFlattenOf(listOf(String::class, Int::class)) shouldBeEqualTo true
            it?.hasTypeArgumentFlattenOf(listOf(SampleClass::class, Int::class)) shouldBeEqualTo false
            it?.hasAllTypeArgumentsFlattenOf(String::class) shouldBeEqualTo true
            it?.hasAllTypeArgumentsFlattenOf(String::class, Set::class) shouldBeEqualTo true
            it?.hasAllTypeArgumentsFlattenOf(String::class, Int::class) shouldBeEqualTo false
            it?.hasAllTypeArgumentsFlattenOf(SampleClass::class, Int::class) shouldBeEqualTo false
            it?.hasAllTypeArgumentsFlattenOf(listOf(String::class)) shouldBeEqualTo true
            it?.hasAllTypeArgumentsFlattenOf(listOf(String::class, Set::class)) shouldBeEqualTo true
            it?.hasAllTypeArgumentsFlattenOf(listOf(String::class, Int::class)) shouldBeEqualTo false
            it?.hasAllTypeArgumentsFlattenOf(listOf(SampleClass::class, Int::class)) shouldBeEqualTo false
            it?.hasTypeArgumentFlatten { type -> type.isKotlinType } shouldBeEqualTo true
            it?.hasTypeArgumentFlatten { type -> type.isExternalType } shouldBeEqualTo false
            it?.hasAllTypeArgumentsFlatten { type -> type.isKotlinType } shouldBeEqualTo true
            it?.hasAllTypeArgumentsFlatten { type -> type.isExternalType } shouldBeEqualTo false
        }
    }

    @Test
    fun `nested-generic-type-argument`() {
        // given
        val sut =
            getSnippetFile("nested-generic-type-argument")
                .properties()
                .first()
                .type
                ?.asGenericTypeDeclaration()

        // then
        assertSoftly(sut) {
            it
                ?.typeArguments
                ?.firstOrNull()
                ?.asGenericTypeDeclaration()
                ?.typeArguments
                ?.firstOrNull()
                ?.declaration
                ?.shouldBeInstanceOf(KoKotlinTypeDeclaration::class)
            it
                ?.typeArguments
                ?.firstOrNull()
                ?.asGenericTypeDeclaration()
                ?.typeArguments
                ?.firstOrNull()
                ?.name
                ?.shouldBeEqualTo("String")

            it
                ?.typeArguments
                ?.firstOrNull()
                ?.asGenericTypeDeclaration()
                ?.typeArguments
                ?.map { typeArgument -> typeArgument.name }
                ?.shouldBeEqualTo(listOf("String"))
        }
    }

    @Test
    fun `function-type-argument`() {
        // given
        val sut =
            getSnippetFile("function-type-argument")
                .properties()
                .first()
                .type
                ?.asGenericTypeDeclaration()

        // then
        assertSoftly(sut) {
            it?.typeArguments?.firstOrNull()?.declaration shouldBeInstanceOf KoFunctionTypeDeclaration::class
            it?.typeArguments?.firstOrNull()?.name shouldBeEqualTo "() -> Unit"
            it?.typeArgumentsFlatten?.map { typeArgument -> typeArgument.name } shouldBeEqualTo listOf("() -> Unit")

            it?.numTypeArguments shouldBeEqualTo 1
            it?.countTypeArguments { type -> type.isFunctionType } shouldBeEqualTo 1
            it?.countTypeArguments { type -> type.isExternalType } shouldBeEqualTo 0
            it?.hasTypeArgumentWithName("() -> Unit", "Int") shouldBeEqualTo true
            it?.hasTypeArgumentWithName("OtherClass", "Int") shouldBeEqualTo false
            it?.hasTypeArgumentWithName(listOf("() -> Unit", "Int")) shouldBeEqualTo true
            it?.hasTypeArgumentWithName(listOf("OtherClass", "Int")) shouldBeEqualTo false
            it?.hasTypeArgumentsWithAllNames("() -> Unit") shouldBeEqualTo true
            it?.hasTypeArgumentsWithAllNames("() -> Unit", "Int") shouldBeEqualTo false
            it?.hasTypeArgumentsWithAllNames("OtherClass", "Int") shouldBeEqualTo false
            it?.hasTypeArgumentsWithAllNames(listOf("() -> Unit")) shouldBeEqualTo true
            it?.hasTypeArgumentsWithAllNames(listOf("() -> Unit", "Int")) shouldBeEqualTo false
            it?.hasTypeArgumentsWithAllNames(listOf("OtherClass", "Int")) shouldBeEqualTo false
            it?.hasTypeArgumentOf(SampleInterface::class, Int::class) shouldBeEqualTo false
            it?.hasTypeArgumentOf(listOf(SampleInterface::class, Int::class)) shouldBeEqualTo false
            it?.hasAllTypeArgumentsOf(SampleInterface::class, Int::class) shouldBeEqualTo false
            it?.hasAllTypeArgumentsOf(listOf(SampleInterface::class, Int::class)) shouldBeEqualTo false
            it?.hasTypeArgument { type -> type.isFunctionType } shouldBeEqualTo true
            it?.hasTypeArgument { type -> type.isExternalType } shouldBeEqualTo false
            it?.hasAllTypeArguments { type -> type.isFunctionType } shouldBeEqualTo true
            it?.hasAllTypeArguments { type -> type.isExternalType } shouldBeEqualTo false

            it?.numTypeArgumentsFlatten shouldBeEqualTo 1
            it?.countTypeArgumentsFlatten { type -> type.isFunctionType } shouldBeEqualTo 1
            it?.countTypeArgumentsFlatten { type -> type.isExternalType } shouldBeEqualTo 0
            it?.hasTypeArgumentFlattenWithName("() -> Unit", "Int") shouldBeEqualTo true
            it?.hasTypeArgumentFlattenWithName("OtherClass", "Int") shouldBeEqualTo false
            it?.hasTypeArgumentFlattenWithName(listOf("() -> Unit", "Int")) shouldBeEqualTo true
            it?.hasTypeArgumentFlattenWithName(listOf("OtherClass", "Int")) shouldBeEqualTo false
            it?.hasTypeArgumentsFlattenWithAllNames("() -> Unit") shouldBeEqualTo true
            it?.hasTypeArgumentsFlattenWithAllNames("() -> Unit", "Int") shouldBeEqualTo false
            it?.hasTypeArgumentsFlattenWithAllNames("OtherClass", "Int") shouldBeEqualTo false
            it?.hasTypeArgumentsFlattenWithAllNames(listOf("() -> Unit")) shouldBeEqualTo true
            it?.hasTypeArgumentsFlattenWithAllNames(listOf("() -> Unit", "Int")) shouldBeEqualTo false
            it?.hasTypeArgumentsFlattenWithAllNames(listOf("OtherClass", "Int")) shouldBeEqualTo false
            it?.hasTypeArgumentFlattenOf(SampleInterface::class, Int::class) shouldBeEqualTo false
            it?.hasTypeArgumentFlattenOf(listOf(SampleInterface::class, Int::class)) shouldBeEqualTo false
            it?.hasAllTypeArgumentsFlattenOf(SampleInterface::class, Int::class) shouldBeEqualTo false
            it?.hasAllTypeArgumentsFlattenOf(listOf(SampleInterface::class, Int::class)) shouldBeEqualTo false
            it?.hasTypeArgumentFlatten { type -> type.isFunctionType } shouldBeEqualTo true
            it?.hasTypeArgumentFlatten { type -> type.isExternalType } shouldBeEqualTo false
            it?.hasAllTypeArgumentsFlatten { type -> type.isFunctionType } shouldBeEqualTo true
            it?.hasAllTypeArgumentsFlatten { type -> type.isExternalType } shouldBeEqualTo false
        }
    }

    @Test
    fun `import-alias-type-argument`() {
        // given
        val sut =
            getSnippetFile("import-alias-type-argument")
                .properties()
                .first()
                .type
                ?.asGenericTypeDeclaration()

        // then
        assertSoftly(sut) {
            it?.typeArguments?.firstOrNull()?.declaration shouldBeInstanceOf KoImportAliasDeclaration::class
            it?.typeArguments?.firstOrNull()?.name shouldBeEqualTo "ImportAlias"
            it?.typeArgumentsFlatten?.map { typeArgument -> typeArgument.name } shouldBeEqualTo listOf("ImportAlias")

            it?.numTypeArguments shouldBeEqualTo 1
            it?.countTypeArguments { type -> type.isImportAlias } shouldBeEqualTo 1
            it?.countTypeArguments { type -> type.isExternalType } shouldBeEqualTo 0
            it?.hasTypeArgumentWithName("ImportAlias", "Int") shouldBeEqualTo true
            it?.hasTypeArgumentWithName("OtherClass", "Int") shouldBeEqualTo false
            it?.hasTypeArgumentWithName(listOf("ImportAlias", "Int")) shouldBeEqualTo true
            it?.hasTypeArgumentWithName(listOf("OtherClass", "Int")) shouldBeEqualTo false
            it?.hasTypeArgumentsWithAllNames("ImportAlias") shouldBeEqualTo true
            it?.hasTypeArgumentsWithAllNames("ImportAlias", "Int") shouldBeEqualTo false
            it?.hasTypeArgumentsWithAllNames("OtherClass", "Int") shouldBeEqualTo false
            it?.hasTypeArgumentsWithAllNames(listOf("ImportAlias")) shouldBeEqualTo true
            it?.hasTypeArgumentsWithAllNames(listOf("ImportAlias", "Int")) shouldBeEqualTo false
            it?.hasTypeArgumentsWithAllNames(listOf("OtherClass", "Int")) shouldBeEqualTo false
            it?.hasTypeArgumentOf(SampleInterface::class, Int::class) shouldBeEqualTo false
            it?.hasTypeArgumentOf(listOf(SampleInterface::class, Int::class)) shouldBeEqualTo false
            it?.hasAllTypeArgumentsOf(SampleInterface::class, Int::class) shouldBeEqualTo false
            it?.hasAllTypeArgumentsOf(listOf(SampleInterface::class, Int::class)) shouldBeEqualTo false
            it?.hasTypeArgument { type -> type.isImportAlias } shouldBeEqualTo true
            it?.hasTypeArgument { type -> type.isExternalType } shouldBeEqualTo false
            it?.hasAllTypeArguments { type -> type.isImportAlias } shouldBeEqualTo true
            it?.hasAllTypeArguments { type -> type.isExternalType } shouldBeEqualTo false

            it?.numTypeArgumentsFlatten shouldBeEqualTo 1
            it?.countTypeArgumentsFlatten { type -> type.isImportAlias } shouldBeEqualTo 1
            it?.countTypeArgumentsFlatten { type -> type.isExternalType } shouldBeEqualTo 0
            it?.hasTypeArgumentFlattenWithName("ImportAlias", "Int") shouldBeEqualTo true
            it?.hasTypeArgumentFlattenWithName("OtherClass", "Int") shouldBeEqualTo false
            it?.hasTypeArgumentFlattenWithName(listOf("ImportAlias", "Int")) shouldBeEqualTo true
            it?.hasTypeArgumentFlattenWithName(listOf("OtherClass", "Int")) shouldBeEqualTo false
            it?.hasTypeArgumentsFlattenWithAllNames("ImportAlias") shouldBeEqualTo true
            it?.hasTypeArgumentsFlattenWithAllNames("ImportAlias", "Int") shouldBeEqualTo false
            it?.hasTypeArgumentsFlattenWithAllNames("OtherClass", "Int") shouldBeEqualTo false
            it?.hasTypeArgumentsFlattenWithAllNames(listOf("ImportAlias")) shouldBeEqualTo true
            it?.hasTypeArgumentsFlattenWithAllNames(listOf("ImportAlias", "Int")) shouldBeEqualTo false
            it?.hasTypeArgumentsFlattenWithAllNames(listOf("OtherClass", "Int")) shouldBeEqualTo false
            it?.hasTypeArgumentFlattenOf(SampleInterface::class, Int::class) shouldBeEqualTo false
            it?.hasTypeArgumentFlattenOf(listOf(SampleInterface::class, Int::class)) shouldBeEqualTo false
            it?.hasAllTypeArgumentsFlattenOf(SampleInterface::class, Int::class) shouldBeEqualTo false
            it?.hasAllTypeArgumentsFlattenOf(listOf(SampleInterface::class, Int::class)) shouldBeEqualTo false
            it?.hasTypeArgumentFlatten { type -> type.isImportAlias } shouldBeEqualTo true
            it?.hasTypeArgumentFlatten { type -> type.isExternalType } shouldBeEqualTo false
            it?.hasAllTypeArgumentsFlatten { type -> type.isImportAlias } shouldBeEqualTo true
            it?.hasAllTypeArgumentsFlatten { type -> type.isExternalType } shouldBeEqualTo false
        }
    }

    @Test
    fun `typealias-type-argument`() {
        // given
        val sut =
            getSnippetFile("typealias-type-argument")
                .properties()
                .first()
                .type
                ?.asGenericTypeDeclaration()

        // then
        assertSoftly(sut) {
            it?.typeArguments?.firstOrNull()?.declaration shouldBeInstanceOf KoTypeAliasDeclaration::class
            it?.typeArguments?.firstOrNull()?.name shouldBeEqualTo "SampleTypeAlias"
            it?.typeArgumentsFlatten?.map { typeArgument -> typeArgument.name } shouldBeEqualTo listOf("SampleTypeAlias")

            it?.numTypeArguments shouldBeEqualTo 1
            it?.countTypeArguments { type -> type.isTypeAlias } shouldBeEqualTo 1
            it?.countTypeArguments { type -> type.isExternalType } shouldBeEqualTo 0
            it?.hasTypeArgumentWithName("SampleTypeAlias", "Int") shouldBeEqualTo true
            it?.hasTypeArgumentWithName("OtherClass", "Int") shouldBeEqualTo false
            it?.hasTypeArgumentWithName(listOf("SampleTypeAlias", "Int")) shouldBeEqualTo true
            it?.hasTypeArgumentWithName(listOf("OtherClass", "Int")) shouldBeEqualTo false
            it?.hasTypeArgumentsWithAllNames("SampleTypeAlias") shouldBeEqualTo true
            it?.hasTypeArgumentsWithAllNames("SampleTypeAlias", "Int") shouldBeEqualTo false
            it?.hasTypeArgumentsWithAllNames("OtherClass", "Int") shouldBeEqualTo false
            it?.hasTypeArgumentsWithAllNames(listOf("SampleTypeAlias")) shouldBeEqualTo true
            it?.hasTypeArgumentsWithAllNames(listOf("SampleTypeAlias", "Int")) shouldBeEqualTo false
            it?.hasTypeArgumentsWithAllNames(listOf("OtherClass", "Int")) shouldBeEqualTo false
            it?.hasTypeArgumentOf(SampleInterface::class, Int::class) shouldBeEqualTo false
            it?.hasTypeArgumentOf(listOf(SampleInterface::class, Int::class)) shouldBeEqualTo false
            it?.hasAllTypeArgumentsOf(SampleInterface::class, Int::class) shouldBeEqualTo false
            it?.hasAllTypeArgumentsOf(listOf(SampleInterface::class, Int::class)) shouldBeEqualTo false
            it?.hasTypeArgument { type -> type.isTypeAlias } shouldBeEqualTo true
            it?.hasTypeArgument { type -> type.isExternalType } shouldBeEqualTo false
            it?.hasAllTypeArguments { type -> type.isTypeAlias } shouldBeEqualTo true
            it?.hasAllTypeArguments { type -> type.isExternalType } shouldBeEqualTo false

            it?.numTypeArgumentsFlatten shouldBeEqualTo 1
            it?.countTypeArgumentsFlatten { type -> type.isTypeAlias } shouldBeEqualTo 1
            it?.countTypeArgumentsFlatten { type -> type.isExternalType } shouldBeEqualTo 0
            it?.hasTypeArgumentFlattenWithName("SampleTypeAlias", "Int") shouldBeEqualTo true
            it?.hasTypeArgumentFlattenWithName("OtherClass", "Int") shouldBeEqualTo false
            it?.hasTypeArgumentFlattenWithName(listOf("SampleTypeAlias", "Int")) shouldBeEqualTo true
            it?.hasTypeArgumentFlattenWithName(listOf("OtherClass", "Int")) shouldBeEqualTo false
            it?.hasTypeArgumentsFlattenWithAllNames("SampleTypeAlias") shouldBeEqualTo true
            it?.hasTypeArgumentsFlattenWithAllNames("SampleTypeAlias", "Int") shouldBeEqualTo false
            it?.hasTypeArgumentsFlattenWithAllNames("OtherClass", "Int") shouldBeEqualTo false
            it?.hasTypeArgumentsFlattenWithAllNames(listOf("SampleTypeAlias")) shouldBeEqualTo true
            it?.hasTypeArgumentsFlattenWithAllNames(listOf("SampleTypeAlias", "Int")) shouldBeEqualTo false
            it?.hasTypeArgumentsFlattenWithAllNames(listOf("OtherClass", "Int")) shouldBeEqualTo false
            it?.hasTypeArgumentFlattenOf(SampleInterface::class, Int::class) shouldBeEqualTo false
            it?.hasTypeArgumentFlattenOf(listOf(SampleInterface::class, Int::class)) shouldBeEqualTo false
            it?.hasAllTypeArgumentsFlattenOf(SampleInterface::class, Int::class) shouldBeEqualTo false
            it?.hasAllTypeArgumentsFlattenOf(listOf(SampleInterface::class, Int::class)) shouldBeEqualTo false
            it?.hasTypeArgumentFlatten { type -> type.isTypeAlias } shouldBeEqualTo true
            it?.hasTypeArgumentFlatten { type -> type.isExternalType } shouldBeEqualTo false
            it?.hasAllTypeArgumentsFlatten { type -> type.isTypeAlias } shouldBeEqualTo true
            it?.hasAllTypeArgumentsFlatten { type -> type.isExternalType } shouldBeEqualTo false
        }
    }

    @Test
    fun `external-type-argument`() {
        // given
        val sut =
            getSnippetFile("external-type-argument")
                .properties()
                .first()
                .type
                ?.asGenericTypeDeclaration()

        // then
        assertSoftly(sut) {
            it?.typeArguments?.firstOrNull()?.declaration shouldBeInstanceOf KoExternalDeclaration::class
            it?.typeArguments?.firstOrNull()?.name shouldBeEqualTo "SampleExternalClass"
            it?.typeArgumentsFlatten?.map { typeArgument -> typeArgument.name } shouldBeEqualTo listOf("SampleExternalClass")

            it?.numTypeArguments shouldBeEqualTo 1
            it?.countTypeArguments { type -> type.isExternalType } shouldBeEqualTo 1
            it?.countTypeArguments { type -> type.isInterface } shouldBeEqualTo 0
            it?.hasTypeArgumentWithName("SampleExternalClass", "Int") shouldBeEqualTo true
            it?.hasTypeArgumentWithName("OtherClass", "Int") shouldBeEqualTo false
            it?.hasTypeArgumentWithName(listOf("SampleExternalClass", "Int")) shouldBeEqualTo true
            it?.hasTypeArgumentWithName(listOf("OtherClass", "Int")) shouldBeEqualTo false
            it?.hasTypeArgumentsWithAllNames("SampleExternalClass") shouldBeEqualTo true
            it?.hasTypeArgumentsWithAllNames("SampleExternalClass", "Int") shouldBeEqualTo false
            it?.hasTypeArgumentsWithAllNames("OtherClass", "Int") shouldBeEqualTo false
            it?.hasTypeArgumentsWithAllNames(listOf("SampleExternalClass")) shouldBeEqualTo true
            it?.hasTypeArgumentsWithAllNames(listOf("SampleExternalClass", "Int")) shouldBeEqualTo false
            it?.hasTypeArgumentsWithAllNames(listOf("OtherClass", "Int")) shouldBeEqualTo false
            it?.hasTypeArgumentOf(SampleExternalClass::class, Int::class) shouldBeEqualTo true
            it?.hasTypeArgumentOf(SampleInterface::class, Int::class) shouldBeEqualTo false
            it?.hasTypeArgumentOf(listOf(SampleExternalClass::class, Int::class)) shouldBeEqualTo true
            it?.hasTypeArgumentOf(listOf(SampleInterface::class, Int::class)) shouldBeEqualTo false
            it?.hasAllTypeArgumentsOf(SampleExternalClass::class) shouldBeEqualTo true
            it?.hasAllTypeArgumentsOf(SampleExternalClass::class, Int::class) shouldBeEqualTo false
            it?.hasAllTypeArgumentsOf(SampleInterface::class, Int::class) shouldBeEqualTo false
            it?.hasAllTypeArgumentsOf(listOf(SampleExternalClass::class)) shouldBeEqualTo true
            it?.hasAllTypeArgumentsOf(listOf(SampleExternalClass::class, Int::class)) shouldBeEqualTo false
            it?.hasAllTypeArgumentsOf(listOf(SampleInterface::class, Int::class)) shouldBeEqualTo false
            it?.hasTypeArgument { type -> type.isExternalType } shouldBeEqualTo true
            it?.hasTypeArgument { type -> type.isInterface } shouldBeEqualTo false
            it?.hasAllTypeArguments { type -> type.isExternalType } shouldBeEqualTo true
            it?.hasAllTypeArguments { type -> type.isInterface } shouldBeEqualTo false

            it?.numTypeArgumentsFlatten shouldBeEqualTo 1
            it?.countTypeArgumentsFlatten { type -> type.isExternalType } shouldBeEqualTo 1
            it?.countTypeArgumentsFlatten { type -> type.isInterface } shouldBeEqualTo 0
            it?.hasTypeArgumentFlattenWithName("SampleExternalClass", "Int") shouldBeEqualTo true
            it?.hasTypeArgumentFlattenWithName("OtherClass", "Int") shouldBeEqualTo false
            it?.hasTypeArgumentFlattenWithName(listOf("SampleExternalClass", "Int")) shouldBeEqualTo true
            it?.hasTypeArgumentFlattenWithName(listOf("OtherClass", "Int")) shouldBeEqualTo false
            it?.hasTypeArgumentsFlattenWithAllNames("SampleExternalClass") shouldBeEqualTo true
            it?.hasTypeArgumentsFlattenWithAllNames("SampleExternalClass", "Int") shouldBeEqualTo false
            it?.hasTypeArgumentsFlattenWithAllNames("OtherClass", "Int") shouldBeEqualTo false
            it?.hasTypeArgumentsFlattenWithAllNames(listOf("SampleExternalClass")) shouldBeEqualTo true
            it?.hasTypeArgumentsFlattenWithAllNames(listOf("SampleExternalClass", "Int")) shouldBeEqualTo false
            it?.hasTypeArgumentsFlattenWithAllNames(listOf("OtherClass", "Int")) shouldBeEqualTo false
            it?.hasTypeArgumentFlattenOf(SampleExternalClass::class) shouldBeEqualTo true
            it?.hasTypeArgumentFlattenOf(SampleExternalClass::class, Int::class) shouldBeEqualTo true
            it?.hasTypeArgumentFlattenOf(SampleInterface::class, Int::class) shouldBeEqualTo false
            it?.hasTypeArgumentFlattenOf(listOf(SampleExternalClass::class)) shouldBeEqualTo true
            it?.hasTypeArgumentFlattenOf(listOf(SampleExternalClass::class, Int::class)) shouldBeEqualTo true
            it?.hasTypeArgumentFlattenOf(listOf(SampleInterface::class, Int::class)) shouldBeEqualTo false
            it?.hasAllTypeArgumentsFlattenOf(SampleExternalClass::class) shouldBeEqualTo true
            it?.hasAllTypeArgumentsFlattenOf(SampleExternalClass::class, Int::class) shouldBeEqualTo false
            it?.hasAllTypeArgumentsFlattenOf(SampleInterface::class, Int::class) shouldBeEqualTo false
            it?.hasAllTypeArgumentsFlattenOf(listOf(SampleExternalClass::class)) shouldBeEqualTo true
            it?.hasAllTypeArgumentsFlattenOf(listOf(SampleExternalClass::class, Int::class)) shouldBeEqualTo false
            it?.hasAllTypeArgumentsFlattenOf(listOf(SampleInterface::class, Int::class)) shouldBeEqualTo false
            it?.hasTypeArgumentFlatten { type -> type.isExternalType } shouldBeEqualTo true
            it?.hasTypeArgumentFlatten { type -> type.isInterface } shouldBeEqualTo false
            it?.hasAllTypeArgumentsFlatten { type -> type.isExternalType } shouldBeEqualTo true
            it?.hasAllTypeArgumentsFlatten { type -> type.isInterface } shouldBeEqualTo false
        }
    }

    @Test
    fun `few-type-arguments`() {
        // given
        val sut =
            getSnippetFile("few-type-arguments")
                .properties()
                .first()
                .type
                ?.asGenericTypeDeclaration()

        // then
        assertSoftly(sut) {
            it
                ?.typeArguments
                ?.map { typeArgument -> typeArgument.name }
                .shouldBeEqualTo(listOf("SampleClass", "List<String>"))

            it?.numTypeArguments shouldBeEqualTo 2

            it
                ?.typeArgumentsFlatten
                ?.map { typeArgument -> typeArgument.name }
                .shouldBeEqualTo(listOf("SampleClass", "List", "String"))

            it?.numTypeArgumentsFlatten shouldBeEqualTo 3
        }
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope(
            "core/declaration/type/kogenerictype/snippet/forkogenerictypedeclarationprovider/",
            fileName,
        )
}
