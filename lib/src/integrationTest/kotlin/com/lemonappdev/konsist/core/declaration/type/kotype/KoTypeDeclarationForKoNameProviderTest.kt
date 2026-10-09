package com.lemonappdev.konsist.core.declaration.type.kotype

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.assertSoftly
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
        assertSoftly(sut) {
            it?.name shouldBeEqualTo value
            it?.hasName(value) shouldBeEqualTo true
            it?.hasName(value.lowercase()) shouldBeEqualTo false
            it?.hasName(value.lowercase(), ignoreCase = true) shouldBeEqualTo true
        }
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
                arguments("nullable-class-type-name", "FixtureType"),
                arguments("not-nullable-class-type-name", "FixtureType"),
                arguments("nullable-interface-type-name", "FixtureInterface"),
                arguments("not-nullable-interface-type-name", "FixtureInterface"),
                arguments("nullable-object-type-name", "FixtureObject"),
                arguments("not-nullable-object-type-name", "FixtureObject"),
                arguments("nullable-function-type-name", "(FixtureObject) -> Unit"),
                arguments("not-nullable-function-type-name", "(FixtureObject) -> Unit"),
                arguments("nullable-import-alias-type-name", "ImportAlias"),
                arguments("not-nullable-import-alias-type-name", "ImportAlias"),
                arguments("nullable-typealias-type-name", "FixtureTypeAlias"),
                arguments("not-nullable-typealias-type-name", "FixtureTypeAlias"),
                arguments("nullable-type-parameter-name", "TestType"),
                arguments("not-nullable-type-parameter-name", "TestType"),
                arguments("nullable-external-type-name", "FixtureExternalClass"),
                arguments("not-nullable-external-type-name", "FixtureExternalClass"),
            )
    }
}
