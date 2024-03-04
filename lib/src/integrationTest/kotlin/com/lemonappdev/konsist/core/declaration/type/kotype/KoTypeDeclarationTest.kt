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
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.MethodSource
import kotlin.reflect.KClass

class KoTypeDeclarationTest {

    @ParameterizedTest
    @MethodSource("provideValuesForToString")
    fun `to-string`(
        fileName: String,
        value: String,
    ) {
        // given
        val sut = getSnippetFile(fileName)
            .classes()
            .first()
            .primaryConstructor?.parameters
            ?.first()
            ?.type

        // then
        sut?.toString() shouldBeEqualTo value
    }

    @ParameterizedTest
    @MethodSource("provideValuesForDeclaration")
    fun `declaration`(
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

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("core/declaration/type/kotype/snippet/forgeneral/", fileName)

    companion object {
        @Suppress("unused")
        @JvmStatic
        fun provideValuesForToString() = listOf(
            arguments("nullable-kotlin-basic-type", "String?"),
            arguments("not-nullable-kotlin-basic-type", "String"),
            arguments("nullable-kotlin-collection-type", "List<String>?"),
            arguments("not-nullable-kotlin-collection-type", "List<String>"),
            arguments("nullable-class-type", "SampleType?"),
            arguments("not-nullable-class-type", "SampleType"),
            arguments("nullable-interface-type", "SampleInterface?"),
            arguments("not-nullable-interface-type", "SampleInterface"),
            arguments("nullable-object-type", "SampleObject?"),
            arguments("not-nullable-object-type", "SampleObject"),
            arguments("nullable-function-type", "((SampleObject) -> Unit)?"),
            arguments("not-nullable-function-type", "(SampleObject) -> Unit"),
            arguments("nullable-import-alias-type", "ImportAlias?"),
            arguments("not-nullable-import-alias-type", "ImportAlias"),
            arguments("nullable-typealias-type", "SampleTypeAlias?"),
            arguments("not-nullable-typealias-type", "SampleTypeAlias"),
            arguments("nullable-external-type", "SampleExternalClass?"),
            arguments("not-nullable-external-type", "SampleExternalClass"),
        )

        @Suppress("unused")
        @JvmStatic
        fun provideValuesForDeclaration() = listOf(
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
