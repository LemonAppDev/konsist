package com.lemonappdev.konsist.core.declaration.kotype

import com.lemonappdev.konsist.TestSnippetProvider
import com.lemonappdev.konsist.api.ext.list.withPrimaryConstructor
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
        val sut =
            getSnippetFile(fileName)
                .classes()
                .withPrimaryConstructor()
                .first()
                .primaryConstructor
                ?.parameters
                ?.first()
                ?.type

        // then
        sut?.fullyQualifiedName shouldBeEqualTo value
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("core/declaration/kotype/snippet/forkofullyqualifiednameprovider/", fileName)

    companion object {
        @Suppress("unused")
        @JvmStatic
        fun provideValues() =
            listOf(
                arguments("simple-type", "com.lemonappdev.konsist.testdata.SampleType"),
                arguments("simple-nullable-type", "com.lemonappdev.konsist.testdata.SampleType"),
                arguments("simple-list-type", "List"),
                arguments("simple-nullable-list-type", "List"),
                arguments("import-alias", "com.lemonappdev.konsist.testdata.SampleType"),
                arguments("nullable-import-alias", "com.lemonappdev.konsist.testdata.SampleType"),
                arguments("non-nullable-type-without-import-and-with-package", "com.samplepackage.SampleTypeClass"),
                arguments("nullable-type-without-import-and-with-package", "com.samplepackage.SampleTypeClass"),
                arguments("non-nullable-type-without-import-and-package", "SampleTypeClass"),
                arguments("nullable-type-without-import-and-package", "SampleTypeClass"),
                arguments("non-nullable-type-in-nested-classes-with-package", "com.samplepackage.SampleClass.SampleNestedType"),
                arguments("nullable-type-in-nested-classes-with-package", "com.samplepackage.SampleClass.SampleNestedType"),
                arguments("non-nullable-type-in-nested-classes-without-package", "SampleClass.SampleNestedType"),
                arguments("nullable-type-in-nested-classes-without-package", "SampleClass.SampleNestedType"),
            )
    }
}
