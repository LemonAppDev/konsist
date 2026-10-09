package com.lemonappdev.konsist.core.declaration.type.kotype

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.MethodSource

class KoTypeDeclarationForKoTextProviderTest {
    @ParameterizedTest
    @MethodSource("provideValues")
    fun `text`(
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
        sut?.text shouldBeEqualTo value
    }

    private fun getSnippetFile(fileName: String) = getSnippetKoScope("core/declaration/type/kotype/snippet/forkotextprovider/", fileName)

    companion object {
        @Suppress("unused")
        @JvmStatic
        fun provideValues() =
            listOf(
                arguments("nullable-kotlin-type-text", "String?"),
                arguments("not-nullable-kotlin-type-text", "String"),
                arguments("nullable-generic-type-text", "List<Set<String>>?"),
                arguments("not-nullable-generic-type-text", "List<Set<String>>"),
                arguments("nullable-class-type-text", "FixtureType?"),
                arguments("not-nullable-class-type-text", "FixtureType"),
                arguments("nullable-interface-type-text", "FixtureInterface?"),
                arguments("not-nullable-interface-type-text", "FixtureInterface"),
                arguments("nullable-object-type-text", "FixtureObject?"),
                arguments("not-nullable-object-type-text", "FixtureObject"),
                arguments("nullable-function-type-text", "((FixtureObject) -> Unit)?"),
                arguments("not-nullable-function-type-text", "(FixtureObject) -> Unit"),
                arguments("nullable-import-alias-type-text", "ImportAlias?"),
                arguments("not-nullable-import-alias-type-text", "ImportAlias"),
                arguments("nullable-typealias-type-text", "FixtureTypeAlias?"),
                arguments("not-nullable-typealias-type-text", "FixtureTypeAlias"),
                arguments("nullable-type-parameter-text", "TestType?"),
                arguments("not-nullable-type-parameter-text", "TestType"),
                arguments("nullable-external-type-text", "FixtureExternalClass?"),
                arguments("not-nullable-external-type-text", "FixtureExternalClass"),
            )
    }
}
