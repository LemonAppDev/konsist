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
        ignoreCase: Boolean,
        value: Boolean,
    ) {
        // given
        val sut =
            getSnippetFile("object-represents-type")
                .objects()
                .first()

        // then
        sut.representsType(type, ignoreCase) shouldBeEqualTo value
    }

    @Suppress("SameParameterValue")
    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/koobject/snippet/forkorepresentstypeprovider/", fileName)

    companion object {
        @Suppress("unused")
        @JvmStatic
        fun provideValues() =
            listOf(
                arguments("SampleObject", false, true),
                arguments("sampleobject", false, false),
                arguments("sampleobject", true, true),
                arguments("OtherObject", false, false),
                arguments("otherobject", false, false),
                arguments("otherobject", true, false),
                arguments("com.lemonappdev.konsist.testdata.SampleObject", false, true),
                arguments("com.lemonappdev.konsist.testdata.sampleobject", false, false),
                arguments("com.lemonappdev.konsist.testdata.sampleobject", true, true),
                arguments("com.lemonappdev.konsist.testdata.OtherObject", false, false),
                arguments("com.lemonappdev.konsist.testdata.otherobject", false, false),
                arguments("com.lemonappdev.konsist.testdata.otherobject", true, false),
                arguments(null, false, false),
                arguments(null, true, false),
            )
    }
}
