package com.lemonappdev.konsist.core.declaration.type.kotype

import com.lemonappdev.konsist.TestSnippetProvider
import com.lemonappdev.konsist.api.declaration.KoClassDeclaration
import com.lemonappdev.konsist.api.declaration.KoExternalDeclaration
import com.lemonappdev.konsist.api.declaration.KoImportAliasDeclaration
import com.lemonappdev.konsist.api.declaration.KoInterfaceDeclaration
import com.lemonappdev.konsist.api.declaration.KoObjectDeclaration
import com.lemonappdev.konsist.api.declaration.KoTypeAliasDeclaration
import com.lemonappdev.konsist.api.declaration.type.KoFunctionTypeDeclaration
import com.lemonappdev.konsist.api.declaration.type.KoKotlinTypeDeclaration
import com.lemonappdev.konsist.externalsample.SampleExternalClass
import com.lemonappdev.konsist.testdata.SampleClass
import com.lemonappdev.konsist.testdata.SampleInterface
import com.lemonappdev.konsist.testdata.SampleObject
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldBeInstanceOf
import org.junit.jupiter.api.Test

@Suppress("detekt.LongMethod")
class KoTypeDeclarationForKoTypeArgumentProviderTest {
    @Test
    fun `type-without-type-arguments`() {
        // given
        val sut =
            getSnippetFile("type-without-type-arguments")
                .properties()
                .first()
                .type

        // then
        assertSoftly(sut) {
            it?.typeArguments shouldBeEqualTo null
            it?.numTypeArguments shouldBeEqualTo 0
            it?.countTypeArguments { type -> type.sourceDeclaration.isClass } shouldBeEqualTo 0
            it?.hasTypeArgumentWithName("String", "Int") shouldBeEqualTo false
            it?.hasTypeArgumentWithName(listOf("String", "Int")) shouldBeEqualTo false
            it?.hasTypeArgumentsWithAllNames("String", "Int") shouldBeEqualTo false
            it?.hasTypeArgumentsWithAllNames(listOf("String", "Int")) shouldBeEqualTo false
            it?.hasTypeArgumentOf(String::class, Int::class) shouldBeEqualTo false
            it?.hasTypeArgumentOf(listOf(String::class, Int::class)) shouldBeEqualTo false
            it?.hasAllTypeArgumentsOf(String::class, Int::class) shouldBeEqualTo false
            it?.hasAllTypeArgumentsOf(listOf(String::class, Int::class)) shouldBeEqualTo false
            it?.hasAllTypeArgumentsOf(listOf(SampleClass::class, Int::class)) shouldBeEqualTo false
            it?.hasTypeArgument { type -> type.sourceDeclaration.isExternalType } shouldBeEqualTo false
            it?.hasAllTypeArguments { type -> type.sourceDeclaration.isExternalType } shouldBeEqualTo false
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

        // then
        assertSoftly(sut) {
            it
                ?.typeArguments
                ?.firstOrNull()
                ?.sourceDeclaration shouldBeInstanceOf KoKotlinTypeDeclaration::class

            it?.typeArguments?.firstOrNull()?.name shouldBeEqualTo "String"
            it?.numTypeArguments shouldBeEqualTo 1
            it?.countTypeArguments { type -> type.sourceDeclaration.isKotlinType } shouldBeEqualTo 1
            it?.countTypeArguments { type -> type.sourceDeclaration.isClass } shouldBeEqualTo 0
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
    fun `class-type-argument`() {
        // given
        val sut =
            getSnippetFile("class-type-argument")
                .properties()
                .first()
                .type

        // then
        assertSoftly(sut) {
            it
                ?.typeArguments
                ?.firstOrNull()
                ?.sourceDeclaration shouldBeInstanceOf KoClassDeclaration::class

            it?.typeArguments?.firstOrNull()?.name shouldBeEqualTo "SampleClass"
            it?.numTypeArguments shouldBeEqualTo 1
            it?.countTypeArguments { type -> type.sourceDeclaration.isClass } shouldBeEqualTo 1
            it?.countTypeArguments { type -> type.sourceDeclaration.isExternalType } shouldBeEqualTo 0
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
            it?.hasTypeArgument { type -> type.sourceDeclaration.isClass } shouldBeEqualTo true
            it?.hasTypeArgument { type -> type.sourceDeclaration.isExternalType } shouldBeEqualTo false
            it?.hasAllTypeArguments { type -> type.sourceDeclaration.isClass } shouldBeEqualTo true
            it?.hasAllTypeArguments { type -> type.sourceDeclaration.isExternalType } shouldBeEqualTo false
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

        // then
        assertSoftly(sut) {
            it
                ?.typeArguments
                ?.firstOrNull()
                ?.sourceDeclaration shouldBeInstanceOf KoInterfaceDeclaration::class

            it?.typeArguments?.firstOrNull()?.name shouldBeEqualTo "SampleInterface"
            it?.numTypeArguments shouldBeEqualTo 1
            it?.countTypeArguments { type -> type.sourceDeclaration.isInterface } shouldBeEqualTo 1
            it?.countTypeArguments { type -> type.sourceDeclaration.isClass } shouldBeEqualTo 0
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
            it?.hasTypeArgument { type -> type.sourceDeclaration.isInterface } shouldBeEqualTo true
            it?.hasTypeArgument { type -> type.sourceDeclaration.isExternalType } shouldBeEqualTo false
            it?.hasAllTypeArguments { type -> type.sourceDeclaration.isInterface } shouldBeEqualTo true
            it?.hasAllTypeArguments { type -> type.sourceDeclaration.isExternalType } shouldBeEqualTo false
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

        // then
        assertSoftly(sut) {
            it
                ?.typeArguments
                ?.firstOrNull()
                ?.sourceDeclaration shouldBeInstanceOf KoObjectDeclaration::class

            it?.typeArguments?.firstOrNull()?.name shouldBeEqualTo "SampleObject"
            it?.numTypeArguments shouldBeEqualTo 1
            it?.countTypeArguments { type -> type.sourceDeclaration.isObject } shouldBeEqualTo 1
            it?.countTypeArguments { type -> type.sourceDeclaration.isClass } shouldBeEqualTo 0
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
            it?.hasTypeArgument { type -> type.sourceDeclaration.isObject } shouldBeEqualTo true
            it?.hasTypeArgument { type -> type.sourceDeclaration.isExternalType } shouldBeEqualTo false
            it?.hasAllTypeArguments { type -> type.sourceDeclaration.isObject } shouldBeEqualTo true
            it?.hasAllTypeArguments { type -> type.sourceDeclaration.isExternalType } shouldBeEqualTo false
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

        // then
        assertSoftly(sut) {
            it
                ?.typeArguments
                ?.firstOrNull()
                ?.sourceDeclaration shouldBeInstanceOf KoKotlinTypeDeclaration::class

            it?.typeArguments?.firstOrNull()?.name shouldBeEqualTo "Set<String>"
            it?.numTypeArguments shouldBeEqualTo 1
            it?.countTypeArguments { type -> type.sourceDeclaration.isKotlinType } shouldBeEqualTo 1
            it?.countTypeArguments { type -> type.sourceDeclaration.isClass } shouldBeEqualTo 0
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
            it?.hasTypeArgumentOf(Set::class, Int::class) shouldBeEqualTo true
            it?.hasTypeArgumentOf(List::class, Int::class) shouldBeEqualTo false
            it?.hasTypeArgumentOf(listOf(Set::class, Int::class)) shouldBeEqualTo true
            it?.hasTypeArgumentOf(listOf(List::class, Int::class)) shouldBeEqualTo false
            it?.hasAllTypeArgumentsOf(Set::class) shouldBeEqualTo true
            it?.hasAllTypeArgumentsOf(List::class) shouldBeEqualTo false
            it?.hasAllTypeArgumentsOf(listOf(Set::class)) shouldBeEqualTo true
            it?.hasAllTypeArgumentsOf(listOf(List::class)) shouldBeEqualTo false
            it?.hasTypeArgument { type -> type.sourceDeclaration.isKotlinType } shouldBeEqualTo true
            it?.hasTypeArgument { type -> type.sourceDeclaration.isExternalType } shouldBeEqualTo false
            it?.hasAllTypeArguments { type -> type.sourceDeclaration.isKotlinType } shouldBeEqualTo true
            it?.hasAllTypeArguments { type -> type.sourceDeclaration.isExternalType } shouldBeEqualTo false
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

        // then
        assertSoftly(sut) {
            it
                ?.typeArguments
                ?.firstOrNull()
                ?.typeArguments
                ?.firstOrNull()
                ?.sourceDeclaration
                ?.shouldBeInstanceOf(KoKotlinTypeDeclaration::class)

            it
                ?.typeArguments
                ?.firstOrNull()
                ?.typeArguments
                ?.firstOrNull()
                ?.sourceDeclaration
                ?.name
                ?.shouldBeEqualTo("String")

            it
                ?.typeArguments
                ?.firstOrNull()
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

        // then
        assertSoftly(sut) {
            it
                ?.typeArguments
                ?.firstOrNull()
                ?.sourceDeclaration shouldBeInstanceOf KoFunctionTypeDeclaration::class

            it?.typeArguments?.firstOrNull()?.name shouldBeEqualTo "() -> Unit"
            it?.numTypeArguments shouldBeEqualTo 1
            it?.countTypeArguments { type -> type.sourceDeclaration.isFunctionType } shouldBeEqualTo 1
            it?.countTypeArguments { type -> type.sourceDeclaration.isExternalType } shouldBeEqualTo 0
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
            it?.hasTypeArgument { type -> type.sourceDeclaration.isFunctionType } shouldBeEqualTo true
            it?.hasTypeArgument { type -> type.sourceDeclaration.isExternalType } shouldBeEqualTo false
            it?.hasAllTypeArguments { type -> type.sourceDeclaration.isFunctionType } shouldBeEqualTo true
            it?.hasAllTypeArguments { type -> type.sourceDeclaration.isExternalType } shouldBeEqualTo false
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

        // then
        assertSoftly(sut) {
            it
                ?.typeArguments
                ?.firstOrNull()
                ?.sourceDeclaration shouldBeInstanceOf KoImportAliasDeclaration::class

            it?.typeArguments?.firstOrNull()?.name shouldBeEqualTo "ImportAlias"
            it?.numTypeArguments shouldBeEqualTo 1
            it?.countTypeArguments { type -> type.sourceDeclaration.isImportAlias } shouldBeEqualTo 1
            it?.countTypeArguments { type -> type.sourceDeclaration.isExternalType } shouldBeEqualTo 0
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
            it?.hasTypeArgument { type -> type.sourceDeclaration.isImportAlias } shouldBeEqualTo true
            it?.hasTypeArgument { type -> type.sourceDeclaration.isExternalType } shouldBeEqualTo false
            it?.hasAllTypeArguments { type -> type.sourceDeclaration.isImportAlias } shouldBeEqualTo true
            it?.hasAllTypeArguments { type -> type.sourceDeclaration.isExternalType } shouldBeEqualTo false
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

        // then
        assertSoftly(sut) {
            it
                ?.typeArguments
                ?.firstOrNull()
                ?.sourceDeclaration shouldBeInstanceOf KoTypeAliasDeclaration::class

            it?.typeArguments?.firstOrNull()?.name shouldBeEqualTo "SampleTypeAlias"
            it?.numTypeArguments shouldBeEqualTo 1
            it?.countTypeArguments { type -> type.sourceDeclaration.isTypeAlias } shouldBeEqualTo 1
            it?.countTypeArguments { type -> type.sourceDeclaration.isExternalType } shouldBeEqualTo 0
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
            it?.hasTypeArgument { type -> type.sourceDeclaration.isTypeAlias } shouldBeEqualTo true
            it?.hasTypeArgument { type -> type.sourceDeclaration.isExternalType } shouldBeEqualTo false
            it?.hasAllTypeArguments { type -> type.sourceDeclaration.isTypeAlias } shouldBeEqualTo true
            it?.hasAllTypeArguments { type -> type.sourceDeclaration.isExternalType } shouldBeEqualTo false
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

        // then
        assertSoftly(sut) {
            it
                ?.typeArguments
                ?.firstOrNull()
                ?.sourceDeclaration shouldBeInstanceOf KoExternalDeclaration::class

            it?.typeArguments?.firstOrNull()?.name shouldBeEqualTo "SampleExternalClass"
            it?.numTypeArguments shouldBeEqualTo 1
            it?.countTypeArguments { type -> type.sourceDeclaration.isExternalType } shouldBeEqualTo 1
            it?.countTypeArguments { type -> type.sourceDeclaration.isInterface } shouldBeEqualTo 0
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
            it?.hasTypeArgument { type -> type.sourceDeclaration.isExternalType } shouldBeEqualTo true
            it?.hasTypeArgument { type -> type.sourceDeclaration.isInterface } shouldBeEqualTo false
            it?.hasAllTypeArguments { type -> type.sourceDeclaration.isExternalType } shouldBeEqualTo true
            it?.hasAllTypeArguments { type -> type.sourceDeclaration.isInterface } shouldBeEqualTo false
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

        // then
        assertSoftly(sut) {
            it
                ?.typeArguments
                ?.map { typeArgument -> typeArgument.name }
                .shouldBeEqualTo(listOf("SampleClass", "List<String>"))
            it?.numTypeArguments shouldBeEqualTo 2
        }
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope(
            "core/declaration/type/kotype/snippet/forkotypeargumentprovider/",
            fileName,
        )
}
