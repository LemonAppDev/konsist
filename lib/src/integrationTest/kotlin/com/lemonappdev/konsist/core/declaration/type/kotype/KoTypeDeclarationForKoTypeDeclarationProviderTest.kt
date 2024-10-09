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
import com.lemonappdev.konsist.api.declaration.type.KoStarProjectionDeclaration
import com.lemonappdev.konsist.api.declaration.type.KoTypeParameterDeclaration
import com.lemonappdev.konsist.externalsample.SampleExternalClass
import com.lemonappdev.konsist.testdata.SampleClass
import com.lemonappdev.konsist.testdata.SampleInterface
import com.lemonappdev.konsist.testdata.SampleObject
import com.lemonappdev.konsist.testdata.SampleType
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldBeInstanceOf
import org.junit.jupiter.api.Test

@Suppress("detekt.LargeClass")
class KoTypeDeclarationForKoTypeDeclarationProviderTest {
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
            it?.hasDeclaration { declaration -> declaration.name == "SampleType" } shouldBeEqualTo true
            it?.hasDeclaration { declaration -> declaration.name == "OtherName" } shouldBeEqualTo false
            it?.hasDeclarationOf(SampleType::class) shouldBeEqualTo true
            it?.hasDeclarationOf(SampleClass::class) shouldBeEqualTo false
            it?.hasSourceDeclarationOf(SampleType::class) shouldBeEqualTo true
            it?.hasSourceDeclarationOf(SampleClass::class) shouldBeEqualTo false
            it?.asClassDeclaration() shouldBeInstanceOf KoClassDeclaration::class
            it?.asClassDeclaration()?.name shouldBeEqualTo "SampleType"
            it?.hasClassDeclaration() shouldBeEqualTo true
            it?.hasClassDeclaration { declaration -> declaration.name == "SampleType" } shouldBeEqualTo true
            it?.hasClassDeclaration { declaration -> declaration.name == "OtherName" } shouldBeEqualTo false
            it?.hasClassDeclarationOf(SampleType::class) shouldBeEqualTo true
            it?.hasClassDeclarationOf(SampleClass::class) shouldBeEqualTo false
            it?.asObjectDeclaration() shouldBeEqualTo null
            it?.hasObjectDeclaration() shouldBeEqualTo false
            it?.hasObjectDeclarationOf(SampleType::class) shouldBeEqualTo false
            it?.asInterfaceDeclaration() shouldBeEqualTo null
            it?.hasInterfaceDeclaration() shouldBeEqualTo false
            it?.hasInterfaceDeclarationOf(SampleType::class) shouldBeEqualTo false
            it?.asTypeAliasDeclaration() shouldBeEqualTo null
            it?.hasTypeAliasDeclaration() shouldBeEqualTo false
            it?.asImportAliasDeclaration() shouldBeEqualTo null
            it?.hasImportAliasDeclaration() shouldBeEqualTo false
            it?.asKotlinTypeDeclaration() shouldBeEqualTo null
            it?.hasKotlinTypeDeclaration() shouldBeEqualTo false
            it?.hasKotlinTypeDeclarationOf(SampleType::class) shouldBeEqualTo false
            it?.asFunctionTypeDeclaration() shouldBeEqualTo null
            it?.hasFunctionTypeDeclaration() shouldBeEqualTo false
            it?.asExternalTypeDeclaration() shouldBeEqualTo null
            it?.hasExternalTypeDeclaration() shouldBeEqualTo false
            it?.asTypeParameterDeclaration() shouldBeEqualTo null
            it?.hasTypeParameterDeclaration() shouldBeEqualTo false
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
            it?.hasDeclaration { declaration -> declaration.name == "SampleType" } shouldBeEqualTo true
            it?.hasDeclaration { declaration -> declaration.name == "OtherName" } shouldBeEqualTo false
            it?.hasDeclarationOf(SampleType::class) shouldBeEqualTo true
            it?.hasDeclarationOf(SampleClass::class) shouldBeEqualTo false
            it?.hasSourceDeclarationOf(SampleType::class) shouldBeEqualTo true
            it?.hasSourceDeclarationOf(SampleClass::class) shouldBeEqualTo false
            it?.asClassDeclaration() shouldBeInstanceOf KoClassDeclaration::class
            it?.asClassDeclaration()?.name shouldBeEqualTo "SampleType"
            it?.hasClassDeclaration() shouldBeEqualTo true
            it?.hasClassDeclaration { declaration -> declaration.name == "SampleType" } shouldBeEqualTo true
            it?.hasClassDeclaration { declaration -> declaration.name == "OtherName" } shouldBeEqualTo false
            it?.hasClassDeclarationOf(SampleType::class) shouldBeEqualTo true
            it?.hasClassDeclarationOf(SampleClass::class) shouldBeEqualTo false
            it?.asObjectDeclaration() shouldBeEqualTo null
            it?.hasObjectDeclaration() shouldBeEqualTo false
            it?.hasObjectDeclarationOf(SampleType::class) shouldBeEqualTo false
            it?.asInterfaceDeclaration() shouldBeEqualTo null
            it?.hasInterfaceDeclaration() shouldBeEqualTo false
            it?.hasInterfaceDeclarationOf(SampleType::class) shouldBeEqualTo false
            it?.asTypeAliasDeclaration() shouldBeEqualTo null
            it?.hasTypeAliasDeclaration() shouldBeEqualTo false
            it?.asImportAliasDeclaration() shouldBeEqualTo null
            it?.hasImportAliasDeclaration() shouldBeEqualTo false
            it?.asKotlinTypeDeclaration() shouldBeEqualTo null
            it?.hasKotlinTypeDeclaration() shouldBeEqualTo false
            it?.hasKotlinTypeDeclarationOf(SampleType::class) shouldBeEqualTo false
            it?.asFunctionTypeDeclaration() shouldBeEqualTo null
            it?.hasFunctionTypeDeclaration() shouldBeEqualTo false
            it?.asExternalTypeDeclaration() shouldBeEqualTo null
            it?.hasExternalTypeDeclaration() shouldBeEqualTo false
            it?.asTypeParameterDeclaration() shouldBeEqualTo null
            it?.hasTypeParameterDeclaration() shouldBeEqualTo false
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
            it?.hasDeclaration { declaration -> declaration.name == "SampleObject" } shouldBeEqualTo true
            it?.hasDeclaration { declaration -> declaration.name == "OtherName" } shouldBeEqualTo false
            it?.hasDeclarationOf(SampleObject::class) shouldBeEqualTo true
            it?.hasDeclarationOf(SampleClass::class) shouldBeEqualTo false
            it?.hasSourceDeclarationOf(SampleObject::class) shouldBeEqualTo true
            it?.hasSourceDeclarationOf(SampleClass::class) shouldBeEqualTo false
            it?.asObjectDeclaration() shouldBeInstanceOf KoObjectDeclaration::class
            it?.asObjectDeclaration()?.name shouldBeEqualTo "SampleObject"
            it?.hasObjectDeclaration() shouldBeEqualTo true
            it?.hasObjectDeclaration { declaration -> declaration.name == "SampleObject" } shouldBeEqualTo true
            it?.hasObjectDeclaration { declaration -> declaration.name == "OtherName" } shouldBeEqualTo false
            it?.hasObjectDeclarationOf(SampleObject::class) shouldBeEqualTo true
            it?.hasObjectDeclarationOf(SampleClass::class) shouldBeEqualTo false
            it?.asClassDeclaration() shouldBeEqualTo null
            it?.hasClassDeclaration() shouldBeEqualTo false
            it?.hasClassDeclarationOf(SampleObject::class) shouldBeEqualTo false
            it?.asInterfaceDeclaration() shouldBeEqualTo null
            it?.hasInterfaceDeclaration() shouldBeEqualTo false
            it?.hasInterfaceDeclarationOf(SampleObject::class) shouldBeEqualTo false
            it?.asTypeAliasDeclaration() shouldBeEqualTo null
            it?.hasTypeAliasDeclaration() shouldBeEqualTo false
            it?.asImportAliasDeclaration() shouldBeEqualTo null
            it?.hasImportAliasDeclaration() shouldBeEqualTo false
            it?.asKotlinTypeDeclaration() shouldBeEqualTo null
            it?.hasKotlinTypeDeclaration() shouldBeEqualTo false
            it?.hasKotlinTypeDeclarationOf(SampleObject::class) shouldBeEqualTo false
            it?.asFunctionTypeDeclaration() shouldBeEqualTo null
            it?.hasFunctionTypeDeclaration() shouldBeEqualTo false
            it?.asExternalTypeDeclaration() shouldBeEqualTo null
            it?.hasExternalTypeDeclaration() shouldBeEqualTo false
            it?.asTypeParameterDeclaration() shouldBeEqualTo null
            it?.hasTypeParameterDeclaration() shouldBeEqualTo false
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
            it?.hasDeclaration { declaration -> declaration.name == "SampleObject" } shouldBeEqualTo true
            it?.hasDeclaration { declaration -> declaration.name == "OtherName" } shouldBeEqualTo false
            it?.hasDeclarationOf(SampleObject::class) shouldBeEqualTo true
            it?.hasDeclarationOf(SampleClass::class) shouldBeEqualTo false
            it?.hasSourceDeclarationOf(SampleObject::class) shouldBeEqualTo true
            it?.hasSourceDeclarationOf(SampleClass::class) shouldBeEqualTo false
            it?.asObjectDeclaration() shouldBeInstanceOf KoObjectDeclaration::class
            it?.asObjectDeclaration()?.name shouldBeEqualTo "SampleObject"
            it?.hasObjectDeclaration() shouldBeEqualTo true
            it?.hasObjectDeclaration { declaration -> declaration.name == "SampleObject" } shouldBeEqualTo true
            it?.hasObjectDeclaration { declaration -> declaration.name == "OtherName" } shouldBeEqualTo false
            it?.hasObjectDeclarationOf(SampleObject::class) shouldBeEqualTo true
            it?.hasObjectDeclarationOf(SampleClass::class) shouldBeEqualTo false
            it?.asClassDeclaration() shouldBeEqualTo null
            it?.hasClassDeclaration() shouldBeEqualTo false
            it?.hasClassDeclarationOf(SampleObject::class) shouldBeEqualTo false
            it?.asInterfaceDeclaration() shouldBeEqualTo null
            it?.hasInterfaceDeclaration() shouldBeEqualTo false
            it?.hasInterfaceDeclarationOf(SampleObject::class) shouldBeEqualTo false
            it?.asTypeAliasDeclaration() shouldBeEqualTo null
            it?.hasTypeAliasDeclaration() shouldBeEqualTo false
            it?.asImportAliasDeclaration() shouldBeEqualTo null
            it?.hasImportAliasDeclaration() shouldBeEqualTo false
            it?.asKotlinTypeDeclaration() shouldBeEqualTo null
            it?.hasKotlinTypeDeclaration() shouldBeEqualTo false
            it?.hasKotlinTypeDeclarationOf(SampleObject::class) shouldBeEqualTo false
            it?.asFunctionTypeDeclaration() shouldBeEqualTo null
            it?.hasFunctionTypeDeclaration() shouldBeEqualTo false
            it?.asExternalTypeDeclaration() shouldBeEqualTo null
            it?.hasExternalTypeDeclaration() shouldBeEqualTo false
            it?.asTypeParameterDeclaration() shouldBeEqualTo null
            it?.hasTypeParameterDeclaration() shouldBeEqualTo false
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
            it?.hasDeclaration { declaration -> declaration.name == "SampleInterface" } shouldBeEqualTo true
            it?.hasDeclaration { declaration -> declaration.name == "OtherName" } shouldBeEqualTo false
            it?.hasDeclarationOf(SampleInterface::class) shouldBeEqualTo true
            it?.hasDeclarationOf(SampleClass::class) shouldBeEqualTo false
            it?.hasSourceDeclarationOf(SampleInterface::class) shouldBeEqualTo true
            it?.hasSourceDeclarationOf(SampleClass::class) shouldBeEqualTo false
            it?.asInterfaceDeclaration() shouldBeInstanceOf KoInterfaceDeclaration::class
            it?.asInterfaceDeclaration()?.name shouldBeEqualTo "SampleInterface"
            it?.hasInterfaceDeclaration() shouldBeEqualTo true
            it?.hasInterfaceDeclaration { declaration -> declaration.name == "SampleInterface" } shouldBeEqualTo true
            it?.hasInterfaceDeclaration { declaration -> declaration.name == "OtherName" } shouldBeEqualTo false
            it?.hasInterfaceDeclarationOf(SampleInterface::class) shouldBeEqualTo true
            it?.hasInterfaceDeclarationOf(SampleClass::class) shouldBeEqualTo false
            it?.asClassDeclaration() shouldBeEqualTo null
            it?.hasClassDeclaration() shouldBeEqualTo false
            it?.hasClassDeclarationOf(SampleInterface::class) shouldBeEqualTo false
            it?.asObjectDeclaration() shouldBeEqualTo null
            it?.hasObjectDeclaration() shouldBeEqualTo false
            it?.hasObjectDeclarationOf(SampleInterface::class) shouldBeEqualTo false
            it?.asTypeAliasDeclaration() shouldBeEqualTo null
            it?.hasTypeAliasDeclaration() shouldBeEqualTo false
            it?.asImportAliasDeclaration() shouldBeEqualTo null
            it?.hasImportAliasDeclaration() shouldBeEqualTo false
            it?.asKotlinTypeDeclaration() shouldBeEqualTo null
            it?.hasKotlinTypeDeclaration() shouldBeEqualTo false
            it?.hasKotlinTypeDeclarationOf(SampleInterface::class) shouldBeEqualTo false
            it?.asFunctionTypeDeclaration() shouldBeEqualTo null
            it?.hasFunctionTypeDeclaration() shouldBeEqualTo false
            it?.asExternalTypeDeclaration() shouldBeEqualTo null
            it?.hasExternalTypeDeclaration() shouldBeEqualTo false
            it?.asTypeParameterDeclaration() shouldBeEqualTo null
            it?.hasTypeParameterDeclaration() shouldBeEqualTo false
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
            it?.hasDeclaration { declaration -> declaration.name == "SampleInterface" } shouldBeEqualTo true
            it?.hasDeclaration { declaration -> declaration.name == "OtherName" } shouldBeEqualTo false
            it?.hasDeclarationOf(SampleInterface::class) shouldBeEqualTo true
            it?.hasDeclarationOf(SampleClass::class) shouldBeEqualTo false
            it?.hasSourceDeclarationOf(SampleInterface::class) shouldBeEqualTo true
            it?.hasSourceDeclarationOf(SampleClass::class) shouldBeEqualTo false
            it?.asInterfaceDeclaration() shouldBeInstanceOf KoInterfaceDeclaration::class
            it?.asInterfaceDeclaration()?.name shouldBeEqualTo "SampleInterface"
            it?.hasInterfaceDeclaration() shouldBeEqualTo true
            it?.hasInterfaceDeclaration { declaration -> declaration.name == "SampleInterface" } shouldBeEqualTo true
            it?.hasInterfaceDeclaration { declaration -> declaration.name == "OtherName" } shouldBeEqualTo false
            it?.hasInterfaceDeclarationOf(SampleInterface::class) shouldBeEqualTo true
            it?.hasInterfaceDeclarationOf(SampleClass::class) shouldBeEqualTo false
            it?.asClassDeclaration() shouldBeEqualTo null
            it?.hasClassDeclaration() shouldBeEqualTo false
            it?.hasClassDeclarationOf(SampleInterface::class) shouldBeEqualTo false
            it?.asObjectDeclaration() shouldBeEqualTo null
            it?.hasObjectDeclaration() shouldBeEqualTo false
            it?.hasObjectDeclarationOf(SampleInterface::class) shouldBeEqualTo false
            it?.asTypeAliasDeclaration() shouldBeEqualTo null
            it?.hasTypeAliasDeclaration() shouldBeEqualTo false
            it?.asImportAliasDeclaration() shouldBeEqualTo null
            it?.hasImportAliasDeclaration() shouldBeEqualTo false
            it?.asKotlinTypeDeclaration() shouldBeEqualTo null
            it?.hasKotlinTypeDeclaration() shouldBeEqualTo false
            it?.hasKotlinTypeDeclarationOf(SampleInterface::class) shouldBeEqualTo false
            it?.asFunctionTypeDeclaration() shouldBeEqualTo null
            it?.hasFunctionTypeDeclaration() shouldBeEqualTo false
            it?.asExternalTypeDeclaration() shouldBeEqualTo null
            it?.hasExternalTypeDeclaration() shouldBeEqualTo false
            it?.asTypeParameterDeclaration() shouldBeEqualTo null
            it?.hasTypeParameterDeclaration() shouldBeEqualTo false
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
            it?.hasDeclaration { declaration -> declaration.name == "ImportAlias" } shouldBeEqualTo true
            it?.hasDeclaration { declaration -> declaration.name == "OtherName" } shouldBeEqualTo false
            it?.hasDeclarationOf(SampleObject::class) shouldBeEqualTo false
            it?.hasSourceDeclarationOf(SampleObject::class) shouldBeEqualTo false
            it?.asImportAliasDeclaration() shouldBeInstanceOf KoImportAliasDeclaration::class
            it?.asImportAliasDeclaration()?.name shouldBeEqualTo "ImportAlias"
            it?.hasImportAliasDeclaration() shouldBeEqualTo true
            it?.hasImportAliasDeclaration { declaration -> declaration.name == "ImportAlias" } shouldBeEqualTo true
            it?.hasImportAliasDeclaration { declaration -> declaration.name == "OtherName" } shouldBeEqualTo false
            it?.asClassDeclaration() shouldBeEqualTo null
            it?.hasClassDeclaration() shouldBeEqualTo false
            it?.asObjectDeclaration() shouldBeEqualTo null
            it?.hasObjectDeclaration() shouldBeEqualTo false
            it?.asInterfaceDeclaration() shouldBeEqualTo null
            it?.hasInterfaceDeclaration() shouldBeEqualTo false
            it?.asTypeAliasDeclaration() shouldBeEqualTo null
            it?.hasTypeAliasDeclaration() shouldBeEqualTo false
            it?.asKotlinTypeDeclaration() shouldBeEqualTo null
            it?.hasKotlinTypeDeclaration() shouldBeEqualTo false
            it?.asFunctionTypeDeclaration() shouldBeEqualTo null
            it?.hasFunctionTypeDeclaration() shouldBeEqualTo false
            it?.asExternalTypeDeclaration() shouldBeEqualTo null
            it?.hasExternalTypeDeclaration() shouldBeEqualTo false
            it?.asTypeParameterDeclaration() shouldBeEqualTo null
            it?.hasTypeParameterDeclaration() shouldBeEqualTo false
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
            it?.hasDeclaration { declaration -> declaration.name == "SampleTypeAlias" } shouldBeEqualTo true
            it?.hasDeclaration { declaration -> declaration.name == "OtherName" } shouldBeEqualTo false
            it?.hasDeclarationOf(SampleObject::class) shouldBeEqualTo false
            it?.hasSourceDeclarationOf(SampleObject::class) shouldBeEqualTo false
            it?.asTypeAliasDeclaration() shouldBeInstanceOf KoTypeAliasDeclaration::class
            it?.asTypeAliasDeclaration()?.name shouldBeEqualTo "SampleTypeAlias"
            it?.hasTypeAliasDeclaration() shouldBeEqualTo true
            it?.hasTypeAliasDeclaration { declaration -> declaration.name == "SampleTypeAlias" } shouldBeEqualTo true
            it?.hasTypeAliasDeclaration { declaration -> declaration.name == "OtherName" } shouldBeEqualTo false
            it?.asClassDeclaration() shouldBeEqualTo null
            it?.hasClassDeclaration() shouldBeEqualTo false
            it?.asObjectDeclaration() shouldBeEqualTo null
            it?.hasObjectDeclaration() shouldBeEqualTo false
            it?.asInterfaceDeclaration() shouldBeEqualTo null
            it?.hasInterfaceDeclaration() shouldBeEqualTo false
            it?.asImportAliasDeclaration() shouldBeEqualTo null
            it?.hasImportAliasDeclaration() shouldBeEqualTo false
            it?.asKotlinTypeDeclaration() shouldBeEqualTo null
            it?.hasKotlinTypeDeclaration() shouldBeEqualTo false
            it?.asFunctionTypeDeclaration() shouldBeEqualTo null
            it?.hasFunctionTypeDeclaration() shouldBeEqualTo false
            it?.asExternalTypeDeclaration() shouldBeEqualTo null
            it?.hasExternalTypeDeclaration() shouldBeEqualTo false
            it?.asTypeParameterDeclaration() shouldBeEqualTo null
            it?.hasTypeParameterDeclaration() shouldBeEqualTo false
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
            it?.hasDeclaration { declaration -> declaration.name == "ImportAlias" } shouldBeEqualTo true
            it?.hasDeclaration { declaration -> declaration.name == "OtherName" } shouldBeEqualTo false
            it?.hasDeclarationOf(SampleObject::class) shouldBeEqualTo false
            it?.hasSourceDeclarationOf(SampleObject::class) shouldBeEqualTo false
            it?.asImportAliasDeclaration() shouldBeInstanceOf KoImportAliasDeclaration::class
            it?.asImportAliasDeclaration()?.name shouldBeEqualTo "ImportAlias"
            it?.hasImportAliasDeclaration() shouldBeEqualTo true
            it?.hasImportAliasDeclaration { declaration -> declaration.name == "ImportAlias" } shouldBeEqualTo true
            it?.hasImportAliasDeclaration { declaration -> declaration.name == "OtherName" } shouldBeEqualTo false
            it?.asClassDeclaration() shouldBeEqualTo null
            it?.hasClassDeclaration() shouldBeEqualTo false
            it?.asObjectDeclaration() shouldBeEqualTo null
            it?.hasObjectDeclaration() shouldBeEqualTo false
            it?.asInterfaceDeclaration() shouldBeEqualTo null
            it?.hasInterfaceDeclaration() shouldBeEqualTo false
            it?.asTypeAliasDeclaration() shouldBeEqualTo null
            it?.hasTypeAliasDeclaration() shouldBeEqualTo false
            it?.asKotlinTypeDeclaration() shouldBeEqualTo null
            it?.hasKotlinTypeDeclaration() shouldBeEqualTo false
            it?.asFunctionTypeDeclaration() shouldBeEqualTo null
            it?.hasFunctionTypeDeclaration() shouldBeEqualTo false
            it?.asExternalTypeDeclaration() shouldBeEqualTo null
            it?.hasExternalTypeDeclaration() shouldBeEqualTo false
            it?.asTypeParameterDeclaration() shouldBeEqualTo null
            it?.hasTypeParameterDeclaration() shouldBeEqualTo false
        }
    }

    @Test
    fun `nullable-type-parameter`() {
        // given
        val sut =
            getSnippetFile("nullable-type-parameter")
                .classes()
                .first()
                .primaryConstructor
                ?.parameters
                ?.first()
                ?.type

        // then
        assertSoftly(sut) {
            it?.hasSourceDeclarationOf(SampleObject::class) shouldBeEqualTo false
            it?.asTypeParameterDeclaration() shouldBeInstanceOf KoTypeParameterDeclaration::class
            it?.asTypeParameterDeclaration()?.name shouldBeEqualTo "TestType"
            it?.hasTypeParameterDeclaration() shouldBeEqualTo true
            it?.hasTypeParameterDeclaration { declaration -> declaration.name == "TestType" } shouldBeEqualTo true
            it?.hasTypeParameterDeclaration { declaration -> declaration.name == "OtherName" } shouldBeEqualTo false
            it?.asClassDeclaration() shouldBeEqualTo null
            it?.hasClassDeclaration() shouldBeEqualTo false
            it?.asObjectDeclaration() shouldBeEqualTo null
            it?.hasObjectDeclaration() shouldBeEqualTo false
            it?.asInterfaceDeclaration() shouldBeEqualTo null
            it?.hasInterfaceDeclaration() shouldBeEqualTo false
            it?.asImportAliasDeclaration() shouldBeEqualTo null
            it?.hasImportAliasDeclaration() shouldBeEqualTo false
            it?.asKotlinTypeDeclaration() shouldBeEqualTo null
            it?.hasKotlinTypeDeclaration() shouldBeEqualTo false
            it?.asFunctionTypeDeclaration() shouldBeEqualTo null
            it?.hasFunctionTypeDeclaration() shouldBeEqualTo false
            it?.asExternalTypeDeclaration() shouldBeEqualTo null
            it?.hasExternalTypeDeclaration() shouldBeEqualTo false
            it?.asTypeAliasDeclaration() shouldBeEqualTo null
            it?.hasTypeAliasDeclaration() shouldBeEqualTo false
        }
    }

    @Test
    fun `not-nullable-type-parameter`() {
        // given
        val sut =
            getSnippetFile("not-nullable-type-parameter")
                .classes()
                .first()
                .primaryConstructor
                ?.parameters
                ?.first()
                ?.type

        // then
        assertSoftly(sut) {
            it?.hasSourceDeclarationOf(SampleObject::class) shouldBeEqualTo false
            it?.asTypeParameterDeclaration() shouldBeInstanceOf KoTypeParameterDeclaration::class
            it?.asTypeParameterDeclaration()?.name shouldBeEqualTo "TestType"
            it?.hasTypeParameterDeclaration() shouldBeEqualTo true
            it?.hasTypeParameterDeclaration { declaration -> declaration.name == "TestType" } shouldBeEqualTo true
            it?.hasTypeParameterDeclaration { declaration -> declaration.name == "OtherName" } shouldBeEqualTo false
            it?.asClassDeclaration() shouldBeEqualTo null
            it?.hasClassDeclaration() shouldBeEqualTo false
            it?.asObjectDeclaration() shouldBeEqualTo null
            it?.hasObjectDeclaration() shouldBeEqualTo false
            it?.asInterfaceDeclaration() shouldBeEqualTo null
            it?.hasInterfaceDeclaration() shouldBeEqualTo false
            it?.asImportAliasDeclaration() shouldBeEqualTo null
            it?.hasImportAliasDeclaration() shouldBeEqualTo false
            it?.asKotlinTypeDeclaration() shouldBeEqualTo null
            it?.hasKotlinTypeDeclaration() shouldBeEqualTo false
            it?.asFunctionTypeDeclaration() shouldBeEqualTo null
            it?.hasFunctionTypeDeclaration() shouldBeEqualTo false
            it?.asExternalTypeDeclaration() shouldBeEqualTo null
            it?.hasExternalTypeDeclaration() shouldBeEqualTo false
            it?.asTypeAliasDeclaration() shouldBeEqualTo null
            it?.hasTypeAliasDeclaration() shouldBeEqualTo false
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
            it?.hasDeclaration { declaration -> declaration.name == "SampleTypeAlias" } shouldBeEqualTo true
            it?.hasDeclaration { declaration -> declaration.name == "OtherName" } shouldBeEqualTo false
            it?.hasDeclarationOf(SampleObject::class) shouldBeEqualTo false
            it?.hasSourceDeclarationOf(SampleObject::class) shouldBeEqualTo false
            it?.asTypeAliasDeclaration() shouldBeInstanceOf KoTypeAliasDeclaration::class
            it?.asTypeAliasDeclaration()?.name shouldBeEqualTo "SampleTypeAlias"
            it?.hasTypeAliasDeclaration() shouldBeEqualTo true
            it?.hasTypeAliasDeclaration { declaration -> declaration.name == "SampleTypeAlias" } shouldBeEqualTo true
            it?.hasTypeAliasDeclaration { declaration -> declaration.name == "OtherName" } shouldBeEqualTo false
            it?.asClassDeclaration() shouldBeEqualTo null
            it?.hasClassDeclaration() shouldBeEqualTo false
            it?.asObjectDeclaration() shouldBeEqualTo null
            it?.hasObjectDeclaration() shouldBeEqualTo false
            it?.asInterfaceDeclaration() shouldBeEqualTo null
            it?.hasInterfaceDeclaration() shouldBeEqualTo false
            it?.asImportAliasDeclaration() shouldBeEqualTo null
            it?.hasImportAliasDeclaration() shouldBeEqualTo false
            it?.asKotlinTypeDeclaration() shouldBeEqualTo null
            it?.hasKotlinTypeDeclaration() shouldBeEqualTo false
            it?.asFunctionTypeDeclaration() shouldBeEqualTo null
            it?.hasFunctionTypeDeclaration() shouldBeEqualTo false
            it?.asExternalTypeDeclaration() shouldBeEqualTo null
            it?.hasExternalTypeDeclaration() shouldBeEqualTo false
            it?.asTypeParameterDeclaration() shouldBeEqualTo null
            it?.hasTypeParameterDeclaration() shouldBeEqualTo false
        }
    }

    @Test
    fun `nullable-kotlin-type`() {
        // given
        val sut =
            getSnippetFile("nullable-kotlin-type")
                .classes()
                .first()
                .primaryConstructor
                ?.parameters
                ?.first()
                ?.type

        // then
        assertSoftly(sut) {
            it?.hasDeclaration { declaration -> declaration.name == "String" } shouldBeEqualTo true
            it?.hasDeclaration { declaration -> declaration.name == "Int" } shouldBeEqualTo false
            it?.hasDeclarationOf(String::class) shouldBeEqualTo true
            it?.hasSourceDeclarationOf(String::class) shouldBeEqualTo true
            it?.hasSourceDeclarationOf(List::class) shouldBeEqualTo false
            it?.asKotlinTypeDeclaration() shouldBeInstanceOf KoKotlinTypeDeclaration::class
            it?.asKotlinTypeDeclaration()?.name shouldBeEqualTo "String"
            it?.hasKotlinTypeDeclaration() shouldBeEqualTo true
            it?.hasKotlinTypeDeclaration { declaration -> declaration.name == "String" } shouldBeEqualTo true
            it?.hasKotlinTypeDeclaration { declaration -> declaration.name == "Set<String>" } shouldBeEqualTo false
            it?.hasKotlinTypeDeclarationOf(String::class) shouldBeEqualTo true
            it?.hasKotlinTypeDeclarationOf(Int::class) shouldBeEqualTo false
            it?.asClassDeclaration() shouldBeEqualTo null
            it?.hasClassDeclaration() shouldBeEqualTo false
            it?.hasClassDeclarationOf(String::class) shouldBeEqualTo false
            it?.asObjectDeclaration() shouldBeEqualTo null
            it?.hasObjectDeclaration() shouldBeEqualTo false
            it?.hasObjectDeclarationOf(String::class) shouldBeEqualTo false
            it?.asInterfaceDeclaration() shouldBeEqualTo null
            it?.hasInterfaceDeclaration() shouldBeEqualTo false
            it?.hasInterfaceDeclarationOf(String::class) shouldBeEqualTo false
            it?.asTypeAliasDeclaration() shouldBeEqualTo null
            it?.hasTypeAliasDeclaration() shouldBeEqualTo false
            it?.asImportAliasDeclaration() shouldBeEqualTo null
            it?.hasImportAliasDeclaration() shouldBeEqualTo false
            it?.asFunctionTypeDeclaration() shouldBeEqualTo null
            it?.hasFunctionTypeDeclaration() shouldBeEqualTo false
            it?.asExternalTypeDeclaration() shouldBeEqualTo null
            it?.hasExternalTypeDeclaration() shouldBeEqualTo false
            it?.hasExternalTypeDeclarationOf(String::class) shouldBeEqualTo false
            it?.asTypeParameterDeclaration() shouldBeEqualTo null
            it?.hasTypeParameterDeclaration() shouldBeEqualTo false
        }
    }

    @Test
    fun `not-nullable-kotlin-type`() {
        // given
        val sut =
            getSnippetFile("not-nullable-kotlin-type")
                .classes()
                .first()
                .primaryConstructor
                ?.parameters
                ?.first()
                ?.type

        // then
        assertSoftly(sut) {
            it?.hasDeclaration { declaration -> declaration.name == "String" } shouldBeEqualTo true
            it?.hasDeclaration { declaration -> declaration.name == "Int" } shouldBeEqualTo false
            it?.hasDeclarationOf(String::class) shouldBeEqualTo true
            it?.hasDeclarationOf(Int::class) shouldBeEqualTo false
            it?.hasSourceDeclarationOf(String::class) shouldBeEqualTo true
            it?.hasSourceDeclarationOf(List::class) shouldBeEqualTo false
            it?.asKotlinTypeDeclaration() shouldBeInstanceOf KoKotlinTypeDeclaration::class
            it?.asKotlinTypeDeclaration()?.name shouldBeEqualTo "String"
            it?.hasKotlinTypeDeclaration() shouldBeEqualTo true
            it?.hasKotlinTypeDeclaration { declaration -> declaration.name == "String" } shouldBeEqualTo true
            it?.hasKotlinTypeDeclaration { declaration -> declaration.name == "Set<String>" } shouldBeEqualTo false
            it?.hasKotlinTypeDeclarationOf(String::class) shouldBeEqualTo true
            it?.hasKotlinTypeDeclarationOf(Int::class) shouldBeEqualTo false
            it?.asClassDeclaration() shouldBeEqualTo null
            it?.hasClassDeclaration() shouldBeEqualTo false
            it?.hasClassDeclarationOf(String::class) shouldBeEqualTo false
            it?.asObjectDeclaration() shouldBeEqualTo null
            it?.hasObjectDeclaration() shouldBeEqualTo false
            it?.hasObjectDeclarationOf(String::class) shouldBeEqualTo false
            it?.asInterfaceDeclaration() shouldBeEqualTo null
            it?.hasInterfaceDeclaration() shouldBeEqualTo false
            it?.hasInterfaceDeclarationOf(String::class) shouldBeEqualTo false
            it?.asTypeAliasDeclaration() shouldBeEqualTo null
            it?.hasTypeAliasDeclaration() shouldBeEqualTo false
            it?.asImportAliasDeclaration() shouldBeEqualTo null
            it?.hasImportAliasDeclaration() shouldBeEqualTo false
            it?.asFunctionTypeDeclaration() shouldBeEqualTo null
            it?.hasFunctionTypeDeclaration() shouldBeEqualTo false
            it?.asExternalTypeDeclaration() shouldBeEqualTo null
            it?.hasExternalTypeDeclaration() shouldBeEqualTo false
            it?.hasExternalTypeDeclarationOf(String::class) shouldBeEqualTo false
            it?.asTypeParameterDeclaration() shouldBeEqualTo null
            it?.hasTypeParameterDeclaration() shouldBeEqualTo false
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
            it?.hasDeclaration { declaration -> declaration.name == "(SampleObject) -> Unit" } shouldBeEqualTo true
            it?.hasDeclaration { declaration -> declaration.name == "OtherName" } shouldBeEqualTo false
            it?.hasDeclarationOf(SampleObject::class) shouldBeEqualTo false
            it?.hasSourceDeclarationOf(SampleObject::class) shouldBeEqualTo false
            it?.asFunctionTypeDeclaration() shouldBeInstanceOf KoFunctionTypeDeclaration::class
            it?.asFunctionTypeDeclaration()?.name shouldBeEqualTo "(SampleObject) -> Unit"
            it?.hasFunctionTypeDeclaration() shouldBeEqualTo true
            it?.hasFunctionTypeDeclaration { declaration -> declaration.name == "(SampleObject) -> Unit" } shouldBeEqualTo true
            it?.hasFunctionTypeDeclaration { declaration -> declaration.name == "SampleObject" } shouldBeEqualTo false
            it?.asClassDeclaration() shouldBeEqualTo null
            it?.hasClassDeclaration() shouldBeEqualTo false
            it?.asObjectDeclaration() shouldBeEqualTo null
            it?.hasObjectDeclaration() shouldBeEqualTo false
            it?.asInterfaceDeclaration() shouldBeEqualTo null
            it?.hasInterfaceDeclaration() shouldBeEqualTo false
            it?.asTypeAliasDeclaration() shouldBeEqualTo null
            it?.hasTypeAliasDeclaration() shouldBeEqualTo false
            it?.asImportAliasDeclaration() shouldBeEqualTo null
            it?.hasImportAliasDeclaration() shouldBeEqualTo false
            it?.asKotlinTypeDeclaration() shouldBeEqualTo null
            it?.hasKotlinTypeDeclaration() shouldBeEqualTo false
            it?.asExternalTypeDeclaration() shouldBeEqualTo null
            it?.hasExternalTypeDeclaration() shouldBeEqualTo false
            it?.asTypeParameterDeclaration() shouldBeEqualTo null
            it?.hasTypeParameterDeclaration() shouldBeEqualTo false
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
            it?.hasDeclaration { declaration -> declaration.name == "(SampleObject) -> Unit" } shouldBeEqualTo true
            it?.hasDeclaration { declaration -> declaration.name == "OtherName" } shouldBeEqualTo false
            it?.hasDeclarationOf(SampleObject::class) shouldBeEqualTo false
            it?.hasSourceDeclarationOf(SampleObject::class) shouldBeEqualTo false
            it?.asFunctionTypeDeclaration() shouldBeInstanceOf KoFunctionTypeDeclaration::class
            it?.asFunctionTypeDeclaration()?.name shouldBeEqualTo "(SampleObject) -> Unit"
            it?.hasFunctionTypeDeclaration() shouldBeEqualTo true
            it?.hasFunctionTypeDeclaration { declaration -> declaration.name == "(SampleObject) -> Unit" } shouldBeEqualTo true
            it?.hasFunctionTypeDeclaration { declaration -> declaration.name == "SampleObject" } shouldBeEqualTo false
            it?.asClassDeclaration() shouldBeEqualTo null
            it?.hasClassDeclaration() shouldBeEqualTo false
            it?.asObjectDeclaration() shouldBeEqualTo null
            it?.hasObjectDeclaration() shouldBeEqualTo false
            it?.asInterfaceDeclaration() shouldBeEqualTo null
            it?.hasInterfaceDeclaration() shouldBeEqualTo false
            it?.asTypeAliasDeclaration() shouldBeEqualTo null
            it?.hasTypeAliasDeclaration() shouldBeEqualTo false
            it?.asImportAliasDeclaration() shouldBeEqualTo null
            it?.hasImportAliasDeclaration() shouldBeEqualTo false
            it?.asKotlinTypeDeclaration() shouldBeEqualTo null
            it?.hasKotlinTypeDeclaration() shouldBeEqualTo false
            it?.asExternalTypeDeclaration() shouldBeEqualTo null
            it?.hasExternalTypeDeclaration() shouldBeEqualTo false
            it?.asTypeParameterDeclaration() shouldBeEqualTo null
            it?.hasTypeParameterDeclaration() shouldBeEqualTo false
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
            it?.hasDeclaration { declaration -> declaration.name == "SampleExternalClass" } shouldBeEqualTo true
            it?.hasDeclaration { declaration -> declaration.name == "OtherName" } shouldBeEqualTo false
            it?.hasDeclarationOf(SampleExternalClass::class) shouldBeEqualTo true
            it?.hasDeclarationOf(SampleClass::class) shouldBeEqualTo false
            it?.hasSourceDeclarationOf(SampleExternalClass::class) shouldBeEqualTo true
            it?.hasSourceDeclarationOf(SampleClass::class) shouldBeEqualTo false
            it?.asExternalTypeDeclaration() shouldBeInstanceOf KoExternalDeclaration::class
            it?.asExternalTypeDeclaration()?.name shouldBeEqualTo "SampleExternalClass"
            it?.hasExternalTypeDeclaration() shouldBeEqualTo true
            it?.hasExternalTypeDeclaration { declaration -> declaration.name == "SampleExternalClass" } shouldBeEqualTo true
            it?.hasExternalTypeDeclaration { declaration -> declaration.name == "OtherName" } shouldBeEqualTo false
            it?.hasExternalTypeDeclarationOf(SampleExternalClass::class) shouldBeEqualTo true
            it?.hasExternalTypeDeclarationOf(SampleClass::class) shouldBeEqualTo false
            it?.asClassDeclaration() shouldBeEqualTo null
            it?.hasClassDeclaration() shouldBeEqualTo false
            it?.hasClassDeclarationOf(SampleExternalClass::class) shouldBeEqualTo false
            it?.asObjectDeclaration() shouldBeEqualTo null
            it?.hasObjectDeclaration() shouldBeEqualTo false
            it?.hasObjectDeclarationOf(SampleExternalClass::class) shouldBeEqualTo false
            it?.asInterfaceDeclaration() shouldBeEqualTo null
            it?.hasInterfaceDeclaration() shouldBeEqualTo false
            it?.hasInterfaceDeclarationOf(SampleExternalClass::class) shouldBeEqualTo false
            it?.asTypeAliasDeclaration() shouldBeEqualTo null
            it?.hasTypeAliasDeclaration() shouldBeEqualTo false
            it?.asImportAliasDeclaration() shouldBeEqualTo null
            it?.hasImportAliasDeclaration() shouldBeEqualTo false
            it?.asKotlinTypeDeclaration() shouldBeEqualTo null
            it?.hasKotlinTypeDeclaration() shouldBeEqualTo false
            it?.hasKotlinTypeDeclarationOf(SampleExternalClass::class) shouldBeEqualTo false
            it?.asFunctionTypeDeclaration() shouldBeEqualTo null
            it?.hasFunctionTypeDeclaration() shouldBeEqualTo false
            it?.asTypeParameterDeclaration() shouldBeEqualTo null
            it?.hasTypeParameterDeclaration() shouldBeEqualTo false
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
            it?.hasDeclaration { declaration -> declaration.name == "SampleExternalClass" } shouldBeEqualTo true
            it?.hasDeclaration { declaration -> declaration.name == "OtherName" } shouldBeEqualTo false
            it?.hasDeclarationOf(SampleExternalClass::class) shouldBeEqualTo true
            it?.hasDeclarationOf(SampleClass::class) shouldBeEqualTo false
            it?.hasSourceDeclarationOf(SampleExternalClass::class) shouldBeEqualTo true
            it?.hasSourceDeclarationOf(SampleClass::class) shouldBeEqualTo false
            it?.asExternalTypeDeclaration() shouldBeInstanceOf KoExternalDeclaration::class
            it?.asExternalTypeDeclaration()?.name shouldBeEqualTo "SampleExternalClass"
            it?.hasExternalTypeDeclaration() shouldBeEqualTo true
            it?.hasExternalTypeDeclaration { declaration -> declaration.name == "SampleExternalClass" } shouldBeEqualTo true
            it?.hasExternalTypeDeclaration { declaration -> declaration.name == "OtherName" } shouldBeEqualTo false
            it?.hasExternalTypeDeclarationOf(SampleExternalClass::class) shouldBeEqualTo true
            it?.hasExternalTypeDeclarationOf(SampleClass::class) shouldBeEqualTo false
            it?.asClassDeclaration() shouldBeEqualTo null
            it?.hasClassDeclaration() shouldBeEqualTo false
            it?.hasClassDeclarationOf(SampleExternalClass::class) shouldBeEqualTo false
            it?.asObjectDeclaration() shouldBeEqualTo null
            it?.hasObjectDeclaration() shouldBeEqualTo false
            it?.hasObjectDeclarationOf(SampleExternalClass::class) shouldBeEqualTo false
            it?.asInterfaceDeclaration() shouldBeEqualTo null
            it?.hasInterfaceDeclaration() shouldBeEqualTo false
            it?.hasInterfaceDeclarationOf(SampleExternalClass::class) shouldBeEqualTo false
            it?.asTypeAliasDeclaration() shouldBeEqualTo null
            it?.hasTypeAliasDeclaration() shouldBeEqualTo false
            it?.asImportAliasDeclaration() shouldBeEqualTo null
            it?.hasImportAliasDeclaration() shouldBeEqualTo false
            it?.asKotlinTypeDeclaration() shouldBeEqualTo null
            it?.hasKotlinTypeDeclaration() shouldBeEqualTo false
            it?.hasKotlinTypeDeclarationOf(SampleExternalClass::class) shouldBeEqualTo false
            it?.asFunctionTypeDeclaration() shouldBeEqualTo null
            it?.hasFunctionTypeDeclaration() shouldBeEqualTo false
            it?.asTypeParameterDeclaration() shouldBeEqualTo null
            it?.hasTypeParameterDeclaration() shouldBeEqualTo false
        }
    }

    @Test
    fun `star-projection-type`() {
        // given
        val sut =
            getSnippetFile("star-projection-type")
                .classes()
                .first()
                .primaryConstructor
                ?.parameters
                ?.first()
                ?.type
                ?.asGenericTypeDeclaration()
                ?.typeArguments
                ?.firstOrNull()
                ?.sourceDeclaration

        // then
        assertSoftly(sut) {
            it shouldBeInstanceOf KoStarProjectionDeclaration::class
            it?.asClassDeclaration() shouldBeEqualTo null
            it?.hasClassDeclaration() shouldBeEqualTo false
            it?.asObjectDeclaration() shouldBeEqualTo null
            it?.hasObjectDeclaration() shouldBeEqualTo false
            it?.asInterfaceDeclaration() shouldBeEqualTo null
            it?.hasInterfaceDeclaration() shouldBeEqualTo false
            it?.asTypeAliasDeclaration() shouldBeEqualTo null
            it?.hasTypeAliasDeclaration() shouldBeEqualTo false
            it?.asImportAliasDeclaration() shouldBeEqualTo null
            it?.hasImportAliasDeclaration() shouldBeEqualTo false
            it?.asKotlinTypeDeclaration() shouldBeEqualTo null
            it?.hasKotlinTypeDeclaration() shouldBeEqualTo false
            it?.asFunctionTypeDeclaration() shouldBeEqualTo null
            it?.hasFunctionTypeDeclaration() shouldBeEqualTo false
            it?.asTypeParameterDeclaration() shouldBeEqualTo null
            it?.hasTypeParameterDeclaration() shouldBeEqualTo false
        }
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope(
            "core/declaration/type/kotype/snippet/forkotypedeclarationprovider/",
            fileName,
        )
}
