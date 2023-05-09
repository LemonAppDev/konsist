package com.lemonappdev.konsist.core.declaration.kodeclaration

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.MethodSource

class KoDeclarationForPackageeTest {
    @ParameterizedTest
    @MethodSource("provideValues")
    fun `declaration-packagee`(
        fileName: String,
        declarationName: String,
        value: String,
    ) {
        // given
        val sut = getSnippetFile(fileName)
            .declarations(includeNested = true)
            .first { it.name == declarationName }

        // then
        sut.packagee shouldBeEqualTo value
    }

    @Test
    fun `typealias-is-in-package`() {
        // given
        val sut = getSnippetFile("typealias-is-in-package")
            .typeAliases()
            .first()

        // then
        sut.packagee shouldBeEqualTo "com.samplepackage"
    }

    @Test
    fun `typealias-is-not-in-package`() {
        // given
        val sut = getSnippetFile("typealias-is-not-in-package")
            .typeAliases()
            .first()

        // then
        sut.packagee shouldBeEqualTo ""
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/kodeclaration/snippet/forpackagee/", fileName)

    companion object {
        @Suppress("unused")
        @JvmStatic
        fun provideValues() = listOf(
            arguments("class-is-in-package", "SampleClass", "com.samplepackage"),
            arguments("class-is-not-in-package", "SampleClass", ""),
            arguments("companion-object-is-in-package", "SampleCompanionObject", "com.samplepackage"),
            arguments("companion-object-is-not-in-package", "SampleCompanionObject", ""),
            arguments("function-is-in-package", "sampleFunction", "com.samplepackage"),
            arguments("function-is-not-in-package", "sampleFunction", ""),
            arguments("interface-is-in-package", "SampleInterface", "com.samplepackage"),
            arguments("interface-is-not-in-package", "SampleInterface", ""),
            arguments("object-is-in-package", "SampleObject", "com.samplepackage"),
            arguments("object-is-not-in-package", "SampleObject", ""),
            arguments("property-is-in-package", "sampleProperty", "com.samplepackage"),
            arguments("property-is-not-in-package", "sampleProperty", ""),
        )
    }
}
