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
                arguments("FixtureObject", false, true),
                arguments("fixtureobject", false, false),
                arguments("fixtureobject", true, true),
                arguments("OtherObject", false, false),
                arguments("otherobject", false, false),
                arguments("otherobject", true, false),
                arguments("com.lemonappdev.konsist.testdata.FixtureObject", false, true),
                arguments("com.lemonappdev.konsist.testdata.fixtureobject", false, false),
                arguments("com.lemonappdev.konsist.testdata.fixtureobject", true, true),
                arguments("com.lemonappdev.konsist.testdata.OtherObject", false, false),
                arguments("com.lemonappdev.konsist.testdata.otherobject", false, false),
                arguments("com.lemonappdev.konsist.testdata.otherobject", true, false),
                arguments(null, false, false),
                arguments(null, true, false),
            )
    }
}
