package com.lemonappdev.konsist.core.declaration.kointerface

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.MethodSource

class KoInterfaceDeclarationForKoRepresentsTypeProviderTest {
    @ParameterizedTest
    @MethodSource("provideValues")
    fun `interface-represents-type`(
        type: String?,
        value: Boolean,
    ) {
        // given
        val sut =
            getSnippetFile("interface-represents-type")
                .interfaces()
                .first()

        // then
        sut.representsType(type) shouldBeEqualTo value
    }

    @Suppress("SameParameterValue")
    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/kointerface/snippet/forkorepresentstypeprovider/", fileName)

    companion object {
        @Suppress("unused")
        @JvmStatic
        fun provideValues() =
            listOf(
                arguments("SampleInterface", true),
                arguments("OtherInterface", false),
                arguments("com.lemonappdev.konsist.testdata.SampleInterface", true),
                arguments("com.lemonappdev.konsist.testdata.OtherInterface", false),
                arguments(null, false),
            )
    }
}
