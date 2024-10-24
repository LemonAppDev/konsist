package com.lemonappdev.konsist.core.declaration.koparent

import com.lemonappdev.konsist.TestSnippetProvider
import com.lemonappdev.konsist.api.declaration.KoClassDeclaration
import com.lemonappdev.konsist.api.declaration.KoExternalDeclaration
import com.lemonappdev.konsist.api.declaration.KoInterfaceDeclaration
import com.lemonappdev.konsist.api.ext.list.parents
import com.lemonappdev.konsist.externalsample.SampleExternalClass
import com.lemonappdev.konsist.testdata.SampleClass
import com.lemonappdev.konsist.testdata.SampleClassWithParameter
import com.lemonappdev.konsist.testdata.SampleCollection1
import com.lemonappdev.konsist.testdata.SampleGenericClassWithParameter
import com.lemonappdev.konsist.testdata.SampleGenericSuperInterface
import com.lemonappdev.konsist.testdata.SampleInterface
import com.lemonappdev.konsist.testdata.SampleParentClass
import com.lemonappdev.konsist.testdata.SampleType
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldBeInstanceOf
import org.junit.jupiter.api.Test

class KoParentDeclarationForKoDeclarationCastProviderTest {
    @Test
    fun `class-with-parent-class-from-file`() {
        // given
        val sut =
            getSnippetFile("class-with-parent-class-from-file")
                .classes()
                .parents()
                .first()

        // then
        assertSoftly(sut) {
            isClass shouldBeEqualTo true
            isObject shouldBeEqualTo false
            isInterface shouldBeEqualTo false
            isTypeAlias shouldBeEqualTo false
            isImportAlias shouldBeEqualTo false
            isKotlinType shouldBeEqualTo false
            isKotlinBasicType shouldBeEqualTo false
            isKotlinCollectionType shouldBeEqualTo false
            isTypeParameter shouldBeEqualTo false
            isExternal shouldBeEqualTo false
            asClassDeclaration() shouldBeInstanceOf KoClassDeclaration::class
            hasClassDeclaration() shouldBeEqualTo true
            hasClassDeclaration { decl -> decl.name == "SampleSuperClass" } shouldBeEqualTo true
            hasClassDeclarationOf(SampleInterface::class) shouldBeEqualTo false
            asObjectDeclaration() shouldBeEqualTo null
            hasObjectDeclaration() shouldBeEqualTo false
            hasObjectDeclaration { decl -> decl.name == "SampleSuperClass" } shouldBeEqualTo false
            hasObjectDeclarationOf(SampleType::class) shouldBeEqualTo false
            asInterfaceDeclaration() shouldBeEqualTo null
            hasInterfaceDeclaration() shouldBeEqualTo false
            hasInterfaceDeclaration { decl -> decl.name == "SampleSuperClass" } shouldBeEqualTo false
            hasInterfaceDeclarationOf(SampleType::class) shouldBeEqualTo false
            asTypeAliasDeclaration() shouldBeEqualTo null
            hasTypeAliasDeclaration() shouldBeEqualTo false
            hasTypeAliasDeclaration { decl -> decl.name == "SampleSuperClass" } shouldBeEqualTo false
            asImportAliasDeclaration() shouldBeEqualTo null
            hasImportAliasDeclaration() shouldBeEqualTo false
            hasImportAliasDeclaration { decl -> decl.name == "SampleSuperClass" } shouldBeEqualTo false
            asKotlinTypeDeclaration() shouldBeEqualTo null
            hasKotlinTypeDeclaration() shouldBeEqualTo false
            hasKotlinTypeDeclaration { decl -> decl.name == "SampleSuperClass" } shouldBeEqualTo false
            hasKotlinTypeDeclarationOf(SampleType::class) shouldBeEqualTo false
            asKotlinBasicTypeDeclaration() shouldBeEqualTo null
            hasKotlinBasicTypeDeclaration() shouldBeEqualTo false
            hasKotlinBasicTypeDeclaration { decl -> decl.name == "SampleSuperClass" } shouldBeEqualTo false
            hasKotlinBasicTypeDeclarationOf(SampleType::class) shouldBeEqualTo false
            asKotlinCollectionTypeDeclaration() shouldBeEqualTo null
            hasKotlinCollectionTypeDeclaration() shouldBeEqualTo false
            hasKotlinCollectionTypeDeclaration { decl -> decl.name == "SampleSuperClass" } shouldBeEqualTo false
            hasKotlinCollectionTypeDeclarationOf(SampleType::class) shouldBeEqualTo false
            asExternalDeclaration() shouldBeEqualTo null
            hasExternalDeclaration() shouldBeEqualTo false
            hasExternalDeclaration { decl -> decl.name == "SampleSuperClass" } shouldBeEqualTo false
            asTypeParameterDeclaration() shouldBeEqualTo null
            hasTypeParameterDeclaration() shouldBeEqualTo false
            hasTypeParameterDeclaration { decl -> decl.name == "SampleSuperClass" } shouldBeEqualTo false
        }
    }

    @Test
    fun `class-with-generic-parent-class-from-file`() {
        // given
        val sut =
            getSnippetFile("class-with-generic-parent-class-from-file")
                .classes()
                .parents()
                .first()

        // then
        assertSoftly(sut) {
            isClass shouldBeEqualTo true
            isObject shouldBeEqualTo false
            isInterface shouldBeEqualTo false
            isTypeAlias shouldBeEqualTo false
            isImportAlias shouldBeEqualTo false
            isKotlinType shouldBeEqualTo false
            isKotlinBasicType shouldBeEqualTo false
            isKotlinCollectionType shouldBeEqualTo false
            isTypeParameter shouldBeEqualTo false
            isExternal shouldBeEqualTo false
            asClassDeclaration() shouldBeInstanceOf KoClassDeclaration::class
            hasClassDeclaration() shouldBeEqualTo true
            hasClassDeclaration { decl -> decl.name == "SampleGenericSuperClass" } shouldBeEqualTo true
            hasClassDeclarationOf(SampleInterface::class) shouldBeEqualTo false
            asObjectDeclaration() shouldBeEqualTo null
            hasObjectDeclaration() shouldBeEqualTo false
            hasObjectDeclaration { decl -> decl.name == "SampleGenericSuperClass" } shouldBeEqualTo false
            hasObjectDeclarationOf(SampleType::class) shouldBeEqualTo false
            asInterfaceDeclaration() shouldBeEqualTo null
            hasInterfaceDeclaration() shouldBeEqualTo false
            hasInterfaceDeclaration { decl -> decl.name == "SampleGenericSuperClass" } shouldBeEqualTo false
            hasInterfaceDeclarationOf(SampleType::class) shouldBeEqualTo false
            asTypeAliasDeclaration() shouldBeEqualTo null
            hasTypeAliasDeclaration() shouldBeEqualTo false
            hasTypeAliasDeclaration { decl -> decl.name == "SampleGenericSuperClass" } shouldBeEqualTo false
            asImportAliasDeclaration() shouldBeEqualTo null
            hasImportAliasDeclaration() shouldBeEqualTo false
            hasImportAliasDeclaration { decl -> decl.name == "SampleGenericSuperClass" } shouldBeEqualTo false
            asKotlinTypeDeclaration() shouldBeEqualTo null
            hasKotlinTypeDeclaration() shouldBeEqualTo false
            hasKotlinTypeDeclaration { decl -> decl.name == "SampleGenericSuperClass" } shouldBeEqualTo false
            hasKotlinTypeDeclarationOf(SampleType::class) shouldBeEqualTo false
            asKotlinBasicTypeDeclaration() shouldBeEqualTo null
            hasKotlinBasicTypeDeclaration() shouldBeEqualTo false
            hasKotlinBasicTypeDeclaration { decl -> decl.name == "SampleGenericSuperClass" } shouldBeEqualTo false
            hasKotlinBasicTypeDeclarationOf(SampleType::class) shouldBeEqualTo false
            asKotlinCollectionTypeDeclaration() shouldBeEqualTo null
            hasKotlinCollectionTypeDeclaration() shouldBeEqualTo false
            hasKotlinCollectionTypeDeclaration { decl -> decl.name == "SampleGenericSuperClass" } shouldBeEqualTo false
            hasKotlinCollectionTypeDeclarationOf(SampleType::class) shouldBeEqualTo false
            asExternalDeclaration() shouldBeEqualTo null
            hasExternalDeclaration() shouldBeEqualTo false
            hasExternalDeclaration { decl -> decl.name == "SampleGenericSuperClass" } shouldBeEqualTo false
            asTypeParameterDeclaration() shouldBeEqualTo null
            hasTypeParameterDeclaration() shouldBeEqualTo false
            hasTypeParameterDeclaration { decl -> decl.name == "SampleGenericSuperClass" } shouldBeEqualTo false
        }
    }

    @Test
    fun `class-with-parametrized-parent-class-from-file`() {
        // given
        val sut =
            getSnippetFile("class-with-parametrized-parent-class-from-file")
                .classes()
                .parents()
                .first()

        // then
        assertSoftly(sut) {
            isClass shouldBeEqualTo true
            isObject shouldBeEqualTo false
            isInterface shouldBeEqualTo false
            isTypeAlias shouldBeEqualTo false
            isImportAlias shouldBeEqualTo false
            isKotlinType shouldBeEqualTo false
            isKotlinBasicType shouldBeEqualTo false
            isKotlinCollectionType shouldBeEqualTo false
            isTypeParameter shouldBeEqualTo false
            isExternal shouldBeEqualTo false
            asClassDeclaration() shouldBeInstanceOf KoClassDeclaration::class
            hasClassDeclaration() shouldBeEqualTo true
            hasClassDeclaration { decl -> decl.name == "SampleParametrizedSuperClass" } shouldBeEqualTo true
            hasClassDeclarationOf(SampleInterface::class) shouldBeEqualTo false
            asObjectDeclaration() shouldBeEqualTo null
            hasObjectDeclaration() shouldBeEqualTo false
            hasObjectDeclaration { decl -> decl.name == "SampleParametrizedSuperClass" } shouldBeEqualTo false
            hasObjectDeclarationOf(SampleType::class) shouldBeEqualTo false
            asInterfaceDeclaration() shouldBeEqualTo null
            hasInterfaceDeclaration() shouldBeEqualTo false
            hasInterfaceDeclaration { decl -> decl.name == "SampleParametrizedSuperClass" } shouldBeEqualTo false
            hasInterfaceDeclarationOf(SampleType::class) shouldBeEqualTo false
            asTypeAliasDeclaration() shouldBeEqualTo null
            hasTypeAliasDeclaration() shouldBeEqualTo false
            hasTypeAliasDeclaration { decl -> decl.name == "SampleParametrizedSuperClass" } shouldBeEqualTo false
            asImportAliasDeclaration() shouldBeEqualTo null
            hasImportAliasDeclaration() shouldBeEqualTo false
            hasImportAliasDeclaration { decl -> decl.name == "SampleParametrizedSuperClass" } shouldBeEqualTo false
            asKotlinTypeDeclaration() shouldBeEqualTo null
            hasKotlinTypeDeclaration() shouldBeEqualTo false
            hasKotlinTypeDeclaration { decl -> decl.name == "SampleParametrizedSuperClass" } shouldBeEqualTo false
            hasKotlinTypeDeclarationOf(SampleType::class) shouldBeEqualTo false
            asKotlinBasicTypeDeclaration() shouldBeEqualTo null
            hasKotlinBasicTypeDeclaration() shouldBeEqualTo false
            hasKotlinBasicTypeDeclaration { decl -> decl.name == "SampleParametrizedSuperClass" } shouldBeEqualTo false
            hasKotlinBasicTypeDeclarationOf(SampleType::class) shouldBeEqualTo false
            asKotlinCollectionTypeDeclaration() shouldBeEqualTo null
            hasKotlinCollectionTypeDeclaration() shouldBeEqualTo false
            hasKotlinCollectionTypeDeclaration { decl -> decl.name == "SampleParametrizedSuperClass" } shouldBeEqualTo false
            hasKotlinCollectionTypeDeclarationOf(SampleType::class) shouldBeEqualTo false
            asExternalDeclaration() shouldBeEqualTo null
            hasExternalDeclaration() shouldBeEqualTo false
            hasExternalDeclaration { decl -> decl.name == "SampleParametrizedSuperClass" } shouldBeEqualTo false
            asTypeParameterDeclaration() shouldBeEqualTo null
            hasTypeParameterDeclaration() shouldBeEqualTo false
            hasTypeParameterDeclaration { decl -> decl.name == "SampleParametrizedSuperClass" } shouldBeEqualTo false
        }
    }

    @Test
    fun `class-with-parametrized-and-generic-parent-class-from-file`() {
        // given
        val sut =
            getSnippetFile("class-with-parametrized-and-generic-parent-class-from-file")
                .classes()
                .parents()
                .first()

        // then
        assertSoftly(sut) {
            isClass shouldBeEqualTo true
            isObject shouldBeEqualTo false
            isInterface shouldBeEqualTo false
            isTypeAlias shouldBeEqualTo false
            isImportAlias shouldBeEqualTo false
            isKotlinType shouldBeEqualTo false
            isKotlinBasicType shouldBeEqualTo false
            isKotlinCollectionType shouldBeEqualTo false
            isTypeParameter shouldBeEqualTo false
            isExternal shouldBeEqualTo false
            asClassDeclaration() shouldBeInstanceOf KoClassDeclaration::class
            hasClassDeclaration() shouldBeEqualTo true
            hasClassDeclaration { decl -> decl.name == "SampleParametrizedSuperClass" } shouldBeEqualTo true
            hasClassDeclarationOf(SampleInterface::class) shouldBeEqualTo false
            asObjectDeclaration() shouldBeEqualTo null
            hasObjectDeclaration() shouldBeEqualTo false
            hasObjectDeclaration { decl -> decl.name == "SampleParametrizedSuperClass" } shouldBeEqualTo false
            hasObjectDeclarationOf(SampleType::class) shouldBeEqualTo false
            asInterfaceDeclaration() shouldBeEqualTo null
            hasInterfaceDeclaration() shouldBeEqualTo false
            hasInterfaceDeclaration { decl -> decl.name == "SampleParametrizedSuperClass" } shouldBeEqualTo false
            hasInterfaceDeclarationOf(SampleType::class) shouldBeEqualTo false
            asTypeAliasDeclaration() shouldBeEqualTo null
            hasTypeAliasDeclaration() shouldBeEqualTo false
            hasTypeAliasDeclaration { decl -> decl.name == "SampleParametrizedSuperClass" } shouldBeEqualTo false
            asImportAliasDeclaration() shouldBeEqualTo null
            hasImportAliasDeclaration() shouldBeEqualTo false
            hasImportAliasDeclaration { decl -> decl.name == "SampleParametrizedSuperClass" } shouldBeEqualTo false
            asKotlinTypeDeclaration() shouldBeEqualTo null
            hasKotlinTypeDeclaration() shouldBeEqualTo false
            hasKotlinTypeDeclaration { decl -> decl.name == "SampleParametrizedSuperClass" } shouldBeEqualTo false
            hasKotlinTypeDeclarationOf(SampleType::class) shouldBeEqualTo false
            asKotlinBasicTypeDeclaration() shouldBeEqualTo null
            hasKotlinBasicTypeDeclaration() shouldBeEqualTo false
            hasKotlinBasicTypeDeclaration { decl -> decl.name == "SampleParametrizedSuperClass" } shouldBeEqualTo false
            hasKotlinBasicTypeDeclarationOf(SampleType::class) shouldBeEqualTo false
            asKotlinCollectionTypeDeclaration() shouldBeEqualTo null
            hasKotlinCollectionTypeDeclaration() shouldBeEqualTo false
            hasKotlinCollectionTypeDeclaration { decl -> decl.name == "SampleParametrizedSuperClass" } shouldBeEqualTo false
            hasKotlinCollectionTypeDeclarationOf(SampleType::class) shouldBeEqualTo false
            asExternalDeclaration() shouldBeEqualTo null
            hasExternalDeclaration() shouldBeEqualTo false
            hasExternalDeclaration { decl -> decl.name == "SampleParametrizedSuperClass" } shouldBeEqualTo false
            asTypeParameterDeclaration() shouldBeEqualTo null
            hasTypeParameterDeclaration() shouldBeEqualTo false
            hasTypeParameterDeclaration { decl -> decl.name == "SampleParametrizedSuperClass" } shouldBeEqualTo false
        }
    }

    @Test
    fun `class-with-parent-interface-from-file`() {
        // given
        val sut =
            getSnippetFile("class-with-parent-interface-from-file")
                .classes()
                .parents()
                .first()

        // then
        assertSoftly(sut) {
            isClass shouldBeEqualTo false
            isObject shouldBeEqualTo false
            isInterface shouldBeEqualTo true
            isTypeAlias shouldBeEqualTo false
            isImportAlias shouldBeEqualTo false
            isKotlinType shouldBeEqualTo false
            isKotlinBasicType shouldBeEqualTo false
            isKotlinCollectionType shouldBeEqualTo false
            isTypeParameter shouldBeEqualTo false
            isExternal shouldBeEqualTo false
            asClassDeclaration() shouldBeEqualTo null
            hasClassDeclaration() shouldBeEqualTo false
            hasClassDeclaration { decl -> decl.name == "SampleSuperInterface" } shouldBeEqualTo false
            hasClassDeclarationOf(SampleInterface::class) shouldBeEqualTo false
            asObjectDeclaration() shouldBeEqualTo null
            hasObjectDeclaration() shouldBeEqualTo false
            hasObjectDeclaration { decl -> decl.name == "SampleSuperInterface" } shouldBeEqualTo false
            hasObjectDeclarationOf(SampleType::class) shouldBeEqualTo false
            asInterfaceDeclaration() shouldBeInstanceOf KoInterfaceDeclaration::class
            hasInterfaceDeclaration() shouldBeEqualTo true
            hasInterfaceDeclaration { decl -> decl.name == "SampleSuperInterface" } shouldBeEqualTo true
            hasInterfaceDeclarationOf(SampleType::class) shouldBeEqualTo false
            asTypeAliasDeclaration() shouldBeEqualTo null
            hasTypeAliasDeclaration() shouldBeEqualTo false
            hasTypeAliasDeclaration { decl -> decl.name == "SampleSuperInterface" } shouldBeEqualTo false
            asImportAliasDeclaration() shouldBeEqualTo null
            hasImportAliasDeclaration() shouldBeEqualTo false
            hasImportAliasDeclaration { decl -> decl.name == "SampleSuperInterface" } shouldBeEqualTo false
            asKotlinTypeDeclaration() shouldBeEqualTo null
            hasKotlinTypeDeclaration() shouldBeEqualTo false
            hasKotlinTypeDeclaration { decl -> decl.name == "SampleSuperInterface" } shouldBeEqualTo false
            hasKotlinTypeDeclarationOf(SampleType::class) shouldBeEqualTo false
            asKotlinBasicTypeDeclaration() shouldBeEqualTo null
            hasKotlinBasicTypeDeclaration() shouldBeEqualTo false
            hasKotlinBasicTypeDeclaration { decl -> decl.name == "SampleSuperInterface" } shouldBeEqualTo false
            hasKotlinBasicTypeDeclarationOf(SampleType::class) shouldBeEqualTo false
            asKotlinCollectionTypeDeclaration() shouldBeEqualTo null
            hasKotlinCollectionTypeDeclaration() shouldBeEqualTo false
            hasKotlinCollectionTypeDeclaration { decl -> decl.name == "SampleSuperInterface" } shouldBeEqualTo false
            hasKotlinCollectionTypeDeclarationOf(SampleType::class) shouldBeEqualTo false
            asExternalDeclaration() shouldBeEqualTo null
            hasExternalDeclaration() shouldBeEqualTo false
            hasExternalDeclaration { decl -> decl.name == "SampleSuperInterface" } shouldBeEqualTo false
            asTypeParameterDeclaration() shouldBeEqualTo null
            hasTypeParameterDeclaration() shouldBeEqualTo false
            hasTypeParameterDeclaration { decl -> decl.name == "SampleSuperInterface" } shouldBeEqualTo false
        }
    }

    @Test
    fun `class-with-generic-parent-interface-from-file`() {
        // given
        val sut =
            getSnippetFile("class-with-generic-parent-interface-from-file")
                .classes()
                .parents()
                .first()

        // then
        assertSoftly(sut) {
            isClass shouldBeEqualTo false
            isObject shouldBeEqualTo false
            isInterface shouldBeEqualTo true
            isTypeAlias shouldBeEqualTo false
            isImportAlias shouldBeEqualTo false
            isKotlinType shouldBeEqualTo false
            isKotlinBasicType shouldBeEqualTo false
            isKotlinCollectionType shouldBeEqualTo false
            isTypeParameter shouldBeEqualTo false
            isExternal shouldBeEqualTo false
            asClassDeclaration() shouldBeEqualTo null
            hasClassDeclaration() shouldBeEqualTo false
            hasClassDeclaration { decl -> decl.name == "SampleGenericSuperInterface" } shouldBeEqualTo false
            hasClassDeclarationOf(SampleInterface::class) shouldBeEqualTo false
            asObjectDeclaration() shouldBeEqualTo null
            hasObjectDeclaration() shouldBeEqualTo false
            hasObjectDeclaration { decl -> decl.name == "SampleGenericSuperInterface" } shouldBeEqualTo false
            hasObjectDeclarationOf(SampleType::class) shouldBeEqualTo false
            asInterfaceDeclaration() shouldBeInstanceOf KoInterfaceDeclaration::class
            hasInterfaceDeclaration() shouldBeEqualTo true
            hasInterfaceDeclaration { decl -> decl.name == "SampleGenericSuperInterface" } shouldBeEqualTo true
            hasInterfaceDeclarationOf(SampleType::class) shouldBeEqualTo false
            asTypeAliasDeclaration() shouldBeEqualTo null
            hasTypeAliasDeclaration() shouldBeEqualTo false
            hasTypeAliasDeclaration { decl -> decl.name == "SampleGenericSuperInterface" } shouldBeEqualTo false
            asImportAliasDeclaration() shouldBeEqualTo null
            hasImportAliasDeclaration() shouldBeEqualTo false
            hasImportAliasDeclaration { decl -> decl.name == "SampleGenericSuperInterface" } shouldBeEqualTo false
            asKotlinTypeDeclaration() shouldBeEqualTo null
            hasKotlinTypeDeclaration() shouldBeEqualTo false
            hasKotlinTypeDeclaration { decl -> decl.name == "SampleGenericSuperInterface" } shouldBeEqualTo false
            hasKotlinTypeDeclarationOf(SampleType::class) shouldBeEqualTo false
            asKotlinBasicTypeDeclaration() shouldBeEqualTo null
            hasKotlinBasicTypeDeclaration() shouldBeEqualTo false
            hasKotlinBasicTypeDeclaration { decl -> decl.name == "SampleGenericSuperInterface" } shouldBeEqualTo false
            hasKotlinBasicTypeDeclarationOf(SampleType::class) shouldBeEqualTo false
            asKotlinCollectionTypeDeclaration() shouldBeEqualTo null
            hasKotlinCollectionTypeDeclaration() shouldBeEqualTo false
            hasKotlinCollectionTypeDeclaration { decl -> decl.name == "SampleGenericSuperInterface" } shouldBeEqualTo false
            hasKotlinCollectionTypeDeclarationOf(SampleType::class) shouldBeEqualTo false
            asExternalDeclaration() shouldBeEqualTo null
            hasExternalDeclaration() shouldBeEqualTo false
            hasExternalDeclaration { decl -> decl.name == "SampleGenericSuperInterface" } shouldBeEqualTo false
            asTypeParameterDeclaration() shouldBeEqualTo null
            hasTypeParameterDeclaration() shouldBeEqualTo false
            hasTypeParameterDeclaration { decl -> decl.name == "SampleGenericSuperInterface" } shouldBeEqualTo false
        }
    }

    @Test
    fun `class-with-parent-by-delegation-from-file`() {
        // given
        val sut =
            getSnippetFile("class-with-parent-by-delegation-from-file")
                .classes()
                .parents()
                .first()

        // then
        assertSoftly(sut) {
            isClass shouldBeEqualTo false
            isObject shouldBeEqualTo false
            isInterface shouldBeEqualTo true
            isTypeAlias shouldBeEqualTo false
            isImportAlias shouldBeEqualTo false
            isKotlinType shouldBeEqualTo false
            isKotlinBasicType shouldBeEqualTo false
            isKotlinCollectionType shouldBeEqualTo false
            isTypeParameter shouldBeEqualTo false
            isExternal shouldBeEqualTo false
            asClassDeclaration() shouldBeEqualTo null
            hasClassDeclaration() shouldBeEqualTo false
            hasClassDeclaration { decl -> decl.name == "SampleSuperInterface" } shouldBeEqualTo false
            hasClassDeclarationOf(SampleInterface::class) shouldBeEqualTo false
            asObjectDeclaration() shouldBeEqualTo null
            hasObjectDeclaration() shouldBeEqualTo false
            hasObjectDeclaration { decl -> decl.name == "SampleSuperInterface" } shouldBeEqualTo false
            hasObjectDeclarationOf(SampleType::class) shouldBeEqualTo false
            asInterfaceDeclaration() shouldBeInstanceOf KoInterfaceDeclaration::class
            hasInterfaceDeclaration() shouldBeEqualTo true
            hasInterfaceDeclaration { decl -> decl.name == "SampleSuperInterface" } shouldBeEqualTo true
            hasInterfaceDeclarationOf(SampleType::class) shouldBeEqualTo false
            asTypeAliasDeclaration() shouldBeEqualTo null
            hasTypeAliasDeclaration() shouldBeEqualTo false
            hasTypeAliasDeclaration { decl -> decl.name == "SampleSuperInterface" } shouldBeEqualTo false
            asImportAliasDeclaration() shouldBeEqualTo null
            hasImportAliasDeclaration() shouldBeEqualTo false
            hasImportAliasDeclaration { decl -> decl.name == "SampleSuperInterface" } shouldBeEqualTo false
            asKotlinTypeDeclaration() shouldBeEqualTo null
            hasKotlinTypeDeclaration() shouldBeEqualTo false
            hasKotlinTypeDeclaration { decl -> decl.name == "SampleSuperInterface" } shouldBeEqualTo false
            hasKotlinTypeDeclarationOf(SampleType::class) shouldBeEqualTo false
            asKotlinBasicTypeDeclaration() shouldBeEqualTo null
            hasKotlinBasicTypeDeclaration() shouldBeEqualTo false
            hasKotlinBasicTypeDeclaration { decl -> decl.name == "SampleSuperInterface" } shouldBeEqualTo false
            hasKotlinBasicTypeDeclarationOf(SampleType::class) shouldBeEqualTo false
            asKotlinCollectionTypeDeclaration() shouldBeEqualTo null
            hasKotlinCollectionTypeDeclaration() shouldBeEqualTo false
            hasKotlinCollectionTypeDeclaration { decl -> decl.name == "SampleSuperInterface" } shouldBeEqualTo false
            hasKotlinCollectionTypeDeclarationOf(SampleType::class) shouldBeEqualTo false
            asExternalDeclaration() shouldBeEqualTo null
            hasExternalDeclaration() shouldBeEqualTo false
            hasExternalDeclaration { decl -> decl.name == "SampleSuperInterface" } shouldBeEqualTo false
            asTypeParameterDeclaration() shouldBeEqualTo null
            hasTypeParameterDeclaration() shouldBeEqualTo false
            hasTypeParameterDeclaration { decl -> decl.name == "SampleSuperInterface" } shouldBeEqualTo false
        }
    }

    @Test
    fun `class-with-parent-class-from-import`() {
        // given
        val sut =
            getSnippetFile("class-with-parent-class-from-import")
                .classes()
                .parents()
                .first()

        // then
        assertSoftly(sut) {
            isClass shouldBeEqualTo true
            isObject shouldBeEqualTo false
            isInterface shouldBeEqualTo false
            isTypeAlias shouldBeEqualTo false
            isImportAlias shouldBeEqualTo false
            isKotlinType shouldBeEqualTo false
            isKotlinBasicType shouldBeEqualTo false
            isKotlinCollectionType shouldBeEqualTo false
            isTypeParameter shouldBeEqualTo false
            isExternal shouldBeEqualTo false
            asClassDeclaration() shouldBeInstanceOf KoClassDeclaration::class
            hasClassDeclaration() shouldBeEqualTo true
            hasClassDeclaration { decl -> decl.name == "SampleParentClass" } shouldBeEqualTo true
            hasClassDeclarationOf(SampleParentClass::class) shouldBeEqualTo true
            hasClassDeclarationOf(SampleClass::class) shouldBeEqualTo false
            hasClassDeclarationOf(SampleInterface::class) shouldBeEqualTo false
            asObjectDeclaration() shouldBeEqualTo null
            hasObjectDeclaration() shouldBeEqualTo false
            hasObjectDeclaration { decl -> decl.name == "SampleParentClass" } shouldBeEqualTo false
            hasObjectDeclarationOf(SampleParentClass::class) shouldBeEqualTo false
            hasObjectDeclarationOf(SampleType::class) shouldBeEqualTo false
            asInterfaceDeclaration() shouldBeEqualTo null
            hasInterfaceDeclaration() shouldBeEqualTo false
            hasInterfaceDeclaration { decl -> decl.name == "SampleParentClass" } shouldBeEqualTo false
            hasInterfaceDeclarationOf(SampleParentClass::class) shouldBeEqualTo false
            hasInterfaceDeclarationOf(SampleType::class) shouldBeEqualTo false
            asTypeAliasDeclaration() shouldBeEqualTo null
            hasTypeAliasDeclaration() shouldBeEqualTo false
            hasTypeAliasDeclaration { decl -> decl.name == "SampleParentClass" } shouldBeEqualTo false
            asImportAliasDeclaration() shouldBeEqualTo null
            hasImportAliasDeclaration() shouldBeEqualTo false
            hasImportAliasDeclaration { decl -> decl.name == "SampleParentClass" } shouldBeEqualTo false
            asKotlinTypeDeclaration() shouldBeEqualTo null
            hasKotlinTypeDeclaration() shouldBeEqualTo false
            hasKotlinTypeDeclaration { decl -> decl.name == "SampleParentClass" } shouldBeEqualTo false
            hasKotlinTypeDeclarationOf(SampleParentClass::class) shouldBeEqualTo false
            hasKotlinTypeDeclarationOf(SampleType::class) shouldBeEqualTo false
            asKotlinBasicTypeDeclaration() shouldBeEqualTo null
            hasKotlinBasicTypeDeclaration() shouldBeEqualTo false
            hasKotlinBasicTypeDeclaration { decl -> decl.name == "SampleParentClass" } shouldBeEqualTo false
            hasKotlinBasicTypeDeclarationOf(SampleParentClass::class) shouldBeEqualTo false
            hasKotlinBasicTypeDeclarationOf(SampleType::class) shouldBeEqualTo false
            asKotlinCollectionTypeDeclaration() shouldBeEqualTo null
            hasKotlinCollectionTypeDeclaration() shouldBeEqualTo false
            hasKotlinCollectionTypeDeclaration { decl -> decl.name == "SampleParentClass" } shouldBeEqualTo false
            hasKotlinCollectionTypeDeclarationOf(SampleParentClass::class) shouldBeEqualTo false
            hasKotlinCollectionTypeDeclarationOf(SampleType::class) shouldBeEqualTo false
            asExternalDeclaration() shouldBeEqualTo null
            hasExternalDeclaration() shouldBeEqualTo false
            hasExternalDeclaration { decl -> decl.name == "SampleParentClass" } shouldBeEqualTo false
            hasExternalDeclarationOf(SampleParentClass::class) shouldBeEqualTo false
            asTypeParameterDeclaration() shouldBeEqualTo null
            hasTypeParameterDeclaration() shouldBeEqualTo false
            hasTypeParameterDeclaration { decl -> decl.name == "SampleParentClass" } shouldBeEqualTo false
        }
    }

    @Test
    fun `class-with-generic-parent-class-from-import`() {
        // given
        val sut =
            getSnippetFile("class-with-generic-parent-class-from-import")
                .classes()
                .parents()
                .first()

        // then
        assertSoftly(sut) {
            isClass shouldBeEqualTo true
            isObject shouldBeEqualTo false
            isInterface shouldBeEqualTo false
            isTypeAlias shouldBeEqualTo false
            isImportAlias shouldBeEqualTo false
            isKotlinType shouldBeEqualTo false
            isKotlinBasicType shouldBeEqualTo false
            isKotlinCollectionType shouldBeEqualTo false
            isTypeParameter shouldBeEqualTo false
            isExternal shouldBeEqualTo false
            asClassDeclaration() shouldBeInstanceOf KoClassDeclaration::class
            hasClassDeclaration() shouldBeEqualTo true
            hasClassDeclaration { decl -> decl.name == "SampleCollection1" } shouldBeEqualTo true
            hasClassDeclarationOf(SampleCollection1::class) shouldBeEqualTo true
            hasClassDeclarationOf(SampleClass::class) shouldBeEqualTo false
            hasClassDeclarationOf(SampleInterface::class) shouldBeEqualTo false
            asObjectDeclaration() shouldBeEqualTo null
            hasObjectDeclaration() shouldBeEqualTo false
            hasObjectDeclaration { decl -> decl.name == "SampleCollection1" } shouldBeEqualTo false
            hasObjectDeclarationOf(SampleCollection1::class) shouldBeEqualTo false
            hasObjectDeclarationOf(SampleType::class) shouldBeEqualTo false
            asInterfaceDeclaration() shouldBeEqualTo null
            hasInterfaceDeclaration() shouldBeEqualTo false
            hasInterfaceDeclaration { decl -> decl.name == "SampleCollection1" } shouldBeEqualTo false
            hasInterfaceDeclarationOf(SampleCollection1::class) shouldBeEqualTo false
            hasInterfaceDeclarationOf(SampleType::class) shouldBeEqualTo false
            asTypeAliasDeclaration() shouldBeEqualTo null
            hasTypeAliasDeclaration() shouldBeEqualTo false
            hasTypeAliasDeclaration { decl -> decl.name == "SampleCollection1" } shouldBeEqualTo false
            asImportAliasDeclaration() shouldBeEqualTo null
            hasImportAliasDeclaration() shouldBeEqualTo false
            hasImportAliasDeclaration { decl -> decl.name == "SampleCollection1" } shouldBeEqualTo false
            asKotlinTypeDeclaration() shouldBeEqualTo null
            hasKotlinTypeDeclaration() shouldBeEqualTo false
            hasKotlinTypeDeclaration { decl -> decl.name == "SampleCollection1" } shouldBeEqualTo false
            hasKotlinTypeDeclarationOf(SampleCollection1::class) shouldBeEqualTo false
            hasKotlinTypeDeclarationOf(SampleType::class) shouldBeEqualTo false
            asKotlinBasicTypeDeclaration() shouldBeEqualTo null
            hasKotlinBasicTypeDeclaration() shouldBeEqualTo false
            hasKotlinBasicTypeDeclaration { decl -> decl.name == "SampleCollection1" } shouldBeEqualTo false
            hasKotlinBasicTypeDeclarationOf(SampleCollection1::class) shouldBeEqualTo false
            hasKotlinBasicTypeDeclarationOf(SampleType::class) shouldBeEqualTo false
            asKotlinCollectionTypeDeclaration() shouldBeEqualTo null
            hasKotlinCollectionTypeDeclaration() shouldBeEqualTo false
            hasKotlinCollectionTypeDeclaration { decl -> decl.name == "SampleCollection1" } shouldBeEqualTo false
            hasKotlinCollectionTypeDeclarationOf(SampleCollection1::class) shouldBeEqualTo false
            hasKotlinCollectionTypeDeclarationOf(SampleType::class) shouldBeEqualTo false
            asExternalDeclaration() shouldBeEqualTo null
            hasExternalDeclaration() shouldBeEqualTo false
            hasExternalDeclaration { decl -> decl.name == "SampleCollection1" } shouldBeEqualTo false
            hasExternalDeclarationOf(SampleCollection1::class) shouldBeEqualTo false
            asTypeParameterDeclaration() shouldBeEqualTo null
            hasTypeParameterDeclaration() shouldBeEqualTo false
            hasTypeParameterDeclaration { decl -> decl.name == "SampleCollection1" } shouldBeEqualTo false
        }
    }

    @Test
    fun `class-with-parametrized-parent-class-from-import`() {
        // given
        val sut =
            getSnippetFile("class-with-parametrized-parent-class-from-import")
                .classes()
                .parents()
                .first()

        // then
        assertSoftly(sut) {
            isClass shouldBeEqualTo true
            isObject shouldBeEqualTo false
            isInterface shouldBeEqualTo false
            isTypeAlias shouldBeEqualTo false
            isImportAlias shouldBeEqualTo false
            isKotlinType shouldBeEqualTo false
            isKotlinBasicType shouldBeEqualTo false
            isKotlinCollectionType shouldBeEqualTo false
            isTypeParameter shouldBeEqualTo false
            isExternal shouldBeEqualTo false
            asClassDeclaration() shouldBeInstanceOf KoClassDeclaration::class
            hasClassDeclaration() shouldBeEqualTo true
            hasClassDeclaration { decl -> decl.name == "SampleClassWithParameter" } shouldBeEqualTo true
            hasClassDeclarationOf(SampleClassWithParameter::class) shouldBeEqualTo true
            hasClassDeclarationOf(SampleClass::class) shouldBeEqualTo false
            hasClassDeclarationOf(SampleInterface::class) shouldBeEqualTo false
            asObjectDeclaration() shouldBeEqualTo null
            hasObjectDeclaration() shouldBeEqualTo false
            hasObjectDeclaration { decl -> decl.name == "SampleClassWithParameter" } shouldBeEqualTo false
            hasObjectDeclarationOf(SampleClassWithParameter::class) shouldBeEqualTo false
            hasObjectDeclarationOf(SampleType::class) shouldBeEqualTo false
            asInterfaceDeclaration() shouldBeEqualTo null
            hasInterfaceDeclaration() shouldBeEqualTo false
            hasInterfaceDeclaration { decl -> decl.name == "SampleClassWithParameter" } shouldBeEqualTo false
            hasInterfaceDeclarationOf(SampleClassWithParameter::class) shouldBeEqualTo false
            hasInterfaceDeclarationOf(SampleType::class) shouldBeEqualTo false
            asTypeAliasDeclaration() shouldBeEqualTo null
            hasTypeAliasDeclaration() shouldBeEqualTo false
            hasTypeAliasDeclaration { decl -> decl.name == "SampleClassWithParameter" } shouldBeEqualTo false
            asImportAliasDeclaration() shouldBeEqualTo null
            hasImportAliasDeclaration() shouldBeEqualTo false
            hasImportAliasDeclaration { decl -> decl.name == "SampleClassWithParameter" } shouldBeEqualTo false
            asKotlinTypeDeclaration() shouldBeEqualTo null
            hasKotlinTypeDeclaration() shouldBeEqualTo false
            hasKotlinTypeDeclaration { decl -> decl.name == "SampleClassWithParameter" } shouldBeEqualTo false
            hasKotlinTypeDeclarationOf(SampleClassWithParameter::class) shouldBeEqualTo false
            hasKotlinTypeDeclarationOf(SampleType::class) shouldBeEqualTo false
            asKotlinBasicTypeDeclaration() shouldBeEqualTo null
            hasKotlinBasicTypeDeclaration() shouldBeEqualTo false
            hasKotlinBasicTypeDeclaration { decl -> decl.name == "SampleClassWithParameter" } shouldBeEqualTo false
            hasKotlinBasicTypeDeclarationOf(SampleClassWithParameter::class) shouldBeEqualTo false
            hasKotlinBasicTypeDeclarationOf(SampleType::class) shouldBeEqualTo false
            asKotlinCollectionTypeDeclaration() shouldBeEqualTo null
            hasKotlinCollectionTypeDeclaration() shouldBeEqualTo false
            hasKotlinCollectionTypeDeclaration { decl -> decl.name == "SampleClassWithParameter" } shouldBeEqualTo false
            hasKotlinCollectionTypeDeclarationOf(SampleClassWithParameter::class) shouldBeEqualTo false
            hasKotlinCollectionTypeDeclarationOf(SampleType::class) shouldBeEqualTo false
            asExternalDeclaration() shouldBeEqualTo null
            hasExternalDeclaration() shouldBeEqualTo false
            hasExternalDeclaration { decl -> decl.name == "SampleClassWithParameter" } shouldBeEqualTo false
            hasExternalDeclarationOf(SampleClassWithParameter::class) shouldBeEqualTo false
            asTypeParameterDeclaration() shouldBeEqualTo null
            hasTypeParameterDeclaration() shouldBeEqualTo false
            hasTypeParameterDeclaration { decl -> decl.name == "SampleClassWithParameter" } shouldBeEqualTo false
        }
    }

    @Test
    fun `class-with-parametrized-and-generic-parent-class-from-import`() {
        // given
        val sut =
            getSnippetFile("class-with-parametrized-and-generic-parent-class-from-import")
                .classes()
                .parents()
                .first()

        // then
        assertSoftly(sut) {
            isClass shouldBeEqualTo true
            isObject shouldBeEqualTo false
            isInterface shouldBeEqualTo false
            isTypeAlias shouldBeEqualTo false
            isImportAlias shouldBeEqualTo false
            isKotlinType shouldBeEqualTo false
            isKotlinBasicType shouldBeEqualTo false
            isKotlinCollectionType shouldBeEqualTo false
            isTypeParameter shouldBeEqualTo false
            isExternal shouldBeEqualTo false
            asClassDeclaration() shouldBeInstanceOf KoClassDeclaration::class
            hasClassDeclaration() shouldBeEqualTo true
            hasClassDeclaration { decl -> decl.name == "SampleGenericClassWithParameter" } shouldBeEqualTo true
            hasClassDeclarationOf(SampleGenericClassWithParameter::class) shouldBeEqualTo true
            hasClassDeclarationOf(SampleClass::class) shouldBeEqualTo false
            hasClassDeclarationOf(SampleInterface::class) shouldBeEqualTo false
            asObjectDeclaration() shouldBeEqualTo null
            hasObjectDeclaration() shouldBeEqualTo false
            hasObjectDeclaration { decl -> decl.name == "SampleGenericClassWithParameter" } shouldBeEqualTo false
            hasObjectDeclarationOf(SampleGenericClassWithParameter::class) shouldBeEqualTo false
            hasObjectDeclarationOf(SampleType::class) shouldBeEqualTo false
            asInterfaceDeclaration() shouldBeEqualTo null
            hasInterfaceDeclaration() shouldBeEqualTo false
            hasInterfaceDeclaration { decl -> decl.name == "SampleGenericClassWithParameter" } shouldBeEqualTo false
            hasInterfaceDeclarationOf(SampleGenericClassWithParameter::class) shouldBeEqualTo false
            hasInterfaceDeclarationOf(SampleType::class) shouldBeEqualTo false
            asTypeAliasDeclaration() shouldBeEqualTo null
            hasTypeAliasDeclaration() shouldBeEqualTo false
            hasTypeAliasDeclaration { decl -> decl.name == "SampleGenericClassWithParameter" } shouldBeEqualTo false
            asImportAliasDeclaration() shouldBeEqualTo null
            hasImportAliasDeclaration() shouldBeEqualTo false
            hasImportAliasDeclaration { decl -> decl.name == "SampleGenericClassWithParameter" } shouldBeEqualTo false
            asKotlinTypeDeclaration() shouldBeEqualTo null
            hasKotlinTypeDeclaration() shouldBeEqualTo false
            hasKotlinTypeDeclaration { decl -> decl.name == "SampleGenericClassWithParameter" } shouldBeEqualTo false
            hasKotlinTypeDeclarationOf(SampleGenericClassWithParameter::class) shouldBeEqualTo false
            hasKotlinTypeDeclarationOf(SampleType::class) shouldBeEqualTo false
            asKotlinBasicTypeDeclaration() shouldBeEqualTo null
            hasKotlinBasicTypeDeclaration() shouldBeEqualTo false
            hasKotlinBasicTypeDeclaration { decl -> decl.name == "SampleGenericClassWithParameter" } shouldBeEqualTo false
            hasKotlinBasicTypeDeclarationOf(SampleGenericClassWithParameter::class) shouldBeEqualTo false
            hasKotlinBasicTypeDeclarationOf(SampleType::class) shouldBeEqualTo false
            asKotlinCollectionTypeDeclaration() shouldBeEqualTo null
            hasKotlinCollectionTypeDeclaration() shouldBeEqualTo false
            hasKotlinCollectionTypeDeclaration { decl -> decl.name == "SampleGenericClassWithParameter" } shouldBeEqualTo false
            hasKotlinCollectionTypeDeclarationOf(SampleGenericClassWithParameter::class) shouldBeEqualTo false
            hasKotlinCollectionTypeDeclarationOf(SampleType::class) shouldBeEqualTo false
            asExternalDeclaration() shouldBeEqualTo null
            hasExternalDeclaration() shouldBeEqualTo false
            hasExternalDeclaration { decl -> decl.name == "SampleGenericClassWithParameter" } shouldBeEqualTo false
            hasExternalDeclarationOf(SampleGenericClassWithParameter::class) shouldBeEqualTo false
            asTypeParameterDeclaration() shouldBeEqualTo null
            hasTypeParameterDeclaration() shouldBeEqualTo false
            hasTypeParameterDeclaration { decl -> decl.name == "SampleGenericClassWithParameter" } shouldBeEqualTo false
        }
    }

    @Test
    fun `class-with-parent-interface-from-import`() {
        // given
        val sut =
            getSnippetFile("class-with-parent-interface-from-import")
                .classes()
                .parents()
                .first()

        // then
        assertSoftly(sut) {
            isClass shouldBeEqualTo false
            isObject shouldBeEqualTo false
            isInterface shouldBeEqualTo true
            isTypeAlias shouldBeEqualTo false
            isImportAlias shouldBeEqualTo false
            isKotlinType shouldBeEqualTo false
            isKotlinBasicType shouldBeEqualTo false
            isKotlinCollectionType shouldBeEqualTo false
            isTypeParameter shouldBeEqualTo false
            isExternal shouldBeEqualTo false
            asClassDeclaration() shouldBeEqualTo null
            hasClassDeclaration() shouldBeEqualTo false
            hasClassDeclaration { decl -> decl.name == "SampleInterface" } shouldBeEqualTo false
            hasClassDeclarationOf(SampleInterface::class) shouldBeEqualTo false
            asObjectDeclaration() shouldBeEqualTo null
            hasObjectDeclaration() shouldBeEqualTo false
            hasObjectDeclaration { decl -> decl.name == "SampleInterface" } shouldBeEqualTo false
            hasObjectDeclarationOf(SampleInterface::class) shouldBeEqualTo false
            asInterfaceDeclaration() shouldBeInstanceOf KoInterfaceDeclaration::class
            hasInterfaceDeclaration() shouldBeEqualTo true
            hasInterfaceDeclaration { decl -> decl.name == "SampleInterface" } shouldBeEqualTo true
            hasInterfaceDeclarationOf(SampleInterface::class) shouldBeEqualTo true
            asTypeAliasDeclaration() shouldBeEqualTo null
            hasTypeAliasDeclaration() shouldBeEqualTo false
            hasTypeAliasDeclaration { decl -> decl.name == "SampleInterface" } shouldBeEqualTo false
            asImportAliasDeclaration() shouldBeEqualTo null
            hasImportAliasDeclaration() shouldBeEqualTo false
            hasImportAliasDeclaration { decl -> decl.name == "SampleInterface" } shouldBeEqualTo false
            asKotlinTypeDeclaration() shouldBeEqualTo null
            hasKotlinTypeDeclaration() shouldBeEqualTo false
            hasKotlinTypeDeclaration { decl -> decl.name == "SampleInterface" } shouldBeEqualTo false
            hasKotlinTypeDeclarationOf(SampleInterface::class) shouldBeEqualTo false
            asKotlinBasicTypeDeclaration() shouldBeEqualTo null
            hasKotlinBasicTypeDeclaration() shouldBeEqualTo false
            hasKotlinBasicTypeDeclaration { decl -> decl.name == "SampleInterface" } shouldBeEqualTo false
            hasKotlinBasicTypeDeclarationOf(SampleInterface::class) shouldBeEqualTo false
            asKotlinCollectionTypeDeclaration() shouldBeEqualTo null
            hasKotlinCollectionTypeDeclaration() shouldBeEqualTo false
            hasKotlinCollectionTypeDeclaration { decl -> decl.name == "SampleInterface" } shouldBeEqualTo false
            hasKotlinCollectionTypeDeclarationOf(SampleInterface::class) shouldBeEqualTo false
            asExternalDeclaration() shouldBeEqualTo null
            hasExternalDeclaration() shouldBeEqualTo false
            hasExternalDeclaration { decl -> decl.name == "SampleInterface" } shouldBeEqualTo false
            asTypeParameterDeclaration() shouldBeEqualTo null
            hasTypeParameterDeclaration() shouldBeEqualTo false
            hasTypeParameterDeclaration { decl -> decl.name == "SampleInterface" } shouldBeEqualTo false
        }
    }

    @Test
    fun `class-with-generic-parent-interface-from-import`() {
        // given
        val sut =
            getSnippetFile("class-with-generic-parent-interface-from-import")
                .classes()
                .parents()
                .first()

        // then
        assertSoftly(sut) {
            isClass shouldBeEqualTo false
            isObject shouldBeEqualTo false
            isInterface shouldBeEqualTo true
            isTypeAlias shouldBeEqualTo false
            isImportAlias shouldBeEqualTo false
            isKotlinType shouldBeEqualTo false
            isKotlinBasicType shouldBeEqualTo false
            isKotlinCollectionType shouldBeEqualTo false
            isTypeParameter shouldBeEqualTo false
            isExternal shouldBeEqualTo false
            asClassDeclaration() shouldBeEqualTo null
            hasClassDeclaration() shouldBeEqualTo false
            hasClassDeclaration { decl -> decl.name == "SampleGenericSuperInterface" } shouldBeEqualTo false
            hasClassDeclarationOf(SampleGenericSuperInterface::class) shouldBeEqualTo false
            asObjectDeclaration() shouldBeEqualTo null
            hasObjectDeclaration() shouldBeEqualTo false
            hasObjectDeclaration { decl -> decl.name == "SampleGenericSuperInterface" } shouldBeEqualTo false
            hasObjectDeclarationOf(SampleGenericSuperInterface::class) shouldBeEqualTo false
            asInterfaceDeclaration() shouldBeInstanceOf KoInterfaceDeclaration::class
            hasInterfaceDeclaration() shouldBeEqualTo true
            hasInterfaceDeclaration { decl -> decl.name == "SampleGenericSuperInterface" } shouldBeEqualTo true
            hasInterfaceDeclarationOf(SampleGenericSuperInterface::class) shouldBeEqualTo true
            asTypeAliasDeclaration() shouldBeEqualTo null
            hasTypeAliasDeclaration() shouldBeEqualTo false
            hasTypeAliasDeclaration { decl -> decl.name == "SampleGenericSuperInterface" } shouldBeEqualTo false
            asImportAliasDeclaration() shouldBeEqualTo null
            hasImportAliasDeclaration() shouldBeEqualTo false
            hasImportAliasDeclaration { decl -> decl.name == "SampleGenericSuperInterface" } shouldBeEqualTo false
            asKotlinTypeDeclaration() shouldBeEqualTo null
            hasKotlinTypeDeclaration() shouldBeEqualTo false
            hasKotlinTypeDeclaration { decl -> decl.name == "SampleGenericSuperInterface" } shouldBeEqualTo false
            hasKotlinTypeDeclarationOf(SampleGenericSuperInterface::class) shouldBeEqualTo false
            asKotlinBasicTypeDeclaration() shouldBeEqualTo null
            hasKotlinBasicTypeDeclaration() shouldBeEqualTo false
            hasKotlinBasicTypeDeclaration { decl -> decl.name == "SampleGenericSuperInterface" } shouldBeEqualTo false
            hasKotlinBasicTypeDeclarationOf(SampleGenericSuperInterface::class) shouldBeEqualTo false
            asKotlinCollectionTypeDeclaration() shouldBeEqualTo null
            hasKotlinCollectionTypeDeclaration() shouldBeEqualTo false
            hasKotlinCollectionTypeDeclaration { decl -> decl.name == "SampleGenericSuperInterface" } shouldBeEqualTo false
            hasKotlinCollectionTypeDeclarationOf(SampleGenericSuperInterface::class) shouldBeEqualTo false
            asExternalDeclaration() shouldBeEqualTo null
            hasExternalDeclaration() shouldBeEqualTo false
            hasExternalDeclaration { decl -> decl.name == "SampleGenericSuperInterface" } shouldBeEqualTo false
            asTypeParameterDeclaration() shouldBeEqualTo null
            hasTypeParameterDeclaration() shouldBeEqualTo false
            hasTypeParameterDeclaration { decl -> decl.name == "SampleGenericSuperInterface" } shouldBeEqualTo false
        }
    }

    @Test
    fun `class-with-parent-by-delegation-from-import`() {
        // given
        val sut =
            getSnippetFile("class-with-parent-by-delegation-from-import")
                .classes()
                .parents()
                .first()

        // then
        assertSoftly(sut) {
            isClass shouldBeEqualTo false
            isObject shouldBeEqualTo false
            isInterface shouldBeEqualTo true
            isTypeAlias shouldBeEqualTo false
            isImportAlias shouldBeEqualTo false
            isKotlinType shouldBeEqualTo false
            isKotlinBasicType shouldBeEqualTo false
            isKotlinCollectionType shouldBeEqualTo false
            isTypeParameter shouldBeEqualTo false
            isExternal shouldBeEqualTo false
            asClassDeclaration() shouldBeEqualTo null
            hasClassDeclaration() shouldBeEqualTo false
            hasClassDeclaration { decl -> decl.name == "SampleInterface" } shouldBeEqualTo false
            hasClassDeclarationOf(SampleInterface::class) shouldBeEqualTo false
            asObjectDeclaration() shouldBeEqualTo null
            hasObjectDeclaration() shouldBeEqualTo false
            hasObjectDeclaration { decl -> decl.name == "SampleInterface" } shouldBeEqualTo false
            hasObjectDeclarationOf(SampleInterface::class) shouldBeEqualTo false
            asInterfaceDeclaration() shouldBeInstanceOf KoInterfaceDeclaration::class
            hasInterfaceDeclaration() shouldBeEqualTo true
            hasInterfaceDeclaration { decl -> decl.name == "SampleInterface" } shouldBeEqualTo true
            hasInterfaceDeclarationOf(SampleInterface::class) shouldBeEqualTo true
            asTypeAliasDeclaration() shouldBeEqualTo null
            hasTypeAliasDeclaration() shouldBeEqualTo false
            hasTypeAliasDeclaration { decl -> decl.name == "SampleInterface" } shouldBeEqualTo false
            asImportAliasDeclaration() shouldBeEqualTo null
            hasImportAliasDeclaration() shouldBeEqualTo false
            hasImportAliasDeclaration { decl -> decl.name == "SampleInterface" } shouldBeEqualTo false
            asKotlinTypeDeclaration() shouldBeEqualTo null
            hasKotlinTypeDeclaration() shouldBeEqualTo false
            hasKotlinTypeDeclaration { decl -> decl.name == "SampleInterface" } shouldBeEqualTo false
            hasKotlinTypeDeclarationOf(SampleInterface::class) shouldBeEqualTo false
            asKotlinBasicTypeDeclaration() shouldBeEqualTo null
            hasKotlinBasicTypeDeclaration() shouldBeEqualTo false
            hasKotlinBasicTypeDeclaration { decl -> decl.name == "SampleInterface" } shouldBeEqualTo false
            hasKotlinBasicTypeDeclarationOf(SampleInterface::class) shouldBeEqualTo false
            asKotlinCollectionTypeDeclaration() shouldBeEqualTo null
            hasKotlinCollectionTypeDeclaration() shouldBeEqualTo false
            hasKotlinCollectionTypeDeclaration { decl -> decl.name == "SampleInterface" } shouldBeEqualTo false
            hasKotlinCollectionTypeDeclarationOf(SampleInterface::class) shouldBeEqualTo false
            asExternalDeclaration() shouldBeEqualTo null
            hasExternalDeclaration() shouldBeEqualTo false
            hasExternalDeclaration { decl -> decl.name == "SampleInterface" } shouldBeEqualTo false
            asTypeParameterDeclaration() shouldBeEqualTo null
            hasTypeParameterDeclaration() shouldBeEqualTo false
            hasTypeParameterDeclaration { decl -> decl.name == "SampleInterface" } shouldBeEqualTo false
        }
    }

    @Test
    fun `class-with-external-parent-class`() {
        // given
        val sut =
            getSnippetFile("class-with-external-parent-class")
                .classes()
                .parents()
                .first()

        // then
        assertSoftly(sut) {
            isClass shouldBeEqualTo false
            isObject shouldBeEqualTo false
            isInterface shouldBeEqualTo false
            isTypeAlias shouldBeEqualTo false
            isImportAlias shouldBeEqualTo false
            isKotlinType shouldBeEqualTo false
            isKotlinBasicType shouldBeEqualTo false
            isKotlinCollectionType shouldBeEqualTo false
            isTypeParameter shouldBeEqualTo false
            isExternal shouldBeEqualTo true
            asClassDeclaration() shouldBeEqualTo null
            hasClassDeclaration() shouldBeEqualTo false
            hasClassDeclaration { decl -> decl.name == "SampleExternalClass" } shouldBeEqualTo false
            hasClassDeclarationOf(SampleExternalClass::class) shouldBeEqualTo false
            asObjectDeclaration() shouldBeEqualTo null
            hasObjectDeclaration() shouldBeEqualTo false
            hasObjectDeclaration { decl -> decl.name == "SampleExternalClass" } shouldBeEqualTo false
            hasObjectDeclarationOf(SampleExternalClass::class) shouldBeEqualTo false
            asInterfaceDeclaration() shouldBeEqualTo null
            hasInterfaceDeclaration() shouldBeEqualTo false
            hasInterfaceDeclaration { decl -> decl.name == "SampleExternalClass" } shouldBeEqualTo false
            hasInterfaceDeclarationOf(SampleExternalClass::class) shouldBeEqualTo false
            asTypeAliasDeclaration() shouldBeEqualTo null
            hasTypeAliasDeclaration() shouldBeEqualTo false
            hasTypeAliasDeclaration { decl -> decl.name == "SampleExternalClass" } shouldBeEqualTo false
            asImportAliasDeclaration() shouldBeEqualTo null
            hasImportAliasDeclaration() shouldBeEqualTo false
            hasImportAliasDeclaration { decl -> decl.name == "SampleExternalClass" } shouldBeEqualTo false
            asKotlinTypeDeclaration() shouldBeEqualTo null
            hasKotlinTypeDeclaration() shouldBeEqualTo false
            hasKotlinTypeDeclaration { decl -> decl.name == "SampleExternalClass" } shouldBeEqualTo false
            hasKotlinTypeDeclarationOf(SampleExternalClass::class) shouldBeEqualTo false
            asKotlinBasicTypeDeclaration() shouldBeEqualTo null
            hasKotlinBasicTypeDeclaration() shouldBeEqualTo false
            hasKotlinBasicTypeDeclaration { decl -> decl.name == "SampleExternalClass" } shouldBeEqualTo false
            hasKotlinBasicTypeDeclarationOf(SampleExternalClass::class) shouldBeEqualTo false
            asKotlinCollectionTypeDeclaration() shouldBeEqualTo null
            hasKotlinCollectionTypeDeclaration() shouldBeEqualTo false
            hasKotlinCollectionTypeDeclaration { decl -> decl.name == "SampleExternalClass" } shouldBeEqualTo false
            hasKotlinCollectionTypeDeclarationOf(SampleExternalClass::class) shouldBeEqualTo false
            asExternalDeclaration() shouldBeInstanceOf KoExternalDeclaration::class
            hasExternalDeclaration() shouldBeEqualTo true
            hasExternalDeclaration { decl -> decl.name == "SampleExternalClass" } shouldBeEqualTo true
            hasExternalDeclarationOf(SampleExternalClass::class) shouldBeEqualTo true
            asTypeParameterDeclaration() shouldBeEqualTo null
            hasTypeParameterDeclaration() shouldBeEqualTo false
            hasTypeParameterDeclaration { decl -> decl.name == "SampleExternalClass" } shouldBeEqualTo false
        }
    }

    @Test
    fun `class-with-generic-external-parent-class`() {
        // given
        val sut =
            getSnippetFile("class-with-generic-external-parent-class")
                .classes()
                .parents()
                .first()

        // then
        assertSoftly(sut) {
            isClass shouldBeEqualTo false
            isObject shouldBeEqualTo false
            isInterface shouldBeEqualTo false
            isTypeAlias shouldBeEqualTo false
            isImportAlias shouldBeEqualTo false
            isKotlinType shouldBeEqualTo false
            isKotlinBasicType shouldBeEqualTo false
            isKotlinCollectionType shouldBeEqualTo false
            isTypeParameter shouldBeEqualTo false
            isExternal shouldBeEqualTo false
            asClassDeclaration() shouldBeEqualTo null
            hasClassDeclaration() shouldBeEqualTo false
            hasClassDeclaration { decl -> decl.name == "someName" } shouldBeEqualTo false
            hasClassDeclarationOf(SampleClass::class) shouldBeEqualTo false
            asObjectDeclaration() shouldBeEqualTo null
            hasObjectDeclaration() shouldBeEqualTo false
            hasObjectDeclaration { decl -> decl.name == "someName" } shouldBeEqualTo false
            hasObjectDeclarationOf(SampleType::class) shouldBeEqualTo false
            asInterfaceDeclaration() shouldBeEqualTo null
            hasInterfaceDeclaration() shouldBeEqualTo false
            hasInterfaceDeclaration { decl -> decl.name == "someName" } shouldBeEqualTo false
            hasInterfaceDeclarationOf(SampleType::class) shouldBeEqualTo false
            asTypeAliasDeclaration() shouldBeEqualTo null
            hasTypeAliasDeclaration() shouldBeEqualTo false
            hasTypeAliasDeclaration { decl -> decl.name == "someName" } shouldBeEqualTo false
            asImportAliasDeclaration() shouldBeEqualTo null
            hasImportAliasDeclaration() shouldBeEqualTo false
            hasImportAliasDeclaration { decl -> decl.name == "someName" } shouldBeEqualTo false
            asKotlinTypeDeclaration() shouldBeEqualTo null
            hasKotlinTypeDeclaration() shouldBeEqualTo false
            hasKotlinTypeDeclaration { decl -> decl.name == "someName" } shouldBeEqualTo false
            hasKotlinTypeDeclarationOf(SampleType::class) shouldBeEqualTo false
            asKotlinBasicTypeDeclaration() shouldBeEqualTo null
            hasKotlinBasicTypeDeclaration() shouldBeEqualTo false
            hasKotlinBasicTypeDeclaration { decl -> decl.name == "someName" } shouldBeEqualTo false
            hasKotlinBasicTypeDeclarationOf(SampleType::class) shouldBeEqualTo false
            asKotlinCollectionTypeDeclaration() shouldBeEqualTo null
            hasKotlinCollectionTypeDeclaration() shouldBeEqualTo false
            hasKotlinCollectionTypeDeclaration { decl -> decl.name == "someName" } shouldBeEqualTo false
            hasKotlinCollectionTypeDeclarationOf(SampleType::class) shouldBeEqualTo false
            asExternalDeclaration() shouldBeEqualTo null
            hasExternalDeclaration() shouldBeEqualTo false
            hasExternalDeclaration { decl -> decl.name == "someName" } shouldBeEqualTo false
            asTypeParameterDeclaration() shouldBeEqualTo null
            hasTypeParameterDeclaration() shouldBeEqualTo false
            hasTypeParameterDeclaration { decl -> decl.name == "someName" } shouldBeEqualTo false
        }
    }

    @Test
    fun `class-with-parametrized-external-parent-class`() {
        // given
        val sut =
            getSnippetFile("class-with-parametrized-external-parent-class")
                .classes()
                .parents()
                .first()

        // then
        assertSoftly(sut) {
            isClass shouldBeEqualTo false
            isObject shouldBeEqualTo false
            isInterface shouldBeEqualTo false
            isTypeAlias shouldBeEqualTo false
            isImportAlias shouldBeEqualTo false
            isKotlinType shouldBeEqualTo false
            isKotlinBasicType shouldBeEqualTo false
            isKotlinCollectionType shouldBeEqualTo false
            isTypeParameter shouldBeEqualTo false
            isExternal shouldBeEqualTo false
            asClassDeclaration() shouldBeEqualTo null
            hasClassDeclaration() shouldBeEqualTo false
            hasClassDeclaration { decl -> decl.name == "someName" } shouldBeEqualTo false
            hasClassDeclarationOf(SampleClass::class) shouldBeEqualTo false
            asObjectDeclaration() shouldBeEqualTo null
            hasObjectDeclaration() shouldBeEqualTo false
            hasObjectDeclaration { decl -> decl.name == "someName" } shouldBeEqualTo false
            hasObjectDeclarationOf(SampleType::class) shouldBeEqualTo false
            asInterfaceDeclaration() shouldBeEqualTo null
            hasInterfaceDeclaration() shouldBeEqualTo false
            hasInterfaceDeclaration { decl -> decl.name == "someName" } shouldBeEqualTo false
            hasInterfaceDeclarationOf(SampleType::class) shouldBeEqualTo false
            asTypeAliasDeclaration() shouldBeEqualTo null
            hasTypeAliasDeclaration() shouldBeEqualTo false
            hasTypeAliasDeclaration { decl -> decl.name == "someName" } shouldBeEqualTo false
            asImportAliasDeclaration() shouldBeEqualTo null
            hasImportAliasDeclaration() shouldBeEqualTo false
            hasImportAliasDeclaration { decl -> decl.name == "someName" } shouldBeEqualTo false
            asKotlinTypeDeclaration() shouldBeEqualTo null
            hasKotlinTypeDeclaration() shouldBeEqualTo false
            hasKotlinTypeDeclaration { decl -> decl.name == "someName" } shouldBeEqualTo false
            hasKotlinTypeDeclarationOf(SampleType::class) shouldBeEqualTo false
            asKotlinBasicTypeDeclaration() shouldBeEqualTo null
            hasKotlinBasicTypeDeclaration() shouldBeEqualTo false
            hasKotlinBasicTypeDeclaration { decl -> decl.name == "someName" } shouldBeEqualTo false
            hasKotlinBasicTypeDeclarationOf(SampleType::class) shouldBeEqualTo false
            asKotlinCollectionTypeDeclaration() shouldBeEqualTo null
            hasKotlinCollectionTypeDeclaration() shouldBeEqualTo false
            hasKotlinCollectionTypeDeclaration { decl -> decl.name == "someName" } shouldBeEqualTo false
            hasKotlinCollectionTypeDeclarationOf(SampleType::class) shouldBeEqualTo false
            asExternalDeclaration() shouldBeEqualTo null
            hasExternalDeclaration() shouldBeEqualTo false
            hasExternalDeclaration { decl -> decl.name == "someName" } shouldBeEqualTo false
            asTypeParameterDeclaration() shouldBeEqualTo null
            hasTypeParameterDeclaration() shouldBeEqualTo false
            hasTypeParameterDeclaration { decl -> decl.name == "someName" } shouldBeEqualTo false
        }
    }

    @Test
    fun `class-with-parametrized-and-generic-external-parent-class`() {
        // given
        val sut =
            getSnippetFile("class-with-parametrized-and-generic-external-parent-class")
                .classes()
                .parents()
                .first()

        // then
        assertSoftly(sut) {
            isClass shouldBeEqualTo false
            isObject shouldBeEqualTo false
            isInterface shouldBeEqualTo false
            isTypeAlias shouldBeEqualTo false
            isImportAlias shouldBeEqualTo false
            isKotlinType shouldBeEqualTo false
            isKotlinBasicType shouldBeEqualTo false
            isKotlinCollectionType shouldBeEqualTo false
            isTypeParameter shouldBeEqualTo false
            isExternal shouldBeEqualTo false
            asClassDeclaration() shouldBeEqualTo null
            hasClassDeclaration() shouldBeEqualTo false
            hasClassDeclaration { decl -> decl.name == "someName" } shouldBeEqualTo false
            hasClassDeclarationOf(SampleClass::class) shouldBeEqualTo false
            asObjectDeclaration() shouldBeEqualTo null
            hasObjectDeclaration() shouldBeEqualTo false
            hasObjectDeclaration { decl -> decl.name == "someName" } shouldBeEqualTo false
            hasObjectDeclarationOf(SampleType::class) shouldBeEqualTo false
            asInterfaceDeclaration() shouldBeEqualTo null
            hasInterfaceDeclaration() shouldBeEqualTo false
            hasInterfaceDeclaration { decl -> decl.name == "someName" } shouldBeEqualTo false
            hasInterfaceDeclarationOf(SampleType::class) shouldBeEqualTo false
            asTypeAliasDeclaration() shouldBeEqualTo null
            hasTypeAliasDeclaration() shouldBeEqualTo false
            hasTypeAliasDeclaration { decl -> decl.name == "someName" } shouldBeEqualTo false
            asImportAliasDeclaration() shouldBeEqualTo null
            hasImportAliasDeclaration() shouldBeEqualTo false
            hasImportAliasDeclaration { decl -> decl.name == "someName" } shouldBeEqualTo false
            asKotlinTypeDeclaration() shouldBeEqualTo null
            hasKotlinTypeDeclaration() shouldBeEqualTo false
            hasKotlinTypeDeclaration { decl -> decl.name == "someName" } shouldBeEqualTo false
            hasKotlinTypeDeclarationOf(SampleType::class) shouldBeEqualTo false
            asKotlinBasicTypeDeclaration() shouldBeEqualTo null
            hasKotlinBasicTypeDeclaration() shouldBeEqualTo false
            hasKotlinBasicTypeDeclaration { decl -> decl.name == "someName" } shouldBeEqualTo false
            hasKotlinBasicTypeDeclarationOf(SampleType::class) shouldBeEqualTo false
            asKotlinCollectionTypeDeclaration() shouldBeEqualTo null
            hasKotlinCollectionTypeDeclaration() shouldBeEqualTo false
            hasKotlinCollectionTypeDeclaration { decl -> decl.name == "someName" } shouldBeEqualTo false
            hasKotlinCollectionTypeDeclarationOf(SampleType::class) shouldBeEqualTo false
            asExternalDeclaration() shouldBeEqualTo null
            hasExternalDeclaration() shouldBeEqualTo false
            hasExternalDeclaration { decl -> decl.name == "someName" } shouldBeEqualTo false
            asTypeParameterDeclaration() shouldBeEqualTo null
            hasTypeParameterDeclaration() shouldBeEqualTo false
            hasTypeParameterDeclaration { decl -> decl.name == "someName" } shouldBeEqualTo false
        }
    }

    @Test
    fun `class-with-external-parent-interface`() {
        // given
        val sut =
            getSnippetFile("class-with-external-parent-interface")
                .classes()
                .parents()
                .first()

        // then
        assertSoftly(sut) {
            isClass shouldBeEqualTo false
            isObject shouldBeEqualTo false
            isInterface shouldBeEqualTo false
            isTypeAlias shouldBeEqualTo false
            isImportAlias shouldBeEqualTo false
            isKotlinType shouldBeEqualTo false
            isKotlinBasicType shouldBeEqualTo false
            isKotlinCollectionType shouldBeEqualTo false
            isTypeParameter shouldBeEqualTo false
            isExternal shouldBeEqualTo false
            asClassDeclaration() shouldBeEqualTo null
            hasClassDeclaration() shouldBeEqualTo false
            hasClassDeclaration { decl -> decl.name == "someName" } shouldBeEqualTo false
            hasClassDeclarationOf(SampleClass::class) shouldBeEqualTo false
            asObjectDeclaration() shouldBeEqualTo null
            hasObjectDeclaration() shouldBeEqualTo false
            hasObjectDeclaration { decl -> decl.name == "someName" } shouldBeEqualTo false
            hasObjectDeclarationOf(SampleType::class) shouldBeEqualTo false
            asInterfaceDeclaration() shouldBeEqualTo null
            hasInterfaceDeclaration() shouldBeEqualTo false
            hasInterfaceDeclaration { decl -> decl.name == "someName" } shouldBeEqualTo false
            hasInterfaceDeclarationOf(SampleType::class) shouldBeEqualTo false
            asTypeAliasDeclaration() shouldBeEqualTo null
            hasTypeAliasDeclaration() shouldBeEqualTo false
            hasTypeAliasDeclaration { decl -> decl.name == "someName" } shouldBeEqualTo false
            asImportAliasDeclaration() shouldBeEqualTo null
            hasImportAliasDeclaration() shouldBeEqualTo false
            hasImportAliasDeclaration { decl -> decl.name == "someName" } shouldBeEqualTo false
            asKotlinTypeDeclaration() shouldBeEqualTo null
            hasKotlinTypeDeclaration() shouldBeEqualTo false
            hasKotlinTypeDeclaration { decl -> decl.name == "someName" } shouldBeEqualTo false
            hasKotlinTypeDeclarationOf(SampleType::class) shouldBeEqualTo false
            asKotlinBasicTypeDeclaration() shouldBeEqualTo null
            hasKotlinBasicTypeDeclaration() shouldBeEqualTo false
            hasKotlinBasicTypeDeclaration { decl -> decl.name == "someName" } shouldBeEqualTo false
            hasKotlinBasicTypeDeclarationOf(SampleType::class) shouldBeEqualTo false
            asKotlinCollectionTypeDeclaration() shouldBeEqualTo null
            hasKotlinCollectionTypeDeclaration() shouldBeEqualTo false
            hasKotlinCollectionTypeDeclaration { decl -> decl.name == "someName" } shouldBeEqualTo false
            hasKotlinCollectionTypeDeclarationOf(SampleType::class) shouldBeEqualTo false
            asExternalDeclaration() shouldBeEqualTo null
            hasExternalDeclaration() shouldBeEqualTo false
            hasExternalDeclaration { decl -> decl.name == "someName" } shouldBeEqualTo false
            asTypeParameterDeclaration() shouldBeEqualTo null
            hasTypeParameterDeclaration() shouldBeEqualTo false
            hasTypeParameterDeclaration { decl -> decl.name == "someName" } shouldBeEqualTo false
        }
    }

    @Test
    fun `class-with-generic-external-parent-interface`() {
        // given
        val sut =
            getSnippetFile("class-with-generic-external-parent-interface")
                .classes()
                .parents()
                .first()

        // then
        assertSoftly(sut) {
            isClass shouldBeEqualTo false
            isObject shouldBeEqualTo false
            isInterface shouldBeEqualTo false
            isTypeAlias shouldBeEqualTo false
            isImportAlias shouldBeEqualTo false
            isKotlinType shouldBeEqualTo false
            isKotlinBasicType shouldBeEqualTo false
            isKotlinCollectionType shouldBeEqualTo false
            isTypeParameter shouldBeEqualTo false
            isExternal shouldBeEqualTo false
            asClassDeclaration() shouldBeEqualTo null
            hasClassDeclaration() shouldBeEqualTo false
            hasClassDeclaration { decl -> decl.name == "someName" } shouldBeEqualTo false
            hasClassDeclarationOf(SampleClass::class) shouldBeEqualTo false
            asObjectDeclaration() shouldBeEqualTo null
            hasObjectDeclaration() shouldBeEqualTo false
            hasObjectDeclaration { decl -> decl.name == "someName" } shouldBeEqualTo false
            hasObjectDeclarationOf(SampleType::class) shouldBeEqualTo false
            asInterfaceDeclaration() shouldBeEqualTo null
            hasInterfaceDeclaration() shouldBeEqualTo false
            hasInterfaceDeclaration { decl -> decl.name == "someName" } shouldBeEqualTo false
            hasInterfaceDeclarationOf(SampleType::class) shouldBeEqualTo false
            asTypeAliasDeclaration() shouldBeEqualTo null
            hasTypeAliasDeclaration() shouldBeEqualTo false
            hasTypeAliasDeclaration { decl -> decl.name == "someName" } shouldBeEqualTo false
            asImportAliasDeclaration() shouldBeEqualTo null
            hasImportAliasDeclaration() shouldBeEqualTo false
            hasImportAliasDeclaration { decl -> decl.name == "someName" } shouldBeEqualTo false
            asKotlinTypeDeclaration() shouldBeEqualTo null
            hasKotlinTypeDeclaration() shouldBeEqualTo false
            hasKotlinTypeDeclaration { decl -> decl.name == "someName" } shouldBeEqualTo false
            hasKotlinTypeDeclarationOf(SampleType::class) shouldBeEqualTo false
            asKotlinBasicTypeDeclaration() shouldBeEqualTo null
            hasKotlinBasicTypeDeclaration() shouldBeEqualTo false
            hasKotlinBasicTypeDeclaration { decl -> decl.name == "someName" } shouldBeEqualTo false
            hasKotlinBasicTypeDeclarationOf(SampleType::class) shouldBeEqualTo false
            asKotlinCollectionTypeDeclaration() shouldBeEqualTo null
            hasKotlinCollectionTypeDeclaration() shouldBeEqualTo false
            hasKotlinCollectionTypeDeclaration { decl -> decl.name == "someName" } shouldBeEqualTo false
            hasKotlinCollectionTypeDeclarationOf(SampleType::class) shouldBeEqualTo false
            asExternalDeclaration() shouldBeEqualTo null
            hasExternalDeclaration() shouldBeEqualTo false
            hasExternalDeclaration { decl -> decl.name == "someName" } shouldBeEqualTo false
            asTypeParameterDeclaration() shouldBeEqualTo null
            hasTypeParameterDeclaration() shouldBeEqualTo false
            hasTypeParameterDeclaration { decl -> decl.name == "someName" } shouldBeEqualTo false
        }
    }

    @Test
    fun `class-with-external-parent-by-delegation`() {
        // given
        val sut =
            getSnippetFile("class-with-external-parent-by-delegation")
                .classes()
                .parents()
                .first()

        // then
        assertSoftly(sut) {
            isClass shouldBeEqualTo false
            isObject shouldBeEqualTo false
            isInterface shouldBeEqualTo false
            isTypeAlias shouldBeEqualTo false
            isImportAlias shouldBeEqualTo false
            isKotlinType shouldBeEqualTo false
            isKotlinBasicType shouldBeEqualTo false
            isKotlinCollectionType shouldBeEqualTo false
            isTypeParameter shouldBeEqualTo false
            isExternal shouldBeEqualTo false
            asClassDeclaration() shouldBeEqualTo null
            hasClassDeclaration() shouldBeEqualTo false
            hasClassDeclaration { decl -> decl.name == "someName" } shouldBeEqualTo false
            hasClassDeclarationOf(SampleClass::class) shouldBeEqualTo false
            asObjectDeclaration() shouldBeEqualTo null
            hasObjectDeclaration() shouldBeEqualTo false
            hasObjectDeclaration { decl -> decl.name == "someName" } shouldBeEqualTo false
            hasObjectDeclarationOf(SampleType::class) shouldBeEqualTo false
            asInterfaceDeclaration() shouldBeEqualTo null
            hasInterfaceDeclaration() shouldBeEqualTo false
            hasInterfaceDeclaration { decl -> decl.name == "someName" } shouldBeEqualTo false
            hasInterfaceDeclarationOf(SampleType::class) shouldBeEqualTo false
            asTypeAliasDeclaration() shouldBeEqualTo null
            hasTypeAliasDeclaration() shouldBeEqualTo false
            hasTypeAliasDeclaration { decl -> decl.name == "someName" } shouldBeEqualTo false
            asImportAliasDeclaration() shouldBeEqualTo null
            hasImportAliasDeclaration() shouldBeEqualTo false
            hasImportAliasDeclaration { decl -> decl.name == "someName" } shouldBeEqualTo false
            asKotlinTypeDeclaration() shouldBeEqualTo null
            hasKotlinTypeDeclaration() shouldBeEqualTo false
            hasKotlinTypeDeclaration { decl -> decl.name == "someName" } shouldBeEqualTo false
            hasKotlinTypeDeclarationOf(SampleType::class) shouldBeEqualTo false
            asKotlinBasicTypeDeclaration() shouldBeEqualTo null
            hasKotlinBasicTypeDeclaration() shouldBeEqualTo false
            hasKotlinBasicTypeDeclaration { decl -> decl.name == "someName" } shouldBeEqualTo false
            hasKotlinBasicTypeDeclarationOf(SampleType::class) shouldBeEqualTo false
            asKotlinCollectionTypeDeclaration() shouldBeEqualTo null
            hasKotlinCollectionTypeDeclaration() shouldBeEqualTo false
            hasKotlinCollectionTypeDeclaration { decl -> decl.name == "someName" } shouldBeEqualTo false
            hasKotlinCollectionTypeDeclarationOf(SampleType::class) shouldBeEqualTo false
            asExternalDeclaration() shouldBeEqualTo null
            hasExternalDeclaration() shouldBeEqualTo false
            hasExternalDeclaration { decl -> decl.name == "someName" } shouldBeEqualTo false
            asTypeParameterDeclaration() shouldBeEqualTo null
            hasTypeParameterDeclaration() shouldBeEqualTo false
            hasTypeParameterDeclaration { decl -> decl.name == "someName" } shouldBeEqualTo false
        }
    }

    @Test
    fun `interface-with-parent-interface-from-file`() {
        // given
        val sut =
            getSnippetFile("interface-with-parent-interface-from-file")
                .interfaces()
                .parents()
                .first()

        // then
        assertSoftly(sut) {
            isClass shouldBeEqualTo false
            isObject shouldBeEqualTo false
            isInterface shouldBeEqualTo false
            isTypeAlias shouldBeEqualTo false
            isImportAlias shouldBeEqualTo false
            isKotlinType shouldBeEqualTo false
            isKotlinBasicType shouldBeEqualTo false
            isKotlinCollectionType shouldBeEqualTo false
            isTypeParameter shouldBeEqualTo false
            isExternal shouldBeEqualTo false
            asClassDeclaration() shouldBeEqualTo null
            hasClassDeclaration() shouldBeEqualTo false
            hasClassDeclaration { decl -> decl.name == "someName" } shouldBeEqualTo false
            hasClassDeclarationOf(SampleClass::class) shouldBeEqualTo false
            asObjectDeclaration() shouldBeEqualTo null
            hasObjectDeclaration() shouldBeEqualTo false
            hasObjectDeclaration { decl -> decl.name == "someName" } shouldBeEqualTo false
            hasObjectDeclarationOf(SampleType::class) shouldBeEqualTo false
            asInterfaceDeclaration() shouldBeEqualTo null
            hasInterfaceDeclaration() shouldBeEqualTo false
            hasInterfaceDeclaration { decl -> decl.name == "someName" } shouldBeEqualTo false
            hasInterfaceDeclarationOf(SampleType::class) shouldBeEqualTo false
            asTypeAliasDeclaration() shouldBeEqualTo null
            hasTypeAliasDeclaration() shouldBeEqualTo false
            hasTypeAliasDeclaration { decl -> decl.name == "someName" } shouldBeEqualTo false
            asImportAliasDeclaration() shouldBeEqualTo null
            hasImportAliasDeclaration() shouldBeEqualTo false
            hasImportAliasDeclaration { decl -> decl.name == "someName" } shouldBeEqualTo false
            asKotlinTypeDeclaration() shouldBeEqualTo null
            hasKotlinTypeDeclaration() shouldBeEqualTo false
            hasKotlinTypeDeclaration { decl -> decl.name == "someName" } shouldBeEqualTo false
            hasKotlinTypeDeclarationOf(SampleType::class) shouldBeEqualTo false
            asKotlinBasicTypeDeclaration() shouldBeEqualTo null
            hasKotlinBasicTypeDeclaration() shouldBeEqualTo false
            hasKotlinBasicTypeDeclaration { decl -> decl.name == "someName" } shouldBeEqualTo false
            hasKotlinBasicTypeDeclarationOf(SampleType::class) shouldBeEqualTo false
            asKotlinCollectionTypeDeclaration() shouldBeEqualTo null
            hasKotlinCollectionTypeDeclaration() shouldBeEqualTo false
            hasKotlinCollectionTypeDeclaration { decl -> decl.name == "someName" } shouldBeEqualTo false
            hasKotlinCollectionTypeDeclarationOf(SampleType::class) shouldBeEqualTo false
            asExternalDeclaration() shouldBeEqualTo null
            hasExternalDeclaration() shouldBeEqualTo false
            hasExternalDeclaration { decl -> decl.name == "someName" } shouldBeEqualTo false
            asTypeParameterDeclaration() shouldBeEqualTo null
            hasTypeParameterDeclaration() shouldBeEqualTo false
            hasTypeParameterDeclaration { decl -> decl.name == "someName" } shouldBeEqualTo false
        }
    }

    @Test
    fun `interface-with-generic-parent-interface-from-file`() {
        // given
        val sut =
            getSnippetFile("interface-with-generic-parent-interface-from-file")
                .interfaces()
                .parents()
                .first()

        // then
        assertSoftly(sut) {
            isClass shouldBeEqualTo false
            isObject shouldBeEqualTo false
            isInterface shouldBeEqualTo false
            isTypeAlias shouldBeEqualTo false
            isImportAlias shouldBeEqualTo false
            isKotlinType shouldBeEqualTo false
            isKotlinBasicType shouldBeEqualTo false
            isKotlinCollectionType shouldBeEqualTo false
            isTypeParameter shouldBeEqualTo false
            isExternal shouldBeEqualTo false
            asClassDeclaration() shouldBeEqualTo null
            hasClassDeclaration() shouldBeEqualTo false
            hasClassDeclaration { decl -> decl.name == "someName" } shouldBeEqualTo false
            hasClassDeclarationOf(SampleClass::class) shouldBeEqualTo false
            asObjectDeclaration() shouldBeEqualTo null
            hasObjectDeclaration() shouldBeEqualTo false
            hasObjectDeclaration { decl -> decl.name == "someName" } shouldBeEqualTo false
            hasObjectDeclarationOf(SampleType::class) shouldBeEqualTo false
            asInterfaceDeclaration() shouldBeEqualTo null
            hasInterfaceDeclaration() shouldBeEqualTo false
            hasInterfaceDeclaration { decl -> decl.name == "someName" } shouldBeEqualTo false
            hasInterfaceDeclarationOf(SampleType::class) shouldBeEqualTo false
            asTypeAliasDeclaration() shouldBeEqualTo null
            hasTypeAliasDeclaration() shouldBeEqualTo false
            hasTypeAliasDeclaration { decl -> decl.name == "someName" } shouldBeEqualTo false
            asImportAliasDeclaration() shouldBeEqualTo null
            hasImportAliasDeclaration() shouldBeEqualTo false
            hasImportAliasDeclaration { decl -> decl.name == "someName" } shouldBeEqualTo false
            asKotlinTypeDeclaration() shouldBeEqualTo null
            hasKotlinTypeDeclaration() shouldBeEqualTo false
            hasKotlinTypeDeclaration { decl -> decl.name == "someName" } shouldBeEqualTo false
            hasKotlinTypeDeclarationOf(SampleType::class) shouldBeEqualTo false
            asKotlinBasicTypeDeclaration() shouldBeEqualTo null
            hasKotlinBasicTypeDeclaration() shouldBeEqualTo false
            hasKotlinBasicTypeDeclaration { decl -> decl.name == "someName" } shouldBeEqualTo false
            hasKotlinBasicTypeDeclarationOf(SampleType::class) shouldBeEqualTo false
            asKotlinCollectionTypeDeclaration() shouldBeEqualTo null
            hasKotlinCollectionTypeDeclaration() shouldBeEqualTo false
            hasKotlinCollectionTypeDeclaration { decl -> decl.name == "someName" } shouldBeEqualTo false
            hasKotlinCollectionTypeDeclarationOf(SampleType::class) shouldBeEqualTo false
            asExternalDeclaration() shouldBeEqualTo null
            hasExternalDeclaration() shouldBeEqualTo false
            hasExternalDeclaration { decl -> decl.name == "someName" } shouldBeEqualTo false
            asTypeParameterDeclaration() shouldBeEqualTo null
            hasTypeParameterDeclaration() shouldBeEqualTo false
            hasTypeParameterDeclaration { decl -> decl.name == "someName" } shouldBeEqualTo false
        }
    }

    @Test
    fun `interface-with-parent-interface-from-import`() {
        // given
        val sut =
            getSnippetFile("interface-with-parent-interface-from-import")
                .interfaces()
                .parents()
                .first()

        // then
        assertSoftly(sut) {
            isClass shouldBeEqualTo false
            isObject shouldBeEqualTo false
            isInterface shouldBeEqualTo false
            isTypeAlias shouldBeEqualTo false
            isImportAlias shouldBeEqualTo false
            isKotlinType shouldBeEqualTo false
            isKotlinBasicType shouldBeEqualTo false
            isKotlinCollectionType shouldBeEqualTo false
            isTypeParameter shouldBeEqualTo false
            isExternal shouldBeEqualTo false
            asClassDeclaration() shouldBeEqualTo null
            hasClassDeclaration() shouldBeEqualTo false
            hasClassDeclaration { decl -> decl.name == "someName" } shouldBeEqualTo false
            hasClassDeclarationOf(SampleClass::class) shouldBeEqualTo false
            asObjectDeclaration() shouldBeEqualTo null
            hasObjectDeclaration() shouldBeEqualTo false
            hasObjectDeclaration { decl -> decl.name == "someName" } shouldBeEqualTo false
            hasObjectDeclarationOf(SampleType::class) shouldBeEqualTo false
            asInterfaceDeclaration() shouldBeEqualTo null
            hasInterfaceDeclaration() shouldBeEqualTo false
            hasInterfaceDeclaration { decl -> decl.name == "someName" } shouldBeEqualTo false
            hasInterfaceDeclarationOf(SampleType::class) shouldBeEqualTo false
            asTypeAliasDeclaration() shouldBeEqualTo null
            hasTypeAliasDeclaration() shouldBeEqualTo false
            hasTypeAliasDeclaration { decl -> decl.name == "someName" } shouldBeEqualTo false
            asImportAliasDeclaration() shouldBeEqualTo null
            hasImportAliasDeclaration() shouldBeEqualTo false
            hasImportAliasDeclaration { decl -> decl.name == "someName" } shouldBeEqualTo false
            asKotlinTypeDeclaration() shouldBeEqualTo null
            hasKotlinTypeDeclaration() shouldBeEqualTo false
            hasKotlinTypeDeclaration { decl -> decl.name == "someName" } shouldBeEqualTo false
            hasKotlinTypeDeclarationOf(SampleType::class) shouldBeEqualTo false
            asKotlinBasicTypeDeclaration() shouldBeEqualTo null
            hasKotlinBasicTypeDeclaration() shouldBeEqualTo false
            hasKotlinBasicTypeDeclaration { decl -> decl.name == "someName" } shouldBeEqualTo false
            hasKotlinBasicTypeDeclarationOf(SampleType::class) shouldBeEqualTo false
            asKotlinCollectionTypeDeclaration() shouldBeEqualTo null
            hasKotlinCollectionTypeDeclaration() shouldBeEqualTo false
            hasKotlinCollectionTypeDeclaration { decl -> decl.name == "someName" } shouldBeEqualTo false
            hasKotlinCollectionTypeDeclarationOf(SampleType::class) shouldBeEqualTo false
            asExternalDeclaration() shouldBeEqualTo null
            hasExternalDeclaration() shouldBeEqualTo false
            hasExternalDeclaration { decl -> decl.name == "someName" } shouldBeEqualTo false
            asTypeParameterDeclaration() shouldBeEqualTo null
            hasTypeParameterDeclaration() shouldBeEqualTo false
            hasTypeParameterDeclaration { decl -> decl.name == "someName" } shouldBeEqualTo false
        }
    }

    @Test
    fun `interface-with-generic-parent-interface-from-import`() {
        // given
        val sut =
            getSnippetFile("interface-with-generic-parent-interface-from-import")
                .interfaces()
                .parents()
                .first()

        // then
        assertSoftly(sut) {
            isClass shouldBeEqualTo false
            isObject shouldBeEqualTo false
            isInterface shouldBeEqualTo false
            isTypeAlias shouldBeEqualTo false
            isImportAlias shouldBeEqualTo false
            isKotlinType shouldBeEqualTo false
            isKotlinBasicType shouldBeEqualTo false
            isKotlinCollectionType shouldBeEqualTo false
            isTypeParameter shouldBeEqualTo false
            isExternal shouldBeEqualTo false
            asClassDeclaration() shouldBeEqualTo null
            hasClassDeclaration() shouldBeEqualTo false
            hasClassDeclaration { decl -> decl.name == "someName" } shouldBeEqualTo false
            hasClassDeclarationOf(SampleClass::class) shouldBeEqualTo false
            asObjectDeclaration() shouldBeEqualTo null
            hasObjectDeclaration() shouldBeEqualTo false
            hasObjectDeclaration { decl -> decl.name == "someName" } shouldBeEqualTo false
            hasObjectDeclarationOf(SampleType::class) shouldBeEqualTo false
            asInterfaceDeclaration() shouldBeEqualTo null
            hasInterfaceDeclaration() shouldBeEqualTo false
            hasInterfaceDeclaration { decl -> decl.name == "someName" } shouldBeEqualTo false
            hasInterfaceDeclarationOf(SampleType::class) shouldBeEqualTo false
            asTypeAliasDeclaration() shouldBeEqualTo null
            hasTypeAliasDeclaration() shouldBeEqualTo false
            hasTypeAliasDeclaration { decl -> decl.name == "someName" } shouldBeEqualTo false
            asImportAliasDeclaration() shouldBeEqualTo null
            hasImportAliasDeclaration() shouldBeEqualTo false
            hasImportAliasDeclaration { decl -> decl.name == "someName" } shouldBeEqualTo false
            asKotlinTypeDeclaration() shouldBeEqualTo null
            hasKotlinTypeDeclaration() shouldBeEqualTo false
            hasKotlinTypeDeclaration { decl -> decl.name == "someName" } shouldBeEqualTo false
            hasKotlinTypeDeclarationOf(SampleType::class) shouldBeEqualTo false
            asKotlinBasicTypeDeclaration() shouldBeEqualTo null
            hasKotlinBasicTypeDeclaration() shouldBeEqualTo false
            hasKotlinBasicTypeDeclaration { decl -> decl.name == "someName" } shouldBeEqualTo false
            hasKotlinBasicTypeDeclarationOf(SampleType::class) shouldBeEqualTo false
            asKotlinCollectionTypeDeclaration() shouldBeEqualTo null
            hasKotlinCollectionTypeDeclaration() shouldBeEqualTo false
            hasKotlinCollectionTypeDeclaration { decl -> decl.name == "someName" } shouldBeEqualTo false
            hasKotlinCollectionTypeDeclarationOf(SampleType::class) shouldBeEqualTo false
            asExternalDeclaration() shouldBeEqualTo null
            hasExternalDeclaration() shouldBeEqualTo false
            hasExternalDeclaration { decl -> decl.name == "someName" } shouldBeEqualTo false
            asTypeParameterDeclaration() shouldBeEqualTo null
            hasTypeParameterDeclaration() shouldBeEqualTo false
            hasTypeParameterDeclaration { decl -> decl.name == "someName" } shouldBeEqualTo false
        }
    }

    @Test
    fun `interface-with-external-parent-interface`() {
        // given
        val sut =
            getSnippetFile("interface-with-external-parent-interface")
                .interfaces()
                .parents()
                .first()

        // then
        assertSoftly(sut) {
            isClass shouldBeEqualTo false
            isObject shouldBeEqualTo false
            isInterface shouldBeEqualTo false
            isTypeAlias shouldBeEqualTo false
            isImportAlias shouldBeEqualTo false
            isKotlinType shouldBeEqualTo false
            isKotlinBasicType shouldBeEqualTo false
            isKotlinCollectionType shouldBeEqualTo false
            isTypeParameter shouldBeEqualTo false
            isExternal shouldBeEqualTo false
            asClassDeclaration() shouldBeEqualTo null
            hasClassDeclaration() shouldBeEqualTo false
            hasClassDeclaration { decl -> decl.name == "someName" } shouldBeEqualTo false
            hasClassDeclarationOf(SampleClass::class) shouldBeEqualTo false
            asObjectDeclaration() shouldBeEqualTo null
            hasObjectDeclaration() shouldBeEqualTo false
            hasObjectDeclaration { decl -> decl.name == "someName" } shouldBeEqualTo false
            hasObjectDeclarationOf(SampleType::class) shouldBeEqualTo false
            asInterfaceDeclaration() shouldBeEqualTo null
            hasInterfaceDeclaration() shouldBeEqualTo false
            hasInterfaceDeclaration { decl -> decl.name == "someName" } shouldBeEqualTo false
            hasInterfaceDeclarationOf(SampleType::class) shouldBeEqualTo false
            asTypeAliasDeclaration() shouldBeEqualTo null
            hasTypeAliasDeclaration() shouldBeEqualTo false
            hasTypeAliasDeclaration { decl -> decl.name == "someName" } shouldBeEqualTo false
            asImportAliasDeclaration() shouldBeEqualTo null
            hasImportAliasDeclaration() shouldBeEqualTo false
            hasImportAliasDeclaration { decl -> decl.name == "someName" } shouldBeEqualTo false
            asKotlinTypeDeclaration() shouldBeEqualTo null
            hasKotlinTypeDeclaration() shouldBeEqualTo false
            hasKotlinTypeDeclaration { decl -> decl.name == "someName" } shouldBeEqualTo false
            hasKotlinTypeDeclarationOf(SampleType::class) shouldBeEqualTo false
            asKotlinBasicTypeDeclaration() shouldBeEqualTo null
            hasKotlinBasicTypeDeclaration() shouldBeEqualTo false
            hasKotlinBasicTypeDeclaration { decl -> decl.name == "someName" } shouldBeEqualTo false
            hasKotlinBasicTypeDeclarationOf(SampleType::class) shouldBeEqualTo false
            asKotlinCollectionTypeDeclaration() shouldBeEqualTo null
            hasKotlinCollectionTypeDeclaration() shouldBeEqualTo false
            hasKotlinCollectionTypeDeclaration { decl -> decl.name == "someName" } shouldBeEqualTo false
            hasKotlinCollectionTypeDeclarationOf(SampleType::class) shouldBeEqualTo false
            asExternalDeclaration() shouldBeEqualTo null
            hasExternalDeclaration() shouldBeEqualTo false
            hasExternalDeclaration { decl -> decl.name == "someName" } shouldBeEqualTo false
            asTypeParameterDeclaration() shouldBeEqualTo null
            hasTypeParameterDeclaration() shouldBeEqualTo false
            hasTypeParameterDeclaration { decl -> decl.name == "someName" } shouldBeEqualTo false
        }
    }

    @Test
    fun `interface-with-generic-external-parent-interface`() {
        // given
        val sut =
            getSnippetFile("interface-with-generic-external-parent-interface")
                .interfaces()
                .parents()
                .first()

        // then
        assertSoftly(sut) {
            isClass shouldBeEqualTo false
            isObject shouldBeEqualTo false
            isInterface shouldBeEqualTo false
            isTypeAlias shouldBeEqualTo false
            isImportAlias shouldBeEqualTo false
            isKotlinType shouldBeEqualTo false
            isKotlinBasicType shouldBeEqualTo false
            isKotlinCollectionType shouldBeEqualTo false
            isTypeParameter shouldBeEqualTo false
            isExternal shouldBeEqualTo false
            asClassDeclaration() shouldBeEqualTo null
            hasClassDeclaration() shouldBeEqualTo false
            hasClassDeclaration { decl -> decl.name == "someName" } shouldBeEqualTo false
            hasClassDeclarationOf(SampleClass::class) shouldBeEqualTo false
            asObjectDeclaration() shouldBeEqualTo null
            hasObjectDeclaration() shouldBeEqualTo false
            hasObjectDeclaration { decl -> decl.name == "someName" } shouldBeEqualTo false
            hasObjectDeclarationOf(SampleType::class) shouldBeEqualTo false
            asInterfaceDeclaration() shouldBeEqualTo null
            hasInterfaceDeclaration() shouldBeEqualTo false
            hasInterfaceDeclaration { decl -> decl.name == "someName" } shouldBeEqualTo false
            hasInterfaceDeclarationOf(SampleType::class) shouldBeEqualTo false
            asTypeAliasDeclaration() shouldBeEqualTo null
            hasTypeAliasDeclaration() shouldBeEqualTo false
            hasTypeAliasDeclaration { decl -> decl.name == "someName" } shouldBeEqualTo false
            asImportAliasDeclaration() shouldBeEqualTo null
            hasImportAliasDeclaration() shouldBeEqualTo false
            hasImportAliasDeclaration { decl -> decl.name == "someName" } shouldBeEqualTo false
            asKotlinTypeDeclaration() shouldBeEqualTo null
            hasKotlinTypeDeclaration() shouldBeEqualTo false
            hasKotlinTypeDeclaration { decl -> decl.name == "someName" } shouldBeEqualTo false
            hasKotlinTypeDeclarationOf(SampleType::class) shouldBeEqualTo false
            asKotlinBasicTypeDeclaration() shouldBeEqualTo null
            hasKotlinBasicTypeDeclaration() shouldBeEqualTo false
            hasKotlinBasicTypeDeclaration { decl -> decl.name == "someName" } shouldBeEqualTo false
            hasKotlinBasicTypeDeclarationOf(SampleType::class) shouldBeEqualTo false
            asKotlinCollectionTypeDeclaration() shouldBeEqualTo null
            hasKotlinCollectionTypeDeclaration() shouldBeEqualTo false
            hasKotlinCollectionTypeDeclaration { decl -> decl.name == "someName" } shouldBeEqualTo false
            hasKotlinCollectionTypeDeclarationOf(SampleType::class) shouldBeEqualTo false
            asExternalDeclaration() shouldBeEqualTo null
            hasExternalDeclaration() shouldBeEqualTo false
            hasExternalDeclaration { decl -> decl.name == "someName" } shouldBeEqualTo false
            asTypeParameterDeclaration() shouldBeEqualTo null
            hasTypeParameterDeclaration() shouldBeEqualTo false
            hasTypeParameterDeclaration { decl -> decl.name == "someName" } shouldBeEqualTo false
        }
    }

    @Test
    fun `object-with-parent-class-from-file`() {
        // given
        val sut =
            getSnippetFile("object-with-parent-class-from-file")
                .objects()
                .parents()
                .first()

        // then
        assertSoftly(sut) {
            isClass shouldBeEqualTo false
            isObject shouldBeEqualTo false
            isInterface shouldBeEqualTo false
            isTypeAlias shouldBeEqualTo false
            isImportAlias shouldBeEqualTo false
            isKotlinType shouldBeEqualTo false
            isKotlinBasicType shouldBeEqualTo false
            isKotlinCollectionType shouldBeEqualTo false
            isTypeParameter shouldBeEqualTo false
            isExternal shouldBeEqualTo false
            asClassDeclaration() shouldBeEqualTo null
            hasClassDeclaration() shouldBeEqualTo false
            hasClassDeclaration { decl -> decl.name == "someName" } shouldBeEqualTo false
            hasClassDeclarationOf(SampleClass::class) shouldBeEqualTo false
            asObjectDeclaration() shouldBeEqualTo null
            hasObjectDeclaration() shouldBeEqualTo false
            hasObjectDeclaration { decl -> decl.name == "someName" } shouldBeEqualTo false
            hasObjectDeclarationOf(SampleType::class) shouldBeEqualTo false
            asInterfaceDeclaration() shouldBeEqualTo null
            hasInterfaceDeclaration() shouldBeEqualTo false
            hasInterfaceDeclaration { decl -> decl.name == "someName" } shouldBeEqualTo false
            hasInterfaceDeclarationOf(SampleType::class) shouldBeEqualTo false
            asTypeAliasDeclaration() shouldBeEqualTo null
            hasTypeAliasDeclaration() shouldBeEqualTo false
            hasTypeAliasDeclaration { decl -> decl.name == "someName" } shouldBeEqualTo false
            asImportAliasDeclaration() shouldBeEqualTo null
            hasImportAliasDeclaration() shouldBeEqualTo false
            hasImportAliasDeclaration { decl -> decl.name == "someName" } shouldBeEqualTo false
            asKotlinTypeDeclaration() shouldBeEqualTo null
            hasKotlinTypeDeclaration() shouldBeEqualTo false
            hasKotlinTypeDeclaration { decl -> decl.name == "someName" } shouldBeEqualTo false
            hasKotlinTypeDeclarationOf(SampleType::class) shouldBeEqualTo false
            asKotlinBasicTypeDeclaration() shouldBeEqualTo null
            hasKotlinBasicTypeDeclaration() shouldBeEqualTo false
            hasKotlinBasicTypeDeclaration { decl -> decl.name == "someName" } shouldBeEqualTo false
            hasKotlinBasicTypeDeclarationOf(SampleType::class) shouldBeEqualTo false
            asKotlinCollectionTypeDeclaration() shouldBeEqualTo null
            hasKotlinCollectionTypeDeclaration() shouldBeEqualTo false
            hasKotlinCollectionTypeDeclaration { decl -> decl.name == "someName" } shouldBeEqualTo false
            hasKotlinCollectionTypeDeclarationOf(SampleType::class) shouldBeEqualTo false
            asExternalDeclaration() shouldBeEqualTo null
            hasExternalDeclaration() shouldBeEqualTo false
            hasExternalDeclaration { decl -> decl.name == "someName" } shouldBeEqualTo false
            asTypeParameterDeclaration() shouldBeEqualTo null
            hasTypeParameterDeclaration() shouldBeEqualTo false
            hasTypeParameterDeclaration { decl -> decl.name == "someName" } shouldBeEqualTo false
        }
    }

    @Test
    fun `object-with-generic-parent-class-from-file`() {
        // given
        val sut =
            getSnippetFile("object-with-generic-parent-class-from-file")
                .objects()
                .parents()
                .first()

        // then
        assertSoftly(sut) {
            isClass shouldBeEqualTo false
            isObject shouldBeEqualTo false
            isInterface shouldBeEqualTo false
            isTypeAlias shouldBeEqualTo false
            isImportAlias shouldBeEqualTo false
            isKotlinType shouldBeEqualTo false
            isKotlinBasicType shouldBeEqualTo false
            isKotlinCollectionType shouldBeEqualTo false
            isTypeParameter shouldBeEqualTo false
            isExternal shouldBeEqualTo false
            asClassDeclaration() shouldBeEqualTo null
            hasClassDeclaration() shouldBeEqualTo false
            hasClassDeclaration { decl -> decl.name == "someName" } shouldBeEqualTo false
            hasClassDeclarationOf(SampleClass::class) shouldBeEqualTo false
            asObjectDeclaration() shouldBeEqualTo null
            hasObjectDeclaration() shouldBeEqualTo false
            hasObjectDeclaration { decl -> decl.name == "someName" } shouldBeEqualTo false
            hasObjectDeclarationOf(SampleType::class) shouldBeEqualTo false
            asInterfaceDeclaration() shouldBeEqualTo null
            hasInterfaceDeclaration() shouldBeEqualTo false
            hasInterfaceDeclaration { decl -> decl.name == "someName" } shouldBeEqualTo false
            hasInterfaceDeclarationOf(SampleType::class) shouldBeEqualTo false
            asTypeAliasDeclaration() shouldBeEqualTo null
            hasTypeAliasDeclaration() shouldBeEqualTo false
            hasTypeAliasDeclaration { decl -> decl.name == "someName" } shouldBeEqualTo false
            asImportAliasDeclaration() shouldBeEqualTo null
            hasImportAliasDeclaration() shouldBeEqualTo false
            hasImportAliasDeclaration { decl -> decl.name == "someName" } shouldBeEqualTo false
            asKotlinTypeDeclaration() shouldBeEqualTo null
            hasKotlinTypeDeclaration() shouldBeEqualTo false
            hasKotlinTypeDeclaration { decl -> decl.name == "someName" } shouldBeEqualTo false
            hasKotlinTypeDeclarationOf(SampleType::class) shouldBeEqualTo false
            asKotlinBasicTypeDeclaration() shouldBeEqualTo null
            hasKotlinBasicTypeDeclaration() shouldBeEqualTo false
            hasKotlinBasicTypeDeclaration { decl -> decl.name == "someName" } shouldBeEqualTo false
            hasKotlinBasicTypeDeclarationOf(SampleType::class) shouldBeEqualTo false
            asKotlinCollectionTypeDeclaration() shouldBeEqualTo null
            hasKotlinCollectionTypeDeclaration() shouldBeEqualTo false
            hasKotlinCollectionTypeDeclaration { decl -> decl.name == "someName" } shouldBeEqualTo false
            hasKotlinCollectionTypeDeclarationOf(SampleType::class) shouldBeEqualTo false
            asExternalDeclaration() shouldBeEqualTo null
            hasExternalDeclaration() shouldBeEqualTo false
            hasExternalDeclaration { decl -> decl.name == "someName" } shouldBeEqualTo false
            asTypeParameterDeclaration() shouldBeEqualTo null
            hasTypeParameterDeclaration() shouldBeEqualTo false
            hasTypeParameterDeclaration { decl -> decl.name == "someName" } shouldBeEqualTo false
        }
    }

    @Test
    fun `object-with-parametrized-parent-class-from-file`() {
        // given
        val sut =
            getSnippetFile("object-with-parametrized-parent-class-from-file")
                .objects()
                .parents()
                .first()

        // then
        assertSoftly(sut) {
            isClass shouldBeEqualTo false
            isObject shouldBeEqualTo false
            isInterface shouldBeEqualTo false
            isTypeAlias shouldBeEqualTo false
            isImportAlias shouldBeEqualTo false
            isKotlinType shouldBeEqualTo false
            isKotlinBasicType shouldBeEqualTo false
            isKotlinCollectionType shouldBeEqualTo false
            isTypeParameter shouldBeEqualTo false
            isExternal shouldBeEqualTo false
            asClassDeclaration() shouldBeEqualTo null
            hasClassDeclaration() shouldBeEqualTo false
            hasClassDeclaration { decl -> decl.name == "someName" } shouldBeEqualTo false
            hasClassDeclarationOf(SampleClass::class) shouldBeEqualTo false
            asObjectDeclaration() shouldBeEqualTo null
            hasObjectDeclaration() shouldBeEqualTo false
            hasObjectDeclaration { decl -> decl.name == "someName" } shouldBeEqualTo false
            hasObjectDeclarationOf(SampleType::class) shouldBeEqualTo false
            asInterfaceDeclaration() shouldBeEqualTo null
            hasInterfaceDeclaration() shouldBeEqualTo false
            hasInterfaceDeclaration { decl -> decl.name == "someName" } shouldBeEqualTo false
            hasInterfaceDeclarationOf(SampleType::class) shouldBeEqualTo false
            asTypeAliasDeclaration() shouldBeEqualTo null
            hasTypeAliasDeclaration() shouldBeEqualTo false
            hasTypeAliasDeclaration { decl -> decl.name == "someName" } shouldBeEqualTo false
            asImportAliasDeclaration() shouldBeEqualTo null
            hasImportAliasDeclaration() shouldBeEqualTo false
            hasImportAliasDeclaration { decl -> decl.name == "someName" } shouldBeEqualTo false
            asKotlinTypeDeclaration() shouldBeEqualTo null
            hasKotlinTypeDeclaration() shouldBeEqualTo false
            hasKotlinTypeDeclaration { decl -> decl.name == "someName" } shouldBeEqualTo false
            hasKotlinTypeDeclarationOf(SampleType::class) shouldBeEqualTo false
            asKotlinBasicTypeDeclaration() shouldBeEqualTo null
            hasKotlinBasicTypeDeclaration() shouldBeEqualTo false
            hasKotlinBasicTypeDeclaration { decl -> decl.name == "someName" } shouldBeEqualTo false
            hasKotlinBasicTypeDeclarationOf(SampleType::class) shouldBeEqualTo false
            asKotlinCollectionTypeDeclaration() shouldBeEqualTo null
            hasKotlinCollectionTypeDeclaration() shouldBeEqualTo false
            hasKotlinCollectionTypeDeclaration { decl -> decl.name == "someName" } shouldBeEqualTo false
            hasKotlinCollectionTypeDeclarationOf(SampleType::class) shouldBeEqualTo false
            asExternalDeclaration() shouldBeEqualTo null
            hasExternalDeclaration() shouldBeEqualTo false
            hasExternalDeclaration { decl -> decl.name == "someName" } shouldBeEqualTo false
            asTypeParameterDeclaration() shouldBeEqualTo null
            hasTypeParameterDeclaration() shouldBeEqualTo false
            hasTypeParameterDeclaration { decl -> decl.name == "someName" } shouldBeEqualTo false
        }
    }

    @Test
    fun `object-with-parametrized-and-generic-parent-class-from-file`() {
        // given
        val sut =
            getSnippetFile("object-with-parametrized-and-generic-parent-class-from-file")
                .objects()
                .parents()
                .first()

        // then
        assertSoftly(sut) {
            isClass shouldBeEqualTo false
            isObject shouldBeEqualTo false
            isInterface shouldBeEqualTo false
            isTypeAlias shouldBeEqualTo false
            isImportAlias shouldBeEqualTo false
            isKotlinType shouldBeEqualTo false
            isKotlinBasicType shouldBeEqualTo false
            isKotlinCollectionType shouldBeEqualTo false
            isTypeParameter shouldBeEqualTo false
            isExternal shouldBeEqualTo false
            asClassDeclaration() shouldBeEqualTo null
            hasClassDeclaration() shouldBeEqualTo false
            hasClassDeclaration { decl -> decl.name == "someName" } shouldBeEqualTo false
            hasClassDeclarationOf(SampleClass::class) shouldBeEqualTo false
            asObjectDeclaration() shouldBeEqualTo null
            hasObjectDeclaration() shouldBeEqualTo false
            hasObjectDeclaration { decl -> decl.name == "someName" } shouldBeEqualTo false
            hasObjectDeclarationOf(SampleType::class) shouldBeEqualTo false
            asInterfaceDeclaration() shouldBeEqualTo null
            hasInterfaceDeclaration() shouldBeEqualTo false
            hasInterfaceDeclaration { decl -> decl.name == "someName" } shouldBeEqualTo false
            hasInterfaceDeclarationOf(SampleType::class) shouldBeEqualTo false
            asTypeAliasDeclaration() shouldBeEqualTo null
            hasTypeAliasDeclaration() shouldBeEqualTo false
            hasTypeAliasDeclaration { decl -> decl.name == "someName" } shouldBeEqualTo false
            asImportAliasDeclaration() shouldBeEqualTo null
            hasImportAliasDeclaration() shouldBeEqualTo false
            hasImportAliasDeclaration { decl -> decl.name == "someName" } shouldBeEqualTo false
            asKotlinTypeDeclaration() shouldBeEqualTo null
            hasKotlinTypeDeclaration() shouldBeEqualTo false
            hasKotlinTypeDeclaration { decl -> decl.name == "someName" } shouldBeEqualTo false
            hasKotlinTypeDeclarationOf(SampleType::class) shouldBeEqualTo false
            asKotlinBasicTypeDeclaration() shouldBeEqualTo null
            hasKotlinBasicTypeDeclaration() shouldBeEqualTo false
            hasKotlinBasicTypeDeclaration { decl -> decl.name == "someName" } shouldBeEqualTo false
            hasKotlinBasicTypeDeclarationOf(SampleType::class) shouldBeEqualTo false
            asKotlinCollectionTypeDeclaration() shouldBeEqualTo null
            hasKotlinCollectionTypeDeclaration() shouldBeEqualTo false
            hasKotlinCollectionTypeDeclaration { decl -> decl.name == "someName" } shouldBeEqualTo false
            hasKotlinCollectionTypeDeclarationOf(SampleType::class) shouldBeEqualTo false
            asExternalDeclaration() shouldBeEqualTo null
            hasExternalDeclaration() shouldBeEqualTo false
            hasExternalDeclaration { decl -> decl.name == "someName" } shouldBeEqualTo false
            asTypeParameterDeclaration() shouldBeEqualTo null
            hasTypeParameterDeclaration() shouldBeEqualTo false
            hasTypeParameterDeclaration { decl -> decl.name == "someName" } shouldBeEqualTo false
        }
    }

    @Test
    fun `object-with-parent-interface-from-file`() {
        // given
        val sut =
            getSnippetFile("object-with-parent-interface-from-file")
                .objects()
                .parents()
                .first()

        // then
        assertSoftly(sut) {
            isClass shouldBeEqualTo false
            isObject shouldBeEqualTo false
            isInterface shouldBeEqualTo false
            isTypeAlias shouldBeEqualTo false
            isImportAlias shouldBeEqualTo false
            isKotlinType shouldBeEqualTo false
            isKotlinBasicType shouldBeEqualTo false
            isKotlinCollectionType shouldBeEqualTo false
            isTypeParameter shouldBeEqualTo false
            isExternal shouldBeEqualTo false
            asClassDeclaration() shouldBeEqualTo null
            hasClassDeclaration() shouldBeEqualTo false
            hasClassDeclaration { decl -> decl.name == "someName" } shouldBeEqualTo false
            hasClassDeclarationOf(SampleClass::class) shouldBeEqualTo false
            asObjectDeclaration() shouldBeEqualTo null
            hasObjectDeclaration() shouldBeEqualTo false
            hasObjectDeclaration { decl -> decl.name == "someName" } shouldBeEqualTo false
            hasObjectDeclarationOf(SampleType::class) shouldBeEqualTo false
            asInterfaceDeclaration() shouldBeEqualTo null
            hasInterfaceDeclaration() shouldBeEqualTo false
            hasInterfaceDeclaration { decl -> decl.name == "someName" } shouldBeEqualTo false
            hasInterfaceDeclarationOf(SampleType::class) shouldBeEqualTo false
            asTypeAliasDeclaration() shouldBeEqualTo null
            hasTypeAliasDeclaration() shouldBeEqualTo false
            hasTypeAliasDeclaration { decl -> decl.name == "someName" } shouldBeEqualTo false
            asImportAliasDeclaration() shouldBeEqualTo null
            hasImportAliasDeclaration() shouldBeEqualTo false
            hasImportAliasDeclaration { decl -> decl.name == "someName" } shouldBeEqualTo false
            asKotlinTypeDeclaration() shouldBeEqualTo null
            hasKotlinTypeDeclaration() shouldBeEqualTo false
            hasKotlinTypeDeclaration { decl -> decl.name == "someName" } shouldBeEqualTo false
            hasKotlinTypeDeclarationOf(SampleType::class) shouldBeEqualTo false
            asKotlinBasicTypeDeclaration() shouldBeEqualTo null
            hasKotlinBasicTypeDeclaration() shouldBeEqualTo false
            hasKotlinBasicTypeDeclaration { decl -> decl.name == "someName" } shouldBeEqualTo false
            hasKotlinBasicTypeDeclarationOf(SampleType::class) shouldBeEqualTo false
            asKotlinCollectionTypeDeclaration() shouldBeEqualTo null
            hasKotlinCollectionTypeDeclaration() shouldBeEqualTo false
            hasKotlinCollectionTypeDeclaration { decl -> decl.name == "someName" } shouldBeEqualTo false
            hasKotlinCollectionTypeDeclarationOf(SampleType::class) shouldBeEqualTo false
            asExternalDeclaration() shouldBeEqualTo null
            hasExternalDeclaration() shouldBeEqualTo false
            hasExternalDeclaration { decl -> decl.name == "someName" } shouldBeEqualTo false
            asTypeParameterDeclaration() shouldBeEqualTo null
            hasTypeParameterDeclaration() shouldBeEqualTo false
            hasTypeParameterDeclaration { decl -> decl.name == "someName" } shouldBeEqualTo false
        }
    }

    @Test
    fun `object-with-generic-parent-interface-from-file`() {
        // given
        val sut =
            getSnippetFile("object-with-generic-parent-interface-from-file")
                .objects()
                .parents()
                .first()

        // then
        assertSoftly(sut) {
            isClass shouldBeEqualTo false
            isObject shouldBeEqualTo false
            isInterface shouldBeEqualTo false
            isTypeAlias shouldBeEqualTo false
            isImportAlias shouldBeEqualTo false
            isKotlinType shouldBeEqualTo false
            isKotlinBasicType shouldBeEqualTo false
            isKotlinCollectionType shouldBeEqualTo false
            isTypeParameter shouldBeEqualTo false
            isExternal shouldBeEqualTo false
            asClassDeclaration() shouldBeEqualTo null
            hasClassDeclaration() shouldBeEqualTo false
            hasClassDeclaration { decl -> decl.name == "someName" } shouldBeEqualTo false
            hasClassDeclarationOf(SampleClass::class) shouldBeEqualTo false
            asObjectDeclaration() shouldBeEqualTo null
            hasObjectDeclaration() shouldBeEqualTo false
            hasObjectDeclaration { decl -> decl.name == "someName" } shouldBeEqualTo false
            hasObjectDeclarationOf(SampleType::class) shouldBeEqualTo false
            asInterfaceDeclaration() shouldBeEqualTo null
            hasInterfaceDeclaration() shouldBeEqualTo false
            hasInterfaceDeclaration { decl -> decl.name == "someName" } shouldBeEqualTo false
            hasInterfaceDeclarationOf(SampleType::class) shouldBeEqualTo false
            asTypeAliasDeclaration() shouldBeEqualTo null
            hasTypeAliasDeclaration() shouldBeEqualTo false
            hasTypeAliasDeclaration { decl -> decl.name == "someName" } shouldBeEqualTo false
            asImportAliasDeclaration() shouldBeEqualTo null
            hasImportAliasDeclaration() shouldBeEqualTo false
            hasImportAliasDeclaration { decl -> decl.name == "someName" } shouldBeEqualTo false
            asKotlinTypeDeclaration() shouldBeEqualTo null
            hasKotlinTypeDeclaration() shouldBeEqualTo false
            hasKotlinTypeDeclaration { decl -> decl.name == "someName" } shouldBeEqualTo false
            hasKotlinTypeDeclarationOf(SampleType::class) shouldBeEqualTo false
            asKotlinBasicTypeDeclaration() shouldBeEqualTo null
            hasKotlinBasicTypeDeclaration() shouldBeEqualTo false
            hasKotlinBasicTypeDeclaration { decl -> decl.name == "someName" } shouldBeEqualTo false
            hasKotlinBasicTypeDeclarationOf(SampleType::class) shouldBeEqualTo false
            asKotlinCollectionTypeDeclaration() shouldBeEqualTo null
            hasKotlinCollectionTypeDeclaration() shouldBeEqualTo false
            hasKotlinCollectionTypeDeclaration { decl -> decl.name == "someName" } shouldBeEqualTo false
            hasKotlinCollectionTypeDeclarationOf(SampleType::class) shouldBeEqualTo false
            asExternalDeclaration() shouldBeEqualTo null
            hasExternalDeclaration() shouldBeEqualTo false
            hasExternalDeclaration { decl -> decl.name == "someName" } shouldBeEqualTo false
            asTypeParameterDeclaration() shouldBeEqualTo null
            hasTypeParameterDeclaration() shouldBeEqualTo false
            hasTypeParameterDeclaration { decl -> decl.name == "someName" } shouldBeEqualTo false
        }
    }

    @Test
    fun `object-with-multiline-parent-from-file`() {
        // given
        val sut =
            getSnippetFile("object-with-multiline-parent-from-file")
                .objects()
                .first()
                .parents()
                .first()

        // then
        sut
            .name
            .shouldBeEqualTo("SomeParentClass")
    }

    @Test
    fun `object-with-parent-class-from-import`() {
        // given
        val sut =
            getSnippetFile("object-with-parent-class-from-import")
                .objects()
                .parents()
                .first()

        // then
        assertSoftly(sut) {
            isClass shouldBeEqualTo false
            isObject shouldBeEqualTo false
            isInterface shouldBeEqualTo false
            isTypeAlias shouldBeEqualTo false
            isImportAlias shouldBeEqualTo false
            isKotlinType shouldBeEqualTo false
            isKotlinBasicType shouldBeEqualTo false
            isKotlinCollectionType shouldBeEqualTo false
            isTypeParameter shouldBeEqualTo false
            isExternal shouldBeEqualTo false
            asClassDeclaration() shouldBeEqualTo null
            hasClassDeclaration() shouldBeEqualTo false
            hasClassDeclaration { decl -> decl.name == "someName" } shouldBeEqualTo false
            hasClassDeclarationOf(SampleClass::class) shouldBeEqualTo false
            asObjectDeclaration() shouldBeEqualTo null
            hasObjectDeclaration() shouldBeEqualTo false
            hasObjectDeclaration { decl -> decl.name == "someName" } shouldBeEqualTo false
            hasObjectDeclarationOf(SampleType::class) shouldBeEqualTo false
            asInterfaceDeclaration() shouldBeEqualTo null
            hasInterfaceDeclaration() shouldBeEqualTo false
            hasInterfaceDeclaration { decl -> decl.name == "someName" } shouldBeEqualTo false
            hasInterfaceDeclarationOf(SampleType::class) shouldBeEqualTo false
            asTypeAliasDeclaration() shouldBeEqualTo null
            hasTypeAliasDeclaration() shouldBeEqualTo false
            hasTypeAliasDeclaration { decl -> decl.name == "someName" } shouldBeEqualTo false
            asImportAliasDeclaration() shouldBeEqualTo null
            hasImportAliasDeclaration() shouldBeEqualTo false
            hasImportAliasDeclaration { decl -> decl.name == "someName" } shouldBeEqualTo false
            asKotlinTypeDeclaration() shouldBeEqualTo null
            hasKotlinTypeDeclaration() shouldBeEqualTo false
            hasKotlinTypeDeclaration { decl -> decl.name == "someName" } shouldBeEqualTo false
            hasKotlinTypeDeclarationOf(SampleType::class) shouldBeEqualTo false
            asKotlinBasicTypeDeclaration() shouldBeEqualTo null
            hasKotlinBasicTypeDeclaration() shouldBeEqualTo false
            hasKotlinBasicTypeDeclaration { decl -> decl.name == "someName" } shouldBeEqualTo false
            hasKotlinBasicTypeDeclarationOf(SampleType::class) shouldBeEqualTo false
            asKotlinCollectionTypeDeclaration() shouldBeEqualTo null
            hasKotlinCollectionTypeDeclaration() shouldBeEqualTo false
            hasKotlinCollectionTypeDeclaration { decl -> decl.name == "someName" } shouldBeEqualTo false
            hasKotlinCollectionTypeDeclarationOf(SampleType::class) shouldBeEqualTo false
            asExternalDeclaration() shouldBeEqualTo null
            hasExternalDeclaration() shouldBeEqualTo false
            hasExternalDeclaration { decl -> decl.name == "someName" } shouldBeEqualTo false
            asTypeParameterDeclaration() shouldBeEqualTo null
            hasTypeParameterDeclaration() shouldBeEqualTo false
            hasTypeParameterDeclaration { decl -> decl.name == "someName" } shouldBeEqualTo false
        }
    }

    @Test
    fun `object-with-generic-parent-class-from-import`() {
        // given
        val sut =
            getSnippetFile("object-with-generic-parent-class-from-import")
                .objects()
                .parents()
                .first()

        // then
        assertSoftly(sut) {
            isClass shouldBeEqualTo false
            isObject shouldBeEqualTo false
            isInterface shouldBeEqualTo false
            isTypeAlias shouldBeEqualTo false
            isImportAlias shouldBeEqualTo false
            isKotlinType shouldBeEqualTo false
            isKotlinBasicType shouldBeEqualTo false
            isKotlinCollectionType shouldBeEqualTo false
            isTypeParameter shouldBeEqualTo false
            isExternal shouldBeEqualTo false
            asClassDeclaration() shouldBeEqualTo null
            hasClassDeclaration() shouldBeEqualTo false
            hasClassDeclaration { decl -> decl.name == "someName" } shouldBeEqualTo false
            hasClassDeclarationOf(SampleClass::class) shouldBeEqualTo false
            asObjectDeclaration() shouldBeEqualTo null
            hasObjectDeclaration() shouldBeEqualTo false
            hasObjectDeclaration { decl -> decl.name == "someName" } shouldBeEqualTo false
            hasObjectDeclarationOf(SampleType::class) shouldBeEqualTo false
            asInterfaceDeclaration() shouldBeEqualTo null
            hasInterfaceDeclaration() shouldBeEqualTo false
            hasInterfaceDeclaration { decl -> decl.name == "someName" } shouldBeEqualTo false
            hasInterfaceDeclarationOf(SampleType::class) shouldBeEqualTo false
            asTypeAliasDeclaration() shouldBeEqualTo null
            hasTypeAliasDeclaration() shouldBeEqualTo false
            hasTypeAliasDeclaration { decl -> decl.name == "someName" } shouldBeEqualTo false
            asImportAliasDeclaration() shouldBeEqualTo null
            hasImportAliasDeclaration() shouldBeEqualTo false
            hasImportAliasDeclaration { decl -> decl.name == "someName" } shouldBeEqualTo false
            asKotlinTypeDeclaration() shouldBeEqualTo null
            hasKotlinTypeDeclaration() shouldBeEqualTo false
            hasKotlinTypeDeclaration { decl -> decl.name == "someName" } shouldBeEqualTo false
            hasKotlinTypeDeclarationOf(SampleType::class) shouldBeEqualTo false
            asKotlinBasicTypeDeclaration() shouldBeEqualTo null
            hasKotlinBasicTypeDeclaration() shouldBeEqualTo false
            hasKotlinBasicTypeDeclaration { decl -> decl.name == "someName" } shouldBeEqualTo false
            hasKotlinBasicTypeDeclarationOf(SampleType::class) shouldBeEqualTo false
            asKotlinCollectionTypeDeclaration() shouldBeEqualTo null
            hasKotlinCollectionTypeDeclaration() shouldBeEqualTo false
            hasKotlinCollectionTypeDeclaration { decl -> decl.name == "someName" } shouldBeEqualTo false
            hasKotlinCollectionTypeDeclarationOf(SampleType::class) shouldBeEqualTo false
            asExternalDeclaration() shouldBeEqualTo null
            hasExternalDeclaration() shouldBeEqualTo false
            hasExternalDeclaration { decl -> decl.name == "someName" } shouldBeEqualTo false
            asTypeParameterDeclaration() shouldBeEqualTo null
            hasTypeParameterDeclaration() shouldBeEqualTo false
            hasTypeParameterDeclaration { decl -> decl.name == "someName" } shouldBeEqualTo false
        }
    }

    @Test
    fun `object-with-parametrized-parent-class-from-import`() {
        // given
        val sut =
            getSnippetFile("object-with-parametrized-parent-class-from-import")
                .objects()
                .parents()
                .first()

        // then
        assertSoftly(sut) {
            isClass shouldBeEqualTo false
            isObject shouldBeEqualTo false
            isInterface shouldBeEqualTo false
            isTypeAlias shouldBeEqualTo false
            isImportAlias shouldBeEqualTo false
            isKotlinType shouldBeEqualTo false
            isKotlinBasicType shouldBeEqualTo false
            isKotlinCollectionType shouldBeEqualTo false
            isTypeParameter shouldBeEqualTo false
            isExternal shouldBeEqualTo false
            asClassDeclaration() shouldBeEqualTo null
            hasClassDeclaration() shouldBeEqualTo false
            hasClassDeclaration { decl -> decl.name == "someName" } shouldBeEqualTo false
            hasClassDeclarationOf(SampleClass::class) shouldBeEqualTo false
            asObjectDeclaration() shouldBeEqualTo null
            hasObjectDeclaration() shouldBeEqualTo false
            hasObjectDeclaration { decl -> decl.name == "someName" } shouldBeEqualTo false
            hasObjectDeclarationOf(SampleType::class) shouldBeEqualTo false
            asInterfaceDeclaration() shouldBeEqualTo null
            hasInterfaceDeclaration() shouldBeEqualTo false
            hasInterfaceDeclaration { decl -> decl.name == "someName" } shouldBeEqualTo false
            hasInterfaceDeclarationOf(SampleType::class) shouldBeEqualTo false
            asTypeAliasDeclaration() shouldBeEqualTo null
            hasTypeAliasDeclaration() shouldBeEqualTo false
            hasTypeAliasDeclaration { decl -> decl.name == "someName" } shouldBeEqualTo false
            asImportAliasDeclaration() shouldBeEqualTo null
            hasImportAliasDeclaration() shouldBeEqualTo false
            hasImportAliasDeclaration { decl -> decl.name == "someName" } shouldBeEqualTo false
            asKotlinTypeDeclaration() shouldBeEqualTo null
            hasKotlinTypeDeclaration() shouldBeEqualTo false
            hasKotlinTypeDeclaration { decl -> decl.name == "someName" } shouldBeEqualTo false
            hasKotlinTypeDeclarationOf(SampleType::class) shouldBeEqualTo false
            asKotlinBasicTypeDeclaration() shouldBeEqualTo null
            hasKotlinBasicTypeDeclaration() shouldBeEqualTo false
            hasKotlinBasicTypeDeclaration { decl -> decl.name == "someName" } shouldBeEqualTo false
            hasKotlinBasicTypeDeclarationOf(SampleType::class) shouldBeEqualTo false
            asKotlinCollectionTypeDeclaration() shouldBeEqualTo null
            hasKotlinCollectionTypeDeclaration() shouldBeEqualTo false
            hasKotlinCollectionTypeDeclaration { decl -> decl.name == "someName" } shouldBeEqualTo false
            hasKotlinCollectionTypeDeclarationOf(SampleType::class) shouldBeEqualTo false
            asExternalDeclaration() shouldBeEqualTo null
            hasExternalDeclaration() shouldBeEqualTo false
            hasExternalDeclaration { decl -> decl.name == "someName" } shouldBeEqualTo false
            asTypeParameterDeclaration() shouldBeEqualTo null
            hasTypeParameterDeclaration() shouldBeEqualTo false
            hasTypeParameterDeclaration { decl -> decl.name == "someName" } shouldBeEqualTo false
        }
    }

    @Test
    fun `object-with-parametrized-and-generic-parent-class-from-import`() {
        // given
        val sut =
            getSnippetFile("object-with-parametrized-and-generic-parent-class-from-import")
                .objects()
                .parents()
                .first()

        // then
        assertSoftly(sut) {
            isClass shouldBeEqualTo false
            isObject shouldBeEqualTo false
            isInterface shouldBeEqualTo false
            isTypeAlias shouldBeEqualTo false
            isImportAlias shouldBeEqualTo false
            isKotlinType shouldBeEqualTo false
            isKotlinBasicType shouldBeEqualTo false
            isKotlinCollectionType shouldBeEqualTo false
            isTypeParameter shouldBeEqualTo false
            isExternal shouldBeEqualTo false
            asClassDeclaration() shouldBeEqualTo null
            hasClassDeclaration() shouldBeEqualTo false
            hasClassDeclaration { decl -> decl.name == "someName" } shouldBeEqualTo false
            hasClassDeclarationOf(SampleClass::class) shouldBeEqualTo false
            asObjectDeclaration() shouldBeEqualTo null
            hasObjectDeclaration() shouldBeEqualTo false
            hasObjectDeclaration { decl -> decl.name == "someName" } shouldBeEqualTo false
            hasObjectDeclarationOf(SampleType::class) shouldBeEqualTo false
            asInterfaceDeclaration() shouldBeEqualTo null
            hasInterfaceDeclaration() shouldBeEqualTo false
            hasInterfaceDeclaration { decl -> decl.name == "someName" } shouldBeEqualTo false
            hasInterfaceDeclarationOf(SampleType::class) shouldBeEqualTo false
            asTypeAliasDeclaration() shouldBeEqualTo null
            hasTypeAliasDeclaration() shouldBeEqualTo false
            hasTypeAliasDeclaration { decl -> decl.name == "someName" } shouldBeEqualTo false
            asImportAliasDeclaration() shouldBeEqualTo null
            hasImportAliasDeclaration() shouldBeEqualTo false
            hasImportAliasDeclaration { decl -> decl.name == "someName" } shouldBeEqualTo false
            asKotlinTypeDeclaration() shouldBeEqualTo null
            hasKotlinTypeDeclaration() shouldBeEqualTo false
            hasKotlinTypeDeclaration { decl -> decl.name == "someName" } shouldBeEqualTo false
            hasKotlinTypeDeclarationOf(SampleType::class) shouldBeEqualTo false
            asKotlinBasicTypeDeclaration() shouldBeEqualTo null
            hasKotlinBasicTypeDeclaration() shouldBeEqualTo false
            hasKotlinBasicTypeDeclaration { decl -> decl.name == "someName" } shouldBeEqualTo false
            hasKotlinBasicTypeDeclarationOf(SampleType::class) shouldBeEqualTo false
            asKotlinCollectionTypeDeclaration() shouldBeEqualTo null
            hasKotlinCollectionTypeDeclaration() shouldBeEqualTo false
            hasKotlinCollectionTypeDeclaration { decl -> decl.name == "someName" } shouldBeEqualTo false
            hasKotlinCollectionTypeDeclarationOf(SampleType::class) shouldBeEqualTo false
            asExternalDeclaration() shouldBeEqualTo null
            hasExternalDeclaration() shouldBeEqualTo false
            hasExternalDeclaration { decl -> decl.name == "someName" } shouldBeEqualTo false
            asTypeParameterDeclaration() shouldBeEqualTo null
            hasTypeParameterDeclaration() shouldBeEqualTo false
            hasTypeParameterDeclaration { decl -> decl.name == "someName" } shouldBeEqualTo false
        }
    }

    @Test
    fun `object-with-parent-interface-from-import`() {
        // given
        val sut =
            getSnippetFile("object-with-parent-interface-from-import")
                .objects()
                .parents()
                .first()

        // then
        assertSoftly(sut) {
            isClass shouldBeEqualTo false
            isObject shouldBeEqualTo false
            isInterface shouldBeEqualTo false
            isTypeAlias shouldBeEqualTo false
            isImportAlias shouldBeEqualTo false
            isKotlinType shouldBeEqualTo false
            isKotlinBasicType shouldBeEqualTo false
            isKotlinCollectionType shouldBeEqualTo false
            isTypeParameter shouldBeEqualTo false
            isExternal shouldBeEqualTo false
            asClassDeclaration() shouldBeEqualTo null
            hasClassDeclaration() shouldBeEqualTo false
            hasClassDeclaration { decl -> decl.name == "someName" } shouldBeEqualTo false
            hasClassDeclarationOf(SampleClass::class) shouldBeEqualTo false
            asObjectDeclaration() shouldBeEqualTo null
            hasObjectDeclaration() shouldBeEqualTo false
            hasObjectDeclaration { decl -> decl.name == "someName" } shouldBeEqualTo false
            hasObjectDeclarationOf(SampleType::class) shouldBeEqualTo false
            asInterfaceDeclaration() shouldBeEqualTo null
            hasInterfaceDeclaration() shouldBeEqualTo false
            hasInterfaceDeclaration { decl -> decl.name == "someName" } shouldBeEqualTo false
            hasInterfaceDeclarationOf(SampleType::class) shouldBeEqualTo false
            asTypeAliasDeclaration() shouldBeEqualTo null
            hasTypeAliasDeclaration() shouldBeEqualTo false
            hasTypeAliasDeclaration { decl -> decl.name == "someName" } shouldBeEqualTo false
            asImportAliasDeclaration() shouldBeEqualTo null
            hasImportAliasDeclaration() shouldBeEqualTo false
            hasImportAliasDeclaration { decl -> decl.name == "someName" } shouldBeEqualTo false
            asKotlinTypeDeclaration() shouldBeEqualTo null
            hasKotlinTypeDeclaration() shouldBeEqualTo false
            hasKotlinTypeDeclaration { decl -> decl.name == "someName" } shouldBeEqualTo false
            hasKotlinTypeDeclarationOf(SampleType::class) shouldBeEqualTo false
            asKotlinBasicTypeDeclaration() shouldBeEqualTo null
            hasKotlinBasicTypeDeclaration() shouldBeEqualTo false
            hasKotlinBasicTypeDeclaration { decl -> decl.name == "someName" } shouldBeEqualTo false
            hasKotlinBasicTypeDeclarationOf(SampleType::class) shouldBeEqualTo false
            asKotlinCollectionTypeDeclaration() shouldBeEqualTo null
            hasKotlinCollectionTypeDeclaration() shouldBeEqualTo false
            hasKotlinCollectionTypeDeclaration { decl -> decl.name == "someName" } shouldBeEqualTo false
            hasKotlinCollectionTypeDeclarationOf(SampleType::class) shouldBeEqualTo false
            asExternalDeclaration() shouldBeEqualTo null
            hasExternalDeclaration() shouldBeEqualTo false
            hasExternalDeclaration { decl -> decl.name == "someName" } shouldBeEqualTo false
            asTypeParameterDeclaration() shouldBeEqualTo null
            hasTypeParameterDeclaration() shouldBeEqualTo false
            hasTypeParameterDeclaration { decl -> decl.name == "someName" } shouldBeEqualTo false
        }
    }

    @Test
    fun `object-with-generic-parent-interface-from-import`() {
        // given
        val sut =
            getSnippetFile("object-with-generic-parent-interface-from-import")
                .objects()
                .parents()
                .first()

        // then
        assertSoftly(sut) {
            isClass shouldBeEqualTo false
            isObject shouldBeEqualTo false
            isInterface shouldBeEqualTo false
            isTypeAlias shouldBeEqualTo false
            isImportAlias shouldBeEqualTo false
            isKotlinType shouldBeEqualTo false
            isKotlinBasicType shouldBeEqualTo false
            isKotlinCollectionType shouldBeEqualTo false
            isTypeParameter shouldBeEqualTo false
            isExternal shouldBeEqualTo false
            asClassDeclaration() shouldBeEqualTo null
            hasClassDeclaration() shouldBeEqualTo false
            hasClassDeclaration { decl -> decl.name == "someName" } shouldBeEqualTo false
            hasClassDeclarationOf(SampleClass::class) shouldBeEqualTo false
            asObjectDeclaration() shouldBeEqualTo null
            hasObjectDeclaration() shouldBeEqualTo false
            hasObjectDeclaration { decl -> decl.name == "someName" } shouldBeEqualTo false
            hasObjectDeclarationOf(SampleType::class) shouldBeEqualTo false
            asInterfaceDeclaration() shouldBeEqualTo null
            hasInterfaceDeclaration() shouldBeEqualTo false
            hasInterfaceDeclaration { decl -> decl.name == "someName" } shouldBeEqualTo false
            hasInterfaceDeclarationOf(SampleType::class) shouldBeEqualTo false
            asTypeAliasDeclaration() shouldBeEqualTo null
            hasTypeAliasDeclaration() shouldBeEqualTo false
            hasTypeAliasDeclaration { decl -> decl.name == "someName" } shouldBeEqualTo false
            asImportAliasDeclaration() shouldBeEqualTo null
            hasImportAliasDeclaration() shouldBeEqualTo false
            hasImportAliasDeclaration { decl -> decl.name == "someName" } shouldBeEqualTo false
            asKotlinTypeDeclaration() shouldBeEqualTo null
            hasKotlinTypeDeclaration() shouldBeEqualTo false
            hasKotlinTypeDeclaration { decl -> decl.name == "someName" } shouldBeEqualTo false
            hasKotlinTypeDeclarationOf(SampleType::class) shouldBeEqualTo false
            asKotlinBasicTypeDeclaration() shouldBeEqualTo null
            hasKotlinBasicTypeDeclaration() shouldBeEqualTo false
            hasKotlinBasicTypeDeclaration { decl -> decl.name == "someName" } shouldBeEqualTo false
            hasKotlinBasicTypeDeclarationOf(SampleType::class) shouldBeEqualTo false
            asKotlinCollectionTypeDeclaration() shouldBeEqualTo null
            hasKotlinCollectionTypeDeclaration() shouldBeEqualTo false
            hasKotlinCollectionTypeDeclaration { decl -> decl.name == "someName" } shouldBeEqualTo false
            hasKotlinCollectionTypeDeclarationOf(SampleType::class) shouldBeEqualTo false
            asExternalDeclaration() shouldBeEqualTo null
            hasExternalDeclaration() shouldBeEqualTo false
            hasExternalDeclaration { decl -> decl.name == "someName" } shouldBeEqualTo false
            asTypeParameterDeclaration() shouldBeEqualTo null
            hasTypeParameterDeclaration() shouldBeEqualTo false
            hasTypeParameterDeclaration { decl -> decl.name == "someName" } shouldBeEqualTo false
        }
    }

    @Test
    fun `object-with-multiline-parent-from-import`() {
        // given
        val sut =
            getSnippetFile("object-with-multiline-parent-from-import")
                .objects()
                .first()
                .parents()
                .first()

        // then
        sut
            .name
            .shouldBeEqualTo("SampleClassWithParameter")
    }

    @Test
    fun `object-with-external-parent-class`() {
        // given
        val sut =
            getSnippetFile("object-with-external-parent-class")
                .objects()
                .parents()
                .first()

        // then
        assertSoftly(sut) {
            isClass shouldBeEqualTo false
            isObject shouldBeEqualTo false
            isInterface shouldBeEqualTo false
            isTypeAlias shouldBeEqualTo false
            isImportAlias shouldBeEqualTo false
            isKotlinType shouldBeEqualTo false
            isKotlinBasicType shouldBeEqualTo false
            isKotlinCollectionType shouldBeEqualTo false
            isTypeParameter shouldBeEqualTo false
            isExternal shouldBeEqualTo false
            asClassDeclaration() shouldBeEqualTo null
            hasClassDeclaration() shouldBeEqualTo false
            hasClassDeclaration { decl -> decl.name == "someName" } shouldBeEqualTo false
            hasClassDeclarationOf(SampleClass::class) shouldBeEqualTo false
            asObjectDeclaration() shouldBeEqualTo null
            hasObjectDeclaration() shouldBeEqualTo false
            hasObjectDeclaration { decl -> decl.name == "someName" } shouldBeEqualTo false
            hasObjectDeclarationOf(SampleType::class) shouldBeEqualTo false
            asInterfaceDeclaration() shouldBeEqualTo null
            hasInterfaceDeclaration() shouldBeEqualTo false
            hasInterfaceDeclaration { decl -> decl.name == "someName" } shouldBeEqualTo false
            hasInterfaceDeclarationOf(SampleType::class) shouldBeEqualTo false
            asTypeAliasDeclaration() shouldBeEqualTo null
            hasTypeAliasDeclaration() shouldBeEqualTo false
            hasTypeAliasDeclaration { decl -> decl.name == "someName" } shouldBeEqualTo false
            asImportAliasDeclaration() shouldBeEqualTo null
            hasImportAliasDeclaration() shouldBeEqualTo false
            hasImportAliasDeclaration { decl -> decl.name == "someName" } shouldBeEqualTo false
            asKotlinTypeDeclaration() shouldBeEqualTo null
            hasKotlinTypeDeclaration() shouldBeEqualTo false
            hasKotlinTypeDeclaration { decl -> decl.name == "someName" } shouldBeEqualTo false
            hasKotlinTypeDeclarationOf(SampleType::class) shouldBeEqualTo false
            asKotlinBasicTypeDeclaration() shouldBeEqualTo null
            hasKotlinBasicTypeDeclaration() shouldBeEqualTo false
            hasKotlinBasicTypeDeclaration { decl -> decl.name == "someName" } shouldBeEqualTo false
            hasKotlinBasicTypeDeclarationOf(SampleType::class) shouldBeEqualTo false
            asKotlinCollectionTypeDeclaration() shouldBeEqualTo null
            hasKotlinCollectionTypeDeclaration() shouldBeEqualTo false
            hasKotlinCollectionTypeDeclaration { decl -> decl.name == "someName" } shouldBeEqualTo false
            hasKotlinCollectionTypeDeclarationOf(SampleType::class) shouldBeEqualTo false
            asExternalDeclaration() shouldBeEqualTo null
            hasExternalDeclaration() shouldBeEqualTo false
            hasExternalDeclaration { decl -> decl.name == "someName" } shouldBeEqualTo false
            asTypeParameterDeclaration() shouldBeEqualTo null
            hasTypeParameterDeclaration() shouldBeEqualTo false
            hasTypeParameterDeclaration { decl -> decl.name == "someName" } shouldBeEqualTo false
        }
    }

    @Test
    fun `object-with-generic-external-parent-class`() {
        // given
        val sut =
            getSnippetFile("object-with-generic-external-parent-class")
                .objects()
                .parents()
                .first()

        // then
        assertSoftly(sut) {
            isClass shouldBeEqualTo false
            isObject shouldBeEqualTo false
            isInterface shouldBeEqualTo false
            isTypeAlias shouldBeEqualTo false
            isImportAlias shouldBeEqualTo false
            isKotlinType shouldBeEqualTo false
            isKotlinBasicType shouldBeEqualTo false
            isKotlinCollectionType shouldBeEqualTo false
            isTypeParameter shouldBeEqualTo false
            isExternal shouldBeEqualTo false
            asClassDeclaration() shouldBeEqualTo null
            hasClassDeclaration() shouldBeEqualTo false
            hasClassDeclaration { decl -> decl.name == "someName" } shouldBeEqualTo false
            hasClassDeclarationOf(SampleClass::class) shouldBeEqualTo false
            asObjectDeclaration() shouldBeEqualTo null
            hasObjectDeclaration() shouldBeEqualTo false
            hasObjectDeclaration { decl -> decl.name == "someName" } shouldBeEqualTo false
            hasObjectDeclarationOf(SampleType::class) shouldBeEqualTo false
            asInterfaceDeclaration() shouldBeEqualTo null
            hasInterfaceDeclaration() shouldBeEqualTo false
            hasInterfaceDeclaration { decl -> decl.name == "someName" } shouldBeEqualTo false
            hasInterfaceDeclarationOf(SampleType::class) shouldBeEqualTo false
            asTypeAliasDeclaration() shouldBeEqualTo null
            hasTypeAliasDeclaration() shouldBeEqualTo false
            hasTypeAliasDeclaration { decl -> decl.name == "someName" } shouldBeEqualTo false
            asImportAliasDeclaration() shouldBeEqualTo null
            hasImportAliasDeclaration() shouldBeEqualTo false
            hasImportAliasDeclaration { decl -> decl.name == "someName" } shouldBeEqualTo false
            asKotlinTypeDeclaration() shouldBeEqualTo null
            hasKotlinTypeDeclaration() shouldBeEqualTo false
            hasKotlinTypeDeclaration { decl -> decl.name == "someName" } shouldBeEqualTo false
            hasKotlinTypeDeclarationOf(SampleType::class) shouldBeEqualTo false
            asKotlinBasicTypeDeclaration() shouldBeEqualTo null
            hasKotlinBasicTypeDeclaration() shouldBeEqualTo false
            hasKotlinBasicTypeDeclaration { decl -> decl.name == "someName" } shouldBeEqualTo false
            hasKotlinBasicTypeDeclarationOf(SampleType::class) shouldBeEqualTo false
            asKotlinCollectionTypeDeclaration() shouldBeEqualTo null
            hasKotlinCollectionTypeDeclaration() shouldBeEqualTo false
            hasKotlinCollectionTypeDeclaration { decl -> decl.name == "someName" } shouldBeEqualTo false
            hasKotlinCollectionTypeDeclarationOf(SampleType::class) shouldBeEqualTo false
            asExternalDeclaration() shouldBeEqualTo null
            hasExternalDeclaration() shouldBeEqualTo false
            hasExternalDeclaration { decl -> decl.name == "someName" } shouldBeEqualTo false
            asTypeParameterDeclaration() shouldBeEqualTo null
            hasTypeParameterDeclaration() shouldBeEqualTo false
            hasTypeParameterDeclaration { decl -> decl.name == "someName" } shouldBeEqualTo false
        }
    }

    @Test
    fun `object-with-parametrized-external-parent-class`() {
        // given
        val sut =
            getSnippetFile("object-with-parametrized-external-parent-class")
                .objects()
                .parents()
                .first()

        // then
        assertSoftly(sut) {
            isClass shouldBeEqualTo false
            isObject shouldBeEqualTo false
            isInterface shouldBeEqualTo false
            isTypeAlias shouldBeEqualTo false
            isImportAlias shouldBeEqualTo false
            isKotlinType shouldBeEqualTo false
            isKotlinBasicType shouldBeEqualTo false
            isKotlinCollectionType shouldBeEqualTo false
            isTypeParameter shouldBeEqualTo false
            isExternal shouldBeEqualTo false
            asClassDeclaration() shouldBeEqualTo null
            hasClassDeclaration() shouldBeEqualTo false
            hasClassDeclaration { decl -> decl.name == "someName" } shouldBeEqualTo false
            hasClassDeclarationOf(SampleClass::class) shouldBeEqualTo false
            asObjectDeclaration() shouldBeEqualTo null
            hasObjectDeclaration() shouldBeEqualTo false
            hasObjectDeclaration { decl -> decl.name == "someName" } shouldBeEqualTo false
            hasObjectDeclarationOf(SampleType::class) shouldBeEqualTo false
            asInterfaceDeclaration() shouldBeEqualTo null
            hasInterfaceDeclaration() shouldBeEqualTo false
            hasInterfaceDeclaration { decl -> decl.name == "someName" } shouldBeEqualTo false
            hasInterfaceDeclarationOf(SampleType::class) shouldBeEqualTo false
            asTypeAliasDeclaration() shouldBeEqualTo null
            hasTypeAliasDeclaration() shouldBeEqualTo false
            hasTypeAliasDeclaration { decl -> decl.name == "someName" } shouldBeEqualTo false
            asImportAliasDeclaration() shouldBeEqualTo null
            hasImportAliasDeclaration() shouldBeEqualTo false
            hasImportAliasDeclaration { decl -> decl.name == "someName" } shouldBeEqualTo false
            asKotlinTypeDeclaration() shouldBeEqualTo null
            hasKotlinTypeDeclaration() shouldBeEqualTo false
            hasKotlinTypeDeclaration { decl -> decl.name == "someName" } shouldBeEqualTo false
            hasKotlinTypeDeclarationOf(SampleType::class) shouldBeEqualTo false
            asKotlinBasicTypeDeclaration() shouldBeEqualTo null
            hasKotlinBasicTypeDeclaration() shouldBeEqualTo false
            hasKotlinBasicTypeDeclaration { decl -> decl.name == "someName" } shouldBeEqualTo false
            hasKotlinBasicTypeDeclarationOf(SampleType::class) shouldBeEqualTo false
            asKotlinCollectionTypeDeclaration() shouldBeEqualTo null
            hasKotlinCollectionTypeDeclaration() shouldBeEqualTo false
            hasKotlinCollectionTypeDeclaration { decl -> decl.name == "someName" } shouldBeEqualTo false
            hasKotlinCollectionTypeDeclarationOf(SampleType::class) shouldBeEqualTo false
            asExternalDeclaration() shouldBeEqualTo null
            hasExternalDeclaration() shouldBeEqualTo false
            hasExternalDeclaration { decl -> decl.name == "someName" } shouldBeEqualTo false
            asTypeParameterDeclaration() shouldBeEqualTo null
            hasTypeParameterDeclaration() shouldBeEqualTo false
            hasTypeParameterDeclaration { decl -> decl.name == "someName" } shouldBeEqualTo false
        }
    }

    @Test
    fun `object-with-parametrized-and-generic-external-parent-class`() {
        // given
        val sut =
            getSnippetFile("object-with-parametrized-and-generic-external-parent-class")
                .objects()
                .parents()
                .first()

        // then
        assertSoftly(sut) {
            isClass shouldBeEqualTo false
            isObject shouldBeEqualTo false
            isInterface shouldBeEqualTo false
            isTypeAlias shouldBeEqualTo false
            isImportAlias shouldBeEqualTo false
            isKotlinType shouldBeEqualTo false
            isKotlinBasicType shouldBeEqualTo false
            isKotlinCollectionType shouldBeEqualTo false
            isTypeParameter shouldBeEqualTo false
            isExternal shouldBeEqualTo false
            asClassDeclaration() shouldBeEqualTo null
            hasClassDeclaration() shouldBeEqualTo false
            hasClassDeclaration { decl -> decl.name == "someName" } shouldBeEqualTo false
            hasClassDeclarationOf(SampleClass::class) shouldBeEqualTo false
            asObjectDeclaration() shouldBeEqualTo null
            hasObjectDeclaration() shouldBeEqualTo false
            hasObjectDeclaration { decl -> decl.name == "someName" } shouldBeEqualTo false
            hasObjectDeclarationOf(SampleType::class) shouldBeEqualTo false
            asInterfaceDeclaration() shouldBeEqualTo null
            hasInterfaceDeclaration() shouldBeEqualTo false
            hasInterfaceDeclaration { decl -> decl.name == "someName" } shouldBeEqualTo false
            hasInterfaceDeclarationOf(SampleType::class) shouldBeEqualTo false
            asTypeAliasDeclaration() shouldBeEqualTo null
            hasTypeAliasDeclaration() shouldBeEqualTo false
            hasTypeAliasDeclaration { decl -> decl.name == "someName" } shouldBeEqualTo false
            asImportAliasDeclaration() shouldBeEqualTo null
            hasImportAliasDeclaration() shouldBeEqualTo false
            hasImportAliasDeclaration { decl -> decl.name == "someName" } shouldBeEqualTo false
            asKotlinTypeDeclaration() shouldBeEqualTo null
            hasKotlinTypeDeclaration() shouldBeEqualTo false
            hasKotlinTypeDeclaration { decl -> decl.name == "someName" } shouldBeEqualTo false
            hasKotlinTypeDeclarationOf(SampleType::class) shouldBeEqualTo false
            asKotlinBasicTypeDeclaration() shouldBeEqualTo null
            hasKotlinBasicTypeDeclaration() shouldBeEqualTo false
            hasKotlinBasicTypeDeclaration { decl -> decl.name == "someName" } shouldBeEqualTo false
            hasKotlinBasicTypeDeclarationOf(SampleType::class) shouldBeEqualTo false
            asKotlinCollectionTypeDeclaration() shouldBeEqualTo null
            hasKotlinCollectionTypeDeclaration() shouldBeEqualTo false
            hasKotlinCollectionTypeDeclaration { decl -> decl.name == "someName" } shouldBeEqualTo false
            hasKotlinCollectionTypeDeclarationOf(SampleType::class) shouldBeEqualTo false
            asExternalDeclaration() shouldBeEqualTo null
            hasExternalDeclaration() shouldBeEqualTo false
            hasExternalDeclaration { decl -> decl.name == "someName" } shouldBeEqualTo false
            asTypeParameterDeclaration() shouldBeEqualTo null
            hasTypeParameterDeclaration() shouldBeEqualTo false
            hasTypeParameterDeclaration { decl -> decl.name == "someName" } shouldBeEqualTo false
        }
    }

    @Test
    fun `object-with-external-parent-interface`() {
        // given
        val sut =
            getSnippetFile("object-with-external-parent-interface")
                .objects()
                .parents()
                .first()

        // then
        assertSoftly(sut) {
            isClass shouldBeEqualTo false
            isObject shouldBeEqualTo false
            isInterface shouldBeEqualTo false
            isTypeAlias shouldBeEqualTo false
            isImportAlias shouldBeEqualTo false
            isKotlinType shouldBeEqualTo false
            isKotlinBasicType shouldBeEqualTo false
            isKotlinCollectionType shouldBeEqualTo false
            isTypeParameter shouldBeEqualTo false
            isExternal shouldBeEqualTo false
            asClassDeclaration() shouldBeEqualTo null
            hasClassDeclaration() shouldBeEqualTo false
            hasClassDeclaration { decl -> decl.name == "someName" } shouldBeEqualTo false
            hasClassDeclarationOf(SampleClass::class) shouldBeEqualTo false
            asObjectDeclaration() shouldBeEqualTo null
            hasObjectDeclaration() shouldBeEqualTo false
            hasObjectDeclaration { decl -> decl.name == "someName" } shouldBeEqualTo false
            hasObjectDeclarationOf(SampleType::class) shouldBeEqualTo false
            asInterfaceDeclaration() shouldBeEqualTo null
            hasInterfaceDeclaration() shouldBeEqualTo false
            hasInterfaceDeclaration { decl -> decl.name == "someName" } shouldBeEqualTo false
            hasInterfaceDeclarationOf(SampleType::class) shouldBeEqualTo false
            asTypeAliasDeclaration() shouldBeEqualTo null
            hasTypeAliasDeclaration() shouldBeEqualTo false
            hasTypeAliasDeclaration { decl -> decl.name == "someName" } shouldBeEqualTo false
            asImportAliasDeclaration() shouldBeEqualTo null
            hasImportAliasDeclaration() shouldBeEqualTo false
            hasImportAliasDeclaration { decl -> decl.name == "someName" } shouldBeEqualTo false
            asKotlinTypeDeclaration() shouldBeEqualTo null
            hasKotlinTypeDeclaration() shouldBeEqualTo false
            hasKotlinTypeDeclaration { decl -> decl.name == "someName" } shouldBeEqualTo false
            hasKotlinTypeDeclarationOf(SampleType::class) shouldBeEqualTo false
            asKotlinBasicTypeDeclaration() shouldBeEqualTo null
            hasKotlinBasicTypeDeclaration() shouldBeEqualTo false
            hasKotlinBasicTypeDeclaration { decl -> decl.name == "someName" } shouldBeEqualTo false
            hasKotlinBasicTypeDeclarationOf(SampleType::class) shouldBeEqualTo false
            asKotlinCollectionTypeDeclaration() shouldBeEqualTo null
            hasKotlinCollectionTypeDeclaration() shouldBeEqualTo false
            hasKotlinCollectionTypeDeclaration { decl -> decl.name == "someName" } shouldBeEqualTo false
            hasKotlinCollectionTypeDeclarationOf(SampleType::class) shouldBeEqualTo false
            asExternalDeclaration() shouldBeEqualTo null
            hasExternalDeclaration() shouldBeEqualTo false
            hasExternalDeclaration { decl -> decl.name == "someName" } shouldBeEqualTo false
            asTypeParameterDeclaration() shouldBeEqualTo null
            hasTypeParameterDeclaration() shouldBeEqualTo false
            hasTypeParameterDeclaration { decl -> decl.name == "someName" } shouldBeEqualTo false
        }
    }

    @Test
    fun `object-with-generic-external-parent-interface`() {
        // given
        val sut =
            getSnippetFile("object-with-generic-external-parent-interface")
                .objects()
                .parents()
                .first()

        // then
        assertSoftly(sut) {
            isClass shouldBeEqualTo false
            isObject shouldBeEqualTo false
            isInterface shouldBeEqualTo false
            isTypeAlias shouldBeEqualTo false
            isImportAlias shouldBeEqualTo false
            isKotlinType shouldBeEqualTo false
            isKotlinBasicType shouldBeEqualTo false
            isKotlinCollectionType shouldBeEqualTo false
            isTypeParameter shouldBeEqualTo false
            isExternal shouldBeEqualTo false
            asClassDeclaration() shouldBeEqualTo null
            hasClassDeclaration() shouldBeEqualTo false
            hasClassDeclaration { decl -> decl.name == "someName" } shouldBeEqualTo false
            hasClassDeclarationOf(SampleClass::class) shouldBeEqualTo false
            asObjectDeclaration() shouldBeEqualTo null
            hasObjectDeclaration() shouldBeEqualTo false
            hasObjectDeclaration { decl -> decl.name == "someName" } shouldBeEqualTo false
            hasObjectDeclarationOf(SampleType::class) shouldBeEqualTo false
            asInterfaceDeclaration() shouldBeEqualTo null
            hasInterfaceDeclaration() shouldBeEqualTo false
            hasInterfaceDeclaration { decl -> decl.name == "someName" } shouldBeEqualTo false
            hasInterfaceDeclarationOf(SampleType::class) shouldBeEqualTo false
            asTypeAliasDeclaration() shouldBeEqualTo null
            hasTypeAliasDeclaration() shouldBeEqualTo false
            hasTypeAliasDeclaration { decl -> decl.name == "someName" } shouldBeEqualTo false
            asImportAliasDeclaration() shouldBeEqualTo null
            hasImportAliasDeclaration() shouldBeEqualTo false
            hasImportAliasDeclaration { decl -> decl.name == "someName" } shouldBeEqualTo false
            asKotlinTypeDeclaration() shouldBeEqualTo null
            hasKotlinTypeDeclaration() shouldBeEqualTo false
            hasKotlinTypeDeclaration { decl -> decl.name == "someName" } shouldBeEqualTo false
            hasKotlinTypeDeclarationOf(SampleType::class) shouldBeEqualTo false
            asKotlinBasicTypeDeclaration() shouldBeEqualTo null
            hasKotlinBasicTypeDeclaration() shouldBeEqualTo false
            hasKotlinBasicTypeDeclaration { decl -> decl.name == "someName" } shouldBeEqualTo false
            hasKotlinBasicTypeDeclarationOf(SampleType::class) shouldBeEqualTo false
            asKotlinCollectionTypeDeclaration() shouldBeEqualTo null
            hasKotlinCollectionTypeDeclaration() shouldBeEqualTo false
            hasKotlinCollectionTypeDeclaration { decl -> decl.name == "someName" } shouldBeEqualTo false
            hasKotlinCollectionTypeDeclarationOf(SampleType::class) shouldBeEqualTo false
            asExternalDeclaration() shouldBeEqualTo null
            hasExternalDeclaration() shouldBeEqualTo false
            hasExternalDeclaration { decl -> decl.name == "someName" } shouldBeEqualTo false
            asTypeParameterDeclaration() shouldBeEqualTo null
            hasTypeParameterDeclaration() shouldBeEqualTo false
            hasTypeParameterDeclaration { decl -> decl.name == "someName" } shouldBeEqualTo false
        }
    }

    @Test
    fun `object-with-multiline-external-parent`() {
        // given
        val sut =
            getSnippetFile("object-with-multiline-external-parent")
                .objects()
                .first()
                .parents()
                .first()

        // then
        sut
            .name
            .shouldBeEqualTo("SampleExternalClassWithParameter")
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("core/declaration/koparent/snippet/forkodeclarationcastprovider/", fileName)
}
