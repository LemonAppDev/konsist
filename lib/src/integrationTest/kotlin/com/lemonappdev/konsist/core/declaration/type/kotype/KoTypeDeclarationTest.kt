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
                arguments("nullable-class-type", "FixtureType?"),
                arguments("not-nullable-class-type", "FixtureType"),
                arguments("nullable-interface-type", "FixtureInterface?"),
                arguments("not-nullable-interface-type", "FixtureInterface"),
                arguments("nullable-object-type", "FixtureObject?"),
                arguments("not-nullable-object-type", "FixtureObject"),
                arguments("nullable-function-type", "((FixtureObject) -> Unit)?"),
                arguments("not-nullable-function-type", "(FixtureObject) -> Unit"),
                arguments("nullable-import-alias-type", "ImportAlias?"),
                arguments("not-nullable-import-alias-type", "ImportAlias"),
                arguments("nullable-typealias-type", "FixtureTypeAlias?"),
                arguments("not-nullable-typealias-type", "FixtureTypeAlias"),
                arguments("nullable-type-parameter", "TestType?"),
                arguments("not-nullable-type-parameter", "TestType"),
                arguments("nullable-external-type", "FixtureExternalClass?"),
                arguments("not-nullable-external-type", "FixtureExternalClass"),
            )
    }
}
