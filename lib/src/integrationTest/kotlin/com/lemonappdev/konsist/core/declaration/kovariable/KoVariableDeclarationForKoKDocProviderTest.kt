package com.lemonappdev.konsist.core.declaration.kovariable

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import com.lemonappdev.konsist.api.ext.list.enumConstants
import com.lemonappdev.konsist.api.ext.list.getters
import com.lemonappdev.konsist.api.ext.list.initBlocks
import com.lemonappdev.konsist.api.ext.list.setters
import com.lemonappdev.konsist.api.ext.list.variables
import com.lemonappdev.konsist.api.provider.KoVariableProvider
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldNotBeEqualTo
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.MethodSource

class KoVariableDeclarationForKoKDocProviderTest {
    @ParameterizedTest
    @MethodSource("provideValuesForNoKDoc")
    fun `variable-without-kdoc`(declarations: List<KoVariableProvider>) {
        // given
        val sut = declarations
            .variables
            .first()

        // then
        assertSoftly(sut) {
            kDoc shouldBeEqualTo null
            hasKDoc shouldBeEqualTo false
        }
    }

    @ParameterizedTest
    @MethodSource("provideValuesForKDoc")
    fun `variable-with-kdoc`(declarations: List<KoVariableProvider>) {
        // given
        val sut = declarations
            .variables
            .first()

        // then
        assertSoftly(sut) {
            kDoc shouldNotBeEqualTo null
            hasKDoc shouldBeEqualTo true
        }
    }

    @ParameterizedTest
    @MethodSource("provideValuesForOneLineKDoc")
    fun `variable-with-one-line-kdoc`(declarations: List<KoVariableProvider>) {
        // given
        val sut = declarations
            .variables
            .first()

        // then
        assertSoftly(sut) {
            kDoc shouldNotBeEqualTo null
            hasKDoc shouldBeEqualTo true
        }
    }

    companion object {
        private fun getSnippetFile(fileName: String) =
            getSnippetKoScope("core/declaration/kovariable/snippet/forkokdocprovider/", fileName)

        @Suppress("unused")
        @JvmStatic
        fun provideValuesForNoKDoc() = listOf(
            arguments(getSnippetFile("variable-in-function-without-kdoc").functions()),
            arguments(getSnippetFile("variable-in-init-block-without-kdoc").classes().initBlocks),
            arguments(getSnippetFile("variable-in-enum-constant-without-kdoc").classes().enumConstants),
            arguments(getSnippetFile("variable-in-getter-without-kdoc").properties().getters),
            arguments(getSnippetFile("variable-in-setter-without-kdoc").properties().setters),
        )

        @Suppress("unused")
        @JvmStatic
        fun provideValuesForKDoc() = listOf(
            arguments(getSnippetFile("variable-in-function-with-kdoc").functions()),
            arguments(getSnippetFile("variable-in-init-block-with-kdoc").classes().initBlocks),
            arguments(getSnippetFile("variable-in-enum-constant-with-kdoc").classes().enumConstants),
            arguments(getSnippetFile("variable-in-getter-with-kdoc").properties().getters),
            arguments(getSnippetFile("variable-in-setter-with-kdoc").properties().setters),
        )

        @Suppress("unused")
        @JvmStatic
        fun provideValuesForOneLineKDoc() = listOf(
            arguments(getSnippetFile("variable-in-function-with-one-line-kdoc").functions()),
            arguments(getSnippetFile("variable-in-init-block-with-one-line-kdoc").classes().initBlocks),
            arguments(getSnippetFile("variable-in-enum-constant-with-one-line-kdoc").classes().enumConstants),
            arguments(getSnippetFile("variable-in-getter-with-one-line-kdoc").properties().getters),
            arguments(getSnippetFile("variable-in-setter-with-one-line-kdoc").properties().setters),
        )
    }
}
