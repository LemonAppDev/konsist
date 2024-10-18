package com.lemonappdev.konsist.core.declaration.kotypeargument

import com.lemonappdev.konsist.TestSnippetProvider
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.MethodSource

class KoTypeArgumentDeclarationForKoTypeProviderTest {
    @ParameterizedTest
    @MethodSource("provideValues")
    @Suppress("detekt.LongParameterList")
    fun `type-argument`(
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
                ?.typeArguments
                ?.firstOrNull()

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
        }
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("core/declaration/kotypeargument/snippet/forkotypeprovider/", fileName)

    companion object {
        @Suppress("unused", "detekt.LongMethod")
        @JvmStatic
        fun provideValues() =
            listOf(
                arguments(
                    "kotlin-type-argument",
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
                    "class-type-argument",
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
                    "interface-type-argument",
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
                    "object-type-argument",
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
                    "function-type-argument",
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
                    "import-alias-type-argument",
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
                    "typealias-type-argument",
                    false,
                    false,
                    false,
                    true,
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
                    "parameter-type-argument",
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
                    "external-type-argument",
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
