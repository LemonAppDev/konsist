package com.lemonappdev.konsist.core.declaration.kodeclaration

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import com.lemonappdev.konsist.api.provider.KoPackageDeclarationProvider
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.MethodSource

class KoDeclarationForPackageeTest {
    @ParameterizedTest
    @MethodSource("provideValues")
    fun `declaration-packagee`(
        fileName: String,
        declarationName: String,
        value: String?,
    ) {
        // given
        val sut = getSnippetFile(fileName)
            .declarations(includeNested = true)
            .first { it.name == declarationName } as KoPackageDeclarationProvider

        // then
        sut.packagee?.fullyQualifiedName shouldBeEqualTo value
    }

    private fun getSnippetFile(fileName: String) = getSnippetKoScope("core/declaration/kodeclaration/snippet/forpackagee/", fileName)

    companion object {
        @Suppress("unused")
        @JvmStatic
        fun provideValues() = listOf(
            arguments("class-is-in-package", "SampleClass", "com.samplepackage"),
            arguments("class-is-not-in-package", "SampleClass", null),
            arguments("function-is-in-package", "sampleFunction", "com.samplepackage"),
            arguments("function-is-not-in-package", "sampleFunction", null),
            arguments("interface-is-in-package", "SampleInterface", "com.samplepackage"),
            arguments("interface-is-not-in-package", "SampleInterface", null),
            arguments("object-is-in-package", "SampleObject", "com.samplepackage"),
            arguments("object-is-not-in-package", "SampleObject", null),
            arguments("property-is-in-package", "sampleProperty", "com.samplepackage"),
            arguments("property-is-not-in-package", "sampleProperty", null),
            arguments("typealias-is-in-package", "SampleTypeAlias", "com.samplepackage"),
            arguments("typealias-is-not-in-package", "SampleTypeAlias", null),
        )
    }
}
