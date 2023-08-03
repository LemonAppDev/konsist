package com.lemonappdev.konsist.core.declaration.kotype

import com.lemonappdev.konsist.TestSnippetProvider
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.MethodSource

class KoTypeDeclarationForKoNameProviderTest {
    @ParameterizedTest
    @MethodSource("provideValues")
    fun `type-name`(
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
        sut?.name shouldBeEqualTo value
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("core/declaration/kotypedeclaration/snippet/forkonameprovider/", fileName)

    companion object {
        @Suppress("unused")
        @JvmStatic
        fun provideValues() = listOf(
            arguments("simple-type", "SampleType"),
            arguments("simple-nullable-type", "SampleType?"),
            arguments("simple-list-type", "List<SampleType?>"),
            arguments("simple-nullable-list-type", "List<SampleType?>?"),
            arguments("import-alias", "ImportAlias"),
            arguments("nullable-import-alias", "ImportAlias?"),
        )
    }
}
