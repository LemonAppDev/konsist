package com.lemonappdev.konsist.core.declaration.kocomplexdeclaration

import com.lemonappdev.konsist.TestSnippetProvider
import com.lemonappdev.konsist.api.declaration.KoComplexDeclaration
import com.lemonappdev.konsist.core.ext.toNormalizedPath
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.MethodSource

class KoComplexDeclarationForRepresentsTypeTest {
    @ParameterizedTest
    @MethodSource("provideValues")
    fun `represents-type`(
        fileName: String,
        declarationName: String,
        type: String,
        value: Boolean,
    ) {
        // given
        val sut = getSnippetFile(fileName)
            .declarations(includeNested = true)
            .filterIsInstance<KoComplexDeclaration>()
            .first { it.name == declarationName }

        // then
        sut.representsType(type) shouldBeEqualTo value
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope(
            "core/declaration/kocomplexdeclaration/snippet/forrepresentstype/".toNormalizedPath(),
            fileName,
        )

    companion object {
        @Suppress("unused")
        @JvmStatic
        fun provideValues() = listOf(
            arguments("class-represents-type", "SampleClass", "SampleClass", true),
            arguments("class-represents-type", "SampleClass", "OtherClass", false),
            arguments("class-represents-type", "SampleClass", "com.lemonappdev.konsist.testdata.SampleClass", true),
            arguments("class-represents-type", "SampleClass", "com.lemonappdev.konsist.testdata.OtherClass", false),
            arguments("interface-represents-type", "SampleInterface", "SampleInterface", true),
            arguments("interface-represents-type", "SampleInterface", "OtherInterface", false),
            arguments("interface-represents-type", "SampleInterface", "com.lemonappdev.konsist.testdata.SampleInterface", true),
            arguments("interface-represents-type", "SampleInterface", "com.lemonappdev.konsist.testdata.OtherInterface", false),
            arguments("object-represents-type", "SampleObject", "SampleObject", true),
            arguments("object-represents-type", "SampleObject", "OtherObject", false),
            arguments("object-represents-type", "SampleObject", "com.lemonappdev.konsist.testdata.SampleObject", true),
            arguments("object-represents-type", "SampleObject", "com.lemonappdev.konsist.testdata.OtherObject", false),
        )
    }
}
