package com.lemonappdev.konsist.api.ext.koscope

import com.lemonappdev.konsist.TestSnippetProvider
import com.lemonappdev.konsist.api.declaration.KoBaseDeclaration
import com.lemonappdev.konsist.api.declaration.KoClassDeclaration
import com.lemonappdev.konsist.api.provider.KoBaseProvider
import com.lemonappdev.konsist.api.provider.KoKDocProvider
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.MethodSource

class KoScopeExtTest {
    @ParameterizedTest
    @MethodSource("provideValues")
    fun `scope-contains-all-nested-and-local-base-declarations`(
        includeNested: Boolean,
        includeLocal: Boolean,
    ) {
        // given
        val sut = getSnippetFile("scope-contains-all-nested-and-local-base-declarations")

        // then
        sut.declarationsOf<KoBaseDeclaration>(includeNested = includeNested, includeLocal = includeLocal)
            .shouldBeEqualTo(sut.declarations(includeNested, includeLocal))
    }

    @ParameterizedTest
    @MethodSource("provideValues")
    fun `scope-contains-all-nested-and-local-classes`(
        includeNested: Boolean,
        includeLocal: Boolean,
    ) {
        // given
        val sut = getSnippetFile("scope-contains-all-nested-and-local-classes")

        // then
        sut.declarationsOf<KoClassDeclaration>(includeNested = includeNested, includeLocal = includeLocal)
            .shouldBeEqualTo(sut.classes(includeNested, includeLocal))
    }

    @ParameterizedTest
    @MethodSource("provideValues")
    fun `scope-contains-all-nested-and-local-declarations-which-implement-base-provider`(
        includeNested: Boolean,
        includeLocal: Boolean,
    ) {
        // given
        val sut =
            getSnippetFile("scope-contains-all-nested-and-local-declarations-which-implement-base-provider")

        // then
        sut.declarationsOf<KoBaseProvider>(includeNested = includeNested, includeLocal = includeLocal)
            .shouldBeEqualTo(sut.declarations(includeNested, includeLocal))
    }

    @ParameterizedTest
    @MethodSource("provideValues")
    fun `scope-contains-all-nested-and-local-declarations-which-implement-kdoc-provider`(
        includeNested: Boolean,
        includeLocal: Boolean,
    ) {
        // given
        val sut =
            getSnippetFile("scope-contains-all-nested-and-local-declarations-which-implement-kdoc-provider")

        // then
        sut.declarationsOf<KoKDocProvider>(includeNested = includeNested, includeLocal = includeLocal)
            .shouldBeEqualTo(sut.declarations(includeNested, includeLocal).filterIsInstance<KoKDocProvider>())
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("api/ext/koscope/snippet/", fileName)

    companion object {
        @Suppress("unused", "detekt.LongMethod")
        @JvmStatic
        fun provideValues() = listOf(
            arguments(false, false),
            arguments(true, false),
            arguments(false, true),
            arguments(true, true),
        )
    }
}
