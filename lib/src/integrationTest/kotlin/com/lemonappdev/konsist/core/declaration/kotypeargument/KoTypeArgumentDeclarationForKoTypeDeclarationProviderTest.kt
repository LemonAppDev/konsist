package com.lemonappdev.konsist.core.declaration.kotypeargument

import com.lemonappdev.konsist.TestSnippetProvider
import com.lemonappdev.konsist.api.declaration.KoClassDeclaration
import com.lemonappdev.konsist.api.declaration.KoExternalDeclaration
import com.lemonappdev.konsist.api.declaration.KoImportAliasDeclaration
import com.lemonappdev.konsist.api.declaration.KoInterfaceDeclaration
import com.lemonappdev.konsist.api.declaration.KoObjectDeclaration
import com.lemonappdev.konsist.api.declaration.KoTypeAliasDeclaration
import com.lemonappdev.konsist.api.declaration.KoTypeParameterDeclaration
import com.lemonappdev.konsist.api.declaration.type.KoKotlinTypeDeclaration
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
class KoTypeArgumentDeclarationForKoTypeDeclarationProviderTest {
    @Test
    fun `class-type-argument`() {
        // given
        val sut =
            getSnippetFile("class-type-argument")
                .classes()
                .first()
                .primaryConstructor
                ?.parameters
                ?.first()
                ?.type
                ?.typeArguments
                ?.firstOrNull()

        // then
        assertSoftly(sut) {
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
            it?.asExternalTypeDeclaration() shouldBeEqualTo null
            it?.hasExternalTypeDeclaration() shouldBeEqualTo false
            it?.asTypeParameterDeclaration() shouldBeEqualTo null
            it?.hasTypeParameterDeclaration() shouldBeEqualTo false
        }
    }

    @Test
    fun `object-type-argument`() {
        // given
        val sut =
            getSnippetFile("object-type-argument")
                .classes()
                .first()
                .primaryConstructor
                ?.parameters
                ?.first()
                ?.type
                ?.typeArguments
                ?.firstOrNull()

        // then
        assertSoftly(sut) {
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
            it?.asExternalTypeDeclaration() shouldBeEqualTo null
            it?.hasExternalTypeDeclaration() shouldBeEqualTo false
            it?.asTypeParameterDeclaration() shouldBeEqualTo null
            it?.hasTypeParameterDeclaration() shouldBeEqualTo false
        }
    }

    @Test
    fun `interface-type-argument`() {
        // given
        val sut =
            getSnippetFile("interface-type-argument")
                .classes()
                .first()
                .primaryConstructor
                ?.parameters
                ?.first()
                ?.type
                ?.typeArguments
                ?.firstOrNull()

        // then
        assertSoftly(sut) {
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
            it?.asExternalTypeDeclaration() shouldBeEqualTo null
            it?.hasExternalTypeDeclaration() shouldBeEqualTo false
            it?.asTypeParameterDeclaration() shouldBeEqualTo null
            it?.hasTypeParameterDeclaration() shouldBeEqualTo false
        }
    }

    @Test
    fun `import-alias-type-argument`() {
        // given
        val sut =
            getSnippetFile("import-alias-type-argument")
                .classes()
                .first()
                .primaryConstructor
                ?.parameters
                ?.first()
                ?.type
                ?.typeArguments
                ?.firstOrNull()

        // then
        assertSoftly(sut) {
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
            it?.asExternalTypeDeclaration() shouldBeEqualTo null
            it?.hasExternalTypeDeclaration() shouldBeEqualTo false
            it?.asTypeParameterDeclaration() shouldBeEqualTo null
            it?.hasTypeParameterDeclaration() shouldBeEqualTo false
        }
    }

    @Test
    fun `parameter-type-argument`() {
        // given
        val sut =
            getSnippetFile("parameter-type-argument")
                .classes()
                .first()
                .primaryConstructor
                ?.parameters
                ?.first()
                ?.type
                ?.typeArguments
                ?.firstOrNull()

        // then
        assertSoftly(sut) {
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
            it?.asExternalTypeDeclaration() shouldBeEqualTo null
            it?.hasExternalTypeDeclaration() shouldBeEqualTo false
            it?.asTypeAliasDeclaration() shouldBeEqualTo null
            it?.hasTypeAliasDeclaration() shouldBeEqualTo false
        }
    }

    @Test
    fun `typealias-type-argument`() {
        // given
        val sut =
            getSnippetFile("typealias-type-argument")
                .classes()
                .first()
                .primaryConstructor
                ?.parameters
                ?.first()
                ?.type
                ?.typeArguments
                ?.firstOrNull()

        // then
        assertSoftly(sut) {
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
            it?.asExternalTypeDeclaration() shouldBeEqualTo null
            it?.hasExternalTypeDeclaration() shouldBeEqualTo false
            it?.asTypeParameterDeclaration() shouldBeEqualTo null
            it?.hasTypeParameterDeclaration() shouldBeEqualTo false
        }
    }

    @Test
    fun `kotlin-type-argument`() {
        // given
        val sut =
            getSnippetFile("kotlin-type-argument")
                .classes()
                .first()
                .primaryConstructor
                ?.parameters
                ?.first()
                ?.type
                ?.typeArguments
                ?.firstOrNull()

        // then
        assertSoftly(sut) {
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
            it?.asExternalTypeDeclaration() shouldBeEqualTo null
            it?.hasExternalTypeDeclaration() shouldBeEqualTo false
            it?.hasExternalTypeDeclarationOf(String::class) shouldBeEqualTo false
            it?.asTypeParameterDeclaration() shouldBeEqualTo null
            it?.hasTypeParameterDeclaration() shouldBeEqualTo false
        }
    }

    @Test
    fun `function-type-argument`() {
        // given
        val sut =
            getSnippetFile("function-type-argument")
                .classes()
                .first()
                .primaryConstructor
                ?.parameters
                ?.first()
                ?.type
                ?.typeArguments
                ?.firstOrNull()

        // then
        assertSoftly(sut) {
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
    fun `external-type-argument`() {
        // given
        val sut =
            getSnippetFile("external-type-argument")
                .classes()
                .first()
                .primaryConstructor
                ?.parameters
                ?.first()
                ?.type
                ?.typeArguments
                ?.firstOrNull()

        // then
        assertSoftly(sut) {
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
            it?.asTypeParameterDeclaration() shouldBeEqualTo null
            it?.hasTypeParameterDeclaration() shouldBeEqualTo false
        }
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope(
            "core/declaration/kotypeargument/snippet/forkotypedeclarationprovider/",
            fileName,
        )
}