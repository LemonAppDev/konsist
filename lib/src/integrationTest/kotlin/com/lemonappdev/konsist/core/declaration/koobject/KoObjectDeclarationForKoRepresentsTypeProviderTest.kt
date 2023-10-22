package com.lemonappdev.konsist.core.declaration.koobject

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.MethodSource

class KoObjectDeclarationForKoRepresentsTypeProviderTest {
    @ParameterizedTest
    @MethodSource("provideValues")
    fun `object-represents-type`(
        type: String?,
        value: Boolean,
    ) {
        // given
        val sut = getSnippetFile("object-represents-type")
            .objects()
            .first()

        // then
        sut.representsType(type) shouldBeEqualTo value
    }

    @Suppress("SameParameterValue")
    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/koobject/snippet/forkorepresentstypeprovider/", fileName)

    companion object {
        @Suppress("unused")
        @JvmStatic
        fun provideValues() = listOf(
            arguments("SampleObject", true),
            arguments("OtherObject", false),
            arguments("com.lemonappdev.konsist.testdata.SampleObject", true),
            arguments("com.lemonappdev.konsist.testdata.OtherObject", false),
            arguments(null, false),
        )
    }
}
