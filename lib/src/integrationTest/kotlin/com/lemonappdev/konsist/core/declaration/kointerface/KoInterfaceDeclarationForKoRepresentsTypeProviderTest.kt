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
        ignoreCase: Boolean,
        value: Boolean,
    ) {
        // given
        val sut =
            getSnippetFile("interface-represents-type")
                .interfaces()
                .first()

        // then
        sut.representsType(type, ignoreCase) shouldBeEqualTo value
    }

    @Suppress("SameParameterValue")
    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/kointerface/snippet/forkorepresentstypeprovider/", fileName)

    companion object {
        @Suppress("unused")
        @JvmStatic
        fun provideValues() =
            listOf(
                arguments("FixtureInterface", false, true),
                arguments("fixtureinterface", false, false),
                arguments("fixtureinterface", true, true),
                arguments("OtherInterface", false, false),
                arguments("otherinterface", false, false),
                arguments("otherinterface", true, false),
                arguments("com.lemonappdev.konsist.testdata.FixtureInterface", false, true),
                arguments("com.lemonappdev.konsist.testdata.fixtureinterface", false, false),
                arguments("com.lemonappdev.konsist.testdata.fixtureinterface", true, true),
                arguments("com.lemonappdev.konsist.testdata.OtherInterface", false, false),
                arguments("com.lemonappdev.konsist.testdata.otherinterface", false, false),
                arguments("com.lemonappdev.konsist.testdata.otherinterface", true, false),
                arguments(null, false, false),
                arguments(null, true, false),
            )
    }
}
