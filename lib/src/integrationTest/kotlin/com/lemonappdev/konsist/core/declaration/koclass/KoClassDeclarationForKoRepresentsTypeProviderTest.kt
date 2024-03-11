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
        value: Boolean,
    ) {
        // given
        val sut =
            getSnippetFile("class-represents-type")
                .classes()
                .first()

        // then
        sut.representsType(type) shouldBeEqualTo value
    }

    @Suppress("SameParameterValue")
    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/koclass/snippet/forkorepresentstypeprovider/", fileName)

    companion object {
        @Suppress("unused")
        @JvmStatic
        fun provideValues() =
            listOf(
                arguments("SampleClass", true),
                arguments("OtherClass", false),
                arguments("com.lemonappdev.konsist.testdata.SampleClass", true),
                arguments("com.lemonappdev.konsist.testdata.OtherClass", false),
                arguments(null, false),
            )
    }
}
