package com.lemonappdev.konsist.api.ext.declaration.kocomplexdeclaration

import com.lemonappdev.konsist.TestSnippetProvider
import com.lemonappdev.konsist.api.provider.KoRepresentsTypeProvider
import org.amshove.kluent.assertSoftly
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.MethodSource

class KoComplexDeclarationExtTest {
    @ParameterizedTest
    @MethodSource("provideValues")
    fun `represents-type`(
        fileName: String,
        valueForSampleClass: Boolean,
        valueForSampleInterface: Boolean,
        valueForSampleObject: Boolean,
    ) {
        // given
        val sut = getSnippetFile(fileName)
            .declarations(includeNested = true)
            .filterIsInstance<KoRepresentsTypeProvider>()
            .first()

        // then
        assertSoftly(sut) {
//            representsTypeOf<SampleClass>() shouldBeEqualTo valueForSampleClass
//            representsTypeOf<SampleInterface>() shouldBeEqualTo valueForSampleInterface
//            representsTypeOf<SampleObject>() shouldBeEqualTo valueForSampleObject
        }
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("api/ext/declaration/kocomplexdeclaration/snippet/", fileName)

    companion object {
        @Suppress("unused")
        @JvmStatic
        fun provideValues() = listOf(
            arguments("class-represents-type", true, false, false),
            arguments("interface-represents-type", false, true, false),
            arguments("object-represents-type", false, false, true),
        )
    }
}
