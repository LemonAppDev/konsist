package com.lemonappdev.konsist.core.declaration.kotype

import com.lemonappdev.konsist.TestSnippetProvider
import com.lemonappdev.konsist.api.ext.list.withPrimaryConstructor
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.MethodSource

class KoTypeDeclarationForKoFullyQualifiedNameProviderTest {
    @ParameterizedTest(name = "fully-qualified-name {0} equals {1}")
    @MethodSource("provideValues")
    fun `fully-qualified-name`(
        fileName: String,
        value: String,
    ) {
        // given
        val sut = getSnippetFile(fileName)
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

    @Test
    fun `type-fully-qualified-name-when-other-import-contains-its-name`() {
        // given
        val sut = getSnippetFile("type-fully-qualified-name-when-other-import-contains-its-name")
            .classes()
            .withPrimaryConstructor()
            .first()
            .primaryConstructor
            ?.parameters
            ?.first()
            ?.type

        // then
        sut?.fullyQualifiedName shouldBeEqualTo "com.lemonappdev.konsist.testdata.testpackage.Type"
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("core/declaration/kotype/snippet/forkofullyqualifiednameprovider/", fileName)

    companion object {
        @Suppress("unused")
        @JvmStatic
        fun provideValues() = listOf(
            arguments("type", "com.lemonappdev.konsist.testdata.SampleType"),
            arguments("nullable-type", "com.lemonappdev.konsist.testdata.SampleType"),
            arguments("list-type", "List"),
            arguments("nullable-list-type", "List"),
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
