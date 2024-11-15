package com.lemonappdev.konsist.core.declaration.type.kostarprojection

import com.lemonappdev.konsist.TestSnippetProvider
import com.lemonappdev.konsist.api.declaration.type.KoStarProjectionDeclaration
import com.lemonappdev.konsist.api.ext.list.returnTypes
import com.lemonappdev.konsist.testdata.SampleClass
import com.lemonappdev.konsist.testdata.SampleType
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoStarProjectionDeclarationForKoDeclarationCastProviderTest {
    @Suppress("detekt.LongMethod")
    @Test
    fun `star-projection-type`() {
        // given
        val sut =
            getSnippetFile("star-projection-type")
                .functions()
                .returnTypes
                .firstOrNull()
                ?.typeArguments
                ?.firstOrNull()
                ?.sourceDeclaration as? KoStarProjectionDeclaration

        // then
        assertSoftly(sut) {
            it?.isClass shouldBeEqualTo false
            it?.isObject shouldBeEqualTo false
            it?.isInterface shouldBeEqualTo false
            it?.isClassOrObject shouldBeEqualTo false
            it?.isClassOrInterface shouldBeEqualTo false
            it?.isInterfaceOrObject shouldBeEqualTo false
            it?.isClassOrInterfaceOrObject shouldBeEqualTo false
            it?.isTypeAlias shouldBeEqualTo false
            it?.isImportAlias shouldBeEqualTo false
            it?.isKotlinType shouldBeEqualTo false
            it?.isKotlinBasicType shouldBeEqualTo false
            it?.isKotlinCollectionType shouldBeEqualTo false
            it?.isTypeParameter shouldBeEqualTo false
            it?.isExternal shouldBeEqualTo false
            it?.asClassDeclaration() shouldBeEqualTo null
            it?.hasClassDeclaration() shouldBeEqualTo false
            it?.hasClassDeclaration { decl -> decl.name == "someName" } shouldBeEqualTo false
            it?.hasClassDeclarationOf(SampleClass::class) shouldBeEqualTo false
            it?.asObjectDeclaration() shouldBeEqualTo null
            it?.hasObjectDeclaration() shouldBeEqualTo false
            it?.hasObjectDeclaration { decl -> decl.name == "someName" } shouldBeEqualTo false
            it?.hasObjectDeclarationOf(SampleType::class) shouldBeEqualTo false
            it?.asInterfaceDeclaration() shouldBeEqualTo null
            it?.hasInterfaceDeclaration() shouldBeEqualTo false
            it?.hasInterfaceDeclaration { decl -> decl.name == "someName" } shouldBeEqualTo false
            it?.hasInterfaceDeclarationOf(SampleType::class) shouldBeEqualTo false
            it?.asClassOrObjectDeclaration() shouldBeEqualTo null
            it?.hasClassOrObjectDeclaration() shouldBeEqualTo false
            it?.hasClassOrObjectDeclaration { decl -> decl.name == "someName" } shouldBeEqualTo false
            it?.hasClassOrObjectDeclarationOf(SampleClass::class) shouldBeEqualTo false
            it?.asClassOrInterfaceDeclaration() shouldBeEqualTo null
            it?.hasClassOrInterfaceDeclaration() shouldBeEqualTo false
            it?.hasClassOrInterfaceDeclaration { decl -> decl.name == "someName" } shouldBeEqualTo false
            it?.hasClassOrInterfaceDeclarationOf(SampleClass::class) shouldBeEqualTo false
            it?.asInterfaceOrObjectDeclaration() shouldBeEqualTo null
            it?.hasInterfaceOrObjectDeclaration() shouldBeEqualTo false
            it?.hasInterfaceOrObjectDeclaration { decl -> decl.name == "someName" } shouldBeEqualTo false
            it?.hasInterfaceOrObjectDeclarationOf(SampleClass::class) shouldBeEqualTo false
            it?.asClassOrInterfaceOrObjectDeclaration() shouldBeEqualTo null
            it?.hasClassOrInterfaceOrObjectDeclaration() shouldBeEqualTo false
            it?.hasClassOrInterfaceOrObjectDeclaration { decl -> decl.name == "someName" } shouldBeEqualTo false
            it?.hasClassOrInterfaceOrObjectDeclarationOf(SampleClass::class) shouldBeEqualTo false
            it?.asTypeAliasDeclaration() shouldBeEqualTo null
            it?.hasTypeAliasDeclaration() shouldBeEqualTo false
            it?.hasTypeAliasDeclaration { decl -> decl.name == "someName" } shouldBeEqualTo false
            it?.asImportAliasDeclaration() shouldBeEqualTo null
            it?.hasImportAliasDeclaration() shouldBeEqualTo false
            it?.hasImportAliasDeclaration { decl -> decl.name == "someName" } shouldBeEqualTo false
            it?.asKotlinTypeDeclaration() shouldBeEqualTo null
            it?.hasKotlinTypeDeclaration() shouldBeEqualTo false
            it?.hasKotlinTypeDeclaration { decl -> decl.name == "someName" } shouldBeEqualTo false
            it?.hasKotlinTypeDeclarationOf(SampleType::class) shouldBeEqualTo false
            it?.asKotlinBasicTypeDeclaration() shouldBeEqualTo null
            it?.hasKotlinBasicTypeDeclaration() shouldBeEqualTo false
            it?.hasKotlinBasicTypeDeclaration { decl -> decl.name == "someName" } shouldBeEqualTo false
            it?.hasKotlinBasicTypeDeclarationOf(SampleType::class) shouldBeEqualTo false
            it?.asKotlinCollectionTypeDeclaration() shouldBeEqualTo null
            it?.hasKotlinCollectionTypeDeclaration() shouldBeEqualTo false
            it?.hasKotlinCollectionTypeDeclaration { decl -> decl.name == "someName" } shouldBeEqualTo false
            it?.hasKotlinCollectionTypeDeclarationOf(SampleType::class) shouldBeEqualTo false
            it?.asExternalDeclaration() shouldBeEqualTo null
            it?.hasExternalDeclaration() shouldBeEqualTo false
            it?.hasExternalDeclaration { decl -> decl.name == "someName" } shouldBeEqualTo false
            it?.asTypeParameterDeclaration() shouldBeEqualTo null
            it?.hasTypeParameterDeclaration() shouldBeEqualTo false
            it?.hasTypeParameterDeclaration { decl -> decl.name == "someName" } shouldBeEqualTo false
        }
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope(
            "core/declaration/type/kostarprojection/snippet/forkodeclarationcastprovider/",
            fileName,
        )
}
