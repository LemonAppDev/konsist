package com.lemonappdev.konsist.core.declaration.type.kotype

import com.lemonappdev.konsist.TestSnippetProvider
import com.lemonappdev.konsist.api.declaration.KoClassDeclaration
import com.lemonappdev.konsist.api.declaration.KoExternalDeclaration
import com.lemonappdev.konsist.api.declaration.KoImportAliasDeclaration
import com.lemonappdev.konsist.api.declaration.KoInterfaceDeclaration
import com.lemonappdev.konsist.api.declaration.KoObjectDeclaration
import com.lemonappdev.konsist.api.declaration.KoTypeAliasDeclaration
import com.lemonappdev.konsist.api.declaration.type.KoKotlinTypeDeclaration
import com.lemonappdev.konsist.externalfixture.FixtureExternalClass
import com.lemonappdev.konsist.testdata.FixtureClass
import com.lemonappdev.konsist.testdata.FixtureInterface
import com.lemonappdev.konsist.testdata.FixtureObject
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
            it?.countTypeArguments { type -> type.sourceDeclaration?.isClass == true } shouldBeEqualTo 0
            it?.hasTypeArgumentWithName("String", "Int") shouldBeEqualTo false
            it?.hasTypeArgumentWithName(listOf("String", "Int")) shouldBeEqualTo false
            it?.hasTypeArgumentsWithAllNames("String", "Int") shouldBeEqualTo false
            it?.hasTypeArgumentsWithAllNames(listOf("String", "Int")) shouldBeEqualTo false
            it?.hasTypeArgumentOf(String::class, Int::class) shouldBeEqualTo false
            it?.hasTypeArgumentOf(listOf(String::class, Int::class)) shouldBeEqualTo false
            it?.hasAllTypeArgumentsOf(String::class, Int::class) shouldBeEqualTo false
            it?.hasAllTypeArgumentsOf(listOf(String::class, Int::class)) shouldBeEqualTo false
            it?.hasAllTypeArgumentsOf(listOf(FixtureClass::class, Int::class)) shouldBeEqualTo false
            it?.hasTypeArgument { type -> type.sourceDeclaration?.isExternal == true } shouldBeEqualTo false
            it?.hasAllTypeArguments { type -> type.sourceDeclaration?.isExternal == true } shouldBeEqualTo false
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
            it?.countTypeArguments { type -> type.sourceDeclaration?.isKotlinType == true } shouldBeEqualTo 1
            it?.countTypeArguments { type -> type.sourceDeclaration?.isClass == true } shouldBeEqualTo 0
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
            it?.hasTypeArgumentOf(FixtureClass::class, Int::class) shouldBeEqualTo false
            it?.hasTypeArgumentOf(listOf(String::class, Int::class)) shouldBeEqualTo true
            it?.hasTypeArgumentOf(listOf(FixtureClass::class, Int::class)) shouldBeEqualTo false
            it?.hasAllTypeArgumentsOf(String::class) shouldBeEqualTo true
            it?.hasAllTypeArgumentsOf(String::class, Int::class) shouldBeEqualTo false
            it?.hasAllTypeArgumentsOf(FixtureClass::class, Int::class) shouldBeEqualTo false
            it?.hasAllTypeArgumentsOf(listOf(String::class)) shouldBeEqualTo true
            it?.hasAllTypeArgumentsOf(listOf(String::class, Int::class)) shouldBeEqualTo false
            it?.hasAllTypeArgumentsOf(listOf(FixtureClass::class, Int::class)) shouldBeEqualTo false
            it?.hasTypeArgument { type -> type.sourceDeclaration?.isKotlinType == true } shouldBeEqualTo true
            it?.hasTypeArgument { type -> type.sourceDeclaration?.isExternal == true } shouldBeEqualTo false
            it?.hasAllTypeArguments { type -> type.sourceDeclaration?.isKotlinType == true } shouldBeEqualTo true
            it?.hasAllTypeArguments { type -> type.sourceDeclaration?.isExternal == true } shouldBeEqualTo false
        }
    }

    @Test
    fun `kotlin-type-argument-in-nullable-type`() {
        // given
        val sut =
            getSnippetFile("kotlin-type-argument-in-nullable-type")
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
            it?.countTypeArguments { type -> type.sourceDeclaration?.isKotlinType == true } shouldBeEqualTo 1
            it?.countTypeArguments { type -> type.sourceDeclaration?.isClass == true } shouldBeEqualTo 0
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
            it?.hasTypeArgumentOf(FixtureClass::class, Int::class) shouldBeEqualTo false
            it?.hasTypeArgumentOf(listOf(String::class, Int::class)) shouldBeEqualTo true
            it?.hasTypeArgumentOf(listOf(FixtureClass::class, Int::class)) shouldBeEqualTo false
            it?.hasAllTypeArgumentsOf(String::class) shouldBeEqualTo true
            it?.hasAllTypeArgumentsOf(String::class, Int::class) shouldBeEqualTo false
            it?.hasAllTypeArgumentsOf(FixtureClass::class, Int::class) shouldBeEqualTo false
            it?.hasAllTypeArgumentsOf(listOf(String::class)) shouldBeEqualTo true
            it?.hasAllTypeArgumentsOf(listOf(String::class, Int::class)) shouldBeEqualTo false
            it?.hasAllTypeArgumentsOf(listOf(FixtureClass::class, Int::class)) shouldBeEqualTo false
            it?.hasTypeArgument { type -> type.sourceDeclaration?.isKotlinType == true } shouldBeEqualTo true
            it?.hasTypeArgument { type -> type.sourceDeclaration?.isExternal == true } shouldBeEqualTo false
            it?.hasAllTypeArguments { type -> type.sourceDeclaration?.isKotlinType == true } shouldBeEqualTo true
            it?.hasAllTypeArguments { type -> type.sourceDeclaration?.isExternal == true } shouldBeEqualTo false
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

            it?.typeArguments?.firstOrNull()?.name shouldBeEqualTo "FixtureClass"
            it?.numTypeArguments shouldBeEqualTo 1
            it?.countTypeArguments { type -> type.sourceDeclaration?.isClass == true } shouldBeEqualTo 1
            it?.countTypeArguments { type -> type.sourceDeclaration?.isExternal == true } shouldBeEqualTo 0
            it?.hasTypeArgumentWithName("FixtureClass", "Int") shouldBeEqualTo true
            it?.hasTypeArgumentWithName("OtherClass", "Int") shouldBeEqualTo false
            it?.hasTypeArgumentWithName(listOf("FixtureClass", "Int")) shouldBeEqualTo true
            it?.hasTypeArgumentWithName(listOf("OtherClass", "Int")) shouldBeEqualTo false
            it?.hasTypeArgumentsWithAllNames("FixtureClass") shouldBeEqualTo true
            it?.hasTypeArgumentsWithAllNames("FixtureClass", "Int") shouldBeEqualTo false
            it?.hasTypeArgumentsWithAllNames("OtherClass", "Int") shouldBeEqualTo false
            it?.hasTypeArgumentsWithAllNames(listOf("FixtureClass")) shouldBeEqualTo true
            it?.hasTypeArgumentsWithAllNames(listOf("FixtureClass", "Int")) shouldBeEqualTo false
            it?.hasTypeArgumentsWithAllNames(listOf("OtherClass", "Int")) shouldBeEqualTo false
            it?.hasTypeArgumentOf(FixtureClass::class, Int::class) shouldBeEqualTo true
            it?.hasTypeArgumentOf(FixtureInterface::class, Int::class) shouldBeEqualTo false
            it?.hasTypeArgumentOf(listOf(FixtureClass::class, Int::class)) shouldBeEqualTo true
            it?.hasTypeArgumentOf(listOf(FixtureInterface::class, Int::class)) shouldBeEqualTo false
            it?.hasAllTypeArgumentsOf(FixtureClass::class) shouldBeEqualTo true
            it?.hasAllTypeArgumentsOf(FixtureClass::class, Int::class) shouldBeEqualTo false
            it?.hasAllTypeArgumentsOf(FixtureInterface::class, Int::class) shouldBeEqualTo false
            it?.hasAllTypeArgumentsOf(listOf(FixtureClass::class)) shouldBeEqualTo true
            it?.hasAllTypeArgumentsOf(listOf(FixtureClass::class, Int::class)) shouldBeEqualTo false
            it?.hasAllTypeArgumentsOf(listOf(FixtureInterface::class, Int::class)) shouldBeEqualTo false
            it?.hasTypeArgument { type -> type.sourceDeclaration?.isClass == true } shouldBeEqualTo true
            it?.hasTypeArgument { type -> type.sourceDeclaration?.isExternal == true } shouldBeEqualTo false
            it?.hasAllTypeArguments { type -> type.sourceDeclaration?.isClass == true } shouldBeEqualTo true
            it?.hasAllTypeArguments { type -> type.sourceDeclaration?.isExternal == true } shouldBeEqualTo false
        }
    }

    @Test
    fun `class-type-argument-in-nullable-type`() {
        // given
        val sut =
            getSnippetFile("class-type-argument-in-nullable-type")
                .properties()
                .first()
                .type

        // then
        assertSoftly(sut) {
            it
                ?.typeArguments
                ?.firstOrNull()
                ?.sourceDeclaration shouldBeInstanceOf KoClassDeclaration::class

            it?.typeArguments?.firstOrNull()?.name shouldBeEqualTo "FixtureClass"
            it?.numTypeArguments shouldBeEqualTo 1
            it?.countTypeArguments { type -> type.sourceDeclaration?.isClass == true } shouldBeEqualTo 1
            it?.countTypeArguments { type -> type.sourceDeclaration?.isExternal == true } shouldBeEqualTo 0
            it?.hasTypeArgumentWithName("FixtureClass", "Int") shouldBeEqualTo true
            it?.hasTypeArgumentWithName("OtherClass", "Int") shouldBeEqualTo false
            it?.hasTypeArgumentWithName(listOf("FixtureClass", "Int")) shouldBeEqualTo true
            it?.hasTypeArgumentWithName(listOf("OtherClass", "Int")) shouldBeEqualTo false
            it?.hasTypeArgumentsWithAllNames("FixtureClass") shouldBeEqualTo true
            it?.hasTypeArgumentsWithAllNames("FixtureClass", "Int") shouldBeEqualTo false
            it?.hasTypeArgumentsWithAllNames("OtherClass", "Int") shouldBeEqualTo false
            it?.hasTypeArgumentsWithAllNames(listOf("FixtureClass")) shouldBeEqualTo true
            it?.hasTypeArgumentsWithAllNames(listOf("FixtureClass", "Int")) shouldBeEqualTo false
            it?.hasTypeArgumentsWithAllNames(listOf("OtherClass", "Int")) shouldBeEqualTo false
            it?.hasTypeArgumentOf(FixtureClass::class, Int::class) shouldBeEqualTo true
            it?.hasTypeArgumentOf(FixtureInterface::class, Int::class) shouldBeEqualTo false
            it?.hasTypeArgumentOf(listOf(FixtureClass::class, Int::class)) shouldBeEqualTo true
            it?.hasTypeArgumentOf(listOf(FixtureInterface::class, Int::class)) shouldBeEqualTo false
            it?.hasAllTypeArgumentsOf(FixtureClass::class) shouldBeEqualTo true
            it?.hasAllTypeArgumentsOf(FixtureClass::class, Int::class) shouldBeEqualTo false
            it?.hasAllTypeArgumentsOf(FixtureInterface::class, Int::class) shouldBeEqualTo false
            it?.hasAllTypeArgumentsOf(listOf(FixtureClass::class)) shouldBeEqualTo true
            it?.hasAllTypeArgumentsOf(listOf(FixtureClass::class, Int::class)) shouldBeEqualTo false
            it?.hasAllTypeArgumentsOf(listOf(FixtureInterface::class, Int::class)) shouldBeEqualTo false
            it?.hasTypeArgument { type -> type.sourceDeclaration?.isClass == true } shouldBeEqualTo true
            it?.hasTypeArgument { type -> type.sourceDeclaration?.isExternal == true } shouldBeEqualTo false
            it?.hasAllTypeArguments { type -> type.sourceDeclaration?.isClass == true } shouldBeEqualTo true
            it?.hasAllTypeArguments { type -> type.sourceDeclaration?.isExternal == true } shouldBeEqualTo false
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

            it?.typeArguments?.firstOrNull()?.name shouldBeEqualTo "FixtureInterface"
            it?.numTypeArguments shouldBeEqualTo 1
            it?.countTypeArguments { type -> type.sourceDeclaration?.isInterface == true } shouldBeEqualTo 1
            it?.countTypeArguments { type -> type.sourceDeclaration?.isClass == true } shouldBeEqualTo 0
            it?.hasTypeArgumentWithName("FixtureInterface", "Int") shouldBeEqualTo true
            it?.hasTypeArgumentWithName("OtherClass", "Int") shouldBeEqualTo false
            it?.hasTypeArgumentWithName(listOf("FixtureInterface", "Int")) shouldBeEqualTo true
            it?.hasTypeArgumentWithName(listOf("OtherClass", "Int")) shouldBeEqualTo false
            it?.hasTypeArgumentsWithAllNames("FixtureInterface") shouldBeEqualTo true
            it?.hasTypeArgumentsWithAllNames("FixtureInterface", "Int") shouldBeEqualTo false
            it?.hasTypeArgumentsWithAllNames("OtherClass", "Int") shouldBeEqualTo false
            it?.hasTypeArgumentsWithAllNames(listOf("FixtureInterface")) shouldBeEqualTo true
            it?.hasTypeArgumentsWithAllNames(listOf("FixtureInterface", "Int")) shouldBeEqualTo false
            it?.hasTypeArgumentsWithAllNames(listOf("OtherClass", "Int")) shouldBeEqualTo false
            it?.hasTypeArgumentOf(FixtureInterface::class, Int::class) shouldBeEqualTo true
            it?.hasTypeArgumentOf(FixtureClass::class, Int::class) shouldBeEqualTo false
            it?.hasTypeArgumentOf(listOf(FixtureInterface::class, Int::class)) shouldBeEqualTo true
            it?.hasTypeArgumentOf(listOf(FixtureClass::class, Int::class)) shouldBeEqualTo false
            it?.hasAllTypeArgumentsOf(FixtureInterface::class) shouldBeEqualTo true
            it?.hasAllTypeArgumentsOf(FixtureInterface::class, Int::class) shouldBeEqualTo false
            it?.hasAllTypeArgumentsOf(FixtureClass::class, Int::class) shouldBeEqualTo false
            it?.hasAllTypeArgumentsOf(listOf(FixtureInterface::class)) shouldBeEqualTo true
            it?.hasAllTypeArgumentsOf(listOf(FixtureInterface::class, Int::class)) shouldBeEqualTo false
            it?.hasAllTypeArgumentsOf(listOf(FixtureClass::class, Int::class)) shouldBeEqualTo false
            it?.hasTypeArgument { type -> type.sourceDeclaration?.isInterface == true } shouldBeEqualTo true
            it?.hasTypeArgument { type -> type.sourceDeclaration?.isExternal == true } shouldBeEqualTo false
            it?.hasAllTypeArguments { type -> type.sourceDeclaration?.isInterface == true } shouldBeEqualTo true
            it?.hasAllTypeArguments { type -> type.sourceDeclaration?.isExternal == true } shouldBeEqualTo false
        }
    }

    @Test
    fun `interface-type-argument-in-nullable-type`() {
        // given
        val sut =
            getSnippetFile("interface-type-argument-in-nullable-type")
                .properties()
                .first()
                .type

        // then
        assertSoftly(sut) {
            it
                ?.typeArguments
                ?.firstOrNull()
                ?.sourceDeclaration shouldBeInstanceOf KoInterfaceDeclaration::class

            it?.typeArguments?.firstOrNull()?.name shouldBeEqualTo "FixtureInterface"
            it?.numTypeArguments shouldBeEqualTo 1
            it?.countTypeArguments { type -> type.sourceDeclaration?.isInterface == true } shouldBeEqualTo 1
            it?.countTypeArguments { type -> type.sourceDeclaration?.isClass == true } shouldBeEqualTo 0
            it?.hasTypeArgumentWithName("FixtureInterface", "Int") shouldBeEqualTo true
            it?.hasTypeArgumentWithName("OtherClass", "Int") shouldBeEqualTo false
            it?.hasTypeArgumentWithName(listOf("FixtureInterface", "Int")) shouldBeEqualTo true
            it?.hasTypeArgumentWithName(listOf("OtherClass", "Int")) shouldBeEqualTo false
            it?.hasTypeArgumentsWithAllNames("FixtureInterface") shouldBeEqualTo true
            it?.hasTypeArgumentsWithAllNames("FixtureInterface", "Int") shouldBeEqualTo false
            it?.hasTypeArgumentsWithAllNames("OtherClass", "Int") shouldBeEqualTo false
            it?.hasTypeArgumentsWithAllNames(listOf("FixtureInterface")) shouldBeEqualTo true
            it?.hasTypeArgumentsWithAllNames(listOf("FixtureInterface", "Int")) shouldBeEqualTo false
            it?.hasTypeArgumentsWithAllNames(listOf("OtherClass", "Int")) shouldBeEqualTo false
            it?.hasTypeArgumentOf(FixtureInterface::class, Int::class) shouldBeEqualTo true
            it?.hasTypeArgumentOf(FixtureClass::class, Int::class) shouldBeEqualTo false
            it?.hasTypeArgumentOf(listOf(FixtureInterface::class, Int::class)) shouldBeEqualTo true
            it?.hasTypeArgumentOf(listOf(FixtureClass::class, Int::class)) shouldBeEqualTo false
            it?.hasAllTypeArgumentsOf(FixtureInterface::class) shouldBeEqualTo true
            it?.hasAllTypeArgumentsOf(FixtureInterface::class, Int::class) shouldBeEqualTo false
            it?.hasAllTypeArgumentsOf(FixtureClass::class, Int::class) shouldBeEqualTo false
            it?.hasAllTypeArgumentsOf(listOf(FixtureInterface::class)) shouldBeEqualTo true
            it?.hasAllTypeArgumentsOf(listOf(FixtureInterface::class, Int::class)) shouldBeEqualTo false
            it?.hasAllTypeArgumentsOf(listOf(FixtureClass::class, Int::class)) shouldBeEqualTo false
            it?.hasTypeArgument { type -> type.sourceDeclaration?.isInterface == true } shouldBeEqualTo true
            it?.hasTypeArgument { type -> type.sourceDeclaration?.isExternal == true } shouldBeEqualTo false
            it?.hasAllTypeArguments { type -> type.sourceDeclaration?.isInterface == true } shouldBeEqualTo true
            it?.hasAllTypeArguments { type -> type.sourceDeclaration?.isExternal == true } shouldBeEqualTo false
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

            it?.typeArguments?.firstOrNull()?.name shouldBeEqualTo "FixtureObject"
            it?.numTypeArguments shouldBeEqualTo 1
            it?.countTypeArguments { type -> type.sourceDeclaration?.isObject == true } shouldBeEqualTo 1
            it?.countTypeArguments { type -> type.sourceDeclaration?.isClass == true } shouldBeEqualTo 0
            it?.hasTypeArgumentWithName("FixtureObject", "Int") shouldBeEqualTo true
            it?.hasTypeArgumentWithName("OtherClass", "Int") shouldBeEqualTo false
            it?.hasTypeArgumentWithName(listOf("FixtureObject", "Int")) shouldBeEqualTo true
            it?.hasTypeArgumentWithName(listOf("OtherClass", "Int")) shouldBeEqualTo false
            it?.hasTypeArgumentsWithAllNames("FixtureObject") shouldBeEqualTo true
            it?.hasTypeArgumentsWithAllNames("FixtureObject", "Int") shouldBeEqualTo false
            it?.hasTypeArgumentsWithAllNames("OtherClass", "Int") shouldBeEqualTo false
            it?.hasTypeArgumentsWithAllNames(listOf("FixtureObject")) shouldBeEqualTo true
            it?.hasTypeArgumentsWithAllNames(listOf("FixtureObject", "Int")) shouldBeEqualTo false
            it?.hasTypeArgumentsWithAllNames(listOf("OtherClass", "Int")) shouldBeEqualTo false
            it?.hasTypeArgumentOf(FixtureObject::class, Int::class) shouldBeEqualTo true
            it?.hasTypeArgumentOf(FixtureClass::class, Int::class) shouldBeEqualTo false
            it?.hasTypeArgumentOf(listOf(FixtureObject::class, Int::class)) shouldBeEqualTo true
            it?.hasTypeArgumentOf(listOf(FixtureClass::class, Int::class)) shouldBeEqualTo false
            it?.hasAllTypeArgumentsOf(FixtureObject::class) shouldBeEqualTo true
            it?.hasAllTypeArgumentsOf(FixtureObject::class, Int::class) shouldBeEqualTo false
            it?.hasAllTypeArgumentsOf(FixtureClass::class, Int::class) shouldBeEqualTo false
            it?.hasAllTypeArgumentsOf(listOf(FixtureObject::class)) shouldBeEqualTo true
            it?.hasAllTypeArgumentsOf(listOf(FixtureObject::class, Int::class)) shouldBeEqualTo false
            it?.hasAllTypeArgumentsOf(listOf(FixtureClass::class, Int::class)) shouldBeEqualTo false
            it?.hasTypeArgument { type -> type.sourceDeclaration?.isObject == true } shouldBeEqualTo true
            it?.hasTypeArgument { type -> type.sourceDeclaration?.isExternal == true } shouldBeEqualTo false
            it?.hasAllTypeArguments { type -> type.sourceDeclaration?.isObject == true } shouldBeEqualTo true
            it?.hasAllTypeArguments { type -> type.sourceDeclaration?.isExternal == true } shouldBeEqualTo false
        }
    }

    @Test
    fun `object-type-argument-in-nullable-type`() {
        // given
        val sut =
            getSnippetFile("object-type-argument-in-nullable-type")
                .properties()
                .first()
                .type

        // then
        assertSoftly(sut) {
            it
                ?.typeArguments
                ?.firstOrNull()
                ?.sourceDeclaration shouldBeInstanceOf KoObjectDeclaration::class

            it?.typeArguments?.firstOrNull()?.name shouldBeEqualTo "FixtureObject"
            it?.numTypeArguments shouldBeEqualTo 1
            it?.countTypeArguments { type -> type.sourceDeclaration?.isObject == true } shouldBeEqualTo 1
            it?.countTypeArguments { type -> type.sourceDeclaration?.isClass == true } shouldBeEqualTo 0
            it?.hasTypeArgumentWithName("FixtureObject", "Int") shouldBeEqualTo true
            it?.hasTypeArgumentWithName("OtherClass", "Int") shouldBeEqualTo false
            it?.hasTypeArgumentWithName(listOf("FixtureObject", "Int")) shouldBeEqualTo true
            it?.hasTypeArgumentWithName(listOf("OtherClass", "Int")) shouldBeEqualTo false
            it?.hasTypeArgumentsWithAllNames("FixtureObject") shouldBeEqualTo true
            it?.hasTypeArgumentsWithAllNames("FixtureObject", "Int") shouldBeEqualTo false
            it?.hasTypeArgumentsWithAllNames("OtherClass", "Int") shouldBeEqualTo false
            it?.hasTypeArgumentsWithAllNames(listOf("FixtureObject")) shouldBeEqualTo true
            it?.hasTypeArgumentsWithAllNames(listOf("FixtureObject", "Int")) shouldBeEqualTo false
            it?.hasTypeArgumentsWithAllNames(listOf("OtherClass", "Int")) shouldBeEqualTo false
            it?.hasTypeArgumentOf(FixtureObject::class, Int::class) shouldBeEqualTo true
            it?.hasTypeArgumentOf(FixtureClass::class, Int::class) shouldBeEqualTo false
            it?.hasTypeArgumentOf(listOf(FixtureObject::class, Int::class)) shouldBeEqualTo true
            it?.hasTypeArgumentOf(listOf(FixtureClass::class, Int::class)) shouldBeEqualTo false
            it?.hasAllTypeArgumentsOf(FixtureObject::class) shouldBeEqualTo true
            it?.hasAllTypeArgumentsOf(FixtureObject::class, Int::class) shouldBeEqualTo false
            it?.hasAllTypeArgumentsOf(FixtureClass::class, Int::class) shouldBeEqualTo false
            it?.hasAllTypeArgumentsOf(listOf(FixtureObject::class)) shouldBeEqualTo true
            it?.hasAllTypeArgumentsOf(listOf(FixtureObject::class, Int::class)) shouldBeEqualTo false
            it?.hasAllTypeArgumentsOf(listOf(FixtureClass::class, Int::class)) shouldBeEqualTo false
            it?.hasTypeArgument { type -> type.sourceDeclaration?.isObject == true } shouldBeEqualTo true
            it?.hasTypeArgument { type -> type.sourceDeclaration?.isExternal == true } shouldBeEqualTo false
            it?.hasAllTypeArguments { type -> type.sourceDeclaration?.isObject == true } shouldBeEqualTo true
            it?.hasAllTypeArguments { type -> type.sourceDeclaration?.isExternal == true } shouldBeEqualTo false
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
            it?.countTypeArguments { type -> type.sourceDeclaration?.isKotlinType == true } shouldBeEqualTo 1
            it?.countTypeArguments { type -> type.sourceDeclaration?.isClass == true } shouldBeEqualTo 0
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
            it?.hasTypeArgument { type -> type.sourceDeclaration?.isKotlinType == true } shouldBeEqualTo true
            it?.hasTypeArgument { type -> type.sourceDeclaration?.isExternal == true } shouldBeEqualTo false
            it?.hasAllTypeArguments { type -> type.sourceDeclaration?.isKotlinType == true } shouldBeEqualTo true
            it?.hasAllTypeArguments { type -> type.sourceDeclaration?.isExternal == true } shouldBeEqualTo false
        }
    }

    @Test
    fun `generic-type-argument-in-nullable-type`() {
        // given
        val sut =
            getSnippetFile("generic-type-argument-in-nullable-type")
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
            it?.countTypeArguments { type -> type.sourceDeclaration?.isKotlinType == true } shouldBeEqualTo 1
            it?.countTypeArguments { type -> type.sourceDeclaration?.isClass == true } shouldBeEqualTo 0
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
            it?.hasTypeArgument { type -> type.sourceDeclaration?.isKotlinType == true } shouldBeEqualTo true
            it?.hasTypeArgument { type -> type.sourceDeclaration?.isExternal == true } shouldBeEqualTo false
            it?.hasAllTypeArguments { type -> type.sourceDeclaration?.isKotlinType == true } shouldBeEqualTo true
            it?.hasAllTypeArguments { type -> type.sourceDeclaration?.isExternal == true } shouldBeEqualTo false
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
                ?.sourceDeclaration shouldBeEqualTo null

            it?.typeArguments?.firstOrNull()?.name shouldBeEqualTo "() -> Unit"
            it?.numTypeArguments shouldBeEqualTo 1
            it?.countTypeArguments { type -> type.sourceDeclaration?.isExternal == true } shouldBeEqualTo 0
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
            it?.hasTypeArgumentOf(FixtureInterface::class, Int::class) shouldBeEqualTo false
            it?.hasTypeArgumentOf(listOf(FixtureInterface::class, Int::class)) shouldBeEqualTo false
            it?.hasAllTypeArgumentsOf(FixtureInterface::class, Int::class) shouldBeEqualTo false
            it?.hasAllTypeArgumentsOf(listOf(FixtureInterface::class, Int::class)) shouldBeEqualTo false
            it?.hasTypeArgument { type -> type.sourceDeclaration?.isExternal == true } shouldBeEqualTo false
            it?.hasAllTypeArguments { type -> type.sourceDeclaration?.isExternal == true } shouldBeEqualTo false
        }
    }

    @Test
    fun `function-type-argument-in-nullable-type`() {
        // given
        val sut =
            getSnippetFile("function-type-argument-in-nullable-type")
                .properties()
                .first()
                .type

        // then
        assertSoftly(sut) {
            it
                ?.typeArguments
                ?.firstOrNull()
                ?.sourceDeclaration shouldBeEqualTo null

            it?.typeArguments?.firstOrNull()?.name shouldBeEqualTo "() -> Unit"
            it?.numTypeArguments shouldBeEqualTo 1
            it?.countTypeArguments { type -> type.sourceDeclaration?.isExternal == true } shouldBeEqualTo 0
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
            it?.hasTypeArgumentOf(FixtureInterface::class, Int::class) shouldBeEqualTo false
            it?.hasTypeArgumentOf(listOf(FixtureInterface::class, Int::class)) shouldBeEqualTo false
            it?.hasAllTypeArgumentsOf(FixtureInterface::class, Int::class) shouldBeEqualTo false
            it?.hasAllTypeArgumentsOf(listOf(FixtureInterface::class, Int::class)) shouldBeEqualTo false
            it?.hasTypeArgument { type -> type.sourceDeclaration?.isExternal == true } shouldBeEqualTo false
            it?.hasAllTypeArguments { type -> type.sourceDeclaration?.isExternal == true } shouldBeEqualTo false
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
            it?.countTypeArguments { type -> type.sourceDeclaration?.isImportAlias == true } shouldBeEqualTo 1
            it?.countTypeArguments { type -> type.sourceDeclaration?.isExternal == true } shouldBeEqualTo 0
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
            it?.hasTypeArgumentOf(FixtureInterface::class, Int::class) shouldBeEqualTo false
            it?.hasTypeArgumentOf(listOf(FixtureInterface::class, Int::class)) shouldBeEqualTo false
            it?.hasAllTypeArgumentsOf(FixtureInterface::class, Int::class) shouldBeEqualTo false
            it?.hasAllTypeArgumentsOf(listOf(FixtureInterface::class, Int::class)) shouldBeEqualTo false
            it?.hasTypeArgument { type -> type.sourceDeclaration?.isImportAlias == true } shouldBeEqualTo true
            it?.hasTypeArgument { type -> type.sourceDeclaration?.isExternal == true } shouldBeEqualTo false
            it?.hasAllTypeArguments { type -> type.sourceDeclaration?.isImportAlias == true } shouldBeEqualTo true
            it?.hasAllTypeArguments { type -> type.sourceDeclaration?.isExternal == true } shouldBeEqualTo false
        }
    }

    @Test
    fun `import-alias-type-argument-in-nullable-type`() {
        // given
        val sut =
            getSnippetFile("import-alias-type-argument-in-nullable-type")
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
            it?.countTypeArguments { type -> type.sourceDeclaration?.isImportAlias == true } shouldBeEqualTo 1
            it?.countTypeArguments { type -> type.sourceDeclaration?.isExternal == true } shouldBeEqualTo 0
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
            it?.hasTypeArgumentOf(FixtureInterface::class, Int::class) shouldBeEqualTo false
            it?.hasTypeArgumentOf(listOf(FixtureInterface::class, Int::class)) shouldBeEqualTo false
            it?.hasAllTypeArgumentsOf(FixtureInterface::class, Int::class) shouldBeEqualTo false
            it?.hasAllTypeArgumentsOf(listOf(FixtureInterface::class, Int::class)) shouldBeEqualTo false
            it?.hasTypeArgument { type -> type.sourceDeclaration?.isImportAlias == true } shouldBeEqualTo true
            it?.hasTypeArgument { type -> type.sourceDeclaration?.isExternal == true } shouldBeEqualTo false
            it?.hasAllTypeArguments { type -> type.sourceDeclaration?.isImportAlias == true } shouldBeEqualTo true
            it?.hasAllTypeArguments { type -> type.sourceDeclaration?.isExternal == true } shouldBeEqualTo false
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

            it?.typeArguments?.firstOrNull()?.name shouldBeEqualTo "FixtureTypeAlias"
            it?.numTypeArguments shouldBeEqualTo 1
            it?.countTypeArguments { type -> type.sourceDeclaration?.isTypeAlias == true } shouldBeEqualTo 1
            it?.countTypeArguments { type -> type.sourceDeclaration?.isExternal == true } shouldBeEqualTo 0
            it?.hasTypeArgumentWithName("FixtureTypeAlias", "Int") shouldBeEqualTo true
            it?.hasTypeArgumentWithName("OtherClass", "Int") shouldBeEqualTo false
            it?.hasTypeArgumentWithName(listOf("FixtureTypeAlias", "Int")) shouldBeEqualTo true
            it?.hasTypeArgumentWithName(listOf("OtherClass", "Int")) shouldBeEqualTo false
            it?.hasTypeArgumentsWithAllNames("FixtureTypeAlias") shouldBeEqualTo true
            it?.hasTypeArgumentsWithAllNames("FixtureTypeAlias", "Int") shouldBeEqualTo false
            it?.hasTypeArgumentsWithAllNames("OtherClass", "Int") shouldBeEqualTo false
            it?.hasTypeArgumentsWithAllNames(listOf("FixtureTypeAlias")) shouldBeEqualTo true
            it?.hasTypeArgumentsWithAllNames(listOf("FixtureTypeAlias", "Int")) shouldBeEqualTo false
            it?.hasTypeArgumentsWithAllNames(listOf("OtherClass", "Int")) shouldBeEqualTo false
            it?.hasTypeArgumentOf(FixtureInterface::class, Int::class) shouldBeEqualTo false
            it?.hasTypeArgumentOf(listOf(FixtureInterface::class, Int::class)) shouldBeEqualTo false
            it?.hasAllTypeArgumentsOf(FixtureInterface::class, Int::class) shouldBeEqualTo false
            it?.hasAllTypeArgumentsOf(listOf(FixtureInterface::class, Int::class)) shouldBeEqualTo false
            it?.hasTypeArgument { type -> type.sourceDeclaration?.isTypeAlias == true } shouldBeEqualTo true
            it?.hasTypeArgument { type -> type.sourceDeclaration?.isExternal == true } shouldBeEqualTo false
            it?.hasAllTypeArguments { type -> type.sourceDeclaration?.isTypeAlias == true } shouldBeEqualTo true
            it?.hasAllTypeArguments { type -> type.sourceDeclaration?.isExternal == true } shouldBeEqualTo false
        }
    }

    @Test
    fun `typealias-type-argument-in-nullable-type`() {
        // given
        val sut =
            getSnippetFile("typealias-type-argument-in-nullable-type")
                .properties()
                .first()
                .type

        // then
        assertSoftly(sut) {
            it
                ?.typeArguments
                ?.firstOrNull()
                ?.sourceDeclaration shouldBeInstanceOf KoTypeAliasDeclaration::class

            it?.typeArguments?.firstOrNull()?.name shouldBeEqualTo "FixtureTypeAlias"
            it?.numTypeArguments shouldBeEqualTo 1
            it?.countTypeArguments { type -> type.sourceDeclaration?.isTypeAlias == true } shouldBeEqualTo 1
            it?.countTypeArguments { type -> type.sourceDeclaration?.isExternal == true } shouldBeEqualTo 0
            it?.hasTypeArgumentWithName("FixtureTypeAlias", "Int") shouldBeEqualTo true
            it?.hasTypeArgumentWithName("OtherClass", "Int") shouldBeEqualTo false
            it?.hasTypeArgumentWithName(listOf("FixtureTypeAlias", "Int")) shouldBeEqualTo true
            it?.hasTypeArgumentWithName(listOf("OtherClass", "Int")) shouldBeEqualTo false
            it?.hasTypeArgumentsWithAllNames("FixtureTypeAlias") shouldBeEqualTo true
            it?.hasTypeArgumentsWithAllNames("FixtureTypeAlias", "Int") shouldBeEqualTo false
            it?.hasTypeArgumentsWithAllNames("OtherClass", "Int") shouldBeEqualTo false
            it?.hasTypeArgumentsWithAllNames(listOf("FixtureTypeAlias")) shouldBeEqualTo true
            it?.hasTypeArgumentsWithAllNames(listOf("FixtureTypeAlias", "Int")) shouldBeEqualTo false
            it?.hasTypeArgumentsWithAllNames(listOf("OtherClass", "Int")) shouldBeEqualTo false
            it?.hasTypeArgumentOf(FixtureInterface::class, Int::class) shouldBeEqualTo false
            it?.hasTypeArgumentOf(listOf(FixtureInterface::class, Int::class)) shouldBeEqualTo false
            it?.hasAllTypeArgumentsOf(FixtureInterface::class, Int::class) shouldBeEqualTo false
            it?.hasAllTypeArgumentsOf(listOf(FixtureInterface::class, Int::class)) shouldBeEqualTo false
            it?.hasTypeArgument { type -> type.sourceDeclaration?.isTypeAlias == true } shouldBeEqualTo true
            it?.hasTypeArgument { type -> type.sourceDeclaration?.isExternal == true } shouldBeEqualTo false
            it?.hasAllTypeArguments { type -> type.sourceDeclaration?.isTypeAlias == true } shouldBeEqualTo true
            it?.hasAllTypeArguments { type -> type.sourceDeclaration?.isExternal == true } shouldBeEqualTo false
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

            it?.typeArguments?.firstOrNull()?.name shouldBeEqualTo "FixtureExternalClass"
            it?.numTypeArguments shouldBeEqualTo 1
            it?.countTypeArguments { type -> type.sourceDeclaration?.isExternal == true } shouldBeEqualTo 1
            it?.countTypeArguments { type -> type.sourceDeclaration?.isInterface == true } shouldBeEqualTo 0
            it?.hasTypeArgumentWithName("FixtureExternalClass", "Int") shouldBeEqualTo true
            it?.hasTypeArgumentWithName("OtherClass", "Int") shouldBeEqualTo false
            it?.hasTypeArgumentWithName(listOf("FixtureExternalClass", "Int")) shouldBeEqualTo true
            it?.hasTypeArgumentWithName(listOf("OtherClass", "Int")) shouldBeEqualTo false
            it?.hasTypeArgumentsWithAllNames("FixtureExternalClass") shouldBeEqualTo true
            it?.hasTypeArgumentsWithAllNames("FixtureExternalClass", "Int") shouldBeEqualTo false
            it?.hasTypeArgumentsWithAllNames("OtherClass", "Int") shouldBeEqualTo false
            it?.hasTypeArgumentsWithAllNames(listOf("FixtureExternalClass")) shouldBeEqualTo true
            it?.hasTypeArgumentsWithAllNames(listOf("FixtureExternalClass", "Int")) shouldBeEqualTo false
            it?.hasTypeArgumentsWithAllNames(listOf("OtherClass", "Int")) shouldBeEqualTo false
            it?.hasTypeArgumentOf(FixtureExternalClass::class, Int::class) shouldBeEqualTo true
            it?.hasTypeArgumentOf(FixtureInterface::class, Int::class) shouldBeEqualTo false
            it?.hasTypeArgumentOf(listOf(FixtureExternalClass::class, Int::class)) shouldBeEqualTo true
            it?.hasTypeArgumentOf(listOf(FixtureInterface::class, Int::class)) shouldBeEqualTo false
            it?.hasAllTypeArgumentsOf(FixtureExternalClass::class) shouldBeEqualTo true
            it?.hasAllTypeArgumentsOf(FixtureExternalClass::class, Int::class) shouldBeEqualTo false
            it?.hasAllTypeArgumentsOf(FixtureInterface::class, Int::class) shouldBeEqualTo false
            it?.hasAllTypeArgumentsOf(listOf(FixtureExternalClass::class)) shouldBeEqualTo true
            it?.hasAllTypeArgumentsOf(listOf(FixtureExternalClass::class, Int::class)) shouldBeEqualTo false
            it?.hasAllTypeArgumentsOf(listOf(FixtureInterface::class, Int::class)) shouldBeEqualTo false
            it?.hasTypeArgument { type -> type.sourceDeclaration?.isExternal == true } shouldBeEqualTo true
            it?.hasTypeArgument { type -> type.sourceDeclaration?.isInterface == true } shouldBeEqualTo false
            it?.hasAllTypeArguments { type -> type.sourceDeclaration?.isExternal == true } shouldBeEqualTo true
            it?.hasAllTypeArguments { type -> type.sourceDeclaration?.isInterface == true } shouldBeEqualTo false
        }
    }

    @Test
    fun `external-type-argument-in-nullable-type`() {
        // given
        val sut =
            getSnippetFile("external-type-argument-in-nullable-type")
                .properties()
                .first()
                .type

        // then
        assertSoftly(sut) {
            it
                ?.typeArguments
                ?.firstOrNull()
                ?.sourceDeclaration shouldBeInstanceOf KoExternalDeclaration::class

            it?.typeArguments?.firstOrNull()?.name shouldBeEqualTo "FixtureExternalClass"
            it?.numTypeArguments shouldBeEqualTo 1
            it?.countTypeArguments { type -> type.sourceDeclaration?.isExternal == true } shouldBeEqualTo 1
            it?.countTypeArguments { type -> type.sourceDeclaration?.isInterface == true } shouldBeEqualTo 0
            it?.hasTypeArgumentWithName("FixtureExternalClass", "Int") shouldBeEqualTo true
            it?.hasTypeArgumentWithName("OtherClass", "Int") shouldBeEqualTo false
            it?.hasTypeArgumentWithName(listOf("FixtureExternalClass", "Int")) shouldBeEqualTo true
            it?.hasTypeArgumentWithName(listOf("OtherClass", "Int")) shouldBeEqualTo false
            it?.hasTypeArgumentsWithAllNames("FixtureExternalClass") shouldBeEqualTo true
            it?.hasTypeArgumentsWithAllNames("FixtureExternalClass", "Int") shouldBeEqualTo false
            it?.hasTypeArgumentsWithAllNames("OtherClass", "Int") shouldBeEqualTo false
            it?.hasTypeArgumentsWithAllNames(listOf("FixtureExternalClass")) shouldBeEqualTo true
            it?.hasTypeArgumentsWithAllNames(listOf("FixtureExternalClass", "Int")) shouldBeEqualTo false
            it?.hasTypeArgumentsWithAllNames(listOf("OtherClass", "Int")) shouldBeEqualTo false
            it?.hasTypeArgumentOf(FixtureExternalClass::class, Int::class) shouldBeEqualTo true
            it?.hasTypeArgumentOf(FixtureInterface::class, Int::class) shouldBeEqualTo false
            it?.hasTypeArgumentOf(listOf(FixtureExternalClass::class, Int::class)) shouldBeEqualTo true
            it?.hasTypeArgumentOf(listOf(FixtureInterface::class, Int::class)) shouldBeEqualTo false
            it?.hasAllTypeArgumentsOf(FixtureExternalClass::class) shouldBeEqualTo true
            it?.hasAllTypeArgumentsOf(FixtureExternalClass::class, Int::class) shouldBeEqualTo false
            it?.hasAllTypeArgumentsOf(FixtureInterface::class, Int::class) shouldBeEqualTo false
            it?.hasAllTypeArgumentsOf(listOf(FixtureExternalClass::class)) shouldBeEqualTo true
            it?.hasAllTypeArgumentsOf(listOf(FixtureExternalClass::class, Int::class)) shouldBeEqualTo false
            it?.hasAllTypeArgumentsOf(listOf(FixtureInterface::class, Int::class)) shouldBeEqualTo false
            it?.hasTypeArgument { type -> type.sourceDeclaration?.isExternal == true } shouldBeEqualTo true
            it?.hasTypeArgument { type -> type.sourceDeclaration?.isInterface == true } shouldBeEqualTo false
            it?.hasAllTypeArguments { type -> type.sourceDeclaration?.isExternal == true } shouldBeEqualTo true
            it?.hasAllTypeArguments { type -> type.sourceDeclaration?.isInterface == true } shouldBeEqualTo false
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
                .shouldBeEqualTo(listOf("FixtureClass", "List<String>"))
            it?.numTypeArguments shouldBeEqualTo 2
        }
    }

    @Test
    fun `type-without-type-arguments-ignore-case`() {
        // given
        val sut =
            getSnippetFile("type-without-type-arguments-ignore-case")
                .properties()
                .first()
                .type

        // then
        assertSoftly(sut) {
            it?.hasTypeArgumentWithName("fixtureclass") shouldBeEqualTo false
            it?.hasTypeArgumentWithName("fixtureclass", ignoreCase = true) shouldBeEqualTo false
            it?.hasTypeArgumentWithName(listOf("fixtureclass")) shouldBeEqualTo false
            it?.hasTypeArgumentWithName(listOf("fixtureclass"), ignoreCase = true) shouldBeEqualTo false
            it?.hasTypeArgumentWithName(setOf("fixtureclass")) shouldBeEqualTo false
            it?.hasTypeArgumentWithName(setOf("fixtureclass"), ignoreCase = true) shouldBeEqualTo false
            it?.hasTypeArgumentsWithAllNames("fixtureclass", "list<string>") shouldBeEqualTo false
            it?.hasTypeArgumentsWithAllNames("fixtureclass", "list<string>", ignoreCase = true) shouldBeEqualTo false
            it?.hasTypeArgumentsWithAllNames(listOf("fixtureclass", "list<string>")) shouldBeEqualTo false
            it?.hasTypeArgumentsWithAllNames(listOf("fixtureclass", "list<string>"), ignoreCase = true) shouldBeEqualTo false
            it?.hasTypeArgumentsWithAllNames(setOf("fixtureclass", "list<string>")) shouldBeEqualTo false
            it?.hasTypeArgumentsWithAllNames(setOf("fixtureclass", "list<string>"), ignoreCase = true) shouldBeEqualTo false
        }
    }

    @Test
    fun `type-with-type-arguments-ignore-case`() {
        // given
        val sut =
            getSnippetFile("type-with-type-arguments-ignore-case")
                .properties()
                .first()
                .type

        // then
        assertSoftly(sut) {
            it?.hasTypeArgumentWithName("fixtureclass") shouldBeEqualTo false
            it?.hasTypeArgumentWithName("fixtureclass", ignoreCase = true) shouldBeEqualTo true
            it?.hasTypeArgumentWithName("int") shouldBeEqualTo false
            it?.hasTypeArgumentWithName("int", ignoreCase = true) shouldBeEqualTo false
            it?.hasTypeArgumentWithName("fixtureclass", "int") shouldBeEqualTo false
            it?.hasTypeArgumentWithName("fixtureclass", "int", ignoreCase = true) shouldBeEqualTo true
            it?.hasTypeArgumentWithName(listOf("fixtureclass")) shouldBeEqualTo false
            it?.hasTypeArgumentWithName(listOf("fixtureclass"), ignoreCase = true) shouldBeEqualTo true
            it?.hasTypeArgumentWithName(listOf("int")) shouldBeEqualTo false
            it?.hasTypeArgumentWithName(listOf("int"), ignoreCase = true) shouldBeEqualTo false
            it?.hasTypeArgumentWithName(listOf("fixtureclass", "int")) shouldBeEqualTo false
            it?.hasTypeArgumentWithName(listOf("fixtureclass", "int"), ignoreCase = true) shouldBeEqualTo true
            it?.hasTypeArgumentsWithAllNames("fixtureclass") shouldBeEqualTo false
            it?.hasTypeArgumentsWithAllNames("fixtureclass", ignoreCase = true) shouldBeEqualTo true
            it?.hasTypeArgumentsWithAllNames("fixtureclass", "list<string>") shouldBeEqualTo false
            it?.hasTypeArgumentsWithAllNames("fixtureclass", "list<string>", ignoreCase = true) shouldBeEqualTo true
            it?.hasTypeArgumentsWithAllNames("fixtureclass", "int") shouldBeEqualTo false
            it?.hasTypeArgumentsWithAllNames("fixtureclass", "int", ignoreCase = true) shouldBeEqualTo false
            it?.hasTypeArgumentsWithAllNames(listOf("fixtureclass")) shouldBeEqualTo false
            it?.hasTypeArgumentsWithAllNames(listOf("fixtureclass"), ignoreCase = true) shouldBeEqualTo true
            it?.hasTypeArgumentsWithAllNames(listOf("fixtureclass", "list<string>")) shouldBeEqualTo false
            it?.hasTypeArgumentsWithAllNames(listOf("fixtureclass", "list<string>"), ignoreCase = true) shouldBeEqualTo true
            it?.hasTypeArgumentsWithAllNames(listOf("fixtureclass", "int")) shouldBeEqualTo false
            it?.hasTypeArgumentsWithAllNames(listOf("fixtureclass", "int"), ignoreCase = true) shouldBeEqualTo false
        }
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope(
            "core/declaration/type/kotype/snippet/forkotypeargumentprovider/",
            fileName,
        )
}
