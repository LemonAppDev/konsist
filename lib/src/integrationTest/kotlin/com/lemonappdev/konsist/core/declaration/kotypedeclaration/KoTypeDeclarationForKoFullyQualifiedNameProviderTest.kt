package com.lemonappdev.konsist.core.declaration.kotypedeclaration

import com.lemonappdev.konsist.TestSnippetProvider
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.MethodSource

class KoTypeDeclarationForKoFullyQualifiedNameProviderTest {
    @ParameterizedTest
    @MethodSource("provideValues")
    fun `fully-qualified-name`(
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
        sut?.fullyQualifiedName shouldBeEqualTo value
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("core/declaration/kotypedeclaration/snippet/forkofullyqualifiednameprovider/", fileName)

    companion object {
        @Suppress("unused")
        @JvmStatic
        fun provideValues() = listOf(
            arguments("simple-type", "com.lemonappdev.konsist.testdata.SampleType"),
            arguments("simple-nullable-type", "com.lemonappdev.konsist.testdata.SampleType"),
            arguments("simple-list-type", ""),
            arguments("simple-nullable-list-type", ""),
            arguments("import-alias", "com.lemonappdev.konsist.testdata.SampleType"),
            arguments("nullable-import-alias", "com.lemonappdev.konsist.testdata.SampleType"),
        )
    }
}
