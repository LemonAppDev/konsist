package com.lemonappdev.konsist.core.declaration.type.kotype

import com.lemonappdev.konsist.TestSnippetProvider
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
        isKotlinType: Boolean,
        isKotlinBasicType: Boolean,
        isKotlinCollectionType: Boolean,
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
            it?.isKotlinType shouldBeEqualTo isKotlinType
            it?.isKotlinBasicType shouldBeEqualTo isKotlinBasicType
            it?.isKotlinCollectionType shouldBeEqualTo isKotlinCollectionType
        }
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("core/declaration/type/kotype/snippet/forkokotlintypeprovider/", fileName)

    companion object {
        @Suppress("unused")
        @JvmStatic
        fun provideValues() =
            listOf(
                arguments("nullable-kotlin-basic-type-is-kotlin-type", true, true, false),
                arguments("not-nullable-kotlin-basic-type-is-kotlin-type", true, true, false),
                arguments("nullable-kotlin-collection-type-is-kotlin-type", true, false, true),
                arguments("not-nullable-kotlin-collection-type-is-kotlin-type", true, false, true),
                arguments("nullable-class-type-is-not-kotlin-type", false, false, false),
                arguments("not-nullable-class-type-is-not-kotlin-type", false, false, false),
                arguments("nullable-interface-type-is-not-kotlin-type", false, false, false),
                arguments("not-nullable-interface-type-is-not-kotlin-type", false, false, false),
                arguments("nullable-object-type-is-not-kotlin-type", false, false, false),
                arguments("not-nullable-object-type-is-not-kotlin-type", false, false, false),
                arguments("nullable-function-type-type-is-not-kotlin-type", false, false, false),
                arguments("not-nullable-function-type-type-is-not-kotlin-type", false, false, false),
                arguments("nullable-import-alias-type-is-not-kotlin-type", false, false, false),
                arguments("not-nullable-import-alias-type-is-not-kotlin-type", false, false, false),
                arguments("nullable-typealias-type-is-not-kotlin-type", false, false, false),
                arguments("not-nullable-typealias-type-is-not-kotlin-type", false, false, false),
                arguments("nullable-external-type-is-not-kotlin-type", false, false, false),
                arguments("not-nullable-external-type-is-not-kotlin-type", false, false, false),
            )
    }
}
