package com.lemonappdev.konsist.core.declaration.koparent

import com.lemonappdev.konsist.TestSnippetProvider
import com.lemonappdev.konsist.api.declaration.KoClassDeclaration
import com.lemonappdev.konsist.api.declaration.KoInterfaceDeclaration
import com.lemonappdev.konsist.api.ext.list.parents
import com.lemonappdev.konsist.testdata.SampleClass
import com.lemonappdev.konsist.testdata.SampleInterface
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
    fun `class-with-parametrized-and-generic-parent-class-from-file`() {
        // given
        val sut =
            getSnippetFile("class-with-parametrized-and-generic-parent-class-from-file")
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
            hasObjectDeclaration { decl -> decl.name == "someName" } shouldBeEqualTo false
            hasObjectDeclarationOf(SampleType::class) shouldBeEqualTo false
            asInterfaceDeclaration() shouldBeInstanceOf KoInterfaceDeclaration::class
            hasInterfaceDeclaration() shouldBeEqualTo true
            hasInterfaceDeclaration { decl -> decl.name == "SampleSuperInterface" } shouldBeEqualTo true
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
    fun `class-with-multiline-parent-from-file`() {
        // given
        val sut =
            getSnippetFile("class-with-multiline-parent-from-file")
                .classes()
                .first()
                .parents()
                .first()

        // then
        sut
            .name
            .shouldBeEqualTo("SomeParentClass")
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
    fun `class-with-generic-parent-class-from-import`() {
        // given
        val sut =
            getSnippetFile("class-with-generic-parent-class-from-import")
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
    fun `class-with-parametrized-parent-class-from-import`() {
        // given
        val sut =
            getSnippetFile("class-with-parametrized-parent-class-from-import")
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
    fun `class-with-parametrized-and-generic-parent-class-from-import`() {
        // given
        val sut =
            getSnippetFile("class-with-parametrized-and-generic-parent-class-from-import")
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
    fun `class-with-multiline-parent-from-import`() {
        // given
        val sut =
            getSnippetFile("class-with-multiline-parent-from-import")
                .classes()
                .parents()
                .first()

        // then
        sut
            .name
            .shouldBeEqualTo("SampleClassWithParameter")
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
