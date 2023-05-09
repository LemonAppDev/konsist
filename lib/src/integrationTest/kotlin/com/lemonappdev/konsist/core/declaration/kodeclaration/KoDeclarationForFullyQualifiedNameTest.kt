package com.lemonappdev.konsist.core.declaration.kodeclaration

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.MethodSource

class KoDeclarationForFullyQualifiedNameTest {
    @ParameterizedTest
    @MethodSource("provideValues")
    fun `declaration-fully-qualified-name`(
        fileName: String,
        declarationName: String,
        value: String
    ) {
        // given
        val sut = getSnippetFile(fileName)
            .declarations(includeNested = true)
            .first { it.name == declarationName }

        // then
        sut.fullyQualifiedName shouldBeEqualTo value
    }

    @Test
    fun `typealias-has-fully-qualified-name`() {
        // given
        val sut = getSnippetFile("typealias-has-fully-qualified-name")
            .typeAliases()
            .first()

        // then
        sut.fullyQualifiedName shouldBeEqualTo "com.samplepackage.SampleTypeAlias"
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/kodeclaration/snippet/forfullyqualifiedname/", fileName)

    companion object {
        @Suppress("unused")
        @JvmStatic
        fun provideValues() = listOf(
            arguments("class-has-fully-qualified-name", "SampleClass", "com.samplepackage.SampleClass"),
            arguments("companion-object-has-fully-qualified-name", "SampleCompanionObject", "com.samplepackage.SampleCompanionObject"),
            arguments("function-has-fully-qualified-name", "sampleFunction", "com.samplepackage.sampleFunction"),
            arguments("interface-has-fully-qualified-name", "SampleInterface", "com.samplepackage.SampleInterface"),
            arguments("object-has-fully-qualified-name", "SampleObject", "com.samplepackage.SampleObject"),
            arguments("property-has-fully-qualified-name", "sampleProperty", "com.samplepackage.sampleProperty"),
        )
    }
}
