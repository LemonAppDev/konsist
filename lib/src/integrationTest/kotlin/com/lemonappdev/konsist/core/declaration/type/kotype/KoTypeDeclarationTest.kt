package com.lemonappdev.konsist.core.declaration.type.kotype

import com.lemonappdev.konsist.TestSnippetProvider
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.MethodSource

class KoTypeDeclarationTest {
    @ParameterizedTest
    @MethodSource("provideValues")
    fun `to-string`(
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
        sut?.toString() shouldBeEqualTo value
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("core/declaration/type/kotype/snippet/forgeneral/", fileName)

    companion object {
        @Suppress("unused")
        @JvmStatic
        fun provideValues() =
            listOf(
                arguments("nullable-kotlin-type", "String?"),
                arguments("not-nullable-kotlin-type", "String"),
                arguments("nullable-generic-type", "List<Set<String>>?"),
                arguments("not-nullable-generic-type", "List<Set<String>>"),
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
    }
}

