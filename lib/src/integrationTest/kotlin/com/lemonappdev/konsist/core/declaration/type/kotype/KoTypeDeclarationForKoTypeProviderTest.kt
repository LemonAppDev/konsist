package com.lemonappdev.konsist.core.declaration.type.kotype

import com.lemonappdev.konsist.TestSnippetProvider
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.MethodSource

class KoTypeDeclarationForKoTypeProviderTest {
    @ParameterizedTest
    @MethodSource("provideValues")
    @Suppress("detekt.LongParameterList")
    fun `type`(
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
        isTypeParameter: Boolean,
        isExternalType: Boolean,
        isStarProjection: Boolean,
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
            it?.isTypeParameter shouldBeEqualTo isTypeParameter
            it?.isExternalType shouldBeEqualTo isExternalType
            it?.isStarProjection shouldBeEqualTo isStarProjection
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
            it?.isClass shouldBeEqualTo false
            it?.isObject shouldBeEqualTo false
            it?.isInterface shouldBeEqualTo false
            it?.isTypeAlias shouldBeEqualTo false
            it?.isImportAlias shouldBeEqualTo false
            it?.isKotlinType shouldBeEqualTo false
            it?.isKotlinBasicType shouldBeEqualTo false
            it?.isKotlinCollectionType shouldBeEqualTo false
            it?.isFunctionType shouldBeEqualTo false
            it?.isGenericType shouldBeEqualTo false
            it?.isTypeParameter shouldBeEqualTo false
            it?.isExternalType shouldBeEqualTo false
            it?.isStarProjection shouldBeEqualTo true
        }
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("core/declaration/type/kotype/snippet/forkotypeprovider/", fileName)

    companion object {
        @Suppress("unused", "detekt.LongMethod")
        @JvmStatic
        fun provideValues() =
            listOf(
                arguments(
                    "nullable-kotlin-type",
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
                    false,
                    false,
                    false,
                ),
                arguments(
                    "not-nullable-kotlin-type",
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
                    false,
                    false,
                    false,
                ),
                arguments(
                    "nullable-class-type",
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
                    false,
                    false,
                    false,
                ),
                arguments(
                    "not-nullable-class-type",
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
                    false,
                    false,
                    false,
                ),
                arguments(
                    "nullable-interface-type",
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
                    false,
                    false,
                    false,
                ),
                arguments(
                    "not-nullable-interface-type",
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
                    false,
                    false,
                    false,
                ),
                arguments(
                    "nullable-object-type",
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
                    false,
                    false,
                    false,
                ),
                arguments(
                    "not-nullable-object-type",
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
                    false,
                    false,
                    false,
                ),
                arguments(
                    "nullable-function-type-type",
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
                    false,
                    false,
                    false,
                ),
                arguments(
                    "not-nullable-function-type-type",
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
                    false,
                    false,
                    false,
                ),
                arguments(
                    "nullable-import-alias-type",
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
                    false,
                    false,
                    false,
                ),
                arguments(
                    "not-nullable-import-alias-type",
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
                    false,
                    false,
                    false,
                ),
                arguments(
                    "nullable-typealias-type",
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
                    false,
                    false,
                    false,
                ),
                arguments(
                    "not-nullable-typealias-type",
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
                    false,
                    false,
                    false,
                ),
                arguments(
                    "nullable-type-parameter",
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
                    true,
                    false,
                    false,
                ),
                arguments(
                    "not-nullable-type-parameter",
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
                    true,
                    false,
                    false,
                ),
                arguments(
                    "nullable-external-type",
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
                    false,
                    true,
                    false,
                ),
                arguments(
                    "not-nullable-external-type",
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
                    false,
                    true,
                    false,
                ),
            )
    }
}
