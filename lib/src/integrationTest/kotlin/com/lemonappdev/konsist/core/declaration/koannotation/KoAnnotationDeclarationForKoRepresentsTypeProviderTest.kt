package com.lemonappdev.konsist.core.declaration.koannotation

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.MethodSource

class KoAnnotationDeclarationForKoRepresentsTypeProviderTest {
    @ParameterizedTest
    @MethodSource("provideValues")
    fun `annotation-represents-type`(
        type: String?,
        value: Boolean,
    ) {
        // given
        val sut = getSnippetFile("annotation-represents-type")
            .functions()
            .first()
            .annotations
            .first()

        // then
        sut.representsType(type) shouldBeEqualTo value
    }

    @Suppress("SameParameterValue")
    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/koannotation/snippet/forkorepresentstypeprovider/", fileName)

    companion object {
        @Suppress("unused")
        @JvmStatic
        fun provideValues() = listOf(
            arguments("SampleAnnotation", true),
            arguments("OtherAnnotation", false),
            arguments("com.lemonappdev.konsist.testdata.SampleAnnotation", true),
            arguments("com.lemonappdev.konsist.testdata.OtherAnnotation", false),
            arguments(null, false),
        )
    }
}
