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
import com.lemonappdev.konsist.testdata.SampleType
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldBeInstanceOf
import org.amshove.kluent.shouldNotBeInstanceOf
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.MethodSource
import kotlin.reflect.KClass

@Suppress("detekt.LargeClass")
class KoTypeDeclarationForKoSourceDeclarationProviderTest {
    @ParameterizedTest
    @MethodSource("provideValues")
    fun `source declaration`(
        fileName: String,
        instanceOf: KClass<*>,
        notInstanceOf: KClass<*>,
    ) {
        // given
        val sut =
            getSnippetFile(fileName)
                .classes()
                .first()
                .primaryConstructor
                ?.parameters
                ?.first()
                ?.type

        // then
        assertSoftly(sut) {
            it?.sourceDeclaration shouldBeInstanceOf instanceOf
            it?.sourceDeclaration shouldNotBeInstanceOf notInstanceOf
        }
    }

    @Test
    fun `nullable-kotlin-basic-type`() {
        // given
        val sut =
            getSnippetFile("nullable-kotlin-basic-type")
                .classes()
                .first()
                .primaryConstructor
                ?.parameters
                ?.first()
                ?.type

        // then
        assertSoftly(sut) {
            it?.hasSourceDeclaration { declaration -> declaration.name == "String" } shouldBeEqualTo true
            it?.hasSourceDeclaration { declaration -> declaration.name == "List<String>" } shouldBeEqualTo false
            it?.hasSourceDeclarationOf(String::class) shouldBeEqualTo true
            it?.hasSourceDeclarationOf(List::class) shouldBeEqualTo false
            it?.sourceKotlinType shouldBeInstanceOf KoKotlinTypeDeclaration::class
            it?.sourceKotlinType?.name shouldBeEqualTo "String"
            it?.hasSourceKotlinType() shouldBeEqualTo true
            it?.hasSourceKotlinType { declaration -> declaration.name == "String" } shouldBeEqualTo true
            it?.hasSourceKotlinType { declaration -> declaration.name == "List<String>" } shouldBeEqualTo false
            it?.hasSourceKotlinTypeOf(String::class) shouldBeEqualTo true
            it?.hasSourceKotlinTypeOf(Int::class) shouldBeEqualTo false
            it?.sourceClass shouldBeEqualTo null
            it?.hasSourceClass() shouldBeEqualTo false
            it?.hasSourceClassOf(String::class) shouldBeEqualTo false
            it?.sourceObject shouldBeEqualTo null
            it?.hasSourceObject() shouldBeEqualTo false
            it?.hasSourceObjectOf(String::class) shouldBeEqualTo false
            it?.sourceInterface shouldBeEqualTo null
            it?.hasSourceInterface() shouldBeEqualTo false
            it?.hasSourceInterfaceOf(String::class) shouldBeEqualTo false
            it?.sourceTypeAlias shouldBeEqualTo null
            it?.hasSourceTypeAlias() shouldBeEqualTo false
            it?.sourceImportAlias shouldBeEqualTo null
            it?.hasSourceImportAlias() shouldBeEqualTo false
            it?.sourceFunctionType shouldBeEqualTo null
            it?.hasSourceFunctionType() shouldBeEqualTo false
            it?.sourceExternalType shouldBeEqualTo null
            it?.hasSourceExternalType() shouldBeEqualTo false
            it?.hasSourceExternalTypeOf(String::class) shouldBeEqualTo false
        }
    }

    @Test
    fun `not-nullable-kotlin-basic-type`() {
        // given
        val sut =
            getSnippetFile("not-nullable-kotlin-basic-type")
                .classes()
                .first()
                .primaryConstructor
                ?.parameters
                ?.first()
                ?.type

        // then
        assertSoftly(sut) {
            it?.hasSourceDeclaration { declaration -> declaration.name == "String" } shouldBeEqualTo true
            it?.hasSourceDeclaration { declaration -> declaration.name == "List<String>" } shouldBeEqualTo false
            it?.hasSourceDeclarationOf(String::class) shouldBeEqualTo true
            it?.hasSourceDeclarationOf(List::class) shouldBeEqualTo false
            it?.sourceKotlinType shouldBeInstanceOf KoKotlinTypeDeclaration::class
            it?.sourceKotlinType?.name shouldBeEqualTo "String"
            it?.hasSourceKotlinType() shouldBeEqualTo true
            it?.hasSourceKotlinType { declaration -> declaration.name == "String" } shouldBeEqualTo true
            it?.hasSourceKotlinType { declaration -> declaration.name == "List<String>" } shouldBeEqualTo false
            it?.hasSourceKotlinTypeOf(String::class) shouldBeEqualTo true
            it?.hasSourceKotlinTypeOf(Int::class) shouldBeEqualTo false
            it?.sourceClass shouldBeEqualTo null
            it?.hasSourceClass() shouldBeEqualTo false
            it?.hasSourceClassOf(String::class) shouldBeEqualTo false
            it?.sourceObject shouldBeEqualTo null
            it?.hasSourceObject() shouldBeEqualTo false
            it?.hasSourceObjectOf(String::class) shouldBeEqualTo false
            it?.sourceInterface shouldBeEqualTo null
            it?.hasSourceInterface() shouldBeEqualTo false
            it?.hasSourceInterfaceOf(String::class) shouldBeEqualTo false
            it?.sourceTypeAlias shouldBeEqualTo null
            it?.hasSourceTypeAlias() shouldBeEqualTo false
            it?.sourceImportAlias shouldBeEqualTo null
            it?.hasSourceImportAlias() shouldBeEqualTo false
            it?.sourceFunctionType shouldBeEqualTo null
            it?.hasSourceFunctionType() shouldBeEqualTo false
            it?.sourceExternalType shouldBeEqualTo null
            it?.hasSourceExternalType() shouldBeEqualTo false
            it?.hasSourceExternalTypeOf(String::class) shouldBeEqualTo false
        }
    }

    @Test
    fun `nullable-kotlin-collection-type`() {
        // given
        val sut =
            getSnippetFile("nullable-kotlin-collection-type")
                .classes()
                .first()
                .primaryConstructor
                ?.parameters
                ?.first()
                ?.type

        // then
        assertSoftly(sut) {
            it?.hasSourceDeclaration { declaration -> declaration.name == "List<String>" } shouldBeEqualTo true
            it?.hasSourceDeclaration { declaration -> declaration.name == "String" } shouldBeEqualTo false
            it?.hasSourceDeclarationOf(List::class) shouldBeEqualTo true
            it?.hasSourceDeclarationOf(String::class) shouldBeEqualTo false
            it?.sourceKotlinType shouldBeInstanceOf KoKotlinTypeDeclaration::class
            it?.sourceKotlinType?.name shouldBeEqualTo "List<String>"
            it?.hasSourceKotlinType() shouldBeEqualTo true
            it?.hasSourceKotlinType { declaration -> declaration.name == "List<String>" } shouldBeEqualTo true
            it?.hasSourceKotlinType { declaration -> declaration.name == "String" } shouldBeEqualTo false
            it?.hasSourceKotlinTypeOf(List::class) shouldBeEqualTo true
            it?.hasSourceKotlinTypeOf(String::class) shouldBeEqualTo false
            it?.sourceClass shouldBeEqualTo null
            it?.hasSourceClass() shouldBeEqualTo false
            it?.hasSourceClassOf(List::class) shouldBeEqualTo false
            it?.sourceObject shouldBeEqualTo null
            it?.hasSourceObject() shouldBeEqualTo false
            it?.hasSourceObjectOf(List::class) shouldBeEqualTo false
            it?.sourceInterface shouldBeEqualTo null
            it?.hasSourceInterface() shouldBeEqualTo false
            it?.hasSourceInterfaceOf(List::class) shouldBeEqualTo false
            it?.sourceTypeAlias shouldBeEqualTo null
            it?.hasSourceTypeAlias() shouldBeEqualTo false
            it?.sourceImportAlias shouldBeEqualTo null
            it?.hasSourceImportAlias() shouldBeEqualTo false
            it?.sourceFunctionType shouldBeEqualTo null
            it?.hasSourceFunctionType() shouldBeEqualTo false
            it?.sourceExternalType shouldBeEqualTo null
            it?.hasSourceExternalType() shouldBeEqualTo false
            it?.hasSourceExternalTypeOf(List::class) shouldBeEqualTo false
        }
    }

    @Test
    fun `not-nullable-kotlin-collection-type`() {
        // given
        val sut =
            getSnippetFile("not-nullable-kotlin-collection-type")
                .classes()
                .first()
                .primaryConstructor
                ?.parameters
                ?.first()
                ?.type

        // then
        assertSoftly(sut) {
            it?.hasSourceDeclaration { declaration -> declaration.name == "List<String>" } shouldBeEqualTo true
            it?.hasSourceDeclaration { declaration -> declaration.name == "String" } shouldBeEqualTo false
            it?.hasSourceDeclarationOf(List::class) shouldBeEqualTo true
            it?.hasSourceDeclarationOf(String::class) shouldBeEqualTo false
            it?.sourceKotlinType shouldBeInstanceOf KoKotlinTypeDeclaration::class
            it?.sourceKotlinType?.name shouldBeEqualTo "List<String>"
            it?.hasSourceKotlinType() shouldBeEqualTo true
            it?.hasSourceKotlinType { declaration -> declaration.name == "List<String>" } shouldBeEqualTo true
            it?.hasSourceKotlinType { declaration -> declaration.name == "String" } shouldBeEqualTo false
            it?.hasSourceKotlinTypeOf(List::class) shouldBeEqualTo true
            it?.hasSourceKotlinTypeOf(String::class) shouldBeEqualTo false
            it?.sourceClass shouldBeEqualTo null
            it?.hasSourceClass() shouldBeEqualTo false
            it?.hasSourceClassOf(List::class) shouldBeEqualTo false
            it?.sourceObject shouldBeEqualTo null
            it?.hasSourceObject() shouldBeEqualTo false
            it?.hasSourceObjectOf(List::class) shouldBeEqualTo false
            it?.sourceInterface shouldBeEqualTo null
            it?.hasSourceInterface() shouldBeEqualTo false
            it?.hasSourceInterfaceOf(List::class) shouldBeEqualTo false
            it?.sourceTypeAlias shouldBeEqualTo null
            it?.hasSourceTypeAlias() shouldBeEqualTo false
            it?.sourceImportAlias shouldBeEqualTo null
            it?.hasSourceImportAlias() shouldBeEqualTo false
            it?.sourceFunctionType shouldBeEqualTo null
            it?.hasSourceFunctionType() shouldBeEqualTo false
            it?.sourceExternalType shouldBeEqualTo null
            it?.hasSourceExternalType() shouldBeEqualTo false
            it?.hasSourceExternalTypeOf(List::class) shouldBeEqualTo false
        }
    }

    @Test
    fun `nullable-class-type`() {
        // given
        val sut =
            getSnippetFile("nullable-class-type")
                .classes()
                .first()
                .primaryConstructor
                ?.parameters
                ?.first()
                ?.type

        // then
        assertSoftly(sut) {
            it?.hasSourceDeclaration { declaration -> declaration.name == "SampleType" } shouldBeEqualTo true
            it?.hasSourceDeclaration { declaration -> declaration.name == "OtherName" } shouldBeEqualTo false
            it?.hasSourceDeclarationOf(SampleType::class) shouldBeEqualTo true
            it?.hasSourceDeclarationOf(SampleClass::class) shouldBeEqualTo false
            it?.sourceClass shouldBeInstanceOf KoClassDeclaration::class
            it?.sourceClass?.name shouldBeEqualTo "SampleType"
            it?.hasSourceClass() shouldBeEqualTo true
            it?.hasSourceClass { declaration -> declaration.name == "SampleType" } shouldBeEqualTo true
            it?.hasSourceClass { declaration -> declaration.name == "OtherName" } shouldBeEqualTo false
            it?.hasSourceClassOf(SampleType::class) shouldBeEqualTo true
            it?.hasSourceClassOf(SampleClass::class) shouldBeEqualTo false
            it?.sourceObject shouldBeEqualTo null
            it?.hasSourceObject() shouldBeEqualTo false
            it?.hasSourceObjectOf(SampleType::class) shouldBeEqualTo false
            it?.sourceInterface shouldBeEqualTo null
            it?.hasSourceInterface() shouldBeEqualTo false
            it?.hasSourceInterfaceOf(SampleType::class) shouldBeEqualTo false
            it?.sourceTypeAlias shouldBeEqualTo null
            it?.hasSourceTypeAlias() shouldBeEqualTo false
            it?.sourceImportAlias shouldBeEqualTo null
            it?.hasSourceImportAlias() shouldBeEqualTo false
            it?.sourceKotlinType shouldBeEqualTo null
            it?.hasSourceKotlinType() shouldBeEqualTo false
            it?.hasSourceKotlinTypeOf(SampleType::class) shouldBeEqualTo false
            it?.sourceFunctionType shouldBeEqualTo null
            it?.hasSourceFunctionType() shouldBeEqualTo false
            it?.sourceExternalType shouldBeEqualTo null
            it?.hasSourceExternalType() shouldBeEqualTo false
        }
    }

    @Test
    fun `not-nullable-class-type`() {
        // given
        val sut =
            getSnippetFile("not-nullable-class-type")
                .classes()
                .first()
                .primaryConstructor
                ?.parameters
                ?.first()
                ?.type

        // then
        assertSoftly(sut) {
            it?.hasSourceDeclaration { declaration -> declaration.name == "SampleType" } shouldBeEqualTo true
            it?.hasSourceDeclaration { declaration -> declaration.name == "OtherName" } shouldBeEqualTo false
            it?.hasSourceDeclarationOf(SampleType::class) shouldBeEqualTo true
            it?.hasSourceDeclarationOf(SampleClass::class) shouldBeEqualTo false
            it?.sourceClass shouldBeInstanceOf KoClassDeclaration::class
            it?.sourceClass?.name shouldBeEqualTo "SampleType"
            it?.hasSourceClass() shouldBeEqualTo true
            it?.hasSourceClass { declaration -> declaration.name == "SampleType" } shouldBeEqualTo true
            it?.hasSourceClass { declaration -> declaration.name == "OtherName" } shouldBeEqualTo false
            it?.hasSourceClassOf(SampleType::class) shouldBeEqualTo true
            it?.hasSourceClassOf(SampleClass::class) shouldBeEqualTo false
            it?.sourceObject shouldBeEqualTo null
            it?.hasSourceObject() shouldBeEqualTo false
            it?.hasSourceObjectOf(SampleType::class) shouldBeEqualTo false
            it?.sourceInterface shouldBeEqualTo null
            it?.hasSourceInterface() shouldBeEqualTo false
            it?.hasSourceInterfaceOf(SampleType::class) shouldBeEqualTo false
            it?.sourceTypeAlias shouldBeEqualTo null
            it?.hasSourceTypeAlias() shouldBeEqualTo false
            it?.sourceImportAlias shouldBeEqualTo null
            it?.hasSourceImportAlias() shouldBeEqualTo false
            it?.sourceKotlinType shouldBeEqualTo null
            it?.hasSourceKotlinType() shouldBeEqualTo false
            it?.hasSourceKotlinTypeOf(SampleType::class) shouldBeEqualTo false
            it?.sourceFunctionType shouldBeEqualTo null
            it?.hasSourceFunctionType() shouldBeEqualTo false
            it?.sourceExternalType shouldBeEqualTo null
            it?.hasSourceExternalType() shouldBeEqualTo false
        }
    }

    @Test
    fun `nullable-interface-type`() {
        // given
        val sut =
            getSnippetFile("nullable-interface-type")
                .classes()
                .first()
                .primaryConstructor
                ?.parameters
                ?.first()
                ?.type

        // then
        assertSoftly(sut) {
            it?.hasSourceDeclaration { declaration -> declaration.name == "SampleInterface" } shouldBeEqualTo true
            it?.hasSourceDeclaration { declaration -> declaration.name == "OtherName" } shouldBeEqualTo false
            it?.hasSourceDeclarationOf(SampleInterface::class) shouldBeEqualTo true
            it?.hasSourceDeclarationOf(SampleClass::class) shouldBeEqualTo false
            it?.sourceInterface shouldBeInstanceOf KoInterfaceDeclaration::class
            it?.sourceInterface?.name shouldBeEqualTo "SampleInterface"
            it?.hasSourceInterface() shouldBeEqualTo true
            it?.hasSourceInterface { declaration -> declaration.name == "SampleInterface" } shouldBeEqualTo true
            it?.hasSourceInterface { declaration -> declaration.name == "OtherName" } shouldBeEqualTo false
            it?.hasSourceInterfaceOf(SampleInterface::class) shouldBeEqualTo true
            it?.hasSourceInterfaceOf(SampleClass::class) shouldBeEqualTo false
            it?.sourceClass shouldBeEqualTo null
            it?.hasSourceClass() shouldBeEqualTo false
            it?.hasSourceClassOf(SampleInterface::class) shouldBeEqualTo false
            it?.sourceObject shouldBeEqualTo null
            it?.hasSourceObject() shouldBeEqualTo false
            it?.hasSourceObjectOf(SampleInterface::class) shouldBeEqualTo false
            it?.sourceTypeAlias shouldBeEqualTo null
            it?.hasSourceTypeAlias() shouldBeEqualTo false
            it?.sourceImportAlias shouldBeEqualTo null
            it?.hasSourceImportAlias() shouldBeEqualTo false
            it?.sourceKotlinType shouldBeEqualTo null
            it?.hasSourceKotlinType() shouldBeEqualTo false
            it?.hasSourceKotlinTypeOf(SampleInterface::class) shouldBeEqualTo false
            it?.sourceFunctionType shouldBeEqualTo null
            it?.hasSourceFunctionType() shouldBeEqualTo false
            it?.sourceExternalType shouldBeEqualTo null
            it?.hasSourceExternalType() shouldBeEqualTo false
        }
    }

    @Test
    fun `not-nullable-interface-type`() {
        // given
        val sut =
            getSnippetFile("not-nullable-interface-type")
                .classes()
                .first()
                .primaryConstructor
                ?.parameters
                ?.first()
                ?.type

        // then
        assertSoftly(sut) {
            it?.hasSourceDeclaration { declaration -> declaration.name == "SampleInterface" } shouldBeEqualTo true
            it?.hasSourceDeclaration { declaration -> declaration.name == "OtherName" } shouldBeEqualTo false
            it?.hasSourceDeclarationOf(SampleInterface::class) shouldBeEqualTo true
            it?.hasSourceDeclarationOf(SampleClass::class) shouldBeEqualTo false
            it?.sourceInterface shouldBeInstanceOf KoInterfaceDeclaration::class
            it?.sourceInterface?.name shouldBeEqualTo "SampleInterface"
            it?.hasSourceInterface() shouldBeEqualTo true
            it?.hasSourceInterface { declaration -> declaration.name == "SampleInterface" } shouldBeEqualTo true
            it?.hasSourceInterface { declaration -> declaration.name == "OtherName" } shouldBeEqualTo false
            it?.hasSourceInterfaceOf(SampleInterface::class) shouldBeEqualTo true
            it?.hasSourceInterfaceOf(SampleClass::class) shouldBeEqualTo false
            it?.sourceClass shouldBeEqualTo null
            it?.hasSourceClass() shouldBeEqualTo false
            it?.hasSourceClassOf(SampleInterface::class) shouldBeEqualTo false
            it?.sourceObject shouldBeEqualTo null
            it?.hasSourceObject() shouldBeEqualTo false
            it?.hasSourceObjectOf(SampleInterface::class) shouldBeEqualTo false
            it?.sourceTypeAlias shouldBeEqualTo null
            it?.hasSourceTypeAlias() shouldBeEqualTo false
            it?.sourceImportAlias shouldBeEqualTo null
            it?.hasSourceImportAlias() shouldBeEqualTo false
            it?.sourceKotlinType shouldBeEqualTo null
            it?.hasSourceKotlinType() shouldBeEqualTo false
            it?.hasSourceKotlinTypeOf(SampleInterface::class) shouldBeEqualTo false
            it?.sourceFunctionType shouldBeEqualTo null
            it?.hasSourceFunctionType() shouldBeEqualTo false
            it?.sourceExternalType shouldBeEqualTo null
            it?.hasSourceExternalType() shouldBeEqualTo false
        }
    }

    @Test
    fun `nullable-object-type`() {
        // given
        val sut =
            getSnippetFile("nullable-object-type")
                .classes()
                .first()
                .primaryConstructor
                ?.parameters
                ?.first()
                ?.type

        // then
        assertSoftly(sut) {
            it?.hasSourceDeclaration { declaration -> declaration.name == "SampleObject" } shouldBeEqualTo true
            it?.hasSourceDeclaration { declaration -> declaration.name == "OtherName" } shouldBeEqualTo false
            it?.hasSourceDeclarationOf(SampleObject::class) shouldBeEqualTo true
            it?.hasSourceDeclarationOf(SampleClass::class) shouldBeEqualTo false
            it?.sourceObject shouldBeInstanceOf KoObjectDeclaration::class
            it?.sourceObject?.name shouldBeEqualTo "SampleObject"
            it?.hasSourceObject() shouldBeEqualTo true
            it?.hasSourceObject { declaration -> declaration.name == "SampleObject" } shouldBeEqualTo true
            it?.hasSourceObject { declaration -> declaration.name == "OtherName" } shouldBeEqualTo false
            it?.hasSourceObjectOf(SampleObject::class) shouldBeEqualTo true
            it?.hasSourceObjectOf(SampleClass::class) shouldBeEqualTo false
            it?.sourceClass shouldBeEqualTo null
            it?.hasSourceClass() shouldBeEqualTo false
            it?.hasSourceClassOf(SampleObject::class) shouldBeEqualTo false
            it?.sourceInterface shouldBeEqualTo null
            it?.hasSourceInterface() shouldBeEqualTo false
            it?.hasSourceInterfaceOf(SampleObject::class) shouldBeEqualTo false
            it?.sourceTypeAlias shouldBeEqualTo null
            it?.hasSourceTypeAlias() shouldBeEqualTo false
            it?.sourceImportAlias shouldBeEqualTo null
            it?.hasSourceImportAlias() shouldBeEqualTo false
            it?.sourceKotlinType shouldBeEqualTo null
            it?.hasSourceKotlinType() shouldBeEqualTo false
            it?.hasSourceKotlinTypeOf(SampleObject::class) shouldBeEqualTo false
            it?.sourceFunctionType shouldBeEqualTo null
            it?.hasSourceFunctionType() shouldBeEqualTo false
            it?.sourceExternalType shouldBeEqualTo null
            it?.hasSourceExternalType() shouldBeEqualTo false
        }
    }

    @Test
    fun `not-nullable-object-type`() {
        // given
        val sut =
            getSnippetFile("not-nullable-object-type")
                .classes()
                .first()
                .primaryConstructor
                ?.parameters
                ?.first()
                ?.type

        // then
        assertSoftly(sut) {
            it?.hasSourceDeclaration { declaration -> declaration.name == "SampleObject" } shouldBeEqualTo true
            it?.hasSourceDeclaration { declaration -> declaration.name == "OtherName" } shouldBeEqualTo false
            it?.hasSourceDeclarationOf(SampleObject::class) shouldBeEqualTo true
            it?.hasSourceDeclarationOf(SampleClass::class) shouldBeEqualTo false
            it?.sourceObject shouldBeInstanceOf KoObjectDeclaration::class
            it?.sourceObject?.name shouldBeEqualTo "SampleObject"
            it?.hasSourceObject() shouldBeEqualTo true
            it?.hasSourceObject { declaration -> declaration.name == "SampleObject" } shouldBeEqualTo true
            it?.hasSourceObject { declaration -> declaration.name == "OtherName" } shouldBeEqualTo false
            it?.hasSourceObjectOf(SampleObject::class) shouldBeEqualTo true
            it?.hasSourceObjectOf(SampleClass::class) shouldBeEqualTo false
            it?.sourceClass shouldBeEqualTo null
            it?.hasSourceClass() shouldBeEqualTo false
            it?.hasSourceClassOf(SampleObject::class) shouldBeEqualTo false
            it?.sourceInterface shouldBeEqualTo null
            it?.hasSourceInterface() shouldBeEqualTo false
            it?.hasSourceInterfaceOf(SampleObject::class) shouldBeEqualTo false
            it?.sourceTypeAlias shouldBeEqualTo null
            it?.hasSourceTypeAlias() shouldBeEqualTo false
            it?.sourceImportAlias shouldBeEqualTo null
            it?.hasSourceImportAlias() shouldBeEqualTo false
            it?.sourceKotlinType shouldBeEqualTo null
            it?.hasSourceKotlinType() shouldBeEqualTo false
            it?.hasSourceKotlinTypeOf(SampleObject::class) shouldBeEqualTo false
            it?.sourceFunctionType shouldBeEqualTo null
            it?.hasSourceFunctionType() shouldBeEqualTo false
            it?.sourceExternalType shouldBeEqualTo null
            it?.hasSourceExternalType() shouldBeEqualTo false
        }
    }

    @Test
    fun `nullable-function-type`() {
        // given
        val sut =
            getSnippetFile("nullable-function-type")
                .classes()
                .first()
                .primaryConstructor
                ?.parameters
                ?.first()
                ?.type

        // then
        assertSoftly(sut) {
            it?.hasSourceDeclaration { declaration -> declaration.name == "(SampleObject) -> Unit" } shouldBeEqualTo true
            it?.hasSourceDeclaration { declaration -> declaration.name == "OtherName" } shouldBeEqualTo false
            it?.hasSourceDeclarationOf(SampleObject::class) shouldBeEqualTo false
            it?.sourceFunctionType shouldBeInstanceOf KoFunctionTypeDeclaration::class
            it?.sourceFunctionType?.name shouldBeEqualTo "(SampleObject) -> Unit"
            it?.hasSourceFunctionType() shouldBeEqualTo true
            it?.hasSourceFunctionType { declaration -> declaration.name == "(SampleObject) -> Unit" } shouldBeEqualTo true
            it?.hasSourceFunctionType { declaration -> declaration.name == "SampleObject" } shouldBeEqualTo false
            it?.sourceClass shouldBeEqualTo null
            it?.hasSourceClass() shouldBeEqualTo false
            it?.sourceObject shouldBeEqualTo null
            it?.hasSourceObject() shouldBeEqualTo false
            it?.sourceInterface shouldBeEqualTo null
            it?.hasSourceInterface() shouldBeEqualTo false
            it?.sourceTypeAlias shouldBeEqualTo null
            it?.hasSourceTypeAlias() shouldBeEqualTo false
            it?.sourceImportAlias shouldBeEqualTo null
            it?.hasSourceImportAlias() shouldBeEqualTo false
            it?.sourceKotlinType shouldBeEqualTo null
            it?.hasSourceKotlinType() shouldBeEqualTo false
            it?.sourceExternalType shouldBeEqualTo null
            it?.hasSourceExternalType() shouldBeEqualTo false
        }
    }

    @Test
    fun `not-nullable-function-type`() {
        // given
        val sut =
            getSnippetFile("not-nullable-function-type")
                .classes()
                .first()
                .primaryConstructor
                ?.parameters
                ?.first()
                ?.type

        // then
        assertSoftly(sut) {
            it?.hasSourceDeclaration { declaration -> declaration.name == "(SampleObject) -> Unit" } shouldBeEqualTo true
            it?.hasSourceDeclaration { declaration -> declaration.name == "OtherName" } shouldBeEqualTo false
            it?.hasSourceDeclarationOf(SampleObject::class) shouldBeEqualTo false
            it?.sourceFunctionType shouldBeInstanceOf KoFunctionTypeDeclaration::class
            it?.sourceFunctionType?.name shouldBeEqualTo "(SampleObject) -> Unit"
            it?.hasSourceFunctionType() shouldBeEqualTo true
            it?.hasSourceFunctionType { declaration -> declaration.name == "(SampleObject) -> Unit" } shouldBeEqualTo true
            it?.hasSourceFunctionType { declaration -> declaration.name == "SampleObject" } shouldBeEqualTo false
            it?.sourceClass shouldBeEqualTo null
            it?.hasSourceClass() shouldBeEqualTo false
            it?.sourceObject shouldBeEqualTo null
            it?.hasSourceObject() shouldBeEqualTo false
            it?.sourceInterface shouldBeEqualTo null
            it?.hasSourceInterface() shouldBeEqualTo false
            it?.sourceTypeAlias shouldBeEqualTo null
            it?.hasSourceTypeAlias() shouldBeEqualTo false
            it?.sourceImportAlias shouldBeEqualTo null
            it?.hasSourceImportAlias() shouldBeEqualTo false
            it?.sourceKotlinType shouldBeEqualTo null
            it?.hasSourceKotlinType() shouldBeEqualTo false
            it?.sourceExternalType shouldBeEqualTo null
            it?.hasSourceExternalType() shouldBeEqualTo false
        }
    }

    @Test
    fun `nullable-import-alias-type`() {
        // given
        val sut =
            getSnippetFile("nullable-import-alias-type")
                .classes()
                .first()
                .primaryConstructor
                ?.parameters
                ?.first()
                ?.type

        // then
        assertSoftly(sut) {
            it?.hasSourceDeclaration { declaration -> declaration.name == "ImportAlias" } shouldBeEqualTo true
            it?.hasSourceDeclaration { declaration -> declaration.name == "OtherName" } shouldBeEqualTo false
            it?.hasSourceDeclarationOf(SampleObject::class) shouldBeEqualTo false
            it?.sourceImportAlias shouldBeInstanceOf KoImportAliasDeclaration::class
            it?.sourceImportAlias?.name shouldBeEqualTo "ImportAlias"
            it?.hasSourceImportAlias() shouldBeEqualTo true
            it?.hasSourceImportAlias { declaration -> declaration.name == "ImportAlias" } shouldBeEqualTo true
            it?.hasSourceImportAlias { declaration -> declaration.name == "OtherName" } shouldBeEqualTo false
            it?.sourceClass shouldBeEqualTo null
            it?.hasSourceClass() shouldBeEqualTo false
            it?.sourceObject shouldBeEqualTo null
            it?.hasSourceObject() shouldBeEqualTo false
            it?.sourceInterface shouldBeEqualTo null
            it?.hasSourceInterface() shouldBeEqualTo false
            it?.sourceTypeAlias shouldBeEqualTo null
            it?.hasSourceTypeAlias() shouldBeEqualTo false
            it?.sourceKotlinType shouldBeEqualTo null
            it?.hasSourceKotlinType() shouldBeEqualTo false
            it?.sourceFunctionType shouldBeEqualTo null
            it?.hasSourceFunctionType() shouldBeEqualTo false
            it?.sourceExternalType shouldBeEqualTo null
            it?.hasSourceExternalType() shouldBeEqualTo false
        }
    }

    @Test
    fun `not-nullable-import-alias-type`() {
        // given
        val sut =
            getSnippetFile("not-nullable-import-alias-type")
                .classes()
                .first()
                .primaryConstructor
                ?.parameters
                ?.first()
                ?.type

        // then
        assertSoftly(sut) {
            it?.hasSourceDeclaration { declaration -> declaration.name == "ImportAlias" } shouldBeEqualTo true
            it?.hasSourceDeclaration { declaration -> declaration.name == "OtherName" } shouldBeEqualTo false
            it?.hasSourceDeclarationOf(SampleObject::class) shouldBeEqualTo false
            it?.sourceImportAlias shouldBeInstanceOf KoImportAliasDeclaration::class
            it?.sourceImportAlias?.name shouldBeEqualTo "ImportAlias"
            it?.hasSourceImportAlias() shouldBeEqualTo true
            it?.hasSourceImportAlias { declaration -> declaration.name == "ImportAlias" } shouldBeEqualTo true
            it?.hasSourceImportAlias { declaration -> declaration.name == "OtherName" } shouldBeEqualTo false
            it?.sourceClass shouldBeEqualTo null
            it?.hasSourceClass() shouldBeEqualTo false
            it?.sourceObject shouldBeEqualTo null
            it?.hasSourceObject() shouldBeEqualTo false
            it?.sourceInterface shouldBeEqualTo null
            it?.hasSourceInterface() shouldBeEqualTo false
            it?.sourceTypeAlias shouldBeEqualTo null
            it?.hasSourceTypeAlias() shouldBeEqualTo false
            it?.sourceKotlinType shouldBeEqualTo null
            it?.hasSourceKotlinType() shouldBeEqualTo false
            it?.sourceFunctionType shouldBeEqualTo null
            it?.hasSourceFunctionType() shouldBeEqualTo false
            it?.sourceExternalType shouldBeEqualTo null
            it?.hasSourceExternalType() shouldBeEqualTo false
        }
    }

    @Test
    fun `nullable-typealias-type`() {
        // given
        val sut =
            getSnippetFile("nullable-typealias-type")
                .classes()
                .first()
                .primaryConstructor
                ?.parameters
                ?.first()
                ?.type

        // then
        assertSoftly(sut) {
            it?.hasSourceDeclaration { declaration -> declaration.name == "SampleTypeAlias" } shouldBeEqualTo true
            it?.hasSourceDeclaration { declaration -> declaration.name == "OtherName" } shouldBeEqualTo false
            it?.hasSourceDeclarationOf(SampleObject::class) shouldBeEqualTo false
            it?.sourceTypeAlias shouldBeInstanceOf KoTypeAliasDeclaration::class
            it?.sourceTypeAlias?.name shouldBeEqualTo "SampleTypeAlias"
            it?.hasSourceTypeAlias() shouldBeEqualTo true
            it?.hasSourceTypeAlias { declaration -> declaration.name == "SampleTypeAlias" } shouldBeEqualTo true
            it?.hasSourceTypeAlias { declaration -> declaration.name == "OtherName" } shouldBeEqualTo false
            it?.sourceClass shouldBeEqualTo null
            it?.hasSourceClass() shouldBeEqualTo false
            it?.sourceObject shouldBeEqualTo null
            it?.hasSourceObject() shouldBeEqualTo false
            it?.sourceInterface shouldBeEqualTo null
            it?.hasSourceInterface() shouldBeEqualTo false
            it?.sourceImportAlias shouldBeEqualTo null
            it?.hasSourceImportAlias() shouldBeEqualTo false
            it?.sourceKotlinType shouldBeEqualTo null
            it?.hasSourceKotlinType() shouldBeEqualTo false
            it?.sourceFunctionType shouldBeEqualTo null
            it?.hasSourceFunctionType() shouldBeEqualTo false
            it?.sourceExternalType shouldBeEqualTo null
            it?.hasSourceExternalType() shouldBeEqualTo false
        }
    }

    @Test
    fun `not-nullable-typealias-type`() {
        // given
        val sut =
            getSnippetFile("not-nullable-typealias-type")
                .classes()
                .first()
                .primaryConstructor
                ?.parameters
                ?.first()
                ?.type

        // then
        assertSoftly(sut) {
            it?.hasSourceDeclaration { declaration -> declaration.name == "SampleTypeAlias" } shouldBeEqualTo true
            it?.hasSourceDeclaration { declaration -> declaration.name == "OtherName" } shouldBeEqualTo false
            it?.hasSourceDeclarationOf(SampleObject::class) shouldBeEqualTo false
            it?.sourceTypeAlias shouldBeInstanceOf KoTypeAliasDeclaration::class
            it?.sourceTypeAlias?.name shouldBeEqualTo "SampleTypeAlias"
            it?.hasSourceTypeAlias() shouldBeEqualTo true
            it?.hasSourceTypeAlias { declaration -> declaration.name == "SampleTypeAlias" } shouldBeEqualTo true
            it?.hasSourceTypeAlias { declaration -> declaration.name == "OtherName" } shouldBeEqualTo false
            it?.sourceClass shouldBeEqualTo null
            it?.hasSourceClass() shouldBeEqualTo false
            it?.sourceObject shouldBeEqualTo null
            it?.hasSourceObject() shouldBeEqualTo false
            it?.sourceInterface shouldBeEqualTo null
            it?.hasSourceInterface() shouldBeEqualTo false
            it?.sourceImportAlias shouldBeEqualTo null
            it?.hasSourceImportAlias() shouldBeEqualTo false
            it?.sourceKotlinType shouldBeEqualTo null
            it?.hasSourceKotlinType() shouldBeEqualTo false
            it?.sourceFunctionType shouldBeEqualTo null
            it?.hasSourceFunctionType() shouldBeEqualTo false
            it?.sourceExternalType shouldBeEqualTo null
            it?.hasSourceExternalType() shouldBeEqualTo false
        }
    }

    @Test
    fun `nullable-external-type`() {
        // given
        val sut =
            getSnippetFile("nullable-external-type")
                .classes()
                .first()
                .primaryConstructor
                ?.parameters
                ?.first()
                ?.type

        // then
        assertSoftly(sut) {
            it?.hasSourceDeclaration { declaration -> declaration.name == "SampleExternalClass" } shouldBeEqualTo true
            it?.hasSourceDeclaration { declaration -> declaration.name == "OtherName" } shouldBeEqualTo false
            it?.hasSourceDeclarationOf(SampleExternalClass::class) shouldBeEqualTo true
            it?.hasSourceDeclarationOf(SampleClass::class) shouldBeEqualTo false
            it?.sourceExternalType shouldBeInstanceOf KoExternalDeclaration::class
            it?.sourceExternalType?.name shouldBeEqualTo "SampleExternalClass"
            it?.hasSourceExternalType() shouldBeEqualTo true
            it?.hasSourceExternalType { declaration -> declaration.name == "SampleExternalClass" } shouldBeEqualTo true
            it?.hasSourceExternalType { declaration -> declaration.name == "OtherName" } shouldBeEqualTo false
            it?.hasSourceExternalTypeOf(SampleExternalClass::class) shouldBeEqualTo true
            it?.hasSourceExternalTypeOf(SampleClass::class) shouldBeEqualTo false
            it?.sourceClass shouldBeEqualTo null
            it?.hasSourceClass() shouldBeEqualTo false
            it?.hasSourceClassOf(SampleExternalClass::class) shouldBeEqualTo false
            it?.sourceObject shouldBeEqualTo null
            it?.hasSourceObject() shouldBeEqualTo false
            it?.hasSourceObjectOf(SampleExternalClass::class) shouldBeEqualTo false
            it?.sourceInterface shouldBeEqualTo null
            it?.hasSourceInterface() shouldBeEqualTo false
            it?.hasSourceInterfaceOf(SampleExternalClass::class) shouldBeEqualTo false
            it?.sourceTypeAlias shouldBeEqualTo null
            it?.hasSourceTypeAlias() shouldBeEqualTo false
            it?.sourceImportAlias shouldBeEqualTo null
            it?.hasSourceImportAlias() shouldBeEqualTo false
            it?.sourceKotlinType shouldBeEqualTo null
            it?.hasSourceKotlinType() shouldBeEqualTo false
            it?.hasSourceKotlinTypeOf(SampleExternalClass::class) shouldBeEqualTo false
            it?.sourceFunctionType shouldBeEqualTo null
            it?.hasSourceFunctionType() shouldBeEqualTo false
        }
    }

    @Test
    fun `not-nullable-external-type`() {
        // given
        val sut =
            getSnippetFile("not-nullable-external-type")
                .classes()
                .first()
                .primaryConstructor
                ?.parameters
                ?.first()
                ?.type

        // then
        assertSoftly(sut) {
            it?.hasSourceDeclaration { declaration -> declaration.name == "SampleExternalClass" } shouldBeEqualTo true
            it?.hasSourceDeclaration { declaration -> declaration.name == "OtherName" } shouldBeEqualTo false
            it?.hasSourceDeclarationOf(SampleExternalClass::class) shouldBeEqualTo true
            it?.hasSourceDeclarationOf(SampleClass::class) shouldBeEqualTo false
            it?.sourceExternalType shouldBeInstanceOf KoExternalDeclaration::class
            it?.sourceExternalType?.name shouldBeEqualTo "SampleExternalClass"
            it?.hasSourceExternalType() shouldBeEqualTo true
            it?.hasSourceExternalType { declaration -> declaration.name == "SampleExternalClass" } shouldBeEqualTo true
            it?.hasSourceExternalType { declaration -> declaration.name == "OtherName" } shouldBeEqualTo false
            it?.hasSourceExternalTypeOf(SampleExternalClass::class) shouldBeEqualTo true
            it?.hasSourceExternalTypeOf(SampleClass::class) shouldBeEqualTo false
            it?.sourceClass shouldBeEqualTo null
            it?.hasSourceClass() shouldBeEqualTo false
            it?.hasSourceClassOf(SampleExternalClass::class) shouldBeEqualTo false
            it?.sourceObject shouldBeEqualTo null
            it?.hasSourceObject() shouldBeEqualTo false
            it?.hasSourceObjectOf(SampleExternalClass::class) shouldBeEqualTo false
            it?.sourceInterface shouldBeEqualTo null
            it?.hasSourceInterface() shouldBeEqualTo false
            it?.hasSourceInterfaceOf(SampleExternalClass::class) shouldBeEqualTo false
            it?.sourceTypeAlias shouldBeEqualTo null
            it?.hasSourceTypeAlias() shouldBeEqualTo false
            it?.sourceImportAlias shouldBeEqualTo null
            it?.hasSourceImportAlias() shouldBeEqualTo false
            it?.sourceKotlinType shouldBeEqualTo null
            it?.hasSourceKotlinType() shouldBeEqualTo false
            it?.hasSourceKotlinTypeOf(SampleExternalClass::class) shouldBeEqualTo false
            it?.sourceFunctionType shouldBeEqualTo null
            it?.hasSourceFunctionType() shouldBeEqualTo false
        }
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope(
            "core/declaration/type/kotype/snippet/forkosourcedeclarationprovider/",
            fileName,
        )

    companion object {
        @Suppress("unused")
        @JvmStatic
        fun provideValues() =
            listOf(
                arguments("nullable-kotlin-basic-type", KoKotlinTypeDeclaration::class, KoClassDeclaration::class),
                arguments("not-nullable-kotlin-basic-type", KoKotlinTypeDeclaration::class, KoClassDeclaration::class),
                arguments("nullable-kotlin-collection-type", KoKotlinTypeDeclaration::class, KoClassDeclaration::class),
                arguments("not-nullable-kotlin-collection-type", KoKotlinTypeDeclaration::class, KoClassDeclaration::class),
                arguments("nullable-class-type", KoClassDeclaration::class, KoKotlinTypeDeclaration::class),
                arguments("not-nullable-class-type", KoClassDeclaration::class, KoKotlinTypeDeclaration::class),
                arguments("nullable-interface-type", KoInterfaceDeclaration::class, KoKotlinTypeDeclaration::class),
                arguments("not-nullable-interface-type", KoInterfaceDeclaration::class, KoKotlinTypeDeclaration::class),
                arguments("nullable-object-type", KoObjectDeclaration::class, KoKotlinTypeDeclaration::class),
                arguments("not-nullable-object-type", KoObjectDeclaration::class, KoKotlinTypeDeclaration::class),
                arguments("nullable-function-type", KoFunctionTypeDeclaration::class, KoKotlinTypeDeclaration::class),
                arguments("not-nullable-function-type", KoFunctionTypeDeclaration::class, KoKotlinTypeDeclaration::class),
                arguments("nullable-import-alias-type", KoImportAliasDeclaration::class, KoKotlinTypeDeclaration::class),
                arguments("not-nullable-import-alias-type", KoImportAliasDeclaration::class, KoKotlinTypeDeclaration::class),
                arguments("nullable-typealias-type", KoTypeAliasDeclaration::class, KoKotlinTypeDeclaration::class),
                arguments("not-nullable-typealias-type", KoTypeAliasDeclaration::class, KoKotlinTypeDeclaration::class),
                arguments("nullable-external-type", KoExternalDeclaration::class, KoKotlinTypeDeclaration::class),
                arguments("not-nullable-external-type", KoExternalDeclaration::class, KoKotlinTypeDeclaration::class),
            )
    }
}
