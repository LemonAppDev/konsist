package com.lemonappdev.konsist.core.declaration.koclass

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.MethodSource

class KoClassDeclarationForKoRepresentsTypeProviderTest {
    @ParameterizedTest
    @MethodSource("provideValues")
    fun `class-represents-type`(
        type: String?,
        ignoreCase: Boolean,
        value: Boolean,
    ) {
        // given
        val sut =
            getSnippetFile("class-represents-type")
                .classes()
                .first()

        // then
        sut.representsType(type, ignoreCase) shouldBeEqualTo value
    }

    @Suppress("SameParameterValue")
    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/koclass/snippet/forkorepresentstypeprovider/", fileName)

    companion object {
        @Suppress("unused")
        @JvmStatic
        fun provideValues() =
            listOf(
                arguments("SampleClass", false, true),
                arguments("sampleclass", false, false),
                arguments("sampleclass", true, true),
                arguments("OtherClass", false, false),
                arguments("otherclass", false, false),
                arguments("otherclass", true, false),
                arguments("com.lemonappdev.konsist.testdata.SampleClass", false, true),
                arguments("com.lemonappdev.konsist.testdata.sampleclass", false, false),
                arguments("com.lemonappdev.konsist.testdata.sampleclass", true, true),
                arguments("com.lemonappdev.konsist.testdata.OtherClass", false, false),
                arguments("com.lemonappdev.konsist.testdata.otherclass", false, false),
                arguments("com.lemonappdev.konsist.testdata.otherclass", true, false),
                arguments(null, false, false),
                arguments(null, true, false),
            )
    }
}
