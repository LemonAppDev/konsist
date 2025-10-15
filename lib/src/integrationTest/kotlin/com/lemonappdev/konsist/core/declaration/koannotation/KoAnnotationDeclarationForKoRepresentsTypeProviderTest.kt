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
        ignoreCase: Boolean,
        value: Boolean,
    ) {
        // given
        val sut =
            getSnippetFile("annotation-represents-type")
                .functions()
                .first()
                .annotations
                .first()

        // then
        sut.representsType(type, ignoreCase) shouldBeEqualTo value
    }

    @Suppress("SameParameterValue")
    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/koannotation/snippet/forkorepresentstypeprovider/", fileName)

    companion object {
        @Suppress("unused")
        @JvmStatic
        fun provideValues() =
            listOf(
                arguments("SampleAnnotation", false, true),
                arguments("sampleannotation", false, false),
                arguments("sampleannotation", true, true),
                arguments("OtherAnnotation", false, false),
                arguments("otherannotation", false, false),
                arguments("otherannotation", true, false),
                arguments("com.lemonappdev.konsist.testdata.SampleAnnotation", false, true),
                arguments("com.lemonappdev.konsist.testdata.sampleannotation", false, false),
                arguments("com.lemonappdev.konsist.testdata.sampleannotation", true, true),
                arguments("com.lemonappdev.konsist.testdata.OtherAnnotation", false, false),
                arguments("com.lemonappdev.konsist.testdata.otherannotation", false, false),
                arguments("com.lemonappdev.konsist.testdata.otherannotation", true, false),
                arguments(null, false, false),
                arguments(null, true, false),
            )
    }
}
