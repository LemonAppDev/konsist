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
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldBeInstanceOf
import org.amshove.kluent.shouldNotBeInstanceOf
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.MethodSource
import kotlin.reflect.KClass

class KoTypeDeclarationForKoSourceDeclarationProviderTest {
    @ParameterizedTest
    @MethodSource("provideValues")
    fun `source declaration`(
        fileName: String,
        instanceOf: KClass<*>,
        notInstanceOf: KClass<*>,
    ) {
        // given
        val sut = getSnippetFile(fileName)
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
        val sut = getSnippetFile("nullable-kotlin-basic-type")
            .classes()
            .first()
            .primaryConstructor
            ?.parameters
            ?.first()
            ?.type

        // then
        assertSoftly(sut) {
            it?.sourceKotlinType shouldBeInstanceOf KoKotlinTypeDeclaration::class
            it?.sourceKotlinType?.name shouldBeEqualTo "String"
            it?.sourceClass shouldBeEqualTo null
            it?.sourceObject shouldBeEqualTo null
            it?.sourceInterface shouldBeEqualTo null
            it?.sourceTypeAlias shouldBeEqualTo null
            it?.sourceImportAlias shouldBeEqualTo null
            it?.sourceFunctionType shouldBeEqualTo null
            it?.sourceExternalType shouldBeEqualTo null
        }
    }

    @Test
    fun `not-nullable-kotlin-basic-type`() {
        // given
        val sut = getSnippetFile("not-nullable-kotlin-basic-type")
            .classes()
            .first()
            .primaryConstructor
            ?.parameters
            ?.first()
            ?.type

        // then
        assertSoftly(sut) {
            it?.sourceKotlinType shouldBeInstanceOf KoKotlinTypeDeclaration::class
            it?.sourceKotlinType?.name shouldBeEqualTo "String"
            it?.sourceClass shouldBeEqualTo null
            it?.sourceObject shouldBeEqualTo null
            it?.sourceInterface shouldBeEqualTo null
            it?.sourceTypeAlias shouldBeEqualTo null
            it?.sourceImportAlias shouldBeEqualTo null
            it?.sourceFunctionType shouldBeEqualTo null
            it?.sourceExternalType shouldBeEqualTo null
        }
    }

    @Test
    fun `nullable-kotlin-collection-type`() {
        // given
        val sut = getSnippetFile("nullable-kotlin-collection-type")
            .classes()
            .first()
            .primaryConstructor
            ?.parameters
            ?.first()
            ?.type

        // then
        assertSoftly(sut) {
            it?.sourceKotlinType shouldBeInstanceOf KoKotlinTypeDeclaration::class
            it?.sourceKotlinType?.name shouldBeEqualTo "List<String>"
            it?.sourceClass shouldBeEqualTo null
            it?.sourceObject shouldBeEqualTo null
            it?.sourceInterface shouldBeEqualTo null
            it?.sourceTypeAlias shouldBeEqualTo null
            it?.sourceImportAlias shouldBeEqualTo null
            it?.sourceFunctionType shouldBeEqualTo null
            it?.sourceExternalType shouldBeEqualTo null
        }
    }

    @Test
    fun `not-nullable-kotlin-collection-type`() {
        // given
        val sut = getSnippetFile("not-nullable-kotlin-collection-type")
            .classes()
            .first()
            .primaryConstructor
            ?.parameters
            ?.first()
            ?.type

        // then
        assertSoftly(sut) {
            it?.sourceKotlinType shouldBeInstanceOf KoKotlinTypeDeclaration::class
            it?.sourceKotlinType?.name shouldBeEqualTo "List<String>"
            it?.sourceClass shouldBeEqualTo null
            it?.sourceObject shouldBeEqualTo null
            it?.sourceInterface shouldBeEqualTo null
            it?.sourceTypeAlias shouldBeEqualTo null
            it?.sourceImportAlias shouldBeEqualTo null
            it?.sourceFunctionType shouldBeEqualTo null
            it?.sourceExternalType shouldBeEqualTo null
        }
    }

    @Test
    fun `nullable-class-type`() {
        // given
        val sut = getSnippetFile("nullable-class-type")
            .classes()
            .first()
            .primaryConstructor
            ?.parameters
            ?.first()
            ?.type

        // then
        assertSoftly(sut) {
            it?.sourceClass shouldBeInstanceOf KoClassDeclaration::class
            it?.sourceClass?.name shouldBeEqualTo "SampleType"
            it?.sourceObject shouldBeEqualTo null
            it?.sourceInterface shouldBeEqualTo null
            it?.sourceTypeAlias shouldBeEqualTo null
            it?.sourceImportAlias shouldBeEqualTo null
            it?.sourceKotlinType shouldBeEqualTo null
            it?.sourceFunctionType shouldBeEqualTo null
            it?.sourceExternalType shouldBeEqualTo null
        }
    }

    @Test
    fun `not-nullable-class-type`() {
        // given
        val sut = getSnippetFile("not-nullable-class-type")
            .classes()
            .first()
            .primaryConstructor
            ?.parameters
            ?.first()
            ?.type

        // then
        assertSoftly(sut) {
            it?.sourceClass shouldBeInstanceOf KoClassDeclaration::class
            it?.sourceClass?.name shouldBeEqualTo "SampleType"
            it?.sourceObject shouldBeEqualTo null
            it?.sourceInterface shouldBeEqualTo null
            it?.sourceTypeAlias shouldBeEqualTo null
            it?.sourceImportAlias shouldBeEqualTo null
            it?.sourceKotlinType shouldBeEqualTo null
            it?.sourceFunctionType shouldBeEqualTo null
            it?.sourceExternalType shouldBeEqualTo null
        }
    }

    @Test
    fun `nullable-interface-type`() {
        // given
        val sut = getSnippetFile("nullable-interface-type")
            .classes()
            .first()
            .primaryConstructor
            ?.parameters
            ?.first()
            ?.type

        // then
        assertSoftly(sut) {
            it?.sourceInterface shouldBeInstanceOf KoInterfaceDeclaration::class
            it?.sourceInterface?.name shouldBeEqualTo "SampleInterface"
            it?.sourceClass shouldBeEqualTo null
            it?.sourceObject shouldBeEqualTo null
            it?.sourceTypeAlias shouldBeEqualTo null
            it?.sourceImportAlias shouldBeEqualTo null
            it?.sourceKotlinType shouldBeEqualTo null
            it?.sourceFunctionType shouldBeEqualTo null
            it?.sourceExternalType shouldBeEqualTo null
        }
    }

    @Test
    fun `not-nullable-interface-type`() {
        // given
        val sut = getSnippetFile("not-nullable-interface-type")
            .classes()
            .first()
            .primaryConstructor
            ?.parameters
            ?.first()
            ?.type

        // then
        assertSoftly(sut) {
            it?.sourceInterface shouldBeInstanceOf KoInterfaceDeclaration::class
            it?.sourceInterface?.name shouldBeEqualTo "SampleInterface"
            it?.sourceClass shouldBeEqualTo null
            it?.sourceObject shouldBeEqualTo null
            it?.sourceTypeAlias shouldBeEqualTo null
            it?.sourceImportAlias shouldBeEqualTo null
            it?.sourceKotlinType shouldBeEqualTo null
            it?.sourceFunctionType shouldBeEqualTo null
            it?.sourceExternalType shouldBeEqualTo null
        }
    }

    @Test
    fun `nullable-object-type`() {
        // given
        val sut = getSnippetFile("nullable-object-type")
            .classes()
            .first()
            .primaryConstructor
            ?.parameters
            ?.first()
            ?.type

        // then
        assertSoftly(sut) {
            it?.sourceObject shouldBeInstanceOf KoObjectDeclaration::class
            it?.sourceObject?.name shouldBeEqualTo "SampleObject"
            it?.sourceClass shouldBeEqualTo null
            it?.sourceInterface shouldBeEqualTo null
            it?.sourceTypeAlias shouldBeEqualTo null
            it?.sourceImportAlias shouldBeEqualTo null
            it?.sourceKotlinType shouldBeEqualTo null
            it?.sourceFunctionType shouldBeEqualTo null
            it?.sourceExternalType shouldBeEqualTo null
        }
    }

    @Test
    fun `not-nullable-object-type`() {
        // given
        val sut = getSnippetFile("not-nullable-object-type")
            .classes()
            .first()
            .primaryConstructor
            ?.parameters
            ?.first()
            ?.type

        // then
        assertSoftly(sut) {
            it?.sourceObject shouldBeInstanceOf KoObjectDeclaration::class
            it?.sourceObject?.name shouldBeEqualTo "SampleObject"
            it?.sourceClass shouldBeEqualTo null
            it?.sourceInterface shouldBeEqualTo null
            it?.sourceTypeAlias shouldBeEqualTo null
            it?.sourceImportAlias shouldBeEqualTo null
            it?.sourceKotlinType shouldBeEqualTo null
            it?.sourceFunctionType shouldBeEqualTo null
            it?.sourceExternalType shouldBeEqualTo null
        }
    }

    @Test
    fun `nullable-function-type`() {
        // given
        val sut = getSnippetFile("nullable-function-type")
            .classes()
            .first()
            .primaryConstructor
            ?.parameters
            ?.first()
            ?.type

        // then
        assertSoftly(sut) {
            it?.sourceFunctionType shouldBeInstanceOf KoFunctionTypeDeclaration::class
            it?.sourceFunctionType?.name shouldBeEqualTo "(SampleObject) -> Unit"
            it?.sourceClass shouldBeEqualTo null
            it?.sourceObject shouldBeEqualTo null
            it?.sourceInterface shouldBeEqualTo null
            it?.sourceTypeAlias shouldBeEqualTo null
            it?.sourceImportAlias shouldBeEqualTo null
            it?.sourceKotlinType shouldBeEqualTo null
            it?.sourceExternalType shouldBeEqualTo null
        }
    }

    @Test
    fun `not-nullable-function-type`() {
        // given
        val sut = getSnippetFile("not-nullable-function-type")
            .classes()
            .first()
            .primaryConstructor
            ?.parameters
            ?.first()
            ?.type

        // then
        assertSoftly(sut) {
            it?.sourceFunctionType shouldBeInstanceOf KoFunctionTypeDeclaration::class
            it?.sourceFunctionType?.name shouldBeEqualTo "(SampleObject) -> Unit"
            it?.sourceClass shouldBeEqualTo null
            it?.sourceObject shouldBeEqualTo null
            it?.sourceInterface shouldBeEqualTo null
            it?.sourceTypeAlias shouldBeEqualTo null
            it?.sourceImportAlias shouldBeEqualTo null
            it?.sourceKotlinType shouldBeEqualTo null
            it?.sourceExternalType shouldBeEqualTo null
        }
    }

    @Test
    fun `nullable-import-alias-type`() {
        // given
        val sut = getSnippetFile("nullable-import-alias-type")
            .classes()
            .first()
            .primaryConstructor
            ?.parameters
            ?.first()
            ?.type

        // then
        assertSoftly(sut) {
            it?.sourceImportAlias shouldBeInstanceOf KoImportAliasDeclaration::class
            it?.sourceImportAlias?.name shouldBeEqualTo "ImportAlias"
            it?.sourceClass shouldBeEqualTo null
            it?.sourceObject shouldBeEqualTo null
            it?.sourceInterface shouldBeEqualTo null
            it?.sourceTypeAlias shouldBeEqualTo null
            it?.sourceKotlinType shouldBeEqualTo null
            it?.sourceFunctionType shouldBeEqualTo null
            it?.sourceExternalType shouldBeEqualTo null
        }
    }

    @Test
    fun `not-nullable-import-alias-type`() {
        // given
        val sut = getSnippetFile("not-nullable-import-alias-type")
            .classes()
            .first()
            .primaryConstructor
            ?.parameters
            ?.first()
            ?.type

        // then
        assertSoftly(sut) {
            it?.sourceImportAlias shouldBeInstanceOf KoImportAliasDeclaration::class
            it?.sourceImportAlias?.name shouldBeEqualTo "ImportAlias"
            it?.sourceClass shouldBeEqualTo null
            it?.sourceObject shouldBeEqualTo null
            it?.sourceInterface shouldBeEqualTo null
            it?.sourceTypeAlias shouldBeEqualTo null
            it?.sourceKotlinType shouldBeEqualTo null
            it?.sourceFunctionType shouldBeEqualTo null
            it?.sourceExternalType shouldBeEqualTo null
        }
    }

    @Test
    fun `nullable-typealias-type`() {
        // given
        val sut = getSnippetFile("nullable-typealias-type")
            .classes()
            .first()
            .primaryConstructor
            ?.parameters
            ?.first()
            ?.type

        // then
        assertSoftly(sut) {
            it?.sourceTypeAlias shouldBeInstanceOf KoTypeAliasDeclaration::class
            it?.sourceTypeAlias?.name shouldBeEqualTo "SampleTypeAlias"
            it?.sourceClass shouldBeEqualTo null
            it?.sourceObject shouldBeEqualTo null
            it?.sourceInterface shouldBeEqualTo null
            it?.sourceImportAlias shouldBeEqualTo null
            it?.sourceKotlinType shouldBeEqualTo null
            it?.sourceFunctionType shouldBeEqualTo null
            it?.sourceExternalType shouldBeEqualTo null
        }
    }

    @Test
    fun `not-nullable-typealias-type`() {
        // given
        val sut = getSnippetFile("not-nullable-typealias-type")
            .classes()
            .first()
            .primaryConstructor
            ?.parameters
            ?.first()
            ?.type

        // then
        assertSoftly(sut) {
            it?.sourceTypeAlias shouldBeInstanceOf KoTypeAliasDeclaration::class
            it?.sourceTypeAlias?.name shouldBeEqualTo "SampleTypeAlias"
            it?.sourceClass shouldBeEqualTo null
            it?.sourceObject shouldBeEqualTo null
            it?.sourceInterface shouldBeEqualTo null
            it?.sourceImportAlias shouldBeEqualTo null
            it?.sourceKotlinType shouldBeEqualTo null
            it?.sourceFunctionType shouldBeEqualTo null
            it?.sourceExternalType shouldBeEqualTo null
        }
    }

    @Test
    fun `nullable-external-type`() {
        // given
        val sut = getSnippetFile("nullable-external-type")
            .classes()
            .first()
            .primaryConstructor
            ?.parameters
            ?.first()
            ?.type

        // then
        assertSoftly(sut) {
            it?.sourceExternalType shouldBeInstanceOf KoExternalDeclaration::class
            it?.sourceExternalType?.name shouldBeEqualTo "SampleExternalClass"
            it?.sourceClass shouldBeEqualTo null
            it?.sourceObject shouldBeEqualTo null
            it?.sourceInterface shouldBeEqualTo null
            it?.sourceTypeAlias shouldBeEqualTo null
            it?.sourceImportAlias shouldBeEqualTo null
            it?.sourceKotlinType shouldBeEqualTo null
            it?.sourceFunctionType shouldBeEqualTo null
        }
    }

    @Test
    fun `not-nullable-external-type`() {
        // given
        val sut = getSnippetFile("not-nullable-external-type")
            .classes()
            .first()
            .primaryConstructor
            ?.parameters
            ?.first()
            ?.type

        // then
        assertSoftly(sut) {
            it?.sourceExternalType shouldBeInstanceOf KoExternalDeclaration::class
            it?.sourceExternalType?.name shouldBeEqualTo "SampleExternalClass"
            it?.sourceClass shouldBeEqualTo null
            it?.sourceObject shouldBeEqualTo null
            it?.sourceInterface shouldBeEqualTo null
            it?.sourceTypeAlias shouldBeEqualTo null
            it?.sourceImportAlias shouldBeEqualTo null
            it?.sourceKotlinType shouldBeEqualTo null
            it?.sourceFunctionType shouldBeEqualTo null
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
        fun provideValues() = listOf(
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
