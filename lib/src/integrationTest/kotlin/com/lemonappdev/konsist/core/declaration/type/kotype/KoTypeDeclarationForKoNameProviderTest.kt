package com.lemonappdev.konsist.core.declaration.type.kotype

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.MethodSource

class KoTypeDeclarationForKoNameProviderTest {
    @ParameterizedTest
    @MethodSource("provideValues")
    fun `name`(
        fileName: String,
        value: String,
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
        sut?.name shouldBeEqualTo value
    }

    private fun getSnippetFile(fileName: String) = getSnippetKoScope("core/declaration/type/kotype/snippet/forkonameprovider/", fileName)

    companion object {
        @Suppress("unused")
        @JvmStatic
        fun provideValues() =
            listOf(
                arguments("nullable-kotlin-type-name", "String"),
                arguments("not-nullable-kotlin-type-name", "String"),
                arguments("nullable-generic-type-name", "List<Set<String>>"),
                arguments("not-nullable-generic-type-name", "List<Set<String>>"),
                arguments("nullable-class-type-name", "SampleType"),
                arguments("not-nullable-class-type-name", "SampleType"),
                arguments("nullable-interface-type-name", "SampleInterface"),
                arguments("not-nullable-interface-type-name", "SampleInterface"),
                arguments("nullable-object-type-name", "SampleObject"),
                arguments("not-nullable-object-type-name", "SampleObject"),
                arguments("nullable-function-type-name", "(SampleObject) -> Unit"),
                arguments("not-nullable-function-type-name", "(SampleObject) -> Unit"),
                arguments("nullable-import-alias-type-name", "ImportAlias"),
                arguments("not-nullable-import-alias-type-name", "ImportAlias"),
                arguments("nullable-typealias-type-name", "SampleTypeAlias"),
                arguments("not-nullable-typealias-type-name", "SampleTypeAlias"),
                arguments("nullable-external-type-name", "SampleExternalClass"),
                arguments("not-nullable-external-type-name", "SampleExternalClass"),
            )
    }
}
