package com.lemonappdev.konsist.core.declaration.type.kotype

import com.lemonappdev.konsist.TestSnippetProvider
import io.kotest.matchers.equals.shouldBeEqual
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.MethodSource

class KoTypeDeclarationForKoTypeProviderTest {
    @ParameterizedTest
    @MethodSource("provideValues")
    fun `kotlin-type`(
        fileName: String,
        isClass: Boolean,
        isObject: Boolean,
        isInterface: Boolean,
        isTypeAlias: Boolean,
        isImportAlias: Boolean,
        isKotlinType: Boolean,
        isKotlinBasicType: Boolean,
        isKotlinCollectionType: Boolean,
        isFunctionType: Boolean,
        isGenericType: Boolean,
        isExternalType: Boolean,
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
            it?.isClass shouldBeEqualTo isClass
            it?.isObject shouldBeEqualTo isObject
            it?.isInterface shouldBeEqualTo isInterface
            it?.isTypeAlias shouldBeEqualTo isTypeAlias
            it?.isImportAlias shouldBeEqualTo isImportAlias
            it?.isKotlinType shouldBeEqualTo isKotlinType
            it?.isKotlinBasicType shouldBeEqualTo isKotlinBasicType
            it?.isKotlinCollectionType shouldBeEqualTo isKotlinCollectionType
            it?.isFunctionType shouldBeEqualTo isFunctionType
            it?.isGenericType shouldBeEqualTo isGenericType
            it?.isExternalType shouldBeEqualTo isExternalType
        }
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("core/declaration/type/kotype/snippet/forkokotlintypeprovider/", fileName)

    companion object {
        @Suppress("unused")
        @JvmStatic
        fun provideValues() =
            listOf(
                arguments(
                    "nullable-kotlin-type-is-kotlin-type",
                    false,
                    false,
                    false,
                    false,
                    false,
                    true,
                    true,
                    false,
                    false,
                    false,
                    false
                ),
                arguments(
                    "not-nullable-kotlin-type-is-kotlin-type",
                    false,
                    false,
                    false,
                    false,
                    false,
                    true,
                    true,
                    false,
                    false,
                    false,
                    false
                ),
//                arguments("nullable-generic-type-is-kotlin-type", false, false, false, false, false, true, false, true, false, false, false),
//                arguments("not-nullable-generic-type-is-kotlin-type", false, false, false, false, false, true, false, true, false, false, false),
                arguments(
                    "nullable-class-type-is-not-kotlin-type",
                    true,
                    false,
                    false,
                    false,
                    false,
                    false,
                    false,
                    false,
                    false,
                    false,
                    false
                ),
                arguments(
                    "not-nullable-class-type-is-not-kotlin-type",
                    true,
                    false,
                    false,
                    false,
                    false,
                    false,
                    false,
                    false,
                    false,
                    false,
                    false
                ),
                arguments(
                    "nullable-interface-type-is-not-kotlin-type",
                    false,
                    false,
                    true,
                    false,
                    false,
                    false,
                    false,
                    false,
                    false,
                    false,
                    false
                ),
                arguments(
                    "not-nullable-interface-type-is-not-kotlin-type",
                    false,
                    false,
                    true,
                    false,
                    false,
                    false,
                    false,
                    false,
                    false,
                    false,
                    false
                ),
                arguments(
                    "nullable-object-type-is-not-kotlin-type",
                    false,
                    true,
                    false,
                    false,
                    false,
                    false,
                    false,
                    false,
                    false,
                    false,
                    false
                ),
                arguments(
                    "not-nullable-object-type-is-not-kotlin-type",
                    false,
                    true,
                    false,
                    false,
                    false,
                    false,
                    false,
                    false,
                    false,
                    false,
                    false
                ),
                arguments(
                    "nullable-function-type-type-is-not-kotlin-type",
                    false,
                    false,
                    false,
                    false,
                    false,
                    false,
                    false,
                    false,
                    true,
                    false,
                    false
                ),
                arguments(
                    "not-nullable-function-type-type-is-not-kotlin-type",
                    false,
                    false,
                    false,
                    false,
                    false,
                    false,
                    false,
                    false,
                    true,
                    false,
                    false
                ),
                arguments(
                    "nullable-import-alias-type-is-not-kotlin-type",
                    false,
                    false,
                    false,
                    false,
                    true,
                    false,
                    false,
                    false,
                    false,
                    false,
                    false
                ),
                arguments(
                    "not-nullable-import-alias-type-is-not-kotlin-type",
                    false,
                    false,
                    false,
                    false,
                    true,
                    false,
                    false,
                    false,
                    false,
                    false,
                    false
                ),
                arguments(
                    "nullable-typealias-type-is-not-kotlin-type",
                    false,
                    false,
                    false,
                    true,
                    false,
                    false,
                    false,
                    false,
                    false,
                    false,
                    false
                ),
                arguments(
                    "not-nullable-typealias-type-is-not-kotlin-type",
                    false,
                    false,
                    false,
                    true,
                    false,
                    false,
                    false,
                    false,
                    false,
                    false,
                    false
                ),
                arguments(
                    "nullable-external-type-is-not-kotlin-type",
                    false,
                    false,
                    false,
                    false,
                    false,
                    false,
                    false,
                    false,
                    false,
                    false,
                    true
                ),
                arguments(
                    "not-nullable-external-type-is-not-kotlin-type",
                    false,
                    false,
                    false,
                    false,
                    false,
                    false,
                    false,
                    false,
                    false,
                    false,
                    true
                ),
            )
    }
}
