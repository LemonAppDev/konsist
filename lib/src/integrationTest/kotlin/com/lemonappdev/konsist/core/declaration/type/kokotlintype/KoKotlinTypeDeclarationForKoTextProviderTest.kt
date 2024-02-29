package com.lemonappdev.konsist.core.declaration.type.kokotlintype

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.MethodSource

class KoKotlinTypeDeclarationForKoTextProviderTest {
    @ParameterizedTest
    @MethodSource("provideValues")
    fun `text`(
        fileName: String,
        value: String,
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
        sut?.text shouldBeEqualTo value
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/type/kokotlintype/snippet/forkotextprovider/", fileName)

    companion object {
        @Suppress("unused")
        @JvmStatic
        fun provideValues() = listOf(
            arguments("nullable-kotlin-basic-type-text", "String?"),
            arguments("not-nullable-kotlin-basic-type-text", "String"),
            arguments("nullable-kotlin-collection-type-text", "List<String>?"),
            arguments("not-nullable-kotlin-collection-type-text", "List<String>"),
            arguments("nullable-class-type-text", "SampleType?"),
            arguments("not-nullable-class-type-text", "SampleType"),
            arguments("nullable-interface-type-text", "SampleInterface?"),
            arguments("not-nullable-interface-type-text", "SampleInterface"),
            arguments("nullable-object-type-text", "SampleObject?"),
            arguments("not-nullable-object-type-text", "SampleObject"),
            arguments("nullable-function-type-type-text", "((SampleObject) -> Unit)?"),
            arguments("not-nullable-function-type-type-text", "(SampleObject)) -> Unit"),
            arguments("nullable-import-alias-type-text", "ImportAlias?"),
            arguments("not-nullable-import-alias-type-text", "ImportAlias"),
            arguments("nullable-typealias-type-text", "SampleTypeAlias?"),
            arguments("not-nullable-typealias-type-text", "SampleTypeAlias"),
            arguments("nullable-external-type-text", "SampleExternalClass?"),
            arguments("not-nullable-external-type-text", "SampleExternalClass"),
        )
    }
}
